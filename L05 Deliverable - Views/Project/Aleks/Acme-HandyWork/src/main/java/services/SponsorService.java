
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
import domain.Actor;
import domain.Box;
import domain.Message;
import domain.Sponsor;
import domain.Sponsorship;

@Service
@Transactional
public class SponsorService {

	@Autowired
	private SponsorRepository	sponsorRepository;
	@Autowired
	private ActorService		actorService;
	@Autowired
	private BoxService			boxService;


	public Collection<Sponsor> findAll() {
		return this.sponsorRepository.findAll();
	}

	public Sponsor create() {
		Sponsor s;
		s = new Sponsor();
		s = Utiles.createSponsor();
		Assert.notNull(s);
		return s;
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

	public Sponsor updateSponsor(final Sponsor s) {

		UserAccount user;
		user = LoginService.getPrincipal();
		Sponsor saved;

		if (user.equals(s.getAccount()))
			saved = this.sponsorRepository.save(s);
		else
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

	public void sendMessage(final Actor sender, final Collection<Actor> recipient, final Message m) {
		Assert.notNull(LoginService.getPrincipal().getAuthorities());
		Assert.notEmpty(recipient);
		Assert.notNull(m);
		this.actorService.sendIndividualMessage(sender, recipient, m);
	}

	public Collection<Box> manageNotSystemBoxes() {
		Assert.notNull(this.sponsorRepository.findByUserAccountId(LoginService.getPrincipal().getId()));
		Collection<Box> boxes;
		boxes = this.boxService.findAllNotSystemBoxes(LoginService.getPrincipal().getId());
		return boxes;
	}

}
