
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.EndorserRecord;

@Component
@Transactional
public class EndorserRecToStringConverter implements Converter<EndorserRecord, String> {

	@Override
	public String convert(final EndorserRecord endorser) {
		String result;
		if (endorser == null)
			result = null;
		else
			result = String.valueOf(endorser.getId());

		return result;
	}
}
