
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Aolet;
import domain.FixUpTask;

@Repository
public interface AoletRepository extends JpaRepository<Aolet, Integer> {

	@Query("select a from Actor a where a.account.id = ?1")
	Actor findActorByUserAccount(int id);

	@Query("select f.aolets from Customer c join c.fixUpTask f where c.account.id = ?1")
	Collection<Aolet> getAoletsByCustomer(int customer);

	@Query("select f.aolets from FixUpTask f where f.id = ?1")
	Collection<Aolet> getAoletsByFixUpTask(int fixuptask);

	@Query("select a from FixUpTask f join f.aolets a where f.id = ?1 and a.finalMode = true")
	Collection<Aolet> getAoletsByFixUpTaskFinalMode(int fixuptask);

	@Query("select f from FixUpTask f join f.aolets a where a.id = ?1")
	FixUpTask getFixUpTaskByAolet(int aolet);
}
