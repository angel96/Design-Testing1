
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Actor extends DomainEntity {

	private String				name;
	private String				middleName;
	private String				surname;
	private String				photo;
	private String				email;
	private String				phone;
	private String				adress;
	private Integer				numberProfiles;
	private UserAccount			account;
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
	@Pattern(regexp = "^([a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+)|([\\w\\s]+<[a-zA-Z0-9_!#$%&*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+>+)|([0-9a-zA-Z]([-.\\\\w]*[0-9a-zA-Z])+@+(?=\\?))|([\\w\\s]+<[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@+>)$")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@Pattern(regexp = "^(\\+([1-9]|[1-9][0-9]|[1-9][0-9][0-9])\\s\\(([1-9]|[1-9][0-9]|[1-9][0-9][0-9])\\)\\s\\d{4,9})|(\\+([1-9]|[1-9][0-9]|[1-9][0-9][0-9])\\s\\d{4,9})|(\\d{4,9})$")
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
	@OneToMany
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
	@OneToMany
	public Collection<Message> getMessage() {
		return this.message;
	}

	public void setMessage(final Collection<Message> message) {
		this.message = message;
	}

	public boolean isBan() {
		return this.ban;
	}

	public void setBan(final boolean ban) {
		this.ban = ban;
	}
	@OneToOne
	public UserAccount getAccount() {
		return this.account;
	}

	public void setAccount(final UserAccount account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Actor [name=" + this.name + ", middleName=" + this.middleName + ", surname=" + this.surname + ", photo=" + this.photo + ", email=" + this.email + ", phone=" + this.phone + ", numberProfiles=" + this.numberProfiles + "]";
	}

}
