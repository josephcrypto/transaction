package dv16888.com.transaction.services;

import com.alibaba.fastjson.JSONObject;
import dv16888.com.transaction.entity.CasinoGameTable;
import dv16888.com.transaction.model.CasinoRecordModel;


public class SendMessageToWs extends LuzhuCalculation{

    @Override
    protected void calculate(CasinoRecordModel record, CasinoGameTable gameTable) {

        System.out.println("Save CasinoRecord to Mongodb" + JSONObject.toJSONString(record));

        System.out.println("User List "+ JSONObject.toJSONString(LuzhuCalculation.userList));
    }

}
