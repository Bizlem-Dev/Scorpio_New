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


public class SaveReportNewRowWisePositionCheck_changed {

	public static void main(String[] args) {
    
		String jsonString=MethodJsonOnlyInsert.jsonInpput();
		System.out.println(jsonString);
		
      String data=SaveReportNewRowWisePositionCheck_changed.SaveReportNewStructureChanged(null, jsonString);
      System.out.println("newJson:: "+data);
    /* boolean datacheck= SaveReportDataClass.isJSONValid(data);
     if(datacheck){
    	 String subjectdata="CPP_MR_BSS_SPORE_35-55_DWT_TONNAGE_LIST_20.03.2019";
    	 subjectdata=subjectdata.replaceAll("_", " ");
      String finaldata= ReportTypeIdentify.ReportTypeAndFinalJson(data, null, "");
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
     }*/
    
      
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
		   	         							        System.out.println("rateJsonObj: "+rateJsonObj);
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
		   	         								       int rateNotFoundPosition= headerJSonObj.length();
		   	         								    //   headerJSonObj.put(String.valueOf(rateNotFoundPosition), "RateType");
		   	         								       newHeader.put(String.valueOf(rateNotFoundPosition), "RateType");
		   	         								       dataJSonObj.put(String.valueOf(rateNotFoundPosition), rateType);
		   	         							 }	 
		   	         							    }else{
		   	         							         dataJSonObj.put(JsonKey, rateJsonObj);
		   	         							      if( !valuemaxJson.equals("RateType") ){
		   	         								       int rateNotFoundPosition= headerJSonObj.length();
		   	         								    //   headerJSonObj.put(String.valueOf(rateNotFoundPosition), "RateType");
		   	         								       newHeader.put(String.valueOf(rateNotFoundPosition), "RateType");
		   	         								       dataJSonObj.put(String.valueOf(rateNotFoundPosition), rateType);
		   	         							 }	   	         							         
		   	         							         
		   	         							    }
		   	         							 } else if( !valuemaxJson.equals("Rate") ){
		   	         							 System.out.println("rateJsonObj_inside: "+rateJsonObj);
		   	         								       int rateNotFoundPosition= headerJSonObj.length();
		   	         								        int rateNotFoundPositionData= dataJSonObj.length();
//		   	         								       headerJSonObj.put(String.valueOf(rateNotFoundPosition), "Rate");
		   	         								     //  headerJSonObj.put(String.valueOf(JsonKey), "Rate");
		   	         								       newHeader.put(String.valueOf(String.valueOf(rateNotFoundPosition)), "Rate");
		   	         								       System.out.println(newHeader);
		   	         								       //dataJSonObj.put(String.valueOf(rateNotFoundPosition), max_keyValeu_original);
		   	         								       dataJSonObj.put(String.valueOf(rateNotFoundPositionData), rateJsonObj);
		   	         								       System.out.println(dataJSonObj);
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
		   	         								       int rateNotFoundPosition= headerJSonObj.length();
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
	    	         							/*JSONObject chartereobj=ChartererUnknownAndCommentCheck.chartererData(max_keyValeu_original);*/
	    	         							JSONObject chartereobj=CombineCharterer_Status_Rate.chartererSplit(max_keyValeu_original);
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
	    	        		   	         								       int rateNotFoundPosition= headerJSonObj.length();
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
	    	        		   	         								       int rateNotFoundPosition= headerJSonObj.length();
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
	
	

	   
	
}
