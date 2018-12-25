
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.MessageRepository;
import utilities.Utiles;
import domain.Actor;
import domain.Box;
import domain.Message;

@Service
@Transactional
public class MessageService {

	@Autowired
	private MessageRepository	messageRepository;

	@Autowired
	private BoxService			boxService;


	public Collection<Message> getMessagesByBox(final int boxId) {
		return this.messageRepository.getMessagesByBox(boxId);
	}

	public Message findOne(final int id) {
		return this.messageRepository.findOne(id);
	}

	public Message sendMessage(final Message message) {

		Message result;

		result = this.messageRepository.save(message);

		//Message is sent
		final Actor sender = result.getSender();

		Box outBox;
		outBox = this.boxService.getActorSendedBox(sender.getId());

		Collection<Message> messagesInSendedBox;
		messagesInSendedBox = outBox.getMessage();
		messagesInSendedBox.add(result);
		outBox.setMessage(messagesInSendedBox);
		this.received(result, Utiles.spamWord(Utiles.limpiaString(result.getBody())));

		return result;
	}
	public void received(final Message result, final Boolean spam) {
		Box box;
		final Collection<Actor> recipients = result.getReceiver();
		for (final Actor actor : recipients) {
			if (spam)
				box = this.boxService.getActorSpamBox(actor.getId());
			else
				box = this.boxService.getActorSendedBox(actor.getId());
			Collection<Message> messagesInSpamBox;
			messagesInSpamBox = box.getMessage();
			messagesInSpamBox.add(result);
			box.setMessage(messagesInSpamBox);
		}

	}
	public void delete(final Message message) {
		this.messageRepository.delete(message.getId());
	}
}
