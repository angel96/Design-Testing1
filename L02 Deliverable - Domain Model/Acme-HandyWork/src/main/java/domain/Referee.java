
package domain;

import java.util.Collection;

import javax.persistence.Entity;

@Entity
public class Referee extends Actor {

	private Collection<Complaint>	complaints;


	//no deberia ser una collecion de report y ya gracias a eso llegas a los complaint??

	public Collection<Complaint> getComplaints() {
		return this.complaints;
	}

	public void setComplaints(final Collection<Complaint> complaints) {
		this.complaints = complaints;
	}
}
