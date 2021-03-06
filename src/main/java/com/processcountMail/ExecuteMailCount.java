package com.processcountMail;

import java.io.PrintWriter;
import javax.jcr.Node;
import javax.jcr.Session;

public class ExecuteMailCount {
	
	
public static void executeMailCount(PrintWriter out, Session session, String dateWiseCheck, String Subject, String TimeStamp, String emailNodeIdLink){
		
		try {
			
			Node scorpio=null;
			Node Datewise=null;
			Node executeMailCount=null;
			Node ReportData=null;
			Node subNode=null;
			
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
			
			if(ReportData.hasNode("executeMailCount")){
				executeMailCount=ReportData.getNode("executeMailCount");
			}else{
				executeMailCount=ReportData.addNode("executeMailCount");
				 session.refresh(true);
				 session.save();
			}
			
			if(executeMailCount.hasNode(dateWiseCheck)){
				Datewise=executeMailCount.getNode(dateWiseCheck);
			}else{
				Datewise=executeMailCount.addNode(dateWiseCheck);
				Datewise.setProperty("jcr:count", String.valueOf(0));
				 session.refresh(true);
				 session.save();
			}
			
			 String count=Datewise.getProperty("jcr:count").getString();
			 String forid= String.valueOf(Integer.parseInt(count)+1);
			 
			 if(!Datewise.hasNode(forid)){
				 subNode=Datewise.addNode(forid);
				 
				 subNode.setProperty("Count", forid);
				 subNode.setProperty("Subject", Subject);
				 subNode.setProperty("TimeStamp", TimeStamp);
				 subNode.setProperty("emailNodeIdLink", emailNodeIdLink);
				
				 Datewise.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
				 System.out.println("node_Processed:: "+Integer.parseInt(count)+1+" TimeStamp:: "+TimeStamp);
				 session.refresh(true);
				 session.save();
			 }
			 
			
		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}
	
	
}

