package itsar.mes.business;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import itsar.mes.model.Linea;
import itsar.mes.model.MESConstants;
import itsar.mes.model.Stazione;

public class StazioniManager {

	private static List<Linea> linee;

	public static List<Stazione> listStazioni() {
		if (linee == null) {
			linee = new ArrayList<>();
			Linea l = new Linea();
			l.setNome(MESConstants.LINEE[0]);
			l.setTag("01");
			linee.add(l);
			for (int i = 0; i < MESConstants.STAZIONI_STAMPAGGIO.length; i++) {
				Stazione st = new Stazione();
				st.setLinea(l);
				st.setNome(MESConstants.STAZIONI_STAMPAGGIO[i]);
				st.setTagStazione("0" + String.valueOf(i + 1));
				l.getStazioni().add(st);
			}
			l = new Linea();
			l.setNome(MESConstants.LINEE[1]);
			l.setTag("02");
			linee.add(l);
			for (int i = 0; i < MESConstants.STAZIONI_ASSEMBLAGGIO.length; i++) {
				Stazione st = new Stazione();
				st.setLinea(l);
				st.setNome(MESConstants.STAZIONI_ASSEMBLAGGIO[i]);
				st.setTagStazione("0" + String.valueOf(i + 1));
				l.getStazioni().add(st);
			}
			l = new Linea();
			l.setNome(MESConstants.LINEE[2]);
			l.setTag("03");
			linee.add(l);
			for (int i = 0; i < MESConstants.STAZIONI_CONFEZIONAMENTO.length; i++) {
				Stazione st = new Stazione();
				st.setLinea(l);
				st.setNome(MESConstants.STAZIONI_CONFEZIONAMENTO[i]);
				st.setTagStazione("0" + String.valueOf(i + 1));
				l.getStazioni().add(st);
			}
		}
		return linee.stream()
				.flatMap(l -> l.getStazioni().stream())
				.collect(Collectors.toList());
	}

}
