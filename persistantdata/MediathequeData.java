package persistantdata;

import java.sql.*;
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
			String req = "SELECT d.idDoc,d.titreDoc,d.auteurDoc,d.typeDoc,d.NumEmprunteur,u.loginuser,u.passworduser FROM DOCUMENT d,UTILISATEUR u WHERE idDoc = " + numDocument + " AND d.NumEmprunteur=u.idUser";
			Statement st;
			st = conn.createStatement();
			ResultSet r = st.executeQuery(req);
			r.next();
			int type = r.getInt(3);
			switch (type) {
			case 1:
				d = new Livre(r.getString(0), r.getString(2), r.getString(1), getUser(r.getString(4),r.getString(5)));
				break;

			default:
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return d;
	}

	@Override
	public void nouveauDocument(int type, Object... args) {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc...
		try {
			String req = "insert into DOCUMENT (idDoc, titreDoc, auteurDoc, typeDoc, numEmprunteur) values(seq_doc.next,2,";
			for (int i = 0; i < args.length; i++) {
				req += args[i]+",";
			}
			req += ",'null')";
			Statement st;
			st = conn.createStatement();
			ResultSet r = st.executeQuery(req);
			r.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
