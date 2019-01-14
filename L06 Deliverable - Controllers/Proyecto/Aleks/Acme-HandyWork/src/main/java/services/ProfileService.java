
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ProfileRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Curriculum;
import domain.HandyWorker;
import domain.Profile;

@Service
@Transactional
public class ProfileService {

	@Autowired
	private ProfileRepository	profileRepository;

	@Autowired
	private CurriculumService	serviceCurriculum;


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

	public Profile createProfile() {
		Profile result;
		result = new Profile();
		result.setNick("");
		result.setLink("");
		result.setSocialNetworkName("");
		return result;
	}

	public Profile save(final Profile p) {

		Profile modify;
		modify = this.profileRepository.save(p);
		UserAccount user;
		user = LoginService.getPrincipal();
		Actor a;
		a = this.profileRepository.findActorByUserAccountId(user.getId());

		if (p.getSocialNetworkName().equals("LinkedIn")) {
			HandyWorker h;
			h = this.serviceCurriculum.getHandyByUserAccount(user.getId());
			Curriculum c;
			c = this.serviceCurriculum.createCurriculum();
			h.setCurriculum(c);
			this.serviceCurriculum.save(c);

		}
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
