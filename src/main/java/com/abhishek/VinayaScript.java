package com.abhishek;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.mongocode.PdfErrorInMongoDb;
import com.reportinformationsystem.DateFromToApiReport;

public class VinayaScript {

	public static void main(String[] args) {
		//String fileName="/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/svgoutput/TAIPAN_CLEAN_MARKET_REPORT_-_04_JANUARY_2019_1_TAIPANCLEAN_MARKET_REPORT_04_JANUARY_2019.json";
		
		//String output=DateFromToApiReport.processPdftoSvg("/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/CLARKSONS_PLATOU_AG_LR1_LIST_DATED_21.03.19_1_CLARKSONSPLATOU_AG_LR1_LIST_DATED_21.03.19.pdf");
		//String output=DateFromToApiReport.processPdftoSvg("/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/SERNAVIMAR_TIME_CHARTER_REPORT_1_PinkReport31jan.pdf");
		String output=DateFromToApiReport.processPdftoSvg("/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/CLARKSONS_PLATOU_ECI_MR_LIST_DATED_29.04.19_1_CLARKSONSPLATOU_ECI_MR_LIST_DATED_29.04.19.pdf");
		//String output="/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/BRAEMAR_ACM_WEEKLY_TANKER_MARKET_REPORT_-_WEEK_ENDING_FRIDAY_22_February_2019_1_BraemarACM_Weekly_Tanker_Market_Report_22_February_2019.pdf";
		//String fileName="/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/svgoutput/TC_MARKET_REPORT_WEEK_8_1_weeklytc_report_8.json";
	    System.out.println(output);
//		String json=vinayaScript(output);
	    String data= vinayaScript("/home/ubuntu/TestedMailJSon/CLARKSONS PLATOU AG LR1 LIST DATED 29.04.19.json", "");
		System.out.println(data);
	    
		
	}
	
	
	public static String vinayaScript(String fileName, String timestampDateAndTime){
   		
   		StringBuffer response1=null;
   		
   		try {

   			fileName=URLEncoder.encode(fileName, "UTF-8");
   			
   			String url = "";
//   			url="http://dev.bizlem.io:5028/?file="+fileName;
   			url="http://dev.bizlem.io:5035/svgjson?file="+fileName;
   			
   			//url="http://dev.bizlem.io:5006/?file="+fileName+ "&active=" + active;
   			
//   			url = url.replace(" ", "%20");
   			
   			URL url1 = new URL(url);
   			HttpURLConnection con = (HttpURLConnection) url1.openConnection();
   			/*con.setConnectTimeout(900000);
   			con.setReadTimeout(900000);*/ // minute to milliseconds
//   			int responseCode = con.getResponseCode();
//   			System.out.println("responseCode: "+responseCode);

   			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
   			String inputLine;
   			 response1 = new StringBuffer();

   			while ((inputLine = in.readLine()) != null) {
   				response1.append(inputLine);
   			}
   			in.close();

   		} catch (Exception e) {
//   			System.out.println(e.getMessage());
   			e.printStackTrace();
//   			out.println(e.getMessage());
   		  /* String forpdfOnly="/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/pdfErrorStored/"+fileName+"_"+timestampDateAndTime+".txt";
   		  // writeUsingOutputStream("pdfError::  "+fileName, forpdfOnly);
   		  PdfErrorInMongoDb.storePdfErrorMongo("PDFERRORDATABASE", "PdfCollection", timestampDateAndTime, fileName, e.getMessage());
   			return "false";*/
   			
   		}
   		
   		return response1.toString();
   		
   	}
	
	public static void writeUsingOutputStream(String data, String path) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(path), true);
            os.write(data.getBytes(), 0, data.length());
            
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
