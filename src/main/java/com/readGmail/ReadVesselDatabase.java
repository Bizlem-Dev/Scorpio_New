package com.readGmail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.Workspace;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.mysql.jdbc.Connection;

public class ReadVesselDatabase {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/*Document htmlFile = null;
		htmlFile = Jsoup.parse(new File("D:\\Scorpio\\Scorpio\\scorpip-demo\\demo\\g.html"), "ISO-8859-1");
		Document doc = Jsoup.parseBodyFragment(htmlFile.toString());
		Element body = doc.body();
		String data=body.wholeText();*/
		
		ReadVesselDatabase s=new ReadVesselDatabase();
		String data=s.readFileAsString("D:\\Scorpio\\Scorpio\\scorpip-demo\\demo\\g.html");
		//System.out.println(data);
		
		Document doc = Jsoup.parseBodyFragment(data);
		Element body = doc.body();
		String data1=body.wholeText();
		System.out.println(data1);
	}
	
	public String readFileAsString(String filePath) throws IOException {
        StringBuffer fileData = new StringBuffer();
        BufferedReader reader = new BufferedReader( new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }
	
	
	public static void scorpioDataBaseSave(Session session, PrintWriter out){
		
		try {
			
			Node scorpio=null;
			Node vessel=null;
			Node subNode=null;
		
			
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
			
			String sql="SELECT vesselName,vesselType,dwt,loa,built,cubics,ice,sternline,owners,status FROM scorpio";
			 
			Connection con=null;
			Node vesselSubNode=null;
			Node vesselTypeSubNode=null;
			Node dwtSubNode=null;
			Node loaSubNode=null;
			Node builtSubNode=null;
			Node iceSubNode=null;
			Node ownersSubNode=null;
			
			con=MysqlConnection.getConnectionObj();
			Statement st = con.createStatement();
			 ResultSet rs = st.executeQuery(sql);
			 while (rs.next()){
				 
				String count=vessel.getProperty("jcr:count").getString();
				String forid= String.valueOf(Integer.parseInt(count)+1);
				String vesselName = rs.getString("vesselName");
				String vesselname="";
				if(vesselName.contains("")){
					vesselname=vesselName.replace(" ", "_");
				}
				
				String vesselType = rs.getString("vesselType");
				String dwt = rs.getString("dwt");
				String loa = rs.getString("loa");
				String built = rs.getString("built");
				String cubics = rs.getString("cubics");
				String ice = rs.getString("ice");
				String sternline = rs.getString("sternline");
				String owners = rs.getString("owners");
				String status = rs.getString("status");
				
				
				
				
				String forid1="";
				
				if(scorpio.hasNode("vesselType")){
					vesselTypeSubNode=scorpio.getNode("vesselType");
					String count1=vesselTypeSubNode.getProperty("jcr:count").getString();
					forid1= String.valueOf(Integer.parseInt(count1)+1);
					
					if(!vesselTypeSubNode.hasNode(forid1)){
						Node vesselTypeSubNodesub=vesselTypeSubNode.addNode(forid1);
						
						vesselTypeSubNodesub.setProperty("id", forid1);
						vesselTypeSubNodesub.setProperty("vesselType", vesselType);
					}
					vesselTypeSubNode.setProperty("jcr:count",String.valueOf(Integer.parseInt(count1)+1));
					session.save();
					
				}else{
					vesselTypeSubNode=scorpio.addNode("vesselType");
					vesselTypeSubNode.setProperty("jcr:count", String.valueOf(0));
					session.save();
				}
				
				
				
				
				String foriddwt="";
				if(scorpio.hasNode("dwt")){
					dwtSubNode=scorpio.getNode("dwt");
					String count1=dwtSubNode.getProperty("jcr:count").getString();
					foriddwt= String.valueOf(Integer.parseInt(count1)+1);
					
					if(!dwtSubNode.hasNode(foriddwt)){
						Node vesselTypeSubNodesub=dwtSubNode.addNode(foriddwt);
						
						vesselTypeSubNodesub.setProperty("id", foriddwt);
						vesselTypeSubNodesub.setProperty("dwt", dwt);
					}
					dwtSubNode.setProperty("jcr:count",String.valueOf(Integer.parseInt(count1)+1));
					session.save();
				}else{
					dwtSubNode=scorpio.addNode("dwt");
					dwtSubNode.setProperty("jcr:count", String.valueOf(0));
					session.save();
				}
				
				String foridloa="";
				if(scorpio.hasNode("loa")){
					loaSubNode=scorpio.getNode("loa");
					String count1=loaSubNode.getProperty("jcr:count").getString();
					foridloa= String.valueOf(Integer.parseInt(count1)+1);
					
					if(!loaSubNode.hasNode(foridloa)){
						Node vesselTypeSubNodesub=loaSubNode.addNode(foridloa);
						
						vesselTypeSubNodesub.setProperty("id", foridloa);
						vesselTypeSubNodesub.setProperty("loa", loa);
					}
					
					loaSubNode.setProperty("jcr:count",String.valueOf(Integer.parseInt(count1)+1));
					session.save();
					
				}else{
					loaSubNode=scorpio.addNode("loa");
					loaSubNode.setProperty("jcr:count", String.valueOf(0));
					session.save();
				}
				
				String foridbuilt="";
				if(scorpio.hasNode("built")){
					builtSubNode=scorpio.getNode("built");
					String count1=builtSubNode.getProperty("jcr:count").getString();
					foridbuilt= String.valueOf(Integer.parseInt(count1)+1);
					
					if(!builtSubNode.hasNode(foridbuilt)){
						Node vesselTypeSubNodesub=builtSubNode.addNode(foridbuilt);
						
						vesselTypeSubNodesub.setProperty("id", foridbuilt);
						vesselTypeSubNodesub.setProperty("built", built);
					}
					builtSubNode.setProperty("jcr:count",String.valueOf(Integer.parseInt(count1)+1));
					session.save();
					
				}else{
					builtSubNode=scorpio.addNode("built");
					builtSubNode.setProperty("jcr:count", String.valueOf(0));
					session.save();
				}
				
				String foridice="";
				if(scorpio.hasNode("ice")){
					iceSubNode=scorpio.getNode("ice");
					String count1=iceSubNode.getProperty("jcr:count").getString();
					foridice= String.valueOf(Integer.parseInt(count1)+1);
					
					if(!iceSubNode.hasNode(foridice)){
						Node vesselTypeSubNodesub=iceSubNode.addNode(foridice);
						
						vesselTypeSubNodesub.setProperty("id", foridice);
						vesselTypeSubNodesub.setProperty("ice", ice);
					}
					iceSubNode.setProperty("jcr:count",String.valueOf(Integer.parseInt(count1)+1));
					session.save();
					
				}else{
					iceSubNode=scorpio.addNode("ice");
					iceSubNode.setProperty("jcr:count", String.valueOf(0));
					session.save();
				}
				
				String foridowners="";
				if(scorpio.hasNode("owners")){
					ownersSubNode=scorpio.getNode("owners");
					String count1=ownersSubNode.getProperty("jcr:count").getString();
					foridowners= String.valueOf(Integer.parseInt(count1)+1);
					
					if(!ownersSubNode.hasNode(foridowners)){
						Node vesselTypeSubNodesub=ownersSubNode.addNode(foridowners);
						
						vesselTypeSubNodesub.setProperty("id", foridowners);
						vesselTypeSubNodesub.setProperty("owners", owners);
					}
					ownersSubNode.setProperty("jcr:count",String.valueOf(Integer.parseInt(count1)+1));
					session.save();
					
				}else{
					ownersSubNode=scorpio.addNode("owners");
					ownersSubNode.setProperty("jcr:count", String.valueOf(0));
					session.save();
				}
				
				if(vessel.hasNode(vesselname)){
					vesselSubNode=vessel.getNode(vesselname);
				}else{
					vesselSubNode=vessel.addNode(vesselname);
//					vessel.setProperty("jcr:count", String.valueOf(0));
					session.save();
				}
				
				 vesselSubNode.setProperty("id", forid);
				 vesselSubNode.setProperty("vesselName", vesselName);
				 vesselSubNode.setProperty("vesselType_id", forid1);
				 vesselSubNode.setProperty("dwt_id", foriddwt);
				 vesselSubNode.setProperty("loa_id", foridloa);
				 vesselSubNode.setProperty("built_id", foridbuilt);
				 vesselSubNode.setProperty("cubics", "");
				 vesselSubNode.setProperty("ice_id", foridice);
				 vesselSubNode.setProperty("sternline", "");
				 vesselSubNode.setProperty("owners_id", foridowners);
				 vesselSubNode.setProperty("status", status);
				 
				 vessel.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
				 
				 out.printf(" %s\n", forid);
			     out.printf(" %s\n", vesselname);
			     
				 
			 }
				 
			
			
		} catch (Exception e) {
			out.println(e.getMessage());
		}
		
	}
	
	
public static void scorpioDataBaseSave_new(Session session, PrintWriter out){
		
		try {
			
			Node scorpio=null;
			Node vessel=null;
			
			if(session.getRootNode().hasNode("DataBase_new")){
				scorpio = session.getRootNode().getNode("DataBase_new");
			}else{
				scorpio = session.getRootNode().addNode("DataBase_new");
				session.save();
			}
			
			if(scorpio.hasNode("vessel")){
				vessel=scorpio.getNode("vessel");
			}else{
				vessel=scorpio.addNode("vessel");
				vessel.setProperty("jcr:count", String.valueOf(0));
				session.save();
			}
			
			String sql="SELECT vesselName,vesselType,dwt,loa,built,cubics,ice,sternline,owners,status FROM scorpio2";
			 
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
				
				String vesselType = rs.getString("vesselType");
				String dwt = rs.getString("dwt");
				String loa = rs.getString("loa");
				String built = rs.getString("built");
				String cubics = rs.getString("cubics");
				String ice = rs.getString("ice");
				String sternline = rs.getString("sternline");
				String owners = rs.getString("owners");
				String status = rs.getString("status");
				
				
				if(vessel.hasNode(vesselname)){
					vesselSubNode=vessel.getNode(vesselname);
				}else{
					vesselSubNode=vessel.addNode(vesselname);
//					vessel.setProperty("jcr:count", String.valueOf(0));
					session.save();
				}
				
				 String forid1=check_vesselType(session, vesselType);
				 String status_id= check_Status(session, status);
				 String foridowners= check_Owners(session, owners);
				
				 vesselSubNode.setProperty("id", forid);
				 vesselSubNode.setProperty("vesselName", vesselName);
				 vesselSubNode.setProperty("vesselType_id", forid1);
				 vesselSubNode.setProperty("dwt", dwt);
				 vesselSubNode.setProperty("loa", loa);
				 vesselSubNode.setProperty("built", built);
				 vesselSubNode.setProperty("cubics", "");
				 vesselSubNode.setProperty("ice", ice);
				 vesselSubNode.setProperty("sternline", "");
				 vesselSubNode.setProperty("owners_id", foridowners);
				 vesselSubNode.setProperty("status_id", status_id);
				 
				 vessel.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
//				 session.save();
				 
				 out.printf(" %s\n", forid);
			     out.printf(" %s\n", vesselname);
			     
				 
			 }
				 
			
			
		} catch (Exception e) {
			out.println(e.getMessage());
		}
		
	}

public static String check_vesselType(Session session,String mysqlvesselType) {

	
	String vessel_id = null;

	try {

		Node obj = null;

		Workspace workspace = session.getWorkspace();

		String slingqery = "select [vesselType] from [nt:base] where (contains('vesselType','" + mysqlvesselType
				+ "')) and ISDESCENDANTNODE('/DataBase_new/vesselType/')";
		Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
		QueryResult queryResult = query.execute();
		NodeIterator iterator = queryResult.getNodes();
		while (iterator.hasNext()) {

			obj = iterator.nextNode();
			vessel_id = obj.getProperty("id").getString();

		}

		
		
	} catch (Exception e) {
		return e.getMessage();
	}
	return vessel_id;

}

public static String check_Status(Session session,String mysqlStatus) {

	
	String vessel_id = null;

	try {

		Node obj = null;

		Workspace workspace = session.getWorkspace();

		String slingqery = "select [Status] from [nt:base] where (contains('Status','" + mysqlStatus
				+ "')) and ISDESCENDANTNODE('/DataBase_new/Status/')";
		Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
		QueryResult queryResult = query.execute();
		NodeIterator iterator = queryResult.getNodes();
		while (iterator.hasNext()) {

			obj = iterator.nextNode();
			vessel_id = obj.getProperty("id").getString();

		}

	} catch (Exception e) {
		return e.getMessage();
	}
	return vessel_id;

}

public static String check_Owners(Session session,String mysqlOwners) {

	
	String vessel_id = null;

	try {

		Node obj = null;

		Workspace workspace = session.getWorkspace();

		String slingqery = "select [ownerName] from [nt:base] where (contains('ownerName','" + mysqlOwners
				+ "')) and ISDESCENDANTNODE('/DataBase_new/owners/')";
		Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
		QueryResult queryResult = query.execute();
		NodeIterator iterator = queryResult.getNodes();
		while (iterator.hasNext()) {

			obj = iterator.nextNode();
			vessel_id = obj.getProperty("id").getString();

		}

	} catch (Exception e) {
		return e.getMessage();
	}
	return vessel_id;

}


public static void scorpioOwner(Session session, PrintWriter out){
	
	try {
		
		Node scorpio=null;
		Node vessel=null;
		
		if(session.getRootNode().hasNode("DataBase_new")){
			scorpio = session.getRootNode().getNode("DataBase_new");
		}else{
			scorpio = session.getRootNode().addNode("DataBase_new");
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
//				vessel.setProperty("jcr:count", String.valueOf(0));
				session.save();
			}
			
			 vesselSubNode.setProperty("id", forid);
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



}
