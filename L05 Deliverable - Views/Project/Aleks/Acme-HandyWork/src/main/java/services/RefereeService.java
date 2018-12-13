
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RefereeRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
import domain.Actor;
import domain.Box;
import domain.Message;
import domain.Referee;

@Service
@Transactional
public class RefereeService {

	@Autowired
	private RefereeRepository	refereeRepository;
	@Autowired
	private ActorService		actorService;
	@Autowired
	private BoxService			boxService;


	public Collection<Referee> findAll() {
		return this.refereeRepository.findAll();
	}

	public Referee findOne(final int idReferee) {
		return this.refereeRepository.findOne(idReferee);
	}

	public Referee findByUserAccount(final int userAccount) {
		Assert.notNull(userAccount);
		return this.refereeRepository.findByUserAccount(userAccount);
	}

	public Referee create() {
		UserAccount creator;
		creator = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(creator.getAuthorities(), Authority.ADMIN));
		Referee r;
		r = new Referee();
		r = Utiles.createReferee();
		Assert.notNull(r);
		return r;

	}

	public Referee save(final Referee ref) {
		Assert.notNull(ref);
		return this.refereeRepository.save(ref);
	}

	public Referee update(final Referee ref) {
		Assert.notNull(ref);
		Referee saved;

		saved = this.refereeRepository.save(ref);
		return saved;

	}
	public void sendMessage(final Actor sender, final Collection<Actor> recipient, final Message m) {
		Assert.notNull(LoginService.getPrincipal().getAuthorities());
		Assert.notEmpty(recipient);
		Assert.notNull(m);
		this.actorService.sendIndividualMessage(sender, recipient, m);
	}
	public Collection<Box> manageNotSystemBoxes() {
		Assert.notNull(this.refereeRepository.findByUserAccount(LoginService.getPrincipal().getId()));
		Collection<Box> boxes;
		boxes = this.boxService.findAllNotSystemBoxes(LoginService.getPrincipal().getId());
		return boxes;
	}
}
