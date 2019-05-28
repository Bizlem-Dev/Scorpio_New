package com.subjectheaders;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.reportinformationsystem.SaveReportDataClass;

public class QueryBoostForTitles {

	public static void main(String[] args) {
		 String q="CLARKSON USA - DTY CARIBS HANDY MR PANAMAX LIST (ATTACHED)";
		// q = q.replaceAll("((?<![A-Za-z])(AND|and|And))","");
		// System.out.println("q:: "+q);
		 JSONObject s=SubjectData(q, null);
		 System.out.println(s);
		 
		String data= SubjectHeadersAllMethods.etaBasisRegrexDate("29/10/2019 string", null);
		System.out.println(data);
	}
	
	public static JSONObject SubjectData(String q, PrintWriter out){
		 JSONObject s=null;
		try {
			q = q.replaceAll("((?<![A-Za-z])(AND|and|And))","");
			
			String data=fetchsolrdata(q);
			String etaBasis=SubjectHeadersAllMethods.etaBasisRegrexDate(q, out);
			boolean checkdata=SaveReportDataClass.isJSONValid(data);
		      if(checkdata){
		    	  String allData=parsesolrresp(data);
		    	  boolean checkallData=SaveReportDataClass.isJSONValid(allData);
			     	 if(checkallData==true){
			     		  s=new JSONObject(allData);
			     		 
			     		if( s.length()>0){
			     			s.put("ETABasis", etaBasis);
						  }else{
							  s.put("ETABasis", etaBasis);
						  }
			     		//System.out.println(s);
			     	 }
		      }
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return s;
	}
	
	public static String fetchsolrdata(String passvalue){
		StringBuffer response1=null;
		try {
			passvalue=passvalue.toUpperCase();
			
			passvalue = URLEncoder.encode(passvalue, "UTF-8");
			
			String url = "";
			//String passvalue="EASTPORT CLEAN LR POSITION BSS TAIWAN AS AT 28TH MAR 2019";
			//String passvalue="HANDY/MR DPP BSEA TONNAGE LIST UPDATED 27/03/19";
			String fl="Key,value,score";
			String q="value_str:("+passvalue+")^2";

//			url="http://35.231.163.191:8983/solr/subject_data/select?q="+q+"&fl="+fl+"";
			url="http://35.231.163.191:8983/solr/subject_data/select?q="+q+"&fl="+fl;
			//url="http://172.31.0.3:8983/solr/subject_data/select?q="+q+"&fl="+fl+"";
			url = url.replace(" ", "%20");
			
			URL url1 = new URL(url);
			HttpURLConnection con = (HttpURLConnection) url1.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			 response1 = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response1.append(inputLine);
			}
			System.out.println(response1);
			in.close();
            con.disconnect();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return response1.toString();
	}

	public static String parsesolrresp(String resp) {
		String allData="";
		try {
			JSONObject sorrespobj = new JSONObject(resp);
			if (sorrespobj.has("response") ) {

				JSONObject Subsorrespobj = sorrespobj.getJSONObject("response");
				if(Subsorrespobj.has("docs")){
				   JSONArray docs = Subsorrespobj.getJSONArray("docs");
				
				JSONObject RG=new JSONObject();
				JSONObject CT=new JSONObject();
				JSONObject VT=new JSONObject();
				
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
					 
					 if(keyString.equals("RepositionRegion")){
						 double scoreRG=0.0;
						 if(RG.has("RepositionRegion")){
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
					 
					 else if(keyString.equals("CargoType")){
						 
						 double scoreCT=0.0;
						 if(CT.has("CargoType")){
							 scoreCT= CT.getDouble("score");
							 if(score<scoreCT){
								// System.out.println("less score go in else part");
							 }else{
								// System.out.println("ct");
								 CT.put("score", score);
								 CT.put(keyString, valueString);
							 }
						 }else{
							// System.out.println("ctt");
							 CT.put("score", score);
							 CT.put(keyString, valueString);
						 }
						 
					 }// ct check
                       else if(keyString.equals("VesselType")){
						 
						 double scoreVT=0.0;
						 if(VT.has("VesselType")){
							 scoreVT= VT.getDouble("score");
							 if(score<scoreVT){
								// System.out.println("less score go in else part");
							 }else{
								// System.out.println("vt");
								 VT.put("score", score);
								 VT.put(keyString, valueString);
							 }
						 }else{
							// System.out.println("vtt");
							 VT.put("score", score);
							 VT.put(keyString, valueString);
						 }
						 
					 }// vt check
					
					
				} // for close
				//System.out.println("RG:: "+RG);
				//System.out.println("CT:: "+CT);
				//System.out.println("VT:: "+VT);
				
				JSONObject newObj=new JSONObject();
				
				String newRg="";
				String newCT="";
				String newVT="";
				
				if(RG.has("RepositionRegion")){
					newRg=RG.getString("RepositionRegion");
				}if(CT.has("CargoType")){
					newCT=CT.getString("CargoType");
				}
				if(VT.has("VesselType")){
					newVT=VT.getString("VesselType");
				}
				
				newObj.put("RepositionRegion", newRg);
				newObj.put("CargoType", newCT);
				newObj.put("VesselType", newVT);
				
				allData=newObj.toString();
			}
			}

		} catch (Exception e) {
			return allData;

		}

		return allData;
	}
	
}
