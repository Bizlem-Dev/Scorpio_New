package com.abhishek;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.jcr.api.SlingRepository;

import com.abhishek.AbhishekScript;
import com.abhishek.VinayaScript;
import com.pallavi.code.pallavi__json3_execution;
import com.pallavi.code.secondStepMethodCall;
import com.readGmail.GmailMethods;
import com.reportinformationsystem.DateFromToApiReport;
import com.reportinformationsystem.ExpertScriptCall;
import com.reportinformationsystem.FifteenMinuteClass;
import com.reportinformationsystem.SaveReportDataClass;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.log4j.Logger;
import org.apache.felix.scr.annotations.Properties;

@Component(immediate = true, metatype = false)
@Service(value = javax.servlet.Servlet.class)
@Properties({ 
		@Property(name = "sling.servlet.paths", value = { "/testmyservlet" }),
		})

public class Myservlet extends SlingAllMethodsServlet {
	@Reference
	private SlingRepository repo;
	Session session = null;
	static ResourceBundle bundle = ResourceBundle.getBundle("config");
	final static Logger logger = Logger.getLogger(Myservlet.class);
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try{

		session = repo.login(new SimpleCredentials("admin", "admin".toCharArray()));
	    String subject=request.getParameter("subject");
	    String sentMailTime=request.getParameter("date");
		
	    ReadGmailDataToPassPythonApi(session, out, subject, sentMailTime);
		
		
		 
		
		}catch(Exception e){
//			e.printStackTrace(out);
		    out.println(e.getMessage());
		}
		
	}

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException, IOException {
	
	}
	
	
public static void ReadGmailDataToPassPythonApi(Session session, PrintWriter out, String subject, String date){
	
	try {
		
		SaveReportDataClass SDC=new SaveReportDataClass();
		Node rootNode=session.getRootNode();
		//out.println("rootNode: "+rootNode.getName());
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
							  
							  
							  long htmlSize=0;
							  if( !(textFlag.equals("1")) ){
							  if(textNode.hasNodes()){
								  //String subjectNodePath1= subjectNode.getPath();
								  if( subject.equals(textNode.getName().toString()) && date.equals(textSentMailTime)){
								  htmlSize= textNode.getNodes().getSize();
								  out.println("passdate: "+textSentMailTime);
								  Date date1=new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(textSentMailTime);
								  if(date1!=null){
									  out.println("parsedDate: "+date1);
								  boolean schedulerReadMail= FifteenMinuteClass.fifteenDaysBeforeDataRead(date1);
								 /*
								  Calendar cal = Calendar.getInstance();
									cal.setTime(date1);
									String receivedDate = cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
											+ cal.get(Calendar.YEAR);*/
								
									
//							  if(receivedOnlyDate.equals("7-3-2019")){
								  if (schedulerReadMail == true) {
									  logger.info("*********************Strting point ************************************************************************************************************");
									  logger.info("MainNode_Subject:: "+subjectNode.getName().toString());
									  logger.info("SubNode_mail_Html:: "+textNode.getName().toString());
									  logger.info("html_TimeStamp:: "+textSentMailTime);
									  
								 if(htmlSize==1){
//								 if(htmlSize>1){
								 
							  if(textNode.hasProperty("tomcat_file_path")){
								  String textTomcatFilePath=textNode.getProperty("tomcat_file_path").getString();
								
//									  out.print("kalka_html: "+receivedOnlyDate);
								  if(textTomcatFilePath.contains(".html")){
								    // out.println("textTomcatFilePath: "+textTomcatFilePath);
								     String emailUrl="";
									  if(textNode.hasProperty("file_link")){
										  emailUrl=textNode.getProperty("file_link").getString();
									  }
									  String subjectNodePath= subjectNode.getPath();
								      subjectNodePath = (GmailMethods.isNullString(subjectNodePath)) ? "" : subjectNodePath;
								      out.println("subjectNodePath: "+subjectNodePath);
								  
								    String ExpertScriptCallHere=ExpertScriptCall.postExpertScript(textTomcatFilePath, out);
								    
								    logger.info("ExpertCodeOutputHtml:: "+ExpertScriptCallHere);
								    logger.info("html_emailUrl:: "+emailUrl);
								    logger.info("html_textSentMailTime:: "+textSentMailTime);
								    logger.info("htmlTomcatFilePath:: "+textTomcatFilePath);
								    
								    String filepathfromourside="/home/ubuntu/TestedMailJSon/"+textNode.getName().toString()+"_"+timestampDate+".txt";
									  boolean checkjsonString=SaveReportDataClass.isJSONValid(ExpertScriptCallHere);
									    if(checkjsonString==true){
									    	out.println("ExpertCodeOutputHtml: "+ExpertScriptCallHere);
									        out.println("textTomcatFilePath: "+textTomcatFilePath);
									        out.println("textSentMailTime_html: "+textSentMailTime);
									       // String MycodeHere= AbhishekScript.excelHtmlScript(ExpertScriptCallHere, out);
									       String pallaviCodeHere= pallavi__json3_execution.UpdatedScriptForKnownAndUnknown(out, session, ExpertScriptCallHere, emailUrl, textSentMailTime, subjectNodePath, from_Source, timestampDate, timestampDateAndTime, filepathfromourside);
									       logger.info("htmlpallaviCodeHere:: "+pallaviCodeHere);
									        boolean checkjsonStringAbhishek=SaveReportDataClass.isJSONValid(pallaviCodeHere);
									        if(checkjsonStringAbhishek){
									        	out.println("pallaviCodeHereHtml: "+pallaviCodeHere);
									        	
										       // SDC.parseAllReportFromJsonToSave(out, session, pallaviCodeHere, emailUrl, textSentMailTime,subjectNodePath, from_Source, timestampDate, timestampDateAndTime, "html", ExpertScriptCallHere);
										          textNode.setProperty("Flag", "1");
												//  ExecuteMailCount.executeMailCount(out, session, receivedDate, subjectNode.getName(), timestampDateAndTime, subjectNode.getPath());
									        }
									    }
								  
								 
								 // relevalent data
								  
								  //session.save();
									    
								  }
								} //end
								//set flag also in parseall method inside
//							  }// date check equals 
//							  }
								 
//							  } 
//							  }// check without attachment read
							//} // textFlag check here
								 
							 } else if(htmlSize>1){
								  
							 
//							  if(textNode.hasNodes()){
								 NodeIterator textNodeItr=textNode.getNodes();
								 
								 while(textNodeItr.hasNext()){
									 Node textInsideAttachmentFolderNode=textNodeItr.nextNode();
//									 out.println("textInsideAttachmentFolderNode: "+textInsideAttachmentFolderNode.getName());
									 if(textInsideAttachmentFolderNode.hasNodes()){
										 NodeIterator textInsideAttachmentFolderNodeItr=textInsideAttachmentFolderNode.getNodes();
										 int i=0;
										 while(textInsideAttachmentFolderNodeItr.hasNext()){
											   Node attachmentNode=textInsideAttachmentFolderNodeItr.nextNode();
											   i++;
//											   out.println("attachmentNode: "+attachmentNode.getName());
											   String attachmentNodeFlag="";
											   if(attachmentNode.hasProperty("Flag")){
												   attachmentNodeFlag=attachmentNode.getProperty("Flag").getString();
												  // out.println("Flag :: "+attachmentNodeFlag);
											   }
											 
											  
											 if(!attachmentNodeFlag.equals("1")){
//											   if(attachmentNodeFlag.equals("1")){
//												 out.println("attachmentNode: "+attachmentNode);
												 if(attachmentNode.hasProperty("tomcat_file_path")){
													  String attachmentTomcatFilePath=attachmentNode.getProperty("tomcat_file_path").getString();
													 /* out.println("attachmentTomcatFilePath: "+attachmentTomcatFilePath);*/
													 
													 // if(attachmentTomcatFilePath.equals("/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/TcReports_1_TCReport.pdf")){
														  
//														  }else{
													  
													    logger.info("AttachmentHasattachmentTomcatFilePath:: "+attachmentTomcatFilePath);	
													    logger.info("SubjectNameattachmentTomcatFilePath:: "+attachmentNode.getName().toString());
													    
													  if(!GmailMethods.isNullString(attachmentTomcatFilePath)){
													  if(attachmentTomcatFilePath.contains(".pdf")){
														 // out.println("attachmentTomcatFilePath_pdf: "+attachmentTomcatFilePath);
														 // Date date1=new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(textSentMailTime);
														  //boolean schedulerReadMail= FifteenMinuteClass.fifteenDaysBeforeDataRead(date1);
														   /* Calendar cal = Calendar.getInstance();
															cal.setTime(date1);
															//String receivedOnlyDate = String.valueOf(cal.get(Calendar.DATE));
															String receivedOnlyDate = cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
																		+ cal.get(Calendar.YEAR);
															
															
														  if(receivedOnlyDate.equals("7-3-2019")){*/
														// if (schedulerReadMail == true) {
															  
//															  out.print("kalka_pdf: "+receivedOnlyDate);
															 
															// if(attachmentTomcatFilePath.equals("/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/TC_MARKET_REPORT_END_2018WEEK_41_1_weeklytc_report_1.pdf")){
															 // if(attachmentTomcatFilePath.equals("/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/Braemar_ACM_-_Eastern_CPP_LR_Report_-_04.03.19_1_BraemarACM_Clean_East_of_Suez_LR_Report_04.03.19.pdf")){
																 // out.print("inside match");
																  String emailUrl="";
														  if(attachmentNode.hasProperty("tomcat_file_link")){
															  emailUrl=attachmentNode.getProperty("tomcat_file_link").getString();
															  emailUrl = (GmailMethods.isNullString(emailUrl)) ? "" : emailUrl;
															  
														  }
														  
														  String subjectNodePath= subjectNode.getPath();
														  subjectNodePath = (GmailMethods.isNullString(subjectNodePath)) ? "" : subjectNodePath;
														  out.println("subjectNodePath: "+subjectNodePath);
														  String svgUrl = DateFromToApiReport.processPdftoSvg(attachmentTomcatFilePath);
														  String filepathfromourside="/home/ubuntu/TestedMailJSon/"+attachmentNode.getName().toString()+"_"+timestampDate+".txt";
														  if(!GmailMethods.isNullString(svgUrl)){
														       String vinayaScriptCall=VinayaScript.vinayaScript(svgUrl, timestampDateAndTime);
															    logger.info("PdfvinayaScriptCall:: "+vinayaScriptCall);
															    logger.info("Pdf_emailUrl:: "+emailUrl);
															    logger.info("Pdftimestamp:: "+textSentMailTime);
														      if( !( vinayaScriptCall.equals("false") )  ){
														      if( !GmailMethods.isNullString(vinayaScriptCall) ){
														           boolean checkjsonString=SaveReportDataClass.isJSONValid(vinayaScriptCall);
															     if(checkjsonString==true){
													          	   out.println("attachmentTomcatFilePath_pdf: "+attachmentTomcatFilePath);
													          	   out.println("vinayaScriptCall_Output_svgtopdf: "+vinayaScriptCall);
													          	   String pallaviCodeHere= pallavi__json3_execution.UpdatedScriptForKnownAndUnknown(out, session, vinayaScriptCall, emailUrl, textSentMailTime, subjectNodePath, from_Source, timestampDate, timestampDateAndTime, filepathfromourside);
													          	   logger.info("PdfpallaviCodeHere:: "+pallaviCodeHere);
													          	   boolean checkjsonStringfinalobj=SaveReportDataClass.isJSONValid(pallaviCodeHere);
																	    if(checkjsonStringfinalobj==true){
																	    	out.println("pallaviCodeHere_pdf: "+pallaviCodeHere);
																	    
																	    	//SDC.parseAllReportFromJsonToSave(out, session, pallaviCodeHere, emailUrl, textSentMailTime, subjectNodePath, from_Source, timestampDate, timestampDateAndTime, "pdf", vinayaScriptCall);
																	    }
													                   attachmentNode.setProperty("Flag", "1");
															   //  session.save();
															     
															} // null check pdfScript
														}
														  } // svg check close
//														  } //........  															  } // check 15  days wala
													  }// check if	
													  													  // false
														  //set flag also in parseall method inside
//													  }// equals
													  }// pdf close
													 
													  
//													 if( emailUrl.contains(".xls") || emailUrl.contains(".xlsx") ){
													  if( (attachmentTomcatFilePath.contains(".xls")) || (attachmentTomcatFilePath.contains(".xlsx")) || (attachmentTomcatFilePath.contains(".XLS")) || (attachmentTomcatFilePath.contains(".XLSX")) ){
//														  out.println("attachmentTomcatFilePath_excel: "+attachmentTomcatFilePath);
														  
														//  if(attachmentTomcatFilePath.equals("/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/BRAEMAR_ACM_-_CLEAN_LR1_LIST_BSS_YOSU_-_16_JANUARY_2019_1_BRAEMARACM_CLEAN_LR1_LIST_BSS_YOSU_16_JANUARY_2019.xlsx")){
														 // Date date1=new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(textSentMailTime);
														 // boolean schedulerReadMail= FifteenMinuteClass.fifteenDaysBeforeDataRead(date1);
														 /* Calendar cal = Calendar.getInstance();
															cal.setTime(date1);
															String receivedOnlyDate = cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
																	+ cal.get(Calendar.YEAR);
														
														
													  if(receivedOnlyDate.equals("7-3-2019")){*/
														 // if (schedulerReadMail == true) {
															  //}else{
//															  out.print("kalka_excel: "+receivedOnlyDate);
														  String emailUrl="";
														  if(attachmentNode.hasProperty("tomcat_file_link")){
															  emailUrl=attachmentNode.getProperty("tomcat_file_link").getString();
															  emailUrl = (GmailMethods.isNullString(emailUrl)) ? "" : emailUrl;
															  
														  }
														  String subjectNodePath= subjectNode.getPath();
														  subjectNodePath = (GmailMethods.isNullString(subjectNodePath)) ? "" : subjectNodePath;
														 // out.println("subjectNodePath: "+subjectNodePath);
														  
														        String ExpertScriptCallHere=ExpertScriptCall.postExpertScript(attachmentTomcatFilePath, out);
														        logger.info("ExpertScriptCallHere_Excel:: "+ExpertScriptCallHere);
														        logger.info("emailUrl_Excel:: "+emailUrl);
														        String filepathfromourside="/home/ubuntu/TestedMailJSon/"+attachmentNode.getName().toString()+"_"+timestampDate+".txt";
														       if( !( ExpertScriptCallHere.equals("false") )  ){
														       
														       boolean checkjsonString=SaveReportDataClass.isJSONValid(ExpertScriptCallHere);
																if(checkjsonString==true){
																 out.println("attachmentTomcatFilePath_excel: "+attachmentTomcatFilePath);
														         out.println("ExpertScriptexceloutput: "+ExpertScriptCallHere);
														         //String MycodeHere= AbhishekScript.excelHtmlScript(ExpertScriptCallHere, out);
														         String pallaviCodeHere= pallavi__json3_execution.UpdatedScriptForKnownAndUnknown(out, session, ExpertScriptCallHere, emailUrl, textSentMailTime, subjectNodePath, from_Source, timestampDate, timestampDateAndTime, filepathfromourside);
														         logger.info("pallaviCodeHere_Excel:: "+pallaviCodeHere);
														         
														         logger.info("*******************************endingPoint*********************************************************************************************");
														         boolean checkjsonStringAbhishek=SaveReportDataClass.isJSONValid(pallaviCodeHere);
															        if(checkjsonStringAbhishek){
															        	out.println("pallaviCodeHere_excel: "+pallaviCodeHere);
														               // SDC.parseAllReportFromJsonToSave(out, session, pallaviCodeHere, emailUrl, textSentMailTime,subjectNodePath, from_Source, timestampDate, timestampDateAndTime, "excel", ExpertScriptCallHere);
															        }
														             attachmentNode.setProperty("Flag", "1");
																//  session.save();
																}
															}
															
														 // } // chek 														  // set flag also in parseall method inside
														  }
													  }// excel close
													  
//													 } 
													  }
//												  } // attachmentTomcatFilePath close
												 
											 } // attachmentNodeFlag close
											  /*if(i==20){
												  break;
											  }*/
											   
										 }// textInsideAttachmentFolderNodeItr while close
									 } // textInsideAttachmentFolderNodeItr hashnodes check
									 
								 }// textNodeItr while close
								 
							  } // textNode hasnode check
							  }
								  }// null take
							  }
							  }
							  }
//						}// html not has 
							  
							
						}// subjectNodeItr while close
					} // subject node hasnodes
					
					
					
				}//gmailDataNodeItr while close
				
			} //hasnodes gmaildata
		}// root node 
		
	} catch (Exception e) {
		out.println(e.getMessage());
	}
}
	
}

