
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SponsorshipRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
import domain.Sponsor;
import domain.Sponsorship;
import domain.Tutorial;

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
	public Sponsorship createSponsorship(final Sponsor sponsor, final Tutorial tutorial) {
		Sponsorship result;
		result = new Sponsorship();

		result.setUrlBanner("");
		result.setCreditCard(Utiles.createFakeCreditCard());
		result.setLinkTPage("");
		result.setSponsor(sponsor);
		result.setTutorial(tutorial);

		return result;
	}

	public Sponsorship save(final Sponsorship sponsorship) {

		UserAccount login;
		login = LoginService.getPrincipal();

		Sponsor logged;
		logged = this.serviceSponsor.findByUserAccount(login.getId());

		Assert.notNull(logged);

		Sponsorship saved;

		saved = this.sponsorshipRepository.save(sponsorship);

		return saved;
	}

	public void delete(final int id) {
		final UserAccount user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER) || Utiles.findAuthority(user.getAuthorities(), Authority.SPONSOR));
		this.sponsorshipRepository.delete(id);
	}

	public void delete(final Collection<Sponsorship> sponsorships) {
		final UserAccount user = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER) || Utiles.findAuthority(user.getAuthorities(), Authority.SPONSOR));
		this.sponsorshipRepository.delete(sponsorships);
	}

}
