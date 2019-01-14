
package converters;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.EndorsementRepository;
import domain.Endorsable;

@Component
@Transactional
public class StringToEndorsableConverter implements Converter<String, Endorsable> {

	@Autowired
	private EndorsementRepository	endorsementRepository;


	@Override
	public Endorsable convert(final String source) {
		Endorsable res;
		int id;
		try {
			if (StringUtils.isEmpty(source))
				res = null;
			else {
				id = Integer.valueOf(source);
				res = this.endorsementRepository.findEndorsableByUserAccountId(id);
			}
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}
		return res;
	}
}
