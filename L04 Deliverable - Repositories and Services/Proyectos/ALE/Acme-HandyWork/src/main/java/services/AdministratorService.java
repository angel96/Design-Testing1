
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
import domain.Administrator;
import domain.Customer;
import domain.HandyWorker;
import domain.Message;
import domain.Profile;
import domain.Referee;
import domain.SpamWord;
import domain.Sponsor;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository	adminRepository;
	@Autowired
	private SpamWordService			spamService;
	@Autowired
	private SponsorService			sponsorService;
	@Autowired
	private CustomerService			customerService;
	@Autowired
	private HandyWorkerService		hwService;
	@Autowired
	private RefereeService			refereeService;


	public Administrator createAnotherAdministrator() {
		int adminId;
		adminId = this.adminRepository.findAdministratorById(LoginService.getPrincipal().getId());
		Assert.notNull(adminId);
		Administrator admin;
		UserAccount account;
		Authority auth;
		Collection<Authority> authorities;

		admin = new Administrator();
		account = new UserAccount();
		auth = new Authority();
		authorities = new ArrayList<>();

		auth.setAuthority(Authority.ADMIN);
		authorities.add(auth);
		account.addAuthority(auth);
		admin.setAccount(account);
		admin.setMessage(new ArrayList<Message>());
		admin.setProfiles(new ArrayList<Profile>());

		return admin;
	}

	public Message broadcastMessage(final Message m) { //TODO
		return null;
	}

	public Collection<Customer> findCustomersWith10PerCentMoreFixUpPublishedThanAvgOrderApps() {
		final int adminId = this.adminRepository.findAdministratorById(LoginService.getPrincipal().getId());
		Assert.notNull(adminId);
		return this.adminRepository.findCustomers10PerCentMoreFixUpThanAvgOrderApplication();
	}

	public Collection<HandyWorker> findHandyWorkersWith10PerCentMoreAppsPublishedThanAvgOrderApps() {
		final int adminId = this.adminRepository.findAdministratorById(LoginService.getPrincipal().getId());
		Assert.notNull(adminId);
		return this.adminRepository.findHandyWorkers10PerCentMoreAppsThanAvgAcepptedOrderApplication();
	}

	public Collection<Administrator> findAllAdminWithSpamWords() {
		final int adminId = this.adminRepository.findAdministratorById(LoginService.getPrincipal().getId());
		Assert.notNull(adminId);
		Collection<SpamWord> spam;
		spam = this.spamService.findAll();
		Collection<Administrator> admins;
		admins = this.adminRepository.findAll();
		//DONDE MIRO XD	TODO

		return null;
	}

	//Ban Actors
	public void unbanAdmin(final Administrator admin) {
		Assert.isTrue(admin.isBan() != true);
		final int adminId = this.adminRepository.findAdministratorById(LoginService.getPrincipal().getId());
		Assert.notNull(adminId);
		Assert.isTrue(Utiles.a(admin.getAccount().getAuthorities()).equals(Authority.ADMIN));
		admin.setBan(false);
		this.adminRepository.save(admin);
	}
	public void unbanCustomer(final Customer cus) {
		Assert.isTrue(cus.isBan() != true);
		final int adminId = this.adminRepository.findAdministratorById(LoginService.getPrincipal().getId());
		Assert.notNull(adminId);
		Assert.isTrue(Utiles.a(cus.getAccount().getAuthorities()).equals(Authority.CUSTOMER));
		cus.setBan(false);
		this.customerService.save(cus);
	}
	public void unbanHandyWorker(final HandyWorker hw) {
		Assert.isTrue(hw.isBan() != true);
		final int adminId = this.adminRepository.findAdministratorById(LoginService.getPrincipal().getId());
		Assert.notNull(adminId);
		Assert.isTrue(Utiles.a(hw.getAccount().getAuthorities()).equals(Authority.HANDY_WORKER));
		hw.setBan(false);
		this.hwService.save(hw);
	}

	public void unbanSponsor(final Sponsor sponsor) {
		Assert.isTrue(sponsor.isBan() != true);
		final int adminId = this.adminRepository.findAdministratorById(LoginService.getPrincipal().getId());
		Assert.notNull(adminId);
		Assert.isTrue(Utiles.a(sponsor.getAccount().getAuthorities()).equals(Authority.SPONSOR));
		sponsor.setBan(false);
		this.sponsorService.addSponsor(sponsor);
	}

	public void unbanReferee(final Referee referee) {
		Assert.isTrue(referee.isBan() != true);
		final int adminId = this.adminRepository.findAdministratorById(LoginService.getPrincipal().getId());
		Assert.notNull(adminId);
		Assert.isTrue(Utiles.a(referee.getAccount().getAuthorities()).equals(Authority.REFEREE));
		referee.setBan(false);
		this.refereeService.save(referee);
	}

	//Unban Actors

	public void banAdmin(final Administrator admin) {
		Assert.isTrue(admin.isBan() == true);
		final int adminId = this.adminRepository.findAdministratorById(LoginService.getPrincipal().getId());
		Assert.notNull(adminId);
		Assert.isTrue(Utiles.a(admin.getAccount().getAuthorities()).equals(Authority.ADMIN));
		admin.setBan(true);
		this.adminRepository.save(admin);
	}

	public void banCustomer(final Customer cus) {
		Assert.isTrue(cus.isBan() == true);
		final int adminId = this.adminRepository.findAdministratorById(LoginService.getPrincipal().getId());
		Assert.notNull(adminId);
		Assert.isTrue(Utiles.a(cus.getAccount().getAuthorities()).equals(Authority.CUSTOMER));
		cus.setBan(true);
		this.customerService.save(cus);
	}
	public void banHandyWorker(final HandyWorker hw) {
		Assert.isTrue(hw.isBan() == true);
		final int adminId = this.adminRepository.findAdministratorById(LoginService.getPrincipal().getId());
		Assert.notNull(adminId);
		Assert.isTrue(Utiles.a(hw.getAccount().getAuthorities()).equals(Authority.HANDY_WORKER));
		hw.setBan(true);
		this.hwService.save(hw);
	}

	public void banSponsor(final Sponsor sponsor) {
		Assert.isTrue(sponsor.isBan() == true);
		final int adminId = this.adminRepository.findAdministratorById(LoginService.getPrincipal().getId());
		Assert.notNull(adminId);
		Assert.isTrue(Utiles.a(sponsor.getAccount().getAuthorities()).equals(Authority.SPONSOR));
		sponsor.setBan(true);
		this.sponsorService.addSponsor(sponsor);
	}

	public void banReferee(final Referee referee) {
		Assert.isTrue(referee.isBan() == true);
		final int adminId = this.adminRepository.findAdministratorById(LoginService.getPrincipal().getId());
		Assert.notNull(adminId);
		Assert.isTrue(Utiles.a(referee.getAccount().getAuthorities()).equals(Authority.REFEREE));
		referee.setBan(true);
		this.refereeService.save(referee);
	}

	//Top three
	public Collection<Customer> topThreeCustomerOrderByComplaints() {
		final int adminId = this.adminRepository.findAdministratorById(LoginService.getPrincipal().getId());
		Assert.notNull(adminId);
		List<Customer> topThree;
		topThree = this.adminRepository.topTreeCustomerOrderByComplaints();
		List<Customer> result;
		result = topThree.subList(0, 3);
		return result;
	}

	public Collection<HandyWorker> topThreeHandyWorkerOrderByComplaints() {
		final int adminId = this.adminRepository.findAdministratorById(LoginService.getPrincipal().getId());
		Assert.notNull(adminId);
		List<HandyWorker> topThree;
		topThree = this.adminRepository.topTreeHandyWorkerOrderByComplaints();
		List<HandyWorker> result;
		result = topThree.subList(0, 3);
		return result;
	}
}
