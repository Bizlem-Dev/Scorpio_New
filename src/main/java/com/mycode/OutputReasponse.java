package com.mycode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class OutputReasponse {

	public static void main(String[] args) {
//		String filePath = "D:\\scorpio pdf broker\\pexea12-fup-info-extraction-784a354ceda4\\pexea12-fup-info-extraction-784a354ceda4\\CLARKSONS_MIDDLE_EAST_CPP_TRACKERS_-_261018_1_ULSDTRACKER_2017_ONWARDS.xlsx";
        
		String filePath = "/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/CLARKSONS_MIDDLE_EAST_CPP_TRACKERS_-_120419_1_NEWJET_TRACKER.xls";
		String count= excelClarksonCountBigSize(filePath, null);
        System.out.println(count);
	}

	public static String excelClarksonCountBigSize(String filePath, PrintWriter out) {
      String count="";
		try {

//			URL obj = new URL("http://localhost:8085/ExcelCheckApi/CheckExcelSize");
			URL obj = new URL("https://dev.bizlem.io:8082/ExcelCheckApi/CheckExcelSize");
			HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
			postConnection.setRequestMethod("POST");
//			postConnection.setRequestProperty("userId", "a1bcdefgh");
//			postConnection.setRequestProperty("Content-Type", "application/json");
			postConnection.setDoOutput(true);
			OutputStream os = postConnection.getOutputStream();
			os.write(filePath.getBytes());
			os.flush();
			os.close();
			int responseCode = postConnection.getResponseCode();
			 out.println("POST Response Code :  " + responseCode);
			//System.out.println("POST Response Message : " + postConnection.getResponseMessage());
//			if (responseCode == HttpURLConnection.HTTP_CREATED) { // success
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(postConnection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
					count=response.toString();
				}
				in.close();
				// print result
				//System.out.println(response.toString());
			} else {
				out.println("POST NOT WORKED");
			}

		} catch (Exception e) {
			return count;
		}
		return count;

	}

}
