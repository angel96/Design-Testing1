
package converters;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.MiscellaneousRepository;
import domain.MiscellaneousRecord;

@Component
@Transactional
public class StringToMiscellaneousConverter implements Converter<String, MiscellaneousRecord> {

	@Autowired
	MiscellaneousRepository	miscellaneousRepository;


	@Override
	public MiscellaneousRecord convert(final String text) {
		MiscellaneousRecord result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.miscellaneousRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
