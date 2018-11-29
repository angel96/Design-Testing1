
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Box;
import domain.HandyWorker;

@Repository
public interface HandyWorkerRepository extends JpaRepository<HandyWorker, Integer> {

	@Query("select c from HandyWorker c where c.account.id = ?1")
	HandyWorker findByUserAccount(int id);
	@Query("select b from HandyWorker a join a.boxes b where a.id=?1 and b.fromSystem = 0")
	public Collection<Box> manageNotSystemBoxes(int id);
}
