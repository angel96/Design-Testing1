
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.TutorialRepository;
import domain.Tutorial;

@Component
@Transactional
public class StringToTutorialConverter implements Converter<String, Tutorial> {

	@Autowired
	private TutorialRepository	repositoryTutorial;


	@Override
	public Tutorial convert(final String source) {
		Tutorial res;
		int id;
		try {
			if (StringUtils.isEmpty(source))
				res = null;
			else {
				id = Integer.valueOf(source);
				res = this.repositoryTutorial.findOne(id);
			}
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}
		return res;
	}
}
