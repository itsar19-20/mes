package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Messaggio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;		//verificare se il tipo è appropriato
	private String testo;
        
	/*
	 * getter
	 */
	
	public String getTesto() {
	
		return this.testo;
	}
	
	public long getId() {
	
		return this.id; 
	}

	/*
	 * setter
	 */
	
	public void setTesto(String testo) {
		this.testo = testo;
	}
	
}
