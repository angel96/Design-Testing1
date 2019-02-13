
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
	public Mesage createMessage(final Actor a) {
		Mesage message;
		message = new Mesage();

		message.setSender(a);
		message.setBody("");
		message.setMoment(new Date());
		message.setTags(new ArrayList<String>());
		message.setSubject("");

		message.setPriority("NEUTRAL");
		message.setBox(new ArrayList<Box>());
		message.setReceiver(new ArrayList<Actor>());

		return message;
	}

	public Mesage sendMessage(final Mesage message) {

		Mesage result;

		result = this.messageRepository.save(message);

		//Message is sent
		final Actor sender = result.getSender();

		Box outBox;
		if (message.getTags().contains("Application"))
			outBox = this.boxService.getActorEntryBox(sender.getId());
		else
			outBox = this.boxService.getActorSendedBox(sender.getId());

		Collection<Mesage> messagesInSendedBox;
		messagesInSendedBox = outBox.getMessage();
		messagesInSendedBox.add(result);
		outBox.setMessage(messagesInSendedBox);

		Collection<Box> boxesMessage;
		boxesMessage = result.getBox();
		boxesMessage.add(outBox);
		result.setBox(boxesMessage);
		final boolean spam = Utiles.spamWord(Utiles.limpiaString(result.getSubject())) && Utiles.spamWord(Utiles.limpiaString(result.getBody()));
		this.received(result, spam);
		sender.setSuspicious(spam);
		return result;
	}
	public void received(final Mesage result, final Boolean spam) {

		final Collection<Actor> recipients = result.getReceiver();

		final int userlogged = LoginService.getPrincipal().getId();

		Collection<Box> boxes;

		if (spam)
			boxes = this.messageRepository.getBoxesFromActors("Spam Box", recipients, userlogged);
		else
			boxes = this.messageRepository.getBoxesFromActors("In Box", recipients, userlogged);

		Collection<Box> boxesMessage;
		boxesMessage = result.getBox();
		boxesMessage.addAll(boxes);
		result.setBox(boxesMessage);

		for (final Box box : boxesMessage) {

			Collection<Mesage> messagesInBox;
			messagesInBox = box.getMessage();
			messagesInBox.add(result);
			box.setMessage(messagesInBox);
		}

	}

	public Integer moveTo(final String boxCase, final Mesage mesage) {

		final Actor a = this.boxService.findActorByUserAccount(LoginService.getPrincipal().getId());

		Collection<Box> mesageBoxes;
		mesageBoxes = mesage.getBox();

		Collection<Box> currentBoxes;
		currentBoxes = this.messageRepository.getMesageBoxesFromActor(mesage.getId(), a.getId());
		Box box = null;

		if (boxCase.equals("In Box")) {
			box = this.boxService.getActorEntryBox(a.getId());
			mesageBoxes.removeAll(currentBoxes);
		} else if (boxCase.equals("Trash Box")) {
			box = this.boxService.getActorTrashBox(a.getId());
			mesageBoxes.removeAll(currentBoxes);
		} else
			box = this.boxService.getActorOtherBox(a.getId(), Integer.valueOf(boxCase));

		Collection<Mesage> mess;
		mess = box.getMessage();

		mess.add(mesage);
		box.setMessage(mess);

		mesageBoxes.add(box);
		mesage.setBox(mesageBoxes);

		this.messageRepository.save(mesage);

		return box.getId();
	}

	public void deleteFromSystem(final Mesage mesage) {
		final Actor a = this.boxService.findActorByUserAccount(LoginService.getPrincipal().getId());

		Box b;
		b = this.boxService.getActorTrashBox(a.getId());

		Collection<Mesage> mess;
		mess = b.getMessage();

		Collection<Box> boxesMesage;
		boxesMesage = mesage.getBox();

		if (mess.contains(mesage)) {
			mess.remove(mesage);
			b.setMessage(mess);

			boxesMesage.remove(b);
			mesage.setBox(boxesMesage);
		}

	}
}
