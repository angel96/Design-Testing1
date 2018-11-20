
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import domain.HandyWorker;

public class HandyWorkerService {

	@Autowired
	private HandyWorkerService	serviceHandyWorker;


	public HandyWorker findByUserAccount(final int userAccount) {
		HandyWorker result;
		result = this.serviceHandyWorker.findByUserAccount(userAccount);
		Assert.notNull(result);
		return result;
	}
}
