package model;

import javax.persistence.*;

@Entity
public class Prodotto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String nome;
	private String categoria;
	private float prezzo;
	
	//Getters e Setters
	//
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
}