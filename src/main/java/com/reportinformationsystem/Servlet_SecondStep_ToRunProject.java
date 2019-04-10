package com.reportinformationsystem;

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

import com.errorreport.StatusForUi;
import com.pallavi.code.secondStepMethodCall;
import com.readGmail.GmailMethods;
import com.readGmail.Gmail_Pojo;

import ChangedStructureCurrent.GmailReadMailChanged;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Properties;

@Component(immediate = true, metatype = false)
@Service(value = javax.servlet.Servlet.class)
@Properties({ 
		@Property(name = "sling.servlet.paths", value = { "/SecondStep" }),
		})

public class Servlet_SecondStep_ToRunProject extends SlingAllMethodsServlet {
	@Reference
	private SlingRepository repo;
	Session session = null;
	static ResourceBundle bundle = ResourceBundle.getBundle("config");

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try{

		session = repo.login(new SimpleCredentials("admin", "admin".toCharArray()));
		
		 String datePass=StatusForUi.cronCurrentTimeParseHere();
		if( (datePass!=null) && (!GmailMethods.isNullString(datePass)) ){
			 String cronNodeName=StatusForUi.nodeCreateCronInSling(out, session, datePass);
			 
			 if( (cronNodeName!=null) && (!GmailMethods.isNullString(cronNodeName)) ){
				 secondStepMethodCall.ReadGmailDataToPassPythonApi(session, out, cronNodeName);
			 }
			 
		}
		
		
		//DateFromToApiReport.ReadGmailDataToPassPythonApi(session, out);
//		secondStepMethodCall.ReadGmailDataToPassPythonApi(session, out);
		
		//session.save();
		}catch(Exception e){
			//e.printStackTrace(out);
		    out.println(e.getMessage());
		}
		
	}

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException, IOException {
	
	}
	
}
