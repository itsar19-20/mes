package model;

import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	public void setStatiLinea(List<StatoLinea> statiLinea) {
		this.statiLinea = statiLinea;
	}


	@Override
	public String toString() {
		return "LineaDiProduzione [nome=" + nome + ", codiceLinea=" + codiceLinea + "]";
	}
	
}
