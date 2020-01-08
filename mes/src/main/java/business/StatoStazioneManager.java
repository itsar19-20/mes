package business;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import model.SegnaleStazione;
import model.StatoStazione;
import model.Stazione;
import utils.JPAUtil;

public class StatoStazioneManager {
	
	private static final int TIMER_REFRESH_STATO_STAZIONE = 10000; // 10 secondi

	private static StatoStazioneManager instance;
	
	private Map<Stazione, Boolean> timerStazione;
	/**
	 * 
	 */
	private StatoStazioneManager() {
		this.timerStazione = new HashMap<>();
	}
	
	public static StatoStazioneManager getInstance() {
		if (instance == null)
			instance = new StatoStazioneManager();
		return instance;
	}

	public void avviaTimerLetturaStatoStazione(Stazione st) {
		//TODO: fare in modo di poter stoppare il timer
		//TODO: fare in modo che rimanga sempre un thread attivo con il timer
		while (timerStazione.get(st)) {
			EntityManager em = JPAUtil.getInstance().getEntityManagerFactory().createEntityManager();
			StatoStazione ss = leggiStatoStazione(st);
			em.getTransaction().begin();
			em.persist(ss);
			em.getTransaction().commit();
			em.close();
			try {
				Thread.sleep(TIMER_REFRESH_STATO_STAZIONE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public StatoStazione leggiStatoStazione(Stazione st) {
		StatoStazione _return = new StatoStazione();
		// leggo da DataFromSCADA lo stato attale della stazione indicata
		// ??
		//TODO: imlemetare il client per leggere lo stato dal servizio web SCADA (per il prof.)
		_return.setStatoSegnale(SegnaleStazione.libera);
		_return.setStazione(st);
		_return.setTimeStamp(new Date());
		
		return _return;
	}

}
