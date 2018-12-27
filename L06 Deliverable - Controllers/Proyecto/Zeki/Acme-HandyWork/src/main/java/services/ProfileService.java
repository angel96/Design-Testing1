
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ProfileRepository;
import security.LoginService;
import domain.Administrator;
import domain.Profile;

@Service
@Transactional
public class ProfileService {

	@Autowired
	private ProfileRepository		profileRepository;

	@Autowired
	private AdministratorService	adminSerivice;


	public Collection<Profile> findAll() {
		return this.profileRepository.findAll();
	}

	public Profile findOne(final int id) {
		return this.profileRepository.findOne(id);
	}

	public Profile save(final Profile p) {

		if (LoginService.getPrincipal().getAuthorities().toString().equals("[ADMIN]")) {
			System.out.println(LoginService.getPrincipal().getId());
			Administrator a;
			System.out.println(LoginService.getPrincipal().getId());
			a = this.profileRepository.findAdministratorByUserAccountId(LoginService.getPrincipal().getId());
			System.out.println(a);
			Collection<Profile> profiles;
			profiles = this.profileRepository.getProfilesByAdmin(a.getId());
			System.out.println(profiles);
			profiles.add(p);
			a.setProfiles(profiles);
			this.adminSerivice.save(a);
		} else if (LoginService.getPrincipal().getAuthorities().toString().equals("[HANDY_WORKER]")) {

		} else if (LoginService.getPrincipal().getAuthorities().toString().equals("[CUSTOMER]")) {

		} else if (LoginService.getPrincipal().getAuthorities().toString().equals("[REFEREE]")) {

		} else if (LoginService.getPrincipal().getAuthorities().toString().equals("[SPONSOR]")) {

		}

		Profile modify;
		modify = this.profileRepository.save(p);
		return modify;
	}
}
