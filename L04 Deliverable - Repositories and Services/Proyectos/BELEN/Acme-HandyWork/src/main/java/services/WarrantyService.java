
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.WarrantyRepository;
import domain.Warranty;

@Service
@Transactional
public class WarrantyService {

	@Autowired
	private WarrantyRepository	repositoryWarranty;


	public Collection<Warranty> findAll() {
		return this.repositoryWarranty.findAll();
	}

	public Warranty findOne(final int id) {
		return this.repositoryWarranty.findOne(id);
	}

	public Warranty create() {

		Warranty w;
		w = new Warranty();

		w.setTitle("");
		w.setLaws("");
		w.setTerms("");
		w.setDraftMode(true);

		return w;
	}
	public Warranty addWarranty(final Warranty w) {
		Warranty result;
		Assert.notNull(w);
		result = this.repositoryWarranty.save(w);
		Assert.notNull(result);
		return result;
	}

	//Update or Delete can only be done when Warranty is on draft mode.

	public Warranty updateWarranty(final int id, final Warranty newer) {

		Warranty w;
		w = this.findOne(id);
		if (w.isDraftMode()) {
			w.setTitle(newer.getTitle());
			w.setTerms(newer.getTerms());
			w.setLaws(newer.getLaws());
		} else
			throw new IllegalAccessError();

		return this.repositoryWarranty.save(w);
	}

	public void deleteWarranty(final int id) {
		Warranty w;
		w = this.findOne(id);

		if (w.isDraftMode())
			this.repositoryWarranty.delete(w);
		else
			throw new IllegalAccessError();
	}
}
