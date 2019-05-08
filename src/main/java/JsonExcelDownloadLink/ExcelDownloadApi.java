package JsonExcelDownloadLink;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import org.apache.sling.commons.json.JSONObject;

import ChangedStructureCurrent.FetchData;


public class ExcelDownloadApi {

	public static void main(String[] args) {

	}
	
	
public static void excelTonnageDownloadData(Session session, PrintWriter out){
			
	JSONObject mainJsonobj = null;
		try {
			FetchData fd = new FetchData();
				 DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				 Date currentdate = new Date();
				 Calendar cal = GregorianCalendar.getInstance();
				 cal.setTime(currentdate);
				 cal.add(Calendar.DAY_OF_YEAR, -7);
				 
				 Date threeDaysAgo = cal.getTime();
				 String threeDaysBeforeDate=sdf.format(threeDaysAgo);
				 out.println("threeDaysBeforeDate: "+threeDaysBeforeDate);
				
				 String dataTo = convertStringToDate(threeDaysBeforeDate) + "T23:59:59.999Z";
				 
				Workspace workspace = session.getWorkspace();
				String slingqery = "select [ReportTimestamp] from [nt:base] where ReportTimestamp <= CAST('"+dataTo+"' AS DATE) and ISDESCENDANTNODE('/scorpioDataBase/ReportData/Tonnage/')";		
				out.println("slingqery:: "+slingqery);
				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();
				NodeIterator iterator = queryResult.getNodes();
				out.println("size:: "+queryResult.getNodes().getSize());
				
				JSONObject keyNameObject = null;
				JSONArray keyNameObjectJsonarray = new JSONArray();
				int i = 0;
				while (iterator.hasNext()) {
					Node nextnode = iterator.nextNode();
					
					int parseId = 0;
					i++;
					String subNodeName = nextnode.getName();
					
					
					
				}
			
			
			} catch (Exception e) {
				e.printStackTrace(out);
				
			}
}

public static  String convertStringToDate(String dateString)
{
    Date date = null;
    String formatteddate = null;
    DateFormat df = null;

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
  
    	
    
    formatteddate = dfOut.format(date);
}
    catch ( Exception ex ){
    	ex.getMessage();
    }
    return formatteddate;
}

}
