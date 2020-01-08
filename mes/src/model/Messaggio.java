package model;

import javax.persistence.*;

@Entity
public class Messaggio {

	@Id
	private Date id;		//verificare se il tipo è appropriato
	private String testo;
        
	/*
	 * getter
	 */
	
	public String getTesto() {
	
		return this.testo;
	}
	
	public Date getId() {
	
		return this.id; 
	}

	/*
	 * setter
	 */
	
	public void setTesto(String testo) {
		this.testo = testo;
	}
	
}
