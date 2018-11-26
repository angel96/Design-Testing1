
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.HandyWorkerRepository;
import security.Authority;
import security.UserAccount;
import domain.Application;
import domain.Curriculum;
import domain.Finder;
import domain.HandyWorker;
import domain.Message;
import domain.Note;
import domain.Phase;
import domain.Profile;
import domain.Tutorial;

@Service
@Transactional
public class HandyWorkerService {

	@Autowired
	private HandyWorkerRepository	repositoryHandyWorker;


	public HandyWorker findByUserAccount(final int userAccount) {
		HandyWorker result;
		result = this.repositoryHandyWorker.findByUserAccount(userAccount);
		Assert.notNull(result);
		return result;
	}
	public HandyWorker create() {
		HandyWorker handyWorker;
		handyWorker = new HandyWorker();
		UserAccount account;
		account = new UserAccount();
		Authority auth;
		auth = new Authority();
		Collection<Authority> authorities;
		authorities = new ArrayList<>();
		auth.setAuthority(Authority.HANDY_WORKER);
		authorities.add(auth);
		account.setAuthorities(authorities);
		handyWorker.setAccount(account);
		handyWorker.setApplication(new ArrayList<Application>());
		handyWorker.setFinders(new ArrayList<Finder>());
		handyWorker.setMessage(new ArrayList<Message>());
		handyWorker.setNotes(new ArrayList<Note>());
		handyWorker.setPhase(new ArrayList<Phase>());
		handyWorker.setProfiles(new ArrayList<Profile>());
		handyWorker.setTutoriales(new ArrayList<Tutorial>());
		handyWorker.setAdress("");
		handyWorker.setBan(false);
		handyWorker.setCurriculum(new Curriculum());
		handyWorker.setEmail("");
		handyWorker.setId(0);
		handyWorker.setMiddleName("");
		handyWorker.setName("");
		handyWorker.setPhone("");
		handyWorker.setPhoto("");
		handyWorker.setScore(0.0);
		handyWorker.setSurname("");
		handyWorker.setVersion(0);
		return handyWorker;
	}

	public HandyWorker save(final HandyWorker hw) {
		Assert.notNull(hw);
		return this.repositoryHandyWorker.save(hw);
	}

	public Message sendMessage(final HandyWorker sender, final HandyWorker recipient, final Message message) {
		Assert.notNull(sender);
		Assert.notNull(recipient);
		Assert.notNull(message);
		message.setSender(sender);
		message.setReceiver(recipient);
		//REPO PARA PERSISTIR EN BBDD, EN QUE REPO EN CONCRETO???? HARIA FALTA QUE EN ALGUN LADO SE CREE EL MENSAJE NO? -> REPO DE MENSAJES??? //TODO
		return null;
	}
}
