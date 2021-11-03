package dv16888.com.transaction.repository;

import dv16888.com.transaction.entity.CasinoFinances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface CasinoFinanceRepository extends JpaRepository<CasinoFinances, Integer> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO `casino_finances` (`amount`, `direction`, `finance_type`, `comment`, `userid`, `tableno`, `xuehao`, `juhao`, `finance_time`, `balance`, `user_type`, `bid`) " +
            "VALUES (:amount, :direction, :financeType, :comment, :userId, :tableNo, :xuehao, :juhao, now(), :balance, :userType, :bId)")
    int saveUserFinance(float amount, byte direction, byte financeType, String comment, int userId, int tableNo, int xuehao, int juhao, float balance, byte userType, int bId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO `casino_finances` (`amount`, `direction`, `finance_type`, `comment`, `tableno`, `xuehao`, `juhao`, `finance_time`, `balance`, `user_type`, `agency_id`, `bid`) " +
            "VALUES (:amount, :direction, :financeType, :comment, :tableNo, :xuehao, :juhao, now(), :balance, :userType, :agencyId, :bId)")
    int saveAgencyFinance(float amount, byte direction, byte financeType, String comment, int tableNo, int xuehao, int juhao, float balance, byte userType, int agencyId, int bId);

}
