package mes.scada.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mes.scada.model.SegnaleStazione;
import mes.scada.model.StatoStazione;


@RestController
public class LineaController {
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static Logger log = LoggerFactory.getLogger(LineaController.class);
	
	private static Random rand = new Random(); 

	private static final StatoStazione libera = new StatoStazione( counter.incrementAndGet(), SegnaleStazione.libera); 
	
	private static List<StatoStazione> linea001 = Arrays.asList(libera, libera, libera, libera, libera, libera); 
	private static List<StatoStazione> linea002 = Arrays.asList(libera, libera, libera, libera, libera, libera, libera);
	private static List<StatoStazione> linea003 = Arrays.asList(libera, libera, libera, libera, libera, libera, libera, libera, libera); 
	
	private static List<String> statoLinea = Arrays.asList("ferma", "ferma","ferma","ferma"); 
	
	
	@GetMapping("/linea")
	public List<StatoStazione> getLinea( 	@RequestParam(value= "codice", defaultValue = "000") String codice){
		
		switch( codice ) {
		
		case "001":
			
			return linea001; 
			
		case "002":
			
			return linea002;
			
		case "003":
			
			return linea003; 
			
		default:
		
			log.debug("LineaController: getLinea(): unexpected parameter"); 
			return null; 
		}
	}
	
	
	@PostMapping("/aggiorna")
	public static void updateStatoLinea(	@RequestParam(value = "stato", defaultValue = "ferma") String stato,
								 			@RequestParam(value = "codice", defaultValue = "000") String codice )
	{
		switch( codice ) {
		
		case "001":
			
			statoLinea.set( 1, stato); 
			break;
			
		case "002":
			
			statoLinea.set( 2, stato);
			break;
			
		case "003":
			
			statoLinea.set( 3, stato); 
			break;
			
		default:
			log.debug("LineaController: updateStatoLinea(): unexpected parameter"); 
			break; 
		}
	}
	
	@GetMapping("/aggiorna")
	public void linea(@RequestParam(value="codice", defaultValue = "000") String codice){ 
		
		switch( codice ) {
		
		case "001": 
			
			updateLinea(codice);
			break;
			
		case "002":
			
			updateLinea(codice); 
			break; 
			
		case "003": 
			
			updateLinea(codice); 
			break; 
			
		default: 
			
			log.debug("LineaController: updateLinea(): unexpected parameter"); 
			break; 
		} 
	}
	
	private void updateLinea( String codice) {
		
		String stato = "ferma";
		List<StatoStazione> statoStazioni = new ArrayList<>(); 
		
		switch( codice ) {
		
		case "001":
			
			stato = statoLinea.get(1); 
			statoStazioni = linea001; 
			break;
			
		case "002":
			
			stato = statoLinea.get(2); 
			statoStazioni = linea002; 
			break;
			
		case "003":
			
			stato = statoLinea.get(3); 
			statoStazioni = linea003; 
			break;
			
		default:
			
			System.out.println("LineaController: updateLinea(): unexpected parameter"); 
			break; 
		}
		
		if( stato.equals("avviata")) {
			
			next(statoStazioni);
				
		}else if( stato.equals("ferma")) {
			
			for( int i=0; i < statoStazioni.size(); i++) {
				statoStazioni.set( i, libera);
			}
			
		}else if( stato.equals("inErrore")) {
			
			//nothing 
			
		}else if( stato.equals("inPausa")) {
			
			//nothing 
		}
	}
	
	private void next( List<StatoStazione> statoStazioni){ 
		
		for( int i=0; i < statoStazioni.size(); i++ ) {
			 
			StatoStazione nuovo = null;
			StatoStazione current = statoStazioni.get(i);
	
			//BASE
			if( i == 0 ) {
				
				switch(current.getStatoSegnale().toString()) {

				case "libera":
					nuovo = new StatoStazione(counter.incrementAndGet(), SegnaleStazione.oggetto); 
					statoStazioni.set(i, nuovo); 
					break; 
				case "oggetto":
					nuovo = new StatoStazione(counter.incrementAndGet(), SegnaleStazione.azione); 
					statoStazioni.set(i, nuovo); 
					break; 
				case "azione": 
					break; 
				case "anomalia":
					nuovo = new StatoStazione(counter.incrementAndGet(), SegnaleStazione.libera); 
					statoStazioni.set(i, nuovo);
					break;
					
				default:
					log.debug("LineaController: next(): anomalous signal"); 
					break; 
				}
			}
			
			//BIVIO
			if( current.getStatoSegnale().equals(SegnaleStazione.azione)) {
			
				int intero = rand.nextInt(10); 
				
				if( intero == 0 ) {
					
					nuovo = new StatoStazione(counter.incrementAndGet(), SegnaleStazione.anomalia); 
					statoStazioni.set(i, nuovo);
					
				} else {
				
					nuovo = new StatoStazione(counter.incrementAndGet(), SegnaleStazione.libera); 
					statoStazioni.set(i, nuovo); 
					
					//salta un indice...
					if( ++i < statoStazioni.size() ) {
						
						nuovo = new StatoStazione(counter.incrementAndGet(), SegnaleStazione.oggetto);
						statoStazioni.set( i, nuovo);	
					}
				}
			}
			
			
			//NORMALE
			if(  ! current.getStatoSegnale().equals(SegnaleStazione.azione) && i != 0 ) {
				
				switch(current.getStatoSegnale().toString()) {

				case "libera":
					nuovo = new StatoStazione(counter.incrementAndGet(), SegnaleStazione.libera); 
					statoStazioni.set(i, nuovo); 
					break; 
				case "oggetto":
					nuovo = new StatoStazione(counter.incrementAndGet(), SegnaleStazione.azione); 
					statoStazioni.set(i, nuovo); 
					break; 
				case "anomalia":
					nuovo = new StatoStazione(counter.incrementAndGet(), SegnaleStazione.libera); 
					statoStazioni.set(i, nuovo);
					break;
					
				default:
					break; 
				}
			}
		}
	}
}
