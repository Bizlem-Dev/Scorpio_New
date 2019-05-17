package com.subjectheaders;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

public class PortSlashFoundCheck {

	public static void main(String[] args) {
		String q="AG/E.AFR";
		
		 Pattern id=null;
		  Matcher m=null;
		  
		 // String url="EASTPORT CLEAN MR POSITION BSS S.KOREA AS AT 15th MAR 2019";
		   id = Pattern.compile("([A-Za-z]{2,15}\\/[A-Za-z].[A-Za-z]{1,15})");
		   m = id.matcher(q);
	    	if (m.find()){
	    		String etaBasisDate = m.group(0);
	    	    System.out.println("alag0:: "+etaBasisDate);
	    	    
	    	}
		
		/*String solrResp=fetchsolrdata(q);
		boolean portFoundSlashCheck=parsesolrresp(solrResp);
		if(portFoundSlashCheck){
			System.out.println("yes");
		}*/
	}
	
	public static String fetchsolrdata(String q) {
	    //  System.out.println(q);
			StringBuffer response1 = null;
			try {
				 q=q.toUpperCase();
				 q=q.replaceAll(" ", "_");
				 System.out.println(q);
				 q = URLEncoder.encode(q, "UTF-8");
				 String url = "";

				url = "http://35.231.163.191:8983/solr/PortFound/select?fl=score,Key,value&defType=dismax&mm=1&pf=q&&q=value:("+q+")&&qf=value";
				
				URL url1 = new URL(url);
				HttpURLConnection con = (HttpURLConnection) url1.openConnection();

				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				response1 = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response1.append(inputLine);
				}
				//System.out.println(response1.toString());
				in.close();

			} catch (Exception e) {
				return e.getMessage();
			}
			return response1.toString();
			

		}
	
	public static boolean parsesolrresp(String solrResp) {
		boolean portFound=false;
		try {
			JSONObject sorrespobj = new JSONObject(solrResp);
			if (sorrespobj.has("response") && sorrespobj.getJSONObject("response").has("maxScore")
					&& sorrespobj.getJSONObject("response").has("docs")) {

				JSONObject Subsorrespobj = sorrespobj.getJSONObject("response");
				JSONArray docs = Subsorrespobj.getJSONArray("docs");
				for (int i = 0; i < docs.length(); i++) {
					 JSONObject subdocs = docs.getJSONObject(i);
					 String keyString="";
					 
					 if(subdocs.has("Key")){
						 JSONArray key= subdocs.getJSONArray("Key");
						  keyString= key.getString(0);
						  
						  if("Port".equals(keyString)){
							  portFound=true;
						  }
					 }
					 
				} // for close
				
			}

		} catch (Exception e) {
			
		}

		return portFound;
	}
	
		

}
