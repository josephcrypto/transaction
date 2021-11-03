package dv16888.com.transaction.services.reposervices;

import dv16888.com.transaction.entity.CasinoUser;
import dv16888.com.transaction.repository.CasinoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private CasinoUserRepository casinoUserRepository;

    public CasinoUser getById(int id){
        return casinoUserRepository.getById(id);
    }

    public CasinoUser saveUpdate(CasinoUser casinoUser){
        return casinoUserRepository.save(casinoUser);
    }

}
