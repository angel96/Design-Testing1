
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import domain.Customer;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository	customerRepository;


	public Customer findByUserAccount(final int userAccount) {

		Customer c;

		c = this.customerRepository.findByUserAccount(userAccount);

		Assert.notNull(c);

		return c;

	}

}
