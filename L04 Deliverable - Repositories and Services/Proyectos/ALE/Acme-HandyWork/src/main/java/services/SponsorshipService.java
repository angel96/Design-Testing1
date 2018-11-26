
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SponsorshipRepository;
import security.UserAccount;
import domain.CreditCard;
import domain.Sponsor;
import domain.Sponsorship;

@Service
@Transactional
public class SponsorshipService {

	@Autowired
	private SponsorshipRepository	sponsorshipRepository;

	@Autowired
	private SponsorService			sponsorService;

	@Autowired
	private TutorialService			tutorialService;


	public Collection<Sponsorship> findAll() {
		return this.sponsorshipRepository.findAll();
	}

	public Sponsorship findOne(final int id) {
		return this.sponsorshipRepository.findOne(id);
	}

	public Sponsorship create(final UserAccount userAccount, final int idTutorial) {
		Sponsorship result;
		Sponsor s;
		result = new Sponsorship();
		result.setUrlBanner("");
		result.setCreditCard(new CreditCard());
		result.setLinkTPage("");
		s = this.sponsorService.findByUserAccount(userAccount);
		result.setSponsor(s);
		result.setTutorial(this.tutorialService.findOne(idTutorial));
		return result;
	}
	public Sponsorship add(final Sponsorship s) {
		return null;
	}
	public Sponsorship update(final int id, final Sponsorship newer) {
		return null;
	}
	public void delete(final int id) {
		Assert.notNull(this.findOne(id));
		this.sponsorshipRepository.delete(id);
	}
}
