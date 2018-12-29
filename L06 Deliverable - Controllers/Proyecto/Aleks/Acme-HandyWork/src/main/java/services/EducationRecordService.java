
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.EducationRecordRepository;
import domain.EducationRecord;

@Service
@Transactional
public class EducationRecordService {

	@Autowired
	private EducationRecordRepository	educationRecRepository;


	public Collection<EducationRecord> findAll() {
		return this.educationRecRepository.findAll();
	}

	public EducationRecord findOne(final int idEducation) {
		return this.educationRecRepository.findOne(idEducation);
	}
}
