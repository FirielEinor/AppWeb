package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RendreDoc extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		
		out.print("<form action= http://localhost:8080/ProjectWebJava/confirmRendre>");
		out.println("Num document");
		out.println("<input name='idDoc' type='text'>");
		out.println("<input type=submit>");
		out.println("</form>");
	}
}
