
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.WordRepository;
import domain.Word;

@Service
@Transactional
public class WordService {

	@Autowired
	private WordRepository	wordRepository;


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
		Word word;
		Assert.notNull(d);
		word = this.wordRepository.save(d);
		Assert.notNull(word);
		return word;
	}
	public Word updateWord(final int id, final Word d) {
		Word update, saved;
		update = this.findById(id);
		Assert.notNull(d);
		update.setWord(d.getWord());
		update.setIsGood(d.getIsGood());
		saved = this.wordRepository.save(update);
		Assert.notNull(saved);
		return saved;
	}
	public void deleteWord(final int id) {
		Assert.notNull(this.wordRepository.findOne(id));
		this.wordRepository.delete(id);
	}
}
