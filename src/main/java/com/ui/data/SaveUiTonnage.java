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
		@Property(name = "sling.servlet.paths", value = { "/saveUiTonnage" }),
		})

public class SaveUiTonnage extends SlingAllMethodsServlet {
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
				
				String reporttype="";
				String repositionregion="";
				String cargotype="";
				String vesselname="";
				String vesseltype="";
				String built="";
				String dwt="";
				
				if(jsonobjdata.has("reporttype")){
					reporttype=jsonobjdata.getString("reporttype");
				}
				
				if(jsonobjdata.has("repositionregion")){
					repositionregion=jsonobjdata.getString("repositionregion");
				}
				
				
				if(jsonobjdata.has("cargotype")){
					cargotype=jsonobjdata.getString("cargotype");
				}
				
				
				if(jsonobjdata.has("vesselname")){
					vesselname=jsonobjdata.getString("vesselname");
				}
				
				if(jsonobjdata.has("vesseltype")){
					vesseltype=jsonobjdata.getString("vesseltype");
				}
				
				if(jsonobjdata.has("built")){
					built=jsonobjdata.getString("built");
				}
				
				if(jsonobjdata.has("dwt")){
					dwt=jsonobjdata.getString("dwt");
				}
				String cubics="";
				if(jsonobjdata.has("cubics")){
					cubics=jsonobjdata.getString("cubics");
				}
				String loa="";
				if(jsonobjdata.has("loa")){
					loa=jsonobjdata.getString("loa");
				}
				String ice="";
				if(jsonobjdata.has("ice")){
					ice=jsonobjdata.getString("ice");
				}
				String sternline="";
				if(jsonobjdata.has("sternline")){
					sternline=jsonobjdata.getString("sternline");
				}
				String owners="";
				if(jsonobjdata.has("owners")){
					owners=jsonobjdata.getString("owners");
				}
				String operators="";
				if(jsonobjdata.has("operators")){
					operators=jsonobjdata.getString("operators");
				}
				String employementstatus="";
				if(jsonobjdata.has("employementstatus")){
					employementstatus=jsonobjdata.getString("employementstatus");
				}
				String openport="";
				if(jsonobjdata.has("openport")){
					openport=jsonobjdata.getString("openport");
				}
				String opendate="";
				if(jsonobjdata.has("opendate")){
					opendate=jsonobjdata.getString("opendate");
				}
				String etabasis="";
				if(jsonobjdata.has("etabasis")){
					etabasis=jsonobjdata.getString("etabasis");
				}
				String comment="";
				if(jsonobjdata.has("comment")){
					comment=jsonobjdata.getString("comment");
				}
				String source="";
				if(jsonobjdata.has("source")){
					source=jsonobjdata.getString("source");
				}
				String reporttimestamp="";
				if(jsonobjdata.has("reporttimestamp")){
					reporttimestamp=jsonobjdata.getString("reporttimestamp");
				}
				
				UiMethods.TonnageSave(session, out, reporttype, repositionregion, cargotype, vesselname, vesseltype, built, dwt, cubics, loa, ice, sternline, owners, operators, employementstatus, openport, opendate, etabasis, comment, source, reporttimestamp);
				
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
