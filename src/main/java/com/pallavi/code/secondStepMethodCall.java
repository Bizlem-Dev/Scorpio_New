package com.pallavi.code;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.ValueFormatException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.version.VersionException;

import org.apache.log4j.Logger;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import com.abhishek.VinayaScript;
import com.errorreport.StatusForUi;
import com.mycode.MethodJsonOnlyInsert;
import com.mycode.Methods;
import com.mycode.OutputReasponse;
import com.mycode.ReportTypeCorrection;
import com.mycode.ReportTypeIdentify;
import com.mycode.SaveReportDataClassNewStr;
import com.mycode.SaveReportNewRowWisePositionCheck;
import com.processcountMail.ExecuteMailCount;
import com.readGmail.GmailMethods;
import com.reportinformationsystem.DateFromToApiReport;
import com.reportinformationsystem.ExpertScriptCall;
import com.reportinformationsystem.FifteenMinuteClass;
import com.reportinformationsystem.SaveReportDataClass;
import com.subjectheaders.PdfSubjectHeaders;
import com.subjectheaders.SolrLogicSubjectHeaders;
import com.subjectheaders.SubjectHeadersAllMethods;

public class secondStepMethodCall {

	final static Logger logger = Logger.getLogger(secondStepMethodCall.class);
	public static void main(String[] args) throws ParseException {
		/*Date date1=new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse("Wed Sep 19 12:12:35 IST 2018");
		System.out.println(date1);*/
		
		String data="HANDY_AND_MR_FUEL_OIL_POSITIONS_BASIS_MALTA_AS_OF_FRIDAY_12TH_OCTOBER_2018.";
		data=data.replaceAll("_", " ");
		System.out.println(data);
		
	}
	
public static void ReadGmailDataToPassPythonApi(Session session, PrintWriter out, String cronNodeName){
		
		try {
			
			SaveReportDataClass SDC=new SaveReportDataClass();
			Node rootNode=session.getRootNode();
			//out.println("rootNode: "+rootNode.getName());
			if(rootNode.hasNode("GmailData1")){
				Node GmailData=rootNode.getNode("GmailData1");
				//out.println("GmailData: "+GmailData.getName());
				if(GmailData.hasNodes()){
					NodeIterator gmailDataNodeItr=GmailData.getNodes();
					int fivereadNodeCount=0;
					while(gmailDataNodeItr.hasNext()){
						//inc count
						fivereadNodeCount++;
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
									  try {
										//String subjectNodePath1= subjectNode.getPath();
										  htmlSize= textNode.getNodes().getSize();
										//  System.out.println("passdate: "+textSentMailTime);
										 // out.println("passdate: "+textSentMailTime);
										
										  if(!GmailMethods.isNullString(textSentMailTime)){
										      Date date1=new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(textSentMailTime);
										  if(date1!=null){
											  out.println("parsedDate: "+date1);
										  boolean schedulerReadMail= FifteenMinuteClass.fifteenDaysBeforeDataRead(date1);
										 
										  Calendar cal = Calendar.getInstance();
											cal.setTime(date1);
											String receivedDate = cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
													+ cal.get(Calendar.YEAR);
										
											
//								  if(receivedOnlyDate.equals("7-3-2019")){
										  if (schedulerReadMail == true) {
											  
										      String nodeNameRepaceunderscore=subjectNode.getName().toString();
										      nodeNameRepaceunderscore=nodeNameRepaceunderscore.replaceAll("_", " ");
										      
											//  logger.info("*********************Strting point ************************************************************************************************************");
											//  logger.info("MainNode_Subject:: "+subjectNode.getName().toString());
											//  logger.info("SubNode_mail_Html:: "+textNode.getName().toString());
											//  logger.info("html_TimeStamp:: "+textSentMailTime);
											  
											  if(htmlSize==1){
												  htmlParser(session, out, SDC, subjectNode, textNode, textSentMailTime,
														  from_Source, timestampDate, timestampDateAndTime, receivedDate,
														  nodeNameRepaceunderscore, cronNodeName);

											  } else if(htmlSize>1){
										  
 
//								  if(textNode.hasNodes()){
										 NodeIterator textNodeItr=textNode.getNodes();
										 
										 while(textNodeItr.hasNext()){
											 Node textInsideAttachmentFolderNode=textNodeItr.nextNode();
//										 out.println("textInsideAttachmentFolderNode: "+textInsideAttachmentFolderNode.getName());
											 if(textInsideAttachmentFolderNode.hasNodes()){
												 NodeIterator textInsideAttachmentFolderNodeItr=textInsideAttachmentFolderNode.getNodes();
												 int i=0;
												 while(textInsideAttachmentFolderNodeItr.hasNext()){
													 boolean attachmentFlag=false;
													   Node attachmentNode=textInsideAttachmentFolderNodeItr.nextNode();
													   i++;
//												   out.println("attachmentNode: "+attachmentNode.getName());
													   String attachmentNodeFlag="";
													   if(attachmentNode.hasProperty("Flag")){
														   attachmentNodeFlag=attachmentNode.getProperty("Flag").getString();
														  // out.println("Flag :: "+attachmentNodeFlag);
													   }
													 
													  
													 if(!attachmentNodeFlag.equals("1")){
														 //lastProcessedTimeForUi(session, textSentMailTime);
//												   if(attachmentNodeFlag.equals("1")){
//													 out.println("attachmentNode: "+attachmentNode);
														 if(attachmentNode.hasProperty("tomcat_file_path")){
															  String attachmentTomcatFilePath=attachmentNode.getProperty("tomcat_file_path").getString();
															 /* out.println("attachmentTomcatFilePath: "+attachmentTomcatFilePath);*/
															 
															 // if(attachmentTomcatFilePath.equals("/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/TcReports_1_TCReport.pdf")){
																  
//															  }else{
															  
															   // logger.info("AttachmentHasattachmentTomcatFilePath:: "+attachmentTomcatFilePath);	
															   // logger.info("SubjectNameattachmentTomcatFilePath:: "+attachmentNode.getName().toString());
															    
															  if(!GmailMethods.isNullString(attachmentTomcatFilePath)){
															  if(attachmentTomcatFilePath.contains(".pdf")){
																  attachmentFlag=true;
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
																	  
//																  out.print("kalka_pdf: "+receivedOnlyDate);
																	 
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
																  /*String svgUrl = DateFromToApiReport.processPdftoSvg(attachmentTomcatFilePath);
																  String filepathfromourside="/home/ubuntu/TestedMailJSon/"+attachmentNode.getName().toString()+"_"+timestampDate+".txt";
																  if(!GmailMethods.isNullString(svgUrl)){
																       String vinayaScriptCall=VinayaScript.vinayaScript(svgUrl);
																       System.out.println("vinayaScriptCall:: "+vinayaScriptCall+ " "+attachmentNode.getName().toString()+ " "+textSentMailTime);
																	    logger.info("PdfvinayaScriptCall:: "+vinayaScriptCall);
																	    logger.info("Pdf_emailUrl:: "+emailUrl);
																	    logger.info("Pdftimestamp:: "+textSentMailTime);
																      if( !( vinayaScriptCall.equals("false") )  ){
																      if( !GmailMethods.isNullString(vinayaScriptCall) ){
																           boolean checkjsonString=SaveReportDataClass.isJSONValid(vinayaScriptCall);
																	     if(checkjsonString==true){
																	    	// take count of vinayaScriptCall
															          	   out.println("attachmentTomcatFilePath_pdf: "+attachmentTomcatFilePath);
															          	   out.println("vinayaScriptCall_Output_svgtopdf: "+vinayaScriptCall);
															          	   String pallaviCodeHere= pallavi__json3_execution.UpdatedScriptForKnownAndUnknown(out, session, vinayaScriptCall, emailUrl, textSentMailTime, subjectNodePath, from_Source, timestampDate, timestampDateAndTime, filepathfromourside);
															          	   logger.info("PdfpallaviCodeHere:: "+pallaviCodeHere);
															          	   System.out.println("PdfpallaviCodeHere:: "+pallaviCodeHere+ " "+attachmentNode.getName().toString()+ " "+textSentMailTime);
															          	   boolean checkjsonStringfinalobj=SaveReportDataClass.isJSONValid(pallaviCodeHere);
																			    if(checkjsonStringfinalobj==true){
																			    	// JSONObject data=SubjectHeadersAllMethods.getUrlByStanbol(nodeNameRepaceunderscore, out);
																			    	  JSONObject myJSonSubject=SolrLogicSubjectHeaders.SubjectData(nodeNameRepaceunderscore, out);
																			    	  JSONArray finalArray=new JSONArray();
																			    	  if(myJSonSubject!=null && myJSonSubject.length()!=0){
																		        		    JSONObject pdfData=PdfSubjectHeaders.pdfData(vinayaScriptCall);
																		        		    JSONObject dataFinal=null;
																		        		    if(pdfData!=null && pdfData.length()!=0){
																		        		       dataFinal=PdfSubjectHeaders.finalHeaderPdfData(myJSonSubject, pdfData);
																		        		       finalArray.put(dataFinal);
																		        		    }else{
																		        		    	finalArray.put(myJSonSubject);
																		        		    }
																		        		  
																		        			JSONObject stabnBolCheckSubjectHeader=new JSONObject(pallaviCodeHere);
																		        			stabnBolCheckSubjectHeader.put("subject_HeadersData", finalArray);
																		        			pallaviCodeHere=stabnBolCheckSubjectHeader.toString();
																		        		    
																		        	  }
																			    	
																			    	out.println("pallaviCodeHere_pdf: "+pallaviCodeHere);
																			    //take count of pallaviCodeHere
																			    	SDC.parseAllReportFromJsonToSave(out, session, pallaviCodeHere, emailUrl, textSentMailTime, subjectNodePath, from_Source, timestampDate, timestampDateAndTime, "pdf", vinayaScriptCall);
																			    }
															                   attachmentNode.setProperty("Flag", "1");
																	   //  session.save();
																	     
																	} // null check pdfScript
																}
																  } // svg check close
//															  } //........  															  } // check 15  days wala
															  }// check if	
*/															  													  // false
																  //set flag also in parseall method inside
//														  }// equals
															  }// pdf close
															 
															  
//														 if( emailUrl.contains(".xls") || emailUrl.contains(".xlsx") ){
															  if( (attachmentTomcatFilePath.contains(".xls")) || (attachmentTomcatFilePath.contains(".xlsx")) || (attachmentTomcatFilePath.contains(".XLS")) || (attachmentTomcatFilePath.contains(".XLSX")) ){
																  attachmentFlag=true;
//															  out.println("attachmentTomcatFilePath_excel: "+attachmentTomcatFilePath);
																  
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
//																  out.print("kalka_excel: "+receivedOnlyDate);
																  
																  int clarksonCOunt=0;
																  if(from_Source.equalsIgnoreCase("clarksons")){
																	  
																	  String countExcelBigSize= OutputReasponse.excelClarksonCountBigSize(attachmentTomcatFilePath, out);
																	  if( !GmailMethods.isNullString(countExcelBigSize) ){
																		  clarksonCOunt= Integer.parseInt(countExcelBigSize);
																	  }
																  } // clarkson check
																  
																  String emailUrl="";
																  if(attachmentNode.hasProperty("tomcat_file_link")){
																	  emailUrl=attachmentNode.getProperty("tomcat_file_link").getString();
																	  emailUrl = (GmailMethods.isNullString(emailUrl)) ? "" : emailUrl;
																	  
																  }
																  String subjectNodePath= subjectNode.getPath();
																  subjectNodePath = (GmailMethods.isNullString(subjectNodePath)) ? "" : subjectNodePath;
																  String finalwithQty="";
																  Node subjectNodeNode=null;
																  
																  if(clarksonCOunt>700){
																	  subjectNodeNode=StatusForUi.collectProcessDataStatus(out, session, cronNodeName, textSentMailTime, subjectNode.getName().toString(), "YES", "", "", "", "", emailUrl, subjectNodePath,"", from_Source);
																	  subjectNodeNode.setProperty("Ui_Update", "Rejected Due To Size");
																	  subjectNodeNode.setProperty("Nlp1", "Rejected Due To Size");
																	  subjectNodeNode.setProperty("Nlp2", "Rejected Due To Size");
																	  subjectNodeNode.setProperty("Nlp3_and_4_QC", "Rejected Due To Size");
																  }else{
																  
																 // out.println("subjectNodePath: "+subjectNodePath);
																      subjectNodeNode= StatusForUi.collectProcessDataStatus(out, session, cronNodeName, textSentMailTime, subjectNode.getName().toString(), "YES", "", "", "", "", emailUrl, subjectNodePath,"", from_Source);
																        String ExpertScriptCallHere=ExpertScriptCall.postExpertScript(attachmentTomcatFilePath, out);
																       // System.out.println("ExpertScriptCallHere:: "+ExpertScriptCallHere+ " "+attachmentNode.getName().toString()+ " "+textSentMailTime);
																       // logger.info("ExpertScriptCallHere_Excel:: "+ExpertScriptCallHere);
																       // logger.info("emailUrl_Excel:: "+emailUrl);
																        String filepathfromourside="/home/ubuntu/TestedMailJSon/"+attachmentNode.getName().toString()+"_"+timestampDate+".txt";
																       if( !( ExpertScriptCallHere.equals("false") )  ){
																    	 
																       boolean checkjsonString=SaveReportDataClass.isJSONValid(ExpertScriptCallHere);
																		if(checkjsonString==true){
																			  subjectNodeNode.setProperty("Nlp1", "YES");
																		 out.println("attachmentTomcatFilePath_excel: "+attachmentTomcatFilePath);
																         out.println("ExpertScriptexceloutput: "+ExpertScriptCallHere);
																         //String MycodeHere= AbhishekScript.excelHtmlScript(ExpertScriptCallHere, out);
																         String pallaviCodeHere= pallavi_UpdatedScript_copy_17_04.updatedMainScript(out, session, ExpertScriptCallHere, emailUrl, textSentMailTime, subjectNodePath, from_Source, timestampDate, timestampDateAndTime, filepathfromourside);
																         out.println("pallaviCodeHere_excel_newCode: "+pallaviCodeHere);
																         //logger.info("pallaviCodeHere_Excel:: "+pallaviCodeHere);
																        // System.out.println("pallaviCodeHere:: "+pallaviCodeHere+ " "+attachmentNode.getName().toString()+ " "+textSentMailTime);
																         
																        // logger.info("*******************************endingPoint*********************************************************************************************");
																         boolean checkjsonStringAbhishek=SaveReportDataClass.isJSONValid(pallaviCodeHere);
																	        if(checkjsonStringAbhishek){
																	        	out.println("pallaviCodeHere_excel: "+pallaviCodeHere);
																	        	subjectNodeNode.setProperty("Nlp2", "YES");
																	        	String firstMethod=SaveReportNewRowWisePositionCheck.SaveReportNewStructureChanged(out, pallaviCodeHere);
																	        	 boolean firstMethodAbhishek=SaveReportDataClass.isJSONValid(firstMethod);
																	        	 if(firstMethodAbhishek){
																	        		 out.println("firstMethod_excel: "+firstMethod);
																	        		 String secondMethod= ReportTypeIdentify.ReportTypeAndFinalJson(firstMethod, out, nodeNameRepaceunderscore);
																	        		 boolean secondMethodAbhishek=SaveReportDataClass.isJSONValid(secondMethod);
																	        		 if(secondMethodAbhishek){
																	        			 out.println("secondMethodAbhishek_excel: "+secondMethodAbhishek);
																	        			 
																	        			 String reportThird=ReportTypeCorrection.ReportTypeRemaining(secondMethod, out, nodeNameRepaceunderscore);
																	        			 boolean reportThirdMethodAbhishek=SaveReportDataClass.isJSONValid(reportThird);
																	        			 if(reportThirdMethodAbhishek){
																	        			String finalJson= Methods.ReportTypeAndFinalJson(reportThird, out, nodeNameRepaceunderscore);
																	        			boolean finalJsonAbhishek=SaveReportDataClass.isJSONValid(finalJson);
																	        			if(finalJsonAbhishek){
																	        				out.println("finalMethodAbhishek_excel: "+finalJson);
																	        				
																	        				String remainingDataCorrectedHere=MethodJsonOnlyInsert.applyToAllJsonAsSameReport(finalJson);
																	        				boolean remainingDataCorrectedHereAbhishek=SaveReportDataClass.isJSONValid(remainingDataCorrectedHere);
																	        				if(remainingDataCorrectedHereAbhishek){
																	        				 finalwithQty=MethodJsonOnlyInsert.cargoQtyCheckFromNUmeric(remainingDataCorrectedHere);
																	        				boolean finalQtyAbhishek=SaveReportDataClass.isJSONValid(finalwithQty);
																		        			if(finalQtyAbhishek){
																		        				out.println("finalMethodwithqtyAbhishek_excel: "+finalwithQty);
																		        				subjectNodeNode.setProperty("Nlp3_and_4_QC", "YES");
																		        				SaveReportDataClassNewStr.parseAllReportFromJsonToSave(out, session, finalwithQty, emailUrl, textSentMailTime,subjectNodePath, from_Source, timestampDate, timestampDateAndTime, "excel", ExpertScriptCallHere);
																		        				attachmentNode.setProperty("Flag", "1");
																		        				 out.println("uientereduistatus:: "+cronNodeName);
																		        				
																		        			}else{
																		        				subjectNodeNode.setProperty("Nlp3_and_4_QC", "NO");
																		        			}
																	        				} //
																	        			}
																	        		 }//
																	        		 }
																	        	 }
																                
																	        }else{
																	        	subjectNodeNode.setProperty("Nlp2", "NO");
																	        }
																             
																		//  session.save();
																		}else{
																			  subjectNodeNode.setProperty("Nlp1", "NO");
																		}
																       }// clarkson count size here close
																	}
																       boolean reportCheck=StatusForUi.statusCheckReportForUi(finalwithQty);
																      if(reportCheck==true){
																    	  subjectNodeNode.setProperty("Ui_Update", "YES");
																      }else{
																    	  subjectNodeNode.setProperty("Ui_Update", "NO");
																      }
																      session.save();
																	
																 // } // chek 														  // set flag also in parseall method inside
																  }
															  
															  if(!(attachmentFlag)){

																  htmlParser(session, out, SDC, subjectNode, textNode, textSentMailTime,
																		  from_Source, timestampDate, timestampDateAndTime, receivedDate,
																		  nodeNameRepaceunderscore, cronNodeName);

															    
															  }
															  
															  }// excel close
															  
//														 } 
															  }
//													  } // attachmentTomcatFilePath close
														
														 
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
										  }else{
											  out.println("nulldateFound: "+textNode.getName().toString());
										  }
									} catch (Exception e) {
										// TODO Auto-generated catch block
										out.println(e.getMessage());
										e.printStackTrace(out);
									}
								  }
								  }
//							}// html not has 
								  
								
							}// subjectNodeItr while close
						} // subject node hasnodes
						
						
					//if count is 5 break	
						/*if(fivereadNodeCount==5){
							break;
						}*/
						
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
			e.printStackTrace(out);
			out.println(e.getMessage());
		}
	}

public static void htmlParser(Session session, PrintWriter out, SaveReportDataClass SDC, Node subjectNode,
		Node textNode, String textSentMailTime, String from_Source, String timestampDate, String timestampDateAndTime,
		String receivedDate, String nodeNameRepaceunderscore, String cronNodeName){
	try {
		//									 if(htmlSize>1){
		 
		if(textNode.hasProperty("tomcat_file_path")){
		  String textTomcatFilePath=textNode.getProperty("tomcat_file_path").getString();
		
//										  out.print("kalka_html: "+receivedOnlyDate);
		  if(textTomcatFilePath.contains(".html")){
		    // out.println("textTomcatFilePath: "+textTomcatFilePath);
		     String emailUrl="";
		     String finalwithQty="";
			  if(textNode.hasProperty("file_link")){
				  emailUrl=textNode.getProperty("file_link").getString();
			  }
			  String subjectNodePath= subjectNode.getPath();
		      subjectNodePath = (GmailMethods.isNullString(subjectNodePath)) ? "" : subjectNodePath;
		      out.println("subjectNodePath: "+subjectNodePath);
		  
		     // lastProcessedTimeForUi(session, textSentMailTime);
		      Node subjectNodeNode= StatusForUi.collectProcessDataStatus(out, session, cronNodeName, textSentMailTime, subjectNode.getName().toString(), "YES", "", "", "", "", emailUrl, subjectNodePath,"", from_Source);
		      
		    String ExpertScriptCallHere=ExpertScriptCall.postExpertScript(textTomcatFilePath, out);
		    
		      String filepathfromourside="/home/ubuntu/TestedMailJSon/"+textNode.getName().toString()+"_"+timestampDate+".txt";
			  boolean checkjsonString=SaveReportDataClass.isJSONValid(ExpertScriptCallHere);
			    if(checkjsonString==true){
			    	out.println();
			    	subjectNodeNode.setProperty("Nlp1", "YES");
			    	// take count of ExpertScriptCallHere
			    	out.println("ExpertCodeOutputHtml: "+ExpertScriptCallHere);
			        out.println("textTomcatFilePath: "+textTomcatFilePath);
			        out.println("textSentMailTime_html: "+textSentMailTime);
			       // String MycodeHere= AbhishekScript.excelHtmlScript(ExpertScriptCallHere, out);
			       String pallaviCodeHere= pallavi_UpdatedScript_copy_17_04.updatedMainScript(out, session, ExpertScriptCallHere, emailUrl, textSentMailTime, subjectNodePath, from_Source, timestampDate, timestampDateAndTime, filepathfromourside);
			       
			      // take count of pallaviCodeHere
			        boolean checkjsonStringAbhishek=SaveReportDataClass.isJSONValid(pallaviCodeHere);
			        if(checkjsonStringAbhishek){
						  out.println("pallaviCodeHereHtml: "+pallaviCodeHere);
						  subjectNodeNode.setProperty("Nlp2", "YES");
						  String firstMethod=SaveReportNewRowWisePositionCheck.SaveReportNewStructureChanged(out, pallaviCodeHere);
				        	 boolean firstMethodAbhishek=SaveReportDataClass.isJSONValid(firstMethod);
				        	 if(firstMethodAbhishek){
				        		 out.println("firstMethod_html: "+firstMethod);
				        		 String secondMethod= ReportTypeIdentify.ReportTypeAndFinalJson(firstMethod, out, nodeNameRepaceunderscore);
				        		 boolean secondMethodAbhishek=SaveReportDataClass.isJSONValid(secondMethod);
				        		 if(secondMethodAbhishek){
				        			 out.println("secondMethodAbhishek_html: "+secondMethodAbhishek);
				        			 String reportThird=ReportTypeCorrection.ReportTypeRemaining(secondMethod, out, nodeNameRepaceunderscore);
				        			 boolean reportThirdMethodAbhishek=SaveReportDataClass.isJSONValid(reportThird);
				        			 if(reportThirdMethodAbhishek){
				        			 
				        			String finalJson= Methods.ReportTypeAndFinalJson(reportThird, out, nodeNameRepaceunderscore);
				        			boolean finalJsonAbhishek=SaveReportDataClass.isJSONValid(finalJson);
				        			if(finalJsonAbhishek){
				        				out.println("finalMethodAbhishek_html: "+finalJson);
				        				
				        				String remainingDataCorrectedHere=MethodJsonOnlyInsert.applyToAllJsonAsSameReport(finalJson);
				        				boolean remainingDataCorrectedHereAbhishek=SaveReportDataClass.isJSONValid(remainingDataCorrectedHere);
				        				if(remainingDataCorrectedHereAbhishek){
				        				
				        				 finalwithQty=MethodJsonOnlyInsert.cargoQtyCheckFromNUmeric(remainingDataCorrectedHere);
				        				boolean finalQtyAbhishek=SaveReportDataClass.isJSONValid(finalwithQty);
					        			if(finalQtyAbhishek){
					        				subjectNodeNode.setProperty("Nlp3_and_4_QC", "YES");
					        				out.println("finalMethodwithqtyAbhishek_html: "+finalwithQty);
					        				SaveReportDataClassNewStr.parseAllReportFromJsonToSave(out, session, finalwithQty, emailUrl, textSentMailTime,subjectNodePath, from_Source, timestampDate, timestampDateAndTime, "html", ExpertScriptCallHere);
									          textNode.setProperty("Flag", "1");
									         
											 // ExecuteMailCount.executeMailCount(out, session, receivedDate, subjectNode.getName(), timestampDateAndTime, subjectNode.getPath());
					        			}else{
					        				subjectNodeNode.setProperty("Nlp3_and_4_QC", "NO");
					        			}
				        				}
				        			}
				        			 }
				        		 }
				        	 }
			        	 
			        }else{
			        	subjectNodeNode.setProperty("Nlp2", "NO");
			        }
			    }else{
			    	subjectNodeNode.setProperty("Nlp1", "NO");
			    }
		  
			    boolean reportCheck=StatusForUi.statusCheckReportForUi(finalwithQty);
			      if(reportCheck==true){
			    	  subjectNodeNode.setProperty("Ui_Update", "YES");
			      }else{
			    	  subjectNodeNode.setProperty("Ui_Update", "NO");
			      }
			    session.save();
			    
		 // relevalent data
		  
		  //session.save();
			    
		  }
		} //end
		//set flag also in parseall method inside
//								  }// date check equals 
//								  }
		 
//								  } 
//								  }// check without attachment read
//} // textFlag check here
	} catch (Exception e) {
		// TODO Auto-generated catch block
		try {
			session.save();
		} catch (RepositoryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace(out);
		}
		e.printStackTrace(out);
	}
}

 public static void lastProcessedTimeForUi(Session session, String lastProcessedTimeForUi){
	 
	 try {
			Node scorpio=null;
			Node ReportData=null;
		
		 if(session.getRootNode().hasNode("scorpioDataBase")){
				scorpio = session.getRootNode().getNode("scorpioDataBase");
			}
		 
		 if(scorpio.hasNode("ReportData")){
				ReportData = scorpio.getNode("ReportData");
				ReportData.setProperty("lastProcessedTimeForUi", lastProcessedTimeForUi);
				session.save();
			}
		 
		 
		 
	} catch (Exception e) {
		
	}
 }

public static String getLastProcessedTimeForUi(Session session){
	String lastProcessedTimeForUi="";
	 try {
			Node scorpio=null;
			Node ReportData=null;
		
		 if(session.getRootNode().hasNode("scorpioDataBase")){
				scorpio = session.getRootNode().getNode("scorpioDataBase");
			}
		 
		 if(scorpio.hasNode("ReportData")){
				ReportData = scorpio.getNode("ReportData");
				
				if(ReportData.hasProperty("lastProcessedTimeForUi")){
					lastProcessedTimeForUi=ReportData.getProperty("lastProcessedTimeForUi").getString();
				}
				
			}
		 
		 
		 
	} catch (Exception e) {
		
	}
	return lastProcessedTimeForUi;
 }


}
