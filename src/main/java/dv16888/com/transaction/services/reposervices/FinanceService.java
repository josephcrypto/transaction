package dv16888.com.transaction.services.reposervices;

import dv16888.com.transaction.entity.CasinoFinances;
import dv16888.com.transaction.repository.CasinoFinanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinanceService {

    @Autowired
    private CasinoFinanceRepository casinoFinanceRepository;

    public void saveUpdate(CasinoFinances finances){
        if (finances.getCasinoUser() != null) {
            casinoFinanceRepository.saveUserFinance(finances.getAmount(), finances.getDirection(), finances.getFinanceType(), finances.getComment(), finances.getCasinoUser().getUid(),
                    finances.getTableNo(), finances.getXuehao(), finances.getJuhao(), finances.getBalance(), finances.getUserType(), finances.getCasinoBetRecords().getBid());

        }else if (finances.getCasinoAgency() != null) {
            casinoFinanceRepository.saveAgencyFinance(finances.getAmount(), finances.getDirection(), finances.getFinanceType(), finances.getComment(),
                    finances.getTableNo(), finances.getXuehao(), finances.getJuhao(), finances.getBalance(), finances.getUserType(),
                    finances.getCasinoAgency().getAid() , finances.getCasinoBetRecords().getBid());
        }
    }
}
