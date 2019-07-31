
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Access(AccessType.PROPERTY)
public class Finder extends DomainEntity {

	private Collection<FixUpTask>	fixUpTask;
	private Date					searchStart;
	private Date					searchEnd;
	
	@OneToMany
	public Collection<FixUpTask> getFixUpTask() {
		return fixUpTask;
	}
	public void setFixUpTask(Collection<FixUpTask> fixUpTask) {
		this.fixUpTask = fixUpTask;
	}
	@Temporal(TemporalType.DATE)
	public Date getSearchStart() {
		return searchStart;
	}
	public void setSearchStart(Date searchStart) {
		this.searchStart = searchStart;
	}
	@Temporal(TemporalType.DATE)
	public Date getSearchEnd() {
		return searchEnd;
	}
	public void setSearchEnd(Date searchEnd) {
		this.searchEnd = searchEnd;
	}
	
	
}
