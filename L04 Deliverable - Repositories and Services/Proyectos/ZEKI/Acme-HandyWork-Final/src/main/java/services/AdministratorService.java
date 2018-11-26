package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import domain.Administrator;


@Service
@Transactional
public class AdministratorService {
	
	@Autowired
	private AdministratorRepository	administratorRepository;


	public Administrator findByUserAccount(final int userAccount) {

		return this.administratorRepository.findByUserAccount(userAccount);

	}
	
	public Administrator finAdmin(final int id) {
		return this.administratorRepository.findAdmin(id);
	}
	
	public Administrator update(final Administrator a) {
		Assert.notNull(a);
		return this.administratorRepository.save(a);
	}

}
