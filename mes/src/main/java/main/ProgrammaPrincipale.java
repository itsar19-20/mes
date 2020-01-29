package main;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import model.LineaDiProduzione;
import model.Stazione;
import model.Utente;
import utils.JPAUtil; 

public class ProgrammaPrincipale {

	public static void main(String[] args) {

		// creo un entity manager
		EntityManager em = JPAUtil.getInstance().getEntityManagerFactory().createEntityManager();

		generaLinee(em); 
		
		generaUtenti(em); 
					
	}
	
	public static void generaUtenti( EntityManager em) {
		
				
				Utente utenteUno = new Utente("Giada Cosentino","password", "operatore");
				Utente utenteDue = new Utente("Daniele Tozzi", "password", "operatore");
				Utente utenteTre = new Utente("Davide de Cristofaro", "password","operatore");
				Utente utenteQuattro = new Utente("Giulio Cigno", "password", "operatore");
				
				em.getTransaction().begin();
				em.persist(utenteUno);
				em.persist(utenteDue);
				em.persist(utenteTre); 
				em.persist(utenteQuattro); 
				em.getTransaction().commit();
	}
	
	
	public static void generaLinee( EntityManager em) {
		
				
				//UNO
				LineaDiProduzione lineaUno = new LineaDiProduzione(); 
				lineaUno.setCodiceLinea("001");
				lineaUno.setNome("Stampaggio componenti plastiche");

				//dichiaro un elenco di stazioni
				List<Stazione> elencoStazioniUNO = new ArrayList<Stazione>();
				
				Stazione stazione00 = new Stazione( lineaUno, "versamento granuli plastici", "01_00");
				elencoStazioniUNO.add(stazione00); 
				
				Stazione stazione01 = new Stazione( lineaUno, "riscaldamento del polimero", "01_01");
				elencoStazioniUNO.add(stazione01);
				
				Stazione stazione02 = new Stazione( lineaUno, "impressione dello stampo", "01_02");
				elencoStazioniUNO.add(stazione02);
				
				Stazione stazione03 = new Stazione( lineaUno, "raffreddamento in vasca d'acqua", "01_03");
				elencoStazioniUNO.add(stazione03);
				
				Stazione stazione04 = new Stazione( lineaUno, "asciugatura", "01_04");
				elencoStazioniUNO.add(stazione04);
				
				Stazione stazione05 = new Stazione( lineaUno, "stoccaggio in ceste metalliche", "01_05");
				elencoStazioniUNO.add(stazione05);
				
				lineaUno.setStazioni( elencoStazioniUNO); 
				
				
				
				//DUE 
				LineaDiProduzione lineaDue = new LineaDiProduzione();
				lineaDue.setCodiceLinea("002");
				lineaDue.setNome("Assemblaggio delle parti");
				
				//dichiaro un elenco di stazioni
				List<Stazione> elencoStazioniDUE = new ArrayList<Stazione>();
				
				Stazione stazioneDue00 = new Stazione( lineaDue, "rimozione bave e posizionamento", "02_00");
				elencoStazioniDUE.add(stazioneDue00);
				
				Stazione stazioneDue01 = new Stazione( lineaDue, "applicazione viti", "02_01");
				elencoStazioniDUE.add(stazioneDue01);
				
				Stazione stazioneDue02 = new Stazione( lineaDue, "serraggio bulloni", "02_02");
				elencoStazioniDUE.add(stazioneDue02);
				
				Stazione stazioneDue03 = new Stazione( lineaDue, "applicazione sigillante adesivo", "02_03");
				elencoStazioniDUE.add(stazioneDue03);
				
				Stazione stazioneDue04 = new Stazione( lineaDue, "chiusura a pressione", "02_04");
				elencoStazioniDUE.add(stazioneDue04);
				
				Stazione stazioneDue05 = new Stazione( lineaDue, "verifica trafilatura aria", "02_05");
				elencoStazioniDUE.add(stazioneDue05);
				
				Stazione stazioneDue06 = new Stazione( lineaDue, "stoccaggio in ceste metalliche", "02_06");
				elencoStazioniDUE.add(stazioneDue06);
				
				lineaDue.setStazioni( elencoStazioniDUE);
				
				
				
				//TRE
				LineaDiProduzione lineaTre = new LineaDiProduzione();
				lineaTre.setCodiceLinea("003");
				lineaTre.setNome("Confezionamento");
				
				//dichiaro un elenco di stazioni
				List<Stazione> elencoStazioniTRE = new ArrayList<Stazione>();
				
				Stazione stazioneTre00 = new Stazione( lineaTre, "posizionamento degli oggetti", "03_00");
				elencoStazioniTRE.add(stazioneTre00);
				
				Stazione stazioneTre01 = new Stazione( lineaTre, "caricamento degli imballi", "03_01");
				elencoStazioniTRE.add(stazioneTre01);
				
				Stazione stazioneTre02 = new Stazione( lineaTre, "prima piegatura della scatola", "03_02");
				elencoStazioniTRE.add(stazioneTre02);
				
				Stazione stazioneTre03 = new Stazione( lineaTre, "posizionamento dell'oggetto nella base", "03_03");
				elencoStazioniTRE.add(stazioneTre03);
				
				Stazione stazioneTre04 = new Stazione( lineaTre, "stampa, piegatura e inserimento istruzioni", "03_04");
				elencoStazioniTRE.add(stazioneTre04);
				
				Stazione stazioneTre05 = new Stazione( lineaTre, "chiusura della scatola", "03_05");
				elencoStazioniTRE.add(stazioneTre05);
				
				Stazione stazioneTre06 = new Stazione( lineaTre, "posizionamento delle scatole in cartoni", "03_06");
				elencoStazioniTRE.add(stazioneTre06);
				
				Stazione stazioneTre07 = new Stazione( lineaTre, "posizionamento dei cartoni su pallet", "03_07");
				elencoStazioniTRE.add(stazioneTre07);
				
				Stazione stazioneTre08 = new Stazione( lineaTre, "uscita del pallet e invio al magazzino", "03_08");
				elencoStazioniTRE.add(stazioneTre08);
				
				lineaTre.setStazioni( elencoStazioniTRE); 
				
				
				// scrive sul database le linee 
				
				//inizio
				em.getTransaction().begin();
				
				//elabora...
				em.persist(lineaUno);
				em.persist(lineaDue);
				em.persist(lineaTre); 
				
				// scrive
				em.getTransaction().commit();
	}

}
