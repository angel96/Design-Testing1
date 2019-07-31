
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Box;
import domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("select c from Customer c where c.account.id = ?1")
	Customer findByUserAccount(int id);
	@Query("select b from Customer a join a.boxes b where a.id=?1 and b.fromSystem = 0")
	public Collection<Box> manageNotSystemBoxes(int id);
}
