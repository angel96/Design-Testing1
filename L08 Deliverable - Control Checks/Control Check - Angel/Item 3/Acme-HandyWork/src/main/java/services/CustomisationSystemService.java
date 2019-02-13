
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CustomisationSystemRepository;
import security.Authority;
import security.LoginService;
import utilities.Utiles;
import domain.Actor;
import domain.Customer;
import domain.CustomisationSystem;
import domain.HandyWorker;

@Service
@Transactional
public class CustomisationSystemService {

	@Autowired
	private CustomisationSystemRepository	repositoryCustomisationSystem;


	public Collection<Actor> findAllSuspiciousActors() {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));
		return this.repositoryCustomisationSystem.findAllSuspiciousActors();
	}
	public Collection<Actor> findAllNoEnabledActors() {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));
		return this.repositoryCustomisationSystem.findAllNoEnabledActors();
	}

	public void banActor(final int id) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));
		Actor a;
		a = this.repositoryCustomisationSystem.findActor(id);
		a.setSuspicious(true);
		a.getAccount().setEnabled(false);
	}

	public void unBanActor(final int id) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));

		Actor a;
		a = this.repositoryCustomisationSystem.findActor(id);
		a.setSuspicious(false);
		a.getAccount().setEnabled(true);
	}

	public CustomisationSystem findUnique() {
		return this.repositoryCustomisationSystem.findAll().get(0);
	}

	public CustomisationSystem save(final CustomisationSystem custom) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));
		return this.repositoryCustomisationSystem.save(custom);
	}

	public Map<String, Double> dashboardRatioApplications() {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));
		Map<String, Double> result;
		result = new HashMap<String, Double>();
		final Double pendingApp = this.repositoryCustomisationSystem.findRatioOfPendingApplications();
		final Double acceptedApp = this.repositoryCustomisationSystem.findRationOfAcceptedAplications();
		final Double rejectedApp = this.repositoryCustomisationSystem.findRationOfRejectedApplications();
		final Double elapsedApp = this.repositoryCustomisationSystem.findRationOfPendingApplicationCannotChangeItsStatus();
		final Double fixUpComp = this.repositoryCustomisationSystem.ratioOfFixUpTasksWithComplaint();
		final Double aoletsPublished = this.repositoryCustomisationSystem.ratioPublishedAoletsPerFixUpTask();
		final Double aoletsUnPublished = this.repositoryCustomisationSystem.ratioUnPublishedAoletsPerFixUpTask();

		if (aoletsPublished == null || aoletsPublished == 0.0)
			result.put("RatioAoletsPublished", 0.0);
		else
			result.put("RatioAoletsPublished", aoletsPublished);

		if (aoletsUnPublished == null || aoletsUnPublished == 0.0)
			result.put("RatioAoletsUnPublished", 0.0);
		else
			result.put("RatioAoletsUnPublished", aoletsUnPublished);

		if (pendingApp == null || pendingApp == 0.0)
			result.put("RatioPendingApplications", 0.0);
		else
			result.put("RatioPendingApplications", pendingApp);

		if (acceptedApp == null || acceptedApp == 0.0)
			result.put("RatioAcceptedApplications", 0.0);
		else
			result.put("RatioAcceptedApplications", acceptedApp);
		if (rejectedApp == null || rejectedApp == 0.0)
			result.put("RatioRejectedApplications", 0.0);
		else
			result.put("RatioRejectedApplications", rejectedApp);
		if (elapsedApp == null || elapsedApp == 0.0)
			result.put("RatioApplicationsItsStatusCannotbechanged", 0.0);
		else
			result.put("RatioApplicationsItsStatusCannotbechanged", elapsedApp);
		if (fixUpComp == null || fixUpComp == 0.0)
			result.put("RatioFixUpTaskComplaint", 0.0);
		else
			result.put("RatioFixUpTaskComplaint", fixUpComp);
		return result;
	}
	public Map<String, double[]> dashboardStatistics() {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));
		Map<String, double[]> result;
		result = new HashMap<String, double[]>();

		result.put("AoletsFixUpTask", Utiles.convertToArrayDoubleFromString(this.repositoryCustomisationSystem.findAoletsPerFixUpTask()));
		result.put("FixUpTaskPerUser", Utiles.convertToArrayDoubleFromString(this.repositoryCustomisationSystem.findFixUpTaskPerUser()));
		result.put("ApplicationsPerFixUpTask", Utiles.convertToArrayDoubleFromString(this.repositoryCustomisationSystem.findApplicationPerFixUpTask()));
		result.put("MaximumPriceFixUpTask", Utiles.convertToArrayDoubleFromString(this.repositoryCustomisationSystem.findMaximumPricePerFixUpTask()));
		result.put("PriceOfferedInApplication", Utiles.convertToArrayDoubleFromString(this.repositoryCustomisationSystem.findPriceOfferedInApplication()));
		result.put("ComplaintsPerFixUpTask", Utiles.convertToArrayDoubleFromString(this.repositoryCustomisationSystem.findComplaintsPerFixUpTask()));
		result.put("NotesPerReport", Utiles.convertToArrayDoubleFromString(this.repositoryCustomisationSystem.findNotesPerReport()));

		return result;
	}
	public Map<String, Collection<? extends Actor>> dashboardActors() {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));

		Map<String, Collection<? extends Actor>> result;

		result = new HashMap<String, Collection<? extends Actor>>();

		Collection<Customer> customers = this.repositoryCustomisationSystem.topTreeCustomerOrderByComplaints();
		Collection<HandyWorker> handyWorkers = this.repositoryCustomisationSystem.topTreeHandyWorkerOrderByComplaints();

		if (!customers.isEmpty())
			customers = new ArrayList<Customer>(this.repositoryCustomisationSystem.topTreeCustomerOrderByComplaints()).subList(0, 3);
		if (!handyWorkers.isEmpty())
			handyWorkers = new ArrayList<HandyWorker>(this.repositoryCustomisationSystem.topTreeHandyWorkerOrderByComplaints()).subList(0, 3);

		result.put("customersWith10PerCentMoreFixUpPublishedThanAvgOrderApps", this.repositoryCustomisationSystem.findCustomers10PerCentMoreFixUpThanAvgOrderApplication());
		result.put("HandyWorkersWith10PerCentMoreAppsPublishedThanAvgOrderApps", this.repositoryCustomisationSystem.findHandyWorkers10PerCentMoreAppsThanAvgAcepptedOrderApplication());
		result.put("topThreeCustomerOrderByComplaints", customers.isEmpty() ? new ArrayList<Customer>() : customers);
		result.put("topThreeHandyWorkerOrderByComplaints", handyWorkers.isEmpty() ? new ArrayList<HandyWorker>() : handyWorkers);

		return result;
	}
}
