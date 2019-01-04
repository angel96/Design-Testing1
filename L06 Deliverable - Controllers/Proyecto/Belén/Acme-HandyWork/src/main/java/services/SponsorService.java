
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SponsorRepository;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
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

	public Sponsor findOne(final int idReferee) {
		return this.sponsorRepository.findOne(idReferee);
	}

	public Sponsor findByUserAccount(final int userAccount) {
		Assert.notNull(userAccount);
		return this.sponsorRepository.findByUserAccount(userAccount);
	}

	public Sponsor addSponsor(final Sponsor s) {
		Sponsor result;
		Assert.notNull(s);
		result = this.sponsorRepository.save(s);
		Assert.notNull(result);
		return result;
	}

	public Sponsor save(final Sponsor spo) {
		if (spo.getId() != 0) {
			final UserAccount account = this.findOne(spo.getId()).getAccount();
			account.setUsername(spo.getAccount().getUsername());
			account.setPassword(Utiles.hashPassword(spo.getAccount().getPassword()));
			account.setAuthorities(spo.getAccount().getAuthorities());
			spo.setAccount(account);
		} else
			spo.getAccount().setPassword(Utiles.hashPassword(spo.getAccount().getPassword()));

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
