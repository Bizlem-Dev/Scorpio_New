package com.NewStructureScorpio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Stack;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;


public class ChartererUnknownAndCommentCheck {

	public static void main(String[] args) throws JSONException {
		 String q="LUKOIL=FLD";
		//String d="{\"StructureType\":\"paragraph\",\"VesselName\":\"SAUGER\",\"unknown1\":\"60\",\"CargoGrade\":\"ums\",\"unknown3\":\"J.ALI/KAZ\",\"Rate\":\"335K\",\"unknown6\":\"ATC=FXD/CORR\",\"ReportType\":\"Spot\",\"LCEnd\":\"25/03\",\"Charterer\":\" \",\"Status\":\" \",\"CargoQty\":\" \",\"LCStart\":\" \",\"LoadPort\":\" \",\"DiscPort\":\" \",\"CargoType\":\"\",\"RepositionRegion\":\"Best\",\"VesselType\":\"\"}";
		JSONObject data=chartererData(q);
		System.out.println(data);
	}
	
	public static JSONObject chartererData(String unknow_commentVariable){
		JSONObject finaldata=new JSONObject();
		try {
			
			Stack <JSONObject> chartererRep=new Stack<JSONObject>();
			
					String data=fetchsolrdata(unknow_commentVariable);
					JSONObject data1=parsesolrresp(data);
				      
					
					if(data1 != null && data1.length()>0){
						//System.out.println("g:: "+data1);
						if(data1.has("Charterer")){
							chartererRep.push(data1);	
							//System.out.println("Charterer Stack Size :: "+chartererRep.size());
						}
					}
					
				
			
			if(!chartererRep.empty() && chartererRep.size()>0 && chartererRep!=null){
				finaldata.put("Charterer",chartererRep.pop());
				//System.out.println("d: "+finaldata);
			}
			
			//System.out.println(finaldata);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return finaldata;
		
	}
	
	public static JSONObject parsesolrresp(String solrResp) {
//		String allData="";
		JSONObject keyValue=null;
		//System.out.println("resp "+resp);
		try {
			JSONObject sorrespobj = new JSONObject(solrResp);
			// JSONArray arrayKeyValueData=new JSONArray();
			if (sorrespobj.has("response") && sorrespobj.getJSONObject("response").has("maxScore")
					&& sorrespobj.getJSONObject("response").has("docs")) {

				JSONObject Subsorrespobj = sorrespobj.getJSONObject("response");
				JSONArray docs = Subsorrespobj.getJSONArray("docs");
//              if(docs.length()>0){
				for (int i = 0; i < docs.length(); i++) {
					 JSONObject subdocs = docs.getJSONObject(i);
					 String keyString="";
					 String valueString="";
					 if(subdocs.has("Key")){
						 JSONArray key= subdocs.getJSONArray("Key");
						  keyString= key.getString(0);
					 }
					 if(subdocs.has("value")){
						 JSONArray value= subdocs.getJSONArray("value");
						 valueString= value.getString(0);
					 }
					 keyValue=new JSONObject();
					 keyValue.put(keyString, valueString);
					 
					/* arrayKeyValueData.put(keyValue);*/
					
					
//					 System.out.println("key: "+ keyString +"  "+"value: "+ valueString);
				} // for close
				/*if(arrayKeyValueData.length()>0){
				   allData= arrayKeyValueData.toString();
				}*/
//			}// check length
			}

		} catch (Exception e) {
			//return allData;
                //e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return keyValue;
	}
	
	
	public static String fetchsolrdata(String q) {
	    //  System.out.println(q);
			StringBuffer response1 = null;
			try {
				 q=q.toUpperCase();
				 q = URLEncoder.encode(q, "UTF-8");
				 String url = "";

				url = "http://35.231.163.191:8983/solr/Charterer/select?fl=score,Key,value&defType=dismax&mm=1&pf=q&&q=value:("+q+")&&qf=value";
				
				URL url1 = new URL(url);
				System.setProperty("http.keepAlive","false");
				HttpURLConnection con = (HttpURLConnection) url1.openConnection();

				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				response1 = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response1.append(inputLine);
				}
				//System.out.println(response1.toString());
				in.close();
				con.disconnect();

			} catch (Exception e) {
				return e.getMessage();
			}
			return response1.toString();
			

		}
		
	

}
