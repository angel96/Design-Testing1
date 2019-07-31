
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SpamWordRepository;
import domain.SpamWord;

@Service
@Transactional
public class SpamWordService {

	@Autowired
	private SpamWordRepository	spamWordRepository;


	public Collection<SpamWord> findAll() {
		Collection<SpamWord> spam;
		spam = this.spamWordRepository.findAll();
		Assert.notNull(spam);
		return spam;
	}
}
