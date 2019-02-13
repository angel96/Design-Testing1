
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.MesageRepository;
import domain.Mesage;

@Component
@Transactional
public class StringToMesageConverter implements Converter<String, Mesage> {

	@Autowired
	public MesageRepository	repositoryMesage;


	@Override
	public Mesage convert(final String source) {
		Mesage res;
		int id;
		try {
			if (StringUtils.isEmpty(source))
				res = null;
			else {
				id = Integer.valueOf(source);
				res = this.repositoryMesage.findOne(id);
			}
		} catch (final Exception oops) {
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}
