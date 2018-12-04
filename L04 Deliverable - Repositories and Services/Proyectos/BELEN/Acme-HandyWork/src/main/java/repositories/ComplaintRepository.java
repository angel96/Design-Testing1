package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Complaint;
import domain.Customer;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer>{
	
	@Query("select f.complaint from HandyWorker h join h.application a join a.fixUpTask f where h.id = ?1")
	Collection<Complaint> findComplaintByHandyWorkerId(int handyWId);
	
	@Query("select c from Complaint c where c.referee is empty")
	Collection<Complaint> findComplaintNoRefereeAssigned();
	
	@Query("select c from Complaint c where c.referee is not empty")
	Collection<Complaint> findComplaintRefereeAssigned();
	
	@Query("select c from Complaint c where c.referee.id = ?1")
	Collection<Complaint> findComplaintByRefereeId(int refereeId);
	
	@Query("select c from Customer c join c.complaint x where x.id = ?1")
	Customer findCustomerByComplaintId(int complaintId);
	
	@Query("select c.complaint from Customer c where c.id = ?1")
	Collection<Complaint> findComplaintByCustomerId(int customerId);

	@Query("select min(f.complaint.size) from FixUpTask f")
	double findMinimumOfComplaintsPerFixUpTask();
	
	@Query("select max(f.complaint.size) from FixUpTask f")
	double findMaximumOfComplaintsPerFixUpTask();
	
	@Query("select stddev(f.complaint.size) from FixUpTask f")
	double findStandartDeviationOfComplaintsPerFixUpTask();
	
	@Query("select avg(f.complaint.size) from FixUpTask f")
	double findAverageOfComplaintsPerFixUpTask();
	
}
