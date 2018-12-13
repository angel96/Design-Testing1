
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.HandyWorkerRepository;
import security.LoginService;
import utilities.Utiles;
import domain.Actor;
import domain.Box;
import domain.Category;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Message;
import domain.Warranty;

@Service
@Transactional
public class HandyWorkerService {

	@Autowired
	private HandyWorkerRepository	repositoryHandyWorker;
	@Autowired
	private BoxService				boxService;
	@Autowired
	private ActorService			actorService;


	public Collection<HandyWorker> findAll() {
		return this.repositoryHandyWorker.findAll();
	}

	public HandyWorker findOne(final int id) {
		return this.repositoryHandyWorker.findOne(id);
	}

	public HandyWorker findByUserAccount(final int userAccount) {
		HandyWorker result;
		result = this.repositoryHandyWorker.findByUserAccount(userAccount);
		Assert.notNull(result);
		return result;
	}
	public HandyWorker create() {
		HandyWorker handyWorker;
		handyWorker = new HandyWorker();
		handyWorker = Utiles.createHandyWorker();
		Assert.notNull(handyWorker);
		return handyWorker;
	}

	public HandyWorker save(final HandyWorker hw) {
		Assert.notNull(hw);
		return this.repositoryHandyWorker.save(hw);
	}

	public HandyWorker update(final HandyWorker newHw) {
		Assert.notNull(newHw);
		HandyWorker saved;
		saved = this.save(newHw);
		Assert.notNull(saved);
		return saved;
	}

	public Collection<Box> manageNotSystemBoxes() {
		Assert.notNull(this.repositoryHandyWorker.findByUserAccount(LoginService.getPrincipal().getId()));
		Collection<Box> boxes;
		boxes = this.boxService.findAllNotSystemBoxes(LoginService.getPrincipal().getId());
		return boxes;
	}

	public Collection<FixUpTask> findByKeyWord(final String s) {
		return this.repositoryHandyWorker.findAllByKeyWorkd("%" + s + "%");
	}
	public Collection<FixUpTask> findByPrices(final double d1, final double d2) {
		return this.repositoryHandyWorker.findAllByPrices(d1, d2);
	}
	public Collection<FixUpTask> findByCategory(final Category category) {
		return this.repositoryHandyWorker.findAllByCategory(category);
	}
	public Collection<FixUpTask> findByWarranty(final Warranty warranty) {
		return this.repositoryHandyWorker.findAllByWarranty(warranty);
	}

	public void sendMessage(final Actor sender, final Collection<Actor> recipient, final Message m) {
		Assert.notNull(LoginService.getPrincipal().getAuthorities());
		Assert.notEmpty(recipient);
		Assert.notNull(m);
		this.actorService.sendIndividualMessage(sender, recipient, m);
	}

}
