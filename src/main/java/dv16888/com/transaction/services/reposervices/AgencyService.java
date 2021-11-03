package dv16888.com.transaction.services.reposervices;

import dv16888.com.transaction.entity.CasinoAgency;
import dv16888.com.transaction.repository.CasinoAgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgencyService {

    @Autowired
    private CasinoAgencyRepository casinoAgencyRepository;

    public CasinoAgency saveUpdate(CasinoAgency casinoAgency){
        return casinoAgencyRepository.save(casinoAgency);
    }
}
