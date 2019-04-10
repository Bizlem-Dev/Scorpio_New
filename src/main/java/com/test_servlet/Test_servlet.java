package com.test_servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.jcr.api.SlingRepository;

import com.abhishek.UnseenMailReadProcess;
import com.abhishek.logCheck;
import com.anagha.ReadExcelData_new;
import com.pallavi.code.secondStepMethodCall;
import com.readGmail.CreateNodeFrmDirectory_Save_Attachment_text_xml_in_Sling;
import com.readGmail.ExcelReadMethods;
import com.readGmail.Gmail_Pojo;
import com.readGmail.ReadEmailFromGmail;
import com.readGmail.ReadVesselDatabase;
import com.readGmail.SlingMethods;
import com.reportinformationsystem.DateFromToApiReport;
import com.reportinformationsystem.ExpertScriptCall;
import com.reportinformationsystem.FifteenMinuteClass;
import com.reportinformationsystem.ReportBeforeOnWeekDataDisplay;
import com.reportinformationsystem.SaveReportDataClass;
import com.reportinformationsystem.fetchAllReport;
import com.srsimages.imageUpload;
import com.ui.data.CreateExcelMethods;

import ChangedStructureCurrent.ChangedStructureCurrent_Methods;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.log4j.Logger;
import org.apache.felix.scr.annotations.Properties;

@Component(immediate = true, metatype = false)
@Service(value = javax.servlet.Servlet.class)
@Properties({ 
	    @Property(name = "service.description", value = "Save product Servlet"),
	    @Property(name = "service.vendor", value = "VISL Company"),
		@Property(name = "sling.servlet.paths", value = { "/servlet/service/testtoday" }),
		@Property(name = "sling.servlet.resourceTypes", value = "sling/servlet/default"),//sling/servlet/default
		@Property(name = "sling.servlet.extensions", value = "data"),
		})

public class Test_servlet extends SlingAllMethodsServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Reference
	private SlingRepository repo;
	
	
	static ResourceBundle bundle = ResourceBundle.getBundle("config");
	//final static Logger logger = Logger.getLogger(Test_servlet.class);
	Session session = null;
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		if (request.getRequestPathInfo().getExtension().equals("data")) {		
		
		try{
			
		session = repo.login(new SimpleCredentials("admin", "admin".toCharArray()));
		/*out.println("hii");
		logger.info("hii");*/
		//ReadExcelData_new.chartererDataSlingInsert(out, session);
//		logCheck.data();
		
		//out.println(logger.getRoot());
//		String fileName = "/home/ubuntu/pyscript/Missing Ports 22 Dec.xls";
//		fetchAllReport.pdfTonnage(session, out);
		//DateFromToApiReport.setFlagZero(session, out);
		/*FifteenMinuteClass.fetchVesselForSolr(out, session);
		FifteenMinuteClass.fetchCargograde(out, session);
		FifteenMinuteClass.fetchCargogradeOperators(out, session);
		FifteenMinuteClass.fetchCargogradeowners(out, session);
		FifteenMinuteClass.fetchCargogradePort(out, session);
		FifteenMinuteClass.Date(out);*/
		
		//SaveReportDataClass d=new SaveReportDataClass();
		/*d.parseAllReportFromJsonToSave(out, session, "", "", "", "", "", "", "", "");*/
		
//		fetchAllReport.data(session);
//		FifteenMinuteClass.portNotMasterupdatehttps(out, session);
//		DateFromToApiReport.ReadGmailDataChangeHttps(session, out);
//		FifteenMinuteClass.portNotMasterDelete4(out, session);
//		fetchAllReport.pdfTonnage(session, out);
		fetchAllReport.fetchTonnage_37KData(out, session);
		//fetchAllReport.fetchSpotDataChangedNew(out, session);
		//DateFromToApiReport.ReadGmailDataChangeHttps(session, out);
	/*	Gmail_Pojo gm = null;
		gm = new Gmail_Pojo();
		gm.setProtocol(bundle.getString("protocol"));
		gm.setHost(bundle.getString("host"));
		gm.setPort(bundle.getString("port"));
		gm.setUserName(bundle.getString("userName"));
		gm.setPassWord(bundle.getString("password"));
		UnseenMailReadProcess.processEmail(gm, out);*/
		
//		FifteenMinuteClass.portNotMaster(out, session);
//		FifteenMinuteClass.portNotMaster(out, session);
		
		//String fileName = "/home/ubuntu/pyscript/lucene-export.xls"; //Missing Ports 22 Dec.xls
		//String fileName = "/home/ubuntu/pyscript/lucene-export12.xls";
//		ExcelReadMethods.readFile(fileName, out, session);
		
//		ExcelReadMethods.data(out, session);
		//DateFromToApiReport.setFlagZero(session, out);
		//String filePath="/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/UPDATED_LR2_POSITION_LISTS_BSS_FUJAIRAH_1.html";
		/*String filePath="/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/UPDATED_LR2_POSITION_LISTS_BSS_FUJAIRAH_1_FUJAIRAHLR2_POSITION_LIST_23_OCT.xlsx";
		String json=ExpertScriptCall.postExpertScript(filePath);
		out.println(json);*/
		
		
		
		/*d.parseAllReportFromJsonToSave(out, session, "", "", "", "", "", "", "", "");*/
		
		//ChangedStructureCurrent_Methods d1=new ChangedStructureCurrent_Methods();
		//d1.scorpioDataBaseSave_new(session, out);
		//Node scorpio=null;
//		Node vessel=null;
		
		/*if(session.getRootNode().hasNode("scorpioDataBase")){
			scorpio = session.getRootNode().getNode("scorpioDataBase");
			rename(scorpio, "scorpioDataBase_16_feb_19");
		}*/
		
		
		/*if(scorpio.hasNode("Port")){
			Node vessel=scorpio.getNode("Port");
			rename(vessel, "Port_25_dec");
			session.save();
//			vessel.remove();
//			session.save();
		}
		
		if(scorpio.hasNode("vessel")){
			Node vessel=scorpio.getNode("vessel");
			rename(vessel, "vessel_27");
			session.save();
//			vessel.remove();
//			session.save();
		}*/
		 
		
		
		//d1.scorpioPort(session, out);
		
		
		
//		ReportBeforeOnWeekDataDisplay.fetch(out, session);
		/*ReportBeforeOnWeekDataDisplay.fetchBrokertcrate(out, session);
		ReportBeforeOnWeekDataDisplay.fetchSpot(out, session);
		ReportBeforeOnWeekDataDisplay.fetchTimecharter(out, session);*/
		/*String path=request.getServletPath();
		request.getRequestDispatcher("/test/.test2").forward(request, response);
		out.println("save "+path);*/
		
//		CreateExcelMethods.createExcelBrokerTcRate(out, "");
//		SlingMethods.readGmailDatafromsling_fetchallMails(out, session);
		/*JSONArray data=SlingMethods.readGmailDatafromsling(out, session);
		out.println(data);*/
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

		}// extension
	}

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

	}
	
	public static void rename(Node node, String newName) throws RepositoryException 
	{
		node.getSession().move(node.getPath(), node.getParent().getPath() + "/" + newName);
	}
	
}
