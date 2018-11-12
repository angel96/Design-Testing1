
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class HandyWorker extends Endorsable {
	private Collection<Application> application;
	private Collection<Phase> phase;
	
	@OneToMany
	public Collection<Application> getApplication() {
		return application;
	}

	public void setApplication(Collection<Application> application) {
		this.application = application;
		
	}
	
	@OneToMany
	public Collection<Phase> getPhase() {
		return phase;
	}

	public void setPhase(Collection<Phase> phase) {
		this.phase = phase;
	}

	

}
