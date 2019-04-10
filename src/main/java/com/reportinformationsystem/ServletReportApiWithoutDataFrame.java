package com.reportinformationsystem;

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

import com.readGmail.GmailMethods;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Properties;

@Component(immediate = true, metatype = false)
@Service(value = javax.servlet.Servlet.class)
@Properties({ 
		@Property(name = "sling.servlet.paths", value = { "/ReportApi" }),
		})

public class ServletReportApiWithoutDataFrame extends SlingAllMethodsServlet {
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
		boolean flag=false;
		 
		ChangedApiWithoutMultipleArray CPWMA=new ChangedApiWithoutMultipleArray();
		BufferedReader br = request.getReader();
		String line = "";
		StringBuffer sb = new StringBuffer();
		while((line = br.readLine()) != null){
			sb.append(line);
		}
		 JSONObject resultresponse=new JSONObject(sb.toString());
		 String from="";
		 String to="";
		 String reporttype="";
		 String Authorization="";
		 
		 if(resultresponse.has("from")){
		     from= resultresponse.getString("from");
		 }
		 if(resultresponse.has("to")){
		     to= resultresponse.getString("to");
		 }
		 if(resultresponse.has("reporttype")){
			  reporttype= resultresponse.getString("reporttype");
		 } 
		 if(resultresponse.has("Authorization")){
			 Authorization= resultresponse.getString("Authorization");
		 }
		 
		 //.......................setting credentials
		 if( !GmailMethods.isNullString(Authorization) ){
			 
		 flag=AuthHelper.isAllowedCredentials(Authorization);
		 if(flag==true){
		 
		 if( !GmailMethods.isNullString(from) && !GmailMethods.isNullString(to) && !GmailMethods.isNullString(reporttype) ){
		 if(reporttype.equals("Tonnage")){
			 JSONObject dataobj=CPWMA.getTonnageReportDateWithoutDataFrame(session, from, to, reporttype, out);
			 out.println(dataobj.toString());
			 
		 }else if(reporttype.equals("Spot")){
			 JSONObject dataobj=CPWMA.getSpotReportDateWithoutDataFrame(session, from, to, reporttype, out);
			 out.println(dataobj.toString());
			 
		 }else if(reporttype.equals("TimeCharterReports")){
			 JSONObject dataobj=CPWMA.getTimeCharterReportDateWithoutDataFrame(session, from, to, reporttype, out);
			 out.println(dataobj.toString());
			 
		 }else if(reporttype.equals("BrokerTcRate")){
			 JSONObject dataobj=CPWMA.getBrokerTcRateReportDateWithoutDataFrame(session, from, to, reporttype, out);
			 out.println(dataobj.toString());
		 }
	
	}else{
		if(reporttype.equals("Tonnage")){
			 JSONObject dataobj=CPWMA.getTonnageReportWithoutDataFrame(session, reporttype, out);
			 out.println(dataobj.toString());
			 
		 }else if(reporttype.equals("Spot")){
			 JSONObject dataobj=CPWMA.getSpotReportWithoutDataFrame(session, reporttype, out);
			 out.println(dataobj.toString());
			 
		 }else if(reporttype.equals("TimeCharterReports")){
			 JSONObject dataobj=CPWMA.getTimeCharterReportWithoutDataFrame(session, reporttype, out);
			 out.println(dataobj.toString());
			 
		 }else if(reporttype.equals("BrokerTcRate")){
			 JSONObject dataobj=CPWMA.getBrokerTcRateReportWithoutDataFrame(session, reporttype, out);
			 out.println(dataobj.toString());
			 
		 }
	}
		
		 
		 }  // closing credentials
		 
		 else{
			 out.println("Invalid authentication");
		 }
		 }// check null
		 
		}catch(Exception e){
			e.printStackTrace(out);
//		    out.println(e.getMessage());
		}
		
		
	}
	
}
