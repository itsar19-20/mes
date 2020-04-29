package itsar.mes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Utente {
	
		/*
		 * CONSTRUCTORS
		 */
		protected Utente() {}
	
		public Utente (String nome, String password) {
			
			this.nome = nome;
			this.password = password;
		}
		
		/*
		 * ATTRIBUTES
		 */
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@JsonProperty("id")
		private long id;	
		
		@JsonProperty("nome")
		private String nome;
		
		@JsonIgnore
		private String password;
		

		/*
		 *	GETTERS AND SETTERS
		 */
		
		public long getId() {
			return id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
		/*
		 * OVERRIDES
		 */
		@Override
		public String toString() {
			return "Utente [id=" + id + ", nome=" + nome + ", password=" + password + "]";
		}
}
