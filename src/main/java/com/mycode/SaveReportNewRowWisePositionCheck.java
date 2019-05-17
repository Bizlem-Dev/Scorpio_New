package com.mycode;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.NewStructureScorpio.ChartererUnknownAndCommentCheck;
import com.NewStructureScorpio.RateRegex;
import com.anagha.CombineCharterer_Status_Rate;
import com.readGmail.GmailMethods;
import com.reportinformationsystem.SaveReportDataClass;
import com.subjectheaders.QueryBoostForTitles;


public class SaveReportNewRowWisePositionCheck {

	public static void main(String[] args) {
      /*String jsonString="{\"table_1\":{\r\n" + 
      		"\"header_data\": [\r\n" + 
      		"            {\r\n" + 
      		"                \"data\": [\r\n" + 
      		"                    {\r\n" + 
      		"                        \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",\r\n" + 
      		"                        \"word\": \"CPP\"\r\n" + 
      		"                    },\r\n" + 
      		"                    {\r\n" + 
      		"                        \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\r\n" + 
      		"                        \"word\": \"Usg\"\r\n" + 
      		"                    },\r\n" + 
      		"                    {\r\n" + 
      		"                        \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\",\r\n" + 
      		"                        \"word\": \"position list\"\r\n" + 
      		"                    }\r\n" + 
      		"                ],\r\n" + 
      		"                \"line_index\": 0,\r\n" + 
      		"                \"number_of_words\": 3\r\n" + 
      		"            },\r\n" + 
      		"            {\r\n" + 
      		"                \"data\": [],\r\n" + 
      		"                \"line_index\": 1,\r\n" + 
      		"                \"number_of_words\": 0\r\n" + 
      		"            },\r\n" + 
      		"            {\r\n" + 
      		"                \"data\": [\r\n" + 
      		"                    {\r\n" + 
      		"                        \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\r\n" + 
      		"                        \"word\": \"March\"\r\n" + 
      		"                    }\r\n" + 
      		"                ],\r\n" + 
      		"                \"line_index\": 2,\r\n" + 
      		"                \"number_of_words\": 1\r\n" + 
      		"            },\r\n" + 
      		"            {\r\n" + 
      		"                \"data\": [\r\n" + 
      		"                    {\r\n" + 
      		"                        \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\r\n" + 
      		"                        \"word\": \"HOUSTON\"\r\n" + 
      		"                    },\r\n" + 
      		"                    {\r\n" + 
      		"                        \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",\r\n" + 
      		"                        \"word\": \"CPP\"\r\n" + 
      		"                    }\r\n" + 
      		"                ],\r\n" + 
      		"                \"line_index\": 3,\r\n" + 
      		"                \"number_of_words\": 2\r\n" + 
      		"            },\r\n" + 
      		"            {\r\n" + 
      		"                \"data\": [\r\n" + 
      		"                    {\r\n" + 
      		"                        \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",\r\n" + 
      		"                        \"word\": \"hold\"\r\n" + 
      		"                    }\r\n" + 
      		"                ],\r\n" + 
      		"                \"line_index\": 4,\r\n" + 
      		"                \"number_of_words\": 1\r\n" + 
      		"            },\r\n" + 
      		"            {\r\n" + 
      		"                \"data\": [\r\n" + 
      		"                    {\r\n" + 
      		"                        \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Current_Status\",\r\n" + 
      		"                        \"word\": \"open\"\r\n" + 
      		"                    },\r\n" + 
      		"                    {\r\n" + 
      		"                        \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",\r\n" + 
      		"                        \"word\": \"handy\"\r\n" + 
      		"                    }\r\n" + 
      		"                ],\r\n" + 
      		"                \"line_index\": 5,\r\n" + 
      		"                \"number_of_words\": 2\r\n" + 
      		"            },\r\n" + 
      		"            {\r\n" + 
      		"                \"data\": [\r\n" + 
      		"                    {\r\n" + 
      		"                        \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",\r\n" + 
      		"                        \"word\": \"LR1\"\r\n" + 
      		"                    }\r\n" + 
      		"                ],\r\n" + 
      		"                \"line_index\": 6,\r\n" + 
      		"                \"number_of_words\": 1\r\n" + 
      		"            }\r\n" + 
      		"        ],\r\n" + 
      		"\"table_data\":[\r\n" + 
      		"{\"StructureType\":\"table\",\"data\":{\"0\":\"STENAWECO ELEGANCE\",\"1\":\"TRICON\",\"2\":\"38\",\"3\":\"CLN\",\"4\":\"23 - 25 MAR\",\"5\":\"USG\",\"6\":\"BRAZIL\",\"7\":\"330k\",\"8\":\"FIXED\"},\"header\":{\"0\":\"VesselName\",\"1\":\"Charterer\",\"2\":\"CargoQty\",\"3\":\"CargoGrade\",\"4\":\"LCStart\",\"5\":\"LoadPort\",\"6\":\"DiscPort\",\"7\":\"unknown\",\"8\":\"Status\"},\"line_index\":26\r\n" + 
      		"},\r\n" + 
      		"{\"StructureType\":\"table\",\"data\":{\"0\":\"STI NOTTING HILL\",\"1\":\"CNR\",\"2\":\"38\",\"3\":\"CLN\",\"4\":\"20 - 22 MAR\",\"5\":\"HOUSTON\",\"6\":\"OPS\",\"7\":\"RNR\",\"8\":\"FIXED\"},\"header\":{\"0\":\"VesselName\",\"1\":\"Charterer\",\"2\":\"CargoQty\",\"3\":\"CargoGrade\",\"4\":\"LCStart\",\"5\":\"LoadPort\",\"6\":\"Date\",\"7\":\"Rate\",\"8\":\"Date\"},\"line_index\":27\r\n" + 
      		"}\r\n" + 
      		"]\r\n" + 
      		"}\r\n" + 
      		"}";*/
		
		String jsonString=MethodJsonOnlyInsert.jsonInpput();
		
      String data=SaveReportNewRowWisePositionCheck.SaveReportNewStructureChanged(null, jsonString);
      System.out.println("newJson:: "+data);
     boolean datacheck= SaveReportDataClass.isJSONValid(data);
     if(datacheck){
    	 String subjectdata="CPP_MR_BSS_SPORE_35-55_DWT_TONNAGE_LIST_20.03.2019";
    	 subjectdata=subjectdata.replaceAll("_", " ");
      String finaldata= ReportTypeIdentify.ReportTypeAndFinalJson(data, null, "",null, "", "","","");
     // System.out.println("finaldata:: "+finaldata);
      String f= ReportTypeCorrection.ReportTypeRemaining(finaldata, null, "");
     // System.out.println("f:: "+f);
      String data1= MethodJsonOnlyInsert.applyToAllJsonAsSameReport(f);
		 System.out.println("data1 "+data1);
      
      boolean datacheck1= SaveReportDataClass.isJSONValid(data);
      if(datacheck1){
     // String last=Methods.ReportTypeAndFinalJson(finaldata ,null, "");
     // System.out.println("last:: "+last);
      }
     }
    
      
	}
	
	
	public static String SaveReportNewStructureChanged(PrintWriter out, String jsonString){

		try{
			
			JSONObject jsonStringInput=new JSONObject(jsonString);
			//System.out.println(jsonString);
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
	                	 
	                	 if(table_dataArray.length()>0){
	                		 LinkedHashMap<String, String> lhmap=new LinkedHashMap<>(); //
	                		  for(int i=0;i<table_dataArray.length();i++){
	                    		  tableinsideDataOnlyJonObj=  table_dataArray.getJSONObject(i);
	                    		// System.out.println(tableinsideDataOnlyJonObj);
	                    		  JSONObject dataJSonObj=null;
	                    		 if(tableinsideDataOnlyJonObj.has("new_data")){ //data
	                    			dataJSonObj= tableinsideDataOnlyJonObj.getJSONObject("new_data");
	                    			 
	                    		 }else if(tableinsideDataOnlyJonObj.has("data")){ //data
		                    			dataJSonObj= tableinsideDataOnlyJonObj.getJSONObject("data");
		                    			 
		                    		 }
	                    		 
	                    		 JSONObject headerJSonObj=null;
	                    		
	                    		 if(tableinsideDataOnlyJonObj.has("new_header_data")){
	                    			headerJSonObj= tableinsideDataOnlyJonObj.getJSONObject("new_header_data");
	                    			 JSONObject newHeader=new JSONObject();
    	         					boolean vesselNameCheckBlank=false;

	                    			Iterator<String>itr= headerJSonObj.keys();
	                    			Iterator<String>tempitr= headerJSonObj.keys();

	                    			while(tempitr.hasNext()){
	                    				String JsonKey=tempitr.next();
	                    				String valuemaxJson=headerJSonObj.getString(JsonKey);

	                    				if(valuemaxJson.equalsIgnoreCase("VesselName") || valuemaxJson.equalsIgnoreCase("Vessel") ){
	    	         						vesselNameCheckBlank=true;
//	    	         						System.out.println("check:: "+vesselNameCheckBlank);
	    	         						break;
	    	         					}
	    	         					
	                    			}
	                    			
    	         					if(vesselNameCheckBlank==true){
    	         						//System.out.println("true_VesselName");

	                    			while(itr.hasNext()){
	                    				String JsonKey=itr.next();
	                    				String valuemaxJson=headerJSonObj.getString(JsonKey);
	                    			//	System.out.println("keys:: "+JsonKey);
	                    				
	    	         					newHeader.put(JsonKey, valuemaxJson);
	    	         					
	    	         					String Rate_Position="";
	    	         					if(tabledataJsonObj.has("Rate_Position")){
	    	         						Rate_Position=tabledataJsonObj.getString("Rate_Position");
	    	         					}
	    	         					
	    	         					if( !GmailMethods.isNullString(Rate_Position) ){
	    	         						 newHeader.put(Rate_Position, "Rate");
	    	         						
	    	         					}else{
	    	         						if(valuemaxJson.equals("Rate")){
		    	         						 String dataRateValue=dataJSonObj.getString(JsonKey);
		    	         						if( !GmailMethods.isNullString(dataRateValue) ){
		    	         							  if( valuemaxJson.equals("RateType") ){
		    	         								 String dataRateTypeValue=dataJSonObj.getString(JsonKey);
		    	         								if( !GmailMethods.isNullString(dataRateTypeValue) ){
		    	         									 int rateNotFoundPosition= newHeader.length();
//				   	         							        String rate=RateRegex.RateRegrex(dataRateTypeValue, out);
			   	         								        String rateType=RateRegex.getRateVal(dataRateTypeValue);
			   	         								       newHeader.put(String.valueOf(rateNotFoundPosition), "RateType");
			   	         								       dataJSonObj.put(String.valueOf(rateNotFoundPosition), rateType);
		    	         								}else{
		    	         									if( !valuemaxJson.equals("RateType") ){
		    	         										   int rateNotFoundPosition= newHeader.length();
					   	         							        String rate=RateRegex.RateRegrex(dataRateValue, out);
				   	         								        String rateType=RateRegex.getRateVal(rate);
				   	         								       newHeader.put(String.valueOf(rateNotFoundPosition), "RateType");
				   	         								       dataJSonObj.put(String.valueOf(rateNotFoundPosition), rateType);
		    	         									}
		    	         								}
		   	         								      
		   	         							 }	
		    	         						}
		    	         					}
		    	         					String rate="";
		    	         					String rateType="";
		    	         					if(valuemaxJson.contains("unknown")){
		    	         						//System.out.println("position_unknown:: "+JsonKey);
		    	         						
//		    	         						if(dataJSonObj.has(JsonKey)){
		    	         							String max_keyValeu_original=dataJSonObj.getString(JsonKey);
		    	         							//System.out.println("max_keyValeu_original:: "+max_keyValeu_original);
		   	         							    rate=RateRegex.RateRegrex(max_keyValeu_original, out);
		   	         							  //  System.out.println("rate:: "+rate);
//		   	         							    rateType=RateRegex.getRateVal(rate);
		   	         							   // lhmap.put(JsonKey, "Rate");
		   	         							   // lhmap.put("RateType", rateType);
//		   	         							    break;
		   	         							    
		   	         							   JSONObject dataAndRateJsonObj=CombineCharterer_Status_Rate.combine_Rate_Date(max_keyValeu_original);
		   	         							   String rateJsonObj="";
		   	         							   String dateJsonObj="";
		   	         							   
		   	         							   if( dataAndRateJsonObj!=null && dataAndRateJsonObj.length()!=0 ){
		   	         							        if(dataAndRateJsonObj.has("Rate")){
		   	         							        rateJsonObj= dataAndRateJsonObj.getString("Rate");
		   	         							       // System.out.println("rateJsonObj: "+rateJsonObj);
		   	         							        rateType=RateRegex.getRateVal(rateJsonObj);
		   	         							        }if(dataAndRateJsonObj.has("Date")){
		   	         							            dateJsonObj= dataAndRateJsonObj.getString("Date");
			   	         							        }
		   	         							    }
//		   	         							    if( !GmailMethods.isNullString(rate) ){
		   	         							 if( !GmailMethods.isNullString(rateJsonObj) ){
		   	         							    
		   	         							 if(valuemaxJson.equals("Rate")){
		   	         							    String dataRateValue=dataJSonObj.getString(JsonKey);
		   	         							    if( !GmailMethods.isNullString(dataRateValue) ){
		   	         							        // System.out.println("1:: "+dataJSonObj);
		   	         							     if( !valuemaxJson.equals("RateType") ){
		   	         								      int rateNotFoundPosition= newHeader.length();
		   	         								    //   headerJSonObj.put(String.valueOf(rateNotFoundPosition), "RateType");
		   	         								       newHeader.put(String.valueOf(rateNotFoundPosition), "RateType");
		   	         								       dataJSonObj.put(String.valueOf(rateNotFoundPosition), rateType);
		   	         							 }	 
		   	         							    }else{
		   	         							         dataJSonObj.put(JsonKey, rateJsonObj);
		   	         							      if( !valuemaxJson.equals("RateType") ){
		   	         							    	 int rateNotFoundPosition= newHeader.length();
		   	         								    //   headerJSonObj.put(String.valueOf(rateNotFoundPosition), "RateType");
		   	         								       newHeader.put(String.valueOf(rateNotFoundPosition), "RateType");
		   	         								       dataJSonObj.put(String.valueOf(rateNotFoundPosition), rateType);
		   	         							 }	   	         							         
		   	         							         
		   	         							    }
		   	         							 } else if( !valuemaxJson.equals("Rate") ){
		   	         							// System.out.println("rateJsonObj_inside: "+rateJsonObj);
		   	         							             int rateNotFoundPosition= newHeader.length();
		   	         								       // int rateNotFoundPositionData= dataJSonObj.length();
//		   	         								       headerJSonObj.put(String.valueOf(rateNotFoundPosition), "Rate");
		   	         								     //  headerJSonObj.put(String.valueOf(JsonKey), "Rate");
		   	         								       newHeader.put(String.valueOf(rateNotFoundPosition), "Rate");
		   	         								       //dataJSonObj.put(String.valueOf(rateNotFoundPosition), max_keyValeu_original);
		   	         								       dataJSonObj.put(String.valueOf(rateNotFoundPosition), rateJsonObj);
		   	         							 }
		   	         							 
		   	         							 if(valuemaxJson.equals("RateType")){
		   	         							     String dataRatetypeValue=dataJSonObj.getString(JsonKey);
		   	         							     if( !GmailMethods.isNullString(dataRatetypeValue) ){
		   	         							    	 
		   	         							     }else{
		   	         							        dataJSonObj.put(JsonKey, rateType);
		   	         							     }
		   	         							 }else if( !valuemaxJson.equals("RateType") ){
		   	         								       int rateNotFoundPosition= newHeader.length();
		   	         								    //   headerJSonObj.put(String.valueOf(rateNotFoundPosition), "RateType");
		   	         								       newHeader.put(String.valueOf(rateNotFoundPosition), "RateType");
		   	         								       dataJSonObj.put(String.valueOf(rateNotFoundPosition), rateType);
		   	         							 }
		   	         							    
		   	         							    }    // rate blank check
		   	         							    
//		    	         						}
		    	         					} if( valuemaxJson.contains("Comment") || valuemaxJson.contains("Comments") ){
		    	         						  //System.out.println("position_comment:: "+JsonKey);
		    	         						if(dataJSonObj.has(JsonKey)){
		    	         							String max_keyValeu_original=dataJSonObj.getString(JsonKey);
		   	         							   /* rate=RateRegex.RateRegrex(max_keyValeu_original, out);
		   	         							    rateType=RateRegex.getRateVal(rate);
		   	         							    
		   	         							   lhmap.put("Rate", rate);
		   	         							   lhmap.put("RateTypeComment", rateType);*/
		    	         							
		    	         							JSONObject dataAndRateJsonObj=CombineCharterer_Status_Rate.combine_Rate_Date(max_keyValeu_original);
			   	         							   String rateJsonObj="";
			   	         							   String dateJsonObj="";
			   	         							   
			   	         							   if( dataAndRateJsonObj!=null && dataAndRateJsonObj.length()!=0 ){
			   	         							        if(dataAndRateJsonObj.has("Rate")){
			   	         							        rateJsonObj= dataAndRateJsonObj.getString("Rate");
			   	         							        rateType=RateRegex.getRateVal(rateJsonObj);
			   	         							        }if(dataAndRateJsonObj.has("Date")){
			   	         							            dateJsonObj= dataAndRateJsonObj.getString("Date");
				   	         							        }
			   	         							    }
		    	         							
		   	         							   
//			   	         							  if( !GmailMethods.isNullString(rate) ){
					   	         							 if( !GmailMethods.isNullString(rateJsonObj) ){
		   	         							   
		   	         							if(valuemaxJson.equals("Comment") || valuemaxJson.equals("Comments")){
		   	         							if(valuemaxJson.equals("Rate")){
		   	         							    String dataRateValue=dataJSonObj.getString(JsonKey);
		   	         							    if( !GmailMethods.isNullString(dataRateValue) ){
		   	         							    	
		   	         							    }else{
		   	         							         dataJSonObj.put(JsonKey, rateJsonObj);
		   	         							    }
		   	         							 }else if( !valuemaxJson.equals("Rate") ){
		   	         								int rateNotFoundPosition= newHeader.length();
		   	         								       newHeader.put(String.valueOf(rateNotFoundPosition), "Rate");
		   	         								       dataJSonObj.put(String.valueOf(rateNotFoundPosition), rateJsonObj);
		   	         							 }
		   	         							
		   	         						if(valuemaxJson.equals("RateType")){
	  	         							     String dataRatetypeValue=dataJSonObj.getString(JsonKey);
	  	         							     if( !GmailMethods.isNullString(dataRatetypeValue) ){
	  	         							    	 
	  	         							     }else{
	  	         							        dataJSonObj.put(JsonKey, rateType);
	  	         							     }
	  	         							 }else if( !valuemaxJson.equals("RateType") ){
	  	         								int rateNotFoundPosition= newHeader.length();
	  	         								       newHeader.put(String.valueOf(rateNotFoundPosition), "RateType");
	  	         								       dataJSonObj.put(String.valueOf(rateNotFoundPosition), rateType);
	  	         							 }
		   	         							
		   	         								
		   	         							}
		   	         							   } // rate check here
		   	         							   
//		   	         							   break;
		    	         						}
		    	         					}// rate and ratetype
		    	         					
	    	         					} // else rate_position close
	    	         					
	    	         					// ...................... charetrer start here
	    	         					String Charterer="";
	    	         					if(valuemaxJson.contains("unknown")){
	    	         						//System.out.println("position_unknown:: "+JsonKey);
	    	         						if(dataJSonObj.has(JsonKey)){
	    	         							String max_keyValeu_original=dataJSonObj.getString(JsonKey);
	    	         							/*JSONObject chartereobj=ChartererUnknownAndCommentCheck.chartererData(max_keyValeu_original);*/
	    	         							JSONObject chartereobj=CombineCharterer_Status_Rate.chartererSplit(max_keyValeu_original);
	    	         							
	    	         							if(chartereobj!=null && chartereobj.length()!=0){
	    	         								//System.out.println("chartereobj: "+chartereobj);
	    	         								if(chartereobj.has("Charterer")){
	    	         									JSONObject chartererSolrjsonobj=chartereobj.getJSONObject("Charterer");

	    	        									if(chartererSolrjsonobj.has("Charterer")){
	    	        										Charterer=chartererSolrjsonobj.getString("Charterer");
	    	        										
	    	        										lhmap.put(JsonKey, "Charterer");
	    	        										
	    	        										if( !GmailMethods.isNullString(Charterer) ){
	    	        											 if(valuemaxJson.equals("Charterer")){
	    	        		   	         							    String dataRateValue=dataJSonObj.getString(JsonKey);
	    	        		   	         							    if( !GmailMethods.isNullString(dataRateValue) ){
	    	        		   	         							    	
	    	        		   	         							    }else{
	    	        		   	         							         dataJSonObj.put(JsonKey, Charterer);
	    	        		   	         							    }
	    	        		   	         							 }else if( !valuemaxJson.equals("Charterer") ){
	    	        		   	         								int rateNotFoundPosition= newHeader.length();
	    	        		   	         								      /* headerJSonObj.put(String.valueOf(rateNotFoundPosition), "Charterer");
	    	        		   	         								       dataJSonObj.put(String.valueOf(rateNotFoundPosition), Charterer);*/
	    	        		   	         								       newHeader.put(String.valueOf(rateNotFoundPosition), "Charterer");
    	        		   	         								           dataJSonObj.put(String.valueOf(rateNotFoundPosition), Charterer);
	    	        		   	         							 }
	    	        											
	    	        											
	    	        										} // charterer check here
//	    	        										break;
	    	        									}
	    	         								}
	    	         							}
	    	         						}
	    	         					} if( valuemaxJson.contains("Comment") || valuemaxJson.contains("Comments") ){
	    	         						//System.out.println("position_comment:: "+JsonKey);
	    	         						if(dataJSonObj.has(JsonKey)){
	    	         							String max_keyValeu_original=dataJSonObj.getString(JsonKey);
	    	         							/*JSONObject chartereobj=ChartererUnknownAndCommentCheck.chartererData(max_keyValeu_original);*/
	    	         							JSONObject chartereobj=CombineCharterer_Status_Rate.chartererSplit(max_keyValeu_original);
	    	         							if(chartereobj!=null && chartereobj.length()!=0){
	    	         								if(chartereobj.has("Charterer")){
	    	         									JSONObject chartererSolrjsonobj=chartereobj.getJSONObject("Charterer");

	    	        									if(chartererSolrjsonobj.has("Charterer")){
	    	        										Charterer=chartererSolrjsonobj.getString("Charterer");
	    	        										
	    	        										lhmap.put("ChartererComment", Charterer);
	    	        										
	    	        										if(valuemaxJson.equals("Comment") || valuemaxJson.equals("Comments")){
	    	        											if( !GmailMethods.isNullString(Charterer) ){
		    	        											 if(valuemaxJson.equals("Charterer")){
		    	        		   	         							    String dataRateValue=dataJSonObj.getString(JsonKey);
		    	        		   	         							    if( !GmailMethods.isNullString(dataRateValue) ){
		    	        		   	         							    	
		    	        		   	         							    }else{
		    	        		   	         							         dataJSonObj.put(JsonKey, Charterer);
		    	        		   	         							    }
		    	        		   	         							 }else if( !valuemaxJson.equals("Charterer") ){
		    	        		   	         								int rateNotFoundPosition= newHeader.length();
		    	        		   	         								       newHeader.put(String.valueOf(rateNotFoundPosition), "Charterer");
		    	        		   	         								       dataJSonObj.put(String.valueOf(rateNotFoundPosition), Charterer);
		    	        		   	         							 }
		    	        											
		    	        											
		    	        										} // charterer check here
	    	        										}
//	    	        										break;
	    	        									}
	    	         								}
	    	         							}
	    	         						}
	    	         						
	    	         					} // charterer
	    	         					
	    	         					//.....................status here
	    	         					
	    	         					String Status="";
	    	         					if(valuemaxJson.contains("unknown")){
	    	         						if(dataJSonObj.has(JsonKey)){
	    	         							String max_keyValeu_original=dataJSonObj.getString(JsonKey);
	    	         							/*JSONObject statusobj=StatusUnknownAndComment.SubjectData(max_keyValeu_original,out);*/
	    	         							JSONObject statusobj=CombineCharterer_Status_Rate.statusSplit(max_keyValeu_original, out);
	    	         							if(statusobj!=null && statusobj.length()!=0){
	    	         								if(statusobj.has("Status")){
	    	        										Status=statusobj.getString("Status");
	    	        										
	    	        										if( !GmailMethods.isNullString(Status) ){
	    	        											 if(valuemaxJson.equals("Status")){
	    	        		   	         							    String dataRateValue=dataJSonObj.getString(JsonKey);
	    	        		   	         							    if( !GmailMethods.isNullString(dataRateValue) ){
	    	        		   	         							    	
	    	        		   	         							    }else{
	    	        		   	         							         dataJSonObj.put(JsonKey, Status);
	    	        		   	         							    }
	    	        		   	         							 }else if( !valuemaxJson.equals("Status") ){
	    	        		   	         								int rateNotFoundPosition= newHeader.length();
	    	        		   	         								       newHeader.put(String.valueOf(rateNotFoundPosition), "Status");
    	        		   	         								           dataJSonObj.put(String.valueOf(rateNotFoundPosition), Status);
	    	        		   	         							 }
	    	        											
	    	        											
	    	        										} // Status check here
//	    	        										break;
	    	        									}
	    	         								}
	    	         							}
	    	         					} if( valuemaxJson.contains("Comment") || valuemaxJson.contains("Comments") ){
	    	         						//System.out.println("position_comment:: "+JsonKey);
	    	         						if(dataJSonObj.has(JsonKey)){
	    	         							String max_keyValeu_original=dataJSonObj.getString(JsonKey);
	    	         							JSONObject statusobj=StatusUnknownAndComment.SubjectData(max_keyValeu_original,out);
	    	         							if(statusobj!=null && statusobj.length()!=0){
	    	         								if(statusobj.has("Status")){
	    	        										Status=statusobj.getString("Status");
	    	        										
	    	        										if(valuemaxJson.equals("Comment") || valuemaxJson.equals("Comments")){
	    	        											if( !GmailMethods.isNullString(Status) ){
		    	        											 if(valuemaxJson.equals("Status")){
		    	        		   	         							    String dataRateValue=dataJSonObj.getString(JsonKey);
		    	        		   	         							    if( !GmailMethods.isNullString(dataRateValue) ){
		    	        		   	         							    	
		    	        		   	         							    }else{
		    	        		   	         							         dataJSonObj.put(JsonKey, Status);
		    	        		   	         							    }
		    	        		   	         							 }else if( !valuemaxJson.equals("Status") ){
		    	        		   	         								       int rateNotFoundPosition= newHeader.length();
		    	        		   	         								       newHeader.put(String.valueOf(rateNotFoundPosition), "Status");
		    	        		   	         								       dataJSonObj.put(String.valueOf(rateNotFoundPosition), Status);
		    	        		   	         							 }
		    	        											
		    	        											
		    	        										} // status check here
	    	        										}
//	    	        										break;
	    	        									}
	    	         								}
	    	         							}
	    	         						
	    	         					} // Status 
	    	         					
	    	         					
	    	         					// //.....................status close here
	    	         					
	    	         					
	    	         					//.....................Tc Unknown And Comments..............
	    	         					
	    	         					if(valuemaxJson.equals("Period")){
	    	         						String Port="";
	    	         						String dataRateValue=dataJSonObj.getString(JsonKey);
	    	         						if( !GmailMethods.isNullString(dataRateValue) ){
	    	         							
	    	         							if(valuemaxJson.contains("unknown")){
	    	         								if(dataJSonObj.has(JsonKey)){
	    	         									String max_keyValeu_original=dataJSonObj.getString(JsonKey);
	    	         									 JSONObject dataAndRateJsonObj=TcMethods.Timechartere_combine_Rate_Date(max_keyValeu_original);
	  		   	         							   String rateJsonObj="";
	  		   	         							   String dateJsonObj="";
	  		   	         							   
	  		   	         						 if( dataAndRateJsonObj!=null && dataAndRateJsonObj.length()!=0 ){
		   	         							        if(dataAndRateJsonObj.has("Rate")){
		   	         							            rateJsonObj= dataAndRateJsonObj.getString("Rate");
		   	         							        }if(dataAndRateJsonObj.has("Date")){
		   	         							            dateJsonObj= dataAndRateJsonObj.getString("Date");
			   	         							        }
		   	         							    }// dataAndRateJsonObj check
	  		   	         						 
	  		   	         					         if( !valuemaxJson.equals("Rate") ){
		   	         							            int rateNotFoundPosition= newHeader.length();
		   	         								       
		   	         								       newHeader.put(String.valueOf(rateNotFoundPosition), "Rate");
		   	         								       dataJSonObj.put(String.valueOf(rateNotFoundPosition), rateJsonObj);
		   	         							 } // rate check
	  		   	         					         
	  		   	         					   if( !valuemaxJson.equalsIgnoreCase("Port") ){
  	         							            int portNotFoundPosition= newHeader.length();
 	    	         							    JSONObject deliveryPlaceobj=QueryBoostForTitles.SubjectData(max_keyValeu_original,out);
 	    	         							if(deliveryPlaceobj!=null && deliveryPlaceobj.length()!=0){
 	    	         								if(deliveryPlaceobj.has("RepositionRegion")){
 	    	         									Port=deliveryPlaceobj.getString("RepositionRegion");
 	    	         									
 	    	         									if( !GmailMethods.isNullString(Port) ){
 	    	         										newHeader.put(String.valueOf(portNotFoundPosition), "Port");
 	      	         								        dataJSonObj.put(String.valueOf(portNotFoundPosition), Port);
 	    	         									}
 	    	         								}
 	    	         							}
  	         							            
  	         								       
  	         							 } // delivery place check
	  		   	         					   
	  		   	         					   if( !GmailMethods.isNullString(dateJsonObj) ){
	  		   	         						    int dateNotFoundPosition= newHeader.length();
	         								       
	         								       newHeader.put(String.valueOf(dateNotFoundPosition), "Date");
	         								       dataJSonObj.put(String.valueOf(dateNotFoundPosition), dateJsonObj);
	  		   	         						   
	  		   	         					   } // date 
	  		   	         					         
	  		   	         					   
	  		   	         							   
	    	         								} // has jsonkey check
	    	         								
	    	         							} // unknown check
	    	         							
	    	         							
	    	         							if(valuemaxJson.contains("Comment") || valuemaxJson.contains("Comments")){
	    	         								if(dataJSonObj.has(JsonKey)){
	    	         									String max_keyValeu_original=dataJSonObj.getString(JsonKey);
	    	         									 JSONObject dataAndRateJsonObj=TcMethods.Timechartere_combine_Rate_Date(max_keyValeu_original);
	  		   	         							   String rateJsonObj="";
	  		   	         							   String dateJsonObj="";
	  		   	         							   
	  		   	         						 if( dataAndRateJsonObj!=null && dataAndRateJsonObj.length()!=0 ){
		   	         							        if(dataAndRateJsonObj.has("Rate")){
		   	         							            rateJsonObj= dataAndRateJsonObj.getString("Rate");
		   	         							        }if(dataAndRateJsonObj.has("Date")){
		   	         							            dateJsonObj= dataAndRateJsonObj.getString("Date");
			   	         							        }
		   	         							    }// dataAndRateJsonObj check
	  		   	         						 
	  		   	         					         if( !valuemaxJson.equals("Rate") ){
		   	         							            int rateNotFoundPosition= newHeader.length();
		   	         								       
		   	         								       newHeader.put(String.valueOf(rateNotFoundPosition), "Rate");
		   	         								       dataJSonObj.put(String.valueOf(rateNotFoundPosition), rateJsonObj);
		   	         							 } // rate check
	  		   	         					         
	  		   	         					   if( !valuemaxJson.equalsIgnoreCase("Port") ){
  	         							            int portNotFoundPosition= newHeader.length();
 	    	         							    JSONObject deliveryPlaceobj=QueryBoostForTitles.SubjectData(max_keyValeu_original,out);
 	    	         							if(deliveryPlaceobj!=null && deliveryPlaceobj.length()!=0){
 	    	         								if(deliveryPlaceobj.has("RepositionRegion")){
 	    	         									Port=deliveryPlaceobj.getString("RepositionRegion");
 	    	         									
 	    	         									if( !GmailMethods.isNullString(Port) ){
 	    	         										newHeader.put(String.valueOf(portNotFoundPosition), "Port");
 	      	         								        dataJSonObj.put(String.valueOf(portNotFoundPosition), Port);
 	    	         									}
 	    	         								}
 	    	         							}
  	         							            
  	         								       
  	         							 } // delivery place check
	  		   	         					   
	  		   	         					   if( !GmailMethods.isNullString(dateJsonObj) ){
	  		   	         						    int dateNotFoundPosition= newHeader.length();
	         								       
	         								       newHeader.put(String.valueOf(dateNotFoundPosition), "Date");
	         								       dataJSonObj.put(String.valueOf(dateNotFoundPosition), dateJsonObj);
	  		   	         						   
	  		   	         					   } // date 
	  		   	         					         
	  		   	         					   
	  		   	         							   
	    	         								} // has jsonkey check
	    	         								
	    	         							} // comment check
	    	         							
	    	         							
	    	         							
	    	         						} // blank check period here
	    	         						
	    	         						
	    	         						
	    	         					} // period check here
	    	         					
	    	         					
	    	         					//............Tc Unknown And Comments end..................
	    	         					
	    	         					
	    	         					
	                    			} // while close
	                    			
	                    		 }//vesselName checkend 
    	         					 table_dataArray.getJSONObject(i).put("new_header_data", newHeader);
	                    		 } // data close
	                    		
	                    		 
	                    		
	                    		 
	                    		 
	                    	   } // for close 

	                		  
	                		  //..........................................................
	                		  
	                		 
	                		  
	                		  
	                		  //......................................................................
	                		  
//	                		  System.out.println("jsonStringInput:: "+jsonStringInput);
	                		  jsonString=jsonStringInput.toString();
	                		  
	                	 } // length check>0
					 }// table_data check
				} // jsonobject check
				
			}// while close table1 etc key
			
					
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return jsonString;
		
	}
	
	
	
	
	/*public static String SaveReportNewStructureChanged(PrintWriter out, String jsonString){

		try{
			
			JSONObject jsonStringInput=new JSONObject(jsonString);
			//System.out.println(jsonString);
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
	                	 
	                	 if(table_dataArray.length()>0){
	                		 LinkedHashMap<String, String> lhmap=new LinkedHashMap<>(); //
	                		  for(int i=0;i<table_dataArray.length();i++){
	                    		  tableinsideDataOnlyJonObj=  table_dataArray.getJSONObject(i);
	                    		// System.out.println(tableinsideDataOnlyJonObj);
	                    		  JSONObject dataJSonObj=null;
	                    		 if(tableinsideDataOnlyJonObj.has("data")){
	                    			dataJSonObj= tableinsideDataOnlyJonObj.getJSONObject("data");
	                    			 
	                    		 } // data close 
	                    		 JSONObject headerJSonObj=null;
	                    		
	                    		 if(tableinsideDataOnlyJonObj.has("new_header_data")){
	                    			headerJSonObj= tableinsideDataOnlyJonObj.getJSONObject("new_header_data");
	                    			 JSONObject newHeader=new JSONObject();
    	         					boolean vesselNameCheckBlank=false;

	                    			Iterator<String>itr= headerJSonObj.keys();
	                    			Iterator<String>tempitr= headerJSonObj.keys();

	                    			while(tempitr.hasNext()){
	                    				String JsonKey=tempitr.next();
	                    				String valuemaxJson=headerJSonObj.getString(JsonKey);

	                    				if(valuemaxJson.equalsIgnoreCase("VesselName") || valuemaxJson.equalsIgnoreCase("Vessel")){
	    	         						vesselNameCheckBlank=true;
//	    	         						System.out.println("check:: "+vesselNameCheckBlank);
	    	         						break;
	    	         					}
	    	         					
	                    			}
	                    			
    	         					if(vesselNameCheckBlank==true){
    	         						//System.out.println("true_VesselName");

	                    			while(itr.hasNext()){
	                    				String JsonKey=itr.next();
	                    				String valuemaxJson=headerJSonObj.getString(JsonKey);
	                    			//	System.out.println("keys:: "+JsonKey);
	                    				
	    	         					newHeader.put(JsonKey, valuemaxJson);
	    	         					
	    	         					String Rate_Position="";
	    	         					if(tabledataJsonObj.has("Rate_Position")){
	    	         						Rate_Position=tabledataJsonObj.getString("Rate_Position");
	    	         					}
	    	         					
	    	         					if( !GmailMethods.isNullString(Rate_Position) ){
	    	         						 newHeader.put(Rate_Position, "Rate");
	    	         						
	    	         					}else{
	    	         						if(valuemaxJson.equals("Rate")){
		    	         						 String dataRateValue=dataJSonObj.getString(JsonKey);
		    	         						if( !GmailMethods.isNullString(dataRateValue) ){
		    	         							  if( !valuemaxJson.equals("RateType") ){
		   	         								       int rateNotFoundPosition= headerJSonObj.length();
			   	         							        String rate=RateRegex.RateRegrex(dataRateValue, out);
		   	         								        String rateType=RateRegex.getRateVal(rate);
		   	         								       newHeader.put(String.valueOf(rateNotFoundPosition), "RateType");
		   	         								       dataJSonObj.put(String.valueOf(rateNotFoundPosition), rateType);
		   	         							 }	
		    	         						}
		    	         					}
		    	         					String rate="";
		    	         					String rateType="";
		    	         					if(valuemaxJson.contains("unknown")){
		    	         						//System.out.println("position_unknown:: "+JsonKey);
		    	         						
//		    	         						if(dataJSonObj.has(JsonKey)){
		    	         							String max_keyValeu_original=dataJSonObj.getString(JsonKey);
		    	         							//System.out.println("max_keyValeu_original:: "+max_keyValeu_original);
		   	         							    rate=RateRegex.RateRegrex(max_keyValeu_original, out);
		   	         							  //  System.out.println("rate:: "+rate);
		   	         							    rateType=RateRegex.getRateVal(rate);
		   	         							   // lhmap.put(JsonKey, "Rate");
		   	         							   // lhmap.put("RateType", rateType);
//		   	         							    break;
		   	         							    
		   	         							    if( !GmailMethods.isNullString(rate) ){
		   	         							    
		   	         							 if(valuemaxJson.equals("Rate")){
		   	         							    String dataRateValue=dataJSonObj.getString(JsonKey);
		   	         							    if( !GmailMethods.isNullString(dataRateValue) ){
		   	         							        // System.out.println("1:: "+dataJSonObj);
		   	         							     if( !valuemaxJson.equals("RateType") ){
		   	         								       int rateNotFoundPosition= headerJSonObj.length();
		   	         								    //   headerJSonObj.put(String.valueOf(rateNotFoundPosition), "RateType");
		   	         								       newHeader.put(String.valueOf(rateNotFoundPosition), "RateType");
		   	         								       dataJSonObj.put(String.valueOf(rateNotFoundPosition), rateType);
		   	         							 }	 
		   	         							    }else{
		   	         							         dataJSonObj.put(JsonKey, max_keyValeu_original);
		   	         							      if( !valuemaxJson.equals("RateType") ){
		   	         								       int rateNotFoundPosition= headerJSonObj.length();
		   	         								    //   headerJSonObj.put(String.valueOf(rateNotFoundPosition), "RateType");
		   	         								       newHeader.put(String.valueOf(rateNotFoundPosition), "RateType");
		   	         								       dataJSonObj.put(String.valueOf(rateNotFoundPosition), rateType);
		   	         							 }	   	         							         
		   	         							         
		   	         							    }
		   	         							 } else if( !valuemaxJson.equals("Rate") ){
		   	         								     //  int rateNotFoundPosition= headerJSonObj.length();
//		   	         								       headerJSonObj.put(String.valueOf(rateNotFoundPosition), "Rate");
		   	         								     //  headerJSonObj.put(String.valueOf(JsonKey), "Rate");
		   	         								       newHeader.put(String.valueOf(JsonKey), "Rate");
		   	         								     //  System.out.println(newHeader);
		   	         								       //dataJSonObj.put(String.valueOf(rateNotFoundPosition), max_keyValeu_original);
		   	         								       dataJSonObj.put(String.valueOf(JsonKey), max_keyValeu_original);
		   	         								     //  System.out.println(dataJSonObj);
		   	         							 }
		   	         							 
		   	         							 if(valuemaxJson.equals("RateType")){
		   	         							     String dataRatetypeValue=dataJSonObj.getString(JsonKey);
		   	         							     if( !GmailMethods.isNullString(dataRatetypeValue) ){
		   	         							    	 
		   	         							     }else{
		   	         							        dataJSonObj.put(JsonKey, rateType);
		   	         							     }
		   	         							 }else if( !valuemaxJson.equals("RateType") ){
		   	         								       int rateNotFoundPosition= headerJSonObj.length();
		   	         								    //   headerJSonObj.put(String.valueOf(rateNotFoundPosition), "RateType");
		   	         								       newHeader.put(String.valueOf(rateNotFoundPosition), "RateType");
		   	         								       dataJSonObj.put(String.valueOf(rateNotFoundPosition), rateType);
		   	         							 }
		   	         							    
		   	         							    }    // rate blank check
		   	         							    
//		    	         						}
		    	         					} if( valuemaxJson.contains("Comment") || valuemaxJson.contains("Comments") ){
		    	         						  //System.out.println("position_comment:: "+JsonKey);
		    	         						if(dataJSonObj.has(JsonKey)){
		    	         							String max_keyValeu_original=dataJSonObj.getString(JsonKey);
		   	         							    rate=RateRegex.RateRegrex(max_keyValeu_original, out);
		   	         							    rateType=RateRegex.getRateVal(rate);
		   	         							    
		   	         							   lhmap.put("Rate", rate);
		   	         							   lhmap.put("RateTypeComment", rateType);
		   	         							   
		   	         							   if( !GmailMethods.isNullString(rate) ){
		   	         							   
		   	         							if(valuemaxJson.equals("Comment") || valuemaxJson.equals("Comments")){
		   	         							if(valuemaxJson.equals("Rate")){
		   	         							    String dataRateValue=dataJSonObj.getString(JsonKey);
		   	         							    if( !GmailMethods.isNullString(dataRateValue) ){
		   	         							    	
		   	         							    }else{
		   	         							         dataJSonObj.put(JsonKey, rate);
		   	         							    }
		   	         							 }else if( !valuemaxJson.equals("Rate") ){
		   	         								       int rateNotFoundPosition= headerJSonObj.length();
		   	         								      newHeader.put(String.valueOf(rateNotFoundPosition), "Rate");
		   	         								       dataJSonObj.put(String.valueOf(rateNotFoundPosition), rate);
		   	         							 }
		   	         							
		   	         						if(valuemaxJson.equals("RateType")){
	  	         							     String dataRatetypeValue=dataJSonObj.getString(JsonKey);
	  	         							     if( !GmailMethods.isNullString(dataRatetypeValue) ){
	  	         							    	 
	  	         							     }else{
	  	         							        dataJSonObj.put(JsonKey, rateType);
	  	         							     }
	  	         							 }else if( !valuemaxJson.equals("RateType") ){
	  	         								       int rateNotFoundPosition= headerJSonObj.length();
	  	         								       newHeader.put(String.valueOf(rateNotFoundPosition), "RateType");
	  	         								       dataJSonObj.put(String.valueOf(rateNotFoundPosition), rateType);
	  	         							 }
		   	         							
		   	         								
		   	         							}
		   	         							   } // rate check here
		   	         							   
//		   	         							   break;
		    	         						}
		    	         					}// rate and ratetype
		    	         					
	    	         					} // else rate_position close
	    	         					
	    	         					// ...................... charetrer start here
	    	         					String Charterer="";
	    	         					if(valuemaxJson.contains("unknown")){
	    	         						//System.out.println("position_unknown:: "+JsonKey);
	    	         						if(dataJSonObj.has(JsonKey)){
	    	         							String max_keyValeu_original=dataJSonObj.getString(JsonKey);
	    	         							JSONObject chartereobj=ChartererUnknownAndCommentCheck.chartererData(max_keyValeu_original);
	    	         							if(chartereobj!=null && chartereobj.length()!=0){
	    	         								if(chartereobj.has("Charterer")){
	    	         									JSONObject chartererSolrjsonobj=chartereobj.getJSONObject("Charterer");

	    	        									if(chartererSolrjsonobj.has("Charterer")){
	    	        										Charterer=chartererSolrjsonobj.getString("Charterer");
	    	        										
	    	        										lhmap.put(JsonKey, "Charterer");
	    	        										
	    	        										if( !GmailMethods.isNullString(Charterer) ){
	    	        											 if(valuemaxJson.equals("Charterer")){
	    	        		   	         							    String dataRateValue=dataJSonObj.getString(JsonKey);
	    	        		   	         							    if( !GmailMethods.isNullString(dataRateValue) ){
	    	        		   	         							    	
	    	        		   	         							    }else{
	    	        		   	         							         dataJSonObj.put(JsonKey, Charterer);
	    	        		   	         							    }
	    	        		   	         							 }else if( !valuemaxJson.equals("Charterer") ){
	    	        		   	         								      // int rateNotFoundPosition= headerJSonObj.length();
	    	        		   	         								       headerJSonObj.put(String.valueOf(rateNotFoundPosition), "Charterer");
	    	        		   	         								       dataJSonObj.put(String.valueOf(rateNotFoundPosition), Charterer);
	    	        		   	         								newHeader.put(String.valueOf(JsonKey), "Charterer");
    	        		   	         								       dataJSonObj.put(String.valueOf(JsonKey), Charterer);
	    	        		   	         							 }
	    	        											
	    	        											
	    	        										} // charterer check here
//	    	        										break;
	    	        									}
	    	         								}
	    	         							}
	    	         						}
	    	         					} if( valuemaxJson.contains("Comment") || valuemaxJson.contains("Comments") ){
	    	         						//System.out.println("position_comment:: "+JsonKey);
	    	         						if(dataJSonObj.has(JsonKey)){
	    	         							String max_keyValeu_original=dataJSonObj.getString(JsonKey);
	    	         							JSONObject chartereobj=ChartererUnknownAndCommentCheck.chartererData(max_keyValeu_original);
	    	         							if(chartereobj!=null && chartereobj.length()!=0){
	    	         								if(chartereobj.has("Charterer")){
	    	         									JSONObject chartererSolrjsonobj=chartereobj.getJSONObject("Charterer");

	    	        									if(chartererSolrjsonobj.has("Charterer")){
	    	        										Charterer=chartererSolrjsonobj.getString("Charterer");
	    	        										
	    	        										lhmap.put("ChartererComment", Charterer);
	    	        										
	    	        										if(valuemaxJson.equals("Comment") || valuemaxJson.equals("Comments")){
	    	        											if( !GmailMethods.isNullString(Charterer) ){
		    	        											 if(valuemaxJson.equals("Charterer")){
		    	        		   	         							    String dataRateValue=dataJSonObj.getString(JsonKey);
		    	        		   	         							    if( !GmailMethods.isNullString(dataRateValue) ){
		    	        		   	         							    	
		    	        		   	         							    }else{
		    	        		   	         							         dataJSonObj.put(JsonKey, Charterer);
		    	        		   	         							    }
		    	        		   	         							 }else if( !valuemaxJson.equals("Charterer") ){
		    	        		   	         								       int rateNotFoundPosition= headerJSonObj.length();
		    	        		   	         								   newHeader.put(String.valueOf(rateNotFoundPosition), "Charterer");
		    	        		   	         								       dataJSonObj.put(String.valueOf(rateNotFoundPosition), Charterer);
		    	        		   	         							 }
		    	        											
		    	        											
		    	        										} // charterer check here
	    	        										}
//	    	        										break;
	    	        									}
	    	         								}
	    	         							}
	    	         						}
	    	         						
	    	         					} // charterer
	    	         					
	    	         					//.....................status here
	    	         					
	    	         					String Status="";
	    	         					if(valuemaxJson.contains("unknown")){
	    	         						if(dataJSonObj.has(JsonKey)){
	    	         							String max_keyValeu_original=dataJSonObj.getString(JsonKey);
	    	         							JSONObject statusobj=StatusUnknownAndComment.SubjectData(max_keyValeu_original,out);
	    	         							if(statusobj!=null && statusobj.length()!=0){
	    	         								if(statusobj.has("Status")){
	    	        										Status=statusobj.getString("Status");
	    	        										
	    	        										if( !GmailMethods.isNullString(Status) ){
	    	        											 if(valuemaxJson.equals("Status")){
	    	        		   	         							    String dataRateValue=dataJSonObj.getString(JsonKey);
	    	        		   	         							    if( !GmailMethods.isNullString(dataRateValue) ){
	    	        		   	         							    	
	    	        		   	         							    }else{
	    	        		   	         							         dataJSonObj.put(JsonKey, Status);
	    	        		   	         							    }
	    	        		   	         							 }else if( !valuemaxJson.equals("Status") ){
	    	        		   	         								   newHeader.put(String.valueOf(JsonKey), "Status");
    	        		   	         								       dataJSonObj.put(String.valueOf(JsonKey), Status);
	    	        		   	         							 }
	    	        											
	    	        											
	    	        										} // Status check here
//	    	        										break;
	    	        									}
	    	         								}
	    	         							}
	    	         					} if( valuemaxJson.contains("Comment") || valuemaxJson.contains("Comments") ){
	    	         						//System.out.println("position_comment:: "+JsonKey);
	    	         						if(dataJSonObj.has(JsonKey)){
	    	         							String max_keyValeu_original=dataJSonObj.getString(JsonKey);
	    	         							JSONObject statusobj=StatusUnknownAndComment.SubjectData(max_keyValeu_original,out);
	    	         							if(statusobj!=null && statusobj.length()!=0){
	    	         								if(statusobj.has("Status")){
	    	        										Status=statusobj.getString("Status");
	    	        										
	    	        										if(valuemaxJson.equals("Comment") || valuemaxJson.equals("Comments")){
	    	        											if( !GmailMethods.isNullString(Status) ){
		    	        											 if(valuemaxJson.equals("Status")){
		    	        		   	         							    String dataRateValue=dataJSonObj.getString(JsonKey);
		    	        		   	         							    if( !GmailMethods.isNullString(dataRateValue) ){
		    	        		   	         							    	
		    	        		   	         							    }else{
		    	        		   	         							         dataJSonObj.put(JsonKey, Status);
		    	        		   	         							    }
		    	        		   	         							 }else if( !valuemaxJson.equals("Status") ){
		    	        		   	         								       int rateNotFoundPosition= headerJSonObj.length();
		    	        		   	         								       newHeader.put(String.valueOf(rateNotFoundPosition), "Status");
		    	        		   	         								       dataJSonObj.put(String.valueOf(rateNotFoundPosition), Status);
		    	        		   	         							 }
		    	        											
		    	        											
		    	        										} // status check here
	    	        										}
//	    	        										break;
	    	        									}
	    	         								}
	    	         							}
	    	         						
	    	         					} // Status 
	    	         					
	    	         					
	    	         					// //.....................status close here
	    	         					
	    	         					
	                    			} // while close
	                    			
	                    		 }//vesselName checkend 
    	         					 table_dataArray.getJSONObject(i).put("new_header_data", newHeader);
	                    		 } // data close
	                    		
	                    		 
	                    		
	                    		 
	                    		 
	                    	   } // for close 

	                		  
	                		  //..........................................................
	                		  
	                		 
	                		  
	                		  
	                		  //......................................................................
	                		  
//	                		  System.out.println("jsonStringInput:: "+jsonStringInput);
	                		  jsonString=jsonStringInput.toString();
	                		  
	                	 } // length check>0
					 }// table_data check
				} // jsonobject check
				
			}// while close table1 etc key
			
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonString;
		
	}
	*/
	

	   
	
}
