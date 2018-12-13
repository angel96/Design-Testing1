
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import utilities.Utiles;
import domain.Actor;
import domain.Box;
import domain.Message;
import domain.SpamWord;

@Service
@Transactional
public class ActorService {

	@Autowired
	private ActorRepository				actorRepository;

	@Autowired
	private MessageService				messages;
	@Autowired
	private SpamWordService				spam;
	private final Collection<SpamWord>	spamwords	= this.spam.findAll();


	//TODO el broadcast

	public Message sendIndividualMessage(final Actor sender, final Collection<Actor> recipients, final Message m) {
		Collection<Box> box;
		box = new ArrayList<>();
		Box entry;
		Box spam;
		entry = new Box();
		spam = new Box();
		entry.setName("entry");
		spam.setName("spam");
		m.setSender(sender);
		m.setReceiver(recipients);
		if (Utiles.checkSpamOnStrings(sender, this.spamwords, m.getBody())) {
			box.add(spam);
			m.setBox(box);
		} else
			box.add(entry);
		m.setBox(box);
		if (Utiles.checkSpamOnStrings(sender, this.spamwords, m.getSubject())) {
			box.add(spam);
			m.setBox(box);
		} else
			box.add(entry);
		m.setBox(box);

		if (Utiles.checkSpamOnCollection(sender, this.spamwords, m.getTags())) {
			box.add(spam);
			m.setBox(box);
		} else
			box.add(entry);
		m.setBox(box);

		Message me;
		me = this.messages.save(m);
		this.actorRepository.save(sender);
		return me;
	}

	public void banActor(final Actor a) {
		Assert.isTrue(a.isBan() == false);
		Actor ac;
		ac = this.actorRepository.findActorByUserAccountId(LoginService.getPrincipal().getId());
		Assert.notNull(ac);
		Assert.isTrue(Utiles.findAuthority(ac.getAccount().getAuthorities(), Authority.ADMIN));
		a.setBan(true);
		this.actorRepository.save(a);
	}

	public void unbanActor(final Actor a) {
		Assert.isTrue(a.isBan() == true);
		Actor ac;
		ac = this.actorRepository.findActorByUserAccountId(LoginService.getPrincipal().getId());
		Assert.notNull(ac);
		Assert.isTrue(Utiles.findAuthority(ac.getAccount().getAuthorities(), Authority.ADMIN));
		a.setBan(false);
		this.actorRepository.save(a);
	}
}
