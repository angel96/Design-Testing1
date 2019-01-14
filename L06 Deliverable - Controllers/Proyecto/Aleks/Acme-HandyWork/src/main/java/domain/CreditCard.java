
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
@Access(AccessType.PROPERTY)
public class CreditCard {

	private String	holderName;
	private String	brandName;
	private String	number;
	private Date	expiration;
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
	public String getNumber() {
		return this.number;
	}

	public void setNumber(final String number) {
		String s;
		s = number.replaceAll("[^a-zA-Z0-9]", "");
		if (Long.valueOf(s) instanceof Long)
			this.number = number;
		else
			throw new IllegalArgumentException("Invalid Number");

	}
	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	public Date getExpiration() {
		return this.expiration;
	}

	public void setExpiration(final Date expiration) {
		this.expiration = expiration;
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
