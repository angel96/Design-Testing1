
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
public class Report extends DomainEntity {

	private Date				moment;
	private String				description;
	private Collection<String>	attachments;
	private Collection<Note>	notes;
	private Complaint			complaints;


	@NotBlank
	public Complaint getComplaints() {
		return this.complaints;
	}

	public void setComplaints(final Complaint complaints) {
		this.complaints = complaints;
	}

	@NotBlank
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}
	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}
	@URL
	public Collection<String> getAttachments() {
		return this.attachments;
	}

	public void setAttachments(final Collection<String> attachments) {
		this.attachments = attachments;
	}

	public Collection<Note> getNotes() {
		return this.notes;
	}

	public void setNotes(final Collection<Note> notes) {
		this.notes = notes;
	}

}
