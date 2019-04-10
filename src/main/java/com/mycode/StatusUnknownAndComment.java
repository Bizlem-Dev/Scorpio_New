package com.mycode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.reportinformationsystem.SaveReportDataClass;

public class StatusUnknownAndComment {

	public static void main(String[] args) {
		String q="ST SHIPPING-FX";
		JSONObject data=SubjectData(q, null);
		System.out.println(data);
	}
	
	public static JSONObject SubjectData(String q, PrintWriter out){
		JSONObject allData=null;
		try {
			
			String data=fetchsolrdata(q);
			boolean checkdata=SaveReportDataClass.isJSONValid(data);
		      if(checkdata){
		    	   allData=parsesolrresp(data);
			     	 
		      }
			
		} catch (Exception e) {
			return allData;
		}
		return allData;
	}
	
	public static String fetchsolrdata(String passvalue){
		StringBuffer response1=null;
		try {
			passvalue=passvalue.toUpperCase();
			
			passvalue = URLEncoder.encode(passvalue, "UTF-8");
			
			String url = "";
			String fl="Key,value,score";
			String q="value:("+passvalue+")^2";

			url="http://35.188.227.168:8983/solr/StatusUNComm/select?q="+q+"&fl="+fl+"";
			
			url = url.replace(" ", "%20");
			
			URL url1 = new URL(url);
			HttpURLConnection con = (HttpURLConnection) url1.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			 response1 = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response1.append(inputLine);
			}
			//System.out.println(response1);
			in.close();

		} catch (Exception e) {
			
		}
		return response1.toString();
	}
	
	public static JSONObject parsesolrresp(String resp) {
		JSONObject newObj=new JSONObject();
		try {
			JSONObject sorrespobj = new JSONObject(resp);
			if (sorrespobj.has("response") ) {

				JSONObject Subsorrespobj = sorrespobj.getJSONObject("response");
				if(Subsorrespobj.has("docs")){
				   JSONArray docs = Subsorrespobj.getJSONArray("docs");
				
				JSONObject RG=new JSONObject();
				
				for (int i = 0; i < docs.length(); i++) {
					 JSONObject subdocs = docs.getJSONObject(i);
					 String keyString="";
					 String valueString="";
					 // json get here
					//System.out.println("subdocs:: "+subdocs);
					double score=0.0;
					if(subdocs.has("score")){
						score=subdocs.getDouble("score");
						//System.out.println("score:: "+score);
					}
					

					 if(subdocs.has("Key")){
						 JSONArray key= subdocs.getJSONArray("Key");
						  keyString= key.getString(0);
					 }
					 if(subdocs.has("value")){
						 JSONArray value= subdocs.getJSONArray("value");
						 valueString= value.getString(0);
					 }
					 
					 if(keyString.equals("Status")){
						 double scoreRG=0.0;
						 if(RG.has("Status")){
							 scoreRG= RG.getDouble("score");
							 if(score<scoreRG){
								// System.out.println("less score go in else part");
							 }else{
								// System.out.println("rg");
								 RG.put("score", score);
								 RG.put(keyString, valueString);
							 }
						 }else{
							// System.out.println("rgg");
							 RG.put("score", score);
							 RG.put(keyString, valueString);
						 }
						 
						 
					 }// RG check
					
				} // for close
				
				String newRg="";
				
				if(RG.has("Status")){
					newRg=RG.getString("Status");
				}
				
				newObj.put("Status", newRg);
				
//				allData=newObj.toString();
			}
			}

		} catch (Exception e) {
			return newObj;

		}

		return newObj;
	}
	


}
