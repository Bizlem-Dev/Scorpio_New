package com.reportinformationsystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.jcr.api.SlingRepository;

import com.readGmail.GmailMethods;

import ChangedStructureCurrent.ChangedStructureCurrent_Methods;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Properties;

@Component(immediate = true, metatype = false)
@Service(value = javax.servlet.Servlet.class)
@Properties({ 
		@Property(name = "sling.servlet.paths", value = { "/reportTimeCharterReportsSave" }),
		})

public class saveTimeCharterReportsFromUi extends SlingAllMethodsServlet {
	@Reference
	private SlingRepository repo;
	Session session = null;
	static ResourceBundle bundle = ResourceBundle.getBundle("config");

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException, IOException {

		

	}

	
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		
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
				String editnodename="";
				
				ChangedStructureCurrent_Methods s=new ChangedStructureCurrent_Methods();
				
				if(jsonobjdata.has("informatio")){
					information=jsonobjdata.getString("informatio");
					information=s.check_Information(session, information, out);
				}
				
				if(jsonobjdata.has("spottc")){
					spottc=jsonobjdata.getString("spottc");
					spottc=s.check_ReportType(session, spottc, out);
				}
				
				
				if(jsonobjdata.has("date")){
					date=jsonobjdata.getString("date");
				}
				
				Node vesselNode=null;
				String objName="";
				Node obj=null;
				String vessel_id="";
				
				if(jsonobjdata.has("vesselnam")){
					Node scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
					vesselNode=scorpioDataBase.getNode("vessel");
					vesselname=jsonobjdata.getString("vesselnam");
					
					objName=s.check_vesselName_FOr_Ui_Save(session, vesselname, out);
					if(!GmailMethods.isNullString(objName)){
						if(vesselNode.hasNode(objName)){
							obj=vesselNode.getNode(objName);
						}
						if(obj.hasProperty("vessel_Id")){
							 vessel_id = obj.getProperty("vessel_Id").getString();
//							out.println("vessel_id "+vessel_id);
						}
						
						
						
					}//
					else{
						if(!vesselNode.hasNode(vesselname)){
							String NotvesselName=s.vesselSlingSave(session, out, vesselname);
							if(vesselNode.hasNode(NotvesselName)){
								Node notVesselNode=vesselNode.getNode(NotvesselName);
								
								if(notVesselNode.hasProperty("vessel_Id")){
									   vessel_id = notVesselNode.getProperty("vessel_Id").getString();
//										out.println("vessel_id "+vessel_id);
									}
							}
							
							
						}
					}
					
				}// vesselname
				
				if(jsonobjdata.has("cppdpp")){
					cppdpp=jsonobjdata.getString("cppdpp");
					cppdpp=s.check_CargoType(session, cppdpp, out);
				}
				
				if(jsonobjdata.has("chartere")){
					charterer=jsonobjdata.getString("chartere");
					charterer=s.check_Charterer(session, charterer, out);
				}
				
				if(jsonobjdata.has("delivery")){
					delivery=jsonobjdata.getString("delivery");
				}
				
				String deliveryplace="";
				if(jsonobjdata.has("deliveryplace")){
					deliveryplace=jsonobjdata.getString("deliveryplace");
					deliveryplace=s.check_DeliveryPlace(session, deliveryplace, out);
				}
				String period="";
				if(jsonobjdata.has("period")){
					period=jsonobjdata.getString("period");
				}
				String periodunit="";
				if(jsonobjdata.has("periodunit")){
					periodunit=jsonobjdata.getString("periodunit");
					periodunit=s.check_Periodunit(session, periodunit, out);
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
					unit=s.check_currencyUnit(session, unit, out);
				}
				
				String comments="";
				if(jsonobjdata.has("commentobj")){
					comments=jsonobjdata.getString("commentobj");
				}
				String source="";
				if(jsonobjdata.has("sourc")){
					source=jsonobjdata.getString("sourc");
					source=s.check_Source(session, source,out);
				}
				String vesseltype="";
				if(jsonobjdata.has("vesseltyp")){
					vesseltype=jsonobjdata.getString("vesseltyp");
					vesseltype=ChangedStructureCurrent_Methods.check_vesselType(session, vesseltype, out);
				}
				String timestamp="";
				if(jsonobjdata.has("timestamp")){
					timestamp=jsonobjdata.getString("timestamp");
				}
				
				if(jsonobjdata.has("editnodename")){
					editnodename=jsonobjdata.getString("editnodename");
				}
				if(!editnodename.equals("")){
					ReportBeforeOnWeekDataDisplay.TimecharterReportEditUi(out, session, information, spottc, date, vessel_id, cppdpp, charterer, delivery, deliveryplace, period, periodunit, redelivery, tcrate, rate, unit, comments, timestamp, source, vesseltype, editnodename);
				}else{
				
				   ReportBeforeOnWeekDataDisplay.callTimecharterReport(out, session, information, spottc, date, vessel_id, cppdpp, charterer, delivery, deliveryplace, period, periodunit, redelivery, tcrate, rate, unit, comments, timestamp, source, vesseltype);
//				UiMethods.TimeCharterReportSave(session, out, information, spottc, date, vesselname, cppdpp, charterer, delivery, deliveryplace, period, periodunit, redelivery, tcrate, rate, unit, comments, timestamp, source, vesseltype);
				
			}
				
				
			}// for
			
		}// if

		
		 
		}catch(Exception e){
//			e.printStackTrace();
		    out.println(e.getMessage());
		}

	}
	
}
