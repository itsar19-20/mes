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
    public List<LineaDiProduzione> elencoLinee() {
    	
    	List<LineaDiProduzione> elenco = new ArrayList<>(); 
    	
    	//TODO: implementare la ricerca delle linee nel db
    	for( LineaDiProduzione linea : this.entityManager.createNamedQuery("LineaDiProduzione.findAll", LineaDiProduzione.class).getResultList()) {
    		
    		elenco.add( linea); 
    	
    	}
    	return elenco; 
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
    	StatoLinea stato = new StatoLinea( linea, StatiLinea.avviata );
    	//scrive lo stato corrente della linea sul database
    	this.memorizzaStatoLinea( stato);  
    	
    }

    /**
     * @param LineaDiProduzione
     */
    public void ferma( LineaDiProduzione linea) {
    	
    	linea.setUltimoStato( StatiLinea.ferma );
    	StatoLinea stato = new StatoLinea( linea, StatiLinea.ferma );
    	//scrive lo stato corrente della linea sul database
    	this.memorizzaStatoLinea( stato);  
    }
    
    /**
     * @param LineaDiProduzione
     */
    public void inErrore( LineaDiProduzione linea) {
    	
    	linea.setUltimoStato( StatiLinea.inErrore );
    	StatoLinea stato = new StatoLinea( linea, StatiLinea.inErrore );
    	//scrive lo stato corrente della linea sul database
    	this.memorizzaStatoLinea( stato);  
    }
    
    /**
     * @param LineaDiProduzione
     */
    public void inPausa( LineaDiProduzione linea) {
    	
    	linea.setUltimoStato( StatiLinea.inPausa );
    	StatoLinea stato = new StatoLinea( linea, StatiLinea.inPausa );
    	//scrive lo stato corrente della linea sul database
    	this.memorizzaStatoLinea( stato);
    }
    
    /**
     * @param LineaDiProduzione
     * @return
     */
    public StatiLinea getStatoLinea( LineaDiProduzione linea) {
        
        return linea.getUltimoStato();
    }

}