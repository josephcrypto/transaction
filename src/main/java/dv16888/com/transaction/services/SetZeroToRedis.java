package dv16888.com.transaction.services;

import com.alibaba.fastjson.JSONObject;
import dv16888.com.transaction.entity.CasinoGameTable;
import dv16888.com.transaction.model.CasinoRecordModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class SetZeroToRedis extends LuzhuCalculation{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String LOG = "Set Zero To Redis ============> ";

    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    public SetZeroToRedis(RedisTemplate<String, String> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void calculate(CasinoRecordModel record, CasinoGameTable table) {

        logger.info(LOG + "Set zero to All Money in Redis"+ JSONObject.toJSONString(record));

        try{
            String tableNo = record.getTableNo();
            ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();

            Integer gametype = table.getCasinoGame().getGid();
            if (gametype == 1) {
                valueOperations.set("banker_money_" + tableNo, "0");
                valueOperations.set("player_money_" + tableNo, "0");
                valueOperations.set("tie_money_" + tableNo, "0");
                valueOperations.set("banker_double_money_" + tableNo, "0");
                valueOperations.set("player_double_money_" + tableNo, "0");
                valueOperations.set("banker_money_most_" + tableNo, "0");
                valueOperations.set("player_money_most_" + tableNo, "0");
                valueOperations.set("tie_money_most_" + tableNo, "0");
                valueOperations.set("banker_double_money_most_" + tableNo, "0");
                valueOperations.set("player_double_money_most_" + tableNo, "0");
                valueOperations.set("current_people_" + tableNo, "0");
            } else if (gametype == 2) {
                valueOperations.set("dragon_money_" + tableNo, "0");
                valueOperations.set("tiger_money_" + tableNo, "0");
                valueOperations.set("tie_2_money_" + tableNo, "0");
                valueOperations.set("pair_money_" + tableNo, "0");
                valueOperations.set("dragon_money_most_" + tableNo, "0");
                valueOperations.set("tiger_money_most_" + tableNo, "0");
                valueOperations.set("tie_2_money_most_" + tableNo, "0");
                valueOperations.set("pair_money_most_" + tableNo, "0");
                valueOperations.set("current_people_" + tableNo, "0");
            }
            logger.info(LOG + "Set Zero to redis success");
        }catch (Exception e){
            logger.error(LOG + "Set Zero to redis fail " +e.getMessage());
        }

    }
}
