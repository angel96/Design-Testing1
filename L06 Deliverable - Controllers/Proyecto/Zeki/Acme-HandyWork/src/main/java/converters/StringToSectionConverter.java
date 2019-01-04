
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.SectionRepository;
import domain.Section;

@Component
@Transactional
public class StringToSectionConverter implements Converter<String, Section> {

	@Autowired
	private SectionRepository	repositorySection;


	@Override
	public Section convert(final String source) {
		Section res;
		int id;
		try {
			if (StringUtils.isEmpty(source))
				res = null;
			else {
				id = Integer.valueOf(source);
				res = this.repositorySection.findOne(id);
			}
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}
		return res;
	}
}
