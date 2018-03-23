package persistantdata;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import mediatheque.*;

// classe mono-instance  dont l'unique instance n'est connue que de la bibliotheque
// via une auto-déclaration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
// Jean-François Brette 01/01/2018
	static {
		Mediatheque.getInstance().setData(new MediathequeData());
	}
	
	private Connection conn;
	
	private MediathequeData() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@vs-oracle2:1521:ORCL","GRP207US10","GRP207US10");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	// renvoie la liste de tous les documents de la bibliothèque
	@Override
	public List<Document> tousLesDocuments() {
		List<Document> list = new ArrayList<Document>();
		try {
			String req = "SELECT idDoc FROM DOCUMENT";
			Statement st;
			st = conn.createStatement();
			ResultSet r = st.executeQuery(req);
			System.out.println(req);
			while(r.next()){
				System.out.println("je rentre dans boucle req de toutdoc idDoc = " + r.getInt("idDoc"));
				list.add(getDocument(r.getInt("idDoc")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}
	

	// va récupérer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		try {
			System.out.println(login + " " + password + " Data SQL");
			String req = "SELECT * FROM UTILISATEUR WHERE LOGINUSER='" + login + "' AND PASSWORDUSER='" + password + "'";
			
			//String req = "SELECT idUser FROM UTILISATEUR";
			Statement st;
			System.out.println(req);
			st = conn.createStatement();
			ResultSet r = st.executeQuery(req);
			Integer id = null;
			String loginU = null;
			String passU = null;
			String typeU = null;
			while(r.next()){
				System.out.println("je passe dans le next");
				id = r.getInt("idUser");
				loginU = r.getString("loginUser");
				passU = r.getString("passwordUser");
				typeU = r.getString("typeUser");
			}
			
			if (loginU == null || passU == null)
				return null;
			return new Utilisateur(loginU,passU,typeU,id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// va récupérer le document de numéro numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public synchronized Document getDocument(int numDocument) {	
		Document d = null;
		try {
			String req = "SELECT idDoc,titreDoc,NumEmprunteur FROM DOCUMENT WHERE idDoc = " + numDocument;
			Statement st;
			st = conn.createStatement();
			ResultSet r = st.executeQuery(req);
			
			if(!r.next()){
				System.out.println("passe !!");
				return null;
			}
			int numDoc = r.getInt("idDoc");
			System.out.println(numDoc);
			String titreDoc = r.getString(2);
			Integer numEmprunteur = r.getInt(3);
			System.out.println(titreDoc);
			System.out.println(numEmprunteur);
			Utilisateur u = null;
			if (numEmprunteur != 0){
				System.out.println("rentre dans pas user");
				req = "SELECT loginUser,passwordUser FROM UTILISATEUR WHERE idUser =" + numEmprunteur;
				r = st.executeQuery(req);
				if(r.next()){
					String login = r.getString(1);
					String pass = r.getString(2);
					u = getUser(login,pass);
				}
			}
			System.out.println("arrive ici getDoc");
			req = "SELECT * FROM DVD WHERE idDVD =" + numDoc;
			r = st.executeQuery(req);
			
			if(r.next()){
				System.out.println("rentre dans DVD");
				d = new DVD(r.getInt(1),titreDoc,r.getString(2),r.getInt(3),u);
			}
			else {
				System.out.println("rentre dans Livre");
				req = "SELECT * FROM LIVRE WHERE idLivre =" + numDoc;
				r = st.executeQuery(req);
				if(r.next()){
					System.out.println("trouve un livre : " +r.getInt("idLivre")+ " " + r.getString(2) + " " + r.getString(3));
					d = new Livre(r.getInt("idLivre"), titreDoc, r.getString(2),r.getString(3), u);
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(d.getTitre());
		return d;
		
	}

	@Override
	public synchronized void nouveauDocument(String type, Object... args) {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc...
		try {
			String req = "insert into DOCUMENT (idDoc, titreDoc, numEmprunteur) values(seq_doc.nextval,";
			List<Object> Arg = (List<Object>) args[0];
			req += "'" +Arg.get(0).toString()+"',";
			req += "null)";
			Statement st;
			st = conn.createStatement();
			System.out.println(req);
			st.executeQuery(req);
			
			req = "insert into " + type + " values(seq_doc.currval,";
			if(type.equals("Livre")){
				req += "'" +Arg.get(1).toString()+"',";
				req += Arg.get(2).toString()+")";
				
			}
			else if(type.equals("DVD")){
				req += "'" +Arg.get(1).toString()+"',";
				req += Arg.get(2).toString()+")";
				
			}
			
			System.out.println(req);
			st.execute(req);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public List<String> getArgDoc(String type){
		List<String> listeArg = new ArrayList<String>();
		listeArg.add("titreDoc");
		switch (type) {
		case "Livre":
			listeArg.add("auteur");
			listeArg.add("nbPage");
			break;
		case "DVD":
			listeArg.add("realisateur");
			listeArg.add("dureeMinute");
		default:
			break;
		}
		return listeArg;
	}
	
	@Override
	public List<String> getTypesDoc(){
		List<String> types = new ArrayList<String>();
		
		//Les types sont coder en dur pour le moment mais a termes il peuvent probablement etre recuperer dans la base de données
		types.add("DVD");
		types.add("Livre");
		
		return types;		
	}

}
