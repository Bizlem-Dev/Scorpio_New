package ChangedStructureCurrent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.jcr.api.SlingRepository;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Properties;

@Component(immediate = true, metatype = false)
@Service(value = javax.servlet.Servlet.class)
@Properties({ 
		@Property(name = "sling.servlet.paths", value = { "/reportApiOld" }),
		})

public class ReportApiServlet extends SlingAllMethodsServlet {
	@Reference
	private SlingRepository repo;
	Session session = null;
	static ResourceBundle bundle = ResourceBundle.getBundle("config");

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException, IOException {

	}

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException, IOException {
		
		
//		response.setContentType("application/json");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try{

		session = repo.login(new SimpleCredentials("admin", "admin".toCharArray()));
		 FetchData fd=new FetchData();
		BufferedReader br = request.getReader();
		String line = "";
		StringBuffer sb = new StringBuffer();
		while((line = br.readLine()) != null){
			sb.append(line);
		}
		//out.println(sb.toString());
		 JSONObject resultresponse=new JSONObject(sb.toString());
		 String from= resultresponse.getString("from");
		 String to= resultresponse.getString("to");
		 String reporttype= resultresponse.getString("reporttype");
		 
//		 String data=fd.checkDatesForRecords(session, "", "30-10-2018","Position List", out);
		 String data=fd.checkDatesForRecords(session, from, to,reporttype, out);
		 JSONObject dataobj=new JSONObject(data);
//		 response.setContentLength(dataobj.length());
		 
		 out.println(dataobj.toString());
		 
		
		}catch(Exception e){
			e.printStackTrace(out);
		    out.println(e.getMessage());
		}
		
		
	}
	
}
