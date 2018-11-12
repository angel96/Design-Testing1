
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class EducationRecord extends DomainEntity {

	private String				diplomaTitle;
	private Date				startStudies;
	private Date				endStudies;
	private String				institutionDiploma;
	private String				attachment;
	private Collection<String>	comments;


	@NotBlank
	public String getDiplomaTitle() {
		return this.diplomaTitle;
	}

	public void setDiplomaTitle(final String diplomaTitle) {
		this.diplomaTitle = diplomaTitle;
	}
	@NotBlank
	public Date getStartStudies() {
		return this.startStudies;
	}

	public void setStartStudies(final Date startStudies) {
		this.startStudies = startStudies;
	}
	@NotBlank
	public Date getEndStudies() {
		return this.endStudies;
	}

	public void setEndStudies(final Date endStudies) {
		this.endStudies = endStudies;
	}
	@NotBlank
	public String getInstitutionDiploma() {
		return this.institutionDiploma;
	}

	public void setInstitutionDiploma(final String institutionDiploma) {
		this.institutionDiploma = institutionDiploma;
	}
	@URL
	public String getAttachment() {
		return this.attachment;
	}

	public void setAttachment(final String attachment) {
		this.attachment = attachment;
	}
	@ElementCollection
	public Collection<String> getComments() {
		return this.comments;
	}

	public void setComments(final Collection<String> comments) {
		this.comments = comments;
	}

}
