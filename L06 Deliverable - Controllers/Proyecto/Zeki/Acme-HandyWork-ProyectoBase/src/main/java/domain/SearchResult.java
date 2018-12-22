
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class SearchResult extends DomainEntity {

	private Collection<FixUpTask>	fixUpTask;
	private Date					searchStart;
	private Date					searchEnd;


	@OneToOne(optional = true, targetEntity = SearchResult.class)
	public Collection<FixUpTask> getFixuptask() {
		return this.fixUpTask;
	}

	public void setFixuptask(final Collection<FixUpTask> fixuptask) {
		this.fixUpTask = fixuptask;
	}
	@Temporal(TemporalType.DATE)
	public Date getSearchStart() {
		return this.searchStart;
	}

	public void setSearchStart(final Date searchStart) {
		this.searchStart = searchStart;
	}
	@Temporal(TemporalType.DATE)
	public Date getSearchEnd() {
		return this.searchEnd;
	}

	public void setSearchEnd(final Date searchEnd) {
		this.searchEnd = searchEnd;
	}

}
