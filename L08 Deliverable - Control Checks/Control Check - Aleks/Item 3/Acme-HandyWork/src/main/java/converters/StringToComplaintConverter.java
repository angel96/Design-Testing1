
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.ComplaintRepository;
import domain.Complaint;

@Component
@Transactional
public class StringToComplaintConverter implements Converter<String, Complaint> {

	@Autowired
	private ComplaintRepository	complaintRepository;


	@Override
	public Complaint convert(final String source) {
		Complaint res;
		int id;
		try {
			if (StringUtils.isEmpty(source))
				res = null;
			else {
				id = Integer.valueOf(source);
				res = this.complaintRepository.findOne(id);
			}
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}
