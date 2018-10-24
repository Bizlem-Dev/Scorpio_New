package com.test_servlet;

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

import com.readGmail.SlingMethods;
import com.ui.data.CreateExcelMethods;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Properties;

@Component(immediate = true, metatype = false)
@Service(value = javax.servlet.Servlet.class)
@Properties({ 
		@Property(name = "sling.servlet.paths", value = { "/BrokerTcRate" }),
		})

public class BrokerTcRateFetchServlet extends SlingAllMethodsServlet {
	@Reference
	private SlingRepository repo;
	Session session = null;
	static ResourceBundle bundle = ResourceBundle.getBundle("config");

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		
     try{

		session = repo.login(new SimpleCredentials("admin", "admin".toCharArray()));
		
		String BrokerTcRatedaywisedataString=SlingMethods.fetchBrokerTcRateDaywiseData(out, session);
		
		
		if(BrokerTcRatedaywisedataString!=null){
			JSONObject BrokerTcRatedaywisedata=new JSONObject(BrokerTcRatedaywisedataString);
			out.println(BrokerTcRatedaywisedata);
			
		}else{
			String error="No daywise data is on Mail";
			JSONObject errorJsonobj=new JSONObject();
			errorJsonobj.put("BrokerTcRateError", error);
			out.println(errorJsonobj);
		}
		
		}catch(Exception e){
			
		    out.println(e.getMessage());
		}

	}

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

	}
	
}
