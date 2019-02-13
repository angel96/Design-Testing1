
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.WageRepository;
import domain.Wage;

@Component
@Transactional
public class StringToWageConverter implements Converter<String, Wage> {

	@Autowired
	private WageRepository	wageRepository;


	@Override
	public Wage convert(final String s) {
		final Wage result;
		final int id;
		try {
			if (StringUtils.isEmpty(s))
				result = null;
			else {
				id = Integer.valueOf(s);
				result = this.wageRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
