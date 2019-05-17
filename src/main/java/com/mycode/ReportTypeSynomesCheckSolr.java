package com.mycode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

public class ReportTypeSynomesCheckSolr {
	public static void main(String[] args) {
		String resp=fetchsolrdata("position list");
		String reportName=parsesolrresp(resp);
		System.out.println("reportName:: "+reportName);
	}
	public static String fetchsolrdata(String passvalue){
		StringBuffer response1=null;
		try {
			
			passvalue=passvalue.toUpperCase();
			passvalue=passvalue.replaceAll(" ", "_");
			//System.out.println(passvalue);
			
			passvalue = URLEncoder.encode(passvalue, "UTF-8");
			
			String url = "";
			url = "http://35.231.163.191:8983/solr/ReportType_Synomes/select?q=Data_str:" + passvalue + "&fl=score,HeaderName";
			
			URL url1 = new URL(url);
			HttpURLConnection con = (HttpURLConnection) url1.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			 response1 = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response1.append(inputLine);
			}
			//System.out.println(response1);
			in.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return response1.toString();
	}
	
	public static String parsesolrresp(String resp) {
		String HeaderName = "";
		try {
			JSONObject sorrespobj = new JSONObject(resp);

			if (sorrespobj.has("response") && sorrespobj.getJSONObject("response").has("maxScore")
					&& sorrespobj.getJSONObject("response").has("docs")) {

				JSONObject Subsorrespobj = sorrespobj.getJSONObject("response");
				JSONArray docs = Subsorrespobj.getJSONArray("docs");
				double maxscore = Subsorrespobj.getDouble("maxScore");
				for (int i = 0; i < docs.length(); i++) {
					JSONObject subdocs = docs.getJSONObject(i);
					double score = subdocs.getDouble("score");
					if (score == maxscore) {
						HeaderName = subdocs.getJSONArray("HeaderName").getString(0);
					}
					
				}
			}

		} catch (Exception e) {
			return HeaderName;

		}

		return HeaderName;
	}

	
}
