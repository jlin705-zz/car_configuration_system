package coreservlets;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import Adapter.BuildAuto;
import Adapter.proxyAutomotive;
import Exception.InvalidOptionException;

@SuppressWarnings("serial")
public class CarSelect extends HttpServlet{
	@SuppressWarnings("rawtypes")
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws IOException, ServletException{
		proxyAutomotive auto=new BuildAuto();
		auto.readFile("/Users/neo_cupid/Desktop/Tomcat/webapps/examples/WEB-INF/classes/coreservlets/Focus_Wagon_ZTW.txt");
		response.setContentType("text/html");
		for(java.util.Iterator itr=auto.getOptionSetNamesIterator();itr.hasNext();){
			String a=(String) itr.next();
			String b= request.getParameter(a);
			try {
				auto.setOptionChoice(a, b);
			} catch (InvalidOptionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		request.setAttribute("Choice", auto);
		RequestDispatcher view= request.getRequestDispatcher("/result.jsp");
		view.forward(request, response);
	}
}