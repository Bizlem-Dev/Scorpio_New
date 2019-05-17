package ChangedStructureCurrent;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.Workspace;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.mysql.jdbc.Connection;
import com.readGmail.GmailMethods;
import com.readGmail.MysqlConnection;
import com.readGmail.SlingMethods;

public class ChangedStructureCurrent_Methods {

	public static void main(String[] args) {

	}
	
	public void reportTypeSlingStructure(PrintWriter out, Session session){
		
		try {
			
			Node scorpioDataBase=null;
			Node ReportType=null;
			
			if(session.getRootNode().hasNode("scorpioDataBase")){
				scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
			}else{
				scorpioDataBase=session.getRootNode().addNode("scorpioDataBase");
				//session.save();
			}
			
			
			if(scorpioDataBase.hasNode("ReportType")){
				ReportType=scorpioDataBase.getNode("ReportType");
			}else{
				ReportType=scorpioDataBase.addNode("ReportType");
				ReportType.setProperty("jcr:count", String.valueOf(0));
				session.save();
			}
			
			String reportTypeArray[]={"Tonnage","Spot","TimeCharterReports","BrokerTcRate", "TC", "Position List"};
			
			
			
			for(int i=0;i<reportTypeArray.length;i++){
				String arrayIndexData=reportTypeArray[i];
				
				Node arrayIndexDataNode=null;
				String jcrCount=ReportType.getProperty("jcr:count").getString();
				int jcrCountIntValue = Integer.parseInt(jcrCount)+1;
				String reportTypeId=String.valueOf(jcrCountIntValue);
				
				
				if (ReportType.hasNode(arrayIndexData)) {
					arrayIndexDataNode=ReportType.getNode(arrayIndexData);
				}else{
					arrayIndexDataNode=ReportType.addNode(arrayIndexData);
					session.save();
				}
				
				arrayIndexDataNode.setProperty("reportTypeId", reportTypeId);
				arrayIndexDataNode.setProperty("reportType", arrayIndexData);
				ReportType.setProperty("jcr:count", String.valueOf(String.valueOf(Integer.parseInt(jcrCount)+1)));
				session.save();
			}
			
			
			
		} catch (Exception e) {
//			out.println(e.getMessage());
		}
	}
	
public String reportTypeSlingAdd(PrintWriter out, Session session, String passData){
	String reportTypeId="";
		try {
			
			Node scorpioDataBase=null;
			Node ReportType=null;
			
			if(session.getRootNode().hasNode("scorpioDataBase")){
				scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
			}else{
				scorpioDataBase=session.getRootNode().addNode("scorpioDataBase");
				session.save();
			}
			
			
			if(scorpioDataBase.hasNode("ReportType")){
				ReportType=scorpioDataBase.getNode("ReportType");
			}else{
				ReportType=scorpioDataBase.addNode("ReportType");
				ReportType.setProperty("jcr:count", String.valueOf(0));
				session.save();
			}
			
//			String reportTypeArray[]={};
			
//			for(int i=0;i<reportTypeArray.length;i++){
				String arrayIndexData=passData;
				
				Node arrayIndexDataNode=null;
				String jcrCount=ReportType.getProperty("jcr:count").getString();
				int jcrCountIntValue = Integer.parseInt(jcrCount)+1;
				 reportTypeId=String.valueOf(jcrCountIntValue);
				
				
				if (ReportType.hasNode(arrayIndexData)) {
					arrayIndexDataNode=ReportType.getNode(arrayIndexData);
				}else{
					arrayIndexDataNode=ReportType.addNode(arrayIndexData);
					session.save();
				}
				
				arrayIndexDataNode.setProperty("reportTypeId", reportTypeId);
				arrayIndexDataNode.setProperty("reportType", arrayIndexData);
				ReportType.setProperty("jcr:count", String.valueOf(String.valueOf(Integer.parseInt(jcrCount)+1)));
				session.save();
//			}
			
		} catch (Exception e) {
//			out.println(e.getMessage());
		}
		
		return reportTypeId;
	}
	
public void OperatorsSlingStructure(PrintWriter out, Session session){
		
		try {
			
			Node scorpioDataBase=null;
			Node ReportType=null;
			
			if(session.getRootNode().hasNode("scorpioDataBase")){
				scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
			}else{
				scorpioDataBase=session.getRootNode().addNode("scorpioDataBase");
				//session.save();
			}
			
			
			if(scorpioDataBase.hasNode("Operators")){
				ReportType=scorpioDataBase.getNode("Operators");
			}else{
				ReportType=scorpioDataBase.addNode("Operators");
				ReportType.setProperty("jcr:count", String.valueOf(0));
				//session.save();
			}
			
			String reportTypeArray[]={"IMS","Hafnia","IPG","Vitol","Valero","IMS Greece","D'Amico","Knutsen","Thenamaris","Trafigura"
					,"Ancora","Norient","Maersk","CPTA","Montanari","MedNav","DLP","Product Shipping & Trading","Scorpio","Socomar","Handytankers","Elka","AMPTC"
					,"CSSA","BW Pacific","Avin","Ardmoreship","Zodiac","Island Nav","Vietsea","Diamond S","Bull","Hansa","Lyras","Eastmed","GS Caltex"};
			
			
			
			for(int i=0;i<reportTypeArray.length;i++){
				String arrayIndexData=reportTypeArray[i];
				String data=arrayIndexData.replace(" ", "_").replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9]", "_");
				
				Node arrayIndexDataNode=null;
				String jcrCount=ReportType.getProperty("jcr:count").getString();
				int jcrCountIntValue = Integer.parseInt(jcrCount)+1;
				String reportTypeId=String.valueOf(jcrCountIntValue);
				
				
				if (ReportType.hasNode(data)) {
					arrayIndexDataNode=ReportType.getNode(data);
				}else{
					arrayIndexDataNode=ReportType.addNode(data);
					//session.save();
				}
				
				arrayIndexDataNode.setProperty("operatorsNameId", reportTypeId);
				arrayIndexDataNode.setProperty("operatorsName", arrayIndexData);
				ReportType.setProperty("jcr:count", String.valueOf(String.valueOf(Integer.parseInt(jcrCount)+1)));
				session.save();
			}
			
			
			
		} catch (Exception e) {
//			out.println(e.getMessage());
		}
	}

public String OperatorsSlingAdd(PrintWriter out, Session session, String operatorsNamePassData){
	String reportTypeId="";
	try {
		
		Node scorpioDataBase=null;
		Node ReportType=null;
		
		if(session.getRootNode().hasNode("scorpioDataBase_16_feb_19")){
			scorpioDataBase=session.getRootNode().getNode("scorpioDataBase_16_feb_19");
		}else{
			scorpioDataBase=session.getRootNode().addNode("scorpioDataBase_16_feb_19");
			session.save();
		}
		
		
		if(scorpioDataBase.hasNode("Operators")){
			ReportType=scorpioDataBase.getNode("Operators");
		}else{
			ReportType=scorpioDataBase.addNode("Operators");
			ReportType.setProperty("jcr:count", String.valueOf(0));
			session.save();
		}
		
			String arrayIndexData=operatorsNamePassData;
			String data=arrayIndexData.replace(" ", "_").replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9]", "_");
			
			Node arrayIndexDataNode=null;
			String jcrCount=ReportType.getProperty("jcr:count").getString();
			int jcrCountIntValue = Integer.parseInt(jcrCount)+1;
			 reportTypeId=String.valueOf(jcrCountIntValue);
			
			
			if (ReportType.hasNode(data)) {
				arrayIndexDataNode=ReportType.getNode(data);
			}else{
				arrayIndexDataNode=ReportType.addNode(data);
				session.save();
			}
			
			arrayIndexDataNode.setProperty("operatorsNameId", reportTypeId);
			arrayIndexDataNode.setProperty("operatorsName", arrayIndexData.toUpperCase());
			ReportType.setProperty("jcr:count", String.valueOf(String.valueOf(Integer.parseInt(jcrCount)+1)));
			session.save();
		
	} catch (Exception e) {
//		out.println(e.getMessage());
	}
	return reportTypeId;
}

public void ChartererSlingStructure(PrintWriter out, Session session){
	
	try {
		
		Node scorpioDataBase=null;
		Node ReportType=null;
		
		if(session.getRootNode().hasNode("scorpioDataBase")){
			scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
		}else{
			scorpioDataBase=session.getRootNode().addNode("scorpioDataBase");
			session.save();
		}
		
		
		if(scorpioDataBase.hasNode("Charterer")){
			ReportType=scorpioDataBase.getNode("Charterer");
		}else{
			ReportType=scorpioDataBase.addNode("Charterer");
			ReportType.setProperty("jcr:count", String.valueOf(0));
			session.save();
		}
		
		String reportTypeArray[]={"BBNAFT","Exxon","REPSOL","LITASCO","ST Shipping","Total","BP","Petroineos","Valero","Irving"
				,"Vitol","Litasco","UML","Petrobras","SARAS","Shell","Unipec","Reliance","BPCL","P66","PetroVietnam","Scorpio","Trafigura"
				,"Norden","Maersk"};
		
		
		
		for(int i=0;i<reportTypeArray.length;i++){
			String arrayIndexData=reportTypeArray[i];
			String data=arrayIndexData.replace(" ", "_").replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9]", "_");
			
			Node arrayIndexDataNode=null;
			String jcrCount=ReportType.getProperty("jcr:count").getString();
			int jcrCountIntValue = Integer.parseInt(jcrCount)+1;
			String reportTypeId=String.valueOf(jcrCountIntValue);
			
			
			if (ReportType.hasNode(data)) {
				arrayIndexDataNode=ReportType.getNode(data);
			}else{
				arrayIndexDataNode=ReportType.addNode(data);
				//session.save();
			}
			
			arrayIndexDataNode.setProperty("chartererNameId", reportTypeId);
			arrayIndexDataNode.setProperty("chartererName", arrayIndexData);
			ReportType.setProperty("jcr:count", String.valueOf(String.valueOf(Integer.parseInt(jcrCount)+1)));
			session.save();
		}
		
		
		
	} catch (Exception e) {
//		out.println(e.getMessage());
	}
}


public String ChartererSlingAdd(PrintWriter out, Session session, String chartererNamePassData){
	String reportTypeId="";
	try {
		
		Node scorpioDataBase=null;
		Node ReportType=null;
		
		if(session.getRootNode().hasNode("scorpioDataBase")){
			scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
		}else{
			scorpioDataBase=session.getRootNode().addNode("scorpioDataBase");
			session.save();
		}
		
		
		if(scorpioDataBase.hasNode("Charterer")){
			ReportType=scorpioDataBase.getNode("Charterer");
		}else{
			ReportType=scorpioDataBase.addNode("Charterer");
			ReportType.setProperty("jcr:count", String.valueOf(0));
			session.save();
		}
		
			String arrayIndexData=chartererNamePassData;
			String data=arrayIndexData.replace(" ", "_").replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9]", "_");
			
			Node arrayIndexDataNode=null;
			String jcrCount=ReportType.getProperty("jcr:count").getString();
			int jcrCountIntValue = Integer.parseInt(jcrCount)+1;
			 reportTypeId=String.valueOf(jcrCountIntValue);
			
			
			if (ReportType.hasNode(data)) {
				arrayIndexDataNode=ReportType.getNode(data);
			}else{
				arrayIndexDataNode=ReportType.addNode(data);
				session.save();
			}
			
			arrayIndexDataNode.setProperty("chartererNameId", reportTypeId);
			arrayIndexDataNode.setProperty("chartererName", arrayIndexData);
			ReportType.setProperty("jcr:count", String.valueOf(String.valueOf(Integer.parseInt(jcrCount)+1)));
			session.save();
		
	} catch (Exception e) {
//		out.println(e.getMessage());
	}
	return reportTypeId;
}


public void DeliveryPlaceSlingStructure(PrintWriter out, Session session){
	
	try {
		
		Node scorpioDataBase=null;
		Node ReportType=null;
		
		if(session.getRootNode().hasNode("scorpioDataBase")){
			scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
		}else{
			scorpioDataBase=session.getRootNode().addNode("scorpioDataBase");
//			session.save();
		}
		
		
		if(scorpioDataBase.hasNode("Delivery Place")){
			ReportType=scorpioDataBase.getNode("Delivery Place");
		}else{
			ReportType=scorpioDataBase.addNode("Delivery Place");
			ReportType.setProperty("jcr:count", String.valueOf(0));
//			session.save();
		}
		
		String reportTypeArray[]={"India","extended","Brazil","Ex-Yard","AG","Singapore","Dongguan"
				};
		
		
		
		for(int i=0;i<reportTypeArray.length;i++){
			String arrayIndexData=reportTypeArray[i];
			String data=arrayIndexData.replace(" ", "_").replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9]", "_");
			
			Node arrayIndexDataNode=null;
			String jcrCount=ReportType.getProperty("jcr:count").getString();
			int jcrCountIntValue = Integer.parseInt(jcrCount)+1;
			String reportTypeId=String.valueOf(jcrCountIntValue);
			
			
			if (ReportType.hasNode(data)) {
				arrayIndexDataNode=ReportType.getNode(data);
			}else{
				arrayIndexDataNode=ReportType.addNode(data);
//				session.save();
			}
			
			arrayIndexDataNode.setProperty("placeNameId", reportTypeId);
			arrayIndexDataNode.setProperty("placeName", arrayIndexData);
			ReportType.setProperty("jcr:count", String.valueOf(String.valueOf(Integer.parseInt(jcrCount)+1)));
			session.save();
		}
		
		
		
	} catch (Exception e) {
//		out.println(e.getMessage());
	}
}

public String DeliveryPlaceSlingAdd(PrintWriter out, Session session, String placeNamePassData){
	String reportTypeId="";
	try {
		
		Node scorpioDataBase=null;
		Node ReportType=null;
		
		if(session.getRootNode().hasNode("scorpioDataBase")){
			scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
		}else{
			scorpioDataBase=session.getRootNode().addNode("scorpioDataBase");
			session.save();
		}
		
		
		if(scorpioDataBase.hasNode("Delivery Place")){
			ReportType=scorpioDataBase.getNode("Delivery Place");
		}else{
			ReportType=scorpioDataBase.addNode("Delivery Place");
			ReportType.setProperty("jcr:count", String.valueOf(0));
			session.save();
		}
		
			String arrayIndexData=placeNamePassData;
			String data=arrayIndexData.replace(" ", "_").replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9]", "_");
			
			Node arrayIndexDataNode=null;
			String jcrCount=ReportType.getProperty("jcr:count").getString();
			int jcrCountIntValue = Integer.parseInt(jcrCount)+1;
			 reportTypeId=String.valueOf(jcrCountIntValue);
			
			
			if (ReportType.hasNode(data)) {
				arrayIndexDataNode=ReportType.getNode(data);
			}else{
				arrayIndexDataNode=ReportType.addNode(data);
				session.save();
			}
			
			arrayIndexDataNode.setProperty("placeNameId", reportTypeId);
			arrayIndexDataNode.setProperty("placeName", arrayIndexData);
			ReportType.setProperty("jcr:count", String.valueOf(String.valueOf(Integer.parseInt(jcrCount)+1)));
			session.save();
		
	} catch (Exception e) {
//		out.println(e.getMessage());
	}
	return reportTypeId;
}

public void CargoGradeSlingStructure(PrintWriter out, Session session){
	
	try {
		
		Node scorpioDataBase=null;
		Node ReportType=null;
		
		if(session.getRootNode().hasNode("scorpioDataBase")){
			scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
		}else{
			scorpioDataBase=session.getRootNode().addNode("scorpioDataBase");
//			session.save();
		}
		
		
		if(scorpioDataBase.hasNode("Cargo Grade")){
			ReportType=scorpioDataBase.getNode("Cargo Grade");
		}else{
			ReportType=scorpioDataBase.addNode("Cargo Grade");
			ReportType.setProperty("jcr:count", String.valueOf(0));
//			session.save();
		}
		
		String reportTypeArray[]={"Naptha","ULSD","FO","Ultra Low Sulphur Diesel","UMS","Crude","GO","VGO","DPP","Jet","CPP"
				};
		
		
		
		for(int i=0;i<reportTypeArray.length;i++){
			String arrayIndexData=reportTypeArray[i];
			String data=arrayIndexData.replace(" ", "_").replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9]", "_");
			
			Node arrayIndexDataNode=null;
			String jcrCount=ReportType.getProperty("jcr:count").getString();
			int jcrCountIntValue = Integer.parseInt(jcrCount)+1;
			String reportTypeId=String.valueOf(jcrCountIntValue);
			
			
			if (ReportType.hasNode(data)) {
				arrayIndexDataNode=ReportType.getNode(data);
			}else{
				arrayIndexDataNode=ReportType.addNode(data);
//				session.save();
			}
			
			arrayIndexDataNode.setProperty("cargoGradeId", reportTypeId);
			arrayIndexDataNode.setProperty("cargoGrade", arrayIndexData);
			ReportType.setProperty("jcr:count", String.valueOf(String.valueOf(Integer.parseInt(jcrCount)+1)));
			session.save();
		}
		
		
		
	} catch (Exception e) {
//		out.println(e.getMessage());
	}
}

public String CargoGradeSlingAdd(PrintWriter out, Session session, String cargoGradePassData){
	String reportTypeId="";
	try {
		
		Node scorpioDataBase=null;
		Node ReportType=null;
		
		if(session.getRootNode().hasNode("scorpioDataBase")){
			scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
		}else{
			scorpioDataBase=session.getRootNode().addNode("scorpioDataBase");
			session.save();
		}
		
		
		if(scorpioDataBase.hasNode("Cargo Grade")){
			ReportType=scorpioDataBase.getNode("Cargo Grade");
		}else{
			ReportType=scorpioDataBase.addNode("Cargo Grade");
			ReportType.setProperty("jcr:count", String.valueOf(0));
			session.save();
		}
		
			String arrayIndexData=cargoGradePassData;
			String data=arrayIndexData.replace(" ", "_").replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9]", "_");
			
			Node arrayIndexDataNode=null;
			String jcrCount=ReportType.getProperty("jcr:count").getString();
			int jcrCountIntValue = Integer.parseInt(jcrCount)+1;
			 reportTypeId=String.valueOf(jcrCountIntValue);
			
			
			if (ReportType.hasNode(data)) {
				arrayIndexDataNode=ReportType.getNode(data);
			}else{
				arrayIndexDataNode=ReportType.addNode(data);
				session.save();
			}
			
			arrayIndexDataNode.setProperty("cargoGradeId", reportTypeId);
			arrayIndexDataNode.setProperty("cargoGrade", arrayIndexData);
			ReportType.setProperty("jcr:count", String.valueOf(String.valueOf(Integer.parseInt(jcrCount)+1)));
			session.save();
		
	} catch (Exception e) {
//		out.println(e.getMessage());
	}
	return reportTypeId;
}

	
public void SourceSlingStructure(PrintWriter out, Session session){
		
		try {
			
			Node scorpioDataBase=null;
			Node ReportType=null;
			
			if(session.getRootNode().hasNode("scorpioDataBase")){
				scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
			}else{
				scorpioDataBase=session.getRootNode().addNode("scorpioDataBase");
//				session.save();
			}
			
			
			if(scorpioDataBase.hasNode("Source")){
				ReportType=scorpioDataBase.getNode("Source");
			}else{
				ReportType=scorpioDataBase.addNode("Source");
				ReportType.setProperty("jcr:count", String.valueOf(0));
//				session.save();
			}
			
			String reportTypeArray[]={"Affinity","Brassington","Clarksons","Braemar","Marex","Sernavimar","Gibson","Bravo","Steem","Howe Robinson"
					,"MJLF","Vantage","Alliance","brsbrokers","Bravo Tankers","EA Gibson","Essex","SSY","Charles R Weber","Mearsk","Poten","Infinity","McQuilling Partners"};
			
			
			
			for(int i=0;i<reportTypeArray.length;i++){
				String arrayIndexData=reportTypeArray[i];
				String data=arrayIndexData.replace(" ", "_").replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9]", "_");
				
				Node arrayIndexDataNode=null;
				String jcrCount=ReportType.getProperty("jcr:count").getString();
				int jcrCountIntValue = Integer.parseInt(jcrCount)+1;
				String reportTypeId=String.valueOf(jcrCountIntValue);
				
				
				if (ReportType.hasNode(data)) {
					arrayIndexDataNode=ReportType.getNode(data);
				}else{
					arrayIndexDataNode=ReportType.addNode(data);
//					session.save();
				}
				
				arrayIndexDataNode.setProperty("brokerNameId", reportTypeId);
				arrayIndexDataNode.setProperty("brokerName", arrayIndexData);
				ReportType.setProperty("jcr:count", String.valueOf(String.valueOf(Integer.parseInt(jcrCount)+1)));
				session.save();
			}
			
			
			
		} catch (Exception e) {
//			out.println(e.getMessage());
		}
	}
	
public String SourceSlingAdd(PrintWriter out, Session session, String brokerNamePassData){
	String reportTypeId="";
	try {
		
		Node scorpioDataBase=null;
		Node ReportType=null;
		
		if(session.getRootNode().hasNode("scorpioDataBase")){
			scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
		}else{
			scorpioDataBase=session.getRootNode().addNode("scorpioDataBase");
//			session.save();
		}
		
		
		if(scorpioDataBase.hasNode("Source")){
			ReportType=scorpioDataBase.getNode("Source");
		}else{
			ReportType=scorpioDataBase.addNode("Source");
			ReportType.setProperty("jcr:count", String.valueOf(0));
			session.save();
		}
		
			String arrayIndexData=brokerNamePassData;
			String data=arrayIndexData.replace(" ", "_").replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9]", "_");
			
			Node arrayIndexDataNode=null;
			String jcrCount=ReportType.getProperty("jcr:count").getString();
			int jcrCountIntValue = Integer.parseInt(jcrCount)+1;
			 reportTypeId=String.valueOf(jcrCountIntValue);
			
			
			if (ReportType.hasNode(data)) {
				arrayIndexDataNode=ReportType.getNode(data);
			}else{
				arrayIndexDataNode=ReportType.addNode(data);
				session.save();
			}
			
			arrayIndexDataNode.setProperty("brokerNameId", reportTypeId);
			arrayIndexDataNode.setProperty("brokerName", arrayIndexData);
			ReportType.setProperty("jcr:count", String.valueOf(String.valueOf(Integer.parseInt(jcrCount)+1)));
			session.save();
	
	} catch (Exception e) {
//		out.println(e.getMessage());
	}
	return reportTypeId;
}
	
public void CargoTypeSlingStructure(PrintWriter out, Session session){
		
		try {
			
			Node scorpioDataBase=null;
			Node ReportType=null;
			
			if(session.getRootNode().hasNode("scorpioDataBase")){
				scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
			}else{
				scorpioDataBase=session.getRootNode().addNode("scorpioDataBase");
//				session.save();
			}
			
			
			if(scorpioDataBase.hasNode("CargoType")){
				ReportType=scorpioDataBase.getNode("CargoType");
			}else{
				ReportType=scorpioDataBase.addNode("CargoType");
				ReportType.setProperty("jcr:count", String.valueOf(0));
				session.save();
			}
			
			HashMap< String, String> map=new HashMap<String, String>();
			map.put("Cpp", "Clean/Cpp");
			map.put("Dpp", "Dirty/Dpp");
			 
			
			 for ( Map.Entry<String, String> m : map.entrySet() ){ 
                 String arrayIndexData= m.getKey().toString();
                 String property = m.getValue().toString();
				
				Node arrayIndexDataNode=null;
				String jcrCount=ReportType.getProperty("jcr:count").getString();
				int jcrCountIntValue = Integer.parseInt(jcrCount)+1;
				String reportTypeId=String.valueOf(jcrCountIntValue);
				
				
				if (ReportType.hasNode(arrayIndexData)) {
					arrayIndexDataNode=ReportType.getNode(arrayIndexData);
				}else{
					arrayIndexDataNode=ReportType.addNode(arrayIndexData);
					session.save();
				}
				
				arrayIndexDataNode.setProperty("cargoType", property);
				arrayIndexDataNode.setProperty("cargoTypeId", reportTypeId);
				
				ReportType.setProperty("jcr:count", String.valueOf(String.valueOf(Integer.parseInt(jcrCount)+1)));
				session.save();
			}
			
			
			
		} catch (Exception e) {
//			out.println(e.getMessage());
		}
	}

public String CargoTypeSlingAdd(PrintWriter out, Session session, String cargoTypePassData){
	String reportTypeId="";
	try {
		
		Node scorpioDataBase=null;
		Node ReportType=null;
		
		if(session.getRootNode().hasNode("scorpioDataBase")){
			scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
		}else{
			scorpioDataBase=session.getRootNode().addNode("scorpioDataBase");
//			session.save();
		}
		
		
		if(scorpioDataBase.hasNode("CargoType")){
			ReportType=scorpioDataBase.getNode("CargoType");
		}else{
			ReportType=scorpioDataBase.addNode("CargoType");
			ReportType.setProperty("jcr:count", String.valueOf(0));
			session.save();
		}
		 
             String arrayIndexData= cargoTypePassData;
//             String property = m.getValue().toString();
			
			Node arrayIndexDataNode=null;
			String jcrCount=ReportType.getProperty("jcr:count").getString();
			int jcrCountIntValue = Integer.parseInt(jcrCount)+1;
			 reportTypeId=String.valueOf(jcrCountIntValue);
			
			
			if (ReportType.hasNode(arrayIndexData)) {
				arrayIndexDataNode=ReportType.getNode(arrayIndexData);
			}else{
				arrayIndexDataNode=ReportType.addNode(arrayIndexData);
				session.save();
			}
			
			arrayIndexDataNode.setProperty("cargoType", arrayIndexData);
			arrayIndexDataNode.setProperty("cargoTypeId", reportTypeId);
			
			ReportType.setProperty("jcr:count", String.valueOf(String.valueOf(Integer.parseInt(jcrCount)+1)));
			session.save();
		
	} catch (Exception e) {
//		out.println(e.getMessage());
	}
	return reportTypeId;
}
	
public void EmployementStatusSlingStructure(PrintWriter out, Session session){
	
	try {
		
		Node scorpioDataBase=null;
		Node ReportType=null;
		
		if(session.getRootNode().hasNode("scorpioDataBase")){
			scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
		}else{
			scorpioDataBase=session.getRootNode().addNode("scorpioDataBase");
//			session.save();
		}
		
		
		if(scorpioDataBase.hasNode("Employement Status")){
			ReportType=scorpioDataBase.getNode("Employement Status");
		}else{
			ReportType=scorpioDataBase.addNode("Employement Status");
			ReportType.setProperty("jcr:count", String.valueOf(0));
//			session.save();
		}
		
		HashMap< String, String> map=new HashMap<String, String>();
		map.put("Unfixed", "Unfixed");
		map.put("Fixed", "Fixed");
		map.put("Subs", "Subs");
		map.put("Hold", "Hold");
		map.put("Failed", "Failed");

		 
		
		 for ( Map.Entry<String, String> m : map.entrySet() ){ 
             String arrayIndexData= m.getKey().toString();
             String property = m.getValue().toString();
			
			Node arrayIndexDataNode=null;
			String jcrCount=ReportType.getProperty("jcr:count").getString();
			int jcrCountIntValue = Integer.parseInt(jcrCount)+1;
			String reportTypeId=String.valueOf(jcrCountIntValue);
			
			
			if (ReportType.hasNode(arrayIndexData)) {
				arrayIndexDataNode=ReportType.getNode(arrayIndexData);
			}else{
				arrayIndexDataNode=ReportType.addNode(arrayIndexData);
//				session.save();
			}
			
			arrayIndexDataNode.setProperty("status", property);
			arrayIndexDataNode.setProperty("statusId", reportTypeId);
			
			ReportType.setProperty("jcr:count", String.valueOf(String.valueOf(Integer.parseInt(jcrCount)+1)));
			session.save();
		}
		
		
		
	} catch (Exception e) {
//		out.println(e.getMessage());
	}
}

public String EmployementStatusSlingAdd(PrintWriter out, Session session, String statusPassData){
	String reportTypeId="";
	try {
		
		Node scorpioDataBase=null;
		Node ReportType=null;
		
		if(session.getRootNode().hasNode("scorpioDataBase")){
			scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
		}else{
			scorpioDataBase=session.getRootNode().addNode("scorpioDataBase");
			session.save();
		}
		
		
		if(scorpioDataBase.hasNode("Employement Status")){
			ReportType=scorpioDataBase.getNode("Employement Status");
		}else{
			ReportType=scorpioDataBase.addNode("Employement Status");
			ReportType.setProperty("jcr:count", String.valueOf(0));
			session.save();
		}
		
             String arrayIndexData=statusPassData ;
//             String property = m.getValue().toString();
			
			Node arrayIndexDataNode=null;
			String jcrCount=ReportType.getProperty("jcr:count").getString();
			int jcrCountIntValue = Integer.parseInt(jcrCount)+1;
			 reportTypeId=String.valueOf(jcrCountIntValue);
			
			
			if (ReportType.hasNode(arrayIndexData)) {
				arrayIndexDataNode=ReportType.getNode(arrayIndexData);
			}else{
				arrayIndexDataNode=ReportType.addNode(arrayIndexData);
				session.save();
			}
			
			arrayIndexDataNode.setProperty("status", arrayIndexData);
			arrayIndexDataNode.setProperty("statusId", reportTypeId);
			
			ReportType.setProperty("jcr:count", String.valueOf(String.valueOf(Integer.parseInt(jcrCount)+1)));
			session.save();
		
	} catch (Exception e) {
//		out.println(e.getMessage());
	}
	return reportTypeId;
}


public void RateTypeSlingStructure(PrintWriter out, Session session){
	
	try {
		
		Node scorpioDataBase=null;
		Node ReportType=null;
		
		if(session.getRootNode().hasNode("scorpioDataBase")){
			scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
		}else{
			scorpioDataBase=session.getRootNode().addNode("scorpioDataBase");
			session.save();
		}
		
		
		if(scorpioDataBase.hasNode("Rate Type")){
			ReportType=scorpioDataBase.getNode("Rate Type");
		}else{
			ReportType=scorpioDataBase.addNode("Rate Type");
			ReportType.setProperty("jcr:count", String.valueOf(0));
			session.save();
		}
		
		HashMap< String, String> map=new HashMap<String, String>();
		map.put("WS", "WS");
		map.put("Lumpsum", "Lumpsum");
		
		 for ( Map.Entry<String, String> m : map.entrySet() ){ 
             String arrayIndexData= m.getKey().toString();
             String property = m.getValue().toString();
			
			Node arrayIndexDataNode=null;
			String jcrCount=ReportType.getProperty("jcr:count").getString();
			int jcrCountIntValue = Integer.parseInt(jcrCount)+1;
			String reportTypeId=String.valueOf(jcrCountIntValue);
			
			
			if (ReportType.hasNode(arrayIndexData)) {
				arrayIndexDataNode=ReportType.getNode(arrayIndexData);
			}else{
				arrayIndexDataNode=ReportType.addNode(arrayIndexData);
//				session.save();
			}
			
			arrayIndexDataNode.setProperty("type", property);
			arrayIndexDataNode.setProperty("typeId", reportTypeId);
			
			ReportType.setProperty("jcr:count", String.valueOf(String.valueOf(Integer.parseInt(jcrCount)+1)));
			session.save();
		}
		
		
		
	} catch (Exception e) {
//		out.println(e.getMessage());
	}
}

public String RateTypeSlingAdd(PrintWriter out, Session session, String typePassData){
	String reportTypeId="";
	try {
		
		Node scorpioDataBase=null;
		Node ReportType=null;
		
		if(session.getRootNode().hasNode("scorpioDataBase")){
			scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
		}else{
			scorpioDataBase=session.getRootNode().addNode("scorpioDataBase");
			session.save();
		}
		
		
		if(scorpioDataBase.hasNode("Rate Type")){
			ReportType=scorpioDataBase.getNode("Rate Type");
		}else{
			ReportType=scorpioDataBase.addNode("Rate Type");
			ReportType.setProperty("jcr:count", String.valueOf(0));
			session.save();
		}
		
             String arrayIndexData= typePassData;
			
			Node arrayIndexDataNode=null;
			String jcrCount=ReportType.getProperty("jcr:count").getString();
			int jcrCountIntValue = Integer.parseInt(jcrCount)+1;
			 reportTypeId=String.valueOf(jcrCountIntValue);
			
			
			if (ReportType.hasNode(arrayIndexData)) {
				arrayIndexDataNode=ReportType.getNode(arrayIndexData);
			}else{
				arrayIndexDataNode=ReportType.addNode(arrayIndexData);
				session.save();
			}
			
			arrayIndexDataNode.setProperty("type", arrayIndexData);
			arrayIndexDataNode.setProperty("typeId", reportTypeId);
			
			ReportType.setProperty("jcr:count", String.valueOf(String.valueOf(Integer.parseInt(jcrCount)+1)));
			session.save();
		
	} catch (Exception e) {
//		out.println(e.getMessage());
	}
	return reportTypeId;
}

public void PeriodUnitSlingStructure(PrintWriter out, Session session){
	
	try {
		
		Node scorpioDataBase=null;
		Node ReportType=null;
		
		if(session.getRootNode().hasNode("scorpioDataBase")){
			scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
		}else{
			scorpioDataBase=session.getRootNode().addNode("scorpioDataBase");
//			session.save();
		}
		
		
		if(scorpioDataBase.hasNode("Period unit")){
			ReportType=scorpioDataBase.getNode("Period unit");
		}else{
			ReportType=scorpioDataBase.addNode("Period unit");
			ReportType.setProperty("jcr:count", String.valueOf(0));
//			session.save();
		}
		
		HashMap< String, String> map=new HashMap<String, String>();
		map.put("months", "months");
		map.put("years", "years");
		
		
		 for ( Map.Entry<String, String> m : map.entrySet() ){ 
             String arrayIndexData= m.getKey().toString();
             String property = m.getValue().toString();
			
			Node arrayIndexDataNode=null;
			String jcrCount=ReportType.getProperty("jcr:count").getString();
			int jcrCountIntValue = Integer.parseInt(jcrCount)+1;
			String reportTypeId=String.valueOf(jcrCountIntValue);
			
			
			if (ReportType.hasNode(arrayIndexData)) {
				arrayIndexDataNode=ReportType.getNode(arrayIndexData);
			}else{
				arrayIndexDataNode=ReportType.addNode(arrayIndexData);
//				session.save();
			}
			
			arrayIndexDataNode.setProperty("unit", property);
			arrayIndexDataNode.setProperty("unitId", reportTypeId);
			
			ReportType.setProperty("jcr:count", String.valueOf(String.valueOf(Integer.parseInt(jcrCount)+1)));
			session.save();
		}
		
		
		
	} catch (Exception e) {
//		out.println(e.getMessage());
	}
}

public String PeriodUnitSlingAdd(PrintWriter out, Session session, String unitPassData){
	String reportTypeId="";
	try {
		
		Node scorpioDataBase=null;
		Node ReportType=null;
		
		if(session.getRootNode().hasNode("scorpioDataBase")){
			scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
		}else{
			scorpioDataBase=session.getRootNode().addNode("scorpioDataBase");
//			session.save();
		}
		
		
		if(scorpioDataBase.hasNode("Period unit")){
			ReportType=scorpioDataBase.getNode("Period unit");
		}else{
			ReportType=scorpioDataBase.addNode("Period unit");
			ReportType.setProperty("jcr:count", String.valueOf(0));
			session.save();
		}
		
            String arrayIndexData= unitPassData;
            Node arrayIndexDataNode=null;
			String jcrCount=ReportType.getProperty("jcr:count").getString();
			int jcrCountIntValue = Integer.parseInt(jcrCount)+1;
			 reportTypeId=String.valueOf(jcrCountIntValue);
			
			
			if (ReportType.hasNode(arrayIndexData)) {
				arrayIndexDataNode=ReportType.getNode(arrayIndexData);
			}else{
				arrayIndexDataNode=ReportType.addNode(arrayIndexData);
				session.save();
			}
			
			arrayIndexDataNode.setProperty("unit", arrayIndexData);
			arrayIndexDataNode.setProperty("unitId", reportTypeId);
			
			ReportType.setProperty("jcr:count", String.valueOf(String.valueOf(Integer.parseInt(jcrCount)+1)));
			session.save();
		
	} catch (Exception e) {
//		out.println(e.getMessage());
	}
	return reportTypeId;
}


public void currencyUnitSlingStructure(PrintWriter out, Session session){
	
	try {
		
		Node scorpioDataBase=null;
		Node ReportType=null;
		
		if(session.getRootNode().hasNode("scorpioDataBase")){
			scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
		}else{
			scorpioDataBase=session.getRootNode().addNode("scorpioDataBase");
			session.save();
		}
		
		
		if(scorpioDataBase.hasNode("currency Unit")){
			ReportType=scorpioDataBase.getNode("currency Unit");
		}else{
			ReportType=scorpioDataBase.addNode("currency Unit");
			ReportType.setProperty("jcr:count", String.valueOf(0));
			session.save();
		}
		
		 Set<Currency> z = getAllCurrencies();
		 ArrayList<Currency> f=new ArrayList<>(z);
		
		 for(int i=0;i<f.size();i++){

             String arrayIndexData=f.get(i).toString();
			
			Node arrayIndexDataNode=null;
			String jcrCount=ReportType.getProperty("jcr:count").getString();
			int jcrCountIntValue = Integer.parseInt(jcrCount)+1;
			String reportTypeId=String.valueOf(jcrCountIntValue);
			
			
			if (ReportType.hasNode(arrayIndexData)) {
				arrayIndexDataNode=ReportType.getNode(arrayIndexData);
			}else{
				arrayIndexDataNode=ReportType.addNode(arrayIndexData);
				session.save();
			}
			
			arrayIndexDataNode.setProperty("currency_Unit", arrayIndexData);
			arrayIndexDataNode.setProperty("currency_UnitId", reportTypeId);
			
			ReportType.setProperty("jcr:count", String.valueOf(String.valueOf(Integer.parseInt(jcrCount)+1)));
			session.save();
		}
		
		
		
	} catch (Exception e) {
//		out.println(e.getMessage());
	}
}

public String currencyUnitSlingAdd(PrintWriter out, Session session, String currency_UnitPassData){
	 String reportTypeId="";
	try {
		
		Node scorpioDataBase=null;
		Node ReportType=null;
		
		if(session.getRootNode().hasNode("scorpioDataBase")){
			scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
		}else{
			scorpioDataBase=session.getRootNode().addNode("scorpioDataBase");
			session.save();
		}
		
		
		if(scorpioDataBase.hasNode("currency Unit")){
			ReportType=scorpioDataBase.getNode("currency Unit");
		}else{
			ReportType=scorpioDataBase.addNode("currency Unit");
			ReportType.setProperty("jcr:count", String.valueOf(0));
			session.save();
		}
		
		 /*Set<Currency> z = getAllCurrencies();
		 ArrayList<Currency> f=new ArrayList<>(z);*/
		
//		 for(int i=0;i<f.size();i++){

             String arrayIndexData=currency_UnitPassData;
			
			Node arrayIndexDataNode=null;
			String jcrCount=ReportType.getProperty("jcr:count").getString();
			int jcrCountIntValue = Integer.parseInt(jcrCount)+1;
			 reportTypeId=String.valueOf(jcrCountIntValue);
			
			
			if (ReportType.hasNode(arrayIndexData)) {
				arrayIndexDataNode=ReportType.getNode(arrayIndexData);
			}else{
				arrayIndexDataNode=ReportType.addNode(arrayIndexData);
				session.save();
			}
			
			arrayIndexDataNode.setProperty("currency_Unit", arrayIndexData);
			arrayIndexDataNode.setProperty("currency_UnitId", reportTypeId);
			
			ReportType.setProperty("jcr:count", String.valueOf(String.valueOf(Integer.parseInt(jcrCount)+1)));
			session.save();
//		}
		
		
		
	} catch (Exception e) {
//		out.println(e.getMessage());
	}
	
	return reportTypeId;
}

public  Set<Currency> getAllCurrencies()
{
    Set<Currency> toret = new HashSet<Currency>();
    Locale[] locs = Locale.getAvailableLocales();

    for(Locale loc : locs) {
        try {
            Currency currency = Currency.getInstance( loc );

            if ( currency != null ) {
                toret.add( currency );
            }
        } catch(Exception exc)
        {
            // Locale not found
        }
    }

    return toret;
}

public void InformationSlingStructure(PrintWriter out, Session session){
	
	try {
		
		Node scorpioDataBase=null;
		Node ReportType=null;
		
		if(session.getRootNode().hasNode("scorpioDataBase")){
			scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
		}else{
			scorpioDataBase=session.getRootNode().addNode("scorpioDataBase");
			session.save();
		}
		
		
		if(scorpioDataBase.hasNode("Information")){
			ReportType=scorpioDataBase.getNode("Information");
		}else{
			ReportType=scorpioDataBase.addNode("Information");
			ReportType.setProperty("jcr:count", String.valueOf(0));
			session.save();
		}
		
		HashMap< String, String> map=new HashMap<String, String>();
		map.put("Market", "Market");
		map.put("Private", "Private");
		
		 for ( Map.Entry<String, String> m : map.entrySet() ){ 
             String arrayIndexData= m.getKey().toString();
             String property = m.getValue().toString();
			
			Node arrayIndexDataNode=null;
			String jcrCount=ReportType.getProperty("jcr:count").getString();
			int jcrCountIntValue = Integer.parseInt(jcrCount)+1;
			String reportTypeId=String.valueOf(jcrCountIntValue);
			
			
			if (ReportType.hasNode(arrayIndexData)) {
				arrayIndexDataNode=ReportType.getNode(arrayIndexData);
			}else{
				arrayIndexDataNode=ReportType.addNode(arrayIndexData);
				session.save();
			}
			
			arrayIndexDataNode.setProperty("Information", property);
			arrayIndexDataNode.setProperty("InformationId", reportTypeId);
			
			ReportType.setProperty("jcr:count", String.valueOf(String.valueOf(Integer.parseInt(jcrCount)+1)));
			session.save();
		}
		
		
		
	} catch (Exception e) {
		out.println(e.getMessage());
	}
}

public String InformationSlingAdd(PrintWriter out, Session session, String InformationPassData){
	String reportTypeId="";
	try {
		
		Node scorpioDataBase=null;
		Node ReportType=null;
		
		if(session.getRootNode().hasNode("scorpioDataBase")){
			scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
		}else{
			scorpioDataBase=session.getRootNode().addNode("scorpioDataBase");
			session.save();
		}
		
		
		if(scorpioDataBase.hasNode("Information")){
			ReportType=scorpioDataBase.getNode("Information");
		}else{
			ReportType=scorpioDataBase.addNode("Information");
			ReportType.setProperty("jcr:count", String.valueOf(0));
			session.save();
		}
		
             String arrayIndexData= InformationPassData;
            
			Node arrayIndexDataNode=null;
			String jcrCount=ReportType.getProperty("jcr:count").getString();
			int jcrCountIntValue = Integer.parseInt(jcrCount)+1;
			 reportTypeId=String.valueOf(jcrCountIntValue);
			
			
			if (ReportType.hasNode(arrayIndexData)) {
				arrayIndexDataNode=ReportType.getNode(arrayIndexData);
			}else{
				arrayIndexDataNode=ReportType.addNode(arrayIndexData);
				session.save();
			}
			
			arrayIndexDataNode.setProperty("Information", arrayIndexData);
			arrayIndexDataNode.setProperty("InformationId", reportTypeId);
			
			ReportType.setProperty("jcr:count", String.valueOf(String.valueOf(Integer.parseInt(jcrCount)+1)));
			session.save();
		
	} catch (Exception e) {
		out.println(e.getMessage());
	}
	return reportTypeId;
}

public void scorpioOwner(Session session, PrintWriter out){
	
	try {
		
		Node scorpio=null;
		Node vessel=null;
		
		if(session.getRootNode().hasNode("scorpioDataBase")){
			scorpio = session.getRootNode().getNode("scorpioDataBase");
		}else{
			scorpio = session.getRootNode().addNode("scorpioDataBase");
			session.save();
		}
		
		if(scorpio.hasNode("owners")){
			vessel=scorpio.getNode("owners");
		}else{
			vessel=scorpio.addNode("owners");
			vessel.setProperty("jcr:count", String.valueOf(0));
			session.save();
		}
		
		String sql="SELECT vesselName FROM owner";
		 
		Connection con=null;
		Node vesselSubNode=null;
		
		con=MysqlConnection.getConnectionObj();
		Statement st = con.createStatement();
		 ResultSet rs = st.executeQuery(sql);
		 while (rs.next()){
			 
			String count=vessel.getProperty("jcr:count").getString();
			String forid= String.valueOf(Integer.parseInt(count)+1);
			String vesselName = rs.getString("vesselName");
			String vesselname="";
			
				vesselname=vesselName.replace(" ", "_").replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9]", "_");
			
			if(vessel.hasNode(vesselname)){
				vesselSubNode=vessel.getNode(vesselname);
			}else{
				vesselSubNode=vessel.addNode(vesselname);
				session.save();
			}
			
			 vesselSubNode.setProperty("owners_Id", forid);
			 vesselSubNode.setProperty("ownerName", vesselName);
			
			 
			 vessel.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
			 session.save();
			 
			 out.printf(" %s\n", forid);
		     out.printf(" %s\n", vesselname);
		     
			 
		 }
		
	} catch (Exception e) {
		out.println(e.getMessage());
	}
	
}

public static String scorpioOwnerSlingAdd(Session session, PrintWriter out, String ownerNamePassData){
	String forid="";
	try {
		
		Node scorpio=null;
		Node vessel=null;
		
		if(session.getRootNode().hasNode("scorpioDataBase_16_feb_19")){
			scorpio = session.getRootNode().getNode("scorpioDataBase_16_feb_19");
		}else{
			scorpio = session.getRootNode().addNode("scorpioDataBase_16_feb_19");
//			session.save();
		}
		
		if(scorpio.hasNode("owners")){
			vessel=scorpio.getNode("owners");
		}else{
			vessel=scorpio.addNode("owners");
			vessel.setProperty("jcr:count", String.valueOf(0));
			session.save();
		}
		
		   Node vesselSubNode=null;
		
			String count=vessel.getProperty("jcr:count").getString();
			forid= String.valueOf(Integer.parseInt(count)+1);
			String vesselName = ownerNamePassData;
			String vesselname="";
			
				vesselname=vesselName.replace(" ", "_").replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9]", "_");
			
			if(vessel.hasNode(vesselname)){
				vesselSubNode=vessel.getNode(vesselname);
			}else{
				vesselSubNode=vessel.addNode(vesselname);
				session.save();
			}
			
			 vesselSubNode.setProperty("owners_Id", forid);
			 vesselSubNode.setProperty("ownerName", vesselName.toUpperCase());
			
			 
			 vessel.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
			 session.save();
			 
			 //out.printf(" %s\n", forid);
		    // out.printf(" %s\n", vesselname);
		   
	} catch (Exception e) {
//		out.println(e.getMessage());
	}
	return forid;
	
}


public String scorpioPortSlingAdd(Session session, PrintWriter out, String vesselNamePass){
	String forid="";
	try {
		
		Node scorpio=null;
		Node vessel=null;
		
		if(session.getRootNode().hasNode("scorpioDataBase")){
			scorpio = session.getRootNode().getNode("scorpioDataBase");
		}else{
			scorpio = session.getRootNode().addNode("scorpioDataBase");
			session.save();
		}
		
		if(scorpio.hasNode("Port")){
			vessel=scorpio.getNode("Port");
		}else{
			vessel=scorpio.addNode("Port");
			vessel.setProperty("jcr:count", String.valueOf(0));
			session.save();
		}
		
//		String sql="SELECT vesselName FROM portScorpio";
		 
//		Connection con=null;
		Node vesselSubNode=null;
		String count="";
		
//		con=MysqlConnection.getConnectionObj();
//		Statement st = con.createStatement();
//		 ResultSet rs = st.executeQuery(sql);
//		 while (rs.next()){
			 if(vessel.hasProperty("jcr:count")){
			   count=vessel.getProperty("jcr:count").getString();
			   forid= String.valueOf(Integer.parseInt(count)+1);
			 
			
			String vesselName = vesselNamePass;
			String vesselname="";
			
				vesselname=vesselName.replace(" ", "_").replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9]", "_");
			
				
			if(vessel.hasNode(vesselname)){
				vesselSubNode=vessel.getNode(vesselname);
			}else{
				vesselSubNode=vessel.addNode(vesselname);
				

				 vesselSubNode.setProperty("port_id", forid);
				 vesselSubNode.setProperty("portName", vesselName);
				 vesselSubNode.setProperty("flag", "false");
				 
				 
				 vessel.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
				 session.save();
			}
			
//			 session.save();
			 }
			 
			/* out.printf(" %s\n", forid);
		     out.printf(" %s\n", vesselname);*/
		     
			 
//		 }
		
	} catch (Exception e) {
//		out.println(e.getMessage());
		return "";
	}
	
	return forid;
	
}

public void scorpioPort(Session session, PrintWriter out){
	
	try {
		
		Node scorpio=null;
		Node vessel=null;
		
		if(session.getRootNode().hasNode("scorpioDataBase_16_feb_19")){
			scorpio = session.getRootNode().getNode("scorpioDataBase_16_feb_19");
		}else{
			scorpio = session.getRootNode().addNode("scorpioDataBase_16_feb_19");
//			session.save();
		}
		
		if(scorpio.hasNode("Port")){
			vessel=scorpio.getNode("Port");
		}else{
			vessel=scorpio.addNode("Port");
			vessel.setProperty("jcr:count", String.valueOf(0));
			session.save();
		}
		
		String sql="SELECT Portname,Country,Latitude,Longitude,Area,Region,SubArea FROM portScorpio";
		 
		Connection con=null;
		Node vesselSubNode=null;
		
		con=MysqlConnection.getConnectionObj();
		Statement st = con.createStatement();
		 ResultSet rs = st.executeQuery(sql);
		 while (rs.next()){
			 
			String count=vessel.getProperty("jcr:count").getString();
			String forid= String.valueOf(Integer.parseInt(count)+1);
			String vesselName = rs.getString("Portname");
			String Country = rs.getString("Country");
			String Latitude = rs.getString("Latitude");
			String Longitude = rs.getString("Longitude");
			String Area = rs.getString("Area");
			String Region = rs.getString("Region");
			String SubArea = rs.getString("SubArea");
			
			String vesselname="";
			
				vesselname=vesselName.replace(" ", "_").replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9]", "_");
			
			if(vessel.hasNode(vesselname)){
				vesselSubNode=vessel.getNode(vesselname);
			}else{
				vesselSubNode=vessel.addNode(vesselname);
//				session.save();
			}
			
			 vesselSubNode.setProperty("port_id", forid);
			 vesselSubNode.setProperty("portName", vesselName);
			 vesselSubNode.setProperty("Country", Country);
			 vesselSubNode.setProperty("Latitude", Latitude);
			 vesselSubNode.setProperty("Longitude", Longitude);
			 vesselSubNode.setProperty("Area", Area);
			 vesselSubNode.setProperty("Region", Region);
			 vesselSubNode.setProperty("SubArea", SubArea);
			
			 
			 vessel.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
			 session.save();
			 
			 out.printf(" %s\n", forid);
		     out.printf(" %s\n", vesselname);
		     
			 
		 }
		
	} catch (Exception e) {
		out.println(e.getMessage());
	}
	
}


public void scorpioDataBaseSave_new(Session session, PrintWriter out){
	
	try {
		
		Node scorpio=null;
		Node vessel=null;
		
		if(session.getRootNode().hasNode("scorpioDataBase_16_feb_19")){
			scorpio = session.getRootNode().getNode("scorpioDataBase_16_feb_19");
		}else{
			scorpio = session.getRootNode().addNode("scorpioDataBase_16_feb_19");
//			session.save();
		}
		
		if(scorpio.hasNode("vessel")){
			vessel=scorpio.getNode("vessel");
		}else{
			vessel=scorpio.addNode("vessel");
			vessel.setProperty("jcr:count", String.valueOf(0));
			session.save();
		}
		
		String sql="SELECT vesselName,vesselType,dwt,loa,built,cubics,ice,sternline,owners,status,operator FROM scorpio2";
		 
		Connection con=null;
		Node vesselSubNode=null;
//		out.println("above mysql connectin");
		
		con=MysqlConnection.getConnectionObj();
//		out.println("below mysql connectin");
		Statement st = con.createStatement();
		 ResultSet rs = st.executeQuery(sql);
		 while (rs.next()){
			 
			String count=vessel.getProperty("jcr:count").getString();
			String forid= String.valueOf(Integer.parseInt(count)+1);
			String vesselName = rs.getString("vesselName");
			String vesselname="";
			
			  vesselname=vesselName.replace(" ", "_").replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9]", "_");
			
			String vesselType = rs.getString("vesselType");
			String dwt = rs.getString("dwt");
			String loa = rs.getString("loa");
			String built = rs.getString("built");
			String cubics = rs.getString("cubics");
			String ice = rs.getString("ice");
			String sternline = rs.getString("sternline");
			String owners = rs.getString("owners");
			String status = rs.getString("status");
			String operator = rs.getString("operator");
			
			if(vessel.hasNode(vesselname)){
				vesselSubNode=vessel.getNode(vesselname);
			}else{
				vesselSubNode=vessel.addNode(vesselname);
				session.save();
			}
			
			 String forid1=check_vesselType(session, vesselType,out);
			 String status_id= check_Status(session, status);
			 String foridowners= check_Owners(session, owners,out);
			 String  operatorId=check_Operators(session, operator, out);
			
			 vesselSubNode.setProperty("vessel_Id", forid);
			 vesselSubNode.setProperty("vesselName", vesselName);
			 vesselSubNode.setProperty("vesselType_id", forid1);
			 vesselSubNode.setProperty("dwt", dwt);
			 vesselSubNode.setProperty("loa", loa);
			 vesselSubNode.setProperty("built", built);
			 vesselSubNode.setProperty("cubics", cubics);
			 vesselSubNode.setProperty("ice", ice);
			 vesselSubNode.setProperty("sternline", "");
			 vesselSubNode.setProperty("owners_id", foridowners);
			 vesselSubNode.setProperty("status_id", status_id);
			 vesselSubNode.setProperty("operatorId", operatorId);
			 
			 vessel.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
			 session.save();
			 
			 out.printf(" %s\n", forid);
		     out.printf(" %s\n", vesselname);
		     out.printf(" %s\n", operatorId);
		     
			 
		 }
			 
		
		
	} catch (Exception e) {
		out.println(e.getMessage());
	}
	
}

public String vesselSlingSave(Session session, PrintWriter out, String vesselNamePassData){
	String vesselSubNodeName="";
	try {
		
		if(!GmailMethods.isNullString(vesselNamePassData)){
		Node scorpio=null;
		Node vessel=null;
		
		if(session.getRootNode().hasNode("scorpioDataBase")){
			scorpio = session.getRootNode().getNode("scorpioDataBase");
		}else{
			scorpio = session.getRootNode().addNode("scorpioDataBase");
//			session.save();
		}
		
		if(scorpio.hasNode("vessel")){
			vessel=scorpio.getNode("vessel");
		}else{
			vessel=scorpio.addNode("vessel");
			vessel.setProperty("jcr:count", String.valueOf(0));
			session.save();
		}
		
		    Node vesselSubNode=null;
		
			String count=vessel.getProperty("jcr:count").getString();
			String forid= String.valueOf(Integer.parseInt(count)+1);
			String vesselName =vesselNamePassData;
			String vesselname="";
			
			  vesselname=vesselName.replace(" ", "_").replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9]", "_");
			
			if(vessel.hasNode(vesselname)){
				vesselSubNode=vessel.getNode(vesselname);
				vesselSubNodeName=vesselSubNode.getName().toString();
			}else{
				vesselSubNode=vessel.addNode(vesselname);
				vesselSubNodeName=vesselSubNode.getName().toString();
				session.save();
			}
			
//			 String forid1=check_vesselType(session, vesselType, out);
//			 String status_id= check_Status(session, status);
//			 String foridowners= check_Owners(session, owners,out);
			
			 vesselSubNode.setProperty("vessel_Id", forid);
			 vesselSubNode.setProperty("vesselName", vesselName);
			 vesselSubNode.setProperty("Flag", "False");
			
			 /*vesselSubNode.setProperty("vesselType_id", forid1);
			 vesselSubNode.setProperty("dwt", dwt);
			 vesselSubNode.setProperty("loa", loa);
			 vesselSubNode.setProperty("built", built);
			 vesselSubNode.setProperty("cubics", cubics);
			 vesselSubNode.setProperty("ice", ice);
			 vesselSubNode.setProperty("sternline", sternline);
			 vesselSubNode.setProperty("owners_id", foridowners);*/
			 
			 vessel.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
			 session.save();
			 
			// out.printf(" %s\n", forid);
		    // out.printf(" %s\n", vesselname);
		}
		
	} catch (Exception e) {
//		out.println(e.getMessage());
		return "";
	}
	return vesselSubNodeName;
	
}

public static String check_vesselType(Session session,String mysqlvesselType, PrintWriter out) {


String vessel_id = null;

try {

	Node obj = null;
	String vesselTypequery="";

	Workspace workspace = session.getWorkspace();
	mysqlvesselType=mysqlvesselType.toLowerCase().trim();

	/*String slingqery = "select [vesselType] from [nt:base] where (contains('vesselType','" + mysqlvesselType
			+ "')) and ISDESCENDANTNODE('/scorpioDataBase/vesselType/')";*/
	String slingqery = "select [vesselType] from [nt:base] where LOWER(vesselType) ='" + mysqlvesselType
			+ "'  and ISDESCENDANTNODE('/scorpioDataBase/vesselType/')";
	
	Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
	QueryResult queryResult = query.execute();
	NodeIterator iterator = queryResult.getNodes();
	while (iterator.hasNext()) {

		obj = iterator.nextNode();
		if(obj.hasProperty("vesselType_Id")){
			vessel_id = obj.getProperty("vesselType_Id").getString();
		}
		if(obj.hasProperty("vesselType")){
			vesselTypequery = obj.getProperty("vesselType").getString();
		}
		
	}
	
	/*if( !(mysqlvesselType.equals(vesselTypequery.toLowerCase().trim())) ){
//		vessel_id=scorpioVesselTypeSlingAdd(session, out, mysqlvesselType);
		vessel_id=mysqlvesselType;
		return vessel_id;
	}*/

	
	
} catch (Exception e) {
	return "";
}
return vessel_id;

}

public static String scorpioVesselTypeSlingAdd(Session session, PrintWriter out, String vesselNamePass){
	String forid="";
	try {
		
		Node scorpio=null;
		Node vessel=null;
		
		if(session.getRootNode().hasNode("scorpioDataBase_16_feb_19")){
			scorpio = session.getRootNode().getNode("scorpioDataBase_16_feb_19");
		}else{
			scorpio = session.getRootNode().addNode("scorpioDataBase_16_feb_19");
			session.save();
		}
		
		if(scorpio.hasNode("vesselType")){
			vessel=scorpio.getNode("vesselType");
		}else{
			vessel=scorpio.addNode("vesselType");
			vessel.setProperty("jcr:count", String.valueOf(0));
			session.save();
		}
	
		    Node vesselSubNode=null;
			String count=vessel.getProperty("jcr:count").getString();
			forid= String.valueOf(Integer.parseInt(count)+1);
			String vesselName = vesselNamePass;
			
              if(vessel.hasNode(vesselName)){
				vesselSubNode=vessel.getNode(vesselName);
			}else{
				vesselSubNode=vessel.addNode(vesselName);
				session.save();
			}
			
			 vesselSubNode.setProperty("vesselType_Id", forid);
			 vesselSubNode.setProperty("vesselType", vesselName.toUpperCase());
			
			 
			 vessel.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
			 session.save();
			 
			 out.printf(" %s\n", forid);
		     out.printf(" %s\n", vesselName);
	
	} catch (Exception e) {
//		out.println(e.getMessage());
		return "";
	}
	
	return forid;
	
}

public static String check_Status(Session session,String mysqlStatus) {


String vessel_id = null;

try {

	Node obj = null;

	Workspace workspace = session.getWorkspace();

	String slingqery = "select [Status] from [nt:base] where (contains('Status','" + mysqlStatus
			+ "')) and ISDESCENDANTNODE('/scorpioDataBase_16_feb_19/Status/')";
	Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
	QueryResult queryResult = query.execute();
	NodeIterator iterator = queryResult.getNodes();
	while (iterator.hasNext()) {

		obj = iterator.nextNode();
		if(obj.hasProperty("id")){
			vessel_id = obj.getProperty("id").getString();
		}
		

	}

} catch (Exception e) {
//	return e.getMessage();
	return "";
	
}
return vessel_id;

}

public static String check_Owners(Session session,String mysqlOwners, PrintWriter out) {


String vessel_id = null;

try {

	Node obj = null;
	String ownerName="";

	Workspace workspace = session.getWorkspace();
	mysqlOwners=mysqlOwners.toLowerCase().trim();
	

	/*String slingqery = "select [ownerName] from [nt:base] where (contains('ownerName','" + mysqlOwners
			+ "')) and ISDESCENDANTNODE('/scorpioDataBase/owners/')";*/
	String slingqery = "select [ownerName] from [nt:base] where LOWER(ownerName) ='" + mysqlOwners
			+ "'  and ISDESCENDANTNODE('/scorpioDataBase/owners/')";
	Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
	QueryResult queryResult = query.execute();
	NodeIterator iterator = queryResult.getNodes();
	while (iterator.hasNext()) {

		obj = iterator.nextNode();
		if(obj.hasProperty("owners_Id")){
			vessel_id = obj.getProperty("owners_Id").getString();
		}
		if(obj.hasProperty("ownerName")){
			ownerName = obj.getProperty("ownerName").getString();
		}
		
	}
	
	/*if( !( mysqlOwners.equals(ownerName.toLowerCase().trim()) ) ){
//		vessel_id=scorpioOwnerSlingAdd(session, out, mysqlOwners.toUpperCase());
		vessel_id=mysqlOwners;
		return vessel_id;
	}*/

} catch (Exception e) {
	return "";
}
return vessel_id;

}

public  String check_ReportType(Session session,String reportType, PrintWriter out) {


String vessel_id = "";

try {

	if(reportType!=""){
	Node obj = null;
	String vesselName="";

	Workspace workspace = session.getWorkspace();
	reportType=reportType.toLowerCase().trim();

	/*String slingqery = "select [reportType] from [nt:base] where (contains('reportType','" + reportType
			+ "')) and ISDESCENDANTNODE('/scorpioDataBase/ReportType/')";*/
	String slingqery = "select [reportType] from [nt:base] where LOWER(reportType) ='" + reportType
			+ "'  and ISDESCENDANTNODE('/scorpioDataBase/ReportType/')";
	Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
	QueryResult queryResult = query.execute();
	NodeIterator iterator = queryResult.getNodes();
	while (iterator.hasNext()) {

		obj = iterator.nextNode();
		if(obj.hasProperty("reportTypeId")){
			vessel_id = obj.getProperty("reportTypeId").getString();
		}
		if(obj.hasProperty("reportType")){
			vesselName = obj.getProperty("reportType").getString();
		}
		
	}
	
	/*if( !(reportType.equals(vesselName.toLowerCase().trim())) ){
		vessel_id=reportTypeSlingAdd(out, session, reportType);
		return vessel_id;
	}*/
	
}

} catch (Exception e) {
	return "";
}
return vessel_id;

}

public  String check_Port(Session session,String Port , PrintWriter out) {


//String vessel_id = "";
	String data="";
//JSONObject vesselJson=null;
try {
if(!GmailMethods.isNullString(Port)){
	Node obj = null;
	String portName="";
//	String Area="";

	Workspace workspace = session.getWorkspace();
	Port=Port.toLowerCase().trim();

	/*String slingqery = "select [portName] from [nt:base] where (contains('portName','" + Port
			+ "')) and ISDESCENDANTNODE('/scorpioDataBase/Port/')";*/
	String slingqery = "select [portName] from [nt:base] where LOWER(portName) ='" + Port
			+ "'  and ISDESCENDANTNODE('/scorpioDataBase/Port/')";
	Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
	QueryResult queryResult = query.execute();
	NodeIterator iterator = queryResult.getNodes();
	JSONObject vesselJson=new JSONObject();
	while (iterator.hasNext()) {
		
		obj = iterator.nextNode();
		if(obj.hasProperty("port_id")){
			String vessel_id = obj.getProperty("port_id").getString();
			vesselJson.put("port_id", vessel_id);
		}
		if(obj.hasProperty("portName")){
			 portName = obj.getProperty("portName").getString();
			vesselJson.put("portName", portName);
//			vesselJson.put("flag", "true");
		}
		if(obj.hasProperty("flag")){
			String flag = obj.getProperty("flag").getString();
			vesselJson.put("flag", flag);
		}
		
		if(obj.hasProperty("Area")){
			String Area = obj.getProperty("Area").getString();
			vesselJson.put("Area", Area);
		}
		
		data=vesselJson.toString();
		
	}
	
	if( !(Port.equals(portName.toLowerCase().trim())) ){
		
		if(Port.contains(" ")){
			Port=Port.replaceAll(" ", "_").toLowerCase();
			data=check_PortCheckUnderScore(session, Port, out);
			/*String vessel_id=Port;
			vesselJson.put("port_id", vessel_id);
			vesselJson.put("flag", "false");*/
			/*data=vesselJson.toString();*/
		}
//		String vessel_id=scorpioPortSlingAdd(session, out, Port);
		
//		return data;
	}
	
}

} catch (Exception e) {
//	out.println(e.getMessage());
	return "";
	
}
return data;

}


public  String check_PortCheckUnderScore(Session session,String Port , PrintWriter out) {


	//String vessel_id = "";
		String data="";
	//JSONObject vesselJson=null;
	try {
	if(!GmailMethods.isNullString(Port)){
		Node obj = null;
		String portName="";
//		String Area="";

		Workspace workspace = session.getWorkspace();
		Port=Port.toLowerCase().trim();

		/*String slingqery = "select [portName] from [nt:base] where (contains('portName','" + Port
				+ "')) and ISDESCENDANTNODE('/scorpioDataBase/Port/')";*/
		String slingqery = "select [portName] from [nt:base] where LOWER(portName) ='" + Port
				+ "'  and ISDESCENDANTNODE('/scorpioDataBase/Port/')";
		Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
		QueryResult queryResult = query.execute();
		NodeIterator iterator = queryResult.getNodes();
		JSONObject vesselJson=new JSONObject();
		while (iterator.hasNext()) {
			
			obj = iterator.nextNode();
			if(obj.hasProperty("port_id")){
				String vessel_id = obj.getProperty("port_id").getString();
				vesselJson.put("port_id", vessel_id);
			}
			if(obj.hasProperty("portName")){
				 portName = obj.getProperty("portName").getString();
				vesselJson.put("portName", portName);
//				vesselJson.put("flag", "true");
			}
			if(obj.hasProperty("flag")){
				String flag = obj.getProperty("flag").getString();
				vesselJson.put("flag", flag);
			}
			
			if(obj.hasProperty("Area")){
				String Area = obj.getProperty("Area").getString();
				vesselJson.put("Area", Area);
			}
			
			data=vesselJson.toString();
			
		}
		
		/*if( !(Port.equals(portName.toLowerCase().trim())) ){
//			String vessel_id=scorpioPortSlingAdd(session, out, Port);
			String vessel_id=Port;
			vesselJson.put("port_id", vessel_id);
			vesselJson.put("flag", "false");
			data=vesselJson.toString();
//			return data;
		}*/
		
	}

	} catch (Exception e) {
//		out.println(e.getMessage());
		return "";
		
	}
	return data;

	}

public  String check_CargoType(Session session,String CargoType, PrintWriter out) {


String vessel_id = "";

try {

	if(CargoType!=""){
		
		out.println("queryfirstline: "+CargoType);
	Node obj = null;
	String cargoTypeQuery="";

	Workspace workspace = session.getWorkspace();
	if(CargoType.contains("/")){
		CargoType=CargoType.replace("/", " ");
	}
	CargoType=CargoType.toLowerCase().trim();
	out.println("query_CargoType: "+CargoType);

	String slingqery = "select [cargoType] from [nt:base] where (contains('cargoType','" + CargoType
			+ "')) and ISDESCENDANTNODE('/scorpioDataBase/CargoType/')";
	/*String slingqery = "select [cargoType] from [nt:base] where LOWER(cargoType) ='" + CargoType
			+ "'  and ISDESCENDANTNODE('/scorpioDataBase/CargoType/')";*/
	Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
	QueryResult queryResult = query.execute();
	NodeIterator iterator = queryResult.getNodes();
	while (iterator.hasNext()) {

		obj = iterator.nextNode();
		if(obj.hasProperty("cargoTypeId")){
			vessel_id = obj.getProperty("cargoTypeId").getString();
			out.println("quertid: "+vessel_id);
		}
		if(obj.hasProperty("cargoType")){
			cargoTypeQuery = obj.getProperty("cargoType").getString();
		}
		
		
	}
	
	/*if(CargoType.contains(cargoTypeQuery.toLowerCase())){
		if(obj.hasProperty("cargoTypeId")){
			vessel_id = obj.getProperty("cargoTypeId").getString();
			out.println("cargoContentEquals: "+vessel_id);
		}
	}else{
		vessel_id=CargoTypeSlingAdd(out, session, CargoType.toUpperCase());
	}*/
	
	/*if( ( !CargoType.equalsIgnoreCase(cargoTypeQuery.toLowerCase().trim()) ) ){
//		vessel_id=CargoTypeSlingAdd(out, session, CargoType.toUpperCase());
		 vessel_id=CargoType;
//		return vessel_id;
	}*/
	
}

} catch (Exception e) {
//	e.printStackTrace(out);
	return "";
}
return vessel_id;

}

public  String check_Operators(Session session,String Operators, PrintWriter out) {


String vessel_id = "";

try {

	if(Operators!=""){
	Node obj = null;
	String operatorsName="";

	Workspace workspace = session.getWorkspace();
	Operators=Operators.toLowerCase().trim();

	/*String slingqery = "select [operatorsName] from [nt:base] where (contains('operatorsName','" + Operators
			+ "')) and ISDESCENDANTNODE('/scorpioDataBase/Operators/')";*/
	String slingqery = "select [operatorsName] from [nt:base] where LOWER(operatorsName) ='" + Operators
			+ "'  and ISDESCENDANTNODE('/scorpioDataBase/Operators/')";
	Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
	QueryResult queryResult = query.execute();
	NodeIterator iterator = queryResult.getNodes();
	while (iterator.hasNext()) {

		obj = iterator.nextNode();
		if(obj.hasProperty("operatorsNameId")){
			vessel_id = obj.getProperty("operatorsNameId").getString();
		}
		if(obj.hasProperty("operatorsName")){
			operatorsName = obj.getProperty("operatorsName").getString();
		}
		
		
	}
	
	/*if( !(Operators.equals(operatorsName.toLowerCase().trim())) ){
//		vessel_id=OperatorsSlingAdd(out, session, Operators);
		vessel_id=Operators;
		return vessel_id;
	}*/
}

} catch (Exception e) {
	return "";
}
return vessel_id;

}



public  String check_vesselName(Session session,String vesselName, PrintWriter out) {

//	JSONObject allVesselProperties=null;
	String vesselSubNodeName="";
try {
	
	Node obj = null;
	String vesselNamequery="";

	Workspace workspace = session.getWorkspace();
	vesselName=vesselName.toLowerCase().trim();
	out.println("insidevesselName "+vesselName);

	if(!GmailMethods.isNullString(vesselName)){
	/*String slingqery = "select [vesselName] from [nt:base] where (contains('vesselName','" + vesselName
			+ "')) and ISDESCENDANTNODE('/scorpioDataBase/vessel/')";*/
	/*String slingqery = "select [vesselName] from [nt:base] where vesselName ='" + vesselName
			+ "'  and ISDESCENDANTNODE('/scorpioDataBase/vessel/')";*/
	
	/*String slingqery = "select [vesselName] from [nt:base] where LOWER(vesselName) ='" + vesselName
	+ "'  and ISDESCENDANTNODE('/scorpioDataBase/vessel/')";*/  // my code 
		
		String slingqery = "select [vesselName] from [nt:base] where LOWER(vesselName) ='" + vesselName
				+ "'  and ISDESCENDANTNODE('/scorpioDataBase/vessel/')";
	
	Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
//	query.setLimit(10);
	QueryResult queryResult = query.execute();
	NodeIterator iterator = queryResult.getNodes();
	out.println("count "+iterator.getSize());
	while (iterator.hasNext()) {

		obj = iterator.nextNode();
//		out.println("obj "+obj);
		if(obj.hasProperty("vessel_Id")){
			String vessel_id = obj.getProperty("vessel_Id").getString();
			out.println("vessel_id "+vessel_id);
		}
		
		if(obj.hasProperty("vesselName")){
			 vesselNamequery = obj.getProperty("vesselName").getString();
			 out.println("vesselNamequery: "+vesselNamequery);
		}
		
		/*String built = obj.getProperty("built").getString();
		String cubics = obj.getProperty("cubics").getString();
		String dwt = obj.getProperty("dwt").getString();
		String ice = obj.getProperty("ice").getString();
		String loa = obj.getProperty("loa").getString();
		String owners_id = obj.getProperty("owners_id").getString();
		String sternline = obj.getProperty("sternline").getString();
		String vesselType_id = obj.getProperty("vesselType_id").getString(); //
		String status_id = obj.getProperty("status_id").getString();
		
		 allVesselProperties=new JSONObject();
		allVesselProperties.put("vessel_Id", vessel_id);
		allVesselProperties.put("built", built);
		allVesselProperties.put("cubics", cubics);
		allVesselProperties.put("dwt", dwt);
		allVesselProperties.put("ice", ice);
		allVesselProperties.put("loa", loa);
		allVesselProperties.put("owners_id", owners_id);
		allVesselProperties.put("sternline", sternline);
		allVesselProperties.put("vesselType_id", vesselType_id);
		allVesselProperties.put("status_id", status_id);*/

	}
	
	if(vesselName.equals(vesselNamequery.toLowerCase().trim())){
		JSONObject allVesselProperties=new JSONObject();
		if(obj.hasProperty("vessel_Id")){
			String vessel_id = obj.getProperty("vessel_Id").getString();
			out.println("vessel_id "+vessel_id);
			allVesselProperties.put("vessel_Id", vessel_id);
			
		}
		if(obj.hasProperty("vesselName")){
			vesselNamequery = obj.getProperty("vesselName").getString();
			allVesselProperties.put("vesselNamequery", obj.getName());
//			allVesselProperties.put("Flag", "True");
			 out.println("vesselNamequery: "+vesselNamequery);
		}
		
		if(obj.hasProperty("Flag")){
			String Flag = obj.getProperty("Flag").getString();
			allVesselProperties.put("Flag", Flag);
			 out.println("Flag: "+Flag);
		}
		
		if(obj.hasProperty("built")){
			String built = obj.getProperty("built").getString();
			allVesselProperties.put("built", built);
		}
		if(obj.hasProperty("cubics")){
			String cubics = obj.getProperty("cubics").getString();
			allVesselProperties.put("cubics", cubics);
		}
		if(obj.hasProperty("dwt")){
			String dwt = obj.getProperty("dwt").getString();
			allVesselProperties.put("dwt", dwt);
		}
		if(obj.hasProperty("ice")){
			String ice = obj.getProperty("ice").getString();
			allVesselProperties.put("ice", ice);
		}
		if(obj.hasProperty("loa")){
			String loa = obj.getProperty("loa").getString();
			allVesselProperties.put("loa", loa);
		}
		if(obj.hasProperty("owners_id")){
			String owners_id = obj.getProperty("owners_id").getString();
			allVesselProperties.put("owners_id", owners_id);
		}
		if(obj.hasProperty("sternline")){
			String sternline = obj.getProperty("sternline").getString();
			allVesselProperties.put("sternline", sternline);
		}
		if(obj.hasProperty("vesselType_id")){
			String vesselType_id = obj.getProperty("vesselType_id").getString();
			allVesselProperties.put("vesselType_id", vesselType_id);
		}
		if(obj.hasProperty("status_id")){
			String status_id = obj.getProperty("status_id").getString();
			allVesselProperties.put("status_id", status_id);
		}
		if(obj.hasProperty("operatorId")){
			String operatorId = obj.getProperty("operatorId").getString();
			allVesselProperties.put("operatorId", operatorId);
		}
		
		vesselSubNodeName=allVesselProperties.toString();
		//out.println("vesselSubNodeName :: "+vesselSubNodeName);
	}
	//out.println("allVesselProperties: "+allVesselProperties);
	else{
		if(vesselName.contains(" ")){
			vesselName=vesselName.replaceAll(" ", "_").toLowerCase();
			vesselSubNodeName=check_vesselNameCheckUnderScore(session, vesselName, out);
		}
		
				
//		vesselSubNodeName=vesselSlingSave(session, out, vesselName);
		/*vesselSubNodeName=vesselName;
		out.println("other node: "+vesselSubNodeName);*/
		/*JSONObject allVesselProperties=new JSONObject();
		allVesselProperties.put("vesselNameNew", vesselSubNodeName);
		allVesselProperties.put("Flag", "False");*/
//		vesselSubNodeName=allVesselProperties.toString();
//		return vesselSubNodeName;
	}
} // vesselname blank check
} catch (Exception e) {
//	e.printStackTrace(out);
//	out.println(e.getMessage());
	return "";	
}
return vesselSubNodeName;

}

public  String check_vesselNameCheckUnderScore(Session session,String vesselName, PrintWriter out) {

//	JSONObject allVesselProperties=null;
	String vesselSubNodeName="";
try {
	
	Node obj = null;
	String vesselNamequery="";

	Workspace workspace = session.getWorkspace();
	vesselName=vesselName.toLowerCase().trim();
	out.println("insidevesselName "+vesselName);

	if(!GmailMethods.isNullString(vesselName)){
	/*String slingqery = "select [vesselName] from [nt:base] where (contains('vesselName','" + vesselName
			+ "')) and ISDESCENDANTNODE('/scorpioDataBase/vessel/')";*/
	/*String slingqery = "select [vesselName] from [nt:base] where vesselName ='" + vesselName
			+ "'  and ISDESCENDANTNODE('/scorpioDataBase/vessel/')";*/
	
	/*String slingqery = "select [vesselName] from [nt:base] where LOWER(vesselName) ='" + vesselName
	+ "'  and ISDESCENDANTNODE('/scorpioDataBase/vessel/')";*/  // my code 
		
		String slingqery = "select [vesselName] from [nt:base] where LOWER(vesselName) ='" + vesselName
				+ "'  and ISDESCENDANTNODE('/scorpioDataBase/vessel/')";
	
	Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
//	query.setLimit(10);
	QueryResult queryResult = query.execute();
	NodeIterator iterator = queryResult.getNodes();
	out.println("count "+iterator.getSize());
	while (iterator.hasNext()) {

		obj = iterator.nextNode();
//		out.println("obj "+obj);
		if(obj.hasProperty("vessel_Id")){
			String vessel_id = obj.getProperty("vessel_Id").getString();
			out.println("vessel_id "+vessel_id);
		}
		
		if(obj.hasProperty("vesselName")){
			 vesselNamequery = obj.getProperty("vesselName").getString();
			 out.println("vesselNamequery: "+vesselNamequery);
		}
		
		/*String built = obj.getProperty("built").getString();
		String cubics = obj.getProperty("cubics").getString();
		String dwt = obj.getProperty("dwt").getString();
		String ice = obj.getProperty("ice").getString();
		String loa = obj.getProperty("loa").getString();
		String owners_id = obj.getProperty("owners_id").getString();
		String sternline = obj.getProperty("sternline").getString();
		String vesselType_id = obj.getProperty("vesselType_id").getString(); //
		String status_id = obj.getProperty("status_id").getString();
		
		 allVesselProperties=new JSONObject();
		allVesselProperties.put("vessel_Id", vessel_id);
		allVesselProperties.put("built", built);
		allVesselProperties.put("cubics", cubics);
		allVesselProperties.put("dwt", dwt);
		allVesselProperties.put("ice", ice);
		allVesselProperties.put("loa", loa);
		allVesselProperties.put("owners_id", owners_id);
		allVesselProperties.put("sternline", sternline);
		allVesselProperties.put("vesselType_id", vesselType_id);
		allVesselProperties.put("status_id", status_id);*/

	}
	
	if(vesselName.equals(vesselNamequery.toLowerCase().trim())){
		JSONObject allVesselProperties=new JSONObject();
		if(obj.hasProperty("vessel_Id")){
			String vessel_id = obj.getProperty("vessel_Id").getString();
			out.println("vessel_id "+vessel_id);
			allVesselProperties.put("vessel_Id", vessel_id);
			
		}
		if(obj.hasProperty("vesselName")){
			vesselNamequery = obj.getProperty("vesselName").getString();
			allVesselProperties.put("vesselNamequery", obj.getName());
//			allVesselProperties.put("Flag", "True");
			 out.println("vesselNamequery: "+vesselNamequery);
		}
		
		if(obj.hasProperty("Flag")){
			String Flag = obj.getProperty("Flag").getString();
			allVesselProperties.put("Flag", Flag);
			 out.println("Flag: "+Flag);
		}
		
		if(obj.hasProperty("built")){
			String built = obj.getProperty("built").getString();
			allVesselProperties.put("built", built);
		}
		if(obj.hasProperty("cubics")){
			String cubics = obj.getProperty("cubics").getString();
			allVesselProperties.put("cubics", cubics);
		}
		if(obj.hasProperty("dwt")){
			String dwt = obj.getProperty("dwt").getString();
			allVesselProperties.put("dwt", dwt);
		}
		if(obj.hasProperty("ice")){
			String ice = obj.getProperty("ice").getString();
			allVesselProperties.put("ice", ice);
		}
		if(obj.hasProperty("loa")){
			String loa = obj.getProperty("loa").getString();
			allVesselProperties.put("loa", loa);
		}
		if(obj.hasProperty("owners_id")){
			String owners_id = obj.getProperty("owners_id").getString();
			allVesselProperties.put("owners_id", owners_id);
		}
		if(obj.hasProperty("sternline")){
			String sternline = obj.getProperty("sternline").getString();
			allVesselProperties.put("sternline", sternline);
		}
		if(obj.hasProperty("vesselType_id")){
			String vesselType_id = obj.getProperty("vesselType_id").getString();
			allVesselProperties.put("vesselType_id", vesselType_id);
		}
		if(obj.hasProperty("status_id")){
			String status_id = obj.getProperty("status_id").getString();
			allVesselProperties.put("status_id", status_id);
		}
		if(obj.hasProperty("operatorId")){
			String operatorId = obj.getProperty("operatorId").getString();
			allVesselProperties.put("operatorId", operatorId);
		}
		
		vesselSubNodeName=allVesselProperties.toString();
		//out.println("vesselSubNodeName :: "+vesselSubNodeName);
	}
	//out.println("allVesselProperties: "+allVesselProperties);
//	else{
//		vesselSubNodeName=vesselSlingSave(session, out, vesselName);
		/*vesselSubNodeName=vesselName;
		out.println("other node: "+vesselSubNodeName);*/
		/*JSONObject allVesselProperties=new JSONObject();
		allVesselProperties.put("vesselNameNew", vesselSubNodeName);
		allVesselProperties.put("Flag", "False");*/
//		vesselSubNodeName=allVesselProperties.toString();
//		return vesselSubNodeName;
//	}
} // vesselname blank check
} catch (Exception e) {
//	e.printStackTrace(out);
//	out.println(e.getMessage());
	return "";	
}
return vesselSubNodeName;

}

public  String check_EmployementStatus(Session session,String status, PrintWriter out) {


String vessel_id = "";

try {

	if(status!=""){
	Node obj = null;
	String statusQuery="";

	Workspace workspace = session.getWorkspace();
	status=status.toLowerCase().trim();

	/*String slingqery = "select [status] from [nt:base] where (contains('status','" + status
			+ "')) and ISDESCENDANTNODE('/scorpioDataBase/Employement Status/')";*/
	String slingqery = "select [status] from [nt:base] where LOWER(status) ='" + status
			+ "'  and ISDESCENDANTNODE('/scorpioDataBase/Employement Status/')";
	Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
	QueryResult queryResult = query.execute();
	NodeIterator iterator = queryResult.getNodes();
	while (iterator.hasNext()) {

		obj = iterator.nextNode();
		if(obj.hasProperty("statusId")){
			vessel_id = obj.getProperty("statusId").getString();
		}
		if(obj.hasProperty("status")){
			statusQuery = obj.getProperty("status").getString();
		}
		
		
	}
	
	if( !(status.equals(statusQuery.toLowerCase().trim())) ){
//		vessel_id=EmployementStatusSlingAdd(out, session, status);
		vessel_id=status;
		return vessel_id;
	}
	
}

} catch (Exception e) {
	return "";
}
return vessel_id;

}

public  String check_Source(Session session,String Source, PrintWriter out) {


String vessel_id = "";

try {

	if(Source!=""){
	Node obj = null;
	String brokerName="";

	Workspace workspace = session.getWorkspace();
	Source=Source.toLowerCase().trim();

	/*String slingqery = "select [brokerName] from [nt:base] where (contains('brokerName','" + Source
			+ "')) and ISDESCENDANTNODE('/scorpioDataBase/Source/')";*/
	String slingqery = "select [brokerName] from [nt:base] where LOWER(brokerName) ='" + Source
			+ "'  and ISDESCENDANTNODE('/scorpioDataBase/Source/')";
	Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
	QueryResult queryResult = query.execute();
	NodeIterator iterator = queryResult.getNodes();
	while (iterator.hasNext()) {

		obj = iterator.nextNode();
		if(obj.hasProperty("brokerNameId")){
			vessel_id = obj.getProperty("brokerNameId").getString();
		}
		if(obj.hasProperty("brokerName")){
			brokerName = obj.getProperty("brokerName").getString();
		}
		
		
	}
	
	if( !(Source.equals(brokerName.toLowerCase().trim())) ){
		vessel_id=SourceSlingAdd(out, session, Source);
		return vessel_id;
	}
}

} catch (Exception e) {
	return "";
}
return vessel_id;

}

public  String check_Information(Session session,String Information, PrintWriter out) {


String vessel_id = "";

try {

	if(Information!=""){
	Node obj = null;
	String Informationquery="";

	Workspace workspace = session.getWorkspace();
	Information=Information.toLowerCase().trim();

	/*String slingqery = "select [Information] from [nt:base] where (contains('Information','" + Information
			+ "')) and ISDESCENDANTNODE('/scorpioDataBase/Information/')";*/
	String slingqery = "select [Information] from [nt:base] where LOWER(Information) ='" + Information
			+ "'  and ISDESCENDANTNODE('/scorpioDataBase/Information/')";
	Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
	QueryResult queryResult = query.execute();
	NodeIterator iterator = queryResult.getNodes();
	while (iterator.hasNext()) {

		obj = iterator.nextNode();
		if(obj.hasProperty("InformationId")){
			vessel_id = obj.getProperty("InformationId").getString();
		}
		if(obj.hasProperty("Information")){
			Informationquery = obj.getProperty("Information").getString();
		}
		
		
	}
	
	if( !(Information.equals(Informationquery.toLowerCase().trim())) ){
		vessel_id=InformationSlingAdd(out, session, Information);
		return vessel_id;
	}
}

} catch (Exception e) {
	return "";
}
return vessel_id;

}

public  String check_Charterer(Session session,String Charterer, PrintWriter out) {


String vessel_id = "";

try {

	if(Charterer!=""){
	Node obj = null;
	String chartererName="";

	Workspace workspace = session.getWorkspace();
	Charterer=Charterer.toLowerCase().trim();

	/*String slingqery = "select [chartererName] from [nt:base] where (contains('chartererName','" + Charterer
			+ "')) and ISDESCENDANTNODE('/scorpioDataBase/Charterer/')";*/
	String slingqery = "select [chartererName] from [nt:base] where LOWER(chartererName) ='" + Charterer
			+ "'  and ISDESCENDANTNODE('/scorpioDataBase/Charterer/')";
	Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
	QueryResult queryResult = query.execute();
	NodeIterator iterator = queryResult.getNodes();
	while (iterator.hasNext()) {

		obj = iterator.nextNode();
		if(obj.hasProperty("chartererNameId")){
			vessel_id = obj.getProperty("chartererNameId").getString();
		}
		if(obj.hasProperty("chartererName")){
			chartererName = obj.getProperty("chartererName").getString();
		}
		
		
	}
	
	if( !(Charterer.equals(chartererName.toLowerCase().trim())) ){
//		vessel_id=ChartererSlingAdd(out, session, Charterer);
		vessel_id=Charterer;
		return vessel_id;
	}
}

} catch (Exception e) {
	return "";
}
return vessel_id;

}

public  String check_CargoGrade(Session session,String CargoGrade, PrintWriter out) {


String vessel_id = "";

try {

	if(CargoGrade!=""){
	Node obj = null;
	String cargoGrade="";

	Workspace workspace = session.getWorkspace();
	CargoGrade=CargoGrade.toLowerCase().trim();

	/*String slingqery = "select [cargoGrade] from [nt:base] where (contains('cargoGrade','" + CargoGrade
			+ "')) and ISDESCENDANTNODE('/scorpioDataBase/Cargo Grade/')";*/
	String slingqery = "select [cargoGrade] from [nt:base] where LOWER(cargoGrade) ='" + CargoGrade
			+ "'  and ISDESCENDANTNODE('/scorpioDataBase/Cargo Grade/')";
	Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
	QueryResult queryResult = query.execute();
	NodeIterator iterator = queryResult.getNodes();
	while (iterator.hasNext()) {

		obj = iterator.nextNode();
		if(obj.hasProperty("cargoGradeId")){
			vessel_id = obj.getProperty("cargoGradeId").getString();
		}
		if(obj.hasProperty("cargoGrade")){
			cargoGrade = obj.getProperty("cargoGrade").getString();
		}
		
		
	}
	
	if( !(CargoGrade.equals(cargoGrade.toLowerCase().trim())) ){
//		vessel_id=CargoGradeSlingAdd(out, session, CargoGrade);
		vessel_id=CargoGrade;
		return vessel_id;
	}
}

} catch (Exception e) {
	return "";
}
return vessel_id;

}

public  String check_RateType(Session session,String RateType, PrintWriter out) {


String vessel_id = "";

try {

	if(RateType!=""){
	Node obj = null;
	String type="";

	Workspace workspace = session.getWorkspace();
	RateType=RateType.toLowerCase().trim();

	/*String slingqery = "select [type] from [nt:base] where (contains('type','" + RateType
			+ "')) and ISDESCENDANTNODE('/scorpioDataBase/Rate Type/')";*/
	String slingqery = "select [type] from [nt:base] where LOWER(type) ='" + RateType
			+ "'  and ISDESCENDANTNODE('/scorpioDataBase/Rate Type/')";
	Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
	QueryResult queryResult = query.execute();
	NodeIterator iterator = queryResult.getNodes();
	while (iterator.hasNext()) {

		obj = iterator.nextNode();
		if(obj.hasProperty("typeId")){
			vessel_id = obj.getProperty("typeId").getString();
		}
		if(obj.hasProperty("type")){
			type = obj.getProperty("type").getString();
		}
		
		
	}
	
	/*if( !(RateType.equals(type.toLowerCase().trim())) ){
//		vessel_id=RateTypeSlingAdd(out, session, RateType);
		vessel_id=RateType;
		return vessel_id;
	}*/
}

} catch (Exception e) {
	return "";
}
return vessel_id;

}

public  String check_DeliveryPlace(Session session,String DeliveryPlace, PrintWriter out) {


String vessel_id = "";

try {

	if(DeliveryPlace!=""){
	Node obj = null;
	String placeName="";

	Workspace workspace = session.getWorkspace();
	DeliveryPlace=DeliveryPlace.toLowerCase().trim();

	/*String slingqery = "select [placeName] from [nt:base] where (contains('placeName','" + DeliveryPlace
			+ "')) and ISDESCENDANTNODE('/scorpioDataBase/Delivery Place/')";*/
	String slingqery = "select [placeName] from [nt:base] where LOWER(placeName) ='" + DeliveryPlace
			+ "'  and ISDESCENDANTNODE('/scorpioDataBase/Delivery Place/')";
	Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
	QueryResult queryResult = query.execute();
	NodeIterator iterator = queryResult.getNodes();
	while (iterator.hasNext()) {

		obj = iterator.nextNode();
		if(obj.hasProperty("placeNameId")){
			vessel_id = obj.getProperty("placeNameId").getString();
		}
		if(obj.hasProperty("placeName")){
			placeName = obj.getProperty("placeName").getString();
		}
		
		
	}
	
	if( !(DeliveryPlace.equals(placeName.toLowerCase().trim())) ){
//		vessel_id=DeliveryPlaceSlingAdd(out, session, DeliveryPlace);
		vessel_id=DeliveryPlace;
		return vessel_id;
	}
}

} catch (Exception e) {
	return "";
}
return vessel_id;

}

public  String check_Periodunit(Session session,String Periodunit, PrintWriter out) {


String vessel_id = "";

try {

	if(Periodunit!=""){
	Node obj = null;
	String unit="";

	Workspace workspace = session.getWorkspace();
	Periodunit=Periodunit.toLowerCase().trim();

	/*String slingqery = "select [unit] from [nt:base] where (contains('unit','" + Periodunit
			+ "')) and ISDESCENDANTNODE('/scorpioDataBase/Period unit/')";*/
	String slingqery = "select [unit] from [nt:base] where LOWER(unit) ='" + Periodunit
			+ "'  and ISDESCENDANTNODE('/scorpioDataBase/Period unit/')";
	Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
	QueryResult queryResult = query.execute();
	NodeIterator iterator = queryResult.getNodes();
	while (iterator.hasNext()) {

		obj = iterator.nextNode();
		if(obj.hasProperty("unitId")){
			vessel_id = obj.getProperty("unitId").getString();
		}
		if(obj.hasProperty("unit")){
			unit = obj.getProperty("unit").getString();
		}
		
		
	}
	
	if( !(Periodunit.equals(unit.toLowerCase().trim())) ){
//		vessel_id=PeriodUnitSlingAdd(out, session, Periodunit);
		vessel_id=Periodunit;
		return vessel_id;
	}
}

} catch (Exception e) {
	return "";
}
return vessel_id;

}

public  String check_currencyUnit(Session session,String currencyUnit, PrintWriter out) {


String vessel_id = "";

try {

	if(currencyUnit!=""){
	Node obj = null;
	String currency_Unit_query="";

	Workspace workspace = session.getWorkspace();
	currencyUnit=currencyUnit.toLowerCase().trim();

	/*String slingqery = "select [currency_Unit] from [nt:base] where (contains('currency_Unit','" + currencyUnit
			+ "')) and ISDESCENDANTNODE('/scorpioDataBase/currency Unit/')";*/
	String slingqery = "select [currency_Unit] from [nt:base] where LOWER(currency_Unit) ='" + currencyUnit
			+ "'  and ISDESCENDANTNODE('/scorpioDataBase/currency Unit/')";
	Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
	QueryResult queryResult = query.execute();
	NodeIterator iterator = queryResult.getNodes();
	while (iterator.hasNext()) {

		obj = iterator.nextNode();
		if(obj.hasProperty("currency_UnitId")){
			vessel_id = obj.getProperty("currency_UnitId").getString();
		}
		if(obj.hasProperty("currency_Unit")){
			currency_Unit_query = obj.getProperty("currency_Unit").getString();
		}
		
		
	}
     
	if( !(currencyUnit.equals(currency_Unit_query.toLowerCase().trim())) ){
//		vessel_id=currencyUnitSlingAdd(out, session, currencyUnit);
		vessel_id=currencyUnit;
		
		return vessel_id;
	}
	
}

} catch (Exception e) {
	return "";
}
return vessel_id;

}



public void parseAllReportUsingApi(PrintWriter out, Session session){
	
	try {
		
		String allReport="{\n" +
				"\"allReport\":[\n" +
				"\n" +
				"{\n" +
				"\n" +
				"\"ReportType\":\"Spot\",\n" +
				"\"RepositionRegion\":\"\",\n" +
				"\"CargoType\":\"Clean/CPP\",\n" +
				"\"VesselName\":\"British Cirrus\",\n" +
				"\"VesselType\":\"\",\n" +
				"\"Built\":\"\",\n" +
				"\"DWT\":\"\",\n" +
				"\"Cubics\":\"\",\n" +
				"\"LOA\":\"\",\n" +
				"\"ICE\":\"\",\n" +
				"\"SternLine\":\"\",\n" +
				"\"Owners\":\"\",\n" +
				"\"Operators\":\"\",\n" +
				"\"EmploymentStatus\":\"\",\n" +
				"\"OpenPort\":\"\",\n" +
				"\"OpenDate\":\"\",\n" +
				"\"ETABasis\":\"\",\n" +
				"\"Comment\":\"Subs\",\n" +
				"\"Source\":\"Braemar\",\n" +
				"\"ReportTimestamp\":\"Wed Nov 1 15:15:02 UTC 2018\",\n" +
				"\"Information\":\"Market\",\n" +
				"\"FixtureType\":\"Spot\",\n" +
				"\"Charterer\":\"Exxon\",\n" +
				"\"Status\":\"Subs\",\n" +
				"\"CargoGrade\":\"ULSD\",\n" +
				"\"CargoQty\":\"30\",\n" +
				"\"LCStart\":\"05-08-2018\",\n" +
				"\"LCEnd\":\"07-08-2018\",\n" +
				"\"LoadPort\":\"ARA\",\n" +
				"\"DiscPort\":\"UKC\",\n" +
				"\"RateType\":\"WS\",\n" +
				"\"Rate\":\"125\",\n" +
				"\"ReportDate\":\"1-11-2018 22:30\",\n" +
				"\"Spot_TC\":\"\",\n" +
				"\"Date\":\"\",\n" +
				"\"CPP_DPP\":\"\",\n" +
				"\"Delivery\":\"\",\n" +
				"\"DeliveryPlace\":\"\",\n" +
				"\"Period\":\"\",\n" +
				"\"Periodunit\":\"\",\n" +
				"\"Redelivery\":\"\",\n" +
				"\"TCRate\":\"\",\n" +
				"\"CurrencyUnit\":\"\",\n" +
				"\"Month_Year\":\"\",\n" +
				"\"1yr\":\"\",\n" +
				"\"2yr\":\"\",\n" +
				"\"3yr\":\"\",\n" +
				"\"5yr\":\"\"\n" +
				"\n" +
				"}\n" +
				"\n" +
				"]\n" +
				"}";
		
		JSONObject allReportJsonObject=new JSONObject(allReport);
		JSONArray allReportJsonArray=null;
		if(allReportJsonObject.has("allReport")){
			allReportJsonArray=allReportJsonObject.getJSONArray("allReport");
			
			for(int i=0;i<allReportJsonArray.length();i++){
				JSONObject allReportJsonArrayInsideJSonobject=allReportJsonArray.getJSONObject(i);
				String check_ReportType_id="";
				if(allReportJsonArrayInsideJSonobject.has("ReportType")){
					String ReportTypeString=allReportJsonArrayInsideJSonobject.getString("ReportType");
					check_ReportType_id=check_ReportType(session, ReportTypeString, out);
				}
				String check_Port_repositionRegion_id="";
				if(allReportJsonArrayInsideJSonobject.has("RepositionRegion")){
					String RepositionRegionString=allReportJsonArrayInsideJSonobject.getString("RepositionRegion");
					check_Port_repositionRegion_id=check_Port(session, RepositionRegionString, out);
				}
				String check_CargoType_cargotype_id="";
				if(allReportJsonArrayInsideJSonobject.has("CargoType")){
					String CargoTypeString=allReportJsonArrayInsideJSonobject.getString("CargoType");
					check_CargoType_cargotype_id=check_CargoType(session, CargoTypeString,out);
				}
				String check_vesselNameProperties="";
				String vessel_Id="";
				String built="";
				String cubics="";
				String dwt="";
				String ice="";
				String loa="";
				String owners_id="";
				String sternline="";
				String vesselType_id="";
				String status_id="";
				Node scorpioDataBase=null;
				Node vessel=null;
				if(session.getRootNode().hasNode("scorpioDataBase")){
					scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
				}
				if(scorpioDataBase.hasNode("vessel")){
					vessel=scorpioDataBase.getNode("vessel");
				}
				
				if(allReportJsonArrayInsideJSonobject.has("VesselName")){
					Node vesselSubNodeName=null;
					String VesselNameString=allReportJsonArrayInsideJSonobject.getString("VesselName");
					out.println("VesselNameString :"+VesselNameString);
					check_vesselNameProperties=check_vesselName(session, VesselNameString, out);
					out.println("check_vesselNameProperties :"+check_vesselNameProperties);
					JSONObject check_vesselNamePropertiesJsonobject=new JSONObject(check_vesselNameProperties);
					out.println("check_vesselNamePropertiesJsonobject :"+check_vesselNamePropertiesJsonobject);
					if(check_vesselNamePropertiesJsonobject.has("vessel_Id")){
						vessel_Id=check_vesselNamePropertiesJsonobject.getString("vessel_Id");
					}else{
						if(vessel.hasNode(check_vesselNameProperties)){
							vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
							vessel_Id=vesselSubNodeName.getProperty("vessel_Id").getString();
						}
					}
					
					if(check_vesselNamePropertiesJsonobject.has("built")){
						built=check_vesselNamePropertiesJsonobject.getString("built");
					}else{
						if(allReportJsonArrayInsideJSonobject.has("Built")){
							built=allReportJsonArrayInsideJSonobject.getString("Built");
							
							if(vessel.hasNode(check_vesselNameProperties)){
								vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
								vesselSubNodeName.setProperty("built", built);
								session.save();
							}
							
						}
						
					}
					
					if(check_vesselNamePropertiesJsonobject.has("cubics")){
						cubics=check_vesselNamePropertiesJsonobject.getString("cubics");
					}else{
						if(allReportJsonArrayInsideJSonobject.has("Cubics")){
							cubics=allReportJsonArrayInsideJSonobject.getString("Cubics");
							
							if(vessel.hasNode(check_vesselNameProperties)){
								vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
								vesselSubNodeName.setProperty("cubics", cubics);
								session.save();
							}
							
						}
					}
					
					if(check_vesselNamePropertiesJsonobject.has("dwt")){
						dwt=check_vesselNamePropertiesJsonobject.getString("dwt");
					}else{
						if(allReportJsonArrayInsideJSonobject.has("DWT")){
							dwt=allReportJsonArrayInsideJSonobject.getString("DWT");
							
							if(vessel.hasNode(check_vesselNameProperties)){
								vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
								vesselSubNodeName.setProperty("dwt", dwt);
								session.save();
							}
							
						}
					}
					
					if(check_vesselNamePropertiesJsonobject.has("ice")){
						ice=check_vesselNamePropertiesJsonobject.getString("ice");
					}else{
						if(allReportJsonArrayInsideJSonobject.has("ICE")){
							ice=allReportJsonArrayInsideJSonobject.getString("ICE");
							
							if(vessel.hasNode(check_vesselNameProperties)){
								vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
								vesselSubNodeName.setProperty("ice", ice);
								session.save();
							}
							
						}
					}
					
					if(check_vesselNamePropertiesJsonobject.has("loa")){
						loa=check_vesselNamePropertiesJsonobject.getString("loa");
						
					}else{
						if(allReportJsonArrayInsideJSonobject.has("LOA")){
							loa=allReportJsonArrayInsideJSonobject.getString("LOA");
							
							if(vessel.hasNode(check_vesselNameProperties)){
								vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
								vesselSubNodeName.setProperty("loa", loa);
								session.save();
							}
							
						}
					}
					
					if(check_vesselNamePropertiesJsonobject.has("owners_id")){
						owners_id=check_vesselNamePropertiesJsonobject.getString("owners_id");
					}else{
						if(allReportJsonArrayInsideJSonobject.has("Owners")){
							String owners_Name=allReportJsonArrayInsideJSonobject.getString("Owners");
							owners_id=check_Owners(session, owners_Name,out);
							
							if(vessel.hasNode(check_vesselNameProperties)){
								vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
								vesselSubNodeName.setProperty("owners_id", owners_id);
								session.save();
							}
						}
					}
					
					if(check_vesselNamePropertiesJsonobject.has("sternline")){
						sternline=check_vesselNamePropertiesJsonobject.getString("sternline");
						
					}else{
						if(allReportJsonArrayInsideJSonobject.has("SternLine")){
							sternline=allReportJsonArrayInsideJSonobject.getString("SternLine");
							
							if(vessel.hasNode(check_vesselNameProperties)){
								vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
								vesselSubNodeName.setProperty("sternline", sternline);
								session.save();
							}
							
						}
					}
					
					if(check_vesselNamePropertiesJsonobject.has("vesselType_id")){
						vesselType_id=check_vesselNamePropertiesJsonobject.getString("vesselType_id");
						
						
					}else{
						if(allReportJsonArrayInsideJSonobject.has("VesselType")){
							String VesselType_Name=allReportJsonArrayInsideJSonobject.getString("VesselType");
							vesselType_id=check_vesselType(session, VesselType_Name, out);
							if(vessel.hasNode(check_vesselNameProperties)){
								vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
								vesselSubNodeName.setProperty("vesselType_id", vesselType_id);
								session.save();
							}
						}
					}
					
					if(check_vesselNamePropertiesJsonobject.has("status_id")){
						status_id=check_vesselNamePropertiesJsonobject.getString("status_id");
					}
					
				}
				String check_Operators_id="";
				if(allReportJsonArrayInsideJSonobject.has("Operators")){
					String OperatorsString=allReportJsonArrayInsideJSonobject.getString("Operators");
					check_Operators_id=check_Operators(session, OperatorsString,out);
				}
				String check_EmployementStatus="";
				if(allReportJsonArrayInsideJSonobject.has("EmploymentStatus")){
					String EmploymentStatusString=allReportJsonArrayInsideJSonobject.getString("EmploymentStatus");
					check_EmployementStatus=check_EmployementStatus(session, EmploymentStatusString,out);
				}
				String check_OpenPort_id="";
				if(allReportJsonArrayInsideJSonobject.has("OpenPort")){
					String OpenPortString=allReportJsonArrayInsideJSonobject.getString("OpenPort");
					check_OpenPort_id=check_Port(session, OpenPortString, out);
				}
				String OpenDateString="";
				if(allReportJsonArrayInsideJSonobject.has("OpenDate")){
					 OpenDateString=allReportJsonArrayInsideJSonobject.getString("OpenDate");
				}
				String ETABasisString="";
				if(allReportJsonArrayInsideJSonobject.has("ETABasis")){
					 ETABasisString=allReportJsonArrayInsideJSonobject.getString("ETABasis");
				}
				String CommentString="";
				if(allReportJsonArrayInsideJSonobject.has("Comment")){
					 CommentString=allReportJsonArrayInsideJSonobject.getString("Comment");
				}
				String check_Source="";
				if(allReportJsonArrayInsideJSonobject.has("Source")){
					String SourceString=allReportJsonArrayInsideJSonobject.getString("Source");
					check_Source=check_Source(session, SourceString,out);
				}
				JSONObject dateJson=null;
				String combinedatetimestamp="";
				if(allReportJsonArrayInsideJSonobject.has("ReportTimestamp")){
					String ReportTimestampString=allReportJsonArrayInsideJSonobject.getString("ReportTimestamp");
					if(ReportTimestampString!=""){
						Date date=GmailMethods.parseDate(ReportTimestampString);
//						out.println("date: "+date);
						 dateJson=SlingMethods.formatDate_Month_Year_Day(date, out);
//						out.println("dateJson: "+dateJson);
						 
						    int day=0;
							int month=0;
							int year=0;
							int hours=0;
							int Minute=0;
							if(dateJson.has("day")){
								 day=dateJson.getInt("day");
							}if(dateJson.has("month")){
								 month=dateJson.getInt("month");
							}
							if(dateJson.has("year")){
								 year=dateJson.getInt("year");
							}
							if(dateJson.has("hours")){
								 hours=dateJson.getInt("hours");
							}
							if(dateJson.has("Minute")){
								Minute=dateJson.getInt("Minute");
							}
						
						 combinedatetimestamp=String.valueOf(day)+"-"+String.valueOf(month)+"-"+String.valueOf(year)+" "+hours+":"+Minute;
						 out.println("combinedatetimestamp: "+combinedatetimestamp);
					}
				}
				String check_Information="";
				if(allReportJsonArrayInsideJSonobject.has("Information")){
					String InformationString=allReportJsonArrayInsideJSonobject.getString("Information");
					check_Information=check_Information(session, InformationString, out);
				}
				String check_FixturetType_id="";
				if(allReportJsonArrayInsideJSonobject.has("FixtureType")){
					String FixtureTypeString=allReportJsonArrayInsideJSonobject.getString("FixtureType");
					check_FixturetType_id=check_ReportType(session, FixtureTypeString, out);
				}
				String check_Charterer="";
				if(allReportJsonArrayInsideJSonobject.has("Charterer")){
					String ChartererString=allReportJsonArrayInsideJSonobject.getString("Charterer");
					check_Charterer=check_Charterer(session, ChartererString, out);
				}
				String check_Status="";
				if(allReportJsonArrayInsideJSonobject.has("Status")){
					String StatusString=allReportJsonArrayInsideJSonobject.getString("Status");
					check_Status=check_EmployementStatus(session, StatusString,out);
				}
				String check_CargoGrade="";
				if(allReportJsonArrayInsideJSonobject.has("CargoGrade")){
					String CargoGradeString=allReportJsonArrayInsideJSonobject.getString("CargoGrade");
					check_CargoGrade=check_CargoGrade(session, CargoGradeString, out);
				}
				String CargoQtyString="";
				if(allReportJsonArrayInsideJSonobject.has("CargoQty")){
					 CargoQtyString=allReportJsonArrayInsideJSonobject.getString("CargoQty");
				}
				String LCStartString="";
				if(allReportJsonArrayInsideJSonobject.has("LCStart")){
					 LCStartString=allReportJsonArrayInsideJSonobject.getString("LCStart");
				}
				String LCEndString="";
				if(allReportJsonArrayInsideJSonobject.has("LCEnd")){
					 LCEndString=allReportJsonArrayInsideJSonobject.getString("LCEnd");
				}
				String check_LoadPort_id="";
				if(allReportJsonArrayInsideJSonobject.has("LoadPort")){
					String LoadPortString=allReportJsonArrayInsideJSonobject.getString("LoadPort");
					check_LoadPort_id=check_Port(session, LoadPortString, out);
				}
				String check_DiscPort_id="";
				if(allReportJsonArrayInsideJSonobject.has("DiscPort")){
					String DiscPortString=allReportJsonArrayInsideJSonobject.getString("DiscPort");
					check_DiscPort_id=check_Port(session, DiscPortString, out);
				}
				String check_RateType="";
				if(allReportJsonArrayInsideJSonobject.has("RateType")){
					String RateTypeString=allReportJsonArrayInsideJSonobject.getString("RateType");
					check_RateType=check_RateType(session, RateTypeString, out);
				}
				String RateString="";
				if(allReportJsonArrayInsideJSonobject.has("Rate")){
					 RateString=allReportJsonArrayInsideJSonobject.getString("Rate");
				}
				String ReportDateString="";
				if(allReportJsonArrayInsideJSonobject.has("ReportDate")){
					 ReportDateString=allReportJsonArrayInsideJSonobject.getString("ReportDate");
				}
				String check_Spot_TCS_id="";
				if(allReportJsonArrayInsideJSonobject.has("Spot_TC")){
					String Spot_TCString=allReportJsonArrayInsideJSonobject.getString("Spot_TC");
					check_Spot_TCS_id=check_ReportType(session, Spot_TCString, out);
				}
				String DateString="";
				if(allReportJsonArrayInsideJSonobject.has("Date")){
					 DateString=allReportJsonArrayInsideJSonobject.getString("Date");
				}
				String check_CPP_DPP_id="";
				if(allReportJsonArrayInsideJSonobject.has("CPP_DPP")){
					String CPP_DPPString=allReportJsonArrayInsideJSonobject.getString("CPP_DPP");
					check_CPP_DPP_id=check_CargoType(session, CPP_DPPString, out);
				}
				String DeliveryString="";
				if(allReportJsonArrayInsideJSonobject.has("Delivery")){
					 DeliveryString=allReportJsonArrayInsideJSonobject.getString("Delivery");
				}
				String check_DeliveryPlace="";
				if(allReportJsonArrayInsideJSonobject.has("DeliveryPlace")){
					String DeliveryPlaceString=allReportJsonArrayInsideJSonobject.getString("DeliveryPlace");
					check_DeliveryPlace=check_DeliveryPlace(session, DeliveryPlaceString, out);
				}
				String PeriodString="";
				if(allReportJsonArrayInsideJSonobject.has("Period")){
					 PeriodString=allReportJsonArrayInsideJSonobject.getString("Period");
				}
				String check_Periodunit="";
				if(allReportJsonArrayInsideJSonobject.has("Periodunit")){
					String PeriodunitString=allReportJsonArrayInsideJSonobject.getString("Periodunit");
					check_Periodunit=check_Periodunit(session, PeriodunitString, out);
				}
				String RedeliveryString="";
				if(allReportJsonArrayInsideJSonobject.has("Redelivery")){
					 RedeliveryString=allReportJsonArrayInsideJSonobject.getString("Redelivery");
				}
				String TCRateString="";
				if(allReportJsonArrayInsideJSonobject.has("TCRate")){
					 TCRateString=allReportJsonArrayInsideJSonobject.getString("TCRate");
				}
				String check_currencyUnit="";
				if(allReportJsonArrayInsideJSonobject.has("CurrencyUnit")){
					String CurrencyUnitString=allReportJsonArrayInsideJSonobject.getString("CurrencyUnit");
					check_currencyUnit=check_currencyUnit(session, CurrencyUnitString, out);
				}
				String Month_YearString="";
				if(allReportJsonArrayInsideJSonobject.has("Month_Year")){
					 Month_YearString=allReportJsonArrayInsideJSonobject.getString("Month_Year");
				}
				String first_yrString="";
				if(allReportJsonArrayInsideJSonobject.has("1yr")){
					 first_yrString=allReportJsonArrayInsideJSonobject.getString("1yr");
				}
				String second_yrString="";
				if(allReportJsonArrayInsideJSonobject.has("2yr")){
					 second_yrString=allReportJsonArrayInsideJSonobject.getString("2yr");
				}
				String third_yrString="";
				if(allReportJsonArrayInsideJSonobject.has("3yr")){
					 third_yrString=allReportJsonArrayInsideJSonobject.getString("3yr");
				}
				String fifth_yrString="";
				if(allReportJsonArrayInsideJSonobject.has("5yr")){
					 fifth_yrString=allReportJsonArrayInsideJSonobject.getString("5yr");
				}
				
				saveAllReportDataInSling(out, session, dateJson, check_ReportType_id, check_Port_repositionRegion_id, check_CargoType_cargotype_id, vessel_Id, vesselType_id, built, dwt, cubics, loa, ice, sternline, owners_id, check_Operators_id, check_EmployementStatus, check_OpenPort_id, OpenDateString, ETABasisString, CommentString, check_Source, combinedatetimestamp, check_Information, check_FixturetType_id, check_Charterer, check_Status, check_CargoGrade, CargoQtyString, LCStartString, LCEndString, check_LoadPort_id, check_DiscPort_id, check_RateType, RateString, ReportDateString, check_Spot_TCS_id, DateString, check_CPP_DPP_id, DeliveryString, check_DeliveryPlace, PeriodString, check_Periodunit, RedeliveryString, TCRateString, check_currencyUnit, Month_YearString, first_yrString, second_yrString, third_yrString, fifth_yrString);
				
				
			}// for close array
			
			
			
		}// allreport check
		
		
		
		
		
		
	} catch (Exception e) {
//		out.println(e.getMessage());
		System.out.println(e.getMessage());
	}
}

public void saveAllReportDataInSling(PrintWriter out, Session session, JSONObject dateJson, String ReportType, String RepositionRegion, String CargoType, String VesselName, String VesselType
		, String Built, String DWT, String Cubics, String LOA, String ICE, String SternLine, String Owners, String Operators, String EmploymentStatus, String OpenPort
		,String OpenDate, String ETABasis, String Comment, String Source, String ReportTimestamp, String Information, String FixtureType, String Charterer, String Status
		, String CargoGrade, String CargoQty, String LCStart, String LCEnd, String LoadPort, String DiscPort, String RateType, String Rate, String ReportDate
		, String Spot_TC, String Date, String CPP_DPP, String Delivery, String DeliveryPlace, String Period, String Periodunit, String Redelivery, String TCRate, String CurrencyUnit
		, String Month_Year, String first_yr, String second_yr, String third_yr, String fourth_yr){
	
	try {
		
		Node scorpio=null;
		Node Email=null;
		if(session.getRootNode().hasNode("scorpioDataBase")){
			scorpio = session.getRootNode().getNode("scorpioDataBase");
		}else{
			scorpio = session.getRootNode().addNode("scorpioDataBase");
			session.save();
		}
		
		if(scorpio.hasNode("Email")){
			Email=scorpio.getNode("Email");
		}else{
			Email=scorpio.addNode("Email");
			session.save();
		}
		

			int day=dateJson.getInt("day");
			int month=dateJson.getInt("month");
			int year=dateJson.getInt("year");
			out.println(day+" "+" "+month+" "+year);
			
			Node yearNode=null;
			Node monthNode=null;
			Node dayNode=null;
			
			String yearString=String.valueOf(year);
			String monthString=String.valueOf(month);
			String dayString=String.valueOf(day);
			out.println(dayString+" "+" "+monthString+" "+yearString);
			
			String yearNodeName="";
			if(Email.hasNode(yearString)){
				yearNode=Email.getNode(yearString);
				yearNodeName=yearNode.getName().toString();
			}
			
			String monthNodeName="";
			if(yearNodeName!=""){
			if(yearNode.hasNode(monthString)){
				monthNode=yearNode.getNode(monthString);
				monthNodeName=monthNode.getName().toString();
			}
			}
			
			String dayNodeName="";
			if(monthNodeName!=""){
			if(monthNode.hasNode(dayString)){
				dayNode=monthNode.getNode(dayString);
				dayNodeName=dayNode.getName().toString();
			}
			}
			
//			out.println("beside");
			Node yearNodeElse=null;
			Node monthNodeElse=null;
			Node dayNodeElse=null;
			
			if(yearNodeName!="" && monthNodeName!="" && dayNodeName!=""){
			if( (yearNodeName.equals(yearString)) && ( monthNodeName.equals(monthString)) && ( dayNodeName.equals(dayString))){
				out.println("if");
				callSaveMethods(out, session, dayNode, ReportType, RepositionRegion, CargoType, VesselName, VesselType, Built, DWT, Cubics, LOA, ICE, SternLine, Owners, Operators, EmploymentStatus, OpenPort, OpenDate, ETABasis, Comment, Source, ReportTimestamp, Information, FixtureType, Charterer, Status, CargoGrade, CargoQty, LCStart, LCEnd, LoadPort, DiscPort, RateType, Rate, ReportDate, Spot_TC, Date, CPP_DPP, Delivery, DeliveryPlace, Period, Periodunit, Redelivery, TCRate, CurrencyUnit, Month_Year, first_yr, second_yr, third_yr, fourth_yr);
				
				
			}
			}else{
				out.println("else");
				if(Email.hasNode(String.valueOf(year))){
					 yearNodeElse=Email.getNode( String.valueOf(year) );
				}else{
					 yearNodeElse=Email.addNode( String.valueOf(year) );
				}
				
				if(yearNodeElse.hasNode(String.valueOf(month))){
					 monthNodeElse=yearNodeElse.getNode( String.valueOf(month) );
				}else{
					 monthNodeElse=yearNodeElse.addNode( String.valueOf(month) );
				}
				
				if(monthNodeElse.hasNode(String.valueOf(day))){
					 dayNodeElse=monthNodeElse.getNode( String.valueOf(day) );
				}else{
					 dayNodeElse=monthNodeElse.addNode( String.valueOf(day) );
				}
				
				
				dayNodeElse.setProperty("jcr:count", String.valueOf(0));
				
				
				callSaveMethods(out, session, dayNodeElse, ReportType, RepositionRegion, CargoType, VesselName, VesselType, Built, DWT, Cubics, LOA, ICE, SternLine, Owners, Operators, EmploymentStatus, OpenPort, OpenDate, ETABasis, Comment, Source, ReportTimestamp, Information, FixtureType, Charterer, Status, CargoGrade, CargoQty, LCStart, LCEnd, LoadPort, DiscPort, RateType, Rate, ReportDate, Spot_TC, Date, CPP_DPP, Delivery, DeliveryPlace, Period, Periodunit, Redelivery, TCRate, CurrencyUnit, Month_Year, first_yr, second_yr, third_yr, fourth_yr);
				session.save();
			}

		
		
	} catch (Exception e) {
//		out.println("message: "+e.getMessage());
		System.out.println(e.getMessage());
	}
}


public void callSaveMethods(PrintWriter out, Session session,Node dayNode, String ReportType, String RepositionRegion, String CargoType, String VesselName, String VesselType
		, String Built, String DWT, String Cubics, String LOA, String ICE, String SternLine, String Owners, String Operators, String EmploymentStatus, String OpenPort
		,String OpenDate, String ETABasis, String Comment, String Source, String ReportTimestamp, String Information, String FixtureType, String Charterer, String Status
		, String CargoGrade, String CargoQty, String LCStart, String LCEnd, String LoadPort, String DiscPort, String RateType, String Rate, String ReportDate
		, String Spot_TC, String Date, String CPP_DPP, String Delivery, String DeliveryPlace, String Period, String Periodunit, String Redelivery, String TCRate, String CurrencyUnit
		, String Month_Year, String first_yr, String second_yr, String third_yr, String fourth_yr){
	
	try {
		
		Node dayNodeList=null;
		String jcrCount=dayNode.getProperty("jcr:count").getString();
		int jcrCountIntValue = Integer.parseInt(jcrCount)+1;
		String dayNodeId=String.valueOf(jcrCountIntValue);
		
		if(dayNode.hasNode(dayNodeId)){
			dayNodeList=dayNode.getNode(dayNodeId);
		}else{
			dayNodeList=dayNode.addNode(dayNodeId);
		}
		
		dayNodeList.setProperty("Id", dayNodeId);
		dayNodeList.setProperty("ReportType", ReportType);
		dayNodeList.setProperty("RepositionRegion", RepositionRegion);
		dayNodeList.setProperty("CargoType", CargoType);
		dayNodeList.setProperty("VesselName", VesselName);
		dayNodeList.setProperty("VesselType", VesselType);
		dayNodeList.setProperty("Built", Built);
		dayNodeList.setProperty("DWT", DWT);
		dayNodeList.setProperty("Cubics", Cubics);
		dayNodeList.setProperty("LOA", LOA);
		dayNodeList.setProperty("ICE", ICE);
		dayNodeList.setProperty("SternLine", SternLine);
		dayNodeList.setProperty("Owners", Owners);
		dayNodeList.setProperty("Operators", Operators);
		dayNodeList.setProperty("EmploymentStatus", EmploymentStatus);
		dayNodeList.setProperty("OpenPort", OpenPort);
		dayNodeList.setProperty("OpenDate", OpenDate);
		dayNodeList.setProperty("ETABasis", ETABasis);
		dayNodeList.setProperty("Comment", Comment);
		dayNodeList.setProperty("Source", Source);
		dayNodeList.setProperty("ReportTimestamp", ReportTimestamp);
		dayNodeList.setProperty("Information", Information);
		dayNodeList.setProperty("FixtureType", FixtureType);
		dayNodeList.setProperty("Charterer", Charterer);
		dayNodeList.setProperty("Status", Status);
		dayNodeList.setProperty("CargoGrade", CargoGrade);
		dayNodeList.setProperty("CargoQty", CargoQty);
		dayNodeList.setProperty("LCStart", LCStart);
		dayNodeList.setProperty("LCEnd", LCEnd);
		dayNodeList.setProperty("LoadPort", LoadPort);
		dayNodeList.setProperty("DiscPort", DiscPort);
		dayNodeList.setProperty("RateType", RateType);
		dayNodeList.setProperty("Rate", Rate);
		dayNodeList.setProperty("ReportDate", ReportDate);
		dayNodeList.setProperty("Spot_TC", Spot_TC);
		dayNodeList.setProperty("Date", Date);
		dayNodeList.setProperty("CPP_DPP", CPP_DPP);
		dayNodeList.setProperty("Delivery", Delivery);
		dayNodeList.setProperty("DeliveryPlace", DeliveryPlace);
		dayNodeList.setProperty("Period", Period);
		dayNodeList.setProperty("Periodunit", Periodunit);
		dayNodeList.setProperty("Redelivery", Redelivery);
		dayNodeList.setProperty("TCRate", TCRate);
		dayNodeList.setProperty("CurrencyUnit", CurrencyUnit);
		dayNodeList.setProperty("Month_Year", Month_Year);
		dayNodeList.setProperty("1yr", first_yr);
		dayNodeList.setProperty("2yr", second_yr);
		dayNodeList.setProperty("3yr", third_yr);
		dayNodeList.setProperty("5yr", fourth_yr);
		
		
		dayNode.setProperty("jcr:count", String.valueOf(String.valueOf(Integer.parseInt(jcrCount)+1)));
	    session.save();
		
	} catch (Exception e) {
//		out.println(e.getMessage());
		System.out.println(e.getMessage());
	}
}

public  String check_vesselName_FOr_Ui_Save(Session session,String vesselName, PrintWriter out) {

//	String vesselSubNodeName="";
	String objName="";
try {
	Node obj = null;
	Workspace workspace = session.getWorkspace();
	vesselName=vesselName.toLowerCase().trim();

	if(!GmailMethods.isNullString(vesselName)){
	
	String slingqery = "select [vesselName] from [nt:base] where LOWER(vesselName) ='" + vesselName
	+ "'  and ISDESCENDANTNODE('/scorpioDataBase/vessel/')";
	
	Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
//	query.setLimit(10);
	QueryResult queryResult = query.execute();
	NodeIterator iterator = queryResult.getNodes();
	while (iterator.hasNext()) {

		obj = iterator.nextNode();
		 objName=obj.getName();
		
	}
	
} // vesselname blank check
} catch (Exception e) {
//	e.printStackTrace(out);
	return "";
	
}
return objName;

}


}
