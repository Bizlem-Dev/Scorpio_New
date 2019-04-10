package com.reportinformationsystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
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
		@Property(name = "sling.servlet.paths", value = { "/reportTimeCharterReportsRemove" }),
		})

public class removeTimeCharterReportsFromUi extends SlingAllMethodsServlet {
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
		
		String subNodeName=request.getParameter("id");
		removeTimeCharterReportsNode(session, subNodeName, out);
		
		
		}catch(Exception e){
//			e.printStackTrace(out);
		    out.println(e.getMessage());
		}
		
	}

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException, IOException {
	
	}
	
	public static void removeTimeCharterReportsNode(Session session, String subNodeName, PrintWriter out) throws PathNotFoundException, RepositoryException{
		
		Node scorpioDataBase=null;
		Node ReportData=null;
		Node TCReports=null;
		Node Tonnage=null;
		
		if(session.getRootNode().hasNode("scorpioDataBase")){
			scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
		}
		
		if(scorpioDataBase.hasNode("ReportData")){
			 ReportData=scorpioDataBase.getNode("ReportData");
		}
		if(ReportData.hasNode("TCReports")){
			TCReports=ReportData.getNode("TCReports");
		}
		if(TCReports.hasNode("TimeCharterReport")){
			Tonnage=TCReports.getNode("TimeCharterReport");
		}

		Node one=null;
		if(Tonnage.hasNode(subNodeName)){
			one=Tonnage.getNode(subNodeName);
			one.remove();
			session.save();
		}

	
}
	
}
