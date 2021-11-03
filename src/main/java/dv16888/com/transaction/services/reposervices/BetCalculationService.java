package dv16888.com.transaction.services.reposervices;

import dv16888.com.transaction.entity.CasinoBetRecordsCalculation;
import dv16888.com.transaction.repository.BetCalculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BetCalculationService {

    @Autowired
    private BetCalculationRepository calculationRepository;

    public CasinoBetRecordsCalculation saveUpdate(CasinoBetRecordsCalculation recordsCalculation){
        return calculationRepository.save(recordsCalculation);
    }
}
