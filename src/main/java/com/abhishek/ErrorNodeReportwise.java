package com.abhishek;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.sling.commons.json.JSONObject;

import com.readGmail.GmailMethods;
import com.readGmail.SlingMethods;

import ChangedStructureCurrent.ChangedStructureCurrent_Methods;
import ChangedStructureCurrent.FetchData;

public class ErrorNodeReportwise {

	public static void main(String[] args) {
	}
	
	public static void callAllDynamicReport(PrintWriter out, Session session, String ReportType, 
			String emailUrl,String subjectNodePath,String textSentMailTime, String from_Source, String reportcomesFrom,String allReportJsonArrayInsideJSonobject){
		
		if(!GmailMethods.isNullString(ReportType)){
			
		
		FetchData CSC=new FetchData();
		ReportType=CSC.fetch_ReportType(session, ReportType);
		try {
		
				Node scorpio=null;
				Node Tonnage=null;
				Node ReportData=null;
				Node subNode=null;
				Node Other=null;
				Node ReportErrorNode=null;

				if(session.getRootNode().hasNode("scorpioDataBase")){
					scorpio = session.getRootNode().getNode("scorpioDataBase");
				}else{
					scorpio = session.getRootNode().addNode("scorpioDataBase");
					session.refresh(true);
					session.save();
				}

				if(scorpio.hasNode("ReportData")){
					ReportData = scorpio.getNode("ReportData");
				}else{
					ReportData = scorpio.addNode("ReportData");
					session.refresh(true);
					session.save();
				}

				if(ReportData.hasNode("Other")){
					Other=ReportData.getNode("Other");
				}else{
					Other=ReportData.addNode("Other");
					session.refresh(true);
					session.save();
				}
				if(Other.hasNode("ReportErrorNode")){
					ReportErrorNode = Other.getNode("ReportErrorNode");
				}else{
					ReportErrorNode = Other.addNode("ReportErrorNode");
					session.refresh(true);
					session.save();
				}if(ReportErrorNode.hasNode(ReportType)){
					Tonnage = ReportErrorNode.getNode(ReportType);
				}else{
					Tonnage = ReportErrorNode.addNode(ReportType);
					Tonnage.setProperty("jcr:count", String.valueOf(0));
					session.refresh(true);
					session.save();
				}

				Calendar c = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

				if( !GmailMethods.isNullString(textSentMailTime) ){
					c.setTime(sdf.parse(textSentMailTime));
				}

				String count=Tonnage.getProperty("jcr:count").getString();
				String forid= String.valueOf(Integer.parseInt(count)+1);
				if(!Tonnage.hasNode(forid)){
					subNode=Tonnage.addNode(forid);
					subNode.setProperty("Sr.No", forid);
					subNode.setProperty("emailUrl", emailUrl);
					subNode.setProperty("ReportType",ReportType);
					subNode.setProperty("ReportTimestamp", c);
					subNode.setProperty("emailClientNodeId", subjectNodePath);
					subNode.setProperty("textSentMailTime", textSentMailTime);
					subNode.setProperty("reportcomesFrom", reportcomesFrom); //
					subNode.setProperty("SingleJson",  allReportJsonArrayInsideJSonobject);
					subNode.setProperty("from_Source", from_Source);

					Tonnage.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
					session.refresh(true);
					session.save();

			}
		

		} catch (Exception e) {
			
		}
		}
	}
	
	public static void callTonnageError(PrintWriter out, Session session, String ReportType, String RepositionRegion, String CargoType
			,String VesselName, String VesselType,String Built,String DWT,String Cubics,String LOA,String ICE,String SternLine,String Owners
			,String Operators,String EmploymentStatus,String OpenPort,String OpenDate,String ETABasis,String Comment,String Source,String ReportTimestamp, String emailUrl,String subjectNodePath,String textSentMailTime, String from_Source, String timestampDate,String timestampDateAndTime, String Flag, String flagPort, String flagPortOpen1, String reportcomesFrom, String data, String allReportJsonArrayInsideJSonobject){

		try {
			

				Node scorpio=null;
				Node Tonnage=null;
				Node ReportData=null;
				Node subNode=null;
				Node Other=null;
				Node ReportErrorNode=null;

				if(session.getRootNode().hasNode("scorpioDataBase")){
					scorpio = session.getRootNode().getNode("scorpioDataBase");
				}else{
					scorpio = session.getRootNode().addNode("scorpioDataBase");
					session.refresh(true);
					session.save();
				}

				if(scorpio.hasNode("ReportData")){
					ReportData = scorpio.getNode("ReportData");
				}else{
					ReportData = scorpio.addNode("ReportData");
					session.refresh(true);
					session.save();
				}

				if(ReportData.hasNode("Other")){
					Other=ReportData.getNode("Other");
				}else{
					Other=ReportData.addNode("Other");
					session.refresh(true);
					session.save();
				}
				if(Other.hasNode("ReportErrorNode")){
					ReportErrorNode = Other.getNode("ReportErrorNode");
				}else{
					ReportErrorNode = Other.addNode("ReportErrorNode");
					session.refresh(true);
					session.save();
				}if(ReportErrorNode.hasNode("Tonnage")){
					Tonnage = ReportErrorNode.getNode("Tonnage");
				}else{
					Tonnage = ReportErrorNode.addNode("Tonnage");
					Tonnage.setProperty("jcr:count", String.valueOf(0));
					session.refresh(true);
					session.save();
				}

				Calendar cal = null;
				cal = Calendar.getInstance();

				Date minDate = new Date(cal.getTimeInMillis());  // get today date

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

				String combinedatetimestamp=s+"-"+String.valueOf(month)+"-"+String.valueOf(year);
				Calendar c = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

				if( !GmailMethods.isNullString(textSentMailTime) ){
					c.setTime(sdf.parse(textSentMailTime));
				}

				String count=Tonnage.getProperty("jcr:count").getString();
				String forid= String.valueOf(Integer.parseInt(count)+1);
				if(!Tonnage.hasNode(forid)){
					subNode=Tonnage.addNode(forid);

					subNode.setProperty("Sr.No", forid);
					subNode.setProperty("Id", forid);
					subNode.setProperty("dateAndTime", combinedatetimestamp);
					subNode.setProperty("emailUrl", emailUrl);
					subNode.setProperty("ReportType",ReportType);
					out.println(" inside tonnage report rg:: "+RepositionRegion);
					subNode.setProperty("RepositionRegion",RepositionRegion);
					out.println(" inside tonnage report CT:: "+CargoType);
					subNode.setProperty("CargoType",CargoType);
					subNode.setProperty("VesselName", VesselName);
					out.println(" inside tonnage report VT:: "+VesselType);
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
					if(!GmailMethods.isNullString(OpenDate)){
						OpenDate=new ConvertDate().convertDateFormat(OpenDate);
					}else{
						OpenDate="";
					}
					subNode.setProperty("OpenDate", OpenDate);

					if(!GmailMethods.isNullString(ETABasis)){
						ETABasis=new ConvertDate().convertDateFormat(ETABasis);
					}else{
						ETABasis="";
					}
					subNode.setProperty("ETABasis", ETABasis);

					subNode.setProperty("Comment", Comment);
					subNode.setProperty("Source", Source);
					subNode.setProperty("ReportTimestamp", c);
					subNode.setProperty("emailClientNodeId", subjectNodePath);
					subNode.setProperty("textSentMailTime", textSentMailTime);
					subNode.setProperty("vesselFlag", Flag); 
					subNode.setProperty("flagOpenPort", flagPortOpen1);
					subNode.setProperty("reportcomesFrom", reportcomesFrom); //
					subNode.setProperty("SingleJson",  allReportJsonArrayInsideJSonobject);

					ChangedStructureCurrent_Methods CSC=new ChangedStructureCurrent_Methods();
					if(!GmailMethods.isNullString(from_Source)){
						from_Source=CSC.check_Source(session, from_Source,out);
					}else{
						from_Source="";
					}
					subNode.setProperty("from_Source", from_Source);
					subNode.setProperty("timestampDate", timestampDate);
					subNode.setProperty("timestampDateAndTime", timestampDateAndTime);

					Tonnage.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
					session.refresh(true);
					session.save();

				}


		} catch (Exception e) {
			//			out.println(e.getMessage());
		}
	}
	
	public static void callSpotError(PrintWriter out, Session session, String Information, String FixtureType, String Charterer, String Status
			,String CargoQty, String CargoGrade, String LCStart, String LCEnd, String LoadPort, String DiscPort, String VesselName
			,String RateType, String Rate, String Comment, String ReportDate, String Source, String VesselType, String ReportTimestamp, String emailUrl, String subjectNodePath, String textSentMailTime, String CargoType, String from_Source, String timestampDate,String timestampDateAndTime, String Flag, String flagPort, String flagPortOpen1, String reportcomesFrom, String data, String allReportJsonArrayInsideJSonobject){

		try {
				Node scorpio=null;
				Node Tonnage=null;
				Node ReportData=null;
				Node subNode=null;
				Node Other=null;
				Node ReportErrorNode=null;
				if(session.getRootNode().hasNode("scorpioDataBase")){
					scorpio = session.getRootNode().getNode("scorpioDataBase");
				}else{
					scorpio = session.getRootNode().addNode("scorpioDataBase");
					session.refresh(true);
					session.save();
				}
				if(scorpio.hasNode("ReportData")){
					ReportData = scorpio.getNode("ReportData");
				}else{
					ReportData = scorpio.addNode("ReportData");
					session.refresh(true);
					session.save();
				}
				if(ReportData.hasNode("Other")){
					Other=ReportData.getNode("Other");
				}else{
					Other=ReportData.addNode("Other");
					session.refresh(true);
					session.save();
				}
				if(Other.hasNode("ReportErrorNode")){
					ReportErrorNode = Other.getNode("ReportErrorNode");
				}else{
					ReportErrorNode = Other.addNode("ReportErrorNode");
					session.refresh(true);
					session.save();
				}if(ReportErrorNode.hasNode("Spot")){
					Tonnage = ReportErrorNode.getNode("Spot");
				}else{
					Tonnage = ReportErrorNode.addNode("Spot");
					Tonnage.setProperty("jcr:count", String.valueOf(0));
					session.refresh(true);
					session.save();
				}
				String count=Tonnage.getProperty("jcr:count").getString();
				String forid= String.valueOf(Integer.parseInt(count)+1);

				Calendar c = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
				c.setTime(sdf.parse(textSentMailTime));

				if(!Tonnage.hasNode(forid)){
					subNode=Tonnage.addNode(forid);

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

					String combinedatetimestamp=s+"-"+String.valueOf(month)+"-"+String.valueOf(year);

					subNode.setProperty("Sr.No", forid);
					subNode.setProperty("Id", forid);
					subNode.setProperty("Information", "2");
					subNode.setProperty("FixtureType", FixtureType);
					subNode.setProperty("Charterer", Charterer);
					subNode.setProperty("Status", Status);
					subNode.setProperty("CargoType", CargoType);
					subNode.setProperty("CargoGrade", CargoGrade);
					subNode.setProperty("CargoQty", CargoQty);
					if(!GmailMethods.isNullString(LCStart)){
						LCStart=new ConvertDate().convertDateFormat(LCStart);
					}else{
						LCStart="";
					}
					subNode.setProperty("LCStart", LCStart);
					if(!GmailMethods.isNullString(LCEnd)){
						LCEnd=new ConvertDate().convertDateFormat(LCEnd);
					}else{
						LCEnd="";
					}
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
					subNode.setProperty("dateAndTime", combinedatetimestamp);
					subNode.setProperty("ReportType", "2");
					subNode.setProperty("ReportTimestamp", c);
					subNode.setProperty("emailUrl", emailUrl);
					subNode.setProperty("emailClientNodeId", subjectNodePath);
					subNode.setProperty("textSentMailTime", textSentMailTime);
					subNode.setProperty("vesselFlag", Flag);
					subNode.setProperty("flagPort", flagPort);
					subNode.setProperty("reportcomesFrom", reportcomesFrom);
					subNode.setProperty("SingleJson",  allReportJsonArrayInsideJSonobject);
					ChangedStructureCurrent_Methods CSC=new ChangedStructureCurrent_Methods();
					if(!GmailMethods.isNullString(from_Source)){
						from_Source=CSC.check_Source(session, from_Source,out);
					}else{
						from_Source="";
					}
					subNode.setProperty("from_Source", from_Source);
					subNode.setProperty("timestampDate", timestampDate);
					subNode.setProperty("timestampDateAndTime", timestampDateAndTime);
					Tonnage.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
					session.refresh(true);
					session.save();
				}

		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}
	
	public static void callTimecharError(PrintWriter out, Session session, String Information, String Spot_TC, String Date, String VesselName
			, String CPP_DPP, String Charterer, String Delivery, String DeliveryPlace, String Period, String Periodunit, String Redelivery
			, String TCRate, String Rate, String Unit, String Comments, String TimeStamp, String Source, String Vesseltype, String emailUrl, String subjectNodePath, String textSentMailTime, String from_Source, String timestampDate,String timestampDateAndTime, String Flag, String flagPort, String flagPortOpen1,  String reportcomesFrom, String data, String allReportJsonArrayInsideJSonobject){

		try {

				Node scorpio=null;
				Node TimeCharterReport=null;
				Node subNode=null;
				Node ReportData=null;
				Node Other=null;
				Node ReportErrorNode=null;

				if(session.getRootNode().hasNode("scorpioDataBase")){
					scorpio = session.getRootNode().getNode("scorpioDataBase");
				}else{
					scorpio = session.getRootNode().addNode("scorpioDataBase");
					session.refresh(true);
					session.save();
				}

				if(scorpio.hasNode("ReportData")){
					ReportData = scorpio.getNode("ReportData");
				}else{
					ReportData = scorpio.addNode("ReportData");
					session.refresh(true);
					session.save();
				}
				if(ReportData.hasNode("Other")){
					Other=ReportData.getNode("Other");
				}else{
					Other=ReportData.addNode("Other");
					session.refresh(true);
					session.save();
				}
				if(Other.hasNode("ReportErrorNode")){
					ReportErrorNode = Other.getNode("ReportErrorNode");
				}else{
					ReportErrorNode = Other.addNode("ReportErrorNode");
					session.refresh(true);
					session.save();
				}

				if(ReportErrorNode.hasNode("TimeCharterReport")){
					TimeCharterReport=ReportErrorNode.getNode("TimeCharterReport");
				}else{
					TimeCharterReport=ReportErrorNode.addNode("TimeCharterReport");
					TimeCharterReport.setProperty("jcr:count", String.valueOf(0));
					session.refresh(true);
					session.save();
				}

				String count=TimeCharterReport.getProperty("jcr:count").getString();
				String forid= String.valueOf(Integer.parseInt(count)+1);

				Calendar c = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
				c.setTime(sdf.parse(textSentMailTime));

				if(!TimeCharterReport.hasNode(forid)){
					subNode=TimeCharterReport.addNode(forid);

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
					String combinedatetimestamp=s+"-"+String.valueOf(month)+"-"+String.valueOf(year);
					subNode.setProperty("Sr.No", forid);
					subNode.setProperty("Id", forid);
					subNode.setProperty("Information", "2");
					subNode.setProperty("Spot_TC","5");
					subNode.setProperty("dateAndTime", combinedatetimestamp);
					subNode.setProperty("emailUrl", emailUrl);
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
					subNode.setProperty("TimeStamp", c);
					subNode.setProperty("Source", Source);
					subNode.setProperty("Vesseltype", Vesseltype);
					subNode.setProperty("ReportType", "3");
					subNode.setProperty("ReportTimestamp", TimeStamp);
					subNode.setProperty("emailClientNodeId", subjectNodePath);
					subNode.setProperty("textSentMailTime", textSentMailTime);
					subNode.setProperty("vesselFlag", Flag);
					subNode.setProperty("reportcomesFrom", reportcomesFrom);
					subNode.setProperty("SingleJson",  allReportJsonArrayInsideJSonobject);
					ChangedStructureCurrent_Methods CSC=new ChangedStructureCurrent_Methods();
					if(!GmailMethods.isNullString(from_Source)){
						from_Source=CSC.check_Source(session, from_Source,out);
					}else{
						from_Source="";
					}
					subNode.setProperty("from_Source", from_Source);
					subNode.setProperty("timestampDate", timestampDate);
					subNode.setProperty("timestampDateAndTime", timestampDateAndTime);

					TimeCharterReport.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
					session.refresh(true);
					session.save();
				}

		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}

	public static void callBrokerTcRateError(PrintWriter out, Session session, String Broker, String VesselType, String Month_Year, String first_yr,
			String second_yr, String third_yr, String fifth_yr, String ReportTimestamp, String emailUrl, String subjectNodePath, String textSentMailTime, String from_Source, String timestampDate,String timestampDateAndTime, String Flag, String flagPort, String flagPortOpen1, String reportcomesFrom, String data, String allReportJsonArrayInsideJSonobject){

		try {

				Node scorpio=null;
				Node BrokerTCRate=null;
				Node subNode=null;
				Node TCReports=null;
				Node ReportData=null;
				Node Other=null;
				Node ReportErrorNode=null;

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
					session.refresh(true);
					session.save();
				}

				if(ReportData.hasNode("Other")){
					Other=ReportData.getNode("Other");
				}else{
					Other=ReportData.addNode("Other");
					session.refresh(true);
					session.save();
				}
				if(Other.hasNode("ReportErrorNode")){
					ReportErrorNode = Other.getNode("ReportErrorNode");
				}else{
					ReportErrorNode = Other.addNode("ReportErrorNode");
					session.refresh(true);
					session.save();
				}

				if(ReportErrorNode.hasNode("BrokerTCRate")){
					BrokerTCRate=ReportErrorNode.getNode("BrokerTCRate");
				}else{
					BrokerTCRate=ReportErrorNode.addNode("BrokerTCRate");
					BrokerTCRate.setProperty("jcr:count", String.valueOf(0));
					session.refresh(true);
					session.save();
				}

				String count=BrokerTCRate.getProperty("jcr:count").getString();
				String forid= String.valueOf(Integer.parseInt(count)+1);
				Calendar c = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
				c.setTime(sdf.parse(textSentMailTime));

				if(!BrokerTCRate.hasNode(forid)){
					subNode=BrokerTCRate.addNode(forid);

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

					String combinedatetimestamp=s+"-"+String.valueOf(month)+"-"+String.valueOf(year);

					subNode.setProperty("Sr.No", forid);
					subNode.setProperty("Id", forid);
					subNode.setProperty("dateAndTime", combinedatetimestamp);
					subNode.setProperty("emailUrl", emailUrl);
					subNode.setProperty("Broker", Broker);
					subNode.setProperty("VesselType", VesselType);
					subNode.setProperty("Month_Year", Month_Year);
					subNode.setProperty("first_yr", first_yr);
					subNode.setProperty("second_yr", second_yr);
					subNode.setProperty("third_yr", third_yr);
					subNode.setProperty("fifth_yr", fifth_yr);
					subNode.setProperty("ReportType", "4");
					subNode.setProperty("ReportTimestamp", c);
					subNode.setProperty("emailClientNodeId", subjectNodePath);
					subNode.setProperty("textSentMailTime", textSentMailTime);
					subNode.setProperty("reportcomesFrom", reportcomesFrom);  //vinayaJson
					subNode.setProperty("SingleJson", allReportJsonArrayInsideJSonobject);
					ChangedStructureCurrent_Methods CSC=new ChangedStructureCurrent_Methods();
					if(!GmailMethods.isNullString(from_Source)){
						from_Source=CSC.check_Source(session, from_Source,out);
					}else{
						from_Source="";
					}

					subNode.setProperty("from_Source", from_Source);
					subNode.setProperty("timestampDate", timestampDate);
					subNode.setProperty("timestampDateAndTime", timestampDateAndTime);

					BrokerTCRate.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
					session.refresh(true);
					session.save();
				}

		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}
	
	public static void callCheckBlankNodeError(PrintWriter out, Session session, String emailUrl,  String reportcomesFrom, String data, String allReportJsonArrayInsideJSonobject, String timestampDateAndTime){

		try {


			Node scorpio=null;
			Node subNode=null;
			Node TCReports=null;
			Node ReportData=null;
			Node Other=null;

			if(session.getRootNode().hasNode("scorpioDataBase")){
				scorpio = session.getRootNode().getNode("scorpioDataBase");
			}else{
				scorpio = session.getRootNode().addNode("scorpioDataBase");
				//session.save();
			}

			if(scorpio.hasNode("ReportData")){
				ReportData = scorpio.getNode("ReportData");
			}else{
				ReportData = scorpio.addNode("ReportData");
				//session.save();
			}

			if(ReportData.hasNode("Other")){
				Other = ReportData.getNode("Other");
			}else{
				Other = ReportData.addNode("Other");
				session.refresh(true);
				session.save();
			}
			
			if(Other.hasNode("Error")){
				TCReports = Other.getNode("Error");
			}else{
				TCReports = Other.addNode("Error");
				TCReports.setProperty("jcr:count", String.valueOf(0));
				//session.save();
			}

			String count=TCReports.getProperty("jcr:count").getString();
			String forid= String.valueOf(Integer.parseInt(count)+1);
			if(!TCReports.hasNode(forid)){
				subNode=TCReports.addNode(forid);
				subNode.setProperty("Id", forid);
				subNode.setProperty("emailUrl", emailUrl);
				subNode.setProperty("reportcomesFrom", reportcomesFrom); 
				/* subNode.setProperty("JsonArrayData", data); */
				subNode.setProperty("Jsonobj", allReportJsonArrayInsideJSonobject);
				subNode.setProperty("timestampDateAndTime", timestampDateAndTime);
				/* if("pdf".equals(reportcomesFrom)){
				 subNode.setProperty("pdfJson", data);
			 }*/

				TCReports.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
				session.refresh(true);
				session.save();
			}



		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}
	
	public static void callOtherNodeError(PrintWriter out, Session session, String emailUrl,  String reportcomesFrom, String data, String allReportJsonArrayInsideJSonobject, String Error, String timestampDateAndTime){

		try {


			Node scorpio=null;
			Node subNode=null;
			Node TCReports=null;
			Node ReportData=null;
			Node Other=null;

			if(session.getRootNode().hasNode("scorpioDataBase")){
				scorpio = session.getRootNode().getNode("scorpioDataBase");
			}else{
				scorpio = session.getRootNode().addNode("scorpioDataBase");
				//session.save();
			}

			if(scorpio.hasNode("ReportData")){
				ReportData = scorpio.getNode("ReportData");
			}else{
				ReportData = scorpio.addNode("ReportData");
				//session.save();
			}

			if(ReportData.hasNode("Other")){
				Other = ReportData.getNode("Other");
			}else{
				Other = ReportData.addNode("Other");
				session.refresh(true);
				session.save();
			}
			
			if(Other.hasNode("Error")){
				TCReports = Other.getNode("Error");
			}else{
				TCReports = Other.addNode("Error");
				TCReports.setProperty("jcr:count", String.valueOf(0));
				//session.save();
			}

			String count=TCReports.getProperty("jcr:count").getString();
			String forid= String.valueOf(Integer.parseInt(count)+1);
			if(!TCReports.hasNode(forid)){
				subNode=TCReports.addNode(forid);
				subNode.setProperty("Id", forid);
				subNode.setProperty("emailUrl", emailUrl);
				subNode.setProperty("reportcomesFrom", reportcomesFrom); 
				subNode.setProperty("JsonArrayData", data); 
				subNode.setProperty("Jsonobj", allReportJsonArrayInsideJSonobject);
				subNode.setProperty("PallaviReturnError", Error); //
				subNode.setProperty("timestampDateAndTime", timestampDateAndTime);
				/*if("pdf".equals(reportcomesFrom)){
					 subNode.setProperty("pdfJson", data);
				 }*/

				TCReports.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
				session.refresh(true);
				session.save();
			}



		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}


}
