
package domain;

import java.util.Collection;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
public class EndorserRecord extends DomainEntity {

	private String				fullNameEndorser;
	private String				email;
	private Integer				phone;
	private String				linkedin;
	private Collection<String>	comments;


	@NotBlank
	public String getFullNameEndorser() {
		return this.fullNameEndorser;
	}

	public void setFullNameEndorser(final String fullNameEndorser) {
		this.fullNameEndorser = fullNameEndorser;
	}
	@NotBlank
	@Email
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}
	@NotBlank
	public Integer getPhone() {
		return this.phone;
	}

	public void setPhone(final Integer phone) {
		this.phone = phone;
	}
	@NotBlank
	@URL
	public String getLinkedin() {
		return this.linkedin;
	}

	public void setLinkedin(final String linkedin) {
		this.linkedin = linkedin;
	}

	public Collection<String> getComments() {
		return this.comments;
	}

	public void setComments(final Collection<String> comments) {
		this.comments = comments;
	}

}
