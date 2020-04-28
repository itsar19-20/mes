package itsar.mes.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import itsar.mes.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long> {

	Utente findById(long id);
	List<Utente> findByNome( String nome); 
}
