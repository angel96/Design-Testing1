
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
import domain.Complaint;
import domain.Customer;
import domain.FixUpTask;
import domain.Message;
import domain.Note;
import domain.Profile;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository	customerRepository;


	public Customer findByUserAccount(final int userAccount) {

		return this.customerRepository.findByUserAccount(userAccount);

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
		account.setAuthorities(authorities);
		customer.setAccount(account);
		customer.setComplaint(new ArrayList<Complaint>());
		customer.setFixUpTask(new ArrayList<FixUpTask>());
		customer.setMessage(new ArrayList<Message>());
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
	//TODO mensajes

	public Customer save(final Customer cust) {
		Assert.notNull(cust);
		return this.customerRepository.save(cust);
	}
}
