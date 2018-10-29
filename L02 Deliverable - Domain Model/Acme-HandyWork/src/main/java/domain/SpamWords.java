
package domain;

import javax.persistence.Entity;

@Entity
public class SpamWords extends DomainEntity {

	private String	word;	//¿No deberia ser una lista?


	public String getWord() {
		return this.word;
	}

	public void setWord(final String word) {
		this.word = word;
	}

}
