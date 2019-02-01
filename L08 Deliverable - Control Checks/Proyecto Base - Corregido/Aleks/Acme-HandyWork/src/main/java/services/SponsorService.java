
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SponsorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
import domain.Box;
import domain.Profile;
import domain.Sponsor;
import domain.Sponsorship;

@Service
@Transactional
public class SponsorService {

	@Autowired
	private SponsorRepository	sponsorRepository;

	@Autowired
	private BoxService			boxService;


	public Collection<Sponsor> findAll() {
		return this.sponsorRepository.findAll();
	}

	public Collection<Sponsorship> getSponsorshipsBySponsor(final Sponsor s) {
		Collection<Sponsorship> result;
		Assert.notNull(s);
		result = this.sponsorRepository.getSponsorshipsBySponsor(s.getId());
		Assert.isTrue(result.size() >= 0);
		return result;
	}

	public Sponsor findById(final int id) {
		return this.sponsorRepository.findOne(id);
	}

	public Sponsor findByUserAccount(final int userAccount) {
		Assert.notNull(userAccount);
		return this.sponsorRepository.findByUserAccount(userAccount);
	}
	public Sponsor createSponsor() {
		Sponsor w;

		w = new Sponsor();

		w.setName("");
		w.setMiddleName("");
		w.setSurname("");
		w.setEmail("");
		w.setAdress("");
		w.setPhoto("");
		w.setSponsorship(new ArrayList<Sponsorship>());
		w.setProfiles(new ArrayList<Profile>());
		w.setBoxes(new ArrayList<Box>());

		UserAccount user;
		user = new UserAccount();
		user.setUsername("");
		user.setPassword("");
		user.setEnabled(true);
		Authority authority;
		authority = new Authority();
		authority.setAuthority(Authority.SPONSOR);
		user.addAuthority(authority);

		w.setAccount(user);

		return w;
	}

	public Sponsor save(final Sponsor spo) {
		if (spo.getId() != 0) {
			final UserAccount account = this.sponsorRepository.findOne(spo.getId()).getAccount();
			account.setUsername(spo.getAccount().getUsername());
			account.setPassword(Utiles.hashPassword(spo.getAccount().getPassword()));
			account.setAuthorities(spo.getAccount().getAuthorities());
			spo.setAccount(account);
		} else {
			spo.getAccount().setPassword(Utiles.hashPassword(spo.getAccount().getPassword()));
			final Collection<Box> boxes = this.boxService.save(Utiles.initBoxes());
			spo.setBoxes(boxes);
		}
		Sponsor modify;

		modify = this.sponsorRepository.saveAndFlush(spo);

		return modify;
	}

	public void deleteSponsor(final int id) {

		UserAccount user;
		user = LoginService.getPrincipal();

		Sponsor s;
		s = this.findById(id);

		if (s != null && user.equals(s.getAccount()))
			this.sponsorRepository.delete(id);
		else
			throw new IllegalAccessError("Trying to delete a sponsor which is not the same as logged");
	}

}
