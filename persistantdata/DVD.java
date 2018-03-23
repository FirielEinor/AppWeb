package persistantdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Utilisateur;

public class DVD implements Document {
	private int numDVD;
	private String titreDVD;
	private String realisateur;
	private int duree;
	private Utilisateur u;
	private String nomDriver = "oracle.jdbc.OracleDriver";
	private String url = "jdbc:oracle:thin:@vs-oracle2:1521:ORCL";
	private String user = "GRP207US10";
	private String password = "GRP207US10";
	PreparedStatement st;
	Connection connexion;

	
	
	public DVD(int numDVD, String titreDVD, String realisateur, int duree,Utilisateur u) {
		this.numDVD = numDVD;
		this.titreDVD = titreDVD;
		this.realisateur = realisateur;
		this.duree = duree;
		this.u = u;
	}

	@Override
	public void emprunter(Utilisateur a) throws EmpruntException {
		// TODO Auto-generated method stub
		try {
			Class.forName(nomDriver);
			connexion = DriverManager.getConnection(url,user,password);
			Statement stVols = connexion.createStatement();
			String reqVols = "UPDATE DOCUMENT SET numEmprunteur '= "+ a.getId() +"' WHERE idDoc = '"+ numDVD + "'";
			ResultSet resultats = stVols.executeQuery(reqVols);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void retour() {
		// TODO Auto-generated method stub
		try {
			Class.forName(nomDriver);
			connexion = DriverManager.getConnection(url,user,password);
			Statement stVols = connexion.createStatement();
			String reqVols = "UPDATE DOCUMENT SET numEmprunteur = null WHERE idDoc = "+ numDVD;
			ResultSet resultats = stVols.executeQuery(reqVols);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object[] affiche() {
		// TODO Auto-generated method stub
		Object[] DVDData = null;		
		DVDData[0] = numDVD;
		DVDData[1] = titreDVD;
		DVDData[2] = realisateur;
		DVDData[3] = duree;
		DVDData[4] = u;		
		return DVDData;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return numDVD;
	}

	@Override
	public String getTitre() {
		// TODO Auto-generated method stub
		return titreDVD;
	}

}
