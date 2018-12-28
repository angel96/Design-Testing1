
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
import domain.Actor;
import domain.Administrator;
import domain.Box;
import domain.Customer;
import domain.HandyWorker;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository	adminRepository;

	@Autowired
	private BoxService				boxService;


	public Administrator findOne(final int userAccount) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));
		Administrator saved;
		saved = this.adminRepository.findAdministratorByUserAccountId(LoginService.getPrincipal().getId());
		Assert.notNull(saved);
		return saved;
	}
	public Administrator findAdmin(final int administrator) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));
		Administrator saved;
		saved = this.adminRepository.findOne(administrator);
		Assert.notNull(saved);
		return saved;
	}

	public Administrator save(final Administrator admin) {

		if (admin.getId() != 0) {
			final UserAccount account = this.findAdmin(admin.getId()).getAccount();
			account.setUsername(admin.getAccount().getUsername());
			account.setPassword(Utiles.hashPassword(admin.getAccount().getPassword()));
			account.setAuthorities(admin.getAccount().getAuthorities());
			admin.setAccount(account);
		} else {
			admin.getAccount().setPassword(Utiles.hashPassword(admin.getAccount().getPassword()));
			final Collection<Box> boxes = this.boxService.save(Utiles.initBoxes());
			admin.setBoxes(boxes);
		}

		Administrator modify;

		modify = this.adminRepository.saveAndFlush(admin);

		return modify;
	}

	//Admin dashboard

	public Map<String, Collection<? extends Actor>> dashboardActors() {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));

		Map<String, Collection<? extends Actor>> result;

		result = new HashMap<String, Collection<? extends Actor>>();

		result.put("customersWith10PerCentMoreFixUpPublishedThanAvgOrderApps", this.adminRepository.findCustomers10PerCentMoreFixUpThanAvgOrderApplication());
		result.put("HandyWorkersWith10PerCentMoreAppsPublishedThanAvgOrderApps", this.adminRepository.findHandyWorkers10PerCentMoreAppsThanAvgAcepptedOrderApplication());
		result.put("topThreeCustomerOrderByComplaints", new ArrayList<Customer>(this.adminRepository.topTreeCustomerOrderByComplaints()).subList(0, 3));
		result.put("topThreeHandyWorkerOrderByComplaints", new ArrayList<HandyWorker>(this.adminRepository.topTreeHandyWorkerOrderByComplaints()).subList(0, 3));

		return result;
	}

	public boolean unbanActor(final Actor a) {
		a.setBan(false);
		return a.isBan();
	}
	public boolean banActor(final Actor a) {
		a.setBan(true);
		return a.isBan();
	}

}
