package dv16888.com.transaction.services;

import com.alibaba.fastjson.JSONObject;
import dv16888.com.transaction.config.IMqttSender;
import dv16888.com.transaction.entity.CasinoGameTable;
import dv16888.com.transaction.model.CasinoRecordModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SendMessagetoMqtt extends LuzhuCalculation{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String LOG = "Send Record to Mqtt ===========> ";

    @Autowired
    private IMqttSender iMqttSender;

    public SendMessagetoMqtt(IMqttSender iMqttSender){
        this.iMqttSender = iMqttSender;
    }

    @Override
    protected void calculate(CasinoRecordModel record, CasinoGameTable gameTable) {
        logger.info(LOG);
        try{
            JSONObject message = new JSONObject();
            message.put("message_type", "jiesuan");
            message.put("tableno", record.getTableNo());
            message.put("xuehao", gameTable.getXuehao());
            message.put("juhao", gameTable.getJuhao());
            iMqttSender.sendToMqtt("records", 0, JSONObject.toJSONString(message));
            logger.info(LOG + "Send Message to Mqtt Success");
        }catch (Exception e){
            logger.error(LOG + "Send Message to Mqtt fail");
        }
    }

}
