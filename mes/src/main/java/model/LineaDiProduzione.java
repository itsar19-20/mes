package model;

import java.util.*;
import javax.persistence.*;

@Entity
public class LineaDiProduzione {

	/**
     * Default constructor
     */
    public LineaDiProduzione() {

    }

    
    private String nome;

    @OneToMany 
    private List<Stazione> stazioni;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String codiceLinea;

    private StatiLinea ultimoStato; 


	/**
     *	methods
     */
    public void aggiorna() {
        // lo aggiorna direttamente dal database??
    }


    /*
     * getters
     */
	public String getNome() {
		return nome;
	}

	public List<Stazione> getStazioni() {
		return stazioni;
	}

	public String getCodiceLinea() {
		return codiceLinea;
	}	

	public StatiLinea getUltimoStato() {
		return null;
	}


	/*
	 * setters
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setStazioni(List<Stazione> stazioni) {
		this.stazioni = stazioni;
	}

	public void setCodiceLinea(String codiceLinea) {
		this.codiceLinea = codiceLinea;
	}
	
	public void setUltimoStato(StatiLinea ultimoStato) {
		this.ultimoStato = ultimoStato;
	}

}
