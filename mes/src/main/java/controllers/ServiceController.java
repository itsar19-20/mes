package controllers;

import java.io.*;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import business.LineaManager;
import business.ResponseWriter;
import business.StatoStazioniManager;
import model.LineaDiProduzione;
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
		
		//reindirizza l'utente alla risorsa /index.html
		response.sendRedirect("/");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.debug("controllers.ServiceController: doPOST()");
		
		LineaManager lm = LineaManager.getInstance();	
		
		String codiceLinea = request.getParameter("id");
		
		response.setContentType("application/json");
		
		String snapshot = lm.getSnapshot(codiceLinea);
		
		response.getWriter().append(snapshot).close();
	}

}
