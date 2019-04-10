package com.reportinformationsystem;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.Workspace;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.readGmail.GmailMethods;
import com.readGmail.SlingMethods;

import ChangedStructureCurrent.FetchData;

public class ReportBeforeOnWeekDataDisplay {

	public static void main(String[] args) {
		Date d=getLastWeek1();
		System.out.println(d);
	}
	
	public static Date getLastWeek1() {
	    GregorianCalendar dayBeforeThisWeek = new GregorianCalendar();
	    int dayFromMonday = (dayBeforeThisWeek.get(Calendar.DAY_OF_WEEK) + 7 - Calendar.MONDAY) % 7;
	    dayBeforeThisWeek.add(Calendar.DATE, -dayFromMonday-6); //-6
	    return dayBeforeThisWeek.getTime();
	  }
	
	public static JSONArray getTonnageReportBeforeOneWeek(Session session,String currentDate, String lastWeekDate,PrintWriter out) {

//		JSONObject mainJsonobj=null;
		JSONArray keyNameObjectJsonarray=null;
		try {
			
			if( !GmailMethods.isNullString(currentDate) && !GmailMethods.isNullString(lastWeekDate) ){

					
					FetchData fd = new FetchData();
					/*String reporttypequeryid = fd.check_ReportTypeToFetchapi(session, reportType, out);*/
					/*String dataCurrentDate=FetchData.convertStringToDate(currentDate);
					String dataLastWeekDate=FetchData.convertStringToDate(lastWeekDate);*/
					Workspace workspace = session.getWorkspace();

					String slingqery = "select [dateAndTime] from [nt:base] where dateAndTime >='" + lastWeekDate
							+ "'and dateAndTime <='" + currentDate 
							+  "' and ISDESCENDANTNODE('/scorpioDataBase/ReportData/Tonnage/')";
					Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
					QueryResult queryResult = query.execute();
					NodeIterator iterator = queryResult.getNodes();
					boolean nodeFlag = false;

					JSONObject keyNameObject=null;
					 keyNameObjectJsonarray=new JSONArray();
					int i=0;
					while (iterator.hasNext()) {
					i++;
						nodeFlag = true;
						Node nextnode = iterator.nextNode();
						String subNodeName=nextnode.getName();
						
						String EmploymentStatus="";
						String Owners="";
						String OpenPort="";
						String Operators="";
						String Id="";
						String CargoType="";
						String Comment="";
						String ETABasis="";
						String OpenDate="";
						String ReportType="";
						String ReportTimestamp="";
						String RepositionRegion="";
						String Source="";
						String VesselName="";
						String Sr_No="";
						String dateAndTime="";
						String emailUrl="";
						
						if(nextnode.hasProperty("Id")){
							Id=nextnode.getProperty("Id").getString();
						}
						if(nextnode.hasProperty("Sr.No")){
							Sr_No=nextnode.getProperty("Sr.No").getString();
						}
						if(nextnode.hasProperty("dateAndTime")){
							dateAndTime=nextnode.getProperty("dateAndTime").getString();
						}if(nextnode.hasProperty("emailUrl")){
							emailUrl=nextnode.getProperty("emailUrl").getString();
						}
						
						if(nextnode.hasProperty("EmploymentStatus")){
							EmploymentStatus=nextnode.getProperty("EmploymentStatus").getString();
							EmploymentStatus=fd.fetch_EmployementStatus(session, EmploymentStatus);
							
						}
						if(nextnode.hasProperty("Owners")){
							Owners=nextnode.getProperty("Owners").getString();
							Owners=FetchData.fetch_Owners(session, Owners);
							
						}
						/*if(nextnode.hasProperty("OpenPort")){
							OpenPort=nextnode.getProperty("OpenPort").getString();
							OpenPort=fd.fetch_Port(session, OpenPort);
							
						}*/
						if(nextnode.hasProperty("Operators")){
							Operators=nextnode.getProperty("Operators").getString();
							Operators=fd.fetch_Operators(session, Operators);
							
						}
						if(nextnode.hasProperty("CargoType")){
							CargoType=nextnode.getProperty("CargoType").getString();
							CargoType=fd.fetch_CargoType(session, CargoType);
							
						}
						if(nextnode.hasProperty("Comment")){
							Comment=nextnode.getProperty("Comment").getString();
							
						}
						if(nextnode.hasProperty("ETABasis")){
							ETABasis=nextnode.getProperty("ETABasis").getString();
							
						}
						if(nextnode.hasProperty("OpenDate")){
							OpenDate=nextnode.getProperty("OpenDate").getString();
							
						}
						if(nextnode.hasProperty("ReportType")){
							ReportType=nextnode.getProperty("ReportType").getString();
							ReportType=fd.fetch_ReportType(session, ReportType);
							
						}
						if(nextnode.hasProperty("ReportTimestamp")){
							ReportTimestamp=nextnode.getProperty("ReportTimestamp").getString();
							
						}
						/*if(nextnode.hasProperty("RepositionRegion")){
							RepositionRegion=nextnode.getProperty("RepositionRegion").getString();
							RepositionRegion=fd.fetch_Port(session, RepositionRegion);
							
						}*/
						
						if (nextnode.hasProperty("OpenPort")) {
							OpenPort = nextnode.getProperty("OpenPort").getString();
							String OpenPort1 = fd.fetch_Port(session, OpenPort);

							boolean checkjsonString = SaveReportDataClass.isJSONValid(OpenPort1);
							if (checkjsonString == true) {
								JSONObject data = new JSONObject(OpenPort1);
								if (data.has("portName")) {
									OpenPort = data.getString("portName");
								}
								if (data.has("Area")) {
									RepositionRegion = data.getString("Area");
									// check_Port_repositionRegion_id=check_OpenPort_id;
								}else {
									RepositionRegion="";
								}

							}

						}

						if (GmailMethods.isNullString(RepositionRegion)) {

							if (nextnode.hasProperty("RepositionRegion")) {
								RepositionRegion = nextnode.getProperty("RepositionRegion").getString();

								String RepositionRegion1 = fd.fetch_Port(session, RepositionRegion);
								boolean checkjsonString = SaveReportDataClass.isJSONValid(RepositionRegion1);
								if (checkjsonString == true) {
									JSONObject data = new JSONObject(RepositionRegion1);
									
									if (data.has("Area")) {
										RepositionRegion = data.getString("Area");
										// check_Port_repositionRegion_id=check_OpenPort_id;
									}

								}

							} else {
								RepositionRegion = "";
							}

						} // blank
						
						
						/*if (nextnode.hasProperty("RepositionRegion")) {
							RepositionRegion = nextnode.getProperty("RepositionRegion").getString();
							
	                        if(!GmailMethods.isNullString(RepositionRegion)){
	                        	Pattern pattern = Pattern.compile("[0-9]+"); 
	                        	Matcher matcher = pattern.matcher(RepositionRegion);

	                        	// Find all matches
	                        	boolean numbeBooleanr=false;
	                        	while (matcher.find()) { 
	                        	  // Get the matching string  
	                        		numbeBooleanr=true;
//	                        	  String match = matcher.group();
	                        	}
	                        	
	                        	if(numbeBooleanr==true){
	                        		RepositionRegion = fd.fetch_Port(session, RepositionRegion);
	                        	}else{
	                        		RepositionRegion = nextnode.getProperty("RepositionRegion").getString();
	                        	}
	                        	
	                        	
							}else{
								RepositionRegion="";
							}
							
							

							
						}*/
						
						/*if(nextnode.hasProperty("Source")){
							Source=nextnode.getProperty("Source").getString();
							Source=fd.fetch_Source(session, Source);
							
						}*/
						
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
					        }
					            
					        else{
					        	if (nextnode.hasProperty("from_Source")) {
					        		Source = nextnode.getProperty("from_Source").getString();
					        		Source = fd.fetch_Source(session, Source);
					        	}
					        }
					           
					    }
						
						if(nextnode.hasProperty("VesselName")){
							VesselName=nextnode.getProperty("VesselName").getString();
							VesselName=fd.fetch_vesselName(session, VesselName);
							
							boolean checkjsonString=SaveReportDataClass.isJSONValid(VesselName);
							if(checkjsonString==true){
							JSONObject fetch_vesselNamejsonobject=new JSONObject(VesselName);
							if(fetch_vesselNamejsonobject.has("vessel_Id")){
								VesselName=fetch_vesselNamejsonobject.getString("vessel_Id");
							}
						}
							
						}
						String Built="";
						if(nextnode.hasProperty("Built")){
							
							Built=nextnode.getProperty("Built").getString();
							
						}
						String DWT="";
						if(nextnode.hasProperty("DWT")){
							
							DWT=nextnode.getProperty("DWT").getString();
							
						}
						String Cubics="";
						if(nextnode.hasProperty("Cubics")){
							
							Cubics=nextnode.getProperty("Cubics").getString();
							
						}
						String LOA="";
						if(nextnode.hasProperty("LOA")){
							
							LOA=nextnode.getProperty("LOA").getString();
							
						}
						
						String ICE="";
						if(nextnode.hasProperty("ICE")){
							
							ICE=nextnode.getProperty("ICE").getString();
							
						}
						
						String SternLine="";
						if(nextnode.hasProperty("SternLine")){
							
							SternLine=nextnode.getProperty("SternLine").getString();
							
						}
						String VesselType="";
						if(nextnode.hasProperty("VesselType")){
							
							VesselType=nextnode.getProperty("VesselType").getString();
							VesselType=FetchData.fetch_vesselType(session, VesselType);
							
						}
						String emailClientNodeId="";
						if (nextnode.hasProperty("emailClientNodeId")) {
							emailClientNodeId = nextnode.getProperty("emailClientNodeId").getString();

						}
						
						keyNameObject=new JSONObject();
						
						keyNameObject.put("tonnageId", Id);
						keyNameObject.put("EmploymentStatus", EmploymentStatus);
						keyNameObject.put("Owners", Owners);
						keyNameObject.put("OpenPort", OpenPort);
						keyNameObject.put("Operators", Operators);
						keyNameObject.put("CargoType", CargoType);
						keyNameObject.put("Comment", Comment);
						keyNameObject.put("ETABasis", ETABasis);
						keyNameObject.put("OpenDate", OpenDate);
						keyNameObject.put("ReportType", ReportType);
						keyNameObject.put("ReportTimestamp", ReportTimestamp);
						keyNameObject.put("RepositionRegion", RepositionRegion);
						keyNameObject.put("Source", Source);
						keyNameObject.put("VesselName", VesselName);
						keyNameObject.put("Built", Built);
						keyNameObject.put("DWT", DWT);
						keyNameObject.put("Cubics", Cubics);
						keyNameObject.put("LOA", LOA);
						keyNameObject.put("ICE", ICE);
						keyNameObject.put("SternLine", SternLine);
						keyNameObject.put("VesselType", VesselType);
						keyNameObject.put("Sr_No", Sr_No);
						keyNameObject.put("subNodeName", subNodeName);
						keyNameObject.put("dateAndTime", dateAndTime);
						keyNameObject.put("i", i);
						keyNameObject.put("emailUrl", emailUrl);
						keyNameObject.put("emailClientNodeId", emailClientNodeId);
						
						if(keyNameObject.length()>0){
						   keyNameObjectJsonarray.put(keyNameObject);
						}
						
						
						
					} // while
					
					
					
					
					if(!nodeFlag){
						keyNameObject=new JSONObject();
						keyNameObject.put("Error", "no data Available");
						keyNameObject.put("ErrorId", "1");
						keyNameObjectJsonarray.put(keyNameObject);
						
				 }
					
					/*if(keyNameObjectJsonarray.length()>0){
						 mainJsonobj=new JSONObject();
						 mainJsonobj.put("tonnageList", keyNameObjectJsonarray);

					}*/
					
				}

			} catch (Exception e) {
				out.println(e.getMessage());

			}
			return keyNameObjectJsonarray;

		}
	
	public static JSONArray getSpotReportBeforeOneWeek(Session session,String currentDate, String lastWeekDate,PrintWriter out) {

//		JSONObject mainJsonobj=null;
		JSONArray keyNameObjectJsonarray=null;
		try {
			
			if( !GmailMethods.isNullString(currentDate) && !GmailMethods.isNullString(lastWeekDate) ){

					
					FetchData fd = new FetchData();
//					String reporttypequeryid = fd.check_ReportTypeToFetchapi(session, reportType, out);
				/*	String dataCurrentDate=FetchData.convertStringToDate(currentDate);
					String dataLastWeekDate=FetchData.convertStringToDate(lastWeekDate);*/
					Workspace workspace = session.getWorkspace();
					String slingqery = "select [dateAndTime] from [nt:base] where dateAndTime >='" + lastWeekDate
							+ "'and dateAndTime <='" + currentDate 
							+ "' and ISDESCENDANTNODE('/scorpioDataBase/ReportData/Spot/')";
					Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
					QueryResult queryResult = query.execute();
					NodeIterator iterator = queryResult.getNodes();
					boolean nodeFlag = false;
					JSONObject keyNameObject=null;
					 keyNameObjectJsonarray=new JSONArray();
					
					while (iterator.hasNext()) {
					
						nodeFlag = true;
						Node nextnode = iterator.nextNode();
						String subNodeName=nextnode.getName();
						
						String Information="";
						String FixtureType="";
						String Charterer="";
						String Status="";
						String CargoType="";
						String CargoGrade="";
						String CargoQty="";
						String LCStart="";
						String Source="";
						String VesselName="";
						String LCEnd="";
						String LoadPort="";
						String DiscPort="";
						String RateType="";
						String Rate="";
						String Comments="";
						String Id="";
						String dateAndTime="";
						String Sr_No="";
						String emailUrl="";
						
						if(nextnode.hasProperty("Id")){
							Id=nextnode.getProperty("Id").getString();
						}
						if(nextnode.hasProperty("Sr.No")){
							Sr_No=nextnode.getProperty("Sr.No").getString();
						}
						if(nextnode.hasProperty("dateAndTime")){
							dateAndTime=nextnode.getProperty("dateAndTime").getString();
						}
						if(nextnode.hasProperty("Information")){
							Information=nextnode.getProperty("Information").getString();
							Information=fd.fetch_Information(session, Information);
							
						}if(nextnode.hasProperty("emailUrl")){
							emailUrl=nextnode.getProperty("emailUrl").getString();
						}
						if(nextnode.hasProperty("FixtureType")){
							FixtureType=nextnode.getProperty("FixtureType").getString();
							FixtureType=fd.fetch_ReportType(session, FixtureType);
							
						}
						if(nextnode.hasProperty("Charterer")){
							Charterer=nextnode.getProperty("Charterer").getString();
							Charterer=fd.fetch_Charterer(session, Charterer);
							
						}
						if(nextnode.hasProperty("Status")){
							Status=nextnode.getProperty("Status").getString();
							Status=fd.fetch_EmployementStatus(session, Status);
							
						}
						if(nextnode.hasProperty("CargoType")){
							CargoType=nextnode.getProperty("CargoType").getString();
							CargoType=fd.fetch_CargoType(session, CargoType);
							
						}
						if(nextnode.hasProperty("CargoGrade")){
							CargoGrade=nextnode.getProperty("CargoGrade").getString();
							CargoGrade=fd.fetch_CargoGrade(session, CargoGrade);
							
						}
						if(nextnode.hasProperty("CargoQty")){
							CargoQty=nextnode.getProperty("CargoQty").getString();
							
						}
						if(nextnode.hasProperty("LCStart")){
							LCStart=nextnode.getProperty("LCStart").getString();
							
						}
						if(nextnode.hasProperty("LCEnd")){
							LCEnd=nextnode.getProperty("LCEnd").getString();
							
						}
						
						if (nextnode.hasProperty("LoadPort")) {
							LoadPort = nextnode.getProperty("LoadPort").getString();
							//out.println("LoadPort:: "+LoadPort);
							String LoadPort1 = fd.fetch_Port(session, LoadPort);
							//out.println("LoadPort1: "+LoadPort1);
							boolean checkjsonString=SaveReportDataClass.isJSONValid(LoadPort1);
							   if(checkjsonString==true){
								   JSONObject data=new JSONObject(LoadPort1);
								   if(data.has("portName")){
									   LoadPort=data.getString("portName");
									   //out.println("LoadPort: "+LoadPort);
								   }
								   
							   }

						}
						if (nextnode.hasProperty("DiscPort")) {
							DiscPort = nextnode.getProperty("DiscPort").getString();
							DiscPort = fd.fetch_Port(session, DiscPort);
							
							boolean checkjsonString=SaveReportDataClass.isJSONValid(DiscPort);
							   if(checkjsonString==true){
								   JSONObject data=new JSONObject(DiscPort);
								   if(data.has("portName")){
									   DiscPort=data.getString("portName");
								   }
								   
							   }

						}
						
						
						/*if(nextnode.hasProperty("LoadPort")){
							LoadPort=nextnode.getProperty("LoadPort").getString();
							LoadPort=fd.fetch_Port(session, LoadPort);
							
						}
						if(nextnode.hasProperty("DiscPort")){
							DiscPort=nextnode.getProperty("DiscPort").getString();
							DiscPort=fd.fetch_Port(session, DiscPort);
							
						}*/
						if(nextnode.hasProperty("VesselName")){
							VesselName=nextnode.getProperty("VesselName").getString();
							
							VesselName=fd.fetch_vesselName(session, VesselName);
							JSONObject fetch_vesselNamejsonobject=new JSONObject(VesselName);
							if(fetch_vesselNamejsonobject.has("vessel_Id")){
								VesselName=fetch_vesselNamejsonobject.getString("vessel_Id");
							}
							
						}
						if(nextnode.hasProperty("RateType")){
							RateType=nextnode.getProperty("RateType").getString();
							RateType=fd.fetch_RateType(session, RateType);
							
						}
						if(nextnode.hasProperty("Rate")){
							Rate=nextnode.getProperty("Rate").getString();
							
						}
						if(nextnode.hasProperty("Comments")){
							Comments=nextnode.getProperty("Comments").getString();
							
						}
						String ReportDate="";
						if(nextnode.hasProperty("ReportDate")){
							
							ReportDate=nextnode.getProperty("ReportDate").getString();
							
						}
						/*if(nextnode.hasProperty("Source")){
							Source=nextnode.getProperty("Source").getString();
							Source=fd.fetch_Source(session, Source);
							
						}*/
						
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
					        }
					            
					        else{
					        	if (nextnode.hasProperty("from_Source")) {
					        		Source = nextnode.getProperty("from_Source").getString();
					        		Source = fd.fetch_Source(session, Source);
					        	}
					        }
					           
					    }
						
						String Vesseltype="";
						if(nextnode.hasProperty("Vesseltype")){
							
							Vesseltype=nextnode.getProperty("Vesseltype").getString();
							Vesseltype=FetchData.fetch_vesselType(session, Vesseltype);
							
						}
						
						String emailClientNodeId="";
						if (nextnode.hasProperty("emailClientNodeId")) {
							emailClientNodeId = nextnode.getProperty("emailClientNodeId").getString();

						}
						
						keyNameObject =new JSONObject();
						
						keyNameObject.put("spotId", Id);
						keyNameObject.put("Information", Information);
						keyNameObject.put("FixtureType", FixtureType);
						keyNameObject.put("LCStart", LCStart);
						keyNameObject.put("LCEnd", LCEnd);
						keyNameObject.put("LoadPort", LoadPort);
						keyNameObject.put("DiscPort", DiscPort);
						keyNameObject.put("VesselName", VesselName);
						keyNameObject.put("Charterer", Charterer);
						keyNameObject.put("Status", Status);
						keyNameObject.put("CargoType", CargoType);
						keyNameObject.put("CargoGrade", CargoGrade);
						keyNameObject.put("CargoQty", CargoQty);
						keyNameObject.put("RateType", RateType);
						keyNameObject.put("Rate", Rate);
						keyNameObject.put("Comments", Comments);
						keyNameObject.put("ReportDate", ReportDate);
						keyNameObject.put("Source", Source);
						keyNameObject.put("Vesseltype", Vesseltype);
						keyNameObject.put("Sr_No", Sr_No);
						keyNameObject.put("dateAndTime", dateAndTime);
						keyNameObject.put("subNodeName", subNodeName);
						keyNameObject.put("emailUrl", emailUrl);
						keyNameObject.put("emailClientNodeId", emailClientNodeId);
						
						if(keyNameObject.length()>0){
						   keyNameObjectJsonarray.put(keyNameObject);
						}
						
						
						
					} // while
					
					
					if(!nodeFlag){
					
						keyNameObject=new JSONObject();
						keyNameObject.put("Error", "no data Available");
						keyNameObject.put("ErrorId", "2");
						keyNameObjectJsonarray.put(keyNameObject);
						
				 }
					
					/*if(keyNameObjectJsonarray.length()>0){
						 mainJsonobj=new JSONObject();
						 mainJsonobj.put("spotList", keyNameObjectJsonarray);

					}*/
					
					
				}

			} catch (Exception e) {
				out.println(e.getMessage());

			}
			return keyNameObjectJsonarray;

		}

	public static JSONArray getTimeCharterReportBeforeOneWeek(Session session,String currentDate, String lastWeekDate,PrintWriter out) {

//		JSONObject mainJsonobj=null;
		JSONArray keyNameObjectJsonarray=null;
		try {
			
			if( !GmailMethods.isNullString(currentDate) && !GmailMethods.isNullString(lastWeekDate) ){

					
					FetchData fd = new FetchData();
//					String reporttypequeryid = fd.check_ReportTypeToFetchapi(session, reportType, out);
					/*String dataCurrentDate=FetchData.convertStringToDate(currentDate);
					String dataLastWeekDate=FetchData.convertStringToDate(lastWeekDate);*/
					Workspace workspace = session.getWorkspace();
					String slingqery = "select [dateAndTime] from [nt:base] where dateAndTime >='" + lastWeekDate
							+ "'and dateAndTime <='" + currentDate 
							+ "' and ISDESCENDANTNODE('/scorpioDataBase/ReportData/TCReports/TimeCharterReport/')";
					Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
					QueryResult queryResult = query.execute();

					NodeIterator iterator = queryResult.getNodes();
					boolean nodeFlag = false;

					JSONObject keyNameObject=null;
					 keyNameObjectJsonarray=new JSONArray();
					
					while (iterator.hasNext()) {
					
						nodeFlag = true;
						Node nextnode = iterator.nextNode();
						String subNodeName=nextnode.getName();
						
						String Information="";
						String Charterer="";
						String Source="";
						String VesselName="";
						String Comments="";
						String Rate="";
						String Id="";
						String dateAndTime="";
						String Sr_No="";
					    String emailUrl="";
						
						if(nextnode.hasProperty("Id")){
							Id=nextnode.getProperty("Id").getString();
						}if(nextnode.hasProperty("Sr.No")){
							Sr_No=nextnode.getProperty("Sr.No").getString();
						}
						if(nextnode.hasProperty("dateAndTime")){
							dateAndTime=nextnode.getProperty("dateAndTime").getString();
						}
						
						if(nextnode.hasProperty("Information")){
							Information=nextnode.getProperty("Information").getString();
							Information=fd.fetch_Information(session, Information);
							
						}
						String Spot_TC="";
						if(nextnode.hasProperty("Spot_TC")){
							
							Spot_TC=nextnode.getProperty("Spot_TC").getString();
							Spot_TC=fd.fetch_ReportType(session, Spot_TC);
							
						}if(nextnode.hasProperty("emailUrl")){
							emailUrl=nextnode.getProperty("emailUrl").getString();
						}
						String Date="";
						if(nextnode.hasProperty("Date")){
							
							Date=nextnode.getProperty("Date").getString();
							
						}
						
						if(nextnode.hasProperty("VesselName")){
							VesselName=nextnode.getProperty("VesselName").getString();
							
							VesselName=fd.fetch_vesselName(session, VesselName);
							JSONObject fetch_vesselNamejsonobject=new JSONObject(VesselName);
							if(fetch_vesselNamejsonobject.has("vessel_Id")){
								VesselName=fetch_vesselNamejsonobject.getString("vessel_Id");
							}
							
						}
						String CPP_DPP="";
						if(nextnode.hasProperty("CPP_DPP")){
							
							CPP_DPP=nextnode.getProperty("CPP_DPP").getString();
							CPP_DPP=fd.fetch_CargoType(session, CPP_DPP);
							
						}
						if(nextnode.hasProperty("Charterer")){
							Charterer=nextnode.getProperty("Charterer").getString();
							Charterer=fd.fetch_Charterer(session, Charterer);
							
						}
						String Delivery="";
						if(nextnode.hasProperty("Delivery")){
							
							Delivery=nextnode.getProperty("Delivery").getString();
							
						}
						String DeliveryPlace="";
						if(nextnode.hasProperty("DeliveryPlace")){
							
							DeliveryPlace=nextnode.getProperty("DeliveryPlace").getString();
							DeliveryPlace=fd.fetch_DeliveryPlace(session, DeliveryPlace);
							
						}
						String Period="";
						if(nextnode.hasProperty("Period")){
							
							Period=nextnode.getProperty("Period").getString();
							
						}
						String Periodunit="";
						if(nextnode.hasProperty("Periodunit")){
							
							Periodunit=nextnode.getProperty("Periodunit").getString();
							Periodunit=fd.fetch_Periodunit(session, Periodunit);
							
						}
						String Redelivery="";
						if(nextnode.hasProperty("Redelivery")){
							
							Redelivery=nextnode.getProperty("Redelivery").getString();
							
						}
						String TCRate="";
						if(nextnode.hasProperty("TCRate")){
							
							TCRate=nextnode.getProperty("TCRate").getString();
							
						}
						
						if(nextnode.hasProperty("Rate")){
							Rate=nextnode.getProperty("Rate").getString();
							
						}
						String Unit="";
						if(nextnode.hasProperty("Unit")){
							
							Unit=nextnode.getProperty("Unit").getString();
							Unit=fd.fetch_currencyUnit(session, Unit);
							
						}
						
						if(nextnode.hasProperty("Comments")){
							Comments=nextnode.getProperty("Comments").getString();
							
						}
						
						/*if(nextnode.hasProperty("Source")){
							Source=nextnode.getProperty("Source").getString();
							Source=fd.fetch_Source(session, Source);
							
						}*/
						
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
					        }
					            
					        else{
					        	if (nextnode.hasProperty("from_Source")) {
					        		Source = nextnode.getProperty("from_Source").getString();
					        		Source = fd.fetch_Source(session, Source);
					        	}
					        }
					           
					    }
						
						String Vesseltype="";
						if(nextnode.hasProperty("Vesseltype")){
							
							Vesseltype=nextnode.getProperty("Vesseltype").getString();
							Vesseltype=FetchData.fetch_vesselType(session, Vesseltype);
							
						}
						String TimeStamp="";
						if(nextnode.hasProperty("TimeStamp")){
							
							TimeStamp=nextnode.getProperty("TimeStamp").getString();
							
						}
						String emailClientNodeId="";
						if (nextnode.hasProperty("emailClientNodeId")) {
							emailClientNodeId = nextnode.getProperty("emailClientNodeId").getString();

						}
						
						keyNameObject =new JSONObject();
						
						keyNameObject.put("TimeCharterReportsId", Id);
						keyNameObject.put("Information", Information);
						keyNameObject.put("Spot_TC", Spot_TC);
						keyNameObject.put("Date", Date);
						keyNameObject.put("VesselName", VesselName);
						keyNameObject.put("CPP_DPP", CPP_DPP);
						keyNameObject.put("Charterer", Charterer);
						keyNameObject.put("Delivery", Delivery);
						keyNameObject.put("DeliveryPlace", DeliveryPlace);
						keyNameObject.put("Period", Period);
						keyNameObject.put("Periodunit", Periodunit);
						keyNameObject.put("Redelivery", Redelivery);
						keyNameObject.put("TCRate", TCRate);
						keyNameObject.put("Rate", Rate);
						keyNameObject.put("Unit", Unit);
						keyNameObject.put("Comments", Comments);
						keyNameObject.put("Source", Source);
						keyNameObject.put("Vesseltype", Vesseltype);
						keyNameObject.put("TimeStamp", TimeStamp);
						keyNameObject.put("Sr_No", Sr_No);
						keyNameObject.put("subNodeName", subNodeName);
						keyNameObject.put("dateAndTime", dateAndTime);
						keyNameObject.put("emailUrl", emailUrl);
						keyNameObject.put("emailClientNodeId", emailClientNodeId);
						
						if(keyNameObject.length()>0){
						   keyNameObjectJsonarray.put(keyNameObject);
						}
						
						
						
					} // while
					
					
					if(!nodeFlag){
						keyNameObject=new JSONObject();
						keyNameObject.put("Error", "no data Available");
						keyNameObject.put("ErrorId", "3");
						keyNameObjectJsonarray.put(keyNameObject);
						
				 }
					
					/*if(keyNameObjectJsonarray.length()>0){
						 mainJsonobj=new JSONObject();
						 mainJsonobj.put("TimeCharterReportsList", keyNameObjectJsonarray);

					}*/
					
					
				}

			} catch (Exception e) {
				out.println(e.getMessage());

			}
			return keyNameObjectJsonarray;

		}
	
	public static JSONArray getBrokerTcRateReportBeforeOneWeek(Session session,String currentDate, String lastWeekDate,PrintWriter out) {

//		JSONObject mainJsonobj=null;
		JSONArray keyNameObjectJsonarray=null;
		try {
			
			if( !GmailMethods.isNullString(currentDate) && !GmailMethods.isNullString(lastWeekDate) ){

					
					FetchData fd = new FetchData();
//					String reporttypequeryid = fd.check_ReportTypeToFetchapi(session, reportType, out);
					/*String dataCurrentDate=FetchData.convertStringToDate(currentDate);
					String dataLastWeekDate=FetchData.convertStringToDate(lastWeekDate);*/
					Workspace workspace = session.getWorkspace();

					String slingqery = "select [dateAndTime] from [nt:base] where dateAndTime >='" + lastWeekDate
							+ "'and dateAndTime <='" + currentDate 
							+ "' and ISDESCENDANTNODE('/scorpioDataBase/ReportData/TCReports/BrokerTCRate/')";
					Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
					QueryResult queryResult = query.execute();

					NodeIterator iterator = queryResult.getNodes();
					boolean nodeFlag = false;

					JSONObject keyNameObject=null;
					 keyNameObjectJsonarray=new JSONArray();
					
					while (iterator.hasNext()) {
					
						nodeFlag = true;
						Node nextnode = iterator.nextNode();
						String subNodeName=nextnode.getName();
						
						String Id="";
						String dateAndTime="";
						String Sr_No="";
						String emailUrl="";
						
						if(nextnode.hasProperty("Id")){
							
							Id=nextnode.getProperty("Id").getString();
						}if(nextnode.hasProperty("Sr.No")){
							Sr_No=nextnode.getProperty("Sr.No").getString();
						}
						if(nextnode.hasProperty("dateAndTime")){
							dateAndTime=nextnode.getProperty("dateAndTime").getString();
						}
						String Broker="";
						/*if(nextnode.hasProperty("Broker")){
							
							Broker=nextnode.getProperty("Broker").getString();
							Broker=fd.fetch_Source(session, Broker);
							
						}*/
						
						if (nextnode.hasProperty("Broker")) {
							Broker = nextnode.getProperty("Broker").getString();
							
					        boolean numeric = true;

					        try {
					            Integer num = Integer.parseInt(Broker);
					            
					        } catch (NumberFormatException e) {
					            numeric = false;
					        }

					        if(numeric){
					        	Broker = fd.fetch_Source(session, Broker);
					        }
					            
					        else{
					        	if (nextnode.hasProperty("from_Source")) {
					        		Broker = nextnode.getProperty("from_Source").getString();
					        		Broker = fd.fetch_Source(session, Broker);
					        	}
					        }
					           
					    }
						
						if(nextnode.hasProperty("emailUrl")){
							emailUrl=nextnode.getProperty("emailUrl").getString();
						}
						String VesselType="";
						if(nextnode.hasProperty("VesselType")){
							
							VesselType=nextnode.getProperty("VesselType").getString();
							VesselType=FetchData.fetch_vesselType(session, VesselType);
							
						}
						String Month_Year="";
						if(nextnode.hasProperty("Month_Year")){
							
							Month_Year=nextnode.getProperty("Month_Year").getString();
							
						}
						String first_yr="";
						if(nextnode.hasProperty("first_yr")){
							
							first_yr=nextnode.getProperty("first_yr").getString();
							
						}
						
						String second_yr="";
						if(nextnode.hasProperty("second_yr")){
							
							second_yr=nextnode.getProperty("second_yr").getString();
							
						}
						String third_yr="";
						if(nextnode.hasProperty("third_yr")){
							
							third_yr=nextnode.getProperty("third_yr").getString();
							
						}
						String fifth_yr="";
						if(nextnode.hasProperty("fifth_yr")){
							
							fifth_yr=nextnode.getProperty("fifth_yr").getString();
							
						}
						String emailClientNodeId="";
						 if (nextnode.hasProperty("emailClientNodeId")) {
								emailClientNodeId = nextnode.getProperty("emailClientNodeId").getString();

							}
						
						   keyNameObject=new JSONObject();
							
							keyNameObject.put("BrokerTcRateId", Id);
							keyNameObject.put("Broker", Broker);
							keyNameObject.put("VesselType", VesselType);
							keyNameObject.put("Month_Year", Month_Year);
							keyNameObject.put("first_yr", first_yr);
							keyNameObject.put("second_yr", second_yr);
							keyNameObject.put("third_yr", third_yr);
							keyNameObject.put("fifth_yr", fifth_yr);
							keyNameObject.put("dateAndTime", dateAndTime);
							keyNameObject.put("subNodeName", subNodeName);
							keyNameObject.put("Sr_No", Sr_No);
							keyNameObject.put("emailUrl", emailUrl);
							keyNameObject.put("emailClientNodeId", emailClientNodeId);
							
						if(keyNameObject.length()>0){
						   keyNameObjectJsonarray.put(keyNameObject);
						}
						
						
						
					} // while
					
					
					if(!nodeFlag){
						keyNameObject=new JSONObject();
						keyNameObject.put("Error", "no data Available");
						keyNameObject.put("ErrorId", "4");
						keyNameObjectJsonarray.put(keyNameObject);
						
				 }
					
					/*if(keyNameObjectJsonarray.length()>0){
						 mainJsonobj=new JSONObject();
						 mainJsonobj.put("BrokerTcRateList", keyNameObjectJsonarray);

					}*/
					
					
				}

			} catch (Exception e) {
				out.println(e.getMessage());

			}
			return keyNameObjectJsonarray;

		}

	public static void fetch(PrintWriter out, Session session){
		try {
			
			Node scorpio = session.getRootNode().getNode("scorpioDataBase");
			Node ReportData=scorpio.getNode("ReportData");
			Node Tonnage=ReportData.getNode("Tonnage");
			
			if(Tonnage.hasNodes()){
				NodeIterator itr=Tonnage.getNodes();
				while(itr.hasNext()){
					Node nextnode=itr.nextNode();
					String dateAndTime="";
					
					if(nextnode.hasProperty("dateAndTime")){
						dateAndTime=nextnode.getProperty("dateAndTime").getString();
						 Date date=GmailMethods.parseDate(dateAndTime);
						    JSONObject dateJson=SlingMethods.formatDate_Month_Year_Day(date, out);
						 
						    int day=0;
							int month=0;
							int year=0;
							int hours=0;
							int Minute=0;
							String s="";
							if(dateJson.has("day")){
								 day=dateJson.getInt("day");
								 String dayString=String.valueOf(day);
								 boolean b3 = Pattern.matches("(\\d{1}$)", dayString); 
								 if(b3==true){
									  s="0"+day;
								 }else{
									 s=String.valueOf(day);
								 }
								 
							}if(dateJson.has("month")){
								 month=dateJson.getInt("month");
							}
							if(dateJson.has("year")){
								 year=dateJson.getInt("year");
							}
							if(dateJson.has("hours")){
								 hours=dateJson.getInt("hours");
							}
							if(dateJson.has("Minute")){
								Minute=dateJson.getInt("Minute");
							}
						
						   String combinedatetimestamp=s+"-"+String.valueOf(month)+"-"+String.valueOf(year);
						   nextnode.setProperty("FormatedDate", combinedatetimestamp);
						   session.save();
					}
					
					
					
				}
			}
			
			
		}catch(Exception e){
			
		}
	}
	
	public static void fetchSpot(PrintWriter out, Session session){
		try {
			
			Node scorpio = session.getRootNode().getNode("scorpioDataBase");
			Node ReportData=scorpio.getNode("ReportData");
			Node Tonnage=ReportData.getNode("Spot");
			
			if(Tonnage.hasNodes()){
				NodeIterator itr=Tonnage.getNodes();
				while(itr.hasNext()){
					Node nextnode=itr.nextNode();
					String dateAndTime="";
					
					if(nextnode.hasProperty("dateAndTime")){
						dateAndTime=nextnode.getProperty("dateAndTime").getString();
						 Date date=GmailMethods.parseDate(dateAndTime);
						    JSONObject dateJson=SlingMethods.formatDate_Month_Year_Day(date, out);
						 
						    int day=0;
							int month=0;
							int year=0;
							int hours=0;
							int Minute=0;
							String s="";
							if(dateJson.has("day")){
								 day=dateJson.getInt("day");
								 String dayString=String.valueOf(day);
								 boolean b3 = Pattern.matches("(\\d{1}$)", dayString); 
								 if(b3==true){
									  s="0"+day;
								 }else{
									 s=String.valueOf(day);
								 }
								 
							}if(dateJson.has("month")){
								 month=dateJson.getInt("month");
							}
							if(dateJson.has("year")){
								 year=dateJson.getInt("year");
							}
							if(dateJson.has("hours")){
								 hours=dateJson.getInt("hours");
							}
							if(dateJson.has("Minute")){
								Minute=dateJson.getInt("Minute");
							}
						
						   String combinedatetimestamp=s+"-"+String.valueOf(month)+"-"+String.valueOf(year);
						   nextnode.setProperty("FormatedDate", combinedatetimestamp);
						   session.save();
					}
					
					
					
				}
			}
			
			
		}catch(Exception e){
			
		}
	}
	
	public static void fetchTimecharter(PrintWriter out, Session session){
		try {
			
			Node scorpio = session.getRootNode().getNode("scorpioDataBase");
			Node ReportData=scorpio.getNode("ReportData");
			Node TCReports=ReportData.getNode("TCReports");
			Node Tonnage=TCReports.getNode("TimeCharterReport");
			
			if(Tonnage.hasNodes()){
				NodeIterator itr=Tonnage.getNodes();
				while(itr.hasNext()){
					Node nextnode=itr.nextNode();
					String dateAndTime="";
					
					if(nextnode.hasProperty("dateAndTime")){
						dateAndTime=nextnode.getProperty("dateAndTime").getString();
						 Date date=GmailMethods.parseDate(dateAndTime);
						    JSONObject dateJson=SlingMethods.formatDate_Month_Year_Day(date, out);
						 
						    int day=0;
							int month=0;
							int year=0;
							int hours=0;
							int Minute=0;
							String s="";
							if(dateJson.has("day")){
								 day=dateJson.getInt("day");
								 String dayString=String.valueOf(day);
								 boolean b3 = Pattern.matches("(\\d{1}$)", dayString); 
								 if(b3==true){
									  s="0"+day;
								 }else{
									 s=String.valueOf(day);
								 }
								 
							}if(dateJson.has("month")){
								 month=dateJson.getInt("month");
							}
							if(dateJson.has("year")){
								 year=dateJson.getInt("year");
							}
							if(dateJson.has("hours")){
								 hours=dateJson.getInt("hours");
							}
							if(dateJson.has("Minute")){
								Minute=dateJson.getInt("Minute");
							}
						
						   String combinedatetimestamp=s+"-"+String.valueOf(month)+"-"+String.valueOf(year);
						   nextnode.setProperty("FormatedDate", combinedatetimestamp);
						   session.save();
					}
					
					
					
				}
			}
			
			
		}catch(Exception e){
			
		}
	}
	
	public static void fetchBrokertcrate(PrintWriter out, Session session){
		try {
			
			Node scorpio = session.getRootNode().getNode("scorpioDataBase");
			Node ReportData=scorpio.getNode("ReportData");
			Node TCReports=ReportData.getNode("TCReports");
			Node Tonnage=TCReports.getNode("BrokerTCRate");
			
			if(Tonnage.hasNodes()){
				NodeIterator itr=Tonnage.getNodes();
				while(itr.hasNext()){
					Node nextnode=itr.nextNode();
					String dateAndTime="";
					
					if(nextnode.hasProperty("dateAndTime")){
						dateAndTime=nextnode.getProperty("dateAndTime").getString();
						 Date date=GmailMethods.parseDate(dateAndTime);
						    JSONObject dateJson=SlingMethods.formatDate_Month_Year_Day(date, out);
						 
						    int day=0;
							int month=0;
							int year=0;
							int hours=0;
							int Minute=0;
							String s="";
							if(dateJson.has("day")){
								 day=dateJson.getInt("day");
								 String dayString=String.valueOf(day);
								 boolean b3 = Pattern.matches("(\\d{1}$)", dayString); 
								 if(b3==true){
									  s="0"+day;
								 }else{
									 s=String.valueOf(day);
								 }
								 
							}if(dateJson.has("month")){
								 month=dateJson.getInt("month");
							}
							if(dateJson.has("year")){
								 year=dateJson.getInt("year");
							}
							if(dateJson.has("hours")){
								 hours=dateJson.getInt("hours");
							}
							if(dateJson.has("Minute")){
								Minute=dateJson.getInt("Minute");
							}
						
						   String combinedatetimestamp=s+"-"+String.valueOf(month)+"-"+String.valueOf(year);
						   nextnode.setProperty("FormatedDate", combinedatetimestamp);
						   session.save();
					}
					
					
					
				}
			}
			
			
		}catch(Exception e){
			
		}
	}
	
	public static void TonnageReportSaveUi(PrintWriter out, Session session, String ReportType, String RepositionRegion, String CargoType
			,String VesselName, String VesselType,String Built,String DWT,String Cubics,String LOA,String ICE,String SternLine,String Owners
			,String Operators,String EmploymentStatus,String OpenPort,String OpenDate,String ETABasis,String Comment,String Source,String ReportTimestamp){
		
		try {
			
			Node scorpio=null;
			Node Tonnage=null;
			Node ReportData=null;
			Node subNode=null;
			
			if(session.getRootNode().hasNode("scorpioDataBase")){
				scorpio = session.getRootNode().getNode("scorpioDataBase");
			}else{
				scorpio = session.getRootNode().addNode("scorpioDataBase");
				session.save();
			}
			
			if(scorpio.hasNode("ReportData")){
				ReportData = scorpio.getNode("ReportData");
			}else{
				ReportData = scorpio.addNode("ReportData");
				session.save();
			}
			
			if(ReportData.hasNode("Tonnage")){
				Tonnage=ReportData.getNode("Tonnage");
			}else{
				Tonnage=ReportData.addNode("Tonnage");
				Tonnage.setProperty("jcr:count", String.valueOf(0));
				Tonnage.setProperty("netChangedPointer", String.valueOf(0));
				session.save();
			}
			
			 String count=Tonnage.getProperty("jcr:count").getString();
			 String forid= String.valueOf(Integer.parseInt(count)+1);
			 if(!Tonnage.hasNode(forid)){
				 subNode=Tonnage.addNode(forid);
				 				
				 subNode.setProperty("Sr.No", forid);
				 subNode.setProperty("Id", forid);

				 subNode.setProperty("ReportType",ReportType);
				 subNode.setProperty("RepositionRegion",RepositionRegion);
				 subNode.setProperty("CargoType",CargoType);
				 subNode.setProperty("VesselName", VesselName);
				 subNode.setProperty("VesselType", VesselType);
				 subNode.setProperty("Built", Built);
				 subNode.setProperty("DWT", DWT);
				 subNode.setProperty("Cubics", Cubics);
				 subNode.setProperty("LOA", LOA);
				 subNode.setProperty("ICE", ICE);
				 subNode.setProperty("SternLine", SternLine);
				 subNode.setProperty("Owners", Owners);
				 subNode.setProperty("Operators", Operators);
				 subNode.setProperty("EmploymentStatus", EmploymentStatus); 
				 subNode.setProperty("OpenPort", OpenPort);
				 subNode.setProperty("OpenDate", OpenDate);
				 subNode.setProperty("ETABasis", ETABasis);
				 subNode.setProperty("Comment", Comment);
				 subNode.setProperty("Source", Source);
				 
				    Calendar c = Calendar.getInstance();
				   SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
				   c.setTime(sdf.parse(ReportTimestamp));
				   subNode.setProperty("ReportTimestamp", c);
				 
				 
				 Tonnage.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
				 session.save();
				 
			 }
			
		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}
	
	public static void callSpotReport(PrintWriter out, Session session, String Information, String FixtureType, String Charterer, String Status
			,String CargoQty, String CargoGrade, String LCStart, String LCEnd, String LoadPort, String DiscPort, String VesselName
			,String RateType, String Rate, String Comment, String ReportDate, String Source, String VesselType, String CargoType){
		
		try {
			
			Node scorpio=null;
			Node Tonnage=null;
			Node ReportData=null;
			Node subNode=null;
			
			if(session.getRootNode().hasNode("scorpioDataBase")){
				scorpio = session.getRootNode().getNode("scorpioDataBase");
			}else{
				scorpio = session.getRootNode().addNode("scorpioDataBase");
				session.save();
			}
			
			if(scorpio.hasNode("ReportData")){
				ReportData = scorpio.getNode("ReportData");
			}else{
				ReportData = scorpio.addNode("ReportData");
				session.save();
			}
			
			if(ReportData.hasNode("Spot")){
				Tonnage=ReportData.getNode("Spot");
			}else{
				Tonnage=ReportData.addNode("Spot");
				Tonnage.setProperty("jcr:count", String.valueOf(0));
				Tonnage.setProperty("netChangedPointer", String.valueOf(0));
				session.save();
			}
			
			 String count=Tonnage.getProperty("jcr:count").getString();
			 String forid= String.valueOf(Integer.parseInt(count)+1);
			 if(!Tonnage.hasNode(forid)){
				 subNode=Tonnage.addNode(forid);
				 
				 subNode.setProperty("Sr.No", forid);
				 subNode.setProperty("Id", forid);
				 subNode.setProperty("Information", Information);
				 subNode.setProperty("FixtureType", FixtureType);
				 subNode.setProperty("Charterer", Charterer);
				 subNode.setProperty("Status", Status);
				 subNode.setProperty("CargoType", CargoType);
				 subNode.setProperty("CargoGrade", CargoGrade);
				 subNode.setProperty("CargoQty", CargoQty);
				 subNode.setProperty("LCStart", LCStart);
				 subNode.setProperty("LCEnd", LCEnd);
				 subNode.setProperty("LoadPort", LoadPort);
				 subNode.setProperty("DiscPort", DiscPort);
				 subNode.setProperty("VesselName", VesselName);
				 subNode.setProperty("RateType", RateType);
				 subNode.setProperty("Rate", Rate);
				 subNode.setProperty("Comments", Comment);
				 subNode.setProperty("ReportDate", ReportDate);
				 subNode.setProperty("Source", Source);
				 subNode.setProperty("Vesseltype", VesselType);
				 subNode.setProperty("ReportType", "2");

				 Tonnage.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
				 session.save();
			 }
			
		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}
	
	public static void callTimecharterReport(PrintWriter out, Session session, String Information, String Spot_TC, String Date, String VesselName
			, String CPP_DPP, String Charterer, String Delivery, String DeliveryPlace, String Period, String Periodunit, String Redelivery
			, String TCRate, String Rate, String Unit, String Comments, String TimeStamp, String Source, String Vesseltype){
		
		try {
			
			Node scorpio=null;
			Node TimeCharterReport=null;
			Node subNode=null;
			Node ReportData=null;
			Node TCReports=null;
			
			if(session.getRootNode().hasNode("scorpioDataBase")){
				scorpio = session.getRootNode().getNode("scorpioDataBase");
			}else{
				scorpio = session.getRootNode().addNode("scorpioDataBase");
				session.save();
			}
			
			if(scorpio.hasNode("ReportData")){
				ReportData = scorpio.getNode("ReportData");
			}else{
				ReportData = scorpio.addNode("ReportData");
				session.save();
			}
			
			if(ReportData.hasNode("TCReports")){
				TCReports = ReportData.getNode("TCReports");
			}else{
				TCReports = ReportData.addNode("TCReports");
				session.save();
			}
			
			
			
			if(TCReports.hasNode("TimeCharterReport")){
				TimeCharterReport=TCReports.getNode("TimeCharterReport");
			}else{
				TimeCharterReport=TCReports.addNode("TimeCharterReport");
				TimeCharterReport.setProperty("jcr:count", String.valueOf(0));
				TimeCharterReport.setProperty("netChangedPointer", String.valueOf(0));
				session.save();
			}
			
			 String count=TimeCharterReport.getProperty("jcr:count").getString();
			 String forid= String.valueOf(Integer.parseInt(count)+1);
			 if(!TimeCharterReport.hasNode(forid)){
				 subNode=TimeCharterReport.addNode(forid);
				
				 subNode.setProperty("Sr.No", forid);
				 subNode.setProperty("Id", forid);
				 subNode.setProperty("Information", Information);
				 subNode.setProperty("Spot_TC",Spot_TC);
				 
				 subNode.setProperty("Date", Date);
				 subNode.setProperty("VesselName", VesselName);
				 subNode.setProperty("CPP_DPP", CPP_DPP);
				 subNode.setProperty("Charterer", Charterer);
				 subNode.setProperty("Delivery", Delivery);
				 subNode.setProperty("DeliveryPlace", DeliveryPlace);
				 subNode.setProperty("Period", Period);
				 subNode.setProperty("Periodunit", Periodunit);
				 subNode.setProperty("Redelivery", Redelivery);
				 subNode.setProperty("TCRate", TCRate);
				 subNode.setProperty("Rate", Rate);
				 subNode.setProperty("Unit", Unit);
				 subNode.setProperty("Comments", Comments);
				 subNode.setProperty("TimeStamp", TimeStamp);
				 subNode.setProperty("Source", Source);
				 subNode.setProperty("Vesseltype", Vesseltype);
				 subNode.setProperty("ReportType", "3");
				 subNode.setProperty("ReportTimestamp", TimeStamp);
				 
				 TimeCharterReport.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
				 session.save();
				 
			 }
			
		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}
	
	public static void callBrokerTcRateReportSAveUi(PrintWriter out, Session session, String Broker, String VesselType, String Month_Year, String first_yr,
			String second_yr, String third_yr, String fifth_yr){
			
			try {
				
				Node scorpio=null;
				Node BrokerTCRate=null;
				Node subNode=null;
				Node TCReports=null;
				Node ReportData=null;
				
				if(session.getRootNode().hasNode("scorpioDataBase")){
					scorpio = session.getRootNode().getNode("scorpioDataBase");
				}else{
					scorpio = session.getRootNode().addNode("scorpioDataBase");
					session.save();
				}
				
				if(scorpio.hasNode("ReportData")){
					ReportData = scorpio.getNode("ReportData");
				}else{
					ReportData = scorpio.addNode("ReportData");
					session.save();
				}
				
				if(ReportData.hasNode("TCReports")){
					TCReports = ReportData.getNode("TCReports");
				}else{
					TCReports = ReportData.addNode("TCReports");
					session.save();
				}
				
				if(TCReports.hasNode("BrokerTCRate")){
					BrokerTCRate=TCReports.getNode("BrokerTCRate");
				}else{
					BrokerTCRate=TCReports.addNode("BrokerTCRate");
					BrokerTCRate.setProperty("jcr:count", String.valueOf(0));
					BrokerTCRate.setProperty("netChangedPointer", String.valueOf(0));
					session.save();
				}
				
				 String count=BrokerTCRate.getProperty("jcr:count").getString();
				 String forid= String.valueOf(Integer.parseInt(count)+1);
				 if(!BrokerTCRate.hasNode(forid)){
					 subNode=BrokerTCRate.addNode(forid);
					 
					 subNode.setProperty("Sr.No", forid);
					 subNode.setProperty("Id", forid);
					
					 subNode.setProperty("Broker", Broker);
					 subNode.setProperty("VesselType", VesselType);
					 subNode.setProperty("Month_Year", Month_Year);
					 subNode.setProperty("first_yr", first_yr);
					 subNode.setProperty("second_yr", second_yr);
					 subNode.setProperty("third_yr", third_yr);
					 subNode.setProperty("fifth_yr", fifth_yr);
					 subNode.setProperty("ReportType", "4");
					 
					 BrokerTCRate.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
					 session.save();
					 
				 }
				
				
			} catch (Exception e) {
				out.println(e.getMessage());
			}
		}
	
	public static void TonnageReportEditUi(PrintWriter out, Session session, String ReportType, String RepositionRegion, String CargoType
			,String VesselName, String VesselType,String Built,String DWT,String Cubics,String LOA,String ICE,String SternLine,String Owners
			,String Operators,String EmploymentStatus,String OpenPort,String OpenDate,String ETABasis,String Comment,String Source,String ReportTimestamp, String passNodeName){
		
		try {
			
			Node scorpio=null;
			Node Tonnage=null;
			Node ReportData=null;
			Node subNode=null;
			
			if(session.getRootNode().hasNode("scorpioDataBase")){
				scorpio = session.getRootNode().getNode("scorpioDataBase");
			}else{
				scorpio = session.getRootNode().addNode("scorpioDataBase");
				session.save();
			}
			
			if(scorpio.hasNode("ReportData")){
				ReportData = scorpio.getNode("ReportData");
			}else{
				ReportData = scorpio.addNode("ReportData");
				session.save();
			}
			
			if(ReportData.hasNode("Tonnage")){
				Tonnage=ReportData.getNode("Tonnage");
			}else{
				Tonnage=ReportData.addNode("Tonnage");
				Tonnage.setProperty("jcr:count", String.valueOf(0));
				Tonnage.setProperty("netChangedPointer", String.valueOf(0));
				session.save();
			}
			
			 if(Tonnage.hasNode(passNodeName)){
				 subNode=Tonnage.getNode(passNodeName);
				 				
				 /*subNode.setProperty("Sr.No", forid);
				 subNode.setProperty("Id", forid);*/

				 subNode.setProperty("ReportType",ReportType);
				 subNode.setProperty("RepositionRegion",RepositionRegion);
				 subNode.setProperty("CargoType",CargoType);
				 subNode.setProperty("VesselName", VesselName);
				 subNode.setProperty("VesselType", VesselType);
				 subNode.setProperty("Built", Built);
				 subNode.setProperty("DWT", DWT);
				 subNode.setProperty("Cubics", Cubics);
				 subNode.setProperty("LOA", LOA);
				 subNode.setProperty("ICE", ICE);
				 subNode.setProperty("SternLine", SternLine);
				 subNode.setProperty("Owners", Owners);
				 subNode.setProperty("Operators", Operators);
				 subNode.setProperty("EmploymentStatus", EmploymentStatus); 
				 subNode.setProperty("OpenPort", OpenPort);
				 subNode.setProperty("OpenDate", OpenDate);
				 subNode.setProperty("ETABasis", ETABasis);
				 subNode.setProperty("Comment", Comment);
				 subNode.setProperty("Source", Source);
				  Calendar c = Calendar.getInstance();
				   SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
				   c.setTime(sdf.parse(ReportTimestamp));
				   subNode.setProperty("ReportTimestamp", c);
				 
				 session.save();
				 
			 }
			
		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}

	public static void SpotReportEditUi(PrintWriter out, Session session, String Information, String FixtureType, String Charterer, String Status
			,String CargoQty, String CargoGrade, String LCStart, String LCEnd, String LoadPort, String DiscPort, String VesselName
			,String RateType, String Rate, String Comment, String ReportDate, String Source, String VesselType, String CargoType, String passNodeName){
		
		try {
			
			Node scorpio=null;
			Node Tonnage=null;
			Node ReportData=null;
			Node subNode=null;
			
			if(session.getRootNode().hasNode("scorpioDataBase")){
				scorpio = session.getRootNode().getNode("scorpioDataBase");
			}else{
				scorpio = session.getRootNode().addNode("scorpioDataBase");
				session.save();
			}
			
			if(scorpio.hasNode("ReportData")){
				ReportData = scorpio.getNode("ReportData");
			}else{
				ReportData = scorpio.addNode("ReportData");
				session.save();
			}
			
			if(ReportData.hasNode("Spot")){
				Tonnage=ReportData.getNode("Spot");
			}else{
				Tonnage=ReportData.addNode("Spot");
				Tonnage.setProperty("jcr:count", String.valueOf(0));
				Tonnage.setProperty("netChangedPointer", String.valueOf(0));
				session.save();
			}
			
			 if(Tonnage.hasNode(passNodeName)){
				 subNode=Tonnage.getNode(passNodeName);
				 
				 /*subNode.setProperty("Sr.No", forid);
				 subNode.setProperty("Id", forid);*/
				 subNode.setProperty("Information", Information);
				 subNode.setProperty("FixtureType", FixtureType);
				 subNode.setProperty("Charterer", Charterer);
				 subNode.setProperty("Status", Status);
				 subNode.setProperty("CargoType", CargoType);
				 subNode.setProperty("CargoGrade", CargoGrade);
				 subNode.setProperty("CargoQty", CargoQty);
				 subNode.setProperty("LCStart", LCStart);
				 subNode.setProperty("LCEnd", LCEnd);
				 subNode.setProperty("LoadPort", LoadPort);
				 subNode.setProperty("DiscPort", DiscPort);
				 subNode.setProperty("VesselName", VesselName);
				 subNode.setProperty("RateType", RateType);
				 subNode.setProperty("Rate", Rate);
				 subNode.setProperty("Comments", Comment);
				 subNode.setProperty("ReportDate", ReportDate);
				 subNode.setProperty("Source", Source);
				 subNode.setProperty("Vesseltype", VesselType);
				 subNode.setProperty("ReportType", "2");

				 session.save();
			 }
			
		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}
	
	public static void TimecharterReportEditUi(PrintWriter out, Session session, String Information, String Spot_TC, String Date, String VesselName
			, String CPP_DPP, String Charterer, String Delivery, String DeliveryPlace, String Period, String Periodunit, String Redelivery
			, String TCRate, String Rate, String Unit, String Comments, String TimeStamp, String Source, String Vesseltype, String passNodeName){
		
		try {
			
			Node scorpio=null;
			Node TimeCharterReport=null;
			Node subNode=null;
			Node ReportData=null;
			Node TCReports=null;
			
			if(session.getRootNode().hasNode("scorpioDataBase")){
				scorpio = session.getRootNode().getNode("scorpioDataBase");
			}else{
				scorpio = session.getRootNode().addNode("scorpioDataBase");
				session.save();
			}
			
			if(scorpio.hasNode("ReportData")){
				ReportData = scorpio.getNode("ReportData");
			}else{
				ReportData = scorpio.addNode("ReportData");
				session.save();
			}
			
			if(ReportData.hasNode("TCReports")){
				TCReports = ReportData.getNode("TCReports");
			}else{
				TCReports = ReportData.addNode("TCReports");
				session.save();
			}
			
			
			
			if(TCReports.hasNode("TimeCharterReport")){
				TimeCharterReport=TCReports.getNode("TimeCharterReport");
			}else{
				TimeCharterReport=TCReports.addNode("TimeCharterReport");
				TimeCharterReport.setProperty("jcr:count", String.valueOf(0));
				TimeCharterReport.setProperty("netChangedPointer", String.valueOf(0));
				session.save();
			}
			
			 if(TimeCharterReport.hasNode(passNodeName)){
				 subNode=TimeCharterReport.getNode(passNodeName);
				
				/* subNode.setProperty("Sr.No", forid);
				 subNode.setProperty("Id", forid);*/
				 subNode.setProperty("Information", Information);
				 subNode.setProperty("Spot_TC",Spot_TC);
				 
				 subNode.setProperty("Date", Date);
				 subNode.setProperty("VesselName", VesselName);
				 subNode.setProperty("CPP_DPP", CPP_DPP);
				 subNode.setProperty("Charterer", Charterer);
				 subNode.setProperty("Delivery", Delivery);
				 subNode.setProperty("DeliveryPlace", DeliveryPlace);
				 subNode.setProperty("Period", Period);
				 subNode.setProperty("Periodunit", Periodunit);
				 subNode.setProperty("Redelivery", Redelivery);
				 subNode.setProperty("TCRate", TCRate);
				 subNode.setProperty("Rate", Rate);
				 subNode.setProperty("Unit", Unit);
				 subNode.setProperty("Comments", Comments);
				 subNode.setProperty("TimeStamp", TimeStamp);
				 subNode.setProperty("Source", Source);
				 subNode.setProperty("Vesseltype", Vesseltype);
				 subNode.setProperty("ReportType", "3");
				 subNode.setProperty("ReportTimestamp", TimeStamp);
				 
				 session.save();
				 
			 }
			
		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}
	
	public static void BrokerTcRateReportEditUi(PrintWriter out, Session session, String Broker, String VesselType, String Month_Year, String first_yr,
			String second_yr, String third_yr, String fifth_yr, String passNodeName){
			
			try {
				
				Node scorpio=null;
				Node BrokerTCRate=null;
				Node subNode=null;
				Node TCReports=null;
				Node ReportData=null;
				
				if(session.getRootNode().hasNode("scorpioDataBase")){
					scorpio = session.getRootNode().getNode("scorpioDataBase");
				}else{
					scorpio = session.getRootNode().addNode("scorpioDataBase");
					session.save();
				}
				
				if(scorpio.hasNode("ReportData")){
					ReportData = scorpio.getNode("ReportData");
				}else{
					ReportData = scorpio.addNode("ReportData");
					session.save();
				}
				
				if(ReportData.hasNode("TCReports")){
					TCReports = ReportData.getNode("TCReports");
				}else{
					TCReports = ReportData.addNode("TCReports");
					session.save();
				}
				
				if(TCReports.hasNode("BrokerTCRate")){
					BrokerTCRate=TCReports.getNode("BrokerTCRate");
				}else{
					BrokerTCRate=TCReports.addNode("BrokerTCRate");
					BrokerTCRate.setProperty("jcr:count", String.valueOf(0));
					BrokerTCRate.setProperty("netChangedPointer", String.valueOf(0));
					session.save();
				}
				
				 if(BrokerTCRate.hasNode(passNodeName)){
					 subNode=BrokerTCRate.getNode(passNodeName);
					 
					 /*subNode.setProperty("Sr.No", forid);
					 subNode.setProperty("Id", forid);*/
					
					 subNode.setProperty("Broker", Broker);
					 subNode.setProperty("VesselType", VesselType);
					 subNode.setProperty("Month_Year", Month_Year);
					 subNode.setProperty("first_yr", first_yr);
					 subNode.setProperty("second_yr", second_yr);
					 subNode.setProperty("third_yr", third_yr);
					 subNode.setProperty("fifth_yr", fifth_yr);
					 subNode.setProperty("ReportType", "4");
					 
					 session.save();
					 
				 }
				
				
			} catch (Exception e) {
				out.println(e.getMessage());
			}
		}
	
	
}
