package main;

import javax.persistence.EntityManager;

import model.LineaDiProduzione;
import utils.JPAUtil; 

public class ProgrammaPrincipale {

	public static void main(String[] args) {

		EntityManager em = JPAUtil.getInstance().getEntityManagerFactory().createEntityManager();
		
		LineaDiProduzione lineaUno = new LineaDiProduzione(); 
		lineaUno.setNome("Stampaggio componenti plastiche");
	
		LineaDiProduzione lineaDue = new LineaDiProduzione(); 
		lineaDue.setNome("Assemblaggio delle parti");
		
		LineaDiProduzione lineaTre = new LineaDiProduzione(); 
		lineaTre.setNome("Confezionamento");
		
		em.getTransaction().begin();
		em.persist(lineaUno);
		em.persist(lineaDue);
		em.persist(lineaTre); 
		em.getTransaction().commit();
		
	}

}
