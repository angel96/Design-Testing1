
package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Category;
import domain.Customer;
import domain.FixUpTask;
import domain.Warranty;

@Repository
public interface FixUpTaskRepository extends JpaRepository<FixUpTask, Integer> {

	@Query("select c.fixUpTask from Customer c where c.id = ?1")
	Collection<FixUpTask> getFixUpTasksByCustomer(int id);

	@Query("select c from Customer c join c.fixUpTask f where f.id = ?1")
	Customer findCustomerByFixUpTask(int id);

	@Query("select f from FixUpTask f where f.ticker LIKE ?1 OR f.description LIKE ?1 OR f.address LIKE ?1 OR f.start >= ?2 OR f.end <= ?3 OR f.warranty = ?4 OR f.category = ?5 OR f.maximumPrice >= ?6 and f.maximumPrice <= ?7")
	Collection<FixUpTask> findAllSearchByFinder(String query, Date start, Date end, Warranty warranty, Category category, double amount1, double amount2);
}
