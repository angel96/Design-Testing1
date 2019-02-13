
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Customer;
import domain.CustomisationSystem;
import domain.HandyWorker;

@Repository
public interface CustomisationSystemRepository extends JpaRepository<CustomisationSystem, Integer> {

	@Query("select a from Actor a where a.suspicious = 1 and a.account.enabled = 1")
	Collection<Actor> findAllSuspiciousActors();

	@Query("select a from Actor a where a.account.enabled = 0")
	Collection<Actor> findAllNoEnabledActors();

	@Query("select a from Actor a where a.id = ?1")
	Actor findActor(int id);

	//Querys --> FixUpTask per User 12.5.1
	@Query("select avg (c.fixUpTask.size), min(c.fixUpTask.size), max(c.fixUpTask.size), stddev(c.fixUpTask.size) from Customer c")
	String findFixUpTaskPerUser();

	//Querys --> Application per fixuptask 12.5.2 
	@Query("select avg(f.application.size),min(f.application.size),max(f.application.size), stddev(f.application.size) from FixUpTask f")
	String findApplicationPerFixUpTask();

	//Querys --> Maximum Price of FixUpTask 12.5.3
	@Query("select avg(f.maximumPrice), min(f.maximumPrice), max(f.maximumPrice), stddev(f.maximumPrice) from FixUpTask f")
	String findMaximumPricePerFixUpTask();

	//Querys --> Price offered in Application 12.5.4
	@Query("select avg(f.offeredPrice),min(f.offeredPrice),max(f.offeredPrice),stddev(f.offeredPrice) from Application f")
	String findPriceOfferedInApplication();

	//Querys --> Ratio of applications 12.5.5
	@Query("select (select count(a) from Application a where a.status = 'pending')*1.0/count(ap) from Application ap")
	Double findRatioOfPendingApplications();
	//Querys --> Ratio of applications 12.5.6
	@Query("select count(f.status)*1.0/(select count(t)*1.0 from Application t) from Application f where f.status = 'accepted'")
	Double findRationOfAcceptedAplications();
	//Querys --> Ratio of applications 12.5.7
	@Query("select count(f.status)*1.0/(select count(t)*1.0 from Application t) from Application f where f.status = 'rejected'")
	Double findRationOfRejectedApplications();
	//Querys --> Ratio of applications 12.5.8
	@Query("select (count(a)*1.0/(select count(ap) from Application ap)) from Application a where current_date() > a.momentElapsed and a.status = 'pending'")
	Double findRationOfPendingApplicationCannotChangeItsStatus();

	//Querys --> Complaints Per FixUpTasks --> NO DASHBOARD
	@Query("select avg(f.complaint.size), min(f.complaint.size), max(f.complaint.size), stddev(f.complaint.size) from FixUpTask f")
	String findComplaintsPerFixUpTask();

	//Querys --> Notes per report --> NO DASHBOARD
	@Query("select avg(r.notes.size), min(r.notes.size), max(r.notes.size), stddev(r.notes.size) from Report r")
	String findNotesPerReport();

	//The ratio of fix-up-tasks with a complaint
	@Query("select count(a)*1.0/(select count(t)*1.0 from FixUpTask t) from FixUpTask a where a.complaint.size > 0")
	Double ratioOfFixUpTasksWithComplaint();

	//Querys --> 10% above the average 12.5.9
	@Query("select f from Customer f join f.fixUpTask t where f.fixUpTask.size > (select avg(f.fixUpTask.size)+(avg(f.fixUpTask.size)/10)*1.0 from Customer f) order by t.application.size")
	Collection<Customer> findCustomers10PerCentMoreFixUpThanAvgOrderApplication();
	//Querys --> 10% above the average 12.5.10
	@Query("select f from HandyWorker f join f.application a where a.status='accepted' and f.application.size > (select avg(f.application.size)+(avg(f.application.size)/10)*1.0 from HandyWorker f) order by f.application.size")
	Collection<HandyWorker> findHandyWorkers10PerCentMoreAppsThanAvgAcepptedOrderApplication();

	//Querys --> Tops 3
	@Query("select c from Customer c order by c.complaint.size DESC")
	Collection<Customer> topTreeCustomerOrderByComplaints();
	@Query("select distinct h from HandyWorker h join h.application t join t.fixUpTask f join f.complaint c group by h.id order by sum(f.complaint.size) DESC")
	Collection<HandyWorker> topTreeHandyWorkerOrderByComplaints();

}
