
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SponsorRepository;
import security.Authority;
import security.UserAccount;
import domain.Sponsor;
import domain.Sponsorship;

@Service
@Transactional
public class SponsorService {

	@Autowired
	private SponsorRepository	sponsorRepository;


	public Collection<Sponsor> findAll() {
		return this.sponsorRepository.findAll();
	}

	public Collection<Sponsorship> getSponsorshipsBySponsor(final int id) {
		return this.sponsorRepository.getSponsorshipsBySponsor(id);
	}

	public Sponsor findById(final int id) {
		return this.sponsorRepository.findOne(id);
	}
	public Sponsor create() {
		Sponsor w;

		w = new Sponsor();

		w.setName("");
		w.setMiddleName("");
		w.setSurname("");
		w.setEmail("");
		w.setAdress("");
		w.setBan(false);
		w.setPhoto("");

		//		final Collection<Message> message;
		//		w.setMessage(message);
		//		final Collection<Sponsorship> sponsorship;
		//		w.setSponsorship(sponsorship);
		//		final Collection<Profile> profile;
		//		w.setProfiles(profile);

		UserAccount user;
		user = new UserAccount();
		user.setUsername("");
		user.setPassword("");

		Authority authority;
		authority = new Authority();

		authority.setAuthority(Authority.SPONSOR);

		user.addAuthority(authority);

		w.setAccount(user);

		return w;
	}

	public Sponsor addSponsor(final Sponsor s) {
		Assert.notNull(s);
		return this.sponsorRepository.save(s);
	}

	public Sponsor updateSponsor(final int id, final Sponsor s) {
		Sponsor update;
		update = this.findById(id);
		Assert.notNull(update);
		final int version = update.getVersion();

		update.setId(id);
		update.setVersion(version + 1);
		update.setName(s.getName());
		update.setMiddleName(s.getMiddleName());
		update.setSurname(s.getSurname());
		update.setEmail(s.getEmail());
		update.setPhone(s.getPhone());
		update.setPhoto(s.getPhoto());
		update.setProfiles(s.getProfiles());
		update.setMessage(s.getMessage());
		update.setAdress(s.getAdress());
		update.setAccount(s.getAccount());

		return this.sponsorRepository.save(update);
	}

	public void deleteSponsor(final int id) {
		Assert.notNull(this.findById(id));
		this.sponsorRepository.delete(id);
	}

}
