
package domain;

import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class CreditCard extends DomainEntity {

	private String	holderName;
	private String	brandName;
	private int		number;
	private Date	expirationMonth;
	private Date	expirationYear;
	private Integer	codeCVV;
	private String	type;


	@NotBlank
	public String getHoldeName() {
		return this.holderName;
	}

	public void setHoldeName(final String holdeName) {
		this.holderName = holdeName;
	}
	@NotBlank
	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandName(final String brandName) {
		this.brandName = brandName;
	}
	@NotBlank
	public int getNumber() {
		return this.number;
	}

	public void setNumber(final int number) {
		this.number = number;
	}

	public Date getExpirationMonth() {
		return this.expirationMonth;
	}

	public void setExpirationMonth(final Date expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public Date getExpirationYear() {
		return this.expirationYear;
	}

	public void setExpirationYear(final Date expirationYear) {
		this.expirationYear = expirationYear;
	}

	public Integer getCodeCVV() {
		return this.codeCVV;
	}

	public void setCodeCVV(final Integer codeCVV) {
		this.codeCVV = codeCVV;
	}

	public String getType() {
		return this.type;
	}

	public void setType(final String type) {
		this.type = type;
	}

}
