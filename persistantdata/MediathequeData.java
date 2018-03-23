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
		return null;
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
			String loginU = null;
			String passU = null;
			String typeU = null;
			while(r.next()){
				System.out.println("je passe dans le next");
				loginU = r.getString("loginUser");
				passU = r.getString("passwordUser");
				typeU = r.getString("typeUser");
			}
			
			if (loginU == null || passU == null)
				return null;
			return new Utilisateur(loginU,passU,typeU);
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
	public Document getDocument(int numDocument) {	
		Document d = null;
		try {
			String req = "SELECT d.idDoc,d.titreDoc,d.NumEmprunteur FROM DOCUMENT d,WHERE d.idDoc = " + numDocument;
			Statement st;
			st = conn.createStatement();
			ResultSet r = st.executeQuery(req);
			r.next();
			int numDoc = r.getInt(0);
			String titreDoc = r.getString(1);
			Integer numEmprunteur = r.getInt(3);
			
			Utilisateur u = null;
			if (numEmprunteur != null){
				req = "SELECT loginUser,passwordUser FROM UTILISATEUR WHERE idUser =" + numEmprunteur;
				r = st.executeQuery(req);
				if(r.next()){
					String login = r.getString(0);
					String pass = r.getString(1);
					u = getUser(login,pass);
				}
			}
			
			req = "SELECT * FROM DVD WHERE idDVD =" + numDoc;
			r = st.executeQuery(req);
			
			if(r.next()){
				d = new DVD(r.getInt(0),titreDoc,r.getString(1),r.getInt(2),u);
			}
			else {
				req = "SELECT * FROM LIVRE WHERE idDVD =" + numDoc;
				r = st.executeQuery(req);
				if(r.next()){
					d = new Livre(r.getInt(0), titreDoc, r.getString(1),r.getString(2), u);
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return d;
	}

	@Override
	public void nouveauDocument(String type, Object... args) {
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
