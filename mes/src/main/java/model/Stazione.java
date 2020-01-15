package model;

import javax.persistence.*;

@Entity
public class Stazione {

	/**
     * Default constructor
     */
    public Stazione() {

    }

    @ManyToOne
    private LineaDiProduzione linea;

    private String nome; 
    
    @Id
    private String codiceStazione;

    
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
    
}
