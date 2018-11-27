
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import security.LoginService;
import security.UserAccount;
import domain.Application;
import domain.CreditCard;
import domain.Customer;
import domain.FixUpTask;

@Service
@Transactional
public class ApplicationService {

	@Autowired
	private ApplicationRepository	applicationRepository;

	@Autowired
	private CustomerService			serviceCustomer;


	public Collection<Application> getApplicationsByCustomer(final Customer c) {
		return this.applicationRepository.getApplicationsByCustomer(c.getId());
	}

	public Customer getCustomerByApplication(final int id) {
		return this.applicationRepository.getCustomerByApplication(id);
	}

	public Application getApplicationAcceptedByPhase(final int id) {
		return this.applicationRepository.getApplicationAceptedByPhase(id);
	}

	public Application findOne(final int id) {
		final Application a = this.applicationRepository.findOne(id);
		return a;
	}

	public Collection<Application> findAll() {
		return this.applicationRepository.findAll();
	}

	public Application save(final Application a) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.notNull(user);

		Customer c;
		c = this.serviceCustomer.findByUserAccount(user.getId());

		Collection<Application> applicationCustomer;
		applicationCustomer = this.getApplicationsByCustomer(c);

		Application saved;
		Assert.notNull(a);
		saved = this.applicationRepository.save(a);

		applicationCustomer.add(saved);

		FixUpTask v;
		v = a.getFixUpTask();
		v.setApplication(applicationCustomer);

		this.serviceCustomer.update(c);

		return saved;
	}

	public Application update(final CreditCard credit, final Application newer) {
		Application saved;

		System.out.println("Newer Service: " + newer);
		UserAccount userLogged;
		userLogged = LoginService.getPrincipal();
		if (userLogged.equals(this.applicationRepository.getCustomerByApplication(newer.getId()).getAccount())) {
			if (newer.getStatus().equals("accepted")) {
				newer.setCreditCard(credit);
				System.out.println("Entro en creditCard");
			}
			saved = this.applicationRepository.save(newer);
			System.out.println("Saved Service: " + saved);
		} else
			throw new IllegalAccessError("An application which doesn´t belong to the customer logged can not be modified");
		Assert.notNull(saved);
		return saved;
	}

	public void addComment(final String comment, final Application a) {
		String comments;
		Application taken;
		taken = this.findOne(a.getId());
		comments = taken.getComments();
		if (comment.equals("") == false || !comment.equals(null) == false) {
			taken.setComments(comments);
			this.updateComment(a.getId(), taken);
		}
	}

	public Application updateComment(final int id, final Application a) {
		Application taken, saved;
		taken = this.findOne(id);
		Assert.notNull(a);
		taken.setComments(a.getComments());
		saved = this.applicationRepository.save(taken);
		Assert.notNull(taken);
		return saved;
	}

}
