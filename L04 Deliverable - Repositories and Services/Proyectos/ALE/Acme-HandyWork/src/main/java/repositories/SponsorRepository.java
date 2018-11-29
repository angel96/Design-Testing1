
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Box;
import domain.Sponsor;
import domain.Sponsorship;

@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Integer> {

	@Query("select s from Sponsor s where s.account.id = ?1")
	Sponsor findByUserAccountId(int idUserAccountId);

	@Query("select s.sponsorship from Sponsor s where s.id = ?1")
	Collection<Sponsorship> getSponsorshipsBySponsor(int id);

	@Query("select b from Sponsor a join a.boxes b where a.id=?1 and b.fromSystem = 0")
	public Collection<Box> manageNotSystemBoxes(int id);

}
