package persistantdata;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Utilisateur;

public class Livre implements Document {
	int id;
	String titre;
	String auteur;
	String nbPages;
	Utilisateur u;
	private String nomDriver = "oracle.jdbc.OracleDriver";
	private String url = "jdbc:oracle:thin:@vs-oracle2:1521:ORCL";
	private String user = "GRP207US10";
	private String password = "GRP207US10";
	PreparedStatement st;
	Connection connexion;
	
	public Livre(int i,String titre, String auteur, String nbPages, Utilisateur u){
		this.id = i;
		this.titre = titre;
		this.auteur = auteur;
		this.nbPages = nbPages;
		this.u = u;
	}

	@Override
	public void emprunter(Utilisateur a) throws EmpruntException {
		// TODO Auto-generated method stub
		try {
			Class.forName(nomDriver);
			Connection connexion = DriverManager.getConnection(url,user,password);
			Statement stVols = connexion.createStatement();
			String reqVols = "UPDATE DOCUMENT SET numEmprunteur '= "+ u +"' WHERE idDoc = '"+ id + "'";
			ResultSet resultats = stVols.executeQuery(reqVols);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void retour() {
		try {
			Class.forName(nomDriver);
			Connection connexion = DriverManager.getConnection(url,user,password);
			Statement stVols = connexion.createStatement();
			String reqVols = "UPDATE DOCUMENT SET numEmprunteur = null WHERE idDoc = "+ id;
			ResultSet resultats = stVols.executeQuery(reqVols);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object[] affiche() {
		Object[] livreData = null;		
		livreData[0] = id;
		livreData[1] = titre;
		livreData[2] = auteur;
		livreData[3] = nbPages;
		livreData[4] = u;		
		return livreData;
	}

}
