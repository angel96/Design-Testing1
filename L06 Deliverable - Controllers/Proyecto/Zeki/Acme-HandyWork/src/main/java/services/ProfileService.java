
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ProfileRepository;
import security.LoginService;
import domain.Actor;
import domain.Profile;

@Service
@Transactional
public class ProfileService {

	@Autowired
	private ProfileRepository	profileRepository;


	//	@Autowired
	//	private AdministratorService	adminSerivice;

	public Collection<Profile> findAll() {
		return this.profileRepository.findAll();
	}

	public Profile findOne(final int id) {
		return this.profileRepository.findOne(id);
	}

	public Actor getActorByUser(final int id) {
		return this.profileRepository.findActorByUserAccountId(id);
	}

	public Profile save(final Profile p) {

		Profile modify;
		modify = this.profileRepository.save(p);
		Actor a;
		a = this.profileRepository.findActorByUserAccountId(LoginService.getPrincipal().getId());
		Collection<Profile> profiles;
		profiles = a.getProfiles();
		profiles.add(modify);
		a.setProfiles(profiles);
		return modify;
	}

	public void deleteProfile(final int id) {
		Profile p;
		p = this.findOne(id);
		this.profileRepository.delete(p);
		Collection<Profile> pro;
		Actor a;
		a = this.profileRepository.findActorByUserAccountId(LoginService.getPrincipal().getId());
		pro = a.getProfiles();
		pro.remove(p);
		a.setProfiles(pro);

	}
}
