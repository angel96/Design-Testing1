
package domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

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
	@Pattern.List({
		@Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"), @Pattern(regexp = "^[\\w\\s]+<[a-zA-Z0-9_!#$%&`+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+>$"), @Pattern(regexp = "^[0-9a-zA-Z]([-.\\\\w]*[0-9a-zA-Z])+@$"),
		@Pattern(regexp = "[\\w\\s]+<[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@>$")
	})
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}
	@NotBlank
	@Pattern.List({
		@Pattern(regexp = "^\\+([1-9]|[1-9][0-9]|[1-9][0-9][0-9])\\s(\\(([1-9]|[1-9][0-9]|[1-9]|[1-9][1-9]\"{2}|9[0-8][0-9]|99[0-9]))\\)\\s\\d{4,9}$"), @Pattern(regexp = "^\\+([1-9]|[1-9][0-9]|[1-9][0-9][0-9])\\s\\d{4,9}$"),
		@Pattern(regexp = "^\\d{4,9}$")
	})
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
