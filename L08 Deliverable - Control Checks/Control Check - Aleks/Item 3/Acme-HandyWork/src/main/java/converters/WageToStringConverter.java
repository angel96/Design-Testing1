
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Wage;

@Component
@Transactional
public class WageToStringConverter implements Converter<Wage, String> {

	@Override
	public String convert(final Wage w) {
		String result;
		if (w == null)
			result = null;
		else
			result = String.valueOf(w.getId());

		return result;
	}

}
