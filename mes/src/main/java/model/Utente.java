package model;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Utente {
	
	/*
	 * default constructor
	 */
	public Utente() {
		
	}
	//TODO: pensare a come risolvere il problema dell'univocita' del nome utente (per l'autenticazione)
	/*
	 * alternative constructor
	 */
	public Utente (String nome, String password, String ruolo) {
		
		this.nome = nome;
		this.password = password;
		this.ruolo = ruolo;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String ruolo;
	
	private String nome;
	
	@JsonIgnore
	private String password;
	private boolean attivo;

	/*
	 * getters and setters
	 */
	
	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean isAttivo() {
		return attivo;
	}
		
	public void setAttivo(boolean attivo) {
			this.attivo = attivo;
	}
}
