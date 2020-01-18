package model;

import java.util.*;
import javax.persistence.*;

@Entity
@NamedQuery( name="LineaDiProduzione.findAll", query="SELECT linea FROM LineaDiProduzione linea")
public class LineaDiProduzione {

	/**
     * Default constructor
     */
    public LineaDiProduzione() {

    }

    
    private String nome;

    @OneToMany(mappedBy="linea", cascade = CascadeType.ALL)
    private List<Stazione> stazioni;

    @Id
    private String codiceLinea;
    @OneToMany(mappedBy="linea")
    private List<StatoLinea> statiLinea;

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

	public List<StatoLinea> getStatiLinea() {
		return statiLinea;
	}
	
	public StatiLinea getUltimoStato() {
		return this.ultimoStato;
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

	public void setStatiLinea(List<StatoLinea> statiLinea) {
		this.statiLinea = statiLinea;
	}

}
