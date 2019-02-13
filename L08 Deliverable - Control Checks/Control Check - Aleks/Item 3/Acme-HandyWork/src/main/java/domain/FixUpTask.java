
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

// @Indexed
@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class FixUpTask extends DomainEntity {

	private String					ticker;
	private Date					moment;
	private String					description;
	private String					address;
	private double					maximumPrice;
	private Date					start;
	private Date					end;
	private Collection<Phase>		phases;
	private Warranty				warranty;
	private Category				category;
	private Collection<Complaint>	complaint;
	private Collection<Application>	application;
	private Collection<Wage>		wages;


	@OneToMany
	public Collection<Wage> getWages() {
		return this.wages;
	}

	public void setWages(final Collection<Wage> wages) {
		this.wages = wages;
	}

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^\\d{4}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])-[\\w]{6}$")
	//	@Field
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}
	@NotBlank
	//	@Field
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}
	@NotBlank
	//	@Field
	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}
	@Digits(integer = 3, fraction = 2)
	public double getMaximumPrice() {
		return this.maximumPrice;
	}

	public void setMaximumPrice(final double maximumPrice) {
		this.maximumPrice = maximumPrice;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	public Date getStart() {
		return this.start;
	}

	public void setStart(final Date start) {
		this.start = start;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	public Date getEnd() {
		return this.end;
	}

	public void setEnd(final Date end) {
		this.end = end;
	}
	@OneToOne
	public Warranty getWarranty() {
		return this.warranty;
	}

	public void setWarranty(final Warranty warranty) {
		this.warranty = warranty;
	}

	@OneToMany
	public Collection<Phase> getPhases() {
		return this.phases;
	}

	public void setPhases(final Collection<Phase> phases) {
		this.phases = phases;
	}
	@OneToOne(cascade = {
		CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH
	})
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(final Category category) {
		this.category = category;
	}
	@OneToMany
	public Collection<Complaint> getComplaint() {
		return this.complaint;
	}

	public void setComplaint(final Collection<Complaint> complaint) {
		this.complaint = complaint;
	}

	@OneToMany(mappedBy = "fixUpTask")
	public Collection<Application> getApplication() {
		return this.application;
	}

	public void setApplication(final Collection<Application> application) {
		this.application = application;
	}

}
