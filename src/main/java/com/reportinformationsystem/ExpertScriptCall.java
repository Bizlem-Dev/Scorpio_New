package com.reportinformationsystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;

public class ExpertScriptCall {

	public static void main(String[] args) {

		String filePath = "D:\\scorpio pdf broker\\Analysis-28-29\\ODIN_CPP_MKT_RPT_-_WEEK_13_4.html";
		// String
		// filePath="/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/UPDATED_LR2_POSITION_LISTS_BSS_FUJAIRAH_1.html";
		 String responseString=postExpertScript(filePath, null);
		  System.out.println(responseString);
		 

		/*String data = data(
				"/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/svgoutput/TRUE_NORTH_WEEKLY_CLEAN_REPORT_-_WEEK_14_1_TNCWeekly_Commentary_Week_14_2019.json");
		System.out.println(data);*/
	}

	public static String postExpertScript(String filePath, PrintWriter out) {
		String responseString = "";
		try {

			File uploadFile1 = new File(filePath);
			String charset = "UTF-8";

			StringBuilder response = new StringBuilder();

			MultipartUtility multipart = new MultipartUtility("http://34.74.243.55:5025/project5", charset);

			multipart.addFormField("only_extract_html_line", "True");

			multipart.addFilePart("file", uploadFile1);
			// multipart.addFilePart("file", uploadFile2);

			List<String> response1 = multipart.finish();

			out.println("SERVER REPLIED:");

			for (String line : response1) {
				// System.out.println(line);
				response.append(line);
			}
			responseString = response.toString();

		} catch (Exception e) {
			System.out.println(e.getMessage());
//e.printStackTrace();
			return "false";
		}
		return responseString;
	}

	public static String postExpertScript1(String filePath, PrintWriter out1) {
		String responseString = "";
		try {

			// List<String> response = new ArrayList<String>();
			StringBuilder response = new StringBuilder();
			try {
				// http://34.80.26.185:5015/project1
				/* URL url = new URL("http://34.80.26.185:5015/project5"); */
				URL url = new URL("http://34.80.26.185:5025/project5");
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestMethod("POST");

				// FileBody fileBody = new FileBody(new
				// File("E://ExcelData//HtmlToPdfFile//TestFolder//InputFileshtmlToJson//FME_MESSAGE_1549021398868.html"));
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
					BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					String line = null;
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
					// System.out.println("Response Returned
					// is"+response.toString());
					responseString = response.toString();
					reader.close();
					connection.disconnect();
				}
			} catch (Exception e) {
				e.printStackTrace(out1);
				return "false";
			}

		} catch (Exception e) {

		}
		return responseString;
	}

	public static String data(String filePath) {

		StringBuilder response = new StringBuilder();
		String filename = "";

		try {
			// byte[] b =getBytesFromFile(filep4);
			// response = sendPOST(bundle.getString("mergingapi"), b);
			File filep4 = new File(filePath);
			System.out.println("File Name : " + filep4.getName());

			MultipartUtility multipart = new MultipartUtility("", "UTF-8");
			multipart.addFilePart("file", filep4);

			List<String> response1 = multipart.finish();

			System.out.println("API  SERVER REPLIED:");

			for (String line : response1) {
				// System.out.println(line);
				response.append(line);
			}
			// response.toString();
			System.out.println("Merge API Response Returned is" + response.toString());


		} catch (Exception e) {
           e.printStackTrace();
			// TODO: handle exception
		}
		return filename;


	}
	
	public static String postPdfExpertScript(String filePath, PrintWriter out) {
		String responseString = "";
		try {

			File uploadFile1 = new File(filePath);
			String charset = "UTF-8";

			StringBuilder response = new StringBuilder();

			MultipartUtility multipart = new MultipartUtility("http://34.74.243.55:5029/project5", charset);

			multipart.addFormField("only_extract_html_line", "True");

			multipart.addFilePart("file", uploadFile1);
			// multipart.addFilePart("file", uploadFile2);

			List<String> response1 = multipart.finish();

			out.println("SERVER REPLIED:");

			for (String line : response1) {
				// System.out.println(line);
				response.append(line);
			}
			responseString = response.toString();

		} catch (Exception e) {
//e.printStackTrace();
			return "false";
		}
		return responseString;
	}
	

}
