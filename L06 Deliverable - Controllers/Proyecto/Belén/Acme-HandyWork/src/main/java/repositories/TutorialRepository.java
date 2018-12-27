
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Section;
import domain.Sponsorship;
import domain.Tutorial;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Integer> {

	@Query("select h.tutoriales from HandyWorker h where h.account.id = ?1")
	Collection<Tutorial> getTutorialsByHandyWorker(int id);

	@Query("select t.section from Tutorial t where t.id = ?1")
	Collection<Section> getSectionsByTutorial(int id);

	@Query("select t.sponsorship from Tutorial t where t.id = ?1")
	Collection<Sponsorship> getSponsorshipsByTutorial(int id);
}
