package ChangedStructureCurrent;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.Value;
import javax.jcr.Workspace;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;
import javax.jcr.query.Row;
import javax.jcr.query.RowIterator;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import com.readGmail.GmailMethods;
import com.readGmail.SlingMethods;

public class FetchData {

	public static void main(String[] args) throws IOException, JSONException, ParseException {
		
		/*String data=getdata("09-10-2018", "30-10-2018", "Position List");
		System.out.println(data);*/
		
		 String data= getdata("10-04-2019", "15-04-2019", "Spot");
		System.out.println(data);
		 /*String data=convertStringToDate("09-10-2018");
		 System.out.println(data);
		 Date date=GmailMethods.parseDate(data);
		 System.out.println(date);
		 
		 long s=9;
		 String d=String.valueOf(s);
		 s=Long.parseLong(d);
		 System.out.println(s);*/
	}
	
	public static  String convertStringToDate(String dateString)
	{
	    Date date = null;
	    String formatteddate = null;
	    DateFormat df = null;
//	    DateFormat dfOut = new SimpleDateFormat("dd-MM-yyyy"); //E MMM dd HH:mm:ss Z yyyy
//	    DateFormat dfOut = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");//, Locale.ENGLISH
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
	
	public static String getdata(String from, String to, String reporttype) throws IOException, JSONException {
        JSONObject inputdata =null;
   
      URL url = new URL("https://dev.bizlem.io:8082/scorpio/ReportApi"); 
      System.out.println("url =======" + url);
      HttpURLConnection http = (HttpURLConnection) url.openConnection();
      http.setRequestMethod("POST");
      http.setRequestProperty("Content-Type", "application/json");
//      http.setRequestProperty("Authorization", "Bearer "+SFDCAcessTokenApi.getAccessToken());
     // http.setUseCaches(false);
      http.setDoOutput(true);
      //http.setRequestMethod("POST");
     
      DataOutputStream writer = new DataOutputStream(http.getOutputStream());
      inputdata=new JSONObject();
      inputdata.put("from",from);
      inputdata.put("to",to);
      inputdata.put("reporttype",reporttype);
      
      //
//      OutputStream writer = http.getOutputStream();

//      writer.write(text.getBytes());
      writer.writeBytes(inputdata.toString());
      writer.flush();
      writer.close();
  	
      // out.println("Writer Url : "+writer);
      int responseCode = http.getResponseCode();
      System.out.println("POST Response Code :: " + responseCode);
      StringBuffer buffer = new StringBuffer();
      
      if (responseCode == 200) { // success
    	  
    	  
       BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
       String inputLine;
       while ((inputLine = in.readLine()) != null) {
           //System.out.println("POST request  worked");
           //System.out.println(in.readLine());
        buffer.append(inputLine);
       }
      //
   //  System.out.println(buffer.toString());
      in.close();
      
      } else {
       System.out.println("POST request not worked");
      }
      
  
   return buffer.toString();
}
	
	public  String fetch_ReportType(Session session,String reportType) {


		String vessel_id = "";

		try {

			
			Node obj = null;

			Workspace workspace = session.getWorkspace();

			String slingqery = "select [reportTypeId] from [nt:base] where (contains('reportTypeId','" + reportType
					+ "')) and ISDESCENDANTNODE('/scorpioDataBase/ReportType/')";
			Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
			QueryResult queryResult = query.execute();
			NodeIterator iterator = queryResult.getNodes();
			while (iterator.hasNext()) {

				obj = iterator.nextNode();
				vessel_id = obj.getProperty("reportType").getString();//reportType

			}
		

		} catch (Exception e) {
			return "";
		}
		return vessel_id;

		}

		public  String fetch_Port(Session session,String Port) {


//		String vessel_id = "";
			String data="";

		try {
		if(Port!=""){
			Node obj = null;
			String portName="";

			Workspace workspace = session.getWorkspace();

			String slingqery = "select [port_id] from [nt:base] where (contains('port_id','" + Port
					+ "')) and ISDESCENDANTNODE('/scorpioDataBase/Port/')";
			Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
			QueryResult queryResult = query.execute();
			NodeIterator iterator = queryResult.getNodes();
			JSONObject vesselJson=new JSONObject();
			while (iterator.hasNext()) {

				obj = iterator.nextNode();
				if(obj.hasProperty("portName")){
				   String vessel_id = obj.getProperty("portName").getString();//portName
				   vesselJson.put("portName", vessel_id);
				}

			}
			
			if(obj.hasProperty("port_id")){
				String vessel_id = obj.getProperty("port_id").getString();
				vesselJson.put("port_id", vessel_id);
			}
			/*if(obj.hasProperty("portName")){
				 portName = obj.getProperty("portName").getString();
				 vesselJson.put("portName", portName);
			}*/
			
			if(obj.hasProperty("Area")){
				String Area = obj.getProperty("Area").getString();
				vesselJson.put("Area", Area);
			}
			
			data=vesselJson.toString();
			
		}

		} catch (Exception e) {
			return "";
		}
		return data;

		}

		public  String fetch_CargoType(Session session,String CargoType) {


		String vessel_id = "";

		try {

			if(CargoType!=""){
			Node obj = null;

			Workspace workspace = session.getWorkspace();

			String slingqery = "select [cargoTypeId] from [nt:base] where (contains('cargoTypeId','" + CargoType
					+ "')) and ISDESCENDANTNODE('/scorpioDataBase/CargoType/')";
			Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
			QueryResult queryResult = query.execute();
			NodeIterator iterator = queryResult.getNodes();
			while (iterator.hasNext()) {

				obj = iterator.nextNode();
				vessel_id = obj.getProperty("cargoType").getString();//cargoType

			}
			}

		} catch (Exception e) {
			return "";
		}
		return vessel_id;

		}

		public  String fetch_Operators(Session session,String Operators) {


		String vessel_id = "";

		try {

			if(Operators!=""){
			Node obj = null;

			Workspace workspace = session.getWorkspace();

			String slingqery = "select [operatorsNameId] from [nt:base] where (contains('operatorsNameId','" + Operators
					+ "')) and ISDESCENDANTNODE('/scorpioDataBase/Operators/')";
			Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
			QueryResult queryResult = query.execute();
			NodeIterator iterator = queryResult.getNodes();
			while (iterator.hasNext()) {

				obj = iterator.nextNode();
				vessel_id = obj.getProperty("operatorsName").getString();//operatorsName

			}
			}

		} catch (Exception e) {
			return "";
		}
		return vessel_id;

		}

		public  String fetch_vesselName(Session session,String vesselName) {

		/*JSONObject allVesselProperties=null;*/
			String data="";
		try {

			Node obj = null;

			Workspace workspace = session.getWorkspace();

			String slingqery = "select [vessel_Id] from [nt:base] where (contains('vessel_Id','" + vesselName
					+ "')) and ISDESCENDANTNODE('/scorpioDataBase/vessel/')";
			Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
			QueryResult queryResult = query.execute();
			NodeIterator iterator = queryResult.getNodes();
			while (iterator.hasNext()) {

				obj = iterator.nextNode();
				 String vessel_id="";
				 String owners_id="";
				 String vesselType_id="";
				 
				if(obj.hasProperty("vesselName")){
				    vessel_id = obj.getProperty("vesselName").getString();//vesselName
				}
				/*String built = obj.getProperty("built").getString();
				String cubics = obj.getProperty("cubics").getString();
				String dwt = obj.getProperty("dwt").getString();
				String ice = obj.getProperty("ice").getString();
				String loa = obj.getProperty("loa").getString();*/
				if(obj.hasProperty("owners_id")){
				    owners_id = obj.getProperty("owners_id").getString();
				    owners_id=fetch_Owners(session, owners_id);
				}
				
//				String sternline = obj.getProperty("sternline").getString();
				if(obj.hasProperty("vesselType_id")){
				   vesselType_id = obj.getProperty("vesselType_id").getString(); //
				   vesselType_id=fetch_vesselType(session, vesselType_id);
				}
				
//				String status_id = obj.getProperty("status_id").getString();
				
				JSONObject allVesselProperties=new JSONObject();
				allVesselProperties.put("vessel_Id", vessel_id);
				/*allVesselProperties.put("built", built);
				allVesselProperties.put("cubics", cubics);
				allVesselProperties.put("dwt", dwt);
				allVesselProperties.put("ice", ice);
				allVesselProperties.put("loa", loa);*/
				allVesselProperties.put("owners_id", owners_id);
//				allVesselProperties.put("sternline", sternline);
				allVesselProperties.put("vesselType_id", vesselType_id);
//				allVesselProperties.put("status_id", status_id);
                data=allVesselProperties.toString();
			}

		} catch (Exception e) {
			return "";
		}
		return data;

		}
		
		public static String fetch_vesselType(Session session,String mysqlvesselType) {


			String vessel_id = null;

			try {

				Node obj = null;

				Workspace workspace = session.getWorkspace();

				String slingqery = "select [vesselType_Id] from [nt:base] where (contains('vesselType_Id','" + mysqlvesselType
						+ "')) and ISDESCENDANTNODE('/scorpioDataBase/vesselType/')";
				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();
				NodeIterator iterator = queryResult.getNodes();
				while (iterator.hasNext()) {

					obj = iterator.nextNode();
					vessel_id = obj.getProperty("vesselType").getString();//vesselType

				}

				
				
			} catch (Exception e) {
				return "";
			}
			return vessel_id;

			}
		
		public static String fetch_Owners(Session session,String mysqlOwners) {


			String vessel_id = null;

			try {

				Node obj = null;

				Workspace workspace = session.getWorkspace();

				String slingqery = "select [owners_Id] from [nt:base] where (contains('owners_Id','" + mysqlOwners
						+ "')) and ISDESCENDANTNODE('/scorpioDataBase/owners/')";
				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();
				NodeIterator iterator = queryResult.getNodes();
				while (iterator.hasNext()) {

					obj = iterator.nextNode();
					vessel_id = obj.getProperty("ownerName").getString();//ownerName

				}

			} catch (Exception e) {
				return "";
			}
			return vessel_id;

			}
		

		public  String fetch_EmployementStatus(Session session,String status) {


		String vessel_id = "";

		try {

			if(status!=""){
			Node obj = null;

			Workspace workspace = session.getWorkspace();

			String slingqery = "select [statusId] from [nt:base] where (contains('statusId','" + status
					+ "')) and ISDESCENDANTNODE('/scorpioDataBase/Employement Status/')";
			Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
			QueryResult queryResult = query.execute();
			NodeIterator iterator = queryResult.getNodes();
			while (iterator.hasNext()) {

				obj = iterator.nextNode();
				vessel_id = obj.getProperty("status").getString();//status

			}
			}

		} catch (Exception e) {
			return "";
		}
		return vessel_id;

		}

		public  String fetch_Source(Session session,String Source) {


		String vessel_id = "";

		try {

			if(Source!=""){
			Node obj = null;

			Workspace workspace = session.getWorkspace();

			String slingqery = "select [brokerNameId] from [nt:base] where (contains('brokerNameId','" + Source
					+ "')) and ISDESCENDANTNODE('/scorpioDataBase/Source/')";
			Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
			QueryResult queryResult = query.execute();
			NodeIterator iterator = queryResult.getNodes();
			while (iterator.hasNext()) {

				obj = iterator.nextNode();
				vessel_id = obj.getProperty("brokerName").getString();//brokerName

			}
			}

		} catch (Exception e) {
			return "";
		}
		return vessel_id;

		}

		public  String fetch_Information(Session session,String Information) {


		String vessel_id = "";

		try {

			if(Information!=""){
			Node obj = null;

			Workspace workspace = session.getWorkspace();

			String slingqery = "select [InformationId] from [nt:base] where (contains('InformationId','" + Information
					+ "')) and ISDESCENDANTNODE('/scorpioDataBase/Information/')";
			Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
			QueryResult queryResult = query.execute();
			NodeIterator iterator = queryResult.getNodes();
			while (iterator.hasNext()) {

				obj = iterator.nextNode();
				vessel_id = obj.getProperty("Information").getString();//Information

			}
			}

		} catch (Exception e) {
			return "";
		}
		return vessel_id;

		}

		public  String fetch_Charterer(Session session,String Charterer) {


		String vessel_id = "";

		try {

			if(Charterer!=""){
			Node obj = null;

			Workspace workspace = session.getWorkspace();

			String slingqery = "select [chartererNameId] from [nt:base] where (contains('chartererNameId','" + Charterer
					+ "')) and ISDESCENDANTNODE('/scorpioDataBase/Charterer/')";
			Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
			QueryResult queryResult = query.execute();
			NodeIterator iterator = queryResult.getNodes();
			while (iterator.hasNext()) {

				obj = iterator.nextNode();
				vessel_id = obj.getProperty("chartererName").getString();//chartererName

			}
			}

		} catch (Exception e) {
			return "";
		}
		return vessel_id;

		}

		public  String fetch_CargoGrade(Session session,String CargoGrade) {


		String vessel_id = "";

		try {

			if(CargoGrade!=""){
			Node obj = null;

			Workspace workspace = session.getWorkspace();

			String slingqery = "select [cargoGradeId] from [nt:base] where (contains('cargoGradeId','" + CargoGrade
					+ "')) and ISDESCENDANTNODE('/scorpioDataBase/Cargo Grade/')";
			Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
			QueryResult queryResult = query.execute();
			NodeIterator iterator = queryResult.getNodes();
			while (iterator.hasNext()) {

				obj = iterator.nextNode();
				vessel_id = obj.getProperty("cargoGrade").getString();//cargoGrade

			}
			}

		} catch (Exception e) {
			return "";
		}
		return vessel_id;

		}

		public  String fetch_RateType(Session session,String RateType) {


		String vessel_id = "";

		try {

			if(RateType!=""){
			Node obj = null;

			Workspace workspace = session.getWorkspace();

			String slingqery = "select [typeId] from [nt:base] where (contains('typeId','" + RateType
					+ "')) and ISDESCENDANTNODE('/scorpioDataBase/Rate Type/')";
			Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
			QueryResult queryResult = query.execute();
			NodeIterator iterator = queryResult.getNodes();
			while (iterator.hasNext()) {

				obj = iterator.nextNode();
				vessel_id = obj.getProperty("type").getString();//type

			}
			}

		} catch (Exception e) {
			return "";
		}
		return vessel_id;

		}

		public  String fetch_DeliveryPlace(Session session,String DeliveryPlace) {


		String vessel_id = "";

		try {

			if(DeliveryPlace!=""){
			Node obj = null;

			Workspace workspace = session.getWorkspace();

			String slingqery = "select [placeNameId] from [nt:base] where (contains('placeNameId','" + DeliveryPlace
					+ "')) and ISDESCENDANTNODE('/scorpioDataBase/Delivery Place/')";
			Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
			QueryResult queryResult = query.execute();
			NodeIterator iterator = queryResult.getNodes();
			while (iterator.hasNext()) {

				obj = iterator.nextNode();
				vessel_id = obj.getProperty("placeName").getString();//placeName

			}
			}

		} catch (Exception e) {
			return "";
		}
		return vessel_id;

		}

		public  String fetch_Periodunit(Session session,String Periodunit) {


		String vessel_id = "";

		try {

			if(Periodunit!=""){
			Node obj = null;

			Workspace workspace = session.getWorkspace();

			String slingqery = "select [unitId] from [nt:base] where (contains('unitId','" + Periodunit
					+ "')) and ISDESCENDANTNODE('/scorpioDataBase/Period unit/')";
			Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
			QueryResult queryResult = query.execute();
			NodeIterator iterator = queryResult.getNodes();
			while (iterator.hasNext()) {

				obj = iterator.nextNode();
				vessel_id = obj.getProperty("unit").getString();//unit

			}
			}

		} catch (Exception e) {
			return "";
		}
		return vessel_id;

		}

		public  String fetch_currencyUnit(Session session,String currencyUnit) {


		String vessel_id = "";

		try {

			if(currencyUnit!=""){
			Node obj = null;

			Workspace workspace = session.getWorkspace();

			String slingqery = "select [currency_UnitId] from [nt:base] where (contains('currency_UnitId','" + currencyUnit
					+ "')) and ISDESCENDANTNODE('/scorpioDataBase/currency Unit/')";
			Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
			QueryResult queryResult = query.execute();
			NodeIterator iterator = queryResult.getNodes();
			while (iterator.hasNext()) {

				obj = iterator.nextNode();
				vessel_id = obj.getProperty("currency_Unit").getString();//currency_Unit

			}
			}

		} catch (Exception e) {
			return "";
		}
		return vessel_id;

		}
		
		public  String check_ReportTypeToFetchapi(Session session,String reportType, PrintWriter out) {


			String vessel_id = "";

			try {

				if(reportType!=""){
				Node obj = null;
				String vesselName="";

				Workspace workspace = session.getWorkspace();
				reportType=reportType.toLowerCase().trim();

				/*String slingqery = "select [reportType] from [nt:base] where (contains('reportType','" + reportType
						+ "')) and ISDESCENDANTNODE('/scorpioDataBase/ReportType/')";*/
				String slingqery = "select [reportType] from [nt:base] where LOWER(reportType) ='" + reportType
						+ "'  and ISDESCENDANTNODE('/scorpioDataBase/ReportType/')";
				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();
				NodeIterator iterator = queryResult.getNodes();
				while (iterator.hasNext()) {

					obj = iterator.nextNode();
					if(obj.hasProperty("reportTypeId")){
						vessel_id = obj.getProperty("reportTypeId").getString();
					}
					if(obj.hasProperty("reportType")){
						vesselName = obj.getProperty("reportType").getString();
					}
					
				}
				
				
			}

			} catch (Exception e) {
				out.println(e.getMessage());
				return "";
			}
			return vessel_id;

			}
		
		
		

public static JSONObject fetchReportWiseData(PrintWriter out, Session session, String reporttypeName, Node node ,int j){
			
//			JSONObject mainJsonobj=null;
	JSONObject keyNameObject=null;
			try {
				FetchData fd=new FetchData();
					
					
					JSONArray keyNameObjectJsonarray=new JSONArray();
					
							String subNodeName=node.getName();
							
							
							String Owners="";
							String OpenPort="";
							String Operators="";
							String Id="";
							String CargoType="";
							String Comment="";
							String ETABasis="";
							String OpenDate="";
							String ReportType="";
							String ReportTimestamp="";
							String RepositionRegion="";
							String Source="";
							String VesselName="";
							
							String Information="";
							String FixtureType="";
							String Charterer="";
							String Status="";
							String CargoGrade="";
							String CargoQty="";
							String LCStart="";
							String LCEnd="";
							String LoadPort="";
							String DiscPort="";
							String RateType="";
							String Rate="";
							
							
							JSONArray EmploymentStatusJsonarray=new JSONArray();
							JSONArray OwnersJsonarray=new JSONArray();
							JSONArray OpenPortJsonarray=new JSONArray();
							JSONArray OperatorsJsonarray=new JSONArray();
							JSONArray CargoTypeJsonarray=new JSONArray();
							JSONArray CommentJsonarray=new JSONArray();
							JSONArray ETABasisJsonarray=new JSONArray();
							JSONArray OpenDateJsonarray=new JSONArray();
							JSONArray ReportTypeJsonarray=new JSONArray();
							JSONArray ReportTimestampJsonarray=new JSONArray();
							JSONArray RepositionRegionJsonarray=new JSONArray();
							JSONArray SourceJsonarray=new JSONArray();
							JSONArray VesselNameJsonarray=new JSONArray();
							JSONArray BuiltJsonarray=new JSONArray();
							JSONArray DWTJsonarray=new JSONArray();
							JSONArray CubicsJsonarray=new JSONArray();
							JSONArray LOAJsonarray=new JSONArray();
							JSONArray ICEJsonarray=new JSONArray();
							JSONArray SternLinejsonarray=new JSONArray();
							JSONArray VesselTypejsonarray=new JSONArray();
							
							JSONArray InformationJsonarray=new JSONArray();
							JSONArray FixtureTypeJsonarray=new JSONArray();
							JSONArray ChartererJsonarray=new JSONArray();
							JSONArray StatusJsonarray=new JSONArray();
							JSONArray CargoGradeJsonarray=new JSONArray();
							JSONArray CargoQtyJsonarray=new JSONArray();
							JSONArray LCStartJsonarray=new JSONArray();
							JSONArray LCEndJsonarray=new JSONArray();
							JSONArray LoadPortJsonarray=new JSONArray();
							JSONArray DiscPortJsonarray=new JSONArray();
							JSONArray RateTypeJsonarray=new JSONArray();
							JSONArray RateJsonarray=new JSONArray();
							JSONArray ReportDateJsonarray=new JSONArray();
							
							JSONArray Spot_TCJsonarray=new JSONArray();
							JSONArray DateJsonarray=new JSONArray();
							JSONArray CPP_DPPJsonarray=new JSONArray();
							JSONArray DeliveryJsonarray=new JSONArray();
							JSONArray DeliveryPlaceJsonarray=new JSONArray();
							JSONArray PeriodJsonarray=new JSONArray();
							JSONArray RedeliveryJsonarray=new JSONArray();
							JSONArray TCRateJsonarray=new JSONArray();
							JSONArray PeriodunitJsonarray=new JSONArray();
							JSONArray UnitJsonarray=new JSONArray();
							
							JSONArray Month_YearJsonarray=new JSONArray();
							JSONArray first_yrJsonarray=new JSONArray();
							JSONArray second_yrJsonarray=new JSONArray();
							JSONArray third_yrJsonarray= new JSONArray();
							JSONArray fifth_yrJsonarray= new JSONArray();
							
							
							if(node.hasProperty("Id")){
								Id=node.getProperty("Id").getString();
							}
							
							if(node.hasProperty("EmploymentStatus")){
								String EmploymentStatus=node.getProperty("EmploymentStatus").getString();
								
								EmploymentStatus=fd.fetch_EmployementStatus(session, EmploymentStatus);
								
								JSONObject EmploymentStatusJsonObject=new JSONObject();
								EmploymentStatusJsonObject.put("EmploymentStatus", EmploymentStatus);
								if(EmploymentStatusJsonObject.length()>0){
								EmploymentStatusJsonarray.put(EmploymentStatusJsonObject);
								}
							}
							if(node.hasProperty("Owners")){
								Owners=node.getProperty("Owners").getString();
								Owners=fetch_Owners(session, Owners);
								JSONObject OwnersJsonObject=new JSONObject();
								OwnersJsonObject.put("Owners", Owners);
								if(OwnersJsonObject.length()>0){
								   OwnersJsonarray.put(OwnersJsonObject);
								}
							}
							if(node.hasProperty("OpenPort")){
								OpenPort=node.getProperty("OpenPort").getString();
								OpenPort=fd.fetch_Port(session, OpenPort);
								JSONObject OpenPortJsonObject=new JSONObject();
								OpenPortJsonObject.put("OpenPort", OpenPort);
								if(OpenPortJsonObject.length()>0){
								OpenPortJsonarray.put(OpenPortJsonObject);
								}
							}
							if(node.hasProperty("Operators")){
								Operators=node.getProperty("Operators").getString();
								Operators=fd.fetch_Operators(session, Operators);
								JSONObject OperatorsJsonaobject=new JSONObject();
								OperatorsJsonaobject.put("Operators", Operators);
								if(OperatorsJsonaobject.length()>0){
								OperatorsJsonarray.put(OperatorsJsonaobject);
								}
							}
							if(node.hasProperty("CargoType")){
								CargoType=node.getProperty("CargoType").getString();
								CargoType=fd.fetch_CargoType(session, CargoType);
								JSONObject CargoTypeJsonaobject=new JSONObject();
								CargoTypeJsonaobject.put("CargoType", CargoType);
								if(CargoTypeJsonaobject.length()>0){
								CargoTypeJsonarray.put(CargoTypeJsonaobject);
								}
							}
							if(node.hasProperty("Comment")){
								Comment=node.getProperty("Comment").getString();
								
								JSONObject CommentJsonaobject=new JSONObject();
								CommentJsonaobject.put("Comment", Comment);
								if(CommentJsonaobject.length()>0){
								CommentJsonarray.put(CommentJsonaobject);
								}
							}
							if(node.hasProperty("ETABasis")){
								ETABasis=node.getProperty("ETABasis").getString();
								
								JSONObject ETABasisJsonaobject=new JSONObject();
								ETABasisJsonaobject.put("ETABasis", ETABasis);
								if(ETABasisJsonaobject.length()>0){
								ETABasisJsonarray.put(ETABasisJsonaobject);
								}
							}
							if(node.hasProperty("OpenDate")){
								OpenDate=node.getProperty("OpenDate").getString();
								
								JSONObject OpenDateJsonaobject=new JSONObject();
								OpenDateJsonaobject.put("OpenDate", OpenDate);
								if(OpenDateJsonaobject.length()>0){
								OpenDateJsonarray.put(OpenDateJsonaobject);
								}
							}
							
								
								JSONObject ReportTypeJsonaobject=new JSONObject();
								ReportTypeJsonaobject.put("ReportType", reporttypeName);
								if(ReportTypeJsonaobject.length()>0){
								ReportTypeJsonarray.put(ReportTypeJsonaobject);
								}
							
							if(node.hasProperty("ReportTimestamp")){
								ReportTimestamp=node.getProperty("ReportTimestamp").getString();
								
								JSONObject ReportTimestampJsonaobject=new JSONObject();
								ReportTimestampJsonaobject.put("ReportTimestamp", ReportTimestamp);
								if(ReportTimestampJsonaobject.length()>0){
								ReportTimestampJsonarray.put(ReportTimestampJsonaobject);
								}
							}
							if(node.hasProperty("RepositionRegion")){
								RepositionRegion=node.getProperty("RepositionRegion").getString();
								RepositionRegion=fd.fetch_Port(session, RepositionRegion);
								JSONObject RepositionRegionJsonaobject=new JSONObject();
								RepositionRegionJsonaobject.put("RepositionRegion", RepositionRegion);
								if(RepositionRegionJsonaobject.length()>0){
								RepositionRegionJsonarray.put(RepositionRegionJsonaobject);
								}
							}
							if(node.hasProperty("Source")){
								Source=node.getProperty("Source").getString();
								Source=fd.fetch_Source(session, Source);
								JSONObject SourceJsonaobject=new JSONObject();
								SourceJsonaobject.put("Source", Source);
								if(SourceJsonaobject.length()>0){
								SourceJsonarray.put(SourceJsonaobject);
								}
							}
							if(node.hasProperty("VesselName")){
								VesselName=node.getProperty("VesselName").getString();
								VesselName=fd.fetch_vesselName(session, VesselName);
								JSONObject fetch_vesselNamejsonobject=new JSONObject(VesselName);
								if(fetch_vesselNamejsonobject.has("vessel_Id")){
									VesselName=fetch_vesselNamejsonobject.getString("vessel_Id");
								}
								JSONObject VesselNameJsonaobject=new JSONObject();
								VesselNameJsonaobject.put("VesselName", VesselName);
								if(VesselNameJsonaobject.length()>0){
								VesselNameJsonarray.put(VesselNameJsonaobject);
								}
							}
							
							if(node.hasProperty("Built")){
								String Built="";
								Built=node.getProperty("Built").getString();
								
								JSONObject BuiltJsonaobject=new JSONObject();
								BuiltJsonaobject.put("Built", Built);
								if(BuiltJsonaobject.length()>0){
									BuiltJsonarray.put(BuiltJsonaobject);
								}
							}
							
							if(node.hasProperty("DWT")){
								String DWT="";
								DWT=node.getProperty("DWT").getString();
								
								JSONObject DWTJsonaobject=new JSONObject();
								DWTJsonaobject.put("DWT", DWT);
								if(DWTJsonaobject.length()>0){
									DWTJsonarray.put(DWTJsonaobject);
								}
							}
							
							if(node.hasProperty("Cubics")){
								String Cubics="";
								Cubics=node.getProperty("Cubics").getString();
								
								JSONObject CubicsJsonaobject=new JSONObject();
								CubicsJsonaobject.put("Cubics", Cubics);
								if(CubicsJsonaobject.length()>0){
									CubicsJsonarray.put(CubicsJsonaobject);
								}
							}
							
							if(node.hasProperty("LOA")){
								String LOA="";
								LOA=node.getProperty("LOA").getString();
								
								JSONObject LOAJsonaobject=new JSONObject();
								LOAJsonaobject.put("LOA", LOA);
								if(LOAJsonaobject.length()>0){
									LOAJsonarray.put(LOAJsonaobject);
								}
							}
							
							
							if(node.hasProperty("ICE")){
								String ICE="";
								ICE=node.getProperty("ICE").getString();
								
								JSONObject ICEJsonaobject=new JSONObject();
								ICEJsonaobject.put("ICE", ICE);
								if(ICEJsonaobject.length()>0){
									ICEJsonarray.put(ICEJsonaobject);
								}
							}
							
							
							if(node.hasProperty("SternLine")){
								String SternLine="";
								SternLine=node.getProperty("SternLine").getString();
								
								JSONObject SternLineJsonaobject=new JSONObject();
								SternLineJsonaobject.put("SternLine", SternLine);
								if(SternLineJsonaobject.length()>0){
									SternLinejsonarray.put(SternLineJsonaobject);
								}
							}
							
							if(node.hasProperty("VesselType")){
								String VesselType="";
								VesselType=node.getProperty("VesselType").getString();
								VesselType=fetch_vesselType(session, VesselType);
								JSONObject VesselTypeJsonaobject=new JSONObject();
								VesselTypeJsonaobject.put("VesselType", VesselType);
								if(VesselTypeJsonaobject.length()>0){
									VesselTypejsonarray.put(VesselTypeJsonaobject);
								}
							}
							
							if(node.hasProperty("Information")){
								Information=node.getProperty("Information").getString();
								Information=fd.fetch_Information(session, Information);
								JSONObject InformationJsonObject=new JSONObject();
								InformationJsonObject.put("Information", Information);
								if(InformationJsonObject.length()>0){
									InformationJsonarray.put(InformationJsonObject);
								}
							}
							if(node.hasProperty("FixtureType")){
								FixtureType=node.getProperty("FixtureType").getString();
								FixtureType=fd.fetch_ReportType(session, FixtureType);
								JSONObject FixtureTypeJsonObject=new JSONObject();
								FixtureTypeJsonObject.put("FixtureType", FixtureType);
								if(FixtureTypeJsonObject.length()>0){
									FixtureTypeJsonarray.put(FixtureTypeJsonObject);
								}
							}
							if(node.hasProperty("Charterer")){
								Charterer=node.getProperty("Charterer").getString();
								Charterer=fd.fetch_Charterer(session, Charterer);
								JSONObject ChartererJsonObject=new JSONObject();
								 ChartererJsonObject.put("Charterer", Charterer);
								if(ChartererJsonObject.length()>0){
								   ChartererJsonarray.put(ChartererJsonObject);
								}
							}
							
							if(node.hasProperty("CargoGrade")){
								CargoGrade=node.getProperty("CargoGrade").getString();
								CargoGrade=fd.fetch_CargoGrade(session, CargoGrade);
								JSONObject CargoGradeJsonobject=new JSONObject();
								CargoGradeJsonobject.put("CargoGrade", CargoGrade);
								if(CargoGradeJsonobject.length()>0){
									CargoGradeJsonarray.put(CargoGradeJsonobject);
								}
							}
							if(node.hasProperty("Status")){
								Status=node.getProperty("Status").getString();
								Status=fd.fetch_EmployementStatus(session, Status);
								JSONObject StatusJsonobject=new JSONObject();
								StatusJsonobject.put("Status", Status);
								if(StatusJsonobject.length()>0){
									StatusJsonarray.put(StatusJsonobject);
								}
							}if(node.hasProperty("CargoQty")){
								CargoQty=node.getProperty("CargoQty").getString();
								
								JSONObject CargoQtyJsonaobject=new JSONObject();
								CargoQtyJsonaobject.put("CargoQty", CargoQty);
								if(CargoQtyJsonaobject.length()>0){
									CargoQtyJsonarray.put(CargoQtyJsonaobject);
								}
							}
							if(node.hasProperty("LCStart")){
								LCStart=node.getProperty("LCStart").getString();
								
								JSONObject LCStartJsonaobject=new JSONObject();
								LCStartJsonaobject.put("LCStart", LCStart);
								if(LCStartJsonaobject.length()>0){
									LCStartJsonarray.put(LCStartJsonaobject);
								}
							}
							if(node.hasProperty("LCEnd")){
								LCEnd=node.getProperty("LCEnd").getString();
								
								JSONObject LCEndJsonaobject=new JSONObject();
								LCEndJsonaobject.put("LCEnd", LCEnd);
								if(LCEndJsonaobject.length()>0){
									LCEndJsonarray.put(LCEndJsonaobject);
								}
							}
							if(node.hasProperty("LoadPort")){
								LoadPort=node.getProperty("LoadPort").getString();
								LoadPort=fd.fetch_Port(session, LoadPort);
								JSONObject LoadPortJsonaobject=new JSONObject();
								LoadPortJsonaobject.put("LoadPort", LoadPort);
								if(LoadPortJsonaobject.length()>0){
									LoadPortJsonarray.put(LoadPortJsonaobject);
								}
							}
							if(node.hasProperty("DiscPort")){
								DiscPort=node.getProperty("DiscPort").getString();
								DiscPort=fd.fetch_Port(session, DiscPort);
								JSONObject DiscPortJsonaobject=new JSONObject();
								DiscPortJsonaobject.put("DiscPort", DiscPort);
								if(DiscPortJsonaobject.length()>0){
									DiscPortJsonarray.put(DiscPortJsonaobject);
								}
							}
							if(node.hasProperty("RateType")){
								RateType=node.getProperty("RateType").getString();
								RateType=fd.fetch_RateType(session, RateType);
								JSONObject RateTypeJsonaobject=new JSONObject();
								RateTypeJsonaobject.put("RateType", RateType);
								if(RateTypeJsonaobject.length()>0){
									RateTypeJsonarray.put(RateTypeJsonaobject);
								}
							}
							if(node.hasProperty("Rate")){
								Rate=node.getProperty("Rate").getString();
								
								JSONObject RateJsonaobject=new JSONObject();
								RateJsonaobject.put("Rate", Rate);
								if(RateJsonaobject.length()>0){
									RateJsonarray.put(RateJsonaobject);
								}
							}
							if(node.hasProperty("ReportDate")){
								String ReportDate="";
								ReportDate=node.getProperty("ReportDate").getString();
								
								JSONObject ReportDateJsonaobject=new JSONObject();
								ReportDateJsonaobject.put("ReportDate", ReportDate);
								if(ReportDateJsonaobject.length()>0){
									ReportDateJsonarray.put(ReportDateJsonaobject);
								}
							}
							
							if(node.hasProperty("Spot_TC")){
								String Spot_TC="";
								Spot_TC=node.getProperty("Spot_TC").getString();
								Spot_TC=fd.fetch_ReportType(session, Spot_TC);
								JSONObject Spot_TCJsonObject=new JSONObject();
								Spot_TCJsonObject.put("Spot_TC", Spot_TC);
								if(Spot_TCJsonObject.length()>0){
									Spot_TCJsonarray.put(Spot_TCJsonObject);
								}
							}
							if(node.hasProperty("Date")){
								String Date="";
								Date=node.getProperty("Date").getString();
								
								JSONObject DateJsonObject=new JSONObject();
								DateJsonObject.put("Date", Date);
								if(DateJsonObject.length()>0){
									DateJsonarray.put(DateJsonObject);
								}
							}
							if(node.hasProperty("CPP_DPP")){
								String CPP_DPP="";
								CPP_DPP=node.getProperty("CPP_DPP").getString();
								CPP_DPP=fd.fetch_CargoType(session, CPP_DPP);
								JSONObject CPP_DPPJsonobject=new JSONObject();
								CPP_DPPJsonobject.put("CPP_DPP", CPP_DPP);
								if(CPP_DPPJsonobject.length()>0){
									CPP_DPPJsonarray.put(CPP_DPPJsonobject);
								}
							}
							if(node.hasProperty("Delivery")){
								String Delivery="";
								Delivery=node.getProperty("Delivery").getString();
								
								JSONObject DeliveryJsonobject=new JSONObject();
								DeliveryJsonobject.put("Delivery", Delivery);
								if(DeliveryJsonobject.length()>0){
									DeliveryJsonarray.put(DeliveryJsonobject);
								}
							}
							if(node.hasProperty("DeliveryPlace")){
								String DeliveryPlace="";
								DeliveryPlace=node.getProperty("DeliveryPlace").getString();
								DeliveryPlace=fd.fetch_DeliveryPlace(session, DeliveryPlace);
								JSONObject DeliveryPlaceJsonaobject=new JSONObject();
								DeliveryPlaceJsonaobject.put("DeliveryPlace", DeliveryPlace);
								if(DeliveryPlaceJsonaobject.length()>0){
									DeliveryPlaceJsonarray.put(DeliveryPlaceJsonaobject);
								}
							}
							if(node.hasProperty("Period")){
								String Period="";
								Period=node.getProperty("Period").getString();
								
								JSONObject PeriodJsonaobject=new JSONObject();
								PeriodJsonaobject.put("Period", Period);
								if(PeriodJsonaobject.length()>0){
									PeriodJsonarray.put(PeriodJsonaobject);
								}
							}
							if(node.hasProperty("Periodunit")){
								String Periodunit="";
								Periodunit=node.getProperty("Periodunit").getString();
								Periodunit=fd.fetch_Periodunit(session, Periodunit);
								JSONObject PeriodunitJsonaobject=new JSONObject();
								PeriodunitJsonaobject.put("Periodunit", Periodunit);
								if(PeriodunitJsonaobject.length()>0){
									PeriodunitJsonarray.put(PeriodunitJsonaobject);
								}
							}
							if(node.hasProperty("Redelivery")){
								String Redelivery="";
								Redelivery=node.getProperty("Redelivery").getString();
								
								JSONObject RedeliveryJsonaobject=new JSONObject();
								RedeliveryJsonaobject.put("Redelivery", Redelivery);
								if(RedeliveryJsonaobject.length()>0){
									RedeliveryJsonarray.put(RedeliveryJsonaobject);
								}
							}
							if(node.hasProperty("TCRate")){
								String TCRate="";
								TCRate=node.getProperty("TCRate").getString();
								
								JSONObject TCRateJsonaobject=new JSONObject();
								TCRateJsonaobject.put("TCRate", TCRate);
								if(TCRateJsonaobject.length()>0){
									TCRateJsonarray.put(TCRateJsonaobject);
								}
							}
							if(node.hasProperty("CurrencyUnit")){
								String Unit="";
								Unit=node.getProperty("CurrencyUnit").getString();
								Unit=fd.fetch_currencyUnit(session, Unit);
								JSONObject UnitJsonaobject=new JSONObject();
								UnitJsonaobject.put("CurrencyUnit", Unit);
								if(UnitJsonaobject.length()>0){
									UnitJsonarray.put(UnitJsonaobject);
								}
							}
							if(node.hasProperty("Month_Year")){
								String Month_Year="";
								Month_Year=node.getProperty("Month_Year").getString();
								
								JSONObject Month_YearJsonObject=new JSONObject();
								Month_YearJsonObject.put("Month_Year", Month_Year);
								if(Month_YearJsonObject.length()>0){
									Month_YearJsonarray.put(Month_YearJsonObject);
								}
							}
							
							if(node.hasProperty("1yr")){
								String first_yr="";
								first_yr=node.getProperty("1yr").getString();
								
								JSONObject first_yrJsonaobject=new JSONObject();
								first_yrJsonaobject.put("first_yr", first_yr);
								if(first_yrJsonaobject.length()>0){
									first_yrJsonarray.put(first_yrJsonaobject);
								}
							}
							
							
							if(node.hasProperty("2yr")){
								String second_yr="";
								second_yr=node.getProperty("2yr").getString();
								
								JSONObject second_yrJsonobject=new JSONObject();
								second_yrJsonobject.put("second_yr", second_yr);
								if(second_yrJsonobject.length()>0){
									second_yrJsonarray.put(second_yrJsonobject);
								}
							}
							if(node.hasProperty("3yr")){
								String third_yr="";
								third_yr=node.getProperty("3yr").getString();
								
								JSONObject third_yrJsonaobject=new JSONObject();
								third_yrJsonaobject.put("third_yr", third_yr);
								if(third_yrJsonaobject.length()>0){
									third_yrJsonarray.put(third_yrJsonaobject);
								}
							}
							if(node.hasProperty("5yr")){
								String fifth_yr="";
								fifth_yr=node.getProperty("5yr").getString();
								
								JSONObject fifth_yrJsonobject=new JSONObject();
								fifth_yrJsonobject.put("fifth_yr", fifth_yr);
								if(fifth_yrJsonobject.length()>0){
									fifth_yrJsonarray.put(fifth_yrJsonobject);
								}
							}
							
							
							
							 keyNameObject=new JSONObject();
							
							keyNameObject.put("allReportId", Id);
							keyNameObject.put("EmploymentStatusJsonarray", EmploymentStatusJsonarray);
							keyNameObject.put("OwnersJsonarray", OwnersJsonarray);
							keyNameObject.put("OpenPortJsonarray", OpenPortJsonarray);
							keyNameObject.put("OperatorsJsonarray", OperatorsJsonarray);
							keyNameObject.put("CargoTypeJsonarray", CargoTypeJsonarray);
							keyNameObject.put("CommentJsonarray", CommentJsonarray);
							keyNameObject.put("ETABasisJsonarray", ETABasisJsonarray);
							keyNameObject.put("OpenDateJsonarray", OpenDateJsonarray);
							keyNameObject.put("ReportTypeJsonarray", ReportTypeJsonarray);
							keyNameObject.put("ReportTimestampJsonarray", ReportTimestampJsonarray);
							keyNameObject.put("RepositionRegionJsonarray", RepositionRegionJsonarray);
							keyNameObject.put("SourceJsonarray", SourceJsonarray);
							keyNameObject.put("VesselNameJsonarray", VesselNameJsonarray);
							keyNameObject.put("BuiltJsonarray", BuiltJsonarray);
							keyNameObject.put("DWTJsonarray", DWTJsonarray);
							keyNameObject.put("CubicsJsonarray", CubicsJsonarray);
							keyNameObject.put("LOAJsonarray", LOAJsonarray);
							keyNameObject.put("ICEJsonarray", ICEJsonarray);
							keyNameObject.put("SternLinejsonarray", SternLinejsonarray);
							keyNameObject.put("VesselTypejsonarray", VesselTypejsonarray);
							keyNameObject.put("subNodeName", subNodeName);
							
							
							keyNameObject.put("InformationJsonarray", InformationJsonarray);
							keyNameObject.put("FixtureTypeJsonarray", FixtureTypeJsonarray);
							keyNameObject.put("LCStartJsonarray", LCStartJsonarray);
							keyNameObject.put("LCEndJsonarray", LCEndJsonarray);
							keyNameObject.put("LoadPortJsonarray", LoadPortJsonarray);
							keyNameObject.put("DiscPortJsonarray", DiscPortJsonarray);
							keyNameObject.put("ChartererJsonarray", ChartererJsonarray);
							keyNameObject.put("StatusJsonarray", StatusJsonarray);
							keyNameObject.put("CargoGradeJsonarray", CargoGradeJsonarray);
							keyNameObject.put("CargoQtyJsonarray", CargoQtyJsonarray);
							keyNameObject.put("RateTypeJsonarray", RateTypeJsonarray);
							keyNameObject.put("RateJsonarray", RateJsonarray);
							keyNameObject.put("ReportDateJsonarray", ReportDateJsonarray);
							
							keyNameObject.put("Spot_TCJsonarray", Spot_TCJsonarray);
							keyNameObject.put("DateJsonarray", DateJsonarray);
							keyNameObject.put("CPP_DPPJsonarray", CPP_DPPJsonarray);
							keyNameObject.put("DeliveryJsonarray", DeliveryJsonarray);
							keyNameObject.put("DeliveryPlaceJsonarray", DeliveryPlaceJsonarray);
							keyNameObject.put("PeriodJsonarray", PeriodJsonarray);
							keyNameObject.put("PeriodunitJsonarray", PeriodunitJsonarray);
							keyNameObject.put("RedeliveryJsonarray", RedeliveryJsonarray);
							keyNameObject.put("TCRateJsonarray", TCRateJsonarray);
							keyNameObject.put("CurrencyUnitJsonarray", UnitJsonarray);
							
							keyNameObject.put("Month_YearJsonarray", Month_YearJsonarray);
							keyNameObject.put("first_yrJsonarray", first_yrJsonarray);
							keyNameObject.put("second_yrJsonarray", second_yrJsonarray);
							keyNameObject.put("third_yrJsonarray", third_yrJsonarray);
							keyNameObject.put("fifth_yrJsonarray", fifth_yrJsonarray);
							keyNameObject.put("i", j);
							
							/*if(keyNameObject.length()>0){
							   keyNameObjectJsonarray.put(keyNameObject);
							}*/
						
					
					/*if(keyNameObjectJsonarray.length()>0){
						 mainJsonobj=new JSONObject();
						 mainJsonobj.put("ReportWiseList", keyNameObjectJsonarray);
						
//						out.println("mainJsonobj: "+mainJsonobj);
					}*/
				
				
			} catch (Exception e) {
			   out.println(e.getMessage());
			   
			}
			 return keyNameObject;
			
		}
		
		
		public  String checkDatesForRecords(Session session,String from, String to,String reportType,PrintWriter out) {

			JSONObject mainJsonobj=null;
			try {
                
				String reporttypequeryid=check_ReportTypeToFetchapi(session, reportType, out);
				out.println("reporttypequeryid: "+reporttypequeryid);
				
				if(  !GmailMethods.isNullString(from) && !GmailMethods.isNullString(to) && !GmailMethods.isNullString(reportType)  ){

				String dataFrom=convertStringToDate(from);
				String dataTo=convertStringToDate(to);
				
				Workspace workspace = session.getWorkspace();

				String slingqery = "select [ReportTimestamp] from [nt:base] where ReportTimestamp >='" + dataFrom
						+ "'and ReportTimestamp <='" + dataTo 
						+ "' and ReportType="+reporttypequeryid+" and ISDESCENDANTNODE('/scorpioDataBase/Email/')";
				
				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();
				
				// get column names
                String[] columnNames = queryResult.getColumnNames();
				for(int i=0;i<columnNames.length;i++){
					//out.println("columnNames: "+columnNames[i]);
				}
				JSONArray keyNameObjectJsonarray=new JSONArray();
				// get column rows
                int j=0;
				RowIterator rowIterator = queryResult.getRows();
				
				while(rowIterator.hasNext()){
					// get next row
                     j++;
					Row row = rowIterator.nextRow();
					// get all values of row

					Value[] values = row.getValues();
					
					for(int i=0;i<values.length;i++){
						//out.println("values: "+values[i]);
					}
					
					Node node=row.getNode();
					if(node.hasProperty("ReportType")){
						/*String reporttypequeryid=check_ReportTypeToFetchapi(session, reportType, out);
						out.println("reporttypequeryid: "+reporttypequeryid);*/
						/*String ReportType_id=node.getProperty("ReportType").getString();
						out.println("ReportType_id: "+ReportType_id);*/
						/*if(reporttypequeryid.equals(ReportType_id)){
							out.println("true: ");*/
							String ReportType_id1=node.getProperty("ReportType").getString();
							String reporttypeName=fetch_ReportType(session, ReportType_id1);
							 JSONObject keyNameObject=fetchReportWiseData(out, session, reporttypeName, node, j);
							 if(keyNameObject.length()>0){
								 keyNameObjectJsonarray.put(keyNameObject);
							 }
//						}
						
					}
					
					
				}
				
				if(keyNameObjectJsonarray.length()>0){
					 mainJsonobj=new JSONObject();
					 mainJsonobj.put("ReportWiseList", keyNameObjectJsonarray);
				}

				
			}else{
				mainJsonobj=datafetch(out, session);
			}

			} catch (Exception e) {
				return e.getMessage();
//				e.printStackTrace(out);
			}
			return mainJsonobj.toString();

			}
		
		public static JSONObject datafetch(PrintWriter out, Session session){
			JSONObject mainJsonobj=null;
			try {
				
				Node email=session.getRootNode().getNode("scorpioDataBase").getNode("Email");
				if(email.hasNodes()){
					NodeIterator itremailnodes=email.getNodes();
					while(itremailnodes.hasNext()){
						Node emailNextNode=itremailnodes.nextNode();
						//out.println("emailNextNode: "+emailNextNode);
						if(emailNextNode.hasNodes()){
							NodeIterator emailNextNodeitr=emailNextNode.getNodes();
							JSONArray keyNameObjectJsonarray=new JSONArray();
							while(emailNextNodeitr.hasNext()){
								Node monthNextNode=emailNextNodeitr.nextNode();
								//out.println("monthNextNode: "+monthNextNode);
								if(monthNextNode.hasNodes()){
									NodeIterator monthNextNodeitr=monthNextNode.getNodes();
									
									while(monthNextNodeitr.hasNext()){
										
										Node nextdayNode=monthNextNodeitr.nextNode();
										if(nextdayNode.hasNodes()){
											JSONObject keyNameObject=null;
											
											FetchData fd=new FetchData();
											int i=0;
											NodeIterator itrday=nextdayNode.getNodes();
											while(itrday.hasNext()){
												i++;
												Node node=itrday.nextNode();
												//out.println("DayNode: "+DayNode);
												
														String subNodeName=node.getName();
														
														String Owners="";
														String OpenPort="";
														String Operators="";
														String Id="";
														String CargoType="";
														String Comment="";
														String ETABasis="";
														String OpenDate="";
														String ReportType="";
														String ReportTimestamp="";
														String RepositionRegion="";
														String Source="";
														String VesselName="";
														
														String Information="";
														String FixtureType="";
														String Charterer="";
														String Status="";
														String CargoGrade="";
														String CargoQty="";
														String LCStart="";
														String LCEnd="";
														String LoadPort="";
														String DiscPort="";
														String RateType="";
														String Rate="";
														
														
														JSONArray EmploymentStatusJsonarray=new JSONArray();
														JSONArray OwnersJsonarray=new JSONArray();
														JSONArray OpenPortJsonarray=new JSONArray();
														JSONArray OperatorsJsonarray=new JSONArray();
														JSONArray CargoTypeJsonarray=new JSONArray();
														JSONArray CommentJsonarray=new JSONArray();
														JSONArray ETABasisJsonarray=new JSONArray();
														JSONArray OpenDateJsonarray=new JSONArray();
														JSONArray ReportTypeJsonarray=new JSONArray();
														JSONArray ReportTimestampJsonarray=new JSONArray();
														JSONArray RepositionRegionJsonarray=new JSONArray();
														JSONArray SourceJsonarray=new JSONArray();
														JSONArray VesselNameJsonarray=new JSONArray();
														JSONArray BuiltJsonarray=new JSONArray();
														JSONArray DWTJsonarray=new JSONArray();
														JSONArray CubicsJsonarray=new JSONArray();
														JSONArray LOAJsonarray=new JSONArray();
														JSONArray ICEJsonarray=new JSONArray();
														JSONArray SternLinejsonarray=new JSONArray();
														JSONArray VesselTypejsonarray=new JSONArray();
														
														JSONArray InformationJsonarray=new JSONArray();
														JSONArray FixtureTypeJsonarray=new JSONArray();
														JSONArray ChartererJsonarray=new JSONArray();
														JSONArray StatusJsonarray=new JSONArray();
														JSONArray CargoGradeJsonarray=new JSONArray();
														JSONArray CargoQtyJsonarray=new JSONArray();
														JSONArray LCStartJsonarray=new JSONArray();
														JSONArray LCEndJsonarray=new JSONArray();
														JSONArray LoadPortJsonarray=new JSONArray();
														JSONArray DiscPortJsonarray=new JSONArray();
														JSONArray RateTypeJsonarray=new JSONArray();
														JSONArray RateJsonarray=new JSONArray();
														JSONArray ReportDateJsonarray=new JSONArray();
														
														JSONArray Spot_TCJsonarray=new JSONArray();
														JSONArray DateJsonarray=new JSONArray();
														JSONArray CPP_DPPJsonarray=new JSONArray();
														JSONArray DeliveryJsonarray=new JSONArray();
														JSONArray DeliveryPlaceJsonarray=new JSONArray();
														JSONArray PeriodJsonarray=new JSONArray();
														JSONArray RedeliveryJsonarray=new JSONArray();
														JSONArray TCRateJsonarray=new JSONArray();
														JSONArray PeriodunitJsonarray=new JSONArray();
														JSONArray UnitJsonarray=new JSONArray();
														
														JSONArray Month_YearJsonarray=new JSONArray();
														JSONArray first_yrJsonarray=new JSONArray();
														JSONArray second_yrJsonarray=new JSONArray();
														JSONArray third_yrJsonarray= new JSONArray();
														JSONArray fifth_yrJsonarray= new JSONArray();
														
														
														if(node.hasProperty("Id")){
															Id=node.getProperty("Id").getString();
														}
														
														if(node.hasProperty("EmploymentStatus")){
															String EmploymentStatus=node.getProperty("EmploymentStatus").getString();
															
															EmploymentStatus=fd.fetch_EmployementStatus(session, EmploymentStatus);
															
															JSONObject EmploymentStatusJsonObject=new JSONObject();
															EmploymentStatusJsonObject.put("EmploymentStatus", EmploymentStatus);
															if(EmploymentStatusJsonObject.length()>0){
															EmploymentStatusJsonarray.put(EmploymentStatusJsonObject);
															}
														}
														if(node.hasProperty("Owners")){
															Owners=node.getProperty("Owners").getString();
															Owners=fetch_Owners(session, Owners);
															JSONObject OwnersJsonObject=new JSONObject();
															OwnersJsonObject.put("Owners", Owners);
															if(OwnersJsonObject.length()>0){
															   OwnersJsonarray.put(OwnersJsonObject);
															}
														}
														if(node.hasProperty("OpenPort")){
															OpenPort=node.getProperty("OpenPort").getString();
															OpenPort=fd.fetch_Port(session, OpenPort);
															JSONObject OpenPortJsonObject=new JSONObject();
															OpenPortJsonObject.put("OpenPort", OpenPort);
															if(OpenPortJsonObject.length()>0){
															OpenPortJsonarray.put(OpenPortJsonObject);
															}
														}
														if(node.hasProperty("Operators")){
															Operators=node.getProperty("Operators").getString();
															Operators=fd.fetch_Operators(session, Operators);
															JSONObject OperatorsJsonaobject=new JSONObject();
															OperatorsJsonaobject.put("Operators", Operators);
															if(OperatorsJsonaobject.length()>0){
															OperatorsJsonarray.put(OperatorsJsonaobject);
															}
														}
														if(node.hasProperty("CargoType")){
															CargoType=node.getProperty("CargoType").getString();
															CargoType=fd.fetch_CargoType(session, CargoType);
															JSONObject CargoTypeJsonaobject=new JSONObject();
															CargoTypeJsonaobject.put("CargoType", CargoType);
															if(CargoTypeJsonaobject.length()>0){
															CargoTypeJsonarray.put(CargoTypeJsonaobject);
															}
														}
														if(node.hasProperty("Comment")){
															Comment=node.getProperty("Comment").getString();
															
															JSONObject CommentJsonaobject=new JSONObject();
															CommentJsonaobject.put("Comment", Comment);
															if(CommentJsonaobject.length()>0){
															CommentJsonarray.put(CommentJsonaobject);
															}
														}
														if(node.hasProperty("ETABasis")){
															ETABasis=node.getProperty("ETABasis").getString();
															
															JSONObject ETABasisJsonaobject=new JSONObject();
															ETABasisJsonaobject.put("ETABasis", ETABasis);
															if(ETABasisJsonaobject.length()>0){
															ETABasisJsonarray.put(ETABasisJsonaobject);
															}
														}
														if(node.hasProperty("OpenDate")){
															OpenDate=node.getProperty("OpenDate").getString();
															
															JSONObject OpenDateJsonaobject=new JSONObject();
															OpenDateJsonaobject.put("OpenDate", OpenDate);
															if(OpenDateJsonaobject.length()>0){
															OpenDateJsonarray.put(OpenDateJsonaobject);
															}
														}
														
														if(node.hasProperty("ReportType")){
															ReportType=node.getProperty("ReportType").getString();
															ReportType=fd.fetch_ReportType(session, ReportType);
															JSONObject ReportTypeJsonaobject=new JSONObject();
															ReportTypeJsonaobject.put("ReportType", ReportType);
															if(ReportTypeJsonaobject.length()>0){
															ReportTypeJsonarray.put(ReportTypeJsonaobject);
															}
														}
														
														if(node.hasProperty("ReportTimestamp")){
															ReportTimestamp=node.getProperty("ReportTimestamp").getString();
															
															JSONObject ReportTimestampJsonaobject=new JSONObject();
															ReportTimestampJsonaobject.put("ReportTimestamp", ReportTimestamp);
															if(ReportTimestampJsonaobject.length()>0){
															ReportTimestampJsonarray.put(ReportTimestampJsonaobject);
															}
														}
														if(node.hasProperty("RepositionRegion")){
															RepositionRegion=node.getProperty("RepositionRegion").getString();
															RepositionRegion=fd.fetch_Port(session, RepositionRegion);
															JSONObject RepositionRegionJsonaobject=new JSONObject();
															RepositionRegionJsonaobject.put("RepositionRegion", RepositionRegion);
															if(RepositionRegionJsonaobject.length()>0){
															RepositionRegionJsonarray.put(RepositionRegionJsonaobject);
															}
														}
														if(node.hasProperty("Source")){
															Source=node.getProperty("Source").getString();
															Source=fd.fetch_Source(session, Source);
															JSONObject SourceJsonaobject=new JSONObject();
															SourceJsonaobject.put("Source", Source);
															if(SourceJsonaobject.length()>0){
															SourceJsonarray.put(SourceJsonaobject);
															}
														}
														if(node.hasProperty("VesselName")){
															VesselName=node.getProperty("VesselName").getString();
															VesselName=fd.fetch_vesselName(session, VesselName);
															JSONObject fetch_vesselNamejsonobject=new JSONObject(VesselName);
															if(fetch_vesselNamejsonobject.has("vessel_Id")){
																VesselName=fetch_vesselNamejsonobject.getString("vessel_Id");
															}
															JSONObject VesselNameJsonaobject=new JSONObject();
															VesselNameJsonaobject.put("VesselName", VesselName);
															if(VesselNameJsonaobject.length()>0){
															VesselNameJsonarray.put(VesselNameJsonaobject);
															}
														}
														
														if(node.hasProperty("Built")){
															String Built="";
															Built=node.getProperty("Built").getString();
															
															JSONObject BuiltJsonaobject=new JSONObject();
															BuiltJsonaobject.put("Built", Built);
															if(BuiltJsonaobject.length()>0){
																BuiltJsonarray.put(BuiltJsonaobject);
															}
														}
														
														if(node.hasProperty("DWT")){
															String DWT="";
															DWT=node.getProperty("DWT").getString();
															
															JSONObject DWTJsonaobject=new JSONObject();
															DWTJsonaobject.put("DWT", DWT);
															if(DWTJsonaobject.length()>0){
																DWTJsonarray.put(DWTJsonaobject);
															}
														}
														
														if(node.hasProperty("Cubics")){
															String Cubics="";
															Cubics=node.getProperty("Cubics").getString();
															
															JSONObject CubicsJsonaobject=new JSONObject();
															CubicsJsonaobject.put("Cubics", Cubics);
															if(CubicsJsonaobject.length()>0){
																CubicsJsonarray.put(CubicsJsonaobject);
															}
														}
														
														if(node.hasProperty("LOA")){
															String LOA="";
															LOA=node.getProperty("LOA").getString();
															
															JSONObject LOAJsonaobject=new JSONObject();
															LOAJsonaobject.put("LOA", LOA);
															if(LOAJsonaobject.length()>0){
																LOAJsonarray.put(LOAJsonaobject);
															}
														}
														
														
														if(node.hasProperty("ICE")){
															String ICE="";
															ICE=node.getProperty("ICE").getString();
															
															JSONObject ICEJsonaobject=new JSONObject();
															ICEJsonaobject.put("ICE", ICE);
															if(ICEJsonaobject.length()>0){
																ICEJsonarray.put(ICEJsonaobject);
															}
														}
														
														
														if(node.hasProperty("SternLine")){
															String SternLine="";
															SternLine=node.getProperty("SternLine").getString();
															
															JSONObject SternLineJsonaobject=new JSONObject();
															SternLineJsonaobject.put("SternLine", SternLine);
															if(SternLineJsonaobject.length()>0){
																SternLinejsonarray.put(SternLineJsonaobject);
															}
														}
														
														if(node.hasProperty("VesselType")){
															String VesselType="";
															VesselType=node.getProperty("VesselType").getString();
															VesselType=fetch_vesselType(session, VesselType);
															JSONObject VesselTypeJsonaobject=new JSONObject();
															VesselTypeJsonaobject.put("VesselType", VesselType);
															if(VesselTypeJsonaobject.length()>0){
																VesselTypejsonarray.put(VesselTypeJsonaobject);
															}
														}
														
														if(node.hasProperty("Information")){
															Information=node.getProperty("Information").getString();
															Information=fd.fetch_Information(session, Information);
															JSONObject InformationJsonObject=new JSONObject();
															InformationJsonObject.put("Information", Information);
															if(InformationJsonObject.length()>0){
																InformationJsonarray.put(InformationJsonObject);
															}
														}
														if(node.hasProperty("FixtureType")){
															FixtureType=node.getProperty("FixtureType").getString();
															FixtureType=fd.fetch_ReportType(session, FixtureType);
															JSONObject FixtureTypeJsonObject=new JSONObject();
															FixtureTypeJsonObject.put("FixtureType", FixtureType);
															if(FixtureTypeJsonObject.length()>0){
																FixtureTypeJsonarray.put(FixtureTypeJsonObject);
															}
														}
														if(node.hasProperty("Charterer")){
															Charterer=node.getProperty("Charterer").getString();
															Charterer=fd.fetch_Charterer(session, Charterer);
															JSONObject ChartererJsonObject=new JSONObject();
															 ChartererJsonObject.put("Charterer", Charterer);
															if(ChartererJsonObject.length()>0){
															   ChartererJsonarray.put(ChartererJsonObject);
															}
														}
														
														if(node.hasProperty("CargoGrade")){
															CargoGrade=node.getProperty("CargoGrade").getString();
															CargoGrade=fd.fetch_CargoGrade(session, CargoGrade);
															JSONObject CargoGradeJsonobject=new JSONObject();
															CargoGradeJsonobject.put("CargoGrade", CargoGrade);
															if(CargoGradeJsonobject.length()>0){
																CargoGradeJsonarray.put(CargoGradeJsonobject);
															}
														}
														if(node.hasProperty("Status")){
															Status=node.getProperty("Status").getString();
															Status=fd.fetch_EmployementStatus(session, Status);
															JSONObject StatusJsonobject=new JSONObject();
															StatusJsonobject.put("Status", Status);
															if(StatusJsonobject.length()>0){
																StatusJsonarray.put(StatusJsonobject);
															}
														}if(node.hasProperty("CargoQty")){
															CargoQty=node.getProperty("CargoQty").getString();
															
															JSONObject CargoQtyJsonaobject=new JSONObject();
															CargoQtyJsonaobject.put("CargoQty", CargoQty);
															if(CargoQtyJsonaobject.length()>0){
																CargoQtyJsonarray.put(CargoQtyJsonaobject);
															}
														}
														if(node.hasProperty("LCStart")){
															LCStart=node.getProperty("LCStart").getString();
															
															JSONObject LCStartJsonaobject=new JSONObject();
															LCStartJsonaobject.put("LCStart", LCStart);
															if(LCStartJsonaobject.length()>0){
																LCStartJsonarray.put(LCStartJsonaobject);
															}
														}
														if(node.hasProperty("LCEnd")){
															LCEnd=node.getProperty("LCEnd").getString();
															
															JSONObject LCEndJsonaobject=new JSONObject();
															LCEndJsonaobject.put("LCEnd", LCEnd);
															if(LCEndJsonaobject.length()>0){
																LCEndJsonarray.put(LCEndJsonaobject);
															}
														}
														if(node.hasProperty("LoadPort")){
															LoadPort=node.getProperty("LoadPort").getString();
															LoadPort=fd.fetch_Port(session, LoadPort);
															JSONObject LoadPortJsonaobject=new JSONObject();
															LoadPortJsonaobject.put("LoadPort", LoadPort);
															if(LoadPortJsonaobject.length()>0){
																LoadPortJsonarray.put(LoadPortJsonaobject);
															}
														}
														if(node.hasProperty("DiscPort")){
															DiscPort=node.getProperty("DiscPort").getString();
															DiscPort=fd.fetch_Port(session, DiscPort);
															JSONObject DiscPortJsonaobject=new JSONObject();
															DiscPortJsonaobject.put("DiscPort", DiscPort);
															if(DiscPortJsonaobject.length()>0){
																DiscPortJsonarray.put(DiscPortJsonaobject);
															}
														}
														if(node.hasProperty("RateType")){
															RateType=node.getProperty("RateType").getString();
															RateType=fd.fetch_RateType(session, RateType);
															JSONObject RateTypeJsonaobject=new JSONObject();
															RateTypeJsonaobject.put("RateType", RateType);
															if(RateTypeJsonaobject.length()>0){
																RateTypeJsonarray.put(RateTypeJsonaobject);
															}
														}
														if(node.hasProperty("Rate")){
															Rate=node.getProperty("Rate").getString();
															
															JSONObject RateJsonaobject=new JSONObject();
															RateJsonaobject.put("Rate", Rate);
															if(RateJsonaobject.length()>0){
																RateJsonarray.put(RateJsonaobject);
															}
														}
														if(node.hasProperty("ReportDate")){
															String ReportDate="";
															ReportDate=node.getProperty("ReportDate").getString();
															
															JSONObject ReportDateJsonaobject=new JSONObject();
															ReportDateJsonaobject.put("ReportDate", ReportDate);
															if(ReportDateJsonaobject.length()>0){
																ReportDateJsonarray.put(ReportDateJsonaobject);
															}
														}
														
														if(node.hasProperty("Spot_TC")){
															String Spot_TC="";
															Spot_TC=node.getProperty("Spot_TC").getString();
															Spot_TC=fd.fetch_ReportType(session, Spot_TC);
															JSONObject Spot_TCJsonObject=new JSONObject();
															Spot_TCJsonObject.put("Spot_TC", Spot_TC);
															if(Spot_TCJsonObject.length()>0){
																Spot_TCJsonarray.put(Spot_TCJsonObject);
															}
														}
														if(node.hasProperty("Date")){
															String Date="";
															Date=node.getProperty("Date").getString();
															
															JSONObject DateJsonObject=new JSONObject();
															DateJsonObject.put("Date", Date);
															if(DateJsonObject.length()>0){
																DateJsonarray.put(DateJsonObject);
															}
														}
														if(node.hasProperty("CPP_DPP")){
															String CPP_DPP="";
															CPP_DPP=node.getProperty("CPP_DPP").getString();
															CPP_DPP=fd.fetch_CargoType(session, CPP_DPP);
															JSONObject CPP_DPPJsonobject=new JSONObject();
															CPP_DPPJsonobject.put("CPP_DPP", CPP_DPP);
															if(CPP_DPPJsonobject.length()>0){
																CPP_DPPJsonarray.put(CPP_DPPJsonobject);
															}
														}
														if(node.hasProperty("Delivery")){
															String Delivery="";
															Delivery=node.getProperty("Delivery").getString();
															
															JSONObject DeliveryJsonobject=new JSONObject();
															DeliveryJsonobject.put("Delivery", Delivery);
															if(DeliveryJsonobject.length()>0){
																DeliveryJsonarray.put(DeliveryJsonobject);
															}
														}
														if(node.hasProperty("DeliveryPlace")){
															String DeliveryPlace="";
															DeliveryPlace=node.getProperty("DeliveryPlace").getString();
															DeliveryPlace=fd.fetch_DeliveryPlace(session, DeliveryPlace);
															JSONObject DeliveryPlaceJsonaobject=new JSONObject();
															DeliveryPlaceJsonaobject.put("DeliveryPlace", DeliveryPlace);
															if(DeliveryPlaceJsonaobject.length()>0){
																DeliveryPlaceJsonarray.put(DeliveryPlaceJsonaobject);
															}
														}
														if(node.hasProperty("Period")){
															String Period="";
															Period=node.getProperty("Period").getString();
															
															JSONObject PeriodJsonaobject=new JSONObject();
															PeriodJsonaobject.put("Period", Period);
															if(PeriodJsonaobject.length()>0){
																PeriodJsonarray.put(PeriodJsonaobject);
															}
														}
														if(node.hasProperty("Periodunit")){
															String Periodunit="";
															Periodunit=node.getProperty("Periodunit").getString();
															Periodunit=fd.fetch_Periodunit(session, Periodunit);
															JSONObject PeriodunitJsonaobject=new JSONObject();
															PeriodunitJsonaobject.put("Periodunit", Periodunit);
															if(PeriodunitJsonaobject.length()>0){
																PeriodunitJsonarray.put(PeriodunitJsonaobject);
															}
														}
														if(node.hasProperty("Redelivery")){
															String Redelivery="";
															Redelivery=node.getProperty("Redelivery").getString();
															
															JSONObject RedeliveryJsonaobject=new JSONObject();
															RedeliveryJsonaobject.put("Redelivery", Redelivery);
															if(RedeliveryJsonaobject.length()>0){
																RedeliveryJsonarray.put(RedeliveryJsonaobject);
															}
														}
														if(node.hasProperty("TCRate")){
															String TCRate="";
															TCRate=node.getProperty("TCRate").getString();
															
															JSONObject TCRateJsonaobject=new JSONObject();
															TCRateJsonaobject.put("TCRate", TCRate);
															if(TCRateJsonaobject.length()>0){
																TCRateJsonarray.put(TCRateJsonaobject);
															}
														}
														if(node.hasProperty("CurrencyUnit")){
															String Unit="";
															Unit=node.getProperty("CurrencyUnit").getString();
															Unit=fd.fetch_currencyUnit(session, Unit);
															JSONObject UnitJsonaobject=new JSONObject();
															UnitJsonaobject.put("CurrencyUnit", Unit);
															if(UnitJsonaobject.length()>0){
																UnitJsonarray.put(UnitJsonaobject);
															}
														}
														if(node.hasProperty("Month_Year")){
															String Month_Year="";
															Month_Year=node.getProperty("Month_Year").getString();
															
															JSONObject Month_YearJsonObject=new JSONObject();
															Month_YearJsonObject.put("Month_Year", Month_Year);
															if(Month_YearJsonObject.length()>0){
																Month_YearJsonarray.put(Month_YearJsonObject);
															}
														}
														
														if(node.hasProperty("1yr")){
															String first_yr="";
															first_yr=node.getProperty("1yr").getString();
															
															JSONObject first_yrJsonaobject=new JSONObject();
															first_yrJsonaobject.put("first_yr", first_yr);
															if(first_yrJsonaobject.length()>0){
																first_yrJsonarray.put(first_yrJsonaobject);
															}
														}
														
														
														if(node.hasProperty("2yr")){
															String second_yr="";
															second_yr=node.getProperty("2yr").getString();
															
															JSONObject second_yrJsonobject=new JSONObject();
															second_yrJsonobject.put("second_yr", second_yr);
															if(second_yrJsonobject.length()>0){
																second_yrJsonarray.put(second_yrJsonobject);
															}
														}
														if(node.hasProperty("3yr")){
															String third_yr="";
															third_yr=node.getProperty("3yr").getString();
															
															JSONObject third_yrJsonaobject=new JSONObject();
															third_yrJsonaobject.put("third_yr", third_yr);
															if(third_yrJsonaobject.length()>0){
																third_yrJsonarray.put(third_yrJsonaobject);
															}
														}
														if(node.hasProperty("5yr")){
															String fifth_yr="";
															fifth_yr=node.getProperty("5yr").getString();
															
															JSONObject fifth_yrJsonobject=new JSONObject();
															fifth_yrJsonobject.put("fifth_yr", fifth_yr);
															if(fifth_yrJsonobject.length()>0){
																fifth_yrJsonarray.put(fifth_yrJsonobject);
															}
														}
														
														
														
														keyNameObject=new JSONObject();
														
														keyNameObject.put("tonnageId", Id);
														keyNameObject.put("EmploymentStatusJsonarray", EmploymentStatusJsonarray);
														keyNameObject.put("OwnersJsonarray", OwnersJsonarray);
														keyNameObject.put("OpenPortJsonarray", OpenPortJsonarray);
														keyNameObject.put("OperatorsJsonarray", OperatorsJsonarray);
														keyNameObject.put("CargoTypeJsonarray", CargoTypeJsonarray);
														keyNameObject.put("CommentJsonarray", CommentJsonarray);
														keyNameObject.put("ETABasisJsonarray", ETABasisJsonarray);
														keyNameObject.put("OpenDateJsonarray", OpenDateJsonarray);
														keyNameObject.put("ReportTypeJsonarray", ReportTypeJsonarray);
														keyNameObject.put("ReportTimestampJsonarray", ReportTimestampJsonarray);
														keyNameObject.put("RepositionRegionJsonarray", RepositionRegionJsonarray);
														keyNameObject.put("SourceJsonarray", SourceJsonarray);
														keyNameObject.put("VesselNameJsonarray", VesselNameJsonarray);
														keyNameObject.put("BuiltJsonarray", BuiltJsonarray);
														keyNameObject.put("DWTJsonarray", DWTJsonarray);
														keyNameObject.put("CubicsJsonarray", CubicsJsonarray);
														keyNameObject.put("LOAJsonarray", LOAJsonarray);
														keyNameObject.put("ICEJsonarray", ICEJsonarray);
														keyNameObject.put("SternLinejsonarray", SternLinejsonarray);
														keyNameObject.put("VesselTypejsonarray", VesselTypejsonarray);
														keyNameObject.put("subNodeName", subNodeName);
														
														
														keyNameObject.put("InformationJsonarray", InformationJsonarray);
														keyNameObject.put("FixtureTypeJsonarray", FixtureTypeJsonarray);
														keyNameObject.put("LCStartJsonarray", LCStartJsonarray);
														keyNameObject.put("LCEndJsonarray", LCEndJsonarray);
														keyNameObject.put("LoadPortJsonarray", LoadPortJsonarray);
														keyNameObject.put("DiscPortJsonarray", DiscPortJsonarray);
														keyNameObject.put("ChartererJsonarray", ChartererJsonarray);
														keyNameObject.put("StatusJsonarray", StatusJsonarray);
														keyNameObject.put("CargoGradeJsonarray", CargoGradeJsonarray);
														keyNameObject.put("CargoQtyJsonarray", CargoQtyJsonarray);
														keyNameObject.put("RateTypeJsonarray", RateTypeJsonarray);
														keyNameObject.put("RateJsonarray", RateJsonarray);
														keyNameObject.put("ReportDateJsonarray", ReportDateJsonarray);
														
														keyNameObject.put("Spot_TCJsonarray", Spot_TCJsonarray);
														keyNameObject.put("DateJsonarray", DateJsonarray);
														keyNameObject.put("CPP_DPPJsonarray", CPP_DPPJsonarray);
														keyNameObject.put("DeliveryJsonarray", DeliveryJsonarray);
														keyNameObject.put("DeliveryPlaceJsonarray", DeliveryPlaceJsonarray);
														keyNameObject.put("PeriodJsonarray", PeriodJsonarray);
														keyNameObject.put("PeriodunitJsonarray", PeriodunitJsonarray);
														keyNameObject.put("RedeliveryJsonarray", RedeliveryJsonarray);
														keyNameObject.put("TCRateJsonarray", TCRateJsonarray);
														keyNameObject.put("CurrencyUnitJsonarray", UnitJsonarray);
														
														keyNameObject.put("Month_YearJsonarray", Month_YearJsonarray);
														keyNameObject.put("first_yrJsonarray", first_yrJsonarray);
														keyNameObject.put("second_yrJsonarray", second_yrJsonarray);
														keyNameObject.put("third_yrJsonarray", third_yrJsonarray);
														keyNameObject.put("fifth_yrJsonarray", fifth_yrJsonarray);
														keyNameObject.put("i", i);
														
														if(keyNameObject.length()>0){
														   keyNameObjectJsonarray.put(keyNameObject);
														  // out.println("keyNameObjectJsonarray: "+keyNameObjectJsonarray);
														}
												
												//s=fetchMixedReportData(out, session, DayNode, i);
											}// while
											
											
										}
										
//										  
									}
									
								}
								if(keyNameObjectJsonarray.length()>0){
									 mainJsonobj=new JSONObject();
									 mainJsonobj.put("tonnageList", keyNameObjectJsonarray);
									
									//out.println("mainJsonobj: "+mainJsonobj);
								}
							}
						}
					}
				}
				
			} catch (Exception e) {
				out.println(e.getMessage());
			}
			return mainJsonobj;
			
		}
		
}
