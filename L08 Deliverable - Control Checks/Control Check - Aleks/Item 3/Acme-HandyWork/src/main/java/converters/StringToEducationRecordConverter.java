
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.EducationRecordRepository;
import domain.EducationRecord;

@Component
@Transactional
public class StringToEducationRecordConverter implements Converter<String, EducationRecord> {

	@Autowired
	private EducationRecordRepository	educationRecordRepository;


	@Override
	public EducationRecord convert(final String source) {
		EducationRecord res;
		int id;
		try {
			if (StringUtils.isEmpty(source))
				res = null;
			else {
				id = Integer.valueOf(source);
				res = this.educationRecordRepository.findOne(id);
			}
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}
