
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.ActorRepository;
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


	//LOS BAN Y UNBAN

	public Message sendIndividualMessage(final Actor sender, final Collection<Actor> recipients, final Message m) {
		//ASSERTS VARIOS
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
}
