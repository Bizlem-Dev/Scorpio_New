package com.anagha;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.NewStructureScorpio.ChartererUnknownAndCommentCheck;
import com.mycode.StatusUnknownAndComment;
import com.readGmail.GmailMethods;
import com.reportinformationsystem.SaveReportDataClass;

public class CombineCharterer_Status_Rate {

	public static void main(String[] args) {
		String splitData="AG-SPORE 17/04 250K-W105 CSSA=SUBS fx";
		JSONObject data=statusSplit(splitData, null);
		System.out.println(data);
	}
	
	public static JSONObject chartererSplit( String splitData ){
		JSONObject chartereobj=new JSONObject();
		try {
			
			if(splitData.contains(" ")){
				String[] splited = splitData.split("\\s+");
				for(int i=0;i<splited.length;i++){
					String data=splited[i];
					if( !GmailMethods.isNullString(data) ){
						 chartereobj=ChartererUnknownAndCommentCheck.chartererData(data);
					}
				} // for loop
				
				
			}else{
				if( !GmailMethods.isNullString(splitData) ){
					 chartereobj=ChartererUnknownAndCommentCheck.chartererData(splitData);
				}
			} // else 
			
		} catch (Exception e) {
			return chartereobj;
		}
		return chartereobj;
	}
	
	public static JSONObject statusSplit( String splitData , PrintWriter out){
		JSONObject statusobj=new JSONObject();
		try {
			
			if(splitData.contains(" ")){
				String[] splited = splitData.split("\\s+");
				for(int i=0;i<splited.length;i++){
					String data=splited[i];
					if( !GmailMethods.isNullString(data) ){
						 statusobj=StatusUnknownAndComment.SubjectData(data,out);
					}
				} // for loop
				
				
			}else{
				if( !GmailMethods.isNullString(splitData) ){
					 statusobj=StatusUnknownAndComment.SubjectData(splitData,out);
				}
			} // else 
			
		} catch (Exception e) {
			return statusobj;
		}
		return statusobj;
	}
	
	
	public static JSONObject combine_Rate_Date( String splitData ){
		String s="";
		String realValueRateMax="";
		String realValueDateMax="";
		ArrayList<String>RealValueRate=new ArrayList<>();
		ArrayList<String>rateCombineAllList=new ArrayList<>();
		ArrayList<String>dateCombineAllList=new ArrayList<>();
		JSONObject dataAndRateJsonObj=new JSONObject();
		
		try {
			
			if(splitData.contains(" ")){
				//System.out.println("Space Contains");
				String[] splited = splitData.split("\\s+");
				//System.out.println("Space count:: "+splited.length);
				
				for(int i=0;i<splited.length;i++){
					String data=splited[i];
					// call api for rate 
					
					if( !GmailMethods.isNullString(data) ){
						String rateApiResponse= ApiRateAndDate(data);
						boolean rateResponseCheck=SaveReportDataClass.isJSONValid(rateApiResponse);
						if(rateResponseCheck){
                           JSONArray rateArray=new JSONArray(rateApiResponse);
//                           System.out.println(rateArray);
                           if( rateArray.length()>0 ){
                        	   for ( int j=0;j<rateArray.length(); j++){
                        		    JSONObject jsonObjRate=rateArray.getJSONObject(j);
                        		  
                        		    if( jsonObjRate.has("Regex_Type") ){
                        		    	String Regex_Type=jsonObjRate.getString("Regex_Type");
                        		    	
                        		    	if( Regex_Type.equalsIgnoreCase("Rate") ){
                        		    		RealValueRate.add(data);
                        		    	    JSONArray valueArray=null;
                        		    	if( jsonObjRate.has("value") ){
                        		    		valueArray=jsonObjRate.getJSONArray("value");
                        		    		
                        		    		String arrayValue=valueArray.get(0).toString();
                        		    		//System.out.println(arrayValue);
                        		    		rateCombineAllList.add(arrayValue);
                        		    	}
                        		    }// check rate here
                        		    	
                        		    	if( Regex_Type.equalsIgnoreCase("Date") ){
                        		    		dateCombineAllList.add(data);
                        		    	
                        		    }// check Date here
                        		    	
                        		    	
                        		    } // Regex_Type check here
                        		    
                        	   } // for loop issues
                               	   
                           } // length check here
							
                           
						} // check isjsonvalid
						
					} // blank check
					
					// end rate here
					
				} // for split check here 
				if( rateCombineAllList.size()>0 && !rateCombineAllList.isEmpty() && rateCombineAllList!=null){
					
                     s = Collections.max(rateCombineAllList);
//                    System.out.println("maxJsonValue:: "+s);
				} // value of json rate
                   if(RealValueRate.size()>0 && !RealValueRate.isEmpty() && RealValueRate!=null ){
					
                     realValueRateMax = Collections.max(RealValueRate);
                     dataAndRateJsonObj.put("Rate", realValueRateMax);
//                    System.out.println("realValueRateMax:: "+realValueRateMax);
				} // value of json rate
                   
                   if(dateCombineAllList.size()>0 && !dateCombineAllList.isEmpty() && dateCombineAllList!=null ){
   					
                       realValueDateMax = Collections.max(dateCombineAllList);
                       dataAndRateJsonObj.put("Date", realValueDateMax);
//                       System.out.println("realValueDateMax:: "+realValueDateMax);
  				} // value of json date
				
			}else{
//				System.out.println("No Contains"+splitData);
				if( !GmailMethods.isNullString(splitData) ){
					String rateApiResponse= ApiRateAndDate(splitData);
					boolean rateResponseCheck=SaveReportDataClass.isJSONValid(rateApiResponse);
					if(rateResponseCheck){
						JSONArray rateArray=new JSONArray(rateApiResponse);
                        if( rateArray.length()>0 ){
                        	 for ( int j=0;j<rateArray.length(); j++){
                        		 JSONObject jsonObjRate=rateArray.getJSONObject(j);
                       		  
                     		    if( jsonObjRate.has("Regex_Type") ){
                     		    	String Regex_Type=jsonObjRate.getString("Regex_Type");
                    		    	
                    		    	if( Regex_Type.equalsIgnoreCase("Rate") ){
                    		    		RealValueRate.add(splitData);
                    		    	} // rate check here
                    		    	
                    		    	if( Regex_Type.equalsIgnoreCase("Date") ){
                    		    		dateCombineAllList.add(splitData);
                    		    	
                    		    }// check Date here
                    		    	
                     		    } // regrex type close
                        	 }
                        }// for rate loop close here
                        if(RealValueRate.size()>0 && !RealValueRate.isEmpty() ){
        					
                            realValueRateMax = Collections.max(RealValueRate);
                            dataAndRateJsonObj.put("Rate", realValueRateMax);
                           // System.out.println("realValueRateMax_else:: "+realValueRateMax);
       				} // value of json rate
                        
                        if(dateCombineAllList.size()>0 && !dateCombineAllList.isEmpty() && dateCombineAllList!=null ){
           					
                            realValueDateMax = Collections.max(dateCombineAllList);
                            dataAndRateJsonObj.put("Date", realValueDateMax);
                           // System.out.println("realValueDateMax:: "+realValueDateMax);
       				} // value of json date
                        
					}
				}
				
				
			} // no space contains
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			s=null;
			realValueRateMax=null;
			RealValueRate.clear();
			rateCombineAllList.clear();
			realValueDateMax=null;
			dateCombineAllList.clear();
		}
		return dataAndRateJsonObj;
		
	}
	
	public static String ApiRateAndDate(String rateApi){
		String count="";
		try {
						URL obj = new URL("http://dev.bizlem.io:5030/date");
						HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
						postConnection.setRequestMethod("POST");
						postConnection.setRequestProperty("Content-Type", "application/json");
						postConnection.setDoOutput(true);
						OutputStream os = postConnection.getOutputStream();
						os.write(rateApi.getBytes());
						os.flush();
						os.close();
						int responseCode = postConnection.getResponseCode();
						//System.out.println("POST Response Code :  " + responseCode);
						//System.out.println("POST Response Message : " + postConnection.getResponseMessage());
//						if (responseCode == HttpURLConnection.HTTP_CREATED) { // success
						if (responseCode == HttpURLConnection.HTTP_OK) { // success
							BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
							String inputLine;
							StringBuffer response = new StringBuffer();
							while ((inputLine = in.readLine()) != null) {
								response.append(inputLine);
								count=response.toString();
							}
							in.close();
							// print result
//							System.out.println(response.toString());
						} else {
							System.out.println("POST NOT WORKED");
						}
					
			
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
		return count;
	}
}

