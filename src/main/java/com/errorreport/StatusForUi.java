package com.errorreport;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.jcr.Node;
import javax.jcr.Session;

import com.readGmail.GmailMethods;

public class StatusForUi {

	public static void main(String[] args) {
		String data=cronCurrentTimeParseHere();
		System.out.println(data);
	}
	
	public static void collectProcessDataStatus(PrintWriter out, Session session, String cronNodeName, String sentMailTime
			, String subject, String Attachment,String nlp1, String nlp2, String nlp3andnlp4qc, String Ui_Update_show_Data
			, String mailLink, String mainNodeName
			){
		
		try {
			
			Node scorpioDataBase=null;
			Node StatusUIProcess=null;
			Node cronNode=null;
			Node subjectNodeNode=null;
			
			if( session.getRootNode().hasNode("scorpioDataBase") ){
				scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
			}if( scorpioDataBase.hasNode("StatusUIProcess") ){
				StatusUIProcess=scorpioDataBase.getNode("StatusUIProcess");
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
					subjectNodeNode.setProperty("Nlp1", nlp1);
					subjectNodeNode.setProperty("Nlp2", nlp2);
					subjectNodeNode.setProperty("Nlp3_and_4_QC", nlp3andnlp4qc);
					subjectNodeNode.setProperty("Ui_Update", Ui_Update_show_Data);
					
				} // !node check in cronnode
				
				cronNode.setProperty("count",String.valueOf(Integer.parseInt(count)+1));
				session.save();
				
			} // cron count check
			
			
			
			
		} catch (Exception e) {
			
		}
		
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
					
					Calendar c = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

					if( !GmailMethods.isNullString(cronCurrentTimeMainNode) ){
						c.setTime(sdf.parse(cronCurrentTimeMainNode));
					}
					
					cronCurrentTimeLastProcessTime.setProperty("Last_Process_Time", c);
					cronNodeName=cronCurrentTimeLastProcessTime.getName().toString();
				} // cron node check here
				
				StatusUIProcess.setProperty("count",String.valueOf(Integer.parseInt(count)+1));
				session.save();
			} // check count has
		  
		  
	} catch (Exception e) {
		return "";
	}
	return cronNodeName;	
		
	}

}
