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
		String splitData="70 / WS86.75 UNIPEC"; 
		
		JSONObject data=combine_Rate_Date(splitData);
		System.out.println("data: "+data);
		/*if(splitData.contains(" ")){
			String[] splited = splitData.split("\\s+");
			for(int i=0;i<splited.length;i++){
				System.out.println("data: "+splited[i]);
			}
		}else{
			System.out.println("data_else: "+splitData);
		}*/
		
		/*JSONObject data=statusSplit(splitData, null);  //AG-SPORE 17/04 250K-W105 CSSA=SUBS fx
		System.out.println("data: "+data);*/
	}
	
	public static JSONObject chartererSplit( String splitData ){
		JSONObject chartereobjNew=new JSONObject();
		try {
			
			if(splitData.contains(" ")){
				String[] splited = splitData.split("\\s+");
				for(int i=0;i<splited.length;i++){
					String data=splited[i];
					if( !GmailMethods.isNullString(data) ){
						JSONObject chartereobj=ChartererUnknownAndCommentCheck.chartererData(data);
                         if(chartereobj!=null && chartereobj.length()>0 ){
                        	 chartereobjNew=chartereobj;
                         }
					}
				} // for loop
				
				 
			}else{
				if( !GmailMethods.isNullString(splitData) ){
					JSONObject chartereobj=ChartererUnknownAndCommentCheck.chartererData(splitData);
					 if(chartereobj!=null && chartereobj.length()>0 ){
						 chartereobjNew=chartereobj;
                     }
				}
			} // else 
			
		} catch (Exception e) {
			return chartereobjNew;
		}
		return chartereobjNew;
	}
	
	public static JSONObject statusSplit( String splitData , PrintWriter out){
		JSONObject statusobjNew=new JSONObject();
		try {
			
			if(splitData.contains(" ")){
				String[] splited = splitData.split("\\s+");
				for(int i=0;i<splited.length;i++){
					String data=splited[i];
					if( !GmailMethods.isNullString(data) ){
						JSONObject statusobj=StatusUnknownAndComment.SubjectData(data,out);
						 if(statusobj!=null && statusobj.length()>0 ){
							 statusobjNew=statusobj;
                         }
					}
				} // for loop
				
				
			}else{
				if( !GmailMethods.isNullString(splitData) ){
					JSONObject statusobj=StatusUnknownAndComment.SubjectData(splitData,out);
					if(statusobj!=null && statusobj.length()>0 ){
						 statusobjNew=statusobj;
                    }
				}
			} // else 
			
		} catch (Exception e) {
			return statusobjNew;
		}
		return statusobjNew;
	}
	
	
	public static JSONObject combine_Rate_Date( String splitData ){
		String realValueRateMax="";
		String realValueDateMax="";
		ArrayList<String>RealValueRate=new ArrayList<>();
		ArrayList<String>dateCombineAllList=new ArrayList<>();
		JSONObject dataAndRateJsonObj=new JSONObject();
		
		try {
			
			if(splitData.contains(" ")){
				//System.out.println("Space Contains");
				String[] splited = splitData.split("\\s+");
				//System.out.println("Space count:: "+splited.length);
				
				for(int i=0;i<splited.length;i++){
					String data=splited[i];
					//System.out.println("dataSplit: "+data);
					// call api for rate 
					
					if( !GmailMethods.isNullString(data) ){
						String rateApiResponse= ApiRateAndDate(data);
						//System.out.println("rateApiResponse: "+rateApiResponse);
						boolean rateResponseCheck=SaveReportDataClass.isJSONValid(rateApiResponse);
						if(rateResponseCheck){
                           JSONArray rateArray=new JSONArray(rateApiResponse);
//                           System.out.println(rateArray);
                           if( rateArray.length()>0 ){
                        	   for ( int j=0;j<rateArray.length(); j++){
                        		    JSONObject jsonObjRate=rateArray.getJSONObject(j);
                        		  
                        		    if( jsonObjRate.has("Regex_Type") ){
                        		    	String Regex_Type=jsonObjRate.getString("Regex_Type");
                        		    	if( !GmailMethods.isNullString(Regex_Type) ){
                        		    	if( Regex_Type.equalsIgnoreCase("Rate") ){
                        		    		RealValueRate.add(data);
                        		    	 
                        		    }// check rate here
                        		    	
                        		    	if( Regex_Type.equalsIgnoreCase("Date") ){
                        		    		dateCombineAllList.add(data);
                        		    	
                        		    }// check Date here
                        		    }// blank check
                        		    	
                        		    } // Regex_Type check here
                        		    
                        	   } // for loop issues
                               	   
                           } // length check here
							
                           
						} // check isjsonvalid
						
					} // blank check
					
					// end rate here
					
				} // for split check here 
				
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
                    		    	if( !GmailMethods.isNullString(Regex_Type) ){
                    		    	if( Regex_Type.equalsIgnoreCase("Rate") ){
                    		    		RealValueRate.add(splitData);
                    		    	} // rate check here
                    		    	
                    		    	if( Regex_Type.equalsIgnoreCase("Date") ){
                    		    		dateCombineAllList.add(splitData);
                    		    	
                    		    }// check Date here
                    		    	
                     		    }// check blank
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
			return dataAndRateJsonObj;
		}finally {
			realValueRateMax=null;
			RealValueRate.clear();
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
			return count;
		}
		return count;
	}
}

