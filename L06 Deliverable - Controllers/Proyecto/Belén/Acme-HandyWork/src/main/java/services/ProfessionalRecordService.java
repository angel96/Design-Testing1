
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.ProfessionalRecRepository;
import domain.ProfessionalRecord;

@Service
@Transactional
public class ProfessionalRecService {

	@Autowired
	private ProfessionalRecRepository	professionalRecRepository;


	public Collection<ProfessionalRecord> findAll() {
		return this.professionalRecRepository.findAll();
	}

	public ProfessionalRecord findOne(final int idProfessional) {
		return this.professionalRecRepository.findOne(idProfessional);
	}
}
