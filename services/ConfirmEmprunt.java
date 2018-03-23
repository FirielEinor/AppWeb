package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatheque.Mediatheque;

public class ConfirmEmprunt extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		int numDoc = Integer.parseInt(request.getParameter("idDoc"));
		Mediatheque m = Mediatheque.getInstance();
		System.out.println(m.getDocument(numDoc));
		m.retour(m.getDocument(numDoc));
		out.println("Document rendu !! <a href='http://localhost:8080/ProjectWebJava/service?login=" + session.getAttribute("login") + "&password=" + session.getAttribute("password")+"'>retour </a>");
	}
}
