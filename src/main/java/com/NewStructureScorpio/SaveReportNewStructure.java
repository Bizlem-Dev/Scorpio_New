package com.NewStructureScorpio;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;


public class SaveReportNewStructure {

	public static void main(String[] args) {
      String jsonString="{\"table_1\":\r\n" + 
      		"{\"header_data\":{},\r\n" + 
      		"\"table_data\":[\r\n" + 
      		"{\"StructureType\":\"table\",\"data\":{\"0\":\"STENAWECO ELEGANCE\",\"1\":\"TRICON\",\"2\":\"38\",\"3\":\"CLN\",\"4\":\"23 - 25 MAR\",\"5\":\"USG\",\"6\":\"BRAZIL\",\"7\":\"137.5\",\"8\":\"FIXED\"},\"header\":{\"0\":\"VesselName\",\"1\":\"Charterer\",\"2\":\"CargoQty\",\"3\":\"CargoGrade\",\"4\":\"LCStart\",\"5\":\"LoadPort\",\"6\":\"DiscPort\",\"7\":\"Rate\",\"8\":\"Status\"},\"line_index\":26\r\n" + 
      		"},\r\n" + 
      		"{\"StructureType\":\"table\",\"data\":{\"0\":\"STI NOTTING HILL\",\"1\":\"CNR\",\"2\":\"38\",\"3\":\"CLN\",\"4\":\"20 - 22 MAR\",\"5\":\"HOUSTON\",\"6\":\"OPS\",\"7\":\"RNR\",\"8\":\"FIXED\"},\"header\":{\"0\":\"VesselName\",\"1\":\"Charterer\",\"2\":\"CargoQty\",\"3\":\"CargoGrade\",\"4\":\"LCStart\",\"5\":\"LoadPort\",\"6\":\"DiscPort\",\"7\":\"Rate\",\"8\":\"Status\"},\"line_index\":27\r\n" + 
      		"}\r\n" + 
      		"]\r\n" + 
      		"}\r\n" + 
      		"}";
      SaveReportNewStructureChanged(null, jsonString);
		
	}
	
	public static String SaveReportNewStructureChanged(PrintWriter out, String jsonString){
		JSONObject maxLengthJsonData = null;
		try{
			LinkedHashMap<String, String> lhmap=new LinkedHashMap<>();
			
			JSONObject jsonStringInput=new JSONObject(jsonString);
			Iterator<String> jsonObjkeys=jsonStringInput.keys();
			while(jsonObjkeys.hasNext()){
				String keyjsonObjkeys=jsonObjkeys.next();
				//System.out.println(keyjsonObjkeys);
				Object jsonObjkeyValueString=jsonStringInput.get(keyjsonObjkeys);
				if (jsonObjkeyValueString instanceof JSONObject) {
					JSONObject tabledataJsonObj=null;
					 tabledataJsonObj = (JSONObject)jsonObjkeyValueString;
					 if(tabledataJsonObj.has("table_data")){
						 JSONArray table_dataArray=tabledataJsonObj.getJSONArray("table_data");
	                	 JSONObject tableinsideDataOnlyJonObj=null;
	                	 
	                	 HashMap<Integer, Integer> keycount = new HashMap<Integer, Integer>();
	                	 if(table_dataArray.length()>0){
	                		  for(int i=0;i<table_dataArray.length();i++){
	                    		  tableinsideDataOnlyJonObj=  table_dataArray.getJSONObject(i);
	                    		// System.out.println(tableinsideDataOnlyJonObj);
	                    		 if(tableinsideDataOnlyJonObj.has("data")){
	                    			 JSONObject dataJSonObj= tableinsideDataOnlyJonObj.getJSONObject("data");
	                    			// System.out.println(dataJSonObj);
	                    			// System.out.println("count: "+dataJSonObj.length());
	         						 keycount.put(dataJSonObj.length(), i);
	                    		 } // data close 
	                    		
	                    		 
	                    	   } // for close 
	                		  int maxlength = (int) Collections.max(keycount.keySet());
	      					// System.out.println("maxlengh: "+maxlength);
	      					int maxlengthposition = keycount.get(maxlength);
	      					 //System.out.println("maxlengthposition: "+maxlengthposition);

	      					maxLengthJsonData = table_dataArray.getJSONObject(maxlengthposition);
	                  	    System.out.println("max JSon:: "+maxLengthJsonData);
	                  	  JSONObject header=null;
	                  	  JSONObject data=null;
	                  	  
	                  	  if(maxLengthJsonData.has("data")){
	                  		 data=maxLengthJsonData.getJSONObject("data");
	                  	  }
	                  	  
	                  	  if(maxLengthJsonData.has("header")){
	                  		header= maxLengthJsonData.getJSONObject("header");
	                  		Iterator<String>itr= header.keys();
	                  		while(itr.hasNext()){
	                  			String maxlengthJsonKey=itr.next();
	         					String valuemaxJson=header.getString(maxlengthJsonKey);
	         					System.out.println("keys:: "+maxlengthJsonKey);
	         					
	         					String rate="";
	         					String rateType="";
	         					
	         					if(valuemaxJson.contains("unknown")){
	         						System.out.println("position_unknown:: "+maxlengthJsonKey);
	         						if(data.has(maxlengthJsonKey)){
	         							String max_keyValeu_original=data.getString(maxlengthJsonKey);
	         							 rate=RateRegex.RateRegrex(max_keyValeu_original, out);
	         							 rateType=RateRegex.getRateVal(rate);
	         							 
	         							for(int i=0;i<table_dataArray.length();i++){
	  		                    		  tableinsideDataOnlyJonObj=  table_dataArray.getJSONObject(i);
	  		                    		// System.out.println(tableinsideDataOnlyJonObj);
	  		                    		 if(tableinsideDataOnlyJonObj.has("data")){
	  		                    			 JSONObject dataJSonObj= tableinsideDataOnlyJonObj.getJSONObject("data");
	  		                    			
	  		                    				 
	  		                    			 
	  		                    		 } // data close 
	  		                    	   } // for close
	         						}
	         						
	         					}else if( valuemaxJson.contains("Comment") || valuemaxJson.contains("Comments") ){
	         						System.out.println("position_comment:: "+maxlengthJsonKey);
	         						if(data.has(maxlengthJsonKey)){
	         							String max_keyValeu_original=data.getString(maxlengthJsonKey);
	         							 rate=RateRegex.RateRegrex(max_keyValeu_original, out);
	         							 rateType=RateRegex.getRateVal(rate);
	         							 
	         							for(int i=0;i<table_dataArray.length();i++){
		  		                    		  tableinsideDataOnlyJonObj=  table_dataArray.getJSONObject(i);
		  		                    		// System.out.println(tableinsideDataOnlyJonObj);
		  		                    		 if(tableinsideDataOnlyJonObj.has("data")){
		  		                    			 JSONObject dataJSonObj= tableinsideDataOnlyJonObj.getJSONObject("data");
		  		                    			 
		  		                    		 } // data close 
		  		                    	   } // for close
	         							
	         						}
	         						
	         					} // rate and ratetype
	         					
	         					String Charterer="";
	         					if(valuemaxJson.contains("unknown")){
	         						System.out.println("position_unknown:: "+maxlengthJsonKey);
	         						if(data.has(maxlengthJsonKey)){
	         							String max_keyValeu_original=data.getString(maxlengthJsonKey);
	         							JSONObject chartereobj=ChartererUnknownAndCommentCheck.chartererData(max_keyValeu_original);
	         							if(chartereobj!=null && chartereobj.length()!=0){        						
	        								if(chartereobj.has("Charterer")){
	        									JSONObject chartererSolrjsonobj=chartereobj.getJSONObject("Charterer");

	        									if(chartererSolrjsonobj.has("Charterer")){
	        										Charterer=chartererSolrjsonobj.getString("Charterer");
	        										
	        										for(int i=0;i<table_dataArray.length();i++){
	        		  		                    		  tableinsideDataOnlyJonObj=  table_dataArray.getJSONObject(i);
	        		  		                    		// System.out.println(tableinsideDataOnlyJonObj);
	        		  		                    		 if(tableinsideDataOnlyJonObj.has("data")){
	        		  		                    			 JSONObject dataJSonObj= tableinsideDataOnlyJonObj.getJSONObject("data");
	        		  		                    			 
	        		  		                    		 } // data close 
	        		  		                    	   } // for close
	        										
	        									}
	        								}

	        							}
	         						}
	         						
	         					}else if( valuemaxJson.contains("Comment") || valuemaxJson.contains("Comments") ){
	         						System.out.println("position_comment:: "+maxlengthJsonKey);
	         						if(data.has(maxlengthJsonKey)){
	         							String max_keyValeu_original=data.getString(maxlengthJsonKey);
	         							JSONObject chartereobj=ChartererUnknownAndCommentCheck.chartererData(max_keyValeu_original);
	         							if(chartereobj!=null && chartereobj.length()!=0){        						
	        								if(chartereobj.has("Charterer")){
	        									JSONObject chartererSolrjsonobj=chartereobj.getJSONObject("Charterer");

	        									if(chartererSolrjsonobj.has("Charterer")){
	        										Charterer=chartererSolrjsonobj.getString("Charterer");
	        										
	        										for(int i=0;i<table_dataArray.length();i++){
	        		  		                    		  tableinsideDataOnlyJonObj=  table_dataArray.getJSONObject(i);
	        		  		                    		// System.out.println(tableinsideDataOnlyJonObj);
	        		  		                    		 if(tableinsideDataOnlyJonObj.has("data")){
	        		  		                    			 JSONObject dataJSonObj= tableinsideDataOnlyJonObj.getJSONObject("data");
	        		  		                    			 
	        		  		                    		 } // data close 
	        		  		                    	   } // for close
	        										
	        									}
	        								}

	        							}
	         						}
	         						
	         					} // charterer
	         					
	         					
	         					
	         					
	                  		}
	                  	  }
	                		  
	                	 } // length check>0
					 }// table_data check
				} // jsonobject check
			}// while close table1 etc key
			
					
		} catch (Exception e) {
			
		}
		return jsonString;
		
	}
	
	

	   
	
}
