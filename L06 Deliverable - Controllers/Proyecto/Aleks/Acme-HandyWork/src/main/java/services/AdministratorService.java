
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
	private SponsorService			sponsorService;

	@Autowired
	private CustomerService			customerService;

	@Autowired
	private HandyWorkerService		hwService;

	@Autowired
	private RefereeService			refereeService;

	@Autowired
	private BoxService				boxService;

	@Autowired
	private MessageService			messageService;


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
	//Ban Actors
	//	public void unbanAdmin(final Administrator admin) {
	//		Assert.isTrue(admin.isBan() == true);
	//		Administrator administrator;
	//		administrator = this.adminRepository.findAdministratorByUserAccountId(LoginService.getPrincipal().getId());
	//		Assert.notNull(administrator);
	//		Assert.isTrue(Utiles.findAuthority(admin.getAccount().getAuthorities(), Authority.ADMIN));
	//		admin.setBan(false);
	//		this.adminRepository.save(admin);
	//	}
	//	public void unbanCustomer(final Administrator admin, final Customer cus) {
	//		Assert.isTrue(cus.isBan());
	//		Administrator administrator;
	//		administrator = this.adminRepository.findAdministratorByUserAccountId(LoginService.getPrincipal().getId());
	//		Assert.notNull(administrator);
	//		Assert.isTrue(Utiles.findAuthority(cus.getAccount().getAuthorities(), Authority.CUSTOMER));
	//		cus.setBan(false);
	//		this.customerService.save(cus);
	//	}
	//	public void unbanHandyWorker(final Administrator admin, final HandyWorker hw) {
	//		Assert.isTrue(hw.isBan() == true);
	//		Administrator administrator;
	//		administrator = this.adminRepository.findAdministratorByUserAccountId(LoginService.getPrincipal().getId());
	//		Assert.notNull(administrator);
	//		Assert.isTrue(Utiles.findAuthority(hw.getAccount().getAuthorities(), Authority.HANDY_WORKER));
	//		hw.setBan(false);
	//		this.hwService.save(hw);
	//	}
	//	public void unbanSponsor(final Administrator admin, final Sponsor sponsor) {
	//		Assert.isTrue(sponsor.isBan() == true);
	//		Administrator administrator;
	//		administrator = this.adminRepository.findAdministratorByUserAccountId(LoginService.getPrincipal().getId());
	//		Assert.notNull(administrator);
	//		Assert.isTrue(Utiles.findAuthority(sponsor.getAccount().getAuthorities(), Authority.SPONSOR));
	//		sponsor.setBan(false);
	//		this.sponsorService.addSponsor(sponsor);
	//	}
	//
	//	public void unbanReferee(final Administrator admin, final Referee referee) {
	//		Assert.isTrue(referee.isBan() == true);
	//		Administrator administrator;
	//		administrator = this.adminRepository.findAdministratorByUserAccountId(LoginService.getPrincipal().getId());
	//		Assert.notNull(administrator);
	//		Assert.isTrue(Utiles.findAuthority(referee.getAccount().getAuthorities(), Authority.REFEREE));
	//		referee.setBan(false);
	//		this.refereeService.save(referee);
	//	}
	//	//Unban Actors
	//
	//	public void banAdmin(final Administrator admin) {
	//		Assert.isTrue(admin.isBan() == false);
	//		Administrator administrator;
	//		administrator = this.adminRepository.findAdministratorByUserAccountId(LoginService.getPrincipal().getId());
	//		Assert.notNull(administrator);
	//		Assert.isTrue(Utiles.findAuthority(admin.getAccount().getAuthorities(), Authority.ADMIN));
	//		admin.setBan(true);
	//		this.adminRepository.save(admin);
	//	}
	//	public void banCustomer(final Administrator admin, final Customer cus) {
	//		Assert.isTrue(cus.isBan() == false);
	//		Administrator administrator;
	//		administrator = this.adminRepository.findAdministratorByUserAccountId(LoginService.getPrincipal().getId());
	//		Assert.notNull(administrator);
	//		Assert.isTrue(Utiles.findAuthority(cus.getAccount().getAuthorities(), Authority.CUSTOMER));
	//		cus.setBan(true);
	//		this.customerService.save(cus);
	//	}
	//	public void banHandyWorker(final Administrator admin, final HandyWorker hw) {
	//		Assert.isTrue(hw.isBan() == false);
	//		Administrator administrator;
	//		administrator = this.adminRepository.findAdministratorByUserAccountId(LoginService.getPrincipal().getId());
	//		Assert.notNull(administrator);
	//		Assert.isTrue(Utiles.findAuthority(hw.getAccount().getAuthorities(), Authority.HANDY_WORKER));
	//		hw.setBan(true);
	//		this.hwService.save(hw);
	//	}
	//	public void banSponsor(final Administrator admin, final Sponsor sponsor) {
	//		Assert.isTrue(sponsor.isBan() == false);
	//		Administrator administrator;
	//		administrator = this.adminRepository.findAdministratorByUserAccountId(LoginService.getPrincipal().getId());
	//		Assert.notNull(administrator);
	//		Assert.isTrue(Utiles.findAuthority(sponsor.getAccount().getAuthorities(), Authority.SPONSOR));
	//		sponsor.setBan(true);
	//		this.sponsorService.addSponsor(sponsor);
	//	}
	//	public void banReferee(final Administrator admin, final Referee referee) {
	//		Assert.isTrue(referee.isBan() == false);
	//		Administrator administrator;
	//		administrator = this.adminRepository.findAdministratorByUserAccountId(LoginService.getPrincipal().getId());
	//		Assert.notNull(administrator);
	//		Assert.isTrue(Utiles.findAuthority(referee.getAccount().getAuthorities(), Authority.REFEREE));
	//		referee.setBan(true);
	//		this.refereeService.save(referee);
	//	}
	//
	//	public void sendMessage(final Administrator sender, final Administrator recipient, final Message m) {
	//		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));
	//		Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.ADMIN));
	//		Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.ADMIN));
	//		Assert.notNull(m);
	//		Collection<Actor> receivers;
	//		receivers = new ArrayList<>();
	//		receivers.add(recipient);
	//		/*
	//		 * m.setSender(sender);
	//		 * m.setReceiver(receivers);
	//		 * Collection<Message> received;
	//		 * received = this.messageService.findAllMessagesReceivedBy(recipient.getAccount().getId());
	//		 * Utiles.sendIndividualMessage(recipient, received, m);
	//		 * this.adminRepository.save(recipient);
	//		 */
	//		m.setSender(sender);
	//		m.setReceiver(receivers);
	//	}
	/*
	 * public void sendMessage(final Administrator sender, final Sponsor recipient, final Message m) {
	 * Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));
	 * Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.ADMIN));
	 * Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.SPONSOR));
	 * Assert.notNull(m);
	 * m.setSender(sender);
	 * Collection<Actor> receivers;
	 * receivers = new ArrayList<>();
	 * receivers.add(recipient);
	 * m.setReceiver(receivers);
	 * Collection<Message> received;
	 * received = this.messageService.findAllMessagesReceivedBy(recipient.getAccount().getId());
	 * Utiles.sendIndividualMessage(recipient, received, m);
	 * this.sponsorService.addSponsor(recipient);
	 * }
	 * 
	 * public void sendMessage(final Administrator sender, final Referee recipient, final Message m) {
	 * Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));
	 * Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.ADMIN));
	 * Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.REFEREE));
	 * Assert.notNull(m);
	 * m.setSender(sender);
	 * Collection<Actor> receivers;
	 * receivers = new ArrayList<>();
	 * receivers.add(recipient);
	 * m.setReceiver(receivers);
	 * Collection<Message> received;
	 * received = this.messageService.findAllMessagesReceivedBy(recipient.getAccount().getId());
	 * Utiles.sendIndividualMessage(recipient, received, m);
	 * this.refereeService.save(recipient);
	 * }
	 * 
	 * public void sendMessage(final Administrator sender, final Customer recipient, final Message m) {
	 * Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));
	 * Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.ADMIN));
	 * Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.CUSTOMER));
	 * Assert.notNull(m);
	 * m.setSender(sender);
	 * Collection<Actor> receivers;
	 * receivers = new ArrayList<>();
	 * receivers.add(recipient);
	 * m.setReceiver(receivers);
	 * Collection<Message> received;
	 * received = this.messageService.findAllMessagesReceivedBy(recipient.getAccount().getId());
	 * Utiles.sendIndividualMessage(recipient, received, m);
	 * 
	 * this.customerService.save(recipient);
	 * }
	 * 
	 * public void sendMessage(final Administrator sender, final HandyWorker recipient, final Message m) {
	 * Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));
	 * Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.ADMIN));
	 * Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.HANDY_WORKER));
	 * Assert.notNull(m);
	 * m.setSender(sender);
	 * Collection<Actor> receivers;
	 * receivers = new ArrayList<>();
	 * receivers.add(recipient);
	 * m.setReceiver(receivers);
	 * Collection<Message> received;
	 * received = this.messageService.findAllMessagesReceivedBy(recipient.getAccount().getId());
	 * Utiles.sendIndividualMessage(recipient, received, m);
	 * 
	 * this.hwService.save(recipient);
	 * }
	 * 
	 * public Collection<Actor> getSuspiciousActors(final Administrator admin) {
	 * Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));
	 * return this.adminRepository.getSuspiciousActors();
	 * }
	 */
}
