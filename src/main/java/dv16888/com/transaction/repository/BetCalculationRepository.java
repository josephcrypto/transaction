package dv16888.com.transaction.repository;

import dv16888.com.transaction.entity.CasinoBetRecordsCalculation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BetCalculationRepository extends JpaRepository<CasinoBetRecordsCalculation, Integer> {
}
