package com.mycode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.anagha.CombineCharterer_Status_Rate;
import com.readGmail.GmailMethods;
import com.reportinformationsystem.SaveReportDataClass;

public class TcMethods {

	public static JSONObject delivery_Date_Redelivery_RedeliveryPlace(HashMap<String, String> g){
		JSONObject countObj=new JSONObject();
		try {
			ArrayList<String> date=new ArrayList<>(); 
			ArrayList<String> port=new ArrayList<>();
			
			 for (Map.Entry<String, String> entry : g.entrySet())
			  {
  				
  				if(entry.getValue().equalsIgnoreCase("Date")){

  					date.add(entry.getKey());
  					
  				}if(entry.getValue().equalsIgnoreCase("Port")){
  					port.add(entry.getKey());
  				}
  			
			  } // map for loop close
			 
			 
			 if(date!=null && date.size()>0){
				 
				 if(Collections.min(date).equals(Collections.max(date))){
					 countObj.put("Delivery", Collections.min(date));
				 }else{
					 countObj.put("Delivery", Collections.min(date));
					 countObj.put("Redelivery", Collections.max(date));
					 
					for( int i=0; i<date.size();i++){
						String data=date.get(i);
						
						if( data.equals(Collections.min(date)) || data.equals(Collections.max(date))  ){
							
						}
						else {
							 countObj.put("Date", data);
						}
					} // for loop
					 
				 }
				 
			 }if(port!=null && port.size()>0){
				 
				 if(Collections.min(port).equals(Collections.max(port))){
					 countObj.put("DeliveryPlace", Collections.min(port));
				 }
				 
				 
			 }
			 
    			 
			
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return countObj;
	}
	
	public static JSONObject Timechartere_combine_Rate_Date( String splitData ){
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
						String rateApiResponse= CombineCharterer_Status_Rate.ApiRateAndDate(data);
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
					String rateApiResponse= CombineCharterer_Status_Rate.ApiRateAndDate(splitData);
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

	
}
