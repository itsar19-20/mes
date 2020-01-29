package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import business.AuthenticationManager;
import model.Utente;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	
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
		response.sendRedirect("./html/login.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username == null || password == null) {
			response.sendError(400, "username e password sono obbligatorie.");
			return;
		}
		AuthenticationManager auth = new AuthenticationManager();
		Utente user = auth.login(username, password);
		
		request.getSession().setAttribute("user", user);
		ObjectMapper om = new ObjectMapper();
		response.setContentType("application/json");
<<<<<<< HEAD
		response.getWriter().append(om.writeValueAsString(user));
=======
		String jsonUser = om.writeValueAsString(u);
		System.out.println(jsonUser);
		response.getWriter().append(jsonUser).close();
>>>>>>> branch 'master' of https://github.com/itsar19-20/mes
	}

}
