
package domain;

import java.util.Collection;

import javax.persistence.Entity;

@Entity
public class Referee extends Actor {

	private Collection<Report>	reports;
	private Collection<Note>	notes;


	public Collection<Report> getReports() {
		return this.reports;
	}

	public void setReports(final Collection<Report> reports) {
		this.reports = reports;
	}

	public Collection<Note> getNotes() {
		return this.notes;
	}

	public void setNotes(final Collection<Note> notes) {
		this.notes = notes;
	}

}
