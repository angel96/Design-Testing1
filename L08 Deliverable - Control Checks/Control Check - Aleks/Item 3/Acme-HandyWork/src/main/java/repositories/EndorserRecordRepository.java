
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.EndorserRecord;

@Repository
public interface EndorserRecordRepository extends JpaRepository<EndorserRecord, Integer> {

	@Query("select e from Actor a join a.curriculum c join c.endorserRecord e where a.id = ?1")
	Collection<EndorserRecord> findEndorserRecordByUserId(int id);
}
