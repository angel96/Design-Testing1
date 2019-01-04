
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import security.UserAccount;
import utilities.Utiles;
import domain.Customer;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository	customerRepository;


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

}
