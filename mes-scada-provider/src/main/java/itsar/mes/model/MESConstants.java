package itsar.mes.model;

public class MESConstants {
	
	public static final String[] LINEE = {"stampaggio", "assemblaggio", "confezionamento"};
	
	public static final String[] STAZIONI_STAMPAGGIO = {"versamento", "riscaldamento", "impressione", "raffreddamento", "asciugatura", "stoccaggio"};
	public static final String[] STAZIONI_ASSEMBLAGGIO = {"bave", "viti", "serraggio", "sigillante", "chiusura", "verifica", "stoccaggio"};
	public static final String[] STAZIONI_CONFEZIONAMENTO = {"posizionamento", "imballi", "piegatura", "base", "stampa", "chiusura", "cartoni", "pallet", "uscita"};
	
	public static final int SEGNALE_LIBERO = 0;
	public static final int SEGNALE_PRESENZA = 1;
	public static final int SEGNALE_AZIONE = 2;
	public static final int SEGNALE_ANOMALIA = 9;

}
