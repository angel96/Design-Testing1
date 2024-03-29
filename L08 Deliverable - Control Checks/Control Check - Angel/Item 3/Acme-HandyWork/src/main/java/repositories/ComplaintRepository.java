
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Complaint;
import domain.Customer;
import domain.FixUpTask;
import domain.Referee;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {

	@Query("select a from Actor a where a.account.id = ?1")
	Actor findActorByUserAccountId(int id);

	@Query("select a from Referee a where a.account.id = ?1")
	Referee findRefereeByUserAccountId(int id);
	//actor
	@Query("select f.complaint from Actor h join h.application a join a.fixUpTask f where h.id = ?1")
	Collection<Complaint> findComplaintByHandyWorkerId(int handyWId);

	@Query("select c from Complaint c where c.referee is empty")
	Collection<Complaint> findComplaintNoRefereeAssigned();

	@Query("select c from Complaint c where c.referee is not empty")
	Collection<Complaint> findComplaintRefereeAssigned();
	//oki
	@Query("select c from Complaint c where c.referee.id = ?1")
	Collection<Complaint> findComplaintByRefereeId(int refereeId);

	@Query("select c from Customer c join c.complaint x where x.id = ?1")
	Customer findCustomerByComplaintId(int complaintId);
	//actor
	@Query("select c.complaint from Actor c where c.id = ?1")
	Collection<Complaint> findComplaintByCustomerId(int customerId);

	@Query("select f from FixUpTask f join f.complaint c where c.id = ?1")
	FixUpTask getFixUpTaskByComplaint(int id);

}
