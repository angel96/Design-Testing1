
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Warranty;

@Component
@Transactional
public class WarrantyToStringConverter implements Converter<Warranty, String> {

	@Override
	public String convert(final Warranty source) {
		String result;
		if (source == null)
			result = null;
		else
			result = String.valueOf(source.getId());
		return result;
	}

}
