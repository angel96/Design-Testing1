
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Wage;

@Repository
public interface WageRepository extends JpaRepository<Wage, Integer> {

	@Query("select w from Customer c join c.fixUpTask f join f.wages w where c.account.id = ?1")
	Collection<Wage> getWagesByUser(int accountId);

	@Query("select f.wages from FixUpTask f where f.id = ?1")
	Collection<Wage> getWagesForFixUp(int fixId);
}
