package com.ui.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
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
		@Property(name = "sling.servlet.paths", value = { "/saveUiTimeCharterReports" }),
		})

public class SaveUiTimeCharterReports extends SlingAllMethodsServlet {
	@Reference
	private SlingRepository repo;
	Session session = null;
	static ResourceBundle bundle = ResourceBundle.getBundle("config");

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		try{

		session = repo.login(new SimpleCredentials("admin", "admin".toCharArray()));
		
		JSONObject passdata=new JSONObject(request.getParameter("employees"));
		
		if(passdata.has("accounting")){
			JSONArray jsonarraydata=passdata.getJSONArray("accounting");
			for(int i=0;i<jsonarraydata.length();i++){
				JSONObject jsonobjdata=jsonarraydata.getJSONObject(i);
				
				String information="";
				String spottc="";
				String date="";
				String vesselname="";
				String cppdpp="";
				String charterer="";
				String delivery="";
				
				if(jsonobjdata.has("informatio")){
					information=jsonobjdata.getString("informatio");
				}
				
				if(jsonobjdata.has("spottc")){
					spottc=jsonobjdata.getString("spottc");
				}
				
				
				if(jsonobjdata.has("date")){
					date=jsonobjdata.getString("date");
				}
				
				
				if(jsonobjdata.has("vesselnam")){
					vesselname=jsonobjdata.getString("vesselnam");
				}
				
				if(jsonobjdata.has("cppdpp")){
					cppdpp=jsonobjdata.getString("cppdpp");
				}
				
				if(jsonobjdata.has("chartere")){
					charterer=jsonobjdata.getString("chartere");
				}
				
				if(jsonobjdata.has("delivery")){
					delivery=jsonobjdata.getString("delivery");
				}
				
				String deliveryplace="";
				if(jsonobjdata.has("deliveryplace")){
					deliveryplace=jsonobjdata.getString("deliveryplace");
				}
				String period="";
				if(jsonobjdata.has("period")){
					period=jsonobjdata.getString("period");
				}
				String periodunit="";
				if(jsonobjdata.has("periodunit")){
					periodunit=jsonobjdata.getString("periodunit");
				}
				String redelivery="";
				if(jsonobjdata.has("redelivery")){
					redelivery=jsonobjdata.getString("redelivery");
				}
				String tcrate="";
				if(jsonobjdata.has("tcrate")){
					tcrate=jsonobjdata.getString("tcrate");
				}
				String rate="";
				if(jsonobjdata.has("rat")){
					rate=jsonobjdata.getString("rat");
				}
				String unit="";
				if(jsonobjdata.has("unit")){
					unit=jsonobjdata.getString("unit");
				}
				
				String comments="";
				if(jsonobjdata.has("commentobj")){
					comments=jsonobjdata.getString("commentobj");
				}
				String source="";
				if(jsonobjdata.has("sourc")){
					source=jsonobjdata.getString("sourc");
				}
				String vesseltype="";
				if(jsonobjdata.has("vesseltyp")){
					vesseltype=jsonobjdata.getString("vesseltyp");
				}
				String timestamp="";
				if(jsonobjdata.has("timestamp")){
					timestamp=jsonobjdata.getString("timestamp");
				}
				
				UiMethods.TimeCharterReportSave(session, out, information, spottc, date, vesselname, cppdpp, charterer, delivery, deliveryplace, period, periodunit, redelivery, tcrate, rate, unit, comments, timestamp, source, vesseltype);
				
			}// for
			
		}// if

		
		 
		}catch(Exception e){
			e.printStackTrace();
		    out.println(e.getMessage());
		}

	}

	
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

	}
	
}
