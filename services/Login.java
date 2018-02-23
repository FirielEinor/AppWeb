package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {
	protected void doGet(HttpServletRequest request,
            HttpServletResponse response)  throws IOException, ServletException
	{
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        
        out.println("<body bgcolor=\"white\">");
        out.println("<h1>authentification réussi tester <a href ='http://localhost:8080/aviation/verif'>ici</a></h1>");
        out.println("</body>");
        out.println("</html>");
	}
}
