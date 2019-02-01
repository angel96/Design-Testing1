
package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
import domain.Actor;
import domain.Application;
import domain.Customer;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Mesage;

@Service
@Transactional
public class ApplicationService {

	@Autowired
	private ApplicationRepository	applicationRepository;

	@Autowired
	private HandyWorkerService		serviceHWorker;

	@Autowired
	private MesageService			serviceMesage;


	public void elapsedApplications(final int id) {
		Collection<Application> result;
		result = this.applicationRepository.elapsedApplications(id);
		for (final Application app : result)
			app.setStatus("elapsed");
	}

	public void elapsedApplicationsByFixUpTask(final int idCustomer, final int fixuptask) {
		Collection<Application> result;
		result = this.applicationRepository.getApplicationsByFixUpTaskElapsed(idCustomer, fixuptask);
		for (final Application app : result)
			app.setStatus("elapsed");
	}
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
		UserAccount user;
		user = LoginService.getPrincipal();
		if (Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER))
			Assert.isTrue(this.applicationRepository.getApplicationsByHandyWorker(user.getId()).contains(this.applicationRepository.findOne(id)));
		else if (Utiles.findAuthority(user.getAuthorities(), Authority.CUSTOMER))
			Assert.isTrue(this.applicationRepository.getApplicationsByCustomer(user.getId()).contains(this.applicationRepository.findOne(id)));
		Application a;
		a = this.applicationRepository.findOne(id);
		return a;
	}
	public Collection<Application> findAll() {
		return this.applicationRepository.findAll();
	}
	public Application createApplication(final FixUpTask f) {
		Application a;
		a = new Application();
		a.setFixUpTask(f);
		a.setMoment(new Date());
		a.setMomentElapsed(f.getEnd());
		a.setOfferedPrice(0.0);
		a.setStatus("pending");
		a.setCreditCard(Utiles.createFakeCreditCard());

		return a;
	}
	public Application save(final FixUpTask f, final Application a) {

		UserAccount user;
		user = LoginService.getPrincipal();
		final int idBefore = a.getId();

		Assert.isTrue(Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER) || Utiles.findAuthority(user.getAuthorities(), Authority.CUSTOMER));

		Application saved;
		saved = this.applicationRepository.save(a);

		if (Utiles.statusTEMP != a.getStatus() && idBefore != 0) {
			final Actor actor = this.applicationRepository.findByUserAccount(user.getId());
			final Mesage m = this.serviceMesage.createMessage(actor);

			m.setSubject("Application with status changed <br> Oferta con estado cambiado.");
			m.setBody("This message is intended to inform about the current status they are involved: " + a.getStatus() + "<br> Este mensaje es meramente informativo. Se utilizara para indicar el estado de la aplicaci�n: "
				+ Utiles.statusTranslation().get(a.getStatus()));
			m.setReceiver(new ArrayList<Actor>(Arrays.asList(this.applicationRepository.getHandyWorkerByApplication(a.getId()))));
			m.setTags(Arrays.asList("Application", a.getStatus(), new Date().toString()));
			this.serviceMesage.sendMessage(m);
		}

		if (saved.getStatus().equals("accepted"))
			for (final Application app : f.getApplication())
				if (app != saved && !app.getStatus().equals("elapsed"))
					app.setStatus("rejected");

		if (Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER)) {

			HandyWorker h;
			h = this.serviceHWorker.findByUserAccount(user.getId());

			Collection<Application> apps;
			apps = f.getApplication();

			if (!apps.contains(a)) {
				apps.add(saved);
				f.setApplication(apps);

				Collection<Application> handyApps;
				handyApps = new ArrayList<>();
				handyApps = h.getApplication();

				handyApps.add(saved);
				h.setApplication(handyApps);

			}

		}

		return saved;
	}
}