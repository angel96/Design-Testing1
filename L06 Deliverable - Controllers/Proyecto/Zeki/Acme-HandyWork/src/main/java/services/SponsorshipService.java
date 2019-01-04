
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SponsorshipRepository;
import security.LoginService;
import security.UserAccount;
import domain.Sponsor;
import domain.Sponsorship;

@Service
@Transactional
public class SponsorshipService {

	@Autowired
	private SponsorshipRepository	sponsorshipRepository;

	@Autowired
	private SponsorService			serviceSponsor;


	public Collection<Sponsorship> findAll() {
		return this.sponsorshipRepository.findAll();
	}

	public Sponsorship findOne(final int id) {
		return this.sponsorshipRepository.findOne(id);
	}

	public Sponsorship add(final Sponsorship sponsorship) {

		UserAccount login;
		login = LoginService.getPrincipal();

		Sponsor logged;
		logged = this.serviceSponsor.findByUserAccount(login);

		Assert.notNull(logged);

		Sponsorship saved;

		saved = this.sponsorshipRepository.save(sponsorship);
		System.out.println(saved.getLinkTPage());

		return saved;
	}

	public void delete(final int id) {
		Assert.notNull(this.findOne(id));
		this.sponsorshipRepository.delete(id);
	}

}
