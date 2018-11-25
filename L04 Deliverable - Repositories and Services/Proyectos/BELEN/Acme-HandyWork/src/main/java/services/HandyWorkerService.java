
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import repositories.HandyWorkerRepository;
import domain.HandyWorker;

public class HandyWorkerService {

	@Autowired
	private HandyWorkerRepository	repositoryHandyWorker;


	public HandyWorker findByUserAccount(final int userAccount) {
		HandyWorker result;
		result = this.repositoryHandyWorker.findByUserAccount(userAccount);
		Assert.notNull(result);
		return result;
	}
}
