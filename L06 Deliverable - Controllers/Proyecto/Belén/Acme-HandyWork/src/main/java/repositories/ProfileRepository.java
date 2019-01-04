
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

	@Query("select a.profiles from Administrator a where a.id = ?1")
	Collection<Profile> getProfilesByAdmin(int id);

	@Query("select a from Administrator a where a.account.id = ?1")
	Administrator findAdministratorByUserAccountId(int id);

	//	@Query("select a.profiles from Actor a where a.id = ?1")
	//	Collection<Profile> getProfilesByAdmin(int id);
	//
	//	@Query("select a from Actor a where a.account.id = ?1")
	//	Administrator findAdministratorByUserAccountId(int id);
}
