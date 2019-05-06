package com.reportinformationsystem;

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

import com.mongocode.RemoveNodeFifteenDays;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Properties;

@Component(immediate = true, metatype = false)
@Service(value = javax.servlet.Servlet.class)
@Properties({ 
		@Property(name = "sling.servlet.paths", value = { "/newReport" }),
		})

public class CommonServlet extends SlingAllMethodsServlet {
	@Reference
	private SlingRepository repo;
	Session session = null;
	static ResourceBundle bundle = ResourceBundle.getBundle("config");

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		
		
		try{

		session = repo.login(new SimpleCredentials("admin", "admin".toCharArray()));
		
		SaveReportDataClass SRDC=new SaveReportDataClass();
		DateFromToApiReport DFTAR=new DateFromToApiReport();
		
		//DateFromToApiReport.setFlagZero(session, out);
		
		//RemoveNodeFifteenDays.removeFifteenDaysbeforeData(session, "", out, -15, currentDate);
		
		/*JSONArray obj=DateFromToApiReport.ReadGmailDataToShowOnUi(session, out);
		out.println(obj);*/
//		DateFromToApiReport.ReadGmailDataToPassPythonApi(session, out);
//		SRDC.parseAllReportFromJsonToSave(out, session, "");
		
		/*JSONObject data=ChangedApiWithoutMultipleArray.fetchBrokerTcRateDataChanged(out, session);
		out.println(data);*/
		/*JSONObject obj=SRDC.getSpotReport(session, "Spot", out);
		out.println(obj);*/
		/*JSONObject obj=SaveReportDataClass.fetchTonnageData(out, session);
		out.println(obj);*/
		/*JSONObject obj=SRDC.getSpotReport(session, "Spot", out);
		out.println(obj);*/
		
		/*JSONObject obj=DFTAR.getTonnageReportDate(session, "09-11-2018", "30-11-2018", "Tonnage", out);
		out.println(obj);*/
		
       // out.println("below save");
		}catch(Exception e){
			out.println(e.getMessage());
		}

	}

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

	}
	
}
