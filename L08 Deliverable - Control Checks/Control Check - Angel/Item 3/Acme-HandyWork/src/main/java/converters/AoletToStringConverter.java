
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Aolet;

@Component
@Transactional
public class AoletToStringConverter implements Converter<Aolet, String> {

	@Override
	public String convert(final Aolet entity) {
		String result;

		if (entity == null)
			result = null;
		else
			result = String.valueOf(entity.getId());

		return result;
	}

}
