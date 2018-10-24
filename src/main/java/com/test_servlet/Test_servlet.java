package com.test_servlet;

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

import com.readGmail.CreateNodeFrmDirectory_Save_Attachment_text_xml_in_Sling;
import com.readGmail.ReadEmailFromGmail;
import com.readGmail.ReadVesselDatabase;
import com.readGmail.SlingMethods;
import com.ui.data.CreateExcelMethods;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Properties;

@Component(immediate = true, metatype = false)
@Service(value = javax.servlet.Servlet.class)
@Properties({ 
		@Property(name = "sling.servlet.paths", value = { "/testtoday" }),
		})

public class Test_servlet extends SlingAllMethodsServlet {
	@Reference
	private SlingRepository repo;
	Session session = null;
	static ResourceBundle bundle = ResourceBundle.getBundle("config");

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		
		
		try{

		session = repo.login(new SimpleCredentials("admin", "admin".toCharArray()));
		
//		CreateExcelMethods.createExcelBrokerTcRate(out, "");
//		SlingMethods.readGmailDatafromsling_fetchallMails(out, session);
		JSONObject data=SlingMethods.readGmailDatafromsling(out, session);
		out.println(data);
//		ReadVesselDatabase.scorpioDataBaseSave_new(session, out);
//		ReadVesselDatabase.scorpioOwner(session, out);
		
//		SlingMethods.TonnageSave(session, out);
//		SlingMethods.SpotSave(session, out);
//		SlingMethods.TimeCharterReportSave(session, out);
//		SlingMethods.BrokerTCRateSave(session, out);
		
//		SlingMethods.TonnageNodeRemove(session, out);
//		new ReadEmailFromGmail(out);
//		CreateNodeFrmDirectory_Save_Attachment_text_xml_in_Sling.Nodecreate_saveattachment_text_xml(bundle, session, out);
        
		}catch(Exception e){
			
		    out.println(e.getMessage());
		}

	}

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

	}
	
}
