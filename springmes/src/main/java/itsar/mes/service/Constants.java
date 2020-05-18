package itsar.mes.service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import itsar.mes.model.StatoLinea;

public class Constants {
	
	/*
	 * CONSTANTS
	 */
	public static final StatoLinea FERMA = new StatoLinea("ferma");
	public static final StatoLinea AVVIATA = new StatoLinea("avviata");
	public static final StatoLinea ERRORE = new StatoLinea("errore"); 
	public static final StatoLinea PAUSA = new StatoLinea("pausa"); 
	
	public static final List<String> NOME_STAZIONI_LINEA_UNO = Arrays.asList("versamento granuli plastici","riscaldamento del polimero","impressione dello stampo", "raffreddamento in vasca d'acqua", "asciugatura","stoccaggio in ceste metalliche");  
	public static final List<String> NOME_STAZIONI_LINEA_DUE = Arrays.asList("rimozione bave e posizionamento","applicazione viti","serraggio bulloni","applicazione sigillante adesivo","chiusura a pressione","verifica trafilatura aria","stoccaggio in ceste metalliche"); 
	public static final List<String> NOME_STAZIONI_LINEA_TRE = Arrays.asList("posizionamento degli oggetti","caricamento degli imballi","prima piegatura della scatola","posizionamento dell'oggetto nella base","stampa, piegatura e inserimento istruzioni","chiusura della scatola","posizionamento delle scatole in cartoni","posizionamento dei cartoni su pallet","uscita del pallet e invio al magazzino" ); 

	public static final List<String> CODICE_STAZIONI_LINEA_UNO = Arrays.asList("001:001","001:002","001:003","001:004","001:005","001:006");
	public static final List<String> CODICE_STAZIONI_LINEA_DUE = Arrays.asList("002:001","002:002","002:003","002:004","002:005","002:006","002:007");
	public static final List<String> CODICE_STAZIONI_LINEA_TRE = Arrays.asList("003:001","003:002","003:003","003:004","003:005","003:006","003:007","003:008","003:009");
	
	
	public static final AtomicLong counter = new AtomicLong();

}