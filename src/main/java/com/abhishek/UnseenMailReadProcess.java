package com.abhishek;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.ResourceBundle;


import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;

import com.readGmail.GmailMethods;
import com.readGmail.Gmail_Pojo;



public class UnseenMailReadProcess {
	static ResourceBundle bundle = ResourceBundle.getBundle("config");
	static Session session=null;
	 static Properties properties;
	// private static Store store ;
	// private static Folder folder;
	private static Store store;

	public static void main(String[] args) {

		Gmail_Pojo gm = null;
		gm = new Gmail_Pojo();
		gm.setProtocol(bundle.getString("protocol"));
		gm.setHost(bundle.getString("host"));
		gm.setPort(bundle.getString("port"));
		gm.setUserName(bundle.getString("userName"));
		gm.setPassWord(bundle.getString("password"));
		
		//String port=gm.getPort();
		//System.out.println(port);
//		processEmail(gm);
		
		/*String protocol = "imap";
		String host = "imap.gmail.com";
		String port = "993";

		String userName = "abhishek.tiwari@bizlem.com";
		String password = "a1b2hishek";*/
		
		//downloadEmails(protocol, host, port, userName, password);
		
		
	}
	/*public static void downloadEmails(String protocol, String host, String port, String userName, String password) {
		
		java.util.Properties properties=getServerProperties(protocol, host, port);
		javax.mail.Session session = javax.mail.Session.getDefaultInstance(properties);
		
		try {
			
			// connects to the message store
			   Store store = session.getStore(protocol);
			   store.connect(userName, password);
						
						
			// opens the inbox folder
				Folder folderInbox = store.getFolder("INBOX");
				folderInbox.open(Folder.READ_WRITE); // .READ_ONLY);

				int x = -2;
				int y = 1;
				Calendar cal = GregorianCalendar.getInstance();
				cal.add(Calendar.DAY_OF_YEAR, y);
				Date tomorrow = cal.getTime();
				System.out.println(tomorrow);
				System.out.println(tomorrow);
				cal.add(Calendar.DAY_OF_YEAR, x);
				Date oneDaysAgo = cal.getTime();
				System.out.println(oneDaysAgo);
				System.out.println(oneDaysAgo);

				SearchTerm olderThan = new ReceivedDateTerm(ComparisonTerm.LT, tomorrow);
				SearchTerm newerThan = new ReceivedDateTerm(ComparisonTerm.GT, oneDaysAgo);
				SearchTerm andTerm = new AndTerm(olderThan, newerThan);
				Message[] messages = folderInbox.search(andTerm);

				int count = folderInbox.getMessageCount();
				System.out.println(count);
				
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	*/

	public static Properties getServerProperties(String protocol, String host, String port) {
		 properties = new Properties();
		// server setting
		properties.put(String.format("mail.%s.host", protocol), host);
		properties.put(String.format("mail.%s.port", protocol), port);
		// SSL setting
		properties.setProperty(String.format("mail.%s.socketFactory.class", protocol),
				"javax.net.ssl.SSLSocketFactory");
		properties.setProperty(String.format("mail.%s.socketFactory.fallback", protocol), "false");
		properties.setProperty(String.format("mail.%s.socketFactory.port", protocol), String.valueOf(port));

		return properties;
	}
	
	
	public static Folder openFolder(String folderName) throws Exception {
        
         store = null;
		 Folder folder   = store.getDefaultFolder();
        
        folder = folder.getFolder(folderName);
        
        if (folder != null) {
        
        // try to open read/write and if that fails try read-only
        try {
            
            folder.open(Folder.READ_WRITE);
            
        } catch (MessagingException ex) {
            folder.open(Folder.READ_ONLY);
            
        }
        
	}// 
		return folder;
        
        
    }
	
	public static void processEmail( Gmail_Pojo gmail_pj, PrintWriter out) {

		try {
			
			getServerConnect(gmail_pj);
			Folder folder = openFolder("INBOX");
			SearchTerm term = searchMailterm(out);

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
						Message message = arrayMessages[i];

						
						//boolean schedulerReadMail=FifteenMinuteClass.returnTimeRange(recDate);
//						if (schedulerReadMail == true) {
							
						String seen = seen_UnseenEmails( message, out);
						if(seen.equals("0")){

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
						String receivedOnlyDate=null;
						Date recDate = message.getReceivedDate();
						if (recDate != null) {
						     out.println("recDate: " + recDate);
							Calendar cal = Calendar.getInstance();
							cal.setTime(recDate);
							 receivedOnlyDate = String.valueOf(cal.get(Calendar.DATE));
							
							 if(receivedOnlyDate!=null){
								 out.println("receivedOnlyDate:: "+receivedOnlyDate);
								 
								
								 Calendar cal1 = null;
								 cal1 = Calendar.getInstance();
								 Date todayDate = new Date(cal1.getTimeInMillis());
								 if(todayDate!=null){
								     out.println("todayDate:: "+todayDate);
								   String todayDateOnly=String.valueOf(cal1.get(Calendar.DATE));
								   out.println("todayDateOnly:: "+todayDateOnly);
								   
								 }// todayDate check null
								 
							 } //receivedOnlyDate null check
							
							 
						}// received date null check
						

						

							
						/*if (i == 1) {
						break;
					}*/
							
						}// check sheduler 

						
						 

						
						  }catch(Exception e){ continue; }
						 

					} // for close
				

				} // term check null close
			}

		} catch (Exception e) {
			 e.printStackTrace(out);
			out.print(e.getMessage());
		}

	}
	
	public static SearchTerm searchMailterm(PrintWriter out){
		
		SearchTerm term = null;
		try {
			
			int x = -2;
			int y = 1;
			Calendar cal = GregorianCalendar.getInstance();
			cal.add(Calendar.DAY_OF_YEAR, y);
			Date tomorrow = cal.getTime();
			//out.println(tomorrow);
			cal.add(Calendar.DAY_OF_YEAR, x);
			Date oneDaysAgo = cal.getTime();
			//out.println(oneDaysAgo);

			out.println(oneDaysAgo + " === " + tomorrow);
			
			SearchTerm olderThan = new ReceivedDateTerm(ComparisonTerm.LT, tomorrow);
			SearchTerm newerThan = new ReceivedDateTerm(ComparisonTerm.GT, oneDaysAgo);
			term = new AndTerm(olderThan, newerThan); // concat the search terms
			
			/*Calendar cal = null;
			cal = Calendar.getInstance();
			Date minDate = new Date(cal.getTimeInMillis());  // get today date
			cal.add(Calendar.DAY_OF_MONTH, 1);               // add 1 day
			Date maxDate = new Date(cal.getTimeInMillis());  // get tomorrow date
			
			out.println(maxDate + " === " + minDate);
			SearchTerm minDateTerm = new ReceivedDateTerm(ComparisonTerm.GE, minDate);
            SearchTerm maxDateTerm = new ReceivedDateTerm(ComparisonTerm.LE, maxDate);
            
            term = new AndTerm(minDateTerm, maxDateTerm);    // concat the search terms
*/				out.println("term: "+term);
		} catch (Exception e) {
			out.println(e.getMessage());
		}
		return term;
	}
	
	 public static String seen_UnseenEmails(Message message, PrintWriter out){
	    	
	    	String seen="";
	    	try {
				
	    		boolean isseen = message.getFlags().contains(Flags.Flag.SEEN);
	    		if (isseen == true) {
         		seen = "1";
         		//out.println("seen : "+seen);
         	}else{
         		seen = "0";
         		out.println("Unseen : "+seen);
         	} 
	    		
			} catch (Exception e) {
				out.println(e.getMessage());
			}
			return seen;
	    	
	    }
	 
	 public static Properties getServerConnect(Gmail_Pojo gmail_pj) {
		 
	     Properties properties = new Properties();
	try{
		 
		 
		// server setting
		properties.put(String.format("mail.%s.host", gmail_pj.getProtocol()), gmail_pj.getHost());
		properties.put(String.format("mail.%s.port", gmail_pj.getProtocol()), gmail_pj.getPort());
		
		// SSL setting
		properties.setProperty(String.format("mail.%s.socketFactory.class", gmail_pj.getProtocol()),"javax.net.ssl.SSLSocketFactory");
		properties.setProperty(String.format("mail.%s.socketFactory.fallback", gmail_pj.getProtocol()), "false");
		properties.setProperty(String.format("mail.%s.socketFactory.port", gmail_pj.getProtocol()), String.valueOf(gmail_pj.getPort()));
		
		Session session = javax.mail.Session.getDefaultInstance(properties);
		// connects to the message store
	    Store store = session.getStore(gmail_pj.getProtocol());
		store.connect(gmail_pj.getUserName(), gmail_pj.getPassWord());
		
	}catch(Exception e){
		e.printStackTrace();
	}
		return properties;
	}


}
