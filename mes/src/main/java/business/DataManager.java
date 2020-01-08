package business;

import java.util.*;
import model.StatoStazione;

public class DataManager {
	
	/*
	 * default constructor
	 */
	public DataManager() {
		
		// set end point to default... 
	}

	/*
	 * fields
	 */
	private String endpointServizioREST; // protocol SCADA

	/*
	 * methods
	 */
	public StatoStazione getSegnaleFromStazione( String codiceLinea, String codiceStazione) {
		
		return null; 
	}
	
	public List<StatoStazione> getSegnaleFromLinea( String codiceLinea){
		
		return null;
	}

	/*
	 * getters
	 */
	public String getEndpointServizioREST() {
		
		return endpointServizioREST;
	}
	
}
