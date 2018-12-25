package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Complaint;
import domain.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer>{
	
	@Query("select r from Referee f join f.complaints c join c.report r where f.id = ?1")
	Collection<Report> findReportsByRefereeId(int refereeId);
	
	@Query("select c.report from Complaint c where c.id = ?1")
	Collection<Report> findReportsByComplaintId(int complaintId);
	
	@Query("select c from Report r join r.complaint c where r.id = ?1")
	Complaint findComplaintByReportId(int reportId);
	
	@Query("select r from Report r where r.finalMode = true")
	Collection<Report> findReportinFinalMode();
}
