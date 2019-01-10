
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;
import domain.FixUpTask;

@Repository
public interface FixUpTaskRepository extends JpaRepository<FixUpTask, Integer> {

	@Query("select c from Customer c join c.fixUpTask f where f.id = ?1")
	Customer findCustomerByFixUpTask(int id);

	@Query("select c.fixUpTask from Customer c where c.account.id = ?1")
	Collection<FixUpTask> findAllByUser(int userAccountId);

	@Query("select f from HandyWorker h join h.application a join a.fixUpTask f where h.id = ?1")
	Collection<FixUpTask> getFixUpTasksByHandyWorker(int id);
}
