package itsar.mes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

import itsar.mes.model.StatoStazione;
import itsar.mes.model.Utente;
import itsar.mes.repositories.StatoStazioneRepository;
import itsar.mes.repositories.UtenteRepository;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@EnableJpaRepositories("itsar.mes.repositories")
public class MesApplication {
	
	private static final Logger log = LoggerFactory.getLogger(MesApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(MesApplication.class, args);
	}
	
	@Bean(name = "webClient")
	public WebClient client() {
		
		return WebClient.create("http://localhost:9090"); 
	}
	
	@Bean
	public CommandLineRunner demo(UtenteRepository utenteRepository, StatoStazioneRepository statoStazioneRepository) {
	    return (args) -> {
	      // save a few users
		  utenteRepository.save(new Utente("Daniele", "123"));
	      utenteRepository.save(new Utente("Davide", "123"));
		  utenteRepository.save(new Utente("Giada", "123"));
	      utenteRepository.save(new Utente("Giulio", "123"));
	      
	      // fetch all users
	      log.info("Utente found with findAll():");
	      log.info("-------------------------------");
	      for (Utente utente : utenteRepository.findAll()) {
	        log.info( utente.toString());
	      }
	      log.info("");
	   
	      statoStazioneRepository.deleteAll();

	      // fetch all StatoStazione
	      log.info("StatoStazione found with findAll():");
	      log.info("-------------------------------");
	      for (StatoStazione stato : statoStazioneRepository.findAll()) {
	        log.info( stato.toString());
	      }
	      log.info("");
	      
	    };
	}
}
