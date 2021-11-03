package dv16888.com.transaction.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dv16888.com.transaction.entity.*;
import dv16888.com.transaction.model.CasinoRecordModel;
import dv16888.com.transaction.services.mongoservices.MongoBetCalculationService;
import dv16888.com.transaction.services.reposervices.*;
import org.apache.commons.math3.util.Precision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Date;
import java.util.List;

public class BetCalculation extends LuzhuCalculation{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String LOG = "User Bet Calculation =========> ";

    private BetRecordService betRecordService;

    private CalculationService calculationService;

    private UserService userService;

    private FinanceService financeService;

    private AgencyService agencyService;

    private RedisTemplate<String, String> redisTemplate;

    private BetCalculationService betCalculationService;

    private MongoBetCalculationService mongoBetCalculationService;

    @Autowired
    public BetCalculation(BetRecordService betRecordService, CalculationService calculationService,
                          UserService userService, FinanceService financeService,
                          AgencyService agencyService, RedisTemplate<String, String> redisTemplate,
                          BetCalculationService betCalculationService,
                          MongoBetCalculationService mongoBetCalculationService){
        this.betRecordService = betRecordService;
        this.calculationService = calculationService;
        this.userService = userService;
        this.financeService = financeService;
        this.agencyService = agencyService;
        this.redisTemplate = redisTemplate;
        this.betCalculationService = betCalculationService;
        this.mongoBetCalculationService = mongoBetCalculationService;
    }

    @Override
    protected void calculate(CasinoRecordModel record, CasinoGameTable casinoGameTable) {
        logger.info(LOG + JSONObject.toJSONString(record));
        try{

            int gameType = casinoGameTable.getCasinoGame().getGid();

            List<CasinoBetRecords> betRecordsList = betRecordService.getAllByJiesuanAndGameTable((byte) 0, casinoGameTable);

            for (CasinoBetRecords betRecords : betRecordsList) {
                int xuehao = betRecords.getXuehao();
                int juhao = betRecords.getJuhao();
                String betContent = betRecords.getBetContent();
                JSONObject betObj = JSONObject.parseObject(betContent);
                float betMoney = betRecords.getBetMoney();
                byte withoutFee = betRecords.getWithoutFee();
                int result = record.getResult();
                byte banker6 = record.getBanker6();

                // Get Bet User
                CasinoUser betUser = betRecords.getCasinoUser();
                JSONObject usrPeilv = JSONObject.parseObject(betUser.getPeilv());
                int longhuShahe = betUser.getLonghuShahe();

                // User Agency
                CasinoAgency parentAgency = betUser.getCasinoAgency();
                float ximalvDouble = parentAgency.getXimalvDouble();
                float ximalvSingle = parentAgency.getXimalvSingle();

                // calculate return money
                float returnMoney = 0;
                if (gameType == 1) {
                    String peilv = usrPeilv.getString("baccarat");
                    if (withoutFee == 1) {
                        returnMoney = calculationService.baccaratNoFee(result, betContent, peilv, banker6);
                    } else {
                        returnMoney = calculationService.baccarat(result, betContent, peilv);
                    }
                } else if (gameType == 2) {
                    String peilv = usrPeilv.getString("dragon_tiger");
                    if (longhuShahe == 1) {
                        returnMoney = calculationService.dragonTigerShahe(result, betContent, peilv);
                    } else {
                        returnMoney = calculationService.dragonTiger(result, betContent, peilv);
                    }
                }

                int winLose = returnMoney >= betMoney ? 1 : 0;
                //betRecords.setCasinoUser(betUser);
                betRecords.setIsJiesuan((byte) 1);
                betRecords.setJsTime(new Date());
                betRecords.setWinLose((byte) winLose);
                betRecords.setBenefit(returnMoney - betMoney);

                if (gameType == 2) {
                    if (longhuShahe == 1) {
                        betRecords.setXimaMoney(0.0f);
                    } else {
                        String peilv = usrPeilv.getString("dragon_tiger");
                        JSONObject peObj = JSONObject.parseObject(peilv);
                        float dragonPeilv = peObj.getFloat("dragon");
                        float tigerPeilv = peObj.getFloat("tiger");
                        int dragonMoney = betObj.getInteger("dragon");
                        int tigerMoney = betObj.getInteger("tiger");

                        //押龙、押虎的赔率 Odds for betting on dragon and tiger
                        if ((result & 1) == 1 && (dragonMoney > tigerMoney)) {
                            betRecords.setXimaMoney((dragonMoney - tigerMoney) * dragonPeilv);
                        } else if ((result & 2) == 2 && (dragonMoney < tigerMoney)) {
                            betRecords.setXimaMoney((tigerMoney - dragonMoney) * tigerPeilv);
                        } else if (dragonMoney == tigerMoney) {
                            betRecords.setXimaMoney(0.0f);
                        }
                    }
                } else if (gameType == 1) {
                    if (withoutFee == 1) {
                        betRecords.setXimaMoney(0.0f);
                    } else {
                        String peilv = usrPeilv.getString("baccarat");
                        JSONObject peObj = JSONObject.parseObject(peilv);
                        float bankerPeilv = peObj.getFloat("banker");
                        float playerPeilv = peObj.getFloat("player");
                        int bankerMoney = betObj.getInteger("banker");
                        int playerMoney = betObj.getInteger("player");

                        //押庄、押闲的赔率 Odds for betting on bank and betting on idle
                        if ((result & 1) == 1 && (bankerMoney > playerMoney)) {
                            betRecords.setXimaMoney((bankerMoney - playerMoney) * bankerPeilv);
                        } else if ((result & 2) == 2 && (bankerMoney < playerMoney)) {
                            betRecords.setXimaMoney((playerMoney - bankerMoney) * playerPeilv);
                        } else if (bankerMoney == playerMoney) {
                            betRecords.setXimaMoney(0.0f);
                        }
                    }
                }

                if ((result & 4) == 4) {
                    betRecords.setXimaMoney(0.0f);
                }

                if (gameType == 1) {
                    betRecords.setXimaMoney(betRecords.getXimaMoney() + betObj.getInteger("tie") + betObj.getInteger("banker_double") + betObj.getInteger("player_double"));
                } else if (gameType == 2) {
                    betRecords.setXimaMoney(betRecords.getXimaMoney() + betObj.getInteger("tie_2") + betObj.getInteger("pair"));
                }

                //Calculate the code washing fee
                if (ximalvDouble != 0) {
                    betRecords.setXimaFee(Precision.round(betRecords.getXimaMoney() * ximalvDouble / 100, 2));
                } else if (ximalvSingle != 0) {
                    if (betRecords.getWinLose() == 0)
                        betRecords.setXimaFee(Precision.round(betRecords.getXimaMoney() * ximalvSingle / 100, 2));
                    else
                        betRecords.setXimaFee(0.0f);
                }

                // Save bet Records
                CasinoBetRecords updateRecord = betRecordService.saveUpdate(betRecords);
                if (updateRecord == null) {
                    logger.error(LOG + "Error Saving Record");
                }

                JSONObject winArr = new JSONObject();
                winArr.put("balance", betUser.getBalance());
                winArr.put("message", returnMoney > betMoney ? "恭喜您赢了" : "再接再厉,胜败乃兵家常事");

                if (returnMoney != 0) {
                    betUser.setBalance(betUser.getBalance() + returnMoney);
                    CasinoUser updateUser = userService.saveUpdate(betUser);

                    if (updateUser == null) {
                        logger.error(LOG + "Error Salving User Balance");
                    }

                    winArr.put("balance", updateUser.getBalance());

                    // Save Finance
                    CasinoFinances finances = new CasinoFinances();
                    finances.setAmount(returnMoney);
                    finances.setDirection((byte) 1);
                    finances.setFinanceType((byte) 5);
                    finances.setBalance(updateUser.getBalance());
                    finances.setUserType((byte) 1);
                    finances.setCasinoUser(updateUser);
                    finances.setTableNo(Integer.parseInt(record.getTableNo()));
                    finances.setXuehao(xuehao);
                    finances.setJuhao(juhao);
                    finances.setFinanceTime(new Date());
                    finances.setComment("返奖");
                    finances.setCasinoBetRecords(updateRecord);
                    try {
                        financeService.saveUpdate(finances);
                        logger.info(LOG + "Success Saving Finances");
                    }catch(Exception e){
                        logger.error(LOG + "Error Saving Finances" + e.getMessage());
                    }
                }

                // Save win to redis
                ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
                valueOperations.set("win:" + betUser.getUid(), JSONObject.toJSONString(winArr));

                // Refund Money to parent agency
                float refundMoneyAgency = 0;
                if (gameType == 1) {
                    String peilv = usrPeilv.getString("baccarat");
                    JSONObject peObj = JSON.parseObject(peilv);
                    float bankerPeilv = peObj.getFloat("banker");
                    float playerPeilv = peObj.getFloat("player");
                    float tiePeilv = peObj.getFloat("tie");
                    float bankerPairPeilv = peObj.getFloat("banker_double");
                    float playerPairPeilv = peObj.getFloat("player_double");

                    int bankerMoney = betObj.getInteger("banker");
                    int playerMoney = betObj.getInteger("player");

                    if ((bankerPeilv > 0 && bankerPeilv < 0.95) && (result & 1) == 1 && (bankerMoney > playerMoney)) {
                        refundMoneyAgency += Precision.round((bankerMoney - playerMoney) * (0.95 - bankerPeilv), 2);
                    } else if ((playerPeilv > 0 && playerPeilv < 1) && (result & 2) == 2 && (bankerMoney < playerMoney)) {
                        refundMoneyAgency += Precision.round((playerMoney - bankerMoney) * (1 - playerPeilv), 2);
                    }

                    int tieMoney = betObj.getInteger("tie");
                    if ((tiePeilv > 0 && tiePeilv < 8) && (result & 4) == 4) {
                        refundMoneyAgency += Precision.round(tieMoney * (8 - tiePeilv), 2);
                    }

                    int bankerDoubleMoney = betObj.getInteger("banker_double");
                    if ((bankerPairPeilv > 0 && bankerPairPeilv < 11) && (result & 8) == 8) {
                        refundMoneyAgency += Precision.round(bankerDoubleMoney * (11 - bankerPairPeilv), 2);
                    }

                    int playerDoubleMoney = betObj.getInteger("player_double");
                    if ((playerPairPeilv > 0 && playerPairPeilv < 11) && (result & 16) == 16) {
                        refundMoneyAgency += Precision.round(playerDoubleMoney * (11 - playerPairPeilv), 2);
                    }
                } else if (gameType == 2) {
                    String peilv = usrPeilv.getString("dragon_tiger");
                    JSONObject peObj = JSONObject.parseObject(peilv);
                    float dragonPeilv = peObj.getFloat("dragon");
                    float tigerPeilv = peObj.getFloat("tiger");
                    float tiePeilv = peObj.getFloat("tie");
                    float pairPeilv = peObj.getFloat("pair");

                    int dragonMoney = betObj.getInteger("dragon");
                    int tigerMoney = betObj.getInteger("tiger");
                    if ((dragonPeilv > 0 && dragonPeilv < 0.97) && (result & 1) == 1 && (dragonMoney > tigerMoney)) {
                        refundMoneyAgency += Precision.round((dragonMoney - tigerMoney) * (0.97 - dragonPeilv), 2);
                    } else if ((tigerPeilv > 0 && tigerPeilv < 1) && (result & 2) == 2 && (dragonMoney < tigerMoney)) {
                        refundMoneyAgency += Precision.round((tigerMoney - dragonMoney) * (0.97 - tiePeilv), 2);
                    }

                    int tieMoney = betObj.getInteger("tie_2");
                    if ((tiePeilv > 0 && tiePeilv < 8) && (result & 4) == 4) {
                        refundMoneyAgency += Precision.round(tieMoney * (8 - tiePeilv), 2);
                    }

                    int pairMoney = betObj.getInteger("pair");
                    if ((pairPeilv > 0 && pairPeilv < 11) && ((result & 8) == 8 || (result & 16) == 16)) {
                        refundMoneyAgency += Precision.round(pairMoney * (11 - pairPeilv), 2);
                    }
                }

                if (refundMoneyAgency > 0) {
                    parentAgency.setBalance(parentAgency.getBalance() + refundMoneyAgency);

                    // Save Agency refund money
                    CasinoAgency updateAgency = agencyService.saveUpdate(parentAgency);
                    if (updateAgency == null) {
                        logger.error(LOG + "Error Saving parent_agency");
                    }

                    // Agency Finance
                    CasinoFinances aFinance = new CasinoFinances();
                    aFinance.setAmount(refundMoneyAgency);
                    aFinance.setDirection((byte) 1);
                    aFinance.setFinanceType((byte) 9);
                    aFinance.setBalance(updateAgency.getBalance());
                    aFinance.setCasinoAgency(updateAgency);
                    aFinance.setTableNo(Integer.parseInt(record.getTableNo()));
                    aFinance.setXuehao(xuehao);
                    aFinance.setJuhao(juhao);
                    aFinance.setUserType((byte) 2);
                    aFinance.setFinanceTime(new Date());
                    aFinance.setComment("从会员" + betUser.getUserName() + " " + record.getTableNo() + "桌 " + xuehao + "靴 " + juhao + "局 抽水 " + refundMoneyAgency + "元");
                    aFinance.setCasinoBetRecords(updateRecord);

                    // Agency Finance Save
                    try{
                        financeService.saveUpdate(aFinance);
                        logger.info(LOG + "Success Saving Agency Finances");
                    }catch (Exception e){
                        logger.error(LOG + "Error Saving Agency Finances" + e.getMessage());
                    }
                }

                // For websocket user
                JSONObject user = new JSONObject();
                user.put("uid", betUser.getUid());
                user.put("balance", betUser.getBalance());
                LuzhuCalculation.userList.add(user);

                // Save to betRecord calculation
                CasinoBetRecordsCalculation calculation = new CasinoBetRecordsCalculation();
                calculation.setBid(betRecords.getBid());
                calculation.setBetContent(betRecords.getBetContent());
                calculation.setBetMoney(betMoney);
                calculation.setUserId(betUser.getUid());
                calculation.setTableNo(Integer.parseInt(record.getTableNo()));
                calculation.setIsJiesuan((byte) 1);
                calculation.setJsTime(new Date());
                calculation.setWinLose((byte) winLose);
                calculation.setJuhao(juhao);
                calculation.setXuehao(xuehao);
                calculation.setWithoutFee(withoutFee);
                calculation.setBetTime(betRecords.getBetTime());
                calculation.setXimaMoney(betRecords.getXimaMoney());
                calculation.setBenefit(betRecords.getBenefit());
                calculation.setXimaFee(betRecords.getXimaFee());
                CasinoBetRecordsCalculation betRecordCalculation = betCalculationService.saveUpdate(calculation);
                //save to mongodb
                mongoBetCalculationService.saveUpdate(betRecordCalculation);
            }
            logger.info(LOG + "Bet Calculation Success");
        }catch (Exception e){
            logger.error(LOG + "Calculation Error " + e.getMessage());
        }
    }

}
