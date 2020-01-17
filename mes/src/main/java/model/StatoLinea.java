package model;

import java.util.Date;
import javax.persistence.*; 

@Entity
public class StatoLinea {
	
	/*
	 * default constructor
	 */
	public StatoLinea() {

	}

	/*
	 * alternative constructor
	 */
	public StatoLinea( LineaDiProduzione linea, StatiLinea statoLinea) {
		
		this.linea = linea; 
		this.statoLinea = statoLinea; 
		
		//genero il timestamp
		
		this.timestamp = new java.util.Date(); 
		
	}
	
	@Id
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	@ManyToOne
	private LineaDiProduzione linea;
	
	private StatiLinea statoLinea;
	
	/* 
	 * getters
	 */
	
	public Date getTimestamp() {
		return timestamp;
	}
	public LineaDiProduzione getLinea() {
		return linea;
	}
	public StatiLinea getStatoLinea() {
		return statoLinea;
	}
	
	/*
	 * setters
	 */
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	public void setLinea(LineaDiProduzione linea) {
		this.linea = linea;
	}
	
	public void setStatoLinea(StatiLinea statoLinea) {
		this.statoLinea = statoLinea;
	}
}
