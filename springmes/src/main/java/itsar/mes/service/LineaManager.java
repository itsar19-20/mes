package itsar.mes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import itsar.mes.model.SegnaleStazione;
import itsar.mes.model.StatoLinea;
import itsar.mes.model.StatoStazione;
import itsar.mes.service.Constants;

@Service
public class LineaManager {
	
	private static Logger log = LoggerFactory.getLogger(LineaManager.class);
	private static Random rand = new Random(); 
	
	/*
	 * PUBLIC METHODS
	 */
	public void updateStatoLineaOnError( StatoLinea stato, List<StatoStazione> linea )
	{
		String value = stato.getStato(); 
		
		if( ! value.equals(Constants.ERRORE.getStato())) {
			
			for( StatoStazione stazione : linea ) {
				
				String segnale = stazione.getStatoSegnale().toString(); 
				
				if( segnale.equals(SegnaleStazione.anomalia.toString()) ) {
		
					stato.setStato(Constants.ERRORE.getStato()); 
					break; 
				}
			}
		
		} else {
			
			stato.setStato(Constants.PAUSA.getStato());
		}
	}
	
	public void update( String codiceLinea, StatoLinea current, List<StatoStazione> elencoStati ) {
		
		String statoLinea;
		List<StatoStazione> statoStazioni; 
		List<String> nomeStazioni; 
		List<String> codiceStazioni;
		
		switch( codiceLinea ) {
		
		case "001":
			
			statoLinea = current.getStato();  
			statoStazioni = elencoStati;
			nomeStazioni = Constants.NOME_STAZIONI_LINEA_UNO;
			codiceStazioni = Constants.CODICE_STAZIONI_LINEA_UNO;
			break;
			
		case "002":
			
			statoLinea = current.getStato(); 
			statoStazioni = elencoStati; 
			nomeStazioni = Constants.NOME_STAZIONI_LINEA_DUE;
			codiceStazioni = Constants.CODICE_STAZIONI_LINEA_DUE;
			break;
			
		case "003":
			
			statoLinea = current.getStato(); 
			statoStazioni = elencoStati; 
			nomeStazioni = Constants.NOME_STAZIONI_LINEA_TRE; 
			codiceStazioni = Constants.CODICE_STAZIONI_LINEA_TRE;
			break;
			
		default:
			
			log.debug("LineaManager: updateLinea(): unexpected parameter"); 
			return; 
		}
		
		if( statoLinea.equals(Constants.AVVIATA.getStato())) {
						
			next(statoStazioni);
				
		}else if( statoLinea.equals(Constants.FERMA.getStato())) {
						
			for( int i=0; i < statoStazioni.size() && i < nomeStazioni.size(); i++) {
	
				StatoStazione stato = new StatoStazione( Constants.counter.incrementAndGet(), null, SegnaleStazione.libera, nomeStazioni.get(i), codiceStazioni.get(i) ); 
				statoStazioni.set( i, stato);
			}
			
		}else if( statoLinea.equals(Constants.ERRORE.getStato())) {
			
			for( int i=0; i < statoStazioni.size() && i < nomeStazioni.size(); i++) {
				
				StatoStazione stato = new StatoStazione( Constants.counter.incrementAndGet(), null, statoStazioni.get(i).getStatoSegnale(), nomeStazioni.get(i), codiceStazioni.get(i) ); 
				statoStazioni.set( i, stato);
			}			
			
		}else if( statoLinea.equals(Constants.PAUSA.getStato())) {
			
			for( int i=0; i < statoStazioni.size() && i < nomeStazioni.size(); i++) {
				
				StatoStazione stato = new StatoStazione( Constants.counter.incrementAndGet(), null, statoStazioni.get(i).getStatoSegnale(), nomeStazioni.get(i), codiceStazioni.get(i) ); 
				statoStazioni.set( i, stato);
			}
		}
	}
	
	
	/*
	 *  PRIVATE METHODS
	 *
	 */	
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
			nuovo = new StatoStazione(Constants.counter.incrementAndGet(), null, SegnaleStazione.oggetto, current.getNomeStazione(), current.getCodice());
			statoStazioni.set(index, nuovo); 
			break; 
		case "oggetto":
			nuovo = new StatoStazione(Constants.counter.incrementAndGet(), null, SegnaleStazione.azione, current.getNomeStazione(), current.getCodice()); 
			statoStazioni.set(index, nuovo); 
			break; 
		case "azione": 
			break; 
		case "anomalia":
			nuovo = new StatoStazione(Constants.counter.incrementAndGet(), null, SegnaleStazione.libera, current.getNomeStazione(), current.getCodice()); 
			statoStazioni.set(index, nuovo);
			break;
			
		default:
			log.debug("LineaManager: next(): parameter error"); 
			break; 
		}
	}
	
	private int bivio( List<StatoStazione> statoStazioni, int index) {

		int intero = rand.nextInt(10); 
		
		StatoStazione nuovo; 
		StatoStazione current = statoStazioni.get(index);
		
		if( intero == 0 ) {
			
			nuovo = new StatoStazione(Constants.counter.incrementAndGet(), null, SegnaleStazione.anomalia, current.getNomeStazione(), current.getCodice());
			statoStazioni.set(index, nuovo);
			
		} else {
		
			nuovo = new StatoStazione(Constants.counter.incrementAndGet(), null, SegnaleStazione.libera, current.getNomeStazione(), current.getCodice()); 
			statoStazioni.set(index, nuovo); 
			
			//salta un indice...
			if( ++index < statoStazioni.size() ) {
				
				StatoStazione nextToCurrent = statoStazioni.get(index); //previously incremented
				
				nuovo = new StatoStazione(Constants.counter.incrementAndGet(), null, SegnaleStazione.oggetto, nextToCurrent.getNomeStazione(), current.getCodice());
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
			nuovo = new StatoStazione(Constants.counter.incrementAndGet(), null, SegnaleStazione.libera, current.getNomeStazione(), current.getCodice()); 
			statoStazioni.set(index, nuovo); 
			break; 
		case "oggetto":
			nuovo = new StatoStazione(Constants.counter.incrementAndGet(), null, SegnaleStazione.azione, current.getNomeStazione(), current.getCodice()); 
			statoStazioni.set(index, nuovo); 
			break; 
		case "anomalia":
			nuovo = new StatoStazione(Constants.counter.incrementAndGet(), null, SegnaleStazione.libera, current.getNomeStazione(), current.getCodice()); 
			statoStazioni.set(index, nuovo);
			break;
			
		default:
			break; 
		}
	}

}
