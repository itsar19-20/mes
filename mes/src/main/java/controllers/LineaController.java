package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import business.AuthenticationManager;
import business.ResponseWriter;
import model.Utente;

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
		
		//restituisce la pagina di login
		respoWriter.write("C:\\Users\\user\\git\\mes_master\\mes\\src\\main\\webapp\\WEB-INF\\linea.html", response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.debug("controllers: LineaController: doPOST()");
	}

}