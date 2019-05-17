package com.pallavi.code;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;

import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.jcr.api.SlingRepository;

import com.readGmail.SlingMethods;


import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Properties;

@Component(immediate = true, metatype = false)
@Service(value = javax.servlet.Servlet.class)
@Properties({ @Property(name = "sling.servlet.paths", value = { "/tablelablerecognition" }), })

public class TableLableRecognition extends SlingAllMethodsServlet {
	@Reference
	private SlingRepository repo;
	Session session = null;
	static ResourceBundle bundle = ResourceBundle.getBundle("config");

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
String type="";
		try {
			String data = request.getParameter("data");
			int headercount = 0;

			if (data.length() > 0) {
				String[] arr = data.split(",");
				for (int i = 0; i < arr.length; i++) {
					String key = arr[i];
					System.out.println("key  " + key);
					String solrKeyList = fetchsolrdata(key.trim());
					if (solrKeyList != null) {
						String headerName = pallavi_SortJSON.parsesolrresp(solrKeyList, "HeaderName");
						System.out.println("headerName:: " + headerName);
						if (headerName != "") {
							headercount++;
						}
					}

				}
			}
			if (headercount >= 4) {
				type="TableLables";
               out.print(type);
			}else {
				type="TableData";
	               out.print(type);
			}
		} catch (Exception e) {
            out.print(type);
		}
	}


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
		url = "http://35.231.163.191:8983/solr/Header_Synomes/select?q=Data_str:\"" + q + "\"&fl=score,HeaderName";
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
//		System.out.println(e.getMessage());
		return "";
	}
	return response1.toString();

}



public static boolean isJSONValid(String test) {
	try {
		new JSONObject(test);
	} catch (Exception ex) {
		try {
			new JSONArray(test);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
        return false	;	
        }
	}
	return true;
}


}
