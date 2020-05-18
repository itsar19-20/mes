package itsar.mes.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import itsar.mes.model.SegnaleStazione;
import itsar.mes.model.StatoLinea;
import itsar.mes.model.StatoStazione;
import itsar.mes.repositories.StatoStazioneRepository;
import itsar.mes.service.Constants;

@Service
public class UpdateManager {

	private static Logger log = LoggerFactory.getLogger(UpdateManager.class);
		
	@Autowired
	private StatoStazioneRepository repository; 
	
	@Autowired
	private LineaManager lineaManager; 
	
	/*
	 *  STATES
	 */
	private static List<StatoStazione> linea001 = init("001");  
	private static List<StatoStazione> linea002 = init("002"); 
	private static List<StatoStazione> linea003 = init("003");
	
	private static List<StatoLinea> statoLinea = Arrays.asList( Constants.FERMA, Constants.FERMA, Constants.FERMA, Constants.FERMA); //000, 001, 002, 003
	
	/*
	 * PUBLIC METHODS
	 * 
	 */
	public boolean updateStatoLinea( String codice, String stato ) {
		
		switch( codice ) {
		
		case "001":
			
			statoLinea.get(1).setStato(stato); 
			return true; 	
			
		case "002":
			
			statoLinea.get(2).setStato(stato); 
			return true; 	
		
		case "003":

			statoLinea.get(3).setStato(stato);
			return true; 	
			
		default:
			log.debug("UpdateManager: updateStatoLinea(): unexpected parameter");
			return false; 
		}
	}
	
	public List<StatoStazione> getLinea(String codice){
		
		switch( codice ) {
		
		case "001":
			return linea001; 

		case "002":
			return linea002; 
		
		case "003":
			return linea003; 
		
		default:
			log.debug("UpdateManager: getLinea(): unexpected parameter");
		}
		
		return null; 
	}
	
	public StatoLinea getStatoLinea(String codice) {

		switch( codice ) {
		
		case "001":
			return statoLinea.get(1); 

		case "002":
			return statoLinea.get(2); 
		
		case "003":
			return statoLinea.get(3); 
		
		default:
			log.debug("UpdateManager: getLinea(): unexpected parameter");
		}
		
		return null; 
	}
	
	/*
	 * SCHEDULED METHODS
	 */	
	@Async
	@Scheduled(fixedRate = 10000)
	public void aggiornaLinea001() {
	
		lineaManager.update("001", statoLinea.get(1), linea001); 
		save("001");

		lineaManager.updateStatoLineaOnError(statoLinea.get(1), linea001); 
	}
	
	@Async
	@Scheduled(fixedRate = 10000)
	public void aggiornaLinea002() {
	
		lineaManager.update("002", statoLinea.get(2), linea002); 
		save("002");

		lineaManager.updateStatoLineaOnError(statoLinea.get(2), linea002); 
	}
	
	@Async
	@Scheduled(fixedRate = 10000)
	public void aggiornaLinea003() {
	
		lineaManager.update("003", statoLinea.get(3), linea003);  
		save("003");
		
		lineaManager.updateStatoLineaOnError(statoLinea.get(3), linea003);
	}
	
	/*
	 * PRIVATE METHODS
	 */
	private static List<StatoStazione> init(String codice){
		
		List<StatoStazione> result = new ArrayList<>();
		
		switch( codice ) {
		
		case "001":
			
			for( int i=0; i < Constants.NOME_STAZIONI_LINEA_UNO.size(); i++ ) {
				StatoStazione stato = new StatoStazione( Constants.counter.incrementAndGet(), null, SegnaleStazione.libera, Constants.NOME_STAZIONI_LINEA_UNO.get(i), Constants.CODICE_STAZIONI_LINEA_UNO.get(i));
				result.add(stato);
			}
			break; 
		
		case "002":
		
			for( int i=0; i < Constants.NOME_STAZIONI_LINEA_DUE.size(); i++ ) {
				StatoStazione stato = new StatoStazione( Constants.counter.incrementAndGet(), null, SegnaleStazione.libera, Constants.NOME_STAZIONI_LINEA_DUE.get(i), Constants.CODICE_STAZIONI_LINEA_DUE.get(i));
				result.add(stato);
			}
			break; 
			
		case "003":
			
			for( int i=0; i < Constants.NOME_STAZIONI_LINEA_TRE.size(); i++ ) {
				StatoStazione stato = new StatoStazione( Constants.counter.incrementAndGet(), null, SegnaleStazione.libera, Constants.NOME_STAZIONI_LINEA_TRE.get(i), Constants.CODICE_STAZIONI_LINEA_TRE.get(i));
				result.add(stato);
			}
			break;
			
		default:
		}
		
		return result; 
	}
	
	private void save( String codiceLinea ) {
		
		switch( codiceLinea ) {
		
		case "001":
			for( StatoStazione stato : linea001 ) {
				repository.save(stato);
			}
			break; 
			
		case "002":
			for( StatoStazione stato : linea002 ) {
				repository.save(stato);
			}
			break;
			
		case "003":
			for( StatoStazione stato : linea003 ) {
				repository.save(stato);
			}
			break;
			
		default: 
			break; 
		}
	}
}
