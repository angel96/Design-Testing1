
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Mesage;

@Repository
public interface MesageRepository extends JpaRepository<Mesage, Integer> {

	@Query("select b.message from Box b where b.id = ?1")
	Collection<Mesage> getMessagesByBox(int id);
}
