package coreservlets;

import java.io.*;
import java.util.Iterator;

import javax.servlet.*;
import javax.servlet.http.*;
import Adapter.BuildAuto;
import Adapter.proxyAutomotive;

@SuppressWarnings("serial")
public class servlet extends HttpServlet {

    @SuppressWarnings("rawtypes")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {	try {
			proxyAutomotive auto=new BuildAuto();
			auto.readFile("/Users/neo_cupid/Desktop/Tomcat/webapps/examples/WEB-INF/classes/coreservlets/Focus_Wagon_ZTW.txt");
		
			response.setContentType("text/html");
            
            
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<head>");
        out.println("<title>Car Config</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Car cofiguration</h1>");
        out.println("<form method=\"POST\" action=\"coreservlets.CarSelect\">");
        out.println("<table border=\"1\">");
        out.println("<tr>");
        out.println("<td>Car Make/Model</td>"+"<td>"+auto.getMake()+" "+auto.getModel()+"</td></tr>");
        for(Iterator itr=auto.getOptionSetNamesIterator();itr.hasNext();){
        	String a=(String) itr.next();
            out.println("<tr><td>"+a+"</td>");
        	out.println("<td><select name="+a+" size=\"1\">");
        	for(int i=0;i<auto.getA1().getOptionSet(a).counter();i++){
        		out.println("<option>"+auto.getA1().getOptionSet(a).getelement(i).getName());
        	}
        	  out.println("</select></td>");
              out.println("</tr>");
        }
        out.println("<tr><td><input type=\"SUBMIT\"></td></tr>");
        out.println("</center>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
	/*	} catch (BlankFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
