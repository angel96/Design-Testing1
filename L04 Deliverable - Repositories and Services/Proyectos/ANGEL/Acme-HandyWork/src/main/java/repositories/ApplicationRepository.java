
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;
import domain.Customer;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	@Query("select avg(f.application.size) from FixUpTask f")
	double findAVGOfApplicationPerFixUpTask();
	@Query("select min(f.application.size) from FixUpTask f")
	double findMINOfApplicationPerFixUpTask();
	@Query("select max(f.application.size) from FixUpTask f")
	double findMAXOfApplicationPerFixUpTask();
	@Query("select stddev(f.application.size) from FixUpTask f")
	double findATDDEVOfApplicationPerFixUpTask();

	@Query("select avg(f.offeredPrice) from Application f")
	double findAVGOfPriceOfferedInApplicatio();
	@Query("select min(f.offeredPrice) from Application f")
	double findMINOfPriceOfferedInApplicatio();
	@Query("select max(f.offeredPrice) from Application f")
	double findMAXOfPriceOfferedInApplicatio();
	@Query("select stddev(f.offeredPrice) from Application f")
	double findATDDEVOfPriceOfferedInApplicatio();

	@Query("select (select count(a) from Application a where a.status = 'pending')*1.0/count(ap) from Application ap")
	double findRatioOfPendingApplications();

	@Query("select count(f.status)*1.0/(select count(t)*1.0 from Application t) from Application f where f.status = 'accepted'")
	double findRationOfAcceptedAplications();

	@Query("select count(f.status)*1.0/(select count(t)*1.0 from Application t) from Application f where f.status = 'rejected'")
	double findRationOfRejectedApplications();

	@Query("select (count(a)*1.0/(select count(ap) from Application ap)) from Application a where current_date() > a.momentElapsed and a.status = 'pending'")
	double findRationOfPendingApplicationCannotChangeItsStatus();
	//10.2
	@Query("select f.application from Customer c join c.fixUpTask f where c = ?1")
	Collection<Application> getApplicationsByCustomer(int id);

	@Query("select c from Customer c join c.fixUpTask f join f.application a where a.id = ?1")
	Customer getCustomerByApplication(int id);

	@Query("select a from Application a join a.fixUpTask f join f.phases p where p.id = ?1 and a.status = 'accepted'")
	Application getApplicationAceptedByPhase(int id);
}
