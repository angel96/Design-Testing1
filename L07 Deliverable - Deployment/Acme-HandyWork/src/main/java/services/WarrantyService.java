
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.WarrantyRepository;
import security.Authority;
import security.LoginService;
import utilities.Utiles;
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
	public Collection<Warranty> findAllFinalWarranties() {
		return this.repositoryWarranty.findAllFinalWarranties();
	}
	public Warranty findOne(final int id) {
		return this.repositoryWarranty.findOne(id);
	}
	public Warranty createWarranty() {

		Warranty w;
		w = new Warranty();

		w.setTitle("");
		w.setLaws("");
		w.setTerms("");
		w.setDraftMode(true);

		return w;
	}

	public Warranty save(final Warranty w) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));
		Warranty result = null;
		result = this.repositoryWarranty.save(w);
		return result;
	}
	public void deleteWarranty(final int id) {
		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.ADMIN));
		Warranty w;
		w = this.repositoryWarranty.findOne(id);

		if (w.isDraftMode())
			this.repositoryWarranty.delete(w);
	}

}
