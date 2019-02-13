
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.AoletRepository;
import domain.Aolet;

@Component
@Transactional
public class StringToAoletConverter implements Converter<String, Aolet> {

	@Autowired
	private AoletRepository	repository;


	@Override
	public Aolet convert(final String source) {
		Aolet res;
		int id;
		try {
			if (StringUtils.isEmpty(source))
				res = null;
			else {
				id = Integer.valueOf(source);
				res = this.repository.findOne(id);
			}
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}
