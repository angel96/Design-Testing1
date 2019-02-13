
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.MiscellaneousRecord;

@Repository
public interface MiscellaneousRecordRepository extends JpaRepository<MiscellaneousRecord, Integer> {

	@Query("select e from Actor a join a.curriculum c join c.miscellaneousRecord e where a.id = ?1")
	Collection<MiscellaneousRecord> findMiscellaneousRecordByUserId(int id);
}
