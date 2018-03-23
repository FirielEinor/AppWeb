package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatheque.Document;
import mediatheque.Mediatheque;
import persistantdata.MediathequeData;

public class EmprunterDoc extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		out.println("<html>");
		out.println("<body>");
		
			
			out.println("<h2>Choisissez un document</h2>");
			out.print("<form action= http://localhost:8080/ProjectWebJava/ConfirmEmprunt>");
			
			out.println("<select name ='idDoc'>");
			for (Document d : Mediatheque.getInstance().tousLesDocuments()){
				out.println("<option value ='"+d.getId()+"'>"+d.getTitre()+"</option>");
			}
			out.println("</select>");
			out.println("<input type=submit>");
			out.println("</form>");
    	
		
		
		
		out.println("</body>");
        out.println("</html>");
	}
}
