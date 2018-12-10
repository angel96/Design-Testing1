
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
public class Referee extends Actor {

	private Collection<Note>	notes;
	private Collection<Complaint> complaints;

	@OneToMany
	public Collection<Note> getNotes() {
		return this.notes;
	}

	public void setNotes(final Collection<Note> notes) {
		this.notes = notes;
	}
	
	@OneToMany(mappedBy = "referee")
	public Collection<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(Collection<Complaint> complaints) {
		this.complaints = complaints;
	}

}
