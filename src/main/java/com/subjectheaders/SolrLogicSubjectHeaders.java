package com.subjectheaders;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.jcr.Session;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import com.readGmail.GmailMethods;
import com.reportinformationsystem.SaveReportDataClass;

public class SolrLogicSubjectHeaders {

	public static void main(String[] args) throws JSONException {
     // String q="EASTPORT CLEAN MR POSITION BSS S.KOREA AS AT 15th MAR 2019";
     // String q="MOVEMENTS DAILY REPORT PORT ANTONIO MR DATED 19TH MARCH 2019";
      String q="HANDY AND MR FUEL OIL POSITIONS BASIS MALTA AS OF FRIDAY 29TH MARCH 2019";
      String etaBasis=SubjectHeadersAllMethods.etaBasisRegrexDate(q, null);
      System.out.println(etaBasis);
      JSONObject d= SubjectData(q, null);
      System.out.println(d);
      
     // String data="{  \"header_data\": {    \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion\": \"Fujairah\",     \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\": \"LR2\"  },   \"table_data\": [    {      \"BLT\": \"2016-02-17 00:00:00\",       \"CBS\": \"125\",       \"COMMENTS \": \"blst ex twn\",       \"DWT\": \"110\",       \"FUJ\": \"2018-10-29 00:00:00\",       \"LOA\": \"249.9\",       \"OPEN\": \"2018-10-27 00:00:00\",       \"OPEN PORT\": \"Fujairah\",       \"OWNER\": \"SCORPIO\",       \"STATUS\": \"nan\",       \"StructureType\": \"table\",       \"VESSEL\": \"Sti Symphony\"    }]}";
     /* String data="[  {    \"BM\": \"53\",     \"COMMENT\": \"SUBS\",     \"DATE\": \"MAR 20\",     \"FLEET\": \"ELKA\",     \"OPEN\": \"MAR 20\",     \"PORT\": \"HOUSTON\",     \"StructureType\": \"table\",     \"VESSEL             D\": \"ELKA ELEFTHERIA\",     \"WT  C\": \"45\",     \"unknown_0\": \"\"  },   {    \"BM\": \"53\",     \"COMMENT\": \"SUBS\",     \"DATE\": \"MAR 20\",     \"FLEET\": \"ELKA\",     \"OPEN\": \"MAR 20\",     \"PORT\": \"HOUSTON\",     \"StructureType\": \"table\",     \"VESSEL             D\": \"ELKA HERCULES\",     \"WT  C\": \"44\",     \"unknown_0\": \"\"  },   {    \"BM\": \"54\",     \"COMMENT\": \"SUBS\",     \"DATE\": \"MAR 20\",     \"FLEET\": \"ELKA\",     \"OPEN\": \"MAR 20\",     \"PORT\": \"HOUSTON\",     \"StructureType\": \"table\",     \"VESSEL             D\": \"ELKA BENE\",     \"WT  C\": \"45\",     \"unknown_0\": \"\"  }]";
//      JSONObject expertScriptJSon=new JSONObject(data);
      JSONObject expertScriptJSon= SolrLogicSubjectHeaders.checkJsonOrArray(data);
      JSONObject finalheadre=finalHeaderData(d, expertScriptJSon);
      System.out.println(finalheadre);*/
      
      
	}
	
	
	public static JSONObject SubjectData(String q, PrintWriter out){
		JSONObject g=new JSONObject();
		try {
			 String data= fetchsolrdata(q);
			 String etaBasis=SubjectHeadersAllMethods.etaBasisRegrexDate(q, out);
		      boolean checkdata=SaveReportDataClass.isJSONValid(data);
		      if(checkdata){
		    	  String allData= parsesolrresp(data, q, out);
		    	 // System.out.println("alldata: "+allData);
		     	  boolean checkallData=isJSONValid1(allData);
		     	 if(checkallData==true){
		     		JSONArray s=new JSONArray(allData);
					//JSONArray finaldata=new JSONArray();
					
					 for(int i=0;i<s.length();i++){
						 JSONObject ss= s.getJSONObject(i);
						 Iterator<String> itr=ss.keys();
						 while(itr.hasNext()){
							 String key= itr.next().toString();
							  g.put(key, ss.getString(key));
						 }
						  g.put("ETABasis", etaBasis);
						  
					 }
					// finaldata.put(g); 
					 System.out.println(g);
		     	 }
		      }
			
		      if( g.length()==0){
				  g.put("ETABasis", etaBasis);
			  }
			
		} catch (Exception e) {
			
		}
		return g;
	}
	
	public static boolean isJSONValid1(String test) {
	    try {
	        new JSONObject(test);
	    } catch (JSONException ex) {
	        // edited, to include @Arthur's comment
	        // e.g. in case JSONArray is valid as well...
	        try {
	            new JSONArray(test);
	        } catch (JSONException ex1) {
	            return false;
	        }
	    }
	    return true;
	}
	
	public static boolean isJSONValid(String test) {
	    try {
	    	
	    	 test = test.trim();
	    	 if (test.startsWith("[")) {
	    		 //System.out.println("true");
	    		 return true;
			}
	    	
	    } catch (Exception e) {
	        return false;
	    }
	    return true;
	}
	
	public static JSONObject checkJsonOrArray(String oldJson) {
		JSONObject objectjson = null;
		JSONArray copyObj = new JSONArray();
		try {
			oldJson = oldJson.trim();
			if (oldJson.startsWith("{")) {
				// System.out.println("reqkeyset "+reqkeyset);
				objectjson = new JSONObject(oldJson);

			} else if (oldJson.startsWith("[")) {
				JSONArray allReportJsonArray = new JSONArray(oldJson);
				objectjson = new JSONObject();
				objectjson.put("AllData", allReportJsonArray);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectjson;
	}

	
	public static String parsesolrresp(String resp, String SubjectData, PrintWriter out) {
		String allData="";
		//System.out.println("resp "+resp);
		try {
			JSONObject sorrespobj = new JSONObject(resp);
			 JSONArray arrayKeyValueData=new JSONArray();
			if (sorrespobj.has("response") && sorrespobj.getJSONObject("response").has("maxScore")
					&& sorrespobj.getJSONObject("response").has("docs")) {

				JSONObject Subsorrespobj = sorrespobj.getJSONObject("response");
				JSONArray docs = Subsorrespobj.getJSONArray("docs");
//              if(docs.length()>0){
				for (int i = 0; i < docs.length(); i++) {
					 JSONObject subdocs = docs.getJSONObject(i);
					 String keyString="";
					 String valueString="";
					 if(subdocs.has("Key")){
						 JSONArray key= subdocs.getJSONArray("Key");
						  keyString= key.getString(0);
					 }
					 if(subdocs.has("value")){
						 JSONArray value= subdocs.getJSONArray("value");
						 valueString= value.getString(0);
					 }
					 JSONObject keyValue=new JSONObject();
					 keyValue.put(keyString, valueString);
					 
					 arrayKeyValueData.put(keyValue);
					
					
//					 System.out.println("key: "+ keyString +"  "+"value: "+ valueString);
				} // for close
				if(arrayKeyValueData.length()>0){
				   allData= arrayKeyValueData.toString();
				}
//			}// check length
			}

		} catch (Exception e) {
			return allData;

		}

		return allData;
	}
	
	public static String fetchsolrdata(String q) {
    //  System.out.println(q);
		StringBuffer response1 = null;
		try {
			 q=q.toUpperCase();
			 q = URLEncoder.encode(q, "UTF-8");
           // q=q.toUpperCase();
			String url = "";
			String f1="fl=score,Key,value";
			String df="value_str";

			url = "http://35.188.227.168:8983/solr/subject_data/select?fl=score,Key,value&defType=dismax&mm=1&pf=q&&q=value_str:("+q+")&&qf=value_str";
			//url = "http://35.188.227.168:8983/solr/subject_data/select?fl=score,Key,value&defType=dismax&mm=1&pf=q&&q=value:("+q+")&&qf=value";
			//url = "http://35.188.227.168:8983/solr/subject_data/select?q=value_str:{!dismax}"+q +"&qf=value^1 Key^1";
			
			URL url1 = new URL(url);
			HttpURLConnection con = (HttpURLConnection) url1.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			response1 = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response1.append(inputLine);
			}
			//System.out.println(response1.toString());
			in.close();

		} catch (Exception e) {
			return e.getMessage();
		}
		return response1.toString();
		

	}
	
	public static JSONObject finalHeaderData(JSONObject myJSonSubject, JSONObject expertScriptJSon){
		JSONObject finalHeaderData=new JSONObject();
		try {
			
			JSONObject header_data=null;
			
			String RepositionRegionExpert="";
			String vesselTypeExpert="";
			String cargoTypeExpert="";
			String DateExpert="";
			
			if(expertScriptJSon.has("header_data")){
				header_data=expertScriptJSon.getJSONObject("header_data");
				
				if(header_data.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion")){
					RepositionRegionExpert=header_data.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion");
				}
				
				if(header_data.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType")){
					vesselTypeExpert=header_data.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType");
				}
				
				if(header_data.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type")){
					cargoTypeExpert=header_data.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type");
				}else if(header_data.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#CargoType")){
					cargoTypeExpert=header_data.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#CargoType");
				}
				if(header_data.has("Date")){
					DateExpert=header_data.getString("Date");
				}
				
				/*System.out.println("vesselTypeExpert: "+vesselTypeExpert);
				System.out.println("RepositionRegionExpert: "+RepositionRegionExpert);
				System.out.println("cargoTypeExpert: "+cargoTypeExpert);*/
				
			}
			
			String RepositionRegionMyJson="";
			String VesselTypeMyJson="";
			String Cargo_TypeMyJson="";
			String ETABasisMyJson="";
			
			if(myJSonSubject.has("RepositionRegion")){
				RepositionRegionMyJson=myJSonSubject.getString("RepositionRegion");
			}
			if(myJSonSubject.has("VesselType")){
				VesselTypeMyJson=myJSonSubject.getString("VesselType");
			}
			if(myJSonSubject.has("Cargo_Type")){
				Cargo_TypeMyJson=myJSonSubject.getString("Cargo_Type");
			}else if(myJSonSubject.has("CargoType")){
				Cargo_TypeMyJson=myJSonSubject.getString("CargoType");
			}
			if(myJSonSubject.has("ETABasis")){
				ETABasisMyJson=myJSonSubject.getString("ETABasis");
			}
			
			/*System.out.println("RepositionRegionMyJson: "+RepositionRegionMyJson);
			System.out.println("VesselTypeMyJson: "+VesselTypeMyJson);
			System.out.println("Cargo_TypeMyJson: "+Cargo_TypeMyJson);
			System.out.println("ETABasisMyJson: "+ETABasisMyJson);*/
			
			if(GmailMethods.isNullString(RepositionRegionMyJson)){
				finalHeaderData.put("RepositionRegion", RepositionRegionExpert);
			}else{
				finalHeaderData.put("RepositionRegion", RepositionRegionMyJson);
			}
			if(GmailMethods.isNullString(VesselTypeMyJson)){
				finalHeaderData.put("VesselType", vesselTypeExpert);
			}else{
				finalHeaderData.put("VesselType", VesselTypeMyJson);
			}
			if(GmailMethods.isNullString(Cargo_TypeMyJson)){
				finalHeaderData.put("CargoType", cargoTypeExpert);
			}else{
				finalHeaderData.put("CargoType", Cargo_TypeMyJson);
			}
			if(GmailMethods.isNullString(ETABasisMyJson)){
				finalHeaderData.put("ETABasis", DateExpert);
			}else{
				finalHeaderData.put("ETABasis", ETABasisMyJson);
			}
			
			
		} catch (Exception e) {
			
		}
		return finalHeaderData;
	}
	
	public static String headersDataExcel(PrintWriter out, String allReport, Session session){
		JSONObject dataAll=null;
		String dataall="";
		try {
			
			JSONObject objectjson = new JSONObject(allReport);
			
			//...... for headers data .........
			if(objectjson.has("subject_HeadersData")){
				JSONArray header_data_jsonarray=objectjson.getJSONArray("subject_HeadersData");
				
				for(int i=0;i<header_data_jsonarray.length();i++){
					JSONObject headerJsonobj=header_data_jsonarray.getJSONObject(i);
					
					String check_Port_repositionRegion_id="";					
					if(headerJsonobj.has("RepositionRegion")){
						 check_Port_repositionRegion_id=headerJsonobj.getString("RepositionRegion");
					}
					String check_CargoType_cargotype_id="";
					if(headerJsonobj.has("CargoType")){
						check_CargoType_cargotype_id=headerJsonobj.getString("CargoType");
					}
					String vesselType_id="";
					if(headerJsonobj.has("VesselType")){
					   vesselType_id=headerJsonobj.getString("VesselType");
					} 
					
					String ETABasisString="";
					if(headerJsonobj.has("ETABasis")){
					   ETABasisString=headerJsonobj.getString("ETABasis");
					}
					
					 dataAll=new JSONObject();
					 dataAll.put("RG", check_Port_repositionRegion_id);
					 dataAll.put("CT", check_CargoType_cargotype_id);
					 dataAll.put("VT", vesselType_id);
					 dataAll.put("EB", ETABasisString);
					 
					 dataall=dataAll.toString();
				}
			}
	        // ................. close headers data..........
			
		} catch (Exception e) {
			
		}
		return dataall;
	}

}
