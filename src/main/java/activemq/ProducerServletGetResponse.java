package activemq;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.jcr.Node;
import javax.jcr.NodeIterator;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import javax.servlet.ServletException;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.jcr.api.SlingRepository;
import com.abhishek.VinayaScript;
import com.errorreport.StatusForUi;
import com.mongocode.PdfErrorInMongoDb;
import com.mycode.MethodJsonOnlyInsert;
import com.mycode.Methods;
import com.mycode.OutputReasponse;
import com.mycode.ReportTypeCorrection;
import com.mycode.ReportTypeIdentify;
import com.mycode.SaveReportNewRowWisePositionCheck;
import com.pallavi.code.pallavi_UpdatedScript_copy_25_04_19;
import com.pallavi.code.pallavi_WriteFile;
import com.readGmail.GmailMethods;
import com.reportinformationsystem.DateFromToApiReport;
import com.reportinformationsystem.ExpertScriptCall;
import com.reportinformationsystem.FifteenMinuteClass;
import com.reportinformationsystem.SaveReportDataClass;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest; 
import org.apache.sling.api.SlingHttpServletResponse;

@SlingServlet(paths = "/getProducerResponse")
public class ProducerServletGetResponse extends SlingAllMethodsServlet {
	private static final long serialVersionUID = 1;
	@Reference
	private SlingRepository repo;
	Session session = null;
	
	@Override
	public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		
          PrintWriter out = response.getWriter();
		
		try {
			
			session = repo.login(new SimpleCredentials("admin", "admin".toCharArray()));
		    
			  String datePass=StatusForUi.cronCurrentTimeParseHere();
				if( (datePass!=null) && (!GmailMethods.isNullString(datePass)) ){
					out.println("uidatePass:: "+datePass);
					 String cronNodeName=StatusForUi.nodeCreateCronInSling(out, session, datePass);
					 
					 if( (cronNodeName!=null) && (!GmailMethods.isNullString(cronNodeName)) ){
						 out.println("uidatecronNodeName:: "+cronNodeName);
						 ReadGmailDataToPassPythonApi(session, out, cronNodeName);
					 }
					 
				}
		        
			
		} catch (Exception e) {
			e.printStackTrace(out);
		}finally {
			out.close();
		}
		
	}
	
public static void ReadGmailDataToPassPythonApi(Session session, PrintWriter out, String cronNodeName){
		
	  String textSentMailTime="";
	  String textXmlFileLink="";
	  String textFlag="";
	  String tomcat_file_link="";
	  String from_Source="";
	  String timestampDate="";
	  String timestampDateAndTime="";
	  String attachmentNodeFlag="";
	
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
								  
								  if(textNode.hasProperty("SentMailTime")){
									 textSentMailTime=textNode.getProperty("SentMailTime").getString();
								  }// timeStamp purpose
								  if(textNode.hasProperty("Flag")){
										 textFlag=textNode.getProperty("Flag").getString();
								  }// once saved again will not read this mail
								  if(textNode.hasProperty("xml_file_link")){
										 textXmlFileLink=textNode.getProperty("xml_file_link").getString();
								  }// emailUrl purpose
								  
								 
								  if(textNode.hasProperty("from_Source")){
									  from_Source=textNode.getProperty("from_Source").getString();
									  }// timeStamp purpose
								  
								 
								  if(textNode.hasProperty("timestampDate")){
									  timestampDate=textNode.getProperty("timestampDate").getString();
									  }// timeStamp purpose
								 
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
										  htmlSize= textNode.getNodes().getSize();
										
										  if(!GmailMethods.isNullString(textSentMailTime)){
											  SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
											  Date date1 = formatter.parse(textSentMailTime);
											  
										  if(date1!=null){
											  out.println("parsedDate: "+date1);
										  boolean schedulerReadMail= FifteenMinuteClass.fifteenDaysBeforeDataRead(date1);
										  
										     SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
										     String receivedDate= formatter1.format(date1);
											
										  if (schedulerReadMail == true) {
											  
										      String nodeNameRepaceunderscore=subjectNode.getName().toString();
										      nodeNameRepaceunderscore=nodeNameRepaceunderscore.replaceAll("_", " ");
											  
											  if(htmlSize==1){
												  htmlParser(session, out, SDC, subjectNode, textNode, textSentMailTime,
														  from_Source, timestampDate, timestampDateAndTime, receivedDate,
														  nodeNameRepaceunderscore, cronNodeName);

											  } else if(htmlSize>1){
										  
										 NodeIterator textNodeItr=textNode.getNodes();
										 while(textNodeItr.hasNext()){
											 Node textInsideAttachmentFolderNode=textNodeItr.nextNode();
											 if(textInsideAttachmentFolderNode.hasNodes()){
												 NodeIterator textInsideAttachmentFolderNodeItr=textInsideAttachmentFolderNode.getNodes();
												 while(textInsideAttachmentFolderNodeItr.hasNext()){
													 boolean attachmentFlag=false;
													   Node attachmentNode=textInsideAttachmentFolderNodeItr.nextNode();
													   
													   if(attachmentNode.hasProperty("Flag")){
														   attachmentNodeFlag=attachmentNode.getProperty("Flag").getString();
													   }
													  
													 if(!attachmentNodeFlag.equals("1")){
														 if(attachmentNode.hasProperty("tomcat_file_path")){
															  String attachmentTomcatFilePath=attachmentNode.getProperty("tomcat_file_path").getString();
															  if(!GmailMethods.isNullString(attachmentTomcatFilePath)){
																  
											//............................................pdf start here...................................
															  if(attachmentTomcatFilePath.contains(".pdf")){
																  attachmentFlag = pdfProcessData(session, out,
																		cronNodeName, subjectNode, textSentMailTime,
																		from_Source, timestampDate,
																		timestampDateAndTime, nodeNameRepaceunderscore,
																		attachmentNode, attachmentTomcatFilePath);
															  }// pdf close	
															  
									//............................................pdf close here...................................	
															  
															  if( (attachmentTomcatFilePath.contains(".xls")) || (attachmentTomcatFilePath.contains(".xlsx")) || (attachmentTomcatFilePath.contains(".XLS")) || (attachmentTomcatFilePath.contains(".XLSX")) ){
																  attachmentFlag=true;
																  excelProcessData(session, out, cronNodeName,
																		subjectNode, textSentMailTime, from_Source,
																		timestampDate, timestampDateAndTime,
																		nodeNameRepaceunderscore, attachmentNode,
																		attachmentTomcatFilePath);
																  
																  } // excel close here contains xls
															  
															  if(!(attachmentFlag)){

																  htmlParser(session, out, SDC, subjectNode, textNode, textSentMailTime,
																		  from_Source, timestampDate, timestampDateAndTime, receivedDate,
																		  nodeNameRepaceunderscore, cronNodeName);

															    
															  }
															  
															  }// excel close
															  }
														 
													 } // attachmentNodeFlag close
													   
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
										e.printStackTrace(out);
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
				e1.printStackTrace(out);
			}
		}finally {
           if( GmailMethods.isNullString(textSentMailTime) ){
        	   textSentMailTime=null;
			}if( GmailMethods.isNullString(textXmlFileLink) ){
				 textXmlFileLink=null;
				}if( GmailMethods.isNullString(textFlag) ){
					textFlag=null;
				}if( GmailMethods.isNullString(tomcat_file_link) ){
					tomcat_file_link=null;
				}if( GmailMethods.isNullString(from_Source) ){
					from_Source=null;
				}if( GmailMethods.isNullString(timestampDate) ){
					timestampDate=null;
				}if( GmailMethods.isNullString(timestampDateAndTime) ){
					timestampDateAndTime=null;
				}if( GmailMethods.isNullString(attachmentNodeFlag) ){
					attachmentNodeFlag=null;
				}
			  
			   out.close();
		}
	}

public static void excelProcessData(Session session, PrintWriter out, String cronNodeName, Node subjectNode,
		String textSentMailTime, String from_Source, String timestampDate, String timestampDateAndTime,
		String nodeNameRepaceunderscore, Node attachmentNode, String attachmentTomcatFilePath) {
	
	String emailUrl="";
	String finalwithQty="";
	
	try{
		JSONObject producerJSonObj=new JSONObject();
	   int clarksonCOunt=0;
	  if(from_Source.equalsIgnoreCase("clarksons")){
		  String countExcelBigSize= OutputReasponse.excelClarksonCountBigSize(attachmentTomcatFilePath, out);
		  if( !GmailMethods.isNullString(countExcelBigSize) ){
			  clarksonCOunt= Integer.parseInt(countExcelBigSize);
		  }
	  } // clarkson check
	  
	  
	  if(attachmentNode.hasProperty("tomcat_file_link")){
		  emailUrl=attachmentNode.getProperty("tomcat_file_link").getString();
		  emailUrl = (GmailMethods.isNullString(emailUrl)) ? "" : emailUrl;
		  producerJSonObj.put("emailUrl", emailUrl);
		  
	  }
	  producerJSonObj.put("textSentMailTime", textSentMailTime);
	  String subjectNodePath= subjectNode.getPath();
	  subjectNodePath = (GmailMethods.isNullString(subjectNodePath)) ? "" : subjectNodePath;
	  producerJSonObj.put("subjectNodePath", subjectNodePath);
	  producerJSonObj.put("from_Source", from_Source);
	  producerJSonObj.put("timestampDate", timestampDate);
	  producerJSonObj.put("timestampDateAndTime", timestampDateAndTime);
	  producerJSonObj.put("extension", "excel");
	  
	  
	  Node subjectNodeNode=null;
	  Node clarksonChekNode=null;
	  
	  if(clarksonCOunt>700){
		  clarksonChekNode=StatusForUi.collectProcessDataStatus(out, session, cronNodeName, textSentMailTime, subjectNode.getName().toString(), "YES", "", "", "", "", emailUrl, subjectNodePath,"", from_Source);
		  clarksonChekNode.setProperty("Ui_Update", "Rejected Due To Size");
		  clarksonChekNode.setProperty("Nlp1", "Rejected Due To Size");
		  clarksonChekNode.setProperty("Nlp2", "Rejected Due To Size");
		  clarksonChekNode.setProperty("Nlp3_and_4_QC", "Rejected Due To Size");
		  attachmentNode.setProperty("Flag", "1");
		  session.save();
	  }else{
	        subjectNodeNode= StatusForUi.collectProcessDataStatus(out, session, cronNodeName, textSentMailTime, subjectNode.getName().toString(), "YES", "", "", "", "", emailUrl, subjectNodePath,"", from_Source);
	        producerJSonObj.put("subjectNodeNode", subjectNodeNode.getPath());
	        String ExpertScriptCallHere=ExpertScriptCall.postExpertScript(attachmentTomcatFilePath, out);
	        String filepathfromourside="/home/ubuntu/TestedMailJSon/"+attachmentNode.getName().toString()+"_"+timestampDateAndTime+".txt";
	       if( !( ExpertScriptCallHere.equals("false") )  ){
	           boolean checkjsonString=SaveReportDataClass.isJSONValid(ExpertScriptCallHere);
			if(checkjsonString==true){
				producerJSonObj.put("ExpertScriptCallHere", ExpertScriptCallHere);
				  subjectNodeNode.setProperty("Nlp1", "YES");
				  attachmentNode.setProperty("Flag", "1");
			 out.println("attachmentTomcatFilePath_excel: "+attachmentTomcatFilePath);
	         out.println("ExpertScriptexceloutput: "+ExpertScriptCallHere);
	         String pallaviCodeHere= pallavi_UpdatedScript_copy_25_04_19.updatedMainScript(out, session, ExpertScriptCallHere, emailUrl, textSentMailTime, subjectNodePath, from_Source, timestampDate, timestampDateAndTime, filepathfromourside);
	         out.println("pallaviCodeHere_excel_newCode: "+pallaviCodeHere);
	         boolean checkjsonStringAbhishek=SaveReportDataClass.isJSONValid(pallaviCodeHere);
		        if(checkjsonStringAbhishek){
		        	out.println("pallaviCodeHere_excel: "+pallaviCodeHere);
		        	subjectNodeNode.setProperty("Nlp2", "YES");
		        	attachmentNode.setProperty("Flag", "1");
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
			        				pallavi_WriteFile.writeUsingOutputStream( "AbhishekJSON ::====== "+finalwithQty.toString() ,filepathfromourside );
			        				subjectNodeNode.setProperty("Nlp3_and_4_QC", "YES");
			        				attachmentNode.setProperty("Flag", "1");
			        				producerJSonObj.put("finalwithQty", finalwithQty);
			        				
			        				ActiveMQCall amq=new ActiveMQCall();
			        				amq.GetProducer("PROCESSMAILS",amq.createRandomString(), producerJSonObj.toString());
			        				
			        				//SaveReportDataClassNewStr.parseAllReportFromJsonToSave(out, session, finalwithQty, emailUrl, textSentMailTime,subjectNodePath, from_Source, timestampDate, timestampDateAndTime, "excel", ExpertScriptCallHere);
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
		        	attachmentNode.setProperty("Flag", "1");
		        }
	             
			}else{
				  subjectNodeNode.setProperty("Nlp1", "NO");
				  attachmentNode.setProperty("Flag", "1");
			}
			
			 /*boolean reportCheck=StatusForUi.statusCheckReportForUi(finalwithQty);
		      if(reportCheck==true){
		    	  subjectNodeNode.setProperty("Ui_Update", "YES");
		    	  String allreport=StatusForUi.getReportTypeUi(finalwithQty);
		    	  subjectNodeNode.setProperty("Type", allreport);
		      }else{
		    	  subjectNodeNode.setProperty("Ui_Update", "NO");
		      }*/
		      session.save();
			
	       }// clarkson count size here close
  }
	}catch( Exception e ){
		try {
			session.save();
		} catch (Exception e2) {
			e2.printStackTrace(out);
		}
	}finally {
		 emailUrl=null;
		 finalwithQty=null;
	}
}

public static boolean pdfProcessData(Session session, PrintWriter out, String cronNodeName, Node subjectNode,
		String textSentMailTime, String from_Source, String timestampDate, String timestampDateAndTime,
		String nodeNameRepaceunderscore, Node attachmentNode, String attachmentTomcatFilePath) {
	
	boolean attachmentFlag;
	attachmentFlag=true;
	String finalwithQty="";
	String emailUrl="";
	
	try{
		JSONObject producerJSonObj=new JSONObject();
	  Node subjectNodeNode=null;
	  
			  
	  if(attachmentNode.hasProperty("tomcat_file_link")){
		  emailUrl=attachmentNode.getProperty("tomcat_file_link").getString();
		  emailUrl = (GmailMethods.isNullString(emailUrl)) ? "" : emailUrl;
		  producerJSonObj.put("emailUrl", emailUrl);
	  }
	  
	  String subjectNodePath= subjectNode.getPath();
	  subjectNodePath = (GmailMethods.isNullString(subjectNodePath)) ? "" : subjectNodePath;
	  producerJSonObj.put("textSentMailTime", textSentMailTime);
	  producerJSonObj.put("subjectNodePath", subjectNodePath);
	 	  producerJSonObj.put("from_Source", from_Source);
	 	  producerJSonObj.put("timestampDate", timestampDate);
	 	  producerJSonObj.put("timestampDateAndTime", timestampDateAndTime);
	 	  producerJSonObj.put("extension", "pdf");
	  
	  subjectNodeNode= StatusForUi.collectProcessDataStatus(out, session, cronNodeName, textSentMailTime, subjectNode.getName().toString(), "YES", "", "", "", "", emailUrl, subjectNodePath,"", from_Source);
	  producerJSonObj.put("subjectNodeNode", subjectNodeNode.getPath());
	  String svgUrl = DateFromToApiReport.processPdftoSvg(attachmentTomcatFilePath);
	 String filepathfromourside="/home/ubuntu/TestedMailJSon/"+attachmentNode.getName().toString()+"_"+timestampDateAndTime+".txt";
	  if(!GmailMethods.isNullString(svgUrl)){
		  String vinayaScriptCall=VinayaScript.vinayaScript(svgUrl, timestampDateAndTime);
		  if( !( vinayaScriptCall.equals("false") )  ){
			  if( !GmailMethods.isNullString(vinayaScriptCall) ){
				  boolean checkjsonString=SaveReportDataClass.isJSONValid(vinayaScriptCall);
				     if(checkjsonString==true){
				    	 producerJSonObj.put("ExpertScriptCallHere", vinayaScriptCall);
				    	 //........................................
				    	 JSONObject pdfDataBlankCheck=new JSONObject(vinayaScriptCall);
				    	 if(pdfDataBlankCheck.length()==0){
				    		 attachmentNode.setProperty("Flag", "1");
				    		 PdfErrorInMongoDb.storePdfErrorMongo("PDFERRORDATABASE", "PdfCollection", timestampDateAndTime, attachmentNode.getName().toString(), "BlankJSOn");
				    	 }
				    	 
				    	 //......................
				    	 
				    	 subjectNodeNode.setProperty("Nlp1", "YES");
				    	 attachmentNode.setProperty("Flag", "1");
				    	 
				    	 String pallaviCodeHere= pallavi_UpdatedScript_copy_25_04_19.updatedMainScript(out, session, vinayaScriptCall, emailUrl, textSentMailTime, subjectNodePath, from_Source, timestampDate, timestampDateAndTime, filepathfromourside);
				    	 boolean checkjsonStringAbhishek=SaveReportDataClass.isJSONValid(pallaviCodeHere);
				    	 if(checkjsonStringAbhishek){
				    		 out.println("pallaviCodeHere_pdf_newCode: "+pallaviCodeHere);
				    		 subjectNodeNode.setProperty("Nlp2", "YES");
				    		 attachmentNode.setProperty("Flag", "1");
				    		 
				    		 String firstMethod=SaveReportNewRowWisePositionCheck.SaveReportNewStructureChanged(out, pallaviCodeHere);
				        	 boolean firstMethodAbhishek=SaveReportDataClass.isJSONValid(firstMethod);
				        	 if(firstMethodAbhishek){
				        		 out.println("firstMethod_pdf: "+firstMethod);
				        		 
				        		 String secondMethod= ReportTypeIdentify.ReportTypeAndFinalJson(firstMethod, out, nodeNameRepaceunderscore);
				        		 boolean secondMethodAbhishek=SaveReportDataClass.isJSONValid(secondMethod);
				        		 if(secondMethodAbhishek){
				        			 
				        			 out.println("secondMethodAbhishek_pdf: "+secondMethodAbhishek);
				        			 
				        			 String reportThird=ReportTypeCorrection.ReportTypeRemaining(secondMethod, out, nodeNameRepaceunderscore);
				        			 boolean reportThirdMethodAbhishek=SaveReportDataClass.isJSONValid(reportThird);
				        			 if(reportThirdMethodAbhishek){
				        				 String finalJson= Methods.ReportTypeAndFinalJson(reportThird, out, nodeNameRepaceunderscore);
						        			boolean finalJsonAbhishek=SaveReportDataClass.isJSONValid(finalJson);
						        			if(finalJsonAbhishek){
						        				out.println("finalMethodAbhishek_pdf: "+finalJson);
						        				
						        				String remainingDataCorrectedHere=MethodJsonOnlyInsert.applyToAllJsonAsSameReport(finalJson);
						        				boolean remainingDataCorrectedHereAbhishek=SaveReportDataClass.isJSONValid(remainingDataCorrectedHere);
						        				if(remainingDataCorrectedHereAbhishek){
						        					finalwithQty=MethodJsonOnlyInsert.cargoQtyCheckFromNUmeric(remainingDataCorrectedHere);
							        				boolean finalQtyAbhishek=SaveReportDataClass.isJSONValid(finalwithQty);
								        			if(finalQtyAbhishek){
								        				out.println("finalMethodwithqtyAbhishek_pdf: "+finalwithQty);
								        				pallavi_WriteFile.writeUsingOutputStream( "AbhishekJSON ::====== "+finalwithQty.toString() ,filepathfromourside );
								        				subjectNodeNode.setProperty("Nlp3_and_4_QC", "YES");
								        				attachmentNode.setProperty("Flag", "1");
								        				producerJSonObj.put("finalwithQty", finalwithQty);
								        				
								        				ActiveMQCall amq=new ActiveMQCall();
								        				amq.GetProducer("PROCESSMAILS",amq.createRandomString(), producerJSonObj.toString());
								        				
								        				//SaveReportDataClassNewStr.parseAllReportFromJsonToSave(out, session, finalwithQty, emailUrl, textSentMailTime,subjectNodePath, from_Source, timestampDate, timestampDateAndTime, "pdf", vinayaScriptCall);
								        				attachmentNode.setProperty("Flag", "1");
								        				out.println("uientereduistatus:: "+cronNodeName);
								        				
								        				
								        			}else{
								        				subjectNodeNode.setProperty("Nlp3_and_4_QC", "NO");
								        				attachmentNode.setProperty("Flag", "1");
								        			}
								        			
						        				}
						        			}
				        				 
				        			 }
				        			 
				        		 }
				        		 
				        		 
				        	 }
				    		 
				    		 
				    	 } else{
				    		 subjectNodeNode.setProperty("Nlp2", "NO");
				    		 attachmentNode.setProperty("Flag", "1");
				    	 }
				        
				    	 
				    	 
				     }else{
						  subjectNodeNode.setProperty("Nlp1", "NO");
						  attachmentNode.setProperty("Flag", "1");
						}
				     
				     
				     /*boolean reportCheck=StatusForUi.statusCheckReportForUi(finalwithQty);
				      if(reportCheck==true){
				    	  subjectNodeNode.setProperty("Ui_Update", "YES");
				    	  String allreport=StatusForUi.getReportTypeUi(finalwithQty);
				    	  subjectNodeNode.setProperty("Type", allreport);
				      }else{
				    	  subjectNodeNode.setProperty("Ui_Update", "NO");
				      }*/
				      session.save();
				     
				     
			  }// vinaya check pdf script
			  
			  
		  } // check pdf false
	  }// check if	
	}catch( Exception e ){
		try {
			session.save();
		} catch (Exception e2) {
			e2.printStackTrace(out);
		}
	}finally {
		 emailUrl=null;
		 finalwithQty=null;
	}
	return attachmentFlag;
}

public static void htmlParser(Session session, PrintWriter out, SaveReportDataClass SDC, Node subjectNode,
		Node textNode, String textSentMailTime, String from_Source, String timestampDate, String timestampDateAndTime,
		String receivedDate, String nodeNameRepaceunderscore, String cronNodeName){
	
	String emailUrl="";
    String finalwithQty="";
	
	try {
		JSONObject producerJSonObj=new JSONObject();
		if(textNode.hasProperty("tomcat_file_path")){
		  String textTomcatFilePath=textNode.getProperty("tomcat_file_path").getString();
		  if(textTomcatFilePath.contains(".html")){
		     
			  if(textNode.hasProperty("file_link")){
				  emailUrl=textNode.getProperty("file_link").getString();
				  producerJSonObj.put("emailUrl", emailUrl);
			  }
			  String subjectNodePath= subjectNode.getPath();
		      subjectNodePath = (GmailMethods.isNullString(subjectNodePath)) ? "" : subjectNodePath;
		      out.println("subjectNodePath: "+subjectNodePath);
		      
		          producerJSonObj.put("textSentMailTime", textSentMailTime);
		          producerJSonObj.put("subjectNodePath", subjectNodePath);
		     	  producerJSonObj.put("from_Source", from_Source);
		     	  producerJSonObj.put("timestampDate", timestampDate);
		     	  producerJSonObj.put("timestampDateAndTime", timestampDateAndTime);
		     	  producerJSonObj.put("extension", "html");
		      
		      Node subjectNodeNode= StatusForUi.collectProcessDataStatus(out, session, cronNodeName, textSentMailTime, subjectNode.getName().toString(), "YES", "", "", "", "", emailUrl, subjectNodePath,"", from_Source);
		      producerJSonObj.put("subjectNodeNode", subjectNodeNode.getPath());
		      String ExpertScriptCallHere=ExpertScriptCall.postExpertScript(textTomcatFilePath, out);
		    
		      String filepathfromourside="/home/ubuntu/TestedMailJSon/"+textNode.getName().toString()+"_"+timestampDateAndTime+".txt";
			  boolean checkjsonString=SaveReportDataClass.isJSONValid(ExpertScriptCallHere);
			    if(checkjsonString==true){
			    	producerJSonObj.put("ExpertScriptCallHere", ExpertScriptCallHere);
			    	subjectNodeNode.setProperty("Nlp1", "YES");
			    	textNode.setProperty("Flag", "1");
			    	
			    	out.println("ExpertCodeOutputHtml: "+ExpertScriptCallHere);
			        out.println("textTomcatFilePath: "+textTomcatFilePath);
			        out.println("textSentMailTime_html: "+textSentMailTime);
			        String pallaviCodeHere= pallavi_UpdatedScript_copy_25_04_19.updatedMainScript(out, session, ExpertScriptCallHere, emailUrl, textSentMailTime, subjectNodePath, from_Source, timestampDate, timestampDateAndTime, filepathfromourside);
			        boolean checkjsonStringAbhishek=SaveReportDataClass.isJSONValid(pallaviCodeHere);
			        if(checkjsonStringAbhishek){
						  out.println("pallaviCodeHereHtml: "+pallaviCodeHere);
						  subjectNodeNode.setProperty("Nlp2", "YES");
						  textNode.setProperty("Flag", "1");
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
					        				producerJSonObj.put("finalwithQty", finalwithQty);
					        				pallavi_WriteFile.writeUsingOutputStream( "AbhishekJSON ::====== "+finalwithQty.toString() ,filepathfromourside );
					        				
					        				ActiveMQCall amq=new ActiveMQCall();
					        				amq.GetProducer("PROCESSMAILS",amq.createRandomString(), producerJSonObj.toString());
					        				
					        				//SaveReportDataClassNewStr.parseAllReportFromJsonToSave(out, session, finalwithQty, emailUrl, textSentMailTime,subjectNodePath, from_Source, timestampDate, timestampDateAndTime, "html", ExpertScriptCallHere);
									          
					        				textNode.setProperty("Flag", "1");
					        			}else{
					        				subjectNodeNode.setProperty("Nlp3_and_4_QC", "NO");
					        				textNode.setProperty("Flag", "1");
					        			}
				        				}
				        			}
				        			 }
				        		 }
				        	 }
			        	 
			        }else{
			        	subjectNodeNode.setProperty("Nlp2", "NO");
			        	textNode.setProperty("Flag", "1");
			        }
			    }else{
			    	subjectNodeNode.setProperty("Nlp1", "NO");
			    	textNode.setProperty("Flag", "1");
			    }
		  
			   /* boolean reportCheck=StatusForUi.statusCheckReportForUi(finalwithQty);
			      if(reportCheck==true){
			    	  subjectNodeNode.setProperty("Ui_Update", "YES");
			    	  String allreport=StatusForUi.getReportTypeUi(finalwithQty);
			    	  subjectNodeNode.setProperty("Type", allreport);
			      }else{
			    	  subjectNodeNode.setProperty("Ui_Update", "NO");
			      }*/
			    session.save();
		  }
		} //end
	} catch (Exception e) {
		try {
			session.save();
			
		} catch (RepositoryException e1) {
			e1.printStackTrace(out);
		}
	}finally {
		 emailUrl=null;
	     finalwithQty=null;
	}
}


}

