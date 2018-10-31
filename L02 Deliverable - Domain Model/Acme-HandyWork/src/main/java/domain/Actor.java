
package domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
public class Actor extends DomainEntity {

	private String				name;
	private String				middleName;
	private String				surname;
	private String				photo;
	private String				email;
	private String				phone;
	private String				adress;
	private Integer				numberProfiles;
	private Collection<Profile>	profiles;
	private Collection<Message>	message;
	private boolean				ban;


	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}
	@NotBlank
	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}
	@NotBlank
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}
	@NotBlank
	@URL
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(final String photo) {
		this.photo = photo;
	}
	@NotBlank
	@Email
	@Pattern.List({
		@Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"), @Pattern(regexp = "^[\\w\\s]+<[a-zA-Z0-9_!#$%&*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+>$"), @Pattern(regexp = "^[0-9a-zA-Z]([-.\\\\w]*[0-9a-zA-Z])+@$"),
		@Pattern(regexp = "[\\w\\s]+<[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@>$")
	})
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}
	@Pattern.List({
		@Pattern(regexp = "^\\+([1-9]|[1-9][0-9]|[1-9][0-9][0-9])\\s(\\(([1-9]|[1-9][0-9]|[1-9]|[1-9][1-9]\"{2}|9[0-8][0-9]|99[0-9]))\\)\\s\\d{4,9}$"), @Pattern(regexp = "^\\+([1-9]|[1-9][0-9]|[1-9][0-9][0-9])\\s\\d{4,9}$"),
		@Pattern(regexp = "^\\d{4,9}$")
	})
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public Integer getNumberProfiles() {
		return this.numberProfiles;
	}

	public void setNumberProfiles(final Integer numberProfiles) {
		this.numberProfiles = numberProfiles;
	}
	public Collection<Profile> getProfiles() {
		return this.profiles;
	}

	public void setProfiles(final Collection<Profile> profiles) {
		this.profiles = profiles;
	}

	public String getAdress() {
		return this.adress;
	}

	public void setAdress(final String adress) {
		this.adress = adress;
	}

	public Collection<Message> getMessage() {
		return this.message;
	}

	public void setMessage(final Collection<Message> message) {
		this.message = message;
	}

	@NotBlank
	public boolean isBan() {
		return this.ban;
	}

	public void setBan(final boolean ban) {
		this.ban = ban;
	}

	@Override
	public String toString() {
		return "Actor [name=" + this.name + ", middleName=" + this.middleName + ", surname=" + this.surname + ", photo=" + this.photo + ", email=" + this.email + ", phone=" + this.phone + ", numberProfiles=" + this.numberProfiles + "]";
	}
}
