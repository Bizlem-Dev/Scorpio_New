package com.abhishek;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.jcr.api.SlingRepository;

import com.abhishek.UnseenMailReadProcess;
import com.mongocode.GmailReadSaveInMongoDb;
import com.mongocode.MongoDbConnection;
import com.processcountMail.SaveMailCount;
import com.readGmail.CreateNodeFrmDirectory_Save_Attachment_text_xml_in_Sling;
import com.readGmail.ExcelReadMethods;
import com.readGmail.GmailMethods;
import com.readGmail.Gmail_Pojo;
import com.readGmail.ReadEmailFromGmail;
import com.readGmail.ReadVesselDatabase;
import com.readGmail.SlingMethods;
import com.reportinformationsystem.DateFromToApiReport;
import com.reportinformationsystem.ExpertScriptCall;
import com.reportinformationsystem.FifteenMinuteClass;
import com.reportinformationsystem.ReportBeforeOnWeekDataDisplay;
import com.reportinformationsystem.SaveReportDataClass;
import com.reportinformationsystem.fetchAllReport;
import com.srsimages.imageUpload;
import com.ui.data.CreateExcelMethods;

import ChangedStructureCurrent.ChangedStructureCurrent_Methods;
import ChangedStructureCurrent.GmailReadMailChanged;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Properties;

@Component(immediate = true, metatype = false)
@Service(value = javax.servlet.Servlet.class)
@Properties({ @Property(name = "service.description", value = "Save product Servlet"),
		@Property(name = "service.vendor", value = "VISL Company"),
		@Property(name = "sling.servlet.paths", value = { "/servlet/service/UnseenCountOnly" }),
		@Property(name = "sling.servlet.resourceTypes", value = "sling/servlet/default"), // sling/servlet/default
		@Property(name = "sling.servlet.extensions", value = "data"), })

public class UnseenMailCountOnly extends SlingAllMethodsServlet {
	@Reference
	private SlingRepository repo;

	static ResourceBundle bundle = ResourceBundle.getBundle("config");
	Session session = null;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		ResourceBundle bundle = ResourceBundle.getBundle("config");
		Folder folder;
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		if (request.getRequestPathInfo().getExtension().equals("data")) {
			Message message=null;
			try {

				session = repo.login(new SimpleCredentials("admin", "admin".toCharArray()));

				Gmail_Pojo gm = null;
				gm = new Gmail_Pojo();
				gm.setProtocol(bundle.getString("protocol"));
				gm.setHost(bundle.getString("host"));
				gm.setPort(bundle.getString("port"));
				gm.setUserName(bundle.getString("userName"));
				gm.setPassWord(bundle.getString("password"));

				GmailMethods.getServerConnect(gm);
				folder = GmailMethods.openFolder(bundle.getString("GmailFolderName"));
				//SearchTerm term = searchMailterm(out);

//				if (term != null) {
					// fetches new messages from server
					// Message[] arrayMessages = folder.getMessages(); //
					// without
					// search we can
					// use this
				
				Flags seen = new Flags(Flags.Flag.SEEN);
				FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
				
				Calendar cal1 = GregorianCalendar.getInstance();
				cal1.add(Calendar.DAY_OF_MONTH, -12);
				Date twelveDaysBefore = cal1.getTime();
				ReceivedDateTerm term = new ReceivedDateTerm(ComparisonTerm.GT,twelveDaysBefore);
				SearchTerm searchTerm = new AndTerm(unseenFlagTerm,term);
				
					Message[] arrayMessages = folder.search(searchTerm); // search
					out.println("arrayMessages  :: " + arrayMessages.length);
					String formatedDate1=null;

					for (int i = 0; i < arrayMessages.length; i++) { // this
						// will
						// print
						//Message message=null;
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
							out.println("UnreadMailFound:: "+recDate+" "+i);
							out.println("Subject:: "+message.getSubject());
							//out.println("UnreadMailFound:: "+recDate+" "+i);
							
							//boolean schedulerReadMail=FifteenMinuteClass.returnTimeRange(recDate);
//							if (schedulerReadMail == true) {
								
							//out.println("ok");

							//String seen = GmailMethods.seen_UnseenEmails(out, message);

							Address[] fromAddress = message.getFrom();
							String from = "";
							if (fromAddress.length > 0) {
								from = fromAddress[0].toString();
								out.println("from: " + from);

								if (from.indexOf("@") != -1) {
									from = from.substring(from.indexOf("@") + 1); // e.g.
																					// @google.com
								}
								if (from.indexOf(".") != -1) {
									from = from.substring(0, from.indexOf("."));
									from = (GmailMethods.isNullString(from)) ? "" : from;
									out.println("from: " + from);
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
								out.println("date3: " + date3);
								Calendar cal = Calendar.getInstance();
								cal.setTime(date3);

								 formatedDate = cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
										+ cal.get(Calendar.YEAR) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":"
										+ cal.get(Calendar.MINUTE);
								// formatedDate=convertStringToDate(formatedDate);
								formatedDate = (GmailMethods.isNullString(formatedDate)) ? "" : formatedDate;
								out.println("formatedDate : " + formatedDate);

								 formatedDate1 = cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
										+ cal.get(Calendar.YEAR);
								formatedDate1 = (GmailMethods.isNullString(formatedDate1)) ? "" : formatedDate1;
								out.println("formatedDate1 : " + formatedDate1);
							}

							String mailSentDate = "";
							if (!GmailMethods.isNullString(sentDate)) {
								Date date = GmailMethods.parseDate(sentDate);
								mailSentDate = GmailMethods.formatDate(date);
							}

							

							String contentType = message.getContentType();
							out.println("subject  :: " + subject);
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
							        out.println("bodyCheck: "+bodyCheck);
							        
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
									out.println("same below");

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
									out.println("else returnNodeForSameTextAttachment: " + returnNodeForSameTextAttachment);

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
								  MongoDbConnection MDC=new MongoDbConnection();
								  MDC.getMongoDbAnyConn("ReadmailDataBase", "ReadMailCollection", message.getSentDate().toString(), "0", message.getSubject(), out);
								  
								  continue;
								  
							  }
							 
					} // for close
					if( !GmailMethods.isNullString(formatedDate1) ){
						MongoDbConnection.saveGmailReadCount("GmailReadCountDataBase", formatedDate1, String.valueOf(arrayMessages.length));
					}
					
					
					

			} catch (Exception e) {
				MongoDbConnection MDC=new MongoDbConnection();
				  try {
					MDC.getMongoDbAnyConn("ReadmailDataBase", "ReadMailCollection", message.getSentDate().toString(), "0", message.getSubject() , out);
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				  e.printStackTrace(out);
				  out.print("mainException"+e.getMessage());
			}

		} // extension
	}

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

	}

	public static SearchTerm searchMailterm(PrintWriter out) {

		SearchTerm term = null;
		try {

			int x = -2;
			int y = 1;
			Calendar cal = GregorianCalendar.getInstance();
			cal.add(Calendar.DAY_OF_YEAR, y);
			Date tomorrow = cal.getTime();
			// out.println(tomorrow);
			cal.add(Calendar.DAY_OF_YEAR, x);
			Date oneDaysAgo = cal.getTime();
			// out.println(oneDaysAgo);

			out.println(oneDaysAgo + " === " + tomorrow);

			SearchTerm olderThan = new ReceivedDateTerm(ComparisonTerm.LT, tomorrow);
			SearchTerm newerThan = new ReceivedDateTerm(ComparisonTerm.GT, oneDaysAgo);
			term = new AndTerm(olderThan, newerThan); // concat the search terms

			/*
			 * Calendar cal = null; cal = Calendar.getInstance(); Date minDate =
			 * new Date(cal.getTimeInMillis()); // get today date
			 * cal.add(Calendar.DAY_OF_MONTH, 1); // add 1 day Date maxDate =
			 * new Date(cal.getTimeInMillis()); // get tomorrow date
			 * 
			 * out.println(maxDate + " === " + minDate); SearchTerm minDateTerm
			 * = new ReceivedDateTerm(ComparisonTerm.GE, minDate); SearchTerm
			 * maxDateTerm = new ReceivedDateTerm(ComparisonTerm.LE, maxDate);
			 * 
			 * term = new AndTerm(minDateTerm, maxDateTerm); // concat the
			 * search terms
			 */ out.println("term: " + term);
		} catch (Exception e) {
			out.println(e.getMessage());
		}
		return term;
	}

	public static String seen_UnseenEmails(Message message, PrintWriter out) {

		String seen = "";
		try {

			boolean isseen = message.getFlags().contains(Flags.Flag.SEEN);
			if (isseen == true) {
				seen = "1";
				// out.println("seen : "+seen);
			} else {
				seen = "0";
				out.println("Unseen : " + seen);
			}

		} catch (Exception e) {
			out.println(e.getMessage());
		}
		return seen;

	}

}
