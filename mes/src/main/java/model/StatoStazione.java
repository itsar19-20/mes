package model;

import java.util.Date;
import javax.persistence.*;
 
@Entity
public class StatoStazione {

	
	/*
	 * default constructor
	 */
	protected StatoStazione() {

	}

	/*
	 * alternative constructor
	 */
	public StatoStazione( Stazione stazione, SegnaleStazione segnale) {
		
		this.stazione = stazione; 
		this.statoSegnale = segnale; 
		
		//genero il timestamp
		
		this.TimeStamp = new java.util.Date(); 
		
	}
	
	@Id
	private long id; 
	@Temporal(TemporalType.TIMESTAMP)
	private Date TimeStamp;
	@ManyToOne
	private Stazione stazione;
	private SegnaleStazione statoSegnale;
	
	/*
	 * getters
	 */
	public Date getTimeStamp() {
		return TimeStamp;
	}
	
	public Stazione getStazione() {
		return stazione;
	}
	
	public SegnaleStazione getStatoSegnale() {
		return statoSegnale;
	}
	
	/*
	 * setters
	 */
	
	public void setTimeStamp(Date timeStamp) {
		TimeStamp = timeStamp;
	}
	public void setStazione(Stazione stazione) {
		this.stazione = stazione;
	}
	
	public void setStatoSegnale(SegnaleStazione statoSegnale) {
		this.statoSegnale = statoSegnale;
	}
	
	
}
