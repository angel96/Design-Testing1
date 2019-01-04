
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.ProfessionalRecordRepository;
import domain.ProfessionalRecord;

@Service
@Transactional
public class ProfessionalRecordService {

	@Autowired
	private ProfessionalRecordRepository	professionalRecRepository;


	public Collection<ProfessionalRecord> findAll() {
		return this.professionalRecRepository.findAll();
	}

	public ProfessionalRecord findOne(final int idProfessional) {
		return this.professionalRecRepository.findOne(idProfessional);
	}
}
