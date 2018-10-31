
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;

@Entity
public class SearchResult extends DomainEntity {

	private Collection<FixUpTask>	fixUpTask;
	private Date					searchStart;
	private Date					searchEnd;


	public Collection<FixUpTask> getFixuptask() {
		return this.fixUpTask;
	}

	public void setFixuptask(final Collection<FixUpTask> fixuptask) {
		this.fixUpTask = fixuptask;
	}

	public Date getSearchStart() {
		return this.searchStart;
	}

	public void setSearchStart(final Date searchStart) {
		this.searchStart = searchStart;
	}

	public Date getSearchEnd() {
		return this.searchEnd;
	}

	public void setSearchEnd(final Date searchEnd) {
		this.searchEnd = searchEnd;
	}

}
