package com.mongocode;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.Workspace;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;

import org.apache.sling.commons.json.JSONArray;

import com.readGmail.GmailMethods;

public class RemoveNodeFifteenDays {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		 DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		 
		Date cronDate = formatter.parse("24-04-2019");
		String cronDateString=formatter.format(cronDate);
		System.out.println("cronDateString: "+cronDateString);
		
		 Calendar cal = GregorianCalendar.getInstance();
//		 cal.add( Calendar.DAY_OF_YEAR, -15);
		 cal.setTime(cronDate);
		 cal.add(Calendar.DAY_OF_YEAR, -15);
		 
		 Date threeDaysAgo = cal.getTime();
		 String threeDaysBeforeDate=sdf.format(threeDaysAgo);
		 System.out.println("fifteenDaysBeforeDate: "+threeDaysBeforeDate);
		 
		 String in= "-15";
		 int i= Integer.parseInt(in);
		 System.out.println(i);
		 
		
	}
	
public static void removeFifteenDaysbeforeData(Session session, String NodePath, PrintWriter out, int passRemoveDays, String currentDate){
			
		
		try {
			if ( !GmailMethods.isNullString(currentDate)  && !GmailMethods.isNullString(NodePath) && passRemoveDays!=0 ) {
				
				/* DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				 Calendar cal = GregorianCalendar.getInstance();
				 cal.add( Calendar.DAY_OF_YEAR, passRemoveDays);
				 Date threeDaysAgo = cal.getTime();
				 String threeDaysBeforeDate=sdf.format(threeDaysAgo);*/
				 SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
				 DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				
				 Date currentdate = formatter.parse(currentDate);
				 Calendar cal = GregorianCalendar.getInstance();
//				 cal.add( Calendar.DAY_OF_YEAR, -15);
				 cal.setTime(currentdate);
				 cal.add(Calendar.DAY_OF_YEAR, passRemoveDays);
				 
				 Date threeDaysAgo = cal.getTime();
				 String threeDaysBeforeDate=sdf.format(threeDaysAgo);
				 out.println("threeDaysBeforeDate: "+threeDaysBeforeDate);
				
				 String dataTo = convertStringToDate(threeDaysBeforeDate) + "T23:59:59.999Z";
				
				 
				Workspace workspace = session.getWorkspace();
				String slingqery = "select [ReportTimestamp] from [nt:base] where ReportTimestamp <= CAST('"+dataTo+"' AS DATE) and ISDESCENDANTNODE('"+NodePath+"')";		
				out.println("slingqery:: "+slingqery);
				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();
				NodeIterator iterator = queryResult.getNodes();
				out.println("size:: "+queryResult.getNodes().getSize());
				JSONArray arr =new JSONArray();
				while (iterator.hasNext()) {
					Node nextnode = iterator.nextNode();
				
					
					//nextnode.remove();
					//out.println("remove node: "+nextnode.getPath());
					
					if (nextnode.hasProperty("ReportTimestamp")) {
						String ReportTimestamp1 = nextnode.getProperty("ReportTimestamp").getString();
						out.println("date ::"+ReportTimestamp1);
						arr.put(nextnode.getPath());
					}
					
				}
				for(int i=0; i<arr.length(); i++){
					out.println("arr.getString(i) "+arr.getString(i));

					Node node1= session.getNode(arr.getString(i));
					 out.println("node1 "+node1.getPath());
                  node1.remove();
                 out.println("Removed");
				}
				
			}else{
				out.println("no data Available which you have passed to delete the nodes");
			}
			
			session.save();
			
			} catch (Exception e) {
				e.printStackTrace(out);
				
			}
}

public static  String convertStringToDate(String dateString)
{
    Date date = null;
    String formatteddate = null;
    DateFormat df = null;
//    DateFormat dfOut = new SimpleDateFormat("dd-MM-yyyy"); //E MMM dd HH:mm:ss Z yyyy
//    DateFormat dfOut = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");//, Locale.ENGLISH
    DateFormat dfOut = new SimpleDateFormat("yyyy-MM-dd"); //E MMM dd HH:mm:ss Z yyyy
    try{
    	if (dateString.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
    		df = new SimpleDateFormat("dd/MM/yyyy");
    		date = df.parse(dateString);
    	
    }
    	else if (dateString.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")) {
    		df = new SimpleDateFormat("dd-MM-yyyy");
    		date = df.parse(dateString);
    	}
    	else if (dateString.matches("([0-9]{4})([0-9]{2})([0-9]{2})")) {
    		df = new SimpleDateFormat("yyyyMMdd");
    		date = df.parse(dateString);
    	} else if (dateString.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")) {
    		df = new SimpleDateFormat("yyyy-MM-dd");
    		date = df.parse(dateString);
    	} else if (dateString.matches("([0-9]{4})/([0-9]{2})/([0-9]{2})")) {
    		df = new SimpleDateFormat("yyyy/MM/dd");
    		date = df.parse(dateString);
    	}else if (dateString.matches("([0-9]{1})/([0-9]{1})/([0-9]{2})")) {
    		df = new SimpleDateFormat("d/M/yy");
    		date = df.parse(dateString);
    	}else if (dateString.matches("([0-9]{1})-([0-9]{1})-([0-9]{2})")) {
    		df = new SimpleDateFormat("d-M-yy");
    		date = df.parse(dateString);
    	}else if (dateString.matches("([0-9]{2})/([0-9]{1})/([0-9]{1})")) {
    		df = new SimpleDateFormat("yy/M/d");
    		date = df.parse(dateString);
    	}else if (dateString.matches("([0-9]{2})-([0-9]{1})-([0-9]{1})")) {
    		df = new SimpleDateFormat("yy-M-d");
    		date = df.parse(dateString);
    	}else if (dateString.matches("([0-9]{1})/([0-9]{2})/([0-9]{4})")) {
    		df = new SimpleDateFormat("d/MM/yyyy");
    		date = df.parse(dateString);
    	}
    	else if (dateString.matches("([0-9]{1})/([0-9]{1})/([0-9]{4})")) {
    		df = new SimpleDateFormat("d/M/yyyy");
    		date = df.parse(dateString);
    	}
    	else if (dateString.matches("([0-9]{2})/([0-9]{1})/([0-9]{2})")) {
    		df = new SimpleDateFormat("dd/M/yy");
    		date = df.parse(dateString);
    	}
    	else if (dateString.matches("([0-9]{2})/([0-9]{1})/([0-9]{4})")) {
    		df = new SimpleDateFormat("dd/M/yyyy");
    		date = df.parse(dateString);
    	}else if (dateString.matches("([0-9]{2})/([0-9]{2})/([0-9]{2})")) {
    		df = new SimpleDateFormat("dd/MM/yy");
    		date = df.parse(dateString);
    	}else if (dateString.matches("([0-9]{1})/([0-9]{2})/([0-9]{2})")) {
    	df = new SimpleDateFormat("d/MM/yy");
    	date = df.parse(dateString);
    	}else if (dateString.matches("([0-9]{1})-([0-9]{2})-([0-9]{4})")) {
    		df = new SimpleDateFormat("d-MM-yyyy");
    		date = df.parse(dateString);
    	}
    	else if (dateString.matches("([0-9]{1})-([0-9]{1})-([0-9]{4})")) {
    		df = new SimpleDateFormat("d-M-yyyy");
    		date = df.parse(dateString);
    	}
    	else if (dateString.matches("([0-9]{2})-([0-9]{1})-([0-9]{4})")) {
    		df = new SimpleDateFormat("dd-M-yyyy");
    		date = df.parse(dateString);
    	}else if (dateString.matches("([0-9]{2})-([0-9]{2})-([0-9]{2})")) {
    	df = new SimpleDateFormat("dd-MM-yy");
    	date = df.parse(dateString);
    	}else if (dateString.matches("([0-9]{1})-([0-9]{2})-([0-9]{2})")) {
    		df = new SimpleDateFormat("d-MM-yy");
    		date = df.parse(dateString);
    	}
  
    	//............................
    /*	else if (dateString.matches("([0-9]{1})([0-9]{1})([0-9]{2})")) {
    		df = new SimpleDateFormat("dMyy");
    		date = df.parse(dateString);
    	}else if (dateString.matches("([0-9]{2})([0-9]{1})([0-9]{1})")) {
    		df = new SimpleDateFormat("yyMd");
    		date = df.parse(dateString);
    	}else if (dateString.matches("([0-9]{1})([0-9]{2})([0-9]{4})")) {
    		df = new SimpleDateFormat("dMMyyyy");
    		date = df.parse(dateString);
    	}else if (dateString.matches("([0-9]{1})([0-9]{1})([0-9]{4})")) {
    		df = new SimpleDateFormat("dMyyyy");
    		date = df.parse(dateString);
    	}else if (dateString.matches("([0-9]{2})([0-9]{1})([0-9]{2})")) {
    		df = new SimpleDateFormat("ddMyy");
    		date = df.parse(dateString);
    	}else if (dateString.matches("([0-9]{2})([0-9]{1})([0-9]{4})")) {
    		df = new SimpleDateFormat("ddMyyyy");
    		date = df.parse(dateString);
    	}else if (dateString.matches("([0-9]{2})([0-9]{2})([0-9]{2})")) {
    		df = new SimpleDateFormat("ddMMyy");
    		date = df.parse(dateString);
    	}else if (dateString.matches("([0-9]{1})([0-9]{2})([0-9]{2})")) {
        	df = new SimpleDateFormat("dMMyy");
        	date = df.parse(dateString);
	    	}else if (dateString.matches("([0-9]{2})([0-9]{2})([0-9]{4})")) {
	    		df = new SimpleDateFormat("ddMMyyyy");
	    		date = df.parse(dateString);
	    	}*/
    	
    
    formatteddate = dfOut.format(date);
}
    catch ( Exception ex ){
    	ex.getMessage();
    }
    return formatteddate;
}

}
