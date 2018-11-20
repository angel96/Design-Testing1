
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FixUpTaskRepository;
import security.UserAccount;
import utilities.Utiles;
import domain.Application;
import domain.Category;
import domain.Complaint;
import domain.Customer;
import domain.FixUpTask;
import domain.Phase;
import domain.Warranty;

@Service
@Transactional
public class FixUpTaskService {

	@Autowired
	private FixUpTaskRepository	fixUpTaskRepository;
	private CustomerService		serviceCustomer;


	public Collection<FixUpTask> findAll() {
		return this.fixUpTaskRepository.findAll();
	}

	public Collection<FixUpTask> getFixUpTasksByCustomer(final Customer c) {
		return this.fixUpTaskRepository.getFixUpTasksByCustomer(c.getId());
	}
	public FixUpTask findOne(final int id) {
		return this.fixUpTaskRepository.findOne(id);
	}
	public FixUpTask create(final Customer c) {
		UserAccount user;
		user = this.serviceCustomer.findByUserAccount(c.getId()).getAccount();
		Assert.notNull(user);
		FixUpTask fut;
		fut = new FixUpTask();
		fut.setAddress("");
		fut.setApplication(new ArrayList<Application>());
		fut.setCategory(new Category());
		fut.setComplaint(new ArrayList<Complaint>());
		fut.setDescription("");
		fut.setEnd(new Date());
		fut.setMaximumPrice(0.0);
		fut.setMoment(new Date());
		fut.setPhases(new ArrayList<Phase>());
		fut.setWarranty(new Warranty());
		fut.setTicker(Utiles.generateTicker());
		return fut;
	}
	public FixUpTask save(final FixUpTask f) {
		Assert.notNull(f);
		return this.fixUpTaskRepository.save(f);
	}
	public FixUpTask update(final int id, final FixUpTask newer) {
		FixUpTask old;
		final FixUpTask saved;
		old = this.findOne(id);
		old.setAddress(newer.getAddress());
		old.setApplication(newer.getApplication());
		old.setCategory(newer.getCategory());
		old.setComplaint(newer.getComplaint());
		old.setDescription(newer.getDescription());
		old.setEnd(newer.getEnd());
		old.setMaximumPrice(newer.getMaximumPrice());
		old.setMoment(newer.getMoment());
		old.setTicker(newer.getTicker());
		old.setWarranty(newer.getWarranty());
		old.setPhases(newer.getPhases());
		saved = this.fixUpTaskRepository.save(old);
		Assert.notNull(saved);
		return saved;
	}
	public void delete(final int id) {
		Assert.notNull(this.findOne(id));
		this.fixUpTaskRepository.delete(id);
	}
}
