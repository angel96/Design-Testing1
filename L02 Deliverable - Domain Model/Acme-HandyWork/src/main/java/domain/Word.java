
package domain;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Word extends DomainEntity {

	private String	word;
	private Boolean	isGood;


	@NotBlank
	public String getWord() {
		return this.word;
	}

	public void setWord(final String word) {
		this.word = word;
	}
	@NotBlank
	public Boolean getIsGood() {
		return this.isGood;
	}

	public void setIsGood(final Boolean isGood) {
		this.isGood = isGood;
	}
}
