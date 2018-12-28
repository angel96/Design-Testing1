
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.ProfessionalRecord;

@Component
@Transactional
public class ProfessionalRecToStringConverter implements Converter<ProfessionalRecord, String> {

	@Override
	public String convert(final ProfessionalRecord professional) {
		String result;
		if (professional == null)
			result = null;
		else
			result = String.valueOf(professional.getId());

		return result;
	}
}
