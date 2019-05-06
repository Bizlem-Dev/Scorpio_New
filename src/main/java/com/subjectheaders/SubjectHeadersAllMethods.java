package com.subjectheaders;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

public class SubjectHeadersAllMethods {

	public static String stanbolBreakResponse(String plain_text) {
		String res = null;
		try {
			BufferedReader reader = null;

			int BUFFER_SIZE = 4096;
			InputStream isr = new ByteArrayInputStream(plain_text.toString().getBytes("UTF-8"));

			URL url = new URL("http://35.227.82.195:8080/enhancer/chain/scorpiosvchain");
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

			httpConn.setUseCaches(false);
			httpConn.setDoOutput(true);
			// httpConn.setRequestProperty("Content-Type", "application/json");
			// httpConn.setRequestProperty("Content-Type", "text/plain");
			// httpConn.setRequestProperty("Accept","application/rdf+xml");
			// httpConn.setRequestProperty("Content-Type","application/pdf");
			// httpConn.setRequestMethod("POST");

			httpConn.setRequestProperty("Accept", "application/json");
			httpConn.setRequestProperty("Content-Type", "application/pdf");
			// httpConn.setRequestProperty("Content-Type", "application/json");
			// httpConn.setRequestProperty("Content-Type",
			// "application/x-www-form-urlencoded");

			OutputStream outputStream = httpConn.getOutputStream();
			// outputStream.write("file=".getBytes());

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = isr.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			// System.out.println("outputStream"+outputStream.toString());
			outputStream.flush();
			outputStream.close();
			isr.close();
			int responseCode = httpConn.getResponseCode();
			// System.out.println("responseCode " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) {

				reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
				String response = reader.readLine();

				String line1;
				StringBuffer sb = new StringBuffer();
				sb.append("[{");
				while ((line1 = reader.readLine()) != null) {
					sb.append(line1);
				}
				// System.out.println(sb.toString());
				res = sb.toString();
			}
			// System.out.println(res);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return res;
	}
	
	public static JSONObject getUrlByStanbol(String adata, PrintWriter out) {
		JSONObject urlnadvalue = new JSONObject();

		try {

			if (!adata.isEmpty()) {
				String stanboljson = stanbolBreakResponse(adata);
				// System.out.println("stanboljson "+stanboljson);
				JSONArray arrobj = new JSONArray(stanboljson);
				// System.out.println(arrobj.length());

				for (int i = 0; i < arrobj.length(); i++) {
					JSONObject obj = arrobj.getJSONObject(i);
					String entity_ref_url = null;
					String entity_vlaue = null;
					;
					String entity_type = null;

					if (obj.has("http://fise.iks-project.eu/ontology/entity-reference")) {
						JSONArray entityref = obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-reference");
						entity_ref_url = entityref.getJSONObject(0).getString("@id");

						 //System.out.println(entity_ref_url);
					}
					if (obj.has("http://fise.iks-project.eu/ontology/entity-label")) {
						JSONArray entityrlabel = obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-label");
						entity_vlaue = entityrlabel.getJSONObject(0).getString("@value");
						// System.out.println(entity_vlaue);
					}
					if (obj.has("http://fise.iks-project.eu/ontology/entity-type")) {
						JSONArray entityrtype = obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-type");
						entity_type = entityrtype.getJSONObject(0).getString("@id");
						// System.out.println(entity_type);
						if (entity_type.contains("#")) {
							entity_type = entity_type.substring(entity_type.lastIndexOf("#") + 1);
						}
					}
					if ((entity_vlaue != null)) {
						/* urlnadvalue.put(entity_vlaue, entity_type);*/
						 String etaBasis=etaBasisRegrexDate(adata, out);
						
						 urlnadvalue.put(entity_type, entity_vlaue);
						 urlnadvalue.put("ETABasis", etaBasis);
						 //subfinaljson.put(entity_type, entity_vlaue);

					}
				}
			}
		} catch (JSONException e) {
			//e.printStackTrace();
			try {
				return urlnadvalue.put("error", e.getMessage());
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}

		}
		return urlnadvalue;

	}
	
	 public static String etaBasisRegrexDate(String url, PrintWriter out){
		  String etaBasisDate="";
		  try {
			  Pattern id=null;
			  Matcher m=null;
			  
			 // String url="EASTPORT CLEAN MR POSITION BSS S.KOREA AS AT 15th MAR 2019";
			   id = Pattern.compile("(\\d{1,2}\\s\\w{3,}\\s\\d{4})");
			   m = id.matcher(url);
		    	if (m.find()){
		    		 etaBasisDate = m.group(1);
		    	    out.println("alag0:: "+etaBasisDate);
		    	    
		    	}else{
		    		id = Pattern.compile("(\\d{1,2}th\\s([A-Za-z]{3,})\\s\\d{4})");
		    		 m = id.matcher(url);
			    	if (m.find()){
			    		etaBasisDate = m.group(1);
			    		out.println("alag1:: "+etaBasisDate);
			    	}else{
			    		id = Pattern.compile("(\\d{1,2}\\s([A-Za-z]{3,})\\s\\d{4})");
			    		 m = id.matcher(url);
				    	if (m.find()){
				    		etaBasisDate = m.group(1);
				    		out.println("alag2:: "+etaBasisDate);
				    	}else{
				    		id = Pattern.compile("(\\d{1,2}\\s\\[A-Za-z]{3,}\\s\\d{4})");
				    		 m = id.matcher(url);
					    	if (m.find()){
					    		etaBasisDate = m.group(1);
					    		out.println("alag3:: "+etaBasisDate);
					    	}else{
					    		id = Pattern.compile("(\\d{1,2}\\s[A-Za-z]{3,}\\s\\d{4})");
					    		 m = id.matcher(url);
						    	if (m.find()){
						    		etaBasisDate = m.group(1);
						    		out.println("alag4:: "+etaBasisDate);
						    	}else{
						    		id = Pattern.compile("(\\d{1,2}-[A-Za-z]{3,}-\\d{4})");
						    		 m = id.matcher(url);
							    	if (m.find()){
							    		etaBasisDate = m.group(1);
							    		out.println("alag5:: "+etaBasisDate);
							    	}else{
							    		id = Pattern.compile("(\\d{1,2}\\.\\d{1,2}\\.\\d{2})");
							    		 m = id.matcher(url);
								    	if (m.find()){
								    		etaBasisDate = m.group(1);
								    		out.println("alag6:: "+etaBasisDate);
								    	}else{
								    		id = Pattern.compile("(BY MONTH\\s/\\s\\d{4}-\\d{4})");
								    		 m = id.matcher(url);
									    	if (m.find()){
									    		etaBasisDate = m.group(1);
									    		out.println("alag7:: "+etaBasisDate);
									    	}else{
									    		id = Pattern.compile("(\\d{1,2}\\/\\d{1,2}\\/\\d{4})");
									    		 m = id.matcher(url);
										    	if (m.find()){
										    		etaBasisDate = m.group(1);
										    		out.println("alag8:: "+etaBasisDate);
										    	}else{
										    		id = Pattern.compile("(\\d{1,2}-[A-Za-z]{2,}-\\d{2})");
										    		 m = id.matcher(url);
											    	if (m.find()){
											    		etaBasisDate = m.group(1);
											    		out.println("alag9:: "+etaBasisDate);
											    	}else{
											    		id = Pattern.compile("(([0-9]{1,4}|[A-Za-z]{1,3})\\/([0-9]{1,4}|[A-Za-z]{1,3})\\/([0-9]{1,4}|[A-Za-z]{1,3}))");
											    		 m = id.matcher(url);
												    	if (m.find()){
												    		etaBasisDate = m.group(1);
												    		out.println("alag10:: "+etaBasisDate);
												    	}else{
												    		id = Pattern.compile("([0-9]{1,2}\\/[A-Za-z]{1,3}\\/[0-9]{1,4})");
												    		 m = id.matcher(url);
													    	if (m.find()){
													    		etaBasisDate = m.group(1);
													    		out.println("alag11:: "+etaBasisDate);
													    	}else{
													    		id = Pattern.compile("([0-9]{1,2}\\s[A-Za-z]{1,3}\\s[0-9]{1,4})");
													    		 m = id.matcher(url);
														    	if (m.find()){
														    		etaBasisDate = m.group(1);
														    		out.println("alag12:: "+etaBasisDate);
														    	}else{
														    		id = Pattern.compile("([0-9]{1,2}[A-Za-z]{1,2}\\s[A-Za-z]{1,3}\\s[0-9]{1,4})");
														    		 m = id.matcher(url);
															    	if (m.find()){
															    		etaBasisDate = m.group(1);
															    		out.println("alag13:: "+etaBasisDate);
															    	}else{
															    		id = Pattern.compile("(\\([0-9]{1,2}\\-[0-9]{1,2}\\/[A-Za-z]{0,3}\\/[0-9]{1,2}\\))");
															    		 m = id.matcher(url);
																    	if (m.find()){
																    		etaBasisDate = m.group(1);
																    		out.println("alag14:: "+etaBasisDate);
																    	}else{
																    		id = Pattern.compile("([0-9]{1,2}\\.[0-9]{1,2}\\.[0-9]{1,2})");
																    		 m = id.matcher(url);
																	    	if (m.find()){
																	    		etaBasisDate = m.group(1);
																	    		out.println("alag15:: "+etaBasisDate);
																	    	}else{
																	    		id = Pattern.compile("([0-9]{1,2}\\/0-9]{1,2}\\/[0-9]{1,2})");
																	    		 m = id.matcher(url);
																		    	if (m.find()){
																		    		etaBasisDate = m.group(1);
																		    		out.println("alag16:: "+etaBasisDate);
																		    	}else{
																		    		id = Pattern.compile("([A-Za-z]{1,10}\\s[0-9]{1,2}[TtHh]{1,2}\\,\\s[0-9]{1,4})");
																		    		 m = id.matcher(url);
																			    	if (m.find()){
																			    		etaBasisDate = m.group(1);
																			    		out.println("alag17:: "+etaBasisDate);
																			    	}else{
																			    		id = Pattern.compile("([0-9]{1,2}\\.[0-9]{1,2}\\.[0-9]{1,2})");
																			    		 m = id.matcher(url);
																				    	if (m.find()){
																				    		etaBasisDate = m.group(1);
																				    		out.println("alag18:: "+etaBasisDate);
																				    	}else{
																				    		id = Pattern.compile("([A-Za-z]{1,10}\\s[0-9]{1,2}\\,\\s[0-9]{1,4})");
																				    		 m = id.matcher(url);
																					    	if (m.find()){
																					    		etaBasisDate = m.group(1);
																					    		out.println("alag19:: "+etaBasisDate);
																					    	}else{
																					    		id = Pattern.compile("([0-9]{1,2}[A-Za-z]{1,2}\\([0-9]{1,2}\\/[A-Za-z]{1,3}\\/[0-9]{1,2}\\))");
																					    		 m = id.matcher(url);
																						    	if (m.find()){
																						    		etaBasisDate = m.group(1);
																						    		out.println("alag20:: "+etaBasisDate);
																						    	}else{
																						    		id = Pattern.compile("([0-9]{1,2}\\.[0-9]{1,2}\\.[0-9]{1,2})");
																						    		 m = id.matcher(url);
																							    	if (m.find()){
																							    		etaBasisDate = m.group(1);
																							    		out.println("alag21:: "+etaBasisDate);
																							    	}else{
																							    		id = Pattern.compile("([0-9]{1,2}\\/[0-9]{1,2}\\/[0-9]{1,4})");
																							    		 m = id.matcher(url);
																								    	if (m.find()){
																								    		etaBasisDate = m.group(1);
																								    		out.println("alag22:: "+etaBasisDate);
																								    	}else{
																								    		id = Pattern.compile("([0-9]{1,2}[A-Za-z]{1,2}\\s[A-Za-z]{1,10}\\s[0-9]{1,4})");
																								    		 m = id.matcher(url);
																									    	if (m.find()){
																									    		etaBasisDate = m.group(1);
																									    		out.println("alag23:: "+etaBasisDate);
																									    	}else{
																									    		id = Pattern.compile("([0-9]{1,2}[A-Za-z]{1,2}\\s[A-Za-z]{1,10})");
																									    		 m = id.matcher(url);
																										    	if (m.find()){
																										    		etaBasisDate = m.group(1);
																										    		out.println("alag24:: "+etaBasisDate);
																										    	}else{
																										    		id = Pattern.compile("(\\([0-9]{1,2}\\/[A-Za-z]{1,3}\\/[0-9]{1,2}\\))");
																										    		 m = id.matcher(url);
																											    	if (m.find()){
																											    		etaBasisDate = m.group(1);
																											    		out.println("alag25:: "+etaBasisDate);
																											    	}else{
																											    		id = Pattern.compile("([0-9]{1,2}\\-[0-9]{1,2}\\s[A-Za-z]{1,3})");
																											    		 m = id.matcher(url);
																												    	if (m.find()){
																												    		etaBasisDate = m.group(1);
																												    		out.println("alag26:: "+etaBasisDate);
																												    	}else{
																												    		id = Pattern.compile("([0-9]{1,2}\\/[A-Za-z]{1,3}\\/[0-9]{1,4})");
																												    		 m = id.matcher(url);
																													    	if (m.find()){
																													    		etaBasisDate = m.group(1);
																													    		out.println("alag27:: "+etaBasisDate);
																													    	}else{
																													    		id = Pattern.compile("(\\([0-9]{1,2}[A-Za-z]{1,2}\\s[A-Za-z]{1,3}\\s\\-\\s[0-9]{1,2}[A-Za-z]{1,2}\\s[A-Za-z]{1,3}\\))");
																													    		 m = id.matcher(url);
																														    	if (m.find()){
																														    		etaBasisDate = m.group(1);
																														    		out.println("alag28:: "+etaBasisDate);
																														    	}else{
																														    		id = Pattern.compile("([0-9]{1,2}\\s[A-Za-z]{1,3})");
																														    		 m = id.matcher(url);
																															    	if (m.find()){
																															    		etaBasisDate = m.group(1);
																															    		out.println("alag29:: "+etaBasisDate);
																															    	}else{
																															    		id = Pattern.compile("([0-9]{1,2}[A-Za-z]{1,2}\\s[A-Za-z]{1,10})");
																															    		 m = id.matcher(url);
																																    	if (m.find()){
																																    		etaBasisDate = m.group(1);
																																    		out.println("alag30:: "+etaBasisDate);
																																    	}else{
																																    		id = Pattern.compile("([0-9]{1,2}\\/[0-9]{1,2}\\/[0-9]{1,4})");
																																    		 m = id.matcher(url);
																																	    	if (m.find()){
																																	    		etaBasisDate = m.group(1);
																																	    		out.println("alag31:: "+etaBasisDate);
																																	    	}else{
																																	    		id = Pattern.compile("([0-9]{1,2}[A-Za-z]{1,2}\\s[A-Za-z]{1,10}\\s[0-9]{1,4})");
																																	    		 m = id.matcher(url);
																																		    	if (m.find()){
																																		    		etaBasisDate = m.group(1);
																																		    		out.println("alag32:: "+etaBasisDate);
																																		    	}else{
																																		    		id = Pattern.compile("([0-9]{1,2}\\.[0-9]{1,2}\\.[0-9]{1,4})");
																																		    		 m = id.matcher(url);
																																			    	if (m.find()){
																																			    		etaBasisDate = m.group(1);
																																			    		out.println("alag33:: "+etaBasisDate);
																																			    	}else{
																																			    		id = Pattern.compile("([0-9]{1,2}\\/[A-Za-z]{1,3}\\/[0-9]{1,2})");
																																			    		 m = id.matcher(url);
																																				    	if (m.find()){
																																				    		etaBasisDate = m.group(1);
																																				    		out.println("alag34:: "+etaBasisDate);
																																				    	}else{
																																				    		id = Pattern.compile("([0-9]{1,2}[A-Za-z]{1,2}\\s[A-Za-z]{1,3}\\s\\-\\s[0-9]{1,2}[A-Za-z]{1,2}\\s[A-Za-z]{1,3})");
																																				    		 m = id.matcher(url);
																																					    	if (m.find()){
																																					    		etaBasisDate = m.group(1);
																																					    		out.println("alag35:: "+etaBasisDate);
																																					    	}else{
																																					    		id = Pattern.compile("([0-9]{1,2}\\s[A-Za-z]{1,3})");
																																					    		 m = id.matcher(url);
																																						    	if (m.find()){
																																						    		etaBasisDate = m.group(1);
																																						    		out.println("alag36:: "+etaBasisDate);
																																						    	}else{
																																						    		id = Pattern.compile("([0-9]{1,2}[A-Za-z]{1,2}\\s[A-Za-z]{1,10}\\s[0-9]{1,4})");
																																						    		 m = id.matcher(url);
																																							    	if (m.find()){
																																							    		etaBasisDate = m.group(1);
																																							    		out.println("alag36:: "+etaBasisDate);
																																							    	}else{
																																							    		id = Pattern.compile("([A-Za-z]{1,10}\\s[0-9]{1,2}\\s[0-9]{1,4})");
																																							    		 m = id.matcher(url);
																																								    	if (m.find()){
																																								    		etaBasisDate = m.group(1);
																																								    		out.println("alag37:: "+etaBasisDate);
																																								    	}else{
																																								    		id = Pattern.compile("(([0-9]|[0][0-9]|[12][0-9]|[3][01])\\/([0-9]|[0][0-9]|[1][012])\\/[0-9]{4}[ ]?$)");
																																								    		 m = id.matcher(url);
																																									    	if (m.find()){
																																									    		etaBasisDate = m.group(1);
																																									    		out.println("alag37:: "+etaBasisDate);
																																									    	}else{
																																								    		
																																								    		
																																								    		  out.println("no EtaBasis Found");
																																									    	}
																																								    	}
																																							    		
																																							    	}
																																						    		
																																						    		
																																						    	}
																																					    	}
																																				    	}
																																			    	}
																																		    	}
																																	    	}
																																    	}
																															    	}
																														    	}
																													    	}
																												    	}
																											    	}
																										    	}
																									    	}
																								    	}
																							    	}
																						    	}
																					    	}
																				    	}
																			    	}
																		    	}
																	    	}
																    	}
															    	}
														    	}
													    	}
												    	}

											    	}
										    	}
									    	}
								    	}
							    	}
						    	}
					    	}
				    	}
			    		
			    	}
			    	
		    	}
			  
			  
			  
		} catch (Exception e) {
			return etaBasisDate;
		}
		return etaBasisDate;
	  }
	
}
