package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer>{
	
	@Query("select r.reports from Referee r where r.id = ?1")
	Collection<Report> findReportsByRefereeId(int refereeId);
	
	@Query("select r from Report r where r.finalMode = true")
	Collection<Report> findReportinFinalMode();
}
