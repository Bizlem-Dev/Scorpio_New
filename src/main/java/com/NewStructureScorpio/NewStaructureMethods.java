package com.NewStructureScorpio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

public class NewStaructureMethods {

	public static JSONObject maxJsonCount(String jsonString, PrintWriter out) {
		JSONObject temp_subobj = null;
		try {
			
			JSONObject jsonStringObj=new JSONObject(jsonString);
			Iterator<String> jsonObjkeys=jsonStringObj.keys();
			while(jsonObjkeys.hasNext()){
				String jsonObjKeysString=jsonObjkeys.next();
				Object jsonObjkeyValueString=jsonStringObj.get(jsonObjKeysString);
				if (jsonObjkeyValueString instanceof JSONObject) {
					//System.out.println(jsonObjkeyValueString);
                   JSONObject tabledataJsonObj=null;
                   tabledataJsonObj = (JSONObject)jsonObjkeyValueString;
                  // System.out.println(tabledataJsonObj);
                   if(tabledataJsonObj.has("table_data")){
                	   JSONArray table_dataArray=tabledataJsonObj.getJSONArray("table_data");
                	  // System.out.println(table_dataArray);
                	   
                	   HashMap<Integer, Integer> keycount = new HashMap<Integer, Integer>();
                	   JSONObject tableinsideDataOnlyJonObj=null;
                	   
                	   if(table_dataArray.length()>0){
                	   for(int i=0;i<table_dataArray.length();i++){
                		  tableinsideDataOnlyJonObj=  table_dataArray.getJSONObject(i);
                		// System.out.println(tableinsideDataOnlyJonObj);
                		 if(tableinsideDataOnlyJonObj.has("data")){
                			 JSONObject dataJSonObj= tableinsideDataOnlyJonObj.getJSONObject("data");
                			// System.out.println(dataJSonObj);
                			// System.out.println("count: "+dataJSonObj.length());
     						 keycount.put(dataJSonObj.length(), i);
                		 }
                		
                		 
                	   }
                	   
                		int maxlength = (int) Collections.max(keycount.keySet());
    					// System.out.println("maxlengh: "+maxlength);
    					int maxlengthposition = keycount.get(maxlength);
    					 //System.out.println("maxlengthposition: "+maxlengthposition);

    					temp_subobj = table_dataArray.getJSONObject(maxlengthposition);
                	   // System.out.println(temp_subobj);
                   } 
                   }
                   
                   
                   
				}
			}
			
			
		} catch (Exception e) {
		   
		}
		return temp_subobj;
		
	}
	
	public static String executesolrQueryAllReportForData(String data, int mm) throws UnsupportedEncodingException {

		StringBuffer response = new StringBuffer();

		try {

			 data = URLEncoder.encode(data, "UTF-8");
			 //String url1 = "http://34.73.112.165:8983/solr/AllReport/select?fl=score,DocumentName,url&q=Data_str:"+data+"&rows=3";
			 String url1="http://34.73.112.165:8983/solr/AllReport/select?fl=score,DocumentName,url&defType=dismax&mm="+mm+"&pf=Data&&q=Data:("+data+")&&qf=Data";
			 URL url = new URL(url1);
			 System.out.println("url ****  "+url1);
			 HttpURLConnection con = (HttpURLConnection) url.openConnection();
			 con.setRequestMethod("GET");
			 con.setRequestProperty("Content-Type", "application/json");
			 con.setDoOutput(true);
			 BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
			 String inputLine;
			 while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			 }
			 System.out.println(response);
			 in.close();
		}
		catch (Exception e) {
	    System.out.println(e.getMessage());
		}
		return response.toString();
	}
	
public static JSONObject columnCheckFromSolr(JSONObject tabledataJsonObj, JSONObject maxLengthJsonData) {
		
		try {
			LinkedHashMap<String, String> lhmap=new LinkedHashMap<>();
			JSONObject header=null;
			if(maxLengthJsonData.has("header")){
				 header= maxLengthJsonData.getJSONObject("header");
				  
	 				Iterator<String>itr= header.keys();
	 				while(itr.hasNext()){
	 					String maxlengthJsonKey=itr.next();
	 					String valuemaxJson=header.getString(maxlengthJsonKey);
	 					
	 					if(tabledataJsonObj.has("table_data")){
	 						JSONArray table_dataArray=tabledataJsonObj.getJSONArray("table_data");
	 		          	   JSONObject tableinsideDataOnlyJonObj=null;
	 		          	   
	 		          	   if(table_dataArray.length()>0){
	 		          		String dataValue="";
	 		          		 for(int i=0;i<table_dataArray.length();i++){
	 		          			 tableinsideDataOnlyJonObj=  table_dataArray.getJSONObject(i);
		                			JSONObject dataJSonObj=null;
		                 		  if(tableinsideDataOnlyJonObj.has("data")){
		                 			 dataJSonObj= tableinsideDataOnlyJonObj.getJSONObject("data");
		                 			
		                 			 dataValue= dataJSonObj.getString(maxlengthJsonKey);
 		                 			 dataValue=dataValue.replaceAll(" ", "_");
 		                 			 dataValue =dataValue+"\""+dataValue+"\""+ quote(dataValue);
		                 		  }
		                 		 System.out.println("dataJSonObj:: "+dataJSonObj);
	 		          		 }
	 		          	
	 	 	       		    
	 		          		 
	 		          	   }
	 					}
	 					
	 				}
			}
			
			
		} catch (Exception e) {
		   
		}
		return null;
		
	}

	public static String quote(String s) {
	    return new StringBuilder()
	        .append('"')
	        .append(s)
	        .append('"')
	        .append(",")
	        .toString();
	}
}
