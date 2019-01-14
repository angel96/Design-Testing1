
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import security.Authority;
import security.UserAccount;
import utilities.Utiles;
import domain.Actor;
import domain.Box;
import domain.Complaint;
import domain.Customer;
import domain.FixUpTask;
import domain.Note;
import domain.Profile;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository	customerRepository;

	@Autowired
	private BoxService			boxService;


	public Collection<Customer> findAll() {
		return this.customerRepository.findAll();
	}

	public Actor findActorByUserAccount(final int id) {
		return this.customerRepository.findActorByUserAccount(id);
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
	public Customer findByFixUp(final int id) {
		return this.customerRepository.findByFixUpTask(id);
	}
	public Customer createCustomer() {

		Customer customer;
		customer = new Customer();

		UserAccount account;
		account = new UserAccount();
		account.setEnabled(true);
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

		customer.setEmail("");

		customer.setMiddleName("");
		customer.setName("");
		customer.setPhone("");
		customer.setPhoto("");
		customer.setScore(0.0);
		customer.setSurname("");

		return customer;
	}
	public Customer save(final Customer cust) {
		if (cust.getId() != 0) {
			final UserAccount account = this.findOne(cust.getId()).getAccount();
			account.setUsername(cust.getAccount().getUsername());
			account.setPassword(Utiles.hashPassword(cust.getAccount().getPassword()));
			account.setAuthorities(cust.getAccount().getAuthorities());
			cust.setAccount(account);
		} else {
			cust.getAccount().setPassword(Utiles.hashPassword(cust.getAccount().getPassword()));
			final Collection<Box> boxes = this.boxService.save(Utiles.initBoxes());
			cust.setBoxes(boxes);
		}
		Customer modify;

		modify = this.customerRepository.saveAndFlush(cust);

		return modify;
	}

}
