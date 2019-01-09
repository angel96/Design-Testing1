
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Curriculum;
import domain.HandyWorker;
import domain.Profile;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Integer> {

	@Query("select h from HandyWorker h where h.account.id = ?1")
	HandyWorker findHandyByUserAccountId(int id);

	@Query("select p from HandyWorker h join h.profiles p where p.socialNetworkName like 'LinkedIn' and h.id = ?1")
	Profile findLinkedInByHandyWorkerId(int id);
}
