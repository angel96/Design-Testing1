
package domain;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Stock extends DomainEntity {

	private FixUpTask	fixuptask;
	private Date		searchStart;
	private Date		searchEnd;


	public FixUpTask getFixuptask() {
		return this.fixuptask;
	}

	public void setFixuptask(final FixUpTask fixuptask) {
		this.fixuptask = fixuptask;
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
