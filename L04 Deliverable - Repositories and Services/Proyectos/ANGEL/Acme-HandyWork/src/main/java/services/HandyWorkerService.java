
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.HandyWorkerRepository;
import domain.Category;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Warranty;

@Service
@Transactional
public class HandyWorkerService {

	@Autowired
	private HandyWorkerRepository	repositoryHandyWorker;


	public HandyWorker findByUserAccount(final int userAccount) {
		HandyWorker result;
		result = this.repositoryHandyWorker.findByUserAccount(userAccount);
		Assert.notNull(result);
		return result;
	}
	public HandyWorker update(final HandyWorker h) {
		Assert.notNull(h);
		return this.repositoryHandyWorker.save(h);
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
}
