
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

		Collection<Sponsorship> sponsorShipsFromSponsor;
		sponsorShipsFromSponsor = logged.getSponsorship();

		Assert.notNull(logged);

		Sponsorship saved;

		saved = this.sponsorshipRepository.save(sponsorship);

		sponsorShipsFromSponsor.add(sponsorship);
		logged.setSponsorship(sponsorShipsFromSponsor);

		this.serviceSponsor.updateSponsor(logged.getId(), logged);

		return saved;
	}
	public Sponsorship update(final int id, final Sponsorship newer) {

		Sponsorship old, saved;

		old = this.findOne(id);

		old.setSponsor(newer.getSponsor());
		old.setCreditCard(newer.getCreditCard());
		old.setLinkTPage(newer.getLinkTPage());
		old.setTutorial(newer.getTutorial());
		old.setUrlBanner(newer.getUrlBanner());

		UserAccount logged;
		logged = LoginService.getPrincipal();

		if (logged.equals(old.getSponsor().getAccount()))
			saved = this.sponsorshipRepository.save(old);
		else
			throw new IllegalAccessError();

		return saved;
	}
	public void delete(final int id) {
		Assert.notNull(this.findOne(id));
		this.sponsorshipRepository.delete(id);
	}

}
