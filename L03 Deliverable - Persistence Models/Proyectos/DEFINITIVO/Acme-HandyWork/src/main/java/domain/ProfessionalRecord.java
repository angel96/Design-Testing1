
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
public class ProfessionalRecord extends DomainEntity {

	private String				companyName;
	private Date				startWorking;
	private Date				endWorking;
	private String				role;
	private String				attachment;
	private Collection<String>	comments;


	@NotBlank
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(final String companyName) {
		this.companyName = companyName;
	}
	@NotBlank
	public Date getStartWorking() {
		return this.startWorking;
	}

	public void setStartWorking(final Date startWorking) {
		this.startWorking = startWorking;
	}
	@NotBlank
	public Date getEndWorking() {
		return this.endWorking;
	}

	public void setEndWorking(final Date endWorking) {
		this.endWorking = endWorking;
	}
	@NotBlank
	public String getRole() {
		return this.role;
	}

	public void setRole(final String role) {
		this.role = role;
	}
	@URL
	public String getAttachment() {
		return this.attachment;
	}

	public void setAttachment(final String attachment) {
		this.attachment = attachment;
	}

	public Collection<String> getComments() {
		return this.comments;
	}

	public void setComments(final Collection<String> comments) {
		this.comments = comments;
	}

}
