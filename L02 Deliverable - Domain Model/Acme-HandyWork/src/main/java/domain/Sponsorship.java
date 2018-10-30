
package domain;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
public class Sponsorship extends DomainEntity {

	private String		urlBanner;
	private String		linktpage;
	private CreditCard	creditCard;
	private Tutorial	tutorial;	//?


	@URL
	@NotBlank
	public String getUrlBanner() {
		return this.urlBanner;
	}

	public void setUrlBanner(final String urlBanner) {
		this.urlBanner = urlBanner;
	}
	@URL
	@NotBlank
	public String getLinktpage() {
		return this.linktpage;
	}

	public void setLinktpage(final String linktpage) {
		this.linktpage = linktpage;
	}

	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public Tutorial getTutorial() {
		return this.tutorial;
	}

	public void setTutorial(final Tutorial tutorial) {
		this.tutorial = tutorial;
	}

}
