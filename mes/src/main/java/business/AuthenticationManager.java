package business;

import javax.persistence.EntityManager;

import model.Utente;
import utils.JPAUtil;

public class AuthenticationManager {
	
	public Utente login(String nome, String password) {
	
		Utente _return = null;
		EntityManager em = JPAUtil.getInstance().getEntityManagerFactory().createEntityManager();
		
		_return = em.find(Utente.class, nome);
		if (_return != null) {
			if (!password.contentEquals(_return.getPassword())) {
				_return = null;
			}
		} 
		em.close();
		return _return;
	}
}
