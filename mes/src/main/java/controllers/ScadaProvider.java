package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import business.LineaManager;
import business.ResponseWriter;
import model.LineaDiProduzione;
import model.SegnaleStazione;
import model.StatoStazione;
import model.Stazione;
import utils.JPAUtil;


@WebServlet("/scada")
public class ScadaProvider extends HttpServlet {
		

		private static Logger log = LoggerFactory.getLogger(ScadaProvider.class);
		private static Random rand = new Random(); 
		
		private static final long serialVersionUID = 1L;
	    
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public ScadaProvider() {
	    	super(); 
	    }

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			log.debug("controllers: ScadaProvider: doGET()");
		
			LineaManager lm = LineaManager.getInstance(); 
			
			String codiceLinea = request.getParameter("codiceLinea"); 
				
			List<StatoStazione> catena = new ArrayList<>(); 
			List<Stazione> elencoStazioni = lm.getAllStazioni(codiceLinea); 
			
			for( Stazione s : elencoStazioni ) {
			
				SegnaleStazione segnale = randomSegnaleStazione();  	
				StatoStazione stato = new StatoStazione( s,  segnale);
					
				catena.add(stato);
					
			}
			
			ObjectWriter ow = new ObjectMapper().writerWithDefaultPrettyPrinter();
			String json = ow.writeValueAsString( catena);
		
			response.setContentType("application/json");
			response.getWriter().append(json).close();
			
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			log.debug("controllers: ScadaProvider: doPOST()");
			this.doGet(request, response);
		}
		
		
		private SegnaleStazione randomSegnaleStazione() {
			
			log.debug("controllers: ScadaProvider: randomSegnaleStazione()");
			
			int intero = rand.nextInt(10); 
			
			if( intero < 5 ) {
				
				return SegnaleStazione.libera; 
				
			}else if( intero < 7 ) {
				
				return SegnaleStazione.oggetto; 
				
			}else if( intero < 9 ) {
				
				return SegnaleStazione.azione;
			
			}else if( intero < 10 ) {
				
				return SegnaleStazione.anomalia; 
			}
			
			return null;
					
		}
}
