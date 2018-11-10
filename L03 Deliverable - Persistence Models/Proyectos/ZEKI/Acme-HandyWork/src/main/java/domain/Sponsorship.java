package domain;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;



import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Sponsorship extends DomainEntity {
	
	private String urlBanner;
	private String linkTPage;
	private CreditCard creditCard;
	private Sponsor sponsor;
	private Tutorial tutorial;


	@URL
	public String getUrlBanner() {
		return urlBanner;
	}
	
	public void setUrlBanner(String urlBanner) {
		this.urlBanner = urlBanner;
	}
	
	@URL
	public String getLinkTPage() {
		return linkTPage;
	}
	public void setLinkTPage(String linkTPage) {
		this.linkTPage = linkTPage;
	}

	@ManyToOne(optional=false)
	public Sponsor getSponsor() {
		return this.sponsor;
	}

	public void setSponsor(final Sponsor sponsor) {
		this.sponsor = sponsor;
	}

	@ManyToOne(optional=false)
	public Tutorial getTutorial() {
		return this.tutorial;
	}

	public void setTutorial(final Tutorial tutorial) {
		this.tutorial = tutorial;
	}
	//collection de 1 cosa se puede? porque otra forma seria 
	//one to one pero es una composicion y no se como

	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}


}
