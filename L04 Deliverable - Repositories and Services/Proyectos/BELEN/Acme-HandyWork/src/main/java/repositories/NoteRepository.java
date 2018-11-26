package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Note;
import domain.Report;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer>{
	
	@Query("select r from Report r join r.notes n where n.id = ?1")
	Report findReportByNoteId(int noteId);
	
	@Query("select c.notes from Customer c where c.id = ?1")
	Collection<Note> findNotesByCustomerId(int customerId);
	
	@Query("select stddev(r.notes.size) from Report r")
	double findStandartDeviationOfNotesPerReport();
	
	@Query("select min(r.notes.size) from Report r")
	double findMinimumOfNotesPerReport();
	
	@Query("select max(r.notes.size) from Report r")
	double findMaximumOfNotesPerReport();
	
	@Query("select avg(r.notes.size) from Report r")
	double findAverageOfNotesPerReport();
}
