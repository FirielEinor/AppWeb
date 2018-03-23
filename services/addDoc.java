package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatheque.Mediatheque;

public class addDoc extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		
		if(type == null){
			out.println("<html>");
        		out.println("<body>");
        			out.println("<h2>Choisissez un type de document</h2>");
        			out.println("<select name ='typeSelect'>");
        			out.println("<option value ='DVD'>DVD<option>");
        			out.println("<option value ='Livre'>Livre<option>");
        			out.println("</select>");
        			out.println("<a href=\"http://localhost:8080/ProjectWebJava/addDoc?type=" + request.getAttribute("type") +"\"><input type=button  value=\"ajouter un document\"/></a>");
            	out.println("</body>");
            out.println("</html>");
		}
		
	}
}
