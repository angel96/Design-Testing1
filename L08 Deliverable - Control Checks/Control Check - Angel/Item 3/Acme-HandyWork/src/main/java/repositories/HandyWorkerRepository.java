
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;
import domain.HandyWorker;

@Repository
public interface HandyWorkerRepository extends JpaRepository<HandyWorker, Integer> {

	@Query("select c from HandyWorker c where c.account.id = ?1")
	HandyWorker findByUserAccount(int id);
	@Query("select distinct (select h from HandyWorker h join h.application app where a = app) from Customer c join c.fixUpTask f join f.application a where a.status = 'accepted' and c.id = ?1")
	Collection<HandyWorker> findHandyWorkersByCustomerId(int id);
	@Query("select distinct (select c from Customer c join c.fixUpTask f join f.application a where a = app) from HandyWorker h join h.application app where app.status = 'accepted' and h.id = ?1")
	Collection<Customer> findCustomerByHandyWorkerId(int id);

	@Query("select h from HandyWorker h join h.tutorials t where t.id = ?1")
	HandyWorker findHandyWorkerByTutorial(int id);
}
