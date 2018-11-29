
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
import domain.Application;
import domain.CreditCard;
import domain.Customer;

@Service
@Transactional
public class ApplicationService {

	@Autowired
	private ApplicationRepository	applicationRepository;

	@Autowired
	private HandyWorkerService		serviceHWorker;


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

		return this.applicationRepository.findOne(id);
	}

	public Collection<Application> findAll() {
		return this.applicationRepository.findAll();
	}

	public Application save(final Application a) {

		UserAccount user;
		user = LoginService.getPrincipal();

		Assert.notNull(user);
		Assert.isTrue(user.equals(this.serviceHWorker.findByUserAccount(user.getId()).getAccount()));
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER));
		Assert.notNull(a);

		Application saved;

		saved = this.applicationRepository.save(a);

		return saved;
	}
	public Application update(final Application newer) {
		Application saved;
		UserAccount userLogged;
		userLogged = LoginService.getPrincipal();

		Assert.isTrue(Utiles.findAuthority(userLogged.getAuthorities(), Authority.HANDY_WORKER));
		if (userLogged.equals(this.serviceHWorker.findByUserAccount(newer.getId()).getAccount()))
			saved = this.applicationRepository.save(newer);
		else
			throw new IllegalAccessError("An application which doesn´t belong to the customer logged can not be modified");
		Assert.notNull(saved);
		return saved;
	}
	public Application updateStatus(final CreditCard credit, final Application newer) {
		Application saved;
		UserAccount userLogged;
		userLogged = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(userLogged.getAuthorities(), Authority.CUSTOMER));
		if (userLogged.equals(this.applicationRepository.getCustomerByApplication(newer.getId()).getAccount())) {
			if (newer.getStatus().equals("accepted"))
				newer.setCreditCard(credit);
			saved = this.applicationRepository.save(newer);
			System.out.println("Saved: " + saved);
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
		UserAccount userLogged;
		userLogged = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(userLogged.getAuthorities(), Authority.CUSTOMER));
		Assert.isTrue(userLogged.equals(this.applicationRepository.getCustomerByApplication(a.getId()).getAccount()));
		if (comment.equals("") == false || !comment.equals(null) == false) {
			taken.setComments(comments);
			this.updateComment(a.getId(), taken);
		}
	}

	public Application updateComment(final int id, final Application a) {

		UserAccount userLogged;
		userLogged = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(userLogged.getAuthorities(), Authority.CUSTOMER));
		Assert.isTrue(userLogged.equals(this.applicationRepository.getCustomerByApplication(a.getId()).getAccount()));

		Application taken, saved;
		taken = this.findOne(id);
		Assert.notNull(a);
		taken.setComments(a.getComments());
		saved = this.applicationRepository.save(taken);
		Assert.notNull(taken);
		return saved;
	}
}
