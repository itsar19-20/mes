package itsar.mes.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StatoLinea {

	@JsonProperty("stato")
	private String stato;
	
	@JsonIgnore
	private String bgcolor; 
	
	@JsonCreator
	public StatoLinea(@JsonProperty("value") String value) {
		
		this.stato = value; 
	}
	
	
	/*
	 * GETTERS
	 */
	
	public String getStato() {
		return stato;
	}

	public String getBgcolor() {
		return bgcolor;
	}

	/*
	 * SETTERS
	 */

	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}
}
