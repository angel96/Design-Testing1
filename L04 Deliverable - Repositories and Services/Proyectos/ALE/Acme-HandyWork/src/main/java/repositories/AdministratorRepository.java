
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.Customer;
import domain.HandyWorker;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

	@Query("select a from Administrator where a.id = ?1")
	int findAdministratorById(int id);
	@Query("select f from Customer f join f.fixUpTask t where f.fixUpTask.size > (select avg(f.fixUpTask.size)+(avg(f.fixUpTask.size)/10)*1.0 from Customer f) order by t.application.size")
	Collection<Customer> findCustomers10PerCentMoreFixUpThanAvgOrderApplication();
	@Query("select f from HandyWorker f join f.application a where a.status='accepted' and f.application.size > (select avg(f.application.size)+(avg(f.application.size)/10)*1.0 from HandyWorker f) order by f.application.size")
	Collection<HandyWorker> findHandyWorkers10PerCentMoreAppsThanAvgAcepptedOrderApplication();
	@Query("select c from Customer c order by c.complaint.size DESC")
	List<Customer> topTreeCustomerOrderByComplaints();
	@Query("select distinct h from HandyWorker h join h.application t join t.fixUpTask f join f.complaint c group by h.id order by sum(f.complaint.size) DESC;")
	List<HandyWorker> topTreeHandyWorkerOrderByComplaints();
}
