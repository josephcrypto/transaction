package dv16888.com.transaction.services.reposervices;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    public float baccarat(int result, String betContent, String peilv){
        JSONObject betJson = JSONObject.parseObject(betContent);
        int bankerMoney = betJson.getInteger("banker");
        int playerMoney = betJson.getInteger("player");
        int tieMoney = betJson.getInteger("tie");
        int bankerDoubleMoney = betJson.getInteger("banker_double");
        int playerDoubleMoney = betJson.getInteger("player_double");

        JSONObject peilvObj = JSONObject.parseObject(peilv);
        float bankerPeilv = peilvObj.getFloat("banker");
        float playerPeilv = peilvObj.getFloat("player");
        float tiePeilv = peilvObj.getFloat("tie");
        float bankerDoublePeilv = peilvObj.getFloat("banker_double");
        float playerDoublePeilv = peilvObj.getFloat("player_double");

        float money = 0;

        //庄赢
        if ((result & 1) == 1) {
            money += bankerMoney * (1 + bankerPeilv);
        }
        //闲赢
        if (((result & 2) == 2)) {
            money += playerMoney * (1 + playerPeilv);
        }
        //和局
        if ((result & 4) == 4) {
            bankerMoney = (bankerMoney > 0) ? bankerMoney : 0;
            playerMoney = (playerMoney > 0) ? playerMoney : 0;
            money += tieMoney * (1 + tiePeilv) + bankerMoney + playerMoney;
        }
        //庄对赢
        if ((result & 8) == 8) {
            money += bankerDoubleMoney * (1 + bankerDoublePeilv);
        }
        //闲对赢
        if ((result & 16) == 16) {
            money += playerDoubleMoney * (1 + playerDoublePeilv);
        }

        return money;
    }

    public float baccaratNoFee(int result, String betContent, String peilv, byte banker6){
        JSONObject betJson = JSONObject.parseObject(betContent);
        int bankerMoney = betJson.getInteger("banker");
        int playerMoney = betJson.getInteger("player");
        int tieMoney = betJson.getInteger("tie");
        int bankerDoubleMoney = betJson.getInteger("banker_double");
        int playerDoubleMoney = betJson.getInteger("player_double");

        JSONObject peilvObj = JSONObject.parseObject(peilv);
        float tiePeilv = peilvObj.getFloat("tie");
        float bankerDoublePeilv = peilvObj.getFloat("banker_double");
        float playerDoublePeilv = peilvObj.getFloat("player_double");

        float money = 0;

        //庄赢
        if ((result & 1) == 1) {
            if (banker6 == 1)
                money += bankerMoney * 1.5;
            else
                money += bankerMoney * 2;
        }
        //闲赢
        if ((result & 2) == 2) {
            money += playerMoney * 2;
        }
        //和局
        if ((result & 4) == 4) {
            money += tieMoney * (1 + tiePeilv);
            if(bankerMoney > 0) money += bankerMoney ;
            if(playerMoney > 0) money += playerMoney;
        }
        //庄对赢
        if ((result & 8) == 8) {
            money += bankerDoubleMoney * (1 + bankerDoublePeilv);
        }
        //闲对赢
        if ((result & 16) == 16) {
            money += playerDoubleMoney * (1 + playerDoublePeilv);
        }

        return money;
    }

    public float dragonTiger(int result, String betContent, String peilv){
        JSONObject betObj = JSONObject.parseObject(betContent);
        int dragonMoney = betObj.getInteger("dragon");
        int tigerMoney = betObj.getInteger("tiger");
        int tieMoney = betObj.getInteger("tie_2");
        int pairMoney = betObj.getInteger("pair");

        JSONObject peiObj = JSONObject.parseObject(peilv);
        float dragonPeilv = peiObj.getFloat("dragon");
        float tigerPeilv = peiObj.getFloat("tiger");
        float tiePeilv = peiObj.getFloat("tie");
        float pairPeilv = peiObj.getFloat("pair");

        float money = 0;

        //龙赢
        if ((result & 1) == 1) {
            money += dragonMoney * (1 + dragonPeilv);
        }
        //虎赢
        if ((result & 2) == 2) {
            money += tigerMoney * (1 + tigerPeilv);
        }
        //和局
        if ((result & 4) == 4) {
            dragonMoney = (dragonMoney > 0) ? dragonMoney : 0;
            tigerMoney = (tigerMoney > 0) ? tigerMoney : 0;
            money += tieMoney * (1 + tiePeilv) + dragonMoney + tigerMoney;
        }
        //对子
        if ((result & 8) == 8 || (result & 16) == 16) {
            money += pairMoney * (1 + pairPeilv);
        }

        return money;
    }

    public float dragonTigerShahe(int result, String betContent, String peilv){
        JSONObject betObj = JSONObject.parseObject(betContent);
        int dragonMoney = betObj.getInteger("dragon");
        int tigerMoney = betObj.getInteger("tiger");
        int tieMoney = betObj.getInteger("tie_2");

        JSONObject peiObj = JSONObject.parseObject(peilv);
        float tiePeilv = peiObj.getFloat("tie");

        float money = 0;

        //龙赢
        if ((result & 1) == 1) {
            money += dragonMoney * 2;
        }
        //虎赢
        if ((result & 2) == 2) {
            money += tigerMoney * 2;
        }
        //和局
        if ((result & 4) == 4) {
            money += tieMoney * (1 + tiePeilv);
            if(dragonMoney > 0) money += dragonMoney * 0.5 ;
            if(tigerMoney > 0) money += tigerMoney * 0.5 ;
        }

        return money;
    }

    public float bull(){
        return 0.0f;
    }
}
