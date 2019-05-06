package com.stanbol;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;
import javax.mail.Message.RecipientType;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;
import javax.servlet.ServletException;

import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.jcr.api.SlingRepository;

import com.mongocode.GmailReadSaveInMongoDb;
import com.mongocode.MongoDbConnection;
import com.readGmail.GmailMethods;
import com.readGmail.Gmail_Pojo;

import ChangedStructureCurrent.GmailReadMailChanged;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest; 
import org.apache.sling.api.SlingHttpServletResponse;


@SlingServlet(paths = "/servlet/service/UnseenCountOnly")
public class SlingTestServlet extends SlingAllMethodsServlet {
	private static final long serialVersionUID = 1;
	@Reference
	private SlingRepository repo;

	static ResourceBundle bundle = ResourceBundle.getBundle("config");
	Session session = null;
	
	@Override
	public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Folder folder=null;
		Store store=null;
		try {
			
			session = repo.login(new SimpleCredentials("admin", "admin".toCharArray()));
			System.out.println("entered session");
			
			Gmail_Pojo gm = null;
			gm = new Gmail_Pojo();
			gm.setProtocol(bundle.getString("protocol"));
			gm.setHost(bundle.getString("host"));
			gm.setPort(bundle.getString("port"));
			gm.setUserName(bundle.getString("userName"));
			gm.setPassWord(bundle.getString("password"));
			
			 Properties properties = new Properties();
			// server setting
				properties.put(String.format("mail.%s.host", gm.getProtocol()), gm.getHost());
				properties.put(String.format("mail.%s.port", gm.getProtocol()), gm.getPort());
				
				// SSL setting
				properties.setProperty(String.format("mail.%s.socketFactory.class", gm.getProtocol()),"javax.net.ssl.SSLSocketFactory");
				properties.setProperty(String.format("mail.%s.socketFactory.fallback", gm.getProtocol()), "false");
				properties.setProperty(String.format("mail.%s.socketFactory.port", gm.getProtocol()), String.valueOf(gm.getPort()));
				
				javax.mail.Session session	 = javax.mail.Session.getDefaultInstance(properties);
				
				// connects to the message store
			    store = session.getStore(gm.getProtocol());
				store.connect(gm.getUserName(), gm.getPassWord());
				
				 // Open the Folder
		         folder = store.getDefaultFolder();
		        
		        folder = folder.getFolder(bundle.getString("GmailFolderName"));
		        
		        if (folder != null) {
		        	folder.open(Folder.READ_WRITE);
		        	

					Flags seen = new Flags(Flags.Flag.SEEN);
					FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
//					FlagTerm unseenFlagTerm = new FlagTerm(seen, true);
					
					Calendar cal1 = GregorianCalendar.getInstance();
					cal1.add(Calendar.DAY_OF_MONTH, -4); //12
//					cal1.add(Calendar.DAY_OF_MONTH, -6);
					Date twelveDaysBefore = cal1.getTime();
					ReceivedDateTerm term = new ReceivedDateTerm(ComparisonTerm.GT,twelveDaysBefore);
					SearchTerm searchTerm = new AndTerm(unseenFlagTerm,term);
					
						Message[] arrayMessages = folder.search(searchTerm); // search
						//out.println("arrayMessages  :: " + arrayMessages.length);
						System.out.println("arrayMessages :: "+arrayMessages.length);
						String formatedDate1=null;
		        	
						for (int i = 0; i < arrayMessages.length; i++) { 
							try{
							
							formatedDate1 = processMessage(out, arrayMessages, formatedDate1, i);
								 
							}catch(Exception e){
								continue;
							}
						} // for close
						
						if( !GmailMethods.isNullString(formatedDate1) ){
							MongoDbConnection.saveGmailReadCount("GmailReadCountDataBase", formatedDate1, String.valueOf(arrayMessages.length));
						}
						
						
		        	
		        }// folder check not null
			
		        
		        
		        
			
			
		} catch (Exception e) {
			e.printStackTrace();
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
	
	public String processMessage(PrintWriter out, Message[] arrayMessages, String formatedDate1, int i)
			throws MessagingException {
		// this
			// will
			// print
			Message message=null;
			try { // all
					// message
				 message = arrayMessages[i];
				// out.println(message.getReceivedDate());
				
				Date recDate = message.getReceivedDate();
//							recDate = (GmailMethods.isNullString(recDate)) ? "" : recDate;
				/*String mailReceivedDate = "";
				if (!GmailMethods.isNullString(recDate)) {
					Date date1 = GmailMethods.parseDate(recDate);
					mailReceivedDate = GmailMethods.formatDate(date1);
				}*/
				//out.println("UnreadMailFound:: "+recDate+" "+i);
				//out.println("Subject:: "+message.getSubject());
				//out.println("UnreadMailFound:: "+recDate+" "+i);
				
				//boolean schedulerReadMail=FifteenMinuteClass.returnTimeRange(recDate);
//							if (schedulerReadMail == true) {
					
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

				String subject = message.getSubject();

				String toList = GmailMethods.parseAddresses(message.getRecipients(RecipientType.TO));
				String ccList = GmailMethods.parseAddresses(message.getRecipients(RecipientType.CC));

				String sentDate = message.getSentDate().toString();
				sentDate = (GmailMethods.isNullString(sentDate)) ? "" : sentDate;
				
				
				

				// String dateStr = "Mon Jun 18 00:00:00 IST 2012";
				DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
				Date date3 = (Date) formatter.parse(sentDate);
				String formatedDate=null;
//							String formatedDate1=null;

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
					//out.println("formatedDate1 : " + formatedDate1);
				}

				String mailSentDate = "";
				if (!GmailMethods.isNullString(sentDate)) {
					Date date = GmailMethods.parseDate(sentDate);
					mailSentDate = GmailMethods.formatDate(date);
				}

				

				String contentType = message.getContentType();
				System.out.println("subject  :: " + subject);
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
				       // out.println("bodyCheck: "+bodyCheck);
				        
				      //  bodyCheck = (GmailMethods.isNullString(bodyCheck)) ? "" : bodyCheck;
				
				// bodyText = bodyText.toString().replaceAll("\\<.*?>",
				// "").replaceAll("[image:\\s]*.*]", "");
//							bodyText = (GmailMethods.isNullString(bodyText)) ? "" : bodyText;

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
						System.out.println("same below");
						
						if( !GmailMethods.isNullString(formatedDate1) ){
							int AttachmentLength=GmailReadSaveInMongoDb.attachmentCount(contentType, message);
							GmailReadSaveInMongoDb.GmailReadMongo("ReadMail", message.getSentDate().toString(), message.getSubject(), message.getSentDate(), "1", AttachmentLength);
						}

					} else {
						
						subject_replace_node_mainnode = content.addNode(subject_replace);
						subject_replace_node_mainnode.setProperty("jcr:count", String.valueOf(0));
						session.save();
						
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
						//out.println("else returnNodeForSameTextAttachment: " + returnNodeForSameTextAttachment);

						GmailReadMailChanged.AttachmentSaveData(out, contentType, message, returnNodeForSameTextAttachment,
								subject_replace, session);
						
						//...................................... mongosubject readmail flag1 here
						
						if( !GmailMethods.isNullString(formatedDate1) ){
							int AttachmentLength=GmailReadSaveInMongoDb.attachmentCount(contentType, message);
							GmailReadSaveInMongoDb.GmailReadMongo("ReadMail", message.getSentDate().toString(), message.getSubject(), message.getSentDate(), "1", AttachmentLength);
						}
						
						//........................................ end mongo
						
						//SaveMailCount.saveMailCount(out, session, formatedDate1, subject_replace_node_mainnode.getName(), formatedDate, subject_replace_node_mainnode.getPath());
						//original mails
						
						//session.save();
					} 
					}
					
				/*if (i == 1) {
				break;
			}*/
					
//							}// check sheduler 

				
				 

				
				  }catch(Exception e){ 
					  System.out.println("Errorcaught insert into Mongo db "+e.getMessage());
					  MongoDbConnection MDC=new MongoDbConnection();
					  String errorMsg=e.getMessage();
					  MDC.getMongoDbAnyConn("ReadmailDataBase", "ReadMailCollection", message.getSentDate().toString(), "0", message.getSubject(), errorMsg);
					  
					  return formatedDate1;
					  
				  }
		return formatedDate1;
	}
	

}
