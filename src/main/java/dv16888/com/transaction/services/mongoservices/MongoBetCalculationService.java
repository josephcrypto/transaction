package dv16888.com.transaction.services.mongoservices;

import dv16888.com.transaction.entity.CasinoBetRecordsCalculation;
import dv16888.com.transaction.mongoentity.MongoBetRecordsCalculation;
import dv16888.com.transaction.mongorepository.MongoBetCalculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MongoBetCalculationService {

    @Autowired
    private MongoBetCalculationRepository mongoBetCalculationRepository;

    public MongoBetRecordsCalculation saveUpdate(CasinoBetRecordsCalculation recordsCalculation){
        MongoBetRecordsCalculation mongoBetRecordsCalculation = new MongoBetRecordsCalculation();
        mongoBetRecordsCalculation.setBid(recordsCalculation.getBid());
        mongoBetRecordsCalculation.setUserId(recordsCalculation.getUserId());
        mongoBetRecordsCalculation.setBetMoney(recordsCalculation.getBetMoney());
        mongoBetRecordsCalculation.setBetContent(recordsCalculation.getBetContent());
        mongoBetRecordsCalculation.setTableNo(recordsCalculation.getTableNo());
        mongoBetRecordsCalculation.setIsJiesuan(recordsCalculation.getIsJiesuan());
        mongoBetRecordsCalculation.setJsTime(recordsCalculation.getJsTime());
        mongoBetRecordsCalculation.setWinLose(recordsCalculation.getWinLose());
        mongoBetRecordsCalculation.setXuehao(recordsCalculation.getXuehao());
        mongoBetRecordsCalculation.setJuhao(recordsCalculation.getJuhao());
        mongoBetRecordsCalculation.setWithoutFee(recordsCalculation.getWithoutFee());
        mongoBetRecordsCalculation.setBetTime(recordsCalculation.getBetTime());
        mongoBetRecordsCalculation.setXimaMoney(recordsCalculation.getXimaMoney());
        mongoBetRecordsCalculation.setBenefit(recordsCalculation.getBenefit());
        mongoBetRecordsCalculation.setXimaFee(recordsCalculation.getXimaFee());
        return mongoBetCalculationRepository.save(mongoBetRecordsCalculation);
    }

}
