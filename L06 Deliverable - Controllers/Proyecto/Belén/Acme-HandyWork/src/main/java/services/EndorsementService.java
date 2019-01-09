
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EndorsementRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.Utiles;
import domain.Actor;
import domain.Endorsable;
import domain.Endorsement;

@Service
@Transactional
public class EndorsementService {

	@Autowired
	private EndorsementRepository	repositoryEndorsement;


	public Collection<Endorsement> findAll() {
		return this.repositoryEndorsement.findAll();
	}
	public Endorsement findOne(final int id) {
		return this.repositoryEndorsement.findOne(id);
	}

	public Actor findActorByUserId(final int id) {
		return this.repositoryEndorsement.findActorByUserAccountId(id);
	}

	public Endorsable findEndorsableByUserId(final int id) {
		return this.repositoryEndorsement.findEndorsableByUserAccountId(id);
	}

	public Collection<Endorsement> findEndorsementsByActorSended(final Endorsable endorsable) {
		System.out.println(endorsable.getId());
		Collection<Endorsement> result;
		result = this.repositoryEndorsement.getEndorsementsByActorSended(endorsable.getId());
		System.out.println(result);
		System.out.println(this.repositoryEndorsement.getEndorsementsByActorSended(endorsable.getId()));
		return result;
	}

	public Endorsement createEndorsement(final int id) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue((Utiles.findAuthority(user.getAuthorities(), Authority.CUSTOMER) || Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER)));
		Endorsable sender;
		sender = this.repositoryEndorsement.findEndorsableByUserAccountId(user.getId());
		Endorsable receiver;
		receiver = this.repositoryEndorsement.findEndorsableByUserAccountId(id);
		Endorsement result;
		result = new Endorsement();
		result.setMoment(new Date());
		result.setUserSended(sender);
		result.setUserReceived(receiver);
		result.setComments(new ArrayList<String>());
		return result;
	}

	public Endorsement save(final Endorsement e, final int id) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue((Utiles.findAuthority(user.getAuthorities(), Authority.CUSTOMER) || Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER)));
		Endorsement saved;
		Endorsable sender;
		sender = this.repositoryEndorsement.findEndorsableByUserAccountId(user.getId());
		Endorsable receiver;
		receiver = this.repositoryEndorsement.findEndorsableByUserAccountId(id);
		e.setUserSended(sender);
		e.setUserReceived(receiver);
		e.setMoment(new Date());
		saved = this.repositoryEndorsement.save(e);
		return saved;
	}

	public void delete(final int idEndorsement) {
		UserAccount user;
		user = LoginService.getPrincipal();
		Assert.isTrue((Utiles.findAuthority(user.getAuthorities(), Authority.CUSTOMER) || Utiles.findAuthority(user.getAuthorities(), Authority.HANDY_WORKER)));
		Endorsement deleted;
		deleted = this.repositoryEndorsement.findOne(idEndorsement);
		this.repositoryEndorsement.delete(deleted);
	}
}
