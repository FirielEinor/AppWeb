package services;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ServletInit extends HttpServlet {
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try {
			Class.forName("persistantdata.MediathequeData");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
