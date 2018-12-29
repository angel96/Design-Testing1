
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.MesageRepository;
import security.LoginService;
import utilities.Utiles;
import domain.Actor;
import domain.Box;
import domain.Mesage;

@Service
@Transactional
public class MesageService {

	@Autowired
	private MesageRepository	messageRepository;

	@Autowired
	private BoxService			boxService;


	public Collection<Mesage> getMessagesByBox(final int boxId) {
		return this.messageRepository.getMessagesByBox(boxId);
	}

	public Mesage findOne(final int id) {
		return this.messageRepository.findOne(id);
	}

	public Mesage sendMessage(final Mesage message) {

		Mesage result;

		result = this.messageRepository.save(message);

		//Message is sent
		final Actor sender = result.getSender();

		Box outBox;
		outBox = this.boxService.getActorSendedBox(sender.getId());

		Collection<Mesage> messagesInSendedBox;
		messagesInSendedBox = outBox.getMessage();
		messagesInSendedBox.add(result);
		outBox.setMessage(messagesInSendedBox);

		Collection<Box> boxesMessage;
		boxesMessage = result.getBox();
		boxesMessage.add(outBox);
		result.setBox(boxesMessage);

		this.received(result, Utiles.spamWord(Utiles.limpiaString(result.getSubject())) && Utiles.spamWord(Utiles.limpiaString(result.getBody())));

		return result;
	}
	public void received(final Mesage result, final Boolean spam) {
		Box box;
		final Collection<Actor> recipients = result.getReceiver();
		for (final Actor actor : recipients) {
			if (spam)
				box = this.boxService.getActorSpamBox(actor.getId());
			else
				box = this.boxService.getActorEntryBox(actor.getId());

			Collection<Box> boxesMessage;
			boxesMessage = result.getBox();
			boxesMessage.add(box);
			result.setBox(boxesMessage);

			Collection<Mesage> messagesInSpamBox;
			messagesInSpamBox = box.getMessage();
			messagesInSpamBox.add(result);
			box.setMessage(messagesInSpamBox);
		}

	}

	//In case a mesage wants to be moved from SpamBox to InBox
	public void moveToInBox(final Mesage mesage) {
		final Collection<Box> currentBox = mesage.getBox();
		currentBox.clear();

		final Box entryBox = this.boxService.getActorEntryBox(LoginService.getPrincipal().getId());

		final Collection<Mesage> messFromEntryBox = entryBox.getMessage();

		messFromEntryBox.add(mesage);

		entryBox.setMessage(messFromEntryBox);

		currentBox.add(entryBox);

		mesage.setBox(currentBox);

	}

	public void moveToTrash(final Mesage mesage) {

		final Collection<Box> currentBoxes = mesage.getBox();
		currentBoxes.clear();

		final Box trashBoxFromUser = this.boxService.getActorTrashBox(LoginService.getPrincipal().getId());
		final Collection<Mesage> mess = trashBoxFromUser.getMessage();

		mess.add(mesage);
		trashBoxFromUser.setMessage(mess);

		currentBoxes.add(trashBoxFromUser);

		mesage.setBox(currentBoxes);
	}
	public void moveToOtherBox(final Box boxToBeMoved, final Mesage mesage) {

	}
	public void deleteFromSystem(final Mesage message) {
		this.messageRepository.delete(message);
	}
}
