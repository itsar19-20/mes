package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Stazione {

	/**
     * Default constructor
     */
    public Stazione() {

    }
    
    /*
     * alternative constructor
     */
    public Stazione( LineaDiProduzione linea, String nome, String codiceStazione) {
    	
    	this.linea = linea;
    	this.nome = nome; 
    	this.codiceStazione = codiceStazione; 
    	
    }

    @ManyToOne
    @JsonIgnore
    private LineaDiProduzione linea;

    private String nome; 
    
    @Id
    private String codiceStazione;
    
    @OneToMany(mappedBy="stazione")
    @JsonIgnore
    private List<StatoStazione> statiStazione;
	
    /*
     * getters 
     */
    public LineaDiProduzione getLinea() {
		return linea;
	}

    public String getNome() {
		return nome;
	}
    
	public String getCodiceStazione() {
		return codiceStazione;
	}
	
	public List<StatoStazione> getStatiStazione() {
		return statiStazione;
	}

	/*
	 * setters
	 */
	public void setLinea(LineaDiProduzione linea) {
		this.linea = linea;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCodiceStazione(String codiceStazione) {
		this.codiceStazione = codiceStazione;
	}

	public void setStatiStazione(List<StatoStazione> statiStazione) {
		this.statiStazione = statiStazione;
	}
   
}
