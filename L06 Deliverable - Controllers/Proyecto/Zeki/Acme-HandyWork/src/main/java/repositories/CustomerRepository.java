
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("select c from Customer c where c.account.id = ?1")
	Customer findByUserAccount(int id);

	@Query("select avg(f.fixUpTask.size) from Customer f")
	double findAVGOfFixUpTaskPerUser();
	@Query("select min(f.fixUpTask.size) from Customer f")
	double findMINOfFixUpTaskPerUser();
	@Query("select max(f.fixUpTask.size) from Customer f")
	double findMAXOfFixUpTaskPerUser();
	@Query("select stddev(f.fixUpTask.size) from Customer f")
	double findATDDEVOfFixUpTaskPerUser();
}
