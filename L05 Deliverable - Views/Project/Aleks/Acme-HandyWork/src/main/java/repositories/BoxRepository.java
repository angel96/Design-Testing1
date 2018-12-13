
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Box;

@Repository
public interface BoxRepository extends JpaRepository<Box, Integer> {

	@Query("select b from Actor a join a.boxes b where a.account.id = ?1 and b.name= 'entry'")
	Box getActorEntryBox(int userAccountId);
	@Query("select b from Actor a join a.boxes b where a.account.id = ?1 and b.name= 'spam'")
	Box getActorSpamBox(int userAccountId);
	@Query("select b from Actor a join a.boxes b where a.account.id = ?1 and b.name= 'sended'")
	Box getActorSendedBox(int userAccountId);
	@Query("select b from Actor a join a.boxes b where a.account.id = ?1 and b.name= 'trash'")
	Box getActorTrashBox(int userAccountId);
	@Query("select b from Actor a join a.boxes b where a.account.id= ?1")
	Collection<Box> getAllBoxesByActor(int userAccountId);
	@Query("select b from Actor a join a.boxes b where a.account.id=?1 and b.fromSystem = 0")
	Collection<Box> manageNotSystemBoxes(int userAccountId);

}
