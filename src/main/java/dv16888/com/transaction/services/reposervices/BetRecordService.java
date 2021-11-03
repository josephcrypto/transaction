package dv16888.com.transaction.services.reposervices;

import dv16888.com.transaction.entity.CasinoBetRecords;
import dv16888.com.transaction.entity.CasinoGameTable;
import dv16888.com.transaction.repository.CasinoBetrecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BetRecordService {

    @Autowired
    private CasinoBetrecordsRepository betrecordsRepository;

    public List<CasinoBetRecords> getAllByJiesuanAndGameTable(byte isJiesuan, CasinoGameTable gameTable){
        return betrecordsRepository.findAllByIsJiesuanAndCasinoGameTable(isJiesuan, gameTable);
    }

    public CasinoBetRecords saveUpdate(CasinoBetRecords betRecords){return betrecordsRepository.save(betRecords);
    }


}
