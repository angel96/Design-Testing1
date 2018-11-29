
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
import domain.Administrator;
import domain.Box;
import domain.Complaint;
import domain.Customer;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Message;
import domain.Note;
import domain.Profile;
import domain.Referee;
import domain.Sponsor;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository		customerRepository;
	@Autowired
	private AdministratorService	adminService;
	@Autowired
	private HandyWorkerService		hwService;
	@Autowired
	private RefereeService			refereeService;
	@Autowired
	private SponsorService			sponsorService;
	@Autowired
	private BoxService				boxService;
	@Autowired
	private MessageService			messageService;


	public Collection<Customer> findAll() {
		return this.customerRepository.findAll();
	}
	public Customer findByUserAccount(final int userAccount) {
		Customer c;
		c = this.customerRepository.findByUserAccount(userAccount);
		Assert.notNull(c);
		return c;

	}

	public Customer update(final Customer c) {
		Assert.notNull(c);
		return this.customerRepository.save(c);
	}

	public Customer create() {
		Customer customer;
		customer = new Customer();
		UserAccount account;
		account = new UserAccount();
		Authority auth;
		auth = new Authority();
		Collection<Authority> authorities;
		authorities = new ArrayList<>();
		auth.setAuthority(Authority.CUSTOMER);
		authorities.add(auth);
		account.setUsername("");
		account.setPassword("");
		account.setAuthorities(authorities);
		customer.setAccount(account);
		customer.setComplaint(new ArrayList<Complaint>());
		customer.setFixUpTask(new ArrayList<FixUpTask>());
		customer.setNotes(new ArrayList<Note>());
		customer.setProfiles(new ArrayList<Profile>());
		customer.setAdress("");
		customer.setBan(false);
		customer.setEmail("");
		customer.setId(0);
		customer.setMiddleName("");
		customer.setName("");
		customer.setPhone("");
		customer.setPhoto("");
		customer.setScore(0.0);
		customer.setSurname("");
		customer.setVersion(0);
		return customer;
	}

	public Customer save(final Customer cust) {
		Assert.notNull(cust);
		return this.customerRepository.save(cust);
	}

	public Collection<Box> manageNotSystemBoxes(final Customer cust) {
		Assert.notNull(this.customerRepository.findByUserAccount(LoginService.getPrincipal().getId()));
		Collection<Box> boxes;
		boxes = this.boxService.findAllNonBoxes(cust.getId());
		return boxes;
	}
	public void sendMessage(final Customer sender, final Administrator recipient, final Message m) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.CUSTOMER));
		Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.CUSTOMER));
		Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.ADMIN));
		Assert.notNull(m);
		m.setSender(sender);
		m.setReceiver(recipient);
		Collection<Message> received;
		received = this.messageService.findAllMessagesReceivedBy(recipient.getAccount().getId());
		Utiles.sendIndividualMessage(recipient, received, m);

		this.adminService.save(recipient);
	}

	public void sendMessage(final Customer sender, final Sponsor recipient, final Message m) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.CUSTOMER));
		Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.CUSTOMER));
		Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.SPONSOR));
		Assert.notNull(m);
		m.setSender(sender);
		m.setReceiver(recipient);
		Collection<Message> received;
		received = this.messageService.findAllMessagesReceivedBy(recipient.getAccount().getId());
		Utiles.sendIndividualMessage(recipient, received, m);

		this.sponsorService.addSponsor(recipient);
	}

	public void sendMessage(final Customer sender, final Referee recipient, final Message m) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.CUSTOMER));
		Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.CUSTOMER));
		Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.REFEREE));
		Assert.notNull(m);
		m.setSender(sender);
		m.setReceiver(recipient);
		Collection<Message> received;
		received = this.messageService.findAllMessagesReceivedBy(recipient.getAccount().getId());
		Utiles.sendIndividualMessage(recipient, received, m);
		this.refereeService.save(recipient);
	}

	public void sendMessage(final Customer sender, final Customer recipient, final Message m) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.CUSTOMER));
		Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.CUSTOMER));
		Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.CUSTOMER));
		Assert.notNull(m);
		m.setSender(sender);
		m.setReceiver(recipient);
		Collection<Message> received;
		received = this.messageService.findAllMessagesReceivedBy(recipient.getAccount().getId());
		Utiles.sendIndividualMessage(recipient, received, m);
		this.customerRepository.save(recipient);
	}

	public void sendMessage(final Customer sender, final HandyWorker recipient, final Message m) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.CUSTOMER));
		Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.CUSTOMER));
		Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.HANDY_WORKER));
		Assert.notNull(m);
		m.setSender(sender);
		m.setReceiver(recipient);
		Collection<Message> received;
		received = this.messageService.findAllMessagesReceivedBy(recipient.getAccount().getId());
		Utiles.sendIndividualMessage(recipient, received, m);
		this.hwService.save(recipient);
	}
}
