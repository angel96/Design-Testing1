
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Section;
import domain.Tutorial;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {

	@Query("select a from Actor a where a.account.id = ?1")
	Actor findByUserAccount(int id);
	@Query("select t from Tutorial t join t.section s where s.id = ?1")
	Tutorial findTutorialBySection(int id);
}
