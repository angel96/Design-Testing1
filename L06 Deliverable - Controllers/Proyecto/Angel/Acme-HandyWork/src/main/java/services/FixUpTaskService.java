
package services;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
import domain.Finder;
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
		fixUpTaskCustomer = c.getFixUpTask();

		FixUpTask saved;
		Assert.notNull(f);
		saved = this.fixUpTaskRepository.save(f);

		fixUpTaskCustomer.add(saved);

		c.setFixUpTask(fixUpTaskCustomer);

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

	public Collection<FixUpTask> findAllByFinder(final Finder finder) {

		StringBuilder builder;
		builder = new StringBuilder();
		builder.append("select f from FixUpTask f where ");

		final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("Acme-HandyWork");
		final EntityManager entityManager = emFactory.createEntityManager();

		if (finder.getSingleKey() != null && !finder.getSingleKey().equals(" ") && !finder.getSingleKey().equals(""))
			builder.append("f.ticker LIKE CONCAT('%'," + finder.getSingleKey() + ",'%') OR f.description LIKE CONCAT('%'," + finder.getSingleKey() + ",'%') OR f.address LIKE CONCAT('%'," + finder.getSingleKey() + ",'%') AND");

		if (finder.getCategory() != null && finder.getCategory().getId() != 0)
			builder.append(" f.category.id = " + finder.getCategory().getId() + " AND");

		if (finder.getWarranty() != null && finder.getWarranty().getId() != 0)
			builder.append(" f.warranty.id = " + finder.getWarranty().getId() + " AND");

		final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

		if (finder.getStartDate() != null)
			builder.append(" f.start >= '" + format.format(finder.getStartDate()) + "' AND");
		if (finder.getEndDate() != null)
			builder.append(" f.end <= '" + format.format(finder.getEndDate()) + "' AND");

		if (finder.getPrice1() != null || finder.getPrice1() > 0.0)
			builder.append(" f.maximumPrice >= " + finder.getPrice1() + " AND");

		if (finder.getPrice2() != null || finder.getPrice2() > 0.0)
			builder.append(" f.maximumPrice <= " + finder.getPrice2());
		System.out.println("===========\nQUERY:" + builder.toString() + " ============ \n\n");

		final TypedQuery<FixUpTask> query = entityManager.createQuery(builder.toString(), FixUpTask.class);

		return query.getResultList();
	}
}
