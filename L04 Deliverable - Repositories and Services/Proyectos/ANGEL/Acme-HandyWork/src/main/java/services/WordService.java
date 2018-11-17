
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

	public Word create() {
		Word w;
		w = new Word();
		w.setWord("");
		w.setIsGood(true);
		return w;
	}
	public Word addWord(final Word d) {
		Assert.notNull(d);
		return this.wordRepository.save(d);
	}
	public Word updateWord(final int id, final Word d) {
		Word update;
		update = this.findById(id);
		final int version = update.getVersion();
		Assert.notNull(d);
		update.setVersion(version + 1);
		update.setWord(d.getWord());
		update.setIsGood(d.getIsGood());

		return this.wordRepository.save(update);
	}
	public void deleteWord(final int id) {
		Assert.notNull(this.wordRepository.findOne(id));
		this.wordRepository.delete(id);
	}
}
