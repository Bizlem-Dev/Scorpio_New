package com.reportinformationsystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.readGmail.GmailMethods;
import com.readGmail.SlingMethods;

public class CreateExcelOfAllReports {

	public static void main(String[] args) {
		String str="01-02";
		
		if (str.matches("([0-9]{2})-([0-9]{2})")) {
		    System.out.println("yes");
		}else{
			 System.out.println("no");
		}
	}

	public static String createExcelTimeCharterReportsNew(PrintWriter out, String fetchTonnageDaywiseData) {
		JSONObject final_obj = null;
		try {

			int rowIndex = 0;
			// Create a Workbook
			Workbook workbook = new HSSFWorkbook(); // XSSFWorkbook new
													// HSSFWorkbook() for
													// generating `.xls` file
			/*
			 * CreationHelper helps us create instances of various things like
			 * DataFormat, Hyperlink, RichTextString etc, in a format (HSSF,
			 * XSSF) independent way
			 */
			CreationHelper createHelper = workbook.getCreationHelper();

			// Create a Sheet
			Sheet sheet = workbook.createSheet("TimeCharterReports-Summary");
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
			// String
			// brokerfetchdata=SlingMethods.fetchBrokerTcRateDaywiseData(out,
			// session);
			String tonnagefetchdata = fetchTonnageDaywiseData;
			// String
			// brokerfetchdata="{\"BrokerTcRateList\":[{\"BrokerTcRateId\":\"6\",\"dateAndTime\":\"Sun
			// Oct 08 18:56:35 UTC
			// 2018\",\"BrokerJsonarray\":[{\"Broker\":\"f\"}],\"VesselTypeJsonarray\":[{\"VesselType\":\"\"}],\"Month_YearJsonarray\":[{\"Month_Year\":\"\"}],\"first_yrJsonarray\":[{\"first_yr\":\"\"}],\"second_yrJsonarray\":[{\"second_yr\":\"\"}],\"third_yrJsonarray\":[{\"third_yr\":\"\"}],\"fifth_yrJsonarray\":[{\"fifth_yr\":\"\"}],\"subNodeName\":\"6\",\"size\":8,\"parseId\":7},{\"BrokerTcRateId\":\"7\",\"dateAndTime\":\"Mon
			// Oct 08 07:27:16 UTC
			// 2018\",\"BrokerJsonarray\":[{\"Broker\":\"a\"}],\"VesselTypeJsonarray\":[{\"VesselType\":\"b\"}],\"Month_YearJsonarray\":[{\"Month_Year\":\"c\"}],\"first_yrJsonarray\":[{\"first_yr\":\"d\"}],\"second_yrJsonarray\":[{\"second_yr\":\"f\"}],\"third_yrJsonarray\":[{\"third_yr\":\"g\"}],\"fifth_yrJsonarray\":[{\"fifth_yr\":\"h\"}],\"subNodeName\":\"7\",\"size\":8,\"parseId\":8}]}";

			JSONObject Tonnagejsonobj = new JSONObject(tonnagefetchdata);

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
			
			Cell emailUrl = brokertcratedatarow.createCell(18);
			emailUrl.setCellValue("emailUrl");
			emailUrl.setCellStyle(headerCellStyle); //SingleJson
			
			Cell SingleJson = brokertcratedatarow.createCell(19);
			SingleJson.setCellValue("SingleJson");
			SingleJson.setCellStyle(headerCellStyle);
			
			Cell Extra_Column = brokertcratedatarow.createCell(20);
			Extra_Column.setCellValue("Extra_Column");
			Extra_Column.setCellStyle(headerCellStyle);

			if (Tonnagejsonobj.has("TimeCharterReportsList")) {
				JSONArray combinebrokerjsonarray = new JSONArray();

				JSONArray tonnageList = Tonnagejsonobj.getJSONArray("TimeCharterReportsList");
				for (int i = 0; i < tonnageList.length(); i++) {
					JSONObject combinebrokerobj = new JSONObject();

					JSONObject BrokerTcRateList_inside_jsonobj = tonnageList.getJSONObject(i);

					String broker = "";
					if (BrokerTcRateList_inside_jsonobj.has("Information")) {
						broker = BrokerTcRateList_inside_jsonobj.getString("Information");
						combinebrokerobj.put("Informationobj", broker);
					}

					String vesseltype_value = "";
					if (BrokerTcRateList_inside_jsonobj.has("Spot_TC")) {
						vesseltype_value = BrokerTcRateList_inside_jsonobj.getString("Spot_TC");
						combinebrokerobj.put("Spot_TCobj", vesseltype_value);
					}

					String Month_Year_value = "";
					if (BrokerTcRateList_inside_jsonobj.has("Date")) {
						Month_Year_value = BrokerTcRateList_inside_jsonobj.getString("Date");
						combinebrokerobj.put("Dateobj", Month_Year_value);
					}

					String firstyr_value = "";
					if (BrokerTcRateList_inside_jsonobj.has("VesselName")) {
						firstyr_value = BrokerTcRateList_inside_jsonobj.getString("VesselName");
						combinebrokerobj.put("VesselNameobj", firstyr_value);
					}

					String firstyr_value1 = "";
					if (BrokerTcRateList_inside_jsonobj.has("CPP_DPP")) {
						firstyr_value1 = BrokerTcRateList_inside_jsonobj.getString("CPP_DPP");
						combinebrokerobj.put("CPP_DPPobj", firstyr_value1);
					}

					String firstyr_value12 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Charterer")) {
						firstyr_value12 = BrokerTcRateList_inside_jsonobj.getString("Charterer");
						combinebrokerobj.put("Chartererobj", firstyr_value12);
					}

					String firstyr_value13 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Delivery")) {
						firstyr_value13 = BrokerTcRateList_inside_jsonobj.getString("Delivery");
						combinebrokerobj.put("Deliveryobj", firstyr_value13);
					}

					String firstyr_value14 = "";
					if (BrokerTcRateList_inside_jsonobj.has("DeliveryPlace")) {
						firstyr_value14 = BrokerTcRateList_inside_jsonobj.getString("DeliveryPlace");
						combinebrokerobj.put("DeliveryPlaceobj", firstyr_value14);
					}

					String firstyr_value15 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Period")) {
						firstyr_value15 = BrokerTcRateList_inside_jsonobj.getString("Period");
						combinebrokerobj.put("Periodobj", firstyr_value15);
					}

					String firstyr_value16 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Periodunit")) {
						firstyr_value16 = BrokerTcRateList_inside_jsonobj.getString("Periodunit");
						combinebrokerobj.put("Periodunitobj", firstyr_value16);
					}

					String firstyr_value17 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Redelivery")) {
						firstyr_value17 = BrokerTcRateList_inside_jsonobj.getString("Redelivery");
						combinebrokerobj.put("Redeliveryobj", firstyr_value17);
					}

					String firstyr_value18 = "";
					if (BrokerTcRateList_inside_jsonobj.has("TCRate")) {
						firstyr_value18 = BrokerTcRateList_inside_jsonobj.getString("TCRate");
						combinebrokerobj.put("TCRateobj", firstyr_value18);
					}

					String firstyr_value19 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Rate")) {
						firstyr_value19 = BrokerTcRateList_inside_jsonobj.getString("Rate");
						combinebrokerobj.put("Rateobj", firstyr_value19);
					}

					String firstyr_value20 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Unit")) {
						firstyr_value20 = BrokerTcRateList_inside_jsonobj.getString("Unit");
						combinebrokerobj.put("Unitobj", firstyr_value20);
					}

					String firstyr_value2 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Comments")) {
						firstyr_value2 = BrokerTcRateList_inside_jsonobj.getString("Comments");
						combinebrokerobj.put("Commentsobj", firstyr_value2);
					}

					String firstyr_value3 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Source")) {
						firstyr_value3 = BrokerTcRateList_inside_jsonobj.getString("Source");
						combinebrokerobj.put("Sourceobj", firstyr_value3);
					}

					String firstyr_value4 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Vesseltype")) {
						firstyr_value4 = BrokerTcRateList_inside_jsonobj.getString("Vesseltype");
						combinebrokerobj.put("Vesseltypeobj", firstyr_value4);
					}
					String firstyr_value5 = "";
					if (BrokerTcRateList_inside_jsonobj.has("TimeStamp")) {
						firstyr_value5 = BrokerTcRateList_inside_jsonobj.getString("TimeStamp");
						combinebrokerobj.put("TimeStampobj", firstyr_value5);
					}
					
					String firstyr_value144 = "";
					if (BrokerTcRateList_inside_jsonobj.has("SingleJson")) {
						firstyr_value144 = BrokerTcRateList_inside_jsonobj.getString("SingleJson");
						combinebrokerobj.put("SingleJsonobj", firstyr_value144);
					}
					
					
					String emailUrl1 = "";
					if (BrokerTcRateList_inside_jsonobj.has("emailUrl")) {
						emailUrl1 = BrokerTcRateList_inside_jsonobj.getString("emailUrl");
						combinebrokerobj.put("emailurlobj", emailUrl1);
					}
					String Extra_Column12 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Extra_Column")) {
						Extra_Column12 = BrokerTcRateList_inside_jsonobj.getString("Extra_Column");
						combinebrokerobj.put("Extra_ColumnObj", Extra_Column12);
					}

					if (combinebrokerobj.length() > 0) {
						combinebrokerjsonarray.put(combinebrokerobj);
					}

				} // main for

				if (combinebrokerjsonarray.length() > 0) {
					for (int j = 0; j < combinebrokerjsonarray.length(); j++) {
						JSONObject brokercombineobj = combinebrokerjsonarray.getJSONObject(j);

						String brokerobj = "";
						if (brokercombineobj.has("Informationobj")) {
							brokerobj = brokercombineobj.getString("Informationobj");
						}

						Row brokerrow = sheet.createRow((rowIndex + 1 + j));

						Cell broker_value = brokerrow.createCell(0);
						broker_value.setCellValue(brokerobj);

						String vesseltypeobj = "";
						if (brokercombineobj.has("Spot_TCobj")) {
							vesseltypeobj = brokercombineobj.getString("Spot_TCobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(1);
						broker_value.setCellValue(vesseltypeobj);

						String vesseltypeobj1 = "";
						if (brokercombineobj.has("Dateobj")) {
							vesseltypeobj1 = brokercombineobj.getString("Dateobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(2);
						broker_value.setCellValue(vesseltypeobj1);

						String vesseltypeobj12 = "";
						if (brokercombineobj.has("VesselNameobj")) {
							vesseltypeobj12 = brokercombineobj.getString("VesselNameobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(3);
						broker_value.setCellValue(vesseltypeobj12);

						String vesseltypeobj13 = "";
						if (brokercombineobj.has("CPP_DPPobj")) {
							vesseltypeobj13 = brokercombineobj.getString("CPP_DPPobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(4);
						broker_value.setCellValue(vesseltypeobj13);

						String vesseltypeobj14 = "";
						if (brokercombineobj.has("Chartererobj")) {
							vesseltypeobj14 = brokercombineobj.getString("Chartererobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(5);
						broker_value.setCellValue(vesseltypeobj14);

						String vesseltypeobj15 = "";
						if (brokercombineobj.has("Deliveryobj")) {
							vesseltypeobj15 = brokercombineobj.getString("Deliveryobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(6);
						broker_value.setCellValue(vesseltypeobj15);

						String vesseltypeobj16 = "";
						if (brokercombineobj.has("DeliveryPlaceobj")) {
							vesseltypeobj16 = brokercombineobj.getString("DeliveryPlaceobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(7);
						broker_value.setCellValue(vesseltypeobj16);

						String vesseltypeobj17 = "";
						if (brokercombineobj.has("Periodobj")) {
							vesseltypeobj17 = brokercombineobj.getString("Periodobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(8);
						broker_value.setCellValue(vesseltypeobj17);

						String vesseltypeobj18 = "";
						if (brokercombineobj.has("Periodunitobj")) {
							vesseltypeobj18 = brokercombineobj.getString("Periodunitobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(9);
						broker_value.setCellValue(vesseltypeobj18);

						String vesseltypeobj19 = "";
						if (brokercombineobj.has("Redeliveryobj")) {
							vesseltypeobj19 = brokercombineobj.getString("Redeliveryobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(10);
						broker_value.setCellValue(vesseltypeobj19);

						String vesseltypeobj20 = "";
						if (brokercombineobj.has("TCRateobj")) {
							vesseltypeobj20 = brokercombineobj.getString("TCRateobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(11);
						broker_value.setCellValue(vesseltypeobj20);

						String vesseltypeobj2 = "";
						if (brokercombineobj.has("Rateobj")) {
							vesseltypeobj2 = brokercombineobj.getString("Rateobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(12);
						broker_value.setCellValue(vesseltypeobj2);

						String vesseltypeobj3 = "";
						if (brokercombineobj.has("Unitobj")) {
							vesseltypeobj3 = brokercombineobj.getString("Unitobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(13);
						broker_value.setCellValue(vesseltypeobj3);

						String vesseltypeobj4 = "";
						if (brokercombineobj.has("Commentsobj")) {
							vesseltypeobj4 = brokercombineobj.getString("Commentsobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(14);
						broker_value.setCellValue(vesseltypeobj4);

						String vesseltypeobj5 = "";
						if (brokercombineobj.has("Sourceobj")) {
							vesseltypeobj5 = brokercombineobj.getString("Sourceobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(15);
						broker_value.setCellValue(vesseltypeobj5);

						String vesseltypeobj6 = "";
						if (brokercombineobj.has("Vesseltypeobj")) {
							vesseltypeobj6 = brokercombineobj.getString("Vesseltypeobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(16);
						broker_value.setCellValue(vesseltypeobj6);

						String vesseltypeobj7 = "";
						if (brokercombineobj.has("TimeStampobj")) {
							vesseltypeobj7 = brokercombineobj.getString("TimeStampobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(17);
						broker_value.setCellValue(vesseltypeobj7);
						
						String vesseltypeobj1712 = "";
						if (brokercombineobj.has("emailurlobj")) {
							vesseltypeobj1712 = brokercombineobj.getString("emailurlobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(18);
						broker_value.setCellValue(vesseltypeobj1712);

						String vesseltypeobj1713 = "";
						if (brokercombineobj.has("SingleJsonobj")) {
							vesseltypeobj1713 = brokercombineobj.getString("SingleJsonobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(19);
						broker_value.setCellValue(vesseltypeobj1713);
						
						String vesseltypeobj17134 = "";
						if (brokercombineobj.has("Extra_ColumnObj")) {
							vesseltypeobj17134 = brokercombineobj.getString("Extra_ColumnObj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(20);
						broker_value.setCellValue(vesseltypeobj17134);
						
					}
				} //

			} // main if

			Date currentdate = new Date();
			DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String day_month_year=sdf.format(currentdate);

			String fileName="TimeCharterReports"+"Data"+"_"+day_month_year+".xls";
			fileName = fileName.replace(" ", "-");

			File linuxdirectory = new File("/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpioexcel");
			if (!linuxdirectory.exists()) {

				linuxdirectory.mkdir();
				out.println("folder created in linux");
			}

			// Write the output to a file
			// FileOutputStream fileOut = new
			// FileOutputStream("D:\\Scorpio\\Email\\"+fileName.trim());
			FileOutputStream fileOut = new FileOutputStream(
					"/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpioexcel/" + fileName.trim());
			workbook.write(fileOut);
			fileOut.close();

			// Closing the workbook
			workbook.close();

			final_obj = new JSONObject();
			final_obj.put("fileName", fileName.trim());
			final_obj.put("fileUrl", "https://dev.bizlem.io:8082/scorpioexcel/" + fileName.trim());
			final_obj.put("status", "S");

		} catch (Exception e) {
			return "{\"status\":\"E\",\"Message\" : " + e.getMessage() + "}";
		}
		return final_obj.toString();
		
	}

	public static String createExcelSpotNew(PrintWriter out, String fetchTonnageDaywiseData) {
		JSONObject final_obj = null;
		try {

			int rowIndex = 0;
			// Create a Workbook
			Workbook workbook = new HSSFWorkbook(); // XSSFWorkbook new
													// HSSFWorkbook() for
													// generating `.xls` file
			/*
			 * CreationHelper helps us create instances of various things like
			 * DataFormat, Hyperlink, RichTextString etc, in a format (HSSF,
			 * XSSF) independent way
			 */
			CreationHelper createHelper = workbook.getCreationHelper();

			// Create a Sheet
			Sheet sheet = workbook.createSheet("Spot-Summary");
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
			// String
			// brokerfetchdata=SlingMethods.fetchBrokerTcRateDaywiseData(out,
			// session);
			String tonnagefetchdata = fetchTonnageDaywiseData;
			// String
			// brokerfetchdata="{\"BrokerTcRateList\":[{\"BrokerTcRateId\":\"6\",\"dateAndTime\":\"Sun
			// Oct 08 18:56:35 UTC
			// 2018\",\"BrokerJsonarray\":[{\"Broker\":\"f\"}],\"VesselTypeJsonarray\":[{\"VesselType\":\"\"}],\"Month_YearJsonarray\":[{\"Month_Year\":\"\"}],\"first_yrJsonarray\":[{\"first_yr\":\"\"}],\"second_yrJsonarray\":[{\"second_yr\":\"\"}],\"third_yrJsonarray\":[{\"third_yr\":\"\"}],\"fifth_yrJsonarray\":[{\"fifth_yr\":\"\"}],\"subNodeName\":\"6\",\"size\":8,\"parseId\":7},{\"BrokerTcRateId\":\"7\",\"dateAndTime\":\"Mon
			// Oct 08 07:27:16 UTC
			// 2018\",\"BrokerJsonarray\":[{\"Broker\":\"a\"}],\"VesselTypeJsonarray\":[{\"VesselType\":\"b\"}],\"Month_YearJsonarray\":[{\"Month_Year\":\"c\"}],\"first_yrJsonarray\":[{\"first_yr\":\"d\"}],\"second_yrJsonarray\":[{\"second_yr\":\"f\"}],\"third_yrJsonarray\":[{\"third_yr\":\"g\"}],\"fifth_yrJsonarray\":[{\"fifth_yr\":\"h\"}],\"subNodeName\":\"7\",\"size\":8,\"parseId\":8}]}";

			JSONObject Tonnagejsonobj = new JSONObject(tonnagefetchdata);

			/*Cell Broker = brokertcratedatarow.createCell(0);
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
*/
			Cell Broker = brokertcratedatarow.createCell(0);
			Broker.setCellValue("Information");
			Broker.setCellStyle(headerCellStyle);

			Cell vesseltype = brokertcratedatarow.createCell(1);
			vesseltype.setCellValue("FixtureType");
			vesseltype.setCellStyle(headerCellStyle);

			Cell monthYear = brokertcratedatarow.createCell(2);
			monthYear.setCellValue("Charterer");
			monthYear.setCellStyle(headerCellStyle);

			Cell firstYear = brokertcratedatarow.createCell(3);
			firstYear.setCellValue("Status");
			firstYear.setCellStyle(headerCellStyle);

			Cell secondYear = brokertcratedatarow.createCell(4);
			secondYear.setCellValue("CargoType");
			secondYear.setCellStyle(headerCellStyle);

			Cell thirdYear = brokertcratedatarow.createCell(5);
			thirdYear.setCellValue("CargoGrade");
			thirdYear.setCellStyle(headerCellStyle);

			Cell fifthYear = brokertcratedatarow.createCell(6);
			fifthYear.setCellValue("CargoQty");
			fifthYear.setCellStyle(headerCellStyle);

			Cell OpenDate = brokertcratedatarow.createCell(7);
			OpenDate.setCellValue("LCStart");
			OpenDate.setCellStyle(headerCellStyle);

			Cell ReportType = brokertcratedatarow.createCell(8);
			ReportType.setCellValue("LCEnd");
			ReportType.setCellStyle(headerCellStyle);

			Cell ReportTimestamp = brokertcratedatarow.createCell(9);
			ReportTimestamp.setCellValue("LoadPort");
			ReportTimestamp.setCellStyle(headerCellStyle);

			Cell RepositionRegion = brokertcratedatarow.createCell(10);
			RepositionRegion.setCellValue("DiscPort");
			RepositionRegion.setCellStyle(headerCellStyle);

			Cell Source = brokertcratedatarow.createCell(11);
			Source.setCellValue("VesselName");
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

			Cell ReportTimestampSpot = brokertcratedatarow.createCell(18);
			ReportTimestampSpot.setCellValue("ReportTimestamp");
			ReportTimestampSpot.setCellStyle(headerCellStyle);
			
			Cell emailUrl = brokertcratedatarow.createCell(19);
			emailUrl.setCellValue("emailUrl");
			emailUrl.setCellStyle(headerCellStyle); //SingleJson
			
			Cell SingleJson = brokertcratedatarow.createCell(20);
			SingleJson.setCellValue("SingleJson");
			SingleJson.setCellStyle(headerCellStyle);
			
			Cell Extra_Column = brokertcratedatarow.createCell(21);
			Extra_Column.setCellValue("Extra_Column");
			Extra_Column.setCellStyle(headerCellStyle);
			
			
			if (Tonnagejsonobj.has("spotList")) {
				JSONArray combinebrokerjsonarray = new JSONArray();

				JSONArray tonnageList = Tonnagejsonobj.getJSONArray("spotList");
				for (int i = 0; i < tonnageList.length(); i++) {
					JSONObject combinebrokerobj = new JSONObject();

					JSONObject BrokerTcRateList_inside_jsonobj = tonnageList.getJSONObject(i);

					String broker = "";
					if (BrokerTcRateList_inside_jsonobj.has("Information")) {
						broker = BrokerTcRateList_inside_jsonobj.getString("Information");
						combinebrokerobj.put("Informationobj", broker);
					}

					String vesseltype_value = "";
					if (BrokerTcRateList_inside_jsonobj.has("FixtureType")) {
						vesseltype_value = BrokerTcRateList_inside_jsonobj.getString("FixtureType");
						combinebrokerobj.put("FixtureTypeobj", vesseltype_value);
					}

					String Month_Year_value = "";
					if (BrokerTcRateList_inside_jsonobj.has("LCStart")) {
						Month_Year_value = BrokerTcRateList_inside_jsonobj.getString("LCStart");
						combinebrokerobj.put("LCStartobj", Month_Year_value);
					}

					String firstyr_value = "";
					if (BrokerTcRateList_inside_jsonobj.has("LCEnd")) {
						firstyr_value = BrokerTcRateList_inside_jsonobj.getString("LCEnd");
						combinebrokerobj.put("LCEndobj", firstyr_value);
					}

					String firstyr_value1 = "";
					if (BrokerTcRateList_inside_jsonobj.has("LoadPort")) {
						firstyr_value1 = BrokerTcRateList_inside_jsonobj.getString("LoadPort");
						combinebrokerobj.put("LoadPortobj", firstyr_value1);
					}

					String firstyr_value2 = "";
					if (BrokerTcRateList_inside_jsonobj.has("DiscPort")) {
						firstyr_value2 = BrokerTcRateList_inside_jsonobj.getString("DiscPort");
						combinebrokerobj.put("DiscPortobj", firstyr_value2);
					}

					String firstyr_value12 = "";
					if (BrokerTcRateList_inside_jsonobj.has("VesselName")) {
						firstyr_value12 = BrokerTcRateList_inside_jsonobj.getString("VesselName");
						combinebrokerobj.put("VesselNameobj", firstyr_value12);
					}

					String firstyr_value13 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Charterer")) {
						firstyr_value13 = BrokerTcRateList_inside_jsonobj.getString("Charterer");
						combinebrokerobj.put("Chartererobj", firstyr_value13);
					}

					String firstyr_value14 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Status")) {
						firstyr_value14 = BrokerTcRateList_inside_jsonobj.getString("Status");
						combinebrokerobj.put("Statusobj", firstyr_value14);
					}

					String firstyr_value15 = "";
					if (BrokerTcRateList_inside_jsonobj.has("CargoType")) {
						firstyr_value15 = BrokerTcRateList_inside_jsonobj.getString("CargoType");
						combinebrokerobj.put("CargoTypeobj", firstyr_value15);
					}

					String firstyr_value16 = "";
					if (BrokerTcRateList_inside_jsonobj.has("CargoGrade")) {
						firstyr_value16 = BrokerTcRateList_inside_jsonobj.getString("CargoGrade");
						combinebrokerobj.put("CargoGradeobj", firstyr_value16);
					}

					String firstyr_value17 = "";
					if (BrokerTcRateList_inside_jsonobj.has("CargoQty")) {
						firstyr_value17 = BrokerTcRateList_inside_jsonobj.getString("CargoQty");
						combinebrokerobj.put("CargoQtyobj", firstyr_value17);
					}

					String firstyr_value18 = "";
					if (BrokerTcRateList_inside_jsonobj.has("RateType")) {
						firstyr_value18 = BrokerTcRateList_inside_jsonobj.getString("RateType");
						combinebrokerobj.put("RateTypeobj", firstyr_value18);
					}

					String firstyr_value19 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Rate")) {
						firstyr_value19 = BrokerTcRateList_inside_jsonobj.getString("Rate");
						combinebrokerobj.put("Rateobj", firstyr_value19);
					}

					String firstyr_value20 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Comments")) {
						firstyr_value20 = BrokerTcRateList_inside_jsonobj.getString("Comments");
						combinebrokerobj.put("Commentsobj", firstyr_value20);
					}

					String firstyr_value8 = "";
					if (BrokerTcRateList_inside_jsonobj.has("ReportDate")) {
						firstyr_value8 = BrokerTcRateList_inside_jsonobj.getString("ReportDate");
						combinebrokerobj.put("ReportDateobj", firstyr_value8);
					}

					String firstyr_value9 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Source")) {
						firstyr_value9 = BrokerTcRateList_inside_jsonobj.getString("Source");
						combinebrokerobj.put("Sourceobj", firstyr_value9);
					}

					String firstyr_value7 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Vesseltype")) {
						firstyr_value7 = BrokerTcRateList_inside_jsonobj.getString("Vesseltype");
						combinebrokerobj.put("Vesseltypeobj", firstyr_value7);
					}
					String firstyr_value81 = "";
					if (BrokerTcRateList_inside_jsonobj.has("ReportTimestamp")) {
						firstyr_value81 = BrokerTcRateList_inside_jsonobj.getString("ReportTimestamp");
						combinebrokerobj.put("ReportTimestampobj", firstyr_value81);
					}
					
					String firstyr_value144 = "";
					if (BrokerTcRateList_inside_jsonobj.has("SingleJson")) {
						firstyr_value144 = BrokerTcRateList_inside_jsonobj.getString("SingleJson");
						combinebrokerobj.put("SingleJsonobj", firstyr_value144);
					}
					
					
					String emailUrl1 = "";
					if (BrokerTcRateList_inside_jsonobj.has("emailUrl")) {
						emailUrl1 = BrokerTcRateList_inside_jsonobj.getString("emailUrl");
						combinebrokerobj.put("emailurlobj", emailUrl1);
					}
					String Extra_Column12 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Extra_Column")) {
						Extra_Column12 = BrokerTcRateList_inside_jsonobj.getString("Extra_Column");
						combinebrokerobj.put("Extra_ColumnObj", Extra_Column12);
					}

					if (combinebrokerobj.length() > 0) {
						combinebrokerjsonarray.put(combinebrokerobj);
					}

				} // main for

				if (combinebrokerjsonarray.length() > 0) {
					for (int j = 0; j < combinebrokerjsonarray.length(); j++) {
						JSONObject brokercombineobj = combinebrokerjsonarray.getJSONObject(j);

						String brokerobj = "";
						if (brokercombineobj.has("Informationobj")) {
							brokerobj = brokercombineobj.getString("Informationobj");
						}

						Row brokerrow = sheet.createRow((rowIndex + 1 + j));

						Cell broker_value = brokerrow.createCell(0);
						broker_value.setCellValue(brokerobj);

						String vesseltypeobj = "";
						if (brokercombineobj.has("FixtureTypeobj")) {
							vesseltypeobj = brokercombineobj.getString("FixtureTypeobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(1);
						broker_value.setCellValue(vesseltypeobj);

						String vesseltypeobj12 = "";
						if (brokercombineobj.has("Chartererobj")) {
							vesseltypeobj12 = brokercombineobj.getString("Chartererobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(2);
						broker_value.setCellValue(vesseltypeobj12);

						String vesseltypeobj13 = "";
						if (brokercombineobj.has("Statusobj")) {
							vesseltypeobj13 = brokercombineobj.getString("Statusobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(3);
						broker_value.setCellValue(vesseltypeobj13);

						String vesseltypeobj14 = "";
						if (brokercombineobj.has("CargoTypeobj")) {
							vesseltypeobj14 = brokercombineobj.getString("CargoTypeobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(4);
						broker_value.setCellValue(vesseltypeobj14);

						String vesseltypeobj15 = "";
						if (brokercombineobj.has("CargoGradeobj")) {
							vesseltypeobj15 = brokercombineobj.getString("CargoGradeobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(5);
						broker_value.setCellValue(vesseltypeobj15);

						String vesseltypeobj16 = "";
						if (brokercombineobj.has("CargoQtyobj")) {
							vesseltypeobj16 = brokercombineobj.getString("CargoQtyobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(6);
						broker_value.setCellValue(vesseltypeobj16);

						String vesseltypeobj17 = "";
						if (brokercombineobj.has("LCStartobj")) {
							vesseltypeobj17 = brokercombineobj.getString("LCStartobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(7);
						broker_value.setCellValue(vesseltypeobj17);

						String vesseltypeobj18 = "";
						if (brokercombineobj.has("LCEndobj")) {
							vesseltypeobj18 = brokercombineobj.getString("LCEndobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(8);
						broker_value.setCellValue(vesseltypeobj18);

						String vesseltypeobj19 = "";
						if (brokercombineobj.has("LoadPortobj")) {
							vesseltypeobj19 = brokercombineobj.getString("LoadPortobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(9);
						broker_value.setCellValue(vesseltypeobj19);

						String vesseltypeobj20 = "";
						if (brokercombineobj.has("DiscPortobj")) {
							vesseltypeobj20 = brokercombineobj.getString("DiscPortobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(10);
						broker_value.setCellValue(vesseltypeobj20);

						String vesseltypeobj2 = "";
						if (brokercombineobj.has("VesselNameobj")) {
							vesseltypeobj2 = brokercombineobj.getString("VesselNameobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(11);
						broker_value.setCellValue(vesseltypeobj2);

						String vesseltypeobj3 = "";
						if (brokercombineobj.has("RateTypeobj")) {
							vesseltypeobj3 = brokercombineobj.getString("RateTypeobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(12);
						broker_value.setCellValue(vesseltypeobj3);

						String vesseltypeobj4 = "";
						if (brokercombineobj.has("Rateobj")) {
							vesseltypeobj4 = brokercombineobj.getString("Rateobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(13);
						broker_value.setCellValue(vesseltypeobj4);

						String vesseltypeobj5 = "";
						if (brokercombineobj.has("Commentsobj")) {
							vesseltypeobj5 = brokercombineobj.getString("Commentsobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(14);
						broker_value.setCellValue(vesseltypeobj5);

						String vesseltypeobj6 = "";
						if (brokercombineobj.has("ReportDateobj")) {
							vesseltypeobj6 = brokercombineobj.getString("ReportDateobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(15);
						broker_value.setCellValue(vesseltypeobj6);

						String vesseltypeobj7 = "";
						if (brokercombineobj.has("Sourceobj")) {
							vesseltypeobj7 = brokercombineobj.getString("Sourceobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(16);
						broker_value.setCellValue(vesseltypeobj7);

						String vesseltypeobj8 = "";
						if (brokercombineobj.has("Vesseltypeobj")) {
							vesseltypeobj8 = brokercombineobj.getString("Vesseltypeobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(17);
						broker_value.setCellValue(vesseltypeobj8);
						
						String vesseltypeobj9 = "";
						if (brokercombineobj.has("ReportTimestampobj")) {
							vesseltypeobj9 = brokercombineobj.getString("ReportTimestampobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(18);
						broker_value.setCellValue(vesseltypeobj9);

						String vesseltypeobj1712 = "";
						if (brokercombineobj.has("emailurlobj")) {
							vesseltypeobj1712 = brokercombineobj.getString("emailurlobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(19);
						broker_value.setCellValue(vesseltypeobj1712);

						String vesseltypeobj1713 = "";
						if (brokercombineobj.has("SingleJsonobj")) {
							vesseltypeobj1713 = brokercombineobj.getString("SingleJsonobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(20);
						broker_value.setCellValue(vesseltypeobj1713);
						
						
						String vesseltypeobj17134 = "";
						if (brokercombineobj.has("Extra_ColumnObj")) {
							vesseltypeobj17134 = brokercombineobj.getString("Extra_ColumnObj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(21);
						broker_value.setCellValue(vesseltypeobj17134);
						
					}
				} //

			} // main if

			Date currentdate = new Date();
			DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String day_month_year=sdf.format(currentdate);

			String fileName = "Spot" + "Data" + "_" + day_month_year + ".xls";
			fileName = fileName.replace(" ", "-");

			File linuxdirectory = new File("/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpioexcel");
			if (!linuxdirectory.exists()) {

				linuxdirectory.mkdir();
				out.println("folder created in linux");
			}

			// Write the output to a file
			// FileOutputStream fileOut = new
			// FileOutputStream("D:\\Scorpio\\Email\\"+fileName.trim());
			FileOutputStream fileOut = new FileOutputStream(
					"/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpioexcel/" + fileName.trim());
			workbook.write(fileOut);
			fileOut.close();

			// Closing the workbook
			workbook.close();

			final_obj = new JSONObject();
			final_obj.put("fileName", fileName.trim());
			final_obj.put("fileUrl", "https://dev.bizlem.io:8082/scorpioexcel/" + fileName.trim());
			final_obj.put("status", "S");

		} catch (Exception e) {
			return "{\"status\":\"E\",\"Message\" : " + e.getMessage() + "}";
		}
		return final_obj.toString();
	}

	public static String createExcelTonnageNew(PrintWriter out, String fetchTonnageDaywiseData) {
		JSONObject final_obj = null;
		try {

			int rowIndex = 0;
			// Create a Workbook
			Workbook workbook = new HSSFWorkbook(); // XSSFWorkbook new
													// HSSFWorkbook() for
													// generating `.xls` file
			/*
			 * CreationHelper helps us create instances of various things like
			 * DataFormat, Hyperlink, RichTextString etc, in a format (HSSF,
			 * XSSF) independent way
			 */
			CreationHelper createHelper = workbook.getCreationHelper();

			// Create a Sheet
			Sheet sheet = workbook.createSheet("Tonnage-Summary");
			// Create a Font for styling header cells
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
	        /*CellStyle style = workbook.createCellStyle();
	        Font font = workbook.createFont();
	        font.setColor(HSSFColor.HSSFColorPredefined.GREY_50_PERCENT.getIndex());
	        style.setFont(font);*/
			
			 CellStyle style = workbook.createCellStyle();
			 style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			 style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			 

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
			// String
			// brokerfetchdata=SlingMethods.fetchBrokerTcRateDaywiseData(out,
			// session);
			String tonnagefetchdata = fetchTonnageDaywiseData;
			// String
			// brokerfetchdata="{\"BrokerTcRateList\":[{\"BrokerTcRateId\":\"6\",\"dateAndTime\":\"Sun
			// Oct 08 18:56:35 UTC
			// 2018\",\"BrokerJsonarray\":[{\"Broker\":\"f\"}],\"VesselTypeJsonarray\":[{\"VesselType\":\"\"}],\"Month_YearJsonarray\":[{\"Month_Year\":\"\"}],\"first_yrJsonarray\":[{\"first_yr\":\"\"}],\"second_yrJsonarray\":[{\"second_yr\":\"\"}],\"third_yrJsonarray\":[{\"third_yr\":\"\"}],\"fifth_yrJsonarray\":[{\"fifth_yr\":\"\"}],\"subNodeName\":\"6\",\"size\":8,\"parseId\":7},{\"BrokerTcRateId\":\"7\",\"dateAndTime\":\"Mon
			// Oct 08 07:27:16 UTC
			// 2018\",\"BrokerJsonarray\":[{\"Broker\":\"a\"}],\"VesselTypeJsonarray\":[{\"VesselType\":\"b\"}],\"Month_YearJsonarray\":[{\"Month_Year\":\"c\"}],\"first_yrJsonarray\":[{\"first_yr\":\"d\"}],\"second_yrJsonarray\":[{\"second_yr\":\"f\"}],\"third_yrJsonarray\":[{\"third_yr\":\"g\"}],\"fifth_yrJsonarray\":[{\"fifth_yr\":\"h\"}],\"subNodeName\":\"7\",\"size\":8,\"parseId\":8}]}";

			JSONObject Tonnagejsonobj = new JSONObject(tonnagefetchdata);

			/*Cell Broker = brokertcratedatarow.createCell(0);
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
			VesselType.setCellStyle(headerCellStyle);*/
			
			Cell Broker = brokertcratedatarow.createCell(0);
			Broker.setCellValue("ReportType");
			Broker.setCellStyle(headerCellStyle);

			Cell vesseltype = brokertcratedatarow.createCell(1);
			vesseltype.setCellValue("RepositionRegion");
			vesseltype.setCellStyle(headerCellStyle);

			Cell LOA = brokertcratedatarow.createCell(2);
			LOA.setCellValue("ETABasis");
			LOA.setCellStyle(headerCellStyle);
			
			Cell monthYear = brokertcratedatarow.createCell(3);
			monthYear.setCellValue("CargoType");
			monthYear.setCellStyle(headerCellStyle);

			Cell firstYear = brokertcratedatarow.createCell(4);
			firstYear.setCellValue("VesselName");
			firstYear.setCellStyle(headerCellStyle);

			Cell secondYear = brokertcratedatarow.createCell(5);
			secondYear.setCellValue("VesselType");
			secondYear.setCellStyle(headerCellStyle);

			Cell thirdYear = brokertcratedatarow.createCell(6);
			thirdYear.setCellValue("Built");
			thirdYear.setCellStyle(headerCellStyle);

			Cell fifthYear = brokertcratedatarow.createCell(7);
			fifthYear.setCellValue("DWT");
			fifthYear.setCellStyle(headerCellStyle);

			Cell OpenDate = brokertcratedatarow.createCell(8);
			OpenDate.setCellValue("Cubics");
			OpenDate.setCellStyle(headerCellStyle);

			Cell ReportType = brokertcratedatarow.createCell(9);
			ReportType.setCellValue("LOA");
			ReportType.setCellStyle(headerCellStyle);

			Cell ReportTimestamp = brokertcratedatarow.createCell(10);
			ReportTimestamp.setCellValue("ICE");
			ReportTimestamp.setCellStyle(headerCellStyle);

			Cell RepositionRegion = brokertcratedatarow.createCell(11);
			RepositionRegion.setCellValue("SternLine");
			RepositionRegion.setCellStyle(headerCellStyle);

			Cell Source = brokertcratedatarow.createCell(12);
			Source.setCellValue("Owners");
			Source.setCellStyle(headerCellStyle);

			Cell VesselName = brokertcratedatarow.createCell(13);
			VesselName.setCellValue("Operators");
			VesselName.setCellStyle(headerCellStyle);

			Cell Built = brokertcratedatarow.createCell(14);
			Built.setCellValue("EmploymentStatus");
			Built.setCellStyle(headerCellStyle);

			Cell DWT = brokertcratedatarow.createCell(15);
			DWT.setCellValue("OpenPort");
			DWT.setCellStyle(headerCellStyle);

			Cell Cubics = brokertcratedatarow.createCell(16);
			Cubics.setCellValue("OpenDate");
			Cubics.setCellStyle(headerCellStyle);

			

			Cell ICE = brokertcratedatarow.createCell(17);
			ICE.setCellValue("Comment");
			ICE.setCellStyle(headerCellStyle);

			Cell SternLine = brokertcratedatarow.createCell(18);
			SternLine.setCellValue("Source");
			SternLine.setCellStyle(headerCellStyle);

			Cell VesselType = brokertcratedatarow.createCell(19);
			VesselType.setCellValue("ReportTimestamp");
			VesselType.setCellStyle(headerCellStyle);
			
			Cell emailUrl = brokertcratedatarow.createCell(20);
			emailUrl.setCellValue("emailUrl");
			emailUrl.setCellStyle(headerCellStyle); 
			
			Cell SingleJson = brokertcratedatarow.createCell(21);
			SingleJson.setCellValue("SingleJson");
			SingleJson.setCellStyle(headerCellStyle);
			
			Cell Extra_Column = brokertcratedatarow.createCell(22);
			Extra_Column.setCellValue("Extra_Column");
			Extra_Column.setCellStyle(headerCellStyle);

			if (Tonnagejsonobj.has("tonnageList")) {
				JSONArray combinebrokerjsonarray = new JSONArray();

				JSONArray tonnageList = Tonnagejsonobj.getJSONArray("tonnageList");
				for (int i = 0; i < tonnageList.length(); i++) {
					JSONObject combinebrokerobj = new JSONObject();

					JSONObject BrokerTcRateList_inside_jsonobj = tonnageList.getJSONObject(i);
					String broker = "";
					if (BrokerTcRateList_inside_jsonobj.has("EmploymentStatus")) {
						broker = BrokerTcRateList_inside_jsonobj.getString("EmploymentStatus");
						combinebrokerobj.put("EmploymentStatusobj", broker);
					}

					String vesseltype_value = "";
					if (BrokerTcRateList_inside_jsonobj.has("Owners")) {
						vesseltype_value = BrokerTcRateList_inside_jsonobj.getString("Owners");
						combinebrokerobj.put("Ownersobj", vesseltype_value);
					}

					String Month_Year_value = "";
					if (BrokerTcRateList_inside_jsonobj.has("OpenPort")) {
						Month_Year_value = BrokerTcRateList_inside_jsonobj.getString("OpenPort");
						combinebrokerobj.put("OpenPortobj", Month_Year_value);
					}

					String firstyr_value = "";
					if (BrokerTcRateList_inside_jsonobj.has("Operators")) {
						firstyr_value = BrokerTcRateList_inside_jsonobj.getString("Operators");
						combinebrokerobj.put("Operatorsobj", firstyr_value);
					}
					String CargoType = "";
					if (BrokerTcRateList_inside_jsonobj.has("CargoType")) {
						CargoType = BrokerTcRateList_inside_jsonobj.getString("CargoType");
						combinebrokerobj.put("CargoTypeobj", CargoType);
					}
					String Comment = "";
					if (BrokerTcRateList_inside_jsonobj.has("Comment")) {
						Comment = BrokerTcRateList_inside_jsonobj.getString("Comment");
						combinebrokerobj.put("Commentobj", Comment);
					}

					String firstyr_value6 = "";
					if (BrokerTcRateList_inside_jsonobj.has("ETABasis")) {
						firstyr_value6 = BrokerTcRateList_inside_jsonobj.getString("ETABasis");
						combinebrokerobj.put("ETABasisobj", firstyr_value6);
					}

					String firstyr_value5 = "";
					if (BrokerTcRateList_inside_jsonobj.has("OpenDate")) {
						firstyr_value5 = BrokerTcRateList_inside_jsonobj.getString("OpenDate");
						combinebrokerobj.put("OpenDateobj", firstyr_value5);
					}

					String firstyr_value4 = "";
					if (BrokerTcRateList_inside_jsonobj.has("ReportType")) {
						firstyr_value4 = BrokerTcRateList_inside_jsonobj.getString("ReportType");
						combinebrokerobj.put("ReportTypeobj", firstyr_value4);
					}

					String firstyr_value3 = "";
					if (BrokerTcRateList_inside_jsonobj.has("ReportType")) {
						firstyr_value3 = BrokerTcRateList_inside_jsonobj.getString("ReportTimestamp");
						combinebrokerobj.put("ReportTimestampobj", firstyr_value3);
					}

					String firstyr_value2 = "";
					if (BrokerTcRateList_inside_jsonobj.has("RepositionRegion")) {
						firstyr_value2 = BrokerTcRateList_inside_jsonobj.getString("RepositionRegion");
						combinebrokerobj.put("RepositionRegionobj", firstyr_value2);
					}

					String firstyr_value1 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Source")) {
						firstyr_value1 = BrokerTcRateList_inside_jsonobj.getString("Source");
						combinebrokerobj.put("Sourceobj", firstyr_value1);
					}

					String VesselName1 = "";
					if (BrokerTcRateList_inside_jsonobj.has("VesselName")) {
						VesselName1 = BrokerTcRateList_inside_jsonobj.getString("VesselName");
						combinebrokerobj.put("VesselNameobj", VesselName1);
					}

					String Built1 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Built")) {
						Built1 = BrokerTcRateList_inside_jsonobj.getString("Built");
						combinebrokerobj.put("Builtobj", Built1);
					}

					String firstyr_value7 = "";
					if (BrokerTcRateList_inside_jsonobj.has("DWT")) {
						firstyr_value7 = BrokerTcRateList_inside_jsonobj.getString("DWT");
						combinebrokerobj.put("DWTobj", firstyr_value7);
					}

					String firstyr_value8 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Cubics")) {
						firstyr_value8 = BrokerTcRateList_inside_jsonobj.getString("Cubics");
						combinebrokerobj.put("Cubicsobj", firstyr_value8);
					}

					String firstyr_value9 = "";
					if (BrokerTcRateList_inside_jsonobj.has("LOA")) {
						firstyr_value9 = BrokerTcRateList_inside_jsonobj.getString("LOA");
						combinebrokerobj.put("LOAobj", firstyr_value9);
					}

					String firstyr_value10 = "";
					if (BrokerTcRateList_inside_jsonobj.has("ICE")) {
						firstyr_value10 = BrokerTcRateList_inside_jsonobj.getString("ICE");
						combinebrokerobj.put("ICEobj", firstyr_value10);
					}

					String firstyr_value11 = "";
					if (BrokerTcRateList_inside_jsonobj.has("SternLine")) {
						firstyr_value11 = BrokerTcRateList_inside_jsonobj.getString("SternLine");
						combinebrokerobj.put("SternLineobj", firstyr_value11);
					}

					String firstyr_value12 = "";
					if (BrokerTcRateList_inside_jsonobj.has("VesselType")) {
						firstyr_value12 = BrokerTcRateList_inside_jsonobj.getString("VesselType");
						combinebrokerobj.put("VesselTypeeobj", firstyr_value12);
					}
					
					String emailUrl1 = "";
					if (BrokerTcRateList_inside_jsonobj.has("emailUrl")) {
						emailUrl1 = BrokerTcRateList_inside_jsonobj.getString("emailUrl");
						combinebrokerobj.put("emailurlobj", emailUrl1);
					}
					
					
					String firstyr_value13 = "";
					if (BrokerTcRateList_inside_jsonobj.has("SingleJson")) {
						firstyr_value13 = BrokerTcRateList_inside_jsonobj.getString("SingleJson");
						combinebrokerobj.put("SingleJsonobj", firstyr_value13);
					}
					
					String Extra_Column12 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Extra_Column")) {
						Extra_Column12 = BrokerTcRateList_inside_jsonobj.getString("Extra_Column");
						combinebrokerobj.put("Extra_ColumnObj", Extra_Column12);
					}

					if (combinebrokerobj.length() > 0) {
						combinebrokerjsonarray.put(combinebrokerobj);
					}

				} // main for

				if (combinebrokerjsonarray.length() > 0) {
					for (int j = 0; j < combinebrokerjsonarray.length(); j++) {
						JSONObject brokercombineobj = combinebrokerjsonarray.getJSONObject(j);

						String brokerobj = "";
						if (brokercombineobj.has("ReportTypeobj")) {
							brokerobj = brokercombineobj.getString("ReportTypeobj");
						}

						Row brokerrow = sheet.createRow((rowIndex + 1 + j));

						Cell broker_value = brokerrow.createCell(0);
						broker_value.setCellValue(brokerobj);

						String vesseltypeobj = "";
						if (brokercombineobj.has("RepositionRegionobj")) {
							vesseltypeobj = brokercombineobj.getString("RepositionRegionobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(1);
						broker_value.setCellValue(vesseltypeobj);

						String vesseltypeobj14 = "";
						if (brokercombineobj.has("ETABasisobj")) {
							vesseltypeobj14 = brokercombineobj.getString("ETABasisobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(2);
						broker_value.setCellValue(vesseltypeobj14);
						
						
						String vesseltypeobj1 = "";
						if (brokercombineobj.has("CargoTypeobj")) {
							vesseltypeobj1 = brokercombineobj.getString("CargoTypeobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(3);
						broker_value.setCellValue(vesseltypeobj1);

						String vesseltypeobj2 = "";
						if (brokercombineobj.has("VesselNameobj")) {
							vesseltypeobj2 = brokercombineobj.getString("VesselNameobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(4);
						if("Route Rate".equalsIgnoreCase(vesseltypeobj2)){
//							row.getCell(0).setCellStyle(style);
							broker_value.setCellStyle(style);
						}
						broker_value.setCellValue(vesseltypeobj2);

						String vesseltypeobj3 = "";
						if (brokercombineobj.has("VesselTypeeobj")) {
							vesseltypeobj3 = brokercombineobj.getString("VesselTypeeobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(5);
						broker_value.setCellValue(vesseltypeobj3);

						String vesseltypeobj4 = "";
						if (brokercombineobj.has("Builtobj")) {
							vesseltypeobj4 = brokercombineobj.getString("Builtobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(6);
						broker_value.setCellValue(vesseltypeobj4);

						String vesseltypeobj5 = "";
						if (brokercombineobj.has("DWTobj")) {
							vesseltypeobj5 = brokercombineobj.getString("DWTobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(7);
						broker_value.setCellValue(vesseltypeobj5);

						String vesseltypeobj6 = "";
						if (brokercombineobj.has("Cubicsobj")) {
							vesseltypeobj6 = brokercombineobj.getString("Cubicsobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(8);
						broker_value.setCellValue(vesseltypeobj6);

						String vesseltypeobj17 = "";
						if (brokercombineobj.has("LOAobj")) {
							vesseltypeobj17 = brokercombineobj.getString("LOAobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(9);
						broker_value.setCellValue(vesseltypeobj17);

						String vesseltypeobj7 = "";
						if (brokercombineobj.has("ICEobj")) {
							vesseltypeobj7 = brokercombineobj.getString("ICEobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(10);
						broker_value.setCellValue(vesseltypeobj7);

						String vesseltypeobj8 = "";
						if (brokercombineobj.has("SternLineobj")) {
							vesseltypeobj8 = brokercombineobj.getString("SternLineobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(11);
						broker_value.setCellValue(vesseltypeobj8);

						String vesseltypeobj9 = "";
						if (brokercombineobj.has("Ownersobj")) {
							vesseltypeobj9 = brokercombineobj.getString("Ownersobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(12);
						broker_value.setCellValue(vesseltypeobj9);

						String vesseltypeobj10 = "";
						if (brokercombineobj.has("Operatorsobj")) {
							vesseltypeobj10 = brokercombineobj.getString("Operatorsobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(13);
						broker_value.setCellValue(vesseltypeobj10);

						String vesseltypeobj11 = "";
						if (brokercombineobj.has("EmploymentStatusobj")) {
							vesseltypeobj11 = brokercombineobj.getString("EmploymentStatusobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(14);
						broker_value.setCellValue(vesseltypeobj11);

						String vesseltypeobj12 = "";
						if (brokercombineobj.has("OpenPortobj")) {
							vesseltypeobj12 = brokercombineobj.getString("OpenPortobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(15);
						broker_value.setCellValue(vesseltypeobj12);

						String vesseltypeobj13 = "";
						if (brokercombineobj.has("OpenDateobj")) {
							vesseltypeobj13 = brokercombineobj.getString("OpenDateobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(16);
						broker_value.setCellValue(vesseltypeobj13);

						

						String vesseltypeobj15 = "";
						if (brokercombineobj.has("Commentobj")) {
							vesseltypeobj15 = brokercombineobj.getString("Commentobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(17);
						broker_value.setCellValue(vesseltypeobj15);

						String vesseltypeobj16 = "";
						if (brokercombineobj.has("Sourceobj")) {
							vesseltypeobj16 = brokercombineobj.getString("Sourceobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(18);
						broker_value.setCellValue(vesseltypeobj16);

						String vesseltypeobj171 = "";
						if (brokercombineobj.has("ReportTimestampobj")) {
							vesseltypeobj171 = brokercombineobj.getString("ReportTimestampobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(19);
						broker_value.setCellValue(vesseltypeobj171);

						String vesseltypeobj1712 = "";
						if (brokercombineobj.has("emailurlobj")) {
							vesseltypeobj1712 = brokercombineobj.getString("emailurlobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(20);
						broker_value.setCellValue(vesseltypeobj1712);

						String vesseltypeobj1713 = "";
						if (brokercombineobj.has("SingleJsonobj")) {
							vesseltypeobj1713 = brokercombineobj.getString("SingleJsonobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(21);
						broker_value.setCellValue(vesseltypeobj1713);
						
						
						String vesseltypeobj17134 = "";
						if (brokercombineobj.has("Extra_ColumnObj")) {
							vesseltypeobj17134 = brokercombineobj.getString("Extra_ColumnObj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(22);
						broker_value.setCellValue(vesseltypeobj17134);
						
					}

				} //

			} // if

			Date currentdate = new Date();
			DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String day_month_year=sdf.format(currentdate);

			String fileName = "Tonnage" + "Data" + "_" + day_month_year + ".xls";
			fileName = fileName.replace(" ", "-");

			File linuxdirectory = new File("/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpioexcel");
			if (!linuxdirectory.exists()) {

				linuxdirectory.mkdir();
				out.println("folder created in linux");
			}

			// Write the output to a file
			// FileOutputStream fileOut = new
			// FileOutputStream("D:\\Scorpio\\Email\\"+fileName.trim());
			FileOutputStream fileOut = new FileOutputStream(
					"/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpioexcel/" + fileName.trim());
			workbook.write(fileOut);
			fileOut.close();

			// Closing the workbook
			workbook.close();

			final_obj = new JSONObject();
			final_obj.put("fileName", fileName.trim());
			final_obj.put("fileUrl", "https://dev.bizlem.io:8082/scorpioexcel/" + fileName.trim());
			final_obj.put("status", "S");

		} catch (Exception e) {
			return "{\"status\":\"E\",\"Message\" : " + e.getMessage() + "}";
		}
		return final_obj.toString();
	}

	public static String createExcelBrokerTcRateNew(PrintWriter out, String fetchBrokerTcRateDaywiseData) {
		JSONObject final_obj = null;
		try {

			int rowIndex = 0;
			// Create a Workbook
			Workbook workbook = new HSSFWorkbook(); // XSSFWorkbook new
													// HSSFWorkbook() for
													// generating `.xls` file
			/*
			 * CreationHelper helps us create instances of various things like
			 * DataFormat, Hyperlink, RichTextString etc, in a format (HSSF,
			 * XSSF) independent way
			 */
			CreationHelper createHelper = workbook.getCreationHelper();

			// Create a Sheet
			Sheet sheet = workbook.createSheet("BrokerTcRate-Summary");
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

			String brokerfetchdata = fetchBrokerTcRateDaywiseData;

			JSONObject brokertcratejsonobj = new JSONObject(brokerfetchdata);

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
			
			Cell emailUrl = brokertcratedatarow.createCell(7);
			emailUrl.setCellValue("emailUrl");
			emailUrl.setCellStyle(headerCellStyle); //SingleJson
			
			Cell SingleJson = brokertcratedatarow.createCell(8);
			SingleJson.setCellValue("SingleJson");
			SingleJson.setCellStyle(headerCellStyle);
			
			Cell Extra_Column = brokertcratedatarow.createCell(9);
			Extra_Column.setCellValue("Extra_Column");
			Extra_Column.setCellStyle(headerCellStyle);

			if (brokertcratejsonobj.has("BrokerTcRateList")) {
				JSONArray combinebrokerjsonarray = new JSONArray();
				JSONArray BrokerTcRateList = brokertcratejsonobj.getJSONArray("BrokerTcRateList");

				for (int i = 0; i < BrokerTcRateList.length(); i++) {
					JSONObject combinebrokerobj = new JSONObject();

					JSONObject BrokerTcRateList_inside_jsonobj = BrokerTcRateList.getJSONObject(i);
					String broker = "";
					if (BrokerTcRateList_inside_jsonobj.has("Broker")) {
						broker = BrokerTcRateList_inside_jsonobj.getString("Broker");
						combinebrokerobj.put("brokerobj", broker);
					}
					String vesseltype_value = "";
					if (BrokerTcRateList_inside_jsonobj.has("VesselType")) {
						vesseltype_value = BrokerTcRateList_inside_jsonobj.getString("VesselType");
						combinebrokerobj.put("vesseltypeobj", vesseltype_value);
					}

					String Month_Year_value = "";
					if (BrokerTcRateList_inside_jsonobj.has("Month_Year")) {
						Month_Year_value = BrokerTcRateList_inside_jsonobj.getString("Month_Year");
						combinebrokerobj.put("Month_Yearobj", Month_Year_value);
					}
					String firstyr_value = "";
					if (BrokerTcRateList_inside_jsonobj.has("first_yr")) {
						firstyr_value = BrokerTcRateList_inside_jsonobj.getString("first_yr");
						combinebrokerobj.put("firstyrobj", firstyr_value);
					}
					String second_value = "";
					if (BrokerTcRateList_inside_jsonobj.has("second_yr")) {
						second_value = BrokerTcRateList_inside_jsonobj.getString("second_yr");
						combinebrokerobj.put("secondyrobj", second_value);
					}
					String third_value = "";
					if (BrokerTcRateList_inside_jsonobj.has("third_yr")) {
						third_value = BrokerTcRateList_inside_jsonobj.getString("third_yr");
						combinebrokerobj.put("thirdyrobj", third_value);
					}
					String fifth_value = "";
					if (BrokerTcRateList_inside_jsonobj.has("fifth_yr")) {
						fifth_value = BrokerTcRateList_inside_jsonobj.getString("fifth_yr");
						combinebrokerobj.put("fifthyrobj", fifth_value);
					}
					
					String firstyr_value144 = "";
					if (BrokerTcRateList_inside_jsonobj.has("SingleJson")) {
						firstyr_value144 = BrokerTcRateList_inside_jsonobj.getString("SingleJson");
						combinebrokerobj.put("SingleJsonobj", firstyr_value144);
					}
					
					
					String emailUrl1 = "";
					if (BrokerTcRateList_inside_jsonobj.has("emailUrl")) {
						emailUrl1 = BrokerTcRateList_inside_jsonobj.getString("emailUrl");
						combinebrokerobj.put("emailurlobj", emailUrl1);
					}
					String Extra_Column12 = "";
					if (BrokerTcRateList_inside_jsonobj.has("Extra_Column")) {
						Extra_Column12 = BrokerTcRateList_inside_jsonobj.getString("Extra_Column");
						combinebrokerobj.put("Extra_ColumnObj", Extra_Column12);
					}

					if (combinebrokerobj.length() > 0) {
						combinebrokerjsonarray.put(combinebrokerobj);
					}

				} // main for

				if (combinebrokerjsonarray.length() > 0) {
					for (int j = 0; j < combinebrokerjsonarray.length(); j++) {
						JSONObject brokercombineobj = combinebrokerjsonarray.getJSONObject(j);
						String brokerobj = "";
						if (brokercombineobj.has("brokerobj")) {
							brokerobj = brokercombineobj.getString("brokerobj");
						}

						Row brokerrow = sheet.createRow((rowIndex + 1 + j));

						Cell broker_value = brokerrow.createCell(0);
						broker_value.setCellValue(brokerobj);

						String vesseltypeobj = "";
						if (brokercombineobj.has("vesseltypeobj")) {
							vesseltypeobj = brokercombineobj.getString("vesseltypeobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(1);
						broker_value.setCellValue(vesseltypeobj);

						String Month_Yearobj = "";
						if (brokercombineobj.has("Month_Yearobj")) {
							Month_Yearobj = brokercombineobj.getString("Month_Yearobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(2);
						broker_value.setCellValue(Month_Yearobj);

						String firstyrobj = "";
						if (brokercombineobj.has("firstyrobj")) {
							firstyrobj = brokercombineobj.getString("firstyrobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(3);
						broker_value.setCellValue(firstyrobj);

						String secondyrobj = "";
						if (brokercombineobj.has("secondyrobj")) {
							secondyrobj = brokercombineobj.getString("secondyrobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(4);
						broker_value.setCellValue(secondyrobj);

						String thirdyrobj = "";
						if (brokercombineobj.has("thirdyrobj")) {
							thirdyrobj = brokercombineobj.getString("thirdyrobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(5);
						broker_value.setCellValue(thirdyrobj);

						String fifthyrobj = "";
						if (brokercombineobj.has("fifthyrobj")) {
							fifthyrobj = brokercombineobj.getString("fifthyrobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(6);
						broker_value.setCellValue(fifthyrobj);

						String vesseltypeobj1712 = "";
						if (brokercombineobj.has("emailurlobj")) {
							vesseltypeobj1712 = brokercombineobj.getString("emailurlobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(7);
						broker_value.setCellValue(vesseltypeobj1712);

						String vesseltypeobj1713 = "";
						if (brokercombineobj.has("SingleJsonobj")) {
							vesseltypeobj1713 = brokercombineobj.getString("SingleJsonobj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(8);
						broker_value.setCellValue(vesseltypeobj1713);
						
						String vesseltypeobj17134 = "";
						if (brokercombineobj.has("Extra_ColumnObj")) {
							vesseltypeobj17134 = brokercombineobj.getString("Extra_ColumnObj");
						}

						brokerrow = sheet.getRow((rowIndex + 1 + j));
						if (brokerrow == null) {
							brokerrow = sheet.createRow((rowIndex + 1 + j));
						}

						broker_value = brokerrow.createCell(9);
						broker_value.setCellValue(vesseltypeobj17134);
						
					}
				} // length check zero

			} // main if

			Date currentdate = new Date();
			DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String day_month_year=sdf.format(currentdate);

			String fileName = "broker" + "tcrate" + "_" +day_month_year+ ".xls";
			fileName = fileName.replace(" ", "-");

			File linuxdirectory = new File("/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpioexcel");
			if (!linuxdirectory.exists()) {

				linuxdirectory.mkdir();
				out.println("folder created in linux");
			}

			// Write the output to a file
			// FileOutputStream fileOut = new
			// FileOutputStream("D:\\Scorpio\\Email\\"+fileName.trim());
			FileOutputStream fileOut = new FileOutputStream(
					"/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpioexcel/" + fileName.trim());
			workbook.write(fileOut);
			fileOut.close();

			// Closing the workbook
			workbook.close();

			final_obj = new JSONObject();
			final_obj.put("fileName", fileName.trim());
			final_obj.put("fileUrl", "https://dev.bizlem.io:8082/scorpioexcel/" + fileName.trim());
			final_obj.put("status", "S");

		} catch (Exception e) {
			return "{\"status\":\"E\",\"Message\" : " + e.getMessage() + "}";
		}
		return final_obj.toString();
	}

}
