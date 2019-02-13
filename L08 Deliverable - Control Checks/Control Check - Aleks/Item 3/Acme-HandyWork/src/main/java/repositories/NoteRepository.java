
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;
import domain.HandyWorker;
import domain.Note;
import domain.Referee;
import domain.Report;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {

	@Query("select r from Report r join r.notes n where n.id = ?1")
	Report findReportByNoteId(int noteId);
	//las de customer
	@Query("select c.notes from Customer c where c.id = ?1")
	Collection<Note> findNotesByCustomerId(int customerId);

	@Query("select a from Customer a where a.account.id = ?1")
	Customer findCustomerByUserAccountId(int id);
	//las de handy
	@Query("select c.notes from HandyWorker c where c.id = ?1")
	Collection<Note> findNotesByHandyWorkerId(int handyId);

	@Query("select a from HandyWorker a where a.account.id = ?1")
	HandyWorker findHandyWorkerByUserAccountId(int id);
	//las de referee
	@Query("select c.notes from Referee c where c.id = ?1")
	Collection<Note> findNotesByRefereeId(int refereeId);

	@Query("select a from Referee a where a.account.id = ?1")
	Referee findRefereeByUserAccountId(int id);
	//find de los tres actores referenciados
	@Query("select distinct n from HandyWorker h join h.application a join a.fixUpTask f join f.complaint c join c.report r join r.notes n where r.finalMode = true and h.id = ?1")
	Collection<Note> selectNoteReferenceHandy(int id);

	@Query("select distinct n from Customer c join c.complaint g join g.report r join r.notes n where r.finalMode = true and c.id = ?1")
	Collection<Note> selectNoteReferenceCustomer(int id);

	@Query("select distinct n from Complaint c join c.report r join r.notes n where r.complaint.referee.id = ?1 and r.finalMode = true")
	Collection<Note> selectNoteReferenceReferee(int id);

	@Query("select r from Report r join r.notes n where n.id = ?1")
	Report findReportByNote(int id);
}
