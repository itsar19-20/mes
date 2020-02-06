package business;

import java.util.List;

import javax.persistence.EntityManager;

import model.Utente;
import utils.JPAUtil;

public class AuthenticationManager {

	public Utente login(String nome, String password) {

		Utente _return = null;
		EntityManager em = JPAUtil.getInstance().getEntityManagerFactory().createEntityManager();
		
		// JPQL
		List<Utente> utenti = em.createQuery("select u from Utente u where u.nome = :nome", Utente.class)
				.setParameter("nome", nome)
				.getResultList();
		System.out.println("trovati " + utenti.size() + " utenti con username #" + nome + "#");
		
		//sceglie sempre il primo utente nella lista
		if ( utenti.size() > 0 ) 
			_return = utenti.get(0);
		
		if (_return != null) {
			if (!password.contentEquals(_return.getPassword())) {
				_return = null;
			}
		}
		em.close();
		
		return _return;
	}
}
