
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.FixUpTask;

@Component
@Transactional
public class FixUpTaskToStringConverter implements Converter<FixUpTask, String> {

	@Override
	public String convert(final FixUpTask fix) {
		String result;
		if (fix == null)
			result = null;
		else
			result = String.valueOf(fix.getId());
		return result;
	}

}
