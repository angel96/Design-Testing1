
package converters;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.ProfessionalRecRepository;
import domain.ProfessionalRecord;

@Component
@Transactional
public class StringToProfessionalRecConverter implements Converter<String, ProfessionalRecord> {

	@Autowired
	ProfessionalRecRepository	professionalRepository;


	@Override
	public ProfessionalRecord convert(final String text) {
		ProfessionalRecord result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.professionalRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
