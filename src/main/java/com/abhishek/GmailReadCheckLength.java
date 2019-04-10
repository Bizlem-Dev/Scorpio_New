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
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
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
		@Property(name = "sling.servlet.paths", value = { "/servlet/service/len" }),
		@Property(name = "sling.servlet.resourceTypes", value = "sling/servlet/default"), // sling/servlet/default
		@Property(name = "sling.servlet.extensions", value = "data"), })

public class GmailReadCheckLength extends SlingAllMethodsServlet {
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
		String PassDateUser=request.getParameter("PassDateUser");
		response.setContentType("text/html");
		if (request.getRequestPathInfo().getExtension().equals("data")) {

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
				SearchTerm term = searchMailterm(out);

				if (term != null) {
					// fetches new messages from server
					// Message[] arrayMessages = folder.getMessages(); //
					// without
					// search we can
					// use this
					Message[] arrayMessages = folder.search(term); // search
					//out.println("arrayMessages  :: " + arrayMessages.length);
                      int j=0;
					for (int i = 0; i < arrayMessages.length; i++) { // this
						// will
						// print
						try { // all
								// message
							Message message = arrayMessages[i];

								String subject = message.getSubject();

								String sentDate = message.getSentDate().toString();
								sentDate = (GmailMethods.isNullString(sentDate)) ? "" : sentDate;

								Date recDate = message.getReceivedDate();
								String receivedOnlyDate = null;

								if (recDate != null) {
									//out.println("recDate: " + recDate);
									Calendar cal = Calendar.getInstance();
									cal.setTime(recDate);
									
									 receivedOnlyDate = cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
											+ cal.get(Calendar.YEAR);

											// out.println("receivedOnlyDate_Before: "+receivedOnlyDate);

											if (receivedOnlyDate.equals(PassDateUser)) {
                                                    j++;
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


												if (!GmailMethods.isNullString(subject)) {

													String subject_replace = subject.replace("RE: CXL     RE: ", "")
															.replace("CXL     RE: ", "").replace("REF: ", "")
															.replace("RE: ", "").replace("FW: ", "").replace("#", "_")
															.replace(":", "_").replace(",", "_").replace("[", "")
															.replace("]", "").replaceAll("[\\//|:]+", "").trim()
															.replaceAll("\\s{1,}", "_").replace("Fwd:", "")
															.replace("Fwd: Re :", "").replaceAll("[^\\p{ASCII}]", "")
															.replaceAll("\\_+", "_");
													subject_replace = subject_replace.replace("&", "AND")
															.replaceAll("\\*", "_");
													subject_replace = (GmailMethods.isNullString(subject_replace)) ? ""
															: subject_replace;

													out.println("MailCount: "+j);
													out.println("Subject: "+subject_replace);
													out.println("From: "+from);
													out.println("ReceivedDate: "+receivedOnlyDate);
												}

											}
											
											
								}

								/*
								 * if (i == 1) { break; }
								 */

							

						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					
					out.println("FinalMailCount:: "+j);
				}

			} catch (Exception e) {
				//e.printStackTrace(out);
				// out.println(e.getMessage());
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

			int x = -10;
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
