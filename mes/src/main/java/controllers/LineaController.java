package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import business.LineaManager;
import business.RequestManager;
import business.ResponseWriter;
import business.StatoStazioniManager;
import model.LineaDiProduzione;
import model.StatoLinea;
import model.StatoStazione;

@WebServlet("/linea")
public class LineaController extends HttpServlet {
	
	private static Logger log = LoggerFactory.getLogger(LineaController.class);
	
	private static final long serialVersionUID = 1L; 
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LineaController() {
    	super(); 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.debug("controllers: LineaController: doGET()");
		
		ResponseWriter respoWriter = new ResponseWriter(); 
		
		try {
		
			//restituisce la pagina di login
			respoWriter.write("./src/main/webapp/WEB-INF/linea.html", response);
		
		} catch( Exception e) {
			
			log.debug("controllers: LineaController: doGET(): error");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.debug("controllers: LineaController: doPOST()");
		
		String tipoRichiesta = request.getParameter("tipo");
		
		switch (tipoRichiesta) {

		case "aggiornamento":
			
			String stopString = request.getParameter("stop"); 
			
			if( stopString.equals("false"))
				aggiornamento( request, response); 
			break;
			
		case "avviamento":
			
			avviamento( request, response); 
			break;

		case "stop":
			
			stop( request, response); 
			break; 
			
		case "pausa":
			
			pausa( request, response);
			break; 
			
		default:
			break;
		}
	}
	
	private void aggiornamento( HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			RequestManager rm = RequestManager.getInstance();
				
			String codiceLinea = request.getParameter("codiceLinea");
				
			String aggiornamento = rm.requestGET("http://localhost:8080/scada?codiceLinea=" + codiceLinea); 
				
			//append to response
			response.setContentType("application/json");
			response.getWriter().append(aggiornamento).close();
				
			//salvataggio nel DB
			ObjectMapper om = new ObjectMapper(); 
			List<StatoStazione> nuovoStatoStazioni = om.readValue(aggiornamento, om.getTypeFactory().constructCollectionType(List.class, StatoStazione.class));
				
			StatoStazioniManager sm = StatoStazioniManager.getInstance(); 
				
			for( StatoStazione stato : nuovoStatoStazioni ) {
					
				sm.memorizzaStatoStazione(stato);
			}
			
		}catch (IOException e) {
			
			log.debug("controllers: LineaController: aggiornamento(): IOException");
		}
		
	}
	
	private void avviamento( HttpServletRequest request, HttpServletResponse response) {
		
		String codiceLinea = request.getParameter("codiceLinea");
		
		LineaManager lm = LineaManager.getInstance();
		LineaDiProduzione linea = lm.getLinea( codiceLinea);
		
		//salvataggio nel DB
		lm.avvia(linea);
		
	}
	
	private void stop( HttpServletRequest request, HttpServletResponse response) {
		
		String codiceLinea = request.getParameter("codiceLinea");
		
		LineaManager lm = LineaManager.getInstance();
		LineaDiProduzione linea = lm.getLinea( codiceLinea);
		
		//salvataggio nel DB
		lm.ferma(linea);
		
	}

	private void pausa( HttpServletRequest request, HttpServletResponse response) {
		
		String codiceLinea = request.getParameter("codiceLinea");
		
		LineaManager lm = LineaManager.getInstance();
		LineaDiProduzione linea = lm.getLinea( codiceLinea);
		
		//salvataggio nel DB
		lm.inPausa(linea);
		
	}
}