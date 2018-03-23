package mediatheque;

public class Utilisateur {
	private int id;
	private String login;
	private String password;
	private String type;
	
	public Utilisateur(String login, String password,String type,int id) {
		this.login = login;
		this.password = password;
		this.type = type;
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getType() {
		return type;
	}
	
	public int getId(){
		return id;
	}
}
