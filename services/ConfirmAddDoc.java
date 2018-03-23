package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatheque.Mediatheque;

public class ConfirmAddDoc extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		
		List<String> types = Mediatheque.getInstance().getArgDoc(type);
		
		List<Object> Arg = new ArrayList<Object>();
		
		for(String s:types){
			Arg.add(request.getParameter(s));
		}
		System.out.println(Arg);
		HttpSession session = request.getSession(false);
		
		Mediatheque.getInstance().nouveauDocument(type, Arg);
		out.println("Document ajouter !! <a href='http://localhost:8080/ProjectWebJava/service?login=" + session.getAttribute("login") + "&password=" + session.getAttribute("password")+"'>retour </a>");
//		String titreDoc = request.getParameter("titreDoc");
//		if(type.equals("DVD")){
//			String real = request.getParameter("realisateur");
//			int duree = Integer.parseInt(request.getParameter("duree"));
//			Mediatheque.getInstance().nouveauDocument(type, titreDoc,real,duree);
//		}
//		else if(type.equals("Livre")){
//			String auteur = request.getParameter("auteur");
//			int nbPage = Integer.parseInt(request.getParameter("nbPage"));
//			Mediatheque.getInstance().nouveauDocument(type, titreDoc,auteur,nbPage);
//		}
	}
}
