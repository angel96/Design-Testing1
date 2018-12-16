
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


	public Collection<Message> findAllMessagesSendedBy(final int id) {
		Assert.notNull(id);
		Collection<Message> m;
		m = this.messageRepository.findAllMessagesSendedByAnActor(id);
		Assert.notNull(m);
		return m;
	}

}
