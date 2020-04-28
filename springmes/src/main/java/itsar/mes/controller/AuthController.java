package itsar.mes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import itsar.mes.model.Utente;
import itsar.mes.repositories.UtenteRepository;

@RestController
public class AuthController {
	
	private final UtenteRepository repository;

	public AuthController( UtenteRepository repository) {
		this.repository = repository; 
	}
	
	@PostMapping("/login")
	public Utente login(	@RequestParam(value = "nome") String nome,
							@RequestParam(value = "password") String password ) 
	{
		
		for( Utente utente : repository.findByNome(nome)) {
			
			if( utente.getPassword().equals(password)) {
				
				return utente;
			}
		}
		
		return null; 
	}
}
