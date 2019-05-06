package com.mycode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jcr.Session;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.abhishek.ErrorNodeReportwise;
import com.readGmail.GmailMethods;
import com.reportinformationsystem.SaveReportDataClass;

public class ReportTypeCorrection {

	public static void main(String[] args) {


		// float numbers 
		double x = 51.741; 
		double y=52.0;
		// find the closest int for these floats 
		System.out.println(Math.ceil(x)); 
		boolean flag = ((Math.ceil(x)- y) == 0.0)?true:false;
		System.out.println(flag);
		String pattern = "[^a-zA-Z.//$//?//_//|]";
		String unVal="";
				Pattern pattern1 = Pattern.compile(pattern);
				Matcher matcher = pattern1.matcher("Abcd");
               
                
				while (matcher.find()) {
					unVal+=matcher.group();
                }	
				System.out.println("Value Passed is "+unVal);
			
	}

	public static boolean reportCorrectionMethod(String vesselNamePass, JSONObject allReportJsonArrayInsideJSonobject, PrintWriter out, Session session, String emailUrl, String reportcomesFrom, String data, String timestampDateAndTime){

		boolean reportChange = false;
		try {
			SaveReportDataClass SRDC =new SaveReportDataClass();
			String returnVesselName="";
			if(allReportJsonArrayInsideJSonobject.has("VesselName") || allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselName")){
				String VesselNameString= "";

				if(allReportJsonArrayInsideJSonobject.has("VesselName")){
					VesselNameString =	allReportJsonArrayInsideJSonobject.getString("VesselName");

				}
				if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselName")){
					VesselNameString =	allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselName");
				}
				returnVesselName = SaveReportDataClass.urlValue(VesselNameString);

			}

			if(!GmailMethods.isNullString(returnVesselName)){
				String vesselPropertiesString=vesselPropertiesDetailsScript(vesselNamePass);
				boolean checkvessel= SaveReportDataClass.isJSONValid(vesselPropertiesString);
				if(checkvessel){
					System.out.println(vesselPropertiesString);
					JSONObject vesselObj=new JSONObject(vesselPropertiesString);

					//String built="";
					double cubics = 0.0;
					double dwt= 0.0;
					double loa = 0.0;
					//"built":"2015","cubics":"51735","dwt":"49999","ice":"No","loa":"183"
					/*if(vesselObj.has("built")){
						built=vesselObj.getString("built");
					}*/
					if(vesselObj.has("cubics")){
						cubics=Double.parseDouble(vesselObj.getString("cubics"))/1000;
					}
					if(vesselObj.has("dwt")){
						dwt=Double.parseDouble(vesselObj.getString("dwt"))/1000;
					}
					if(vesselObj.has("loa")){
						loa=Double.parseDouble(vesselObj.getString("loa"));
					}
					String pattern = "[^a-zA-Z.//$//?//_//|]";
					String unVal="";
						for(int cargo_count=0;cargo_count<=25;cargo_count++){
							
							if(allReportJsonArrayInsideJSonobject.has("unknown_"+cargo_count)){
                            
							Pattern pattern1 = Pattern.compile(pattern);
							Matcher matcher = pattern1.matcher(allReportJsonArrayInsideJSonobject.getString("unknown_"+cargo_count));
                           
                            
							while (matcher.find()) {
								unVal+=matcher.group();
		                    }	
							if(!GmailMethods.isNullString(unVal)){
								boolean cubicflag = ((Math.ceil(Double.parseDouble(unVal))- cubics) == 0.0)?true:false;
								if(cubicflag){
									continue;
								}
								boolean dwtflag = ((Math.ceil(Double.parseDouble(unVal))- dwt) == 0.0)?true:false;
								if(dwtflag){
									continue;
								}
								boolean loaflag = ((Math.ceil(Double.parseDouble(unVal))- loa) == 0.0)?true:false;
								if(loaflag){
									continue;
								}
								boolean rateflag = (!GmailMethods.isNullString(getRateVal(allReportJsonArrayInsideJSonobject.getString("unknown_"+cargo_count))));
							if(rateflag){
								reportChange = true;
								break;
							}
							}								
						}else if(allReportJsonArrayInsideJSonobject.has("unknown"+cargo_count)){
							Pattern pattern1 = Pattern.compile(pattern);
							Matcher matcher = pattern1.matcher(allReportJsonArrayInsideJSonobject.getString("unknown"+cargo_count));
                           
                            
							while (matcher.find()) {
								unVal+=matcher.group();
		                    }	
							if(!GmailMethods.isNullString(unVal)){
								boolean cubicflag = ((Math.ceil(Double.parseDouble(unVal))- cubics) == 0.0)?true:false;
								if(cubicflag){
									continue;
								}
								boolean dwtflag = ((Math.ceil(Double.parseDouble(unVal))- dwt) == 0.0)?true:false;
								if(dwtflag){
									continue;
								}
								boolean loaflag = ((Math.ceil(Double.parseDouble(unVal))- loa) == 0.0)?true:false;
								if(loaflag){
									continue;
								}
								boolean rateflag = (!GmailMethods.isNullString(getRateVal(allReportJsonArrayInsideJSonobject.getString("unknown"+cargo_count))));
							if(rateflag){
								reportChange = true;
								break;
							}
							}		
						}
							
							
						}
						
					
						/*if(vesselObj.has("error")){
							out.println("vesselName does not have in master with properties");
							SRDC.callCheckBlankNodeError(out, session, emailUrl, reportcomesFrom, data, allReportJsonArrayInsideJSonobject.toString(), timestampDateAndTime);
						}*/


				} // jsonValid Check close

			} else{
				out.println("vesselName if blank in main json from pallavi code");
				ErrorNodeReportwise.callCheckBlankNodeError(out, session, emailUrl, reportcomesFrom, data, allReportJsonArrayInsideJSonobject.toString(), timestampDateAndTime);
				// blank check close
			}





		} catch (Exception e) {

		}
		return reportChange;
	}

	public static String vesselPropertiesDetailsScript(String vesselNamePass){

		StringBuffer response1=null;

		try {

			String url = "";
			url="https://dev.bizlem.io:8082/scorpio/checkVesselDetails?vesselName="+vesselNamePass;

			url = url.replace(" ", "%20");

			URL url1 = new URL(url);
			HttpURLConnection con = (HttpURLConnection) url1.openConnection();
			//			int responseCode = con.getResponseCode();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			response1 = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response1.append(inputLine);
			}
			in.close();

		} catch (Exception e) {

		}

		return response1.toString();

	}

	private static String getRateVal(String test) {
		String regexAlphOnly ="[^0-9.-//$//?//|//_]";

		Pattern pattern = Pattern.compile(regexAlphOnly);
		String regexNumOnly ="[^a-zA-Z.//$//?//|//_]";

		Pattern pattern1 = Pattern.compile(regexNumOnly);
		String rateType="";
		String rateTypeVal=null;
		String rate="";
		String []arr= test.split("\\-|/", -1);
		// System.out.println(arr.length);
		boolean rateTypeFlag= false;
		for (String name:arr){
			//System.out.println("Name is "+name);
			Matcher matcher = pattern1.matcher(name);

			while (matcher.find()) {
				rate+=matcher.group();

			}

			Matcher matcher1 = pattern.matcher(name);
			while (matcher1.find()) {
				rateType+=matcher1.group();

			}
			// System.out.println("Rate Type is :: "+rateType);
			// System.out.println("Rate is "+rate);
			if(!GmailMethods.isNullString(rateType)){
				switch(rateType.toUpperCase().trim())
				{

				case "K":
				case "M":{
					if(rateTypeFlag && !(GmailMethods.isNullString(rateTypeVal))){
						rateTypeVal = rateTypeVal+"-" + "LUMPSUM";
					}else{
						rateTypeVal="LUMPSUM";
					}

					break;
				}

				case "S":	
				case "W":
				case "WS":{
					if(rateTypeFlag && !(GmailMethods.isNullString(rateTypeVal))){
						rateTypeVal = rateTypeVal+"-" + "WS";
					}else{
						rateTypeVal="WS";
					}
					break;
				}

				case "RNR":
				{ 
					if(rateTypeFlag && !(GmailMethods.isNullString(rateTypeVal))){
						rateTypeVal = rateTypeVal+"-" + "RNR";
					}else{
						rateTypeVal="RNR";
					}
					break; 
				}
				/*default:{
					if(rateTypeFlag && !(GmailMethods.isNullString(rateTypeVal))){
						rateTypeVal = rateTypeVal+"-" + "WS";
					}else{
						rateTypeVal="WS";
					}
					break;
				}*/
				}

			}
			rateType="";
			rateTypeFlag = true; 

		}
		/*if(BizUtil.isNullString(rateTypeVal) && (!BizUtil.isNullString(rate))){
rateTypeVal = "WS";
}*/

		if(GmailMethods.isNullString(rate) && !("RNR".equalsIgnoreCase(rateTypeVal))){
			rateTypeVal = "";
		}
		return rateTypeVal;
	}
	
	public static String ReportTypeRemaining(String myjson, PrintWriter out, String subjectLinePass){
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
	                		 
	                		    boolean tonnageFound=false;
								boolean spotFound=false;
								boolean timeCharterFound=false;
								boolean brokerTcRateFound=false;
								
								int spotCount=0;
								int tonnageCount=0;
								int tcCount=0;
								int btrCount=0;
								String value="";
	                		 for(int i=0;i<table_dataArray.length();i++){
	                			 tableinsideDataOnlyJonObj=  table_dataArray.getJSONObject(i);
	                			 JSONObject dataJSonObj=null;
	                    		 if(tableinsideDataOnlyJonObj.has("data")){
	                    			dataJSonObj= tableinsideDataOnlyJonObj.getJSONObject("data");
	                    			 
	                    		 } // data close
	                    		 JSONObject headerJSonObj=null;
	                    		 if(tableinsideDataOnlyJonObj.has("new_header_data")){
	                    			 headerJSonObj= tableinsideDataOnlyJonObj.getJSONObject("new_header_data");
	                    		 } // header close
	                    		 JSONObject jsonObj=new JSONObject();
	                    		 if(headerJSonObj!=null){
	                    			 
	                    		 Iterator<String>itr= headerJSonObj.keys();
	                    		 
	                    		 while(itr.hasNext()){
	                    			 String JsonKey=itr.next();
	                    			 if(headerJSonObj.has(JsonKey)){
	                    			 String key=headerJSonObj.getString(JsonKey);
	                    			 if(dataJSonObj.has(JsonKey)){
	                    			  value= dataJSonObj.getString(JsonKey);
	                    			 if(key.equalsIgnoreCase("ReportType")){
	                    				 
	                    				 if(value.equalsIgnoreCase("Tonnage")){
	                    					 tonnageFound=true;
											 tonnageCount++;
												
	                    				 }else if(value.equalsIgnoreCase("Spot")){
	                    					 spotFound=true;
											 spotCount++;
	                    				 } else if(value.toLowerCase().equals("timecharterreports") || value.toLowerCase().equals("timecharter")){
	                    					 timeCharterFound=true;
											 tcCount++;
	                    				 }else if(value.toLowerCase().equals("brokertcrate") || value.toLowerCase().equals("brokerrate")){
	                    					 brokerTcRateFound=true;
											 btrCount++;
	                    				 }
	                    				
	                    			 } // reportType has
	                    			
	                    			 // jsonObj.put(key, value);
	                    			
	                    			 }

	                    			 }
	                    			 
	                    		 } // while keyheader dyanamic 
	                    		 
	                    		 if( spotCount>=3 ){
										break;
									}else if( tonnageCount>=3 ){
										break;
									}else if( tcCount>=3){
										break;
									}else if( btrCount>=3 ){
										break;
									}
	                    		 
	                    		// jsonObjArray.put(jsonObj);
	                		 }
	                    		 
	                		 } // for loop close
	                		 
	                		
	                		if( tonnageFound ){
								if(tonnageCount>=3){
									table_dataArray= tonnagecollectJsonAndRemaining(table_dataArray,   value);
								}
							}else if( spotFound ){
								
								if(spotCount>=3){
									System.out.println("spot count");
									table_dataArray=spotCollectJsonAndRemaining(table_dataArray, value);
								}
								
							}else if( timeCharterFound ){
								if(tcCount>=3){
									table_dataArray=TcBTCCollectJsonAndRemaining(table_dataArray, value);
								}
							}else if( brokerTcRateFound ){
								if(btrCount>=3){
									table_dataArray=TcBTCCollectJsonAndRemaining(table_dataArray, value);
								}
							}
							
	                		
	                		finalJsonString=jsonStringInput.toString();
	                		
	                	 } // length check tablearray
	                	
	                	 
					 } // table_data has check
					 
					 
					 
					 
				} // jsonobj check
			} // while close dyanamic table1 etc
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalJsonString;
	}
	
	public static JSONArray tonnagecollectJsonAndRemaining( JSONArray table_dataArray,String ReportTypeString){
		JSONArray reportArray=new JSONArray();
		try {
			//System.out.println("a:: "+ReportTypeString);
			
			for(int i=0;i<table_dataArray.length();i++){
				JSONObject tableinsideDataOnlyJonObj=table_dataArray.getJSONObject(i);
				JSONObject dataJSonObj=null;
				if(tableinsideDataOnlyJonObj.has("data")){
        			dataJSonObj= tableinsideDataOnlyJonObj.getJSONObject("data");
        			 
        		 } // data close
        		 JSONObject headerJSonObj=null;
        		 JSONObject newHeader=new JSONObject();
        		 if(tableinsideDataOnlyJonObj.has("new_header_data")){
        			 headerJSonObj= tableinsideDataOnlyJonObj.getJSONObject("new_header_data");
        		 } // header close
        		 JSONObject jsonObj=new JSONObject();
        		 if(headerJSonObj!=null){
        			 HashMap<String, String> g=new HashMap<>();
        			 Iterator<String>itr= headerJSonObj.keys();
        		 while(itr.hasNext()){
        			 String headerKeyKey=itr.next(); //0
        			 String headerValue=headerJSonObj.getString(headerKeyKey); //key
        			 newHeader.put(headerKeyKey, headerValue);
        			 
        			g.put(headerKeyKey, headerValue);
        		 } // while keyheader dyanamic 
        		 
                  if(g!=null && !g.isEmpty() && g.size()>0){
                	  
                	  String VesselNamePos="";
      				String LCStartPos="";
      				String LoadPortPos="";
      				String RatePos="";
      				String datePos="";
      				String PortPos="";
      				String LCEndPos="";
      				String DiscPortPos="";
      				
      				String OpenDatePos="";
      				String OpenPortPos="";
      				String ETABasisPos="";
      				String ChartererPos="";
      				String ReportTypePos="";
      				
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
      				boolean ReportTypePoss=false;
      			 
      		 for (Map.Entry<String, String> entry : g.entrySet())
				  {
      			// newHeader.put(entry.getKey(), entry.getValue());
      				
      				if(entry.getValue().equalsIgnoreCase("VesselName")){
      					VesselNamePos=entry.getKey();
      					//System.out.println("VesselNamePos:: "+VesselNamePos);
      					 vesselHas=true;
      				}if(entry.getValue().equalsIgnoreCase("LCStart")){
      					LCStartPos=entry.getKey();
      					//System.out.println("LCStartPos:: "+LCStartPos);
      					LCStartHas=true;
      				}if(entry.getValue().equalsIgnoreCase("LoadPort")){
      					LoadPortPos=entry.getKey();
      					//System.out.println("LoadPortPos:: "+LoadPortPos);
      					LoadPortHas=true;
      				}if(entry.getValue().equalsIgnoreCase("Rate")){
      					RatePos=entry.getKey();
      					//System.out.println("RatePos:: "+RatePos);
      					RateHas=true;
      				}if(entry.getValue().equalsIgnoreCase("Date")){
      					datePos=entry.getKey();
      					//System.out.println("datePos:: "+datePos);
      					DateHas=true;
      				}if(entry.getValue().equalsIgnoreCase("Port")){
      					PortPos=entry.getKey();
      					PortHas=true;
      				}if(entry.getValue().equalsIgnoreCase("LCEnd")){
      					LCEndPos=entry.getKey();
      					LCEndHas=true;
      				}if(entry.getValue().equalsIgnoreCase("DiscPort")){
      					DiscPortPos=entry.getKey();
      					DiscPortHas=true;
      				}if(entry.getValue().equalsIgnoreCase("OpenDate")){
      					OpenDatePos=entry.getKey();
      					OpenDatePoss=true;
      				}if(entry.getValue().equalsIgnoreCase("OpenPort")){
      					OpenPortPos=entry.getKey();
      					OpenPortPoss=true;
      				}if(entry.getValue().equalsIgnoreCase("ETABasis")){
      					ETABasisPos=entry.getKey();
      					ETABasisPoss=true;
      				}if(entry.getValue().equalsIgnoreCase("Charterer")){
      					ChartererPos=entry.getKey();
      					ChartererPoss=true;
      				}if(entry.getValue().equalsIgnoreCase("ReportType")){
      					ReportTypePos=entry.getKey();
      					ReportTypePoss=true;
      				}
      				
      			
				  } // map for loop close
      		 
      		 JSONObject countAll=ReportTypeIdentify.countRateAndPortAndDate(g);
//     		 System.out.println(":: "+countAll);
     		 
     		    int dateCount=0;
     			int portCount=0;
     			int rateCount=0;
     			
     			 if(countAll!=null){
     				 if(countAll.has("Date")){
     					 dateCount= countAll.getInt("Date");
     					// System.out.println("singlejsondate: "+dateCount);
     				 }if(countAll.has("Port")){
     					 portCount=countAll.getInt("Port");
     				 }if(countAll.has("Rate")){
     					 rateCount=countAll.getInt("Rate");
     				 }
     				
     			 }
     			 
     			if( dateCount>=1){
     				 
     				 if(DateHas){
     					JSONObject StandardAndNonStandardDate= ReportTypeIdentify.dateGreaterLesser(g, dataJSonObj);
     					//System.out.println("StandardAndNonStandardDate:: "+StandardAndNonStandardDate);
     					if(StandardAndNonStandardDate!=null && StandardAndNonStandardDate.length()!=0){
     						String ETABasisFound="";
     						String OpenDateFound="";
     						String ETABasisNotFound="";
     						String OpenDateNotFound="";
     						
     						if(StandardAndNonStandardDate.has("DateStandardFound")){
     							JSONArray DateStandardFound=StandardAndNonStandardDate.getJSONArray("DateStandardFound");
     							//System.out.println("DateStandardFound:: "+DateStandardFound);
     							if(DateStandardFound.length()>0){
     							for(int k=0;k<DateStandardFound.length();k++){
         							JSONObject DateStandardFoundOBj= DateStandardFound.getJSONObject(k);
         							//System.out.println("DateStandardFoundOBj:: "+DateStandardFoundOBj);
         							if(DateStandardFoundOBj.has("ETABasis")){
         								ETABasisFound=DateStandardFoundOBj.getString("ETABasis");
         							}if(DateStandardFoundOBj.has("OpenDate")){
         								OpenDateFound=DateStandardFoundOBj.getString("OpenDate");
         							}
         						} // standard date
     							}
     						}if(StandardAndNonStandardDate.has("DateNotStandardFound")){
     							JSONArray DateNotStandardFound=StandardAndNonStandardDate.getJSONArray("DateNotStandardFound");
     							for(int j=0;j<DateNotStandardFound.length();j++){
         							JSONObject DateStandardNotFoundOBj= DateNotStandardFound.getJSONObject(j);
                                          if(DateStandardNotFoundOBj.has("ETABasis")){
                                         	 ETABasisNotFound= DateStandardNotFoundOBj.getString("ETABasis");
         							}
                                           if(DateStandardNotFoundOBj.has("OpenDate")){
                                         	  OpenDateNotFound= DateStandardNotFoundOBj.getString("OpenDate");
                                         }
         						} // not standard date
     						}
     						
     						if( !GmailMethods.isNullString(ETABasisFound) ){
     							newHeader.put(ETABasisFound, "ETABasis");
     						}else{
     							if( !GmailMethods.isNullString(ETABasisNotFound) ){
     							     newHeader.put(ETABasisNotFound, "ETABasis");
     							}
     						}
     						if( !GmailMethods.isNullString(OpenDateFound) ){
     							newHeader.put(OpenDateFound, "OpenDate");
     						}else{
     							if( !GmailMethods.isNullString(OpenDateNotFound) ){
     								newHeader.put(OpenDateNotFound, "OpenDate");
     							}
     						}
     						
     						
     					} // json null check
     				 }// date check
     				 
     				 
     				 
     			} // date count here
     			else{
     				 
      				if(DateHas){
    					 if(!ETABasisPoss){
    						 newHeader.put(datePos, "ETABasis");
    					 }else if(!OpenDatePoss){
    						 newHeader.put(datePos, "OpenDate");
    					 }
    				 } // date check 
     			}
     			 
     			 if(PortHas){
  					newHeader.put(PortPos, "OpenPort");
  				 }
  				 
  				 if(ChartererPoss){
						 newHeader.put(ChartererPos, "Operators");
				 } //  Operators
  			
  				
  				if(ReportTypePoss){
  					newHeader.put(ReportTypePos, "ReportType");
  					dataJSonObj.put(ReportTypePos, ReportTypeString);
  				}else {
  					newHeader.put(String.valueOf(dataJSonObj.length()), "ReportType");
  					dataJSonObj.put(String.valueOf(dataJSonObj.length()), ReportTypeString);
  				}
     			
        			 
        	     	 } // g null check
        		 
                  table_dataArray.getJSONObject(i).put("new_header_data", newHeader);
        		 } // header check null
				
        		
        		
        		 
				/*allReportJsonArrayInsideJSonobject.put("ReportType", ReportTypeString);
				reportArray.put(allReportJsonArrayInsideJSonobject);*/
				
			}// for loop close // first loop
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return table_dataArray;
	}

	public static JSONArray spotCollectJsonAndRemaining( JSONArray table_dataArray,String ReportTypeString){
		try {
			//System.out.println("a:: "+ReportTypeString);
			
			for(int i=0;i<table_dataArray.length();i++){
				JSONObject tableinsideDataOnlyJonObj=table_dataArray.getJSONObject(i);
				JSONObject dataJSonObj=null;
				if(tableinsideDataOnlyJonObj.has("data")){
        			dataJSonObj= tableinsideDataOnlyJonObj.getJSONObject("data");
        			 
        		 } // data close
        		 JSONObject headerJSonObj=null;
        		 JSONObject newHeader=new JSONObject();
        		 if(tableinsideDataOnlyJonObj.has("new_header_data")){
        			 headerJSonObj= tableinsideDataOnlyJonObj.getJSONObject("new_header_data");
        		 } // header close
        		 JSONObject jsonObj=new JSONObject();
        		 if(headerJSonObj!=null){
        			 HashMap<String, String> g=new HashMap<>();
        			 Iterator<String>itr= headerJSonObj.keys();
        		 while(itr.hasNext()){
        			 String headerKeyKey=itr.next(); //0
        			 String headerValue=headerJSonObj.getString(headerKeyKey); //key
        			 newHeader.put(headerKeyKey, headerValue);
        			 
        			g.put(headerKeyKey, headerValue);
        		 } // while keyheader dyanamic 
        		 
                  if(g!=null && !g.isEmpty() && g.size()>0){
                	  
                	  String VesselNamePos="";
      				String LCStartPos="";
      				String LoadPortPos="";
      				String RatePos="";
      				String datePos="";
      				String PortPos="";
      				String LCEndPos="";
      				String DiscPortPos="";
      				
      				String OpenDatePos="";
      				String OpenPortPos="";
      				String ETABasisPos="";
      				String ChartererPos="";
      				String ReportTypePos="";
      				
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
      				boolean ReportTypePoss=false;
      			 
      		 for (Map.Entry<String, String> entry : g.entrySet())
				  {
      			// newHeader.put(entry.getKey(), entry.getValue());
      				
      				if(entry.getValue().equalsIgnoreCase("VesselName")){
      					VesselNamePos=entry.getKey();
      					//System.out.println("VesselNamePos:: "+VesselNamePos);
      					 vesselHas=true;
      				}if(entry.getValue().equalsIgnoreCase("LCStart")){
      					LCStartPos=entry.getKey();
      					//System.out.println("LCStartPos:: "+LCStartPos);
      					LCStartHas=true;
      				}if(entry.getValue().equalsIgnoreCase("LoadPort")){
      					LoadPortPos=entry.getKey();
      					//System.out.println("LoadPortPos:: "+LoadPortPos);
      					LoadPortHas=true;
      				}if(entry.getValue().equalsIgnoreCase("Rate")){
      					RatePos=entry.getKey();
      					//System.out.println("RatePos:: "+RatePos);
      					RateHas=true;
      				}if(entry.getValue().equalsIgnoreCase("Date")){
      					datePos=entry.getKey();
      					//System.out.println("datePos:: "+datePos);
      					DateHas=true;
      				}if(entry.getValue().equalsIgnoreCase("Port")){
      					PortPos=entry.getKey();
      					PortHas=true;
      				}if(entry.getValue().equalsIgnoreCase("LCEnd")){
      					LCEndPos=entry.getKey();
      					LCEndHas=true;
      				}if(entry.getValue().equalsIgnoreCase("DiscPort")){
      					DiscPortPos=entry.getKey();
      					DiscPortHas=true;
      				}if(entry.getValue().equalsIgnoreCase("OpenDate")){
      					OpenDatePos=entry.getKey();
      					OpenDatePoss=true;
      				}if(entry.getValue().equalsIgnoreCase("OpenPort")){
      					OpenPortPos=entry.getKey();
      					OpenPortPoss=true;
      				}if(entry.getValue().equalsIgnoreCase("ETABasis")){
      					ETABasisPos=entry.getKey();
      					ETABasisPoss=true;
      				}if(entry.getValue().equalsIgnoreCase("Charterer")){
      					ChartererPos=entry.getKey();
      					ChartererPoss=true;
      				}if(entry.getValue().equalsIgnoreCase("ReportType")){
      					ReportTypePos=entry.getKey();
      					ReportTypePoss=true;
      				}
      				
      			
				  } // map for loop close
      		 
      		 JSONObject countAll=ReportTypeIdentify.countRateAndPortAndDate(g);
     		// System.out.println(":: "+countAll);
     		 
     		    int dateCount=0;
     			int portCount=0;
     			int rateCount=0;
     			
     			 if(countAll!=null){
     				 if(countAll.has("Date")){
     					 dateCount= countAll.getInt("Date");
     					// System.out.println("singlejsondate: "+dateCount);
     				 }if(countAll.has("Port")){
     					 portCount=countAll.getInt("Port");
     				 }if(countAll.has("Rate")){
     					 rateCount=countAll.getInt("Rate");
     				 }
     				
     			 }
     			JSONObject comparePortAndDate=null;
     			 if(DateHas){ // if date has
     				 
					 comparePortAndDate=new JSONObject();
        			 comparePortAndDate=ReportTypeIdentify.PortAndDatePositionCompare(g);
        			 
        			 if(comparePortAndDate!=null){
        				 if(comparePortAndDate.has("LCStart")){
        					String LCStartPoss= comparePortAndDate.getString("LCStart");
        					 newHeader.put(LCStartPoss, "LCStart");
        				 }if(comparePortAndDate.has("LCEnd")){
        					String LCEndPoss= comparePortAndDate.getString("LCEnd");
        					 newHeader.put(LCEndPoss, "LCEnd");
        				 }
        			 }
					 
				 } // date check
     			 
     			 if(PortHas){
					 if(comparePortAndDate!=null){
						 if(comparePortAndDate.has("LoadPort")){
        					String LoadPortPoss= comparePortAndDate.getString("LoadPort");
        					 newHeader.put(LoadPortPoss, "LoadPort");
        				 }if(comparePortAndDate.has("DiscPort")){
        					String DiscPortPoss=comparePortAndDate.getString("DiscPort");
        					 newHeader.put(DiscPortPoss, "DiscPort");
        				 }
					 }
				 } // port check
  			
     			 if(PortHas){ // if date has
					 if(!LoadPortHas){ //if not else lloadport
						 newHeader.put(LoadPortPos, "LoadPort");
					 }else if(!DiscPortHas){    //if not else discport
						 newHeader.put(LoadPortPos, "DiscPort");
					 }
				 } // port check
  				
  				if(ReportTypePoss){
  					newHeader.put(ReportTypePos, "ReportType");
  					dataJSonObj.put(ReportTypePos, ReportTypeString);
  				}else {
  					newHeader.put(String.valueOf(dataJSonObj.length()), "ReportType");
  					dataJSonObj.put(String.valueOf(dataJSonObj.length()), ReportTypeString);
  				}
     			
        			 
        	     	 } // g null check
        		 
                  table_dataArray.getJSONObject(i).put("new_header_data", newHeader);
        		 } // header check null
				
				
			}// for loop close // first loop
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return table_dataArray;
	}
	
	public static JSONArray TcBTCCollectJsonAndRemaining( JSONArray table_dataArray,String ReportTypeString){
		try {
			//System.out.println("a:: "+ReportTypeString);
			
			for(int i=0;i<table_dataArray.length();i++){
				JSONObject tableinsideDataOnlyJonObj=table_dataArray.getJSONObject(i);
				JSONObject dataJSonObj=null;
				if(tableinsideDataOnlyJonObj.has("data")){
        			dataJSonObj= tableinsideDataOnlyJonObj.getJSONObject("data");
        			 
        		 } // data close
        		 JSONObject headerJSonObj=null;
        		 JSONObject newHeader=new JSONObject();
        		 if(tableinsideDataOnlyJonObj.has("new_header_data")){
        			 headerJSonObj= tableinsideDataOnlyJonObj.getJSONObject("new_header_data");
        		 } // header close
        		 JSONObject jsonObj=new JSONObject();
        		 if(headerJSonObj!=null){
        			 HashMap<String, String> g=new HashMap<>();
        			 Iterator<String>itr= headerJSonObj.keys();
        		 while(itr.hasNext()){
        			 String headerKeyKey=itr.next(); //0
        			 String headerValue=headerJSonObj.getString(headerKeyKey); //key
        			 newHeader.put(headerKeyKey, headerValue);
        			 
        			g.put(headerKeyKey, headerValue);
        		 } // while keyheader dyanamic 
        		 
                  if(g!=null && !g.isEmpty() && g.size()>0){
                	
      				String ReportTypePos="";
      				boolean ReportTypePoss=false;
      			 
      		  for (Map.Entry<String, String> entry : g.entrySet())
				  {
      			
      				if(entry.getValue().equalsIgnoreCase("ReportType")){
      					ReportTypePos=entry.getKey();
      					ReportTypePoss=true;
      				}
      				
      				
      			
				  } // map for loop close
      		 
     			 
  				if(ReportTypePoss){
  					newHeader.put(ReportTypePos, "ReportType");
  					dataJSonObj.put(ReportTypePos, ReportTypeString);
  				}else {
  					newHeader.put(String.valueOf(dataJSonObj.length()), "ReportType");
  					dataJSonObj.put(String.valueOf(dataJSonObj.length()), ReportTypeString);
  				}
     			
        			 
        	     	 } // g null check
        		 
                  table_dataArray.getJSONObject(i).put("new_header_data", newHeader);
        		 } // header check null
				
				
			}// for loop close // first loop
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return table_dataArray;
	}
	
}
