package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
    	this.ultimoStato = null; 
    	
    }

    @ManyToOne
    private LineaDiProduzione linea;

    private String nome; 
    
    @Id
    private String codiceStazione;
    @OneToMany(mappedBy="stazione")
    private List<StatoStazione> statiStazione;

    
    private SegnaleStazione ultimoStato; 
	
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

	public SegnaleStazione getUltimoStato() {
		return ultimoStato;
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

	public void setUltimoStato(SegnaleStazione ultimoStato) {
		this.ultimoStato = ultimoStato;
	}

	public void setStatiStazione(List<StatoStazione> statiStazione) {
		this.statiStazione = statiStazione;
	}
    
}
