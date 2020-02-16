package business;

import java.util.*;

import javax.persistence.EntityManager;

import model.LineaDiProduzione;
import model.StatiLinea;
import model.StatoLinea;
import model.Stazione;
import model.Utente;
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
    
    	EntityManager em = JPAUtil.getInstance().getEntityManagerFactory().createEntityManager();
    	this.entityManager = em;
    }
    
    
    public static LineaManager getInstance() {
    	
    	if( instance == null)
			instance = new LineaManager();
    	
		return instance;
    }
    /**
     * @param elenco
     */
    public List<LineaDiProduzione> elencoLinee() {
    	
    	List<LineaDiProduzione> elenco = new ArrayList<>(); 
    	
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
        
    	StatoLinea stato = new StatoLinea( linea, StatiLinea.avviata );
    	//scrive lo stato corrente della linea sul database
    	this.memorizzaStatoLinea( stato);  
    	
    }

    /**
     * @param String
     * @return LineaDiProduzione
     */
    public LineaDiProduzione getLinea( String codiceLinea ){
    	
    	// JPQL
    	List<LineaDiProduzione> result = this.entityManager.createQuery("select l from LineaDiProduzione l where l.codiceLinea = :id", LineaDiProduzione.class)
    							.setParameter("id", codiceLinea)
    							.getResultList();

    	LineaDiProduzione _return = null; 
    	
    	if( ! result.isEmpty()) {
    		
    		_return = result.get(0); 
    	}
    	
    	return _return; 
    }
    
    /**
     * @param LineaDiProduzione
     * @return
     */
    public StatoLinea getStatoLinea( String codiceLinea) {
        
    	// JPQL
    	List<StatoLinea> result = this.entityManager.createQuery("select s from StatoLinea s where s.linea.codiceLinea = :id ORDER BY s.timestamp DESC", StatoLinea.class)
    							.setParameter("id", codiceLinea)
    							.getResultList();

    	StatoLinea _return = null; 
    	
    	if( ! result.isEmpty()) {
    		
    		_return = result.get(0); 
    	}
    	
    	return _return;
    }
    
    /**
     * @param LineaDiProduzione
     * @return Stazione
     */
    public Stazione getStazione( String codiceStazione) {
		
    	// JPQL
    	List<Stazione> result = this.entityManager.createQuery("select s from Stazione s where s.codiceStazione = :id", Stazione.class)
    							.setParameter("id", codiceStazione)
    							.getResultList();

    	Stazione _return = null; 
    	
    	if( ! result.isEmpty()) {
    		
    		_return = result.get(0); 
    	}
    	
    	return _return;
    }
    
    /**
     * @param LineaDiProduzione
     * @return
     */
    public void ferma( LineaDiProduzione linea) {
    	
    	StatoLinea stato = new StatoLinea( linea, StatiLinea.ferma );
    	//scrive lo stato corrente della linea sul database
    	this.memorizzaStatoLinea( stato);  
    }
    
    /**
     * @param LineaDiProduzione
     */
    public void inErrore( LineaDiProduzione linea) {
    	
    	StatoLinea stato = new StatoLinea( linea, StatiLinea.inErrore );
    	//scrive lo stato corrente della linea sul database
    	this.memorizzaStatoLinea( stato);  
    }
    
    /**
     * @param LineaDiProduzione
     */
    public void inPausa( LineaDiProduzione linea) {
    	
    	StatoLinea stato = new StatoLinea( linea, StatiLinea.inPausa );
    	//scrive lo stato corrente della linea sul database
    	this.memorizzaStatoLinea( stato);
    }
    
    

}