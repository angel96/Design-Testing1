
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SponsorRepository;
import security.LoginService;
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
	public Sponsor findByUserAccount(final UserAccount userAccount) {
		Sponsor s;
		Assert.notNull(userAccount);
		s = this.sponsorRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(s);
		return s;
	}

	public Sponsor addSponsor(final Sponsor s) {
		Sponsor result;
		Assert.notNull(s);
		result = this.sponsorRepository.save(s);
		Assert.notNull(result);
		return result;
	}

	public Sponsor updateSponsor(final int id, final Sponsor s) {

		UserAccount user;
		user = LoginService.getPrincipal();
		Sponsor update, saved;
		update = this.findById(id);

		if (update != null && s != null && user.equals(update.getAccount())) {
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
			update.setSponsorship(s.getSponsorship());
			saved = this.sponsorRepository.save(update);
		} else
			throw new IllegalAccessError("Trying to modify a Sponsor which is not the same as the logged");

		return saved;
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
