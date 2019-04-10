package com.reportinformationsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;

public class ExpertScriptCall {

	public static void main(String[] args) {

		String filePath="/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/UPDATED_LR2_POSITION_LISTS_BSS_FUJAIRAH_1.html";
		String responseString=postExpertScript(filePath);
		System.out.println(responseString);
	}
	
	
	public static String postExpertScript(String filePath){
		String responseString="";
		try {
			
			//List<String> response = new ArrayList<String>();
			StringBuilder response = new StringBuilder();
			try {
			//http://34.80.26.185:5015/project1
			URL url = new URL("http://34.80.26.185:5015/project5");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");

//			FileBody fileBody = new FileBody(new File("E://ExcelData//HtmlToPdfFile//TestFolder//InputFileshtmlToJson//FME_MESSAGE_1549021398868.html"));
			FileBody fileBody = new FileBody(new File(filePath));
			MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.STRICT);
			multipartEntity.addPart("file", fileBody);

			connection.setRequestProperty("Content-Type", multipartEntity.getContentType().getValue());
			OutputStream out = connection.getOutputStream();
			try {
			multipartEntity.writeTo(out);

			} finally {
			out.close();
			}
			int status = connection.getResponseCode();
			if (status == HttpURLConnection.HTTP_OK) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
			connection.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
			response.append(line);
			}
			//System.out.println("Response Returned is"+response.toString());
			responseString=response.toString();
			reader.close();
			connection.disconnect();
			}
			} catch (Exception e) {
			    //e.printStackTrace();
				return "false";
			} 
			
		} catch (Exception e) {
			
		}
		return responseString;
	}

}
