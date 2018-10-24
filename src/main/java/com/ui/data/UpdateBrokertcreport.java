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
		@Property(name = "sling.servlet.paths", value = { "/updateUibroker" }),
		})

public class UpdateBrokertcreport extends SlingAllMethodsServlet {
	@Reference
	private SlingRepository repo;
	Session session = null;
	static ResourceBundle bundle = ResourceBundle.getBundle("config");

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		try{

		session = repo.login(new SimpleCredentials("admin", "admin".toCharArray()));
		
		JSONObject passdata=new JSONObject(request.getParameter("employees"));
		
		if(passdata.has("accounting")){
			JSONArray jsonarraydata=passdata.getJSONArray("accounting");
			for(int i=0;i<jsonarraydata.length();i++){
				JSONObject jsonobjdata=jsonarraydata.getJSONObject(i);
				
				String broker="";
				String vesseltype="";
				String monthyear="";
				String firstyr="";
				String secondyr="";
				String thirdyr="";
				String fifthyr="";
				String editnodename="";
				
				if(jsonobjdata.has("brokerupdate")){
					broker=jsonobjdata.getString("brokerupdate");
				}
				
				if(jsonobjdata.has("vt")){
					vesseltype=jsonobjdata.getString("vt");
				}
				
				
				if(jsonobjdata.has("my")){
					monthyear=jsonobjdata.getString("my");
				}
				
				
				if(jsonobjdata.has("fy")){
					firstyr=jsonobjdata.getString("fy");
				}
				
				if(jsonobjdata.has("sy")){
					secondyr=jsonobjdata.getString("sy");
				}
				
				if(jsonobjdata.has("ty")){
					thirdyr=jsonobjdata.getString("ty");
				}
				
				if(jsonobjdata.has("fifyr")){
					fifthyr=jsonobjdata.getString("fifyr");
				}
				
				if(jsonobjdata.has("editnodename")){
					editnodename=jsonobjdata.getString("editnodename");
				}
				
				
				
				UiMethods.updateBrokertcRate(out, session, editnodename, broker);
				
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
