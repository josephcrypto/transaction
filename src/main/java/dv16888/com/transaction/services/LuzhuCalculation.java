package dv16888.com.transaction.services;

import com.alibaba.fastjson.JSONObject;
import dv16888.com.transaction.entity.CasinoGameTable;
import dv16888.com.transaction.model.CasinoRecordModel;

import java.util.ArrayList;
import java.util.List;

public abstract class LuzhuCalculation {

    public static List<JSONObject> userList = new ArrayList<>();
    protected LuzhuCalculation nextJob;

    public void setNextJob(LuzhuCalculation nextJob){
        this.nextJob = nextJob;
    }

    public void calculateLuzhu(CasinoRecordModel record, CasinoGameTable gameTable)
    {
        // Do Calculation
        calculate(record, gameTable);

        if(nextJob != null){
            nextJob.calculateLuzhu(record, gameTable);
        }
    }

    abstract protected void calculate(CasinoRecordModel record, CasinoGameTable gameTable);

}
