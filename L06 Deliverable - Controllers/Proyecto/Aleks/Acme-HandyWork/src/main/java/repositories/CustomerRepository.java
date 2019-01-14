
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("select c from Customer c where c.account.id = ?1")
	Customer findByUserAccount(int id);
	@Query("select a from Actor a where a.account.id = ?1")
	Actor findActorByUserAccount(int id);

	@Query("select c from Customer c join c.fixUpTask f where f.id = ?1")
	Customer findByFixUpTask(int id);
}
