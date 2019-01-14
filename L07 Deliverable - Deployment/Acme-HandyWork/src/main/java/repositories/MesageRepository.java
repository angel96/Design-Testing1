
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Box;
import domain.Mesage;

@Repository
public interface MesageRepository extends JpaRepository<Mesage, Integer> {

	@Query("select b.message from Box b where b.id = ?1")
	Collection<Mesage> getMessagesByBox(int id);

	@Query("select b from Actor a join a.boxes b where b.name = ?1 and a IN ?2 and a.account.id <> ?3")
	Collection<Box> getBoxesFromActors(String name, Collection<Actor> actors, int userlogged);

	@Query("select b from Actor a join a.boxes b join b.message m where m.id = ?1 and a.id = ?2")
	Collection<Box> getMesageBoxesFromActor(int mesage, int actor);

}
