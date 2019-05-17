package activemq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.servlet.ServletException;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.jcr.api.SlingRepository;

import com.errorreport.StatusForUi;
import com.mongocode.MongoDbConnectionSuccessFullyProcessed;
import com.mycode.SaveReportDataClassNewStr;
import com.readGmail.GmailMethods;
import com.reportinformationsystem.SaveReportDataClass;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest; 
import org.apache.sling.api.SlingHttpServletResponse;


@SlingServlet(paths = "/getConsumerResponse")
public class ConsumerServletGetResponse extends SlingAllMethodsServlet {
	private static final long serialVersionUID = 1;
	@SuppressWarnings("deprecation")
	@Reference
	private SlingRepository repo;

	static ResourceBundle bundle = ResourceBundle.getBundle("config");
	Session session = null;
	
	@Override
	public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {

	}
	
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		 String line = "";
		 String emailUrl="";
		 String textSentMailTime="";
		 String subjectNodePath="";
		 String from_Source="";
		 String timestampDate="";
		 String timestampDateAndTime="";
		 String extension="";
		 String subjectNodeNode="";
		 String ExpertScriptCallHere="";
		 String finalwithQty="";
		 String formatedDate="";
		 int mongoUpdateSuccessCount=0;
		 BufferedReader br=null;
		 StringBuffer sb=null;
		 
      try {
			
			session = repo.login(new SimpleCredentials("admin", "admin".toCharArray()));
		        
               br = request.getReader();
			
			 sb = new StringBuffer();
			while((line = br.readLine()) != null){
				sb.append(line);
			}
			
			String producerData=sb.toString();
		        
			if( !GmailMethods.isNullString(producerData) ){
				
				boolean isjsonValid=SaveReportDataClass.isJSONValid(producerData);
				if( isjsonValid ){
					Node cronSubNode=null;
					JSONObject producerJsonObj=new JSONObject(producerData);
					
					if(producerJsonObj.has("subjectNodeNode")){
						subjectNodeNode=producerJsonObj.getString("subjectNodeNode");
						cronSubNode=session.getNode(subjectNodeNode);
					}
					
					if( producerJsonObj!=null && producerJsonObj.length()!=0 ){
						
						if(producerJsonObj.has("emailUrl")){
							emailUrl=producerJsonObj.getString("emailUrl");
						}if(producerJsonObj.has("textSentMailTime")){
							textSentMailTime=producerJsonObj.getString("textSentMailTime");
						}if(producerJsonObj.has("subjectNodePath")){
							subjectNodePath=producerJsonObj.getString("subjectNodePath");
						}if(producerJsonObj.has("from_Source")){
							from_Source=producerJsonObj.getString("from_Source");
						}if(producerJsonObj.has("timestampDate")){
							timestampDate=producerJsonObj.getString("timestampDate");
						}if(producerJsonObj.has("timestampDateAndTime")){
							timestampDateAndTime=producerJsonObj.getString("timestampDateAndTime");
						}if(producerJsonObj.has("extension")){
							extension=producerJsonObj.getString("extension");
						}if(producerJsonObj.has("ExpertScriptCallHere")){
							ExpertScriptCallHere=producerJsonObj.getString("ExpertScriptCallHere");
						}if(producerJsonObj.has("finalwithQty")){
							finalwithQty=producerJsonObj.getString("finalwithQty");
						}
						
						SaveReportDataClassNewStr.parseAllReportFromJsonToSave(out, session, finalwithQty, emailUrl, textSentMailTime,subjectNodePath, from_Source, timestampDate, timestampDateAndTime, extension, ExpertScriptCallHere);
							
							 Date date = new Date();
							 SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy hh:mm");
							 if( formatter2!=null ){
								 formatedDate= formatter2.format(date);
								 cronSubNode.setProperty("savingTime", formatedDate);
							 }
							
						boolean reportCheck=StatusForUi.statusCheckReportForUi(finalwithQty);
						if(reportCheck==true){
							cronSubNode.setProperty("Ui_Update", "SUCCESS");
							String allreport=StatusForUi.getReportTypeUi(finalwithQty);
							cronSubNode.setProperty("Type", allreport);
							mongoUpdateSuccessCount++;
							
						}
						
						
					} // json check null and length!=0
					else{
						cronSubNode.setProperty("Ui_Update", "FAILED TO RECOGNIZED REPORT");
					}
					
				} // is json valid check
				
			} // check producerData jsonString  blank
			
			session.save();
			
		} catch (Exception e) {
//			e.printStackTrace(out);
			System.out.println(e.getMessage());
		}finally{
			 if( GmailMethods.isNullString(line) ){
				 line=null;
			 } if( GmailMethods.isNullString(emailUrl) ){
				 emailUrl=null;
			 }if( GmailMethods.isNullString(textSentMailTime) ){
				 textSentMailTime=null;
			 }if( GmailMethods.isNullString(subjectNodePath) ){
				 subjectNodePath=null;
			 }if( GmailMethods.isNullString(from_Source) ){
				 from_Source=null;
			 }if( GmailMethods.isNullString(timestampDate) ){
				 timestampDate=null;
			 }if( GmailMethods.isNullString(timestampDateAndTime) ){
				 timestampDateAndTime=null;
			 }if( GmailMethods.isNullString(extension) ){
				 extension=null;
			 }if( GmailMethods.isNullString(subjectNodeNode) ){
				 subjectNodeNode=null;
			 }if( GmailMethods.isNullString(ExpertScriptCallHere) ){
				 ExpertScriptCallHere=null;
			 }if( GmailMethods.isNullString(finalwithQty) ){
				 finalwithQty=null;
			 }if( GmailMethods.isNullString(formatedDate) ){
				 formatedDate=null;
			 }
			 
			 if( mongoUpdateSuccessCount>0 ){
				 if( !GmailMethods.isNullString(timestampDate) ){
						MongoDbConnectionSuccessFullyProcessed.saveGmailReadCount("SuccessMail", timestampDate, String.valueOf(mongoUpdateSuccessCount));
					}
			 }
			 br.close();
			 out.close();
			 
		}


	}
	

}
