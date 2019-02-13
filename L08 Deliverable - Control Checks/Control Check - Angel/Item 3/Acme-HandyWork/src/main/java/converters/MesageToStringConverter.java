
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Mesage;

@Component
@Transactional
public class MesageToStringConverter implements Converter<Mesage, String> {

	@Override
	public String convert(final Mesage source) {
		String result;

		if (source == null)
			result = null;
		else
			result = String.valueOf(source.getId());

		return result;
	}

}
