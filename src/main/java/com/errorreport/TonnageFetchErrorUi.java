package com.errorreport;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.readGmail.GmailMethods;
import com.reportinformationsystem.ReportBeforeOnWeekDataDisplay;
import com.reportinformationsystem.SaveReportDataClass;

import ChangedStructureCurrent.FetchData;

public class TonnageFetchErrorUi {

	public static JSONObject fetchTonnageDataChanged(PrintWriter out, Session session,String countRange) {

		JSONObject mainJsonobj = null;
		try {
			Node Other=null;
			Node ReportErrorNode=null;
			Node Tonnage=null;
			
			FetchData fd = new FetchData();
			Node scorpio = session.getRootNode().getNode("scorpioDataBase");
			Node ReportData = scorpio.getNode("ReportData");
			
			if(ReportData.hasNode("Other")){
				Other=ReportData.getNode("Other");
			}
			if(Other.hasNode("ReportErrorNode")){
				ReportErrorNode=Other.getNode("ReportErrorNode");
			}
			if(ReportErrorNode.hasNode("Tonnage")){
				Tonnage=ReportErrorNode.getNode("Tonnage");
			}
			
			if (Tonnage.hasNodes()) {
				long size = Tonnage.getNodes().getSize()+1;
				long countRangeI=Long.valueOf(countRange);
				JSONObject keyNameObject = null;

				JSONArray keyNameObjectJsonarray = new JSONArray();

				boolean nodeFlag = false;
				int i = 0;
				long from= size - countRangeI;
				long size1 = from + 10;
				for(long count=from;count <size1;count++){
					nodeFlag = true;
					if( Tonnage.hasNode(String.valueOf(count)) ){
					Node nextnode = Tonnage.getNode(String.valueOf(count));
					int parseId = 0;
					i++;
					String subNodeName = nextnode.getName();

					String EmploymentStatus = "";
					String Owners = "";
					String OpenPort = "";
					String Operators = "";
					String Id = "";
					String CargoType = "";
					String Comment = "";
					String ETABasis = "";
					String OpenDate = "";
					String ReportType = "";
					String ReportTimestamp="";
					String RepositionRegion = "";
					String Source = "";
					String VesselName = "";
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
					if (nextnode.hasProperty("dateAndTime")) {
						dateAndTime = nextnode.getProperty("dateAndTime").getString();
					}
					if (nextnode.hasProperty("EmploymentStatus")) {
						String EmploymentStatus1 = nextnode.getProperty("EmploymentStatus").getString();
						EmploymentStatus = fd.fetch_EmployementStatus(session, EmploymentStatus1);

						if(GmailMethods.isNullString(EmploymentStatus) ){
							EmploymentStatus=EmploymentStatus1;
						}
						
					}
					
					if (nextnode.hasProperty("Owners")) {
						String Owners1 = nextnode.getProperty("Owners").getString();
						Owners = FetchData.fetch_Owners(session, Owners1);

						if(GmailMethods.isNullString(Owners) ){
							Owners=Owners1;
						}
						
					}
					if (nextnode.hasProperty("emailUrl")) {
						emailUrl = nextnode.getProperty("emailUrl").getString();

					}
					if (nextnode.hasProperty("emailClientNodeId")) {
						emailClientNodeId = nextnode.getProperty("emailClientNodeId").getString();

					}

						if (nextnode.hasProperty("RepositionRegion")) {
							String RepositionRegion12 = nextnode.getProperty("RepositionRegion").getString();

							String RepositionRegion1 = fd.fetch_Port(session, RepositionRegion12);
							boolean checkjsonString = SaveReportDataClass.isJSONValid(RepositionRegion1);
							if (checkjsonString == true) {
								JSONObject data = new JSONObject(RepositionRegion1);
								
								if (data.has("portName")) {
									RepositionRegion = data.getString("portName");
								}

							}
							
							if(GmailMethods.isNullString(RepositionRegion) ){
								RepositionRegion=RepositionRegion12;
							}

						} else {
							RepositionRegion = "";
						}

//					} // blank
					
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

					if (nextnode.hasProperty("Operators")) {
						String Operators1 = nextnode.getProperty("Operators").getString();
						Operators = fd.fetch_Operators(session, Operators1);

						if(GmailMethods.isNullString(Operators) ){
							Operators=Operators1;
						}
					}
					if (nextnode.hasProperty("CargoType")) {
						String CargoType1 = nextnode.getProperty("CargoType").getString();
						CargoType = fd.fetch_CargoType(session, CargoType1);

						if(GmailMethods.isNullString(CargoType) ){
							CargoType=CargoType1;
						}
					}
					if (nextnode.hasProperty("Comment")) {
						Comment = nextnode.getProperty("Comment").getString();

					}
					if (nextnode.hasProperty("ETABasis")) {
						 ETABasis = nextnode.getProperty("ETABasis").getString();
					}
					
					String date="";
					if (nextnode.hasProperty("OpenPort")) {
						String OpenPort12 = nextnode.getProperty("OpenPort").getString();
						String OpenPort1 = fd.fetch_Port(session, OpenPort12);

						boolean checkjsonString = SaveReportDataClass.isJSONValid(OpenPort1);
						if (checkjsonString == true) {
							JSONObject data = new JSONObject(OpenPort1);
							if (data.has("portName")) {
								OpenPort = data.getString("portName");
							}
							
						}
						
						if(GmailMethods.isNullString(OpenPort) ){
							OpenPort=OpenPort12;
							
				 	} // check null

					}
					
					
					String OpenDate1="";
					if (nextnode.hasProperty("OpenDate")) {
						OpenDate= nextnode.getProperty("OpenDate").getString();

					}
					if (nextnode.hasProperty("ReportType")) {
						ReportType = nextnode.getProperty("ReportType").getString();
						ReportType = fd.fetch_ReportType(session, ReportType);

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
					
					String Flag="";
					if (nextnode.hasProperty("VesselName")) {
						String VesselName1 = nextnode.getProperty("VesselName").getString();
						VesselName = fd.fetch_vesselName(session, VesselName1);
//						if( !GmailMethods.isNullString(VesselName) ){
						boolean checkjsonString = SaveReportDataClass.isJSONValid(VesselName);
						if (checkjsonString == true) {
							JSONObject fetch_vesselNamejsonobject = new JSONObject(VesselName);
							if (fetch_vesselNamejsonobject.has("vessel_Id")) {
								 VesselName = fetch_vesselNamejsonobject.getString("vessel_Id");
								  
							}
						}
						
						if(GmailMethods.isNullString(VesselName)){
							VesselName=VesselName1;
						}
						

					}
					
					String Built = "";
					if (nextnode.hasProperty("Built")) {

						String Built1 = nextnode.getProperty("Built").getString();
						
                         boolean booleanOpenDate = Pattern.matches("(\\d{2}\\-\\d{2}\\-\\d{4})", Built1);
						
						if(booleanOpenDate==true){
							if(Built1.lastIndexOf("-")!=-1){
							   int d=Built1.lastIndexOf("-");
							   Built=Built1.substring(d+1);
							}else{
								Built=Built1;
							}
						}else {
							Built=Built1;
						}

					}
					String DWT = "";
					if (nextnode.hasProperty("DWT")) {

						String DWT1 = nextnode.getProperty("DWT").getString();
						
						boolean booleandwt = Pattern.matches("(\\d{1,3})$", DWT1);
						
						if(booleandwt==true){
							int intDWT1=Integer.parseInt(DWT1);
							int dwtInt=intDWT1*1000;
							DWT=String.valueOf(dwtInt);
						}else{
							 booleandwt = Pattern.matches("(\\d{1,3}\\.\\d{2})", DWT1);
							if(booleandwt==true){
								int intDWT1=Integer.parseInt(DWT1);
								int dwtInt=intDWT1*1000;
								DWT=String.valueOf(dwtInt);
								
							}else{
								DWT=DWT1;
							}
						}

					}
					String Cubics = "";
					if (nextnode.hasProperty("Cubics")) {

						String Cubics1 = nextnode.getProperty("Cubics").getString();

                          boolean booleandwt = Pattern.matches("(\\d{1,3})$", Cubics1);
						
						if(booleandwt==true){
							int intDWT1=Integer.parseInt(Cubics1);
							int dwtInt=intDWT1*1000;
							Cubics=String.valueOf(dwtInt);
						}else{
							 booleandwt = Pattern.matches("(\\d{1,3}\\.\\d{2})", Cubics1);
							if(booleandwt==true){
								int intDWT1=Integer.parseInt(Cubics1);
								int dwtInt=intDWT1*1000;
								Cubics=String.valueOf(dwtInt);
								
							}else{
								Cubics=Cubics1;
							}
						}
						
					}
					String LOA = "";
					if (nextnode.hasProperty("LOA")) {

						LOA = nextnode.getProperty("LOA").getString();

					}

					String ICE = "";
					if (nextnode.hasProperty("ICE")) {

						ICE = nextnode.getProperty("ICE").getString();

					}

					String SternLine = "";
					if (nextnode.hasProperty("SternLine")) {

						SternLine = nextnode.getProperty("SternLine").getString();

					}
					String VesselType = "";
					if (nextnode.hasProperty("VesselType")) {

						String VesselType1 = nextnode.getProperty("VesselType").getString();
						VesselType = FetchData.fetch_vesselType(session, VesselType1);
						if(GmailMethods.isNullString(VesselType)){
							VesselType=VesselType1;
						}
						
					}
					String vesselFlag = "";
					if (nextnode.hasProperty("vesselFlag")) {

						vesselFlag = nextnode.getProperty("vesselFlag").getString();

					}
					String flagPort = "";
					if (nextnode.hasProperty("flagOpenPort")) {

						flagPort = nextnode.getProperty("flagOpenPort").getString();

					}
					

					keyNameObject = new JSONObject();

						keyNameObject.put("OpenDate", OpenDate);
						keyNameObject.put("tonnageId", Id);
						keyNameObject.put("dateAndTime", dateAndTime);
						keyNameObject.put("EmploymentStatus", EmploymentStatus.toUpperCase());
						keyNameObject.put("Owners", Owners.toUpperCase());
						keyNameObject.put("OpenPort", OpenPort.toUpperCase());
						keyNameObject.put("Operators", Operators.toUpperCase());
						keyNameObject.put("CargoType", CargoType.toUpperCase());
						keyNameObject.put("Comment", Comment.toUpperCase());
						keyNameObject.put("ETABasis", ETABasis);
						
						keyNameObject.put("ReportType", ReportType.toUpperCase());
						keyNameObject.put("ReportTimestamp", ReportTimestamp);
						keyNameObject.put("RepositionRegion", RepositionRegion.toUpperCase());
						keyNameObject.put("Source", Source.toUpperCase());
						keyNameObject.put("VesselName", VesselName.toUpperCase());
						keyNameObject.put("Built", Built);
						keyNameObject.put("DWT", DWT);
						keyNameObject.put("Cubics", Cubics);
						keyNameObject.put("LOA", LOA);
						keyNameObject.put("ICE", ICE.toUpperCase());
						keyNameObject.put("SternLine", SternLine);
						keyNameObject.put("VesselType", VesselType.toUpperCase());
						keyNameObject.put("subNodeName", subNodeName);
						keyNameObject.put("size", size + 1);// parseId
						keyNameObject.put("parseId", parseId + 1);
						keyNameObject.put("i", i);
						keyNameObject.put("Sr_No", Sr_No);
						keyNameObject.put("emailUrl", emailUrl);
						keyNameObject.put("emailClientNodeId", emailClientNodeId);
						keyNameObject.put("vesselFlag", vesselFlag);
						keyNameObject.put("flagOpenPort", flagPort);

					if (keyNameObject.length() > 0) {
						keyNameObjectJsonarray.put(keyNameObject);
					}
					

				} 
			}// while close (netpointer)
 
					mainJsonobj = new JSONObject();
					mainJsonobj.put("tonnageList", keyNameObjectJsonarray);

			} // node check close if

		} catch (Exception e) {
			e.printStackTrace(out);
//			out.println(e.getMessage());

		}
		return mainJsonobj;

	}
	
	
	
}
