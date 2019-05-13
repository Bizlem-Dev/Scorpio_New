package com.mycode;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.readGmail.GmailMethods;
import com.reportinformationsystem.SaveReportDataClass;
import com.subjectheaders.QueryBoostForTitles;

public class Methods {

	public static String ReportTypeAndFinalJson(String myjson, PrintWriter out, String subjectLinePass){
		String finalJsonString="";
		try {
			 JSONObject finaljsobj=new JSONObject();
			JSONObject jsonStringInput=new JSONObject(myjson);
			Iterator<String> jsonObjkeys=jsonStringInput.keys();
			while(jsonObjkeys.hasNext()){
				String keyjsonObjkeys=jsonObjkeys.next();
				Object jsonObjkeyValueString=jsonStringInput.get(keyjsonObjkeys);
				if (jsonObjkeyValueString instanceof JSONObject) {
					JSONArray jsonObjArray=new JSONArray();
					JSONObject tabledataJsonObj=null;
					tabledataJsonObj = (JSONObject)jsonObjkeyValueString;
					 if(tabledataJsonObj.has("table_data")){
						 JSONArray table_dataArray=tabledataJsonObj.getJSONArray("table_data");
	                	 JSONObject tableinsideDataOnlyJonObj=null;
	                	 if(table_dataArray.length()>0){
	                		 
	                		 for(int i=0;i<table_dataArray.length();i++){
	                			 tableinsideDataOnlyJonObj=  table_dataArray.getJSONObject(i);
	                			 JSONObject dataJSonObj=null;
	                    		 if(tableinsideDataOnlyJonObj.has("new_data")){ //data
	                    			dataJSonObj= tableinsideDataOnlyJonObj.getJSONObject("new_data");
	                    			 
	                    		 } else if(tableinsideDataOnlyJonObj.has("data")){ 
	                    			   dataJSonObj= tableinsideDataOnlyJonObj.getJSONObject("data");
	                        			 
	                      		 }
	                    		 JSONObject headerJSonObj=null;
	                    		 if(tableinsideDataOnlyJonObj.has("new_header_data")){
	                    			 headerJSonObj= tableinsideDataOnlyJonObj.getJSONObject("new_header_data");
	                    		 } // header close
	                    		 JSONObject objheader=null;
	                    		 if(tableinsideDataOnlyJonObj.has("objheader")){
	                    			 objheader= tableinsideDataOnlyJonObj.getJSONObject("objheader");
	                    		 } // objheader close
	                    		 
	                    		 
	                    		 JSONObject jsonObj=new JSONObject();
	                    		 if(headerJSonObj!=null){
	                    			 
	                    		 Iterator<String>itr= headerJSonObj.keys();
	                    		 
	                    		 while(itr.hasNext()){
	                    			 String JsonKey=itr.next();
	                    			 if(headerJSonObj.has(JsonKey)){
	                    			 String key=headerJSonObj.getString(JsonKey);
	                    			 if(dataJSonObj.has(JsonKey)){
	                    			 String value= dataJSonObj.getString(JsonKey);
	                    			 
	                    			 if(key.equalsIgnoreCase("Vessel")){
	                    				 jsonObj.put("VesselName", value);
	                    			 }else{
	                    				 jsonObj.put(key, value);
	                    			 }
	                    			 
	                    			if(objheader!=null){
	                    				JSONArray h=new JSONArray();
	                    				h.put(objheader);
	                    				jsonObj.put("objheader", h);
	                    				
	                    			}
	                    			
	                    			 }

	                    			 }
	                    			 
	                    		 } // while keyheader dyanamic 
	                    		 
	                    		 jsonObjArray.put(jsonObj);
	                		 }else{
	                			 if(tableinsideDataOnlyJonObj.has("header")){
	                    			 headerJSonObj= tableinsideDataOnlyJonObj.getJSONObject("header");
	                    			 Iterator<String>itr2= headerJSonObj.keys();
	                    			 if(headerJSonObj!=null){
	                    				 while(itr2.hasNext()){
	    	                    			 String JsonKey=itr2.next();
	    	                    			 if(headerJSonObj.has(JsonKey)){
	    	                    			 String key=headerJSonObj.getString(JsonKey);
	    	                    			 if(dataJSonObj.has(JsonKey)){
	    	                    			 String value= dataJSonObj.getString(JsonKey);
	    	                    			 
	    	                    			
	    	                    			  jsonObj.put(key, value);
	    	                    			
	    	                    			 }

	    	                    			 }
	    	                    			 
	    	                    		 } // while keyheader dyanamic
	                    				 jsonObjArray.put(jsonObj);
	                    			 }
	                    		 }
	                		 }
	                		 } // for loop close
	                		
	                	 } // length check tablearray
	                	 if(jsonObjArray.length()>0){
							 JSONObject jsotable_data=new JSONObject();
	            			 
	            			// jsonObjArray=MethodJsonOnlyInsert.cargoQtyCheckFromNUmeric(jsonObjArray);
	            			 jsotable_data.put("table_data", jsonObjArray);
	            			 
	            			JSONArray header_data= checkHeaderDataAndSubjectData(jsonObjArray, tabledataJsonObj, out,subjectLinePass);
	            			if( header_data.length()>0){
	            			    jsotable_data.put("header_data", header_data);
	            			}
	            			
	            			 finaljsobj.put(keyjsonObjkeys, jsotable_data);
	            			 finalJsonString= finaljsobj.toString();
						 }
					 } // table_data has check
					 
					 
					 
					 
				} // jsonobj check
			} // while close dyanamic table1 etc
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalJsonString;
	}
	
	public static JSONArray checkHeaderDataAndSubjectData(JSONArray jsonObjArray, JSONObject tabledataJsonObj , PrintWriter out, String subjectLinePass){
		JSONArray headerFinalArray=new JSONArray();
		try {
			
			//System.out.println(jsonObjArray);
			if(jsonObjArray.length()>0){
				String RepositionRegion="";
				String CargoType="";
				for(int i=0;i<jsonObjArray.length();i++){
					JSONObject jsonObj=jsonObjArray.getJSONObject(i);
				
					if(jsonObj.has("RepositionRegion")){
						RepositionRegion=jsonObj.getString("RepositionRegion");
					}
					if(jsonObj.has("CargoType")){
						CargoType=jsonObj.getString("CargoType");
					}
					
					
				} // for loop close
				
				//..........................Header data
				
				JSONArray header_data=null;
				String cargoTypeWord="";
				String RGWord="";
				String VTWord="";
				String StatusWord="";
				String RTWord="";
				 ArrayList portlist=new ArrayList<>();
				
       		 if( tabledataJsonObj.has("header_data") ){
       			 header_data=tabledataJsonObj.getJSONArray("header_data");
       			// System.out.println("header_data:: "+header_data);
       			 
       			 for(int i=0;i<header_data.length();i++){
       				JSONObject headerJsonObj=header_data.getJSONObject(i);
       				JSONArray data=null;
       				if( headerJsonObj.has("new_data") ){
       					 data=headerJsonObj.getJSONArray("new_data");
       				}else if( headerJsonObj.has("data") ){
       	      			   data= headerJsonObj.getJSONArray("data");
       				}
       				if( data!=null && data.length()>0 ){
       	      			 
       					//System.out.println(data);
       					for(int j=0;j<data.length();j++){
       						JSONObject dataArrayObj=data.getJSONObject(j);
       						String url="";
       	       				if(dataArrayObj.has("url")){
       	       					url=dataArrayObj.getString("url");
       	       					
       	       					if(url.equals("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type")){
       	       					  
       	       					   
       	       					   if(dataArrayObj.has("word")){
       	       						  cargoTypeWord=dataArrayObj.getString("word");
       	       					     // System.out.println("cargoTypeWord:: "+cargoTypeWord);
       	       					   }
       	       					   
       	       					}else if(url.equals("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#CargoType")){
     	       					  
    	       					   
    	       					   if(dataArrayObj.has("word")){
    	       						  cargoTypeWord=dataArrayObj.getString("word");
    	       					     // System.out.println("cargoTypeWord:: "+cargoTypeWord);
    	       					   }
    	       					   
    	       					}// CT
       	       					

       	       					if(url.equals("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion")){
       	       					  
       	       					   
       	       					   if(dataArrayObj.has("word")){
       	       						  RGWord=dataArrayObj.getString("word");
       	       					     // System.out.println("RGWord:: "+RGWord);
       	       					   }
       	       					   
       	       					} // RG
       	       					
       	       					
       	       				if(url.equals("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType")){
     	       					  
    	       					   
    	       					   if(dataArrayObj.has("word")){
    	       						  VTWord=dataArrayObj.getString("word");
    	       					  //    System.out.println("VTWord:: "+VTWord);
    	       					   }
    	       					   
    	       					} // VT
       	       			if(url.equals("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status")){
	       					  
	       					   
	       					   if(dataArrayObj.has("word")){
	       						  StatusWord=dataArrayObj.getString("word");
	       					     // System.out.println("StatusWord:: "+StatusWord);
	       					   }
	       					   
	       					} // Status
       	       			
       	       		if(url.equals("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType")){
     					  
    					   if(dataArrayObj.has("word")){
    						  RTWord=dataArrayObj.getString("word");
    						  String resp=ReportTypeSynomesCheckSolr.fetchsolrdata(RTWord);
    						 boolean reportCheck= SaveReportDataClass.isJSONValid(resp);
    						 if(reportCheck){
    							 RTWord= ReportTypeSynomesCheckSolr.parsesolrresp(resp);
    							// System.out.println("RTWord:: "+RTWord);
    						 }
    					      
    					   }
    					   
    					} // ReportType
       	       		
       	       	if(url.equals("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port")){
					  
					   if(dataArrayObj.has("word")){
						  String portWord=dataArrayObj.getString("word");
						//  System.out.println("por:: "+portWord);
						  if( portWord.toLowerCase().equals("march") || portWord.toLowerCase().equals("best") || portWord.toLowerCase().equals("grey") || portWord.toLowerCase().equals("none") || portWord.toLowerCase().equals("braemer") 
								  || portWord.toLowerCase().equals("clarkson") || portWord.toLowerCase().equals("or") || portWord.toLowerCase().equals("dry")
								  ){
							  
						  }else{
							 // System.out.println("after removed:: "+portWord);
							   portlist.add(portWord);
						  }
					   }
					   
					} // port close
       	       		
       	       				
       	       					
       	       				}
       	       				
       					} // for data close
       				}
       			 } // for headerdata close
       		 }
       		 
       		//..........................Header data close
       		 
       		 //......................subject header Parse
       		 
       		JSONObject subjectLineData=QueryBoostForTitles.SubjectData(subjectLinePass, out);
       		
       		String RepositionRegionSubject="";
       		String CargoTypeSubject="";
       		String VesselTypeSubject="";
       		String ETABasisSubject="";
       		if(subjectLineData!=null){
       		if(subjectLineData.has("RepositionRegion")){
       			RepositionRegionSubject=subjectLineData.getString("RepositionRegion");
       		}if(subjectLineData.has("CargoType")){
       			CargoTypeSubject=subjectLineData.getString("CargoType");
       			
       		}if(subjectLineData.has("VesselType")){
       			VesselTypeSubject=subjectLineData.getString("VesselType");
       			
       		}if(subjectLineData.has("ETABasis")){
       			ETABasisSubject=subjectLineData.getString("ETABasis");
       			
       		}
       		}// null check
       		 
       		 //......................subject header Parse close
       		 
				
				JSONObject JsonCheck=new JSONObject();
				if( !GmailMethods.isNullString(RepositionRegionSubject) ){   // subject json
					JsonCheck.put("RepositionRegion", RepositionRegionSubject);
				
				}else{
					if( !GmailMethods.isNullString(RGWord) ){ // header json
						JsonCheck.put("RepositionRegion", RGWord);
					}else{
						if( portlist!=null && !portlist.isEmpty() && portlist.size()!=0){
						System.out.println("portlist:: "+portlist +"sizeport:: "+portlist.size());
						if(portlist.size()>1){
							System.out.println("true");
							/*String port=portlist.get(portlist.size()-1).toString();
							String port1=portlist.get(portlist.size()-2).toString();*/
							
							String port=portlist.get(0).toString();
							String port1=portlist.get(1).toString();
							
							if( !GmailMethods.isNullString(port)  && !GmailMethods.isNullString(port1) ){
								System.out.println("port1:: "+port +"port2:: "+port1);
								JsonCheck.put("RepositionRegion", port+"/"+port1);
							}
						}else{
							String port=portlist.get(portlist.size()-1).toString();
							if( !GmailMethods.isNullString(port) ){
								JsonCheck.put("RepositionRegion", port);
							}
							
						}
						}// arralist port check null
						else{
						if( !GmailMethods.isNullString(RepositionRegion) ){  // table json
							JsonCheck.put("RepositionRegion", RepositionRegion);
						}
					}
					}
					
					
				} // Rg close
				
				/*if( !GmailMethods.isNullString(RepositionRegion) ){   // table json
					JsonCheck.put("RepositionRegion", RepositionRegion);
				
				}else{
					if( !GmailMethods.isNullString(RGWord) ){ // header json
						JsonCheck.put("RepositionRegion", RGWord);
					}else{
						if( !GmailMethods.isNullString(RepositionRegionSubject) ){  // subject json
							JsonCheck.put("RepositionRegion", RepositionRegionSubject);
						}
					}
					
					
				} // Rg close
*/				
				if( !GmailMethods.isNullString(CargoType) ){
					 JsonCheck.put("CargoType", CargoType); // table json
				}else {
					if( !GmailMethods.isNullString(cargoTypeWord) ){
					     JsonCheck.put("CargoType", cargoTypeWord); // header json
					}else{
						if( !GmailMethods.isNullString(CargoTypeSubject) ){
							JsonCheck.put("CargoType", CargoTypeSubject); // subject json
						}
					}
					
				} // else   // cargotype close here
				
					if( !GmailMethods.isNullString(VTWord) ){
					     JsonCheck.put("VesselType", VTWord); // header json
					}else{
						if( !GmailMethods.isNullString(VesselTypeSubject) ){
							JsonCheck.put("VesselType", VesselTypeSubject); // subject json
						}
					
				} // else   // VesselType close here
				
				
				JsonCheck.put("ETABasis", ETABasisSubject); // subject json  
				JsonCheck.put("Status", StatusWord); // header json 
				JsonCheck.put("ReportType", RTWord);  // header json
				
				headerFinalArray.put(JsonCheck);
				
			} // length  jsonarray check
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return headerFinalArray;
	}
	
}
