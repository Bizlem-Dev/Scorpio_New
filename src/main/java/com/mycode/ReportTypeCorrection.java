package com.mycode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jcr.Session;

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

}
