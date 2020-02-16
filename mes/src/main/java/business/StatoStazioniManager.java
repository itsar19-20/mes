package business;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

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
	
	//TODO: cambiare visibilitÓ
	public void memorizzaStatoStazione( StatoStazione stato) {
		
		this.entityManager.getTransaction().begin();
    	this.entityManager.persist(stato); 
    	this.entityManager.getTransaction().commit();
	}
		
	
	public StatoStazione leggiStatoStazione( String codiceStazione) {
		
		
		// creo un entity manager
		EntityManager em = JPAUtil.getInstance().getEntityManagerFactory().createEntityManager();
		
		// JPQL
    	List<StatoStazione> result = em.createQuery("select s from StatoStazione s where s.stazione.codiceStazione = :id ORDER BY s.TimeStamp DESC", StatoStazione.class)
    							.setParameter("id", codiceStazione)
    							.getResultList();

    	StatoStazione _return = null; 
    	
    	if( ! result.isEmpty()) {
    		
    		_return = result.get(0); 
    	}
    	
    	return _return; 
	}

}
