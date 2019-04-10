package com.reportinformationsystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javax.jcr.Node;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Message.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.readGmail.GmailMethods;
import com.readGmail.Gmail_Pojo;

import ChangedStructureCurrent.GmailReadMailChanged;

public class SampleGmailRead {
	static ResourceBundle bundle = ResourceBundle.getBundle("config");
	private static Folder folder;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gmail_Pojo gm = null;
		gm = new Gmail_Pojo();
		gm.setProtocol(bundle.getString("protocol"));
		gm.setHost(bundle.getString("host"));
		gm.setPort(bundle.getString("port"));
		gm.setUserName(bundle.getString("userName"));
		gm.setPassWord(bundle.getString("password"));
		processEmail(gm);
	}
	
	public static void processEmail(Gmail_Pojo gmail_pj) {

		try {

			GmailMethods.getServerConnect(gmail_pj);
			folder = GmailMethods.openFolder(bundle.getString("GmailFolderName"));
			SearchTerm term = searchMailterm();

			if (term != null) {
				// fetches new messages from server
				Message[] arrayMessages = folder.getMessages(); // without
																// search we can
																// use this
				// Message[] arrayMessages = folder.search(term); // search
				// messages
				if (arrayMessages.length != 0) {
					//out.println("length :: " + arrayMessages.length);

					// for (int i = arrayMessages.length - 1; i >= 0; i--) { //
					// this used for print last message only
					for (int i = 0; i < arrayMessages.length; i++) { // this
																		// will
																		// print
																		// all
																		// message
						Message message = arrayMessages[i];

						String seen = seen_UnseenEmails(message);

						Address[] fromAddress = message.getFrom();
						String from = "";
						if (fromAddress.length > 0) {
							from = fromAddress[0].toString();
						}

						String subject = message.getSubject();
						System.out.println("subject  :: "+subject);
						String toList = GmailMethods.parseAddresses(message.getRecipients(RecipientType.TO));
						String ccList = GmailMethods.parseAddresses(message.getRecipients(RecipientType.CC));

						String sentDate = message.getSentDate().toString();
						sentDate = (GmailMethods.isNullString(sentDate)) ? "" : sentDate;
						String mailSentDate = "";
						if (!GmailMethods.isNullString(sentDate)) {
							Date date = GmailMethods.parseDate(sentDate);
							mailSentDate = GmailMethods.formatDate(date);
						}

						String recDate = message.getReceivedDate().toString();
						recDate = (GmailMethods.isNullString(recDate)) ? "" : recDate;
						String mailReceivedDate = "";
						if (!GmailMethods.isNullString(recDate)) {
							Date date1 = GmailMethods.parseDate(recDate);
							mailReceivedDate = GmailMethods.formatDate(date1);
						}
						String bodyText = getTextFromMessage1(message);
						System.out.println("bodyText  ::  "+bodyText);

//						if (i == 10) {
//							break;
//						}
					} // for close

					GmailMethods.closeFolder();
					GmailMethods.disconnect();
				} // term check null close
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}
	
	static String getTextFromMessage1(Message message) throws MessagingException, IOException {
		String result = "";
		 
			if (message.isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			result = getTextFromMimeMultipart(mimeMultipart);
		}
		return result;

	}
	private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
		String result = "";
		int count = mimeMultipart.getCount();
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			//System.out.println("bodyPart123  :: "+bodyPart.getContentType());
			// if (bodyPart.isMimeType("text/plain")) {
			if (bodyPart.isMimeType("text/html")) {
				String html = (String) bodyPart.getContent();
				//System.out.println("html  :: "+html);
//				Document doc = Jsoup.parseBodyFragment(html);
//				org.jsoup.nodes.Element body = doc.body();
				result = html;
			} else if (bodyPart.getContent() instanceof MimeMultipart) {
				result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
			}
		}
		return result;
	}

	public static SearchTerm searchMailterm(){
		
		SearchTerm term = null;
		try {
			
			Calendar cal = null;
			cal = Calendar.getInstance();
			Date minDate = new Date(cal.getTimeInMillis());  // get today date
			cal.add(Calendar.DAY_OF_MONTH, 1);               // add 1 day
			Date maxDate = new Date(cal.getTimeInMillis());  // get tomorrow date
			
			System.out.println(maxDate + " === " + minDate);
			SearchTerm minDateTerm = new ReceivedDateTerm(ComparisonTerm.GE, minDate);
            SearchTerm maxDateTerm = new ReceivedDateTerm(ComparisonTerm.LE, maxDate);
            
            term = new AndTerm(minDateTerm, maxDateTerm);    // concat the search terms
            System.out.println("term: "+term);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return term;
	}
	
	 public static String seen_UnseenEmails(Message message){
	    	
	    	String seen="";
	    	try {
				
	    		//boolean isseen = message.getFlags().contains(Flags.Flag.SEEN);
	    		boolean isseen = message.getFlags().contains(Flags.Flag.SEEN);
	    		if (isseen == true) {
         		seen = "1";
         		//out.println("seen : "+seen);
         	}else{
         		seen = "0";
         		//out.println("Unseen : "+seen);
         	} 
	    		
			} catch (Exception e) {
				//out.println(e.getMessage());
			}
			return seen;
	    	
	    }
}
