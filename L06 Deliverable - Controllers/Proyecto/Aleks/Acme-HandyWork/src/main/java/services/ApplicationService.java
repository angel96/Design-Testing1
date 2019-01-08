
package services;

import java.util.ArrayList;
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
import domain.Customer;
import domain.FixUpTask;
import domain.HandyWorker;

@Service
@Transactional
public class ApplicationService {

	@Autowired
	private ApplicationRepository	applicationRepository;

	@Autowired
	private HandyWorkerService		serviceHWorker;


	//	@Autowired
	//	private FixUpTaskService		fixUpTaskService;

	public Collection<Application> getApplicationsByHandyWorker(final int accountId) {
		return this.applicationRepository.getApplicationsByHandyWorker(accountId);
	}

	public Customer getCustomerByApplication(final int id) {
		return this.applicationRepository.findCustomerByApplication(id);
	}

	public Application getApplicationAcceptedByPhase(final int id) {
		return this.applicationRepository.getApplicationAceptedByPhase(id);
	}

	public Collection<Application> getApplicationsByFixUp(final int customerAccountId, final int fixUpId) {
		return this.applicationRepository.getApplicationsByFixUpTask(customerAccountId, fixUpId);
	}

	public Application findOne(final int id) {
		Application a;
		a = this.applicationRepository.findOne(id);
		return a;
	}

	public Collection<Application> findAll() {
		return this.applicationRepository.findAll();
	}

	public Application save(final FixUpTask f, final Application a) {

		UserAccount user;
		user = LoginService.getPrincipal();

		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER) || Utiles.findAuthority(user.getAuthorities(), Authority.CUSTOMER));

		Application saved;
		saved = this.applicationRepository.save(a);

		if (Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER)) {
			HandyWorker h;
			h = this.serviceHWorker.findByUserAccount(user.getId());

			Collection<Application> apps;
			apps = f.getApplication();

			apps.add(saved);
			f.setApplication(apps);

			Collection<Application> handyApps;
			handyApps = new ArrayList<>();
			handyApps = h.getApplication();

			handyApps.add(saved);
			h.setApplication(handyApps);

		}

		return saved;
	}

}
