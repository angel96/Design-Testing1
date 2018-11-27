
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.Length;
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
	public String getHolderName() {
		return this.holderName;
	}

	public void setHolderName(final String holderName) {
		this.holderName = holderName;
	}
	@NotBlank
	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandName(final String brandName) {
		this.brandName = brandName;
	}
	@Length(min = 1, max = 16)
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

	@Override
	public String toString() {
		return "CreditCard [holderName=" + this.holderName + ", brandName=" + this.brandName + ", number=" + this.number + ", expirationMonth=" + this.expirationMonth + ", expirationYear=" + this.expirationYear + ", codeCVV=" + this.codeCVV + ", type="
			+ this.type + "]";
	}

}
