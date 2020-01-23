package itsar.mes.business;

import java.util.ArrayList;
import java.util.List;

import itsar.mes.model.Linea;
import itsar.mes.model.MESConstants;
import itsar.mes.model.Stazione;

public class StazioniManager {

	private static List<Stazione> stazioni;

	public static List<Stazione> listStazioni() {
		if (stazioni == null) {
			stazioni = new ArrayList<>();
			Linea l = new Linea();
			l.setNome(MESConstants.LINEE[0]);
			l.setTag("01");
			for (int i = 0; i < MESConstants.STAZIONI_STAMPAGGIO.length; i++) {
				Stazione st = new Stazione();
				st.setLinea(l);
				st.setNome(MESConstants.STAZIONI_STAMPAGGIO[i]);
				st.setTagStazione("0" + String.valueOf(i + 1));
				stazioni.add(st);
			}
			l = new Linea();
			l.setNome(MESConstants.LINEE[1]);
			l.setTag("02");
			for (int i = 0; i < MESConstants.STAZIONI_ASSEMBLAGGIO.length; i++) {
				Stazione st = new Stazione();
				st.setLinea(l);
				st.setNome(MESConstants.STAZIONI_ASSEMBLAGGIO[i]);
				st.setTagStazione("0" + String.valueOf(i + 1));
				stazioni.add(st);
			}
			l = new Linea();
			l.setNome(MESConstants.LINEE[2]);
			l.setTag("03");
			for (int i = 0; i < MESConstants.STAZIONI_ASSEMBLAGGIO.length; i++) {
				Stazione st = new Stazione();
				st.setLinea(l);
				st.setNome(MESConstants.STAZIONI_ASSEMBLAGGIO[i]);
				st.setTagStazione("0" + String.valueOf(i + 1));
				stazioni.add(st);
			}
		}
		return stazioni;
	}

}
