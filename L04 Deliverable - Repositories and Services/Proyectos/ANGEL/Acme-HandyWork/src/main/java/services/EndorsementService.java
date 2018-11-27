
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EndorsementRepository;
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

	//Authenticated as Customer or HandyWorker. It´s taken from Endorsable.
	public Endorsement add(final Endorsement e) {

		Assert.notNull(e);
		return this.repositoryEndorsement.save(e);
	}
	public Endorsement update(final Endorsement e) {
		Endorsement saved;
		saved = this.repositoryEndorsement.save(e);
		Assert.notNull(saved);
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
		this.update(taken);
	}
	public void removeComment(final String comment, final Endorsement e) {
		Collection<String> comments;
		Endorsement taken;
		taken = this.findOne(e.getId());
		comments = taken.getComments();
		if (comments.contains(comment))
			comments.remove(comment);
		taken.setComments(comments);
		this.update(taken);
	}
	public void delete(final int id) {
		Assert.notNull(this.findOne(id));
		this.repositoryEndorsement.delete(id);
	}
}
