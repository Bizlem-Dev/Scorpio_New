package com.reportinformationsystem;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.readGmail.GmailMethods;

import ChangedStructureCurrent.FetchData;

public class FourReportsExcelClass {

	public static void main(String[] args) {
		
		String data=convertStringToDate("12-01-2019");
		System.out.println(data);
		
		String s="27-01-2019";
		int d=s.lastIndexOf("-");
		s=s.substring(d+1);
		System.out.println(s);
		
		int intDWT1=Integer.parseInt("167");
		int f=intDWT1*1000;
		
		System.out.println(String.valueOf(f));
		
		
		LocalDateTime localDateTime = LocalDateTime.parse("2018-12-19T13:55:35");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
		String ReportTimestamp = localDateTime.format(formatter);
		System.out.println(ReportTimestamp);
		if(ReportTimestamp.lastIndexOf("/")!=-1)
		{
			ReportTimestamp=ReportTimestamp.substring(ReportTimestamp.lastIndexOf("/")+1);
			System.out.println(ReportTimestamp);
		}

	}
	
	public static  String convertStringToDate(String dateString)
	{
	    Date date = null;
	    String formatteddate = null;
	    DateFormat df = null;
//	    DateFormat dfOut = new SimpleDateFormat("dd-MM-yyyy"); //E MMM dd HH:mm:ss Z yyyy
//	    DateFormat dfOut = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");//, Locale.ENGLISH
	    DateFormat dfOut = new SimpleDateFormat("dd/MM/yyyy"); //E MMM dd HH:mm:ss Z yyyy   //DD/MM/YYYY  yyyy-MM-dd
	    try{
	    	if (dateString.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
	    		df = new SimpleDateFormat("dd/MM/yyyy");
	    		date = df.parse(dateString);
	    	
        }
	    	else if (dateString.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")) {
	    		df = new SimpleDateFormat("dd-MM-yyyy");
	    		date = df.parse(dateString);
	    	}
	    	else if (dateString.matches("([0-9]{4})([0-9]{2})([0-9]{2})")) {
	    		df = new SimpleDateFormat("yyyyMMdd");
	    		date = df.parse(dateString);
	    	} else if (dateString.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")) {
	    		df = new SimpleDateFormat("yyyy-MM-dd");
	    		date = df.parse(dateString);
	    	} else if (dateString.matches("([0-9]{4})/([0-9]{2})/([0-9]{2})")) {
	    		df = new SimpleDateFormat("yyyy/MM/dd");
	    		date = df.parse(dateString);
	    	}else if (dateString.matches("([0-9]{1})/([0-9]{1})/([0-9]{2})")) {
	    		df = new SimpleDateFormat("d/M/yy");
	    		date = df.parse(dateString);
	    	}else if (dateString.matches("([0-9]{1})-([0-9]{1})-([0-9]{2})")) {
	    		df = new SimpleDateFormat("d-M-yy");
	    		date = df.parse(dateString);
	    	}else if (dateString.matches("([0-9]{2})/([0-9]{1})/([0-9]{1})")) {
	    		df = new SimpleDateFormat("yy/M/d");
	    		date = df.parse(dateString);
	    	}else if (dateString.matches("([0-9]{2})-([0-9]{1})-([0-9]{1})")) {
	    		df = new SimpleDateFormat("yy-M-d");
	    		date = df.parse(dateString);
	    	}else if (dateString.matches("([0-9]{1})/([0-9]{2})/([0-9]{4})")) {
	    		df = new SimpleDateFormat("d/MM/yyyy");
	    		date = df.parse(dateString);
	    	}
	    	else if (dateString.matches("([0-9]{1})/([0-9]{1})/([0-9]{4})")) {
	    		df = new SimpleDateFormat("d/M/yyyy");
	    		date = df.parse(dateString);
	    	}
	    	else if (dateString.matches("([0-9]{2})/([0-9]{1})/([0-9]{2})")) {
	    		df = new SimpleDateFormat("dd/M/yy");
	    		date = df.parse(dateString);
	    	}
	    	else if (dateString.matches("([0-9]{2})/([0-9]{1})/([0-9]{4})")) {
	    		df = new SimpleDateFormat("dd/M/yyyy");
	    		date = df.parse(dateString);
	    	}else if (dateString.matches("([0-9]{2})/([0-9]{2})/([0-9]{2})")) {
	    		df = new SimpleDateFormat("dd/MM/yy");
	    		date = df.parse(dateString);
	    	}else if (dateString.matches("([0-9]{1})/([0-9]{2})/([0-9]{2})")) {
        	df = new SimpleDateFormat("d/MM/yy");
        	date = df.parse(dateString);
	    	}else if (dateString.matches("([0-9]{1})-([0-9]{2})-([0-9]{4})")) {
	    		df = new SimpleDateFormat("d-MM-yyyy");
	    		date = df.parse(dateString);
	    	}
	    	else if (dateString.matches("([0-9]{1})-([0-9]{1})-([0-9]{4})")) {
	    		df = new SimpleDateFormat("d-M-yyyy");
	    		date = df.parse(dateString);
	    	}
	    	else if (dateString.matches("([0-9]{2})-([0-9]{1})-([0-9]{4})")) {
	    		df = new SimpleDateFormat("dd-M-yyyy");
	    		date = df.parse(dateString);
	    	}else if (dateString.matches("([0-9]{2})-([0-9]{2})-([0-9]{2})")) {
        	df = new SimpleDateFormat("dd-MM-yy");
        	date = df.parse(dateString);
	    	}else if (dateString.matches("([0-9]{1})-([0-9]{2})-([0-9]{2})")) {
	    		df = new SimpleDateFormat("d-MM-yy");
	    		date = df.parse(dateString);
	    	}
      
	    	
        
        formatteddate = dfOut.format(date);
    }
	    catch ( Exception ex ){
	    	ex.getMessage();
	    }
	    return formatteddate;
	}
	
	
	public static JSONObject fetchTonnageForExcel(PrintWriter out, Session session) {

		JSONObject mainJsonobj = null;
		try {
			FetchData fd = new FetchData();
			Node scorpio=null;
			Node ReportData=null;
			Node Tonnage=null;
			
			if(session.getRootNode().hasNode("scorpioDataBase")){
				scorpio=session.getRootNode().getNode("scorpioDataBase");
			}
			if(scorpio.hasNode("ReportData")){
				ReportData=scorpio.getNode("ReportData");
			}
			if(ReportData.hasNode("Tonnage")){
				Tonnage=ReportData.getNode("Tonnage");
			}
			
			if (Tonnage.hasNodes()) {
				long size = Tonnage.getNodes().getSize();
				JSONObject keyNameObject = null;

				JSONArray keyNameObjectJsonarray = new JSONArray();

				NodeIterator itr = Tonnage.getNodes();
				
				int i = 0;
				
				while (itr.hasNext()) {
					Node nextnode= itr.nextNode();
					
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
					String ReportTimestamp = "";
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
					/*if (nextnode.hasProperty("ETABasis")) {
						String ETABasis1 = nextnode.getProperty("ETABasis").getString();
						
						 
						
                          boolean booleanOpenDate = Pattern.matches("(\\d{2}\\-\\d{2}\\-\\d{4})", ETABasis1);
                          
						
						if(booleanOpenDate==true){
							ETABasis=FourReportsExcelClass.convertStringToDate(ETABasis1);
							
						}else{
							
							String EtaCheckDataTwoDigit=ChangedApiWithoutMultipleArray.convertStringDayAndMonth(ETABasis1, out);
	                         
	                         if( !GmailMethods.isNullString(EtaCheckDataTwoDigit) ){
	                        	 ETABasis= EtaCheckDataTwoDigit;
	                         }
							//........
	                         else if( ETABasis1.contains("PPT") || ETABasis1.contains("PPT - exten") ){
							
							LocalDateTime localDateTime = LocalDateTime.parse(ReportTimestamp1);
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
							ETABasis = localDateTime.format(formatter);
							
						}else{
							
							
							String data=FifteenMinuteClass.checkSpaceThree(ETABasis1, year);
							if( !GmailMethods.isNullString(data) ){
								ETABasis=data;
							}else{
								String data1=FifteenMinuteClass.twoDate(ETABasis1, year);
								if( !GmailMethods.isNullString(data1) ){
									ETABasis=data1;
								}else{
									String data2=FifteenMinuteClass.twoDateSlash(ETABasis1, year);
									if( !GmailMethods.isNullString(data2) ){
										ETABasis=data2;
									}else{
										if(ETABasis1.equalsIgnoreCase("null")){
											ETABasis=ReportTimestamp2;
										}else{
										
											String data3=FifteenMinuteClass.DateSpach(ETABasis1, year);
											if(!GmailMethods.isNullString(data3)){
												ETABasis=data3;
											}
										else{
											String data4=FifteenMinuteClass.dateDot(ETABasis1, year, out);
											if(!GmailMethods.isNullString(data4)){
										        ETABasis=data4;
											}else{
												String data5=FifteenMinuteClass.datedashSingle(ETABasis1, year);
												if(!GmailMethods.isNullString(data5)){
													ETABasis=data5;
												}else{
													
													String data6=FifteenMinuteClass.dateSpaceSingle(ETABasis1, year);
													if(!GmailMethods.isNullString(data6)){
														ETABasis=data6;
													}else{
													   ETABasis=ETABasis1;
													}
												}
											}
										}
											
										}
									}
								}
							}
							
							ETABasis=ETABasis1;
						}
						// ......
						
						}
					}*/
					
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
					
					/*if (nextnode.hasProperty("OpenDate")) {
						String OpenDate1 = nextnode.getProperty("OpenDate").getString();
						
						boolean booleanOpenDate = Pattern.matches("(\\d{2}\\-\\d{2}\\-\\d{4})", OpenDate1);
						
						if(booleanOpenDate==true){
							 OpenDate=FourReportsExcelClass.convertStringToDate(OpenDate1);
							
						}else {
							if( OpenDate1.equalsIgnoreCase("PPT") || OpenDate1.equalsIgnoreCase("PPT - exten") ){
								
								LocalDateTime localDateTime = LocalDateTime.parse(ReportTimestamp1);
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
								OpenDate = localDateTime.format(formatter);
								
							}else{
								
								String data=FifteenMinuteClass.checkSpaceThree(OpenDate1, year);
								if( !GmailMethods.isNullString(data) ){
									OpenDate=data;
								}else{
									String data1=FifteenMinuteClass.twoDate(OpenDate1, year);
									if( !GmailMethods.isNullString(data1) ){
										OpenDate=data1;
									}else{
										String data2=FifteenMinuteClass.twoDateSlash(OpenDate1, year);
										if( !GmailMethods.isNullString(data2) ){
											OpenDate=data2;
										}else{
											
											if(OpenDate1.equalsIgnoreCase("null")){
												OpenDate=ReportTimestamp2;
											}else{
											
												String data3=FifteenMinuteClass.DateSpach(OpenDate1, year);
												if(!GmailMethods.isNullString(data3)){
													OpenDate=data3;
												}
											
												else{
													String data4=FifteenMinuteClass.dateDot(OpenDate1, year, out);
													if(!GmailMethods.isNullString(data4)){
														OpenDate=data4;
													}else{
														String data5=FifteenMinuteClass.datedashSingle(OpenDate1, year);
														if(!GmailMethods.isNullString(data5)){
															OpenDate=data5;
														}else{
															
															String data6=FifteenMinuteClass.dateSpaceSingle(OpenDate1, year);
															if(!GmailMethods.isNullString(data6)){
																OpenDate=data6;
															}else{
																OpenDate=OpenDate1;
															}
														}
													}
												}
											
												
											}
											
										}
									}
								}
								
								
								
							}// else 
						}

					}*/
					
					if (nextnode.hasProperty("OpenDate")) {
						 OpenDate= nextnode.getProperty("OpenDate").getString();
						//OpenDate=CD.convertDateFormat(OpenDate1);
						
						/*if( OpenDate.contains("PPT") || OpenDate.contains("PPT - exten") ){
							
						     LocalDateTime localDateTime = LocalDateTime.parse(ReportTimestamp1);
						     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
						     OpenDate = localDateTime.format(formatter);
						//out.println("ETABasis_ppt: "+ETABasis);
						
					}*/
						//nextnode.setProperty("OpenDate", OpenDate);
						//session.save();

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

					keyNameObject.put("tonnageId", Id);
					keyNameObject.put("dateAndTime", dateAndTime);
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
					keyNameObject.put("subNodeName", subNodeName);
					keyNameObject.put("size", size + 1);// parseId
					keyNameObject.put("parseId", parseId + 1);
					keyNameObject.put("i", i);
					keyNameObject.put("Sr_No", Sr_No);
					keyNameObject.put("emailUrl", emailUrl);
					keyNameObject.put("emailClientNodeId", emailClientNodeId);
					keyNameObject.put("vesselFlag", vesselFlag);
					keyNameObject.put("flagOpenPort", flagPort);
					
                    // boolean datFormate=AuthHelper.etaBasisCheckDAteformate(OpenDate, out, ReportTimestamp1, year);
					
//					if(datFormate==true){

					if (keyNameObject.length() > 0) {
						keyNameObjectJsonarray.put(keyNameObject);
					}
					
//				}
					
			} // while close
 

					mainJsonobj = new JSONObject();
					mainJsonobj.put("tonnageList", keyNameObjectJsonarray);

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

					}


			} // node check close if

		} catch (Exception e) {
			e.printStackTrace(out);
//			out.println(e.getMessage());

		}
		return mainJsonobj;

	}
	
	
	public static JSONObject fetchSpotDataChanged(PrintWriter out, Session session) {

		JSONObject mainJsonobj = null;
		try {

			FetchData fd = new FetchData();
			Node scorpio=null;
			Node ReportData=null;
			Node Spot=null;
			
			if(session.getRootNode().hasNode("scorpioDataBase")){
				scorpio = session.getRootNode().getNode("scorpioDataBase");
			}
			if(scorpio.hasNode("ReportData")){
				ReportData = scorpio.getNode("ReportData");
			}
			if(ReportData.hasNode("Spot")){
				Spot=ReportData.getNode("Spot");
			}

			if (Spot.hasNodes()) {
				long size = Spot.getNodes().getSize();
				
				String Id = "";

				JSONObject keyNameObject = null;
				JSONArray keyNameObjectJsonarray = new JSONArray();

				NodeIterator itr = Spot.getNodes();
				int i = 0;

				while (itr.hasNext()) {
					Node nextnode=itr.nextNode();

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
					
//                     String DiscPortData="";
					
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
						  
						 // out.println("vesselname: "+VesselName);
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


				} // while close 
	

					mainJsonobj = new JSONObject();
					mainJsonobj.put("spotList", keyNameObjectJsonarray);

					Node ExcelSummaryRecord = null;
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

				


			} // node check close if

		} catch (Exception e) {
			out.println(e.getMessage());

		}
		return mainJsonobj;

	}
	
	
	public static JSONObject fetchTimeCharterReportsDataChanged(PrintWriter out, Session session) {

		JSONObject mainJsonobj = null;
		try {
			FetchData fd = new FetchData();
			Node scorpio=null;
			Node ReportData=null;
			Node TCReports=null;
			Node TimeCharterReports=null;
			
			if(session.getRootNode().hasNode("scorpioDataBase")){
				scorpio = session.getRootNode().getNode("scorpioDataBase");
			}
			if(scorpio.hasNode("ReportData")){
				ReportData = scorpio.getNode("ReportData");
			}
			if(ReportData.hasNode("TCReports")){
				TCReports = ReportData.getNode("TCReports");
			}
			if(TCReports.hasNode("TimeCharterReport")){
				TimeCharterReports=TCReports.getNode("TimeCharterReport");
			}

			if (TimeCharterReports.hasNodes()) {
				long size = TimeCharterReports.getNodes().getSize();
				String Id = "";

				JSONObject keyNameObject = null;
				JSONArray keyNameObjectJsonarray = new JSONArray();

				NodeIterator itr = TimeCharterReports.getNodes();
				int i = 0;

				while (itr.hasNext()) {
					Node nextnode=itr.nextNode();
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
					keyNameObject.put("Date", Date);
					keyNameObject.put("VesselName", VesselName.toUpperCase());
					keyNameObject.put("CPP_DPP", CPP_DPP.toUpperCase());
					keyNameObject.put("Charterer", Charterer.toUpperCase());
					keyNameObject.put("Delivery", Delivery);
					keyNameObject.put("DeliveryPlace", DeliveryPlace.toUpperCase());
					keyNameObject.put("Period", Period);
					keyNameObject.put("Periodunit", Periodunit.toUpperCase());
					keyNameObject.put("Redelivery", Redelivery);
					keyNameObject.put("TCRate", TCRate);
					keyNameObject.put("Rate", Rate);
					keyNameObject.put("Unit", Unit.toUpperCase());
					keyNameObject.put("Comments", Comments.toUpperCase());
					keyNameObject.put("Source", Source.toUpperCase());
					keyNameObject.put("Vesseltype", Vesseltype.toUpperCase());
					keyNameObject.put("TimeStamp", TimeStamp); //TimeStamp
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

				} // while close
			
 
					mainJsonobj = new JSONObject();
					mainJsonobj.put("TimeCharterReportsList", keyNameObjectJsonarray);

					Node ExcelSummaryRecord = null;
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

					}


			} // node check close if

		} catch (Exception e) {
			e.printStackTrace(out);
			out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	
	public static JSONObject fetchBrokerTcRateDataChanged(PrintWriter out, Session session) {

		JSONObject mainJsonobj = null;
		try {
			FetchData fd = new FetchData();
			Node scorpio=null;
			Node ReportData=null;
			Node TCReports=null;
			Node TimeCharterReports=null;
			
			if(session.getRootNode().hasNode("scorpioDataBase")){
				scorpio=session.getRootNode().getNode("scorpioDataBase");
			}
			if(scorpio.hasNode("ReportData")){
				ReportData = scorpio.getNode("ReportData");
			}
			if(ReportData.hasNode("TCReports")){
				TCReports = ReportData.getNode("TCReports");
			}
			if(TCReports.hasNode("BrokerTCRate")){
				TimeCharterReports = TCReports.getNode("BrokerTCRate");
			}

			if (TimeCharterReports.hasNodes()) {

				JSONObject keyNameObject = null;
				long size = TimeCharterReports.getNodes().getSize();
				JSONArray keyNameObjectJsonarray = new JSONArray();

				NodeIterator itr = TimeCharterReports.getNodes();
				int i = 0;
				
				while (itr.hasNext()) {
					 Node nextnode=itr.nextNode();
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
					keyNameObject.put("Month_Year", Month_Year);
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

				} // while close
			  
					mainJsonobj = new JSONObject();
					mainJsonobj.put("BrokerTcRateList", keyNameObjectJsonarray);

					Node ExcelSummaryRecord = null;
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

					}

				
			} // node check close if

		} catch (Exception e) {
			out.println(e.getMessage());

		}
		return mainJsonobj;

	}
	

}
