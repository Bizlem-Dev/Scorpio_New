package com.reportinformationsystem;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.Value;
import javax.jcr.Workspace;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;
import javax.jcr.query.RowIterator;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.abhishek.ConvertDate;
import com.readGmail.GmailMethods;
import com.readGmail.SlingMethods;
import com.subjectheaders.CombinePortAndDateSeparateLogic;
import com.ui.data.CreateExcelMethods;

import ChangedStructureCurrent.ChangedStructureCurrent_Methods;
import ChangedStructureCurrent.FetchData;

public class ChangedApiWithoutMultipleArray {

	public static void main(String[] args) {

		String data = "Clarksons Platou Fuel Oil Rate Grid 31st October 2018 *correction bsea handy*";
		// if(data.contains("*")){
		data = data.replaceAll("\\*", "").replace(" ", "_");
		System.out.println(data);
		// }
	}

	public static JSONObject fetchTonnageDataChanged(PrintWriter out, Session session,String countRange) {

		JSONObject mainJsonobj = null;
		try {
			FetchData fd = new FetchData();
			ConvertDate CD=new ConvertDate();
			
			Node scorpio = session.getRootNode().getNode("scorpioDataBase");
			Node ReportData = scorpio.getNode("ReportData");
			Node Tonnage = ReportData.getNode("Tonnage");
			
			if (Tonnage.hasNodes()) {
//				long size = Tonnage.getNodes().getSize()+1;
				/*long size = Tonnage.getNodes().getSize()+1;*/ //my code
				long size=0;
				if(Tonnage.hasProperty("jcr:count")){
					   String count=Tonnage.getProperty("jcr:count").getString();
					    size= Long.valueOf(count);
					}
				
//				long countRangeI = Long.parseLong(countRange);
				long countRangeI=Long.valueOf(countRange);
				JSONObject keyNameObject = null;

				JSONArray keyNameObjectJsonarray = new JSONArray();

				NodeIterator itr = Tonnage.getNodes();
				//String netChangedPointer = "";
				//String limit = "";
				boolean nodeFlag = false;
				/*if (Tonnage.hasProperty("netChangedPointer")) {
					netChangedPointer = Tonnage.getProperty("netChangedPointer").getString();
					limit = String.valueOf(Integer.parseInt(netChangedPointer) + 1);
				}*/
				int i = 0;
				long from= size - countRangeI;
				long size1 = from + 10;
				//while (Tonnage.hasNode(limit)) {
				for(long count=from;count <size1;count++){
					nodeFlag = true;
					//Node nextnode = Tonnage.getNode(limit);
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
//					Object  ReportTimestamp = null;
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


//					if (GmailMethods.isNullString(RepositionRegion)) {

						if (nextnode.hasProperty("RepositionRegion")) {
							String RepositionRegion12 = nextnode.getProperty("RepositionRegion").getString();

							String RepositionRegion1 = fd.fetch_Port(session, RepositionRegion12);
							boolean checkjsonString = SaveReportDataClass.isJSONValid(RepositionRegion1);
							if (checkjsonString == true) {
								JSONObject data = new JSONObject(RepositionRegion1);
								
								/*if (data.has("Area")) {
									RepositionRegion = data.getString("Area");
									// check_Port_repositionRegion_id=check_OpenPort_id;
								}*/
								
								if (data.has("portName")) {
									RepositionRegion = data.getString("portName");
									// check_Port_repositionRegion_id=check_OpenPort_id;
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
						//ETABasis=CD.convertDateFormat(ETABasis1);
						/* if( ETABasis.contains("PPT") || ETABasis.contains("PPT - exten") ){
								
						     LocalDateTime localDateTime = LocalDateTime.parse(ReportTimestamp1);
						     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
						     ETABasis = localDateTime.format(formatter);
						//out.println("ETABasis_ppt: "+ETABasis);
						
					}*/
						// nextnode.setProperty("ETABasis", ETABasis);
						// session.save();
						 
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
							/*if (data.has("Area")) {
								RepositionRegion = data.getString("Area");
								// check_Port_repositionRegion_id=check_OpenPort_id;
							}else {
								RepositionRegion="";
							}*/

						}
						
						if(GmailMethods.isNullString(OpenPort) ){
							OpenPort=OpenPort12;
							
							/*String portJson=CombinePortAndDateSeparateLogic.portIdentifiedJoint(OpenPort12);
							boolean checkPortandDate = SaveReportDataClass.isJSONValid(portJson);
							if (checkPortandDate == true) {
								JSONObject portdate = new JSONObject(portJson);
								if(portdate.has("port")){
									OpenPort=portdate.getString("port");
								}
								if(portdate.has("date")){
									date=portdate.getString("date");
								}
							}// true
*/						} // check null

					}
					
					
					String OpenDate1="";
					if (nextnode.hasProperty("OpenDate")) {
						OpenDate= nextnode.getProperty("OpenDate").getString();
						//OpenDate=CD.convertDateFormat(OpenDate1);
						
					/*	if( OpenDate1.contains("PPT") || OpenDate1.contains("PPT - exten") ){
							
						     LocalDateTime localDateTime = LocalDateTime.parse(ReportTimestamp1);
						     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
						     OpenDate = localDateTime.format(formatter);
						//out.println("ETABasis_ppt: "+ETABasis);
						
					}*/
						//nextnode.setProperty("OpenDate", OpenDate);
						//session.save();
						/*if(GmailMethods.isNullString(date)){
							String dateJson=CombinePortAndDateSeparateLogic.portIdentifiedJoint(OpenDate1);
							boolean checkPortandDate = SaveReportDataClass.isJSONValid(dateJson);
							if (checkPortandDate == true) {
								JSONObject dateport = new JSONObject(dateJson);
								if(dateport.has("port")){
									String OpenPortDAte=dateport.getString("port");
									if(!GmailMethods.isNullString(OpenPortDAte)){
										OpenPort=OpenPortDAte;
									}
								}
								if(dateport.has("date")){
									date=dateport.getString("date");
									if(!GmailMethods.isNullString(date)){
										OpenDate=date;
									}
								}
							}// true
						}else{
							OpenDate=date;
						}*/

					}
					if (nextnode.hasProperty("ReportType")) {
						ReportType = nextnode.getProperty("ReportType").getString();
						ReportType = fd.fetch_ReportType(session, ReportType);

					}
					
					
					/*if (nextnode.hasProperty("textSentMailTime")) {
					    ReportTimestamp = nextnode.getProperty("textSentMailTime").getString();
					    
					    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
						ReportTimestamp = dateFormat.format(ReportTimestamp);
//						System.out.println(formatedDate);
				    }*/
				
					/*if (nextnode.hasProperty("Source")) {
						Source = nextnode.getProperty("Source").getString();
						Source = fd.fetch_Source(session, Source);

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
								
								/*boolean hasLowercase=false;
//								 hasLowercase = !VesselName.equals(VesselName.toUpperCase());
								
								   
								  hasLowercase= checkString(VesselName);
								  if(hasLowercase==true){
									  
									  Flag="true";
//									  VesselName=VesselName1;
									  
									 // System.out.println("lowercase: "+s);
								  }else{
									  Flag="false";
								  }*/
								  
								  
							}
						}
						
						if(GmailMethods.isNullString(VesselName)){
							VesselName=VesselName1;
						}
						
						/*}else {
							VesselName="Route Rate";
						}*/

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
						//out.println("VesselType1:: "+VesselType1);
						VesselType = FetchData.fetch_vesselType(session, VesselType1);
						//out.println("VesselType:: "+VesselType);
						if(GmailMethods.isNullString(VesselType)){
							VesselType=VesselType1;
							//out.println("VesselType_if:: "+VesselType);
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

					
					
					/*if( OpenDate==null ||  OpenDate==""){
					     
					}*//*else{*/
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
//					}
					
					
					
					//boolean datFormate=AuthHelper.etaBasisCheckDAteformate(OpenDate, out, ReportTimestamp1, year);
					/*if( !GmailMethods.isNullString( OpenDate) ){*/
					
//					if(datFormate==true){
						
					
					

					if (keyNameObject.length() > 0) {
						keyNameObjectJsonarray.put(keyNameObject);
					}
					
//				}
					//limit = String.valueOf(Integer.parseInt(limit) + 1);

				} // while close (netpointer)
			}

				if (!nodeFlag) {

					Date date = ReportBeforeOnWeekDataDisplay.getLastWeek1();
					DateFormat dateFormatlastweek = new SimpleDateFormat("dd-MM-yyyy");
					String lastWeekDate = dateFormatlastweek.format(date);
					// out.println("lastWeekDate: "+lastWeekDate);

					Calendar cal = null;
					cal = Calendar.getInstance();

					Date todayDate = new Date(cal.getTimeInMillis());
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String currentDate = dateFormat.format(todayDate);
					// out.println("currentDate: "+currentDate);

					/*
					 * String
					 * dataCurrentDate=FetchData.convertStringToDate(currentDate
					 * ); String dataLastWeekDate=FetchData.convertStringToDate(
					 * lastWeekDate);
					 */
					keyNameObjectJsonarray = ReportBeforeOnWeekDataDisplay.getTonnageReportBeforeOneWeek(session,
							currentDate, lastWeekDate, out);

				} /*
					 * else{
					 * 
					 * keyNameObject=new JSONObject();
					 * keyNameObject.put("Error", "No New Data Available");
					 * keyNameObject.put("ErrorId", "1");
					 * keyNameObjectJsonarray.put(keyNameObject); }
					 */

				/*
				 * if(keyNameObjectJsonarray.length()>0){ mainJsonobj=new
				 * JSONObject(); mainJsonobj.put("tonnageList",
				 * keyNameObjectJsonarray);
				 * 
				 * }
				 */

				if (!nodeFlag && keyNameObjectJsonarray.length() > 0) {

					mainJsonobj = new JSONObject();
					mainJsonobj.put("tonnageList", keyNameObjectJsonarray);

					/*Node ExcelSummaryRecord = null;
					if (ReportData.hasNode("TonnageExcelSummaryRecord")) {
						ExcelSummaryRecord = ReportData.getNode("TonnageExcelSummaryRecord");
					} else {
						ExcelSummaryRecord = ReportData.addNode("TonnageExcelSummaryRecord");
						session.save();
					}

					String resultExcel = CreateExcelOfAllReports.createExcelTonnageNew(out, mainJsonobj.toString());
					JSONObject jsonResExcel = new JSONObject(resultExcel);
					if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("S")) {

						String fileName = "";
						if (jsonResExcel.has("fileName")) {
							fileName = jsonResExcel.getString("fileName");
						}

						Node ExcelSummaryRecord_SubNode = null;
						if (ExcelSummaryRecord.hasNode(fileName)) {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.getNode(fileName);
						} else {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.addNode(fileName);
							session.save();
						}

						ExcelSummaryRecord_SubNode.setProperty("summary_url", jsonResExcel.getString("fileUrl"));
						session.save();
						keyNameObject = new JSONObject();
						keyNameObject.put("summary_url", jsonResExcel.getString("fileUrl"));
						keyNameObjectJsonarray.put(keyNameObject);

					}else if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("E")) {

					}*/

				} else if (nodeFlag == true && keyNameObjectJsonarray.length() > 0) {

					mainJsonobj = new JSONObject();
					mainJsonobj.put("tonnageList", keyNameObjectJsonarray);
/*
					Node ExcelSummaryRecord = null;
					if (ReportData.hasNode("TonnageExcelSummaryRecord")) {
						ExcelSummaryRecord = ReportData.getNode("TonnageExcelSummaryRecord");
					} else {
						ExcelSummaryRecord = ReportData.addNode("TonnageExcelSummaryRecord");
						session.save();
					}

					String resultExcel = CreateExcelOfAllReports.createExcelTonnageNew(out, mainJsonobj.toString());
					JSONObject jsonResExcel = new JSONObject(resultExcel);
					if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("S")) {

						String fileName = "";
						if (jsonResExcel.has("fileName")) {
							fileName = jsonResExcel.getString("fileName");
						}

						Node ExcelSummaryRecord_SubNode = null;
						if (ExcelSummaryRecord.hasNode(fileName)) {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.getNode(fileName);
						} else {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.addNode(fileName);
							session.save();
						}

						ExcelSummaryRecord_SubNode.setProperty("summary_url", jsonResExcel.getString("fileUrl"));
						session.save();
						keyNameObject.put("summary_url", jsonResExcel.getString("fileUrl"));

					} else if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("E")) {

					}*/

				}

				/*
				 * Node ExcelSummaryRecord=null;
				 * if(ReportData.hasNode("TonnageExcelSummaryRecord")){
				 * ExcelSummaryRecord =
				 * ReportData.getNode("TonnageExcelSummaryRecord"); }else{
				 * ExcelSummaryRecord =
				 * ReportData.addNode("TonnageExcelSummaryRecord");
				 * session.save(); }
				 * 
				 * String
				 * resultExcel=CreateExcelOfAllReports.createExcelTonnageNew(
				 * out, mainJsonobj.toString()); JSONObject jsonResExcel= new
				 * JSONObject(resultExcel); if(jsonResExcel.has("status") &&
				 * jsonResExcel.getString("status").equals("S")) {
				 * 
				 * String fileName=""; if(jsonResExcel.has("fileName")){
				 * fileName=jsonResExcel.getString("fileName"); }
				 * 
				 * Node ExcelSummaryRecord_SubNode=null;
				 * if(ExcelSummaryRecord.hasNode(fileName)){
				 * ExcelSummaryRecord_SubNode =
				 * ExcelSummaryRecord.getNode(fileName); }else{
				 * ExcelSummaryRecord_SubNode =
				 * ExcelSummaryRecord.addNode(fileName); session.save(); }
				 * 
				 * ExcelSummaryRecord_SubNode.setProperty("summary_url",
				 * jsonResExcel.getString("fileUrl")); session.save();
				 * keyNameObject.put("summary_url",
				 * jsonResExcel.getString("fileUrl"));
				 * 
				 * }else if(jsonResExcel.has("status") &&
				 * jsonResExcel.getString("status").equals("E")) {
				 * 
				 * }
				 */

				/*
				 * Tonnage.setProperty("netChangedPointer",String.valueOf(size))
				 * ; session.save();
				 */

			} // node check close if

		} catch (Exception e) {
			e.printStackTrace(out);
//			out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public static boolean checkString(String input) {
	    String specialChars = "~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/?";
	    char currentCharacter;
	    boolean numberPresent = false;
	    boolean upperCasePresent = false;
	    boolean lowerCasePresent = false;
	    boolean specialCharacterPresent = false;
	 
	    for (int i = 0; i < input.length(); i++) {
	        currentCharacter = input.charAt(i);
	        if (Character.isDigit(currentCharacter)) {
	            numberPresent = true;
	        } else if (Character.isUpperCase(currentCharacter)) {
	            upperCasePresent = true;
	        } else if (Character.isLowerCase(currentCharacter)) {
	            lowerCasePresent = true;
	        } else if (specialChars.contains(String.valueOf(currentCharacter))) {
	            specialCharacterPresent = true;
	        }
	    }
	 
	    return lowerCasePresent;
//	      numberPresent && upperCasePresent && lowerCasePresent && specialCharacterPresent;
	    		
	}
	
	public static boolean checkString1(String input) {
	    String specialChars = "~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/?";
	    char currentCharacter;
	    boolean numberPresent = false;
	    boolean upperCasePresent = false;
	    boolean lowerCasePresent = false;
	    boolean specialCharacterPresent = false;
	 
	    for (int i = 0; i < input.length(); i++) {
	        currentCharacter = input.charAt(i);
	        if (Character.isDigit(currentCharacter)) {
	            numberPresent = true;
	        } else if (Character.isUpperCase(currentCharacter)) {
	            upperCasePresent = true;
	        } else if (Character.isLowerCase(currentCharacter)) {
	            lowerCasePresent = true;
	        } else if (specialChars.contains(String.valueOf(currentCharacter))) {
	            specialCharacterPresent = true;
	        }
	    }
	 
	    return numberPresent;
//	      numberPresent && upperCasePresent && lowerCasePresent && specialCharacterPresent;
	    		
	}

	
	public static JSONObject fetchSpotDataChanged(PrintWriter out, Session session ,String countRange) {

		JSONObject mainJsonobj = null;
		try {

			FetchData fd = new FetchData();
			Node scorpio = session.getRootNode().getNode("scorpioDataBase");
			Node ReportData = scorpio.getNode("ReportData");
			Node Spot = ReportData.getNode("Spot");

			if (Spot.hasNodes()) {
				/*long size = Spot.getNodes().getSize()+1;*/
				
				long size=0;
				if(Spot.hasProperty("jcr:count")){
					   String count=Spot.getProperty("jcr:count").getString();
					    size= Long.valueOf(count);
					}
				
				long countRangeI = Long.parseLong(countRange);
				String Id = "";

				JSONObject keyNameObject = null;
				JSONArray keyNameObjectJsonarray = new JSONArray();

				NodeIterator itr = Spot.getNodes();
				int i = 0;

				String netChangedPointer = "";
				String limit = "";
				boolean nodeFlag = false;
				if (Spot.hasProperty("netChangedPointer")) {
					netChangedPointer = Spot.getProperty("netChangedPointer").getString();
					limit = String.valueOf(Integer.parseInt(netChangedPointer) + 1);
				}
				long from= size - countRangeI;
				long size1 = from + 10;
				
//				while (Spot.hasNode(limit)) {
				for(long count=from;count <size1;count++){
					nodeFlag = true;
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
					
					
					if (nextnode.hasProperty("LoadPort")) {
						String LoadPortData = nextnode.getProperty("LoadPort").getString();
						//out.println("LoadPort:: "+LoadPort);
						String LoadPort1 = fd.fetch_Port(session, LoadPortData);
						//out.println("LoadPort1: "+LoadPort1);
						boolean checkjsonString=SaveReportDataClass.isJSONValid(LoadPort1);
						   if(checkjsonString==true){
							   JSONObject data=new JSONObject(LoadPort1);
							   if(data.has("portName")){
								   LoadPort=data.getString("portName");
								   //out.println("LoadPort: "+LoadPort);
							   }
							   
						   }
						   
						   if(GmailMethods.isNullString(LoadPort)){
							   LoadPort=LoadPortData;
						   }

					}
					
//					String DiscPortData="";
					
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
//					String VesselNameData="";
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
						  
						 // out.println("vesselname: "+VesselName);
					}
//					String RateTypeData="";
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
					/*if (nextnode.hasProperty("Source")) {
						Source = nextnode.getProperty("Source").getString();
						Source = fd.fetch_Source(session, Source);

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
						}/*else{
							Vesseltype="";
						}*/
						
						
						
						
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

					
					
					
					
//					if( LoadPort!="null" && LoadPort!="") {
//						out.println(LoadPort);
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
//					}// check null
				} // while close (netpointer)
				}

				if (!nodeFlag) {

					/*Date date = ReportBeforeOnWeekDataDisplay.getLastWeek1();
					DateFormat dateFormatlastweek = new SimpleDateFormat("dd-MM-yyyy");
					String lastWeekDate = dateFormatlastweek.format(date);
					// out.println("lastWeekDate: "+lastWeekDate);

					Calendar cal = null;
					cal = Calendar.getInstance();

					Date todayDate = new Date(cal.getTimeInMillis());
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String currentDate = dateFormat.format(todayDate);

					keyNameObjectJsonarray = ReportBeforeOnWeekDataDisplay.getSpotReportBeforeOneWeek(session,
							currentDate, lastWeekDate, out);*/
					
					
						
						keyNameObject=new JSONObject();
						keyNameObject.put("Error", "no data Available");
						keyNameObject.put("ErrorId", "2");
						keyNameObjectJsonarray.put(keyNameObject);
						
				 

				} /*
					 * else{ keyNameObject=new JSONObject();
					 * keyNameObject.put("Error", "No New Data Available");
					 * keyNameObject.put("ErrorId", "2");
					 * keyNameObjectJsonarray.put(keyNameObject); }
					 */

				/*
				 * if (keyNameObjectJsonarray.length() > 0) { mainJsonobj = new
				 * JSONObject(); mainJsonobj.put("spotList",
				 * keyNameObjectJsonarray);
				 * 
				 * }
				 */

				if (!nodeFlag && keyNameObjectJsonarray.length() > 0) {

					mainJsonobj = new JSONObject();
					mainJsonobj.put("spotList", keyNameObjectJsonarray);

					/*Node ExcelSummaryRecord = null;
					if (ReportData.hasNode("SpotExcelSummaryRecord")) {
						ExcelSummaryRecord = ReportData.getNode("SpotExcelSummaryRecord");
					} else {
						ExcelSummaryRecord = ReportData.addNode("SpotExcelSummaryRecord");
						session.save();
					}

					String resultExcel = CreateExcelOfAllReports.createExcelSpotNew(out, mainJsonobj.toString());
					JSONObject jsonResExcel = new JSONObject(resultExcel);
					if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("S")) {

						String fileName = "";
						if (jsonResExcel.has("fileName")) {
							fileName = jsonResExcel.getString("fileName");
						}

						Node ExcelSummaryRecord_SubNode = null;
						if (ExcelSummaryRecord.hasNode(fileName)) {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.getNode(fileName);
						} else {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.addNode(fileName);
							session.save();
						}

						ExcelSummaryRecord_SubNode.setProperty("summary_url", jsonResExcel.getString("fileUrl"));
						session.save();
						keyNameObject = new JSONObject();
						keyNameObject.put("summary_url", jsonResExcel.getString("fileUrl"));
						keyNameObjectJsonarray.put(keyNameObject);

					} else if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("E")) {

					}
*/
				} else if (nodeFlag == true && keyNameObjectJsonarray.length() > 0) {

					mainJsonobj = new JSONObject();
					mainJsonobj.put("spotList", keyNameObjectJsonarray);

					/*Node ExcelSummaryRecord = null;
					if (ReportData.hasNode("SpotExcelSummaryRecord")) {
						ExcelSummaryRecord = ReportData.getNode("SpotExcelSummaryRecord");
					} else {
						ExcelSummaryRecord = ReportData.addNode("SpotExcelSummaryRecord");
						session.save();
					}

					String resultExcel = CreateExcelOfAllReports.createExcelSpotNew(out, mainJsonobj.toString());
					JSONObject jsonResExcel = new JSONObject(resultExcel);
					if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("S")) {

						String fileName = "";
						if (jsonResExcel.has("fileName")) {
							fileName = jsonResExcel.getString("fileName");
						}

						Node ExcelSummaryRecord_SubNode = null;
						if (ExcelSummaryRecord.hasNode(fileName)) {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.getNode(fileName);
						} else {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.addNode(fileName);
							session.save();
						}

						ExcelSummaryRecord_SubNode.setProperty("summary_url", jsonResExcel.getString("fileUrl"));
						session.save();
						keyNameObject.put("summary_url", jsonResExcel.getString("fileUrl"));

					} else if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("E")) {

					}
*/
				}

				/*
				 * Node ExcelSummaryRecord = null; if
				 * (ReportData.hasNode("SpotExcelSummaryRecord")) {
				 * ExcelSummaryRecord =
				 * ReportData.getNode("SpotExcelSummaryRecord"); } else {
				 * ExcelSummaryRecord =
				 * ReportData.addNode("SpotExcelSummaryRecord"); session.save();
				 * }
				 * 
				 * String resultExcel =
				 * CreateExcelOfAllReports.createExcelSpotNew(out,
				 * mainJsonobj.toString()); JSONObject jsonResExcel = new
				 * JSONObject(resultExcel); if (jsonResExcel.has("status") &&
				 * jsonResExcel.getString("status").equals("S")) {
				 * 
				 * String fileName = ""; if (jsonResExcel.has("fileName")) {
				 * fileName = jsonResExcel.getString("fileName"); }
				 * 
				 * Node ExcelSummaryRecord_SubNode = null; if
				 * (ExcelSummaryRecord.hasNode(fileName)) {
				 * ExcelSummaryRecord_SubNode =
				 * ExcelSummaryRecord.getNode(fileName); } else {
				 * ExcelSummaryRecord_SubNode =
				 * ExcelSummaryRecord.addNode(fileName); session.save(); }
				 * 
				 * ExcelSummaryRecord_SubNode.setProperty("summary_url",
				 * jsonResExcel.getString("fileUrl")); session.save();
				 * keyNameObject.put("summary_url",
				 * jsonResExcel.getString("fileUrl"));
				 * 
				 * } else if (jsonResExcel.has("status") &&
				 * jsonResExcel.getString("status").equals("E")) {
				 * 
				 * }
				 */

				/*
				 * Spot.setProperty("netChangedPointer",String.valueOf(size));
				 * session.save();
				 */

			} // node check close if

		} catch (Exception e) {
			e.printStackTrace(out);
//			out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public static JSONObject fetchTimeCharterReportsDataChanged(PrintWriter out, Session session ,String countRange) {

		JSONObject mainJsonobj = null;
		try {
			FetchData fd = new FetchData();
			Node scorpio = session.getRootNode().getNode("scorpioDataBase");
			Node ReportData = scorpio.getNode("ReportData");
			Node TCReports = ReportData.getNode("TCReports");
			Node TimeCharterReports = TCReports.getNode("TimeCharterReport");

			if (TimeCharterReports.hasNodes()) {
				long size = TimeCharterReports.getNodes().getSize()+1;
				long countRangeI = Long.parseLong(countRange);
				String Id = "";

				JSONObject keyNameObject = null;
				JSONArray keyNameObjectJsonarray = new JSONArray();

				NodeIterator itr = TimeCharterReports.getNodes();
				int i = 0;

				String netChangedPointer = "";
				String limit = "";
				boolean nodeFlag = false;
				if (TimeCharterReports.hasProperty("netChangedPointer")) {
					netChangedPointer = TimeCharterReports.getProperty("netChangedPointer").getString();
					limit = String.valueOf(Integer.parseInt(netChangedPointer) + 1);
				}
				long from= size - countRangeI;
				long size1 = from + 10;
				
//				while (TimeCharterReports.hasNode(limit)) {
				for(long count=from;count <size1;count++){
					nodeFlag = true;
//					Node nextnode = TimeCharterReports.getNode(limit);
					if(TimeCharterReports.hasNode(String.valueOf(count))) {
						Node nextnode = TimeCharterReports.getNode(String.valueOf(count));
					i++;
					int parseId = 0;
					String subNodeName = nextnode.getName();

					String Information = "";
					String Charterer = "";
					String Source = "";
					String VesselName = "";
					String Comments = "";
					String Rate = "";
					String dateAndTime = "";
					String Sr_No = "";
					String emailUrl = "";

					if (nextnode.hasProperty("Id")) {
						Id = nextnode.getProperty("Id").getString();
					}
					if (nextnode.hasProperty("Sr.No")) {
						Sr_No = nextnode.getProperty("Sr.No").getString();
					}
					if (nextnode.hasProperty("dateAndTime")) {
						dateAndTime = nextnode.getProperty("dateAndTime").getString();
					}
					if (nextnode.hasProperty("Information")) {
						Information = nextnode.getProperty("Information").getString();
						Information = fd.fetch_Information(session, Information);

					}
					if (nextnode.hasProperty("emailUrl")) {
						emailUrl = nextnode.getProperty("emailUrl").getString();
					}
					String Spot_TC = "";
					if (nextnode.hasProperty("Spot_TC")) {

						Spot_TC = nextnode.getProperty("Spot_TC").getString();
						Spot_TC = fd.fetch_ReportType(session, Spot_TC);

					}else{
						Spot_TC="TC";
					}
					String Date = "";
					if (nextnode.hasProperty("Date")) {

						Date = nextnode.getProperty("Date").getString();

					}
                   String VesselName1="";
					if (nextnode.hasProperty("VesselName")) {
						VesselName1 = nextnode.getProperty("VesselName").getString();

						VesselName = fd.fetch_vesselName(session, VesselName1);

						if( !GmailMethods.isNullString(VesselName) ){
							boolean checkjsonString = SaveReportDataClass.isJSONValid(VesselName);
							if (checkjsonString == true) {
						       JSONObject fetch_vesselNamejsonobject = new JSONObject(VesselName);
						
						if (fetch_vesselNamejsonobject.has("vessel_Id")) {
							VesselName = fetch_vesselNamejsonobject.getString("vessel_Id");
						}

					}
						}else{
							VesselName=VesselName1;
						}
					}

					String CPP_DPP = "";
					String CPP_DPP1="";
					if (nextnode.hasProperty("CPP_DPP")) {

						CPP_DPP1 = nextnode.getProperty("CPP_DPP").getString();
						CPP_DPP = fd.fetch_CargoType(session, CPP_DPP1);

						if(GmailMethods.isNullString(CPP_DPP)){
							CPP_DPP=CPP_DPP1;
						}
					}

					String Charterer1="";
					if (nextnode.hasProperty("Charterer")) {
						Charterer1 = nextnode.getProperty("Charterer").getString();
						Charterer = fd.fetch_Charterer(session, Charterer1);

						if(GmailMethods.isNullString(Charterer)){
							Charterer=Charterer1;
						}
					}
					
//					String ReportTimestamp1="";
					String ReportTimestamp="";
					if (nextnode.hasProperty("ReportTimestamp")) {
						String ReportTimestamp1 = nextnode.getProperty("ReportTimestamp").getString();
						
						if(ReportTimestamp1.lastIndexOf(".")!=-1){
							ReportTimestamp1=ReportTimestamp1.substring( 0,ReportTimestamp1.lastIndexOf(".") );
//							out.println(ReportTimestamp);
							
							LocalDateTime localDateTime = LocalDateTime.parse(ReportTimestamp1);
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
							ReportTimestamp = localDateTime.format(formatter);
							if(ReportTimestamp.lastIndexOf("/")!=-1)
							{
								ReportTimestamp=ReportTimestamp.substring(ReportTimestamp.lastIndexOf("/")+1);
//								System.out.println(ReportTimestamp);
							}
						}
						
					}
					
					String Delivery = "";
					if (nextnode.hasProperty("Delivery")) {
						Delivery = nextnode.getProperty("Delivery").getString();
						
					}
					
					String DeliveryPlace = "";
					String DeliveryPlace1="";
					if (nextnode.hasProperty("DeliveryPlace")) {

						DeliveryPlace1 = nextnode.getProperty("DeliveryPlace").getString();
						DeliveryPlace = fd.fetch_DeliveryPlace(session, DeliveryPlace1);

						if(GmailMethods.isNullString(DeliveryPlace)){
							DeliveryPlace=DeliveryPlace1;
						}
					}
					String Period = "";
					if (nextnode.hasProperty("Period")) {

						 Period = nextnode.getProperty("Period").getString();

					}
					
					
					String Periodunit = "";
					String Periodunit1 = "";
					if (nextnode.hasProperty("Periodunit")) {

						Periodunit1 = nextnode.getProperty("Periodunit").getString();
						Periodunit = fd.fetch_Periodunit(session, Periodunit1);

						if(GmailMethods.isNullString(DeliveryPlace)){
							Periodunit=Periodunit1;
						}
					}
					String Redelivery1 = "";
					
					if (nextnode.hasProperty("Redelivery")) {

						Redelivery1 = nextnode.getProperty("Redelivery").getString();

					}
					String Redelivery="";
					if( !GmailMethods.isNullString(Redelivery1) ){
						  Redelivery=Redelivery1;
					}else{
						String redeliveryData="";
						if( !GmailMethods.isNullString(Delivery) && !GmailMethods.isNullString(Period) ){
							redeliveryData=FifteenMinuteClass.deliveryDate(Delivery, ReportTimestamp, Period, Periodunit, out);
							Redelivery=redeliveryData;
						}
					}
					
					String TCRate = "";
					if (nextnode.hasProperty("TCRate")) {

						TCRate = nextnode.getProperty("TCRate").getString();

					}

					if (nextnode.hasProperty("Rate")) {
						Rate = nextnode.getProperty("Rate").getString();

					}
					String Unit = "";
					String Unit1 = "";
					if (nextnode.hasProperty("Unit")) {

						Unit1 = nextnode.getProperty("Unit").getString();
						Unit = fd.fetch_currencyUnit(session, Unit1);

						if(GmailMethods.isNullString(Unit)){
							Unit=Unit1;
						}
					}

					if (nextnode.hasProperty("Comments")) {
						Comments = nextnode.getProperty("Comments").getString();

					}

					/*if (nextnode.hasProperty("Source")) {
						Source = nextnode.getProperty("Source").getString();
						Source = fd.fetch_Source(session, Source);

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
					
					
					String Vesseltype = "";
					String Vesseltype1 = "";
					if (nextnode.hasProperty("Vesseltype")) {

						Vesseltype1 = nextnode.getProperty("Vesseltype").getString();
						Vesseltype = FetchData.fetch_vesselType(session, Vesseltype1);
						if(GmailMethods.isNullString(Vesseltype)){
							Vesseltype=Vesseltype1;
						}

					}
					String TimeStamp = "";
					if (nextnode.hasProperty("TimeStamp")) {

						TimeStamp = nextnode.getProperty("TimeStamp").getString();

					}
					
					/*if (nextnode.hasProperty("TimeStamp")) {
						String TimeStamp1 = nextnode.getProperty("TimeStamp").getString();
						
						if(TimeStamp1.lastIndexOf(".")!=-1){
							TimeStamp1=TimeStamp1.substring( 0,TimeStamp1.lastIndexOf(".") );
//							out.println(ReportTimestamp);
							
							LocalDateTime localDateTime = LocalDateTime.parse(TimeStamp1);
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
							TimeStamp = localDateTime.format(formatter);
							if(TimeStamp.lastIndexOf("/")!=-1)
							{
								TimeStamp=TimeStamp.substring(TimeStamp.lastIndexOf("/")+1);
//								System.out.println(ReportTimestamp);
							}
						}
						
					}
					*/
					
					String emailClientNodeId = "";
					if (nextnode.hasProperty("emailClientNodeId")) {
						emailClientNodeId = nextnode.getProperty("emailClientNodeId").getString();

					}
					
					String vesselFlag = "";
					if (nextnode.hasProperty("vesselFlag")) {

						vesselFlag = nextnode.getProperty("vesselFlag").getString();

					}
					

					keyNameObject = new JSONObject();

					keyNameObject.put("TimeCharterReportsId", Id);
					keyNameObject.put("dateAndTime", dateAndTime);
					keyNameObject.put("Information", Information.toUpperCase());
					keyNameObject.put("Spot_TC", Spot_TC.toUpperCase());
					keyNameObject.put("Date", Date.toUpperCase());
					keyNameObject.put("VesselName", VesselName.toUpperCase());
					keyNameObject.put("CPP_DPP", CPP_DPP.toUpperCase());
					keyNameObject.put("Charterer", Charterer.toUpperCase());
					keyNameObject.put("Delivery", Delivery.toUpperCase());
					keyNameObject.put("DeliveryPlace", DeliveryPlace.toUpperCase());
					keyNameObject.put("Period", Period);
					keyNameObject.put("Periodunit", Periodunit.toUpperCase());
					keyNameObject.put("Redelivery", Redelivery.toUpperCase());
					keyNameObject.put("TCRate", TCRate.toUpperCase());
					keyNameObject.put("Rate", Rate.toUpperCase());
					keyNameObject.put("Unit", Unit.toUpperCase());
					keyNameObject.put("Comments", Comments.toUpperCase());
					keyNameObject.put("Source", Source.toUpperCase());
					keyNameObject.put("Vesseltype", Vesseltype.toUpperCase());
					keyNameObject.put("TimeStamp", TimeStamp); 
					keyNameObject.put("subNodeName", subNodeName);
					keyNameObject.put("size", size + 1);// parseId
					keyNameObject.put("parseId", parseId + 1);
					keyNameObject.put("i", i);
					keyNameObject.put("Sr_No", Sr_No);
					keyNameObject.put("emailUrl", emailUrl);
					keyNameObject.put("emailClientNodeId", emailClientNodeId);
					keyNameObject.put("vesselFlag", vesselFlag);
					

					if (keyNameObject.length() > 0) {
						keyNameObjectJsonarray.put(keyNameObject);
					}

					limit = String.valueOf(Integer.parseInt(limit) + 1);

				} // while close
			}

				if (!nodeFlag) {

					Date date = ReportBeforeOnWeekDataDisplay.getLastWeek1();
					DateFormat dateFormatlastweek = new SimpleDateFormat("dd-MM-yyyy");
					String lastWeekDate = dateFormatlastweek.format(date);
					// out.println("lastWeekDate: "+lastWeekDate);

					Calendar cal = null;
					cal = Calendar.getInstance();

					Date todayDate = new Date(cal.getTimeInMillis());
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String currentDate = dateFormat.format(todayDate);

					keyNameObjectJsonarray = ReportBeforeOnWeekDataDisplay.getTimeCharterReportBeforeOneWeek(session,
							currentDate, lastWeekDate, out);

				} /*
					 * else{ keyNameObject=new JSONObject();
					 * keyNameObject.put("Error", "No New Data Available");
					 * keyNameObject.put("ErrorId", "3");
					 * keyNameObjectJsonarray.put(keyNameObject); }
					 */

				if (!nodeFlag && keyNameObjectJsonarray.length() > 0) {

					mainJsonobj = new JSONObject();
					mainJsonobj.put("TimeCharterReportsList", keyNameObjectJsonarray);

					/*Node ExcelSummaryRecord = null;
					if (ReportData.hasNode("TimeCharterReportsExcelSummaryRecord")) {
						ExcelSummaryRecord = ReportData.getNode("TimeCharterReportsExcelSummaryRecord");
					} else {
						ExcelSummaryRecord = ReportData.addNode("TimeCharterReportsExcelSummaryRecord");
						session.save();
					}

					String resultExcel = CreateExcelOfAllReports.createExcelTimeCharterReportsNew(out,
							mainJsonobj.toString());
					JSONObject jsonResExcel = new JSONObject(resultExcel);
					if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("S")) {

						String fileName = "";
						if (jsonResExcel.has("fileName")) {
							fileName = jsonResExcel.getString("fileName");
						}

						Node ExcelSummaryRecord_SubNode = null;
						if (ExcelSummaryRecord.hasNode(fileName)) {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.getNode(fileName);
						} else {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.addNode(fileName);
							session.save();
						}

						ExcelSummaryRecord_SubNode.setProperty("summary_url", jsonResExcel.getString("fileUrl"));
						session.save();
						keyNameObject = new JSONObject();
						keyNameObject.put("summary_url", jsonResExcel.getString("fileUrl"));
						keyNameObjectJsonarray.put(keyNameObject);

					} else if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("E")) {

					}*/

				} else if (nodeFlag == true && keyNameObjectJsonarray.length() > 0) {

					mainJsonobj = new JSONObject();
					mainJsonobj.put("TimeCharterReportsList", keyNameObjectJsonarray);

					/*Node ExcelSummaryRecord = null;
					if (ReportData.hasNode("TimeCharterReportsExcelSummaryRecord")) {
						ExcelSummaryRecord = ReportData.getNode("TimeCharterReportsExcelSummaryRecord");
					} else {
						ExcelSummaryRecord = ReportData.addNode("TimeCharterReportsExcelSummaryRecord");
						session.save();
					}

					String resultExcel = CreateExcelOfAllReports.createExcelTimeCharterReportsNew(out,
							mainJsonobj.toString());
					JSONObject jsonResExcel = new JSONObject(resultExcel);
					if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("S")) {

						String fileName = "";
						if (jsonResExcel.has("fileName")) {
							fileName = jsonResExcel.getString("fileName");
						}

						Node ExcelSummaryRecord_SubNode = null;
						if (ExcelSummaryRecord.hasNode(fileName)) {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.getNode(fileName);
						} else {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.addNode(fileName);
							session.save();
						}

						ExcelSummaryRecord_SubNode.setProperty("summary_url", jsonResExcel.getString("fileUrl"));
						session.save();
						keyNameObject.put("summary_url", jsonResExcel.getString("fileUrl"));

					} else if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("E")) {

					}*/

				}

				/*
				 * if (keyNameObjectJsonarray.length() > 0) { mainJsonobj = new
				 * JSONObject(); mainJsonobj.put("TimeCharterReportsList",
				 * keyNameObjectJsonarray);
				 * 
				 * // out.println(mainJsonobj); }
				 * 
				 * Node ExcelSummaryRecord = null; if
				 * (ReportData.hasNode("TimeCharterReportsExcelSummaryRecord"))
				 * { ExcelSummaryRecord =
				 * ReportData.getNode("TimeCharterReportsExcelSummaryRecord"); }
				 * else { ExcelSummaryRecord =
				 * ReportData.addNode("TimeCharterReportsExcelSummaryRecord");
				 * session.save(); }
				 * 
				 * String resultExcel =
				 * CreateExcelOfAllReports.createExcelTimeCharterReportsNew(out,
				 * mainJsonobj.toString()); JSONObject jsonResExcel = new
				 * JSONObject(resultExcel); if (jsonResExcel.has("status") &&
				 * jsonResExcel.getString("status").equals("S")) {
				 * 
				 * String fileName = ""; if (jsonResExcel.has("fileName")) {
				 * fileName = jsonResExcel.getString("fileName"); }
				 * 
				 * Node ExcelSummaryRecord_SubNode = null; if
				 * (ExcelSummaryRecord.hasNode(fileName)) {
				 * ExcelSummaryRecord_SubNode =
				 * ExcelSummaryRecord.getNode(fileName); } else {
				 * ExcelSummaryRecord_SubNode =
				 * ExcelSummaryRecord.addNode(fileName); session.save(); }
				 * 
				 * ExcelSummaryRecord_SubNode.setProperty("summary_url",
				 * jsonResExcel.getString("fileUrl")); session.save();
				 * keyNameObject.put("summary_url",
				 * jsonResExcel.getString("fileUrl"));
				 * 
				 * } else if (jsonResExcel.has("status") &&
				 * jsonResExcel.getString("status").equals("E")) {
				 * 
				 * }
				 */
				/*
				 * TimeCharterReports.setProperty("netChangedPointer",String.
				 * valueOf(size)); session.save();
				 */

			} // node check close if

		} catch (Exception e) {
			e.printStackTrace(out);
			out.println(e.getMessage());

		}
		return mainJsonobj;

	}
	
	
	
	public static String checkDash(String c){
	
	
	String three=null;
	
	String parts[] = c.split("-");
//	System.out.println(parts.length);
	if (parts.length==3) {
	
	if((null != c)) {
		String one = null;
	    int firstDashIndex = c.indexOf('-');
	    String two = null;
	    if(firstDashIndex >= 0) {
	        one = c.substring(0, firstDashIndex);
	        
	        if((firstDashIndex + 1) < c.length()) {
	            two = c.substring(firstDashIndex + 1);
	        }
	        
	        if((firstDashIndex + 1) < two.length()) {
	            three = two.substring(0,firstDashIndex + 1);
	            
	        }
	        
	        
	    }
	}
	}
	return three;
	}
	
	public static String checkDashOne(String c){
		String one = null;
		try{
		
		String parts[] = c.split("-");
//		System.out.println(parts.length);
		if (parts.length==2) {
		
		if((null != c)) {
		    int firstDashIndex = c.indexOf('-');
		    if(firstDashIndex >= 0) {
		        one = c.substring(0, firstDashIndex);
		       
		    }
		}
		}
	}
		catch(Exception e){
			return "";
		}
		return one;
		}
	
	public static String checkDashSlashOne(String c){
		String one = null;
		
		try{
		
		String parts[] = c.split("/");
		System.out.println(parts.length);
		if (parts.length==2) {
		
		if((null != c)) {
		    int firstDashIndex = c.indexOf('/');
		    if(firstDashIndex >= 0) {
		        one = c.substring(0, firstDashIndex);
		       
		    }
		}
		}
		}catch(Exception e){
			return "";
		}
		return one;
		}
	
	
	public static String checkDashPeriod(String c){
		String one = null;
		
		if((null != c)) {
		    int firstDashIndex = c.indexOf('-');
		    if(firstDashIndex >= 0) {
		        one = c.substring(0, firstDashIndex);
		       
		    }
		}
		
		return one;
		}
	
	public static String checkPlushPeriod(String c){
		String one = null;
		
		if((null != c)) {
		    int firstDashIndex = c.indexOf('+');
		    if(firstDashIndex >= 0) {
		        one = c.substring(0, firstDashIndex);
		       
		    }
		}
		
		return one;
		}
	
	
	public static boolean checkOnlySingleNUmber(String c){
		 boolean b=false;
		try{
		Pattern p = Pattern.compile("[^A-Za-z0-9]");
	     Matcher m = p.matcher(c);
	    // boolean b = m.matches();
	      b = m.find();
	     if (b != true){
//	        System.out.println("There is a special character in my string ");
	    	 b=false;
	     }/*else{
//	         System.out.println("There is no special char.");
	    	
	     }*/
		}catch(Exception e){
			return false;
		}
		return b;
	    
		
		
		}
	
	
	public static int monthsInint(String MonthOfName){
		
//		String MonthOfName = "";
		int number_Of_DaysInMonth = 0;
		
		switch (MonthOfName.toLowerCase()) {
        case "january":
        case "jan":
            number_Of_DaysInMonth = 1;
            break;
        case "february":
        case "feb":
                number_Of_DaysInMonth = 2;
            
            break;
        case "march":
        case "mar":
            number_Of_DaysInMonth = 3;
            break;
        case "april":
        case "apr":
            MonthOfName = "April";
            number_Of_DaysInMonth = 4;
            break;
        case "may":
            MonthOfName = "May";
            number_Of_DaysInMonth = 5;
            break;
        case "june":
        case "jun":
            MonthOfName = "June";
            number_Of_DaysInMonth = 6;
            break;
        case "july":
        case "jul":
            MonthOfName = "July";
            number_Of_DaysInMonth = 7;
            break;
        case "august":
        case "aug":
            MonthOfName = "August";
            number_Of_DaysInMonth = 8;
            break;
        case "september":
        case "sep":
        case "sept":
            MonthOfName = "September";
            number_Of_DaysInMonth = 9;
            break;
        case "october":
        case "oct":
            MonthOfName = "October";
            number_Of_DaysInMonth = 10;
            break;
        case "november":
        case "nov":
            MonthOfName = "November";
            number_Of_DaysInMonth = 11;
            break;
        case "december":
        case "dec":
            MonthOfName = "December";
            number_Of_DaysInMonth = 12;
    }
		return number_Of_DaysInMonth;
   
		
}

	

	public static JSONObject fetchBrokerTcRateDataChanged(PrintWriter out, Session session ,String countRange) {

		JSONObject mainJsonobj = null;
		try {
			FetchData fd = new FetchData();
			Node scorpio = session.getRootNode().getNode("scorpioDataBase");
			Node ReportData = scorpio.getNode("ReportData");
			Node TCReports = ReportData.getNode("TCReports");
			Node TimeCharterReports = TCReports.getNode("BrokerTCRate");

			if (TimeCharterReports.hasNodes()) {

				JSONObject keyNameObject = null;
				long size = TimeCharterReports.getNodes().getSize() +1;
				long countRangeI = Long.parseLong(countRange);
				JSONArray keyNameObjectJsonarray = new JSONArray();

				NodeIterator itr = TimeCharterReports.getNodes();
				int i = 0;

				String netChangedPointer = "";
				String limit = "";
				boolean nodeFlag = false;
				if (TimeCharterReports.hasProperty("netChangedPointer")) {
					netChangedPointer = TimeCharterReports.getProperty("netChangedPointer").getString();
					limit = String.valueOf(Integer.parseInt(netChangedPointer) + 1);
				}

				long from= size - countRangeI;
				long size1 = from + 10;
				
//				while (TimeCharterReports.hasNode(limit)) {
				for(long count=from;count <size1;count++){
					nodeFlag = true;
//					Node nextnode = TimeCharterReports.getNode(limit);
					if(TimeCharterReports.hasNode(String.valueOf(count))) {
						Node nextnode = TimeCharterReports.getNode(String.valueOf(count));

					String Id = "";
					int parseId = 0;
					i++;

					String dateAndTime = "";
					String Sr_No = "";
					String emailUrl = "";

					String subNodeName = nextnode.getName();

					if (nextnode.hasProperty("Id")) {

						Id = nextnode.getProperty("Id").getString();
						parseId = Integer.parseInt(Id);
					}
					if (nextnode.hasProperty("Sr.No")) {
						Sr_No = nextnode.getProperty("Sr.No").getString();
					}
					if (nextnode.hasProperty("dateAndTime")) {
						dateAndTime = nextnode.getProperty("dateAndTime").getString();
					}
					if (nextnode.hasProperty("emailUrl")) {
						emailUrl = nextnode.getProperty("emailUrl").getString();
					}
					String Broker = "";
					/*if (nextnode.hasProperty("Broker")) {

						Broker = nextnode.getProperty("Broker").getString();
						Broker = fd.fetch_Source(session, Broker);

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
					
					String VesselType = "";
					String VesselType1 = "";
					if (nextnode.hasProperty("VesselType")) {

						VesselType1 = nextnode.getProperty("VesselType").getString();
						VesselType = FetchData.fetch_vesselType(session, VesselType1);

						if(GmailMethods.isNullString(VesselType)){
							VesselType=VesselType1;
						}else{
							VesselType="";
						}
						
					}
					String Month_Year = "";
					if (nextnode.hasProperty("Month_Year")) {

						Month_Year = nextnode.getProperty("Month_Year").getString();

					}
					String first_yr = "";
					if (nextnode.hasProperty("first_yr")) {

						first_yr = nextnode.getProperty("first_yr").getString();

					}

					String second_yr = "";
					if (nextnode.hasProperty("second_yr")) {

						second_yr = nextnode.getProperty("second_yr").getString();

					}
					String third_yr = "";
					if (nextnode.hasProperty("third_yr")) {

						third_yr = nextnode.getProperty("third_yr").getString();

					}
					String fifth_yr = "";
					if (nextnode.hasProperty("fifth_yr")) {

						fifth_yr = nextnode.getProperty("fifth_yr").getString();

					}
					String emailClientNodeId = "";
					if (nextnode.hasProperty("emailClientNodeId")) {
						emailClientNodeId = nextnode.getProperty("emailClientNodeId").getString();

					}
					

					keyNameObject = new JSONObject();

					keyNameObject.put("BrokerTcRateId", Id);
					keyNameObject.put("dateAndTime", dateAndTime);
					keyNameObject.put("Broker", Broker.toUpperCase());
					keyNameObject.put("VesselType", VesselType.toUpperCase());
					keyNameObject.put("Month_Year", Month_Year.toUpperCase());
					keyNameObject.put("first_yr", first_yr);
					keyNameObject.put("second_yr", second_yr);
					keyNameObject.put("third_yr", third_yr);
					keyNameObject.put("fifth_yr", fifth_yr);

					keyNameObject.put("subNodeName", subNodeName);
					keyNameObject.put("size", size + 1);// parseId
					keyNameObject.put("parseId", parseId + 1);
					keyNameObject.put("i", i);
					keyNameObject.put("Sr_No", Sr_No);
					keyNameObject.put("emailUrl", emailUrl);
					keyNameObject.put("emailClientNodeId", emailClientNodeId);
					

					if (keyNameObject.length() > 0) {
						keyNameObjectJsonarray.put(keyNameObject);
					}

					limit = String.valueOf(Integer.parseInt(limit) + 1);

				} // while close
			  }

				if (!nodeFlag) {

					Date date = ReportBeforeOnWeekDataDisplay.getLastWeek1();
					DateFormat dateFormatlastweek = new SimpleDateFormat("dd-MM-yyyy");
					String lastWeekDate = dateFormatlastweek.format(date);
					// out.println("lastWeekDate: "+lastWeekDate);

					Calendar cal = null;
					cal = Calendar.getInstance();

					Date todayDate = new Date(cal.getTimeInMillis());
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String currentDate = dateFormat.format(todayDate);

					keyNameObjectJsonarray = ReportBeforeOnWeekDataDisplay.getBrokerTcRateReportBeforeOneWeek(session,
							currentDate, lastWeekDate, out);

				} /*
					 * else{ keyNameObject=new JSONObject();
					 * keyNameObject.put("Error", "No New Data Available");
					 * keyNameObject.put("ErrorId", "4");
					 * keyNameObjectJsonarray.put(keyNameObject); }
					 */

				if (!nodeFlag && keyNameObjectJsonarray.length() > 0) {

					mainJsonobj = new JSONObject();
					mainJsonobj.put("BrokerTcRateList", keyNameObjectJsonarray);

					/*Node ExcelSummaryRecord = null;
					if (ReportData.hasNode("BrokerTcRateExcelSummaryRecord")) {
						ExcelSummaryRecord = ReportData.getNode("BrokerTcRateExcelSummaryRecord");
					} else {
						ExcelSummaryRecord = ReportData.addNode("BrokerTcRateExcelSummaryRecord");
						session.save();
					}

					String resultExcel = CreateExcelOfAllReports.createExcelBrokerTcRateNew(out,
							mainJsonobj.toString());
					JSONObject jsonResExcel = new JSONObject(resultExcel);
					if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("S")) {

						String fileName = "";
						if (jsonResExcel.has("fileName")) {
							fileName = jsonResExcel.getString("fileName");
						}

						Node ExcelSummaryRecord_SubNode = null;
						if (ExcelSummaryRecord.hasNode(fileName)) {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.getNode(fileName);
						} else {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.addNode(fileName);
							session.save();
						}

						ExcelSummaryRecord_SubNode.setProperty("summary_url", jsonResExcel.getString("fileUrl"));
						session.save();
						keyNameObject = new JSONObject();
						keyNameObject.put("summary_url", jsonResExcel.getString("fileUrl"));
						keyNameObjectJsonarray.put(keyNameObject);

					} else if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("E")) {

					}
*/
				} else if (nodeFlag == true && keyNameObjectJsonarray.length() > 0) {

					mainJsonobj = new JSONObject();
					mainJsonobj.put("BrokerTcRateList", keyNameObjectJsonarray);

					/*Node ExcelSummaryRecord = null;
					if (ReportData.hasNode("BrokerTcRateExcelSummaryRecord")) {
						ExcelSummaryRecord = ReportData.getNode("BrokerTcRateExcelSummaryRecord");
					} else {
						ExcelSummaryRecord = ReportData.addNode("BrokerTcRateExcelSummaryRecord");
						session.save();
					}

					String resultExcel = CreateExcelOfAllReports.createExcelBrokerTcRateNew(out,
							mainJsonobj.toString());
					JSONObject jsonResExcel = new JSONObject(resultExcel);
					if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("S")) {

						String fileName = "";
						if (jsonResExcel.has("fileName")) {
							fileName = jsonResExcel.getString("fileName");
						}

						Node ExcelSummaryRecord_SubNode = null;
						if (ExcelSummaryRecord.hasNode(fileName)) {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.getNode(fileName);
						} else {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.addNode(fileName);
							session.save();
						}

						ExcelSummaryRecord_SubNode.setProperty("summary_url", jsonResExcel.getString("fileUrl"));
						session.save();
						keyNameObject.put("summary_url", jsonResExcel.getString("fileUrl"));

					} else if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("E")) {

					}*/

				}

				/*
				 * if (keyNameObjectJsonarray.length() > 0) { mainJsonobj = new
				 * JSONObject(); mainJsonobj.put("BrokerTcRateList",
				 * keyNameObjectJsonarray);
				 * 
				 * // out.println(mainJsonobj); }
				 * 
				 * Node ExcelSummaryRecord = null; if
				 * (ReportData.hasNode("BrokerTcRateExcelSummaryRecord")) {
				 * ExcelSummaryRecord =
				 * ReportData.getNode("BrokerTcRateExcelSummaryRecord"); } else
				 * { ExcelSummaryRecord =
				 * ReportData.addNode("BrokerTcRateExcelSummaryRecord");
				 * session.save(); }
				 * 
				 * String resultExcel =
				 * CreateExcelOfAllReports.createExcelBrokerTcRateNew(out,
				 * mainJsonobj.toString()); JSONObject jsonResExcel = new
				 * JSONObject(resultExcel); if (jsonResExcel.has("status") &&
				 * jsonResExcel.getString("status").equals("S")) {
				 * 
				 * String fileName = ""; if (jsonResExcel.has("fileName")) {
				 * fileName = jsonResExcel.getString("fileName"); }
				 * 
				 * Node ExcelSummaryRecord_SubNode = null; if
				 * (ExcelSummaryRecord.hasNode(fileName)) {
				 * ExcelSummaryRecord_SubNode =
				 * ExcelSummaryRecord.getNode(fileName); } else {
				 * ExcelSummaryRecord_SubNode =
				 * ExcelSummaryRecord.addNode(fileName); session.save(); }
				 * 
				 * ExcelSummaryRecord_SubNode.setProperty("summary_url",
				 * jsonResExcel.getString("fileUrl")); session.save();
				 * keyNameObject.put("summary_url",
				 * jsonResExcel.getString("fileUrl"));
				 * 
				 * } else if (jsonResExcel.has("status") &&
				 * jsonResExcel.getString("status").equals("E")) {
				 * 
				 * }
				 */

				/*
				 * TimeCharterReports.setProperty("netChangedPointer",String.
				 * valueOf(size)); session.save();
				 */

			} // node check close if

		} catch (Exception e) {
			e.printStackTrace(out);
//			out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public JSONObject getTonnageReportWithoutDataFrame(Session session, String reportType, PrintWriter out) {

		JSONObject mainJsonobj = null;
		try {
			FetchData fd = new FetchData();
			ConvertDate CD=new ConvertDate();
			if (!GmailMethods.isNullString(reportType)) {

				Node scorpio = session.getRootNode().getNode("scorpioDataBase");
				Node ReportData = scorpio.getNode("ReportData");
				Node Tonnage = ReportData.getNode("Tonnage");
				FetchData FD = new FetchData();
				String reporttypequeryid = FD.check_ReportTypeToFetchapi(session, reportType, out);
				Workspace workspace = session.getWorkspace();

				String slingqery = "select [ReportType] from [nt:base] where ReportType ='" + reporttypequeryid
						+ "'  and ISDESCENDANTNODE('/scorpioDataBase/ReportData/Tonnage/')";

				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();

				NodeIterator iterator = queryResult.getNodes();

//				RowIterator rowIterator = queryResult.getRows();
				
				boolean nodeFlag = false;
				long size = queryResult.getRows().getSize();
				JSONObject keyNameObject = null;
				JSONArray keyNameObjectJsonarray = new JSONArray();
				String netChangedPointer = "";
				String limit = "";

				/*if (Tonnage.hasProperty("netChangedPointer")) {
					netChangedPointer = Tonnage.getProperty("netChangedPointer").getString();
					limit = String.valueOf(Integer.parseInt(netChangedPointer) + 1);
				}*/
//				if (rowIterator != null) {
				
				while (iterator.hasNext()) {
//					while (Tonnage.hasNode(limit)) {
						nodeFlag = true;
//						Node nextnode = Tonnage.getNode(limit);
						Node nextnode = iterator.nextNode();

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
						String ReportTimestamp = "";
						String RepositionRegion = "";
						String Source = "";
						String VesselName = "";

						if (nextnode.hasProperty("Id")) {
							Id = nextnode.getProperty("Id").getString();
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
						

						if (nextnode.hasProperty("OpenPort")) {
							String OpenPort12 = nextnode.getProperty("OpenPort").getString();
							String OpenPort1 = fd.fetch_Port(session, OpenPort12);

							boolean checkjsonString = SaveReportDataClass.isJSONValid(OpenPort1);
							if (checkjsonString == true) {
								JSONObject data = new JSONObject(OpenPort1);
								if (data.has("portName")) {
									OpenPort = data.getString("portName");
								}
								/*if (data.has("Area")) {
									RepositionRegion = data.getString("Area");
									// check_Port_repositionRegion_id=check_OpenPort_id;
								}else {
									RepositionRegion="";
								}*/

							}
							
							if(GmailMethods.isNullString(OpenPort) ){
								OpenPort=OpenPort12;
							}

						}

//						if (GmailMethods.isNullString(RepositionRegion)) {

							if (nextnode.hasProperty("RepositionRegion")) {
								String RepositionRegion12 = nextnode.getProperty("RepositionRegion").getString();

								String RepositionRegion1 = fd.fetch_Port(session, RepositionRegion12);
								boolean checkjsonString = SaveReportDataClass.isJSONValid(RepositionRegion1);
								if (checkjsonString == true) {
									JSONObject data = new JSONObject(RepositionRegion1);
									
									/*if (data.has("Area")) {
										RepositionRegion = data.getString("Area");
										// check_Port_repositionRegion_id=check_OpenPort_id;
									}*/
									
									if (data.has("portName")) {
										RepositionRegion = data.getString("portName");
										// check_Port_repositionRegion_id=check_OpenPort_id;
									}

								}
								
								if(GmailMethods.isNullString(RepositionRegion) ){
									RepositionRegion=RepositionRegion12;
								}

							} else {
								RepositionRegion = "";
							}

//						} // blank
						
						String ReportTimestamp1="";
						String ReportTimestamp2="";
						if (nextnode.hasProperty("ReportTimestamp")) {
							 ReportTimestamp1 = nextnode.getProperty("ReportTimestamp").getString();
							
							if(ReportTimestamp1.lastIndexOf(".")!=-1){
								ReportTimestamp1=ReportTimestamp1.substring( 0,ReportTimestamp1.lastIndexOf(".") );
//								out.println(ReportTimestamp);
								
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
							//ETABasis=CD.convertDateFormat(ETABasis1);
							 if( ETABasis.contains("PPT") || ETABasis.contains("PPT - exten") ){
									
							     LocalDateTime localDateTime = LocalDateTime.parse(ReportTimestamp1);
							     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
							     ETABasis = localDateTime.format(formatter);
							//out.println("ETABasis_ppt: "+ETABasis);
							
						}
							// session.save();
							 
						}
						
						
						//String elseOpenDate="";
						if (nextnode.hasProperty("OpenDate")) {
							 OpenDate = nextnode.getProperty("OpenDate").getString();
							//OpenDate=CD.convertDateFormat(OpenDate1);
							
							if( OpenDate.contains("PPT") || OpenDate.contains("PPT - exten") ){
								
							     LocalDateTime localDateTime = LocalDateTime.parse(ReportTimestamp1);
							     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
							     OpenDate = localDateTime.format(formatter);
							//out.println("ETABasis_ppt: "+ETABasis);
							
						}
							//session.save();

						}
						if (nextnode.hasProperty("ReportType")) {
							ReportType = nextnode.getProperty("ReportType").getString();
							ReportType = fd.fetch_ReportType(session, ReportType);

						}
						
						
						/*if (nextnode.hasProperty("textSentMailTime")) {
						    ReportTimestamp = nextnode.getProperty("textSentMailTime").getString();
						    
						    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
							ReportTimestamp = dateFormat.format(ReportTimestamp);
//							System.out.println(formatedDate);
					    }*/
					
						/*if (nextnode.hasProperty("Source")) {
							Source = nextnode.getProperty("Source").getString();
							Source = fd.fetch_Source(session, Source);

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
//							if( !GmailMethods.isNullString(VesselName) ){
							boolean checkjsonString = SaveReportDataClass.isJSONValid(VesselName);
							if (checkjsonString == true) {
								JSONObject fetch_vesselNamejsonobject = new JSONObject(VesselName);
								if (fetch_vesselNamejsonobject.has("vessel_Id")) {
									 VesselName = fetch_vesselNamejsonobject.getString("vessel_Id");
									
									/*boolean hasLowercase=false;
//									 hasLowercase = !VesselName.equals(VesselName.toUpperCase());
									
									   
									  hasLowercase= checkString(VesselName);
									  if(hasLowercase==true){
										  
										  Flag="true";
//										  VesselName=VesselName1;
										  
										 // System.out.println("lowercase: "+s);
									  }else{
										  Flag="false";
									  }*/
									  
									  
								}
							}
							
							if(GmailMethods.isNullString(VesselName)){
								VesselName=VesselName1;
							}
							
							/*}else {
								VesselName="Route Rate";
							}*/

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
							//out.println("VesselType1:: "+VesselType1);
							VesselType = FetchData.fetch_vesselType(session, VesselType1);
							//out.println("VesselType:: "+VesselType);
							if(GmailMethods.isNullString(VesselType)){
								VesselType=VesselType1;
								//out.println("VesselType_if:: "+VesselType);
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

						keyNameObject.put("tonnageId", Id);
						keyNameObject.put("EmploymentStatus", EmploymentStatus.toUpperCase());
						keyNameObject.put("Owners", Owners.toUpperCase());
						keyNameObject.put("OpenPort", OpenPort.toUpperCase());
						keyNameObject.put("Operators", Operators.toUpperCase());
						keyNameObject.put("CargoType", CargoType.toUpperCase());
						keyNameObject.put("Comment", Comment.toUpperCase());
						keyNameObject.put("ETABasis", ETABasis);
						keyNameObject.put("OpenDate", OpenDate);
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
						
						//boolean datFormate=AuthHelper.etaBasisCheckDAteformate(OpenDate, out, ReportTimestamp1, year);
						
//						if(datFormate==true){

						if (keyNameObject.length() > 0) {
							keyNameObjectJsonarray.put(keyNameObject);
						}
						//limit = String.valueOf(Integer.parseInt(limit) + 1);
//						}
					} // while
//				}

				if (!nodeFlag) {
					keyNameObject = new JSONObject();
					keyNameObject.put("Error", "No Data Available");
					keyNameObjectJsonarray.put(keyNameObject);

				}

				if (keyNameObjectJsonarray.length() > 0) {
					mainJsonobj = new JSONObject();
					mainJsonobj.put("tonnageList", keyNameObjectJsonarray);

				}

				/*Tonnage.setProperty("netChangedPointer", String.valueOf(size));
				session.save();*/

			}

		} catch (Exception e) {
			out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public JSONObject getSpotReportWithoutDataFrame(Session session, String reportType, PrintWriter out) {

		JSONObject mainJsonobj = null;
		try {
			FetchData fd = new FetchData();
			if (!GmailMethods.isNullString(reportType)) {

				Node scorpio = session.getRootNode().getNode("scorpioDataBase");
				Node ReportData = scorpio.getNode("ReportData");
				Node Tonnage = ReportData.getNode("Spot");
				FetchData FD = new FetchData();
				String reporttypequeryid = FD.check_ReportTypeToFetchapi(session, reportType, out);
				Workspace workspace = session.getWorkspace();

				String slingqery = "select [ReportType] from [nt:base] where ReportType ='" + reporttypequeryid
						+ "'  and ISDESCENDANTNODE('/scorpioDataBase/ReportData/Spot/')";

				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();

//				RowIterator rowIterator = queryResult.getRows();
				NodeIterator iterator = queryResult.getNodes();
				boolean nodeFlag = false;
				long size = Tonnage.getNodes().getSize();
				JSONObject keyNameObject = null;
				JSONArray keyNameObjectJsonarray = new JSONArray();
				String netChangedPointer = "";
				String limit = "";

				/*if (Tonnage.hasProperty("netChangedPointer")) {
					netChangedPointer = Tonnage.getProperty("netChangedPointer").getString();
					limit = String.valueOf(Integer.parseInt(netChangedPointer) + 1);
				}*/
//				if (rowIterator != null) {

//					while (Tonnage.hasNode(limit)) {
				while (iterator.hasNext()) {
						nodeFlag = true;
//						Node nextnode = Tonnage.getNode(limit);
						Node nextnode = iterator.nextNode();

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
						String Id = "";

						if (nextnode.hasProperty("Id")) {
							Id = nextnode.getProperty("Id").getString();
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
							//out.println("LoadPort:: "+LoadPort);
							String LoadPort1 = fd.fetch_Port(session, LoadPortData);
							//out.println("LoadPort1: "+LoadPort1);
							boolean checkjsonString=SaveReportDataClass.isJSONValid(LoadPort1);
							   if(checkjsonString==true){
								   JSONObject data=new JSONObject(LoadPort1);
								   if(data.has("portName")){
									   LoadPort=data.getString("portName");
									   //out.println("LoadPort: "+LoadPort);
								   }
								   
							   }
							   
							   if(GmailMethods.isNullString(LoadPort)){
								   LoadPort=LoadPortData;
							   }

						}
						
//						String DiscPortData="";
						
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
//						String VesselNameData="";
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
							  
							 // out.println("vesselname: "+VesselName);
						}
//						String RateTypeData="";
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
						/*if (nextnode.hasProperty("Source")) {
							Source = nextnode.getProperty("Source").getString();
							Source = fd.fetch_Source(session, Source);

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
							}/*else{
								Vesseltype="";
							}*/
							
							
							
							
						}
						
						String vesselFlag = "";
						if (nextnode.hasProperty("vesselFlag")) {

							vesselFlag = nextnode.getProperty("vesselFlag").getString();

						}
						String flagPort = "";
						if (nextnode.hasProperty("flagPort")) {

							flagPort = nextnode.getProperty("flagPort").getString();

						}
						
						String ReportTimestamp="";
						String ReportTimestamp1="";
						String ReportTimestamp2="";
						if (nextnode.hasProperty("ReportTimestamp")) {
							 ReportTimestamp1 = nextnode.getProperty("ReportTimestamp").getString();
							
							if(ReportTimestamp1.lastIndexOf(".")!=-1){
								ReportTimestamp1=ReportTimestamp1.substring( 0,ReportTimestamp1.lastIndexOf(".") );
//								out.println(ReportTimestamp);
								
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
						
						
						keyNameObject = new JSONObject();

						keyNameObject.put("spotId", Id);
						keyNameObject.put("Information", Information.toUpperCase());
						keyNameObject.put("FixtureType", FixtureType.toUpperCase());
						keyNameObject.put("LCStart", LCStart.toUpperCase());
						keyNameObject.put("LCEnd", LCEnd.toUpperCase());
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
						keyNameObject.put("ReportTimestamp", ReportTimestamp);

						if (keyNameObject.length() > 0) {
							keyNameObjectJsonarray.put(keyNameObject);
						}

						//limit = String.valueOf(Integer.parseInt(limit) + 1);

					} // while

//				}

				if (!nodeFlag) {
					keyNameObject = new JSONObject();
					keyNameObject.put("Error", "No Data Available");
					keyNameObjectJsonarray.put(keyNameObject);

				}

				if (keyNameObjectJsonarray.length() > 0) {
					mainJsonobj = new JSONObject();
					mainJsonobj.put("spotList", keyNameObjectJsonarray);

				}

				/*Tonnage.setProperty("netChangedPointer", String.valueOf(size));
				session.save();*/

			}

		} catch (Exception e) {
			e.printStackTrace(out);
//			out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public JSONObject getTimeCharterReportWithoutDataFrame(Session session, String reportType, PrintWriter out) {

		JSONObject mainJsonobj = null;
		try {
			FetchData fd = new FetchData();
			if (!GmailMethods.isNullString(reportType)) {

				Node scorpio = session.getRootNode().getNode("scorpioDataBase");
				Node ReportData = scorpio.getNode("ReportData");
				Node TCReports = ReportData.getNode("TCReports");
				Node Tonnage = TCReports.getNode("TimeCharterReport");

				FetchData FD = new FetchData();
				String reporttypequeryid = FD.check_ReportTypeToFetchapi(session, reportType, out);
				Workspace workspace = session.getWorkspace();

				String slingqery = "select [ReportType] from [nt:base] where ReportType ='" + reporttypequeryid
						+ "'  and ISDESCENDANTNODE('/scorpioDataBase/ReportData/TCReports/TimeCharterReport/')";

				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();

//				RowIterator rowIterator = queryResult.getRows();
				NodeIterator iterator = queryResult.getNodes();
				boolean nodeFlag = false;
				long size = Tonnage.getNodes().getSize();
				JSONObject keyNameObject = null;
				JSONArray keyNameObjectJsonarray = new JSONArray();
				String netChangedPointer = "";
				String limit = "";

				/*if (Tonnage.hasProperty("netChangedPointer")) {
					netChangedPointer = Tonnage.getProperty("netChangedPointer").getString();
					limit = String.valueOf(Integer.parseInt(netChangedPointer) + 1);
				}*/
//				if (rowIterator != null) {

//					while (Tonnage.hasNode(limit)) {
				while (iterator.hasNext()) {
						nodeFlag = true;
//						Node nextnode = Tonnage.getNode(limit);
						Node nextnode = iterator.nextNode();

						String Information = "";
						String Charterer = "";
						String Source = "";
						String VesselName = "";
						String Comments = "";
						String Rate = "";
						String Id = "";

						if (nextnode.hasProperty("Id")) {
							Id = nextnode.getProperty("Id").getString();
							//out.println("id: "+Id);
						}

						if (nextnode.hasProperty("Information")) {
							Information = nextnode.getProperty("Information").getString();
							Information = fd.fetch_Information(session, Information);
							//out.println("Information: "+Information);
						}
						String Spot_TC = "";
						if (nextnode.hasProperty("Spot_TC")) {

							Spot_TC = nextnode.getProperty("Spot_TC").getString();
							Spot_TC = fd.fetch_ReportType(session, Spot_TC);

						}else{
							Spot_TC="TC";
						}
						String Date = "";
						if (nextnode.hasProperty("Date")) {

							Date = nextnode.getProperty("Date").getString();

						}
						String VesselName1="";
						if (nextnode.hasProperty("VesselName")) {
							VesselName1 = nextnode.getProperty("VesselName").getString();

							VesselName = fd.fetch_vesselName(session, VesselName1);

							if( !GmailMethods.isNullString(VesselName) ){
								boolean checkjsonString = SaveReportDataClass.isJSONValid(VesselName);
								if (checkjsonString == true) {
							       JSONObject fetch_vesselNamejsonobject = new JSONObject(VesselName);
							
							if (fetch_vesselNamejsonobject.has("vessel_Id")) {
								VesselName = fetch_vesselNamejsonobject.getString("vessel_Id");
							}

						}
							}else{
								VesselName=VesselName1;
							}
						}
						String CPP_DPP = "";
						String CPP_DPP1="";
						if (nextnode.hasProperty("CPP_DPP")) {

							CPP_DPP1 = nextnode.getProperty("CPP_DPP").getString();
							CPP_DPP = fd.fetch_CargoType(session, CPP_DPP1);

							if(GmailMethods.isNullString(CPP_DPP)){
								CPP_DPP=CPP_DPP1;
							}
						}

						String Charterer1="";
						if (nextnode.hasProperty("Charterer")) {
							Charterer1 = nextnode.getProperty("Charterer").getString();
							Charterer = fd.fetch_Charterer(session, Charterer1);

							if(GmailMethods.isNullString(Charterer)){
								Charterer=Charterer1;
							}
						}
						String Delivery = "";
						if (nextnode.hasProperty("Delivery")) {

							Delivery = nextnode.getProperty("Delivery").getString();

						}
						String DeliveryPlace = "";
						String DeliveryPlace1="";
						if (nextnode.hasProperty("DeliveryPlace")) {

							DeliveryPlace1 = nextnode.getProperty("DeliveryPlace").getString();
							DeliveryPlace = fd.fetch_DeliveryPlace(session, DeliveryPlace1);

							if(GmailMethods.isNullString(DeliveryPlace)){
								DeliveryPlace=DeliveryPlace1;
							}
						}
						String Period = "";
						if (nextnode.hasProperty("Period")) {

							Period = nextnode.getProperty("Period").getString();

						}
						String ReportTimestamp="";
						if (nextnode.hasProperty("ReportTimestamp")) {
							String ReportTimestamp1 = nextnode.getProperty("ReportTimestamp").getString();
							
							if(ReportTimestamp1.lastIndexOf(".")!=-1){
								ReportTimestamp1=ReportTimestamp1.substring( 0,ReportTimestamp1.lastIndexOf(".") );
//								out.println(ReportTimestamp);
								
								LocalDateTime localDateTime = LocalDateTime.parse(ReportTimestamp1);
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
								ReportTimestamp = localDateTime.format(formatter);
								if(ReportTimestamp.lastIndexOf("/")!=-1)
								{
									ReportTimestamp=ReportTimestamp.substring(ReportTimestamp.lastIndexOf("/")+1);
//									System.out.println(ReportTimestamp);
								}
							}
							
						}
						String Periodunit = "";
						String Periodunit1 = "";
						if (nextnode.hasProperty("Periodunit")) {

							Periodunit1 = nextnode.getProperty("Periodunit").getString();
							Periodunit = fd.fetch_Periodunit(session, Periodunit1);

							if(GmailMethods.isNullString(DeliveryPlace)){
								Periodunit=Periodunit1;
							}
						}
						String Redelivery1 = "";
						if (nextnode.hasProperty("Redelivery")) {

							Redelivery1 = nextnode.getProperty("Redelivery").getString();

						}
						
						String Redelivery="";
						if( !GmailMethods.isNullString(Redelivery1) ){
							  Redelivery=Redelivery1;
						}else{
							String redeliveryData="";
							if( !GmailMethods.isNullString(Delivery) && !GmailMethods.isNullString(Period) ){
								redeliveryData=FifteenMinuteClass.deliveryDate(Delivery, ReportTimestamp, Period, Periodunit, out);
								Redelivery=redeliveryData;
							}
						}
						
						String TCRate = "";
						if (nextnode.hasProperty("TCRate")) {

							TCRate = nextnode.getProperty("TCRate").getString();

						}

						if (nextnode.hasProperty("Rate")) {
							Rate = nextnode.getProperty("Rate").getString();

						}
						String Unit = "";
						String Unit1 = "";
						if (nextnode.hasProperty("Unit")) {

							Unit1 = nextnode.getProperty("Unit").getString();
							Unit = fd.fetch_currencyUnit(session, Unit1);

							if(GmailMethods.isNullString(Unit)){
								Unit=Unit1;
							}
						}

						if (nextnode.hasProperty("Comments")) {
							Comments = nextnode.getProperty("Comments").getString();

						}

						/*if (nextnode.hasProperty("Source")) {
							Source = nextnode.getProperty("Source").getString();
							Source = fd.fetch_Source(session, Source);

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
						
						String Vesseltype = "";
						String Vesseltype1 = "";
						if (nextnode.hasProperty("Vesseltype")) {

							Vesseltype1 = nextnode.getProperty("Vesseltype").getString();
							Vesseltype = FetchData.fetch_vesselType(session, Vesseltype1);
							if(GmailMethods.isNullString(Vesseltype)){
								Vesseltype=Vesseltype1;
							}

						}
						String TimeStamp = "";
						if (nextnode.hasProperty("TimeStamp")) {

							TimeStamp = nextnode.getProperty("TimeStamp").getString();

						}

						keyNameObject = new JSONObject();

						keyNameObject.put("TimeCharterReportsId", Id);
						keyNameObject.put("Information", Information.toUpperCase());
						keyNameObject.put("Spot_TC", Spot_TC.toUpperCase());
						keyNameObject.put("Date", Date);
						keyNameObject.put("VesselName", VesselName.toUpperCase());
						keyNameObject.put("CPP_DPP", CPP_DPP.toUpperCase());
						keyNameObject.put("Charterer", Charterer.toUpperCase());
						keyNameObject.put("Delivery", Delivery.toUpperCase());
						keyNameObject.put("DeliveryPlace", DeliveryPlace.toUpperCase());
						keyNameObject.put("Period", Period.toUpperCase());
						keyNameObject.put("Periodunit", Periodunit.toUpperCase());
						keyNameObject.put("Redelivery", Redelivery.toUpperCase());
						keyNameObject.put("TCRate", TCRate.toUpperCase());
						keyNameObject.put("Rate", Rate.toUpperCase());
						keyNameObject.put("Unit", Unit.toUpperCase());
						keyNameObject.put("Comments", Comments.toUpperCase());
						keyNameObject.put("Source", Source.toUpperCase());
						keyNameObject.put("Vesseltype", Vesseltype.toUpperCase());
						keyNameObject.put("TimeStamp", TimeStamp); //TimeStamp

						if (keyNameObject.length() > 0) {
							keyNameObjectJsonarray.put(keyNameObject);
						}

						//limit = String.valueOf(Integer.parseInt(limit) + 1);

					} // while
				  //  out.println("keyNameObjectJsonarray: "+keyNameObjectJsonarray);

//				}

				if (!nodeFlag) {
					keyNameObject = new JSONObject();
					keyNameObject.put("Error", "No Data Available");
					keyNameObjectJsonarray.put(keyNameObject);

				}

				if (keyNameObjectJsonarray.length() > 0) {
					mainJsonobj = new JSONObject();
					mainJsonobj.put("TimeCharterReportsList", keyNameObjectJsonarray);

				}

				/*Tonnage.setProperty("netChangedPointer", String.valueOf(size));
				session.save();*/
                   // out.println("mainJsonobj: "+mainJsonobj);
			}

		} catch (Exception e) {
			out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public JSONObject getBrokerTcRateReportWithoutDataFrame(Session session, String reportType, PrintWriter out) {

		JSONObject mainJsonobj = null;
		try {
			FetchData fd = new FetchData();
			if (!GmailMethods.isNullString(reportType)) {

				Node scorpio = session.getRootNode().getNode("scorpioDataBase");
				Node ReportData = scorpio.getNode("ReportData");
				Node TCReports = ReportData.getNode("TCReports");
				Node Tonnage = TCReports.getNode("BrokerTCRate");

				FetchData FD = new FetchData();
				String reporttypequeryid = FD.check_ReportTypeToFetchapi(session, reportType, out);
				Workspace workspace = session.getWorkspace();

				String slingqery = "select [ReportType] from [nt:base] where ReportType ='" + reporttypequeryid
						+ "'  and ISDESCENDANTNODE('/scorpioDataBase/ReportData/TCReports/BrokerTCRate/')";

				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();

//				RowIterator rowIterator = queryResult.getRows();
				NodeIterator iterator = queryResult.getNodes();
				boolean nodeFlag = false;
				long size = Tonnage.getNodes().getSize();
				JSONObject keyNameObject = null;
				JSONArray keyNameObjectJsonarray = new JSONArray();
				String netChangedPointer = "";
				String limit = "";

				/*if (Tonnage.hasProperty("netChangedPointer")) {
					netChangedPointer = Tonnage.getProperty("netChangedPointer").getString();
					limit = String.valueOf(Integer.parseInt(netChangedPointer) + 1);
				}*/
				
				
//				if (rowIterator != null) {

//					while (Tonnage.hasNode(limit)) {
					while (iterator.hasNext()) {
						nodeFlag = true;
//						Node nextnode = Tonnage.getNode(limit);
						Node nextnode = iterator.nextNode();
						String Id = "";

						if (nextnode.hasProperty("Id")) {
							Id = nextnode.getProperty("Id").getString();
						}
						String Broker = "";
						/*if (nextnode.hasProperty("Broker")) {

							Broker = nextnode.getProperty("Broker").getString();
							Broker = fd.fetch_Source(session, Broker);

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
						
						
						String VesselType = "";
						String VesselType1 = "";
						if (nextnode.hasProperty("VesselType")) {

							VesselType1 = nextnode.getProperty("VesselType").getString();
							VesselType = FetchData.fetch_vesselType(session, VesselType1);

							if(GmailMethods.isNullString(VesselType)){
								VesselType=VesselType1;
							}else{
								VesselType="";
							}
							
						}
						String Month_Year = "";
						if (nextnode.hasProperty("Month_Year")) {

							Month_Year = nextnode.getProperty("Month_Year").getString();

						}
						String first_yr = "";
						if (nextnode.hasProperty("first_yr")) {

							first_yr = nextnode.getProperty("first_yr").getString();

						}

						String second_yr = "";
						if (nextnode.hasProperty("second_yr")) {

							second_yr = nextnode.getProperty("second_yr").getString();

						}
						String third_yr = "";
						if (nextnode.hasProperty("third_yr")) {

							third_yr = nextnode.getProperty("third_yr").getString();

						}
						String fifth_yr = "";
						if (nextnode.hasProperty("fifth_yr")) {

							fifth_yr = nextnode.getProperty("fifth_yr").getString();

						}

						keyNameObject = new JSONObject();

						keyNameObject.put("BrokerTcRateId", Id);
						keyNameObject.put("Broker", Broker.toUpperCase());
						keyNameObject.put("VesselType", VesselType.toUpperCase());
						keyNameObject.put("Month_Year", Month_Year.toUpperCase());
						keyNameObject.put("first_yr", first_yr);
						keyNameObject.put("second_yr", second_yr);
						keyNameObject.put("third_yr", third_yr);
						keyNameObject.put("fifth_yr", fifth_yr);

						if (keyNameObject.length() > 0) {
							keyNameObjectJsonarray.put(keyNameObject);
						}

						//limit = String.valueOf(Integer.parseInt(limit) + 1);

					} // while

//				}

				if (!nodeFlag) {
					keyNameObject = new JSONObject();
					keyNameObject.put("Error", "No Data Available");
					keyNameObjectJsonarray.put(keyNameObject);

				}

				if (keyNameObjectJsonarray.length() > 0) {
					mainJsonobj = new JSONObject();
					mainJsonobj.put("BrokerTcRateList", keyNameObjectJsonarray);

				}

				/*Tonnage.setProperty("netChangedPointer", String.valueOf(size));
				session.save();*/

			}

		} catch (Exception e) {
			out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public JSONObject getTonnageReportDateWithoutDataFrame(Session session, String from, String to, String reportType,
			PrintWriter out) {

		JSONObject mainJsonobj = null;
		try {
			ConvertDate CD=new ConvertDate();
			if (!GmailMethods.isNullString(from) && !GmailMethods.isNullString(to)
					&& !GmailMethods.isNullString(reportType)) {

				FetchData fd = new FetchData();
				String reporttypequeryid = fd.check_ReportTypeToFetchapi(session, reportType, out);
				String dataFrom = FetchData.convertStringToDate(from);
				dataFrom = dataFrom + "T00:00:00.000Z"; 
				String dataTo = FetchData.convertStringToDate(to);
				dataTo = dataTo + "T23:59:59.999Z";
				Workspace workspace = session.getWorkspace();

//				String slingqery = "select [ReportTimestamp] from [nt:base] where ReportTimestamp >='" + dataFrom
//						+ "'and ReportTimestamp <='" + dataTo + "' and ReportType=" + reporttypequeryid
//						+ " and ISDESCENDANTNODE('/scorpioDataBase/ReportData/Tonnage/')";
				String slingqery = "SELECT [ReportTimestamp] from [nt:base] WHERE ReportTimestamp >= CAST('"+dataFrom+"' AS DATE) AND ReportTimestamp <= CAST('"+dataTo+"' AS DATE) AND ReportType=" + reporttypequeryid
						+ " and ISDESCENDANTNODE('/scorpioDataBase/ReportData/Tonnage/')";
				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();
				NodeIterator iterator = queryResult.getNodes();
				boolean nodeFlag = false;

				JSONObject keyNameObject = null;
				JSONArray keyNameObjectJsonarray = new JSONArray();

				while (iterator.hasNext()) {

					nodeFlag = true;
					Node nextnode = iterator.nextNode();

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
					String ReportTimestamp = "";
					String RepositionRegion = "";
					String Source = "";
					String VesselName = "";
					String Sr_No="";
					String dateAndTime="";

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
					

					if (nextnode.hasProperty("OpenPort")) {
						String OpenPort12 = nextnode.getProperty("OpenPort").getString();
						String OpenPort1 = fd.fetch_Port(session, OpenPort12);

						boolean checkjsonString = SaveReportDataClass.isJSONValid(OpenPort1);
						if (checkjsonString == true) {
							JSONObject data = new JSONObject(OpenPort1);
							if (data.has("portName")) {
								OpenPort = data.getString("portName");
							}
							/*if (data.has("Area")) {
								RepositionRegion = data.getString("Area");
								// check_Port_repositionRegion_id=check_OpenPort_id;
							}else {
								RepositionRegion="";
							}*/

						}
						
						if(GmailMethods.isNullString(OpenPort) ){
							OpenPort=OpenPort12;
						}

					}

//					if (GmailMethods.isNullString(RepositionRegion)) {

						if (nextnode.hasProperty("RepositionRegion")) {
							String RepositionRegion12 = nextnode.getProperty("RepositionRegion").getString();

							String RepositionRegion1 = fd.fetch_Port(session, RepositionRegion12);
							boolean checkjsonString = SaveReportDataClass.isJSONValid(RepositionRegion1);
							if (checkjsonString == true) {
								JSONObject data = new JSONObject(RepositionRegion1);
								
								/*if (data.has("Area")) {
									RepositionRegion = data.getString("Area");
									// check_Port_repositionRegion_id=check_OpenPort_id;
								}*/
								
								if (data.has("portName")) {
									RepositionRegion = data.getString("portName");
									// check_Port_repositionRegion_id=check_OpenPort_id;
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
						//ETABasis=CD.convertDateFormat(ETABasis1);
						 if( ETABasis.contains("PPT") || ETABasis.contains("PPT - exten") ){
								
						     LocalDateTime localDateTime = LocalDateTime.parse(ReportTimestamp1);
						     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
						     ETABasis = localDateTime.format(formatter);
						//out.println("ETABasis_ppt: "+ETABasis);
						
					}
						// session.save();
						 
					}
					
					
					//String elseOpenDate="";
					if (nextnode.hasProperty("OpenDate")) {
						 OpenDate = nextnode.getProperty("OpenDate").getString();
						//OpenDate=CD.convertDateFormat(OpenDate1);
						
						if( OpenDate.contains("PPT") || OpenDate.contains("PPT - exten") ){
							
						     LocalDateTime localDateTime = LocalDateTime.parse(ReportTimestamp1);
						     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
						     OpenDate = localDateTime.format(formatter);
						//out.println("ETABasis_ppt: "+ETABasis);
						
					}
						//session.save();

					}
					if (nextnode.hasProperty("ReportType")) {
						ReportType = nextnode.getProperty("ReportType").getString();
						ReportType = fd.fetch_ReportType(session, ReportType);

					}
					
					
					/*if (nextnode.hasProperty("textSentMailTime")) {
					    ReportTimestamp = nextnode.getProperty("textSentMailTime").getString();
					    
					    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
						ReportTimestamp = dateFormat.format(ReportTimestamp);
//						System.out.println(formatedDate);
				    }*/
				
					/*if (nextnode.hasProperty("Source")) {
						Source = nextnode.getProperty("Source").getString();
						Source = fd.fetch_Source(session, Source);

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
								
								/*boolean hasLowercase=false;
//								 hasLowercase = !VesselName.equals(VesselName.toUpperCase());
								
								   
								  hasLowercase= checkString(VesselName);
								  if(hasLowercase==true){
									  
									  Flag="true";
//									  VesselName=VesselName1;
									  
									 // System.out.println("lowercase: "+s);
								  }else{
									  Flag="false";
								  }*/
								  
								  
							}
						}
						
						if(GmailMethods.isNullString(VesselName)){
							VesselName=VesselName1;
						}
						
						/*}else {
							VesselName="Route Rate";
						}*/

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
						//out.println("VesselType1:: "+VesselType1);
						VesselType = FetchData.fetch_vesselType(session, VesselType1);
						//out.println("VesselType:: "+VesselType);
						if(GmailMethods.isNullString(VesselType)){
							VesselType=VesselType1;
							//out.println("VesselType_if:: "+VesselType);
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

					keyNameObject.put("tonnageId", Id);
					keyNameObject.put("EmploymentStatus", EmploymentStatus.toUpperCase());
					keyNameObject.put("Owners", Owners.toUpperCase());
					keyNameObject.put("OpenPort", OpenPort.toUpperCase());
					keyNameObject.put("Operators", Operators.toUpperCase());
					keyNameObject.put("CargoType", CargoType.toUpperCase());
					keyNameObject.put("Comment", Comment.toUpperCase());
					keyNameObject.put("ETABasis", ETABasis.toUpperCase());
					keyNameObject.put("OpenDate", OpenDate);
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
					
                   // boolean datFormate=AuthHelper.etaBasisCheckDAteformate(OpenDate, out, ReportTimestamp1, year);
					
//					if(datFormate==true){

					if (keyNameObject.length() > 0) {
						keyNameObjectJsonarray.put(keyNameObject);
					}
//				}

				} // while

				if (!nodeFlag) {
					keyNameObject = new JSONObject();
					keyNameObject.put("Error", "no Data Available Between Given Dates");
					keyNameObjectJsonarray.put(keyNameObject);

				}

				if (keyNameObjectJsonarray.length() > 0) {
					mainJsonobj = new JSONObject();
					mainJsonobj.put("tonnageList", keyNameObjectJsonarray);

				}

			}

		} catch (Exception e) {
			out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public JSONObject getSpotReportDateWithoutDataFrame(Session session, String from, String to, String reportType,
			PrintWriter out) {

		JSONObject mainJsonobj = null;
		try {

			if (!GmailMethods.isNullString(from) && !GmailMethods.isNullString(to)
					&& !GmailMethods.isNullString(reportType)) {

				FetchData fd = new FetchData();
				String reporttypequeryid = fd.check_ReportTypeToFetchapi(session, reportType, out);
				String dataFrom = FetchData.convertStringToDate(from);
				dataFrom = dataFrom + "T00:00:00.000Z"; 
				String dataTo = FetchData.convertStringToDate(to);
				dataTo = dataTo + "T23:59:59.999Z";
				Workspace workspace = session.getWorkspace();
//				String slingqery = "select [ReportTimestamp] from [nt:base] where ReportTimestamp >='" + dataFrom
//						+ "'and ReportTimestamp <='" + dataTo + "' and ReportType=" + reporttypequeryid
//						+ " and ISDESCENDANTNODE('/scorpioDataBase/ReportData/Spot/')";
				String slingqery = "SELECT [ReportTimestamp] from [nt:base] WHERE ReportTimestamp >= CAST('"+dataFrom+"' AS DATE) AND ReportTimestamp <= CAST('"+dataTo+"' AS DATE) AND ReportType=" + reporttypequeryid
						+ " and ISDESCENDANTNODE('/scorpioDataBase/ReportData/Spot/')";
				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();
				NodeIterator iterator = queryResult.getNodes();
				boolean nodeFlag = false;
				JSONObject keyNameObject = null;
				JSONArray keyNameObjectJsonarray = new JSONArray();

				while (iterator.hasNext()) {

					nodeFlag = true;
					Node nextnode = iterator.nextNode();

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
					String Id = "";

					if (nextnode.hasProperty("Id")) {
						Id = nextnode.getProperty("Id").getString();
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
						//out.println("LoadPort:: "+LoadPort);
						String LoadPort1 = fd.fetch_Port(session, LoadPortData);
						//out.println("LoadPort1: "+LoadPort1);
						boolean checkjsonString=SaveReportDataClass.isJSONValid(LoadPort1);
						   if(checkjsonString==true){
							   JSONObject data=new JSONObject(LoadPort1);
							   if(data.has("portName")){
								   LoadPort=data.getString("portName");
								   //out.println("LoadPort: "+LoadPort);
							   }
							   
						   }
						   
						   if(GmailMethods.isNullString(LoadPort)){
							   LoadPort=LoadPortData;
						   }

					}
					
//					String DiscPortData="";
					
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
//					String VesselNameData="";
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
						  
						 // out.println("vesselname: "+VesselName);
					}
//					String RateTypeData="";
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
					/*if (nextnode.hasProperty("Source")) {
						Source = nextnode.getProperty("Source").getString();
						Source = fd.fetch_Source(session, Source);

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
						}/*else{
							Vesseltype="";
						}*/
						
						
						
						
					}
					
					String vesselFlag = "";
					if (nextnode.hasProperty("vesselFlag")) {

						vesselFlag = nextnode.getProperty("vesselFlag").getString();

					}
					String flagPort = "";
					if (nextnode.hasProperty("flagPort")) {

						flagPort = nextnode.getProperty("flagPort").getString();

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
					
					keyNameObject = new JSONObject();

					keyNameObject.put("spotId", Id);
					keyNameObject.put("Information", Information.toUpperCase());
					keyNameObject.put("FixtureType", FixtureType.toUpperCase());
					keyNameObject.put("LCStart", LCStart.toUpperCase());
					keyNameObject.put("LCEnd", LCEnd.toUpperCase());
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
					keyNameObject.put("ReportTimestamp", ReportTimestamp);

					if (keyNameObject.length() > 0) {
						keyNameObjectJsonarray.put(keyNameObject);
					}

				} // while

				if (!nodeFlag) {
					keyNameObject = new JSONObject();
					keyNameObject.put("Error", "no Data Available Between Given Dates");
					keyNameObjectJsonarray.put(keyNameObject);

				}

				if (keyNameObjectJsonarray.length() > 0) {
					mainJsonobj = new JSONObject();
					mainJsonobj.put("spotList", keyNameObjectJsonarray);

				}

			}

		} catch (Exception e) {
			out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public JSONObject getTimeCharterReportDateWithoutDataFrame(Session session, String from, String to,
			String reportType, PrintWriter out) {

		JSONObject mainJsonobj = null;
		try {

			if (!GmailMethods.isNullString(from) && !GmailMethods.isNullString(to)
					&& !GmailMethods.isNullString(reportType)) {

				FetchData fd = new FetchData();
				String reporttypequeryid = fd.check_ReportTypeToFetchapi(session, reportType, out);
				String dataFrom = FetchData.convertStringToDate(from);
				dataFrom = dataFrom + "T00:00:00.000Z"; 
				String dataTo = FetchData.convertStringToDate(to);
				dataTo = dataTo + "T23:59:59.999Z";
				Workspace workspace = session.getWorkspace();
//				String slingqery = "select [ReportTimestamp] from [nt:base] where ReportTimestamp >='" + dataFrom
//						+ "'and ReportTimestamp <='" + dataTo + "' and ReportType=" + reporttypequeryid
//						+ " and ISDESCENDANTNODE('/scorpioDataBase/ReportData/TCReports/TimeCharterReport/')";
				String slingqery = "SELECT [ReportTimestamp] from [nt:base] WHERE ReportTimestamp >= CAST('"+dataFrom+"' AS DATE) AND ReportTimestamp <= CAST('"+dataTo+"' AS DATE) AND ReportType=" + reporttypequeryid
						+ " and ISDESCENDANTNODE('/scorpioDataBase/ReportData/TCReports/TimeCharterReport/')";
				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();

				//out.println("slingqery: "+slingqery);
				NodeIterator iterator = queryResult.getNodes();
				
				//out.println("size query:: "+queryResult.getNodes().getSize());
				boolean nodeFlag = false;

				JSONObject keyNameObject = null;
				JSONArray keyNameObjectJsonarray = new JSONArray();

				while (iterator.hasNext()) {
					//out.println("while: "+"true");
					nodeFlag = true;
					Node nextnode = iterator.nextNode();

					String Information = "";
					String Charterer = "";
					String Source = "";
					String VesselName = "";
					String Comments = "";
					String Rate = "";
					String Id = "";

					if (nextnode.hasProperty("Id")) {
						Id = nextnode.getProperty("Id").getString();
					}

					if (nextnode.hasProperty("Information")) {
						Information = nextnode.getProperty("Information").getString();
						Information = fd.fetch_Information(session, Information);

					}
					String Spot_TC = "";
					if (nextnode.hasProperty("Spot_TC")) {

						Spot_TC = nextnode.getProperty("Spot_TC").getString();
						Spot_TC = fd.fetch_ReportType(session, Spot_TC);

					}else{
						Spot_TC="TC";
					}
					String Date = "";
					if (nextnode.hasProperty("Date")) {

						Date = nextnode.getProperty("Date").getString();

					}
					String VesselName1="";
					if (nextnode.hasProperty("VesselName")) {
						VesselName1 = nextnode.getProperty("VesselName").getString();

						VesselName = fd.fetch_vesselName(session, VesselName1);

						if( !GmailMethods.isNullString(VesselName) ){
							boolean checkjsonString = SaveReportDataClass.isJSONValid(VesselName);
							if (checkjsonString == true) {
						       JSONObject fetch_vesselNamejsonobject = new JSONObject(VesselName);
						
						if (fetch_vesselNamejsonobject.has("vessel_Id")) {
							VesselName = fetch_vesselNamejsonobject.getString("vessel_Id");
						}

					}
						}else{
							VesselName=VesselName1;
						}
					}
					String CPP_DPP = "";
					String CPP_DPP1="";
					if (nextnode.hasProperty("CPP_DPP")) {

						CPP_DPP1 = nextnode.getProperty("CPP_DPP").getString();
						CPP_DPP = fd.fetch_CargoType(session, CPP_DPP1);

						if(GmailMethods.isNullString(CPP_DPP)){
							CPP_DPP=CPP_DPP1;
						}
					}

					String Charterer1="";
					if (nextnode.hasProperty("Charterer")) {
						Charterer1 = nextnode.getProperty("Charterer").getString();
						Charterer = fd.fetch_Charterer(session, Charterer1);

						if(GmailMethods.isNullString(Charterer)){
							Charterer=Charterer1;
						}
					}
					String Delivery = "";
					if (nextnode.hasProperty("Delivery")) {

						Delivery = nextnode.getProperty("Delivery").getString();

					}
					String DeliveryPlace = "";
					String DeliveryPlace1="";
					if (nextnode.hasProperty("DeliveryPlace")) {

						DeliveryPlace1 = nextnode.getProperty("DeliveryPlace").getString();
						DeliveryPlace = fd.fetch_DeliveryPlace(session, DeliveryPlace1);

						if(GmailMethods.isNullString(DeliveryPlace)){
							DeliveryPlace=DeliveryPlace1;
						}
					}
					String Period = "";
					if (nextnode.hasProperty("Period")) {

						Period = nextnode.getProperty("Period").getString();

					}
					String ReportTimestamp="";
					if (nextnode.hasProperty("ReportTimestamp")) {
						String ReportTimestamp1 = nextnode.getProperty("ReportTimestamp").getString();
						
						if(ReportTimestamp1.lastIndexOf(".")!=-1){
							ReportTimestamp1=ReportTimestamp1.substring( 0,ReportTimestamp1.lastIndexOf(".") );
//							out.println(ReportTimestamp);
							
							LocalDateTime localDateTime = LocalDateTime.parse(ReportTimestamp1);
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
							ReportTimestamp = localDateTime.format(formatter);
							if(ReportTimestamp.lastIndexOf("/")!=-1)
							{
								ReportTimestamp=ReportTimestamp.substring(ReportTimestamp.lastIndexOf("/")+1);
//								System.out.println(ReportTimestamp);
							}
						}
						
					}
					String Periodunit = "";
					String Periodunit1 = "";
					if (nextnode.hasProperty("Periodunit")) {

						Periodunit1 = nextnode.getProperty("Periodunit").getString();
						Periodunit = fd.fetch_Periodunit(session, Periodunit1);

						if(GmailMethods.isNullString(DeliveryPlace)){
							Periodunit=Periodunit1;
						}
					}
					String Redelivery1 = "";
					if (nextnode.hasProperty("Redelivery")) {

						Redelivery1 = nextnode.getProperty("Redelivery").getString();

					}
					
					String Redelivery="";
					if( !GmailMethods.isNullString(Redelivery1) ){
						  Redelivery=Redelivery1;
					}else{
						String redeliveryData="";
						if( !GmailMethods.isNullString(Delivery) && !GmailMethods.isNullString(Period) ){
							redeliveryData=FifteenMinuteClass.deliveryDate(Delivery, ReportTimestamp, Period, Periodunit, out);
							Redelivery=redeliveryData;
						}
					}
					
					String TCRate = "";
					if (nextnode.hasProperty("TCRate")) {

						TCRate = nextnode.getProperty("TCRate").getString();

					}

					if (nextnode.hasProperty("Rate")) {
						Rate = nextnode.getProperty("Rate").getString();

					}
					String Unit = "";
					String Unit1 = "";
					if (nextnode.hasProperty("Unit")) {

						Unit1 = nextnode.getProperty("Unit").getString();
						Unit = fd.fetch_currencyUnit(session, Unit1);

						if(GmailMethods.isNullString(Unit)){
							Unit=Unit1;
						}
					}

					if (nextnode.hasProperty("Comments")) {
						Comments = nextnode.getProperty("Comments").getString();

					}

					/*if (nextnode.hasProperty("Source")) {
						Source = nextnode.getProperty("Source").getString();
						Source = fd.fetch_Source(session, Source);

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
					
					String Vesseltype = "";
					String Vesseltype1 = "";
					if (nextnode.hasProperty("Vesseltype")) {

						Vesseltype1 = nextnode.getProperty("Vesseltype").getString();
						Vesseltype = FetchData.fetch_vesselType(session, Vesseltype1);
						if(GmailMethods.isNullString(Vesseltype)){
							Vesseltype=Vesseltype1;
						}

					}
					String TimeStamp = "";
					if (nextnode.hasProperty("TimeStamp")) {

						TimeStamp = nextnode.getProperty("TimeStamp").getString();

					}

					keyNameObject = new JSONObject();

					keyNameObject.put("TimeCharterReportsId", Id);
					keyNameObject.put("Information", Information.toUpperCase());
					keyNameObject.put("Spot_TC", Spot_TC.toUpperCase());
					keyNameObject.put("Date", Date);
					keyNameObject.put("VesselName", VesselName.toUpperCase());
					keyNameObject.put("CPP_DPP", CPP_DPP.toUpperCase());
					keyNameObject.put("Charterer", Charterer.toUpperCase());
					keyNameObject.put("Delivery", Delivery.toUpperCase());
					keyNameObject.put("DeliveryPlace", DeliveryPlace.toUpperCase());
					keyNameObject.put("Period", Period.toUpperCase());
					keyNameObject.put("Periodunit", Periodunit.toUpperCase());
					keyNameObject.put("Redelivery", Redelivery.toUpperCase());
					keyNameObject.put("TCRate", TCRate.toUpperCase());
					keyNameObject.put("Rate", Rate.toUpperCase());
					keyNameObject.put("Unit", Unit.toUpperCase());
					keyNameObject.put("Comments", Comments.toUpperCase());
					keyNameObject.put("Source", Source.toUpperCase());
					keyNameObject.put("Vesseltype", Vesseltype.toUpperCase());
					keyNameObject.put("TimeStamp", TimeStamp); //TimeStamp

					if (keyNameObject.length() > 0) {
						keyNameObjectJsonarray.put(keyNameObject);
					}

				} // while

				if (!nodeFlag) {
					keyNameObject = new JSONObject();
					keyNameObject.put("Error", "no Data Available Between Given Dates");
					keyNameObjectJsonarray.put(keyNameObject);

				}

				if (keyNameObjectJsonarray.length() > 0) {
					mainJsonobj = new JSONObject();
					mainJsonobj.put("TimeCharterReportsList", keyNameObjectJsonarray);

				}

			}

		} catch (Exception e) {
			out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public JSONObject getBrokerTcRateReportDateWithoutDataFrame(Session session, String from, String to,
			String reportType, PrintWriter out) {

		JSONObject mainJsonobj = null;
		try {

			if (!GmailMethods.isNullString(from) && !GmailMethods.isNullString(to)
					&& !GmailMethods.isNullString(reportType)) {

				FetchData fd = new FetchData();
				String reporttypequeryid = fd.check_ReportTypeToFetchapi(session, reportType, out);
				String dataFrom = FetchData.convertStringToDate(from);
				dataFrom = dataFrom + "T00:00:00.000Z"; 
				String dataTo = FetchData.convertStringToDate(to);
				dataTo = dataTo + "T23:59:59.999Z";
				Workspace workspace = session.getWorkspace();

//				String slingqery = "select [ReportTimestamp] from [nt:base] where ReportTimestamp >='" + dataFrom
//						+ "'and ReportTimestamp <='" + dataTo + "' and ReportType=" + reporttypequeryid
//						+ " and ISDESCENDANTNODE('/scorpioDataBase/ReportData/TCReports/BrokerTCRate/')";
				String slingqery = "SELECT [ReportTimestamp] from [nt:base] WHERE ReportTimestamp >= CAST('"+dataFrom+"' AS DATE) AND ReportTimestamp <= CAST('"+dataTo+"' AS DATE) AND ReportType=" + reporttypequeryid
						+ " and ISDESCENDANTNODE('/scorpioDataBase/ReportData/TCReports/BrokerTCRate/')";
				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();

				NodeIterator iterator = queryResult.getNodes();
				boolean nodeFlag = false;

				JSONObject keyNameObject = null;
				JSONArray keyNameObjectJsonarray = new JSONArray();

				while (iterator.hasNext()) {

					nodeFlag = true;
					Node nextnode = iterator.nextNode();

					String Id = "";

					if (nextnode.hasProperty("Id")) {

						Id = nextnode.getProperty("Id").getString();
					}
					String Broker = "";
					/*if (nextnode.hasProperty("Broker")) {

						Broker = nextnode.getProperty("Broker").getString();
						Broker = fd.fetch_Source(session, Broker);

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
					
					String VesselType = "";
					String VesselType1 = "";
					if (nextnode.hasProperty("VesselType")) {

						VesselType1 = nextnode.getProperty("VesselType").getString();
						VesselType = FetchData.fetch_vesselType(session, VesselType1);

						if(GmailMethods.isNullString(VesselType)){
							VesselType=VesselType1;
						}else{
							VesselType="";
						}
						
					}
					String Month_Year = "";
					if (nextnode.hasProperty("Month_Year")) {

						Month_Year = nextnode.getProperty("Month_Year").getString();

					}
					String first_yr = "";
					if (nextnode.hasProperty("first_yr")) {

						first_yr = nextnode.getProperty("first_yr").getString();

					}

					String second_yr = "";
					if (nextnode.hasProperty("second_yr")) {

						second_yr = nextnode.getProperty("second_yr").getString();

					}
					String third_yr = "";
					if (nextnode.hasProperty("third_yr")) {

						third_yr = nextnode.getProperty("third_yr").getString();

					}
					String fifth_yr = "";
					if (nextnode.hasProperty("fifth_yr")) {

						fifth_yr = nextnode.getProperty("fifth_yr").getString();

					}

					keyNameObject = new JSONObject();

					keyNameObject.put("BrokerTcRateId", Id);
					keyNameObject.put("Broker", Broker.toUpperCase());
					keyNameObject.put("VesselType", VesselType.toUpperCase());
					keyNameObject.put("Month_Year", Month_Year.toUpperCase());
					keyNameObject.put("first_yr", first_yr);
					keyNameObject.put("second_yr", second_yr);
					keyNameObject.put("third_yr", third_yr);
					keyNameObject.put("fifth_yr", fifth_yr);

					if (keyNameObject.length() > 0) {
						keyNameObjectJsonarray.put(keyNameObject);
					}

				} // while

				if (!nodeFlag) {
					keyNameObject = new JSONObject();
					keyNameObject.put("Error", "no Data Available Between Given Dates");
					keyNameObjectJsonarray.put(keyNameObject);

				}

				if (keyNameObjectJsonarray.length() > 0) {
					mainJsonobj = new JSONObject();
					mainJsonobj.put("BrokerTcRateList", keyNameObjectJsonarray);

				}

			}

		} catch (Exception e) {
//			out.println(e.getMessage());
			e.printStackTrace(out);

		}
		return mainJsonobj;

	}
	
	public static  String convertStringDayAndMonth(String dateString , PrintWriter out)
	{
	   String DateTwoDigit="";

	    try{
	    	
	    	// date and month =currentYear
	    	
	    	Calendar cal = null;
			cal = Calendar.getInstance();
			Date minDate = new Date(cal.getTimeInMillis());
		    Date date=GmailMethods.parseDate(minDate.toString());
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
	    	
			
			
	    	 if (dateString.matches("([0-9]{2})-([0-9]{2})")) {   //   //dd-MM
	    		 DateTwoDigit= dateString+"-"+String.valueOf(year);  
	    		 
	    	}
	    	 if (dateString.matches("([0-9]{2})/([0-9]{2})")) {   //   //dd/MM
	    		 DateTwoDigit= dateString+"-"+String.valueOf(year);  
	    		 
	    	}
	    	  else if (dateString.matches("([0-9]{1})-([0-9]{1})")) {  //d-M
	    		  DateTwoDigit= dateString+"-"+String.valueOf(year);
	    		
	    	}
	    	  else if (dateString.matches("([0-9]{1})/([0-9]{1})")) {  //d/M
	    		  DateTwoDigit= dateString+"/"+String.valueOf(year);
		    		
	    	}
	    	else if (dateString.matches("([0-9]{1})-([0-9]{2})")) { //d-MM
	    		 DateTwoDigit= dateString+"-"+String.valueOf(year);
	    	}
	    	else if (dateString.matches("([0-9]{1})/([0-9]{2})")) { //d/MM
	    		 DateTwoDigit= dateString+"/"+String.valueOf(year);
	    	}
	    	else if (dateString.matches("([0-9]{1})-([0-9]{1})")) {  //d-M
	    		 DateTwoDigit= dateString+"-"+String.valueOf(year);
	    	}
	    	else if (dateString.matches("([0-9]{1})/([0-9]{1})")) {  //d/M
	    		 DateTwoDigit= dateString+"/"+String.valueOf(year);
	    	}
	    	else if (dateString.matches("([0-9]{2})-([0-9]{1})")) {  //dd-M
	    		 DateTwoDigit= dateString+"-"+String.valueOf(year);
	    		
	    	}
	    	else if (dateString.matches("([0-9]{2})/([0-9]{1})")) {  //dd/M
	    		 DateTwoDigit= dateString+"/"+String.valueOf(year);
	    		
	    	}
	    	else if (dateString.matches("([0-9]{2})-([0-9]{2})")) {  // dd-MM
	    		 DateTwoDigit= dateString+"-"+String.valueOf(year);
        	    
	    	}else if (dateString.matches("([0-9]{2})/([0-9]{2})")) {  // dd/MM
	    		 DateTwoDigit= dateString+"/"+String.valueOf(year);
	        	    
	    	}
	    	else if (dateString.matches("([0-9]{1})-([0-9]{2})")) {   //d-MM
	    		 DateTwoDigit= dateString+"-"+String.valueOf(year);
	    		
	    	}
	    	else if (dateString.matches("([0-9]{1})/([0-9]{2})")) {   //d/MM
	    		 DateTwoDigit= dateString+"/"+String.valueOf(year);
	    		
	    	}
	    	 
	    	 // month and year =30
      
	    	 if (dateString.matches("([0-9]{2})-([0-9]{4})")) {   //   //MM-yyyy
	    		 DateTwoDigit= "30"+"-"+dateString;
		    	}
	    	 if (dateString.matches("([0-9]{2})/([0-9]{4})")) {   //   //MM/yyyy
	    		 DateTwoDigit= "30"+"/"+dateString;
		    	}
	    	 else if (dateString.matches("([0-9]{1})-([0-9]{4})")) {  //M-yyyy
		    		DateTwoDigit= "30"+"-"+dateString;
		    	}
	    	 else if (dateString.matches("([0-9]{1})/([0-9]{4})")) {  //M/yyyy
		    		DateTwoDigit= "30"+"/"+dateString;
		    	}
        
       
    }
	    catch ( Exception ex ){
	    	ex.getMessage();
	    }
	    return DateTwoDigit;
	}
	

}
