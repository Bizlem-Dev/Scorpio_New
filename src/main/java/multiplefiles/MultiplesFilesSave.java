package multiplefiles;

import java.io.File;
import java.io.PrintWriter;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.reportinformationsystem.ChangedApiWithoutMultipleArray;

public class MultiplesFilesSave {

	public static void main(String[] args) {
		
	}
	
	public static void saveFiles(Session session, String fileName, PrintWriter out) {
		String count="";
		try {

			Node SRS = null;
			Node uploadedFile = null;
			
             String fileNameSplit[]=fileName.split(",");
			
			for (int i = 0; i < fileNameSplit.length; i++) {
				
				String fileNameSplitSingleWise=fileNameSplit[i];
			
			if (session.getRootNode().hasNode("MultiplesFiles")) {
				SRS = session.getRootNode().getNode("MultiplesFiles");
			}else{
				SRS = session.getRootNode().addNode("MultiplesFiles");
				session.save();
			}

			if (SRS.hasNode("Files")) {
				uploadedFile = SRS.getNode("Files");
				
			}else{
				uploadedFile = SRS.addNode("Files");
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
				subNode.setProperty("fileLink", "http://dev.bizlem.io:8082/SRS_FileUpload/" + fileNameSplitSingleWise);
				subNode.setProperty("fileName", fileNameSplitSingleWise);
				
				uploadedFile.setProperty("jcr:count", String.valueOf(Integer.parseInt(count) + 1));  //
				session.save();

			}
			
			}

		} catch (Exception e) {
			out.println(e.getMessage());
		}
//		return String.valueOf(Integer.parseInt(count) + 1);
	}
	

	public static JSONObject fetchFileUploadedData(Session session, PrintWriter out){
		JSONObject mainJsonobj = null;
		try {
			
			Node SRS=null;
			Node uploadedFile=null;
			if(session.getRootNode().hasNode("MultiplesFiles")){
				SRS=session.getRootNode().getNode("MultiplesFiles");
			}
			
			if(SRS.hasNode("Files")){
				uploadedFile=SRS.getNode("Files");
				
			}
			
			if(uploadedFile.hasNodes()){
				
				JSONObject dataui=null;
				JSONArray keyNameObjectJsonarray = new JSONArray();
				NodeIterator itr=uploadedFile.getNodes();
				
				
				while(itr.hasNext()){
				Node nextNode=itr.nextNode();
				String NodeName=nextNode.getName().toString();
			
			 String fileLocation="";
			 dataui=new JSONObject();
			
			if(nextNode.hasProperty("fileLink")){
				fileLocation=nextNode.getProperty("fileLink").getString();
				dataui.put("fileLocation", fileLocation);
			}
			
			String fileName="";
			if(nextNode.hasProperty("fileName")){
				fileName=nextNode.getProperty("fileName").getString();
				dataui.put("fileName", fileName);
			}
			String srNo="";
			if(nextNode.hasProperty("Sr.No")){
				srNo=nextNode.getProperty("Sr.No").getString();
				dataui.put("srNo", srNo);
			}
			dataui.put("nodeName", NodeName);
			
              if (dataui.length() > 0) {
					keyNameObjectJsonarray.put(dataui);
				}
              
			} //  while
			
				mainJsonobj = new JSONObject();
				mainJsonobj.put("fileList", keyNameObjectJsonarray);
				
			} // if
			
			
			
		} catch (Exception e) {
			e.printStackTrace(out);
		}
		return mainJsonobj;
	}
	

}
