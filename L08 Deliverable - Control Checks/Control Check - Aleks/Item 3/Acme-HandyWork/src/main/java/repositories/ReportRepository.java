
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Complaint;
import domain.Customer;
import domain.HandyWorker;
import domain.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

	@Query("select r from Actor f join f.complaints c join c.report r where f.id = ?1")
	Collection<Report> findReportsByRefereeId(int refereeId);

	@Query("select c.report from Complaint c where c.id = ?1")
	Collection<Report> findReportsByComplaintId(int complaintId);

	@Query("select c from Report r join r.complaint c where r.id = ?1")
	Complaint findComplaintByReportId(int reportId);

	@Query("select r from Actor a, Report r where r.finalMode = true and a.id = ?1")
	Collection<Report> findReportinFinalModeByActorId(int id);

	@Query("select a from Actor a where a.account.id = ?1")
	Actor findActorByUserAccountId(int id);
	//las 4 magnificas
	@Query("select r from Customer c join c.complaint g join g.report r where r.finalMode = true and c.id = ?1")
	Collection<Report> findReportsByCustomerId(int id);

	@Query("select r from HandyWorker h join h.application a join a.fixUpTask f join f.complaint c join c.report r where r.finalMode = true and h.id = ?1 and a.status = 'accepted'")
	Collection<Report> findReportsByhandyWorkerId(int id);

	@Query("select a from Customer a where a.account.id = ?1")
	Customer findCustomerByUserAccountId(int id);

	@Query("select a from HandyWorker a where a.account.id = ?1")
	HandyWorker findHandyByUserAccountId(int id);
}
