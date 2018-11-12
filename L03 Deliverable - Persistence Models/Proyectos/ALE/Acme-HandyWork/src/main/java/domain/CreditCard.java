
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Embeddable
@Access(AccessType.PROPERTY)
public class CreditCard {

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
	@NotBlank
	public Date getExpirationMonth() {
		return this.expirationMonth;
	}

	public void setExpirationMonth(final Date expirationMonth) {
		this.expirationMonth = expirationMonth;
	}
	@NotBlank
	public Date getExpirationYear() {
		return this.expirationYear;
	}

	public void setExpirationYear(final Date expirationYear) {
		this.expirationYear = expirationYear;
	}
	@NotBlank
	@Range(min = 100, max = 999)
	public Integer getCodeCVV() {
		return this.codeCVV;
	}

	public void setCodeCVV(final Integer codeCVV) {
		this.codeCVV = codeCVV;
	}
	@NotBlank
	public String getType() {
		return this.type;
	}

	public void setType(final String type) {
		this.type = type;
	}
}