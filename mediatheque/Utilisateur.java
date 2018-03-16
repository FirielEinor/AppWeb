package mediatheque;

public class Utilisateur {
	private String login;
	private String password;
	private String type;
	
	public Utilisateur(String login, String password,String type) {
		this.login = login;
		this.password = password;
		this.type = type;
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
}
