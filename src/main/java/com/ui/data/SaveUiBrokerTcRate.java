package com.ui.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.jcr.AccessDeniedException;
import javax.jcr.InvalidItemStateException;
import javax.jcr.ItemExistsException;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.ReferentialIntegrityException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.version.VersionException;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.jcr.api.SlingRepository;

import com.readGmail.GmailMethods;
import com.readGmail.SlingMethods;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Properties;

@Component(immediate = true, metatype = false)
@Service(value = javax.servlet.Servlet.class)
@Properties({ 
		@Property(name = "sling.servlet.paths", value = { "/saveUiBrokerTcRate" }),
		})

public class SaveUiBrokerTcRate extends SlingAllMethodsServlet {
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
				
				String broker="";
				String vesseltype="";
				String monthyear="";
				String firstyr="";
				String secondyr="";
				String thirdyr="";
				String fifthyr="";
				String editnodename="";
				
				if(jsonobjdata.has("broker")){
					broker=jsonobjdata.getString("broker");
				}
				
				if(jsonobjdata.has("vesselty")){
					vesseltype=jsonobjdata.getString("vesselty");
				}
				
				
				if(jsonobjdata.has("monthyear")){
					monthyear=jsonobjdata.getString("monthyear");
				}
				
				
				if(jsonobjdata.has("firstyr")){
					firstyr=jsonobjdata.getString("firstyr");
				}
				
				if(jsonobjdata.has("secondyr")){
					secondyr=jsonobjdata.getString("secondyr");
				}
				
				if(jsonobjdata.has("thirdyr")){
					thirdyr=jsonobjdata.getString("thirdyr");
				}
				
				if(jsonobjdata.has("fifthyr")){
					fifthyr=jsonobjdata.getString("fifthyr");
				}
				
				if(jsonobjdata.has("editnodename")){
					editnodename=jsonobjdata.getString("editnodename");
				}
				
				if(!editnodename.equals("")){
					UiMethods.BrokerTCRateEditUi(session, out, broker, vesseltype, monthyear, firstyr, secondyr, thirdyr, fifthyr, editnodename);
				}else{
					UiMethods.BrokerTCRateSaveUi(session, out, broker, vesseltype, monthyear, firstyr, secondyr, thirdyr, fifthyr, "");
				}
				
				
				
			}// for
			
			
			
		}// if
		
//		String id=request.getParameter("id");
		/*String broker=request.getParameter("broker");
		String vesseltype=request.getParameter("vesseltype");
		String monthyear=request.getParameter("monthyear");
		String firstyr=request.getParameter("firstyr");
		String secondyr=request.getParameter("secondyr");
		String thirdyr=request.getParameter("thirdyr");
		String fifthyr=request.getParameter("fifthyr");
		
		out.println("broker: "+broker);
		out.println("vesseltype: "+vesseltype);*/
		
		 
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
