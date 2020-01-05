package model;

import java.util.Date;

public class StatoLinea {

	private Date timestamp;
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
