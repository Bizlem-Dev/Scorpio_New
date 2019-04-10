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
		@Property(name = "sling.servlet.paths", value = { "/reportTonnageSave" }),
		})

public class saveTonnageFromUi extends SlingAllMethodsServlet {
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
				
				String check_ReportType_id="";
				String check_Port_repositionRegion_id="";
				String check_CargoType_cargotype_id="";
				String vesselname="";
				String vesseltype="";
				String built="";
				String dwt="";
				String editnodename="";
				
				ChangedStructureCurrent_Methods CSC=new ChangedStructureCurrent_Methods();
				
				if(jsonobjdata.has("reporttype")){
					String reporttype=jsonobjdata.getString("reporttype");
					check_ReportType_id=CSC.check_ReportType(session, reporttype, out);
				}
				
				if(jsonobjdata.has("repositionregion")){
					String repositionregion=jsonobjdata.getString("repositionregion");
					check_Port_repositionRegion_id=CSC.check_Port(session, repositionregion, out);
					 boolean checkjsonString=SaveReportDataClass.isJSONValid(check_Port_repositionRegion_id);
					   if(checkjsonString==true){
						   JSONObject data=new JSONObject(check_Port_repositionRegion_id);
						   if(data.has("port_id")){
							   check_Port_repositionRegion_id=data.getString("port_id");
						   }
				}
				}
				
				
				if(jsonobjdata.has("cargotype")){
					String cargotype=jsonobjdata.getString("cargotype");
					check_CargoType_cargotype_id=CSC.check_CargoType(session, cargotype,out);
				}
				
				ChangedStructureCurrent_Methods s=new ChangedStructureCurrent_Methods();
				String objName="";
				String cubics="";
				String loa="";
				String ice="";
				String sternline="";
				String owners="";
				Node vesselNode=null;
				
				String vessel_id="";
				
				if(jsonobjdata.has("vesselname")){
					Node scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
					vesselNode=scorpioDataBase.getNode("vessel");
					vesselname=jsonobjdata.getString("vesselname");
					objName=s.check_vesselName_FOr_Ui_Save(session, vesselname, out);
					if(!GmailMethods.isNullString(objName)){
						Node obj=null;
					if(vesselNode.hasNode(objName)){
						obj=vesselNode.getNode(objName);
					}
					
					if(obj.hasProperty("vessel_Id")){
						 vessel_id = obj.getProperty("vessel_Id").getString();
//						out.println("vessel_id "+vessel_id);
					}
					/*
					if(obj.hasProperty("built")){
						 built = obj.getProperty("built").getString();
						
					}
					if(obj.hasProperty("cubics")){
						cubics = obj.getProperty("cubics").getString();
						
					}
					if(obj.hasProperty("dwt")){
						 dwt = obj.getProperty("dwt").getString();
						
					}
					if(obj.hasProperty("ice")){
						 ice = obj.getProperty("ice").getString();
						
					}
					if(obj.hasProperty("loa")){
						 loa = obj.getProperty("loa").getString();
						
					}
					if(obj.hasProperty("owners_id")){
						 owners = obj.getProperty("owners_id").getString();
						
					}
					if(obj.hasProperty("sternline")){
						 sternline = obj.getProperty("sternline").getString();
						
					}
					if(obj.hasProperty("vesselType_id")){
						vesseltype = obj.getProperty("vesselType_id").getString();
						
					}*/
				}
					else{
						
						if(!vesselNode.hasNode(vesselname)){
							String NotvesselName=s.vesselSlingSave(session, out, vesselname);
							if(vesselNode.hasNode(NotvesselName)){
							    Node notVesselNode=vesselNode.getNode(NotvesselName);
							
							
							/*if(jsonobjdata.has("vesseltype")){
								vesseltype=jsonobjdata.getString("vesseltype");
								
								vesseltype=ChangedStructureCurrent_Methods.check_vesselType(session, vesseltype, out);
								notVesselNode.setProperty("vesselType_id", vesseltype);
								session.save();
							}*/
							if(notVesselNode.hasProperty("vessel_Id")){
							   vessel_id = notVesselNode.getProperty("vessel_Id").getString();
//								out.println("vessel_id "+vessel_id);
							}
							/*if(jsonobjdata.has("built")){
								built=jsonobjdata.getString("built");
								notVesselNode.setProperty("built", built);
								session.save();
							}
							
							if(jsonobjdata.has("dwt")){
								dwt=jsonobjdata.getString("dwt");
								notVesselNode.setProperty("dwt", dwt);
								session.save();
							}
							
							if(jsonobjdata.has("cubics")){
								cubics=jsonobjdata.getString("cubics");
								notVesselNode.setProperty("cubics", cubics);
								session.save();
							}
							
							if(jsonobjdata.has("loa")){
								loa=jsonobjdata.getString("loa");
								notVesselNode.setProperty("loa", loa);
								session.save();
							}
							
							if(jsonobjdata.has("ice")){
								ice=jsonobjdata.getString("ice");
								notVesselNode.setProperty("ice", ice);
								session.save();
							}
							
							if(jsonobjdata.has("sternline")){
								sternline=jsonobjdata.getString("sternline");
								notVesselNode.setProperty("sternline", sternline);
								session.save();
							}
							
							if(jsonobjdata.has("owners")){
								owners=jsonobjdata.getString("owners");
								owners=ChangedStructureCurrent_Methods.check_Owners(session, owners,out);
								notVesselNode.setProperty("owners_id", owners);
								session.save();
							}*/
						}
						
					}//
				}//
					
				} // vesselname
				
				
				if(jsonobjdata.has("vesseltype")){
					vesseltype=jsonobjdata.getString("vesseltype");
					
					vesseltype=ChangedStructureCurrent_Methods.check_vesselType(session, vesseltype, out);
					
				}
				if(jsonobjdata.has("built")){
					built=jsonobjdata.getString("built");
					
				}
				
				if(jsonobjdata.has("dwt")){
					dwt=jsonobjdata.getString("dwt");
					
				}
				
				if(jsonobjdata.has("cubics")){
					cubics=jsonobjdata.getString("cubics");
					
				}
				
				if(jsonobjdata.has("loa")){
					loa=jsonobjdata.getString("loa");
					
				}
				
				if(jsonobjdata.has("ice")){
					ice=jsonobjdata.getString("ice");
					
				}
				
				if(jsonobjdata.has("sternline")){
					sternline=jsonobjdata.getString("sternline");
					
				}
				
				if(jsonobjdata.has("owners")){
					owners=jsonobjdata.getString("owners");
					owners=ChangedStructureCurrent_Methods.check_Owners(session, owners,out);
					
				}
				
				String operators="";
				if(jsonobjdata.has("operators")){
					operators=jsonobjdata.getString("operators");
					operators=CSC.check_Operators(session, operators,out);
				}
				String employementstatus="";
				if(jsonobjdata.has("employementstatus")){
					employementstatus=jsonobjdata.getString("employementstatus");
					employementstatus=CSC.check_EmployementStatus(session, employementstatus,out);
				}
				String openport="";
				if(jsonobjdata.has("openport")){
					openport=jsonobjdata.getString("openport");
					openport=CSC.check_Port(session, openport, out);
					boolean checkjsonString=SaveReportDataClass.isJSONValid(openport);
					   if(checkjsonString==true){
						   JSONObject data=new JSONObject(openport);
						   if(data.has("port_id")){
							   openport=data.getString("port_id");
						   }
				}
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
					source=CSC.check_Source(session, source,out);
				}
				String reporttimestamp="";
				if(jsonobjdata.has("reporttimestamp")){
					reporttimestamp=jsonobjdata.getString("reporttimestamp");
				}
				
				if(jsonobjdata.has("editnodename")){
					editnodename=jsonobjdata.getString("editnodename");
				}
				if(!editnodename.equals("")){
					ReportBeforeOnWeekDataDisplay.TonnageReportEditUi(out, session, check_ReportType_id, check_Port_repositionRegion_id, check_CargoType_cargotype_id, vessel_id, vesseltype, built, dwt, cubics, loa, ice, sternline, owners, operators, employementstatus, openport, opendate, etabasis, comment, source, reporttimestamp, editnodename);
				}else{
				
				    ReportBeforeOnWeekDataDisplay.TonnageReportSaveUi(out, session, check_ReportType_id, check_Port_repositionRegion_id, check_CargoType_cargotype_id, vessel_id, vesseltype, built, dwt, cubics, loa, ice, sternline, owners, operators, employementstatus, openport, opendate, etabasis, comment, source, reporttimestamp);
//				UiMethods.TonnageSave(session, out, reporttype, repositionregion, cargotype, vesselname, vesseltype, built, dwt, cubics, loa, ice, sternline, owners, operators, employementstatus, openport, opendate, etabasis, comment, source, reporttimestamp);
			}
				
			}// for
			
		}// if

		
		 
		}catch(Exception e){
			e.printStackTrace();
		    out.println(e.getMessage());
		}

	}
	
}
