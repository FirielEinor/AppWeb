package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatheque.Mediatheque;

public class ConfirmAddDoc extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String titreDoc = request.getParameter("titreDoc");
		if(type.equals("DVD")){
			String real = request.getParameter("real");
			int duree = Integer.parseInt(request.getParameter("Duree"));
			Mediatheque.getInstance().nouveauDocument(type, titreDoc,real,duree);
		}
		else if(type.equals("Livre")){
			String auteur = request.getParameter("auteur");
			int nbPage = Integer.parseInt(request.getParameter("nbPage"));
			Mediatheque.getInstance().nouveauDocument(type, titreDoc,auteur,nbPage);
		}
	}
}
