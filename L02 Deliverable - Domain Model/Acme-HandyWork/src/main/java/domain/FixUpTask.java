
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class FixUpTask extends DomainEntity {

	private String					ticker;
	private Date					moment;
	private String					descrition;
	private String					adress;
	private double					maximumPrice;
	private Date					start;
	private Date					end;
	private Collection<Workplan>	workplan;


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

	public Collection<Workplan> getWorkplan() {
		return this.workplan;
	}

	public void setWorkplan(final Collection<Workplan> workplan) {
		this.workplan = workplan;
	}
}
