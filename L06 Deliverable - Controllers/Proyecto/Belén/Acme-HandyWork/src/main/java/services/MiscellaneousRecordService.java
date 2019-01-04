
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.MiscellaneousRecordRepository;
import domain.MiscellaneousRecord;

@Service
@Transactional
public class MiscellaneousRecordService {

	@Autowired
	private MiscellaneousRecordRepository	miscellaneousRepository;


	public Collection<MiscellaneousRecord> findAll() {
		return this.miscellaneousRepository.findAll();
	}

	public MiscellaneousRecord findOne(final int idMisce) {
		return this.miscellaneousRepository.findOne(idMisce);
	}
}
