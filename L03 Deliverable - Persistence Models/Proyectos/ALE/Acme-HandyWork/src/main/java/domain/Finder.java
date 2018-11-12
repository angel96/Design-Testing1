
package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.Range;

@Entity
public class Finder extends DomainEntity {

	private String			singleKey;
	private String			category;
	private String			warranty;
	private Integer[]		rangeOfPrices;
	private Date[]			rangeofDate;
	private SearchResult	result;


	public String getSingleKey() {
		return this.singleKey;
	}

	public void setSingleKey(final String singleKey) {
		this.singleKey = singleKey;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(final String category) {
		this.category = category;
	}

	public String getWarranty() {
		return this.warranty;
	}

	public void setWarranty(final String warranty) {
		this.warranty = warranty;
	}
	@Range(min = 0, max = 200)
	public Integer[] getRangeOfPrices() {
		return this.rangeOfPrices;
	}

	public void setRangeOfPrices(final Integer[] rangeOfPrices) {
		this.rangeOfPrices = rangeOfPrices;
	}

	public Date[] getRangeofDate() {
		return this.rangeofDate;
	}

	public void setRangeofDate(final Date[] rangeofDate) {
		this.rangeofDate = rangeofDate;
	}
	@OneToOne(optional = false)
	public SearchResult getResult() {
		return this.result;
	}

	public void setResult(final SearchResult result) {
		this.result = result;
	}
}
