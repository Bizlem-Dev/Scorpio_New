package com.mongocode;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.jcr.api.SlingRepository;

import com.readGmail.Gmail_Pojo;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Properties;

@SuppressWarnings("deprecation")
@Component(immediate = true, metatype = false)
@Service(value = javax.servlet.Servlet.class)
@Properties({ 
		@Property(name = "sling.servlet.paths", value = { "/MongoCollectData" }),
		})

public class mongoSubjectMultiplecollectionRead extends SlingAllMethodsServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Reference
	private SlingRepository repo;
	
	static ResourceBundle bundle = ResourceBundle.getBundle("config");

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try{
			Session session = repo.login(new SimpleCredentials("admin", "admin".toCharArray()));
			
			Gmail_Pojo gm = null;
			gm = new Gmail_Pojo();
			gm.setProtocol(bundle.getString("protocol"));
			gm.setHost(bundle.getString("host"));
			gm.setPort(bundle.getString("port"));
			gm.setUserName(bundle.getString("userName"));
			gm.setPassWord(bundle.getString("password"));
			
	
			ReadSubjectWiseMail.multipleCollectionReadData(out, "ReadMail", session, gm);
	    
		}catch(Exception e){
		    out.println(e.getMessage());
		}
		
	}

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException, IOException {
	
	}
	
}
