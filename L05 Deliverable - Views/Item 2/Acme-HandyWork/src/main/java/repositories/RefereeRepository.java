package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Referee;

@Repository
public interface RefereeRepository extends JpaRepository<Referee, Integer>{

	@Query("select r from Referee r where r.account.id = ?1")
	Referee findByUserAccount(int idReferee);
}

	
