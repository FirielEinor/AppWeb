package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

import mediatheque.Utilisateur;

public class Login extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    { 
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String login = request.getParameter("login");
        String password = request.getParameter("mdp");
        String message = "";
        
        if (login != null && password != null) {
			Utilisateur utilisateur = new Utilisateur(login, password);
			HttpSession sessionUtilisateur = request.getSession(true);
			sessionUtilisateur.setAttribute("userId", utilisateur);
			message = "<h1>Vous vous êtes identifié avec le login : " + login + "</h1>";
		} else {
			message = "<h1>Veuillez saisir votre login et votre mot de passe</h1>";
		}
        out.println("<html>");
        	out.println("<body>");
        		out.println(message);
        		out.print("<form action= http://localhost:8080/ProjectWebJava/service ");
                out.print("login\" ");
                out.println("method=POST>");
                out.println("Login:");
                out.println("<input type=text size=20 name=login>");
                out.println("<br>");
                out.println("Password:");
                out.println("<input type=text size=20 name=password>");
                out.println("<br>");
                out.println("<input type=submit>");
                out.println("</form>");
        	out.println("</body>");
        out.println("</html>");
    }

}
