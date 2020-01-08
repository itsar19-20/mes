package model;
import javax.persistence.*;

@Entity
public class Utente {
	
	private String ruolo;
	@Id
	private int id;
	private String nome;
	private String password;
	
	public Boolean verifica() {
		/*da implementare*/
		return null;
		
	}

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

}
