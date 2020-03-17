package business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import business.LineaManager; 

import model.LineaDiProduzione;
import model.SegnaleStazione;
import model.StatiLinea;
import model.StatoLinea;
import model.StatoStazione;
import model.Stazione;

import utils.JPAUtil;

public class StatoStazioniManager {

	
	private static Logger log = LoggerFactory.getLogger(StatoStazioniManager.class);

	private static StatoStazioniManager instance;
	private EntityManager entityManager; 
	
	/*
	 * 	private default constructor
	 */
	private StatoStazioniManager() {
		
		EntityManager em = JPAUtil.getInstance().getEntityManagerFactory().createEntityManager();	
		this.entityManager = em; 
	}
	
	public static StatoStazioniManager getInstance() {
		
		if (instance == null)
			instance = new StatoStazioniManager();
		return instance;
	}
	
	public void memorizzaStatoStazione( StatoStazione stato) {
		
		
		try {
			
			this.entityManager.getTransaction().begin();
	    	this.entityManager.persist(stato); 
	    	this.entityManager.getTransaction().commit();
		
		}catch( Exception e ){
			
			log.debug("business: StatoStazioniManager: memorizzaStatoStazione(): error"); 
		}
	}
	
	public List<StatoStazione> leggiStatoStazioni( String codiceLinea) {
		
		
		try {
			
			StatoStazioniManager sm = StatoStazioniManager.getInstance(); 
			List<String> codiciStazione = sm.leggiCodiciStazioni(codiceLinea); 
			
			
			List<StatoStazione> result = new ArrayList<>();
			
			for( String codice : codiciStazione ) {
				
				StatoStazione stato = sm.leggiStatoStazione(codice);
			
				if( stato != null ) {
					
					result.add(stato); 
				}
			}
			
			if( result.isEmpty() ) {
				
				return null; 
			}
			
			return result; 
			
		}catch( Exception e) {
			
			log.debug("business: StatoStazioniManager: leggiStatoStazioni(): error");
			return null; 
		}
	}
	
	public StatoStazione leggiStatoStazione( String codiceStazione) {
		

		String queryString = "SELECT s FROM StatoStazione s WHERE s.stazione.codiceStazione =:id ORDER BY s.TimeStamp DESC"; 

		try {
		
			//JPQL
			List<StatoStazione> result = this.entityManager.createQuery( queryString, StatoStazione.class)
										 .setParameter( "id", codiceStazione)
										 .setMaxResults(1)
										 .getResultList(); 
			
			if( result.isEmpty() ) {
				
				return null; 
			}
			
			return result.get(0); 
	
		}catch( Exception e) {
			
			log.debug("business: StatoStazioniManager: leggiStatoStazione(): error");
			return null; 
		}
	}
	
	public List<String> leggiCodiciStazioni( String codiceLinea){
			
		String queryString = "SELECT s.codiceStazione FROM Stazione s WHERE s.linea.codiceLinea =:id"; 
		
		try {
			
			//JPQL
			List<String> result = this.entityManager.createQuery( queryString, String.class)
										 .setParameter( "id", codiceLinea)
										 .getResultList(); 
			
			if( result.isEmpty() ) {
				
				return null; 
			}
			
			return result; 
		
		}catch( Exception e) {
			
			log.debug("business: StatoStazioniManager: leggiCodiciStazioni():error");
			return null; 
		}
		
	}
}
