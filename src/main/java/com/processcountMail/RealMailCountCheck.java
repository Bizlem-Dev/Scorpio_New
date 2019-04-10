package com.processcountMail;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import javax.jcr.Session;
import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;

import com.readGmail.GmailMethods;
import com.readGmail.Gmail_Pojo;

public class RealMailCountCheck {

	public static void realMailCountFind(PrintWriter out, String PassDateUser, Session session){
		
		try {
			 ResourceBundle bundle = ResourceBundle.getBundle("config");
			
			Gmail_Pojo gm = null;
			gm = new Gmail_Pojo();
			gm.setProtocol(bundle.getString("protocol"));
			gm.setHost(bundle.getString("host"));
			gm.setPort(bundle.getString("port"));
			gm.setUserName(bundle.getString("userName"));
			gm.setPassWord(bundle.getString("password"));
			
			GmailMethods.getServerConnect(gm);
			Folder folder = GmailMethods.openFolder(bundle.getString("GmailFolderName"));
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
                  ArrayList<String >data=new ArrayList<String>();
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

												//out.println("MailCount: "+j);
												//out.println("Subject: "+subject_replace);
												//out.println("From: "+from);
												//out.println("ReceivedDate: "+receivedOnlyDate);
												String Subject_Date=subject_replace+"_"+receivedOnlyDate;
												
												
												data.add(Subject_Date);
											}

										}
										
										
							}
							
					} catch (Exception e) {
						continue;
					}
				}
				
				out.println("FinalMailCount:: "+j);
				//out.println("data:: "+data);
				
				CompareForIrevalentSubject.saveRealMailCount(out, session, PassDateUser, data.toString(), String.valueOf(j));
				
			}
					
			
			
		} catch (Exception e) {
			
		}
					
					
		
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

			//out.println(oneDaysAgo + " === " + tomorrow);

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
			 */ 
			//out.println("term: " + term);
		} catch (Exception e) {
			out.println(e.getMessage());
		}
		return term;
	}
	
}
