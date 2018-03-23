package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

import mediatheque.Utilisateur;

public class addUser extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    { 
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String message = "Dans une prochaine version !";
        
        out.println("<h1>"+message+"</h1>");
    }

}
