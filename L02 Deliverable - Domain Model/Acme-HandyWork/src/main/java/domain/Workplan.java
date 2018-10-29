
package domain;

import java.util.Collection;

import javax.persistence.Entity;

@Entity
public class Workplan extends DomainEntity {

	private Collection<Phase>	phase;


	public Collection<Phase> getPhase() {
		return this.phase;
	}

	public void setPhase(final Collection<Phase> phase) {
		this.phase = phase;
	}

}
