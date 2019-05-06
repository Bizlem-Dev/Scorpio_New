package com.anagha;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import com.pallavi.code.secondStepMethodCall;
import com.readGmail.GmailMethods;
import com.reportinformationsystem.FifteenMinuteClass;
import com.reportinformationsystem.SaveReportDataClass;

public class pdfDataProcess {

	public static void main(String[] args) {

	}
	
public static void ReadGmailDataForPdf(Session session, PrintWriter out, String cronNodeName){
		
		try {
			
			SaveReportDataClass SDC=new SaveReportDataClass();
			Node rootNode=session.getRootNode();
			if(rootNode.hasNode("GmailData1")){
				Node GmailData=rootNode.getNode("GmailData1");
				if(GmailData.hasNodes()){
					NodeIterator gmailDataNodeItr=GmailData.getNodes();
					while(gmailDataNodeItr.hasNext()){
						Node subjectNode=gmailDataNodeItr.nextNode();
						
						if(subjectNode.hasNodes()){
							NodeIterator subjectNodeItr=subjectNode.getNodes();
							while(subjectNodeItr.hasNext()){
								  Node textNode=subjectNodeItr.nextNode();
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
								  
								  
								  if( !(textFlag.equals("1")) ){
								  if(textNode.hasNodes()){
										
										  if(!GmailMethods.isNullString(textSentMailTime)){
										      Date date1=new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(textSentMailTime);
										  if(date1!=null){
											  out.println("parsedDate: "+date1);
										  boolean schedulerReadMail= FifteenMinuteClass.fifteenDaysBeforeDataRead(date1);
											
										  if (schedulerReadMail == true) {
											  
										      String nodeNameRepaceunderscore=subjectNode.getName().toString();
										      nodeNameRepaceunderscore=nodeNameRepaceunderscore.replaceAll("_", " ");
 
										 NodeIterator textNodeItr=textNode.getNodes();
										 
										 while(textNodeItr.hasNext()){
											 Node textInsideAttachmentFolderNode=textNodeItr.nextNode();
											 if(textInsideAttachmentFolderNode.hasNodes()){
												 NodeIterator textInsideAttachmentFolderNodeItr=textInsideAttachmentFolderNode.getNodes();
												 int i=0;
												 while(textInsideAttachmentFolderNodeItr.hasNext()){
													 boolean attachmentFlag=false;
													   Node attachmentNode=textInsideAttachmentFolderNodeItr.nextNode();
													   i++;
													   String attachmentNodeFlag="";
													   if(attachmentNode.hasProperty("Flag")){
														   attachmentNodeFlag=attachmentNode.getProperty("Flag").getString();
														  // out.println("Flag :: "+attachmentNodeFlag);
													   }
													 
													  
													 if(!attachmentNodeFlag.equals("1")){
														
														 if(attachmentNode.hasProperty("tomcat_file_path")){
															  String attachmentTomcatFilePath=attachmentNode.getProperty("tomcat_file_path").getString();
															 
															    
															  if(!GmailMethods.isNullString(attachmentTomcatFilePath)){
																  
															  if(attachmentTomcatFilePath.contains(".pdf")){
																  attachmentFlag = secondStepMethodCall.pdfProcessData(session, out,
																		cronNodeName, subjectNode, textSentMailTime,
																		from_Source, timestampDate,
																		timestampDateAndTime, nodeNameRepaceunderscore,
																		attachmentNode, attachmentTomcatFilePath);
																  
															  }// pdf close
															 
									//.................................................................................................
															  
															  } // attachment check blank here
															  
															  } // has property attachment
														  
													 } // attachmentNodeFlag close
													 
													 
													   
												 }// textInsideAttachmentFolderNodeItr while close
											 } // textInsideAttachmentFolderNodeItr hashnodes check
											 
										 }// textNodeItr while close
										 
  }
										  }// null take
										  }else{
											  out.println("nulldateFound: "+textNode.getName().toString());
										  }
									
								  }
								  }
								
							}// subjectNodeItr while close
						} // subject node hasnodes
					
						
					}//gmailDataNodeItr while close
					
				} //hasnodes gmaildata
			}// root node 
			
		} catch (Exception e) {
			try {
				session.save();
			} catch (RepositoryException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace(out);
			}
			//e.printStackTrace(out);
			//out.println(e.getMessage());
		}
	}
	

}
