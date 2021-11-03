package dv16888.com.transaction.services.reposervices;

import dv16888.com.transaction.entity.CasinoGameTable;
import dv16888.com.transaction.repository.CasinoGameTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameTableService {

    @Autowired
    private CasinoGameTableRepository casinoGameTableRepository;

    public CasinoGameTable getByTableNo(String tableNo){
        return casinoGameTableRepository.findFirstByTableNo(tableNo);
    }

}
