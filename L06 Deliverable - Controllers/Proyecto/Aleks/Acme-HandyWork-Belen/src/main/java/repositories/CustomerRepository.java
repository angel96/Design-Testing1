
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;
import domain.HandyWorker;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("select c from Customer c where c.account.id = ?1")
	Customer findByUserAccount(int id);

	@Query("select avg(f.fixUpTask.size) from Customer f")
	double findAVGOfFixUpTaskPerUser();
	@Query("select min(f.fixUpTask.size) from Customer f")
	double findMINOfFixUpTaskPerUser();
	@Query("select max(f.fixUpTask.size) from Customer f")
	double findMAXOfFixUpTaskPerUser();
	@Query("select stddev(f.fixUpTask.size) from Customer f")
	double findATDDEVOfFixUpTaskPerUser();

	@Query("select distinct (select h from HandyWorker h join h.application app where a = app) from Customer c join c.fixUpTask f join f.application a where a.status = 'accepted' and c.id = ?1")
	Collection<HandyWorker> findHandyByCustomerId(int id);
}
