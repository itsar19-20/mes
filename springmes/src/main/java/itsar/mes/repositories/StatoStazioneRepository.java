package itsar.mes.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import itsar.mes.model.StatoStazione;

public interface StatoStazioneRepository extends CrudRepository<StatoStazione, Long> {
	
	StatoStazione findById(long id); 
}
