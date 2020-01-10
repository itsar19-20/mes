package main;

import model; 
import javax.persistence.*; 

public class ProgrammaPrincipale {

	public static void main(String[] args) {

		EntityManager em = JPAUtil.getInstance().getEntityManagerFactory().createEntityManager();
		
		LineaDiProduzione lineaUno = new LineaDiProduzione(); 
		lineaUno.nome = "Stampaggio componenti plastiche";
	
		LineaDiProduzione lineaDue = new LineaDiProduzione(); 
		lineaDue.nome = "Assemblaggio delle parti";
		
		LineaDiProduzione lineaTre = new LineaDiProduzione(); 
		lineaTre.nome = "Confezionamento";
		
		em.getTransaction().begin();
		em.persist(lineaUno);
		em.persist(lineaDue);
		em.persist(lineaTre); 
		em.getTransaction().commit();
		
	}

}
