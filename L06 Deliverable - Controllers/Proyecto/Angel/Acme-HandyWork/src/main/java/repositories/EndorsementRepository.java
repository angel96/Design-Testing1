
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Endorsable;
import domain.Endorsement;

@Repository
public interface EndorsementRepository extends JpaRepository<Endorsement, Integer> {

	@Query("select e from Endorsement e where e.userReceived.id = ?1")
	Collection<Endorsement> getEndorsementsByActorReceived(int id);

	@Query("select e from Endorsement e where e.userSended.id = ?1")
	Collection<Endorsement> getEndorsementsByActorSended(int id);

	@Query("select a from Actor a where a.account.id = ?1")
	Actor findActorByUserAccountId(int id);

	@Query("select e from Endorsable e where e.account.id = ?1")
	Endorsable findEndorsableByUserAccountId(int id);

}
