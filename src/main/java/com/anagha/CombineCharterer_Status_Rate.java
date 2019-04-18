package com.anagha;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.readGmail.GmailMethods;
import com.reportinformationsystem.SaveReportDataClass;

public class CombineCharterer_Status_Rate {

	public static void main(String[] args) {
		String splitData="AG-SPORE 17/04 250K-W105 CSSA=SUBS";
		combineCharterer_Status_Rate(splitData);
	}
	
	
	public static void combineCharterer_Status_Rate( String splitData ){
		
		try {
			
			if(splitData.contains(" ")){
				System.out.println("Space Contains");
				String[] splited = splitData.split("\\s+");
				System.out.println("Space count:: "+splited.length);
				
				for(int i=0;i<splited.length;i++){
					String data=splited[i];
					//System.out.println(data);
					// call api for rate 
					
					if( !GmailMethods.isNullString(data) ){
						String rateApiResponse= ApiRateAndDate(data);
						boolean rateResponseCheck=SaveReportDataClass.isJSONValid(rateApiResponse);
						if(rateResponseCheck){
                           JSONArray rateArray=new JSONArray(rateApiResponse);
                           if( rateArray.length()>0 ){
                        	   System.out.println(rateArray);
                        	   for ( int j=0;j<rateArray.length(); j++){
                        		    JSONObject jsonObjRate=rateArray.getJSONObject(j);
                        		  
                        		    if( jsonObjRate.has("Regex_Type") ){
                        		    	String Regex_Type=jsonObjRate.getString("Regex_Type");
                        		    	System.out.println(Regex_Type);
                        		    	// Space contains
                        		    } // Regex_Type check here
                        		    
                        	   } // for loop issues
                               	   
                           } // length check here
							
						} // check isjsonvalid
						
					} // blank check
					
					// end rate here
					
				} // for split check here 
				
			}else{
				System.out.println("No Contains");
				
				
				
			} // no space contains
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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

