
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.ProfileRepository;
import domain.Profile;

@Component
@Transactional
public class StringToProfileConverter implements Converter<String, Profile> {

	@Autowired
	ProfileRepository	profileRepository;


	@Override
	public Profile convert(final String text) {
		Profile result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				System.out.println(id);
				result = this.profileRepository.findOne(id);
			}
			System.out.println(result);
		} catch (final Throwable opps) {
			throw new IllegalArgumentException(opps);
		}
		return result;
	}
}
