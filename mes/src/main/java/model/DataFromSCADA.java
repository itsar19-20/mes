package model;

import java.util.Map;

public class DataFromSCADA {

	
	private String endpointServizioRestSCADA; 

	/*
	 * methods
	 */
	
	public SegnaleStazione getSegnaleFromStazione( String codiceLinea, String codiceStazione) {
		
		return null;
	}
	
	public Map<String,SegnaleStazione> getSegnaleFromLinea( String codiceLinea) {
		
		return null;
	}
	
	/*
	 * getters and setters
	 */
	
	public String getEndpointServizioRestSCADA() {
		return endpointServizioRestSCADA;
	}

	public void setEndpointServizioRestSCADA(String endpointServizioRestSCADA) {
		this.endpointServizioRestSCADA = endpointServizioRestSCADA;
	}
}
