package business;

import java.util.*;

import javax.persistence.EntityManager;

import model.LineaDiProduzione;
import model.StatiLinea;
import model.StatoLinea;
import utils.JPAUtil;

/**
 * 
 */
public class LineaManager {

	
    private static LineaManager instance;
    
    private EntityManager entityManager; 

    
    /**
     * Default constructor
     */
    private LineaManager() {
    	
    }
    
    /*
     * alternative constructor
     */
    private LineaManager( EntityManager em) {
    
    	this.entityManager = em; 
    }
    
    public static LineaManager getInstance( EntityManager em) {
    	
    	if (instance == null)
			instance = new LineaManager( em);
		return instance;
    }
    /**
     * @param elenco
     */
    public void elencoLinee(Set<LineaDiProduzione> elenco) {
    	EntityManager em = JPAUtil.getInstance().getEntityManagerFactory().createEntityManager();
    	//TODO: implementare la ricerca delle linee nel db
    }

    private void memorizzaStatoLinea( StatoLinea stato) {
		
    	this.entityManager.getTransaction().begin();
    	this.entityManager.persist(stato); 
    	this.entityManager.getTransaction().commit();
	}
    
    /**
     * @param LineaDiProduzione
     */
    public void avvia( LineaDiProduzione linea) {
        
    	linea.setUltimoStato( StatiLinea.avviata );
    	
    	StatoLinea stato = new StatoLinea( linea, StatiLinea.avviata);
    	
    	//scrive lo stato corrente della linea sul database
    	this.memorizzaStatoLinea( stato);  
    	
    }

    /**
     * @param LineaDiProduzione
     */
    public void ferma( LineaDiProduzione linea) {
    	// TODO implementare la logica 
    }
    
    /**
     * @param LineaDiProduzione
     */
    public void inErrore( LineaDiProduzione linea) {
    	// TODO implementare la logica di avvio di una linea
    }
    
    /**
     * @param LineaDiProduzione
     */
    public void inPausa( LineaDiProduzione linea) {
    	// TODO implementare la logica di avvio di una linea
    }
    
    /**
     * @param LineaDiProduzione
     * @return
     */
    public StatiLinea getStatoLinea( LineaDiProduzione linea) {
        
        return linea.getUltimoStato();
    }

}