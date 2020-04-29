package itsar.mes.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import itsar.mes.model.SegnaleStazione;
import itsar.mes.model.StatoLinea;
import itsar.mes.model.StatoStazione;
import itsar.mes.repositories.StatoStazioneRepository;
import itsar.mes.repositories.UtenteRepository;


@RestController
public class ScadaController {
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static Logger log = LoggerFactory.getLogger(ScadaController.class);
	private static Random rand = new Random(); 
		
	private final StatoStazioneRepository repository; 
	
	/*
	 * CONSTANTS
	 */
	private static final StatoLinea FERMA = new StatoLinea("ferma");
	private static final StatoLinea AVVIATA = new StatoLinea("avviata");
	private static final StatoLinea ERRORE = new StatoLinea("errore"); 
	private static final StatoLinea PAUSA = new StatoLinea("pausa"); 
	
	private static final List<String> NOME_STAZIONI_LINEA_UNO = Arrays.asList("versamento granuli plastici","riscaldamento del polimero","impressione dello stampo", "raffreddamento in vasca d'acqua", "asciugatura","stoccaggio in ceste metalliche");  
	private static final List<String> NOME_STAZIONI_LINEA_DUE = Arrays.asList("rimozione bave e posizionamento","applicazione viti","serraggio bulloni","applicazione sigillante adesivo","chiusura a pressione","verifica trafilatura aria","stoccaggio in ceste metalliche"); 
	private static final List<String> NOME_STAZIONI_LINEA_TRE = Arrays.asList("posizionamento degli oggetti","caricamento degli imballi","prima piegatura della scatola","posizionamento dell'oggetto nella base","stampa, piegatura e inserimento istruzioni","chiusura della scatola","posizionamento delle scatole in cartoni","posizionamento dei cartoni su pallet","uscita del pallet e invio al magazzino" ); 

	private static final List<String> CODICE_STAZIONI_LINEA_UNO = Arrays.asList("001:001","001:002","001:003","001:004","001:005","001:006");
	private static final List<String> CODICE_STAZIONI_LINEA_DUE = Arrays.asList("002:001","002:002","002:003","002:004","002:005","002:006","002:007");
	private static final List<String> CODICE_STAZIONI_LINEA_TRE = Arrays.asList("003:001","003:002","003:003","003:004","003:005","003:006","003:007","003:008","003:009");
	
	/*
	 *  STATES
	 */
	private static List<StatoStazione> linea001 = init("001");  
	private static List<StatoStazione> linea002 = init("002"); 
	private static List<StatoStazione> linea003 = init("003");
	
	private static List<StatoLinea> statoLinee = Arrays.asList( FERMA, FERMA, FERMA, FERMA); //000, 001, 002, 003

	
	public ScadaController(  StatoStazioneRepository repository ) {
		
		this.repository = repository; 		
	}
	
	private static List<StatoStazione> init(String codice){
		
		List<StatoStazione> result = new ArrayList<>();
		
		switch( codice ) {
		
		case "001":
			
			for( int i=0; i < NOME_STAZIONI_LINEA_UNO.size(); i++ ) {
				StatoStazione stato = new StatoStazione( counter.incrementAndGet(), null, SegnaleStazione.libera, NOME_STAZIONI_LINEA_UNO.get(i), CODICE_STAZIONI_LINEA_UNO.get(i));
				result.add(stato);
			}
			break; 
		
		case "002":
		
			for( int i=0; i < NOME_STAZIONI_LINEA_DUE.size(); i++ ) {
				StatoStazione stato = new StatoStazione( counter.incrementAndGet(), null, SegnaleStazione.libera, NOME_STAZIONI_LINEA_DUE.get(i), CODICE_STAZIONI_LINEA_DUE.get(i));
				result.add(stato);
			}
			break; 
			
		case "003":
			
			for( int i=0; i < NOME_STAZIONI_LINEA_TRE.size(); i++ ) {
				StatoStazione stato = new StatoStazione( counter.incrementAndGet(), null, SegnaleStazione.libera, NOME_STAZIONI_LINEA_TRE.get(i), CODICE_STAZIONI_LINEA_TRE.get(i));
				result.add(stato);
			}
			break;
			
		default:
		}
		
		return result; 
	}
	
	@GetMapping("/linea")
	public List<StatoStazione> getLinea( @RequestParam(value= "codice", defaultValue = "000") String codice){
		
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
	
	@GetMapping("/statolinee")
	public List<StatoLinea> getStatoLinee(){
	
		return statoLinee;
	}
	
	
	@PostMapping("/linea")
	public static void updateStatoLinea(	@RequestParam(value = "stato", defaultValue = "ferma") String stato,
								 			@RequestParam(value = "codice", defaultValue = "000") String codice )
	{
		StatoLinea nuovoStato = new StatoLinea(stato); 
		
		switch( codice ) {
		
		case "001":
			
			statoLinee.set( 1, nuovoStato); 
			break;
			
		case "002":
			
			statoLinee.set( 2, nuovoStato);
			
			break;
			
		case "003":
			
			statoLinee.set( 3, nuovoStato); 
			break;
			
		default:
			log.debug("LineaController: updateStatoLinea(): unexpected parameter"); 
			break; 
		}
	}
	
	@PostMapping("/aggiorna")
	public void linea(@RequestParam(value="codice", defaultValue = "000") String codice){ 
		
		switch( codice ) {
		
		case "001": 

			saveEachState(codice);
			updateLinea(codice);
			break;
			
		case "002":
			
			saveEachState(codice);
			updateLinea(codice); 
			break; 
			
		case "003": 
			
			saveEachState(codice);
			updateLinea(codice);  
			break; 
			
		default: 
			
			log.debug("LineaController: updateLinea(): unexpected parameter"); 
			break; 
		} 
	}
	
	private void saveEachState( String codiceLinea ) {
		
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
	
	private void updateLinea( String codiceLinea) {
		
		String statoLinea;
		List<StatoStazione> statoStazioni; 
		List<String> nomeStazioni; 
		List<String> codiceStazioni;
		
		switch( codiceLinea ) {
		
		case "001":
			
			statoLinea = statoLinee.get(1).getStato(); 
			statoStazioni = linea001;
			nomeStazioni = NOME_STAZIONI_LINEA_UNO;
			codiceStazioni = CODICE_STAZIONI_LINEA_UNO;
			break;
			
		case "002":
			
			statoLinea = statoLinee.get(2).getStato(); 
			statoStazioni = linea002; 
			nomeStazioni = NOME_STAZIONI_LINEA_DUE;
			codiceStazioni = CODICE_STAZIONI_LINEA_DUE;
			break;
			
		case "003":
			
			statoLinea = statoLinee.get(3).getStato(); 
			statoStazioni = linea003; 
			nomeStazioni = NOME_STAZIONI_LINEA_TRE; 
			codiceStazioni = CODICE_STAZIONI_LINEA_TRE;
			break;
			
		default:
			
			System.out.println("LineaController: updateLinea(): unexpected parameter"); 
			return; 
		}
		
		if( statoLinea.equals(AVVIATA.getStato())) {
						
			next(statoStazioni);
				
		}else if( statoLinea.equals(FERMA.getStato())) {
						
			for( int i=0; i < statoStazioni.size() && i < nomeStazioni.size(); i++) {
	
				StatoStazione stato = new StatoStazione( counter.incrementAndGet(), null, SegnaleStazione.libera, nomeStazioni.get(i), codiceStazioni.get(i) ); 
				statoStazioni.set( i, stato);
			}
			
		}else if( statoLinea.equals(ERRORE.getStato())) {
			
			for( int i=0; i < statoStazioni.size() && i < nomeStazioni.size(); i++) {
				
				StatoStazione stato = new StatoStazione( counter.incrementAndGet(), null, statoStazioni.get(i).getStatoSegnale(), nomeStazioni.get(i), codiceStazioni.get(i) ); 
				statoStazioni.set( i, stato);
			}			
			
		}else if( statoLinea.equals(PAUSA.getStato())) {
			
			for( int i=0; i < statoStazioni.size() && i < nomeStazioni.size(); i++) {
				
				StatoStazione stato = new StatoStazione( counter.incrementAndGet(), null, statoStazioni.get(i).getStatoSegnale(), nomeStazioni.get(i), codiceStazioni.get(i) ); 
				statoStazioni.set( i, stato);
			}
		}
	}
	
	
	private void next( List<StatoStazione> statoStazioni){ 
		
		for( int i=0; i < statoStazioni.size(); i++ ) {
			 
			StatoStazione current = statoStazioni.get(i);
			
			//BASE
			if( i == 0 ) {
				
				base( statoStazioni, i); 
			}
			
			//BIVIO
			if( current.getStatoSegnale().equals(SegnaleStazione.azione)) {
			
				//jumps one loop
				i = bivio( statoStazioni, i); 
			}
			
			
			//NORMALE
			if(  ! current.getStatoSegnale().equals(SegnaleStazione.azione) && i != 0 ) {
				
				normale(statoStazioni, i);
			}
		}
	}
	
	private void base( List<StatoStazione> statoStazioni, int index ) {
		
		StatoStazione nuovo; 
		StatoStazione current = statoStazioni.get(index);
		
		switch(current.getStatoSegnale().toString()) {

		case "libera":
			nuovo = new StatoStazione(counter.incrementAndGet(), null, SegnaleStazione.oggetto, current.getNomeStazione(), current.getCodice());
			statoStazioni.set(index, nuovo); 
			break; 
		case "oggetto":
			nuovo = new StatoStazione(counter.incrementAndGet(), null, SegnaleStazione.azione, current.getNomeStazione(), current.getCodice()); 
			statoStazioni.set(index, nuovo); 
			break; 
		case "azione": 
			break; 
		case "anomalia":
			nuovo = new StatoStazione(counter.incrementAndGet(), null, SegnaleStazione.libera, current.getNomeStazione(), current.getCodice()); 
			statoStazioni.set(index, nuovo);
			break;
			
		default:
			log.debug("LineaController: next(): anomalous signal"); 
			break; 
		}
	}
	
	private int bivio( List<StatoStazione> statoStazioni, int index) {

		int intero = rand.nextInt(10); 
		
		StatoStazione nuovo; 
		StatoStazione current = statoStazioni.get(index);
		
		if( intero == 0 ) {
			
			nuovo = new StatoStazione(counter.incrementAndGet(), null, SegnaleStazione.anomalia, current.getNomeStazione(), current.getCodice());
			statoStazioni.set(index, nuovo);
			
		} else {
		
			nuovo = new StatoStazione(counter.incrementAndGet(), null, SegnaleStazione.libera, current.getNomeStazione(), current.getCodice()); 
			statoStazioni.set(index, nuovo); 
			
			//salta un indice...
			if( ++index < statoStazioni.size() ) {
				
				StatoStazione nextToCurrent = statoStazioni.get(index); //previously incremented
				
				nuovo = new StatoStazione(counter.incrementAndGet(), null, SegnaleStazione.oggetto, nextToCurrent.getNomeStazione(), current.getCodice());
				statoStazioni.set( index, nuovo);	
			}
		}
		
		return index; 
	}
	
	private void normale( List<StatoStazione> statoStazioni, int index) {
		
		StatoStazione nuovo; 
		StatoStazione current = statoStazioni.get(index);
		
		switch(current.getStatoSegnale().toString()) {

		case "libera":
			nuovo = new StatoStazione(counter.incrementAndGet(), null, SegnaleStazione.libera, current.getNomeStazione(), current.getCodice()); 
			statoStazioni.set(index, nuovo); 
			break; 
		case "oggetto":
			nuovo = new StatoStazione(counter.incrementAndGet(), null, SegnaleStazione.azione, current.getNomeStazione(), current.getCodice()); 
			statoStazioni.set(index, nuovo); 
			break; 
		case "anomalia":
			nuovo = new StatoStazione(counter.incrementAndGet(), null, SegnaleStazione.libera, current.getNomeStazione(), current.getCodice()); 
			statoStazioni.set(index, nuovo);
			break;
			
		default:
			break; 
		}
	}
}

