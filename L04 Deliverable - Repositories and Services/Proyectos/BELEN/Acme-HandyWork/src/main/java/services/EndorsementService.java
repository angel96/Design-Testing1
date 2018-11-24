
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EndorsementRepository;
import security.UserAccount;
import domain.Endorsable;
import domain.Endorsement;

@Service
@Transactional
public class EndorsementService {

	@Autowired
	private EndorsementRepository	repositoryEndorsement;


	public Collection<Endorsement> findAll() {
		return this.repositoryEndorsement.findAll();
	}
	public Endorsement findOne(final int id) {
		return this.repositoryEndorsement.findOne(id);
	}

	//Authenticated as Customer or HandyWorker
	public Endorsement create(final Endorsable send, final Endorsable receive) {
		UserAccount user;
		user = send.getAccount();
		Assert.notNull(user);
		Endorsement e;
		e = new Endorsement();
		e.setMoment(new Date());
		e.setUserReceived(receive);
		e.setUserSended(send);
		e.setComments(new ArrayList<String>());
		return e;
	}
	//Authenticated as Customer or HandyWorker
	public Endorsement add(final Endorsement e) {
		Assert.notNull(e);
		return this.repositoryEndorsement.save(e);
	}
	public Endorsement update(final int id, final Endorsement e) {
		Endorsement taken, saved;
		taken = this.findOne(id);
		Assert.notNull(e);
		taken.setMoment(e.getMoment());
		taken.setUserSended(e.getUserSended());
		taken.setUserReceived(e.getUserReceived());
		taken.setComments(e.getComments());
		saved = this.repositoryEndorsement.save(taken);
		Assert.notNull(taken);
		return saved;
	}

	public void addComment(final String comment, final Endorsement e) {
		Collection<String> comments;
		Endorsement taken;
		taken = this.findOne(e.getId());
		comments = taken.getComments();
		if (comment != "" || comment != null)
			comments.add(comment);
		taken.setComments(comments);
		this.update(e.getId(), taken);
	}
	public void removeComment(final String comment, final Endorsement e) {
		Collection<String> comments;
		Endorsement taken;
		taken = this.findOne(e.getId());
		comments = taken.getComments();
		if (comments.contains(comment))
			comments.remove(comment);
		taken.setComments(comments);
		this.update(e.getId(), taken);
	}
	public void delete(final int id) {
		Assert.notNull(this.findOne(id));
		this.repositoryEndorsement.delete(id);
	}
}
