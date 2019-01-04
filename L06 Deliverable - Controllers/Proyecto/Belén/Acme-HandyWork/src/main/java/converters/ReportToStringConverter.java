
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Report;

@Component
@Transactional
public class ReportToStringConverter implements Converter<Report, String> {

	@Override
	public String convert(final Report source) {
		String result;
		if (source == null)
			result = null;
		else
			result = String.valueOf(source.getId());
		return result;
	}
}
