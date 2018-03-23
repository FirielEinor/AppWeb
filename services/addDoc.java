package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatheque.Mediatheque;

public class addDoc extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		out.println("<html>");
		out.println("<body>");
		if(type == null){
			
        			out.println("<h2>Choisissez un type de document</h2>");
        			out.print("<form action= http://localhost:8080/ProjectWebJava/addDoc>");
        			
        			out.println("<select name ='type'>");
        			for (String s : Mediatheque.getInstance().getTypesDoc()){
        				out.println("<option value ='"+s+"'>"+s+"</option>");
        			}
        			out.println("</select>");
        			out.println("<input type=submit>");
        			out.println("</form>");
            	
		}
		else{
			out.print("<form action= http://localhost:8080/ProjectWebJava/confirmAddDoc>");
			out.println("Type du document");
			out.println("<input name='type' type='text' value=" + type +" readonly>");		
			List<String> listArg = Mediatheque.getInstance().getArgDoc(type);
			
			for(String s:listArg){
				out.println(s + ":");
				out.println("<input name='"+s+"' type='text'>");
			}
			out.println("<input type=submit>");
			out.println("</form>");
		}
		out.println("</body>");
        out.println("</html>");
	}
}
