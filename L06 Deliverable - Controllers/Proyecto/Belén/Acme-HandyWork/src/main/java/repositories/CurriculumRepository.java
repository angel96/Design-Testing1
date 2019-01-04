
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Curriculum;
import domain.Profile;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Integer> {

	@Query("select a from Actor a where a.account.id = ?1")
	Actor findActorByUserAccountId(int id);

	@Query("select p from Actor a join a.profiles p where p.socialNetworkName like 'LinkedIn' and a.id = ?1")
	Collection<Profile> findLinkedInByActorId(int id);
}
