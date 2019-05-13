package com.mycode;

import java.io.PrintWriter;
import java.util.Iterator;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.readGmail.GmailMethods;


public class LogProcessed_REjected_Reason_Rows {

	public static void main(String[] args) {

	}
	
	
	public static String rowsCount(String PallaviJsonCountRows, PrintWriter out){
		String finalString="";
		try {
			int rowsCount=0;
			
			JSONObject jsonStringInput=new JSONObject(PallaviJsonCountRows);
			Iterator<String> jsonObjkeys=jsonStringInput.keys();
			while(jsonObjkeys.hasNext()){
				String keyjsonObjkeys=jsonObjkeys.next();
				Object jsonObjkeyValueString=jsonStringInput.get(keyjsonObjkeys);
				if (jsonObjkeyValueString instanceof JSONObject) {
					JSONObject tabledataJsonObj=null;
					tabledataJsonObj = (JSONObject)jsonObjkeyValueString;
					 if(tabledataJsonObj.has("table_data")){
						 JSONArray table_dataArray=tabledataJsonObj.getJSONArray("table_data");
	                	 if( table_dataArray.length()>0 ){
	                		 rowsCount=rowsCount+table_dataArray.length();
	                		 finalString=String.valueOf(rowsCount);
	                	 }else{
	                		 finalString="0";
	                	 }
	                	 
					 } // table_data has check
					 
					 
				} // jsonobj check
			} // while close dyanamic table1 etc
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if( GmailMethods.isNullString(finalString) ){
				finalString=null;
			}
			out.close();
		}
		return finalString;
	}
	
	public static String processed_rejected_reason(String myjson, PrintWriter out){
		String finalJsonString="";
		try {
			
			int processedDataCount=0;
			int rejectedDataCount=0;
			
			String rejectedJsonData="";
			 JSONObject dataJSonObj=new JSONObject();
			
			JSONObject countall=new JSONObject();
			
			JSONObject jsonStringInput=new JSONObject(myjson);
			Iterator<String> jsonObjkeys=jsonStringInput.keys();
			while(jsonObjkeys.hasNext()){
				String keyjsonObjkeys=jsonObjkeys.next();
				Object jsonObjkeyValueString=jsonStringInput.get(keyjsonObjkeys);
				if (jsonObjkeyValueString instanceof JSONObject) {
					JSONObject tabledataJsonObj=null;
					tabledataJsonObj = (JSONObject)jsonObjkeyValueString;
					 if(tabledataJsonObj.has("table_data")){
						 JSONArray table_dataArray=tabledataJsonObj.getJSONArray("table_data");
	                	 JSONObject tableinsideDataOnlyJonObj=null;
	                	 if(table_dataArray.length()>0){
	                		 
	                		 for(int i=0;i<table_dataArray.length();i++){
	                			 tableinsideDataOnlyJonObj=  table_dataArray.getJSONObject(i);
	                			 
	                    		 JSONObject headerJSonObj=null;
	                    		 if(tableinsideDataOnlyJonObj.has("new_header_data")){
	                    			 
	                    			 headerJSonObj= tableinsideDataOnlyJonObj.getJSONObject("new_header_data");
	                    		 } else{
	                    			 if(tableinsideDataOnlyJonObj.has("header")){
	                    			    headerJSonObj= tableinsideDataOnlyJonObj.getJSONObject("header");
	                    			 }
	                    		 } // header close
	                    		 
	                    		
	                    		 if(tableinsideDataOnlyJonObj.has("new_data")){ //data
	                    			dataJSonObj= tableinsideDataOnlyJonObj.getJSONObject("new_data");
	                    			 
	                    			rejectedJsonData=rejectedJsonData + "\n" + dataJSonObj.toString();
	                    			
	                    		 } else if(tableinsideDataOnlyJonObj.has("data")){ //data
		                    			   dataJSonObj= tableinsideDataOnlyJonObj.getJSONObject("data");
		                    			 
		                    			   rejectedJsonData=rejectedJsonData + "\n" + dataJSonObj.toString();
		                    		 }
	                    		 
	                    		 
	                    		 boolean vesselNameCheckBlank=false;
	                    		 if(headerJSonObj!=null){
	                    		
	                    		 Iterator<String>itrtemp= headerJSonObj.keys();
	                    		 while(itrtemp.hasNext()){
	                    			 String headerKeyKey=itrtemp.next(); //0
	                    			 String headerValue=headerJSonObj.getString(headerKeyKey); //key
	                    			 
	                    			 if(headerValue.equalsIgnoreCase("VesselName") || headerValue.equalsIgnoreCase("Vessel")){
	    	         						vesselNameCheckBlank=true;
//	    	         						System.out.println("check:: "+vesselNameCheckBlank);
	    	         						break;
	    	         					}
	                    			 
	                    		 }// while close first vesselname check
	                    			if(vesselNameCheckBlank==true){
	                    			
	                    				// processed data
	                    				processedDataCount++;
	                    				countall.put("processedDataCount", String.valueOf(processedDataCount));
	                    		 
	                    		} // vesselname check blank
	                    			else {
	                    				// rejected data and reason
	                    				rejectedDataCount++;
	                    				countall.put("rejectedDataCount", String.valueOf(rejectedDataCount));
	                    				countall.put("rejectedReason", "Vessel Not Found");
	                    				countall.put("rejectedJsonData", rejectedJsonData);
	                    				//System.out.println(rejectedJsonData);
	                    			}
	                    		 
	                    		 }
	                    		
	                    		 
	                    		 
	                		 } // for loop close
	                		 finalJsonString=countall.toString();
	                		
	                	 } // length check tablearray
					 } // table_data has check
					 
					 
					 
				} // jsonobj check
			} // while close dyanamic table1 etc
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if( GmailMethods.isNullString(finalJsonString) ){
				finalJsonString=null;
			}
			out.close();
		}
		return finalJsonString;
	}
	

}
