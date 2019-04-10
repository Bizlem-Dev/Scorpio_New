package com.abhishek;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;

import com.readGmail.GmailMethods;
import com.readGmail.Gmail_Pojo;


public class GmailInboxLengthCheck {
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
try {
	           GmailMethods.getServerConnect(gm);
				folder = GmailMethods.openFolder(bundle.getString("GmailFolderName"));
				SearchTerm term = GmailInboxLengthCheck.searchMailterm();

				if (term != null) {
					// fetches new messages from server
					 //Message[] arrayMessages = folder.getMessages(); // without
					// search we can
					// use this
					Message[] arrayMessages = folder.search(term); // search
					System.out.println("arrayMessages  :: "+arrayMessages.length);
					
					for (int i = 0; i < arrayMessages.length; i++) { // this
						// will
						// print
							try{  // all
									// message
									Message message = arrayMessages[i];
									System.out.println(message.getReceivedDate());
								}catch (Exception e) {
									// TODO: handle exception
								}
					}	
				}
}catch (Exception e) {
	// TODO: handle exception
}
	}
	
	public static SearchTerm searchMailterm(){
		
		SearchTerm term = null;
		try {
			
			int x = -5;
			int y = 1;
			Calendar cal = GregorianCalendar.getInstance();
			cal.add(Calendar.DAY_OF_YEAR, y);
			Date tomorrow = cal.getTime();
			//out.println(tomorrow);
			cal.add(Calendar.DAY_OF_YEAR, x);
			Date oneDaysAgo = cal.getTime();
			//out.println(oneDaysAgo);

			System.out.println(oneDaysAgo + " === " + tomorrow);
			
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
*/				System.out.println("term: "+term);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return term;
	}


}
