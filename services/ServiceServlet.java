package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatheque.Mediatheque;
import mediatheque.Utilisateur;

public class ServiceServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws IOException, ServletException
	    { 
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

	        String login = request.getParameter("login");
	        String password = request.getParameter("password");
	        HttpSession session = request.getSession(true);
	        session.setAttribute("login",login);
			session.setAttribute("password",password);
	        
	        
	        System.out.println(login + " " + password + " affichage");
	        
	        Mediatheque media = Mediatheque.getInstance();
	        Utilisateur user = media.getUser(login, password);
	        
	        if (user==null){
	        	out.println("<h3>Login ou mot de passe incorrect</h3>");
	        	out.print("<h4>Cliquez <a href=http://localhost:8080/ProjectWebJava/login>ici</a> pour retourner à la page de connection </h4>");
	        }
	        else{
	        	System.out.println(user.getType());
	        	out.println("<h3>Bienvenue " + user.getLogin() + "</h3>");
	        	if (user.getType().equals("biblio")){
	        		out.println("<a href=\"http://localhost:8080/ProjectWebJava/addDoc\"><input type=button  value=\"ajouter un document\"/></a>");
	        	}
	        	else {
	        		out.println("<a href=\"http://localhost:8080/ProjectWebJava/rendre\"><input type=button  value=\"rendre document\"/></a>");
	        		out.println("<a href=\"http://localhost:8080/ProjectWebJava/emprunt\"><input type=button  value=\"emprunter document\"/></a>");
	        	}
	        }
	    }
}
