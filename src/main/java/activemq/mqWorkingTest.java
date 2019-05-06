package activemq;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;
import javax.mail.Message.RecipientType;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;
import javax.servlet.ServletException;

import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.jcr.api.SlingRepository;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest; 
import org.apache.sling.api.SlingHttpServletResponse;


@SlingServlet(paths = "/mqWTest")
public class mqWorkingTest extends SlingAllMethodsServlet {
	private static final long serialVersionUID = 1;
	@Reference
	private SlingRepository repo;

	static ResourceBundle bundle = ResourceBundle.getBundle("config");
	Session session = null;
	
	@Override
	public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		try {
			
			session = repo.login(new SimpleCredentials("admin", "admin".toCharArray()));
		        out.println("entered ");
			
		        ActiveMQCall mq=new ActiveMQCall();
		        mq.GetProducer("workingTest", "24", "workingMq");
		        
			
		} catch (Exception e) {
			e.printStackTrace(out);
		}
		
		
		
	}
	
	
	

}
