
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Box;

@Repository
public interface BoxRepository extends JpaRepository<Box, Integer> {

	@Query("select b from Actor a join a.boxes b where a.id = ?1 and b.name= 'entry'")
	Box getActorEntryBox(int actorId);
	@Query("select b from Actor a join a.boxes b where a.id = ?1 and b.name= 'spam'")
	Box getActorSpamBox(int actorId);
	@Query("select b from Actor a join a.boxes b where a.id = ?1 and b.name= 'sended'")
	Box getActorSendedBox(int actorId);
	@Query("select b from Actor a join a.boxes b where a.id = ?1 and b.name= 'trash'")
	Box getActorTrashBox(int actorId);
	@Query("select b from Actor a join a.boxes b where a.id= ?1")
	Collection<Box> getAllBoxesByActor(int actorId);
	@Query("select b from Actor a join a.boxes b where a.id= ?1 and b.fromSystem = 0")
	Collection<Box> getAllNoSystemBoxesByActor(int actorId);

}
