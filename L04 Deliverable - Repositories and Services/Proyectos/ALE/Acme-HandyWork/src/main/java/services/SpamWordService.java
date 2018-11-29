
package services;

import java.util.Collection;

import repositories.SpamWordRepository;
import domain.SpamWord;

public class SpamWordService {

	private SpamWordRepository	spamWordRepository;


	public Collection<SpamWord> findAll() {
		return this.spamWordRepository.findAll();
	}
}
