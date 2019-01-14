
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ProfessionalRecord;

@Repository
public interface ProfessionalRecordRepository extends JpaRepository<ProfessionalRecord, Integer> {

	@Query("select e from Actor a join a.curriculum c join c.professionalRecord e where a.id = ?1")
	Collection<ProfessionalRecord> findProfessionalRecordByUserId(int id);
}
