package mediatheque;

import java.util.List;

public interface PersistentMediatheque {
// Jean-François Brette 01/01/2018
	List<Document> tousLesDocuments();
	
	List<String> getArgDoc(String type);
	
	List<String> getTypesDoc();
	
	Document getDocument(int numDocument);

	Utilisateur getUser(String login, String password);
	
	void nouveauDocument(String type, Object... args );

}
