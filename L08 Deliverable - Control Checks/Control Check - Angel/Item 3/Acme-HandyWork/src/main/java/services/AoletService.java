
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AoletRepository;
import security.Authority;
import security.LoginService;
import utilities.Utiles;
import domain.Actor;
import domain.Aolet;
import domain.Customer;
import domain.FixUpTask;

@Service
@Transactional
public class AoletService {

	@Autowired
	private AoletRepository		repositoryAolet;

	@Autowired
	private FixUpTaskService	fixUpTaskService;


	public FixUpTask fixUpByAolet(final int aolet) {
		return this.repositoryAolet.getFixUpTaskByAolet(aolet);
	}

	public Actor findByUserAccount(final int account) {
		return this.repositoryAolet.findActorByUserAccount(account);
	}

	public Collection<Aolet> getAoletsUserLogged() {
		Collection<Aolet> aolets;
		aolets = this.repositoryAolet.getAoletsByCustomer(LoginService.getPrincipal().getId());
		return aolets;
	}

	public Collection<Aolet> getAoletsByFixUpTask(final int fixuptask) {
		Collection<Aolet> aolets;
		if (Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.CUSTOMER))
			aolets = this.repositoryAolet.getAoletsByFixUpTask(fixuptask);
		else
			aolets = this.repositoryAolet.getAoletsByFixUpTaskFinalMode(fixuptask);

		return aolets;
	}

	public Aolet findOne(final int id) {
		return this.repositoryAolet.findOne(id);
	}

	public Aolet create() {
		Aolet aolet;
		aolet = new Aolet();

		aolet.setTicker(Utiles.generateTickerControlCheck());
		aolet.setBody("");
		aolet.setFinalMode(false);
		aolet.setOptionalPicture("");
		aolet.setTitle("");
		aolet.setMoment(new Date());

		return aolet;
	}

	public Aolet save(final Aolet aolet, final int fixUpTask) {

		final Customer c = (Customer) this.repositoryAolet.findActorByUserAccount(LoginService.getPrincipal().getId());

		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.CUSTOMER));

		FixUpTask f = null;
		Collection<Aolet> aolets = null;

		if (fixUpTask != 0) {

			f = this.fixUpTaskService.findOne(fixUpTask);

			Assert.isTrue(c.getFixUpTask().contains(f));

		} else
			f = this.repositoryAolet.getFixUpTaskByAolet(aolet.getId());

		aolets = f.getAolets();

		if (aolet.getId() != 0) {
			final Aolet one = this.repositoryAolet.findOne(aolet.getId());
			if (one.isFinalMode() == false)
				Utiles.finalModeAolet = false;
		}

		Aolet saved = null;

		if (!aolet.isFinalMode() || Utiles.finalModeAolet == false) {
			System.out.println(aolet.getBody());
			saved = this.repositoryAolet.save(aolet);
			System.out.println(saved.getBody());
		}

		if (!aolets.contains(saved)) {
			aolets.add(saved);
			f.setAolets(aolets);
		}

		return saved;
	}

	public void delete(final Aolet aolet) {
		final Customer c = (Customer) this.repositoryAolet.findActorByUserAccount(LoginService.getPrincipal().getId());

		Assert.isTrue(Utiles.findAuthority(LoginService.getPrincipal().getAuthorities(), Authority.CUSTOMER));

		final Collection<Aolet> aoletsCustomer = this.repositoryAolet.getAoletsByCustomer(c.getAccount().getId());

		Assert.isTrue(aoletsCustomer.contains(aolet));

		final FixUpTask f = this.repositoryAolet.getFixUpTaskByAolet(aolet.getId());

		Collection<Aolet> aolets;
		aolets = f.getAolets();

		if (!aolet.isFinalMode()) {
			aolets.remove(aolet);
			f.setAolets(aolets);
			this.repositoryAolet.delete(aolet);
		}
	}

}
