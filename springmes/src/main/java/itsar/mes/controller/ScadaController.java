package itsar.mes.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import itsar.mes.model.SegnaleStazione;
import itsar.mes.model.StatoLinea;
import itsar.mes.model.StatoStazione;
import itsar.mes.repositories.StatoStazioneRepository;
import itsar.mes.repositories.UtenteRepository;
import itsar.mes.service.Constants;
import itsar.mes.service.UpdateManager;


@RestController
public class ScadaController {
	
	private static Logger log = LoggerFactory.getLogger(ScadaController.class);
	
	@Autowired
	private UpdateManager updateManager;
	
	/*
	 * REQUEST MAPPINGS
	 */
	
	@GetMapping("/linea")
	public List<StatoStazione> getLinea( @RequestParam(value= "codice", defaultValue = "000") String codice){
		
		switch( codice ) {
		
		case "001":
			
			return updateManager.getLinea(codice); 
			
		case "002":
			
			return updateManager.getLinea(codice); 
			
		case "003":
			
			return updateManager.getLinea(codice); 
			
		default:
		
			log.debug("ScadaController: getLinea(): unexpected parameter"); 
			return null; 
		}
	}
	
	@GetMapping("/linea/{codice}/stato")
	public StatoLinea getStatoLinea( @PathVariable(required = true) String codice ){
	
		return updateManager.getStatoLinea(codice);
	}
	
	@PostMapping("/linea/{codice}")
	public boolean updateStatoLinea( @PathVariable(required = true) String codice,
					    			 @RequestParam(value="stato", required = true) String stato){
		
		return updateManager.updateStatoLinea(codice, stato);
	}
}

