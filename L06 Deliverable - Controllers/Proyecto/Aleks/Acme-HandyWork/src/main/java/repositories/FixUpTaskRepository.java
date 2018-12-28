
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;
import domain.FixUpTask;

@Repository
public interface FixUpTaskRepository extends JpaRepository<FixUpTask, Integer> {

	@Query("select c.fixUpTask from Customer c where c.id = ?1")
	Collection<FixUpTask> getFixUpTasksByCustomer(int id);

	@Query("select c from Customer c join c.fixUpTask f where f.id = ?1")
	Customer findCustomerByFixUpTask(int id);
}
