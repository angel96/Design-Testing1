
package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Box;
import domain.Category;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Warranty;

@Repository
public interface HandyWorkerRepository extends JpaRepository<HandyWorker, Integer> {

	//	@Query("select a from HandyWorker a where a.account.id = ?1")
	//	Administrator findHandyWorkerByUserAccountId(int id);
	@Query("select c from HandyWorker c where c.account.id = ?1")
	HandyWorker findByUserAccount(int id);
	@Query("select b from HandyWorker a join a.boxes b where a.id=?1 and b.fromSystem = 0")
	Collection<Box> manageNotSystemBoxes(int id);

	@Query("select f from FixUpTask f where f.warranty = ?1")
	Collection<FixUpTask> findAllByWarranty(Warranty warranty);

	@Query("select f from FixUpTask f where f.category = ?1")
	Collection<FixUpTask> findAllByCategory(Category category);

	@Query("select f from FixUpTask f where f.maximumPrice >= ?1 and f.maximumPrice <= ?2")
	Collection<FixUpTask> findAllByPrices(double d1, double d2);

	@Query("select f from FixUpTask f where f.ticker LIKE ?1 OR f.description LIKE ?1 OR f.address LIKE ?1")
	Collection<FixUpTask> findAllByKeyWorkd(String keyworkd);

	@Query("select f from FixUpTask f where f.start between ?1 and ?2")
	Collection<FixUpTask> findAllStartedByTwoDate(Date date1, Date date2);

	@Query("select f from FixUpTask f where f.end between ?1 and ?2")
	Collection<FixUpTask> findAllFinishedByTwoDate(Date date1, Date date2);

	@Query("select f from FixUpTask f where f.moment between ?1 and ?2")
	Collection<FixUpTask> findAllByTwoDate(Date date1, Date date2);
}
