
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Application;
import domain.Customer;
import domain.FixUpTask;
import domain.HandyWorker;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	@Query("select hw from HandyWorker hw join hw.application a where a.id = ?1")
	HandyWorker getHandyWorkerByApplication(int app);

	@Query("select a from HandyWorker hw join hw.application a where hw.account.id = ?1")
	Collection<Application> getApplicationsByHandyWorker(int userAccountId);

	@Query("select a from Customer c join c.fixUpTask f join f.application a where c.account.id = ?1")
	Collection<Application> getApplicationsByCustomer(int userAccountId);

	@Query("select c from Customer c join c.fixUpTask f join f.application a where a.id = ?1")
	Customer findCustomerByApplication(int id);

	@Query("select a from Actor a where a.account.id = ?1")
	Actor findByUserAccount(int id);

	@Query("select a from Application a join a.fixUpTask f join f.phases p where p.id = ?1 and a.status = 'accepted'")
	Application getApplicationAceptedByPhase(int id);

	@Query("select a.fixUpTask from Application a where a.id = ?1")
	FixUpTask getFixUpTaskByApplication(int id);

	@Query("select a from Customer c join c.fixUpTask f join f.application a where c.account.id = ?1 and f.id = ?2")
	Collection<Application> getApplicationsByFixUpTask(int customerAccountId, int fixUpId);

}
