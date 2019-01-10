
package services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FixUpTaskRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.DatabaseConfig;
import utilities.Utiles;
import domain.Application;
import domain.Category;
import domain.Complaint;
import domain.Customer;
import domain.Finder;
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


	public Collection<FixUpTask> getFixUpTasksByHandyWorker(final int id) {
		return this.fixUpTaskRepository.getFixUpTasksByHandyWorker(id);
	}

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

	public FixUpTask createFixUpTask() {

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
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.notNull(user);

		Customer c;
		c = this.serviceCustomer.findByUserAccount(user.getId());

		Collection<FixUpTask> fixUpTaskCustomer;
		fixUpTaskCustomer = c.getFixUpTask();

		FixUpTask saved;
		saved = this.fixUpTaskRepository.save(f);
		fixUpTaskCustomer.add(saved);
		c.setFixUpTask(fixUpTaskCustomer);

		return saved;
	}
	public void delete(final int id) {

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

	public Collection<FixUpTask> findAllByFinder(final Finder finder) {

		StringBuilder builder;
		builder = new StringBuilder();
		builder.append("select f from FixUpTask f where ");

		final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(DatabaseConfig.PersistenceUnit);
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
