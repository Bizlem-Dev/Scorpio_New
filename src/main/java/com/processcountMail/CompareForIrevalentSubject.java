package com.processcountMail;

import java.io.PrintWriter;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

public class CompareForIrevalentSubject {
	
	
public static void irelevantFromRealMail(PrintWriter out, Session session){
		
		try {
			Node scorpio=null;
			Node ReportData=null;
			Node executeMailCount=null;
			Node saveMailCount=null;
			
			if(session.getRootNode().hasNode("scorpioDataBase")){
				scorpio = session.getRootNode().getNode("scorpioDataBase");
			}
			if(scorpio.hasNode("ReportData")){
				ReportData = scorpio.getNode("ReportData");
			}
			
			if(ReportData.hasNode("saveMailCount")){
				saveMailCount=ReportData.getNode("saveMailCount");
			}
			
			if(saveMailCount.hasNodes()){
				NodeIterator itr=saveMailCount.getNodes();
				while(itr.hasNext()){
					Node nextNodeGetDateNode= itr.nextNode();
					String date=nextNodeGetDateNode.getName().toString();
					RealMailCountCheck.realMailCountFind(out, date, session);
				}
			}
			
			
		} catch (Exception e) {
			
			
		}
	}
	
	

	public static void irelevalentSubjectData(PrintWriter out, Session session){
		
		try {
			Node scorpio=null;
			Node ReportData=null;
			Node executeMailCount=null;
			Node saveMailCount=null;
			
			if(session.getRootNode().hasNode("scorpioDataBase")){
				scorpio = session.getRootNode().getNode("scorpioDataBase");
			}
			if(scorpio.hasNode("ReportData")){
				ReportData = scorpio.getNode("ReportData");
			}
			if(ReportData.hasNode("executeMailCount")){
				executeMailCount=ReportData.getNode("executeMailCount");
			}
			if(ReportData.hasNode("saveMailCount")){
				saveMailCount=ReportData.getNode("saveMailCount");
			}
			
			if(executeMailCount.hasNodes()){
				NodeIterator itr=executeMailCount.getNodes();
				while(itr.hasNext()){
					Node nextNodeExecuteMailCount=itr.nextNode();
					
					if(saveMailCount.hasNodes()){
						NodeIterator itr1=saveMailCount.getNodes();
						while(itr1.hasNext()){
							Node nextNodesaveMailCount=itr1.nextNode();
							
							if(nextNodeExecuteMailCount.equals(nextNodesaveMailCount)){
								
							}else{
								out.println("irevalent:: "+nextNodesaveMailCount);
							}
							
						} //while save mail count 
					} // savemailcount has node check
					
				} // while execute
				
			}// execute has nodes check
			
			
			
		} catch (Exception e) {
			
			
		}
	}
	
	
	public static void irelevantFromReportWis(PrintWriter out, Session session, JSONObject data){
		JSONObject d=null;
		try {
			
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
			
			
			if(Spot.hasNodes()){
				NodeIterator itr=Spot.getNodes();
				while(itr.hasNext()){
					Node SpotHas=itr.nextNode();
					
					String timestampDateAndTime="";
					if(SpotHas.hasProperty("timestampDateAndTime")){
						timestampDateAndTime=SpotHas.getProperty("timestampDateAndTime").getString();
						
						if(data.has("Tonnage")){
							JSONArray TonnageData=data.getJSONArray("Tonnage");
							for(int i=0;i<TonnageData.length();i++){
								JSONObject jsonobj=TonnageData.getJSONObject(i);
								String subject="";
								if(jsonobj.has("subject")){
									subject=jsonobj.getString("subject");
									
								} // subject close
								String TimeStamp="";
								if(jsonobj.has("TimeStamp")){
									TimeStamp=jsonobj.getString("TimeStamp");
								}
								
								if(timestampDateAndTime.equals(TimeStamp)){
									
								}else{
									out.println("irelevant:: "+subject);
									
								}
								
							} // for close 
						}
						
					}
							
					
					
						} // while close
					
					
					
				} // if spot has check 
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace(out);
		}
		
	}
	
	public static JSONObject irelevantFromReportWise(PrintWriter out, Session session){
		JSONObject d=null;
		try {
			
			Node scorpio=null;
			Node ReportData=null;
			Node executeMailCount=null;
			Node saveMailCount=null;
			Node Tonnage=null;
			Node Spot=null;
			
			if(session.getRootNode().hasNode("scorpioDataBase")){
				scorpio = session.getRootNode().getNode("scorpioDataBase");
			}
			if(scorpio.hasNode("ReportData")){
				ReportData = scorpio.getNode("ReportData");
			}
			if(ReportData.hasNode("executeMailCount")){
				executeMailCount=ReportData.getNode("executeMailCount");
			}
			if(ReportData.hasNode("saveMailCount")){
				saveMailCount=ReportData.getNode("saveMailCount");
			}
			
			if(ReportData.hasNode("Tonnage")){
				Tonnage=ReportData.getNode("Tonnage");
			}
			
			if(ReportData.hasNode("Spot")){
				Spot=ReportData.getNode("Spot");
			}
			
			
			if(saveMailCount.hasNodes()){
				NodeIterator itr=saveMailCount.getNodes();
				while(itr.hasNext()){
					Node nextNodeSaveMailCount=itr.nextNode();
					
					if(nextNodeSaveMailCount.hasNodes()){
						NodeIterator itrhas=nextNodeSaveMailCount.getNodes();
						while(itrhas.hasNext()){
							Node nextInsideSavemailNode=itrhas.nextNode();
							String subject="";
							if(nextInsideSavemailNode.hasProperty("Subject")){
								 subject=nextInsideSavemailNode.getProperty("Subject").getString();
							}
							String TimeStamp="";
							if(nextInsideSavemailNode.hasProperty("TimeStamp")){
								TimeStamp=nextInsideSavemailNode.getProperty("TimeStamp").getString();
							}
							JSONArray s1=null;
							if(Tonnage.hasNodes()){
								NodeIterator itrTonnage=Tonnage.getNodes();
								while(itrTonnage.hasNext()){
									Node nextNodeTonnage=itrTonnage.nextNode();
									String timestampDateAndTime="";
									if(nextNodeTonnage.hasProperty("timestampDateAndTime")){
										timestampDateAndTime=nextNodeTonnage.getProperty("timestampDateAndTime").getString();
									}
									if(TimeStamp.equals(timestampDateAndTime)){
										
									}else{
										//out.println(subject);
										// put json and call in spot then we get final result 
										JSONObject s=new JSONObject();
										s.put("subject", subject);
										s.put("TimeStamp", TimeStamp);
										 s1=new JSONArray();
										 s1.put(s);
									}
								}
								
							}//
							
							if(s1.length()>0){
								 d=new JSONObject();
								 d.put("Tonnage", d);
							}
							
						}
					}
					
					
				}// while savemailcount
			}// savemail has nodes check
			
			
			
		} catch (Exception e) {
			e.printStackTrace(out);
		}
		return d;
	}
	
public static void saveRealMailCount(PrintWriter out, Session session, String dateWiseCheck, String Subject, String Count){
		
		try {
			
			Node scorpio=null;
			Node Datewise=null;
			Node saveMailCount=null;
			Node ReportData=null;
			Node subNode=null;
			
			if(session.getRootNode().hasNode("scorpioDataBase")){
				scorpio = session.getRootNode().getNode("scorpioDataBase");
			}else{
				scorpio = session.getRootNode().addNode("scorpioDataBase");
				// session.refresh(true);
				 session.save();
			}
			
			if(scorpio.hasNode("ReportData")){
				ReportData = scorpio.getNode("ReportData");
			}else{
				ReportData = scorpio.addNode("ReportData");
				// session.refresh(true);
				 session.save();
			}
			
			if(ReportData.hasNode("GmailReadCount")){
				saveMailCount=ReportData.getNode("GmailReadCount");
			}else{
				saveMailCount=ReportData.addNode("GmailReadCount");
				// session.refresh(true);
				 session.save();
			}
			
			if(saveMailCount.hasNode(dateWiseCheck)){
				Datewise=saveMailCount.getNode(dateWiseCheck);
			}else{
				Datewise=saveMailCount.addNode(dateWiseCheck);
				Datewise.setProperty("jcr:count", String.valueOf(0));
				// session.refresh(true);
				 session.save();
			}
				 
			Datewise.setProperty("Count", Count);
			Datewise.setProperty("Subject", Subject);
				
				 session.save();
			 
			
		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}
	
	
}
