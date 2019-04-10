package com.errorreport;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.readGmail.GmailMethods;
import com.reportinformationsystem.SaveReportDataClass;

import ChangedStructureCurrent.FetchData;

public class SpotFetchErrorUi {

	public static JSONObject fetchSpotDataChanged(PrintWriter out, Session session ,String countRange) {

		JSONObject mainJsonobj = null;
		try {

			Node Other=null;
			Node ReportErrorNode=null;
			Node Spot=null;
			
			FetchData fd = new FetchData();
			Node scorpio = session.getRootNode().getNode("scorpioDataBase");
			Node ReportData = scorpio.getNode("ReportData");
			
			if(ReportData.hasNode("Other")){
				Other=ReportData.getNode("Other");
			}
			if(Other.hasNode("ReportErrorNode")){
				ReportErrorNode=Other.getNode("ReportErrorNode");
			}
			if(ReportErrorNode.hasNode("Spot")){
				Spot=ReportErrorNode.getNode("Spot");
			}

			if (Spot.hasNodes()) {
				long size = Spot.getNodes().getSize()+1;
				long countRangeI = Long.parseLong(countRange);
				String Id = "";

				JSONObject keyNameObject = null;
				JSONArray keyNameObjectJsonarray = new JSONArray();

				int i = 0;

				String netChangedPointer = "";
				String limit = "";
				if (Spot.hasProperty("netChangedPointer")) {
					netChangedPointer = Spot.getProperty("netChangedPointer").getString();
					limit = String.valueOf(Integer.parseInt(netChangedPointer) + 1);
				}
				long from= size - countRangeI;
				long size1 = from + 10;
				
				for(long count=from;count <size1;count++){
					if(Spot.hasNode(String.valueOf(count)))  {
					Node nextnode = Spot.getNode(String.valueOf(count));

					int parseId = 0;
					i++;
					String subNodeName = nextnode.getName();

					String Information = "";
					String FixtureType = "";
					String Charterer = "";
					String Status = "";
					String CargoType = "";
					String CargoGrade = "";
					String CargoQty = "";
					String LCStart = "";
					String Source = "";
					String VesselName = "";
					String LCEnd = "";
					String LoadPort = "";
					String DiscPort = "";
					String RateType = "";
					String Rate = "";
					String Comments = "";
					String dateAndTime = "";
					String Sr_No = "";
					String emailUrl = "";
					String emailClientNodeId = "";

					if (nextnode.hasProperty("Id")) {
						Id = nextnode.getProperty("Id").getString();
					}
					if (nextnode.hasProperty("Sr.No")) {
						Sr_No = nextnode.getProperty("Sr.No").getString();
					}
					if (nextnode.hasProperty("emailUrl")) {
						emailUrl = nextnode.getProperty("emailUrl").getString();
					}
					if (nextnode.hasProperty("dateAndTime")) {
						dateAndTime = nextnode.getProperty("dateAndTime").getString();
					}
					if (nextnode.hasProperty("Information")) {
						Information = nextnode.getProperty("Information").getString();
						Information = fd.fetch_Information(session, Information);

					}
					if (nextnode.hasProperty("FixtureType")) {
						FixtureType = nextnode.getProperty("FixtureType").getString();
						FixtureType = fd.fetch_ReportType(session, FixtureType);

					}
					String Charterer1="";
					if (nextnode.hasProperty("Charterer")) {
						Charterer1 = nextnode.getProperty("Charterer").getString();
						Charterer = fd.fetch_Charterer(session, Charterer1);

					}
					
					if(GmailMethods.isNullString(Charterer)){
						Charterer=Charterer1;
					}
					String Status1="";
					if (nextnode.hasProperty("Status")) {
						Status1 = nextnode.getProperty("Status").getString();
						Status = fd.fetch_EmployementStatus(session, Status1);

					}
					
					if(GmailMethods.isNullString(Status)){
						Status=Status1;
					}
					String CargoType1="";
					if (nextnode.hasProperty("CargoType")) {
						CargoType1 = nextnode.getProperty("CargoType").getString();
						CargoType = fd.fetch_CargoType(session, CargoType1);

					}
					
					if(GmailMethods.isNullString(CargoType)){
						CargoType=CargoType1;
					}
					
					
					String CargoGrade1="";
					if (nextnode.hasProperty("CargoGrade")) {
						CargoGrade1 = nextnode.getProperty("CargoGrade").getString();
						CargoGrade = fd.fetch_CargoGrade(session, CargoGrade1);

					}
					
					if(GmailMethods.isNullString(CargoGrade)){
						CargoGrade=CargoGrade1;
					}
					
					if (nextnode.hasProperty("CargoQty")) {
						CargoQty = nextnode.getProperty("CargoQty").getString();

					}
					String ReportTimestamp="";
					String ReportTimestamp1="";
					String ReportTimestamp2="";
					if (nextnode.hasProperty("ReportTimestamp")) {
						 ReportTimestamp1 = nextnode.getProperty("ReportTimestamp").getString();
						
						if(ReportTimestamp1.lastIndexOf(".")!=-1){
							ReportTimestamp1=ReportTimestamp1.substring( 0,ReportTimestamp1.lastIndexOf(".") );
//							out.println(ReportTimestamp);
							
							LocalDateTime localDateTime = LocalDateTime.parse(ReportTimestamp1);
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
							ReportTimestamp = localDateTime.format(formatter);
							
							 localDateTime = LocalDateTime.parse(ReportTimestamp1);
							 formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
							  ReportTimestamp2 = localDateTime.format(formatter);
							
						}else {
							ReportTimestamp=ReportTimestamp1;
						}
						
					}
					
					String year="";
					if(ReportTimestamp2.lastIndexOf("/")!=-1){
					   year=ReportTimestamp2.substring(ReportTimestamp2.lastIndexOf("/")+1);
					}
					
					
					if (nextnode.hasProperty("LCStart")) {
						LCStart = nextnode.getProperty("LCStart").getString();

					}
					if (nextnode.hasProperty("LCEnd")) {
						LCEnd = nextnode.getProperty("LCEnd").getString();

					}
					if(GmailMethods.isNullString(LCStart)){
						LCStart=LCEnd;
					}
					if(GmailMethods.isNullString(LCStart)){
						LCStart=LCEnd;
					}
					
					if (nextnode.hasProperty("LoadPort")) {
						String LoadPortData = nextnode.getProperty("LoadPort").getString();
						String LoadPort1 = fd.fetch_Port(session, LoadPortData);
						boolean checkjsonString=SaveReportDataClass.isJSONValid(LoadPort1);
						   if(checkjsonString==true){
							   JSONObject data=new JSONObject(LoadPort1);
							   if(data.has("portName")){
								   LoadPort=data.getString("portName");
							   }
							   
						   }
						   
						   if(GmailMethods.isNullString(LoadPort)){
							   LoadPort=LoadPortData;
						   }

					}
					
					if (nextnode.hasProperty("DiscPort")) {
						String DiscPortData = nextnode.getProperty("DiscPort").getString();
						DiscPort = fd.fetch_Port(session, DiscPortData);
						
						boolean checkjsonString=SaveReportDataClass.isJSONValid(DiscPort);
						   if(checkjsonString==true){
							   JSONObject data=new JSONObject(DiscPort);
							   if(data.has("portName")){
								   DiscPort=data.getString("portName");
							   }
							   
						   }
						   
						   if(GmailMethods.isNullString(DiscPort)){
							   DiscPort=DiscPortData;
						   }

					}
					if (nextnode.hasProperty("VesselName")) {
						String VesselNameData = nextnode.getProperty("VesselName").getString();

						VesselName = fd.fetch_vesselName(session, VesselNameData);
						
						boolean checkjsonString=SaveReportDataClass.isJSONValid(VesselName);
						  if(checkjsonString==true){
						JSONObject fetch_vesselNamejsonobject = new JSONObject(VesselName);
						if (fetch_vesselNamejsonobject.has("vessel_Id")) {
							VesselName = fetch_vesselNamejsonobject.getString("vessel_Id");
						}
						  }
						  
						  if(GmailMethods.isNullString(VesselName)){
							  VesselName=VesselNameData;
						   }
						  
					}
					if (nextnode.hasProperty("RateType")) {
						String RateTypeData = nextnode.getProperty("RateType").getString();
						RateType = fd.fetch_RateType(session, RateTypeData);

						if( GmailMethods.isNullString(RateType) ){
							RateType=RateTypeData;
						}
						
					}
					
					
					if (nextnode.hasProperty("Rate")) {
						Rate = nextnode.getProperty("Rate").getString();

					}
					if (nextnode.hasProperty("Comments")) {
						Comments = nextnode.getProperty("Comments").getString();

					}
					String ReportDate = "";
					if (nextnode.hasProperty("ReportDate")) {

						ReportDate = nextnode.getProperty("ReportDate").getString();

					}
					
					if (nextnode.hasProperty("Source")) {
						Source = nextnode.getProperty("Source").getString();
						
				        boolean numeric = true;

				        try {
				            Integer num = Integer.parseInt(Source);
				            
				        } catch (NumberFormatException e) {
				            numeric = false;
				        }

				        if(numeric){
				        	Source = fd.fetch_Source(session, Source);
				        	String houstontoclarkson="houston";
				        	if(Source.toLowerCase().equals(houstontoclarkson.toLowerCase())){
				        		Source="Clarksons";
				        	}
				        }
				            
				        else{
				        	if (nextnode.hasProperty("from_Source")) {
								Source = nextnode.getProperty("from_Source").getString();
								Source = fd.fetch_Source(session, Source);
								String houstontoclarkson="houston";
					        	if(Source.toLowerCase().equals(houstontoclarkson.toLowerCase())){
					        		Source="Clarksons";
					        	}
				        	}
				        }
				           
				    }
					
					String Vesseltype="";
					if (nextnode.hasProperty("Vesseltype")) {

						String VesseltypeData = nextnode.getProperty("Vesseltype").getString();
						Vesseltype = FetchData.fetch_vesselType(session, VesseltypeData);
						if(GmailMethods.isNullString(Vesseltype)){
							Vesseltype=VesseltypeData;
						}
						
					}
					if (nextnode.hasProperty("emailClientNodeId")) {
						emailClientNodeId = nextnode.getProperty("emailClientNodeId").getString();

					}
					String vesselFlag = "";
					if (nextnode.hasProperty("vesselFlag")) {

						vesselFlag = nextnode.getProperty("vesselFlag").getString();

					}
					String flagPort = "";
					if (nextnode.hasProperty("flagPort")) {

						flagPort = nextnode.getProperty("flagPort").getString();

					}

					keyNameObject = new JSONObject();
					keyNameObject.put("spotId", Id);
					keyNameObject.put("dateAndTime", dateAndTime);
					keyNameObject.put("Information", Information.toUpperCase());
					keyNameObject.put("FixtureType", FixtureType.toUpperCase());
					keyNameObject.put("LCStart", LCStart);
					keyNameObject.put("LCEnd", LCEnd);
					keyNameObject.put("LoadPort", LoadPort.toUpperCase());
					keyNameObject.put("DiscPort", DiscPort.toUpperCase());
					keyNameObject.put("VesselName", VesselName.toUpperCase());
					keyNameObject.put("Charterer", Charterer.toUpperCase());
					keyNameObject.put("Status", Status.toUpperCase());
					keyNameObject.put("CargoType", CargoType.toUpperCase());
					keyNameObject.put("CargoGrade", CargoGrade.toUpperCase());
					keyNameObject.put("CargoQty", CargoQty);
					keyNameObject.put("RateType", RateType.toUpperCase());
					keyNameObject.put("Rate", Rate);
					keyNameObject.put("Comments", Comments.toUpperCase());
					keyNameObject.put("ReportDate", ReportDate);
					keyNameObject.put("Source", Source.toUpperCase());
					keyNameObject.put("Vesseltype", Vesseltype.toUpperCase());
					keyNameObject.put("subNodeName", subNodeName);
					keyNameObject.put("size", size + 1);// parseId
					keyNameObject.put("parseId", parseId + 1);
					keyNameObject.put("i", i);
					keyNameObject.put("Sr_No", Sr_No);
					keyNameObject.put("emailUrl", emailUrl);
					keyNameObject.put("emailClientNodeId", emailClientNodeId);
					keyNameObject.put("vesselFlag", vesselFlag);
					keyNameObject.put("flagPort", flagPort);
					keyNameObject.put("ReportTimestamp", ReportTimestamp);

					if (keyNameObject.length() > 0) {
						keyNameObjectJsonarray.put(keyNameObject);
					}

					limit = String.valueOf(Integer.parseInt(limit) + 1);
				} 
				}// while close (netpointer)

					mainJsonobj = new JSONObject();
					mainJsonobj.put("spotList", keyNameObjectJsonarray);


			} // node check close if

		} catch (Exception e) {
			e.printStackTrace(out);
//			out.println(e.getMessage());

		}
		return mainJsonobj;

	}
}
