
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import security.LoginService;
import utilities.Utiles;
import domain.Actor;
import domain.Box;
import domain.Customer;
import domain.Message;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository	customerRepository;
	@Autowired
	private BoxService			boxService;
	@Autowired
	private ActorService		actorService;


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

	public Customer update(final Customer c) {
		Assert.notNull(c);
		return this.customerRepository.save(c);
	}

	public Customer create() {
		Customer customer;
		customer = new Customer();
		customer = Utiles.createCustomer();
		Assert.notNull(customer);
		return customer;
	}

	public Customer save(final Customer cust) {
		Assert.notNull(cust);
		return this.customerRepository.save(cust);
	}

	public Collection<Box> manageNotSystemBoxes() {
		Assert.notNull(this.customerRepository.findByUserAccount(LoginService.getPrincipal().getId()));
		Collection<Box> boxes;
		boxes = this.boxService.findAllNotSystemBoxes(LoginService.getPrincipal().getId());
		Assert.notNull(boxes);
		return boxes;
	}
	public void sendMessage(final Actor sender, final Collection<Actor> recipient, final Message m) {
		Assert.notNull(LoginService.getPrincipal().getAuthorities());
		Assert.notEmpty(recipient);
		Assert.notNull(m);
		this.actorService.sendIndividualMessage(sender, recipient, m);
	}
}
