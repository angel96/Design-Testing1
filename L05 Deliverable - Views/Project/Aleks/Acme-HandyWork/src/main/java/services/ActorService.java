
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Actor;
import domain.Box;
import domain.Message;

@Service
@Transactional
public class ActorService {

	@Autowired
	private MessageService	messages;


	public Message sendMessage(final Actor sender, final Collection<Actor> recipients, final Message m) {
		//ASSERTS VARIOS
		Collection<Box> box;
		box = new ArrayList<>();
		Box entry;
		entry = new Box();
		//		entry.
		m.setSender(sender);
		m.setReceiver(recipients);
		//		m.setb
		return null;
	}
}
