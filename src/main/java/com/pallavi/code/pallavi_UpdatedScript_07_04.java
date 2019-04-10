package com.pallavi.code;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.jcr.Session;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import com.reportinformationsystem.SaveReportDataClass;

public class pallavi_UpdatedScript_07_04 {
	/**
	 * @param args
	 */
	public static String updatedMainScript(PrintWriter out, Session session, String pythonScriptApiData,
	String emailUrl, String textSentMailTime, String subjectNodePath, String from_Source, String timestampDate,
	String timestampDateAndTime, String filepath) {
	//public static void main(String[] args) {
		//String a = "";
		//System.out.println("start");
		JSONObject obj=null;
		try {
			JSONObject returnobj = new JSONObject();
			 obj = new JSONObject(pythonScriptApiData);
			Iterator mainitr = obj.keys();
			while (mainitr.hasNext()) {
				String TableName = (String) mainitr.next();
				JSONObject Tableobj = obj.getJSONObject(TableName);
				JSONArray table_dataobj = Tableobj.getJSONArray("table_data");
				//JSONArray header_dataobj = Tableobj.getJSONArray("header_data");

				// one loop to check starting of table by checking vesselname
				int vesselline_line_index = 0;
				int vesselline_count = 0;
				for (int i = 0; i < table_dataobj.length(); i++) {
					JSONObject eachrowobj = table_dataobj.getJSONObject(i);
					JSONObject data = eachrowobj.getJSONObject("data");
					JSONObject header = eachrowobj.getJSONObject("header");
					out.println("data  "+data);
					out.println("header  "+header );

					Iterator itr = data.keys();
					if (vesselline_line_index == 0) {
						while (itr.hasNext()) {
							if (vesselline_line_index == 0) {
								//System.out.println("vesselline_no blank");
								String key = (String) itr.next();
								String value = data.getString(key);
								String solrres = pallavi_SortJSON
										.executesolrQuery_bywords(value.toUpperCase().replace(" ", "_"));
								boolean checkjsonString = SaveReportDataClass.isJSONValid(solrres);
								if (checkjsonString) {
									// parsejson from solr //get DocumentName
									String DocumentName = pallavi_SortJSON.parsesolrresp(solrres, "DocumentName");
									//System.out.println("DocumentName  " + DocumentName);
									if (DocumentName.equalsIgnoreCase("VesselName")) {
										// vesselline_no=eachrowobj.getString("Line_no");
										vesselline_line_index = Integer.parseInt(eachrowobj.getString("line_index"));
									}
								}
							} else {
								break;
							}
							//System.out.println("vesselline_no " + vesselline_line_index);
						}
						vesselline_count++;

					} else {
						break;
					}
				}
				//System.out.println("vesselline_count " + vesselline_count);

				//System.out.println("successfull break");
				LinkedHashMap<String, Integer> lhmap = new LinkedHashMap<String, Integer>();

				if(vesselline_count!=0){
				for (int i = vesselline_count - 1; i < table_dataobj.length(); i++) {
					JSONObject eachrowobj = table_dataobj.getJSONObject(i);
					JSONObject data = eachrowobj.getJSONObject("data");
					JSONObject header = eachrowobj.getJSONObject("header");
					//System.out.println("header " + header);
					JSONObject new_data = new JSONObject();
					JSONObject new_header = new JSONObject();
					Iterator itr = header.keys();
					while (itr.hasNext()) {
						String hkey = (String) itr.next();
						String hvalue = header.getString(hkey);
						String dvalue = data.getString(hkey);
						new_header.put(hkey, hvalue);
						//System.out.println("hvalue " + hvalue);
						// now check lable by lable
						if (hvalue.contains("unknown")) {
							// 1. call regax script
							// 2.call sorl no as no header
							// 3. call new solr api
							// 4.execute method
							String synonyms = " ";
							String solrmatch = callSolrforDataMatch(dvalue);
							String regexmatch = " ";
							String regexpattern = "";
							//System.out.println("dvalue "+dvalue);
							JSONObject regexmatchresult = callregex(dvalue);
							boolean checkjsonString = SaveReportDataClass.isJSONValid(regexmatchresult.toString());
							if (checkjsonString) {
								if (regexmatchresult.has("Format")) {
									regexpattern = regexmatchresult.getString("Format");
								}
								if (regexmatchresult.has("Regex_Type")) {
									regexmatch = regexmatchresult.getString("Regex_Type");
								}
							}
							String mathodname = pallavi_SortJSON.executesolrQuery_getMethodName(" ".toUpperCase(),
									synonyms.toUpperCase(), regexmatch.toUpperCase(), solrmatch.toUpperCase(),
									"METHODNAME");
							//System.out.println("mathodname unknown " + mathodname);
							allmethodexceution(data, header, new_header, hkey, hvalue, dvalue, mathodname, synonyms,
									regexmatch, regexpattern, solrmatch, lhmap);
						} else {
							String solrKeyList = fetchsolrdata(hvalue.replace(" ", "_"));
							// //System.out.println("solrKeyList "+solrKeyList);
							if (solrKeyList != null) {
								JSONObject headerNameobj = pallavi_SortJSON.parsesolrresp_updated(solrKeyList,
										"HeaderName", "Extrakey");
								//System.out.println("headerName:: " + headerNameobj);
								if (headerNameobj.has("HeaderName")
										&& !headerNameobj.getString("HeaderName").equalsIgnoreCase(" ")) {
									String headername = headerNameobj.getString("HeaderName");
									if (headerNameobj.has("Extrakey")) {
										new_header.put(hkey, headername);
										// ************************************************************
										String Extrakey = headerNameobj.getString("Extrakey");
										// call stanbol to get data
										if (Extrakey.equalsIgnoreCase("RepositionRegion")) {
											JSONObject Stanbolmatchobj = getUrlByStanbol(hvalue);
											// //System.out.println("Stanbolmatchobj "+Stanbolmatchobj);
											if (Stanbolmatchobj.length() > 0) {
												// method to add data in json in appropriate key

												Iterator stanbolitr = Stanbolmatchobj.keys();
												// //System.out.println(Stanbolmatchobj.length());
												while (stanbolitr.hasNext()) {
													// //System.out.println("in stanbol while");
													String stanbolkey = (String) stanbolitr.next();
													String stanbolvalue = Stanbolmatchobj.getString(stanbolkey);
													// //System.out.println("stanbolkey "+stanbolkey +"
													// stanbolvalue"+stanbolvalue);
													if (stanbolvalue.equalsIgnoreCase("RepositionRegion")) {
														data.put(data.length() + "", stanbolkey);
														new_header.put(data.length() + "", stanbolvalue);
														// //System.out.println("new_header ***^^^^%%%% "+new_header);
													}
												}
											}
										}
										if (Extrakey.equalsIgnoreCase("VesselType")) {
											JSONObject Stanbolmatchobj = getUrlByStanbol(hvalue);
											// //System.out.println("Stanbolmatchobj "+Stanbolmatchobj);
											if (Stanbolmatchobj.length() > 0) {
												// method to add data in json in appropriate key
												Iterator stanbolitr = Stanbolmatchobj.keys();
												// //System.out.println(Stanbolmatchobj.length());
												while (stanbolitr.hasNext()) {
													// //System.out.println("in stanbol while");
													String stanbolkey = (String) stanbolitr.next();
													String stanbolvalue = Stanbolmatchobj.getString(stanbolkey);
													// //System.out.println("stanbolkey "+stanbolkey +"
													// stanbolvalue"+stanbolvalue);
													if (stanbolvalue.equalsIgnoreCase("VesselType")) {
														data.put(data.length() + "", stanbolkey);
														new_header.put(data.length() + "", stanbolvalue);
														// //System.out.println("new_header ***^^^^%%%% "+new_header);
													}
												}
											}
										}
										// *********************************************************

									} else { // if no Extrakey
												// 1. call regax script
												// 2.call sorl
												// 3. call new solr api
												// 4.execute method
										String synonyms = headername;
										//System.out.println("synonyms "+synonyms);
										String solrmatch = callSolrforDataMatch(dvalue);
										//System.out.println("solrmatch "+solrmatch);
										//System.out.println("dvalue "+dvalue);

										String regexmatch = " ";
										String regexpattern = "";
										JSONObject regexmatchresult = callregex(dvalue);
										boolean checkjsonString = SaveReportDataClass
												.isJSONValid(regexmatchresult.toString());
										if (checkjsonString) {
											if (regexmatchresult.has("Format")) {
												regexpattern = regexmatchresult.getString("Format");
											}
											if (regexmatchresult.has("Regex_Type")) {
												regexmatch = regexmatchresult.getString("Regex_Type");
											}
										}
										//System.out.println("regexpattern "+regexpattern+" regexmatch "+regexmatch);
										String mathodname = pallavi_SortJSON.executesolrQuery_getMethodName(
												hvalue.toUpperCase(), synonyms.toUpperCase(), regexmatch.toUpperCase(),
												solrmatch.toUpperCase(), "METHODNAME");
										//System.out.println("mathodname withsynonym " + mathodname);
										allmethodexceution(data, header, new_header, hkey, hvalue, dvalue, mathodname,
												synonyms, regexmatch, regexpattern, solrmatch, lhmap);
									}
								} else {
									// what if header do not match
									// 1. call regax script
									// 2.call sorl
									// 3. call new solr api
									// 4.execute method

									String synonyms = " ";
									String solrmatch = callSolrforDataMatch(dvalue);
									String regexmatch = " ";
									String regexpattern = "";
									//System.out.println("dvalue "+dvalue);

									JSONObject regexmatchresult = callregex(dvalue);
									boolean checkjsonString = SaveReportDataClass
											.isJSONValid(regexmatchresult.toString());
									if (checkjsonString) {
										if (regexmatchresult.has("Format")) {
											regexpattern = regexmatchresult.getString("Format");
										}
										if (regexmatchresult.has("Regex_Type")) {
											regexmatch = regexmatchresult.getString("Regex_Type");
										}
									}
									String mathodname = pallavi_SortJSON.executesolrQuery_getMethodName(
											hvalue.toUpperCase(), synonyms.toUpperCase(), regexmatch.toUpperCase(),
											solrmatch.toUpperCase(), "METHODNAME");
									//System.out.println("mathodname withoutsynonym " + mathodname);
									allmethodexceution(data, header, new_header, hkey, hvalue, dvalue, mathodname,
											synonyms, regexmatch, regexpattern, solrmatch, lhmap);

								}
							}
						}
					}
					table_dataobj.getJSONObject(i).put("new_header_data", new_header);
					System.out.println("&&&&&&&&&&&& " + table_dataobj.getJSONObject(i));
				}}
				// call method to get averagerate position from lhmap

				Tableobj.put("Rate_Position", FindAverageRatePosition(lhmap));
			}

			//System.out.println("obj " + obj);
			
			pallavi_WriteFile.writeUsingOutputStream( "SCRIPTJSON ::====== "+pythonScriptApiData.toString() +
					"\n OUTPUTJSON::====== "+obj .toString() , 
					filepath);

			out.println("obj  "+obj);
			return obj.toString();

		} catch (Exception e) {
			pallavi_WriteFile.writeUsingOutputStream( "SCRIPTJSON ::====== "+pythonScriptApiData.toString() +
					"\n OUTPUTJSON::====== "+obj .toString() +"\n error"+ e.getMessage(), 
					filepath);
			e.printStackTrace();
			out.println("error  "+e.getMessage()+"\n obj  "+obj);

			return obj.toString();

		}
	}

	public static String FindAverageRatePosition(LinkedHashMap<String, Integer> lhmap) {
		String rateposition = "";
		int maxrate = 0;
		if (lhmap.size() > 0) {
			for (String key : lhmap.keySet()) {
				int value = lhmap.get(key);
				if (value > maxrate) {
					maxrate = value;
					rateposition = key;
				}
			}
		}
		return rateposition;
	}

	public static String callSolrforDataMatch(String dvalue) {
		String DocumentName = " ";
		String solrres;
		try {
			if(dvalue.contains(":")) { dvalue=dvalue.replace(":", "");}
			if(dvalue.contains("*")) {dvalue=dvalue.replace("*", "");}
			if(dvalue.contains("\\")) { dvalue=dvalue.replace("\\", "");}
			//if(dvalue.contains("+")) { dvalue=dvalue.replace("+", "");}
			if(dvalue.contains("#")) {dvalue=dvalue.replace("#", "");}
			if(dvalue.contains("?")) {dvalue=dvalue.replace("?", "");}
			solrres = pallavi_SortJSON.executesolrQuery_bywords(dvalue.toUpperCase().replace(" ", "_"));
			boolean checkjsonString = SaveReportDataClass.isJSONValid(solrres);
			if (checkjsonString) {
				DocumentName = pallavi_SortJSON.parsesolrresp(solrres, "DocumentName");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DocumentName;
	}

	public static void allmethodexceution(JSONObject data, JSONObject header, JSONObject new_header, String hkey,
			String hvalue, String dvalue, String mathodname, String synonyms, String regexmatch, String regexpattern,
			String solrmatch, LinkedHashMap<String, Integer> lhmap) {
		try {
			if (mathodname.equalsIgnoreCase("method1")) {
				String[] words = dvalue.split("/");
				int portcount = 0;
				for (int i = 0; i < words.length; i++) {
					String solrres = pallavi_SortJSON
							.executesolrQuery_bywords(words[i].toUpperCase().replace(" ", "_"));
					boolean checkjsonString = SaveReportDataClass.isJSONValid(solrres);
					if (checkjsonString) {
						// parsejson from solr //get DocumentName
						String DocumentName = pallavi_SortJSON.parsesolrresp(solrres, "DocumentName");
						//System.out.println("DocumentName  " + DocumentName);
						if (!DocumentName.equals("Port")) {
							portcount++;
						}
					}
				}
				if (portcount > 0) {
					data.put(hkey, words[0]);
					new_header.put(hkey, "LoadPort");
					if (words.length > 1) {
						data.put(data.length() + "", words[0]);
						new_header.put(data.length() + "", "DiscPort");
					}
				} else {
					data.put(hkey, words[0]);
					new_header.put(hkey, "LoadPort");
				}
			} else if (mathodname.equalsIgnoreCase("method2")) {
				data.put(hkey, dvalue);
				new_header.put(hkey, "OpenPort");
			} else if (mathodname.equalsIgnoreCase("method3")) {
				// call method to convert date in standard form
				JSONObject regexmatchresult = callregex(dvalue);
				boolean checkjsonString = SaveReportDataClass.isJSONValid(regexmatchresult.toString());
				if (checkjsonString) {
					if (regexmatchresult.has("Format")) {
						regexpattern = regexmatchresult.getString("Format");
					}
					if (regexmatchresult.has("Regex_Type")) {
						regexmatch = regexmatchresult.getString("Regex_Type");
					}
				}
				String date = new pallavi_ConvertDatewithPattern().convertDatewithPattern(dvalue, regexpattern);
				data.put(hkey, date);
				new_header.put(hkey, "OpenDate");

			} else if (mathodname.equalsIgnoreCase("method4")) {
				String[] words = dvalue.split(" ");
				String word = "";
				for (int i = 0; i < words.length - 1; i++) {
					word = word + " " + words[i];
					//System.out.println( "word "+word);
				}
				data.put(hkey, word);
				new_header.put(hkey, "OpenPort");

				if (words.length > 0) {
					String date = words[words.length - 1];
					//System.out.println("date  "+date);
					// call regex method to get date pattern
					// convert date into standard format
					// data.put(data.length()+"", date);
					JSONObject regexmatchresult = callregex(date);
					//System.out.println("regexmatchresult "+regexmatchresult);
					boolean checkjsonString = SaveReportDataClass.isJSONValid(regexmatchresult.toString());
					if (checkjsonString) {
						if (regexmatchresult.has("Format")) {
							regexpattern = regexmatchresult.getString("Format");
						}
						if (regexmatchresult.has("Regex_Type")) {
							regexmatch = regexmatchresult.getString("Regex_Type");
						}
					}
					String date_updared = new pallavi_ConvertDatewithPattern().convertDatewithPattern(dvalue,
							regexpattern);
					data.put(data.length() + "", date_updared);
					new_header.put(data.length() + "", "OpenDate");
				}

			} else if (mathodname.equalsIgnoreCase("method5")) {
				// call method to get two dated saperated by &
				String Date1 = "";
				String Date2 = "";
				String date_updared = new pallavi_ConvertDatewithPattern().convertDatewithPattern(dvalue, regexpattern);
				if (date_updared.contains("&")) {
					String[] datearr = date_updared.split("&");
					if (datearr.length > 1) {
						Date1 = datearr[0];
						Date2 = datearr[1];
						data.put(hkey, Date1);
						new_header.put(hkey, "LCStart");
						data.put(data.length() + "", Date2);
						new_header.put(data.length() + "", "LCEnd");
					}else if(datearr.length ==1) {
						Date1 = datearr[0];
						data.put(hkey, Date1);
						new_header.put(hkey, "LCStart");

					}

				}

			} else if (mathodname.equalsIgnoreCase("method6")) {

				String[] words = dvalue.split("/");
				int portcount = 0;
				for (int i = 0; i < words.length; i++) {
					String solrres = pallavi_SortJSON
							.executesolrQuery_bywords(words[i].toUpperCase().replace(" ", "_"));
					boolean checkjsonString = SaveReportDataClass.isJSONValid(solrres);
					if (checkjsonString) {
						// parsejson from solr //get DocumentName
						String DocumentName = pallavi_SortJSON.parsesolrresp(solrres, "DocumentName");
						//System.out.println("DocumentName  " + DocumentName);
						if (!DocumentName.equals("Port")) {
							portcount++;
						}
					}
				}
				if (portcount > 0) {
					data.put(hkey, words[0]);
					new_header.put(hkey, "Port");
					if (words.length > 1) {
						data.put(data.length() + "", words[0]);
						new_header.put(data.length() + "", "Port");
					}
				} else {
					data.put(hkey, dvalue);
					new_header.put(hkey, "Port");
				}

			} else if (mathodname.equalsIgnoreCase("method7")) {
				String[] words = dvalue.split(" ");
				String word = "";
				for (int i = 0; i < words.length - 1; i++) {
					word = word + " " + words[i];
				}
				data.put(hkey, word);
				new_header.put(hkey, "Port");

				if (words.length > 0) {
					String date = words[words.length - 1];
					// all regex method to get date pattern
					// convert date into standard format
					// data.put(data.length()+"", date);
					// new_header.put(data.length()+"", "Date");
					
					
					JSONObject regexmatchresult = callregex(date);
					boolean checkjsonString = SaveReportDataClass.isJSONValid(regexmatchresult.toString());
					if (checkjsonString) {
						if (regexmatchresult.has("Format")) {
							regexpattern = regexmatchresult.getString("Format");
						}
						if (regexmatchresult.has("Regex_Type")) {
							regexmatch = regexmatchresult.getString("Regex_Type");
						}
					}
					String date_updared = new pallavi_ConvertDatewithPattern().convertDatewithPattern(dvalue,
							regexpattern);
					data.put(data.length() + "", date_updared);
					new_header.put(data.length() + "", "Date");

				}

			} else if (mathodname.equalsIgnoreCase("method8")) {
				// call method to get two dated saperated by &
				data.put(hkey, "Date1");
				new_header.put(hkey, "Date");
				data.put(data.length() + "", "Date2");
				new_header.put(data.length() + "", "Date");
			} else if (mathodname.equalsIgnoreCase("method9")) {
				if (lhmap.containsKey(hkey)) {
					int count = (int) lhmap.get(hkey);
					lhmap.put(hkey, count + 1);
				} else {
					lhmap.put(hkey, 1);
				}
				data.put(hkey, dvalue);
				new_header.put(hkey, "Rate");

			} else if (mathodname.equalsIgnoreCase("")) {
				//System.out.println("in blank method");
				JSONObject regexmatchresult = callregex(dvalue);
				//System.out.println("regexmatchresult "+regexmatchresult);
				boolean checkjsonString = SaveReportDataClass.isJSONValid(regexmatchresult.toString());
				//System.out.println("checkjsonString "+checkjsonString);
				if (checkjsonString) {
					if (regexmatchresult.has("Format")) {
						regexpattern = regexmatchresult.getString("Format");
					}
					if (regexmatchresult.has("Regex_Type")) {
						regexmatch = regexmatchresult.getString("Regex_Type");
					}
				}
				//System.out.println(regexpattern  +" "+regexmatch);
				String date = new pallavi_ConvertDatewithPattern().convertDatewithPattern(dvalue, regexpattern);
				//System.out.println(date);
				if (!synonyms.equalsIgnoreCase(" ")) {
					if(regexmatch.equalsIgnoreCase("date") || regexmatch.equalsIgnoreCase("date/date")) {
					data.put(hkey, date);
					}
					new_header.put(hkey, synonyms);
				} else if (!solrmatch.equalsIgnoreCase(" ")) {
					if(regexmatch.equalsIgnoreCase("date")) {
					data.put(hkey, dvalue);
					}else if(solrmatch.equalsIgnoreCase(date)){
						String date1 = new pallavi_ConvertDatewithPattern().convertDatewithPattern(dvalue, regexpattern);

						data.put(hkey, date1);
					}else {
						data.put(hkey, dvalue);

					}
					new_header.put(hkey, solrmatch);
				}else if((!regexmatch.equalsIgnoreCase(" ")) && (regexmatch.equalsIgnoreCase("Rate") || regexmatch.equalsIgnoreCase("Date") )){
					// do nothing
					new_header.put(hkey, regexmatch);

				} else {
					// do nothing
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String fetchsolrdata(String q) {

		// String f1 = "*,score";
		StringBuffer response1 = null;
		// //System.out.println("q: "+q);
		try {
			// q = q.replace(" ", "%20");
			q = URLEncoder.encode(q, "UTF-8");

			String url = "";

			// url = "http://35.188.227.168:8983/solr/TemplateCore/select?q=" + q
			// + "&fl=" + f1 + "&df=" + df;

			// url = "http://35.188.227.168:8983/solr/Header_Synomes/select?q=" +
			// q + "&fl=" + f1;
			// url =
			// "http://35.188.227.168:8983/solr/Header_Synomes/select?q=Data:" +
			// q + "&fl=" + "HeaderName";
			url = "http://35.188.227.168:8983/solr/Header_Synomes/select?q=Data_str:" + q
					+ "&fl=score,HeaderName,Extrakey";
			// url =
			// "http://35.188.227.168:8983/solr/Header_Synomes/select?q=Data:("+q+")&fl=HeaderName";

			// url = url.replace(" ", "%20");

			// //System.out.println("url " + url);
			URL url1 = new URL(url);
			HttpURLConnection con = (HttpURLConnection) url1.openConnection();
			// int responseCode = con.getResponseCode();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			response1 = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response1.append(inputLine);
			}
			// //System.out.println(response1);
			in.close();

		} catch (Exception e) {
			// e.printStackTrace();
			// //System.out.println(e.getMessage());
			return "";
		}
		return response1.toString();

	}

	public static JSONObject getUrlByStanbol(String adata) {
		JSONObject urlnadvalue = new JSONObject();

		try {
			adata = adata.toLowerCase();
			//System.out.println("adata " + adata);
			if (!adata.isEmpty()) {
				String stanboljson = stanbolBreakResponse(adata);
				// //System.out.println("stanboljson "+stanboljson);
				JSONArray arrobj = new JSONArray(stanboljson);
				// //System.out.println(arrobj.length());

				for (int i = 0; i < arrobj.length(); i++) {
					JSONObject obj = arrobj.getJSONObject(i);
					String entity_ref_url = null;
					String entity_vlaue = null;
					;
					String entity_type = null;

					if (obj.has("http://fise.iks-project.eu/ontology/entity-reference")) {
						JSONArray entityref = obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-reference");
						entity_ref_url = entityref.getJSONObject(0).getString("@id");

						// //System.out.println(entity_ref_url);
					}
					if (obj.has("http://fise.iks-project.eu/ontology/entity-label")) {
						JSONArray entityrlabel = obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-label");
						entity_vlaue = entityrlabel.getJSONObject(0).getString("@value");
						// //System.out.println(entity_vlaue);
					}
					if (obj.has("http://fise.iks-project.eu/ontology/entity-type")) {
						JSONArray entityrtype = obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-type");
						entity_type = entityrtype.getJSONObject(0).getString("@id");
						// //System.out.println(entity_type);
						if (entity_type.contains("#")) {
							entity_type = entity_type.substring(entity_type.lastIndexOf("#") + 1);
						}
					}
					if ((entity_vlaue != null)) {
						urlnadvalue.put(entity_vlaue, entity_type);
						// subfinaljson.put(entity_type, entity_vlaue);
						// if (adata.contains(entity_vlaue.toLowerCase())) {
						// //System.out.println("contains");
						// adata = adata.replace(entity_vlaue.toLowerCase(), "");
						//
						// }
					}

				}
			}
			if (!adata.equals("")) {
				urlnadvalue.put(adata, "unknown");

			}
		} catch (JSONException e) {
			e.printStackTrace();
			try {
				return urlnadvalue.put("error", e.getMessage());
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return urlnadvalue;

	}

	public static String stanbolBreakResponse(String plain_text) {
		String res = null;
		try {
			BufferedReader reader = null;

			int BUFFER_SIZE = 4096;
			InputStream isr = new ByteArrayInputStream(plain_text.toString().getBytes("UTF-8"));

			URL url = new URL("http://35.188.227.39:8080/enhancer/chain/scorpiosvchain");
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
			// //System.out.println("outputStream"+outputStream.toString());
			outputStream.flush();
			outputStream.close();
			isr.close();
			int responseCode = httpConn.getResponseCode();
			// //System.out.println("responseCode " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) {

				reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
				String response = reader.readLine();

				String line1;
				StringBuffer sb = new StringBuffer();
				sb.append("[{");
				while ((line1 = reader.readLine()) != null) {
					sb.append(line1);
				}
				// //System.out.println(sb.toString());
				res = sb.toString();
			}
			// //System.out.println(res);

		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}
		return res;
	}

	public static JSONObject callregex(String dvalue) throws UnsupportedEncodingException {
		int responseCode = 0;
		String urlParameters = "";
		StringBuffer response = new StringBuffer();
		JSONObject rtnobj = new JSONObject();

		try {
			// String url1 =
			// "http://35.186.170.65:8983/solr/Vessel/update/json/docs?commit=true";
			// String url1 =
			// "http://35.186.170.65:8983/solr/HSN_Code/update/json/docs?commit=true";
			// String url1 =
			// "http://35.188.227.168:8983/solr/PO_FlatteningTemplate/update/json/docs?commit=true";
			String url1 = "http://dev.bizlem.io:5032/date";

			URL url = new URL(url1);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			// out.println("urlParameters:: " + obj.toString());
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			// urlParameters = urlParameters.replace(" ", "%20");
			wr.writeBytes(dvalue);
			wr.flush();
			wr.close();
			responseCode = con.getResponseCode();
			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			// out.println(response.toString());
			//System.out.println(" response "+response.toString());
			if (!response.toString().equalsIgnoreCase("")) {
				JSONArray arr = new JSONArray(response.toString());
				//System.out.println(arr);
				int count = 0;
				int jsonno = 0;
				for (int i = 0; i < arr.length(); i++) {
					JSONObject obj = arr.getJSONObject(i);
					String length = "";
					if (obj.has("length")) {
						length = obj.getString("length");
					}
					if (Integer.parseInt(length) > count) {
						count = Integer.parseInt(length);
						jsonno = i;
					}

				}
				if (arr.length() > jsonno) {
					JSONObject obj = arr.getJSONObject(jsonno);
					if (obj.has("Format")) {
						rtnobj.put("Format", obj.getString("Format"));
					}
					if (obj.has("Regex_Type")) {
						rtnobj.put("Regex_Type", obj.getString("Regex_Type"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// out.println(e.getMessage());
		}
		return rtnobj;
	}

}