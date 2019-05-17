package com.mycode;


import java.util.Iterator;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import com.reportinformationsystem.SaveReportDataClass;

public class OnlyCheckFourJsonToReportType {

	public static void main(String[] args) throws JSONException {

		String d="{\r\n" + 
				"\"ETABasis\":\"Apr 09\",\r\n" + 
				"\"objheader\":[\r\n" + 
				"{\r\n" + 
				"\"RepositionRegion\":\"Med\",\r\n" + 
				"\"ReportType\":\"Tonnage\",\r\n" + 
				"\"CargoType\":\"\"\r\n" + 
				"}\r\n" + 
				"],\r\n" + 
				"\"VesselName\":\"Seaways Hellas\",\r\n" + 
				"\"DWT\":\"69\",\r\n" + 
				"\"Built\":\"03\",\r\n" + 
				"\"OpenPort\":\"Antwerp\",\r\n" + 
				"\"OpenDate\":\"Apr 09\",\r\n" + 
				"\"Comments\":\"poss\",\r\n" + 
				"\"Owners\":\"Capetank\",\r\n" + 
				"\"ReportType\":\"Tonnage\"\r\n" + 
				"}";
		
		JSONObject f=new JSONObject(d);
		JSONObject jsonObjReturn=fetchOjectHeaderFromTable(f);
		System.out.println(jsonObjReturn);
	
	}
	public static JSONObject fetchOjectHeaderFromTable(JSONObject allReportJsonArrayInsideJSonobject){
		JSONObject f=null;
		try {
				if(allReportJsonArrayInsideJSonobject.has("objheader")){
				   JSONArray objheader=allReportJsonArrayInsideJSonobject.getJSONArray("objheader");
										
					for(int j=0;j<objheader.length();j++) {
						 f=objheader.getJSONObject(j);
						 
			}
		  }
								
							
			
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return f;
	}
	
	public static String checkHeaderDataAndSubjectData( JSONObject tabledataJsonObj , JSONObject headerJSonObj, JSONObject dataJSonObj){
		String  RT="";
		try {
				
				JSONArray header_data=null;
				
				
       		 if( tabledataJsonObj.has("header_data") ){
       			 header_data=tabledataJsonObj.getJSONArray("header_data");
       			// System.out.println("header_data:: "+header_data);
       			 
       			 for(int i=0;i<header_data.length();i++){
       				JSONObject headerJsonObj=header_data.getJSONObject(i);
       				
       				if(headerJsonObj.has("data")){
       					JSONArray data=headerJsonObj.getJSONArray("data");
       					//System.out.println(data);
       					for(int j=0;j<data.length();j++){
       						JSONObject dataArrayObj=data.getJSONObject(j);
       						String url="";
       	       				if(dataArrayObj.has("url")){
       	       					url=dataArrayObj.getString("url");
       	       					
       	       					
       	       		if(url.equals("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType")){
     					  
    					   if(dataArrayObj.has("word")){
    						String  RTWord=dataArrayObj.getString("word");
    						  String resp=ReportTypeSynomesCheckSolr.fetchsolrdata(RTWord);
    						 boolean reportCheck= SaveReportDataClass.isJSONValid(resp);
    						 if(reportCheck){
    							 RT= ReportTypeSynomesCheckSolr.parsesolrresp(resp);
    							 System.out.println("INSIDE RT:: "+RT);
    						 }
    					      
    					   }
    					   
    					} // ReportType
       	       		
       	       					
       	       				}
       	       				
       					} // for data close
       				}
       			 } // for headerdata close
       		 }
       		 
       		 Iterator<String>itr=headerJSonObj.keys();
       		 while(itr.hasNext()){
       			String nextKeys= itr.next();
       			String keysvalue=headerJSonObj.getString(nextKeys);
       			
       			if(keysvalue.equalsIgnoreCase("ReportType")){
       				RT=dataJSonObj.getString(nextKeys);
       				break;
       			}
       			
       		 } // while close
       		 
			
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return RT;
	}
	

}
