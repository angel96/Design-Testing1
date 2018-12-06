package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import domain.HandyWorker;
import domain.Phase;

@Repository
public interface PhasesRepository extends JpaRepository<Phase, Integer> {
	
	@Query("select distinct h.phase from HandyWorker h join h.phase p where h.id = ?1")
	Collection<Phase> getPhasesByHandyWorker(int id);
	
	@Query("select h from HandyWorker h join h.phase p where p.id = ?1")
	HandyWorker getHandyWorkerByPhases(int id);
	
	
}
