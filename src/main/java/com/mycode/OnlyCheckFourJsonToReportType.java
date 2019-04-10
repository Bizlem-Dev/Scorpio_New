package com.mycode;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.reportinformationsystem.SaveReportDataClass;

public class OnlyCheckFourJsonToReportType {

	public static void main(String[] args) {

	}
	
	public static String data(HashMap<String, String> g, int headerCount){
		 String reporttype="";
		try {
			
			 if(g!=null && !g.isEmpty() && g.size()>0){
    			 
    				boolean vesselHas=false;
    				boolean LCStartHas=false;
    				boolean LoadPortHas=false;
    				boolean RateHas=false;
    				boolean DateHas=false;
    				boolean PortHas=false;
    				boolean LCEndHas=false;
    				boolean DiscPortHas=false;
    				
    				boolean OpenDatePoss=false;
    				boolean OpenPortPoss=false;
    				boolean ETABasisPoss=false;
    				boolean ChartererPoss=false;
    			 
    		 for (Map.Entry<String, String> entry : g.entrySet())
			  {
    				
    				if(g.containsValue("VesselName")){
    					
    					 vesselHas=true;
    				}if(g.containsValue("LCStart")){
    					
    					LCStartHas=true;
    				}if(g.containsValue("LoadPort")){
    					
    					LoadPortHas=true;
    				}if(g.containsValue("Rate")){
    					
    					RateHas=true;
    				}if(g.containsValue("Date")){
    					
    					DateHas=true;
    				}if(g.containsValue("Port")){
    					
    					PortHas=true;
    				}if(g.containsValue("LCEnd")){
    					
    					LCEndHas=true;
    				}if(g.containsValue("DiscPort")){
    					
    					DiscPortHas=true;
    				}if(g.containsValue("OpenDate")){
    					
    					OpenDatePoss=true;
    				}if(g.containsValue("OpenPort")){
    					
    					OpenPortPoss=true;
    				}if(g.containsValue("ETABasis")){
    					
    					ETABasisPoss=true;
    				}if(g.containsValue("Charterer")){
    					
    					ChartererPoss=true;
    				}
    				 if(headerCount<=4){
    						break;
    					}
    			
			  } // map for loop close
    		 
    		 JSONObject countAll=ReportTypeIdentify.countRateAndPortAndDate(g);
    		 System.out.println(":: "+countAll);
    		 
    		 int dateCount=0;
    			int portCount=0;
    			int rateCount=0;
    			
    			 if(countAll!=null){
    				 if(countAll.has("Date")){
    					 dateCount= countAll.getInt("Date");
    				 }if(countAll.has("Port")){
    					 portCount=countAll.getInt("Port");
    				 }if(countAll.has("Rate")){
    					 rateCount=countAll.getInt("Rate");
    				 }
    				
    			 }
    			// dateGreaterLesser(g, dataJSonObj);
    			
    			 if( vesselHas && dateCount>=1 && portCount>=1 && rateCount>=1){
    				  reporttype="Spot";
    			 }

    			 else if( vesselHas && LCStartHas && LoadPortHas && RateHas ){
    				 reporttype="Spot";
    				 
    			 }else if(vesselHas && LCEndHas && DiscPortHas && RateHas){
    				 reporttype="Spot";
    			 }// spot 
    			 else {
    				 
    				 if(vesselHas && dateCount>=1 && portCount>=1){
    					 reporttype="Tonnage";
    					 
    				 }else if( vesselHas && OpenDatePoss && OpenPortPoss ){
    					 reporttype="Tonnage";
    				 }else if( vesselHas && OpenDatePoss && OpenPortPoss && ETABasisPoss ){
    					 reporttype="Tonnage";
    				 }
    				 
    			 } // tonnage
				
    			
    		 }// check null map
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reporttype;
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
			e.printStackTrace();
		}
		return RT;
	}
	

}
