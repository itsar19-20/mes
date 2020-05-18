package itsar.mes.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
 
@Entity
public class StatoStazione {

	
	/*
	 * Attributes
	 */
	
	@Id
	@JsonProperty("id")
	private long id; 
	
	@CreationTimestamp
	@JsonProperty("timestamp")
	private Date timestamp;
	
	@JsonProperty("segnale")
	private SegnaleStazione statoSegnale;
	
	@JsonProperty("stazione")
	private String nomeStazione; 
	
	@JsonProperty("codice")
	private String codice; 
	
	
	/*
	 * constructor
	 */
	protected StatoStazione() {	}
	
	@JsonCreator
	public StatoStazione(		@JsonProperty(value="id") long id, 
								@JsonProperty(value="timestamp") Date timestamp, 
								@JsonProperty(value="segnale") SegnaleStazione statoSegnale, 
								@JsonProperty(value="stazione") String nomeStazione,
								@JsonProperty(value="codice") String codice	)
	{
		this.id = id; 
		this.statoSegnale = statoSegnale;
		this.nomeStazione = nomeStazione;
		this.codice = codice;
	}
	
	/*
	 * getters
	 */
	public Date getTimeStamp() {
		return this.timestamp;
	}
	
	public SegnaleStazione getStatoSegnale() {
		return this.statoSegnale;
	}
	
	public String getNomeStazione() {
		return nomeStazione;
	}
	
	public String getCodice() {
		return codice;
	}

	
	/*
	 * setters
	 */	
	public void setStatoSegnale(SegnaleStazione statoSegnale) {
		this.statoSegnale = statoSegnale;
	}

	public void setNomeStazione(String nomeStazione) {
		this.nomeStazione = nomeStazione;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	/*
	 * overrides
	 */
	@Override
	public String toString() {
		return "StatoStazione [id=" + id + ", timestamp=" + timestamp + ", statoSegnale=" + statoSegnale
				+ ", nomeStazione=" + nomeStazione + ", codice=" + codice + "]";
	}
}
