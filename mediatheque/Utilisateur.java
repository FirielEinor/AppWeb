package mediatheque;

public class Utilisateur {
	private String login;
	private String password;
	
	public Utilisateur(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}
}
