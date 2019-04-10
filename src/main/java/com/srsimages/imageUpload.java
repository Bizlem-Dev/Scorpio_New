package com.srsimages;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

public class imageUpload {

	public static void main(String[] args) {

	}

	public static String saveImages(Session session, String fileName, PrintWriter out) {
		String count="";
		try {

			Node SRS = null;
			Node uploadedFile = null;
			if (session.getRootNode().hasNode("SRS")) {
				SRS = session.getRootNode().getNode("SRS");
			}else{
				SRS = session.getRootNode().addNode("SRS");
				session.save();
			}

			if (SRS.hasNode("uploadedFile")) {
				uploadedFile = SRS.getNode("uploadedFile");
				
			}else{
				uploadedFile = SRS.addNode("uploadedFile");
				uploadedFile.setProperty("jcr:count", String.valueOf(0));
				session.save();
			}

			 count = uploadedFile.getProperty("jcr:count").getString();
			String forid = String.valueOf(Integer.parseInt(count) + 1);

			String file_root_path = "/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/SRS_FileUpload/";
			File tomcateirectory = new File(file_root_path);
			if (!tomcateirectory.exists()) {

				tomcateirectory.mkdir();
				// out.println("folder created in Tomcate");
			}

			if (!uploadedFile.hasNode(forid)) {
				Node subNode = uploadedFile.addNode(forid);
				subNode.setProperty("Sr.No", forid);
				subNode.setProperty("fileLink", "https://dev.bizlem.io:8082/SRS_FileUpload/" + fileName);
				// subNode.setProperty("fileJson", "");
				subNode.setProperty("fileSendToScript",
						"/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/SRS_FileUpload/" + fileName);

				String fileJson=essarSteelPythonScriptUrlMethods("/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/SRS_FileUpload/" + fileName);
				
				
				subNode.setProperty("fileJson",
						fileJson);
				
				/*subNode.setProperty("fileLocation",
						"images/" + fileName);
				
				subNode.setProperty("SlingImages",
						"http://dev.bizlem.io:8082/scorpio/HOME/images/" + fileName);*/
				
				
				uploadedFile.setProperty("jcr:count", String.valueOf(Integer.parseInt(count) + 1));  //
				session.save();

			}

		} catch (Exception e) {
			out.println(e.getMessage());
		}
		return String.valueOf(Integer.parseInt(count) + 1);
	}
	
	
	 public static String essarSteelPythonScriptUrlMethods(String fileName){
	   		
	   		StringBuffer response1=null;
	   		
	   		try {

	   			String url = "";
	   			url="http://dev.bizlem.io:5017/?file="+fileName;
	   			
	   			//url="http://dev.bizlem.io:5006/?file="+fileName+ "&active=" + active;
	   			
	   			url = url.replace(" ", "%20");
	   			
	   			URL url1 = new URL(url);
	   			HttpURLConnection con = (HttpURLConnection) url1.openConnection();
	   			/*con.setConnectTimeout(900000);
	   			con.setReadTimeout(900000); // minute to milliseconds
*/	   			int responseCode = con.getResponseCode();
//	   			System.out.println("responseCode: "+responseCode);

	   			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	   			String inputLine;
	   			 response1 = new StringBuffer();

	   			while ((inputLine = in.readLine()) != null) {
	   				response1.append(inputLine);
	   			}
	   			in.close();

	   		} catch (Exception e) {
	   			//System.out.println(e.getMessage());
//	   			out.println(e.getMessage());
	   			return "false";
	   			
	   		}
	   		
	   		return response1.toString();
	   		
	   	}

	public static JSONObject fetchFileUploadedData(Session session, PrintWriter out, String countRange){
		JSONObject mainJsonobj = null;
		try {
			
			Node SRS=null;
			Node uploadedFile=null;
			if(session.getRootNode().hasNode("SRS")){
				SRS=session.getRootNode().getNode("SRS");
			}
			
			if(SRS.hasNode("uploadedFile")){
				uploadedFile=SRS.getNode("uploadedFile");
				
			}
			
			if(uploadedFile.hasNodes()){
				
				//long size = uploadedFile.getNodes().getSize();
				//long countRangeI=Long.valueOf(countRange);
				JSONObject dataui=null;
				JSONArray keyNameObjectJsonarray = new JSONArray();
				//NodeIterator itr=uploadedFile.getNodes();
				
				//long from= size - countRangeI;
				//long size1 = from + 1;
				
				
//				while(itr.hasNext()){
				//for(long count=0;count <size;count++){
				if(!uploadedFile.hasNode(countRange) ){
					countRange = "1";
				}
				
					if( uploadedFile.hasNode(countRange) ){
						Node nextNode = uploadedFile.getNode(countRange);
//				Node nextNode=itr.nextNode();
				
			String fileLocation="";
			String fileJson="";
			 dataui=new JSONObject();
			
			if(nextNode.hasProperty("fileLink")){
				fileLocation=nextNode.getProperty("fileLink").getString();
				dataui.put("fileLocation", fileLocation);
			}/*else {
				fileLocation=nextNode.getProperty("fileLink").getString();
				dataui.put("fileLink", fileLocation);
			}*/
			
			if(nextNode.hasProperty("fileJson")){
				fileJson=nextNode.getProperty("fileJson").getString();
//				out.println("fileJson: "+fileJson);
			}
			String label="";
			if(nextNode.hasProperty("label")){
				label=nextNode.getProperty("label").getString();
				dataui.put("label", label);
			}
			
			JSONObject data=new JSONObject(fileJson);
              if(data.has("textAnnotations")){
            	 JSONArray datajsonarray=data.getJSONArray("textAnnotations");
            	 for(int i=0;i<datajsonarray.length();i++){
            		JSONObject datajsonobj= datajsonarray.getJSONObject(i);
            		String description="";
            		/*if(i==0){*/
            		if(datajsonobj.has("description")){
            			description=datajsonobj.getString("description");
//            			description = description.replace(System.getProperty("line.separator"), "\\n");
//            			description=description.replaceAll("", " ");
            			
            			
            			dataui.put("description", description);
            		}
            	/*} // for only one
*/            		
            	 }// for close
            	 
            	 
            	
            	 
              } // has check text 
				
			
			
			
              if (dataui.length() > 0) {
					keyNameObjectJsonarray.put(dataui);
				}
              
			} // if while
			
				//}
				mainJsonobj = new JSONObject();
				mainJsonobj.put("fileList", keyNameObjectJsonarray);
				
			} // if
			
			
			
		} catch (Exception e) {
			e.printStackTrace(out);
		}
		return mainJsonobj;
	}

}
