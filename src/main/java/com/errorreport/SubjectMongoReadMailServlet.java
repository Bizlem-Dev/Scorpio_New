package com.errorreport;

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
import com.mongocode.MongoDbConnection;
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
		@Property(name = "sling.servlet.paths", value = { "/MongoSubjectStep" }),
		})

public class SubjectMongoReadMailServlet extends SlingAllMethodsServlet {
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
		MongoDbConnection MDC=new MongoDbConnection();
		
		Gmail_Pojo gm = null;
		gm = new Gmail_Pojo();
		gm.setProtocol(bundle.getString("protocol"));
		gm.setHost(bundle.getString("host"));
		gm.setPort(bundle.getString("port"));
		gm.setUserName(bundle.getString("userName"));
		gm.setPassWord(bundle.getString("password"));
		
		MDC.getReadMailCollectionData(out, "ReadmailDataBase", "ReadMailCollection", session, gm);
		
		
		}catch(Exception e){
			//e.printStackTrace(out);
		    out.println(e.getMessage());
		}
		
	}

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException, IOException {
	
	}
	
}
