package com.pallavi.code;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import com.reportinformationsystem.SaveReportDataClass;

public class pallavi_SortJSON {

	public static void main(String[] args) {
		try {
//			String s="{ \"Comment\": \"f.o.c.\", \"ReportType\": \"Tonnage\", \"VesselName\": \"SKS Tanaro\", \"unknown_0\": \"SKS Tanaro\", \"unknown_1\": \"109 000\", \"unknown_10\": \"f.o.c.\", \"unknown_2\": \"119 000\", \"unknown_3\": \"244\", \"unknown_4\": \"1999\", \"unknown_5\": \"12-Feb\", \"unknown_6\": \"Dubai\", \"unknown_7\": \"11-Feb\", \"unknown_8\": \"SKS\", \"unknown_9\": \"Cond/Fo/Crude\", \"x_unknown_0\": 87.480000000000004, \"x_unknown_1\": 210.75999999999999, \"x_unknown_10\": 790.73000000000002, \"x_unknown_2\": 262.62, \"x_unknown_3\": 319.19, \"x_unknown_4\": 351.52999999999997, \"x_unknown_5\": 395.25, \"x_unknown_6\": 429.64999999999998, \"x_unknown_7\": 552.32000000000005, \"x_unknown_8\": 586.69000000000005, \"x_unknown_9\": 714.63999999999999 }, { \"Comment\": \"unc./subs TC / no SIRE\", \"ReportType\": \"Tonnage\", \"VesselName\": \"Champion Princess\", \"unknown_0\": \"Champion Princess\", \"unknown_1\": \"105 258\", \"unknown_10\": \"unc./subs TC / no SIRE\", \"unknown_2\": \"119 000\", \"unknown_3\": \"244\", \"unknown_4\": \"2012\", \"unknown_5\": \"16-Feb\", \"unknown_6\": \"Fujairah\", \"unknown_7\": \"15-Feb\", \"unknown_8\": \"Nyk\", \"unknown_9\": \"Ums\", \"x_unknown_0\": 87.480000000000004, \"x_unknown_1\": 210.75999999999999, \"x_unknown_10\": 790.73000000000002, \"x_unknown_2\": 262.62, \"x_unknown_3\": 319.19, \"x_unknown_4\": 351.52999999999997, \"x_unknown_5\": 395.25, \"x_unknown_6\": 429.64999999999998, \"x_unknown_7\": 552.32000000000005, \"x_unknown_8\": 586.69000000000005, \"x_unknown_9\": 714.63999999999999 }";
//			
//			JSONObject obj= new JSONObject(s);
//			LinkedHashMap hmap=null;
//			pallavi_SortJSON sj= new pallavi_SortJSON();
			
			//hmap= sj.sortJSON(obj);
			
			//System.out.println("****: "+hmap);
			
			
			
//			LinkedHashMap<String, String> hmap = new LinkedHashMap<String, String>();
//			
//			hmap.put("a", "sdffd");
//			hmap.put("b", "iudffd");
//			hmap.put("1", "dfgfd");
//			hmap.put("11", "dfgfd");
//
//			hmap.put("6", "dfgfd");
//			hmap.put("12", "dfgfd");
//			hmap.put("c", "dfgfd");
//
//			System.out.println("****: "+hmap);
//

			
//			String a= "{\"DiscPort\":\"MED\",\"unknown8\":\"\",\"GDE_x\":272.09,\"unknown7\":\"\",\"unknown6\":\"MED\",\"unknown_5\":\"BSEA\",\"CHRTS_x\":38.64,\"CargoGrade\":\"DPP\",\"unknown_9\":\"\",\"QTY_x\":245.57,\"unknown_15\":\"\",\"unknown_14\":\"165\",\"unknown_17\":\"\",\"unknown_16\":\"\",\"unknown_11\":\"\",\"unknown_10\":\"DNR\",\"unknown_13\":\"\",\"unknown_12\":\"WS\",\"ReportType\":\"Spot\",\"Charterer\":\"CNR\",\"DISCH_x\":375.31,\"CargoQty\":\"45\",\"VESSEL_x\":156.02,\"VesselName\":\"KRITI ROCK\",\"unknown_0\":\"CNR\",\"unknown_4\":\"DPP\",\"unknown_3\":\"45\",\"unknown_2\":\"KRITI ROCK\",\"LOAD_x\":309.79,\"unknown_1\":\"SUBS\",\"LoadPort\":\"BSEA\"}";
//			JSONObject obj=new JSONObject(a);
//			
//			LinkedHashMap obj1=pallavi_SortJSON.sortJSONwithout_inkey(obj);
//			System.out.println(obj1);
			
//			JSONArray arr= new JSONArray();
//			for(int i=1; i<13; i++){
//				for(int j=1; j<32; j++){
//						String month="";
//						String day="";
//						if(i<9) {month="0"+i;}else {month = ""+i;}
//						if(j<9) {day="0"+j;}else {day = ""+j;}
//                           arr.put("2019-"+month+"-"+day);
//					
//				}
//			}
//			System.out.println(arr);
			
//			HashMap <Integer,Integer >fonfkeycount = new HashMap<Integer,Integer >();
//			fonfkeycount.put(1, 8);
//			fonfkeycount.put(6, 7);
//			fonfkeycount.put(4, 9);
//			fonfkeycount.put(9, 3);
//			fonfkeycount.put(2, 6);
//
//			
//			System.out.println("fonfkeycount "+fonfkeycount);
//       int maxlength= (int) Collections.max(fonfkeycount.keySet());
//       int maxlengthposition= fonfkeycount.get(maxlength);
//		System.out.println("maxlength "+maxlength+" maxlengthposition "+maxlengthposition);

			
			String res= executesolrQuery_getMethodName("LoadPort".toUpperCase(), "LoadPort".toUpperCase(), "word/word".toUpperCase(), " ","METHODNAME");
			System.out.println(res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//public static LinkedHashMap sortJSON(JSONObject obj, PrintWriter out){
		public static LinkedHashMap sortJSON(JSONObject obj){

		//out.println("obj "+obj);
		LinkedHashMap<String, String> hmap=null;
		try {
		Iterator<String> iterator= obj.keys();
		JSONObject obj1= new JSONObject();
		while (iterator.hasNext()) {
			String key_obj = iterator.next();
			if((!key_obj.contains("x_unknown")) && (!key_obj.contains("_x")) ) {
				
					obj.get(key_obj);
					obj1.put(key_obj, obj.get(key_obj));
				
				//obj.remove(key_obj);
			}
		} 
	//	out.println("**"+obj1);
		
		Iterator<String> iterator1= obj1.keys();
		
		JSONObject obj2= new JSONObject();
		 hmap = new LinkedHashMap<String, String>();
		while (iterator1.hasNext()) {
			String key_obj = iterator1.next();
			if(key_obj.contains("unknown")) {
				
					obj1.get(key_obj);
					System.out.println(obj1.get(key_obj));
					obj2.put(key_obj, obj1.get(key_obj));					
				
			}
			//
		}
		//out.println("obj2: "+obj2);
		
		Iterator<String> iterator2= obj2.keys();
		ArrayList<Integer> arr= new ArrayList<>();
		while (iterator2.hasNext()) {
			String key_obj = iterator2.next();
			
			String sub_key= key_obj.substring(key_obj.indexOf("_")).replace("_", "");
		//	out.println(sub_key);
			arr.add(Integer.parseInt(sub_key));
			Collections.sort(arr); 
		}
			for(int i=0; i<arr.size(); i++) {
				String key_new= "unknown_"+arr.get(i);			
				String val_obj2;
			
					val_obj2 = obj2.getString(key_new);
					hmap.put(key_new, val_obj2);
								
			}
			//out.println("arr: "+arr);
			System.out.println("hmap: "+hmap);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//out.println("sortJSON  1 "+e.getMessage());

			//out.println("sortJSON 2 "+e);
			//e.printStackTrace();
		}
		
		
	
		return hmap;

	}
		public static LinkedHashMap sortJSONwithout_inkey(JSONObject obj){

			//out.println("obj "+obj);
			LinkedHashMap<String, String> hmap=null;
			try {
			Iterator<String> iterator= obj.keys();
			JSONObject obj1= new JSONObject();
			while (iterator.hasNext()) {
				String key_obj = iterator.next();
				if((!key_obj.contains("x_unknown")) && (!key_obj.contains("_x")) ) {
					
						obj.get(key_obj);
						obj1.put(key_obj, obj.get(key_obj));
					
					//obj.remove(key_obj);
				}
			} 
		//	out.println("**"+obj1);
			
			Iterator<String> iterator1= obj1.keys();
			
			JSONObject obj2= new JSONObject();
			 hmap = new LinkedHashMap<String, String>();
			while (iterator1.hasNext()) {
				String key_obj = iterator1.next();
				if(key_obj.contains("unknown")) {
					
						obj1.get(key_obj);
						//System.out.println(obj1.get(key_obj));
						obj2.put(key_obj, obj1.get(key_obj));					
					
				}
				//
			}
		//System.out.println("obj2: "+obj2);
			
			Iterator<String> iterator2= obj2.keys();
			ArrayList<Integer> arr= new ArrayList<>();
			while (iterator2.hasNext()) {
				String key_obj = iterator2.next();
				
				String sub_key="";
				if(sub_key.contains("_")) {		
					sub_key= key_obj.substring(key_obj.indexOf("_")).replace("_", "");
                     }else {
				 sub_key= key_obj.substring(key_obj.length()-1);
				}
			//	System.out.println("subkey "+sub_key);
				arr.add(Integer.parseInt(sub_key));
				Collections.sort(arr); 
			}
				for(int i=0; i<arr.size(); i++) {
					
					String key_new= "";	
					if(obj2.has("unknown"+arr.get(i))) {
						key_new= "unknown"+arr.get(i);
						String val_obj2;
						
						val_obj2 = obj2.getString(key_new);
						hmap.put(key_new, val_obj2);
					}else if(obj2.has("unknown_"+arr.get(i))) {
						key_new= "unknown_"+arr.get(i);

						String val_obj2;
						
						val_obj2 = obj2.getString(key_new);
						hmap.put(key_new, val_obj2);
					}
					
									
				}
				//System.out.println("arr: "+arr);
				//System.out.println("hmap ***: "+hmap);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				//out.println("sortJSON  1 "+e.getMessage());

				//out.println("sortJSON 2 "+e);
				e.printStackTrace();
			}
			
			
		
			return hmap;

		}
		
		public static JSONObject getOnkyKnownkeys(JSONObject obj){
			Iterator<String> iterator= obj.keys();
			JSONObject obj1= new JSONObject();
			while (iterator.hasNext()) {
				String key_obj = iterator.next();
				if(!(key_obj.contains("x_unknown") || key_obj.contains("unknown") || key_obj.contains("_x") )) {
					
						try {
							obj.get(key_obj);
							obj1.put(key_obj, obj.get(key_obj));

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					//obj.remove(key_obj);
				}
			}
			return obj1;
		}
		public static JSONObject getOnkyUNKnownkeys(JSONObject obj){
			Iterator<String> iterator= obj.keys();
			JSONObject obj1= new JSONObject();
			while (iterator.hasNext()) {
				String key_obj = iterator.next();
				if( !key_obj.contains("x_unknown")  &&  !key_obj.contains("_x")  &&  key_obj.contains("unknown")) {
					
						try {
							obj.get(key_obj);
							obj1.put(key_obj, obj.get(key_obj));

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					//obj.remove(key_obj);
				}
			}
			return obj1;
		}
	
		
		
		public static JSONObject replaceheaderdata(JSONObject UNknownjson_copy, JSONObject header_data){
		try {
			Iterator itr3 = header_data.keys();
			while (itr3.hasNext()) {
				System.out.println("headeriterator");
				String headerkey = (String) itr3.next();
				System.out.println("headerkey " + headerkey);
				if (!UNknownjson_copy.has(headerkey)) {
					System.out.println("in header");

					UNknownjson_copy.put(headerkey, header_data.get(headerkey));

				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return UNknownjson_copy;

		}
		return UNknownjson_copy;

	}

        
		
		
		
		
		
		
		//public static JSONObject replacesolarkeys(JSONObject Ukwjson, LinkedHashMap Ukwhmap,PrintWriter out){
			public static JSONObject replacesolarkeys(JSONObject Ukwjson, LinkedHashMap Ukwhmap){

//			String str= "{\"unknown_1\":\"109 000\",\"unknown_2\":\"119 000\",\"unknown_3\":\"244\",\"unknown_4\":\"1999\",\"unknown_5\":\"12-Feb\",\"unknown_6\":\"Dubai\",\"unknown_7\":\"11-Feb\",\"unknown_8\":\"SKS\",\"unknown_9\":\"Cond/Fo/Crude\",\"Comment\":\"f.o.c.\",\"VesselName\":\"SKS Tanaro\"}";

		try {
			
			System.out.println("Ukwjson  "+Ukwjson);
			System.out.println("Ukwhmap  "+Ukwhmap);

		/*JSONObject UNknownjson_copy= new JSONObject(str);
		LinkedHashMap<String, String> copyoflhmap= new LinkedHashMap<>();
		copyoflhmap.put("unknown_0", "vesselName");
		copyoflhmap.put("unknown_1", "");
		copyoflhmap.put("unknown_2", "");
		copyoflhmap.put("unknown_3", "");
		copyoflhmap.put("unknown_4", "Date");
		copyoflhmap.put("unknown_5", "ETABasis");
		copyoflhmap.put("unknown_6", "Port");
		copyoflhmap.put("unknown_7", "Open Date");
		copyoflhmap.put("unknown_8", "Operators");
		copyoflhmap.put("unknown_9", "");
		copyoflhmap.put("unknown_10", "Status");
		*/
		Set set = Ukwhmap.entrySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
		Map.Entry item = (Map.Entry) iterator.next();
		String finalkey=(String) item.getKey();
		String finalvalue=(String) item.getValue();
		System.out.println("finalkey = " + finalkey + " finalvalue = " + finalvalue);
		System.out.println(Ukwjson.has(finalkey));
		System.out.println(!finalvalue.equals(""));
		System.out.println(finalvalue!=null);
		System.out.println(Ukwjson.has(finalkey) && finalvalue!="" && finalvalue!=null);

		if(Ukwjson.has(finalkey) && (!finalvalue.equals("")) && finalvalue!=null) {
		String finalkeyjsonvaue=Ukwjson.getString(finalkey);
		if(!Ukwjson.has(finalvalue)) {
		Ukwjson.put(finalvalue, finalkeyjsonvaue);
		Ukwjson.remove(finalkey);
		//System.out.println("UNknownjson_copy * "+UNknownjson_copy);
		}
		}
		}
		//System.out.println("UNknownjson_copy 2 "+UNknownjson_copy);
		} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return Ukwjson;


		}
	//public static String executesolrQuery(String data,PrintWriter out) throws UnsupportedEncodingException {
		public static String executesolrQuery(String data, int mm) throws UnsupportedEncodingException {

		int responseCode = 0;
		String urlParameters = "";
		StringBuffer response = new StringBuffer();

		try {

			// String url1 =
			// "http://35.188.227.168:8983/solr/Vessel/update/json/docs?commit=true";
			 data = URLEncoder.encode(data, "UTF-8");

			//String url1 = "http://35.188.227.168:8983/solr/AllReport/select?fl=score,DocumentName,url&q=Data:"+data+"&rows=3";
					
			String url1="http://35.188.227.168:8983/solr/AllReport/select?fl=score,DocumentName&defType=dismax&mm="+mm+"&pf=Data&&q=Data:("+data+")&&qf=Data";		
			//	 param = URLEncoder.encode(param, "UTF-8");

			URL url = new URL(url1);
			
			System.out.println("url ****  "+url1);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);

			
			responseCode = con.getResponseCode();
			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			//System.out.println(response.toString());
		}
		catch (Exception e) {

			//e.printStackTrace();
		 System.out.println(e.getMessage());
		}
		return response.toString();
	}
		public static String executesolrQuery_bywords(String data) throws UnsupportedEncodingException {

			int responseCode = 0;
			StringBuffer response = new StringBuffer();
       	 if (data.contains("00:00:00")) {
       		data=data.replace("00:00:00", "");
       		data=data.trim();
        	}

			try {
				// "http://35.188.227.168:8983/solr/Vessel/update/json/docs?commit=true";
				 data = URLEncoder.encode(data, "UTF-8");
				String url1 = "http://35.188.227.168:8983/solr/AllReport/select?fl=score,DocumentName,url&q=Data_str:"+data+"&rows=3";	
				//String url1="http://35.188.227.168:8983/solr/AllReport/select?fl=score,DocumentName&defType=dismax&mm="+mm+"&pf=Data&&q=Data:("+data+")&&qf=Data";		
				//	 param = URLEncoder.encode(param, "UTF-8");
				URL url = new URL(url1);				
				//System.out.println("url ****  "+url1);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("Content-Type", "application/json");
				con.setDoOutput(true);			
				responseCode = con.getResponseCode();
				BufferedReader in = new BufferedReader(
						new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				//System.out.println(response.toString());
			}
			catch (Exception e) {

				//e.printStackTrace();
			 System.out.println(e.getMessage());
			 return new JSONObject().toString();
			}
			return response.toString();
		}
		public static String executesolrQuery_getMethodName(String jsonheader , String synonyms, String regexmatch, 
				String solrmatch, String key) throws UnsupportedEncodingException {

			int responseCode = 0;
			String MethodName="Blank";
			StringBuffer response = new StringBuffer();
       	String data="JSONHEADER_str:\""+jsonheader+"\" AND SYNONYMS_str:\""+synonyms+"\" AND REGAXMATCH_str:\""+regexmatch+"\" AND SOLRMATCH_str:\""+solrmatch+"\"";
       //	System.out.println("data "+data);
			try {
				// "http://35.188.227.168:8983/solr/Vessel/update/json/docs?commit=true";
				 data = URLEncoder.encode(data, "UTF-8");
				//String url1 = "http://35.188.227.168:8983/solr/AllReport/select?fl=score,DocumentName,url&q=Data_str:"+data+"&rows=3";
				//String url1="http://35.188.227.168:8983/solr/AllReport/select?fl=score,DocumentName&defType=dismax&mm="+mm+"&pf=Data&&q=Data:("+data+")&&qf=Data";		
				//	 param = URLEncoder.encode(param, "UTF-8");
                 String url1="http://35.188.227.168:8983/solr/RegexMatch/select?fl=*,score&q="+data;
				URL url = new URL(url1);
				System.out.println("url ****  "+url1);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("Content-Type", "application/json");
				con.setDoOutput(true);
				responseCode = con.getResponseCode();
				BufferedReader in = new BufferedReader(
						new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				//System.out.println(response.toString());
				 JSONObject sorrespobj = new JSONObject(response.toString());
		         if(sorrespobj.has("response") && sorrespobj.getJSONObject("response").has("maxScore") && sorrespobj.getJSONObject("response").has("docs")) {
		       // System.out.println("in parse json ********");
		         JSONObject Subsorrespobj=sorrespobj.getJSONObject("response");
		         JSONArray docs=Subsorrespobj.getJSONArray("docs");
		         double maxscore=Subsorrespobj.getDouble("maxScore");
		        // System.out.println("maxscore "+maxscore);

		         
		       for(int i=0;i<docs.length();i++) {
		        JSONObject subdocs=docs.getJSONObject(i);
		        double score=subdocs.getDouble("score");
		        if(score==maxscore) {
		        	//System.out.println("maxscore "+maxscore+" score   "+score);
		      	   MethodName=subdocs.getJSONArray(key).getString(0);
		        }
		        }
		         }	
			}
			catch (Exception e) {
				//e.printStackTrace();
			 System.out.println(e.getMessage());
			}
			return MethodName;
		}

		public static String executesolrQuery_GetTemplate(String data, int mm) throws UnsupportedEncodingException {

			int responseCode = 0;
			String urlParameters = "";
			StringBuffer response = new StringBuffer();

			try {

				// String url1 =
				// "http://35.188.227.168:8983/solr/Vessel/update/json/docs?commit=true";
				 data = URLEncoder.encode(data, "UTF-8");

				//String url1 = "http://35.188.227.168:8983/solr/AllReport/select?fl=score,DocumentName,url&q=Data:"+data+"&rows=3";
						
					//String url1="http://35.188.227.168:8983/solr/ReportType_Recognizer/select?fl=score,ReportType&defType=dismax&mm="+mm+"&pf=Data&&q=Data:("+data+")&&qf=Data";		
String url1="http://35.188.227.168:8983/solr/ColumnMatch/select?fl=score,ReportType,RequiredColumns&q=("+data+")&rows=3";
				// String url1="http://35.188.227.168:8983/solr/AllReportData/select?fl=score,ReportType&defType=dismax&mm="+mm+"&pf=Data&&q=Data:("+data+")&&qf=Data";		
				//	 param = URLEncoder.encode(param, "UTF-8");

				URL url = new URL(url1);
				
				System.out.println("url ****  "+url1);

				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("Content-Type", "application/json");
				con.setDoOutput(true);

				
				responseCode = con.getResponseCode();
				BufferedReader in = new BufferedReader(
						new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				//System.out.println(response.toString());
			}
			catch (Exception e) {

				//e.printStackTrace();
			 System.out.println(e.getMessage());
			}
			return response.toString();
		}
		
		
		public static String executesolrQuery_solarheader(String data, int mm) throws UnsupportedEncodingException {

			int responseCode = 0;
			String urlParameters = "";
			StringBuffer response = new StringBuffer();

			try {

				// String url1 =
				// "http://35.188.227.168:8983/solr/Vessel/update/json/docs?commit=true";
				 data = URLEncoder.encode(data, "UTF-8");

				//String url1 = "http://35.188.227.168:8983/solr/AllReport/select?fl=score,DocumentName,url&q=Data:"+data+"&rows=3";
						
					String url1="http://35.188.227.168:8983/solr/ReportType_Recognizer/select?fl=score,ReportType&defType=dismax&mm="+mm+"&pf=Data&&q=Data:("+data+")&&qf=Data";		

				// String url1="http://35.188.227.168:8983/solr/AllReportData/select?fl=score,ReportType&defType=dismax&mm="+mm+"&pf=Data&&q=Data:("+data+")&&qf=Data";		
				//	 param = URLEncoder.encode(param, "UTF-8");

				URL url = new URL(url1);
				
				System.out.println("url ****  "+url1);

				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("Content-Type", "application/json");
				con.setDoOutput(true);

				
				responseCode = con.getResponseCode();
				BufferedReader in = new BufferedReader(
						new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				//System.out.println(response.toString());
			}
			catch (Exception e) {

				//e.printStackTrace();
			 System.out.println(e.getMessage());
			}
			return response.toString();
		}
		
		
		
		
	
	//public static String parsesolrresp(String resp,PrintWriter out) {
		public static JSONObject parsesolrresp_updated(String resp, String key, String Extrakey) {
          JSONObject obj = new JSONObject();
	   	  String DocumentName=" ";
	   	  String Extrakeyvalue="";
			try {
			 JSONObject sorrespobj = new JSONObject(resp);
			 
//		        System.out.println(sorrespobj.has("response"));
//		        System.out.println(sorrespobj.getJSONObject("response").has("maxScore"));
//		        System.out.println(sorrespobj.getJSONObject("response").has("docs"));
//		        System.out.println(sorrespobj.has("response") && sorrespobj.getJSONObject("response").has("maxScore") && sorrespobj.getJSONObject("response").has("docs"));

			 
	         if(sorrespobj.has("response") && sorrespobj.getJSONObject("response").has("maxScore") && sorrespobj.getJSONObject("response").has("docs")) {
	       // System.out.println("in parse json ********");
	         JSONObject Subsorrespobj=sorrespobj.getJSONObject("response");
	         JSONArray docs=Subsorrespobj.getJSONArray("docs");
	         double maxscore=Subsorrespobj.getDouble("maxScore");
	       //  System.out.println("maxscore "+maxscore);

	         
	       for(int i=0;i<docs.length();i++) {
	        JSONObject subdocs=docs.getJSONObject(i);
	        double score=subdocs.getDouble("score");
	        if(score==maxscore) {
	        	//System.out.println("maxscore "+maxscore+" score   "+score);
                //   System.out.println("subdocs  "+subdocs);
	      	   DocumentName=subdocs.getJSONArray(key).getString(0);
	      	   if(subdocs.has(Extrakey)) {
	      	 Extrakeyvalue=subdocs.getJSONArray(Extrakey).getString(0);
	      	   }
	        }
	        }
	         }	 
	         
			 obj.put(key, DocumentName);
			 if(!Extrakeyvalue.equalsIgnoreCase("")) {
			 obj.put(Extrakey, Extrakeyvalue);
			 }
		}catch(Exception e) {
			//e.printStackTrace();
			return null;

		}
			
			return obj;
			}
		
		public static String parsesolrresp(String resp, String key) {

		   	  String DocumentName=" ";
		   	  String Extrakeyvalue="";
				try {
				 JSONObject sorrespobj = new JSONObject(resp);
				 
//			        System.out.println(sorrespobj.has("response"));
//			        System.out.println(sorrespobj.getJSONObject("response").has("maxScore"));
//			        System.out.println(sorrespobj.getJSONObject("response").has("docs"));
//			        System.out.println(sorrespobj.has("response") && sorrespobj.getJSONObject("response").has("maxScore") && sorrespobj.getJSONObject("response").has("docs"));

				 
		         if(sorrespobj.has("response") && sorrespobj.getJSONObject("response").has("maxScore") && sorrespobj.getJSONObject("response").has("docs")) {
		       // System.out.println("in parse json ********");
		         JSONObject Subsorrespobj=sorrespobj.getJSONObject("response");
		         JSONArray docs=Subsorrespobj.getJSONArray("docs");
		         double maxscore=Subsorrespobj.getDouble("maxScore");
		       //  System.out.println("maxscore "+maxscore);

		         
		       for(int i=0;i<docs.length();i++) {
		        JSONObject subdocs=docs.getJSONObject(i);
		        double score=subdocs.getDouble("score");
		        if(score==maxscore) {
		        	//System.out.println("maxscore "+maxscore+" score   "+score);

		      	   DocumentName=subdocs.getJSONArray(key).getString(0);
		        }
		        }
		         }	 
		         
			}catch(Exception e) {
				//e.printStackTrace();
				return DocumentName;

			}
				
				return DocumentName;
				}
			
		
		public static JSONObject parsesolrrespfortemplate(String resp) {

		   	  String DocumentName="";
		   	  String ReportType="";
		   	JSONObject RequiredColumnsobj=null;
				try {
				 JSONObject sorrespobj = new JSONObject(resp);
				 
//			        System.out.println(sorrespobj.has("response"));
//			        System.out.println(sorrespobj.getJSONObject("response").has("maxScore"));
//			        System.out.println(sorrespobj.getJSONObject("response").has("docs"));
//			        System.out.println(sorrespobj.has("response") && sorrespobj.getJSONObject("response").has("maxScore") && sorrespobj.getJSONObject("response").has("docs"));

				 
		         if(sorrespobj.has("response") && sorrespobj.getJSONObject("response").has("maxScore") && sorrespobj.getJSONObject("response").has("docs")) {
		        System.out.println("in parse json ********");
		         JSONObject Subsorrespobj=sorrespobj.getJSONObject("response");
		         JSONArray docs=Subsorrespobj.getJSONArray("docs");
		         double maxscore=Subsorrespobj.getDouble("maxScore");
		         System.out.println("maxscore "+maxscore);

		         
		       for(int i=0;i<docs.length();i++) {
		        JSONObject subdocs=docs.getJSONObject(i);
		        double score=subdocs.getDouble("score");
		        if(score==maxscore) {
		        	System.out.println("maxscore "+maxscore+" score   "+score);
			      	   ReportType=subdocs.getJSONArray("ReportType").getString(0);
		      	   DocumentName=subdocs.getJSONArray("RequiredColumns").getString(0);
		      	   if(SaveReportDataClass.isJSONValid(DocumentName)) {
		      		  RequiredColumnsobj = new JSONObject(DocumentName);
		      		RequiredColumnsobj.put("ReportType", ReportType);
		      	   }
		      	   
		      	   
		        }
		        }
		         }	 
		         return RequiredColumnsobj;
			}catch(Exception e) {
				//e.printStackTrace();
				return null;

			}
				
				
				}
			
	
	
	public static <T, E> ArrayList<String> getKeysByValue(Map<T, E> map, E value) {
		ArrayList<String> arr= new ArrayList<>();

	    Set<T> keys = new HashSet<T>();
	    for (Entry<T, E> entry : map.entrySet()) {
	        if (Objects.equals(value, entry.getValue())) {
	            keys.add(entry.getKey());
	        }
	    }
	    arr =  pallavi_SortJSON.compareSet( keys);
	    
	    return arr;
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
	
	
	
	
}