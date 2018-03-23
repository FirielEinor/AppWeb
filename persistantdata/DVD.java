package persistantdata;

import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Utilisateur;

public class DVD implements Document {
	private int numDVD;
	private String titreDVD;
	private String realisateur;
	private int duree;
	private Utilisateur u;
	

	
	
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
