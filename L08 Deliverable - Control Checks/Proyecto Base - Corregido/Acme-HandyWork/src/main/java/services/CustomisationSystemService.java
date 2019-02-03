
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

		result.put("RatioPendingApplications", this.repositoryCustomisationSystem.findRatioOfPendingApplications());
		System.out.println("\n RATIO ============ \n" + this.repositoryCustomisationSystem.findRatioOfPendingApplications());
		result.put("RatioAcceptedApplications", this.repositoryCustomisationSystem.findRationOfAcceptedAplications());
		result.put("RatioRejectedApplications", this.repositoryCustomisationSystem.findRationOfRejectedApplications());
		result.put("RatioApplicationsItsStatusCannotbechanged", this.repositoryCustomisationSystem.findRationOfPendingApplicationCannotChangeItsStatus());
		result.put("RatioFixUpTaskComplaint", this.repositoryCustomisationSystem.ratioOfFixUpTasksWithComplaint());

		return result;
	}
	public Map<String, double[]> dashboardStatistics() {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));
		Map<String, double[]> result;
		result = new HashMap<String, double[]>();

		//result.put("CHFixUpTaskPerUser", Utiles.convertToArrayDoubleFromString(this.repositoryCustomisationSystem.findCHFixUpTaskPerUser()));
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
