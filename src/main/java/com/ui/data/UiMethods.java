package com.ui.data;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.Session;

public class UiMethods {

	
public static void TonnageSave(Session session, PrintWriter out , String ReportType, String RepositionRegion, String CargoType, String VesselName,
		String VesselType, String Built, String DWT, String Cubics, String LOA, String ICE , String SternLine, String Owners, String Operators,
		String EmploymentStatus, String OpenPort, String OpenDate, String ETABasis, String Comment, String Source, String ReportTimestamp){
		
		try {
			
			Node scorpio=null;
			Node Tonnage=null;
			Node subNode=null;
			
			if(session.getRootNode().hasNode("scorpio")){
				scorpio = session.getRootNode().getNode("scorpio");
			}else{
				scorpio = session.getRootNode().addNode("scorpio");
				session.save();
			}
			
			if(scorpio.hasNode("Tonnage")){
				Tonnage=scorpio.getNode("Tonnage");
			}else{
				Tonnage=scorpio.addNode("Tonnage");
				Tonnage.setProperty("jcr:count", String.valueOf(0));
				session.save();
			}
			
			Calendar cal = null;
			cal = Calendar.getInstance();
			
			Date minDate = new Date(cal.getTimeInMillis());  // get today date
			
			 String count=Tonnage.getProperty("jcr:count").getString();
			 String forid= String.valueOf(Integer.parseInt(count)+1);
			 if(!Tonnage.hasNode(forid)){
				 subNode=Tonnage.addNode(forid);
				 				
				 subNode.setProperty("Id", forid);
				 subNode.setProperty("dateAndTime", minDate.toString());
				 subNode.setProperty("emailUrl", "");
				 subNode.setProperty("excelUrl", "");
				 subNode.setProperty("Error", "");
				 subNode.setProperty("attachment", "");
				 subNode.setProperty("ReportType", ReportType);
				 subNode.setProperty("RepositionRegion", RepositionRegion);
				 subNode.setProperty("CargoType", CargoType);
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
				 subNode.setProperty("ReportTimestamp", ReportTimestamp);
				 
				 Tonnage.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
				 session.save();
				 
			 }
			 
			
			
		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}
	
  public static void SpotSave(Session session, PrintWriter out, String Information, String FixtureType, String Charterer, String Status,
		  String CargoType, String CargoGrade, String CargoQty, String LCStart, String LCEnd, String LoadPort, String DiscPort, String VesselName,
		  String RateType, String Rate, String Comments, String ReportDate, String Source, String Vesseltype){
	
	try {
		
		Node scorpio=null;
		Node Tonnage=null;
		Node subNode=null;
		
		if(session.getRootNode().hasNode("scorpio")){
			scorpio = session.getRootNode().getNode("scorpio");
		}else{
			scorpio = session.getRootNode().addNode("scorpio");
			session.save();
		}
		
		if(scorpio.hasNode("Spot")){
			Tonnage=scorpio.getNode("Spot");
		}else{
			Tonnage=scorpio.addNode("Spot");
			Tonnage.setProperty("jcr:count", String.valueOf(0));
			session.save();
		}
		
		 String count=Tonnage.getProperty("jcr:count").getString();
		 String forid= String.valueOf(Integer.parseInt(count)+1);
		 if(!Tonnage.hasNode(forid)){
			 subNode=Tonnage.addNode(forid);
			 				
			    Calendar cal = null;
				cal = Calendar.getInstance();
				Date minDate = new Date(cal.getTimeInMillis());
			 
			 subNode.setProperty("Id", forid);
			 subNode.setProperty("Information", Information);
			 subNode.setProperty("FixtureType", FixtureType);
			 subNode.setProperty("Charterer",Charterer);
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
			 subNode.setProperty("Comments", Comments);
			 subNode.setProperty("ReportDate", ReportDate);
			 subNode.setProperty("Source", Source);
			 subNode.setProperty("Vesseltype", Vesseltype);
			 subNode.setProperty("dateAndTime", minDate.toString());
			 subNode.setProperty("emailUrl", "");
			 subNode.setProperty("excelUrl", "");
			 subNode.setProperty("Error", "");
			 subNode.setProperty("attachment", "");
			 
			 Tonnage.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
			 session.save();
			 
		 }
		 
		
		
	} catch (Exception e) {
		out.println(e.getMessage());
	}
}

  public static void TimeCharterReportSave(Session session, PrintWriter out, String Information, String Spot_TC, String Date, String VesselName,
		  String CPP_DPP, String Charterer, String Delivery, String DeliveryPlace, String Period, String Periodunit, String Redelivery,
		  String TCRate, String Rate, String Unit, String Comments, String TimeStamp, String Source, String Vesseltype){
		
		try {
			
			Node scorpio=null;
			Node TimeCharterReport=null;
			Node subNode=null;
			Node TCReports=null;
			
			if(session.getRootNode().hasNode("scorpio")){
				scorpio = session.getRootNode().getNode("scorpio");
			}else{
				scorpio = session.getRootNode().addNode("scorpio");
				session.save();
			}
			
			if(scorpio.hasNode("TCReports")){
				TCReports = scorpio.getNode("TCReports");
			}else{
				TCReports = scorpio.addNode("TCReports");
				session.save();
			}
			
			if(TCReports.hasNode("TimeCharterReport")){
				TimeCharterReport=TCReports.getNode("TimeCharterReport");
			}else{
				TimeCharterReport=TCReports.addNode("TimeCharterReport");
				TimeCharterReport.setProperty("jcr:count", String.valueOf(0));
				session.save();
			}
			
			 String count=TimeCharterReport.getProperty("jcr:count").getString();
			 String forid= String.valueOf(Integer.parseInt(count)+1);
			 if(!TimeCharterReport.hasNode(forid)){
				 subNode=TimeCharterReport.addNode(forid);
				 				
				 Calendar cal = null;
				 cal = Calendar.getInstance();
				 Date minDate = new Date(cal.getTimeInMillis());
				 
				 subNode.setProperty("Id", forid);
				 subNode.setProperty("Information", Information);
				 subNode.setProperty("Spot_TC", Spot_TC);
				 subNode.setProperty("dateAndTime", minDate.toString());
				 subNode.setProperty("emailUrl", "");
				 subNode.setProperty("excelUrl", "");
				 subNode.setProperty("Error", "");
				 subNode.setProperty("attachment", "");
				 
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
				 
				 TimeCharterReport.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
				 session.save();
				 
			 }
			 
			
			
		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}
  
	public static void BrokerTCRateSaveUi(Session session, PrintWriter out , String Broker, String VesselType, String Month_Year, String first_yr
			, String second_yr, String third_yr, String fifth_yr, String foridui){
		
		try {
			
			Node scorpio=null;
			Node BrokerTCRate=null;
			Node subNode=null;
			Node TCReports=null;
			
			if(session.getRootNode().hasNode("scorpio")){
				scorpio = session.getRootNode().getNode("scorpio");
			}else{
				scorpio = session.getRootNode().addNode("scorpio");
				session.save();
			}
			
			if(scorpio.hasNode("TCReports")){
				TCReports = scorpio.getNode("TCReports");
			}else{
				TCReports = scorpio.addNode("TCReports");
				session.save();
			}
			
			if(TCReports.hasNode("BrokerTCRate")){
				BrokerTCRate=TCReports.getNode("BrokerTCRate");
			}else{
				BrokerTCRate=TCReports.addNode("BrokerTCRate");
				BrokerTCRate.setProperty("jcr:count", String.valueOf(0));
				session.save();
			}
			
			 String count=BrokerTCRate.getProperty("jcr:count").getString();
			 String forid= String.valueOf(Integer.parseInt(count)+1);
			 if(!BrokerTCRate.hasNode(forid)){
				 subNode=BrokerTCRate.addNode(forid);
				 
				 Calendar cal = null;
				 cal = Calendar.getInstance();
				 Date minDate = new Date(cal.getTimeInMillis());
				 				
				 subNode.setProperty("Id", forid);  //foridui
				 subNode.setProperty("dateAndTime", minDate.toString());
				 subNode.setProperty("emailUrl", "");
				 subNode.setProperty("excelUrl", "");
				 subNode.setProperty("Error", "");
				 subNode.setProperty("attachment", "");
				 
				 subNode.setProperty("Broker", Broker);
				 subNode.setProperty("VesselType", VesselType);
				 subNode.setProperty("Month_Year", Month_Year);
				 subNode.setProperty("first_yr", first_yr);
				 subNode.setProperty("second_yr", second_yr);
				 subNode.setProperty("third_yr", third_yr);
				 subNode.setProperty("fifth_yr", fifth_yr);
				 
				 BrokerTCRate.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
				 session.save();
				 
			 }
			 
			
			
		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}

	
	public static void updateBrokertcRate(PrintWriter out, Session session, String passnodenmae, 
			String brokervalue){
		try {
			
			Node scorpio=null;
			Node BrokerTCRate=null;
			Node subNode=null;
			Node TCReports=null;
			
			if(session.getRootNode().hasNode("scorpio")){
				scorpio = session.getRootNode().getNode("scorpio");
			}else{
				scorpio = session.getRootNode().addNode("scorpio");
				session.save();
			}
			
			if(scorpio.hasNode("TCReports")){
				TCReports = scorpio.getNode("TCReports");
			}else{
				TCReports = scorpio.addNode("TCReports");
				session.save();
			}
			
			if(TCReports.hasNode("BrokerTCRate")){
				BrokerTCRate=TCReports.getNode("BrokerTCRate");
			}else{
				BrokerTCRate=TCReports.addNode("BrokerTCRate");
				BrokerTCRate.setProperty("jcr:count", String.valueOf(0));
				session.save();
			}
			
			if(BrokerTCRate.hasNode(passnodenmae)){
				Node psaaNodeName=BrokerTCRate.getNode(passnodenmae);
				
				if(psaaNodeName.hasProperty("Broker")){
					Property s = psaaNodeName.getProperty("Broker");
					s.setValue(brokervalue);
					session.save();
				}
				
			}
			
			
			
			
		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}
	
	public static void BrokerTCRateEditUi(Session session, PrintWriter out , String Broker, String VesselType, String Month_Year, String first_yr
			, String second_yr, String third_yr, String fifth_yr, String passnodenmae){
		
		try {
			
			Node scorpio=null;
			Node BrokerTCRate=null;
			Node TCReports=null;
			
			if(session.getRootNode().hasNode("scorpio")){
				scorpio = session.getRootNode().getNode("scorpio");
			}else{
				scorpio = session.getRootNode().addNode("scorpio");
				session.save();
			}
			
			if(scorpio.hasNode("TCReports")){
				TCReports = scorpio.getNode("TCReports");
			}else{
				TCReports = scorpio.addNode("TCReports");
				session.save();
			}
			
			if(TCReports.hasNode("BrokerTCRate")){
				BrokerTCRate=TCReports.getNode("BrokerTCRate");
			}else{
				BrokerTCRate=TCReports.addNode("BrokerTCRate");
				BrokerTCRate.setProperty("jcr:count", String.valueOf(0));
				session.save();
			}
			
				 Calendar cal = null;
				 cal = Calendar.getInstance();
				 Date minDate = new Date(cal.getTimeInMillis());
				 
				 if(BrokerTCRate.hasNode(passnodenmae)){
					 Node psaaNodeName=BrokerTCRate.getNode(passnodenmae);
				 
					 psaaNodeName.setProperty("dateAndTime_updated", minDate.toString());
					 psaaNodeName.setProperty("Broker", Broker);
					 psaaNodeName.setProperty("VesselType", VesselType);
					 psaaNodeName.setProperty("Month_Year", Month_Year);
					 psaaNodeName.setProperty("first_yr", first_yr);
					 psaaNodeName.setProperty("second_yr",second_yr);
					 psaaNodeName.setProperty("third_yr", third_yr);
					 psaaNodeName.setProperty("fifth_yr", fifth_yr);
				  session.save();
				 
				 }
			 
		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}
	
}
