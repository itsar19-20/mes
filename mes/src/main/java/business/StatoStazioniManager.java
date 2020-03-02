package business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import business.LineaManager; 

import model.LineaDiProduzione;
import model.SegnaleStazione;
import model.StatiLinea;
import model.StatoLinea;
import model.StatoStazione;
import model.Stazione;

import utils.JPAUtil;

public class StatoStazioniManager {
	
	private static final int TIMER_REFRESH_STATO_STAZIONE = 10000*10; // 10 secondi


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

	public void avviaTimerLetturaStazioni( LineaDiProduzione linea) {
		
		//TODO: fare in modo di poter stoppare il timer
		//TODO: fare in modo che rimanga sempre un thread attivo con il timer
		
		/*
		
		while ( linea.getUltimoStato() == StatiLinea.avviata) {
			
			for ( Stazione st : linea.getStazioni()) {
			
					EntityManager em = JPAUtil.getInstance().getEntityManagerFactory().createEntityManager();
					StatoStazione ss = leggiStatoStazione(st);
					em.getTransaction().begin();
					em.persist(ss);
					em.getTransaction().commit();
					em.close();
				
					//utilizzo una istanza di LineaManager per segnalare un errore sulla linea 
					
					if( ss.getStatoSegnale() == SegnaleStazione.anomalia ) {
						
						LineaManager lm = LineaManager.getInstance(em); 
						lm.inErrore( linea);
					}

			}
			
			try {
				Thread.sleep(TIMER_REFRESH_STATO_STAZIONE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}*/
	}
	
	/* suggerimento del professore
	 * 
	public void avviaTimerLetturaStatoStazione(Stazione st) {
		//TODO: fare in modo di poter stoppare il timer
		//TODO: fare in modo che rimanga sempre un thread attivo con il timer
		while (timerStazione.get(st)) {
			EntityManager em = JPAUtil.getInstance().getEntityManagerFactory().createEntityManager();
			StatoStazione ss = leggiStatoStazione(st);
			em.getTransaction().begin();
			em.persist(ss);
			em.getTransaction().commit();
			em.close();
			try {
				Thread.sleep(TIMER_REFRESH_STATO_STAZIONE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}*/
	
	public void memorizzaStatoStazione( StatoStazione stato) {
		
		this.entityManager.getTransaction().begin();
    	this.entityManager.persist(stato); 
    	this.entityManager.getTransaction().commit();
	}
	
	public List<StatoStazione> leggiStatoStazioni( String codiceLinea) {
		
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
	}
	
	public StatoStazione leggiStatoStazione( String codiceStazione) {
		
		String queryString = "SELECT s FROM StatoStazione s WHERE s.stazione.codiceStazione =:id ORDER BY s.TimeStamp DESC"; 
		
		//JPQL
		List<StatoStazione> result = this.entityManager.createQuery( queryString, StatoStazione.class)
									 .setParameter( "id", codiceStazione)
									 .setMaxResults(1)
									 .getResultList(); 
		
		if( result.isEmpty() ) {
			
			return null; 
		}
		
		return result.get(0); 
	}
	
	public List<String> leggiCodiciStazioni( String codiceLinea){
			
		String queryString = "SELECT s.codiceStazione FROM Stazione s WHERE s.linea.codiceLinea =:id"; 
		
		//JPQL
		List<String> result = this.entityManager.createQuery( queryString, String.class)
									 .setParameter( "id", codiceLinea)
									 .getResultList(); 
		
		if( result.isEmpty() ) {
			
			return null; 
		}
		
		return result; 
	}
}
