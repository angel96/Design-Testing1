
package domain;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class CustomisationSystem extends DomainEntity {

	private String	systemName;
	private String	banner;
	private String	message;


	@NotBlank
	public String getSystemName() {
		return this.systemName;
	}

	public void setSystemName(final String systemName) {
		this.systemName = systemName;
	}
	@NotBlank
	public String getBanner() {
		return this.banner;
	}

	public void setBanner(final String banner) {
		this.banner = banner;
	}
	@NotBlank
	public String getMessage() {
		return this.message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}
}
