package model;
import javax.persistence.*;

@Entity
public class Utente {
	
	/*
	 * default constructor
	 */
	public Utente() {
		
	}
	
	/*
	 * alternative constructor
	 */
	public Utente (String nome, String password, String ruolo) {
		
		this.nome = nome;
		this.password = password;
		this.ruolo = ruolo;
	}
	
	private String ruolo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String nome;
	private String password;
	private boolean attivo;

	
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
