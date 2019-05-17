package com.abhishek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import com.readGmail.GmailMethods;
import com.reportinformationsystem.SaveReportDataClass;

import java.util.Map.Entry;
import java.util.regex.Pattern;

public class AbhishekScript {

	public static void main(String[] args) {
		//excelHtmlScript();
//		String unknownvalue="M / y";
//		
//		if(Pattern.matches("(\\d{1,}\\s?(months|Months))", unknownvalue)
//				//|| Pattern.matches("(\\Pnr)", unknownvalue)
//				|| Pattern.matches("(\\d{1,}\\-\\d{1,}\\s?MOS|\\d{1,}\\+\\d{1,}\\s?MOS|\\d{1,}\\+\\d{1,}\\+\\d{1,}\\s?MOS)", unknownvalue)
//				|| Pattern.matches("(\\d{1,}\\-\\d{1,}\\s?(Days|DAYS))", unknownvalue)
//				|| Pattern.matches("(\\d{1,}\\s?(YRS|Yrs))", unknownvalue)
//				|| Pattern.matches("(\\d{1,}\\s?(MOS|Mos))", unknownvalue)
//				|| Pattern.matches("(\\d{1,}\\-\\d{1,}d)", unknownvalue)
//				|| Pattern.matches("(\\d{1,}(yrs|yr|Yrs|Yr)\\s?\\+\\d{1,}(yr|Yr)\\+\\d{1,}(yr|Yr))", unknownvalue)
//				|| Pattern.matches("(\\d{1,}\\-(ott|Ott))", unknownvalue)
//				|| Pattern.matches("(nr|NR|Nr)", unknownvalue)) {
//			System.out.println("In Period regex   " );
//
//			//	updatejson( newsubobj, "Period", unknownvalue, lhmap, unknownkey);
//
//				
//		}else{
//			System.out.println("In else regex   " );
//
//		}
		
		JSONArray arr= new JSONArray();
		for(int i=1; i<13; i++){
			for(int j=1; j<32; j++){
				for(int k=1;k<32; k++){
					String month="";
					if(i<9){month="0"+i;}else{month=""+i;}
					arr.put(k+"-"+j+"/"+month);
					String day1="";
					String day2="";
					if(k<9){day1="0"+k;}else{day1=""+k;}
					if(j<9){day2="0"+j;}else{day2=""+j;}
					arr.put(day1+"-"+day2+"/"+month);


				}
			}
		}
		System.out.println(arr);
	}
	
	public static String excelHtmlScript(String ExpertCallJson, PrintWriter out) {
		String jsonDataReturn="";
		try {

			String json=ExpertCallJson;
			boolean jsonCheck=SaveReportDataClass.isJSONValid(json);
			if(jsonCheck){
				JSONObject maxLengthJsonData = maxKeyJsonData(json, out);
				out.println("maxLengthJsonData:: "+maxLengthJsonData);
				if (maxLengthJsonData!=null) {
					Iterator<String> itr = maxLengthJsonData.keys();
					HashMap<String, String> keyValue = new HashMap<String, String>();

					while (itr.hasNext()) {
						String key = itr.next();
						String solrKeyList = fetchsolrdata(key);
						if (solrKeyList != null) {
							// System.out.println("solrKeyList:: " +
							// solrKeyList);
							String headerName = parsesolrresp(solrKeyList, "HeaderName");
							// System.out.println("headerName:: "+headerName);
							if ( !GmailMethods.isNullString(headerName) ) {
								  keyValue.put(key, headerName);
							}

						}

					} // while close

					 out.println("keyValue:: "+keyValue);
					if ( !keyValue.isEmpty()) {
						String newJson = newJson(keyValue, json);
						 
						boolean booleanCheckJson1 = isJSONValid(newJson);
						if (booleanCheckJson1 == true) {
							out.println(newJson);
							String reportType = reporttypeSolr(newJson);
							if ( !GmailMethods.isNullString(reportType) ) {
								String finalJson = finalJson(newJson, reportType);
								boolean booleanCheckJson = isJSONValid(finalJson);
								if (booleanCheckJson == true) {
									out.println(finalJson);
									jsonDataReturn=finalJson;
								   /* Map<String, String> map=checkUnknownDataReportWise(finalJson, out);
								    if( !map.isEmpty()){
								     String jsonData= finalJSnCheckUnknown(finalJson, map);
								    boolean booleanCheckJson12 = isJSONValid(jsonData);
								    if(booleanCheckJson12){
//								       out.println(jsonData);
								    	jsonDataReturn=jsonData;
								    }
								}// map empty check
*/								}
							}
						} // booleanCheckJson1 check
					} // check maxjsonlength >0
				}// len
				
		}
//			} // else close

		} catch (Exception e) {
           e.printStackTrace();
		}
		return jsonDataReturn;
	}
	
	public static Map<String, String> checkUnknownDataReportWise(String finalJson, PrintWriter out){
		LinkedHashMap<String, String> map=null;
		try {
			boolean checkFinalJson=SaveReportDataClass.isJSONValid(finalJson);
			if(checkFinalJson){
			JSONObject finalJsonObj=new JSONObject(finalJson);
			Iterator<String> keys = finalJsonObj.keys();
			JSONArray allReportJsonArray = null;
			while (keys.hasNext()) {
				
				String key = keys.next();
				Object keyValue = finalJsonObj.get(key);
				
				if (keyValue instanceof JSONArray) {
					allReportJsonArray = (JSONArray) keyValue;
					if (allReportJsonArray.length() > 0) {
						for(int i=0;i<allReportJsonArray.length();i++){
							JSONObject jsonObjLoop=allReportJsonArray.getJSONObject(i);
									
									String unknownJSonKeyResponse=getOnkyUNKnownkeys(jsonObjLoop);
									out.println("unknownJSonKeyResponse:: "+unknownJSonKeyResponse);
									boolean checkUnknownBoolean=isJSONValid(unknownJSonKeyResponse);
									if(checkUnknownBoolean==true){
									  JSONObject unknownJSonKeyResponseJson=new JSONObject(unknownJSonKeyResponse);
									  Iterator<String>itr= unknownJSonKeyResponseJson.keys();
									  int countUnknownKey=0;
									  map=new LinkedHashMap<>();
									
									while(itr.hasNext()){
										String nextUnknownKey=itr.next();
										if(unknownJSonKeyResponseJson.has(nextUnknownKey)){
											String nextUnknownKeyValue=unknownJSonKeyResponseJson.getString(nextUnknownKey);
										   // System.out.println(nextUnknownKeyValue);
											String solrUnknownResponse=solrToCheckUnknownKey(nextUnknownKeyValue);
										   // System.out.println(solrUnknownResponse);
										    
										   boolean checkResponseSolr= isJSONValid(solrUnknownResponse);
										   if(checkResponseSolr){
											   
											  String DocumentName= parsesolrresp(solrUnknownResponse, "DocumentName");
											  map.put(nextUnknownKey, DocumentName);
											 
										   }
										}
										
										countUnknownKey++;
									} // while close
								//	System.out.println(map);
									/*int datecount=0;
									  ArrayList datearr=getKeysByValue(map, "Date");
									  datecount=datearr.size() ;*/
									  //System.out.println("daterr  "+datearr+" datecount "+datecount);
									 // System.out.println("countUnknownKey:: "+countUnknownKey);
									  
									 
									  
									} // boolean check
									
									
									
//								} // tonnage equals
								
								
//							}// reportType check
							
						}// for lenth check
						
						
					} // array length >0 check
					
				} // jsonarray check key
				
			} // while dynamic key
			
		}// check jsonValid
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public static <T, E> ArrayList<String> getKeysByValue(Map<T, E> map, E value) {
		ArrayList<String> arr= new ArrayList<>();

	    Set<T> keys = new HashSet<T>();
	    for (Entry<T, E> entry : map.entrySet()) {
	    	
	    	//System.out.println(value +":: "+entry.getValue());
	        if (Objects.equals(value, entry.getValue())) {
	            keys.add(entry.getKey());
	        }
	    }
	    //System.out.println("keysadd:: "+keys);
	   // arr =  pallavi_SortJSON.compareSet( keys);
	    
	    return arr;
	}
	
	public static <T, E> boolean getEqualsValue(Map<T, E> map, E value) {
//		ArrayList<String> arr= new ArrayList<>();
     boolean checkKey=false; 
//	    Set<T> keys = new HashSet<T>();
	    for (Entry<T, E> entry : map.entrySet()) {
	    	
	    	//System.out.println(value +":: "+entry.getValue());
	        if (Objects.equals(value, entry.getValue())) {
	        	checkKey=true;
	        }
	    }
	   
	   // arr =  pallavi_SortJSON.compareSet( keys);
	    
	    return checkKey;
	}
	
	public static ArrayList compareSet(Set strSet){
		Iterator<String> iterator2= strSet.iterator();
		ArrayList<Integer> arr= new ArrayList<>();
		ArrayList<String> arrRet= new ArrayList<>();
		
		while (iterator2.hasNext()) {
			String str = iterator2.next();

			String sub_key= str.substring(str.indexOf("_")).replace("_", "");
			arr.add(Integer.parseInt(sub_key));
			
			Collections.sort(arr); 
		}
		for(int i=0; i<arr.size(); i++) {
			String key_new= "unknown_"+arr.get(i);	
			arrRet.add(key_new);
		}
		return arrRet;
	}
	
	public static String getOnkyUNKnownkeys(JSONObject obj){
		Iterator<String> iterator= obj.keys();
		JSONObject obj1= new JSONObject();
		String obj1JSonString="";
		while (iterator.hasNext()) {
			String key_obj = iterator.next();
//			if((!(key_obj.contains("x_unknown")) && (key_obj.contains("unknown")))) {
			if( !key_obj.contains("x_unknown")  &&  !key_obj.contains("_x")  &&  key_obj.contains("unknown")) {
				
					try {
						obj.get(key_obj);
						obj1.put(key_obj, obj.get(key_obj));
						obj1JSonString=obj1.toString();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				//obj.remove(key_obj);
			}
		}
		return obj1JSonString;
	}
	
	public static String solrToCheckUnknownKey(String q) {

		StringBuffer response1 = null;
		try {
			q = URLEncoder.encode(q, "UTF-8");

			String url = "";

			url = "http://35.231.163.191:8983/solr/AllReport/select?q=Data:" + q + "&fl=score,DocumentName";
			
			URL url1 = new URL(url);
			HttpURLConnection con = (HttpURLConnection) url1.openConnection();
			// int responseCode = con.getResponseCode();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			response1 = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response1.append(inputLine);
			}
			//System.out.println(response1);
			in.close();

		} catch (Exception e) {
			 e.printStackTrace();
//			System.out.println(e.getMessage());
//			return "";
		}
		return response1.toString();

	}

	public static boolean isJSONValid(String test) {
		try {
			new JSONObject(test);
		} catch (JSONException ex) {
			// edited, to include @Arthur's comment
			// e.g. in case JSONArray is valid as well...
			try {
				new JSONArray(test);
			} catch (JSONException ex1) {
				return false;
			}
		}
		return true;
	}

	public static String newJson(HashMap<String, String> keyValue, String oldJson) {
		String jsonStringCopy = "";
		JSONArray copyObj = new JSONArray();
		try {
			Set<String> reqkeyset = keyValue.keySet();
			oldJson=oldJson.trim();
			if(oldJson.startsWith("{")){
			// System.out.println("reqkeyset "+reqkeyset);
			JSONObject objectjson = new JSONObject(oldJson);
			 Iterator<String> keys = objectjson.keys();
	            while (keys.hasNext()) {
	            	String key = keys.next();
	                 Object keyValue1 = objectjson.get(key);
	                 if (keyValue1 instanceof JSONArray) {
	                	 JSONArray allReportJsonArray=null;
	                	 allReportJsonArray = (JSONArray)keyValue1;
	            	
			//JSONArray allReportJsonArray = new JSONArray(oldJson);
			for (int i = 0; i < allReportJsonArray.length(); i++) {
				JSONObject oldJsonObj = allReportJsonArray.getJSONObject(i);
				JSONObject oldJsonObj_copy = new JSONObject();
				// System.out.println("oldJsonObj 1 "+oldJsonObj);
				Iterator<String> itr = oldJsonObj.keys();
				while (itr.hasNext()) {
					String oldJsonKey = itr.next();

					//System.out.println(reqkeyset);
					if (reqkeyset.contains(oldJsonKey)) {

						String reqkey = keyValue.get(oldJsonKey);
						// System.out.println("keyValue "+reqkey);
						String JSONValue = oldJsonObj.getString(oldJsonKey);
						// System.out.println("JSONValue:: "+JSONValue);
						if (reqkey != null && !reqkey.equals("")) {
							// System.out.println("if");
							oldJsonObj_copy.put(reqkey, JSONValue);

						}
					}else{
						oldJsonObj_copy.put(oldJsonKey, oldJsonObj.getString(oldJsonKey));
					}

				} // while close here

				copyObj.put(oldJsonObj_copy);

			} // for aaraylength close
			
	           }
	            }

			JSONObject allCombine = new JSONObject();
			allCombine.put("requiredOutput", copyObj);
			jsonStringCopy = allCombine.toString();
			}else if(oldJson.startsWith("[")){
				JSONArray allReportJsonArray = new JSONArray(oldJson);
				for (int i = 0; i < allReportJsonArray.length(); i++) {
					JSONObject oldJsonObj = allReportJsonArray.getJSONObject(i);
					JSONObject oldJsonObj_copy = new JSONObject();
					// System.out.println("oldJsonObj 1 "+oldJsonObj);
					Iterator<String> itr = oldJsonObj.keys();
					while (itr.hasNext()) {
						String oldJsonKey = itr.next();

						//System.out.println(reqkeyset);
						if (reqkeyset.contains(oldJsonKey)) {

							String reqkey = keyValue.get(oldJsonKey);
							// System.out.println("keyValue "+reqkey);
							String JSONValue = oldJsonObj.getString(oldJsonKey);
							// System.out.println("JSONValue:: "+JSONValue);
							if (reqkey != null && !reqkey.equals("")) {
								// System.out.println("if");
								oldJsonObj_copy.put(reqkey, JSONValue);

							}
						}else{
							oldJsonObj_copy.put(oldJsonKey, oldJsonObj.getString(oldJsonKey));
						}

					} // while close here

					copyObj.put(oldJsonObj_copy);

				}
				
				JSONObject allCombine = new JSONObject();
				allCombine.put("requiredOutput", copyObj);
				jsonStringCopy = allCombine.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonStringCopy;
	}

	public static boolean checkUnknownX(String jsonArrayString) {
		// int k = 0;
		boolean checkUnknownKey = false;
		try {
			JSONArray allReportJsonArray = new JSONArray(jsonArrayString);
			for (int i = 0; i < allReportJsonArray.length(); i++) {

				JSONObject objzero = allReportJsonArray.getJSONObject(i);
				JSONArray data = objzero.names();
				for (int j = 0; j < data.length(); j++) {
					String finaldata = data.getString(j);

					if (finaldata.contains("unknown")) {
						// k++;
						checkUnknownKey = true;
					}

				}

			}

		} catch (Exception e) {

		}
		// return k;
		return checkUnknownKey;

	}

	public static JSONObject maxKeyJsonData(String jsonArrayString, PrintWriter out) {
		JSONObject temp_subobj = null;
		try {
			System.out.println("check:: "+jsonArrayString);
			jsonArrayString=jsonArrayString.trim();
			if(jsonArrayString.startsWith("{")){
				
		         out.println("yes");
				
				JSONObject objectjson = new JSONObject(jsonArrayString);
				 Iterator<String> keys = objectjson.keys();
		            while (keys.hasNext()) {
		            	 String key = keys.next();
		                 Object keyValue = objectjson.get(key);
		                 if (keyValue instanceof JSONArray) {
		                	 JSONArray allReportJsonArray=null;
		                	 allReportJsonArray = (JSONArray)keyValue;
		                 
				//JSONArray allReportJsonArray = new JSONArray(jsonArrayString);

				if (allReportJsonArray.length() > 0) {

					HashMap<Integer, Integer> keycount = new HashMap<Integer, Integer>();
					for (int maxkeycount = 0; maxkeycount < allReportJsonArray.length(); maxkeycount++) {
						// System.out.println("count: "+maxkeycount);
						JSONObject tempjson = allReportJsonArray.getJSONObject(maxkeycount);
						// System.out.println("tempjsonlength: "+tempjson);
						keycount.put(tempjson.length(), maxkeycount);
						// System.out.println("finalkeycount: "+keycount);
					}

					int maxlength = (int) Collections.max(keycount.keySet());
					// System.out.println("maxlengh: "+maxlength);
					int maxlengthposition = keycount.get(maxlength);
					// System.out.println("maxlengthposition: "+maxlengthposition);

					temp_subobj = allReportJsonArray.getJSONObject(maxlengthposition);
					// System.out.println("maxJson: "+temp_subobj);

				}//
		       } // while close
		            }
				
			} // if curly wala json
			else if(jsonArrayString.startsWith("[")){
				out.println("array else if");
				
				JSONArray allReportJsonArray = new JSONArray(jsonArrayString);
				
				if (allReportJsonArray.length() > 0) {

					HashMap<Integer, Integer> keycount = new HashMap<Integer, Integer>();
					for (int maxkeycount = 0; maxkeycount < allReportJsonArray.length(); maxkeycount++) {
						// System.out.println("count: "+maxkeycount);
						JSONObject tempjson = allReportJsonArray.getJSONObject(maxkeycount);
						// System.out.println("tempjsonlength: "+tempjson);
						keycount.put(tempjson.length(), maxkeycount);
						// System.out.println("finalkeycount: "+keycount);
					}

					int maxlength = (int) Collections.max(keycount.keySet());
					// System.out.println("maxlengh: "+maxlength);
					int maxlengthposition = keycount.get(maxlength);
					// System.out.println("maxlengthposition: "+maxlengthposition);

					temp_subobj = allReportJsonArray.getJSONObject(maxlengthposition);
					// System.out.println("maxJson: "+temp_subobj);

				}//
				
			}
			
			
			

		} catch (Exception e) {

		}
		return temp_subobj;
	}
	
	/*public static JSONObject maxKeyJsonData(String jsonArrayString) {
		JSONObject temp_subobj = null;
		try {
			JSONObject objectjson = new JSONObject(jsonArrayString);
			 Iterator<String> keys = objectjson.keys();
	            while (keys.hasNext()) {
	            	 String key = keys.next();
	                 Object keyValue = objectjson.get(key);
	                 if (keyValue instanceof JSONArray) {
	                	 JSONArray allReportJsonArray=null;
	                	 allReportJsonArray = (JSONArray)keyValue;
	                 
			//JSONArray allReportJsonArray = new JSONArray(jsonArrayString);

			if (allReportJsonArray.length() > 0) {

				HashMap<Integer, Integer> keycount = new HashMap<Integer, Integer>();
				for (int maxkeycount = 0; maxkeycount < allReportJsonArray.length(); maxkeycount++) {
					// System.out.println("count: "+maxkeycount);
					JSONObject tempjson = allReportJsonArray.getJSONObject(maxkeycount);
					// System.out.println("tempjsonlength: "+tempjson);
					keycount.put(tempjson.length(), maxkeycount);
					// System.out.println("finalkeycount: "+keycount);
				}

				int maxlength = (int) Collections.max(keycount.keySet());
				// System.out.println("maxlengh: "+maxlength);
				int maxlengthposition = keycount.get(maxlength);
				// System.out.println("maxlengthposition: "+maxlengthposition);

				temp_subobj = allReportJsonArray.getJSONObject(maxlengthposition);
				// System.out.println("maxJson: "+temp_subobj);

			}//
	       } // while close
	            }
			
			

		} catch (Exception e) {

		}
		return temp_subobj;
	}*/

	public static String fetchsolrdata(String q) {

		// String f1 = "*,score";
		StringBuffer response1 = null;
		// System.out.println("q: "+q);
		try {
			// q = q.replace(" ", "%20");
			q = URLEncoder.encode(q, "UTF-8");

			String url = "";

			// url = "http://35.231.163.191:8983/solr/TemplateCore/select?q=" + q
			// + "&fl=" + f1 + "&df=" + df;

			// url = "http://35.231.163.191:8983/solr/Header_Synomes/select?q=" +
			// q + "&fl=" + f1;
			// url =
			// "http://35.231.163.191:8983/solr/Header_Synomes/select?q=Data:" +
			// q + "&fl=" + "HeaderName";
			url = "http://35.231.163.191:8983/solr/Header_Synomes/select?q=Data:" + q + "&fl=score,HeaderName";
			// url =
			// "http://35.231.163.191:8983/solr/Header_Synomes/select?q=Data:("+q+")&fl=HeaderName";

			// url = url.replace(" ", "%20");

			// System.out.println("url "+url);
			URL url1 = new URL(url);
			HttpURLConnection con = (HttpURLConnection) url1.openConnection();
			// int responseCode = con.getResponseCode();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			response1 = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response1.append(inputLine);
			}
			//System.out.println(response1);
			in.close();

		} catch (Exception e) {
			// e.printStackTrace();
//			System.out.println(e.getMessage());
			return "";
		}
		return response1.toString();

	}

	public static String parsesolrresp(String resp, String solrkey) {
		String HeaderName = "";
		try {
			JSONObject sorrespobj = new JSONObject(resp);

			if (sorrespobj.has("response") && sorrespobj.getJSONObject("response").has("maxScore")
					&& sorrespobj.getJSONObject("response").has("docs")) {

				JSONObject Subsorrespobj = sorrespobj.getJSONObject("response");
				JSONArray docs = Subsorrespobj.getJSONArray("docs");
				double maxscore = Subsorrespobj.getDouble("maxScore");
				// System.out.println("maxscore "+maxscore);

				for (int i = 0; i < docs.length(); i++) {
					JSONObject subdocs = docs.getJSONObject(i);
					double score = subdocs.getDouble("score");
					if (score == maxscore) {
						// System.out.println("maxscore "+maxscore+" score
						// "+score);

						HeaderName = subdocs.getJSONArray(solrkey).getString(0);
					}
					/*else{
						if(HeaderName.isEmpty()){
						if(sorrespobj.has("responseHeader")){
							JSONObject responseHeader=sorrespobj.getJSONObject("responseHeader");
							if(responseHeader.has("params")){
							   JSONObject params=responseHeader.getJSONObject("params");
							if(params.has("q")){
								String q=params.getString("q");
								//System.out.println("q:: "+q);
								
								   String colon = ":";
								   String keyNotHasSynnomes= q.substring(q.lastIndexOf(colon) + 1);
								   
								   HeaderName=keyNotHasSynnomes;
							}
							}
							
						}
						}
						
					}*/
				}
			}

		} catch (Exception e) {
			// e.printStackTrace();
			return HeaderName;

		}

		return HeaderName;
	}

	public static String reporttypeSolr(String newJson) {
		String ReportType = "";
		try {

			JSONObject newJsonObj = new JSONObject(newJson);
			Iterator<String> keys = newJsonObj.keys();
			JSONArray allReportJsonArray = null;
			while (keys.hasNext()) {
				String key = keys.next();
				Object keyValue = newJsonObj.get(key);
				if (keyValue instanceof JSONArray) {
					allReportJsonArray = (JSONArray) keyValue;
					if (allReportJsonArray.length() > 0) {

						HashMap<Integer, Integer> keycount = new HashMap<Integer, Integer>();
						for (int maxkeycount = 0; maxkeycount < allReportJsonArray.length(); maxkeycount++) {
							// System.out.println("count: "+maxkeycount);
							JSONObject tempjson = allReportJsonArray.getJSONObject(maxkeycount);
							// System.out.println("tempjsonlength: "+tempjson);
							keycount.put(tempjson.length(), maxkeycount);
							// System.out.println("finalkeycount: "+keycount);
						}

						int maxlength = (int) Collections.max(keycount.keySet());
						// System.out.println("maxlengh: "+maxlength);
						int maxlengthposition = keycount.get(maxlength);
						// System.out.println("maxlengthposition:
						// "+maxlengthposition);

						JSONObject temp_subobj = allReportJsonArray.getJSONObject(maxlengthposition);
						// System.out.println("maxJson: "+temp_subobj);

						Iterator<String> itr = temp_subobj.keys();
						HashMap<String, String> keyValueForReportType = new HashMap<String, String>();
						String data = "";

						while (itr.hasNext()) {
							String reportJsonKey = itr.next();
							if ((!reportJsonKey.equals("")) || (reportJsonKey != null)) {
								if (data == "") {
									data = "\"" + reportJsonKey.replace(" ", "_") + "\"";
								} else {
									data = data + " And \"" + reportJsonKey.replace(" ", "_") + "\"";
								}
							}
						}

						// System.out.println("data " + data);
						String resp = fetchSolrDataForReportType(data);
						ReportType = parsesolrresp(resp, "ReportType");
						// System.out.println("ReportType " + ReportType);
					}
				}
			}

		} catch (Exception e) {

		}
		return ReportType;
	}

	public static String fetchSolrDataForReportType(String data) {

		// String f1 = "*,score";
		StringBuffer response1 = null;

		try {
			// data = data.replace(" ", "%20");
			data = URLEncoder.encode(data, "UTF-8");

			String url = "";

			// url =
			// "http://35.231.163.191:8983/solr/Header_Synomes/select?q=Data:" +
			// data + "&fl=score,HeaderName";
			url = "http://35.231.163.191:8983/solr/ReportType_Recognizer/select?fl=score,ReportType&defType=dismax&mm="
					+ 4 + "&pf=Data&&q=Data:(" + data + ")&&qf=Data";

			URL url1 = new URL(url);
			HttpURLConnection con = (HttpURLConnection) url1.openConnection();
			// int responseCode = con.getResponseCode();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			response1 = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response1.append(inputLine);
			}
		  //System.out.println(response1);
			in.close();

		} catch (Exception e) {
			// e.printStackTrace();
			//System.out.println(e.getMessage());
			return "";
		}
		return response1.toString();

	}

	public static String finalJson(String newJson, String ReportType) {
		String newJsonString = "";
		try {

			JSONObject newJsonObj = new JSONObject(newJson);
			Iterator<String> keys = newJsonObj.keys();
			JSONArray allReportJsonArray = null;
			JSONArray newJSonArray = new JSONArray();
			while (keys.hasNext()) {
				String key = keys.next();
				Object keyValue = newJsonObj.get(key);
				if (keyValue instanceof JSONArray) {
					allReportJsonArray = (JSONArray) keyValue;
					if (allReportJsonArray.length() > 0) {

						for (int i = 0; i < allReportJsonArray.length(); i++) {
							JSONObject singleObj = allReportJsonArray.getJSONObject(i);
							singleObj.put("ReportType", ReportType);
							newJSonArray.put(singleObj);
						}

						newJsonObj.put(key, newJSonArray);
						// System.out.println(newJsonObj);
						newJsonString = newJsonObj.toString();
					}
				}
			}

		} catch (Exception e) {

		}
		return newJsonString;
	}
	
	public static String finalJSnCheckUnknown(String newJson, Map<String, String> map) {
		String newJsonString = "";
		try {

			JSONObject newJsonObj = new JSONObject(newJson);
			Iterator<String> keys = newJsonObj.keys();
			JSONArray allReportJsonArray = null;
			JSONArray newJSonArray = new JSONArray();
			while (keys.hasNext()) {
				String key = keys.next();
				Object keyValue = newJsonObj.get(key);
				if (keyValue instanceof JSONArray) {
					allReportJsonArray = (JSONArray) keyValue;
					if (allReportJsonArray.length() > 0) {

						JSONObject newObj=null;
						for (int i = 0; i < allReportJsonArray.length(); i++) {
							JSONObject singleObj = allReportJsonArray.getJSONObject(i);
//							System.out.println(singleObj);
							newObj=new JSONObject();
							Iterator<String>itr=singleObj.keys();
							while(itr.hasNext()){
								  String dynamicKeys=itr.next();
								//  System.out.println(dynamicKeys);
//								 boolean checkKey= getEqualsValue(map, "");
								  
								  if("Tonnage".equals(singleObj.getString("ReportType"))){
									 // System.out.println("yes");
								 for (Map.Entry<String, String> entry : map.entrySet())
								  {
//								      System.out.println(entry.getKey() + "/" + entry.getValue());
									 if(map.containsKey(dynamicKeys)){
										 
									  if (Objects.equals("Date", entry.getValue())) {
										 // singleObj.put(entry.getValue(), singleObj.getString(entry.getKey()));
										 /* newObj.put(entry.getValue(), singleObj.getString(entry.getKey()));*/
										  newObj.put("OpenDate", singleObj.getString(entry.getKey()));
										  newObj.remove(entry.getKey());
										 
									  }
									  
									     else if(Objects.equals("Port", entry.getValue())){
											 newObj.put(entry.getValue(), singleObj.getString(entry.getKey()));
											 newObj.remove(entry.getKey());
										 }else if(Objects.equals("VesselName", entry.getValue())){
											 newObj.put(entry.getValue(), singleObj.getString(entry.getKey()));
											 newObj.remove(entry.getKey());
										 }else if(Objects.equals("Status", entry.getValue())){
											 newObj.put(entry.getValue(), singleObj.getString(entry.getKey()));
											 newObj.remove(entry.getKey());
										 }else if(Objects.equals("VesselType", entry.getValue())){
											 newObj.put(entry.getValue(), singleObj.getString(entry.getKey()));
											 newObj.remove(entry.getKey());
										 }else if(Objects.equals("Owners", entry.getValue())){
											 newObj.put(entry.getValue(), singleObj.getString(entry.getKey()));
											 newObj.remove(entry.getKey());
										 }else if(Objects.equals("Operators", entry.getValue())){
											 newObj.put(entry.getValue(), singleObj.getString(entry.getKey()));
											 newObj.remove(entry.getKey());
										 }
										 
								  }
									 else{
									  newObj.put(dynamicKeys, singleObj.getString(dynamicKeys));
								  }
									 newJSonArray.put(newObj);
									 
								  }
								  }  else{
									        // System.out.println("no");
			                            	 newJsonString=newJson;
			                             
								  }
								  
							}// while
							
//							singleObj.put("ReportType", ReportType);
							
						}
                             if(newJSonArray.length()>0){
                            	// System.out.println("inside");
						        newJsonObj.put(key, newJSonArray);
						// System.out.println(newJsonObj);
						        newJsonString = newJsonObj.toString();
                             }
					}
				}
			}

		} catch (Exception e) {
           e.printStackTrace();
		}
		return newJsonString;
	}

}
