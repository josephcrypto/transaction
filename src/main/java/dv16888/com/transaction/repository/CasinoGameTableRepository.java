package dv16888.com.transaction.repository;

import dv16888.com.transaction.entity.CasinoGameTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CasinoGameTableRepository extends JpaRepository<CasinoGameTable,Integer> {

    CasinoGameTable findFirstByTableNo(String tableNo);

}
