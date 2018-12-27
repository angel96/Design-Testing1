
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class HandyWorker extends Endorsable {

	private Collection<Application>	application;
	private Collection<Phase>		phase;
	private Finder					finder;
	private Curriculum				curriculum;
	private Collection<Tutorial>	tutoriales;
	private Collection<Note>		notes;
	private String					make;


	@OneToMany
	public Collection<Application> getApplication() {
		return this.application;
	}

	public void setApplication(final Collection<Application> application) {
		this.application = application;

	}

	@OneToMany
	public Collection<Phase> getPhase() {
		return this.phase;
	}

	public void setPhase(final Collection<Phase> phase) {
		this.phase = phase;
	}
	@OneToOne(optional = false)
	public Finder getFinder() {
		return this.finder;
	}

	public void setFinder(final Finder finder) {
		this.finder = finder;
	}

	@OneToOne(optional = true)
	public Curriculum getCurriculum() {
		return this.curriculum;
	}

	public void setCurriculum(final Curriculum curriculum) {
		this.curriculum = curriculum;
	}
	@OneToMany
	public Collection<Tutorial> getTutoriales() {
		return this.tutoriales;
	}

	public void setTutoriales(final Collection<Tutorial> tutoriales) {
		this.tutoriales = tutoriales;
	}
	@OneToMany
	public Collection<Note> getNotes() {
		return this.notes;
	}

	public void setNotes(final Collection<Note> notes) {
		this.notes = notes;
	}

	@NotBlank
	public String getMake() {
		return this.make;
	}

	public void setMake(final String make) {
		this.make = make;
	}
}
