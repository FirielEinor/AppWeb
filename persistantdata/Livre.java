package persistantdata;

import java.sql.Connection;

import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Utilisateur;

public class Livre implements Document {
	String id;
	String titre;
	String auteur;
	Utilisateur u;
	private Connection conn;
	
	public Livre(String id,String auteur,String titre,Utilisateur u){
		this.id = id;
		this.auteur = auteur;
		this.titre = titre;
		this.u = u;
	}

	@Override
	public void emprunter(Utilisateur a) throws EmpruntException {
		// TODO Auto-generated method stub

	}

	@Override
	public void retour() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] affiche() {
		// TODO Auto-generated method stub
		return null;
	}

}
