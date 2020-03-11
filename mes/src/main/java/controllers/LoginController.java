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

@WebServlet("/login")
public class LoginController extends HttpServlet {
	
	private static Logger log = LoggerFactory.getLogger(LoginController.class);
	
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.debug("controllers: LoginController: doGET()");
		
		ResponseWriter respoWriter = new ResponseWriter(); 
		
		//restituisce la pagina di login
		respoWriter.write("./src/main/webapp/WEB-INF/login.html", response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.debug("controllers: LoginController: doPOST()");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username == null || password == null) {
			response.sendError(400, "username e password sono obbligatorie.");
			return;
		}
		AuthenticationManager auth = new AuthenticationManager();
		Utente user = auth.login(username, password);
		
		ObjectMapper om = new ObjectMapper();
		response.setContentType("application/json");

		String jsonUser = om.writeValueAsString(user);
		response.getWriter().append(jsonUser).close();
		
	}

}