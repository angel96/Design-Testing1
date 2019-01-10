
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.BoxRepository;
import security.LoginService;
import domain.Actor;
import domain.Box;
import domain.Mesage;

@Service
@Transactional
public class BoxService {

	@Autowired
	private BoxRepository	boxRepository;


	public Collection<Actor> findAllActorsSystem() {
		return this.boxRepository.findAllActorsSystem(LoginService.getPrincipal().getId());
	}

	public Actor findActorByUserAccount(final int id) {
		return this.boxRepository.getActorByUserAccount(id);
	}

	public Box findOne(final int id) {
		final Box box = this.boxRepository.findOne(id);
		Assert.isTrue(this.findActorByUserAccount(LoginService.getPrincipal().getId()).getBoxes().contains(box), "");
		return box;
	}

	public Box save(final Box box) {

		Box saved;
		saved = this.boxRepository.save(box);

		final Actor actor = this.boxRepository.getActorByUserAccount(LoginService.getPrincipal().getId());
		Collection<Box> boxesActor;
		boxesActor = actor.getBoxes();
		boxesActor.add(saved);
		actor.setBoxes(boxesActor);

		return saved;
	}

	public Collection<Box> save(final Collection<Box> boxes) {
		return this.boxRepository.save(boxes);
	}

	public Box getActorEntryBox(final int actorId) {
		return this.boxRepository.getActorEntryBox(actorId);
	}
	public Box getActorSpamBox(final int actorId) {
		return this.boxRepository.getActorSpamBox(actorId);
	}
	public Box getActorSendedBox(final int actorId) {
		return this.boxRepository.getActorSendedBox(actorId);
	}
	public Box getActorTrashBox(final int actorId) {
		return this.boxRepository.getActorTrashBox(actorId);
	}

	public Box getActorOtherBox(final int actorId, final int box) {
		return this.boxRepository.getActorOtherBox(actorId, box);
	}
	public Collection<Box> getBoxesFromActor(final int userAccount) {
		return this.boxRepository.getBoxesFromActor(userAccount);
	}
	public Collection<Box> getBoxesFromActorNoSystem(final int userAccount) {
		return this.boxRepository.getBoxesFromActorNoSystem(userAccount);
	}

	public void deleteBox(final Box box) {
		Assert.isTrue(!box.getFromSystem());

		final Actor a = this.boxRepository.getActorByUserAccount(LoginService.getPrincipal().getId());

		Collection<Mesage> messBox;
		messBox = box.getMessage();

		for (final Mesage m : messBox)
			if (m.getBox().contains(box)) {
				final Collection<Box> boxes = m.getBox();
				boxes.remove(box);
				m.setBox(boxes);
			}

		messBox.clear();
		box.setMessage(messBox);

		Collection<Box> boxesActor;
		boxesActor = a.getBoxes();
		boxesActor.remove(box);
		a.setBoxes(boxesActor);

		this.boxRepository.delete(box);
	}

}
