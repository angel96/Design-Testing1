
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.EducationRecord;

@Repository
public interface EducationRecordRepository extends JpaRepository<EducationRecord, Integer> {

	@Query("select e from Actor a join a.curriculum c join c.educationRecord e where a.id = ?1")
	Collection<EducationRecord> findEducationRecordByUserId(int id);
}
