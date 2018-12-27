
package converters;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.EndorserRecRepository;
import domain.EndorserRecord;

@Component
@Transactional
public class StringToEndorserRecConverter implements Converter<String, EndorserRecord> {

	@Autowired
	EndorserRecRepository	endorserRepository;


	@Override
	public EndorserRecord convert(final String text) {
		EndorserRecord result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.endorserRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
