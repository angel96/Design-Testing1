
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.CustomisationSystem;

@Repository
public interface CustomisationSystemRepository extends JpaRepository<CustomisationSystem, Integer> {

	@Query("select a from Actor a where a.suspicious = 1")
	Collection<Actor> findAllSuspiciousActors();
}
