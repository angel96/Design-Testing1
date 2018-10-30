
package domain;

import javax.persistence.Entity;

@Entity
public class Finder extends DomainEntity {

	private Filter	filter;
	private Stock	stock;


	public Filter getFilter() {
		return this.filter;
	}

	public void setFilter(final Filter filter) {
		this.filter = filter;
	}

	public Stock getStock() {
		return this.stock;
	}

	public void setStock(final Stock stock) {
		this.stock = stock;
	}

}
