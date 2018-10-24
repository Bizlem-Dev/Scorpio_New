package com.readGmail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.ui.data.CreateExcelMethods;



public class SlingMethods {

	

	public static void main(String[] args) {
		String a="abc_aalok.txt";
		System.out.println(a.substring(0, a.lastIndexOf(".txt")));
	}
	
	public static void TonnageSave(Session session, PrintWriter out){
		
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
//			cal.add(Calendar.DAY_OF_MONTH, -2);               // add 1 day
//			Date maxDate = new Date(cal.getTimeInMillis());
			
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
				 subNode.setProperty("ReportType", "Position List");
				 subNode.setProperty("RepositionRegion", "AG");
				 subNode.setProperty("CargoType", "Clean/CPP");
				 subNode.setProperty("VesselName", "ACE");
				 subNode.setProperty("VesselType", "pick from database");
				 subNode.setProperty("Built", "pick from database");
				 subNode.setProperty("DWT", "pick from database");
				 subNode.setProperty("Cubics", "pick from database");
				 subNode.setProperty("LOA", "pick from database");
				 subNode.setProperty("ICE", "pick from database");
				 subNode.setProperty("SternLine", "pick from database");
				 subNode.setProperty("Owners", "prashant");
				 subNode.setProperty("Operators", "IMS");
				 subNode.setProperty("EmploymentStatus", "Unfixed"); 
				 subNode.setProperty("OpenPort", "SOHAR");
				 subNode.setProperty("OpenDate", "30-Jul-18");
				 subNode.setProperty("ETABasis", "30-Jul-18");
				 subNode.setProperty("Comment", "L.GO");
				 subNode.setProperty("Source", "Affinity");
				 subNode.setProperty("ReportTimestamp", "30-07-2018 16:09");
				 
				 Tonnage.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
				 session.save();
				 
			 }
			 
			
			
		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}
	
	
public static void SpotSave(Session session, PrintWriter out){
		
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
				 subNode.setProperty("Information", "Market");
				 subNode.setProperty("FixtureType", "Spot");
				 subNode.setProperty("Charterer", "Exxon");
				 subNode.setProperty("Status", "Subs");
				 subNode.setProperty("CargoType", "CPP/Clean");
				 subNode.setProperty("CargoGrade", "ULSD");
				 subNode.setProperty("CargoQty", "30");
				 subNode.setProperty("LCStart", "05-08-2018");
				 subNode.setProperty("LCEnd", "07-08-2018");
				 subNode.setProperty("LoadPort", "ARA");
				 subNode.setProperty("DiscPort", "UKC");
				 subNode.setProperty("VesselName", "British Cirrus");
				 subNode.setProperty("RateType", "WS");
				 subNode.setProperty("Rate", "125");
				 subNode.setProperty("Comments", "Subs");
				 subNode.setProperty("ReportDate", "03-08-2018 22:30");
				 subNode.setProperty("Source", "Braemar");
				 subNode.setProperty("Vesseltype", "This will be picked from Vessel Databse");
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

public static void TimeCharterReportSave(Session session, PrintWriter out){
	
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
			 subNode.setProperty("Information", "Market");
			 subNode.setProperty("Spot_TC", "TC");
			 subNode.setProperty("dateAndTime", minDate.toString());
			 subNode.setProperty("emailUrl", "");
			 subNode.setProperty("excelUrl", "");
			 subNode.setProperty("Error", "");
			 subNode.setProperty("attachment", "");
			 
			 subNode.setProperty("Date", "27-Jul-18");
			 subNode.setProperty("VesselName", "C. Spirit");
			 subNode.setProperty("CPP_DPP", "Dirty/DPP");
			 subNode.setProperty("Charterer", "Reliance");
			 subNode.setProperty("Delivery", "Q3");
			 subNode.setProperty("DeliveryPlace", "India");
			 subNode.setProperty("Period", "12+12");
			 subNode.setProperty("Periodunit", "months");
			 subNode.setProperty("Redelivery", "01-Jul-19");
			 subNode.setProperty("TCRate", "RNR");
			 subNode.setProperty("Rate", "RNR");
			 subNode.setProperty("Unit", "USD");
			 subNode.setProperty("Comments", "DTY Del India");
			 subNode.setProperty("TimeStamp", "27-07-2018");
			 subNode.setProperty("Source", "Braemar");
			 subNode.setProperty("Vesseltype", "from Vessel Database");
			 
			 TimeCharterReport.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
			 session.save();
			 
		 }
		 
		
		
	} catch (Exception e) {
		out.println(e.getMessage());
	}
}

public static void BrokerTCRateSave(Session session, PrintWriter out){
	
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
			 				
			 subNode.setProperty("Id", forid);
			 subNode.setProperty("dateAndTime", minDate.toString());
			 subNode.setProperty("emailUrl", "");
			 subNode.setProperty("excelUrl", "");
			 subNode.setProperty("Error", "");
			 subNode.setProperty("attachment", "");
			 
			 subNode.setProperty("Broker", "Braemar");
			 subNode.setProperty("VesselType", "Handymax");
			 subNode.setProperty("Month_Year", "Jan-18");
			 subNode.setProperty("first_yr", "12000");
			 subNode.setProperty("second_yr", "13,250");
			 subNode.setProperty("third_yr", "14,000");
			 subNode.setProperty("fifth_yr", "13,500");
			 
			 BrokerTCRate.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
			 session.save();
			 
		 }
		 
		
		
	} catch (Exception e) {
		out.println(e.getMessage());
	}
}

public static void TonnageNodeRemove(Session session, PrintWriter out, Node all, String subNodeName){
		
		try {
			
//			Node scorpio=null;
//			Node Tonnage=null;
			
			/*if(session.getRootNode().hasNode("scorpio")){
				scorpio = session.getRootNode().getNode("scorpio");
			}else{
				scorpio = session.getRootNode().addNode("scorpio");
				session.save();
			}*/
			
			/*if(scorpio.hasNode(NodeName)){
				Tonnage=scorpio.getNode(NodeName); // Tonnage
			}else{
				Tonnage=scorpio.addNode(NodeName);
				Tonnage.setProperty("jcr:count", String.valueOf(0));
				session.save();
			}*/
			
			Node one=all.getNode(subNodeName); // do dynamically // "2"
		    String nodeName=one.getName();
		    out.println("NodeName: "+nodeName);
		    
		    int result = Integer.valueOf(nodeName);
		    out.println("previousnodename: "+result);
		    
			 one.remove();
			 session.save();
			 
			 Rename(all, out, result, session);
		     session.save();
			
		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}

public static void rename(Node node, String newName) throws RepositoryException 
{
    node.getSession().move(node.getPath(), node.getParent().getPath() + "/" + newName);
    
}

public static void Rename(Node Tonnage, PrintWriter out, int previousnodename, Session session){
	
	try {
		if(Tonnage.hasNodes()){
			NodeIterator itr=Tonnage.getNodes();
			
			while(itr.hasNext()){
				Node obj=itr.nextNode();
				String nodeNamee=obj.getName();
				int result1 = Integer.valueOf(nodeNamee);
				if(result1>previousnodename){
					int finalint=result1-1;
					out.println("finalint_else: "+finalint);
					Node a=Tonnage.getNode(String.valueOf(result1));
				    rename(a, String.valueOf(finalint));
					session.save();
					
				}
				}
		}
	} catch (Exception e) {
		out.println(e.getMessage());
	}
}

public static JSONObject formatDate_Month_Year_Day(Date date, PrintWriter out)  {
	
	JSONObject dateJsonObject=null;
	try{
	
	if(null != date){
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	
	int day=cal.get(Calendar.DATE);
	int month=(cal.get(Calendar.MONTH)+1);
	int year=cal.get(Calendar.YEAR);
	
	 dateJsonObject=new JSONObject();
	 dateJsonObject.put("day", day);
	 dateJsonObject.put("month", month);
	 dateJsonObject.put("year", year);
	
	}
	}catch(Exception e){
		out.println(e.getMessage());
	}
	return dateJsonObject;
	
}


public static boolean CheckTodayDAte(int dayOfMonth, int month, int year, PrintWriter out){
	   boolean dateBoolean=false;
	try {
		
		    Calendar c = Calendar.getInstance();

		    // set the calendar to start of today
		    c.set(Calendar.HOUR_OF_DAY, 0);
		    c.set(Calendar.MINUTE, 0);
		    c.set(Calendar.SECOND, 0);
		    c.set(Calendar.MILLISECOND, 0);
             Date today = c.getTime();

		    // reuse the calendar to set Sling specified date
		    c.set(Calendar.YEAR, year);
		    c.set(Calendar.MONTH, month - 1);
		    c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		    c.set(Calendar.HOUR_OF_DAY, 0);
		    c.set(Calendar.MINUTE, 0);
		    c.set(Calendar.SECOND, 0);
		    c.set(Calendar.MILLISECOND, 0);
		    
		    // and get date as Sling Date
		    Date dateSpecified = c.getTime();

		    // test condition
		    if (dateSpecified.before(today)) {

//		        out.println("date is previou");
		        dateBoolean=false;
		        
		    } else if (dateSpecified.equals(today)) {

//		        out.println("date is today");
		        dateBoolean=true;
		    } 
		
		
	} catch (Exception e) {
		out.println(e.getMessage());
	}
	return dateBoolean;
}

public static JSONObject fetchTonnageDaywiseData(PrintWriter out, Session session){
	
	JSONObject mainJsonobj=null;
	try {
		Node scorpio = session.getRootNode().getNode("scorpio");
		Node Tonnage=scorpio.getNode("Tonnage");
		
		if(Tonnage.hasNodes()){
			long size=Tonnage.getNodes().getSize();
			
			JSONObject keyNameObject=null;
			
			JSONArray keyNameObjectJsonarray=new JSONArray();
			
			NodeIterator itr=Tonnage.getNodes();
			int i=0;
			while(itr.hasNext()){
			Node nextnode=itr.nextNode();
			String dateAndTime="";
			
			if(nextnode.hasProperty("dateAndTime")){
				dateAndTime=nextnode.getProperty("dateAndTime").getString();
			}
			
			JSONObject dateJson=new JSONObject();
			boolean dateboolean=false;
			if(!GmailMethods.isNullString(dateAndTime)){
				Date date=GmailMethods.parseDate(dateAndTime);
				dateJson=formatDate_Month_Year_Day(date, out);
				
				int day=dateJson.getInt("day");
				int month=dateJson.getInt("month");
				int year=dateJson.getInt("year");
				
				dateboolean=CheckTodayDAte(day, month, year, out);
				
				if(dateboolean==true){
					int parseId=0;
					i++;
					String subNodeName=nextnode.getName();
//					out.println("subNodeName: "+subNodeName);
					
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
					if(nextnode.hasProperty("dateAndTime")){
						dateAndTime=nextnode.getProperty("dateAndTime").getString();
					}
					if(nextnode.hasProperty("EmploymentStatus")){
						EmploymentStatus=nextnode.getProperty("EmploymentStatus").getString();
						
						JSONObject EmploymentStatusJsonObject=new JSONObject();
						EmploymentStatusJsonObject.put("EmploymentStatus", EmploymentStatus);
						if(EmploymentStatusJsonObject.length()>0){
						EmploymentStatusJsonarray.put(EmploymentStatusJsonObject);
						}
					}
					if(nextnode.hasProperty("Owners")){
						Owners=nextnode.getProperty("Owners").getString();
						
						JSONObject OwnersJsonObject=new JSONObject();
						OwnersJsonObject.put("Owners", Owners);
						if(OwnersJsonObject.length()>0){
						OwnersJsonarray.put(OwnersJsonObject);
						}
					}
					if(nextnode.hasProperty("OpenPort")){
						OpenPort=nextnode.getProperty("OpenPort").getString();
						
						JSONObject OpenPortJsonObject=new JSONObject();
						OpenPortJsonObject.put("OpenPort", OpenPort);
						if(OpenPortJsonObject.length()>0){
						OpenPortJsonarray.put(OpenPortJsonObject);
						}
					}
					if(nextnode.hasProperty("Operators")){
						Operators=nextnode.getProperty("Operators").getString();
						
						JSONObject OperatorsJsonaobject=new JSONObject();
						OperatorsJsonaobject.put("Operators", Operators);
						if(OperatorsJsonaobject.length()>0){
						OperatorsJsonarray.put(OperatorsJsonaobject);
						}
					}
					if(nextnode.hasProperty("CargoType")){
						CargoType=nextnode.getProperty("CargoType").getString();
						
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
					if(nextnode.hasProperty("RepositionRegion")){
						RepositionRegion=nextnode.getProperty("RepositionRegion").getString();
						
						JSONObject RepositionRegionJsonaobject=new JSONObject();
						RepositionRegionJsonaobject.put("RepositionRegion", RepositionRegion);
						if(RepositionRegionJsonaobject.length()>0){
						RepositionRegionJsonarray.put(RepositionRegionJsonaobject);
						}
					}
					if(nextnode.hasProperty("Source")){
						Source=nextnode.getProperty("Source").getString();
						
						JSONObject SourceJsonaobject=new JSONObject();
						SourceJsonaobject.put("Source", Source);
						if(SourceJsonaobject.length()>0){
						SourceJsonarray.put(SourceJsonaobject);
						}
					}
					if(nextnode.hasProperty("VesselName")){
						VesselName=nextnode.getProperty("VesselName").getString();
						
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
						
						JSONObject VesselTypeJsonaobject=new JSONObject();
						VesselTypeJsonaobject.put("VesselType", VesselType);
						if(VesselTypeJsonaobject.length()>0){
							VesselTypejsonarray.put(VesselTypeJsonaobject);
						}
					}
					
					keyNameObject=new JSONObject();
					
					keyNameObject.put("tonnageId", Id);
					keyNameObject.put("dateAndTime", dateAndTime);
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
					keyNameObject.put("subNodeName", subNodeName);
					keyNameObject.put("size", size+1);//parseId
					keyNameObject.put("parseId", parseId+1);
					keyNameObject.put("i", i);
					
					if(keyNameObject.length()>0){
					   keyNameObjectJsonarray.put(keyNameObject);
					}
					
				}// date and time boolean check
				
				
			}//  dateandtime blank check close
			
			
			}// while close 
			
			if(keyNameObjectJsonarray.length()>0){
				 mainJsonobj=new JSONObject();
				 mainJsonobj.put("tonnageList", keyNameObjectJsonarray);
				
//				out.println("mainJsonobj: "+mainJsonobj);
			}
			
			
			
			Node ExcelSummaryRecord=null;
			if(Tonnage.hasNode("ExcelSummaryRecord")){
				ExcelSummaryRecord = Tonnage.getNode("ExcelSummaryRecord");
			}else{
				ExcelSummaryRecord = Tonnage.addNode("ExcelSummaryRecord");
				session.save();
			}
			
			String resultExcel=CreateExcelMethods.createExcelTonnage(out, mainJsonobj.toString());
			JSONObject jsonResExcel= new JSONObject(resultExcel);
			if(jsonResExcel.has("status") && jsonResExcel.getString("status").equals("S")) {
				
				 String fileName="";
				if(jsonResExcel.has("fileName")){
					 fileName=jsonResExcel.getString("fileName");
				}
				
				Node ExcelSummaryRecord_SubNode=null;
				if(ExcelSummaryRecord.hasNode(fileName)){
					ExcelSummaryRecord_SubNode = ExcelSummaryRecord.getNode(fileName);
				}else{
					ExcelSummaryRecord_SubNode = ExcelSummaryRecord.addNode(fileName);
					session.save();
				}
				
				ExcelSummaryRecord_SubNode.setProperty("summary_url", jsonResExcel.getString("fileUrl"));
				session.save();
				keyNameObject.put("summary_url", jsonResExcel.getString("fileUrl"));
				 
			 }else if(jsonResExcel.has("status") && jsonResExcel.getString("status").equals("E")) {
				
			 }
			
		} // node check close if
		
	} catch (Exception e) {
	   out.println(e.getMessage());
	   
	}
	 return mainJsonobj;
	
}

public static JSONObject fetchSpotDaywiseData(PrintWriter out, Session session){
	
	JSONObject mainJsonobj=null;
	try {
		Node scorpio = session.getRootNode().getNode("scorpio");
		Node Spot=scorpio.getNode("Spot");
		
		if(Spot.hasNodes()){
			long size=Spot.getNodes().getSize(); 
			String Id="";
			
			JSONObject keyNameObject=null;
			JSONArray keyNameObjectJsonarray=new JSONArray();
			
			NodeIterator itr=Spot.getNodes();
			int i=0;
			while(itr.hasNext()){
			Node nextnode=itr.nextNode();
			String dateAndTime="";
			
			if(nextnode.hasProperty("dateAndTime")){
				dateAndTime=nextnode.getProperty("dateAndTime").getString();
			}
			
			JSONObject dateJson=new JSONObject();
			boolean dateboolean=false;
			if(!GmailMethods.isNullString(dateAndTime)){
				Date date=GmailMethods.parseDate(dateAndTime);
				dateJson=formatDate_Month_Year_Day(date, out);
				
				int day=dateJson.getInt("day");
				int month=dateJson.getInt("month");
				int year=dateJson.getInt("year");
				
				dateboolean=CheckTodayDAte(day, month, year, out);
				
				if(dateboolean==true){
					int parseId=0;
					i++;
					String subNodeName=nextnode.getName();
					
					String Information="";
					String FixtureType="";
					String Charterer="";
					String Status="";
//					String Id="";
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
					if(nextnode.hasProperty("dateAndTime")){
						dateAndTime=nextnode.getProperty("dateAndTime").getString();
					}
					if(nextnode.hasProperty("Information")){
						Information=nextnode.getProperty("Information").getString();
						
						JSONObject InformationJsonObject=new JSONObject();
						InformationJsonObject.put("Information", Information);
						if(InformationJsonObject.length()>0){
							InformationJsonarray.put(InformationJsonObject);
						}
					}
					if(nextnode.hasProperty("FixtureType")){
						FixtureType=nextnode.getProperty("FixtureType").getString();
						
						JSONObject FixtureTypeJsonObject=new JSONObject();
						FixtureTypeJsonObject.put("FixtureType", FixtureType);
						if(FixtureTypeJsonObject.length()>0){
							FixtureTypeJsonarray.put(FixtureTypeJsonObject);
						}
					}
					if(nextnode.hasProperty("Charterer")){
						Charterer=nextnode.getProperty("Charterer").getString();
						
						JSONObject ChartererJsonObject=new JSONObject();
						 ChartererJsonObject.put("Charterer", Charterer);
						if(ChartererJsonObject.length()>0){
						   ChartererJsonarray.put(ChartererJsonObject);
						}
					}
					if(nextnode.hasProperty("Status")){
						Status=nextnode.getProperty("Status").getString();
						
						JSONObject StatusJsonobject=new JSONObject();
						StatusJsonobject.put("Status", Status);
						if(StatusJsonobject.length()>0){
							StatusJsonarray.put(StatusJsonobject);
						}
					}
					if(nextnode.hasProperty("CargoType")){
						CargoType=nextnode.getProperty("CargoType").getString();
						
						JSONObject CargoTypeJsonaobject=new JSONObject();
						CargoTypeJsonaobject.put("CargoType", CargoType);
						if(CargoTypeJsonaobject.length()>0){
						CargoTypeJsonarray.put(CargoTypeJsonaobject);
						}
					}
					if(nextnode.hasProperty("CargoGrade")){
						CargoGrade=nextnode.getProperty("CargoGrade").getString();
						
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
						
						JSONObject LoadPortJsonaobject=new JSONObject();
						LoadPortJsonaobject.put("LoadPort", LoadPort);
						if(LoadPortJsonaobject.length()>0){
							LoadPortJsonarray.put(LoadPortJsonaobject);
						}
					}
					if(nextnode.hasProperty("DiscPort")){
						DiscPort=nextnode.getProperty("DiscPort").getString();
						
						JSONObject DiscPortJsonaobject=new JSONObject();
						DiscPortJsonaobject.put("DiscPort", DiscPort);
						if(DiscPortJsonaobject.length()>0){
							DiscPortJsonarray.put(DiscPortJsonaobject);
						}
					}
					if(nextnode.hasProperty("VesselName")){
						VesselName=nextnode.getProperty("VesselName").getString();
						
						JSONObject VesselNameJsonaobject=new JSONObject();
						VesselNameJsonaobject.put("VesselName", VesselName);
						if(VesselNameJsonaobject.length()>0){
							VesselNameJsonarray.put(VesselNameJsonaobject);
						}
					}
					if(nextnode.hasProperty("RateType")){
						RateType=nextnode.getProperty("RateType").getString();
						
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
						
						JSONObject SourceJsonaobject=new JSONObject();
						SourceJsonaobject.put("Source", Source);
						if(SourceJsonaobject.length()>0){
							SourceJsonarray.put(SourceJsonaobject);
						}
					}
					if(nextnode.hasProperty("Vesseltype")){
						String Vesseltype="";
						Vesseltype=nextnode.getProperty("Vesseltype").getString();
						
						JSONObject VesseltypeJsonaobject=new JSONObject();
						VesseltypeJsonaobject.put("Vesseltype", Vesseltype);
						if(VesseltypeJsonaobject.length()>0){
							VesseltypeJsonarray.put(VesseltypeJsonaobject);
						}
					}
					
					keyNameObject =new JSONObject();
					
					keyNameObject.put("spotId", Id);
					keyNameObject.put("dateAndTime", dateAndTime);
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
					keyNameObject.put("subNodeName", subNodeName);
					keyNameObject.put("size", size+1);//parseId
					keyNameObject.put("parseId", parseId+1);
					keyNameObject.put("i", i);
					
					if(keyNameObject.length()>0){
					keyNameObjectJsonarray.put(keyNameObject);
					}
					
				}// date and time boolean check
				
				
			}//  dateandtime blank check close
			
			
			}// while close 
			
			if(keyNameObjectJsonarray.length()>0){
				 mainJsonobj=new JSONObject();
				 mainJsonobj.put("spotList", keyNameObjectJsonarray);
				
//				out.println(mainJsonobj);
			}
			
			Node ExcelSummaryRecord=null;
			if(Spot.hasNode("ExcelSummaryRecord")){
				ExcelSummaryRecord = Spot.getNode("ExcelSummaryRecord");
			}else{
				ExcelSummaryRecord = Spot.addNode("ExcelSummaryRecord");
				session.save();
			}
			
			String resultExcel=CreateExcelMethods.createExcelSpot(out, mainJsonobj.toString());
			JSONObject jsonResExcel= new JSONObject(resultExcel);
			if(jsonResExcel.has("status") && jsonResExcel.getString("status").equals("S")) {
				
				 String fileName="";
				if(jsonResExcel.has("fileName")){
					 fileName=jsonResExcel.getString("fileName");
				}
				
				Node ExcelSummaryRecord_SubNode=null;
				if(ExcelSummaryRecord.hasNode(fileName)){
					ExcelSummaryRecord_SubNode = ExcelSummaryRecord.getNode(fileName);
				}else{
					ExcelSummaryRecord_SubNode = ExcelSummaryRecord.addNode(fileName);
					session.save();
				}
				
				ExcelSummaryRecord_SubNode.setProperty("summary_url", jsonResExcel.getString("fileUrl"));
				session.save();
				keyNameObject.put("summary_url", jsonResExcel.getString("fileUrl"));
				 
			 }else if(jsonResExcel.has("status") && jsonResExcel.getString("status").equals("E")) {
				
			 }
			
			
			
		} // node check close if
		
	} catch (Exception e) {
	   out.println(e.getMessage());
	   
	}
	 return mainJsonobj;
	
}


public static JSONObject fetchTimeCharterReportsDaywiseData(PrintWriter out, Session session){
	
	JSONObject mainJsonobj=null;
	try {
		Node scorpio = session.getRootNode().getNode("scorpio");
		Node TCReports=scorpio.getNode("TCReports");
		Node TimeCharterReports=TCReports.getNode("TimeCharterReport");
		
		if(TimeCharterReports.hasNodes()){
			long size=TimeCharterReports.getNodes().getSize(); 
			String Id="";
			
			JSONObject keyNameObject=null;
			JSONArray keyNameObjectJsonarray=new JSONArray();
			
			NodeIterator itr=TimeCharterReports.getNodes();
			int i=0;
			while(itr.hasNext()){
			Node nextnode=itr.nextNode();
			String dateAndTime="";
			
			if(nextnode.hasProperty("dateAndTime")){
				dateAndTime=nextnode.getProperty("dateAndTime").getString();
			}
			
			JSONObject dateJson=new JSONObject();
			boolean dateboolean=false;
			if(!GmailMethods.isNullString(dateAndTime)){
				Date date=GmailMethods.parseDate(dateAndTime);
				dateJson=formatDate_Month_Year_Day(date, out);
				
				int day=dateJson.getInt("day");
				int month=dateJson.getInt("month");
				int year=dateJson.getInt("year");
				
				dateboolean=CheckTodayDAte(day, month, year, out);
				
				if(dateboolean==true){
					i++;
					int parseId=0;
					String subNodeName=nextnode.getName();
					
					String Information="";
					String Charterer="";
//					String Id="";
					String Source="";
					String VesselName="";
					String Comments="";
					String Rate="";
					
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
					if(nextnode.hasProperty("dateAndTime")){
						dateAndTime=nextnode.getProperty("dateAndTime").getString();
					}
					if(nextnode.hasProperty("Information")){
						Information=nextnode.getProperty("Information").getString();
						
						JSONObject InformationJsonObject=new JSONObject();
						InformationJsonObject.put("Information", Information);
						if(InformationJsonObject.length()>0){
							InformationJsonarray.put(InformationJsonObject);
						}
					}
					if(nextnode.hasProperty("Spot_TC")){
						String Spot_TC="";
						Spot_TC=nextnode.getProperty("Spot_TC").getString();
						
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
						
						JSONObject VesselNameJsonaobject=new JSONObject();
						VesselNameJsonaobject.put("VesselName", VesselName);
						if(VesselNameJsonaobject.length()>0){
							VesselNameJsonarray.put(VesselNameJsonaobject);
						}
					}
					
					
					if(nextnode.hasProperty("CPP_DPP")){
						String CPP_DPP="";
						CPP_DPP=nextnode.getProperty("CPP_DPP").getString();
						
						JSONObject CPP_DPPJsonobject=new JSONObject();
						CPP_DPPJsonobject.put("CPP_DPP", CPP_DPP);
						if(CPP_DPPJsonobject.length()>0){
							CPP_DPPJsonarray.put(CPP_DPPJsonobject);
						}
					}
					if(nextnode.hasProperty("Charterer")){
						Charterer=nextnode.getProperty("Charterer").getString();
						
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
						
						JSONObject SourceJsonaobject=new JSONObject();
						SourceJsonaobject.put("Source", Source);
						if(SourceJsonaobject.length()>0){
							SourceJsonarray.put(SourceJsonaobject);
						}
					}
					
					if(nextnode.hasProperty("Vesseltype")){
						String Vesseltype="";
						Vesseltype=nextnode.getProperty("Vesseltype").getString();
						
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
					keyNameObject.put("dateAndTime", dateAndTime);
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
					keyNameObject.put("subNodeName", subNodeName);
					keyNameObject.put("size", size+1);//parseId
					keyNameObject.put("parseId", parseId+1);
					keyNameObject.put("i", i);
					
					if(keyNameObject.length()>0){
					  keyNameObjectJsonarray.put(keyNameObject);
					}
					
				}// date and time boolean check
				
				
			}//  dateandtime blank check close
			
			
			}// while close 
			
			if(keyNameObjectJsonarray.length()>0){
				 mainJsonobj=new JSONObject();
				 mainJsonobj.put("TimeCharterReportsList", keyNameObjectJsonarray);
				
//				out.println(mainJsonobj);
			}
			
			
			Node ExcelSummaryRecord=null;
			if(TimeCharterReports.hasNode("ExcelSummaryRecord")){
				ExcelSummaryRecord = TimeCharterReports.getNode("ExcelSummaryRecord");
			}else{
				ExcelSummaryRecord = TimeCharterReports.addNode("ExcelSummaryRecord");
				session.save();
			}
			
			String resultExcel=CreateExcelMethods.createExcelTimeCharterReports(out, mainJsonobj.toString());
			JSONObject jsonResExcel= new JSONObject(resultExcel);
			if(jsonResExcel.has("status") && jsonResExcel.getString("status").equals("S")) {
				
				 String fileName="";
				if(jsonResExcel.has("fileName")){
					 fileName=jsonResExcel.getString("fileName");
				}
				
				Node ExcelSummaryRecord_SubNode=null;
				if(ExcelSummaryRecord.hasNode(fileName)){
					ExcelSummaryRecord_SubNode = ExcelSummaryRecord.getNode(fileName);
				}else{
					ExcelSummaryRecord_SubNode = ExcelSummaryRecord.addNode(fileName);
					session.save();
				}
				
				ExcelSummaryRecord_SubNode.setProperty("summary_url", jsonResExcel.getString("fileUrl"));
				session.save();
				keyNameObject.put("summary_url", jsonResExcel.getString("fileUrl"));
				 
			 }else if(jsonResExcel.has("status") && jsonResExcel.getString("status").equals("E")) {
				
			 }
			
			
		} // node check close if
		
	} catch (Exception e) {
	   out.println(e.getMessage());
	   
	}
	 return mainJsonobj;
	
}

public static String fetchBrokerTcRateDaywiseData(PrintWriter out, Session session){
	
	JSONObject mainJsonobj=null;
	try {
		Node scorpio = session.getRootNode().getNode("scorpio");
		Node TCReports=scorpio.getNode("TCReports");
		Node TimeCharterReports=TCReports.getNode("BrokerTCRate");
		
		if(TimeCharterReports.hasNodes()){
			
			JSONObject keyNameObject=null;
			long size=TimeCharterReports.getNodes().getSize();
			JSONArray keyNameObjectJsonarray=new JSONArray();
			
			NodeIterator itr=TimeCharterReports.getNodes();
			int i=0;
			while(itr.hasNext()){
			Node nextnode=itr.nextNode();
			
//			String subNodeName=nextnode.getName();
			
			String dateAndTime="";
			
			if(nextnode.hasProperty("dateAndTime")){
				dateAndTime=nextnode.getProperty("dateAndTime").getString();
			}
			
			JSONObject dateJson=new JSONObject();
			boolean dateboolean=false;
			if(!GmailMethods.isNullString(dateAndTime)){
				Date date=GmailMethods.parseDate(dateAndTime);
				dateJson=formatDate_Month_Year_Day(date, out);
				
				int day=dateJson.getInt("day");
				int month=dateJson.getInt("month");
				int year=dateJson.getInt("year");
				
				dateboolean=CheckTodayDAte(day, month, year, out);
				
				if(dateboolean==true){
					String Id="";
					int parseId=0;
					i++;
					
					JSONArray BrokerJsonarray=new JSONArray();
					JSONArray VesselTypeJsonarray=new JSONArray();
					JSONArray Month_YearJsonarray=new JSONArray();
					JSONArray first_yrJsonarray=new JSONArray();
					JSONArray second_yrJsonarray=new JSONArray();
					JSONArray third_yrJsonarray= new JSONArray();
					JSONArray fifth_yrJsonarray= new JSONArray();
					
					String subNodeName=nextnode.getName();
					
					if(nextnode.hasProperty("Id")){
						
						Id=nextnode.getProperty("Id").getString();
						parseId=Integer.parseInt(Id);
					}
					if(nextnode.hasProperty("dateAndTime")){
						dateAndTime=nextnode.getProperty("dateAndTime").getString();
					}
					if(nextnode.hasProperty("Broker")){
						String Broker="";
						Broker=nextnode.getProperty("Broker").getString();
						
						JSONObject BrokerJsonObject=new JSONObject();
						BrokerJsonObject.put("Broker", Broker);
						if(BrokerJsonObject.length()>0){
							BrokerJsonarray.put(BrokerJsonObject);
						}
					}
					if(nextnode.hasProperty("VesselType")){
						String VesselType="";
						VesselType=nextnode.getProperty("VesselType").getString();
						
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
					keyNameObject.put("dateAndTime", dateAndTime);
					keyNameObject.put("BrokerJsonarray", BrokerJsonarray);
					keyNameObject.put("VesselTypeJsonarray", VesselTypeJsonarray);
					keyNameObject.put("Month_YearJsonarray", Month_YearJsonarray);
					keyNameObject.put("first_yrJsonarray", first_yrJsonarray);
					keyNameObject.put("second_yrJsonarray", second_yrJsonarray);
					keyNameObject.put("third_yrJsonarray", third_yrJsonarray);
					keyNameObject.put("fifth_yrJsonarray", fifth_yrJsonarray);
					
					keyNameObject.put("subNodeName", subNodeName);
					keyNameObject.put("size", size+1);//parseId
					keyNameObject.put("parseId", parseId+1);
					keyNameObject.put("i", i);
					
					if(keyNameObject.length()>0){
						   keyNameObjectJsonarray.put(keyNameObject);
						}
					
					
				}// date and time boolean check
				
				
			}//  dateandtime blank check close
			
			
			}// while close 
			
			if(keyNameObjectJsonarray.length()>0){
				 mainJsonobj=new JSONObject();
				 mainJsonobj.put("BrokerTcRateList", keyNameObjectJsonarray);
				
//				out.println(mainJsonobj);
			}
			
			Node ExcelSummaryRecord=null;
			if(TimeCharterReports.hasNode("ExcelSummaryRecord")){
				ExcelSummaryRecord = TimeCharterReports.getNode("ExcelSummaryRecord");
			}else{
				ExcelSummaryRecord = TimeCharterReports.addNode("ExcelSummaryRecord");
				session.save();
			}
			
			String resultExcel=CreateExcelMethods.createExcelBrokerTcRate(out, mainJsonobj.toString());
			JSONObject jsonResExcel= new JSONObject(resultExcel);
			if(jsonResExcel.has("status") && jsonResExcel.getString("status").equals("S")) {
				
				 String fileName="";
				if(jsonResExcel.has("fileName")){
					 fileName=jsonResExcel.getString("fileName");
				}
				
				Node ExcelSummaryRecord_SubNode=null;
				if(ExcelSummaryRecord.hasNode(fileName)){
					ExcelSummaryRecord_SubNode = ExcelSummaryRecord.getNode(fileName);
				}else{
					ExcelSummaryRecord_SubNode = ExcelSummaryRecord.addNode(fileName);
					session.save();
				}
				
				ExcelSummaryRecord_SubNode.setProperty("summary_url", jsonResExcel.getString("fileUrl"));
				session.save();
				keyNameObject.put("summary_url", jsonResExcel.getString("fileUrl"));
				 
			 }else if(jsonResExcel.has("status") && jsonResExcel.getString("status").equals("E")) {
				
			 }
			
			
			
			
		} // node check close if
		
	} catch (Exception e) {
	   out.println(e.getMessage());
	   
	}
	 return mainJsonobj.toString();
	
}


public static JSONObject before_After_Substring(String original_Data , String splitter_Data){
	 
	 JSONObject before_After_Data=null;
	 try {
		    
		    before_After_Data=new JSONObject();
//		    String str = "REProject_Configuration_Details";
//		    original_Data=original_Data.toLowerCase();
//			String splitter = "re";
			Pattern p = Pattern.compile("(.*?)" + splitter_Data + "(.*)");
			Matcher m = p.matcher(original_Data);
			String  before="";
			String after="";
			if (m.matches()) {
			    before = m.group(1); // may be empty
			    after = m.group(2); // may be empty
			   
			   before_After_Data.put("before", before);
			   before_After_Data.put("after", after);
//			   System.out.println(firstSubString);
//			   System.out.println(after);
			} else {
			    // splitter not found in str
				before="";
				after="";
				before_After_Data.put("before", before);
				before_After_Data.put("after", after);
				
			}
		 
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
	}
	return before_After_Data;
	 
}


public static void readGmailDatafromsling_fetchallMails(PrintWriter out, Session session){
	
	try {
		
		Node GmaildataCreatedRootnodeSling=null;
		
		if (session.getRootNode().hasNode("readslingnode")) {
			GmaildataCreatedRootnodeSling = session.getRootNode().getNode("readslingnode");
//			  out.println("File Exist!");
		}else {
//			 out.println("NO File Exist!");
		}
		
		if(GmaildataCreatedRootnodeSling.hasNodes()){
			NodeIterator mainNodeIterator=GmaildataCreatedRootnodeSling.getNodes();
			
			while (mainNodeIterator.hasNext()) {
				  Node mainFolder=mainNodeIterator.nextNode();
				  
//				  out.println("mainFolder: "+mainFolder.getName());
				  if(mainFolder.hasNodes()){
					 NodeIterator subFolderIterator= mainFolder.getNodes();
					 int i=0;
					  while(subFolderIterator.hasNext()){
						  Node subFolder= subFolderIterator.nextNode();
//						  out.println("subFolder: "+subFolder.getName());
						  
						  if(subFolder.getName().toString().contains(".txt")){	
							  out.println("subFolder_txt: "+subFolder.getName());
							  i++;
							  
							  if(subFolder.hasProperty("xml_file_link")){
								String xml_file_link= subFolder.getProperty("xml_file_link").getString();
								  out.println("xml_file_link: "+xml_file_link);
								  
								  String original_Data=subFolder.getName();
								  JSONObject s= before_After_Substring(original_Data, ".txt");
								  String replacefile="";
									if(s.has("before")){
										replacefile=s.getString("before");
										 out.println("before: "+replacefile);
									}
									
									   
										if(mainFolder.hasNode(replacefile)){
											 Node mainFolderAttachment= mainFolder.getNode(replacefile);
											 out.println("mainFolderAttachment: "+mainFolderAttachment.getName());
											 
											 if(mainFolderAttachment.hasNodes()){
												NodeIterator subFolderAttachmentIterator= mainFolderAttachment.getNodes();
												while(subFolderAttachmentIterator.hasNext()){
													Node FinalAttachmentFolder=subFolderAttachmentIterator.nextNode();
													out.println("FinalAttachmentFolder: "+FinalAttachmentFolder.getName());
													
													if(FinalAttachmentFolder.hasProperty("tomcat_file_link")){
														String tomcatFileLink=FinalAttachmentFolder.getProperty("tomcat_file_link").getString();
														  out.println("tomcatFileLink: "+tomcatFileLink);
														  JSONObject data=new JSONObject();
														  data.put("tomcatFileLink", tomcatFileLink);
														
													} // tomcat file link check
												} // attachsubfolder check
												
											 }// check attachment hash node
										  } // check replaced file
										
											
									
										
							  }// xmlfilelink close
							  
							  
							  
							  
							  
						  }// txt check subfolder
						  
						  
						  
						  
						  
					  } // while subfolder close
					  
//					  String data=tomcatFileLink.substring(tomcatFileLink.lastIndexOf("/")+1, tomcatFileLink.length());
						
//						if(tomcatFileLink!=""){
						if(mainFolder.hasNode("Email_Fields")){
							Node Email_Fields=mainFolder.getNode("Email_Fields");
							
							if(Email_Fields.hasNode("Attachment_With_Type")){
								Node Attachment_With_Type=Email_Fields.getNode("Attachment_With_Type");
								
								if(Attachment_With_Type.hasNodes()){
									NodeIterator itr=Attachment_With_Type.getNodes();
									while(itr.hasNext()){
										Node nextNode=itr.nextNode();
										
										if(nextNode.hasProperty("Attachment_Name_replace")){
										   String Attachment_Name_replace=nextNode.getProperty("Attachment_Name_replace").getString();
										  
										   /*if(Attachment_Name_replace.equals(data)){
											   
										   }
										   else{
//											   out.println("not match: "+subFolder.getName());
										   }*/
										
										}
									}
								}
							}
						}// email fields
//					}
					  
				  } // mainfolder has node check
				  
				  
			}// while close mainiterator
		}// check rootfolder has node
		
		
		
		
	} catch (Exception e) {
	   out.println(e.getMessage());
	}
}


public static JSONObject readGmailDatafromsling(PrintWriter out, Session session){
	JSONObject s=null;
	try {
		
		Node GmaildataCreatedRootnodeSling=null;
		String attachmentFolderName = null;
		int count = 0;
		if (session.getRootNode().hasNode("SentMailTime")) {
			GmaildataCreatedRootnodeSling = session.getRootNode().getNode("SentMailTime");
		}
		JSONArray dataarray=null;
		 dataarray=new JSONArray();
		 
		 
		 JSONArray innerarray=null;
		 
		if(GmaildataCreatedRootnodeSling.hasNodes()){
			NodeIterator mainNodeIterator=GmaildataCreatedRootnodeSling.getNodes();
			
			while (mainNodeIterator.hasNext()) {
				  Node mainFolder=mainNodeIterator.nextNode();
				  innerarray=new JSONArray();
				  if(mainFolder.hasNodes()){
					 NodeIterator subFolderIterator= mainFolder.getNodes();
					  while(subFolderIterator.hasNext()){
						  JSONObject  data=new JSONObject();
						  Node subFolder= subFolderIterator.nextNode();
						   
							if(subFolder.getName().contains(".txt")){
							if(subFolder.hasProperty("xml_file_link")){
									String xml_file_link=subFolder.getProperty("xml_file_link").getString();
									String emailContent=downloadEmail(xml_file_link);
									
								    String from=StringUtils.substringBetween(emailContent, "<field name=\"from\">", "</field>");
									String to=StringUtils.substringBetween(emailContent, "<field name=\"to\">", "</field>");
									String cc=StringUtils.substringBetween(emailContent, "<field name=\"cc\">", "</field>");
									String subject=StringUtils.substringBetween(emailContent, "<field name=\"subject\">", "</field>");
									String datetime=StringUtils.substringBetween(emailContent, "<field name=\"datetime\">", "</field>");
									String body=StringUtils.substringBetween(emailContent, "<field name=\"body\">", "</field>");
									
									/*String d[]=URLDecoder.decode(body, "UTF-8").split("&");
									out.println("d: "+d);*/
									
									data.put("xml_file_link", xml_file_link);
									data.put("Foldertxt", subFolder.getName());
									data.put("from", from);
									data.put("to", to);
									data.put("cc", cc);
									data.put("subject",subject);
									data.put("datetime", datetime);
									data.put("body", body);
								}

								attachmentFolderName=subFolder.getName().substring(0, subFolder.getName().lastIndexOf(".txt"));
								if(mainFolder.hasNode(attachmentFolderName)){
									Node  mainFolderAttachment= mainFolder.getNode(attachmentFolderName);
									
									 if(mainFolderAttachment.hasNodes()){
										NodeIterator subFolderAttachmentIterator= mainFolderAttachment.getNodes();
										while(subFolderAttachmentIterator.hasNext()){
											Node FinalAttachmentFolder=subFolderAttachmentIterator.nextNode();
											if(FinalAttachmentFolder.hasProperty("tomcat_file_link")){
												 String tomcatFileLink=FinalAttachmentFolder.getProperty("tomcat_file_link").getString();
												 data.put("tomcatFileLink", tomcatFileLink);
												 data.put("foldername", mainFolderAttachment.getName());
												
											} // tomcat file link check
										} // attachsubfolder check
										count++;
									 }// check attachment hash node
								  } // check replaced file

								innerarray.put(data);
							  
							}// .txt close
							 

					  }  // while subfolder close
					 
					 } // mainfolder has node check
				  JSONObject  data1=new JSONObject();
				  data1.put("email", innerarray);
				  dataarray.put(data1);
			}// while close mainiterator
			
		}// check rootfolder has node
//		out.println("dataarray: "+dataarray);  
		
		s=new JSONObject();
		s.put("emailList", dataarray);
		
	} catch (Exception e) {
	   out.println(e.getMessage());
	}
	
	return s;
}

public static String downloadEmail(String link) throws IOException {
	URL url = new URL(link);
//	System.out.println("url =======" + url);
	HttpURLConnection http = (HttpURLConnection) url.openConnection();
	http.setRequestMethod("GET");
	http.setUseCaches(false);
	http.setDoOutput(true);

	int responseCode = http.getResponseCode();
//	System.out.println("POST Response Code :: " + responseCode);
	StringBuffer buffer = new StringBuffer();
	//buffer.append(responseCode);
	if (responseCode == 200) { // success
		BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			buffer.append(inputLine);
		}
//		System.out.println(buffer.toString());
		in.close();
	} else {
//		System.out.println("POST request not worked");
	}
	return buffer.toString();
}

public static void readGmailDataFromSlingNode(PrintWriter out, Session session){
	
	try {
		
		Node scorpio=null;
		Node GmaildataCreatedRootnodeSling=null;
		
		if(session.getRootNode().hasNode("scorpio")){
			scorpio=session.getRootNode().getNode("scorpio");
		}
		
		if (session.getRootNode().hasNode("readslingnode")) {
			GmaildataCreatedRootnodeSling = session.getRootNode().getNode("readslingnode");
			  out.println("File Exist!");
		}else {
			 out.println("NO File Exist!");
		}
		
		
		
		
	} catch (Exception e) {
	   out.println(e.getMessage());
	}
}


	
}
