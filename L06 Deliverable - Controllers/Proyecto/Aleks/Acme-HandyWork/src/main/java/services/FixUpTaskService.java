
package services;

import java.util.Collection;
import java.util.Date;
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
import domain.Category;
import domain.Customer;
import domain.FixUpTask;
import domain.Phase;
import domain.Warranty;

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
	public FixUpTask findOne(final int id) {
		return this.fixUpTaskRepository.findOne(id);
	}
	public Collection<FixUpTask> findAllByUser(final int userAccountId) {
		Collection<FixUpTask> res;
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.CUSTOMER));
		res = this.fixUpTaskRepository.findAllByUser(userAccountId);
		Assert.notNull(res);
		return res;
	}

	public FixUpTask save(final FixUpTask f) {
		Assert.notNull(f);
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.notNull(user);
		//assert pa comprobar que esta fixup pertenece al user logueado, aparte de poner el boton de edit en la vista
		Assert.isTrue(this.fixUpTaskRepository.findAllByUser(user.getId()).contains(f));
		//		Customer c;
		//		c = this.serviceCustomer.findByUserAccount(user.getId());

		//		Collection<FixUpTask> fixUpTaskCustomer;
		//		fixUpTaskCustomer = c.getFixUpTask();

		FixUpTask saved;
		saved = this.fixUpTaskRepository.save(f);

		//		fixUpTaskCustomer.add(saved);

		//		c.setFixUpTask(fixUpTaskCustomer);

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
	/*
	 * public FixUpTask update(final FixUpTask newer) {
	 * FixUpTask saved;
	 * 
	 * UserAccount userLogged;
	 * userLogged = LoginService.getPrincipal();
	 * 
	 * if (userLogged.equals(this.fixUpTaskRepository.findCustomerByFixUpTask(newer.getId()).getAccount()))
	 * saved = this.fixUpTaskRepository.save(newer);
	 * else
	 * throw new IllegalAccessError("A task which doesn´t belong to the customer logged can not be modified");
	 * 
	 * Assert.notNull(saved);
	 * return saved;
	 * }
	 */public void delete(final int id) {

		UserAccount user;
		user = LoginService.getPrincipal();

		Customer c;
		c = this.serviceCustomer.findByUserAccount(user.getId());

		if (c.equals(this.fixUpTaskRepository.findCustomerByFixUpTask(id))) {
			Collection<FixUpTask> collect;
			collect = c.getFixUpTask();
			collect.remove(this.fixUpTaskRepository.findOne(id));
			c.setFixUpTask(collect);
			this.fixUpTaskRepository.delete(id);
		} else
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

	public Collection<FixUpTask> findAllByFinder(final String query, final Date start, final Date end, final Warranty warranty, final Category category, final double amount1, final double amount2) {
		return this.fixUpTaskRepository.findAllSearchByFinder("%" + query + "%", start, end, warranty, category, amount1, amount2);

	}
}
