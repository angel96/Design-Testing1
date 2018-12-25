
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Box;

@Repository
public interface BoxRepository extends JpaRepository<Box, Integer> {

	@Query("select b from Actor a join a.boxes b where a.id = ?1 and b.name= 'In Box'")
	Box getActorEntryBox(int actorId);
	@Query("select b from Actor a join a.boxes b where a.id = ?1 and b.name= 'Spam Box'")
	Box getActorSpamBox(int actorId);
	@Query("select b from Actor a join a.boxes b where a.id = ?1 and b.name= 'Out Box'")
	Box getActorSendedBox(int actorId);
	@Query("select b from Actor a join a.boxes b where a.id = ?1 and b.name= 'Trash Box'")
	Box getActorTrashBox(int actorId);
	@Query("select b from Actor a join a.boxes b where a.id = ?1 and b.name= ?2")
	Box getActorOtherBox(int actorId, String other);

	@Query("select b from Actor a join a.boxes b where a.account.id = ?1")
	Collection<Box> getBoxesFromActor(int actorId);

	@Query("select a from Actor a where a.account.id = ?1")
	Actor getActorByUserAccount(int id);
}
