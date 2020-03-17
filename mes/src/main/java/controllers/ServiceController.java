package controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import business.LineaManager;
import business.ResponseWriter;
import business.StatoStazioniManager;
import model.LineaDiProduzione;
import model.StatoLinea;
import model.StatoStazione;
import model.Stazione;
import utils.JPAUtil;

@WebServlet("/home")
public class ServiceController extends HttpServlet {
	
	private static Logger log = LoggerFactory.getLogger(ServiceController.class);
	
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceController() {
        super(); 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.debug("controllers.ServiceController: doGET()");
		
		try {
		
			//reindirizza l'utente alla risorsa /index.html
			response.sendRedirect("/");
		
		}catch( Exception e) {
			
			log.debug("controllers: ServiceController: doGet(): error");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.debug("controllers.ServiceController: doPOST()");
		
		String tipoRichiesta = request.getParameter("tipo");
		
		switch (tipoRichiesta) {

		case "richiestaLinea":
			
			String id = request.getParameter("id"); 
			richiediLinea( id, response); 
			break;
			
		case "richiestaStatoLinee":
			
			richiediStatoLinee( response); 
			break;
			
		default:
			break;
		}
	}
	
	private void richiediLinea( String codiceLinea, HttpServletResponse response) {
		
		LineaManager lm = LineaManager.getInstance(); 
		
		LineaDiProduzione linea = lm.getLinea(codiceLinea);
		
		ObjectMapper om = new ObjectMapper();
		String result = null; 
		try {
			
			result = om.writeValueAsString(linea);
			
			response.setContentType("application/json");
			response.getWriter().append(result).close();
		
		} catch (Exception e) {
		
			log.debug("business: LineaManager: richiediLinea(): error");
		}
	}
	
	private void richiediStatoLinee( HttpServletResponse response){
		
		LineaManager lm = LineaManager.getInstance(); 
		
		List<StatoLinea> stati = new ArrayList<>();
		
		StatoLinea uno = lm.getStatoLinea("001");
		StatoLinea due = lm.getStatoLinea("002");
		StatoLinea tre = lm.getStatoLinea("003");
		
		if( uno != null && due != null && tre != null) {
			
			stati.add(uno); 
			stati.add(due);
			stati.add(tre);
		
			ObjectMapper om = new ObjectMapper();
			
			try {
				
				String result = om.writeValueAsString(stati);
				response.getWriter().append(result).close();
				
			}catch( Exception e) {
				
				log.debug("business: LineaManager: richiediStatoLinee: error");
			}
		}
	}
}
