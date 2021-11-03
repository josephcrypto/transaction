package dv16888.com.transaction.mongorepository;

import dv16888.com.transaction.mongoentity.MongoBetRecordsCalculation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoBetCalculationRepository extends MongoRepository<MongoBetRecordsCalculation,String> {
}
