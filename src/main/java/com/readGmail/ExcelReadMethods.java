package com.readGmail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.Workspace;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.sling.commons.json.JSONObject;

import com.reportinformationsystem.Pojo;

import ChangedStructureCurrent.ChangedStructureCurrent_Methods;




public class ExcelReadMethods {
    
	ChangedStructureCurrent_Methods CSC=new ChangedStructureCurrent_Methods();
	
	public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
        String data="http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#fulham_road";
      // int j= data.lastIndexOf("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#");
//       data=data.substring(j);
       
      if(data.contains("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#")){
       String key_split[] = data.split("#");
       String key = key_split[1];
       System.out.println(key);
      }
      
      
      String vesselname="ardmore_seaventure";
      if(vesselname.contains("_")){
    	  vesselname=  vesselname.replace("_", " ");
    	  System.out.println(vesselname);
      }
       
       
	}
	
	public  String check_Port(Session session,String Port , PrintWriter out) {


		String vessel_id = "";
//			String data="";
		//JSONObject vesselJson=null;
		try {
		if(!GmailMethods.isNullString(Port)){
			Node obj = null;
			String portName="";
//			String Area="";

			Workspace workspace = session.getWorkspace();
			Port=Port.toUpperCase().trim();

			/*String slingqery = "select [portName] from [nt:base] where (contains('portName','" + Port
					+ "')) and ISDESCENDANTNODE('/scorpioDataBase/Port/')";*/
			String slingqery = "select [portName] from [nt:base] where portName ='" + Port
					+ "'  and ISDESCENDANTNODE('/scorpioDataBase/Port/')";
			Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
			QueryResult queryResult = query.execute();
			NodeIterator iterator = queryResult.getNodes();
			JSONObject vesselJson=new JSONObject();
			while (iterator.hasNext()) {
				
				obj = iterator.nextNode();
				if(obj.hasProperty("port_id")){
					 vessel_id = obj.getProperty("port_id").getString();
//					vesselJson.put("port_id", vessel_id);
				}
				/*if(obj.hasProperty("portName")){
					 portName = obj.getProperty("portName").getString();
					vesselJson.put("portName", portName);
				}
				
				if(obj.hasProperty("Area")){
					String Area = obj.getProperty("Area").getString();
					vesselJson.put("Area", Area);
				}*/
				
//				data=vesselJson.toString();
				
			}
			
			
			
		}

		} catch (Exception e) {
//			out.println(e.getMessage());
			return "";
			
		}
		return vessel_id;

		}

	public  String fetch_Port(Session session,String Port) {


		String vessel_id = "";
//			String data="";

		try {
		if(Port!=""){
			Node obj = null;
//			String portName="";

			Workspace workspace = session.getWorkspace();

			String slingqery = "select [port_id] from [nt:base] where (contains('port_id','" + Port
					+ "')) and ISDESCENDANTNODE('/scorpioDataBase/Port/')";
			Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
			QueryResult queryResult = query.execute();
			NodeIterator iterator = queryResult.getNodes();
//			JSONObject vesselJson=new JSONObject();
			while (iterator.hasNext()) {

				obj = iterator.nextNode();
				if(obj.hasProperty("portName")){
				    vessel_id = obj.getProperty("portName").getString();//portName
//				   vesselJson.put("portName", vessel_id);
				}

			}
			
			
		}

		} catch (Exception e) {
			return "";
		}
		return vessel_id;

		}
	
	public static void data(PrintWriter out, Session session){
		
		try {
			
			Node scorpio = session.getRootNode().getNode("scorpioDataBase");
			Node ReportData = scorpio.getNode("ReportData");
			Node Tonnage = ReportData.getNode("Tonnage");
			
			if (Tonnage.hasNodes()) {
				NodeIterator itr = Tonnage.getNodes();
				while (itr.hasNext()) {
					Node nextNode=itr.nextNode();
					
					if (nextNode.hasProperty("OpenPort")) {
						String OpenPort = nextNode.getProperty("OpenPort").getString();
						ExcelReadMethods m=new ExcelReadMethods();
						String openportName=m.fetch_Port(session, OpenPort);
						String id=m.check_Port(session, openportName, out);
						out.println("id: "+id);
						nextNode.setProperty("OpenPort", id);
						session.save();
						
					}
				}
			}
			
			
			
		} catch (Exception e) {
			
		}
		
	}
	
	
	//@SuppressWarnings("resource")
	public static void readFile(String fileName, PrintWriter out, Session session) throws FileNotFoundException, IOException, SQLException {
        FileInputStream fis;
        String id="";
        int columnindex=0;
        int  totalRows=0;
        int firstrowno=0;
        int lastrowno=0;
        int v=0;
        int batchcount = 0;
        String b="";
        String d = "";
        String e = "";
        String f = "";
        String g = "";
        String h = "";
        String i = "";
        String j = "";
        String k = "";
        String l = "";
        String m = "";
        String n = "";
        String o = "";
        String p = "";
        String q = "";
        List <Pojo> addTodb = null;
        try {
            out.println("-------------------------------READING THE SPREADSHEET-------------------------------------");
            fis = new FileInputStream(fileName);
            HSSFWorkbook workbookRead1=null;
             workbookRead1 = new HSSFWorkbook(fis);
            HSSFSheet spreadsheetRead = null;
            spreadsheetRead= workbookRead1.getSheetAt(0);

          totalRows = spreadsheetRead.getPhysicalNumberOfRows(); // total rows 
          addTodb = new ArrayList<>(totalRows);
            Iterator< Row> rowIterator = spreadsheetRead.iterator();
            while (rowIterator.hasNext()) {
                HSSFRow row = (HSSFRow) rowIterator.next();
                Iterator< Cell> cellIterator = row.cellIterator();
                

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                   
                    
//                    cell.setCellType(0);
                    cell.setCellType(cell.CELL_TYPE_STRING);
//                    cell.setCellType(CellType.STRING);
//                    
                   columnindex=  cell.getColumnIndex();
                  
                   
                  firstrowno=  spreadsheetRead.getFirstRowNum();
                  lastrowno=spreadsheetRead.getLastRowNum();

                  if(!(columnindex==0 || columnindex==1||columnindex==2||columnindex==3||columnindex==4||columnindex==5
                		  ||columnindex==6)){
                	  continue;
                  }
                  
                  
                   if(columnindex==0){
                	  d=cell.getStringCellValue();
                	  
                		  d=cell.getStringCellValue();
                	  
                	  
//                	  out.println("d: "+d); // vesseltype
                  }
                 
                  
                  else if(columnindex==1){
                	  e=cell.getStringCellValue();
                	 
                		  e=cell.getStringCellValue();
                	  
//                	  out.println("e: "+e);  // name vessel
                  }
                   
                   
                  // combinePortAndVesselCheck(out, session, d, e);
                 
                 
                
                }// while close
                batchcount++;
                Pojo pj =new Pojo(d, e);    
                addTodb.add(pj);
     
            }

            
            out.println("Total no of records inserted finally "+batchcount);
            addAllRecords(addTodb, out, session);

            fis.close();
            workbookRead1.close();
        } catch (IOException e1) {
        	//out.println(e1.getMessage());
            e1.printStackTrace(out);
        }
    }
	
	private static void addAllRecords(List<Pojo>recordset, PrintWriter out, Session session){
		
		int count = 0;
		int i =0;
		try{
			
			i = recordset.size();
			out.println("Record to be inserted is  "+i);
			for(Pojo ps :recordset){
				
				//pstmt.setString(7, ps.getIce());
				String d=ps.getVesselName();
				String e=ps.getUrl();
				
				 combinePortAndVesselCheck(out, session, d, e);
				
	            count++;
	            out.println("count: "+count);
	            
	            
	            

			}
			
		}catch(Exception e1){
			e1.printStackTrace(out);
			
		}
	}
	
	
	public static String check_PortExcel(Session session, PrintWriter out, String passportNameExcel, String portExcelUrl) {


		//String vessel_id = "";
			String data="";
		//JSONObject vesselJson=null;
		try {
		if(!GmailMethods.isNullString(passportNameExcel)){
			Node obj = null;
			String portName="";
//			String Area="";

			Workspace workspace = session.getWorkspace();
			
			if(passportNameExcel.contains("_")){
				passportNameExcel=  passportNameExcel.replace("_", " ");
//		    	  System.out.println(vesselname);
		      }
			
			passportNameExcel=passportNameExcel.toLowerCase().trim();

			/*String slingqery = "select [portName] from [nt:base] where (contains('portName','" + Port
					+ "')) and ISDESCENDANTNODE('/scorpioDataBase/Port/')";*/
			String slingqery = "select [portName] from [nt:base] where LOWER(portName) ='" + passportNameExcel
					+ "'  and ISDESCENDANTNODE('/scorpioDataBase2/Port/')";
			Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
			QueryResult queryResult = query.execute();
			NodeIterator iterator = queryResult.getNodes();
			long count=queryResult.getNodes().getSize();
			out.println("portCount: "+count);
			
			while (iterator.hasNext()) {
				
				obj = iterator.nextNode();
				obj.setProperty("portUrl", portExcelUrl);
				session.save();
				
				if(obj.hasProperty("portName")){
					 portName = obj.getProperty("portName").getString();
					
				}
				
				
			}
			
			portName = (GmailMethods.isNullString(portName)) ? "" : portName;
			
			
		}

		} catch (Exception e) {
			out.println(e.getMessage());
//			return "";
			
		}
		return data;

		}
	
	public static String check_vesselExcel(Session session,String passvesselNameExcel, PrintWriter out, String vesselExcelUrl) {


		String vessel_id = null;

		try {

			Node obj = null;

			Workspace workspace = session.getWorkspace();
			
			if(passvesselNameExcel.contains("_")){
				passvesselNameExcel=  passvesselNameExcel.replace("_", " ");
//		    	  System.out.println(vesselname);
		      }
			
			passvesselNameExcel=passvesselNameExcel.toLowerCase().trim();

			/*String slingqery = "select [vesselType] from [nt:base] where (contains('vesselType','" + mysqlvesselType
					+ "')) and ISDESCENDANTNODE('/scorpioDataBase/vesselType/')";*/
			String slingqery = "select [vesselName] from [nt:base] where LOWER(vesselName) ='" + passvesselNameExcel
					+ "'  and ISDESCENDANTNODE('/scorpioDataBase2/vessel/')";
			
			Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
			QueryResult queryResult = query.execute();
			NodeIterator iterator = queryResult.getNodes();
			long count=queryResult.getNodes().getSize();
			//out.println("vesselCount: "+count);
			String vesselNamequery="";
			while (iterator.hasNext()) {

				obj = iterator.nextNode();
				obj.setProperty("vesselUrl", vesselExcelUrl);
				session.save();
				
				if(obj.hasProperty("vesselName")){
					 vesselNamequery = obj.getProperty("vesselName").getString();
					// out.println("vesselNamequery: "+vesselNamequery);
				}//vessel_Id
				
				if(obj.hasProperty("vessel_Id")){
					String vessel_Id = obj.getProperty("vessel_Id").getString();
					 out.println("vessel_Id: "+vessel_Id);
				}//vessel_Id
				
			}
			
			vesselNamequery = (GmailMethods.isNullString(vesselNamequery)) ? "" : vesselNamequery;
			
			/*if(  !(passvesselNameExcel.equals(vesselNamequery.toLowerCase()))  ){
				 check_PortExcel(session, out, passvesselNameExcel, vesselExcelUrl);
			}*/

			
			
		} catch (Exception e) {
			return e.getMessage();
		}
		return vessel_id;

		}
	
	
	public static void combinePortAndVesselCheck(PrintWriter out, Session session, String combineNamePass, String combineUrlPass ){
		
		try {
			
			//check_vesselExcel(session, combineNamePass, out, combineUrlPass);
			check_PortExcel(session, out, combineNamePass, combineUrlPass);
			
			
		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}
	
	public  String check_PortUrl(Session session,String PortUrl , PrintWriter out) {


		//String vessel_id = "";
			String data="";
		//JSONObject vesselJson=null;
		try {
		if(!GmailMethods.isNullString(PortUrl)){
			Node obj = null;
			String portName="";
//			String Area="";

			Workspace workspace = session.getWorkspace();
			PortUrl=PortUrl.toLowerCase().trim();

			/*String slingqery = "select [portName] from [nt:base] where (contains('portName','" + Port
					+ "')) and ISDESCENDANTNODE('/scorpioDataBase/Port/')";*/
			String slingqery = "select [portUrl] from [nt:base] where LOWER(portUrl) ='" + PortUrl
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
				}
				
				if(obj.hasProperty("Area")){
					String Area = obj.getProperty("Area").getString();
					vesselJson.put("Area", Area);
				}
				
				data=vesselJson.toString();
				
			}
			
			
			if( !(PortUrl.equals(portName.toLowerCase().trim())) ){
				String vessel_id=scorpioPortSlingAdd(session, out, PortUrl);
				vesselJson.put("port_id", vessel_id);
				data=vesselJson.toString();
//				return data;
			}
			
		}

		} catch (Exception e) {
//			out.println(e.getMessage());
			return "";
			
		}
		return data;

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
			
//			String sql="SELECT vesselName FROM portScorpio";
			 
//			Connection con=null;
			Node vesselSubNode=null;
			String count="";
			
//			con=MysqlConnection.getConnectionObj();
//			Statement st = con.createStatement();
//			 ResultSet rs = st.executeQuery(sql);
//			 while (rs.next()){
				 if(vessel.hasProperty("jcr:count")){
				   count=vessel.getProperty("jcr:count").getString();
				   forid= String.valueOf(Integer.parseInt(count)+1);
				   String urlSave=vesselNamePass;
				 
				   if(vesselNamePass.contains("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#")){
					   
					       String key_split[] = vesselNamePass.split("#");
					        vesselNamePass = key_split[1];
					        out.println("port_key: "+vesselNamePass);
					      
					   
				String vesselName = vesselNamePass;
				String vesselname="";
				
					vesselname=vesselName.replace(" ", "_").replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9]", "_");
				
					
				if(vessel.hasNode(vesselname)){
					vesselSubNode=vessel.getNode(vesselname);
				}else{
					vesselSubNode=vessel.addNode(vesselname);
					

					 vesselSubNode.setProperty("port_id", forid);
					 vesselSubNode.setProperty("portName", vesselName);
					 vesselSubNode.setProperty("portUrl", urlSave);
					
					 
					 vessel.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
					 session.save();
				}
				
//				 session.save();
				 }
		}// key check url
				 
				/* out.printf(" %s\n", forid);
			     out.printf(" %s\n", vesselname);*/
			     
				 
//			 }
			
		} catch (Exception e) {
//			out.println(e.getMessage());
			return "";
		}
		
		return forid;
		
	}

	
	public  String check_vesselNameUrl(Session session,String vesselNameUrl, PrintWriter out) {

//		JSONObject allVesselProperties=null;
		String vesselSubNodeName="";
	try {
		
		Node obj = null;
		String vesselNamequery="";

		Workspace workspace = session.getWorkspace();
		vesselNameUrl=vesselNameUrl.toLowerCase().trim();
		out.println("insidevesselName "+vesselNameUrl);

		if(!GmailMethods.isNullString(vesselNameUrl)){
		/*String slingqery = "select [vesselName] from [nt:base] where (contains('vesselName','" + vesselName
				+ "')) and ISDESCENDANTNODE('/scorpioDataBase/vessel/')";*/
		/*String slingqery = "select [vesselName] from [nt:base] where vesselName ='" + vesselName
				+ "'  and ISDESCENDANTNODE('/scorpioDataBase/vessel/')";*/
		
		String slingqery = "select [vesselUrl] from [nt:base] where LOWER(vesselUrl) ='" + vesselNameUrl
		+ "'  and ISDESCENDANTNODE('/scorpioDataBase/vessel/')";
		
		Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
//		query.setLimit(10);
		QueryResult queryResult = query.execute();
		NodeIterator iterator = queryResult.getNodes();
		out.println("count "+iterator.getSize());
		while (iterator.hasNext()) {

			obj = iterator.nextNode();
//			out.println("obj "+obj);
			if(obj.hasProperty("vessel_Id")){
				String vessel_id = obj.getProperty("vessel_Id").getString();
				out.println("vessel_id "+vessel_id);
			}
			
			if(obj.hasProperty("vesselName")){
				 vesselNamequery = obj.getProperty("vesselName").getString();
				 out.println("vesselNamequery: "+vesselNamequery);
			}
			

		}
		
		if(vesselNameUrl.equals(vesselNamequery.toLowerCase().trim())){
			JSONObject allVesselProperties=new JSONObject();
			if(obj.hasProperty("vessel_Id")){
				String vessel_id = obj.getProperty("vessel_Id").getString();
				out.println("vessel_id "+vessel_id);
				allVesselProperties.put("vessel_Id", vessel_id);
			}
			if(obj.hasProperty("vesselName")){
				vesselNamequery = obj.getProperty("vesselName").getString();
				allVesselProperties.put("vesselNamequery", obj.getName());
				 out.println("vesselNamequery: "+vesselNamequery);
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
			
		}
		//out.println("allVesselProperties: "+allVesselProperties);
		else{
			vesselSubNodeName=vesselSlingSave(session, out, vesselNameUrl);
			out.println("other node: "+vesselSubNodeName);
//			return vesselSubNodeName;
		}
	} // vesselname blank check
	} catch (Exception e) {
		e.printStackTrace(out);
//		out.println(e.getMessage());
//		return "";	
	}
	return vesselSubNodeName;

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
				session.save();
			}
			
			if(scorpio.hasNode("vessel")){
				vessel=scorpio.getNode("vessel");
			}else{
				vessel=scorpio.addNode("vessel");
				vessel.setProperty("jcr:count", String.valueOf(0));
				session.save();
			}
			
			    String vesselSaveUrl=vesselNamePassData;
			    Node vesselSubNode=null;
			
				String count=vessel.getProperty("jcr:count").getString();
				String forid= String.valueOf(Integer.parseInt(count)+1);
				
				if(vesselNamePassData.contains("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#")){
					   
				       String key_split[] = vesselNamePassData.split("#");
				       vesselNamePassData = key_split[1];
				        out.println("vessel_key: "+vesselNamePassData);
				
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
				
				 vesselSubNode.setProperty("vessel_Id", forid);
				 vesselSubNode.setProperty("vesselName", vesselName);
				 vesselSubNode.setProperty("vesselUrl", vesselSaveUrl);
				 
				 
				 vessel.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
				 session.save();
			}
				 
				// out.printf(" %s\n", forid);
			    // out.printf(" %s\n", vesselname);
			}
			
		} catch (Exception e) {
			out.println(e.getMessage());
		}
		return vesselSubNodeName;
		
	}

	

}
