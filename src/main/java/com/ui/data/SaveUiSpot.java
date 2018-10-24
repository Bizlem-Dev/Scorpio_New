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
		@Property(name = "sling.servlet.paths", value = { "/saveUiSpot" }),
		})

public class SaveUiSpot extends SlingAllMethodsServlet {
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
				String fixturetype="";
				String charterer="";
				String status="";
				String cargotype="";
				String cargograde="";
				String cargoqty="";
				
				if(jsonobjdata.has("info")){
					information=jsonobjdata.getString("info");
				}
				
				if(jsonobjdata.has("fixturetype")){
					fixturetype=jsonobjdata.getString("fixturetype");
				}
				
				
				if(jsonobjdata.has("Charterer")){
					charterer=jsonobjdata.getString("Charterer");
				}
				
				
				if(jsonobjdata.has("status")){
					status=jsonobjdata.getString("status");
				}
				
				if(jsonobjdata.has("CargoType")){
					cargotype=jsonobjdata.getString("CargoType");
				}
				
				if(jsonobjdata.has("cargograde")){
					cargograde=jsonobjdata.getString("cargograde");
				}
				
				if(jsonobjdata.has("cargoqty")){
					cargoqty=jsonobjdata.getString("cargoqty");
				}
				String lcstart="";
				if(jsonobjdata.has("lcstart")){
					lcstart=jsonobjdata.getString("lcstart");
				}
				String lcend="";
				if(jsonobjdata.has("lcend")){
					lcend=jsonobjdata.getString("lcend");
				}
				String loadport="";
				if(jsonobjdata.has("loadport")){
					loadport=jsonobjdata.getString("loadport");
				}
				String discport="";
				if(jsonobjdata.has("discport")){
					discport=jsonobjdata.getString("discport");
				}
				String vesselname="";
				if(jsonobjdata.has("VesselName")){
					vesselname=jsonobjdata.getString("VesselName");
				}
				String ratetype="";
				if(jsonobjdata.has("ratetype")){
					ratetype=jsonobjdata.getString("ratetype");
				}
				String rate="";
				if(jsonobjdata.has("Rate")){
					rate=jsonobjdata.getString("Rate");
				}
				String comments="";
				if(jsonobjdata.has("Comments")){
					comments=jsonobjdata.getString("Comments");
				}
				String reportdate="";
				if(jsonobjdata.has("reportdate")){
					reportdate=jsonobjdata.getString("reportdate");
				}
				String source="";
				if(jsonobjdata.has("Source")){
					source=jsonobjdata.getString("Source");
				}
				String vesseltype="";
				if(jsonobjdata.has("VesselType")){
					vesseltype=jsonobjdata.getString("VesselType");
				}
				
				UiMethods.SpotSave(session, out, information, fixturetype, charterer, status, cargotype, cargograde, cargoqty, lcstart, lcend, loadport, discport, vesselname, ratetype, rate, comments, reportdate, source, vesseltype);
				
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
