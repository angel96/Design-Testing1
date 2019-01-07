
package services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
import domain.FixUpTask;

@Service
@Transactional
public class ApplicationService {

	@Autowired
	private ApplicationRepository	applicationRepository;

	@Autowired
	private HandyWorkerService		serviceHWorker;

	@Autowired
	private FixUpTaskService		fixUpTaskService;


	public Collection<Application> getApplicationsByCustomer(final Customer c) {
		return this.applicationRepository.getApplicationsByCustomer(c.getId());
	}

	public Customer getCustomerByApplication(final int id) {
		return this.applicationRepository.getCustomerByApplication(id);
	}

	public Application getApplicationAcceptedByPhase(final int id) {
		return this.applicationRepository.getApplicationAceptedByPhase(id);
	}

	public Collection<Application> getApplicationsByFixUp(final int customerAccountId, final int fixUpId) {
		return this.applicationRepository.getApplicationsByFixUpTask(customerAccountId, fixUpId);
	}

	public Application findOne(final int id) {

		return this.applicationRepository.findOne(id);
	}

	public Collection<Application> findAll() {
		return this.applicationRepository.findAll();
	}

	public Application save(final FixUpTask f, final Application a) {

		UserAccount user;
		user = LoginService.getPrincipal();

		Assert.notNull(user);
		Assert.isTrue(user.equals(this.serviceHWorker.findByUserAccount(user.getId()).getAccount()));
		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER));
		Assert.notNull(a);
		Assert.notNull(f);

		Application saved;

		saved = this.applicationRepository.save(a);

		Collection<Application> apps;
		apps = f.getApplication();

		apps.add(saved);
		f.setApplication(apps);

		this.fixUpTaskService.updateApplications(f);

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
		Application taken;
		UserAccount userLogged;
		userLogged = LoginService.getPrincipal();
		Assert.isTrue(Utiles.findAuthority(userLogged.getAuthorities(), Authority.CUSTOMER));
		Assert.isTrue(userLogged.equals(this.applicationRepository.getCustomerByApplication(a.getId()).getAccount()));
		if (comment.equals("") == false || !comment.equals(null) == false) {
			a.getComments().add(comment);
			taken = this.applicationRepository.save(a);
			System.out.println(taken.getComments());
		}
	}

	public Map<String, Double> dashboardApplications() {
		Map<String, Double> result;
		result = new HashMap<String, Double>();

		result.put("ApplicationsPerFixUpTaskAVG", this.applicationRepository.findAVGOfApplicationPerFixUpTask());
		result.put("ApplicationsPerFixUpTaskMIN", this.applicationRepository.findMINOfApplicationPerFixUpTask());
		result.put("ApplicationsPerFixUpTaskMAX", this.applicationRepository.findMAXOfApplicationPerFixUpTask());
		result.put("ApplicationsPerFixUpTaskSTDEV", this.applicationRepository.findATDDEVOfApplicationPerFixUpTask());

		result.put("RatioPendingApplications", this.applicationRepository.findRatioOfPendingApplications());
		result.put("RatioAcceptedApplications", this.applicationRepository.findRationOfAcceptedAplications());
		result.put("RatioRejectedApplications", this.applicationRepository.findRationOfRejectedApplications());
		result.put("RatioApplicationsItsStatusCannotbechanged", this.applicationRepository.findRationOfPendingApplicationCannotChangeItsStatus());

		return result;
	}
	public Map<String, Double> dashboardPrices() {
		Map<String, Double> result;
		result = new HashMap<String, Double>();

		result.put("PriceFixUpTaskAVG", this.applicationRepository.findAVGOfPriceOfferedInApplicatio());
		result.put("PriceFixUpTaskMIN", this.applicationRepository.findMINOfPriceOfferedInApplicatio());
		result.put("PriceFixUpTaskMAX", this.applicationRepository.findMAXOfPriceOfferedInApplicatio());
		result.put("PriceFixUpTaskSTDEV", this.applicationRepository.findATDDEVOfPriceOfferedInApplicatio());

		return result;
	}
}
