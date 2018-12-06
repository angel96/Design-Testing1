
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.WordRepository;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Word;

@Service
@Transactional
public class WordService {

	@Autowired
	private WordRepository			wordRepository;

	@Autowired
	private AdministratorService	serviceAdministrator;


	public Word findById(final int id) {
		return this.wordRepository.findOne(id);
	}

	public Collection<Word> getAll() {
		return this.wordRepository.findAll();
	}
	public Collection<Word> findGoodWords() {
		return this.wordRepository.getGoodWords();
	}

	public Collection<Word> findBadWords() {
		return this.wordRepository.getBadWords();
	}

	public Word addWord(final Word d) {

		UserAccount user;
		user = LoginService.getPrincipal();
		Administrator admin;
		admin = this.serviceAdministrator.findOne(user.getId());
		Assert.notNull(admin);

		Word word;
		Assert.notNull(d);
		word = this.wordRepository.save(d);
		Assert.notNull(word);
		return word;
	}
	public Word updateWord(final Word d) {

		UserAccount user;
		user = LoginService.getPrincipal();
		Administrator admin;
		admin = this.serviceAdministrator.findOne(user.getId());
		Assert.notNull(admin);

		Word saved;
		Assert.notNull(d);
		saved = this.wordRepository.save(d);
		Assert.notNull(saved);
		return saved;
	}
	public void deleteWord(final int id) {
		Assert.notNull(this.wordRepository.findOne(id));
		UserAccount user;
		user = LoginService.getPrincipal();
		Administrator admin;
		admin = this.serviceAdministrator.findOne(user.getId());
		Assert.notNull(admin);
		this.wordRepository.delete(id);
	}
}
