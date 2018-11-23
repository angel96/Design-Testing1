
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
public class Warranty extends DomainEntity {

	private String	title;
	private String	terms;
	private String	laws;
	private boolean	isDraft;


	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}
	@NotBlank
	public String getTerms() {
		return this.terms;
	}

	public void setTerms(final String terms) {
		this.terms = terms;
	}
	@NotBlank
	public String getLaws() {
		return this.laws;
	}

	public void setLaws(final String laws) {
		this.laws = laws;
	}

	public boolean isDraft() {
		return this.isDraft;
	}

	public void setDraft(final boolean isDraft) {
		this.isDraft = isDraft;
	}
}