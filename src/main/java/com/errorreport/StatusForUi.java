package com.errorreport;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.readGmail.GmailMethods;
import com.reportinformationsystem.FifteenMinuteClass;
import com.reportinformationsystem.SaveReportDataClass;

public class StatusForUi {

	public static void main(String[] args) {
		
//		DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		 DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		 Date date = new Date();
		 System.out.println("CurrentDate: "+date);
		 
		 Calendar cal = GregorianCalendar.getInstance();
		 cal.add( Calendar.DAY_OF_YEAR, -3);
		 Date threeDaysAgo = cal.getTime();
		 String threeDaysBeforeDate=sdf.format(threeDaysAgo);
		 System.out.println("threeDaysBeforeDate: "+threeDaysBeforeDate);
		 try {
			Date cronDate = formatter.parse("18-04-2019 01:00:05");
			String cronDateString=formatter.format(cronDate);
			System.out.println("cronDateString: "+cronDateString);
			
			boolean f=returnTimeRange(cronDate);
			System.out.println(f);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 
	}
	
	public static Node collectProcessDataStatus(PrintWriter out, Session session, String cronNodeName, String sentMailTime
			, String subject, String Attachment,String nlp1, String nlp2, String nlp3andnlp4qc, String Ui_Update_show_Data
			, String mailLink, String mainNodeName, String jsonString, String brokerName
			){
		Node subjectNodeNode=null;
		try {
			
			Node scorpioDataBase=null;
			Node StatusUIProcess=null;
			Node cronNode=null;
			/*Node subjectNodeNode=null;*/
			
			if( session.getRootNode().hasNode("scorpioDataBase") ){
				scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
			}if( scorpioDataBase.hasNode("StatusUIProcess") ){
				StatusUIProcess=scorpioDataBase.getNode("StatusUIProcess");
				out.println("StatusUIProcess:: "+cronNodeName);
			}if(StatusUIProcess.hasNode(cronNodeName)){
				cronNode=StatusUIProcess.getNode(cronNodeName);
			}
			
			if(cronNode.hasProperty("count")){
				String count=cronNode.getProperty("count").getString();
				String cronId= String.valueOf(Integer.parseInt(count)+1);
				if(!cronNode.hasNode(cronId)){
					subjectNodeNode=cronNode.addNode(cronId);
					
					
					Calendar c = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
					
					if( !GmailMethods.isNullString(sentMailTime) ){
						c.setTime(sdf.parse(sentMailTime));
					}
					
					subjectNodeNode.setProperty("DateandTime", c);
					subjectNodeNode.setProperty("Subject", subject);
					subjectNodeNode.setProperty("mainNodeName", mainNodeName);
					subjectNodeNode.setProperty("emailUrl", mailLink);
					subjectNodeNode.setProperty("Attachment", Attachment);
					
				//	boolean reportFound=StatusForUi.statusCheckReportForUi(jsonString);
//					String yes_no="";
					/*if(reportFound==true){
						 yes_no="YES";
					}else{
						yes_no="NO";
					}
					*/
					subjectNodeNode.setProperty("Nlp1", "");
					subjectNodeNode.setProperty("Nlp2", "");
					subjectNodeNode.setProperty("Nlp3_and_4_QC", "");
					subjectNodeNode.setProperty("Ui_Update", "");
					subjectNodeNode.setProperty("brokerName", brokerName);
					
					
				} // !node check in cronnode
				
				cronNode.setProperty("count",String.valueOf(Integer.parseInt(count)+1));
				session.save();
				
			} // cron count check
			
			
			
			
		} catch (Exception e) {
			return subjectNodeNode;
		}
		return subjectNodeNode;
		
	}
	
	
	public static String cronCurrentTimeParseHere(){
		String datePass=null;
		try {
			 DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			 if(sdf!=null){
				 Date date = new Date();
				 if(date!=null){
					 
					String dateString = sdf.format(date);
					
					if( !GmailMethods.isNullString(dateString) ){
						 datePass=dateString;
					}
					 
				 } // date check
			 } // sdf check
			
			
			
		} catch (Exception e) {
			return "";
		}
		return datePass;
	}
	
	public static String nodeCreateCronInSling(PrintWriter out, Session session, String cronCurrentTimeMainNode){
		String cronNodeName=null;
	  try {
		  
		    Node scorpioDataBase=null;
			Node StatusUIProcess=null;
			Node cronCurrentTimeLastProcessTime=null;
			
			if( session.getRootNode().hasNode("scorpioDataBase") ){
				scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
			}if( scorpioDataBase.hasNode("StatusUIProcess") ){
				StatusUIProcess=scorpioDataBase.getNode("StatusUIProcess");
			}else{
				StatusUIProcess=scorpioDataBase.addNode("StatusUIProcess");
				StatusUIProcess.setProperty("count", String.valueOf(0));
				session.save();
			}
			
			if(StatusUIProcess.hasProperty("count")){
				String count=StatusUIProcess.getProperty("count").getString();
				String cronId= String.valueOf(Integer.parseInt(count)+1);
				if(!StatusUIProcess.hasNode(cronId)){
					cronCurrentTimeLastProcessTime=StatusUIProcess.addNode(cronId);
					cronCurrentTimeLastProcessTime.setProperty("count", String.valueOf(0));
					
					 DateFormat df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
					 Date d2 = df2.parse(cronCurrentTimeMainNode);
					if( d2!=null ){
						out.println("d2:: "+d2);
						cronCurrentTimeLastProcessTime.setProperty("Last_Process_Time", df2.format(d2));
					}
					
					cronNodeName=cronCurrentTimeLastProcessTime.getName().toString();

					StatusUIProcess.setProperty("count",String.valueOf(Integer.parseInt(count)+1));
					session.save();
				} // cron node check here
				
			} // check count has
		  
		  
	} catch (Exception e) {
		return "";
	}
	return cronNodeName;	
		
	}
	
	public static JSONObject fetchCronWiseDataForUi(PrintWriter out, Session session){
		JSONObject cronDataFinal=new JSONObject();
		try {
			Node scorpioDataBase=null;
			Node StatusUIProcess=null;
			
			if( session.getRootNode().hasNode("scorpioDataBase") ){
				scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
			}if( scorpioDataBase.hasNode("StatusUIProcess") ){
				StatusUIProcess=scorpioDataBase.getNode("StatusUIProcess");
			}
			
			if(StatusUIProcess.hasNodes()){
				NodeIterator itr=StatusUIProcess.getNodes();
				JSONArray subjectDataArray=new JSONArray();
				while(itr.hasNext()){
					Node nextNodeCron=itr.nextNode();
					String Last_Process_Time="";
					if(nextNodeCron.hasProperty("Last_Process_Time")){
						Last_Process_Time=nextNodeCron.getProperty("Last_Process_Time").getString();
					}
					
					if(nextNodeCron.hasNodes()){
						
						NodeIterator nextNodeCronItr=nextNodeCron.getNodes();
						
						
						while(nextNodeCronItr.hasNext()){
							Node nextNodeSubject=nextNodeCronItr.nextNode();
							JSONObject subjectData=new JSONObject();
							String Attachment="";
							String DateandTime="";
							String Nlp1="";
							String Nlp2="";
							String Nlp3_and_4_QC="";
							String Subject="";
							String Ui_Update="";
							String emailUrl="";
							String mainNodeName="";
							String brokerName="";
							
							
								if(nextNodeSubject.hasProperty("Attachment")){
									Attachment=nextNodeSubject.getProperty("Attachment").getString();
									subjectData.put("Attachment", Attachment);
								}if(nextNodeSubject.hasProperty("DateandTime")){
									String DateandTime1=nextNodeSubject.getProperty("DateandTime").getString();
									
									if(DateandTime1.lastIndexOf(".")!=-1){
										DateandTime1=DateandTime1.substring( 0,DateandTime1.lastIndexOf(".") );
										LocalDateTime localDateTime = LocalDateTime.parse(DateandTime1);
										DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
										DateandTime = localDateTime.format(formatter);
									}
									
									subjectData.put("DateandTime", DateandTime);
								}if(nextNodeSubject.hasProperty("Nlp1")){
									Nlp1=nextNodeSubject.getProperty("Nlp1").getString();
									subjectData.put("Nlp1", Nlp1);
								}if(nextNodeSubject.hasProperty("Nlp2")){
									Nlp2=nextNodeSubject.getProperty("Nlp2").getString();
									subjectData.put("Nlp2", Nlp2);
								}if(nextNodeSubject.hasProperty("Nlp3_and_4_QC")){
									Nlp3_and_4_QC=nextNodeSubject.getProperty("Nlp3_and_4_QC").getString();
									subjectData.put("Nlp3_and_4_QC", Nlp3_and_4_QC);
								}if(nextNodeSubject.hasProperty("Subject")){
									Subject=nextNodeSubject.getProperty("Subject").getString();
									subjectData.put("Subject", Subject);
								}if(nextNodeSubject.hasProperty("Ui_Update")){
									Ui_Update=nextNodeSubject.getProperty("Ui_Update").getString();
									subjectData.put("Ui_Update", Ui_Update);
								}if(nextNodeSubject.hasProperty("emailUrl")){
									emailUrl=nextNodeSubject.getProperty("emailUrl").getString();
									subjectData.put("emailUrl", emailUrl);
								}if(nextNodeSubject.hasProperty("mainNodeName")){
									mainNodeName=nextNodeSubject.getProperty("mainNodeName").getString();
									subjectData.put("mainNodeName", mainNodeName);
								}if(nextNodeSubject.hasProperty("brokerName")){
									brokerName=nextNodeSubject.getProperty("brokerName").getString();
									subjectData.put("brokerName", brokerName);
								}
								subjectData.put("Last_Process_Time", Last_Process_Time);
								
								subjectDataArray.put(subjectData);
							
						}// while cronnode for get subject node
						
					}

					
					
				} // statusuiprocess while close
				
				//out.println(subjectDataArray);
				
				cronDataFinal.put("cronData", subjectDataArray);
				
			} // check nodes
			
			
		} catch (Exception e) {
			
		}
		return cronDataFinal;
	}
	
	
	
	public static boolean statusCheckReportForUi(String jsonString){
		boolean reportFound=false;
		try {
			
			boolean jsoncheckCargoQty=SaveReportDataClass.isJSONValid(jsonString);
			if(jsoncheckCargoQty){
				JSONObject objectjson = new JSONObject(jsonString);
				Iterator<String> keys = objectjson.keys();
				while (keys.hasNext()) {
					
					String key = keys.next();
					
					Object keyValue = objectjson.get(key);
					if (keyValue instanceof JSONObject) {
						JSONObject tabledataJsonObj=null;
						tabledataJsonObj = (JSONObject)keyValue;
						
						if(tabledataJsonObj.has("table_data")){
							JSONArray allReportJsonArray=tabledataJsonObj.getJSONArray("table_data");
//							System.out.println(allReportJsonArray);
							
							if(allReportJsonArray.length()>0){
								
								for(int i=0;i<allReportJsonArray.length();i++){
									JSONObject allReportJsonArrayInsideJSonobject=allReportJsonArray.getJSONObject(i);
									if(allReportJsonArrayInsideJSonobject.has("ReportType")){
										 reportFound=true;
												break;
									}
									
									
								}// for loop close // first loop
								
									
								}  // length check all report
							
						}
						
					}
					
				} // while close dyanamic main keys tablewise
				
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reportFound;
	}
	
	public static String getReportTypeUi(String jsonString){
		String allReport="";
		try {
			
			boolean jsoncheckCargoQty=SaveReportDataClass.isJSONValid(jsonString);
			if(jsoncheckCargoQty){
				JSONObject objectjson = new JSONObject(jsonString);
				Iterator<String> keys = objectjson.keys();
				while (keys.hasNext()) {
					
					String key = keys.next();
					
					Object keyValue = objectjson.get(key);
					if (keyValue instanceof JSONObject) {
						JSONObject tabledataJsonObj=null;
						tabledataJsonObj = (JSONObject)keyValue;
						
						if(tabledataJsonObj.has("table_data")){
							JSONArray allReportJsonArray=tabledataJsonObj.getJSONArray("table_data");
//							System.out.println(allReportJsonArray);
							
							if(allReportJsonArray.length()>0){
								String ReportTypeString="";
								
								int spotCount=0;
								int tonnageCount=0;
								int tcCount=0;
								int btrCount=0;
								
								for(int i=0;i<allReportJsonArray.length();i++){
									JSONObject allReportJsonArrayInsideJSonobject=allReportJsonArray.getJSONObject(i);
									
									if(allReportJsonArrayInsideJSonobject.has("ReportType")){
										ReportTypeString=allReportJsonArrayInsideJSonobject.getString("ReportType");
										
										if(ReportTypeString.equals("Tonnage")){
											tonnageCount++;
										}else if(ReportTypeString.equals("Spot")){
											spotCount++;
										}else if(ReportTypeString.toLowerCase().equals("timecharterreports") || ReportTypeString.toLowerCase().equals("timecharter")){
											tcCount++;
										}else if( ReportTypeString.toLowerCase().equals("brokertcrate") || ReportTypeString.toLowerCase().equals("brokerrate")){
											btrCount++;
											
										}
										if( spotCount>=3 ){
											allReport=ReportTypeString;
											break;
										}else if( tonnageCount>=3 ){
											allReport=ReportTypeString;
											break;
										}else if( tcCount>=3){
											allReport=ReportTypeString;
											break;
										}else if( btrCount>=3 ){
											allReport=ReportTypeString;
											break;
										}
												
									}
									
									
								}// for loop close // first loop
								
									
								}  // length check all report
							
						}
						
					}
					
				} // while close dyanamic main keys tablewise
				
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allReport;
	}
	
	public static JSONObject fetchCronWiseDataForUiFromLast(PrintWriter out, Session session){
		JSONObject cronDataFinal=new JSONObject();
		try {
			Node scorpioDataBase=null;
			Node StatusUIProcess=null;
			
			if( session.getRootNode().hasNode("scorpioDataBase") ){
				scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
			}if( scorpioDataBase.hasNode("StatusUIProcess") ){
				StatusUIProcess=scorpioDataBase.getNode("StatusUIProcess");
				
			}
			
			if(StatusUIProcess.hasNodes()){
				//NodeIterator itr=StatusUIProcess.getNodes();
				JSONArray subjectDataArray=new JSONArray();
				long sizeCron=0;
				if(StatusUIProcess.hasProperty("count")){
					   String count=StatusUIProcess.getProperty("count").getString();
					   sizeCron= Long.valueOf(count);
					}
//				long sizeCron=StatusUIProcess.getNodes().getSize();
//				out.println("sizeCron: "+sizeCron);
				//sizeCron=StatusUIProcess.getNodes().getSize();
				long j=0;
//				while(itr.hasNext()){
				for(j=sizeCron;j>=0;j--){
					if(StatusUIProcess.hasNode(String.valueOf(j))){
//					Node nextNodeCron=itr.nextNode();
						Node nextNodeCron=StatusUIProcess.getNode( String.valueOf(j) );
//						out.println("j: "+j);
//						out.println("nextNodeCron: "+nextNodeCron.getName().toString());
						
					String Last_Process_Time="";
					if(nextNodeCron.hasProperty("Last_Process_Time")){
						Last_Process_Time=nextNodeCron.getProperty("Last_Process_Time").getString();
					}
					
					if( !GmailMethods.isNullString(Last_Process_Time) ){
						SimpleDateFormat formatte = new SimpleDateFormat("dd-MM-yyyy");
						 Date cronDate = formatte.parse(Last_Process_Time); 
						 boolean cronDateBoolean=returnTimeRange(cronDate);
							if (cronDateBoolean == true) {
					
					if(nextNodeCron.hasNodes()){
						
						//NodeIterator nextNodeCronItr=nextNodeCron.getNodes();
//						long size=nextNodeCron.getNodes().getSize();
						String countCron=nextNodeCron.getProperty("count").getString();
						long size=Long.valueOf(countCron);
						//size=nextNodeCron.getNodes().getSize();
						long i=0;
//						while(nextNodeCronItr.hasNext()){
						for(i=size;i>=0;i--){
//							Node nextNodeSubject=nextNodeCronItr.nextNode();
							if(nextNodeCron.hasNode(String.valueOf(i))){
								Node nextNodeSubject=nextNodeCron.getNode( String.valueOf(i) );
								//out.println("nextNodeSubject: "+nextNodeSubject.getName().toString());
								//out.println("i: "+i);
							JSONObject subjectData=new JSONObject();
							String Attachment="";
							String DateandTime="";
							String Nlp1="";
							String Nlp2="";
							String Nlp3_and_4_QC="";
							String Subject="";
							String Ui_Update="";
							String emailUrl="";
							String mainNodeName="";
							String brokerName="";
							
							
								if(nextNodeSubject.hasProperty("Attachment")){
									Attachment=nextNodeSubject.getProperty("Attachment").getString();
									subjectData.put("Attachment", Attachment);
								}if(nextNodeSubject.hasProperty("DateandTime")){
									String DateandTime1=nextNodeSubject.getProperty("DateandTime").getString();
									
									if(DateandTime1.lastIndexOf(".")!=-1){
										DateandTime1=DateandTime1.substring( 0,DateandTime1.lastIndexOf(".") );
										LocalDateTime localDateTime = LocalDateTime.parse(DateandTime1);
										DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
										DateandTime = localDateTime.format(formatter);
									}
									
									subjectData.put("DateandTime", DateandTime);
								}if(nextNodeSubject.hasProperty("Nlp1")){
									Nlp1=nextNodeSubject.getProperty("Nlp1").getString();
									subjectData.put("Nlp1", Nlp1);
								}if(nextNodeSubject.hasProperty("Nlp2")){
									Nlp2=nextNodeSubject.getProperty("Nlp2").getString();
									subjectData.put("Nlp2", Nlp2);
								}if(nextNodeSubject.hasProperty("Nlp3_and_4_QC")){
									Nlp3_and_4_QC=nextNodeSubject.getProperty("Nlp3_and_4_QC").getString();
									subjectData.put("Nlp3_and_4_QC", Nlp3_and_4_QC);
								}if(nextNodeSubject.hasProperty("Subject")){
									Subject=nextNodeSubject.getProperty("Subject").getString();
									subjectData.put("Subject", Subject);
								}if(nextNodeSubject.hasProperty("Ui_Update")){
									Ui_Update=nextNodeSubject.getProperty("Ui_Update").getString();
									subjectData.put("Ui_Update", Ui_Update);
								}if(nextNodeSubject.hasProperty("emailUrl")){
									emailUrl=nextNodeSubject.getProperty("emailUrl").getString();
									subjectData.put("emailUrl", emailUrl);
								}if(nextNodeSubject.hasProperty("mainNodeName")){
									mainNodeName=nextNodeSubject.getProperty("mainNodeName").getString();
									subjectData.put("mainNodeName", mainNodeName);
								}if(nextNodeSubject.hasProperty("brokerName")){
									brokerName=nextNodeSubject.getProperty("brokerName").getString();
									subjectData.put("brokerName", brokerName);
								}
								subjectData.put("Last_Process_Time", Last_Process_Time);
								
								subjectDataArray.put(subjectData);
								
						}// node check
							
						}// while cronnode for get subject node
						
					}// crondate threedaysbeforeDatecompare
						
					}// check lastprocessdate blank
					}

					
					}// status cron node check
				} // statusuiprocess while close
				
				//out.println(subjectDataArray);
				
				cronDataFinal.put("cronData", subjectDataArray);
				
			} // check nodes
			
			
		} catch (Exception e) {
			e.printStackTrace(out);
		}
		return cronDataFinal;
	}
	
	public static boolean returnTimeRange(Date cronDate) {

		boolean returnTimerange = false;
		try {

			Calendar cal = null;
			cal = Calendar.getInstance();
			Date todayDate = new Date(cal.getTimeInMillis()); // today date 
			//System.out.println("todayDate: "+todayDate);
			
			cal = Calendar.getInstance();
			cal.add( Calendar.DAY_OF_YEAR, -4);
			Date minusSate = cal.getTime(); // three days before date 
		//	System.out.println("minusSate: "+minusSate);
			if (cronDate.after(minusSate) && cronDate.before(todayDate)) {
				returnTimerange = true;
			}

		} catch (Exception e) {
		}
		return returnTimerange;
	}
	

}
