package itsar.mes.model;

public class Stazione {
	
	private Linea linea;
	
	private String nome;
	
	private String tagStazione;
	
	private int stato;

	public Linea getLinea() {
		return linea;
	}

	public void setLinea(Linea linea) {
		this.linea = linea;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTagStazione() {
		return tagStazione;
	}
	
	public String getTagCompleto() {
		return this.linea.getTag() + "." + tagStazione;
	}

	public void setTagStazione(String tag) {
		this.tagStazione = tag;
	}

	public int getStato() {
		return stato;
	}

	public void setStato(int stato) {
		this.stato = stato;
	}

}
