package services;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class ServletInit extends HttpServlet {
	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		super.init(arg0);
		System.out.println("je passe ici!!!");
		System.out.println(getInitParameter("classToInit"));
		try {
			//Class.forName(arg0.toString());
			Class.forName(getInitParameter("classToInit"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
