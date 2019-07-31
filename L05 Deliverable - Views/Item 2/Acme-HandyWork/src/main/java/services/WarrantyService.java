
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.WarrantyRepository;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Warranty;

@Service
@Transactional
public class WarrantyService {

	@Autowired
	private WarrantyRepository		repositoryWarranty;

	@Autowired
	private AdministratorService	serviceAdministrator;


	public Collection<Warranty> findAll() {
		return this.repositoryWarranty.findAll();
	}

	public Warranty findOne(final int id) {
		return this.repositoryWarranty.findOne(id);
	}

	public Warranty addWarranty(final Warranty w) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Administrator admin;
		admin = this.serviceAdministrator.findOne(user.getId());
		Assert.notNull(admin);
		Warranty result;
		Assert.notNull(w);
		result = this.repositoryWarranty.save(w);
		Assert.notNull(result);
		return result;
	}

	//Update or Delete can only be done when Warranty is on draft mode.

	public Warranty updateWarranty(final Warranty newer) {

		Warranty saved;

		UserAccount user;
		user = LoginService.getPrincipal();
		Administrator admin;
		admin = this.serviceAdministrator.findOne(user.getId());

		Assert.notNull(admin);

		if (newer.isDraftMode())
			saved = this.repositoryWarranty.save(newer);
		else
			throw new IllegalAccessError();

		return saved;
	}

	public void deleteWarranty(final int id) {
		Warranty w;
		w = this.findOne(id);

		if (w.isDraftMode())
			this.repositoryWarranty.delete(w);
		else
			throw new IllegalAccessError();
	}

}
