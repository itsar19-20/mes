package business;

import java.util.*;

import javax.persistence.EntityManager;

import model.LineaDiProduzione;
import model.StatiLinea;
import utils.JPAUtil;

/**
 * 
 */
public class LineaManager {

    /**
     * Default constructor
     */
    public LineaManager() {
    }

    /**
     * @param elenco
     */
    public void elencoLinee(Set<LineaDiProduzione> elenco) {
    	EntityManager em = JPAUtil.getInstance().getEntityManagerFactory().createEntityManager();
    	//TODO: implementare la ricerca delle linee nel db
    }

    /**
     * @param codiceLinea 
     * @return
     */
    public StatiLinea getStatoLinea(String codiceLinea) {
        // TODO implementare la logica di gestione dello stato della linea
        return null;
    }

    /**
     * @param codiceLinea
     */
    public void avvia(String codiceLinea) {
        // TODO implementare la logica di avvio di una linea
    }

}