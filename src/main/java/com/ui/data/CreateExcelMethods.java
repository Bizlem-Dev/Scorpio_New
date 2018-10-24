package com.ui.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

import javax.jcr.Session;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.readGmail.GmailMethods;
import com.readGmail.SlingMethods;



public class CreateExcelMethods {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
   String s="Fwd_Report_Generation_Project_Spot_Market_Reports_1";
     s= s.substring(0, s.lastIndexOf("_1"));
     System.out.println(s);
	}
	
	
	public static String createExcelTonnage(PrintWriter out, String fetchTonnageDaywiseData){
	     JSONObject final_obj=null;
		try {
		
			int rowIndex=0;
			// Create a Workbook
	         Workbook workbook = new HSSFWorkbook(); //XSSFWorkbook  new HSSFWorkbook() for generating `.xls` file
	         /* CreationHelper helps us create instances of various things like DataFormat, 
	            Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
	         CreationHelper createHelper = workbook.getCreationHelper();
	         
	         // Create a Sheet
	         Sheet sheet = workbook.createSheet("Tonnage-Summery");
	      // Create a Font for styling header cells
	         Font headerFont = workbook.createFont();
	         headerFont.setBold(true);
	         
	      // Create a CellStyle with the font
	         CellStyle headerCellStyle = workbook.createCellStyle();
	         headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
	         headerCellStyle.setWrapText(true);
	         headerCellStyle.setFont(headerFont);
	         
	         CellStyle hlinkstyle = workbook.createCellStyle();
	         Font hlinkfont = workbook.createFont();
	              hlinkfont.setUnderline(HSSFFont.U_SINGLE);
	              hlinkfont.setColor(HSSFColor.BLUE.index);
	             
	              hlinkstyle.setFont(hlinkfont);
	              
	           // Create Cell Style for formatting Date
	              CellStyle dateCellStyle = workbook.createCellStyle();
	              dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
	              
	              Row brokertcratedatarow = sheet.createRow((rowIndex));
			//String brokerfetchdata=SlingMethods.fetchBrokerTcRateDaywiseData(out, session);
	              String tonnagefetchdata=fetchTonnageDaywiseData;
//	              String brokerfetchdata="{\"BrokerTcRateList\":[{\"BrokerTcRateId\":\"6\",\"dateAndTime\":\"Sun Oct 08 18:56:35 UTC 2018\",\"BrokerJsonarray\":[{\"Broker\":\"f\"}],\"VesselTypeJsonarray\":[{\"VesselType\":\"\"}],\"Month_YearJsonarray\":[{\"Month_Year\":\"\"}],\"first_yrJsonarray\":[{\"first_yr\":\"\"}],\"second_yrJsonarray\":[{\"second_yr\":\"\"}],\"third_yrJsonarray\":[{\"third_yr\":\"\"}],\"fifth_yrJsonarray\":[{\"fifth_yr\":\"\"}],\"subNodeName\":\"6\",\"size\":8,\"parseId\":7},{\"BrokerTcRateId\":\"7\",\"dateAndTime\":\"Mon Oct 08 07:27:16 UTC 2018\",\"BrokerJsonarray\":[{\"Broker\":\"a\"}],\"VesselTypeJsonarray\":[{\"VesselType\":\"b\"}],\"Month_YearJsonarray\":[{\"Month_Year\":\"c\"}],\"first_yrJsonarray\":[{\"first_yr\":\"d\"}],\"second_yrJsonarray\":[{\"second_yr\":\"f\"}],\"third_yrJsonarray\":[{\"third_yr\":\"g\"}],\"fifth_yrJsonarray\":[{\"fifth_yr\":\"h\"}],\"subNodeName\":\"7\",\"size\":8,\"parseId\":8}]}";
			
			JSONObject Tonnagejsonobj=new JSONObject(tonnagefetchdata);
			
			
           Cell Broker = brokertcratedatarow.createCell(0);
                         Broker.setCellValue("EmploymentStatus");
                         Broker.setCellStyle(headerCellStyle);
			
           Cell vesseltype = brokertcratedatarow.createCell(1);
                             vesseltype.setCellValue("Owners");
                             vesseltype.setCellStyle(headerCellStyle);
                         
           Cell monthYear = brokertcratedatarow.createCell(2);
                             monthYear.setCellValue("OpenPort");
                             monthYear.setCellStyle(headerCellStyle);
                             
           Cell firstYear = brokertcratedatarow.createCell(3); 
                             firstYear.setCellValue("Operators");
                             firstYear.setCellStyle(headerCellStyle);
                             
           Cell secondYear = brokertcratedatarow.createCell(4); 
                             secondYear.setCellValue("CargoType");
                             secondYear.setCellStyle(headerCellStyle);
                             
           Cell thirdYear = brokertcratedatarow.createCell(5);
                             thirdYear.setCellValue("Comment");
                             thirdYear.setCellStyle(headerCellStyle);
                             
           Cell fifthYear = brokertcratedatarow.createCell(6); 
                             fifthYear.setCellValue("ETABasis");
                             fifthYear.setCellStyle(headerCellStyle);
			
                             Cell OpenDate = brokertcratedatarow.createCell(7); 
                             OpenDate.setCellValue("OpenDate");
                             OpenDate.setCellStyle(headerCellStyle);
                             
                             Cell ReportType = brokertcratedatarow.createCell(8); 
                             ReportType.setCellValue("ReportType");
                             ReportType.setCellStyle(headerCellStyle);
                             
                             Cell ReportTimestamp = brokertcratedatarow.createCell(9); 
                             ReportTimestamp.setCellValue("ReportTimestamp");
                             ReportTimestamp.setCellStyle(headerCellStyle);
                             
                             Cell RepositionRegion = brokertcratedatarow.createCell(10); 
                             RepositionRegion.setCellValue("RepositionRegion");
                             RepositionRegion.setCellStyle(headerCellStyle);
                             
                             Cell Source = brokertcratedatarow.createCell(11); 
                             Source.setCellValue("Source");
                             Source.setCellStyle(headerCellStyle);
                             
                             Cell VesselName = brokertcratedatarow.createCell(12); 
                             VesselName.setCellValue("VesselName");
                             VesselName.setCellStyle(headerCellStyle);
                             
                             Cell Built = brokertcratedatarow.createCell(13); 
                             Built.setCellValue("Built");
                             Built.setCellStyle(headerCellStyle);
                             
                             Cell DWT = brokertcratedatarow.createCell(14); 
                             DWT.setCellValue("DWT");
                             DWT.setCellStyle(headerCellStyle);
                             
                             Cell Cubics = brokertcratedatarow.createCell(15); 
                             Cubics.setCellValue("Cubics");
                             Cubics.setCellStyle(headerCellStyle);
                             
                             Cell LOA = brokertcratedatarow.createCell(16); 
                             LOA.setCellValue("LOA");
                             LOA.setCellStyle(headerCellStyle);
                             
                             Cell ICE = brokertcratedatarow.createCell(17); 
                             ICE.setCellValue("ICE");
                             ICE.setCellStyle(headerCellStyle);
                             
                             Cell SternLine = brokertcratedatarow.createCell(18); 
                             SternLine.setCellValue("SternLine");
                             SternLine.setCellStyle(headerCellStyle);
                             
                             Cell VesselType = brokertcratedatarow.createCell(19); 
                             VesselType.setCellValue("VesselType");
                             VesselType.setCellStyle(headerCellStyle);
                             
                             
			if(Tonnagejsonobj.has("tonnageList")){
				JSONArray tonnageList=Tonnagejsonobj.getJSONArray("tonnageList");
				
				JSONArray combinebrokerjsonarray=new JSONArray();
				JSONArray vesseltypearray=new JSONArray();
				JSONArray month_YearJsonarray=new JSONArray();
				JSONArray firstyrJsonarray=new JSONArray();
				JSONArray secondyrJsonarray=new JSONArray();
				JSONArray thirdyrJsonarray=new JSONArray();
				JSONArray fifthyrJsonarray=new JSONArray();
				
				JSONArray VesselTypearray=new JSONArray();
				JSONArray SternLinearray=new JSONArray();
				JSONArray ICEarray=new JSONArray();
				JSONArray LOAarray=new JSONArray();
				JSONArray Cubicsarray=new JSONArray();
				JSONArray DWTarray=new JSONArray();
				JSONArray Builtarray=new JSONArray();
				JSONArray VesselNamearray=new JSONArray();
				JSONArray sourceJsonarray=new JSONArray();
				JSONArray repositionRegionJsonarray=new JSONArray();
				JSONArray reporttimestampJsonarray=new JSONArray();
				JSONArray reporttypeJsonarray=new JSONArray();
				JSONArray opendateJsonarray=new JSONArray();
				
				
				for(int i=0;i<tonnageList.length();i++){
					
					JSONObject BrokerTcRateList_inside_jsonobj=tonnageList.getJSONObject(i);
					
					JSONArray BrokerJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("EmploymentStatusJsonarray")){
					    BrokerJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("EmploymentStatusJsonarray");
					    for(int j=0;j<BrokerJsonarray.length();j++){
					    	 JSONObject brokerjsonobj=null;
					         brokerjsonobj=BrokerJsonarray.getJSONObject(j);
					         String broker="";
					         if(brokerjsonobj.has("EmploymentStatus")){
						       broker=brokerjsonobj.getString("EmploymentStatus");
						       JSONObject combinebrokerobj=new JSONObject();
								 combinebrokerobj.put("EmploymentStatusobj", broker);
								 if(combinebrokerobj.length()>0){
								 combinebrokerjsonarray.put(combinebrokerobj);
								 }
					         }
						     
					    }
					}
					
					JSONArray VesselTypeJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("OwnersJsonarray")){
						VesselTypeJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("OwnersJsonarray");
						
						for(int j=0;j<VesselTypeJsonarray.length();j++){
					    	 JSONObject vesseltypejsonobj=null;
					    	 vesseltypejsonobj=VesselTypeJsonarray.getJSONObject(j);
					    	 String vesseltype_value="";
					    	 if(vesseltypejsonobj.has("Owners")){
						      vesseltype_value=vesseltypejsonobj.getString("Owners");
					    	 }
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("Ownersobj", vesseltype_value);
							 if(combinebrokerobj.length()>0){
							 vesseltypearray.put(combinebrokerobj);
							 }
					    }
						
					}
					
					JSONArray Month_YearJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("OpenPortJsonarray")){
						Month_YearJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("OpenPortJsonarray");
						
						for(int j=0;j<Month_YearJsonarray.length();j++){
					    	 JSONObject Month_Yearjsonobj=null;
					    	 Month_Yearjsonobj=Month_YearJsonarray.getJSONObject(j);
					    	 String Month_Year_value="";
					    	 if(Month_Yearjsonobj.has("OpenPort")){
						        Month_Year_value=Month_Yearjsonobj.getString("OpenPort");
					    	 }
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("OpenPortobj", Month_Year_value);
							 if(combinebrokerobj.length()>0){
							     month_YearJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					
					JSONArray first_yrJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("OperatorsJsonarray")){
						first_yrJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("OperatorsJsonarray");
						
						for(int j=0;j<first_yrJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=first_yrJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("Operators")){
						       firstyr_value=first_yrjsonobj.getString("Operators");
					    	 }
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("Operatorsobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
                            firstyrJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray second_yrJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("CargoTypeJsonarray")){
						second_yrJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("CargoTypeJsonarray");
						
						for(int j=0;j<second_yrJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=second_yrJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("CargoType")){
						       firstyr_value=first_yrjsonobj.getString("CargoType");
					    	 }
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("CargoTypeobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							   secondyrJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					
					JSONArray third_yrJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("CommentJsonarray")){
						third_yrJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("CommentJsonarray");
						
						for(int j=0;j<third_yrJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=third_yrJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("Comment")){
						        firstyr_value=first_yrjsonobj.getString("Comment");
					    	 }
						     
						        JSONObject combinebrokerobj=new JSONObject();
							     combinebrokerobj.put("Commentobj", firstyr_value);
							     if(combinebrokerobj.length()>0){
							        thirdyrJsonarray.put(combinebrokerobj);
							     }
					    }
					}
					
					JSONArray fifth_yrJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("ETABasisJsonarray")){
						fifth_yrJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("ETABasisJsonarray");
						
						for(int j=0;j<fifth_yrJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=fifth_yrJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("ETABasis")){
						        firstyr_value=first_yrjsonobj.getString("ETABasis");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("ETABasisobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 fifthyrJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray OpenDateJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("OpenDateJsonarray")){
						OpenDateJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("OpenDateJsonarray");
						
						for(int j=0;j<OpenDateJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=OpenDateJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("OpenDate")){
						       firstyr_value=first_yrjsonobj.getString("OpenDate");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("OpenDateobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							   opendateJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					
					JSONArray ReportTypeJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("ReportTypeJsonarray")){
						ReportTypeJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("ReportTypeJsonarray");
						
						for(int j=0;j<ReportTypeJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=ReportTypeJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("ReportType")){
						        firstyr_value=first_yrjsonobj.getString("ReportType");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("ReportTypeobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 reporttypeJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					
					JSONArray ReportTimestampJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("ReportTimestampJsonarray")){
						ReportTimestampJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("ReportTimestampJsonarray");
						
						for(int j=0;j<ReportTimestampJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=ReportTimestampJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("ReportType")){
						        firstyr_value=first_yrjsonobj.getString("ReportTimestamp");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("ReportTimestampobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							   reporttimestampJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray RepositionRegionJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("RepositionRegionJsonarray")){
						RepositionRegionJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("RepositionRegionJsonarray");
						
						for(int j=0;j<RepositionRegionJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=RepositionRegionJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("RepositionRegion")){
						        firstyr_value=first_yrjsonobj.getString("RepositionRegion");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("RepositionRegionobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							    repositionRegionJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray SourceJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("SourceJsonarray")){
						SourceJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("SourceJsonarray");
						
						for(int j=0;j<SourceJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=SourceJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("Source")){
						        firstyr_value=first_yrjsonobj.getString("Source");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("Sourceobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							    sourceJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray VesselNameJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("VesselNameJsonarray")){
						VesselNameJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("VesselNameJsonarray");
						
						for(int j=0;j<VesselNameJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=VesselNameJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("VesselName")){
						        firstyr_value=first_yrjsonobj.getString("VesselName");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("VesselNameobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							     VesselNamearray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray BuiltJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("BuiltJsonarray")){
						BuiltJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("BuiltJsonarray");
						
						for(int j=0;j<BuiltJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=BuiltJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("Built")){
						         firstyr_value=first_yrjsonobj.getString("Built");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("Builtobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							    Builtarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray DWTJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("DWTJsonarray")){
						DWTJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("DWTJsonarray");
						
						for(int j=0;j<DWTJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=DWTJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("DWT")){
						         firstyr_value=first_yrjsonobj.getString("DWT");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("DWTobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							    DWTarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray CubicsJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("CubicsJsonarray")){
						CubicsJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("CubicsJsonarray");
						
						for(int j=0;j<CubicsJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=CubicsJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("Cubics")){
						       firstyr_value=first_yrjsonobj.getString("Cubics");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("Cubicsobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 Cubicsarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray LOAJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("LOAJsonarray")){
						LOAJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("LOAJsonarray");
						
						for(int j=0;j<LOAJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=LOAJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("LOA")){
						      firstyr_value=first_yrjsonobj.getString("LOA");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("LOAobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 LOAarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray ICEJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("ICEJsonarray")){
						ICEJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("ICEJsonarray");
						
						for(int j=0;j<ICEJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=ICEJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("ICE")){
						      firstyr_value=first_yrjsonobj.getString("ICE");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("ICEobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 ICEarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray SternLinejsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("SternLinejsonarray")){
						SternLinejsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("SternLinejsonarray");
						
						for(int j=0;j<SternLinejsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=SternLinejsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("SternLine")){
						      firstyr_value=first_yrjsonobj.getString("SternLine");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("SternLineobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 SternLinearray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray VesselTypejsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("VesselTypejsonarray")){
						VesselTypejsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("VesselTypejsonarray");
						
						for(int j=0;j<VesselTypejsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=VesselTypejsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("VesselType")){
						      firstyr_value=first_yrjsonobj.getString("VesselType");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("VesselTypeeobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 VesselTypearray.put(combinebrokerobj);
							 }
					    }
					}
					
					
					
				} // for close main
				
				
				if(combinebrokerjsonarray.length()>0){
				for(int j=0;j<combinebrokerjsonarray.length();j++){
					JSONObject brokercombineobj=combinebrokerjsonarray.getJSONObject(j);
					String brokerobj="";
					if(brokercombineobj.has("EmploymentStatusobj")){
						brokerobj=brokercombineobj.getString("EmploymentStatusobj");
					}
					
					 Row brokerrow = sheet.createRow((rowIndex+1+j));
					 
					 Cell broker_value = brokerrow.createCell(0);
					      broker_value.setCellValue(brokerobj);
					                     
					   
				 }// for broker close
				}
		         
				if(vesseltypearray.length()>0){
				for(int k=0;k<vesseltypearray.length();k++){
					JSONObject vesseltypecombineobj=vesseltypearray.getJSONObject(k);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("Ownersobj")){
						vesseltypeobj=vesseltypecombineobj.getString("Ownersobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+k));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+k));
					}
					 
					 Cell broker_value = brokerrow.createCell(1);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close vesseltype
				}
				
				if(month_YearJsonarray.length()>0){
				for(int m=0;m<month_YearJsonarray.length();m++){
					JSONObject vesseltypecombineobj=month_YearJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("OpenPortobj")){
						vesseltypeobj=vesseltypecombineobj.getString("OpenPortobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(2);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(firstyrJsonarray.length()>0){
				for(int m=0;m<firstyrJsonarray.length();m++){
					JSONObject vesseltypecombineobj=firstyrJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("Operatorsobj")){
						vesseltypeobj=vesseltypecombineobj.getString("Operatorsobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(3);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(secondyrJsonarray.length()>0){
				for(int m=0;m<secondyrJsonarray.length();m++){
					JSONObject vesseltypecombineobj=secondyrJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("CargoTypeobj")){
						vesseltypeobj=vesseltypecombineobj.getString("CargoTypeobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(4);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(thirdyrJsonarray.length()>0){
				for(int m=0;m<thirdyrJsonarray.length();m++){
					JSONObject vesseltypecombineobj=thirdyrJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("Commentobj")){
						vesseltypeobj=vesseltypecombineobj.getString("Commentobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(5);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(fifthyrJsonarray.length()>0){
				for(int m=0;m<fifthyrJsonarray.length();m++){
					JSONObject vesseltypecombineobj=fifthyrJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("ETABasisobj")){
						vesseltypeobj=vesseltypecombineobj.getString("ETABasisobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(6);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(opendateJsonarray.length()>0){
				for(int m=0;m<opendateJsonarray.length();m++){
					JSONObject vesseltypecombineobj=opendateJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("OpenDateobj")){
						vesseltypeobj=vesseltypecombineobj.getString("OpenDateobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(7);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				
				}
				
				if(reporttypeJsonarray.length()>0){
				for(int m=0;m<reporttypeJsonarray.length();m++){
					JSONObject vesseltypecombineobj=reporttypeJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("ReportTypeobj")){
						vesseltypeobj=vesseltypecombineobj.getString("ReportTypeobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(8);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(reporttimestampJsonarray.length()>0){
				for(int m=0;m<reporttimestampJsonarray.length();m++){
					JSONObject vesseltypecombineobj=reporttimestampJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("ReportTimestampobj")){
						vesseltypeobj=vesseltypecombineobj.getString("ReportTimestampobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(9);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				
				if(repositionRegionJsonarray.length()>0){
				for(int m=0;m<repositionRegionJsonarray.length();m++){
					JSONObject vesseltypecombineobj=repositionRegionJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("RepositionRegionobj")){
						vesseltypeobj=vesseltypecombineobj.getString("RepositionRegionobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(10);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(sourceJsonarray.length()>0){
				for(int m=0;m<sourceJsonarray.length();m++){
					JSONObject vesseltypecombineobj=sourceJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("Sourceobj")){
						vesseltypeobj=vesseltypecombineobj.getString("Sourceobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(11);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(VesselNamearray.length()>0){
				for(int m=0;m<VesselNamearray.length();m++){
					JSONObject vesseltypecombineobj=VesselNamearray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("VesselNameobj")){
						vesseltypeobj=vesseltypecombineobj.getString("VesselNameobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(12);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(Builtarray.length()>0){
				for(int m=0;m<Builtarray.length();m++){
					JSONObject vesseltypecombineobj=Builtarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("Builtobj")){
						vesseltypeobj=vesseltypecombineobj.getString("Builtobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(13);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(DWTarray.length()>0){
				for(int m=0;m<DWTarray.length();m++){
					JSONObject vesseltypecombineobj=DWTarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("DWTobj")){
						vesseltypeobj=vesseltypecombineobj.getString("DWTobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(14);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(Cubicsarray.length()>0){
				for(int m=0;m<Cubicsarray.length();m++){
					JSONObject vesseltypecombineobj=Cubicsarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("Cubicsobj")){
						vesseltypeobj=vesseltypecombineobj.getString("Cubicsobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(15);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(LOAarray.length()>0){
				for(int m=0;m<LOAarray.length();m++){
					JSONObject vesseltypecombineobj=LOAarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("LOAobj")){
						vesseltypeobj=vesseltypecombineobj.getString("LOAobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(16);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(ICEarray.length()>0){
				for(int m=0;m<ICEarray.length();m++){
					JSONObject vesseltypecombineobj=ICEarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("ICEobj")){
						vesseltypeobj=vesseltypecombineobj.getString("ICEobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(17);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(SternLinearray.length()>0){
				for(int m=0;m<SternLinearray.length();m++){
					JSONObject vesseltypecombineobj=SternLinearray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("SternLineobj")){
						vesseltypeobj=vesseltypecombineobj.getString("SternLineobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(18);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(VesselTypearray.length()>0){
				for(int m=0;m<VesselTypearray.length();m++){
					JSONObject vesseltypecombineobj=VesselTypearray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("VesselTypeeobj")){
						vesseltypeobj=vesseltypecombineobj.getString("VesselTypeeobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(19);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
			}
			
			Calendar cal = null;
			cal = Calendar.getInstance();
			Date minDate = new Date(cal.getTimeInMillis());
			
			JSONObject dateJson=new JSONObject();
			Date date=GmailMethods.parseDate(minDate.toString());
			dateJson=SlingMethods.formatDate_Month_Year_Day(date, out);
			
			int day=dateJson.getInt("day");
			int month=dateJson.getInt("month");
			int year=dateJson.getInt("year");
			
			 String fileName="Tonnage"+"Data"+"_"+day+"-"+month+"-"+year+".xls";
		     fileName=fileName.replace(" ", "-");
		     
		     File linuxdirectory=new File("/home/ubuntu/apache-tomcat-8.5.31/webapps/ROOT/scorpioexcel/");
			   if(!linuxdirectory.exists()){
				   
				 linuxdirectory.mkdir();
				 out.println("folder created in linux");
			   }
			
		  // Write the output to a file
//		        FileOutputStream fileOut = new FileOutputStream("D:\\Scorpio\\Email\\"+fileName.trim());
		        FileOutputStream fileOut = new FileOutputStream("/home/ubuntu/apache-tomcat-8.5.31/webapps/ROOT/scorpioexcel/"+fileName.trim());
		        workbook.write(fileOut);
		        fileOut.close();

		        // Closing the workbook
		        workbook.close();
		        
		         final_obj=new JSONObject();
		         final_obj.put("fileName", fileName.trim());
		 		 final_obj.put("fileUrl", "http://35.199.31.139:8080/scorpioexcel/"+fileName.trim());
		 		 final_obj.put("status", "S");
			
			
		} catch (Exception e) {
//			out.println(e.getMessage());
			return "{\"status\":\"E\",\"Message\" : "+e.getMessage()+"}";
		}
		return final_obj.toString();
	}
	
	
	public static String createExcelSpot(PrintWriter out, String fetchTonnageDaywiseData){
	     JSONObject final_obj=null;
		try {
		
			int rowIndex=0;
			// Create a Workbook
	         Workbook workbook = new HSSFWorkbook(); //XSSFWorkbook  new HSSFWorkbook() for generating `.xls` file
	         /* CreationHelper helps us create instances of various things like DataFormat, 
	            Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
	         CreationHelper createHelper = workbook.getCreationHelper();
	         
	         // Create a Sheet
	         Sheet sheet = workbook.createSheet("Spot-Summery");
	      // Create a Font for styling header cells
	         Font headerFont = workbook.createFont();
	         headerFont.setBold(true);
	         
	      // Create a CellStyle with the font
	         CellStyle headerCellStyle = workbook.createCellStyle();
	         headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
	         headerCellStyle.setWrapText(true);
	         headerCellStyle.setFont(headerFont);
	         
	         CellStyle hlinkstyle = workbook.createCellStyle();
	         Font hlinkfont = workbook.createFont();
	              hlinkfont.setUnderline(HSSFFont.U_SINGLE);
	              hlinkfont.setColor(HSSFColor.BLUE.index);
	             
	              hlinkstyle.setFont(hlinkfont);
	              
	           // Create Cell Style for formatting Date
	              CellStyle dateCellStyle = workbook.createCellStyle();
	              dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
	              
	              Row brokertcratedatarow = sheet.createRow((rowIndex));
			//String brokerfetchdata=SlingMethods.fetchBrokerTcRateDaywiseData(out, session);
	              String tonnagefetchdata=fetchTonnageDaywiseData;
//	              String brokerfetchdata="{\"BrokerTcRateList\":[{\"BrokerTcRateId\":\"6\",\"dateAndTime\":\"Sun Oct 08 18:56:35 UTC 2018\",\"BrokerJsonarray\":[{\"Broker\":\"f\"}],\"VesselTypeJsonarray\":[{\"VesselType\":\"\"}],\"Month_YearJsonarray\":[{\"Month_Year\":\"\"}],\"first_yrJsonarray\":[{\"first_yr\":\"\"}],\"second_yrJsonarray\":[{\"second_yr\":\"\"}],\"third_yrJsonarray\":[{\"third_yr\":\"\"}],\"fifth_yrJsonarray\":[{\"fifth_yr\":\"\"}],\"subNodeName\":\"6\",\"size\":8,\"parseId\":7},{\"BrokerTcRateId\":\"7\",\"dateAndTime\":\"Mon Oct 08 07:27:16 UTC 2018\",\"BrokerJsonarray\":[{\"Broker\":\"a\"}],\"VesselTypeJsonarray\":[{\"VesselType\":\"b\"}],\"Month_YearJsonarray\":[{\"Month_Year\":\"c\"}],\"first_yrJsonarray\":[{\"first_yr\":\"d\"}],\"second_yrJsonarray\":[{\"second_yr\":\"f\"}],\"third_yrJsonarray\":[{\"third_yr\":\"g\"}],\"fifth_yrJsonarray\":[{\"fifth_yr\":\"h\"}],\"subNodeName\":\"7\",\"size\":8,\"parseId\":8}]}";
			
			JSONObject Tonnagejsonobj=new JSONObject(tonnagefetchdata);
			
			
          Cell Broker = brokertcratedatarow.createCell(0);
                        Broker.setCellValue("Information");
                        Broker.setCellStyle(headerCellStyle);
			
          Cell vesseltype = brokertcratedatarow.createCell(1);
                            vesseltype.setCellValue("FixtureType");
                            vesseltype.setCellStyle(headerCellStyle);
                        
          Cell monthYear = brokertcratedatarow.createCell(2);
                            monthYear.setCellValue("LCStart");
                            monthYear.setCellStyle(headerCellStyle);
                            
          Cell firstYear = brokertcratedatarow.createCell(3); 
                            firstYear.setCellValue("LCEnd");
                            firstYear.setCellStyle(headerCellStyle);
                            
          Cell secondYear = brokertcratedatarow.createCell(4); 
                            secondYear.setCellValue("LoadPort");
                            secondYear.setCellStyle(headerCellStyle);
                            
          Cell thirdYear = brokertcratedatarow.createCell(5);
                            thirdYear.setCellValue("DiscPort");
                            thirdYear.setCellStyle(headerCellStyle);
                            
          Cell fifthYear = brokertcratedatarow.createCell(6); 
                            fifthYear.setCellValue("VesselName");
                            fifthYear.setCellStyle(headerCellStyle);
			
                            Cell OpenDate = brokertcratedatarow.createCell(7); 
                            OpenDate.setCellValue("Charterer");
                            OpenDate.setCellStyle(headerCellStyle);
                            
                            Cell ReportType = brokertcratedatarow.createCell(8); 
                            ReportType.setCellValue("Status");
                            ReportType.setCellStyle(headerCellStyle);
                            
                            Cell ReportTimestamp = brokertcratedatarow.createCell(9); 
                            ReportTimestamp.setCellValue("CargoType");
                            ReportTimestamp.setCellStyle(headerCellStyle);
                            
                            Cell RepositionRegion = brokertcratedatarow.createCell(10); 
                            RepositionRegion.setCellValue("CargoGrade");
                            RepositionRegion.setCellStyle(headerCellStyle);
                            
                            Cell Source = brokertcratedatarow.createCell(11); 
                            Source.setCellValue("CargoQty");
                            Source.setCellStyle(headerCellStyle);
                            
                            Cell VesselName = brokertcratedatarow.createCell(12); 
                            VesselName.setCellValue("RateType");
                            VesselName.setCellStyle(headerCellStyle);
                            
                            Cell Built = brokertcratedatarow.createCell(13); 
                            Built.setCellValue("Rate");
                            Built.setCellStyle(headerCellStyle);
                            
                            Cell DWT = brokertcratedatarow.createCell(14); 
                            DWT.setCellValue("Comments");
                            DWT.setCellStyle(headerCellStyle);
                            
                            Cell Cubics = brokertcratedatarow.createCell(15); 
                            Cubics.setCellValue("ReportDate");
                            Cubics.setCellStyle(headerCellStyle);
                            
                            Cell LOA = brokertcratedatarow.createCell(16); 
                            LOA.setCellValue("Source");
                            LOA.setCellStyle(headerCellStyle);
                            
                            Cell ICE = brokertcratedatarow.createCell(17); 
                            ICE.setCellValue("Vesseltype");
                            ICE.setCellStyle(headerCellStyle);
                            
                            
                            
			if(Tonnagejsonobj.has("spotList")){
				JSONArray tonnageList=Tonnagejsonobj.getJSONArray("spotList");
				
				JSONArray combinebrokerjsonarray=new JSONArray();
				JSONArray vesseltypearray=new JSONArray();
				JSONArray month_YearJsonarray=new JSONArray();
				JSONArray firstyrJsonarray=new JSONArray();
				JSONArray secondyrJsonarray=new JSONArray();
				JSONArray thirdyrJsonarray=new JSONArray();
				JSONArray fifthyrJsonarray=new JSONArray();
				
				
				JSONArray ICEarray=new JSONArray();
				JSONArray LOAarray=new JSONArray();
				JSONArray Cubicsarray=new JSONArray();
				JSONArray DWTarray=new JSONArray();
				JSONArray Builtarray=new JSONArray();
				JSONArray VesselNamearray=new JSONArray();
				JSONArray sourceJsonarray=new JSONArray();
				JSONArray repositionRegionJsonarray=new JSONArray();
				JSONArray reporttimestampJsonarray=new JSONArray();
				JSONArray reporttypeJsonarray=new JSONArray();
				JSONArray opendateJsonarray=new JSONArray();
				
				
				for(int i=0;i<tonnageList.length();i++){
					
					JSONObject BrokerTcRateList_inside_jsonobj=tonnageList.getJSONObject(i);
					
					JSONArray BrokerJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("InformationJsonarray")){
					    BrokerJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("InformationJsonarray");
					    for(int j=0;j<BrokerJsonarray.length();j++){
					    	 JSONObject brokerjsonobj=null;
					         brokerjsonobj=BrokerJsonarray.getJSONObject(j);
					         String broker="";
					         if(brokerjsonobj.has("Information")){
						      broker=brokerjsonobj.getString("Information");
					         }
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("Informationobj", broker);
							 if(combinebrokerobj.length()>0){
								 combinebrokerjsonarray.put(combinebrokerobj);
							 }
							 
					    }
					}
					
					JSONArray VesselTypeJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("FixtureTypeJsonarray")){
						VesselTypeJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("FixtureTypeJsonarray");
						
						for(int j=0;j<VesselTypeJsonarray.length();j++){
					    	 JSONObject vesseltypejsonobj=null;
					    	 vesseltypejsonobj=VesselTypeJsonarray.getJSONObject(j);
					    	 String vesseltype_value="";
					    	 if(vesseltypejsonobj.has("FixtureType")){
						      vesseltype_value=vesseltypejsonobj.getString("FixtureType");
					    	 }
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("FixtureTypeobj", vesseltype_value);
							 if(combinebrokerobj.length()>0){
							 vesseltypearray.put(combinebrokerobj);
							 }
					    }
						
					}
					
					JSONArray Month_YearJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("LCStartJsonarray")){
						Month_YearJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("LCStartJsonarray");
						
						for(int j=0;j<Month_YearJsonarray.length();j++){
					    	 JSONObject Month_Yearjsonobj=null;
					    	 Month_Yearjsonobj=Month_YearJsonarray.getJSONObject(j);
					    	 String Month_Year_value="";
					    	 if(Month_Yearjsonobj.has("LCStart")){
						        Month_Year_value=Month_Yearjsonobj.getString("LCStart");
					    	 }
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("LCStartobj", Month_Year_value);
							 if(combinebrokerobj.length()>0){
							 month_YearJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					
					JSONArray first_yrJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("LCEndJsonarray")){
						first_yrJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("LCEndJsonarray");
						
						for(int j=0;j<first_yrJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=first_yrJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("LCEnd")){
						        firstyr_value=first_yrjsonobj.getString("LCEnd");
					    	 }
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("LCEndobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
                              firstyrJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray second_yrJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("LoadPortJsonarray")){
						second_yrJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("LoadPortJsonarray");
						
						for(int j=0;j<second_yrJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=second_yrJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("LoadPort")){
						        firstyr_value=first_yrjsonobj.getString("LoadPort");
					    	 }
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("LoadPortobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 secondyrJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					
					JSONArray third_yrJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("DiscPortJsonarray")){
						third_yrJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("DiscPortJsonarray");
						
						for(int j=0;j<third_yrJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=third_yrJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("DiscPort")){
						      firstyr_value=first_yrjsonobj.getString("DiscPort");
					    	 }
						     
						        JSONObject combinebrokerobj=new JSONObject();
							     combinebrokerobj.put("DiscPortobj", firstyr_value);
							     if(combinebrokerobj.length()>0){
							     thirdyrJsonarray.put(combinebrokerobj);
							     }
					    }
					}
					
					JSONArray fifth_yrJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("VesselNameJsonarray")){
						fifth_yrJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("VesselNameJsonarray");
						
						for(int j=0;j<fifth_yrJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=fifth_yrJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("VesselName")){
						      firstyr_value=first_yrjsonobj.getString("VesselName");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("VesselNameobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 fifthyrJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray OpenDateJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("ChartererJsonarray")){
						OpenDateJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("ChartererJsonarray");
						
						for(int j=0;j<OpenDateJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=OpenDateJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("Charterer")){
						        firstyr_value=first_yrjsonobj.getString("Charterer");
					    	 }
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("Chartererobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 opendateJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					
					JSONArray ReportTypeJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("StatusJsonarray")){
						ReportTypeJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("StatusJsonarray");
						
						for(int j=0;j<ReportTypeJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=ReportTypeJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("Status")){
						        firstyr_value=first_yrjsonobj.getString("Status");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("Statusobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 reporttypeJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					
					JSONArray ReportTimestampJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("CargoTypeJsonarray")){
						ReportTimestampJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("CargoTypeJsonarray");
						
						for(int j=0;j<ReportTimestampJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=ReportTimestampJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("CargoType")){
						      firstyr_value=first_yrjsonobj.getString("CargoType");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("CargoTypeobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 reporttimestampJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray RepositionRegionJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("CargoGradeJsonarray")){
						RepositionRegionJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("CargoGradeJsonarray");
						
						for(int j=0;j<RepositionRegionJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=RepositionRegionJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("CargoGrade")){
						      firstyr_value=first_yrjsonobj.getString("CargoGrade");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("CargoGradeobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 repositionRegionJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray SourceJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("CargoQtyJsonarray")){
						SourceJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("CargoQtyJsonarray");
						
						for(int j=0;j<SourceJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=SourceJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("CargoQty")){
						      firstyr_value=first_yrjsonobj.getString("CargoQty");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("CargoQtyobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 sourceJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray VesselNameJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("RateTypeJsonarray")){
						VesselNameJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("RateTypeJsonarray");
						
						for(int j=0;j<VesselNameJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=VesselNameJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("RateType")){
						      firstyr_value=first_yrjsonobj.getString("RateType");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("RateTypeobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 VesselNamearray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray BuiltJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("RateJsonarray")){
						BuiltJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("RateJsonarray");
						
						for(int j=0;j<BuiltJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=BuiltJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("Rate")){
						      firstyr_value=first_yrjsonobj.getString("Rate");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("Rateobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 Builtarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray DWTJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("CommentsJsonarray")){
						DWTJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("CommentsJsonarray");
						
						for(int j=0;j<DWTJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=DWTJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("Comments")){
						      firstyr_value=first_yrjsonobj.getString("Comments");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("Commentsobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 DWTarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray CubicsJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("ReportDateJsonarray")){
						CubicsJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("ReportDateJsonarray");
						
						for(int j=0;j<CubicsJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=CubicsJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("ReportDate")){
						      firstyr_value=first_yrjsonobj.getString("ReportDate");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("ReportDateobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 Cubicsarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray LOAJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("SourceJsonarray")){
						LOAJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("SourceJsonarray");
						
						for(int j=0;j<LOAJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=LOAJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("Source")){
						      firstyr_value=first_yrjsonobj.getString("Source");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("Sourceobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 LOAarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray ICEJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("VesseltypeJsonarray")){
						ICEJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("VesseltypeJsonarray");
						
						for(int j=0;j<ICEJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=ICEJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("Vesseltype")){
						        firstyr_value=first_yrjsonobj.getString("Vesseltype");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("Vesseltypeobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 ICEarray.put(combinebrokerobj);
							 }
					    }
					}
					
					
				} // for close main
				
				
				if(combinebrokerjsonarray.length()>0){
				for(int j=0;j<combinebrokerjsonarray.length();j++){
					JSONObject brokercombineobj=combinebrokerjsonarray.getJSONObject(j);
					String brokerobj="";
					if(brokercombineobj.has("Informationobj")){
						brokerobj=brokercombineobj.getString("Informationobj");
					}
					
					 Row brokerrow = sheet.createRow((rowIndex+1+j));
					 
					 Cell broker_value = brokerrow.createCell(0);
					      broker_value.setCellValue(brokerobj);
					                     
					   
				 }// for broker close
				}
				
				if(vesseltypearray.length()>0){
				for(int k=0;k<vesseltypearray.length();k++){
					JSONObject vesseltypecombineobj=vesseltypearray.getJSONObject(k);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("FixtureTypeobj")){
						vesseltypeobj=vesseltypecombineobj.getString("FixtureTypeobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+k));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+k));
					}
					 
					 Cell broker_value = brokerrow.createCell(1);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close vesseltype
				}
				
				if(month_YearJsonarray.length()>0){
				for(int m=0;m<month_YearJsonarray.length();m++){
					JSONObject vesseltypecombineobj=month_YearJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("LCStartobj")){
						vesseltypeobj=vesseltypecombineobj.getString("LCStartobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(2);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(firstyrJsonarray.length()>0){
				for(int m=0;m<firstyrJsonarray.length();m++){
					JSONObject vesseltypecombineobj=firstyrJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("LCEndobj")){
						vesseltypeobj=vesseltypecombineobj.getString("LCEndobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(3);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(secondyrJsonarray.length()>0){
				for(int m=0;m<secondyrJsonarray.length();m++){
					JSONObject vesseltypecombineobj=secondyrJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("LoadPortobj")){
						vesseltypeobj=vesseltypecombineobj.getString("LoadPortobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(4);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(thirdyrJsonarray.length()>0){
				for(int m=0;m<thirdyrJsonarray.length();m++){
					JSONObject vesseltypecombineobj=thirdyrJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("DiscPortobj")){
						vesseltypeobj=vesseltypecombineobj.getString("DiscPortobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(5);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(fifthyrJsonarray.length()>0){
				for(int m=0;m<fifthyrJsonarray.length();m++){
					JSONObject vesseltypecombineobj=fifthyrJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("VesselNameobj")){
						vesseltypeobj=vesseltypecombineobj.getString("VesselNameobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(6);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(opendateJsonarray.length()>0){
				for(int m=0;m<opendateJsonarray.length();m++){
					JSONObject vesseltypecombineobj=opendateJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("Chartererobj")){
						vesseltypeobj=vesseltypecombineobj.getString("Chartererobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(7);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(reporttypeJsonarray.length()>0){
				for(int m=0;m<reporttypeJsonarray.length();m++){
					JSONObject vesseltypecombineobj=reporttypeJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("Statusobj")){
						vesseltypeobj=vesseltypecombineobj.getString("Statusobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(8);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				if(reporttimestampJsonarray.length()>0){
				for(int m=0;m<reporttimestampJsonarray.length();m++){
					JSONObject vesseltypecombineobj=reporttimestampJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("CargoTypeobj")){
						vesseltypeobj=vesseltypecombineobj.getString("CargoTypeobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(9);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				if(repositionRegionJsonarray.length()>0){
				for(int m=0;m<repositionRegionJsonarray.length();m++){
					JSONObject vesseltypecombineobj=repositionRegionJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("CargoGradeobj")){
						vesseltypeobj=vesseltypecombineobj.getString("CargoGradeobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(10);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(sourceJsonarray.length()>0){
				for(int m=0;m<sourceJsonarray.length();m++){
					JSONObject vesseltypecombineobj=sourceJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("CargoQtyobj")){
						vesseltypeobj=vesseltypecombineobj.getString("CargoQtyobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(11);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(VesselNamearray.length()>0){
				for(int m=0;m<VesselNamearray.length();m++){
					JSONObject vesseltypecombineobj=VesselNamearray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("RateTypeobj")){
						vesseltypeobj=vesseltypecombineobj.getString("RateTypeobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(12);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				if(Builtarray.length()>0){
				for(int m=0;m<Builtarray.length();m++){
					JSONObject vesseltypecombineobj=Builtarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("Rateobj")){
						vesseltypeobj=vesseltypecombineobj.getString("Rateobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(13);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(DWTarray.length()>0){
				for(int m=0;m<DWTarray.length();m++){
					JSONObject vesseltypecombineobj=DWTarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("Commentsobj")){
						vesseltypeobj=vesseltypecombineobj.getString("Commentsobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(14);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				if(Cubicsarray.length()>0){
				for(int m=0;m<Cubicsarray.length();m++){
					JSONObject vesseltypecombineobj=Cubicsarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("ReportDateobj")){
						vesseltypeobj=vesseltypecombineobj.getString("ReportDateobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(15);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(LOAarray.length()>0){
				for(int m=0;m<LOAarray.length();m++){
					JSONObject vesseltypecombineobj=LOAarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("Sourceobj")){
						vesseltypeobj=vesseltypecombineobj.getString("Sourceobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(16);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(ICEarray.length()>0){
				for(int m=0;m<ICEarray.length();m++){
					JSONObject vesseltypecombineobj=ICEarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("Vesseltypeobj")){
						vesseltypeobj=vesseltypecombineobj.getString("Vesseltypeobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(17);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				
			}
			
			Calendar cal = null;
			cal = Calendar.getInstance();
			Date minDate = new Date(cal.getTimeInMillis());
			
			JSONObject dateJson=new JSONObject();
			Date date=GmailMethods.parseDate(minDate.toString());
			dateJson=SlingMethods.formatDate_Month_Year_Day(date, out);
			
			int day=dateJson.getInt("day");
			int month=dateJson.getInt("month");
			int year=dateJson.getInt("year");
			
			 String fileName="Spot"+"Data"+"_"+day+"-"+month+"-"+year+".xls";
		     fileName=fileName.replace(" ", "-");
		     
		     File linuxdirectory=new File("/home/ubuntu/apache-tomcat-8.5.31/webapps/ROOT/scorpioexcel/");
			   if(!linuxdirectory.exists()){
				   
				 linuxdirectory.mkdir();
				 out.println("folder created in linux");
			   }
			
		  // Write the output to a file
//		        FileOutputStream fileOut = new FileOutputStream("D:\\Scorpio\\Email\\"+fileName.trim());
		        FileOutputStream fileOut = new FileOutputStream("/home/ubuntu/apache-tomcat-8.5.31/webapps/ROOT/scorpioexcel/"+fileName.trim());
		        workbook.write(fileOut);
		        fileOut.close();

		        // Closing the workbook
		        workbook.close();
		        
		         final_obj=new JSONObject();
		         final_obj.put("fileName", fileName.trim());
		 		 final_obj.put("fileUrl", "http://35.199.31.139:8080/scorpioexcel/"+fileName.trim());
		 		 final_obj.put("status", "S");
			
			
		} catch (Exception e) {
//			out.println(e.getMessage());
			return "{\"status\":\"E\",\"Message\" : "+e.getMessage()+"}";
		}
		return final_obj.toString();
	}
	
	public static String createExcelTimeCharterReports(PrintWriter out, String fetchTonnageDaywiseData){
	     JSONObject final_obj=null;
		try {
		
			int rowIndex=0;
			// Create a Workbook
	         Workbook workbook = new HSSFWorkbook(); //XSSFWorkbook  new HSSFWorkbook() for generating `.xls` file
	         /* CreationHelper helps us create instances of various things like DataFormat, 
	            Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
	         CreationHelper createHelper = workbook.getCreationHelper();
	         
	         // Create a Sheet
	         Sheet sheet = workbook.createSheet("TimeCharterReports-Summery");
	      // Create a Font for styling header cells
	         Font headerFont = workbook.createFont();
	         headerFont.setBold(true);
	         
	      // Create a CellStyle with the font
	         CellStyle headerCellStyle = workbook.createCellStyle();
	         headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
	         headerCellStyle.setWrapText(true);
	         headerCellStyle.setFont(headerFont);
	         
	         CellStyle hlinkstyle = workbook.createCellStyle();
	         Font hlinkfont = workbook.createFont();
	              hlinkfont.setUnderline(HSSFFont.U_SINGLE);
	              hlinkfont.setColor(HSSFColor.BLUE.index);
	             
	              hlinkstyle.setFont(hlinkfont);
	              
	           // Create Cell Style for formatting Date
	              CellStyle dateCellStyle = workbook.createCellStyle();
	              dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
	              
	              Row brokertcratedatarow = sheet.createRow((rowIndex));
			//String brokerfetchdata=SlingMethods.fetchBrokerTcRateDaywiseData(out, session);
	              String tonnagefetchdata=fetchTonnageDaywiseData;
//	              String brokerfetchdata="{\"BrokerTcRateList\":[{\"BrokerTcRateId\":\"6\",\"dateAndTime\":\"Sun Oct 08 18:56:35 UTC 2018\",\"BrokerJsonarray\":[{\"Broker\":\"f\"}],\"VesselTypeJsonarray\":[{\"VesselType\":\"\"}],\"Month_YearJsonarray\":[{\"Month_Year\":\"\"}],\"first_yrJsonarray\":[{\"first_yr\":\"\"}],\"second_yrJsonarray\":[{\"second_yr\":\"\"}],\"third_yrJsonarray\":[{\"third_yr\":\"\"}],\"fifth_yrJsonarray\":[{\"fifth_yr\":\"\"}],\"subNodeName\":\"6\",\"size\":8,\"parseId\":7},{\"BrokerTcRateId\":\"7\",\"dateAndTime\":\"Mon Oct 08 07:27:16 UTC 2018\",\"BrokerJsonarray\":[{\"Broker\":\"a\"}],\"VesselTypeJsonarray\":[{\"VesselType\":\"b\"}],\"Month_YearJsonarray\":[{\"Month_Year\":\"c\"}],\"first_yrJsonarray\":[{\"first_yr\":\"d\"}],\"second_yrJsonarray\":[{\"second_yr\":\"f\"}],\"third_yrJsonarray\":[{\"third_yr\":\"g\"}],\"fifth_yrJsonarray\":[{\"fifth_yr\":\"h\"}],\"subNodeName\":\"7\",\"size\":8,\"parseId\":8}]}";
			
			JSONObject Tonnagejsonobj=new JSONObject(tonnagefetchdata);
			
			
         Cell Broker = brokertcratedatarow.createCell(0);
                       Broker.setCellValue("Information");
                       Broker.setCellStyle(headerCellStyle);
			
         Cell vesseltype = brokertcratedatarow.createCell(1);
                           vesseltype.setCellValue("Spot_TC");
                           vesseltype.setCellStyle(headerCellStyle);
                       
         Cell monthYear = brokertcratedatarow.createCell(2);
                           monthYear.setCellValue("Date");
                           monthYear.setCellStyle(headerCellStyle);
                           
         Cell firstYear = brokertcratedatarow.createCell(3); 
                           firstYear.setCellValue("VesselName");
                           firstYear.setCellStyle(headerCellStyle);
                           
         Cell secondYear = brokertcratedatarow.createCell(4); 
                           secondYear.setCellValue("CPP_DPP");
                           secondYear.setCellStyle(headerCellStyle);
                           
         Cell thirdYear = brokertcratedatarow.createCell(5);
                           thirdYear.setCellValue("Charterer");
                           thirdYear.setCellStyle(headerCellStyle);
                           
         Cell fifthYear = brokertcratedatarow.createCell(6); 
                           fifthYear.setCellValue("Delivery");
                           fifthYear.setCellStyle(headerCellStyle);
			
                           Cell OpenDate = brokertcratedatarow.createCell(7); 
                           OpenDate.setCellValue("DeliveryPlace");
                           OpenDate.setCellStyle(headerCellStyle);
                           
                           Cell ReportType = brokertcratedatarow.createCell(8); 
                           ReportType.setCellValue("Period");
                           ReportType.setCellStyle(headerCellStyle);
                           
                           Cell ReportTimestamp = brokertcratedatarow.createCell(9); 
                           ReportTimestamp.setCellValue("Periodunit");
                           ReportTimestamp.setCellStyle(headerCellStyle);
                           
                           Cell RepositionRegion = brokertcratedatarow.createCell(10); 
                           RepositionRegion.setCellValue("Redelivery");
                           RepositionRegion.setCellStyle(headerCellStyle);
                           
                           Cell Source = brokertcratedatarow.createCell(11); 
                           Source.setCellValue("TCRate");
                           Source.setCellStyle(headerCellStyle);
                           
                           Cell VesselName = brokertcratedatarow.createCell(12); 
                           VesselName.setCellValue("Rate");
                           VesselName.setCellStyle(headerCellStyle);
                           
                           Cell Built = brokertcratedatarow.createCell(13); 
                           Built.setCellValue("Unit");
                           Built.setCellStyle(headerCellStyle);
                           
                           Cell DWT = brokertcratedatarow.createCell(14); 
                           DWT.setCellValue("Comments");
                           DWT.setCellStyle(headerCellStyle);
                           
                           Cell Cubics = brokertcratedatarow.createCell(15); 
                           Cubics.setCellValue("Source");
                           Cubics.setCellStyle(headerCellStyle);
                           
                           Cell LOA = brokertcratedatarow.createCell(16); 
                           LOA.setCellValue("Vesseltype");
                           LOA.setCellStyle(headerCellStyle);
                           
                           Cell ICE = brokertcratedatarow.createCell(17); 
                           ICE.setCellValue("TimeStamp");
                           ICE.setCellStyle(headerCellStyle);
                           
                           
                           
			if(Tonnagejsonobj.has("TimeCharterReportsList")){
				JSONArray tonnageList=Tonnagejsonobj.getJSONArray("TimeCharterReportsList");
				
				JSONArray combinebrokerjsonarray=new JSONArray();
				JSONArray vesseltypearray=new JSONArray();
				JSONArray month_YearJsonarray=new JSONArray();
				JSONArray firstyrJsonarray=new JSONArray();
				JSONArray secondyrJsonarray=new JSONArray();
				JSONArray thirdyrJsonarray=new JSONArray();
				JSONArray fifthyrJsonarray=new JSONArray();
				
				
				JSONArray ICEarray=new JSONArray();
				JSONArray LOAarray=new JSONArray();
				JSONArray Cubicsarray=new JSONArray();
				JSONArray DWTarray=new JSONArray();
				JSONArray Builtarray=new JSONArray();
				JSONArray VesselNamearray=new JSONArray();
				JSONArray sourceJsonarray=new JSONArray();
				JSONArray repositionRegionJsonarray=new JSONArray();
				JSONArray reporttimestampJsonarray=new JSONArray();
				JSONArray reporttypeJsonarray=new JSONArray();
				JSONArray opendateJsonarray=new JSONArray();
				
				
				for(int i=0;i<tonnageList.length();i++){
					
					JSONObject BrokerTcRateList_inside_jsonobj=tonnageList.getJSONObject(i);
					
					JSONArray BrokerJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("InformationJsonarray")){
					    BrokerJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("InformationJsonarray");
					    for(int j=0;j<BrokerJsonarray.length();j++){
					    	 JSONObject brokerjsonobj=null;
					         brokerjsonobj=BrokerJsonarray.getJSONObject(j);
					         String broker="";
					         if(brokerjsonobj.has("Information")){
						      broker=brokerjsonobj.getString("Information");
					         }
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("Informationobj", broker);
							 if(combinebrokerobj.length()>0){
								 combinebrokerjsonarray.put(combinebrokerobj);
							 }
							 
					    }
					}
					
					JSONArray VesselTypeJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("Spot_TCJsonarray")){
						VesselTypeJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("Spot_TCJsonarray");
						
						for(int j=0;j<VesselTypeJsonarray.length();j++){
					    	 JSONObject vesseltypejsonobj=null;
					    	 vesseltypejsonobj=VesselTypeJsonarray.getJSONObject(j);
					    	 String vesseltype_value="";
					    	 if(vesseltypejsonobj.has("Spot_TC")){
						      vesseltype_value=vesseltypejsonobj.getString("Spot_TC");
					    	 }
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("Spot_TCobj", vesseltype_value);
							 if(combinebrokerobj.length()>0){
							 vesseltypearray.put(combinebrokerobj);
							 }
					    }
						
					}
					
					JSONArray Month_YearJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("DateJsonarray")){
						Month_YearJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("DateJsonarray");
						
						for(int j=0;j<Month_YearJsonarray.length();j++){
					    	 JSONObject Month_Yearjsonobj=null;
					    	 Month_Yearjsonobj=Month_YearJsonarray.getJSONObject(j);
					    	 String Month_Year_value="";
					    	 if(Month_Yearjsonobj.has("Date")){
						        Month_Year_value=Month_Yearjsonobj.getString("Date");
					    	 }
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("Dateobj", Month_Year_value);
							 if(combinebrokerobj.length()>0){
							 month_YearJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					
					JSONArray first_yrJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("VesselNameJsonarray")){
						first_yrJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("VesselNameJsonarray");
						
						for(int j=0;j<first_yrJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=first_yrJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("VesselName")){
						        firstyr_value=first_yrjsonobj.getString("VesselName");
					    	 }
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("VesselNameobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
                             firstyrJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray second_yrJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("CPP_DPPJsonarray")){
						second_yrJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("CPP_DPPJsonarray");
						
						for(int j=0;j<second_yrJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=second_yrJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("CPP_DPP")){
						        firstyr_value=first_yrjsonobj.getString("CPP_DPP");
					    	 }
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("CPP_DPPobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 secondyrJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					
					JSONArray third_yrJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("ChartererJsonarray")){
						third_yrJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("ChartererJsonarray");
						
						for(int j=0;j<third_yrJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=third_yrJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("Charterer")){
						      firstyr_value=first_yrjsonobj.getString("Charterer");
					    	 }
						     
						        JSONObject combinebrokerobj=new JSONObject();
							     combinebrokerobj.put("Chartererobj", firstyr_value);
							     if(combinebrokerobj.length()>0){
							     thirdyrJsonarray.put(combinebrokerobj);
							     }
					    }
					}
					
					JSONArray fifth_yrJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("DeliveryJsonarray")){
						fifth_yrJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("DeliveryJsonarray");
						
						for(int j=0;j<fifth_yrJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=fifth_yrJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("Delivery")){
						      firstyr_value=first_yrjsonobj.getString("Delivery");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("Deliveryobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 fifthyrJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray OpenDateJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("DeliveryPlaceJsonarray")){
						OpenDateJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("DeliveryPlaceJsonarray");
						
						for(int j=0;j<OpenDateJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=OpenDateJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("DeliveryPlace")){
						        firstyr_value=first_yrjsonobj.getString("DeliveryPlace");
					    	 }
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("DeliveryPlaceobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 opendateJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					
					JSONArray ReportTypeJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("PeriodJsonarray")){
						ReportTypeJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("PeriodJsonarray");
						
						for(int j=0;j<ReportTypeJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=ReportTypeJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("Period")){
						        firstyr_value=first_yrjsonobj.getString("Period");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("Periodobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 reporttypeJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					
					JSONArray ReportTimestampJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("PeriodunitJsonarray")){
						ReportTimestampJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("PeriodunitJsonarray");
						
						for(int j=0;j<ReportTimestampJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=ReportTimestampJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("Periodunit")){
						      firstyr_value=first_yrjsonobj.getString("Periodunit");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("Periodunitobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 reporttimestampJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray RepositionRegionJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("RedeliveryJsonarray")){
						RepositionRegionJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("RedeliveryJsonarray");
						
						for(int j=0;j<RepositionRegionJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=RepositionRegionJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("Redelivery")){
						      firstyr_value=first_yrjsonobj.getString("Redelivery");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("Redeliveryobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 repositionRegionJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray SourceJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("TCRateJsonarray")){
						SourceJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("TCRateJsonarray");
						
						for(int j=0;j<SourceJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=SourceJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("TCRate")){
						      firstyr_value=first_yrjsonobj.getString("TCRate");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("TCRateobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 sourceJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray VesselNameJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("RateJsonarray")){
						VesselNameJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("RateJsonarray");
						
						for(int j=0;j<VesselNameJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=VesselNameJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("Rate")){
						      firstyr_value=first_yrjsonobj.getString("Rate");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("Rateobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 VesselNamearray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray BuiltJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("UnitJsonarray")){
						BuiltJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("UnitJsonarray");
						
						for(int j=0;j<BuiltJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=BuiltJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("Unit")){
						      firstyr_value=first_yrjsonobj.getString("Unit");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("Unitobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 Builtarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray DWTJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("CommentsJsonarray")){
						DWTJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("CommentsJsonarray");
						
						for(int j=0;j<DWTJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=DWTJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("Comments")){
						      firstyr_value=first_yrjsonobj.getString("Comments");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("Commentsobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 DWTarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray CubicsJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("SourceJsonarray")){
						CubicsJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("SourceJsonarray");
						
						for(int j=0;j<CubicsJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=CubicsJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("Source")){
						      firstyr_value=first_yrjsonobj.getString("Source");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("Sourceobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 Cubicsarray.put(combinebrokerobj);
							 }
					    }
					}
					
					
					JSONArray ICEJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("VesseltypeJsonarray")){
						ICEJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("VesseltypeJsonarray");
						
						for(int j=0;j<ICEJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=ICEJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("Vesseltype")){
						        firstyr_value=first_yrjsonobj.getString("Vesseltype");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("Vesseltypeobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 ICEarray.put(combinebrokerobj);
							 }
					    }
					}
					
					
				} // for close main
				
				
				if(combinebrokerjsonarray.length()>0){
				for(int j=0;j<combinebrokerjsonarray.length();j++){
					JSONObject brokercombineobj=combinebrokerjsonarray.getJSONObject(j);
					String brokerobj="";
					if(brokercombineobj.has("Informationobj")){
						brokerobj=brokercombineobj.getString("Informationobj");
					}
					
					 Row brokerrow = sheet.createRow((rowIndex+1+j));
					 
					 Cell broker_value = brokerrow.createCell(0);
					      broker_value.setCellValue(brokerobj);
					                     
					   
				 }// for broker close
				}
				
				if(vesseltypearray.length()>0){
				for(int k=0;k<vesseltypearray.length();k++){
					JSONObject vesseltypecombineobj=vesseltypearray.getJSONObject(k);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("Spot_TCobj")){
						vesseltypeobj=vesseltypecombineobj.getString("Spot_TCobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+k));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+k));
					}
					 
					 Cell broker_value = brokerrow.createCell(1);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close vesseltype
				}
				
				if(month_YearJsonarray.length()>0){
				for(int m=0;m<month_YearJsonarray.length();m++){
					JSONObject vesseltypecombineobj=month_YearJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("Dateobj")){
						vesseltypeobj=vesseltypecombineobj.getString("Dateobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(2);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(firstyrJsonarray.length()>0){
				for(int m=0;m<firstyrJsonarray.length();m++){
					JSONObject vesseltypecombineobj=firstyrJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("VesselNameobj")){
						vesseltypeobj=vesseltypecombineobj.getString("VesselNameobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(3);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(secondyrJsonarray.length()>0){
				for(int m=0;m<secondyrJsonarray.length();m++){
					JSONObject vesseltypecombineobj=secondyrJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("CPP_DPPobj")){
						vesseltypeobj=vesseltypecombineobj.getString("CPP_DPPobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(4);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(thirdyrJsonarray.length()>0){
				for(int m=0;m<thirdyrJsonarray.length();m++){
					JSONObject vesseltypecombineobj=thirdyrJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("Chartererobj")){
						vesseltypeobj=vesseltypecombineobj.getString("Chartererobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(5);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(fifthyrJsonarray.length()>0){
				for(int m=0;m<fifthyrJsonarray.length();m++){
					JSONObject vesseltypecombineobj=fifthyrJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("Deliveryobj")){
						vesseltypeobj=vesseltypecombineobj.getString("Deliveryobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(6);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(opendateJsonarray.length()>0){
				for(int m=0;m<opendateJsonarray.length();m++){
					JSONObject vesseltypecombineobj=opendateJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("DeliveryPlaceobj")){
						vesseltypeobj=vesseltypecombineobj.getString("DeliveryPlaceobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(7);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(reporttypeJsonarray.length()>0){
				for(int m=0;m<reporttypeJsonarray.length();m++){
					JSONObject vesseltypecombineobj=reporttypeJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("Periodobj")){
						vesseltypeobj=vesseltypecombineobj.getString("Periodobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(8);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				if(reporttimestampJsonarray.length()>0){
				for(int m=0;m<reporttimestampJsonarray.length();m++){
					JSONObject vesseltypecombineobj=reporttimestampJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("Periodunitobj")){
						vesseltypeobj=vesseltypecombineobj.getString("Periodunitobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(9);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				if(repositionRegionJsonarray.length()>0){
				for(int m=0;m<repositionRegionJsonarray.length();m++){
					JSONObject vesseltypecombineobj=repositionRegionJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("Redeliveryobj")){
						vesseltypeobj=vesseltypecombineobj.getString("Redeliveryobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(10);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(sourceJsonarray.length()>0){
				for(int m=0;m<sourceJsonarray.length();m++){
					JSONObject vesseltypecombineobj=sourceJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("TCRateobj")){
						vesseltypeobj=vesseltypecombineobj.getString("TCRateobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(11);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(VesselNamearray.length()>0){
				for(int m=0;m<VesselNamearray.length();m++){
					JSONObject vesseltypecombineobj=VesselNamearray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("Rateobj")){
						vesseltypeobj=vesseltypecombineobj.getString("Rateobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(12);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				if(Builtarray.length()>0){
				for(int m=0;m<Builtarray.length();m++){
					JSONObject vesseltypecombineobj=Builtarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("Unitobj")){
						vesseltypeobj=vesseltypecombineobj.getString("Unitobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(13);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(DWTarray.length()>0){
				for(int m=0;m<DWTarray.length();m++){
					JSONObject vesseltypecombineobj=DWTarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("Commentsobj")){
						vesseltypeobj=vesseltypecombineobj.getString("Commentsobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(14);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				if(Cubicsarray.length()>0){
				for(int m=0;m<Cubicsarray.length();m++){
					JSONObject vesseltypecombineobj=Cubicsarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("Sourceobj")){
						vesseltypeobj=vesseltypecombineobj.getString("Sourceobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(15);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				
				
				if(ICEarray.length()>0){
				for(int m=0;m<ICEarray.length();m++){
					JSONObject vesseltypecombineobj=ICEarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("Vesseltypeobj")){
						vesseltypeobj=vesseltypecombineobj.getString("Vesseltypeobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(17);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				
			}
			
			Calendar cal = null;
			cal = Calendar.getInstance();
			Date minDate = new Date(cal.getTimeInMillis());
			
			JSONObject dateJson=new JSONObject();
			Date date=GmailMethods.parseDate(minDate.toString());
			dateJson=SlingMethods.formatDate_Month_Year_Day(date, out);
			
			int day=dateJson.getInt("day");
			int month=dateJson.getInt("month");
			int year=dateJson.getInt("year");
			
			 String fileName="TimeCharterReports"+"Data"+"_"+day+"-"+month+"-"+year+".xls";
		     fileName=fileName.replace(" ", "-");
		     
		     File linuxdirectory=new File("/home/ubuntu/apache-tomcat-8.5.31/webapps/ROOT/scorpioexcel/");
			   if(!linuxdirectory.exists()){
				   
				 linuxdirectory.mkdir();
				 out.println("folder created in linux");
			   }
			
		  // Write the output to a file
//		        FileOutputStream fileOut = new FileOutputStream("D:\\Scorpio\\Email\\"+fileName.trim());
		        FileOutputStream fileOut = new FileOutputStream("/home/ubuntu/apache-tomcat-8.5.31/webapps/ROOT/scorpioexcel/"+fileName.trim());
		        workbook.write(fileOut);
		        fileOut.close();

		        // Closing the workbook
		        workbook.close();
		        
		         final_obj=new JSONObject();
		         final_obj.put("fileName", fileName.trim());
		 		 final_obj.put("fileUrl", "http://35.199.31.139:8080/scorpioexcel/"+fileName.trim());
		 		 final_obj.put("status", "S");
			
			
		} catch (Exception e) {
//			out.println(e.getMessage());
			return "{\"status\":\"E\",\"Message\" : "+e.getMessage()+"}";
		}
		return final_obj.toString();
	}
	
	
	
public static String createExcelBrokerTcRate(PrintWriter out, String fetchBrokerTcRateDaywiseData){
	     JSONObject final_obj=null;
		try {
		
			int rowIndex=0;
			// Create a Workbook
	         Workbook workbook = new HSSFWorkbook(); //XSSFWorkbook  new HSSFWorkbook() for generating `.xls` file
	         /* CreationHelper helps us create instances of various things like DataFormat, 
	            Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
	         CreationHelper createHelper = workbook.getCreationHelper();
	         
	         // Create a Sheet
	         Sheet sheet = workbook.createSheet("BrokerTcRate-Summery");
	      // Create a Font for styling header cells
	         Font headerFont = workbook.createFont();
	         headerFont.setBold(true);
	         
	      // Create a CellStyle with the font
	         CellStyle headerCellStyle = workbook.createCellStyle();
	         headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
	         headerCellStyle.setWrapText(true);
	         headerCellStyle.setFont(headerFont);
	         
	         CellStyle hlinkstyle = workbook.createCellStyle();
	         Font hlinkfont = workbook.createFont();
	              hlinkfont.setUnderline(HSSFFont.U_SINGLE);
	              hlinkfont.setColor(HSSFColor.BLUE.index);
	             
	              hlinkstyle.setFont(hlinkfont);
	              
	           // Create Cell Style for formatting Date
	              CellStyle dateCellStyle = workbook.createCellStyle();
	              dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
	              
	              Row brokertcratedatarow = sheet.createRow((rowIndex));
			//String brokerfetchdata=SlingMethods.fetchBrokerTcRateDaywiseData(out, session);
	              String brokerfetchdata=fetchBrokerTcRateDaywiseData;
//	              String brokerfetchdata="{\"BrokerTcRateList\":[{\"BrokerTcRateId\":\"6\",\"dateAndTime\":\"Sun Oct 08 18:56:35 UTC 2018\",\"BrokerJsonarray\":[{\"Broker\":\"f\"}],\"VesselTypeJsonarray\":[{\"VesselType\":\"\"}],\"Month_YearJsonarray\":[{\"Month_Year\":\"\"}],\"first_yrJsonarray\":[{\"first_yr\":\"\"}],\"second_yrJsonarray\":[{\"second_yr\":\"\"}],\"third_yrJsonarray\":[{\"third_yr\":\"\"}],\"fifth_yrJsonarray\":[{\"fifth_yr\":\"\"}],\"subNodeName\":\"6\",\"size\":8,\"parseId\":7},{\"BrokerTcRateId\":\"7\",\"dateAndTime\":\"Mon Oct 08 07:27:16 UTC 2018\",\"BrokerJsonarray\":[{\"Broker\":\"a\"}],\"VesselTypeJsonarray\":[{\"VesselType\":\"b\"}],\"Month_YearJsonarray\":[{\"Month_Year\":\"c\"}],\"first_yrJsonarray\":[{\"first_yr\":\"d\"}],\"second_yrJsonarray\":[{\"second_yr\":\"f\"}],\"third_yrJsonarray\":[{\"third_yr\":\"g\"}],\"fifth_yrJsonarray\":[{\"fifth_yr\":\"h\"}],\"subNodeName\":\"7\",\"size\":8,\"parseId\":8}]}";
			
			JSONObject brokertcratejsonobj=new JSONObject(brokerfetchdata);
			
			
            Cell Broker = brokertcratedatarow.createCell(0);
                          Broker.setCellValue("Broker");
                          Broker.setCellStyle(headerCellStyle);
			
            Cell vesseltype = brokertcratedatarow.createCell(1);
                              vesseltype.setCellValue("Vessel Type");
                              vesseltype.setCellStyle(headerCellStyle);
                          
            Cell monthYear = brokertcratedatarow.createCell(2);
                              monthYear.setCellValue("Month-Year");
                              monthYear.setCellStyle(headerCellStyle);
                              
            Cell firstYear = brokertcratedatarow.createCell(3); 
                              firstYear.setCellValue("1yr");
                              firstYear.setCellStyle(headerCellStyle);
                              
            Cell secondYear = brokertcratedatarow.createCell(4); 
                              secondYear.setCellValue("2yr");
                              secondYear.setCellStyle(headerCellStyle);
                              
            Cell thirdYear = brokertcratedatarow.createCell(5);
                              thirdYear.setCellValue("3yr");
                              thirdYear.setCellStyle(headerCellStyle);
                              
            Cell fifthYear = brokertcratedatarow.createCell(6); 
                              fifthYear.setCellValue("5yr");
                              fifthYear.setCellStyle(headerCellStyle);
			
                              
                              
			if(brokertcratejsonobj.has("BrokerTcRateList")){
				JSONArray BrokerTcRateList=brokertcratejsonobj.getJSONArray("BrokerTcRateList");
				JSONArray combinebrokerjsonarray=new JSONArray();
				JSONArray vesseltypearray=new JSONArray();
				JSONArray month_YearJsonarray=new JSONArray();
				JSONArray firstyrJsonarray=new JSONArray();
				JSONArray secondyrJsonarray=new JSONArray();
				JSONArray thirdyrJsonarray=new JSONArray();
				JSONArray fifthyrJsonarray=new JSONArray();
				
				
				for(int i=0;i<BrokerTcRateList.length();i++){
					
					JSONObject BrokerTcRateList_inside_jsonobj=BrokerTcRateList.getJSONObject(i);
					
					JSONArray BrokerJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("BrokerJsonarray")){
					    BrokerJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("BrokerJsonarray");
					    for(int j=0;j<BrokerJsonarray.length();j++){
					    	 JSONObject brokerjsonobj=null;
					         brokerjsonobj=BrokerJsonarray.getJSONObject(j);
					         String broker="";
					         if(brokerjsonobj.has("Broker")){
						      broker=brokerjsonobj.getString("Broker");
					         }
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("brokerobj", broker);
							 if(combinebrokerobj.length()>0){
							 combinebrokerjsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray VesselTypeJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("VesselTypeJsonarray")){
						VesselTypeJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("VesselTypeJsonarray");
						
						for(int j=0;j<VesselTypeJsonarray.length();j++){
					    	 JSONObject vesseltypejsonobj=null;
					    	 vesseltypejsonobj=VesselTypeJsonarray.getJSONObject(j);
					    	 String vesseltype_value="";
					    	 if(vesseltypejsonobj.has("VesselType")){
						      vesseltype_value=vesseltypejsonobj.getString("VesselType");
					    	 }
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("vesseltypeobj", vesseltype_value);
							 if(combinebrokerobj.length()>0){
							 vesseltypearray.put(combinebrokerobj);
							 }
					    }
						
					}
					
					JSONArray Month_YearJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("Month_YearJsonarray")){
						Month_YearJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("Month_YearJsonarray");
						
						for(int j=0;j<Month_YearJsonarray.length();j++){
					    	 JSONObject Month_Yearjsonobj=null;
					    	 Month_Yearjsonobj=Month_YearJsonarray.getJSONObject(j);
					    	 String Month_Year_value="";
					    	 if(Month_Yearjsonobj.has("Month_Year")){
						      Month_Year_value=Month_Yearjsonobj.getString("Month_Year");
					    	 }
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("Month_Yearobj", Month_Year_value);
							 if(combinebrokerobj.length()>0){
							 month_YearJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					
					JSONArray first_yrJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("first_yrJsonarray")){
						first_yrJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("first_yrJsonarray");
						
						for(int j=0;j<first_yrJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=first_yrJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("first_yr")){
						      firstyr_value=first_yrjsonobj.getString("first_yr");
					    	 }
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("firstyrobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
                                firstyrJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					JSONArray second_yrJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("second_yrJsonarray")){
						second_yrJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("second_yrJsonarray");
						
						for(int j=0;j<second_yrJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=second_yrJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("second_yr")){
						        firstyr_value=first_yrjsonobj.getString("second_yr");
					    	 }
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("secondyrobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 secondyrJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					
					JSONArray third_yrJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("third_yrJsonarray")){
						third_yrJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("third_yrJsonarray");
						
						for(int j=0;j<third_yrJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=third_yrJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("third_yr")){
						         firstyr_value=first_yrjsonobj.getString("third_yr");
					    	 }
						     
						        JSONObject combinebrokerobj=new JSONObject();
							     combinebrokerobj.put("thirdyrobj", firstyr_value);
							     if(combinebrokerobj.length()>0){
							        thirdyrJsonarray.put(combinebrokerobj);
							     }
					    }
					}
					
					JSONArray fifth_yrJsonarray=null;
					if(BrokerTcRateList_inside_jsonobj.has("fifth_yrJsonarray")){
						fifth_yrJsonarray=BrokerTcRateList_inside_jsonobj.getJSONArray("fifth_yrJsonarray");
						
						for(int j=0;j<fifth_yrJsonarray.length();j++){
					    	 JSONObject first_yrjsonobj=null;
					    	 first_yrjsonobj=fifth_yrJsonarray.getJSONObject(j);
					    	 String firstyr_value="";
					    	 if(first_yrjsonobj.has("fifth_yr")){
						      firstyr_value=first_yrjsonobj.getString("fifth_yr");
					    	 }
						     
						     JSONObject combinebrokerobj=new JSONObject();
							 combinebrokerobj.put("fifthyrobj", firstyr_value);
							 if(combinebrokerobj.length()>0){
							 fifthyrJsonarray.put(combinebrokerobj);
							 }
					    }
					}
					
					
				} // for close main
				
				
				/*out.println(combinebrokerjsonarray);
				out.println(vesseltypearray);
				out.println(month_YearJsonarray);
				out.println(firstyrJsonarray);
				out.println(secondyrJsonarray);
				out.println(thirdyrJsonarray);
				out.println(fifthyrJsonarray);*/
				
				if(combinebrokerjsonarray.length()>0){
				for(int j=0;j<combinebrokerjsonarray.length();j++){
					JSONObject brokercombineobj=combinebrokerjsonarray.getJSONObject(j);
					String brokerobj="";
					if(brokercombineobj.has("brokerobj")){
						brokerobj=brokercombineobj.getString("brokerobj");
					}
					
					 Row brokerrow = sheet.createRow((rowIndex+1+j));
					 
					 Cell broker_value = brokerrow.createCell(0);
					      broker_value.setCellValue(brokerobj);
					                     
					   
				 }// for broker close
				}
				
				if(vesseltypearray.length()>0){
				for(int k=0;k<vesseltypearray.length();k++){
					JSONObject vesseltypecombineobj=vesseltypearray.getJSONObject(k);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("vesseltypeobj")){
						vesseltypeobj=vesseltypecombineobj.getString("vesseltypeobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+k));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+k));
					}
					 
					 Cell broker_value = brokerrow.createCell(1);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close vesseltype
				}
				
				if(month_YearJsonarray.length()>0){
				for(int m=0;m<month_YearJsonarray.length();m++){
					JSONObject vesseltypecombineobj=month_YearJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("Month_Yearobj")){
						vesseltypeobj=vesseltypecombineobj.getString("Month_Yearobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(2);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(firstyrJsonarray.length()>0){
					
				
				for(int m=0;m<firstyrJsonarray.length();m++){
					JSONObject vesseltypecombineobj=firstyrJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("firstyrobj")){
						vesseltypeobj=vesseltypecombineobj.getString("firstyrobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(3);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(secondyrJsonarray.length()>0){
				for(int m=0;m<secondyrJsonarray.length();m++){
					JSONObject vesseltypecombineobj=secondyrJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("secondyrobj")){
						vesseltypeobj=vesseltypecombineobj.getString("secondyrobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(4);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(thirdyrJsonarray.length()>0){
				for(int m=0;m<thirdyrJsonarray.length();m++){
					JSONObject vesseltypecombineobj=thirdyrJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("thirdyrobj")){
						vesseltypeobj=vesseltypecombineobj.getString("thirdyrobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(5);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
				if(fifthyrJsonarray.length()>0){
				for(int m=0;m<fifthyrJsonarray.length();m++){
					JSONObject vesseltypecombineobj=fifthyrJsonarray.getJSONObject(m);
					String vesseltypeobj="";
					if(vesseltypecombineobj.has("fifthyrobj")){
						vesseltypeobj=vesseltypecombineobj.getString("fifthyrobj");
					}
					
					
					Row brokerrow = sheet.getRow((rowIndex+1+m));
					if(brokerrow==null){
						brokerrow = sheet.createRow((rowIndex+1+m));
					}
					 
					 Cell broker_value = brokerrow.createCell(6);
					      broker_value.setCellValue(vesseltypeobj);
					      
				} // close month_YearJsonarray
				}
				
			}
			
			Calendar cal = null;
			cal = Calendar.getInstance();
			Date minDate = new Date(cal.getTimeInMillis());
			
			JSONObject dateJson=new JSONObject();
			Date date=GmailMethods.parseDate(minDate.toString());
			dateJson=SlingMethods.formatDate_Month_Year_Day(date, out);
			
			int day=dateJson.getInt("day");
			int month=dateJson.getInt("month");
			int year=dateJson.getInt("year");
			
			 String fileName="broker"+"tcrate"+"_"+day+"-"+month+"-"+year+".xls";
		     fileName=fileName.replace(" ", "-");
		     
		     File linuxdirectory=new File("/home/ubuntu/apache-tomcat-8.5.31/webapps/ROOT/scorpioexcel/");
			   if(!linuxdirectory.exists()){
				   
				 linuxdirectory.mkdir();
				 out.println("folder created in linux");
			   }
			
			   /* int firstrowno=sheet.getFirstRowNum();
				int lastrowno=sheet.getLastRowNum();
				CellRangeAddress region = new CellRangeAddress(firstrowno, lastrowno, 0, 13);
				RegionUtil.setBorderRight(BorderStyle.THIN, region, sheet);*/
			   
		  // Write the output to a file
//		        FileOutputStream fileOut = new FileOutputStream("D:\\Scorpio\\Email\\"+fileName.trim());
		        FileOutputStream fileOut = new FileOutputStream("/home/ubuntu/apache-tomcat-8.5.31/webapps/ROOT/scorpioexcel/"+fileName.trim());
		        workbook.write(fileOut);
		        fileOut.close();

		        // Closing the workbook
		        workbook.close();
		        
		         final_obj=new JSONObject();
		         final_obj.put("fileName", fileName.trim());
		 		 final_obj.put("fileUrl", "http://35.199.31.139:8080/scorpioexcel/"+fileName.trim());
		 		 final_obj.put("status", "S");
			
			
		} catch (Exception e) {
//			out.println(e.getMessage());
			return "{\"status\":\"E\",\"Message\" : "+e.getMessage()+"}";
		}
		return final_obj.toString();
	}
	
	

}
