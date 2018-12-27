
package services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FixUpTaskRepository;
import repositories.PhasesRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
import domain.Application;
import domain.Customer;
import domain.FixUpTask;
import domain.Phase;

@Service
@Transactional
public class FixUpTaskService {

	@Autowired
	private FixUpTaskRepository	fixUpTaskRepository;

	@Autowired
	private CustomerService		serviceCustomer;

	@Autowired
	private PhasesRepository	repositoryPhase;


	public Collection<FixUpTask> findAll() {
		return this.fixUpTaskRepository.findAll();
	}

	public Collection<FixUpTask> getFixUpTasksByCustomer(final Customer c) {
		return this.fixUpTaskRepository.getFixUpTasksByCustomer(c.getId());
	}
	public FixUpTask findOne(final int id) {
		return this.fixUpTaskRepository.findOne(id);
	}

	public FixUpTask save(final FixUpTask f) {

		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.notNull(user);

		Customer c;
		c = this.serviceCustomer.findByUserAccount(user.getId());

		Collection<FixUpTask> fixUpTaskCustomer;
		fixUpTaskCustomer = this.getFixUpTasksByCustomer(c);

		FixUpTask saved;
		Assert.notNull(f);
		saved = this.fixUpTaskRepository.save(f);

		fixUpTaskCustomer.add(saved);

		c.setFixUpTask(fixUpTaskCustomer);

		this.serviceCustomer.save(c);

		return saved;
	}
	public FixUpTask updateApplications(final FixUpTask newer) {
		FixUpTask saved;

		UserAccount user;
		user = LoginService.getPrincipal();

		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER));

		saved = this.fixUpTaskRepository.save(newer);

		Assert.notNull(saved);
		return saved;
	}
	public FixUpTask update(final FixUpTask newer) {
		FixUpTask saved;

		UserAccount userLogged;
		userLogged = LoginService.getPrincipal();

		if (userLogged.equals(this.fixUpTaskRepository.findCustomerByFixUpTask(newer.getId()).getAccount()))
			saved = this.fixUpTaskRepository.save(newer);
		else
			throw new IllegalAccessError("A task which doesn´t belong to the customer logged can not be modified");

		Assert.notNull(saved);
		return saved;
	}
	public void delete(final int id) {

		UserAccount user;
		user = LoginService.getPrincipal();

		Customer c;
		c = this.serviceCustomer.findByUserAccount(user.getId());

		if (c.equals(this.fixUpTaskRepository.findCustomerByFixUpTask(id)))
			this.fixUpTaskRepository.delete(id);
		else
			throw new IllegalAccessError("A task which doesn´t belong to the customer logged can not be deleted");

	}
	public boolean createWorkPlan(final Collection<Phase> planPhases, final Application a) {
		System.out.println(a);
		Assert.isTrue(a.getStatus().equals("accepted"));
		UserAccount userLogged;
		userLogged = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(userLogged.getAuthorities(), Authority.HANDY_WORKER));

		final List<Phase> phases = this.repositoryPhase.save(planPhases);
		FixUpTask fixup;
		fixup = a.getFixUpTask();
		fixup.setPhases(phases);

		FixUpTask update;
		update = this.fixUpTaskRepository.save(fixup);
		System.out.println(update);
		return update != null;
	}
}
