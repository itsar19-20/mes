package mes.scada.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatoStazione {
	
	
	private final long id; 
	private final Date timestamp;
	private SegnaleStazione statoSegnale;
	
	/*
	 * constructor
	 */
	public StatoStazione( long id, SegnaleStazione segnale) {
		
		this.id = id; 
		this.statoSegnale = segnale; 
		this.timestamp = new java.util.Date(); 
		
	}
	
	/*
	 * getters
	 */
	public Date getTimeStamp() {
		return this.timestamp;
	}
	
	public SegnaleStazione getStatoSegnale() {
		return this.statoSegnale;
	}
	
	/*
	 * setters
	 */	
	public void setStatoSegnale(SegnaleStazione statoSegnale) {
		this.statoSegnale = statoSegnale;
	}
	
}
