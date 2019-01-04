
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

	public void deleteBox(final Box box) {
		if (box.getFromSystem() == false)
			this.boxRepository.delete(box);
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

	public Box getActorOtherBox(final int actorId, final String other) {
		return this.boxRepository.getActorOtherBox(actorId, other);
	}
	public Collection<Box> getBoxesFromActor(final int userAccount) {
		return this.boxRepository.getBoxesFromActor(userAccount);
	}
}
