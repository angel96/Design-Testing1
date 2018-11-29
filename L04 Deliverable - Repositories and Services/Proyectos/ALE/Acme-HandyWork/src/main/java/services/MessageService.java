
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Message;

@Service
@Transactional
public class MessageService {

	@Autowired
	private MessageRepository	messageRepository;


	public Collection<Message> findAllMessagesSendedBy(final Integer actorId) {
		Assert.isTrue(actorId instanceof Integer);
		return this.messageRepository.findAllMessagesSendedByAnActor(actorId);
	}

	public Collection<Message> findAllMessagesReceivedBy(final Integer actorId) {
		Assert.isTrue(actorId instanceof Integer);
		return this.messageRepository.findAllMessagesReceivedByAnActor(actorId);
	}
}
