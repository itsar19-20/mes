package itsar.mes.model;

import java.util.LinkedList;

public class Linea {
	
	private String nome;
	
	private String tag;
	
	private LinkedList<Stazione> stazioni;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public LinkedList<Stazione> getStazioni() {
		return stazioni;
	}

	public void setStazioni(LinkedList<Stazione> stazioni) {
		this.stazioni = stazioni;
	}
	
	

}
