
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.BoxRepository;
import domain.Actor;

@Component
@Transactional
public class StringToActorConverter implements Converter<String, Actor> {

	@Autowired
	private BoxRepository	boxRepository;


	@Override
	public Actor convert(final String source) {
		Actor res;
		int id;
		try {
			if (StringUtils.isEmpty(source))
				res = null;
			else {
				id = Integer.valueOf(source);
				res = this.boxRepository.getActorById(id);
			}
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}
