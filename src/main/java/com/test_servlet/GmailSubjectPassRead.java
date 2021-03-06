package com.test_servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;
import javax.mail.Message.RecipientType;
import javax.mail.search.SearchTerm;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.jcr.api.SlingRepository;

import com.mongocode.MongoDbConnection;
import com.mongodb.DB;
import com.readGmail.GmailMethods;
import com.readGmail.Gmail_Pojo;

import ChangedStructureCurrent.GmailReadMailChanged;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;

@SlingServlet(paths = "/FirstStep_Subject")
public class GmailSubjectPassRead extends SlingAllMethodsServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Reference
	private SlingRepository repo;
	
	static ResourceBundle bundle = ResourceBundle.getBundle("config");

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try{
//			Session session = null;
			Session session = repo.login(new SimpleCredentials("admin", "admin".toCharArray()));
		//out.println("session :: 123"+session);
			out.println("servlet started ");
		Gmail_Pojo gm = null;
		gm = new Gmail_Pojo();
		gm.setProtocol(bundle.getString("protocol"));
		gm.setHost(bundle.getString("host"));
		gm.setPort(bundle.getString("port"));
		gm.setUserName(bundle.getString("userName"));
		gm.setPassWord(bundle.getString("password"));

		String passSubject=request.getParameter("passSubject");
//		String passDateParameter=request.getParameter("arg");
		out.println("method started ");
	    processEmail(session,gm, out, null, "" , passSubject);
		
	    
		}catch(Exception e){
//		    e.printStackTrace(out);
		    out.println(e.getMessage());
		}
		
	}

	public static void processEmail(Session session, Gmail_Pojo gmail_pj, PrintWriter out, DB mongoDataBase, String mongoId, String passSubject) throws MessagingException {
		out.println("method  inside	 ");
		Message message=null;
		Folder folder=null;
		Store store=null;
		
		try {
			// session = repo.login(new SimpleCredentials("admin",
			// "admin".toCharArray()));
			out.println("length :: 123" + session);
			String arrSub[]={"HANDY & MR FUEL OIL POSITIONS BASIS ROTTERDAM, AS OF TUESDAY, 23RD APRIL, 2019.","BRAEMAR ACM - CLEAN LR1 LIST BSS FUJAIRAH - 23 APRIL 2019","BRAEMAR ACM - CLEAN LR2 LIST BSS FUJAIRAH - 23 APRIL 2019",
					"DIRTY HANDY & MR POSITIONS","DIRTY PANAMAX POSITIONS – EUROPE",
					"HANDY/MR DPP UKC TONNAGE LIST UPDATED 23/04/19","CLARKSONS PLATOU AG LR1 LIST DATED 23.04.19",
					"CLARKSONS PLATOU AG MR LIST DATED 23.04.19","CLARKSONS PLATOU ECI  MR LIST DATED 23.04.19",
					"CLARKSONS PLATOU RSEA MR LIST 23.04.19"};
			 Properties properties = new Properties();
			// server setting
				properties.put(String.format("mail.%s.host", gmail_pj.getProtocol()), gmail_pj.getHost());
				properties.put(String.format("mail.%s.port", gmail_pj.getProtocol()), gmail_pj.getPort());
				
				// SSL setting
				properties.setProperty(String.format("mail.%s.socketFactory.class", gmail_pj.getProtocol()),"javax.net.ssl.SSLSocketFactory");
				properties.setProperty(String.format("mail.%s.socketFactory.fallback", gmail_pj.getProtocol()), "false");
				properties.setProperty(String.format("mail.%s.socketFactory.port", gmail_pj.getProtocol()), String.valueOf(gmail_pj.getPort()));
				
				javax.mail.Session session1	 = javax.mail.Session.getDefaultInstance(properties);
				
				// connects to the message store
			    store = session1.getStore(gmail_pj.getProtocol());
				store.connect(gmail_pj.getUserName(), gmail_pj.getPassWord());
				
				 // Open the Folder
		         folder = store.getDefaultFolder();
		        
		        folder = folder.getFolder(bundle.getString("GmailFolderName"));
		
			
			//GmailMethods.getServerConnect(gmail_pj);
			//folder = GmailMethods.openFolder(bundle.getString("GmailFolderName"));
		        if (folder != null) {
		        	folder.open(Folder.READ_WRITE);
		      
//		     for(String emailSubject :arrSub){ 	
		    	 
			SearchTerm term = GmailMethods.searchMailterm(out);

			if (term != null) {
				// fetches new messages from server
				 //Message[] arrayMessages = folder.getMessages(); // without
				// search we can
				// use this
				Message[] arrayMessages = folder.search(term); // search
				// messages
				if (arrayMessages.length != 0) {
					out.println("length :: " + arrayMessages.length);

					// for (int i = arrayMessages.length - 1; i >= 0; i--) { //
					// this used for print last message only
					for (int i = 0; i < arrayMessages.length; i++) { // this
																// will
																		// print
						 try{  // all
						// message
						 message = arrayMessages[i];
						
					//	Date recDate = message.getReceivedDate();
//						recDate = (GmailMethods.isNullString(recDate)) ? "" : recDate;
						/*String mailReceivedDate = "";
						if (!GmailMethods.isNullString(recDate)) {
							Date date1 = GmailMethods.parseDate(recDate);
							mailReceivedDate = GmailMethods.formatDate(date1);
						}*/
						
					//	boolean schedulerReadMail=FifteenMinuteClass.returnTimeRange(recDate);
//						if (schedulerReadMail == true) {
							
						//out.println("ok");

						//String seen = GmailMethods.seen_UnseenEmails(out, message);

						Address[] fromAddress = message.getFrom();
						String from = "";
						if (fromAddress.length > 0) {
							from = fromAddress[0].toString();
							//out.println("from: " + from);

							if (from.indexOf("@") != -1) {
								from = from.substring(from.indexOf("@") + 1); // e.g.
																				// @google.com
							}
							if (from.indexOf(".") != -1) {
								from = from.substring(0, from.indexOf("."));
								from = (GmailMethods.isNullString(from)) ? "" : from;
								//out.println("from: " + from);
							}

						}

						String subject = message.getSubject().trim();
						
						//.... start subject equals.....
						
						if(subject.equalsIgnoreCase(passSubject)){
							out.println("subjectequals:: "+subject);
						String toList = GmailMethods.parseAddresses(message.getRecipients(RecipientType.TO));
						String ccList = GmailMethods.parseAddresses(message.getRecipients(RecipientType.CC));

						String sentDate = message.getSentDate().toString();
						sentDate = (GmailMethods.isNullString(sentDate)) ? "" : sentDate;
						
						
						

						// String dateStr = "Mon Jun 18 00:00:00 IST 2012";
						DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
						Date date3 = (Date) formatter.parse(sentDate);
						String formatedDate=null;
						String formatedDate1=null;

						if (date3 != null) {
							//out.println("date3: " + date3);
							Calendar cal = Calendar.getInstance();
							cal.setTime(date3);

							 formatedDate = cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":"
									+ cal.get(Calendar.MINUTE);
							// formatedDate=convertStringToDate(formatedDate);
							formatedDate = (GmailMethods.isNullString(formatedDate)) ? "" : formatedDate;
							//out.println("formatedDate : " + formatedDate);

							 formatedDate1 = cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR);
							formatedDate1 = (GmailMethods.isNullString(formatedDate1)) ? "" : formatedDate1;
							out.println("formatedDate1 : " + formatedDate1);
						}

						/*String mailSentDate = "";
						if (!GmailMethods.isNullString(sentDate)) {
							Date date = GmailMethods.parseDate(sentDate);
							mailSentDate = GmailMethods.formatDate(date);
						}*/

						

						String contentType = message.getContentType();
						out.println("subject_original  :: " + subject);
						String bodyText = GmailReadMailChanged.getTextFromMessage1(message);
						
						 String bodyCheck=GmailReadMailChanged.getTextFromMessage2(message);
						        bodyCheck = bodyCheck.replace("RE: CXL     RE: ", "").replace("CXL     RE: ", "")
									.replace("REF: ", "").replace("RE: ", "").replace("FW: ", "").replace("#", "_")
									.replace(":", "_").replace(",", "_").replace("[", "").replace("]", "")
									.replaceAll("[\\//|:]+", "").trim().replaceAll("\\s{1,}", "_").replace("Fwd:", "")
									.replace("Fwd: Re :", "").replaceAll("[^\\p{ASCII}]", "").replaceAll("\\_+", "_")
									.replaceAll("[^a-zA-Z0-9.]", "_");
						        
						        bodyCheck = bodyCheck.toString().replaceAll("\\<.*?>",
								 "").replaceAll("[image:\\s]*.*]", "");
						      //  out.println("bodyCheck: "+bodyCheck);
						        
						      //  bodyCheck = (GmailMethods.isNullString(bodyCheck)) ? "" : bodyCheck;
						
						// bodyText = bodyText.toString().replaceAll("\\<.*?>",
						// "").replaceAll("[image:\\s]*.*]", "");
//						bodyText = (GmailMethods.isNullString(bodyText)) ? "" : bodyText;

						String Bodycheckmsg = bodyText.replace("RE: CXL     RE: ", "").replace("CXL     RE: ", "")
								.replace("REF: ", "").replace("RE: ", "").replace("FW: ", "").replace("#", "_")
								.replace(":", "_").replace(",", "_").replace("[", "").replace("]", "")
								.replaceAll("[\\//|:]+", "").trim().replaceAll("\\s{1,}", "_").replace("Fwd:", "")
								.replace("Fwd: Re :", "").replaceAll("[^\\p{ASCII}]", "").replaceAll("\\_+", "_")
								.replaceAll("[^a-zA-Z0-9.]", "_");

						Bodycheckmsg = (GmailMethods.isNullString(Bodycheckmsg)) ? "" : Bodycheckmsg;

						if (!GmailMethods.isNullString(subject)) {
							String subject_replace = subject.replace("RE: CXL     RE: ", "").replace("CXL     RE: ", "")
									.replace("REF: ", "").replace("RE: ", "").replace("FW: ", "").replace("#", "_")
									.replace(":", "_").replace(",", "_").replace("[", "").replace("]", "")
									.replaceAll("[\\//|:]+", "").trim().replaceAll("\\s{1,}", "_").replace("Fwd:", "")
									.replace("Fwd: Re :", "").replaceAll("[^\\p{ASCII}]", "").replaceAll("\\_+", "_");
							subject_replace = subject_replace.replace("&", "AND").replaceAll("\\*", "_");
							subject_replace = (GmailMethods.isNullString(subject_replace)) ? "" : subject_replace;

							if (subject_replace.contains("*")) {
								subject_replace = subject_replace.replaceAll("\\*", "").replace(" ", "_");
								subject_replace = (GmailMethods.isNullString(subject_replace)) ? "" : subject_replace;
							}
							
							out.println("subject_replace :: " + subject_replace);
							Node content = null;
							Node subject_replace_node_mainnode = null;

							if (session.getRootNode().hasNode("GmailData1")) {
								content = session.getRootNode().getNode("GmailData1");
							} else {
								content = session.getRootNode().addNode("GmailData1");
								content.setProperty("jcr:count", String.valueOf(0));
								session.save();
							}

							if (content.hasNode(subject_replace)) {
								subject_replace_node_mainnode = content.getNode(subject_replace);

								GmailReadMailChanged.dublicatecheck_mainNode(out, session, subject_replace, Bodycheckmsg, bodyText, toList,
										from, ccList, sentDate, subject, contentType, message,
										subject_replace_node_mainnode, formatedDate,formatedDate1, bodyCheck);
								
								if(mongoDataBase!=null){
								   MongoDbConnection.mongoUpdateDataBasedOnId(out, mongoDataBase, mongoId, "1");
								}
								out.println("same below");

							} else {
								out.println("else inside not node in sling");
								subject_replace_node_mainnode = content.addNode(subject_replace);
								subject_replace_node_mainnode.setProperty("jcr:count", String.valueOf(0));
								session.save();
								
								out.println("subject_replace_node_mainnode_else:: "+subject_replace_node_mainnode);
								
								String contentCount="";
								if (content.hasProperty("jcr:count")) {
									contentCount = content.getProperty("jcr:count").getString();
									out.println("contentCount: " + contentCount);
									content.setProperty("jcr:count", String.valueOf(Integer.parseInt(contentCount) + 1));
									session.save();
								}
								
								

								String mainnodeCount = "";
								if (subject_replace_node_mainnode.hasProperty("jcr:count")) {
									mainnodeCount = subject_replace_node_mainnode.getProperty("jcr:count").getString();
									out.println("else count: " + mainnodeCount);
									subject_replace_node_mainnode.setProperty("jcr:count",
											String.valueOf(Integer.parseInt(mainnodeCount) + 1));
									session.save();
								}
								Node returnNodeForSameTextAttachment = GmailReadMailChanged.createTextNode_Save_txt_sling(out,
										subject_replace_node_mainnode, subject_replace, bodyText, mainnodeCount,
										Bodycheckmsg, sentDate, session, from, formatedDate,formatedDate1, bodyCheck, message);
								out.println("else returnNodeForSameTextAttachment: " + returnNodeForSameTextAttachment);

							
								
								GmailReadMailChanged.AttachmentSaveData(out, contentType, message, returnNodeForSameTextAttachment,
										subject_replace, session);
								if(mongoDataBase!=null){
									//   MongoDbConnection.mongoUpdateDataBasedOnId(out, mongoDataBase, mongoId, "1");
									}
								//SaveMailCount.saveMailCount(out, session, formatedDate1, subject_replace_node_mainnode.getName(), formatedDate, subject_replace_node_mainnode.getPath());
								//original mails
								
								//session.save();
							} 
							}
						
						// update mongodb flag to 1
						
						
						}// subject equals
						/*if (i == 1) {
						break;
					}*/
							
//						}// check sheduler 

						
						 

						
						  }catch(Exception e){ 
							  out.println("continue mail "+e.getMessage());
							 // e.printStackTrace(out);
							 /* if(mongoDataBase!=null){
								   MongoDbConnection.mongoUpdateDataBasedOnId(out, mongoDataBase, mongoId, "2");
								}
							*/  
							  continue; 
							  
						  }
						 

					} // for close
				

					//GmailMethods.closeFolder();
					//GmailMethods.disconnect();
				} // term check null close
//			}
			}
			
		        }

		} catch (Exception e) {
			/* if(mongoDataBase!=null){
				   MongoDbConnection.mongoUpdateDataBasedOnId(out, mongoDataBase, mongoId, "2");
				}*/
//			  e.printStackTrace(out);
			  out.print("mainException"+e.getMessage());
			  e.printStackTrace(out);
		}
		finally{
			 try {
				 if(null!=folder){
					 folder.close(false);
			 }
				if(null!=store){
				store.close();
				}} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         
			
		}

	}
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException, IOException {
	
	}
	
}
