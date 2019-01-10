
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.FixUpTask;
import domain.Phase;

@Repository
public interface PhasesRepository extends JpaRepository<Phase, Integer> {

	@Query("select f from FixUpTask f join f.phases p where p.id = ?1")
	FixUpTask findFixUpTaskFromPhase(int id);

	@Query("select f from FixUpTask f join f.application p where f.id = ?1 and p.status = 'accepted'")
	FixUpTask findFixUpTaskAccepted(int id);

	@Query("select a from Actor a where a.account.id = ?1")
	Actor findByUserAccount(int id);
}
