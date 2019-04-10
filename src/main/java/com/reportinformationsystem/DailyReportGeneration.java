package com.reportinformationsystem;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;

import com.readGmail.GmailMethods;

public class DailyReportGeneration {

	public static void main(String[] args) {

		
		
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

//			        out.println("date is previou");
			    	
			        dateBoolean=false;
//			    	dateBoolean=true;
			        
			    } else if (dateSpecified.equals(today)) {

			        out.println("date is today");
			    	
			        dateBoolean=true;
			    } 
			
			
		} catch (Exception e) {
			out.println(e.getMessage());
		}
		return dateBoolean; //ReadGmaiData1NodeForCountandotherdetails
	}
	
public static void ReadGmailData1CountAndOtherInformationInfo(Session session, PrintWriter out){
		
		try {
			
			Node rootNode=session.getRootNode();
			if(rootNode.hasNode("GmailData1")){
				Node GmailData=rootNode.getNode("GmailData1");
				//out.println("GmailData: "+GmailData.getName());
				if(GmailData.hasNodes()){
					NodeIterator gmailDataNodeItr=GmailData.getNodes();
					while(gmailDataNodeItr.hasNext()){
						Node subjectNode=gmailDataNodeItr.nextNode();
						//out.println("subjectNode: "+subjectNode.getName());
						if(subjectNode.hasNodes()){
							NodeIterator subjectNodeItr=subjectNode.getNodes();
							int i1=0;
							while(subjectNodeItr.hasNext()){
								  Node textNode=subjectNodeItr.nextNode();
								  i1++;
								 // out.println("textNode: "+textNode.getName());
								  String textSentMailTime="";
								  String textXmlFileLink="";
								  String textFlag="";
								  Date date1=null;
								  if(textNode.hasProperty("SentMailTime")){
									 textSentMailTime=textNode.getProperty("SentMailTime").getString();
									  date1=new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(textSentMailTime);
									 
								  }// timeStamp purpose
								  if(textNode.hasProperty("Flag")){
										 textFlag=textNode.getProperty("Flag").getString();
								  }// once saved again will not read this mail
								  
								  String from_Source="";
								  if(textNode.hasProperty("from_Source")){
									  from_Source=textNode.getProperty("from_Source").getString();
									  }
								  
								 
								
								  if(!GmailMethods.isNullString(textSentMailTime)){
									  boolean dateboolean=false; 
									  if (date1 != null) {
										  
										    Calendar cal = Calendar.getInstance();
											cal.setTime(date1);
											
											int day=cal.get(Calendar.DATE) ;
											int month=(cal.get(Calendar.MONTH) + 1) ;
											int year= cal.get(Calendar.YEAR);
											
											dateboolean=CheckTodayDAte(day, month, year, out);
									  
									  
									  if(dateboolean==true){
										  out.println("date1: "+date1);
									  out.println("texttime: "+textSentMailTime);
									  
										  
								  if(textNode.hasNodes()){
									 long htmlSize= textNode.getNodes().getSize();
									 
								  if(textNode.hasProperty("tomcat_file_path")){
									  String textTomcatFilePath=textNode.getProperty("tomcat_file_path").getString();
									 
									  
									  int htmlcount=0;
									  if(textTomcatFilePath.contains(".html")){
									    // out.println("textTomcatFilePath: "+textTomcatFilePath);
									     String emailUrl="";
										  if(textNode.hasProperty("file_link")){
											  emailUrl=textNode.getProperty("file_link").getString();
										  }
									  String subjectNodePath= subjectNode.getPath();
									  subjectNodePath = (GmailMethods.isNullString(subjectNodePath)) ? "" : subjectNodePath;
									 // out.println("subjectNodePath: "+subjectNodePath);
									  
									  htmlcount++;
									  
									  }
									
									out.println("htmlcount:: "+htmlcount);
									  
								  
									 }
								  } // html close
								  
								
								  
								  if(textNode.hasNodes()){
									 NodeIterator textNodeItr=textNode.getNodes();
									 
									 while(textNodeItr.hasNext()){
										 Node textInsideAttachmentFolderNode=textNodeItr.nextNode();
										 if(textInsideAttachmentFolderNode.hasNodes()){
											 NodeIterator textInsideAttachmentFolderNodeItr=textInsideAttachmentFolderNode.getNodes();
											 int i=0;
											 while(textInsideAttachmentFolderNodeItr.hasNext()){
												   Node attachmentNode=textInsideAttachmentFolderNodeItr.nextNode();
												   i++;
												   String attachmentNodeFlag="";
												   if(attachmentNode.hasProperty("Flag")){
													   attachmentNodeFlag=attachmentNode.getProperty("Flag").getString();
												   }
												 
													 if(attachmentNode.hasProperty("tomcat_file_path")){
														  String attachmentTomcatFilePath=attachmentNode.getProperty("tomcat_file_path").getString();
														  
														  int pdfCount=0;
														  if(!GmailMethods.isNullString(attachmentTomcatFilePath)){
														  if(attachmentTomcatFilePath.contains(".pdf")){
															 
															  
															  String emailUrl="";
															  if(attachmentNode.hasProperty("tomcat_file_link")){
																  emailUrl=attachmentNode.getProperty("tomcat_file_link").getString();
																  emailUrl = (GmailMethods.isNullString(emailUrl)) ? "" : emailUrl;
																  
																  
															  }
															  
															  String subjectNodePath= subjectNode.getPath();
															  subjectNodePath = (GmailMethods.isNullString(subjectNodePath)) ? "" : subjectNodePath;
															 // out.println("subjectNodePath: "+subjectNodePath);
															  pdfCount++;
															  out.println("pdfCount:: "+pdfCount);
															  
														  }// pdf close
														 
														  int excelCount=0;
														  if( (attachmentTomcatFilePath.contains(".xls")) || (attachmentTomcatFilePath.contains(".xlsx")) || (attachmentTomcatFilePath.contains(".XLS")) || (attachmentTomcatFilePath.contains(".XLSX")) ){
//															 
															  
															  
															  String emailUrl="";
															  if(attachmentNode.hasProperty("tomcat_file_link")){
																  emailUrl=attachmentNode.getProperty("tomcat_file_link").getString();
																  emailUrl = (GmailMethods.isNullString(emailUrl)) ? "" : emailUrl;
																  
															  }
															  String subjectNodePath= subjectNode.getPath();
															  subjectNodePath = (GmailMethods.isNullString(subjectNodePath)) ? "" : subjectNodePath;
															  excelCount++;
															  out.println("excelCount:: "+excelCount);
															 														 
															  }
														  }// excel close
														  
//														
														  }
													  } // attachmentTomcatFilePath close
												
												   
											 }// textInsideAttachmentFolderNodeItr while close
										 } // textInsideAttachmentFolderNodeItr hashnodes check
										 
									 }// textNodeItr while close
									 
								  } // textNode hasnode check
							
							}// check timesent
							} // boolean today date check
							}
							//......
								  
								
							}// subjectNodeItr while close
						} // subject node hasnodes
						
						
						
				
					
				} //hasnodes gmaildata
			}// root node 

			
		} catch (Exception e) {
			out.println(e.getMessage());
		}
}




}
