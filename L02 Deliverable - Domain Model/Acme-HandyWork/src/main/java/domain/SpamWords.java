
package domain;

import java.util.Collection;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class SpamWords extends DomainEntity {

	private Collection<String>	word;


	@NotBlank
	public Collection<String> getWord() {
		return this.word;
	}

	public void setWord(final Collection<String> word) {
		this.word = word;
	}

}
