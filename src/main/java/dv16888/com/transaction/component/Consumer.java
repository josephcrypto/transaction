package dv16888.com.transaction.component;

import com.alibaba.fastjson.JSONObject;
import dv16888.com.transaction.config.IMqttSender;
import dv16888.com.transaction.config.JmsConfig;
import dv16888.com.transaction.entity.CasinoGameTable;
import dv16888.com.transaction.model.CasinoRecordModel;
import dv16888.com.transaction.services.*;
import dv16888.com.transaction.services.mongoservices.MongoBetCalculationService;
import dv16888.com.transaction.services.reposervices.*;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

@Component
public class Consumer {

    @Autowired
    private IMqttSender iMqttSender;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private GameTableService gameTableService;

    @Autowired
    private BetRecordService betRecordService;

    @Autowired
    private CalculationService calculationService;

    @Autowired
    private UserService userService;

    @Autowired
    private FinanceService financeService;

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private BetCalculationService betCalculationService;

    @Autowired
    private MongoBetCalculationService mongoBetCalculationService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private DefaultMQPushConsumer consumer;

    public static final String CONSUMER_GROUP = "luzhu-records";

    public Consumer() throws MQClientException {
        consumer = new DefaultMQPushConsumer(CONSUMER_GROUP);
        consumer.setNamesrvAddr(JmsConfig.NAME_SERVER);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe(JmsConfig.TOPIC, "*");
        consumer.registerMessageListener((MessageListenerConcurrently)(msgs, context) -> {
            for (Message msg:msgs){
                try {
                    String body = new String(msg.getBody(), "utf-8");
                    logger.info("Consumer-获取消息-主题topic为= " + msg.getTopic() + ", 消费消息为= " + body);

                    LuzhuCalculation luzhuCalculation = getChainsofFunction();

                    try {
                        CasinoRecordModel recordModel = JSONObject.parseObject(body, CasinoRecordModel.class);
                        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
                        String exitOrNot = valueOperations.get(recordModel.getKey()+ "-cal");

                        if (exitOrNot == null) {
                            valueOperations.set(recordModel.getKey()+ "-cal", "1", 3, TimeUnit.DAYS);

                            CasinoGameTable table = gameTableService.getByTableNo(recordModel.getTableNo());
                            luzhuCalculation.calculateLuzhu(recordModel, table);
                        }
                    } catch (Exception e) {
                        System.out.println("Call to Change Of Responsibility Error ========> " + e.getMessage());
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }  catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
        logger.info("Consumer Started =======================>>>");
    }

    private LuzhuCalculation getChainsofFunction(){
        LuzhuCalculation saveZeroToRedis = new SetZeroToRedis(redisTemplate);
        LuzhuCalculation betCalculation = new BetCalculation(betRecordService, calculationService, userService, financeService, agencyService, redisTemplate, betCalculationService, mongoBetCalculationService);
        LuzhuCalculation sendMessageMqtt = new SendMessagetoMqtt(iMqttSender);
        LuzhuCalculation sendMessageToWs = new SendMessageToWs();

        saveZeroToRedis.setNextJob(betCalculation);
        betCalculation.setNextJob(sendMessageMqtt);
        sendMessageMqtt.setNextJob(sendMessageToWs);
        return saveZeroToRedis;
    }

}
