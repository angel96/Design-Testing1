
package domain;

import javax.persistence.Entity;

@Entity
public class Warranty extends DomainEntity {

	private String	title;
	private String	terms;
	private String	laws;


	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getTerms() {
		return this.terms;
	}

	public void setTerms(final String terms) {
		this.terms = terms;
	}

	public String getLaws() {
		return this.laws;
	}

	public void setLaws(final String laws) {
		this.laws = laws;
	}

}
