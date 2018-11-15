
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Word extends DomainEntity {

	private String	word;
	private boolean	isGood;


	@NotBlank
	public String getWord() {
		return this.word;
	}

	public void setWord(final String word) {
		this.word = word;
	}

	public boolean getIsGood() {
		return this.isGood;
	}

	public void setIsGood(final boolean isGood) {
		this.isGood = isGood;
	}
}
