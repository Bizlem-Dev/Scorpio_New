package com.anagha;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

public class ReadExcelData_new {

	public static void main(String[] args) {
		ReadExcelData_new red= new ReadExcelData_new();
		try {
			//String fp[]= {"D:\\Scorpio\\Scorpio- Inputs for the Design\\Scorpio- Inputs for the Design\\NDA\\CM.xls"}; //For Port
			String fp[]={"D:\\Scorpio\\Scorpio- Inputs for the Design\\Scorpio- Inputs for the Design\\NDA\\port.xls"};
			JSONArray s=red.readExcel(fp);	
			ArrayList<String> list = new ArrayList<String>();     
			for(int i=0; i<s.length(); i++) {				
				if(!s.isNull(i) && !(s.get(i).toString()).equalsIgnoreCase("Null") && !(s.get(i).toString()).isEmpty()) {
					list.add(s.getString(i).replaceAll(" ", "_"));
					//list.add(fp[i]);
//					list.add(s.getString(i));
				}
			}
			System.out.println("list: "+list);
			
			for(int i=0;i<list.size();i++){
				String data=list.get(i);
				
                String RG="Port";
				//System.out.println("data:: "+data);
				
				
				JSONObject obj = new JSONObject();
				obj.put("Key", RG);
				obj.put("value", data);

				//System.out.println("obj: "+obj);

				String ps= postSolr(obj);

				System.out.println(ps);	
				
				
			}
			
			
			
			
			/*JSONObject obj = new JSONObject();
			obj.put("Data", list);
			obj.put("url", "http://34.73.112.165:8983/solr/#/AllReport/CargoGrade");
			obj.put("DocumentName", "CargoGrade");

			System.out.println("obj: "+obj);

			String ps= postSolr(obj);

			System.out.println(ps);*/	
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void chartererDataSlingInsert(PrintWriter out, Session session){
		
		try {
			
			Node scorpio=null;
			Node charterer=null;
			Node subNode=null;
			
			ReadExcelData_new red= new ReadExcelData_new();
			String fp[]= {"/home/ubuntu/ScorpioLog4J/cargograde.xls"}; //For Port
			JSONArray s=red.readExcel(fp);	
			ArrayList<String> list = new ArrayList<String>();     
			for(int i=0; i<s.length(); i++) {				
				if(!s.isNull(i) && !(s.get(i).toString()).equalsIgnoreCase("Null") && !(s.get(i).toString()).isEmpty()) {
					/*list.add(s.getString(i).replaceAll(" ", "_"));*/
					//list.add(fp[i]);
					list.add(s.getString(i));
				}
			}
			//System.out.println("list: "+list);
			
			for(int i=0;i<list.size();i++){
				String data=list.get(i);
				
				String nodePurpose=data.replaceAll(" ", "_");

		       //  out.println("nodePurpose:: "+nodePurpose);
				// out.println("property:: "+data);
				
				 if(session.getRootNode().hasNode("scorpioDataBase")){
						scorpio = session.getRootNode().getNode("scorpioDataBase");
					}
				 if(scorpio.hasNode("Cargo Grade")){
					 charterer=scorpio.getNode("Cargo Grade");
					}
				 
						 String count=charterer.getProperty("jcr:count").getString();
						 String forid= String.valueOf(Integer.parseInt(count)+1);
						 
						 
						 if(!charterer.hasNode(nodePurpose)){
							 subNode=charterer.addNode(nodePurpose);
							 
							 subNode.setProperty("cargoGradeId", forid);
							 subNode.setProperty("cargoGrade", data);
							 
							 charterer.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
							 session.save();
						 }
						
						 out.println(forid);
				
			} // for close
			
			
			
		} catch (Exception e) {
			
		}
	}

	public JSONArray readExcel(String[] fp) throws IOException {
		new JSONArray();
		InputStream ins;
		Set<String> hash_Set= null;
		JSONArray portArr= new JSONArray();
		String str="";
		String str1="";
		JSONObject jsonObj= new JSONObject();
		try {
			hash_Set = new HashSet<String>(); 

			for(int i=0; i<fp.length; i++) {
				System.out.println("fp: "+fp[i]);
				ins = new FileInputStream(new File(fp[i]));
				HSSFWorkbook workbook = new HSSFWorkbook(ins);//Check the code for getting file from server
				HSSFSheet sheet = workbook.getSheetAt(0);			

				Iterator<Row> rowIterator = sheet.iterator();
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();

					if(row.getRowNum()!=0) {
						Iterator<Cell> cellIterator = row.cellIterator();
						while (cellIterator.hasNext()) {
							Cell cell= cellIterator.next();

							if(cell.getColumnIndex()==0){	
								switch (cell.getCellType()) {
								case Cell.CELL_TYPE_STRING:
									//str= cell.getStringCellValue().replace(" ", "_");
									hash_Set.add(cell.getStringCellValue().replace(" ", "_"));
//									hash_Set.add(cell.getStringCellValue());
									break;
								case Cell.CELL_TYPE_NUMERIC:
									//str= cell.getStringCellValue().replace(" ", "_");
									hash_Set.add(String.valueOf(cell.getNumericCellValue()));
									break;
								}
							}else if(cell.getColumnIndex()==1) {
								switch (cell.getCellType()) {
								case Cell.CELL_TYPE_STRING:
									//str1= cell.getStringCellValue().replace(" ", "_");
									hash_Set.add(cell.getStringCellValue().replace(" ", "_"));
									break;
								case Cell.CELL_TYPE_NUMERIC:
									//str1= cell.getStringCellValue().replace(" ", "_");
									hash_Set.add(String.valueOf(cell.getNumericCellValue()));
									break;
								}
							}							
						}					
					}
				}
			}
			Iterator<String> dd= hash_Set.iterator();
			while (dd.hasNext()) {
				String celldata = dd.next();
				portArr.put(celldata);
			}
		} catch (Exception e) {
			System.out.println(" fdyysy  "+e.getMessage());
		}
		return portArr;
	}
	
	public static String postSolr(JSONObject obj) throws UnsupportedEncodingException {
		int responseCode = 0;
		String urlParameters = "";

		try {
		// String url1 =
		// "http://35.186.170.65:8983/solr/Vessel/update/json/docs?commit=true";
		//String url1 = "http://35.186.170.65:8983/solr/HSN_Code/update/json/docs?commit=true";
		/*String url1 = "http://34.73.112.165:8983/solr/AllReport/update/json/docs?commit=true";*/
			String url1 = "http://34.73.112.165:8983/solr/PortFound/update/json/docs?commit=true";

		URL url = new URL(url1);

		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");

		con.setRequestProperty("Content-Type", "application/json");

		//out.println("urlParameters:: " + obj.toString());
		con.setDoOutput(true);

		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		// urlParameters = urlParameters.replace(" ", "%20");
		wr.writeBytes(obj.toString());
		wr.flush();
		wr.close();

		responseCode = con.getResponseCode();

		BufferedReader in = new BufferedReader(
		new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
		response.append(inputLine);
		}
		in.close();

		//out.println(response.toString());
		System.out.println(response.toString());
		}
		catch (Exception e) {
		e.printStackTrace();
		// out.println(e.getMessage());
		}
		return String.valueOf(responseCode) + urlParameters;
		}
}
