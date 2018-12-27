
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
import domain.Box;
import domain.Customer;

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
	public Customer findOne(final int id) {
		return this.customerRepository.findOne(id);
	}
	public Customer findByUserAccount(final int userAccount) {
		Customer c;
		c = this.customerRepository.findByUserAccount(userAccount);
		Assert.notNull(c);
		return c;

	}

	public Customer save(final Customer cust) {
		if (cust.getId() != 0) {
			final UserAccount account = this.findOne(cust.getId()).getAccount();
			account.setUsername(cust.getAccount().getUsername());
			account.setPassword(Utiles.hashPassword(cust.getAccount().getPassword()));
			account.setAuthorities(cust.getAccount().getAuthorities());
			cust.setAccount(account);
		} else
			cust.getAccount().setPassword(Utiles.hashPassword(cust.getAccount().getPassword()));

		Customer modify;

		modify = this.customerRepository.saveAndFlush(cust);

		return modify;
	}
	public Collection<Box> manageNotSystemBoxes(final Customer cust) {
		Assert.notNull(this.customerRepository.findByUserAccount(LoginService.getPrincipal().getId()));
		Collection<Box> boxes;
		boxes = this.boxService.findAllNonBoxes(cust.getId());
		return boxes;
	}
	/*
	 * public void sendMessage(final Customer sender, final Administrator recipient, final Message m) {
	 * Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.CUSTOMER));
	 * Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.CUSTOMER));
	 * Assert.isTrue(Utiles.findAuthority(recipient.getAccount().getAuthorities(), Authority.ADMIN));
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
	 * this.adminService.save(recipient);
	 * }
	 * 
	 * public void sendMessage(final Customer sender, final Sponsor recipient, final Message m) {
	 * Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.CUSTOMER));
	 * Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.CUSTOMER));
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
	 * 
	 * this.sponsorService.addSponsor(recipient);
	 * }
	 * 
	 * public void sendMessage(final Customer sender, final Referee recipient, final Message m) {
	 * Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.CUSTOMER));
	 * Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.CUSTOMER));
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
	 * public void sendMessage(final Customer sender, final Customer recipient, final Message m) {
	 * Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.CUSTOMER));
	 * Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.CUSTOMER));
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
	 * this.customerRepository.save(recipient);
	 * }
	 * 
	 * public void sendMessage(final Customer sender, final HandyWorker recipient, final Message m) {
	 * Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.CUSTOMER));
	 * Assert.isTrue(Utiles.findAuthority(sender.getAccount().getAuthorities(), Authority.CUSTOMER));
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
	 * this.hwService.save(recipient);
	 * }
	 */
}
