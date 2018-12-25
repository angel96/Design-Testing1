
package services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

	public Customer update(final Customer c) {
		Assert.notNull(c);
		return this.customerRepository.save(c);
	}

	public Customer save(final Customer cust) {
		Assert.notNull(cust);
		return this.customerRepository.save(cust);
	}
	public Map<String, Double> dashBoardFixUpTaskPerUser() {
		Map<String, Double> result;
		result = new HashMap<String, Double>();

		result.put("FixUpTaskPerUserAVG", this.customerRepository.findAVGOfFixUpTaskPerUser());
		result.put("FixUpTaskPerUserMIN", this.customerRepository.findMINOfFixUpTaskPerUser());
		result.put("FixUpTaskPerUserMAX", this.customerRepository.findMAXOfFixUpTaskPerUser());
		result.put("FixUpTaskPerUserSTDEV", this.customerRepository.findATDDEVOfFixUpTaskPerUser());

		return result;
	}
}
