
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Box;
import domain.Referee;

@Repository
public interface RefereeRepository extends JpaRepository<Referee, Integer> {

	@Query("select b from Referee a join a.boxes b where a.id=?1 and b.fromSystem = 0")
	public Collection<Box> manageNotSystemBoxes(int id);
	@Query("select r from Referee r where r.account.id=?1")
	Referee findOneByUserAccount(int userAccount);
}
