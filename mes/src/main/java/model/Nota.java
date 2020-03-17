package model;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Nota {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	@Temporal(TemporalType.TIMESTAMP)
	private Date TimeStamp;
	@ManyToOne
	private Utente user;
	private String mail;
	private String testo;
	
	// Getters e Setters
	//
	public Date getTimeStamp() {
		return TimeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		TimeStamp = timeStamp;
	}
	public Utente getUser() {
		return user;
	}
	public void setUser(Utente user) {
		this.user = user;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}

	
}
