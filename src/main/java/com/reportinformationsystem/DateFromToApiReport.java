package com.reportinformationsystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.Workspace;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.abhishek.AbhishekScript;
import com.abhishek.VinayaScript;
import com.pallavi.code.pallavi__json3_execution;
import com.processcountMail.ExecuteMailCount;
import com.readGmail.GmailMethods;

import ChangedStructureCurrent.FetchData;

public class DateFromToApiReport {

	public static void main(String[] args) {

		String day="11";
		boolean b3 = Pattern.matches("(\\d{1}$)", day); 
		if(b3==true){
			String s="0"+day;
			System.out.println(s);
		}else{
			System.out.println("not a single number");
		}
		
		String data="CPp/Clean";
		
		if(data.toLowerCase().contentEquals("cpp/clean")){
			System.out.println("yes");
		}else{
			System.out.println("not");
		}
		
		String gmaildata="/GmailData/UPDATED_LR2_POSITION_LISTS_BSS_FUJAIRAH";
		
		String data2="Lower";
		boolean hasLowercase=false;
		 hasLowercase = !data2.equals(data2.toUpperCase());
//		boolean s=false;
		
		if(hasLowercase==true){
			System.out.println("Must have a lowercase Character");
		}else{
			System.out.println("Must have an uppercase Character");
		}
		
		String filelinkhttps="http://dev.bizlem.io:8082/scorpio1/Project_Configuration_Details_1_WVDupdated_18.09.2018.csv";
		
		if(filelinkhttps.lastIndexOf("http:")!=-1){
		filelinkhttps=filelinkhttps.substring(filelinkhttps.lastIndexOf("http:") + 5);
		filelinkhttps="https:"+filelinkhttps;
		System.out.println(filelinkhttps);
		}
		
		
		/* String pythonScriptApiData=excelStanbolPythonScript("/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/HOWE_ROB_CLEAN_MIDDLE_EAST_MR_TONNAGE_LISTS_(BSS_AGRSEAWCI)_-_THURSDAY_31ST_JAN_2019_1_AGMR_TONNAGE_LIST_JANUARY_31ST_2019.xlsx");
		 System.out.println(pythonScriptApiData);*/
		 
		String output=processPdftoSvg("/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/CLARKSONS_PLATOU_AG_MR_LIST_DATED_22.02.19_1_CLARKSONSPLATOU_AG_MR_LIST_DATED_22.02.19.pdf");
		System.out.println("out:: "+output);
		
		
		
		/*if( gmaildata.lastIndexOf("/")!=-1 ){
		    int data=gmaildata.lastIndexOf("/");
		    String gmail=gmaildata.substring(data+1);
		    System.out.println(gmail);
		}*/
		//String data=pdfPythonScript("/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/svgoutput/TC_MARKET_REPORT_WEEK_46-47_1_weeklytc_report_46_47.json");
		/*String data=excelPythonScript("/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/UPDATED_LR2_POSITION_LISTS_BSS_FUJAIRAH_1_FUJAIRAHLR2_POSITION_LIST_23_OCT.xlsx");
		System.out.println(data);*/
		
		//String data1=excelStanbolPythonScript("/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/UPDATED_LR2_POSITION_LISTS_BSS_FUJAIRAH_1_FUJAIRAHLR2_POSITION_LIST_23_OCT.xlsx");
		
		/*String data1=excelStanbolPythonScript("/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/CLARKSONS_MIDDLE_EAST_CPP_TRACKERS_-_261018_1_ULSDTRACKER_2017_ONWARDS.xlsx");
		System.out.println(data1);*/
		
		//String filename="/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/svgoutput/McQuilling_Asia_-_Clean_East_Daily_Market_Report_(19_Dec_2018)_1_McQuillingAsia_Clean_East_Daily_19_Dec_2018_.json";
		
		/*String filename="/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/HANDYMR_DPP_CMED_TONNAGE_LIST_UPDATED_23_10_18._1.html";
		String data1=htmlStanbolPythonScript(filename);
		System.out.println(data1);*/
		
		/*String data4=pdfStanbolPythonScript("/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/svgoutput/Clarksons_Platou_Shipbroking_-_Aframax_Update_AND_Positions_-_Friday_October_26_2018_1_AFRAMAX10_26_18.json");
		System.out.println(data4);*/
	}
	
	public JSONObject getTonnageReportDate(Session session,String from, String to,String reportType,PrintWriter out) {

		JSONObject mainJsonobj=null;
		try {
			
			if( !GmailMethods.isNullString(from) && !GmailMethods.isNullString(to) && !GmailMethods.isNullString(reportType) ){

					
					FetchData fd = new FetchData();
					String reporttypequeryid = fd.check_ReportTypeToFetchapi(session, reportType, out);
//					out.println("reporttypequeryid: " + reporttypequeryid);
					
					String dataFrom=FetchData.convertStringToDate(from);
//					out.println("dataFrom: "+dataFrom);
					String dataTo=FetchData.convertStringToDate(to);
//					out.println("dataTo: "+dataTo);
					Workspace workspace = session.getWorkspace();

					String slingqery = "select [ReportTimestamp] from [nt:base] where ReportTimestamp >='" + dataFrom
							+ "'and ReportTimestamp <='" + dataTo 
							+ "' and ReportType="+reporttypequeryid+" and ISDESCENDANTNODE('/scorpioDataBase/ReportData/Tonnage/')";
//                    out.println("slingqery: "+slingqery);
					Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
					QueryResult queryResult = query.execute();

//					RowIterator rowIterator = queryResult.getRows();
					NodeIterator iterator = queryResult.getNodes();
					long size=iterator.getSize();
//					out.println("size: "+size);
					boolean nodeFlag = false;

					JSONObject keyNameObject=null;
					JSONArray keyNameObjectJsonarray=new JSONArray();
					
					while (iterator.hasNext()) {
					
						nodeFlag = true;
						Node nextnode = iterator.nextNode();
						
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
						
						JSONArray EmploymentStatusJsonarray=new JSONArray();
						JSONArray OwnersJsonarray=new JSONArray();
						JSONArray OpenPortJsonarray=new JSONArray();
						JSONArray OperatorsJsonarray=new JSONArray();
						JSONArray CargoTypeJsonarray=new JSONArray();
						JSONArray CommentJsonarray=new JSONArray();
						JSONArray ETABasisJsonarray=new JSONArray();
						JSONArray OpenDateJsonarray=new JSONArray();
						JSONArray ReportTypeJsonarray=new JSONArray();
						JSONArray ReportTimestampJsonarray=new JSONArray();
						JSONArray RepositionRegionJsonarray=new JSONArray();
						JSONArray SourceJsonarray=new JSONArray();
						JSONArray VesselNameJsonarray=new JSONArray();
						JSONArray BuiltJsonarray=new JSONArray();
						JSONArray DWTJsonarray=new JSONArray();
						JSONArray CubicsJsonarray=new JSONArray();
						JSONArray LOAJsonarray=new JSONArray();
						JSONArray ICEJsonarray=new JSONArray();
						JSONArray SternLinejsonarray=new JSONArray();
						JSONArray VesselTypejsonarray=new JSONArray();

						if(nextnode.hasProperty("Id")){
							Id=nextnode.getProperty("Id").getString();
						}
						
						if(nextnode.hasProperty("EmploymentStatus")){
							EmploymentStatus=nextnode.getProperty("EmploymentStatus").getString();
							EmploymentStatus=fd.fetch_EmployementStatus(session, EmploymentStatus);
							
							JSONObject EmploymentStatusJsonObject=new JSONObject();
							EmploymentStatusJsonObject.put("EmploymentStatus", EmploymentStatus);
							if(EmploymentStatusJsonObject.length()>0){
							EmploymentStatusJsonarray.put(EmploymentStatusJsonObject);
							}
						}
						if(nextnode.hasProperty("Owners")){
							Owners=nextnode.getProperty("Owners").getString();
							Owners=FetchData.fetch_Owners(session, Owners);
							
							JSONObject OwnersJsonObject=new JSONObject();
							OwnersJsonObject.put("Owners", Owners);
							if(OwnersJsonObject.length()>0){
							   OwnersJsonarray.put(OwnersJsonObject);
							}
						}
						if(nextnode.hasProperty("OpenPort")){
							OpenPort=nextnode.getProperty("OpenPort").getString();
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
							JSONObject OpenPortJsonObject=new JSONObject();
							OpenPortJsonObject.put("OpenPort", OpenPort);
							if(OpenPortJsonObject.length()>0){
							 OpenPortJsonarray.put(OpenPortJsonObject);
							}
						}//
						}
						if (GmailMethods.isNullString(RepositionRegion)) {
						if(nextnode.hasProperty("RepositionRegion")){
							RepositionRegion=nextnode.getProperty("RepositionRegion").getString();
							String RepositionRegion1 = fd.fetch_Port(session, RepositionRegion);
							boolean checkjsonString = SaveReportDataClass.isJSONValid(RepositionRegion1);
							if (checkjsonString == true) {
								JSONObject data = new JSONObject(RepositionRegion1);
								
								if (data.has("Area")) {
									RepositionRegion = data.getString("Area");
									// check_Port_repositionRegion_id=check_OpenPort_id;
								}

							}
							
							JSONObject RepositionRegionJsonaobject=new JSONObject();
							RepositionRegionJsonaobject.put("RepositionRegion", RepositionRegion);
							if(RepositionRegionJsonaobject.length()>0){
							RepositionRegionJsonarray.put(RepositionRegionJsonaobject);
							}
						}else{
							RepositionRegion = "";
						}
					}
						
						if(nextnode.hasProperty("Operators")){
							Operators=nextnode.getProperty("Operators").getString();
							Operators=fd.fetch_Operators(session, Operators);
							
							JSONObject OperatorsJsonaobject=new JSONObject();
							OperatorsJsonaobject.put("Operators", Operators);
							if(OperatorsJsonaobject.length()>0){
							OperatorsJsonarray.put(OperatorsJsonaobject);
							}
						}
						if(nextnode.hasProperty("CargoType")){
							CargoType=nextnode.getProperty("CargoType").getString();
							CargoType=fd.fetch_CargoType(session, CargoType);
							
							JSONObject CargoTypeJsonaobject=new JSONObject();
							CargoTypeJsonaobject.put("CargoType", CargoType);
							if(CargoTypeJsonaobject.length()>0){
							CargoTypeJsonarray.put(CargoTypeJsonaobject);
							}
						}
						if(nextnode.hasProperty("Comment")){
							Comment=nextnode.getProperty("Comment").getString();
							
							JSONObject CommentJsonaobject=new JSONObject();
							CommentJsonaobject.put("Comment", Comment);
							if(CommentJsonaobject.length()>0){
							CommentJsonarray.put(CommentJsonaobject);
							}
						}
						if(nextnode.hasProperty("ETABasis")){
							ETABasis=nextnode.getProperty("ETABasis").getString();
							
							JSONObject ETABasisJsonaobject=new JSONObject();
							ETABasisJsonaobject.put("ETABasis", ETABasis);
							if(ETABasisJsonaobject.length()>0){
							ETABasisJsonarray.put(ETABasisJsonaobject);
							}
						}
						if(nextnode.hasProperty("OpenDate")){
							OpenDate=nextnode.getProperty("OpenDate").getString();
							
							JSONObject OpenDateJsonaobject=new JSONObject();
							OpenDateJsonaobject.put("OpenDate", OpenDate);
							if(OpenDateJsonaobject.length()>0){
							OpenDateJsonarray.put(OpenDateJsonaobject);
							}
						}
						if(nextnode.hasProperty("ReportType")){
							ReportType=nextnode.getProperty("ReportType").getString();
							ReportType=fd.fetch_ReportType(session, ReportType);
							
							JSONObject ReportTypeJsonaobject=new JSONObject();
							ReportTypeJsonaobject.put("ReportType", ReportType);
							if(ReportTypeJsonaobject.length()>0){
							ReportTypeJsonarray.put(ReportTypeJsonaobject);
							}
						}
						if(nextnode.hasProperty("ReportTimestamp")){
							ReportTimestamp=nextnode.getProperty("ReportTimestamp").getString();
							
							JSONObject ReportTimestampJsonaobject=new JSONObject();
							ReportTimestampJsonaobject.put("ReportTimestamp", ReportTimestamp);
							if(ReportTimestampJsonaobject.length()>0){
							ReportTimestampJsonarray.put(ReportTimestampJsonaobject);
							}
						}
						/*if(nextnode.hasProperty("RepositionRegion")){
							RepositionRegion=nextnode.getProperty("RepositionRegion").getString();
							RepositionRegion=fd.fetch_Port(session, RepositionRegion);
							
							JSONObject RepositionRegionJsonaobject=new JSONObject();
							RepositionRegionJsonaobject.put("RepositionRegion", RepositionRegion);
							if(RepositionRegionJsonaobject.length()>0){
							RepositionRegionJsonarray.put(RepositionRegionJsonaobject);
							}
						}*/
						if(nextnode.hasProperty("Source")){
							Source=nextnode.getProperty("Source").getString();
							Source=fd.fetch_Source(session, Source);
							
							JSONObject SourceJsonaobject=new JSONObject();
							SourceJsonaobject.put("Source", Source);
							if(SourceJsonaobject.length()>0){
							SourceJsonarray.put(SourceJsonaobject);
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
							}}
							
							JSONObject VesselNameJsonaobject=new JSONObject();
							VesselNameJsonaobject.put("VesselName", VesselName);
							if(VesselNameJsonaobject.length()>0){
							VesselNameJsonarray.put(VesselNameJsonaobject);
							}
						}
						
						if(nextnode.hasProperty("Built")){
							String Built="";
							Built=nextnode.getProperty("Built").getString();
							
							JSONObject BuiltJsonaobject=new JSONObject();
							BuiltJsonaobject.put("Built", Built);
							if(BuiltJsonaobject.length()>0){
								BuiltJsonarray.put(BuiltJsonaobject);
							}
						}
						
						if(nextnode.hasProperty("DWT")){
							String DWT="";
							DWT=nextnode.getProperty("DWT").getString();
							
							JSONObject DWTJsonaobject=new JSONObject();
							DWTJsonaobject.put("DWT", DWT);
							if(DWTJsonaobject.length()>0){
								DWTJsonarray.put(DWTJsonaobject);
							}
						}
						
						if(nextnode.hasProperty("Cubics")){
							String Cubics="";
							Cubics=nextnode.getProperty("Cubics").getString();
							
							JSONObject CubicsJsonaobject=new JSONObject();
							CubicsJsonaobject.put("Cubics", Cubics);
							if(CubicsJsonaobject.length()>0){
								CubicsJsonarray.put(CubicsJsonaobject);
							}
						}
						
						if(nextnode.hasProperty("LOA")){
							String LOA="";
							LOA=nextnode.getProperty("LOA").getString();
							
							JSONObject LOAJsonaobject=new JSONObject();
							LOAJsonaobject.put("LOA", LOA);
							if(LOAJsonaobject.length()>0){
								LOAJsonarray.put(LOAJsonaobject);
							}
						}
						
						
						if(nextnode.hasProperty("ICE")){
							String ICE="";
							ICE=nextnode.getProperty("ICE").getString();
							
							JSONObject ICEJsonaobject=new JSONObject();
							ICEJsonaobject.put("ICE", ICE);
							if(ICEJsonaobject.length()>0){
								ICEJsonarray.put(ICEJsonaobject);
							}
						}
						
						
						if(nextnode.hasProperty("SternLine")){
							String SternLine="";
							SternLine=nextnode.getProperty("SternLine").getString();
							
							JSONObject SternLineJsonaobject=new JSONObject();
							SternLineJsonaobject.put("SternLine", SternLine);
							if(SternLineJsonaobject.length()>0){
								SternLinejsonarray.put(SternLineJsonaobject);
							}
						}
						
						if(nextnode.hasProperty("VesselType")){
							String VesselType="";
							VesselType=nextnode.getProperty("VesselType").getString();
							VesselType=FetchData.fetch_vesselType(session, VesselType);
							
							JSONObject VesselTypeJsonaobject=new JSONObject();
							VesselTypeJsonaobject.put("VesselType", VesselType);
							if(VesselTypeJsonaobject.length()>0){
								VesselTypejsonarray.put(VesselTypeJsonaobject);
							}
						}
						
						
						keyNameObject=new JSONObject();
						
						keyNameObject.put("tonnageId", Id);
						keyNameObject.put("EmploymentStatusJsonarray", EmploymentStatusJsonarray);
						keyNameObject.put("OwnersJsonarray", OwnersJsonarray);
						keyNameObject.put("OpenPortJsonarray", OpenPortJsonarray);
						keyNameObject.put("OperatorsJsonarray", OperatorsJsonarray);
						keyNameObject.put("CargoTypeJsonarray", CargoTypeJsonarray);
						keyNameObject.put("CommentJsonarray", CommentJsonarray);
						keyNameObject.put("ETABasisJsonarray", ETABasisJsonarray);
						keyNameObject.put("OpenDateJsonarray", OpenDateJsonarray);
						keyNameObject.put("ReportTypeJsonarray", ReportTypeJsonarray);
						keyNameObject.put("ReportTimestampJsonarray", ReportTimestampJsonarray);
						keyNameObject.put("RepositionRegionJsonarray", RepositionRegionJsonarray);
						keyNameObject.put("SourceJsonarray", SourceJsonarray);
						keyNameObject.put("VesselNameJsonarray", VesselNameJsonarray);
						keyNameObject.put("BuiltJsonarray", BuiltJsonarray);
						keyNameObject.put("DWTJsonarray", DWTJsonarray);
						keyNameObject.put("CubicsJsonarray", CubicsJsonarray);
						keyNameObject.put("LOAJsonarray", LOAJsonarray);
						keyNameObject.put("ICEJsonarray", ICEJsonarray);
						keyNameObject.put("SternLinejsonarray", SternLinejsonarray);
						keyNameObject.put("VesselTypejsonarray", VesselTypejsonarray);
						
						
						if(keyNameObject.length()>0){
						   keyNameObjectJsonarray.put(keyNameObject);
						}
						
						
						
					} // while
					
					
					if(!nodeFlag){
						keyNameObject=new JSONObject();
						keyNameObject.put("Error", "No New Data Available");
						keyNameObjectJsonarray.put(keyNameObject);
						
				 }
					
					if(keyNameObjectJsonarray.length()>0){
						 mainJsonobj=new JSONObject();
						 mainJsonobj.put("tonnageList", keyNameObjectJsonarray);

					}
					
					
					

				}

			} catch (Exception e) {
				out.println(e.getMessage());

			}
			return mainJsonobj;

		}

	public JSONObject getSpotReportDate(Session session,String from, String to,String reportType,PrintWriter out) {

		JSONObject mainJsonobj=null;
		try {
			
			if( !GmailMethods.isNullString(from) && !GmailMethods.isNullString(to) && !GmailMethods.isNullString(reportType) ){

					
					FetchData fd = new FetchData();
					String reporttypequeryid = fd.check_ReportTypeToFetchapi(session, reportType, out);
//					out.println("reporttypequeryid: " + reporttypequeryid);
					
					String dataFrom=FetchData.convertStringToDate(from);
//					out.println("dataFrom: "+dataFrom);
					String dataTo=FetchData.convertStringToDate(to);
//					out.println("dataTo: "+dataTo);
					Workspace workspace = session.getWorkspace();

					String slingqery = "select [ReportTimestamp] from [nt:base] where ReportTimestamp >='" + dataFrom
							+ "'and ReportTimestamp <='" + dataTo 
							+ "' and ReportType="+reporttypequeryid+" and ISDESCENDANTNODE('/scorpioDataBase/ReportData/Spot/')";
//                    out.println("slingqery: "+slingqery);
					Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
					QueryResult queryResult = query.execute();

//					RowIterator rowIterator = queryResult.getRows();
					NodeIterator iterator = queryResult.getNodes();
					long size=iterator.getSize();
//					out.println("size: "+size);
					boolean nodeFlag = false;

					JSONObject keyNameObject=null;
					JSONArray keyNameObjectJsonarray=new JSONArray();
					
					while (iterator.hasNext()) {
					
						nodeFlag = true;
						Node nextnode = iterator.nextNode();
						
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
						
						JSONArray InformationJsonarray=new JSONArray();
						JSONArray FixtureTypeJsonarray=new JSONArray();
						JSONArray ChartererJsonarray=new JSONArray();
						JSONArray StatusJsonarray=new JSONArray();
						JSONArray CargoTypeJsonarray=new JSONArray();
						JSONArray CargoGradeJsonarray=new JSONArray();
						JSONArray CargoQtyJsonarray=new JSONArray();
						JSONArray SourceJsonarray=new JSONArray();
						JSONArray VesselNameJsonarray=new JSONArray();
						JSONArray LCStartJsonarray=new JSONArray();
						JSONArray LCEndJsonarray=new JSONArray();
						JSONArray LoadPortJsonarray=new JSONArray();
						JSONArray DiscPortJsonarray=new JSONArray();
						JSONArray RateTypeJsonarray=new JSONArray();
						JSONArray RateJsonarray=new JSONArray();
						JSONArray CommentsJsonarray=new JSONArray();
						JSONArray ReportDateJsonarray=new JSONArray();
						JSONArray VesseltypeJsonarray=new JSONArray();
						
						

						if(nextnode.hasProperty("Id")){
							Id=nextnode.getProperty("Id").getString();
						}
						
						if(nextnode.hasProperty("Information")){
							Information=nextnode.getProperty("Information").getString();
							Information=fd.fetch_Information(session, Information);
							
							JSONObject InformationJsonObject=new JSONObject();
							InformationJsonObject.put("Information", Information);
							if(InformationJsonObject.length()>0){
								InformationJsonarray.put(InformationJsonObject);
							}
						}
						if(nextnode.hasProperty("FixtureType")){
							FixtureType=nextnode.getProperty("FixtureType").getString();
							FixtureType=fd.fetch_ReportType(session, FixtureType);
							
							JSONObject FixtureTypeJsonObject=new JSONObject();
							FixtureTypeJsonObject.put("FixtureType", FixtureType);
							if(FixtureTypeJsonObject.length()>0){
								FixtureTypeJsonarray.put(FixtureTypeJsonObject);
							}
						}
						if(nextnode.hasProperty("Charterer")){
							Charterer=nextnode.getProperty("Charterer").getString();
							Charterer=fd.fetch_Charterer(session, Charterer);
							
							JSONObject ChartererJsonObject=new JSONObject();
							 ChartererJsonObject.put("Charterer", Charterer);
							if(ChartererJsonObject.length()>0){
							   ChartererJsonarray.put(ChartererJsonObject);
							}
						}
						if(nextnode.hasProperty("Status")){
							Status=nextnode.getProperty("Status").getString();
							Status=fd.fetch_EmployementStatus(session, Status);
							
							JSONObject StatusJsonobject=new JSONObject();
							StatusJsonobject.put("Status", Status);
							if(StatusJsonobject.length()>0){
								StatusJsonarray.put(StatusJsonobject);
							}
						}
						if(nextnode.hasProperty("CargoType")){
							CargoType=nextnode.getProperty("CargoType").getString();
							CargoType=fd.fetch_CargoType(session, CargoType);
							
							JSONObject CargoTypeJsonaobject=new JSONObject();
							CargoTypeJsonaobject.put("CargoType", CargoType);
							if(CargoTypeJsonaobject.length()>0){
							CargoTypeJsonarray.put(CargoTypeJsonaobject);
							}
						}
						if(nextnode.hasProperty("CargoGrade")){
							CargoGrade=nextnode.getProperty("CargoGrade").getString();
							CargoGrade=fd.fetch_CargoGrade(session, CargoGrade);
							
							JSONObject CargoGradeJsonobject=new JSONObject();
							CargoGradeJsonobject.put("CargoGrade", CargoGrade);
							if(CargoGradeJsonobject.length()>0){
								CargoGradeJsonarray.put(CargoGradeJsonobject);
							}
						}
						if(nextnode.hasProperty("CargoQty")){
							CargoQty=nextnode.getProperty("CargoQty").getString();
							
							JSONObject CargoQtyJsonaobject=new JSONObject();
							CargoQtyJsonaobject.put("CargoQty", CargoQty);
							if(CargoQtyJsonaobject.length()>0){
								CargoQtyJsonarray.put(CargoQtyJsonaobject);
							}
						}
						if(nextnode.hasProperty("LCStart")){
							LCStart=nextnode.getProperty("LCStart").getString();
							
							JSONObject LCStartJsonaobject=new JSONObject();
							LCStartJsonaobject.put("LCStart", LCStart);
							if(LCStartJsonaobject.length()>0){
								LCStartJsonarray.put(LCStartJsonaobject);
							}
						}
						if(nextnode.hasProperty("LCEnd")){
							LCEnd=nextnode.getProperty("LCEnd").getString();
							
							JSONObject LCEndJsonaobject=new JSONObject();
							LCEndJsonaobject.put("LCEnd", LCEnd);
							if(LCEndJsonaobject.length()>0){
								LCEndJsonarray.put(LCEndJsonaobject);
							}
						}
						if(nextnode.hasProperty("LoadPort")){
							LoadPort=nextnode.getProperty("LoadPort").getString();
							String LoadPort1 = fd.fetch_Port(session, LoadPort);
							//out.println("LoadPort1: "+LoadPort1);
							boolean checkjsonString=SaveReportDataClass.isJSONValid(LoadPort1);
							   if(checkjsonString==true){
								   JSONObject data=new JSONObject(LoadPort1);
								   if(data.has("portName")){
									   LoadPort=data.getString("portName");
									   //out.println("LoadPort: "+LoadPort);
								   }
								   
							JSONObject LoadPortJsonaobject=new JSONObject();
							LoadPortJsonaobject.put("LoadPort", LoadPort);
							if(LoadPortJsonaobject.length()>0){
								LoadPortJsonarray.put(LoadPortJsonaobject);
							}
					      }
						}
						if(nextnode.hasProperty("DiscPort")){
							DiscPort=nextnode.getProperty("DiscPort").getString();
							String LoadPort1 = fd.fetch_Port(session, LoadPort);
							//out.println("LoadPort1: "+LoadPort1);
							boolean checkjsonString=SaveReportDataClass.isJSONValid(LoadPort1);
							   if(checkjsonString==true){
								   JSONObject data=new JSONObject(LoadPort1);
								   if(data.has("portName")){
									   DiscPort=data.getString("portName");
									   //out.println("LoadPort: "+LoadPort);
								   }
							
							JSONObject DiscPortJsonaobject=new JSONObject();
							DiscPortJsonaobject.put("DiscPort", DiscPort);
							if(DiscPortJsonaobject.length()>0){
								DiscPortJsonarray.put(DiscPortJsonaobject);
							}
						  }
						}
						if(nextnode.hasProperty("VesselName")){
							VesselName=nextnode.getProperty("VesselName").getString();
							
							VesselName=fd.fetch_vesselName(session, VesselName);
							JSONObject fetch_vesselNamejsonobject=new JSONObject(VesselName);
							if(fetch_vesselNamejsonobject.has("vessel_Id")){
								VesselName=fetch_vesselNamejsonobject.getString("vessel_Id");
							}
							
							JSONObject VesselNameJsonaobject=new JSONObject();
							VesselNameJsonaobject.put("VesselName", VesselName);
							if(VesselNameJsonaobject.length()>0){
								VesselNameJsonarray.put(VesselNameJsonaobject);
							}
						}
						if(nextnode.hasProperty("RateType")){
							RateType=nextnode.getProperty("RateType").getString();
							RateType=fd.fetch_RateType(session, RateType);
							
							JSONObject RateTypeJsonaobject=new JSONObject();
							RateTypeJsonaobject.put("RateType", RateType);
							if(RateTypeJsonaobject.length()>0){
								RateTypeJsonarray.put(RateTypeJsonaobject);
							}
						}
						if(nextnode.hasProperty("Rate")){
							Rate=nextnode.getProperty("Rate").getString();
							
							JSONObject RateJsonaobject=new JSONObject();
							RateJsonaobject.put("Rate", Rate);
							if(RateJsonaobject.length()>0){
								RateJsonarray.put(RateJsonaobject);
							}
						}
						if(nextnode.hasProperty("Comments")){
							Comments=nextnode.getProperty("Comments").getString();
							
							JSONObject CommentsJsonaobject=new JSONObject();
							CommentsJsonaobject.put("Comments", Comments);
							if(CommentsJsonaobject.length()>0){
								CommentsJsonarray.put(CommentsJsonaobject);
							}
						}
						if(nextnode.hasProperty("ReportDate")){
							String ReportDate="";
							ReportDate=nextnode.getProperty("ReportDate").getString();
							
							JSONObject ReportDateJsonaobject=new JSONObject();
							ReportDateJsonaobject.put("ReportDate", ReportDate);
							if(ReportDateJsonaobject.length()>0){
								ReportDateJsonarray.put(ReportDateJsonaobject);
							}
						}
						if(nextnode.hasProperty("Source")){
							Source=nextnode.getProperty("Source").getString();
							Source=fd.fetch_Source(session, Source);
							
							JSONObject SourceJsonaobject=new JSONObject();
							SourceJsonaobject.put("Source", Source);
							if(SourceJsonaobject.length()>0){
								SourceJsonarray.put(SourceJsonaobject);
							}
						}
						if(nextnode.hasProperty("Vesseltype")){
							String Vesseltype="";
							Vesseltype=nextnode.getProperty("Vesseltype").getString();
							Vesseltype=FetchData.fetch_vesselType(session, Vesseltype);
							
							JSONObject VesseltypeJsonaobject=new JSONObject();
							VesseltypeJsonaobject.put("Vesseltype", Vesseltype);
							if(VesseltypeJsonaobject.length()>0){
								VesseltypeJsonarray.put(VesseltypeJsonaobject);
							}
						}
						keyNameObject =new JSONObject();
						
						keyNameObject.put("spotId", Id);
						keyNameObject.put("InformationJsonarray", InformationJsonarray);
						keyNameObject.put("FixtureTypeJsonarray", FixtureTypeJsonarray);
						keyNameObject.put("LCStartJsonarray", LCStartJsonarray);
						keyNameObject.put("LCEndJsonarray", LCEndJsonarray);
						keyNameObject.put("LoadPortJsonarray", LoadPortJsonarray);
						keyNameObject.put("DiscPortJsonarray", DiscPortJsonarray);
						keyNameObject.put("VesselNameJsonarray", VesselNameJsonarray);
						keyNameObject.put("ChartererJsonarray", ChartererJsonarray);
						keyNameObject.put("StatusJsonarray", StatusJsonarray);
						keyNameObject.put("CargoTypeJsonarray", CargoTypeJsonarray);
						keyNameObject.put("CargoGradeJsonarray", CargoGradeJsonarray);
						keyNameObject.put("CargoQtyJsonarray", CargoQtyJsonarray);
						keyNameObject.put("RateTypeJsonarray", RateTypeJsonarray);
						keyNameObject.put("RateJsonarray", RateJsonarray);
						keyNameObject.put("CommentsJsonarray", CommentsJsonarray);
						keyNameObject.put("ReportDateJsonarray", ReportDateJsonarray);
						keyNameObject.put("SourceJsonarray", SourceJsonarray);
						keyNameObject.put("VesseltypeJsonarray", VesseltypeJsonarray);
						
						if(keyNameObject.length()>0){
						   keyNameObjectJsonarray.put(keyNameObject);
						}
						
						
						
					} // while
					
					
					if(!nodeFlag){
						keyNameObject=new JSONObject();
						keyNameObject.put("Error", "No New Data Available");
						keyNameObjectJsonarray.put(keyNameObject);
						
				 }
					
					if(keyNameObjectJsonarray.length()>0){
						 mainJsonobj=new JSONObject();
						 mainJsonobj.put("spotList", keyNameObjectJsonarray);

					}
					
					
					

				}

			} catch (Exception e) {
				out.println(e.getMessage());

			}
			return mainJsonobj;

		}

	public JSONObject getTimeCharterReportDate(Session session,String from, String to,String reportType,PrintWriter out) {

		JSONObject mainJsonobj=null;
		try {
			
			if( !GmailMethods.isNullString(from) && !GmailMethods.isNullString(to) && !GmailMethods.isNullString(reportType) ){

					
					FetchData fd = new FetchData();
					String reporttypequeryid = fd.check_ReportTypeToFetchapi(session, reportType, out);
//					out.println("reporttypequeryid: " + reporttypequeryid);
					
					String dataFrom=FetchData.convertStringToDate(from);
//					out.println("dataFrom: "+dataFrom);
					String dataTo=FetchData.convertStringToDate(to);
//					out.println("dataTo: "+dataTo);
					Workspace workspace = session.getWorkspace();

					String slingqery = "select [ReportTimestamp] from [nt:base] where ReportTimestamp >='" + dataFrom
							+ "'and ReportTimestamp <='" + dataTo 
							+ "' and ReportType="+reporttypequeryid+" and ISDESCENDANTNODE('/scorpioDataBase/ReportData/TCReports/TimeCharterReport/')";
//                    out.println("slingqery: "+slingqery);
					Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
					QueryResult queryResult = query.execute();

//					RowIterator rowIterator = queryResult.getRows();
					NodeIterator iterator = queryResult.getNodes();
					long size=iterator.getSize();
//					out.println("size: "+size);
					boolean nodeFlag = false;

					JSONObject keyNameObject=null;
					JSONArray keyNameObjectJsonarray=new JSONArray();
					
					while (iterator.hasNext()) {
					
						nodeFlag = true;
						Node nextnode = iterator.nextNode();
						
						String Information="";
						String Charterer="";
						String Source="";
						String VesselName="";
						String Comments="";
						String Rate="";
						String Id="";
						
						JSONArray InformationJsonarray=new JSONArray();
						JSONArray Spot_TCJsonarray=new JSONArray();
						JSONArray DateJsonarray=new JSONArray();
						JSONArray CPP_DPPJsonarray=new JSONArray();
						JSONArray VesselNameJsonarray=new JSONArray();
						JSONArray ChartererJsonarray= new JSONArray();
						JSONArray DeliveryJsonarray=new JSONArray();
						JSONArray DeliveryPlaceJsonarray=new JSONArray();
						JSONArray SourceJsonarray=new JSONArray();
						
						JSONArray RateJsonarray=new JSONArray();
						JSONArray CommentsJsonarray=new JSONArray();
						JSONArray PeriodJsonarray=new JSONArray();
						JSONArray VesseltypeJsonarray=new JSONArray();
						JSONArray RedeliveryJsonarray=new JSONArray();
						JSONArray TCRateJsonarray=new JSONArray();
						JSONArray TimeStampJsonarray=new JSONArray();
						JSONArray PeriodunitJsonarray=new JSONArray();
						JSONArray UnitJsonarray=new JSONArray();
						
						

						if(nextnode.hasProperty("Id")){
							Id=nextnode.getProperty("Id").getString();
						}
						
						if(nextnode.hasProperty("Information")){
							Information=nextnode.getProperty("Information").getString();
							Information=fd.fetch_Information(session, Information);
							
							JSONObject InformationJsonObject=new JSONObject();
							InformationJsonObject.put("Information", Information);
							if(InformationJsonObject.length()>0){
								InformationJsonarray.put(InformationJsonObject);
							}
						}
						if(nextnode.hasProperty("Spot_TC")){
							String Spot_TC="";
							Spot_TC=nextnode.getProperty("Spot_TC").getString();
							Spot_TC=fd.fetch_ReportType(session, Spot_TC);
							
							JSONObject Spot_TCJsonObject=new JSONObject();
							Spot_TCJsonObject.put("Spot_TC", Spot_TC);
							if(Spot_TCJsonObject.length()>0){
								Spot_TCJsonarray.put(Spot_TCJsonObject);
							}
						}
						if(nextnode.hasProperty("Date")){
							String Date="";
							Date=nextnode.getProperty("Date").getString();
							
							JSONObject DateJsonObject=new JSONObject();
							DateJsonObject.put("Date", Date);
							if(DateJsonObject.length()>0){
								DateJsonarray.put(DateJsonObject);
							}
						}
						
						if(nextnode.hasProperty("VesselName")){
							VesselName=nextnode.getProperty("VesselName").getString();
							
							VesselName=fd.fetch_vesselName(session, VesselName);
							JSONObject fetch_vesselNamejsonobject=new JSONObject(VesselName);
							if(fetch_vesselNamejsonobject.has("vessel_Id")){
								VesselName=fetch_vesselNamejsonobject.getString("vessel_Id");
							}
							
							JSONObject VesselNameJsonaobject=new JSONObject();
							VesselNameJsonaobject.put("VesselName", VesselName);
							if(VesselNameJsonaobject.length()>0){
								VesselNameJsonarray.put(VesselNameJsonaobject);
							}
						}
						
						
						if(nextnode.hasProperty("CPP_DPP")){
							String CPP_DPP="";
							CPP_DPP=nextnode.getProperty("CPP_DPP").getString();
							CPP_DPP=fd.fetch_CargoType(session, CPP_DPP);
							
							JSONObject CPP_DPPJsonobject=new JSONObject();
							CPP_DPPJsonobject.put("CPP_DPP", CPP_DPP);
							if(CPP_DPPJsonobject.length()>0){
								CPP_DPPJsonarray.put(CPP_DPPJsonobject);
							}
						}
						if(nextnode.hasProperty("Charterer")){
							Charterer=nextnode.getProperty("Charterer").getString();
							Charterer=fd.fetch_Charterer(session, Charterer);
							
							JSONObject ChartererJsonaobject=new JSONObject();
							ChartererJsonaobject.put("Charterer", Charterer);
							if(ChartererJsonaobject.length()>0){
								ChartererJsonarray.put(ChartererJsonaobject);
							}
						}
						if(nextnode.hasProperty("Delivery")){
							String Delivery="";
							Delivery=nextnode.getProperty("Delivery").getString();
							
							JSONObject DeliveryJsonobject=new JSONObject();
							DeliveryJsonobject.put("Delivery", Delivery);
							if(DeliveryJsonobject.length()>0){
								DeliveryJsonarray.put(DeliveryJsonobject);
							}
						}
						if(nextnode.hasProperty("DeliveryPlace")){
							String DeliveryPlace="";
							DeliveryPlace=nextnode.getProperty("DeliveryPlace").getString();
							DeliveryPlace=fd.fetch_DeliveryPlace(session, DeliveryPlace);
							
							JSONObject DeliveryPlaceJsonaobject=new JSONObject();
							DeliveryPlaceJsonaobject.put("DeliveryPlace", DeliveryPlace);
							if(DeliveryPlaceJsonaobject.length()>0){
								DeliveryPlaceJsonarray.put(DeliveryPlaceJsonaobject);
							}
						}
						if(nextnode.hasProperty("Period")){
							String Period="";
							Period=nextnode.getProperty("Period").getString();
							
							JSONObject PeriodJsonaobject=new JSONObject();
							PeriodJsonaobject.put("Period", Period);
							if(PeriodJsonaobject.length()>0){
								PeriodJsonarray.put(PeriodJsonaobject);
							}
						}
						if(nextnode.hasProperty("Periodunit")){
							String Periodunit="";
							Periodunit=nextnode.getProperty("Periodunit").getString();
							Periodunit=fd.fetch_Periodunit(session, Periodunit);
							
							JSONObject PeriodunitJsonaobject=new JSONObject();
							PeriodunitJsonaobject.put("Periodunit", Periodunit);
							if(PeriodunitJsonaobject.length()>0){
								PeriodunitJsonarray.put(PeriodunitJsonaobject);
							}
						}
						if(nextnode.hasProperty("Redelivery")){
							String Redelivery="";
							Redelivery=nextnode.getProperty("Redelivery").getString();
							
							JSONObject RedeliveryJsonaobject=new JSONObject();
							RedeliveryJsonaobject.put("Redelivery", Redelivery);
							if(RedeliveryJsonaobject.length()>0){
								RedeliveryJsonarray.put(RedeliveryJsonaobject);
							}
						}
						if(nextnode.hasProperty("TCRate")){
							String TCRate="";
							TCRate=nextnode.getProperty("TCRate").getString();
							
							JSONObject TCRateJsonaobject=new JSONObject();
							TCRateJsonaobject.put("TCRate", TCRate);
							if(TCRateJsonaobject.length()>0){
								TCRateJsonarray.put(TCRateJsonaobject);
							}
						}
						
						if(nextnode.hasProperty("Rate")){
							Rate=nextnode.getProperty("Rate").getString();
							
							JSONObject RateJsonaobject=new JSONObject();
							RateJsonaobject.put("Rate", Rate);
							if(RateJsonaobject.length()>0){
								RateJsonarray.put(RateJsonaobject);
							}
						}
						
						if(nextnode.hasProperty("Unit")){
							String Unit="";
							Unit=nextnode.getProperty("Unit").getString();
							Unit=fd.fetch_currencyUnit(session, Unit);
							
							JSONObject UnitJsonaobject=new JSONObject();
							UnitJsonaobject.put("Unit", Unit);
							if(UnitJsonaobject.length()>0){
								UnitJsonarray.put(UnitJsonaobject);
							}
						}
						
						if(nextnode.hasProperty("Comments")){
							Comments=nextnode.getProperty("Comments").getString();
							
							JSONObject CommentsJsonaobject=new JSONObject();
							CommentsJsonaobject.put("Comments", Comments);
							if(CommentsJsonaobject.length()>0){
								CommentsJsonarray.put(CommentsJsonaobject);
							}
						}
						
						if(nextnode.hasProperty("Source")){
							Source=nextnode.getProperty("Source").getString();
							Source=fd.fetch_Source(session, Source);
							
							JSONObject SourceJsonaobject=new JSONObject();
							SourceJsonaobject.put("Source", Source);
							if(SourceJsonaobject.length()>0){
								SourceJsonarray.put(SourceJsonaobject);
							}
						}
						
						if(nextnode.hasProperty("Vesseltype")){
							String Vesseltype="";
							Vesseltype=nextnode.getProperty("Vesseltype").getString();
							Vesseltype=FetchData.fetch_vesselType(session, Vesseltype);
							
							JSONObject VesseltypeJsonaobject=new JSONObject();
							VesseltypeJsonaobject.put("Vesseltype", Vesseltype);
							if(VesseltypeJsonaobject.length()>0){
								VesseltypeJsonarray.put(VesseltypeJsonaobject);
							}
						}
						
						if(nextnode.hasProperty("TimeStamp")){
							String TimeStamp="";
							TimeStamp=nextnode.getProperty("TimeStamp").getString();
							
							JSONObject TimeStampJsonaobject=new JSONObject();
							TimeStampJsonaobject.put("TimeStamp", TimeStamp);
							if(TimeStampJsonaobject.length()>0){
								TimeStampJsonarray.put(TimeStampJsonaobject);
							}
						}
						
						keyNameObject =new JSONObject();
						
						keyNameObject.put("TimeCharterReportsId", Id);
						keyNameObject.put("InformationJsonarray", InformationJsonarray);
						keyNameObject.put("Spot_TCJsonarray", Spot_TCJsonarray);
						keyNameObject.put("DateJsonarray", DateJsonarray);
						keyNameObject.put("VesselNameJsonarray", VesselNameJsonarray);
						keyNameObject.put("CPP_DPPJsonarray", CPP_DPPJsonarray);
						keyNameObject.put("ChartererJsonarray", ChartererJsonarray);
						keyNameObject.put("DeliveryJsonarray", DeliveryJsonarray);
						keyNameObject.put("DeliveryPlaceJsonarray", DeliveryPlaceJsonarray);
						keyNameObject.put("PeriodJsonarray", PeriodJsonarray);
						keyNameObject.put("PeriodunitJsonarray", PeriodunitJsonarray);
						keyNameObject.put("RedeliveryJsonarray", RedeliveryJsonarray);
						keyNameObject.put("TCRateJsonarray", TCRateJsonarray);
						keyNameObject.put("RateJsonarray", RateJsonarray);
						keyNameObject.put("UnitJsonarray", UnitJsonarray);
						keyNameObject.put("CommentsJsonarray", CommentsJsonarray);
						keyNameObject.put("SourceJsonarray", SourceJsonarray);
						keyNameObject.put("VesseltypeJsonarray", VesseltypeJsonarray);
						keyNameObject.put("TimeStampJsonarray", TimeStampJsonarray);
						
						if(keyNameObject.length()>0){
						   keyNameObjectJsonarray.put(keyNameObject);
						}
						
						
						
					} // while
					
					
					if(!nodeFlag){
						keyNameObject=new JSONObject();
						keyNameObject.put("Error", "No New Data Available");
						keyNameObjectJsonarray.put(keyNameObject);
						
				 }
					
					if(keyNameObjectJsonarray.length()>0){
						 mainJsonobj=new JSONObject();
						 mainJsonobj.put("TimeCharterReportsList", keyNameObjectJsonarray);

					}
					
					
					

				}

			} catch (Exception e) {
				out.println(e.getMessage());

			}
			return mainJsonobj;

		}
	
	public JSONObject getBrokerTcRateReportDate(Session session,String from, String to,String reportType,PrintWriter out) {

		JSONObject mainJsonobj=null;
		try {
			
			if( !GmailMethods.isNullString(from) && !GmailMethods.isNullString(to) && !GmailMethods.isNullString(reportType) ){

					
					FetchData fd = new FetchData();
					String reporttypequeryid = fd.check_ReportTypeToFetchapi(session, reportType, out);
//					out.println("reporttypequeryid: " + reporttypequeryid);
					
					String dataFrom=FetchData.convertStringToDate(from);
//					out.println("dataFrom: "+dataFrom);
					String dataTo=FetchData.convertStringToDate(to);
//					out.println("dataTo: "+dataTo);
					Workspace workspace = session.getWorkspace();

					String slingqery = "select [ReportTimestamp] from [nt:base] where ReportTimestamp >='" + dataFrom
							+ "'and ReportTimestamp <='" + dataTo 
							+ "' and ReportType="+reporttypequeryid+" and ISDESCENDANTNODE('/scorpioDataBase/ReportData/TCReports/BrokerTCRate/')";
//                    out.println("slingqery: "+slingqery);
					Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
					QueryResult queryResult = query.execute();

//					RowIterator rowIterator = queryResult.getRows();
					NodeIterator iterator = queryResult.getNodes();
					long size=iterator.getSize();
//					out.println("size: "+size);
					boolean nodeFlag = false;

					JSONObject keyNameObject=null;
					JSONArray keyNameObjectJsonarray=new JSONArray();
					
					while (iterator.hasNext()) {
					
						nodeFlag = true;
						Node nextnode = iterator.nextNode();
						
						String Id="";
						
						JSONArray BrokerJsonarray=new JSONArray();
						JSONArray VesselTypeJsonarray=new JSONArray();
						JSONArray Month_YearJsonarray=new JSONArray();
						JSONArray first_yrJsonarray=new JSONArray();
						JSONArray second_yrJsonarray=new JSONArray();
						JSONArray third_yrJsonarray= new JSONArray();
						JSONArray fifth_yrJsonarray= new JSONArray();
						
						

						if(nextnode.hasProperty("Id")){
							
							Id=nextnode.getProperty("Id").getString();
						}
						
						if(nextnode.hasProperty("Broker")){
							String Broker="";
							Broker=nextnode.getProperty("Broker").getString();
							Broker=fd.fetch_Source(session, Broker);
							
							JSONObject BrokerJsonObject=new JSONObject();
							BrokerJsonObject.put("Broker", Broker);
							if(BrokerJsonObject.length()>0){
								BrokerJsonarray.put(BrokerJsonObject);
							}
						}
						if(nextnode.hasProperty("VesselType")){
							String VesselType="";
							VesselType=nextnode.getProperty("VesselType").getString();
							VesselType=FetchData.fetch_vesselType(session, VesselType);
							
							JSONObject VesselTypeJsonObject=new JSONObject();
							VesselTypeJsonObject.put("VesselType", VesselType);
							if(VesselTypeJsonObject.length()>0){
								VesselTypeJsonarray.put(VesselTypeJsonObject);
							}
						}
						if(nextnode.hasProperty("Month_Year")){
							String Month_Year="";
							Month_Year=nextnode.getProperty("Month_Year").getString();
							
							JSONObject Month_YearJsonObject=new JSONObject();
							Month_YearJsonObject.put("Month_Year", Month_Year);
							if(Month_YearJsonObject.length()>0){
								Month_YearJsonarray.put(Month_YearJsonObject);
							}
						}
						
						if(nextnode.hasProperty("first_yr")){
							String first_yr="";
							first_yr=nextnode.getProperty("first_yr").getString();
							
							JSONObject first_yrJsonaobject=new JSONObject();
							first_yrJsonaobject.put("first_yr", first_yr);
							if(first_yrJsonaobject.length()>0){
								first_yrJsonarray.put(first_yrJsonaobject);
							}
						}
						
						
						if(nextnode.hasProperty("second_yr")){
							String second_yr="";
							second_yr=nextnode.getProperty("second_yr").getString();
							
							JSONObject second_yrJsonobject=new JSONObject();
							second_yrJsonobject.put("second_yr", second_yr);
							if(second_yrJsonobject.length()>0){
								second_yrJsonarray.put(second_yrJsonobject);
							}
						}
						if(nextnode.hasProperty("third_yr")){
							String third_yr="";
							third_yr=nextnode.getProperty("third_yr").getString();
							
							JSONObject third_yrJsonaobject=new JSONObject();
							third_yrJsonaobject.put("third_yr", third_yr);
							if(third_yrJsonaobject.length()>0){
								third_yrJsonarray.put(third_yrJsonaobject);
							}
						}
						if(nextnode.hasProperty("fifth_yr")){
							String fifth_yr="";
							fifth_yr=nextnode.getProperty("fifth_yr").getString();
							
							JSONObject fifth_yrJsonobject=new JSONObject();
							fifth_yrJsonobject.put("fifth_yr", fifth_yr);
							if(fifth_yrJsonobject.length()>0){
								fifth_yrJsonarray.put(fifth_yrJsonobject);
							}
						}
						
						   keyNameObject=new JSONObject();
							
							keyNameObject.put("BrokerTcRateId", Id);
							keyNameObject.put("BrokerJsonarray", BrokerJsonarray);
							keyNameObject.put("VesselTypeJsonarray", VesselTypeJsonarray);
							keyNameObject.put("Month_YearJsonarray", Month_YearJsonarray);
							keyNameObject.put("first_yrJsonarray", first_yrJsonarray);
							keyNameObject.put("second_yrJsonarray", second_yrJsonarray);
							keyNameObject.put("third_yrJsonarray", third_yrJsonarray);
							keyNameObject.put("fifth_yrJsonarray", fifth_yrJsonarray);
							
						if(keyNameObject.length()>0){
						   keyNameObjectJsonarray.put(keyNameObject);
						}
						
						
						
					} // while
					
					
					if(!nodeFlag){
						keyNameObject=new JSONObject();
						keyNameObject.put("Error", "No New Data Available");
						keyNameObjectJsonarray.put(keyNameObject);
						
				 }
					
					if(keyNameObjectJsonarray.length()>0){
						 mainJsonobj=new JSONObject();
						 mainJsonobj.put("BrokerTcRateList", keyNameObjectJsonarray);

					}
					
					
					

				}

			} catch (Exception e) {
				out.println(e.getMessage());

			}
			return mainJsonobj;

		}
	
	 public static String pdfPythonScript(String fileName){
	   		
	   		StringBuffer response1=null;
	   		
	   		try {

	   			String url = "";
	   			url="http://dev.bizlem.io:5002/?file="+fileName;
	   			
	   			//url="http://dev.bizlem.io:5006/?file="+fileName+ "&active=" + active;
	   			
	   			url = url.replace(" ", "%20");
	   			
	   			URL url1 = new URL(url);
	   			HttpURLConnection con = (HttpURLConnection) url1.openConnection();
	   			int responseCode = con.getResponseCode();
//	   			System.out.println("responseCode: "+responseCode);

	   			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	   			String inputLine;
	   			 response1 = new StringBuffer();

	   			while ((inputLine = in.readLine()) != null) {
	   				response1.append(inputLine);
	   			}
	   			in.close();

	   		} catch (Exception e) {
//	   			System.out.println(e.getMessage());
//	   			out.println(e.getMessage());
	   			return "";
	   			
	   		}
	   		
	   		return response1.toString();
	   		
	   	}
	 
	 
	 
	 public static String pdfStanbolPythonScriptUrlMethods(String fileName){
	   		
	   		StringBuffer response1=null;
	   		
	   		try {

	   			String url = "";
	   			url="http://dev.bizlem.io:5010/?file="+fileName;
	   			
	   			//url="http://dev.bizlem.io:5006/?file="+fileName+ "&active=" + active;
	   			
	   			url = url.replace(" ", "%20");
	   			
	   			URL url1 = new URL(url);
	   			HttpURLConnection con = (HttpURLConnection) url1.openConnection();
	   			con.setConnectTimeout(900000);
	   			con.setReadTimeout(900000); // minute to milliseconds
	   			int responseCode = con.getResponseCode();
//	   			System.out.println("responseCode: "+responseCode);

	   			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	   			String inputLine;
	   			 response1 = new StringBuffer();

	   			while ((inputLine = in.readLine()) != null) {
	   				response1.append(inputLine);
	   			}
	   			in.close();

	   		} catch (Exception e) {
	   			//System.out.println(e.getMessage());
//	   			out.println(e.getMessage());
	   			return "false";
	   			
	   		}
	   		
	   		return response1.toString();
	   		
	   	}
	 
	 public static String pdfStanbolPythonScript(String fileName){
	   		
	   		StringBuffer response1=null;
	   		
	   		try {

	   			String url = "";
	   			url="http://dev.bizlem.io:5007/?file="+fileName;
	   			
	   			//url="http://dev.bizlem.io:5006/?file="+fileName+ "&active=" + active;
	   			
	   			url = url.replace(" ", "%20");
	   			
	   			URL url1 = new URL(url);
	   			HttpURLConnection con = (HttpURLConnection) url1.openConnection();
	   			con.setConnectTimeout(480000);
	   			con.setReadTimeout(600000); // minute to milliseconds
	   			int responseCode = con.getResponseCode();
//	   			System.out.println("responseCode: "+responseCode);

	   			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	   			String inputLine;
	   			 response1 = new StringBuffer();

	   			while ((inputLine = in.readLine()) != null) {
	   				response1.append(inputLine);
	   			}
	   			in.close();

	   		} catch (Exception e) {
	   			//System.out.println(e.getMessage());
//	   			out.println(e.getMessage());
	   			return "false";
	   			
	   		}
	   		
	   		return response1.toString();
	   		
	   	}
	 
	 public static String excelPythonScript(String fileName){
	   		
	   		StringBuffer response1=null;
	   		
	   		try {

	   			String url = "";
	   			//url="http://35.199.31.139:5002/?file="+fileName;
	   			url="http://dev.bizlem.io:5000/?file="+fileName;
	   			
	   			//url="http://dev.bizlem.io:5006/?file="+fileName+ "&active=" + active;
	   			
	   			url = url.replace(" ", "%20");
	   			
	   			URL url1 = new URL(url);
	   			HttpURLConnection con = (HttpURLConnection) url1.openConnection();
	   			int responseCode = con.getResponseCode();
//	   			System.out.println("responseCode: "+responseCode);

	   			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	   			String inputLine;
	   			 response1 = new StringBuffer();

	   			while ((inputLine = in.readLine()) != null) {
	   				response1.append(inputLine);
	   			}
	   			in.close();

	   		} catch (Exception e) {
//	   			out.println(e.getMessage());
	   		}
	   		
	   		return response1.toString();
	   		
	   	}
	 
	  public static String textPythonScript(String fileName){
	   		
	   		StringBuffer response1=null;
	   		
	   		try {

	   			String url = "";
	   			url="http://dev.bizlem.io:5001/?file="+fileName;
	   			//url="http://dev.bizlem.io:5006/?file="+fileName+ "&active=" + active;
	   			
	   			url = url.replace(" ", "%20");
	   			
	   			URL url1 = new URL(url);
	   			HttpURLConnection con = (HttpURLConnection) url1.openConnection();
	   			int responseCode = con.getResponseCode();
//	   			System.out.println("responseCode: "+responseCode);

	   			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	   			String inputLine;
	   			 response1 = new StringBuffer();

	   			while ((inputLine = in.readLine()) != null) {
	   				response1.append(inputLine);
	   			}
	   			in.close();

	   		} catch (Exception e) {
//	   			out.println(e.getMessage());
	   		}
	   		
	   		return response1.toString();
	   		
	   	}
	  
	  public static String htmlStanbolPythonScript(String fileName){
	   		
	   		StringBuffer response1=null;
	   		
	   		try {

	   			String url = "";
//	   			url="http://dev.bizlem.io:5006/?file="+fileName;
	   			url="http://dev.bizlem.io:5021/?file="+fileName;
	   			
	   			//url="http://dev.bizlem.io:5006/?file="+fileName+ "&active=" + active;
	   			
	   			url = url.replace(" ", "%20");
	   			
	   			URL url1 = new URL(url);
	   			HttpURLConnection con = (HttpURLConnection) url1.openConnection();
	   			int responseCode = con.getResponseCode();
//	   			System.out.println("responseCode: "+responseCode);

	   			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	   			String inputLine;
	   			 response1 = new StringBuffer();

	   			while ((inputLine = in.readLine()) != null) {
	   				response1.append(inputLine);
	   			}
	   			in.close();

	   		} catch (Exception e) {
//	   			out.println(e.getMessage());
	   			return "false";
	   		}
	   		
	   		return response1.toString();
	   		
	   	}
	  
//	  public static String excelStanbolPythonScript(String fileName){
	  public static String excelStanbolPythonScript(String fileName){
	   		
	   		StringBuffer response1=null;
	   		
	   		try {

	   			String url = "";
	   			url="http://dev.bizlem.io:5004/?file="+fileName;
//	   		    HttpURLConnection.setFollowRedirects(false);
	   			
	   			url = url.replace(" ", "%20");
	   			
	   			URL url1 = new URL(url);
	   			HttpURLConnection con = (HttpURLConnection) url1.openConnection();
//	   		     con.setRequestMethod("HEAD");
//	   		     con.setConnectTimeout(480000); // minute to  milliseconds
	   			   con.setConnectTimeout(900000);
	   			   con.setReadTimeout(900000);
	   			  int responseCode = con.getResponseCode();
	   		     
	   		     /*boolean responseOk=false;
	   		     
	   		     responseOk= (con.getResponseCode() == HttpURLConnection.HTTP_OK);*/
//	   			System.out.println("responseCode: "+responseCode);

	   			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	   			String inputLine;
	   			 response1 = new StringBuffer();

	   			while ((inputLine = in.readLine()) != null) {
	   				response1.append(inputLine);
	   			}
	   			in.close();

	   		} catch (Exception e) {
	   			return "false";
	   			//System.out.println(e.getMessage());
	   		}
	   		
	   		return response1.toString();
	   		
	   	}
	
	  
	  public static String processPdftoSvg(String filename) {
		  String outpathpath = ""; 
		  try {
		   String url="http://dev.bizlem.io:8181/pdf-path-to-coors-path";
		   URL object=new URL(url);

		   HttpURLConnection con = (HttpURLConnection) object.openConnection();
		   con.setDoOutput(true);
		   con.setDoInput(true);
		   con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		   con.setRequestProperty("Accept", "application/json");
		   con.setRequestMethod("POST");

		   JSONObject parent = new JSONObject();
		   String inputFileName = filename;
		   inputFileName = inputFileName.substring(inputFileName.lastIndexOf("/")+1, inputFileName.lastIndexOf("."));
		 //  System.out.println(inputFileName);
		   parent.put("input",filename);
		   parent.put("output", "/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/svgoutput/"+inputFileName+".json");
		   outpathpath = "/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/svgoutput/"+inputFileName+".json";
		   OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
		   wr.write(parent.toString());
		   wr.flush();

		   //display what returns the POST request

		   StringBuilder sb = new StringBuilder();  
		   int HttpResult = con.getResponseCode(); 
		   if (HttpResult == HttpURLConnection.HTTP_OK) {
		       BufferedReader br = new BufferedReader(
		               new InputStreamReader(con.getInputStream(), "utf-8"));
		       String line = null;  
		       while ((line = br.readLine()) != null) {  
		           sb.append(line + "\n");  
		       }
		       br.close();
		       //System.out.println("" + sb.toString());  
		   } else {
		       //System.out.println(con.getResponseMessage());  
		   }
		   }catch (Exception e) {
		  // TODO: handle exception
		 }
		   return outpathpath;
		  }
	  
	  public static String pdfStanbolPythonScriptNewRoundRobin(String fileName){
	   		
	   		StringBuffer response1=null;
	   		
	   		try {

	   			String url = "";
	   			url="http://dev.bizlem.io:5010/?file="+fileName;
	   			
	   			//url="http://dev.bizlem.io:5006/?file="+fileName+ "&active=" + active;
	   			
	   			url = url.replace(" ", "%20");
	   			
	   			URL url1 = new URL(url);
	   			HttpURLConnection con = (HttpURLConnection) url1.openConnection();
	   			/*con.setConnectTimeout(900000);
	   			con.setReadTimeout(900000);*/ // minute to milliseconds
	   			int responseCode = con.getResponseCode();
//	   			System.out.println("responseCode: "+responseCode);

	   			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	   			String inputLine;
	   			 response1 = new StringBuffer();

	   			while ((inputLine = in.readLine()) != null) {
	   				response1.append(inputLine);
	   			}
	   			in.close();

	   		} catch (Exception e) {
	   			//System.out.println(e.getMessage());
//	   			out.println(e.getMessage());
	   			return "false";
	   			
	   		}
	   		
	   		return response1.toString();
	   		
	   	}
	  
	
	public static void ReadGmailDataToPassPythonApi(Session session, PrintWriter out){
		
		try {
			
			SaveReportDataClass SDC=new SaveReportDataClass();
			Node rootNode=session.getRootNode();
			//out.println("rootNode: "+rootNode.getName());
			if(rootNode.hasNode("GmailData1")){
				Node GmailData=rootNode.getNode("GmailData1");
				//out.println("GmailData: "+GmailData.getName());
				if(GmailData.hasNodes()){
					NodeIterator gmailDataNodeItr=GmailData.getNodes();
					while(gmailDataNodeItr.hasNext()){
						Node subjectNode=gmailDataNodeItr.nextNode();
						//out.println("subjectNode: "+subjectNode.getName());
						
						if(subjectNode.hasNodes()){
							NodeIterator subjectNodeItr=subjectNode.getNodes();
							int i1=0;
							while(subjectNodeItr.hasNext()){
								  Node textNode=subjectNodeItr.nextNode();
								  i1++;
								 // out.println("textNode: "+textNode.getName());
								  String textSentMailTime="";
								  String textXmlFileLink="";
								  String textFlag="";
								  String tomcat_file_link="";
								  
								  if(textNode.hasProperty("SentMailTime")){
									 textSentMailTime=textNode.getProperty("SentMailTime").getString();
								  }// timeStamp purpose
								  if(textNode.hasProperty("Flag")){
										 textFlag=textNode.getProperty("Flag").getString();
								  }// once saved again will not read this mail
								  if(textNode.hasProperty("xml_file_link")){
										 textXmlFileLink=textNode.getProperty("xml_file_link").getString();
								  }// emailUrl purpose
								  
								  String from_Source="";
								  if(textNode.hasProperty("from_Source")){
									  from_Source=textNode.getProperty("from_Source").getString();
									  }// timeStamp purpose
								  
								  String timestampDate="";
								  if(textNode.hasProperty("timestampDate")){
									  timestampDate=textNode.getProperty("timestampDate").getString();
									  }// timeStamp purpose
								  String timestampDateAndTime="";
								  if(textNode.hasProperty("timestampDateAndTime")){
									  timestampDateAndTime=textNode.getProperty("timestampDateAndTime").getString();
									  }// timeStamp purpose
								  
								  //tomcat_file_link
								  if(textNode.hasProperty("tomcat_file_link")){
									  tomcat_file_link=textNode.getProperty("tomcat_file_link").getString();
									  }
								  
								  
								  long htmlSize=0;
								  if( !(textFlag.equals("1")) ){
								  if(textNode.hasNodes()){
									  htmlSize= textNode.getNodes().getSize();
//									 if(htmlSize==1){
//									 if(htmlSize>1){
									 
								  if(textNode.hasProperty("tomcat_file_path")){
									  String textTomcatFilePath=textNode.getProperty("tomcat_file_path").getString();
									  
									  Date date1=new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(textSentMailTime);
									  boolean schedulerReadMail= FifteenMinuteClass.fifteenDaysBeforeDataRead(date1);
									  Calendar cal = Calendar.getInstance();
										cal.setTime(date1);
										String receivedDate = cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
												+ cal.get(Calendar.YEAR);
									
									
//								  if(receivedOnlyDate.equals("7-3-2019")){
									  if (schedulerReadMail == true) {
//										  out.print("kalka_html: "+receivedOnlyDate);
									  if(textTomcatFilePath.contains(".html")){
									    // out.println("textTomcatFilePath: "+textTomcatFilePath);
									     String emailUrl="";
										  if(textNode.hasProperty("file_link")){
											  emailUrl=textNode.getProperty("file_link").getString();
										  }
									  String subjectNodePath= subjectNode.getPath();
									  subjectNodePath = (GmailMethods.isNullString(subjectNodePath)) ? "" : subjectNodePath;
									  out.println("subjectNodePath: "+subjectNodePath);
									  
									    String ExpertScriptCallHere=ExpertScriptCall.postExpertScript(textTomcatFilePath, out);
									    String filepathfromourside="/home/ubuntu/TestedMailJSon/"+textNode.getName().toString()+"_"+timestampDate+".txt";
										  boolean checkjsonString=SaveReportDataClass.isJSONValid(ExpertScriptCallHere);
										    if(checkjsonString==true){
										    	out.println("ExpertCodeOutputHtml: "+ExpertScriptCallHere);
										        out.println("textTomcatFilePath: "+textTomcatFilePath);
										        out.println("textSentMailTime_html: "+textSentMailTime);
										       // String MycodeHere= AbhishekScript.excelHtmlScript(ExpertScriptCallHere, out);
										       String pallaviCodeHere= pallavi__json3_execution.UpdatedScriptForKnownAndUnknown(out, session, ExpertScriptCallHere, emailUrl, textSentMailTime, subjectNodePath, from_Source, timestampDate, timestampDateAndTime, filepathfromourside);
										        
										        boolean checkjsonStringAbhishek=SaveReportDataClass.isJSONValid(pallaviCodeHere);
										        if(checkjsonStringAbhishek){
										        	out.println("pallaviCodeHereHtml: "+pallaviCodeHere);
											        SDC.parseAllReportFromJsonToSave(out, session, pallaviCodeHere, emailUrl, textSentMailTime,subjectNodePath, from_Source, timestampDate, timestampDateAndTime, "html", ExpertScriptCallHere);
											          textNode.setProperty("Flag", "1");
													  ExecuteMailCount.executeMailCount(out, session, receivedDate, subjectNode.getName(), timestampDateAndTime, subjectNode.getPath());
										        }
										    }
									  
									 
									 // relevalent data
									  
									  //session.save();
										    
									  }
									}
									//set flag also in parseall method inside
//								  }// date check equals 
								  }
									 
//								  } 
								  }// check without attachment read
								} // textFlag check here
//								  else if(htmlSize>1){
									  
								 
								  if(textNode.hasNodes()){
									 NodeIterator textNodeItr=textNode.getNodes();
									 
									 while(textNodeItr.hasNext()){
										 Node textInsideAttachmentFolderNode=textNodeItr.nextNode();
//										 out.println("textInsideAttachmentFolderNode: "+textInsideAttachmentFolderNode.getName());
										 if(textInsideAttachmentFolderNode.hasNodes()){
											 NodeIterator textInsideAttachmentFolderNodeItr=textInsideAttachmentFolderNode.getNodes();
											 int i=0;
											 while(textInsideAttachmentFolderNodeItr.hasNext()){
												   Node attachmentNode=textInsideAttachmentFolderNodeItr.nextNode();
												   i++;
//												   out.println("attachmentNode: "+attachmentNode.getName());
												   String attachmentNodeFlag="";
												   if(attachmentNode.hasProperty("Flag")){
													   attachmentNodeFlag=attachmentNode.getProperty("Flag").getString();
													  // out.println("Flag :: "+attachmentNodeFlag);
												   }
												 
												  
												 if(!attachmentNodeFlag.equals("1")){
//												   if(attachmentNodeFlag.equals("1")){
//													 out.println("attachmentNode: "+attachmentNode);
													 if(attachmentNode.hasProperty("tomcat_file_path")){
														  String attachmentTomcatFilePath=attachmentNode.getProperty("tomcat_file_path").getString();
														 /* out.println("attachmentTomcatFilePath: "+attachmentTomcatFilePath);*/
														 
														 // if(attachmentTomcatFilePath.equals("/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/TcReports_1_TCReport.pdf")){
															  
//															  }else{
														
														  
														  if(!GmailMethods.isNullString(attachmentTomcatFilePath)){
														  if(attachmentTomcatFilePath.contains(".pdf")){
															 // out.println("attachmentTomcatFilePath_pdf: "+attachmentTomcatFilePath);
															  Date date1=new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(textSentMailTime);
															  boolean schedulerReadMail= FifteenMinuteClass.fifteenDaysBeforeDataRead(date1);
															   /* Calendar cal = Calendar.getInstance();
																cal.setTime(date1);
																//String receivedOnlyDate = String.valueOf(cal.get(Calendar.DATE));
																String receivedOnlyDate = cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
																			+ cal.get(Calendar.YEAR);
																
																
															  if(receivedOnlyDate.equals("7-3-2019")){*/
															 if (schedulerReadMail == true) {
																  
//																  out.print("kalka_pdf: "+receivedOnlyDate);
																 
																// if(attachmentTomcatFilePath.equals("/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/TC_MARKET_REPORT_END_2018WEEK_41_1_weeklytc_report_1.pdf")){
																 // if(attachmentTomcatFilePath.equals("/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/Braemar_ACM_-_Eastern_CPP_LR_Report_-_04.03.19_1_BraemarACM_Clean_East_of_Suez_LR_Report_04.03.19.pdf")){
																	 // out.print("inside match");
																	  String emailUrl="";
															  if(attachmentNode.hasProperty("tomcat_file_link")){
																  emailUrl=attachmentNode.getProperty("tomcat_file_link").getString();
																  emailUrl = (GmailMethods.isNullString(emailUrl)) ? "" : emailUrl;
																  
															  }
															  
															  String subjectNodePath= subjectNode.getPath();
															  subjectNodePath = (GmailMethods.isNullString(subjectNodePath)) ? "" : subjectNodePath;
															  out.println("subjectNodePath: "+subjectNodePath);
															  String svgUrl = processPdftoSvg(attachmentTomcatFilePath);
															  String filepathfromourside="/home/ubuntu/TestedMailJSon/"+attachmentNode.getName().toString()+"_"+timestampDate+".txt";
															  if(!GmailMethods.isNullString(svgUrl)){
															       String vinayaScriptCall=VinayaScript.vinayaScript(svgUrl);
															      if( !( vinayaScriptCall.equals("false") )  ){
															      if( !GmailMethods.isNullString(vinayaScriptCall) ){
															           boolean checkjsonString=SaveReportDataClass.isJSONValid(vinayaScriptCall);
																     if(checkjsonString==true){
														          	   out.println("attachmentTomcatFilePath_pdf: "+attachmentTomcatFilePath);
														          	   out.println("vinayaScriptCall_Output_svgtopdf: "+vinayaScriptCall);
														          	   String pallaviCodeHere= pallavi__json3_execution.UpdatedScriptForKnownAndUnknown(out, session, vinayaScriptCall, emailUrl, textSentMailTime, subjectNodePath, from_Source, timestampDate, timestampDateAndTime, filepathfromourside);
																    	 boolean checkjsonStringfinalobj=SaveReportDataClass.isJSONValid(pallaviCodeHere);
																		    if(checkjsonStringfinalobj==true){
																		    	out.println("pallaviCodeHere_pdf: "+pallaviCodeHere);
																		    
																		    	SDC.parseAllReportFromJsonToSave(out, session, pallaviCodeHere, emailUrl, textSentMailTime, subjectNodePath, from_Source, timestampDate, timestampDateAndTime, "pdf", vinayaScriptCall);
																		    }
														                   attachmentNode.setProperty("Flag", "1");
																   //  session.save();
																     
																} // null check pdfScript
															}
															  } // svg check close
															  } //........  															  } // check 15  days wala
														  }// check if	
														  													  // false
															  //set flag also in parseall method inside
//														  }// equals
														  }// pdf close
														 
														  
//														 if( emailUrl.contains(".xls") || emailUrl.contains(".xlsx") ){
														  if( (attachmentTomcatFilePath.contains(".xls")) || (attachmentTomcatFilePath.contains(".xlsx")) || (attachmentTomcatFilePath.contains(".XLS")) || (attachmentTomcatFilePath.contains(".XLSX")) ){
//															  out.println("attachmentTomcatFilePath_excel: "+attachmentTomcatFilePath);
															  
															//  if(attachmentTomcatFilePath.equals("/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/BRAEMAR_ACM_-_CLEAN_LR1_LIST_BSS_YOSU_-_16_JANUARY_2019_1_BRAEMARACM_CLEAN_LR1_LIST_BSS_YOSU_16_JANUARY_2019.xlsx")){
															  Date date1=new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(textSentMailTime);
															  boolean schedulerReadMail= FifteenMinuteClass.fifteenDaysBeforeDataRead(date1);
															 /* Calendar cal = Calendar.getInstance();
																cal.setTime(date1);
																String receivedOnlyDate = cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
																		+ cal.get(Calendar.YEAR);
															
															
														  if(receivedOnlyDate.equals("7-3-2019")){*/
															  if (schedulerReadMail == true) {
																  //}else{
//																  out.print("kalka_excel: "+receivedOnlyDate);
															  String emailUrl="";
															  if(attachmentNode.hasProperty("tomcat_file_link")){
																  emailUrl=attachmentNode.getProperty("tomcat_file_link").getString();
																  emailUrl = (GmailMethods.isNullString(emailUrl)) ? "" : emailUrl;
																  
															  }
															  String subjectNodePath= subjectNode.getPath();
															  subjectNodePath = (GmailMethods.isNullString(subjectNodePath)) ? "" : subjectNodePath;
															 // out.println("subjectNodePath: "+subjectNodePath);
															  
															        String ExpertScriptCallHere=ExpertScriptCall.postExpertScript(attachmentTomcatFilePath, out);
															        String filepathfromourside="/home/ubuntu/TestedMailJSon/"+attachmentNode.getName().toString()+"_"+timestampDate+".txt";
															       if( !( ExpertScriptCallHere.equals("false") )  ){
															       
															       boolean checkjsonString=SaveReportDataClass.isJSONValid(ExpertScriptCallHere);
																	if(checkjsonString==true){
																	 out.println("attachmentTomcatFilePath_excel: "+attachmentTomcatFilePath);
															         out.println("ExpertScriptexceloutput: "+ExpertScriptCallHere);
															         //String MycodeHere= AbhishekScript.excelHtmlScript(ExpertScriptCallHere, out);
															         String pallaviCodeHere= pallavi__json3_execution.UpdatedScriptForKnownAndUnknown(out, session, ExpertScriptCallHere, emailUrl, textSentMailTime, subjectNodePath, from_Source, timestampDate, timestampDateAndTime, filepathfromourside);
															         boolean checkjsonStringAbhishek=SaveReportDataClass.isJSONValid(pallaviCodeHere);
																        if(checkjsonStringAbhishek){
																        	out.println("pallaviCodeHere_excel: "+pallaviCodeHere);
															                SDC.parseAllReportFromJsonToSave(out, session, pallaviCodeHere, emailUrl, textSentMailTime,subjectNodePath, from_Source, timestampDate, timestampDateAndTime, "excel", ExpertScriptCallHere);
																        }
															             attachmentNode.setProperty("Flag", "1");
																	//  session.save();
																	}
																}
																
															 // } // chek 														  // set flag also in parseall method inside
															  }
														  }// excel close
														  
//														 } 
														  }
													  } // attachmentTomcatFilePath close
													 
												 } // attachmentNodeFlag close
												  /*if(i==20){
													  break;
												  }*/
												   
											 }// textInsideAttachmentFolderNodeItr while close
										 } // textInsideAttachmentFolderNodeItr hashnodes check
										 
									 }// textNodeItr while close
									 
								  } // textNode hasnode check
//							}// html not has 
								  
								
							}// subjectNodeItr while close
						} // subject node hasnodes
						
						
						
					}//gmailDataNodeItr while close
					
				} //hasnodes gmaildata
			}// root node 
			
		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}
	
	public static String downloadEmail(String link) throws IOException {
		URL url = new URL(link);
		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		http.setRequestMethod("GET");
		http.setUseCaches(false);
		http.setDoOutput(true);

		int responseCode = http.getResponseCode();
		StringBuffer buffer = new StringBuffer();
		if (responseCode == 200) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				buffer.append(inputLine);
			}
			in.close();
		} else {
//			System.out.println("POST request not worked");
		}
		return buffer.toString();
	}

	public static JSONArray ReadGmailDataToShowOnUi(Session session, PrintWriter out){
		JSONArray dataarray=null;
		try {
			
			Node rootNode=session.getRootNode();
			
			if(rootNode.hasNode("GmailData")){
				dataarray=new JSONArray();
				JSONArray innerarray=null;
				
				Node GmailData=rootNode.getNode("GmailData");
				if(GmailData.hasNodes()){
					NodeIterator gmailDataNodeItr=GmailData.getNodes();
					while(gmailDataNodeItr.hasNext()){
						Node subjectNode=gmailDataNodeItr.nextNode();
						 innerarray=new JSONArray();
//						out.println("subjectNode: "+subjectNode.getName());
						if(subjectNode.hasNodes()){
							NodeIterator subjectNodeItr=subjectNode.getNodes();
							
							while(subjectNodeItr.hasNext()){
								  Node textNode=subjectNodeItr.nextNode();
//								  out.println("textNode: "+textNode.getName());
								  String textXmlFileLink="";
								  JSONObject data=new JSONObject();
								  
								  if(textNode.hasProperty("xml_file_link")){
										 textXmlFileLink=textNode.getProperty("xml_file_link").getString();
										 
										 String emailContent=downloadEmail(textXmlFileLink);
										    String from=StringUtils.substringBetween(emailContent, "<field name=\"from\">", "</field>");
											String to=StringUtils.substringBetween(emailContent, "<field name=\"to\">", "</field>");
											String cc=StringUtils.substringBetween(emailContent, "<field name=\"cc\">", "</field>");
											String subject=StringUtils.substringBetween(emailContent, "<field name=\"subject\">", "</field>");
											String datetime=StringUtils.substringBetween(emailContent, "<field name=\"datetime\">", "</field>");
											String body=StringUtils.substringBetween(emailContent, "<field name=\"body\">", "</field>");
											
											data.put("xml_file_link", textXmlFileLink);
											data.put("Foldertxt", textNode.getName());
											/*data.put("from", from);
											data.put("to", to);
											data.put("cc", cc);
											data.put("subject",subject);
											data.put("datetime", datetime);
											data.put("body", body);*/
										 
								  }// emailUrl purpose
								  
								  
								  
								  if(textNode.hasNodes()){
									 NodeIterator textNodeItr=textNode.getNodes();
									 
									 while(textNodeItr.hasNext()){
										 Node textInsideAttachmentFolderNode=textNodeItr.nextNode();
//										 out.println("textInsideAttachmentFolderNode: "+textInsideAttachmentFolderNode.getName());
										 if(textInsideAttachmentFolderNode.hasNodes()){
											 NodeIterator textInsideAttachmentFolderNodeItr=textInsideAttachmentFolderNode.getNodes();
											 
											 while(textInsideAttachmentFolderNodeItr.hasNext()){
												   Node attachmentNode=textInsideAttachmentFolderNodeItr.nextNode();
//												   out.println("attachmentNode: "+attachmentNode.getName());

													 if(attachmentNode.hasProperty("tomcat_file_link")){
														  String attachmentTomcatFilePath=attachmentNode.getProperty("tomcat_file_link").getString();
//														  out.println("attachmentTomcatFilePath: "+attachmentTomcatFilePath);
														 
														  data.put("tomcatFileLink", attachmentTomcatFilePath);
														  data.put("foldername", textInsideAttachmentFolderNode.getName());
														  
													  } // attachmentTomcatFilePath close

												   
												   
											 }// textInsideAttachmentFolderNodeItr while close
											 
											/* innerarray.put(data);*/
											 
										 } // textInsideAttachmentFolderNodeItr hashnodes check
										 
										/* innerarray.put(data);*/ // doubt 
										 
									 }// textNodeItr while close
									 
									/* innerarray.put(data);*/
									 
								  } // textNode hasnode check
								  
								  innerarray.put(data);
							}// subjectNodeItr while close
						} // subject node hasnodes
						
						  JSONObject  data1=new JSONObject();
						  data1.put("email", innerarray);
						  dataarray.put(data1);
						
					}//gmailDataNodeItr while close
					
				} //hasnodes gmaildata
			}// root node 
			
		} catch (Exception e) {
			out.println(e.getMessage());
		}
		return dataarray;
	}
	
public static void setFlagZero(Session session, PrintWriter out){
		
		try {
			
			SaveReportDataClass SDC=new SaveReportDataClass();
			Node rootNode=session.getRootNode();
			//out.println(rootNode);
			if(rootNode.hasNode("GmailData1")){
				Node GmailData=rootNode.getNode("GmailData1");
				if(GmailData.hasNodes()){
					NodeIterator gmailDataNodeItr=GmailData.getNodes();
					while(gmailDataNodeItr.hasNext()){
						Node subjectNode=gmailDataNodeItr.nextNode();
						//out.println("subjectNode: "+subjectNode.getName());
						if(subjectNode.hasNodes()){
							NodeIterator subjectNodeItr=subjectNode.getNodes();
							
							while(subjectNodeItr.hasNext()){
								  Node textNode=subjectNodeItr.nextNode();
								 // out.println("textNode: "+textNode.getName());
								  String textSentMailTime="";
								  String textXmlFileLink="";
								  String textFlag="";
								  
								  String from_Source="";
								  if(textNode.hasProperty("from_Source")){
									  from_Source=textNode.getProperty("from_Source").getString();
									  }// timeStamp purpose
								  
								  if(textNode.hasProperty("SentMailTime")){
									 textSentMailTime=textNode.getProperty("SentMailTime").getString();
								  }// timeStamp purpose
								  if(textNode.hasProperty("Flag")){
										 textFlag=textNode.getProperty("Flag").getString();
								  }// once saved again will not read this mail
								  if(textNode.hasProperty("xml_file_link")){
										 textXmlFileLink=textNode.getProperty("xml_file_link").getString();
								  }// emailUrl purpose
								  
								  long htmlSize= textNode.getNodes().getSize();
								  if(htmlSize>1){
								  
								  if(textFlag.equals("1")){
								  String emailUrl="";
									  if(textNode.hasProperty("file_link")){
										  emailUrl=textNode.getProperty("file_link").getString();
									  }
									  
								  if(textNode.hasProperty("tomcat_file_path")){
									  String textTomcatFilePath=textNode.getProperty("tomcat_file_path").getString();
									  if(textTomcatFilePath.contains(".html")){
									    // out.println("textTomcatFilePath: "+textTomcatFilePath);
									  
									 /* textNode.setProperty("Flag", "0");
									  session.save();*/
										  if( !GmailMethods.isNullString(textSentMailTime) ){
						           Date date1=new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(textSentMailTime);
                                   boolean schedulerReadMail= FifteenMinuteClass.fifteenDaysBeforeDataRead1(date1);
									  
									  if (schedulerReadMail == true) {
										  
										  if(textNode.hasProperty("tomcat_file_link")){
											  emailUrl=textNode.getProperty("tomcat_file_link").getString();
											  emailUrl = (GmailMethods.isNullString(emailUrl)) ? "" : emailUrl;
											  
											  out.println("textSentMailTime:: "+textSentMailTime);
											  out.println("emailUrl:: "+emailUrl);
											  out.println("from_Source:: "+from_Source);
											  
										  }
										  
										  textNode.setProperty("Flag", "0");
										  session.save();
										  
										  /*out.println("emailUrl:: "+emailUrl);
										  out.println("from_Source:: "+from_Source);*/
										  
										  
									}
										  }
									//set flag also in parseall method inside
									  
								  }
								  
								} // textFlag check here
								  }
								  }
								  
								  if(textNode.hasNodes()){
									 NodeIterator textNodeItr=textNode.getNodes();
									 
									 while(textNodeItr.hasNext()){
										 Node textInsideAttachmentFolderNode=textNodeItr.nextNode();
//										 out.println("textInsideAttachmentFolderNode: "+textInsideAttachmentFolderNode.getName());
										 if(textInsideAttachmentFolderNode.hasNodes()){
											 NodeIterator textInsideAttachmentFolderNodeItr=textInsideAttachmentFolderNode.getNodes();
											 
											 while(textInsideAttachmentFolderNodeItr.hasNext()){
												   Node attachmentNode=textInsideAttachmentFolderNodeItr.nextNode();
//												   out.println("attachmentNode: "+attachmentNode.getName());
												   String attachmentNodeFlag="";
												   if(attachmentNode.hasProperty("Flag")){
													   attachmentNodeFlag=attachmentNode.getProperty("Flag").getString();
												   }
												 
												  
//												 if(attachmentNodeFlag.equals("1")){
//													 out.println("attachmentNode: "+attachmentNode);
													 if(attachmentNode.hasProperty("tomcat_file_path")){
														  String attachmentTomcatFilePath=attachmentNode.getProperty("tomcat_file_path").getString();
//														  out.println("attachmentTomcatFilePath: "+attachmentTomcatFilePath);
														  
														  Date date1=new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(textSentMailTime);
						                                   boolean schedulerReadMail= FifteenMinuteClass.fifteenDaysBeforeDataRead1(date1);
															  
															  if (schedulerReadMail == true) {
																
														  
														  if(attachmentTomcatFilePath.contains(".pdf")){
															//  out.println("attachmentTomcatFilePath_pdf: "+attachmentTomcatFilePath);
															  String emailUrl="";
												/*			  if(attachmentNode.hasProperty("tomcat_file_link")){
																  emailUrl=attachmentNode.getProperty("tomcat_file_link").getString();
																  emailUrl = (GmailMethods.isNullString(emailUrl)) ? "" : emailUrl;
																  
																 // out.println("textSentMailTime:: "+textSentMailTime);
																 // out.println("emailUrl:: "+emailUrl);
																  //out.println("from_Source:: "+from_Source);
																  

																  out.println("emailUrl:: "+emailUrl);
																  out.println("from_Source:: "+from_Source);
																  out.println("textSentMailTime:: "+textSentMailTime);
															  }*/
															  attachmentNode.setProperty("Flag", "0");
															//  session.save();
															  
														  }
															  
															  
//															  String pythonScriptApiData=pdfPythonScript(attachmentTomcatFilePath);
//															  SDC.parseAllReportFromJsonToSave(out, session, pythonScriptApiData);
															  
															  //set flag also in parseall method inside
															  /*attachmentNode.setProperty("Flag", "0");
															  session.save();*/
														  }// pdf close
														 
														  
//														 if( emailUrl.contains(".xls") || emailUrl.contains(".xlsx") ){
														  if( (attachmentTomcatFilePath.contains(".xls")) || (attachmentTomcatFilePath.contains(".xlsx")) || (attachmentTomcatFilePath.contains(".XLS")) || (attachmentTomcatFilePath.contains(".XLSX")) ){
															 // out.println("attachmentTomcatFilePath_excel: "+attachmentTomcatFilePath);
															  
															  Date date2=new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(textSentMailTime);
							                                   boolean schedulerReadMail1= FifteenMinuteClass.fifteenDaysBeforeDataRead1(date2);
																  
																  if (schedulerReadMail1 == true) {
																	  String emailUrl="";
															/*		  if(attachmentNode.hasProperty("tomcat_file_link")){
																		  emailUrl=attachmentNode.getProperty("tomcat_file_link").getString();
																		  emailUrl = (GmailMethods.isNullString(emailUrl)) ? "" : emailUrl;
																		  
																		 // out.println("textSentMailTime:: "+textSentMailTime);
																		 // out.println("emailUrl:: "+emailUrl);
																		  //out.println("from_Source:: "+from_Source);
																		  

																		  out.println("emailUrl:: "+emailUrl);
																		  out.println("from_Source:: "+from_Source);
																		  out.println("textSentMailTime:: "+textSentMailTime);
																	  }*/
																	  
																	  attachmentNode.setProperty("Flag", "0");
																	 // session.save();
																  }
															  
															 /* attachmentNode.setProperty("Flag", "0");
															  session.save();*/
															  // set flag also in parseall method inside
															  
														  }// excel close
														  
//														 } 
														  
													  } // attachmentTomcatFilePath close
													 
//												 } // attachmentNodeFlag close
												   
												   
											 }// textInsideAttachmentFolderNodeItr while close
										 } // textInsideAttachmentFolderNodeItr hashnodes check
										 
									 }// textNodeItr while close
									 
								  } // textNode hasnode check
								  
								
							}// subjectNodeItr while close
						} // subject node hasnodes
						
						
						
					}//gmailDataNodeItr while close
					
				} //hasnodes gmaildata
			}// root node 
			
		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}

public static JSONObject ReadGmailDataToShowOnUiNew(Session session, PrintWriter out){
//	JSONObject subjectJSonObject=null;
	JSONObject mainarray=null;
	try {
		
		Node rootNode=session.getRootNode();
		
		if(rootNode.hasNode("GmailData1")){
//			JSONArray finalallHtmlLinkAndAttachmentLinkArray=new JSONArray();
			 mainarray=new JSONObject();
			 
			Node GmailData=rootNode.getNode("GmailData1");
			if(GmailData.hasNodes()){
				NodeIterator gmailDataNodeItr=GmailData.getNodes();
				
				Node subjectNode=null;
				int i=0;
				//JSONArray allHtmlLinkAndAttachmentLinkArray=new JSONArray();
				while(gmailDataNodeItr.hasNext()){
					 subjectNode=gmailDataNodeItr.nextNode();
					 i++;
					//out.println("subjectNode: "+subjectNode.getName());
					 JSONArray allHtmlLinkAndAttachmentLinkArray=new JSONArray();
					 if(subjectNode.hasNodes()){
						NodeIterator subjectNodeItr=subjectNode.getNodes();
						
						while(subjectNodeItr.hasNext()){
							  Node textNode=subjectNodeItr.nextNode();
							 // out.println("textNode: "+textNode.getName());
							JSONObject subjectInsideFileLink=new JSONObject();
							  if(textNode.hasProperty("file_link")){
								  String htmlTomcatFilePath=textNode.getProperty("file_link").getString();
								  htmlTomcatFilePath=downloadEmail(htmlTomcatFilePath);
								  subjectInsideFileLink.put("htmlFileLink", htmlTomcatFilePath);
								  
							  }
							  
							 
							  
							  if(textNode.hasNodes()){
								 NodeIterator textNodeItr=textNode.getNodes();
								 StringBuffer attachmentTomcatFileLink= new StringBuffer();
								 StringBuffer attachmentTomcatAttachment_Name = new StringBuffer();
								 while(textNodeItr.hasNext()){
									 Node textInsideAttachmentFolderNode=textNodeItr.nextNode();
//									 out.println("textInsideAttachmentFolderNode: "+textInsideAttachmentFolderNode.getName());
									 if(textInsideAttachmentFolderNode.hasNodes()){
										 NodeIterator textInsideAttachmentFolderNodeItr=textInsideAttachmentFolderNode.getNodes();
										 
										 while(textInsideAttachmentFolderNodeItr.hasNext()){
											   Node attachmentNode=textInsideAttachmentFolderNodeItr.nextNode();
											  
//												 out.println("attachmentNode: "+attachmentNode);
												 if(attachmentNode.hasProperty("tomcat_file_link")){
													 attachmentTomcatFileLink.append(attachmentNode.getProperty("tomcat_file_link").getString()).append("~");
													 // attachmentTomcatFileLink=attachmentNode.getProperty("tomcat_file_link").getString()+":";
													 
//													  out.println("attachmentTomcatFilePath: "+attachmentTomcatFilePath);
													 // subjectInsideFileLink.put("attachmentFileLink", attachmentTomcatFileLink.toString());
 
													  }
												 
												 if(attachmentNode.hasProperty("Attachment_Name")){
													 attachmentTomcatAttachment_Name.append(attachmentNode.getProperty("Attachment_Name").getString()).append("~");
													 // String attachmentTomcatAttachment_Name=attachmentNode.getProperty("Attachment_Name").getString()+":";
//													  out.println("attachmentTomcatFilePath: "+attachmentTomcatFilePath);
													//  subjectInsideFileLink.put("attachmentFileName", attachmentTomcatAttachment_Name);

													  }
												 
												 
											   
											   
										 }
										 subjectInsideFileLink.put("attachmentFileLink", attachmentTomcatFileLink.toString());
										 subjectInsideFileLink.put("attachmentFileName", attachmentTomcatAttachment_Name.toString());
										 
										 // textInsideAttachmentFolderNodeItr while close
									 } // textInsideAttachmentFolderNodeItr hashnodes check

									   //allHtmlLinkAndAttachmentLinkArray.put(subjectInsideFileLink);
								 }// textNodeItr while close
								 
							  } // textNode hasnode check
							  allHtmlLinkAndAttachmentLinkArray.put(subjectInsideFileLink); 
						}// subjectNodeItr while close
						
						 
						
//						out.println(subjectJSonObject);
						
						
					} // subject node hasnodes
					
					 JSONObject subjectJSonObject=new JSONObject();
					 subjectJSonObject.put("active", "");
					 subjectJSonObject.put("array", allHtmlLinkAndAttachmentLinkArray);
					 mainarray.put(subjectNode.getName(), subjectJSonObject);
					 //mainarray.put(subjectJSonObject);
//					 finalallHtmlLinkAndAttachmentLinkArray.put(subjectJSonObject);
					 if(i==20){
							break;
						}
					
				}
				
				//gmailDataNodeItr while close
				
				/*JSONObject subjectJSonObject=new JSONObject();
				subjectJSonObject.put(subjectNode.getName(), allHtmlLinkAndAttachmentLinkArray);*/
				
			} //hasnodes gmaildata
			//out.println(mainarray);
		}// root node 
		
	} catch (Exception e) {
		out.println(e.getMessage());
	}
	return mainarray;
}

public static JSONObject ReadGmailDataChangeHttps(Session session, PrintWriter out){
//	JSONObject subjectJSonObject=null;
	JSONObject mainarray=null;
	try {
		
		Node rootNode=session.getRootNode();
		
		if(rootNode.hasNode("GmailData1")){
//			JSONArray finalallHtmlLinkAndAttachmentLinkArray=new JSONArray();
			 mainarray=new JSONObject();
			 
			Node GmailData=rootNode.getNode("GmailData1");
			if(GmailData.hasNodes()){
				NodeIterator gmailDataNodeItr=GmailData.getNodes();
				
				Node subjectNode=null;
				int i=0;
				//JSONArray allHtmlLinkAndAttachmentLinkArray=new JSONArray();
				while(gmailDataNodeItr.hasNext()){
					 subjectNode=gmailDataNodeItr.nextNode();
					 i++;
					//out.println("subjectNode: "+subjectNode.getName());
					 JSONArray allHtmlLinkAndAttachmentLinkArray=new JSONArray();
					 if(subjectNode.hasNodes()){
						NodeIterator subjectNodeItr=subjectNode.getNodes();
						
						while(subjectNodeItr.hasNext()){
							  Node textNode=subjectNodeItr.nextNode();
							 // out.println("textNode: "+textNode.getName());
							JSONObject subjectInsideFileLink=new JSONObject();
							  if(textNode.hasProperty("file_link")){
								  String htmlTomcatFilePath=textNode.getProperty("file_link").getString();
								  if(htmlTomcatFilePath.lastIndexOf("http:")!=-1){
									  htmlTomcatFilePath=htmlTomcatFilePath.substring(htmlTomcatFilePath.lastIndexOf("http:") + 5);
									  htmlTomcatFilePath="https:"+htmlTomcatFilePath;
										out.println(htmlTomcatFilePath);
										textNode.setProperty("file_link", htmlTomcatFilePath);
										}
								  
							  }if(textNode.hasProperty("file_path")){
								  String htmlTomcatFilePath=textNode.getProperty("file_path").getString();
								  if(htmlTomcatFilePath.lastIndexOf("http:")!=-1){
									  htmlTomcatFilePath=htmlTomcatFilePath.substring(htmlTomcatFilePath.lastIndexOf("http:") + 5);
									  htmlTomcatFilePath="https:"+htmlTomcatFilePath;
										out.println(htmlTomcatFilePath);
										textNode.setProperty("file_path", htmlTomcatFilePath);
										}
								  
							  }//
							  if(textNode.hasProperty("tomcat_file_link")){
								  String htmlTomcatFilePath=textNode.getProperty("tomcat_file_link").getString();
								  if(htmlTomcatFilePath.lastIndexOf("http:")!=-1){
									  htmlTomcatFilePath=htmlTomcatFilePath.substring(htmlTomcatFilePath.lastIndexOf("http:") + 5);
									  htmlTomcatFilePath="https:"+htmlTomcatFilePath;
										out.println(htmlTomcatFilePath);
										textNode.setProperty("tomcat_file_link", htmlTomcatFilePath);
										}
								  
							  }//tomcat_file_path
							  if(textNode.hasProperty("tomcat_file_path")){
								  String htmlTomcatFilePath=textNode.getProperty("tomcat_file_path").getString();
								  if(htmlTomcatFilePath.lastIndexOf("http:")!=-1){
									  htmlTomcatFilePath=htmlTomcatFilePath.substring(htmlTomcatFilePath.lastIndexOf("http:") + 5);
									  htmlTomcatFilePath="https:"+htmlTomcatFilePath;
										out.println(htmlTomcatFilePath);
										textNode.setProperty("tomcat_file_path", htmlTomcatFilePath);
										}
								  
							  }//
							  if(textNode.hasProperty("xml_file_link")){
								  String htmlTomcatFilePath=textNode.getProperty("xml_file_link").getString();
								  if(htmlTomcatFilePath.lastIndexOf("http:")!=-1){
									  htmlTomcatFilePath=htmlTomcatFilePath.substring(htmlTomcatFilePath.lastIndexOf("http:") + 5);
									  htmlTomcatFilePath="https:"+htmlTomcatFilePath;
										out.println(htmlTomcatFilePath);
										textNode.setProperty("xml_file_link", htmlTomcatFilePath);
										}
								  
							  }//
							  
							 
							  
							  if(textNode.hasNodes()){
								 NodeIterator textNodeItr=textNode.getNodes();
								 StringBuffer attachmentTomcatFileLink= new StringBuffer();
								 StringBuffer attachmentTomcatAttachment_Name = new StringBuffer();
								 while(textNodeItr.hasNext()){
									 Node textInsideAttachmentFolderNode=textNodeItr.nextNode();
//									 out.println("textInsideAttachmentFolderNode: "+textInsideAttachmentFolderNode.getName());
									 if(textInsideAttachmentFolderNode.hasNodes()){
										 NodeIterator textInsideAttachmentFolderNodeItr=textInsideAttachmentFolderNode.getNodes();
										 
										 while(textInsideAttachmentFolderNodeItr.hasNext()){
											   Node attachmentNode=textInsideAttachmentFolderNodeItr.nextNode();
											  
//												 out.println("attachmentNode: "+attachmentNode);
												 if(attachmentNode.hasProperty("tomcat_file_link")){
													String htmlTomcatFilePath=  attachmentNode.getProperty("tomcat_file_link").getString();
													if(htmlTomcatFilePath.lastIndexOf("http:")!=-1){
														  htmlTomcatFilePath=htmlTomcatFilePath.substring(htmlTomcatFilePath.lastIndexOf("http:") + 5);
														  htmlTomcatFilePath="https:"+htmlTomcatFilePath;
															out.println(htmlTomcatFilePath);
															attachmentNode.setProperty("tomcat_file_link", htmlTomcatFilePath);
															}
													 
//													 
													  }
												 if(attachmentNode.hasProperty("file_link")){
													String htmlTomcatFilePath= attachmentNode.getProperty("file_link").getString();
													if(htmlTomcatFilePath.lastIndexOf("http:")!=-1){
														  htmlTomcatFilePath=htmlTomcatFilePath.substring(htmlTomcatFilePath.lastIndexOf("http:") + 5);
														  htmlTomcatFilePath="https:"+htmlTomcatFilePath;
															out.println(htmlTomcatFilePath);
															attachmentNode.setProperty("file_link", htmlTomcatFilePath);
															}
													 
//													 
													  }
												 if(attachmentNode.hasProperty("file_path")){
													String htmlTomcatFilePath=attachmentNode.getProperty("file_path").getString();
													if(htmlTomcatFilePath.lastIndexOf("http:")!=-1){
														  htmlTomcatFilePath=htmlTomcatFilePath.substring(htmlTomcatFilePath.lastIndexOf("http:") + 5);
														  htmlTomcatFilePath="https:"+htmlTomcatFilePath;
															out.println(htmlTomcatFilePath);
															attachmentNode.setProperty("file_path", htmlTomcatFilePath);
															}
													 
//													 
													  }
												 
												 
												 
											   
											   
										 }
										 
										 
										 
										 // textInsideAttachmentFolderNodeItr while close
									 } // textInsideAttachmentFolderNodeItr hashnodes check

									   //allHtmlLinkAndAttachmentLinkArray.put(subjectInsideFileLink);
								 }// textNodeItr while close
								 
							  } // textNode hasnode check
							  session.save();
						}// subjectNodeItr while close
						
						 
						
//						out.println(subjectJSonObject);
						
						
					} // subject node hasnodes
					
					 
					
				}
				
				//gmailDataNodeItr while close
				
				/*JSONObject subjectJSonObject=new JSONObject();
				subjectJSonObject.put(subjectNode.getName(), allHtmlLinkAndAttachmentLinkArray);*/
				
			} //hasnodes gmaildata
			//out.println(mainarray);
		}// root node 
		
	} catch (Exception e) {
		out.println(e.getMessage());
	}
	return mainarray;
}

public static String htmlToPdfScript(String filePath, String htmlUrl){
		
//		StringBuffer response1=null;
		
		try {

			String url = "";
			url="http://dev.bizlem.io:5000/api/converter?url="+htmlUrl+"&filename="+filePath;
			
			url = url.replace(" ", "%20");
			
			URL url1 = new URL(url);
			HttpURLConnection con = (HttpURLConnection) url1.openConnection();
			int responseCode = con.getResponseCode();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response1 = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response1.append(inputLine);
			}
			in.close();

		} catch (Exception e) {
			
		}
		
		return filePath;
		
	}

public static String processHtmlPdftoSvg(String filename) {
	  String outpathpath = ""; 
	  try {
	   String url="http://dev.bizlem.io:8181/pdf-path-to-coors-path";
	   URL object=new URL(url);

	   HttpURLConnection con = (HttpURLConnection) object.openConnection();
	   con.setDoOutput(true);
	   con.setDoInput(true);
	   con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	   con.setRequestProperty("Accept", "application/json");
	   con.setRequestMethod("POST");

	   JSONObject parent = new JSONObject();
	   String inputFileName = filename;
	   inputFileName = inputFileName.substring(inputFileName.lastIndexOf("/")+1, inputFileName.lastIndexOf("."));
	 //  System.out.println(inputFileName);
	   parent.put("input",filename);
	  // parent.put("output", "/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/svgoutput/"+inputFileName+".json");
	   parent.put("output", "/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/htmlpdftosvg/"+inputFileName+".json");
	   outpathpath = "/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/htmlpdftosvg/"+inputFileName+".json";
	   OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
	   wr.write(parent.toString());
	   wr.flush();

	   //display what returns the POST request

	   StringBuilder sb = new StringBuilder();  
	   int HttpResult = con.getResponseCode(); 
	   if (HttpResult == HttpURLConnection.HTTP_OK) {
	       BufferedReader br = new BufferedReader(
	               new InputStreamReader(con.getInputStream(), "utf-8"));
	       String line = null;  
	       while ((line = br.readLine()) != null) {  
	           sb.append(line + "\n");  
	       }
	       br.close();
	       //System.out.println("" + sb.toString());  
	   } else {
	       //System.out.println(con.getResponseMessage());  
	   }
	   }catch (Exception e) {
	  // TODO: handle exception
	 }
	   return outpathpath;
	  }

	
}
