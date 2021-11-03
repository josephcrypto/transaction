package dv16888.com.transaction.repository;

import dv16888.com.transaction.entity.CasinoBetRecords;
import dv16888.com.transaction.entity.CasinoGameTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CasinoBetrecordsRepository extends JpaRepository<CasinoBetRecords,Integer> {

 List<CasinoBetRecords> findAllByIsJiesuanAndCasinoGameTable(Byte isjiesuan, CasinoGameTable table);

}
