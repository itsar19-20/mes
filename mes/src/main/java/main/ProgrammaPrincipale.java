package main;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import model.LineaDiProduzione;
import model.Stazione;
import utils.JPAUtil; 

public class ProgrammaPrincipale {

	public static void main(String[] args) {

		EntityManager em = JPAUtil.getInstance().getEntityManagerFactory().createEntityManager();
		
		//UNO
		LineaDiProduzione lineaUno = new LineaDiProduzione(); 
		lineaUno.setNome("Stampaggio componenti plastiche");

		//dichiaro un elenco di stazioni
		List<Stazione> elencoStazioniUNO = new ArrayList<Stazione>();
		
		Stazione stazione00 = new Stazione( lineaUno, "versamento granuli plastici", "00");
		elencoStazioniUNO.add(stazione00); 
		
		Stazione stazione01 = new Stazione( lineaUno, "riscaldamento del polimero", "01");
		elencoStazioniUNO.add(stazione01);
		
		Stazione stazione02 = new Stazione( lineaUno, "impressione dello stampo", "02");
		elencoStazioniUNO.add(stazione02);
		
		Stazione stazione03 = new Stazione( lineaUno, "raffreddamento in vasca d'acqua", "03");
		elencoStazioniUNO.add(stazione03);
		
		Stazione stazione04 = new Stazione( lineaUno, "asciugatura", "04");
		elencoStazioniUNO.add(stazione04);
		
		Stazione stazione05 = new Stazione( lineaUno, "stoccaggio in ceste metalliche", "05");
		elencoStazioniUNO.add(stazione05);
		
		lineaUno.setStazioni( elencoStazioniUNO); 
		
		
		
		//DUE 
		LineaDiProduzione lineaDue = new LineaDiProduzione(); 
		lineaDue.setNome("Assemblaggio delle parti");
		
		//dichiaro un elenco di stazioni
		List<Stazione> elencoStazioniDUE = new ArrayList<Stazione>();
		
		Stazione stazioneDue00 = new Stazione( lineaDue, "rimozione bave e posizionamento", "00");
		elencoStazioniDUE.add(stazioneDue00);
		
		Stazione stazioneDue01 = new Stazione( lineaDue, "applicazione viti", "01");
		elencoStazioniDUE.add(stazioneDue01);
		
		Stazione stazioneDue02 = new Stazione( lineaDue, "serraggio bulloni", "02");
		elencoStazioniDUE.add(stazioneDue02);
		
		Stazione stazioneDue03 = new Stazione( lineaDue, "applicazione sigillante adesivo", "03");
		elencoStazioniDUE.add(stazioneDue03);
		
		Stazione stazioneDue04 = new Stazione( lineaDue, "chiusura a pressione", "04");
		elencoStazioniDUE.add(stazioneDue04);
		
		Stazione stazioneDue05 = new Stazione( lineaDue, "verifica trafilatura aria", "05");
		elencoStazioniDUE.add(stazioneDue05);
		
		Stazione stazioneDue06 = new Stazione( lineaDue, "stoccaggio in ceste metalliche", "06");
		elencoStazioniDUE.add(stazioneDue06);
		
		lineaDue.setStazioni( elencoStazioniDUE);
		
		
		
		//TRE
		LineaDiProduzione lineaTre = new LineaDiProduzione(); 
		lineaTre.setNome("Confezionamento");
		
		//dichiaro un elenco di stazioni
		List<Stazione> elencoStazioniTRE = new ArrayList<Stazione>();
		
		Stazione stazioneTre00 = new Stazione( lineaTre, "posizionamento degli oggetti", "00");
		elencoStazioniTRE.add(stazioneTre00);
		
		Stazione stazioneTre01 = new Stazione( lineaTre, "caricamento degli imballi", "01");
		elencoStazioniTRE.add(stazioneTre01);
		
		Stazione stazioneTre02 = new Stazione( lineaTre, "prima piegatura della scatola", "02");
		elencoStazioniTRE.add(stazioneTre02);
		
		Stazione stazioneTre03 = new Stazione( lineaTre, "posizionamento dell'oggetto nella base", "03");
		elencoStazioniTRE.add(stazioneTre03);
		
		Stazione stazioneTre04 = new Stazione( lineaTre, "stampa, piegatura e inserimento istruzioni", "04");
		elencoStazioniTRE.add(stazioneTre04);
		
		Stazione stazioneTre05 = new Stazione( lineaTre, "chiusura della scatola", "05");
		elencoStazioniTRE.add(stazioneTre05);
		
		Stazione stazioneTre06 = new Stazione( lineaTre, "posizionamento delle scatole in cartoni", "06");
		elencoStazioniTRE.add(stazioneTre06);
		
		Stazione stazioneTre07 = new Stazione( lineaTre, "posizionamento dei cartoni su pallet", "07");
		elencoStazioniTRE.add(stazioneTre07);
		
		Stazione stazioneTre08 = new Stazione( lineaTre, "uscita del pallet e invio al magazzino", "08");
		elencoStazioniTRE.add(stazioneTre08);
		
		lineaTre.setStazioni( elencoStazioniTRE); 
		
		
		em.getTransaction().begin();
		em.persist(lineaUno);
		em.persist(lineaDue);
		em.persist(lineaTre); 
		em.getTransaction().commit();
		
	}

}
