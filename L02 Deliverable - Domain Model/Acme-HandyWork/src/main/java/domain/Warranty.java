
package domain;

import java.util.Collection;

import javax.persistence.Entity;

@Entity
public class Warranty extends DomainEntity {

	private String					title;
	private String					terms;
	private String					laws;
	private Collection<FixUpTask>	fixUpTask;	//??


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

	public Collection<FixUpTask> getFixUpTask() {
		return this.fixUpTask;
	}

	public void setFixUpTask(final Collection<FixUpTask> fixUpTask) {
		this.fixUpTask = fixUpTask;
	}

}
