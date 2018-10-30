
package domain;

import javax.persistence.Entity;

@Entity
public class Finder extends DomainEntity {

	private Filter			filter;
	private SearchResult	result;


	public Filter getFilter() {
		return this.filter;
	}

	public void setFilter(final Filter filter) {
		this.filter = filter;
	}

	public SearchResult getStock() {
		return this.result;
	}

	public void setStock(final SearchResult stock) {
		this.result = stock;
	}

}
