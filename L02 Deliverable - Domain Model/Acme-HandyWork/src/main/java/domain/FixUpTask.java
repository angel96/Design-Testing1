
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class FixUpTask extends DomainEntity {

	private String				ticker;
	private Date				moment;
	private String				descrition;
	private String				adress;
	private double				maximumPrice;
	private Date				start;
	private Date				end;
	private Collection<Phase>	phases;		//??
	private SearchResult		searchResult;	//??
	private Warranty			warranty;		//??


	@NotBlank
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}
	@NotBlank
	public Date getMoment() {
		return this.moment;
	}
	@NotBlank
	public void setMoment(final Date moment) {
		this.moment = moment;
	}
	@NotBlank
	public String getDescrition() {
		return this.descrition;
	}

	public void setDescrition(final String descrition) {
		this.descrition = descrition;
	}
	@NotBlank
	public String getAdress() {
		return this.adress;
	}

	public void setAdress(final String adress) {
		this.adress = adress;
	}
	@NotBlank
	public double getMaximumPrice() {
		return this.maximumPrice;
	}

	public void setMaximumPrice(final double maximumPrice) {
		this.maximumPrice = maximumPrice;
	}
	@NotBlank
	public Date getStart() {
		return this.start;
	}

	public void setStart(final Date start) {
		this.start = start;
	}
	@NotBlank
	public Date getEnd() {
		return this.end;
	}

	public void setEnd(final Date end) {
		this.end = end;
	}

	public SearchResult getSearchResult() {
		return this.searchResult;
	}

	public void setSearchResult(final SearchResult searchResult) {
		this.searchResult = searchResult;
	}

	public Warranty getWarranty() {
		return this.warranty;
	}

	public void setWarranty(final Warranty warranty) {
		this.warranty = warranty;
	}

	public Collection<Phase> getPhases() {
		return this.phases;
	}

	public void setPhases(final Collection<Phase> phases) {
		this.phases = phases;
	}
}
