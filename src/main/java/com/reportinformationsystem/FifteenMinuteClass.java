package com.reportinformationsystem;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.Workspace;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Message.RecipientType;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.SearchTerm;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.readGmail.GmailMethods;
import com.readGmail.Gmail_Pojo;

import ChangedStructureCurrent.ChangedStructureCurrent_Methods;
import ChangedStructureCurrent.FetchData;
import ChangedStructureCurrent.GmailReadMailChanged;

public class FifteenMinuteClass {

	public static void main(String[] args) throws ParseException {
		/*
		 * JSONObject data1=CurrentDateTime(); System.out.println(data1); Date
		 * d=new Date(); boolean data=returnTimeRange(d);
		 * 
		 * if(data==true){ System.out.println("yes"); }else{
		 * System.out.println("not"); }
		 * 
		 * // System.out.println(data);
		 * 
		 * 
		 * Calendar cal = null; cal = Calendar.getInstance(); Date todayDate =
		 * new Date(cal.getTimeInMillis()); DateFormat dateFormat = new
		 * SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); String currentDate =
		 * dateFormat.format(todayDate);
		 * System.out.println("currentDate: "+currentDate);
		 * 
		 * 
		 * 
		 * SimpleDateFormat formatter1=new
		 * SimpleDateFormat("dd-MM-yyyy HH:mm:ss"); Date
		 * date1=formatter1.parse(currentDate);
		 * System.out.println("date1: "+date1);
		 * 
		 * DateTimeFormatter formatter =
		 * DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss" , Locale.ENGLISH);
		 * LocalDate date = LocalDate.parse(currentDate, formatter);
		 * System.out.println(date);
		 * 
		 * Date date11 = new Date(dateFormat.parse(currentDate).getTime());
		 * System.out.println(date11);
		 */

		String space = DateSpach("8 jan", "2018");
		System.out.println(space);

		/*
		 * String d=dateDot("28.01.20", "2018"); System.out.println("d:: "+d);
		 */

		dateSpaceSingle("8 jan 2018", "2018");

		String ReportTimestamp = "12/23/1087";
		ReportTimestamp = ReportTimestamp.substring(ReportTimestamp.lastIndexOf("/") + 1);
		System.out.println(ReportTimestamp);
		// slingDateStructure();
		String c = checkSpaceThree("24 oct", "2018");
		System.out.println("c:: " + c);

		String datatwo = twoDate("oct 27", "2018");
		System.out.println("datatwo:: " + datatwo);

		String datatwoSlash = twoDateSlash("1/27", "2018");
		System.out.println("datatwoSlash:: " + datatwoSlash);

		String data = "2018-12-19T13:55:35.000+05:30";
		if (data.lastIndexOf(".") != -1) {
			data = data.substring(0, data.lastIndexOf("."));
			System.out.println(data);
		}

		// String str="sdfvsdf68fsdfsf8999fsdf09";
		String str = "121+2 - months";
		// String numberOnly= str.replaceAll("[^0-9]", "");
		String numberOnly = str.replaceAll("[^A-Za-z]", "");
		System.out.println(numberOnly);
		int a = 20;
		int b = 12;
		// System.out.println( (a / b) );

		float v = (float) a / b;
		System.out.println(v);

		String s = "1.012";
		String s1 = ".";
		if (s.indexOf(s1) != -1) {
			String datas = s.substring(0, s.indexOf(s1));
			System.out.println("datas:: " + datas);

			int YearFromDivide = Integer.parseInt(datas);
			int yearFinal = Integer.parseInt("2018") + YearFromDivide;
			System.out.println("yearFinal:: " + yearFinal);

			String after = s.substring(s.indexOf(s1) + s1.length());
			System.out.println("after:: " + after);

			if (after.startsWith("0")) {
				String zero = "0";
				if (after.indexOf(zero) != -1) {
					String zeroData = after.substring(0, after.indexOf(zero));
					System.out.println("zeroData: " + zeroData);
					String afterZero = after.substring(after.indexOf(zero) + zero.length());
					System.out.println("afterZero:: " + afterZero);

					if (afterZero.length() != 0) {
						if (Character.isDigit(afterZero.charAt(0))) {
							System.out.println("yes");

						}

					}

					else {

					}
				}

			}

			// String str = "1234567890";
			int fullInt = Integer.parseInt(after);
			String first2char = after.substring(0, 1);
			int intForFirst2Char = Integer.parseInt(first2char);

			String sDate1 = "Fri Sep 28 16:51:44 IST 2018";
			Date date1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(sDate1);
			System.out.println("date1:  " + date1);

			// System.out.println(intForFirst2Char);
		}
		/*
		 * double[] arrayName = new double[10]; double[] resultValue = null;
		 * //resultValue[0] = ((double)14/49)*100;
		 * System.out.println(((double)14/49));
		 */
	}

	public static JSONObject CurrentDateTime() {
		// String formatedDate = null;
		JSONObject data = null;
		try {

			Calendar cal = null;
			cal = Calendar.getInstance();
			Date currentDate = new Date(cal.getTimeInMillis());

			/*
			 * DateFormat formatter = new
			 * SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
			 * 
			 * Date date3 = (Date) formatter.parse(currentDate.toString());
			 * cal.setTime(date3);
			 */
			data = new JSONObject();

			if (currentDate != null) {

				/*
				 * String formatedDate = cal.get(Calendar.HOUR_OF_DAY) + ":" +
				 * cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
				 */

				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				String formatedDate = dateFormat.format(currentDate);

				data.put("currentTime", formatedDate);

				cal = Calendar.getInstance();
				cal.add(Calendar.MINUTE, -15);
				Date minusSate = cal.getTime();

				dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				formatedDate = dateFormat.format(minusSate);

				/*
				 * String formatedDateMinus = cal.get(Calendar.HOUR_OF_DAY) +
				 * ":" + cal.get(Calendar.MINUTE) + ":" +
				 * cal.get(Calendar.SECOND);
				 */
				data.put("currentTimeMinus", formatedDate);

				// Date d=cal.;
				// System.out.println(formatedDate);
			}

		} catch (Exception e) {

		}
		return data;

	}

	public static boolean returnTimeRange(Date ReceivedDate) {

		boolean returnTimerange = false;
		try {

			Calendar cal = null;
			cal = Calendar.getInstance();
			Date todayDate = new Date(cal.getTimeInMillis());
			// DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy
			// HH:mm:ss");
			// String currentDate = dateFormat.format(todayDate);
			// System.out.println("currentDate: " + currentDate);
			cal = Calendar.getInstance();
			cal.add(Calendar.MINUTE, -20);
			Date minusSate = cal.getTime();

			// System.out.println("todayDate :: " + todayDate);
			// System.out.println("minusSate :: " + minusSate);

			// System.out.println("received :: " + ReceivedDate);
			if (ReceivedDate.after(minusSate) && ReceivedDate.before(todayDate)) {
				// System.out.println("aa raha hai ki nahi");
				returnTimerange = true;
			}

		} catch (Exception e) {
		}
		return returnTimerange;
	}

	public static boolean fifteenDaysBeforeDataRead(Date ReceivedDate) {

		boolean returnTimerange = false;
		try {

			Calendar cal = null;
			cal = Calendar.getInstance();
			Date todayDate = new Date(cal.getTimeInMillis());

			cal = Calendar.getInstance();
			// cal.add(Calendar.MINUTE, -20);
			// cal.add(Calendar.DATE, -15); // 15 days before
//			cal.add(Calendar.DATE, -1);
			cal.add(Calendar.DATE, -12); // only previous two days mail can parse 
			Date minusSate = cal.getTime();

			if (ReceivedDate.after(minusSate) && ReceivedDate.before(todayDate)) {
				// System.out.println("aa raha hai ki nahi");
				returnTimerange = true;
			}

		} catch (Exception e) {
		}
		return returnTimerange;
	}

	public static boolean fifteenDaysBeforeDataRead1(Date ReceivedDate) {

		boolean returnTimerange = false;
		try {

			Calendar cal = null;
			cal = Calendar.getInstance();
			Date todayDate = new Date(cal.getTimeInMillis());

			cal = Calendar.getInstance();
			// cal.add(Calendar.MINUTE, -20);
			cal.add(Calendar.DATE, -1); // 15 days before
			Date minusSate = cal.getTime();

			if (ReceivedDate.after(minusSate) && ReceivedDate.before(todayDate)) {
				// System.out.println("aa raha hai ki nahi");
				returnTimerange = true;
			}

		} catch (Exception e) {
		}
		return returnTimerange;
	}

	public static void slingDateStructure(Session session) {

		try {

			Calendar cal = null;
			cal = Calendar.getInstance();
			Date currentDate = new Date(cal.getTimeInMillis());

			if (currentDate != null) {

				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				String formatedDate = dateFormat.format(currentDate);

				/*
				 * formatedDate = cal.get(Calendar.HOUR_OF_DAY) + ":" +
				 * cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
				 */

				/*
				 * formatedDate= cal.get(Calendar.DATE) + "-" +
				 * (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.YEAR)
				 * + " " + cal.get(Calendar.HOUR_OF_DAY) + ":" +
				 * cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
				 */

				formatedDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
						+ cal.get(Calendar.DATE) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE)
						+ ":" + cal.get(Calendar.SECOND);

				System.out.println(formatedDate);

				/*
				 * DateTimeFormatter timeFormatter =
				 * DateTimeFormatter.ISO_LOCAL_DATE; TemporalAccessor accessor =
				 * timeFormatter.parse(formatedDate); Date date=
				 * Date.from(Instant.from(accessor));
				 * System.out.println("date: "+date);
				 */

				Calendar c = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
				c.setTime(sdf.parse("Fri Nov 02 17:44:20 IST 2018"));

				Node scorpioDataBase3 = session.getRootNode().getNode("scorpioDataBase3");
				Node nodecal = null;
				if (scorpioDataBase3.hasNode("cal")) {
					nodecal = scorpioDataBase3.getNode("cal");
				} else {
					nodecal = scorpioDataBase3.addNode("cal");
				}
				nodecal.setProperty("date", c);
				session.save();
				/*
				 * // String testDate = "29-Apr-2010,13:00:14"; DateFormat
				 * formatter = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss"); Date
				 * date = formatter.parse(formatedDate);
				 * System.out.println(date);
				 */

				// Calendar rd = DateUtil.parse(String.format(formatedDate));

			}

		} catch (Exception e) {

		}
	}

	public static void truesetInMasterPort(PrintWriter out, Session session) {

		try {

			Node scorpioDatabase = session.getRootNode().getNode("scorpioDataBase");
			Node Port = scorpioDatabase.getNode("Port");

			if (Port.hasNodes()) {
				NodeIterator itr = Port.getNodes();
				int i = 0;
				while (itr.hasNext()) {
					Node nextNode = itr.nextNode();
					i++;
					if (nextNode.hasProperty("portName")) {
						String dataName = nextNode.getProperty("portName").getString();

						boolean hasLowercase = false;
						// hasLowercase =
						// !VesselName.equals(VesselName.toUpperCase());
						String Flag = "";

						hasLowercase = ChangedApiWithoutMultipleArray.checkString(dataName);
						if (hasLowercase == true) {

							Flag = "false";
							// VesselName=VesselName1;
							nextNode.setProperty("flag", Flag);

							// System.out.println("lowercase: "+s);
						} else {
							Flag = "true";
							nextNode.setProperty("flag", Flag);

						}

						out.println(i);
						session.save();

					}
				}
			}

		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}

	public static void truesetInMasterVessel(PrintWriter out, Session session) {

		try {

			Node scorpioDatabase = session.getRootNode().getNode("scorpioDataBase");
			Node Port = scorpioDatabase.getNode("vessel");

			if (Port.hasNodes()) {
				NodeIterator itr = Port.getNodes();
				int i = 0;
				while (itr.hasNext()) {
					Node nextNode = itr.nextNode();
					i++;
					if (nextNode.hasProperty("vesselName")) {
						String dataName = nextNode.getProperty("vesselName").getString();

						boolean hasLowercase = false;
						// hasLowercase =
						// !VesselName.equals(VesselName.toUpperCase());
						String Flag = "";

						hasLowercase = ChangedApiWithoutMultipleArray.checkString(dataName);
						if (hasLowercase == true) {

							Flag = "False";
							// VesselName=VesselName1;
							nextNode.setProperty("Flag", Flag);

							// System.out.println("lowercase: "+s);
						} else {
							Flag = "True";
							nextNode.setProperty("Flag", Flag);

						}

						out.println(i);
						session.save();

					}
				}
			}

		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}

	public static void portNotMaster(PrintWriter out, Session session) {

		List<Pojo> addTodb = null;
		try {

			Node scorpioDatabase = session.getRootNode().getNode("scorpioDataBase");
			// Node ReportData=scorpioDatabase.getNode("ReportData");
			Node Tonnage = scorpioDatabase.getNode("vessel");// vessel

			if (Tonnage.hasNodes()) {
				NodeIterator itr = Tonnage.getNodes();
				long size = Tonnage.getNodes().getSize();
				// Integer i = (int) (long) size;
				addTodb = new ArrayList<>();
				int j = 0;
				JSONObject s = null;
				JSONArray a = new JSONArray();
				while (itr.hasNext()) {
					Node nextNode = itr.nextNode();
					j++;

					// String
					// openPort=nextNode.getProperty("portUrl").getString();
					// if(GmailMethods.isNullString(openPort)){
					// int openport=Integer.parseInt(openPort);

					// if(openport>8151){

					// out.printf(" %s\n", openPort);
					String vesselName = "";
					if (nextNode.hasProperty("vesselName")) {
						vesselName = nextNode.getProperty("vesselName").getString();
						s = new JSONObject();
						s.put("vessel", vesselName);
						a.put(s);
					}
					// Pojo pj =new Pojo(openPort);

					// addTodb.add(openPort);

					// }

					// }/*else {

					/*
					 * if(openPort.equalsIgnoreCase("#N/A")){ //#N/A String
					 * vesselName=""; if(nextNode.hasProperty("portName")){
					 * vesselName=nextNode.getProperty("portName").getString();
					 * } // Pojo pj =new Pojo(openPort); s=new JSONObject();
					 * s.put("vessel", vesselName); a.put(s); } }
					 */

				} // while close
				JSONObject d = new JSONObject();
				d.put("a", a);

				out.println("Total no of Port found  " + j);
				addAllPort(d, out, session);

			}

		} catch (Exception e) {
			e.printStackTrace(out);
		}
	}

	/* private static void addAllPort(List<Pojo>recordset, PrintWriter out){ */
	private static void addAllPort(JSONObject d, PrintWriter out, Session session) {

		int i = 0;
		int count = 0;
		try {

			int rowIndex = 0;
			Workbook workbook = new HSSFWorkbook();
			Sheet sheet = workbook.createSheet("vesselNotInMaster");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
			headerCellStyle.setWrapText(true);
			headerCellStyle.setFont(headerFont);

			CellStyle hlinkstyle = workbook.createCellStyle();
			Font hlinkfont = workbook.createFont();
			hlinkfont.setUnderline(HSSFFont.U_SINGLE);
			hlinkfont.setColor(HSSFColor.BLUE.index);

			hlinkstyle.setFont(hlinkfont);

			Row brokertcratedatarow = sheet.createRow((rowIndex));
			Cell Broker = brokertcratedatarow.createCell(0);
			Broker.setCellValue("vesselNotInMaster");
			Broker.setCellStyle(headerCellStyle);

			FetchData fd = new FetchData();
			// i = d.size();
			out.println("Record to be inserted is  " + i);
			if (d.has("a")) {
				JSONArray f = d.getJSONArray("a");
				for (int j = 0; j < f.length(); j++) {
					JSONObject c = f.getJSONObject(j);
					if (c.has("vessel")) {
						String port = c.getString("vessel");
						Row brokerrow = sheet.createRow((rowIndex + 1 + j));
						Cell broker_value = brokerrow.createCell(0);

						broker_value.setCellValue(port);

						out.println("j:: " + j);

					}

				}
				//

				// String port=ps.getPort();

				count++;

			}

			File linuxdirectory = new File("/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/PortNotInMaster");
			if (!linuxdirectory.exists()) {

				linuxdirectory.mkdir();
				out.println("folder created in linux");
			}

			String fileName = "vesselNotInMaster.xls";
			FileOutputStream fileOut = new FileOutputStream(
					"/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/PortNotInMaster/" + fileName);
			workbook.write(fileOut);
			fileOut.close();

			workbook.close();

		} catch (Exception e1) {
			e1.printStackTrace(out);

		}
	}

	public static void portNotMasterDelete(PrintWriter out, Session session) {

		List<Pojo> addTodb = null;
		try {

			Node scorpioDatabase = session.getRootNode().getNode("scorpioDataBase3");
			Node ReportData = scorpioDatabase.getNode("ReportData");
			Node Tonnage = ReportData.getNode("Tonnage");

			if (Tonnage.hasNodes()) {
				NodeIterator itr = Tonnage.getNodes();
				long size = Tonnage.getNodes().getSize();
				// Integer i = (int) (long) size;
				addTodb = new ArrayList<>();
				int j = 0;
				JSONObject s = null;
				JSONArray a = new JSONArray();
				while (itr.hasNext()) {
					Node nextNode = itr.nextNode();
					j++;

					if (nextNode.hasProperty("Id")) {
						String openPort = nextNode.getProperty("Id").getString();
						if (!GmailMethods.isNullString(openPort)) {
							int openport = Integer.parseInt(openPort);

							if (openport > 1794) {

								out.printf(" %s\n", openPort);
								//
								nextNode.remove();

								session.save();

							}

						}
					}

				} // while close

			}

		} catch (Exception e) {
			e.printStackTrace(out);
		}
	}

	public static void portNotMasterDelete4(PrintWriter out, Session session) {

		List<Pojo> addTodb = null;
		try {

			Node scorpioDatabase = session.getRootNode().getNode("scorpioDataBase4");
			Node ReportData = scorpioDatabase.getNode("ReportData");
			Node Tonnage = ReportData.getNode("Tonnage");

			if (Tonnage.hasNodes()) {
				NodeIterator itr = Tonnage.getNodes();
				long size = Tonnage.getNodes().getSize();
				// Integer i = (int) (long) size;
				addTodb = new ArrayList<>();
				int j = 0;
				JSONObject s = null;
				JSONArray a = new JSONArray();
				while (itr.hasNext()) {
					Node nextNode = itr.nextNode();
					j++;

					if (nextNode.hasProperty("Id")) {
						String openPort = nextNode.getProperty("Id").getString();
						if (!GmailMethods.isNullString(openPort)) {
							int openport = Integer.parseInt(openPort);

							if (openport > 2369) {

								out.printf(" %s\n", openPort);
								//
								nextNode.remove();

								session.save();

							}

						}
					}

				} // while close

			}

		} catch (Exception e) {
			e.printStackTrace(out);
		}
	}

	public static String check_vesselNameForApi(Session session, String vesselName, PrintWriter out) {

		String vesselSubNodeName = "";
		try {

			Node obj = null;
			String vesselNamequery = "";

			Workspace workspace = session.getWorkspace();
			vesselName = vesselName.toLowerCase().trim();

			if (!GmailMethods.isNullString(vesselName)) {

				String slingqery = "select [vesselName] from [nt:base] where LOWER(vesselName) ='" + vesselName
						+ "'  and ISDESCENDANTNODE('/scorpioDataBase/vessel/')";

				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				// query.setLimit(10);
				QueryResult queryResult = query.execute();
				NodeIterator iterator = queryResult.getNodes();
				while (iterator.hasNext()) {

					obj = iterator.nextNode();

					if (obj.hasProperty("vesselName")) {
						vesselNamequery = obj.getProperty("vesselName").getString();
					}

				}
				JSONObject allVesselProperties = new JSONObject();
				if (vesselName.equals(vesselNamequery.toLowerCase().trim())) {

					if (obj.hasProperty("vessel_Id")) {
						String vessel_id = obj.getProperty("vessel_Id").getString();
						allVesselProperties.put("vessel_Id", vessel_id);

					}
					if (obj.hasProperty("vesselName")) {
						vesselNamequery = obj.getProperty("vesselName").getString();
						allVesselProperties.put("vesselName", obj.getName());
					}

					if (obj.hasProperty("Flag")) {
						String Flag = obj.getProperty("Flag").getString();
						allVesselProperties.put("Flag", Flag);
					}

					if (obj.hasProperty("built")) {
						String built = obj.getProperty("built").getString();
						allVesselProperties.put("built", built);
					}
					if (obj.hasProperty("cubics")) {
						String cubics = obj.getProperty("cubics").getString();
						allVesselProperties.put("cubics", cubics);
					}
					if (obj.hasProperty("dwt")) {
						String dwt = obj.getProperty("dwt").getString();
						allVesselProperties.put("dwt", dwt);
					}
					if (obj.hasProperty("ice")) {
						String ice = obj.getProperty("ice").getString();
						allVesselProperties.put("ice", ice);
					}
					if (obj.hasProperty("loa")) {
						String loa = obj.getProperty("loa").getString();
						allVesselProperties.put("loa", loa);
					}
					FetchData fd = new FetchData();
					if (obj.hasProperty("owners_id")) {
						String owners_id = obj.getProperty("owners_id").getString();
						owners_id = FetchData.fetch_Owners(session, owners_id);
						allVesselProperties.put("ownersName", owners_id);
					}
					if (obj.hasProperty("sternline")) {
						String sternline = obj.getProperty("sternline").getString();
						allVesselProperties.put("sternline", sternline);
					}
					if (obj.hasProperty("vesselType_id")) {
						String vesselType_id = obj.getProperty("vesselType_id").getString();
						vesselType_id = FetchData.fetch_vesselType(session, vesselType_id);
						allVesselProperties.put("vesselType", vesselType_id);
					}

					if (obj.hasProperty("operatorId")) {
						String operatorId = obj.getProperty("operatorId").getString();
						operatorId = fd.fetch_Operators(session, operatorId);
						allVesselProperties.put("operatorName", operatorId);
					}

					vesselSubNodeName = allVesselProperties.toString();
				} else {
					String error = "Not Found";
					allVesselProperties.put("error", error);
					vesselSubNodeName = allVesselProperties.toString();
				}

			} // vesselname blank check
		} catch (Exception e) {
			return e.getMessage();
		}
		return vesselSubNodeName;

	}

	public static String singleString(String passStringCheckLength) {

		String deliveryData = "";
		try {

			if (passStringCheckLength.length() <= 9) {
				deliveryData = passStringCheckLength;
			}

		} catch (Exception e) {
			return e.getMessage();
		}
		return deliveryData;
	}

	public static int allquarters(String delivery1) {
		int data = 0;
		if (delivery1.equalsIgnoreCase("Q1")) {
			String delivery = delivery1;
			data = FifteenMinuteClass.quarterCheckDate(delivery);
		} else if (delivery1.equalsIgnoreCase("Q2")) {
			String delivery = delivery1;
			data = FifteenMinuteClass.quarterCheckDate(delivery);
		} else if (delivery1.equalsIgnoreCase("Q3")) {
			String delivery = delivery1;
			data = FifteenMinuteClass.quarterCheckDate(delivery);
		} else if (delivery1.equalsIgnoreCase("Q4")) {
			String delivery = delivery1;
			data = FifteenMinuteClass.quarterCheckDate(delivery);
		}
		return data;

	}

	public static int quarterCheckDate(String MonthOfName) {

		// String MonthOfName = "";
		int number_Of_DaysInMonth = 0;
		String monthName = "";

		switch (MonthOfName.toLowerCase()) {
		case "q1":
			monthName = "jan";
			number_Of_DaysInMonth = 1;
			break;
		case "q2":
			monthName = "april";
			number_Of_DaysInMonth = 4;
			break;
		case "q3":

			monthName = "jul";
			number_Of_DaysInMonth = 3;
			break;
		case "q4":

			monthName = "oct";
			number_Of_DaysInMonth = 10;

		}
		return number_Of_DaysInMonth;

	}

	public static String identifiymonthYear(String monthOrYear, String delivery, String parsedPeriod,
			String ReportTimestamp, String periodUnit, PrintWriter out) {
		String data = "";
		monthOrYear = monthOrYear.replaceAll("[^A-Za-z]", "");
		monthOrYear = monthOrYear.toLowerCase();

		if (monthOrYear.equalsIgnoreCase("months") || monthOrYear.equalsIgnoreCase("month")) {
			int monthData = Integer.parseInt(delivery) + Integer.parseInt(parsedPeriod);
			if (monthData < 12) {
				data = String.valueOf(monthData) + "-" + ReportTimestamp;
			} else {
				// out.println("delivery:: "+delivery);
				// out.println("parsedPeriod:: "+parsedPeriod);
				int addDeliveryandperiod = Integer.parseInt(delivery) + Integer.parseInt(parsedPeriod);
				float v = (float) addDeliveryandperiod / 12;
				// out.println("v:: "+v);
				data = String.valueOf(v);
				// out.println("vdata:: "+data);
				String s1 = ".";
				if (data.indexOf(s1) != -1) {
					String datas = data.substring(0, data.indexOf(s1));
					// out.println("datas:: "+datas);
					// System.out.println("datas:: "+datas);
					int YearFromDivide = Integer.parseInt(datas);
					// int
					// yearFinal=Integer.parseInt(ReportTimestamp)+YearFromDivide;
					int yearFinal = Integer.parseInt(ReportTimestamp) + Integer.parseInt(datas);
					// out.println("yearFinal:: "+yearFinal);
					String after = data.substring(data.indexOf(s1) + s1.length());
					// out.println("after: "+after);
					if (after.startsWith("0")) {
						String zero = "0";
						// out.println("inside 0: ");
						if (after.indexOf(zero) != -1) {
							String zeroData = after.substring(0, after.indexOf(zero));
							// out.println("zeroData: "+zeroData);

							String afterZero = after.substring(after.indexOf(zero) + zero.length());
							if (afterZero.length() != 0) {
								if (Character.isDigit(afterZero.charAt(0))) {
									data = "1" + "-" + String.valueOf(yearFinal);
									// out.println("finaldata: "+data);
								}
							}
						}

					} else {
						int fullInt = Integer.parseInt(after);
						String first1char = after.substring(0, 1);
						int intForFirst2Char = Integer.parseInt(first1char);
						int monthFromDivide = intForFirst2Char;

						data = String.valueOf(monthFromDivide) + "-" + String.valueOf(yearFinal);
						// out.println("finaldata: "+data);
					}

				}

			}

		} else if (monthOrYear.equalsIgnoreCase("days") || monthOrYear.equalsIgnoreCase("day")) {

			int deliveryInDays = Integer.parseInt(delivery) * 30;
			int totalAddingdeliveryDaysAndPeriodDays = deliveryInDays + Integer.parseInt(parsedPeriod);
			float totalDaysIntoMonths = (float) totalAddingdeliveryDaysAndPeriodDays / 30;
			if (totalDaysIntoMonths < 12) {
				data = String.valueOf(totalDaysIntoMonths) + "-" + ReportTimestamp;
			} else {
				float monthYear = (float) totalDaysIntoMonths / 12;

				data = String.valueOf(monthYear);
				// out.println("vdata:: "+data);
				String s1 = ".";
				if (data.indexOf(s1) != -1) {
					String datas = data.substring(0, data.indexOf(s1));
					// out.println("datas:: "+datas);
					// System.out.println("datas:: "+datas);
					int YearFromDivide = Integer.parseInt(datas);
					// int
					// yearFinal=Integer.parseInt(ReportTimestamp)+YearFromDivide;
					int yearFinal = Integer.parseInt(ReportTimestamp) + Integer.parseInt(datas);
					// out.println("yearFinal:: "+yearFinal);
					String after = data.substring(data.indexOf(s1) + s1.length());
					// out.println("after: "+after);
					if (after.startsWith("0")) {
						String zero = "0";
						// out.println("inside 0: ");
						if (after.indexOf(zero) != -1) {
							String zeroData = after.substring(0, after.indexOf(zero));
							// out.println("zeroData: "+zeroData);

							String afterZero = after.substring(after.indexOf(zero) + zero.length());
							if (afterZero.length() != 0) {
								if (Character.isDigit(afterZero.charAt(0))) {
									data = "1" + "-" + String.valueOf(yearFinal);
									// out.println("finaldata: "+data);
								}
							}
						}

					} else {
						int fullInt = Integer.parseInt(after);
						String first1char = after.substring(0, 1);
						int intForFirst2Char = Integer.parseInt(first1char);
						int monthFromDivide = intForFirst2Char;

						data = String.valueOf(monthFromDivide) + "-" + String.valueOf(yearFinal);
						// out.println("finaldata: "+data);
					}

				}
			}

		} else if (monthOrYear.equalsIgnoreCase("years") || monthOrYear.equalsIgnoreCase("year")
				|| monthOrYear.equalsIgnoreCase("yr") || monthOrYear.equalsIgnoreCase("yrs")) {
			int Yeardata = Integer.parseInt(ReportTimestamp) + Integer.parseInt(parsedPeriod);
			data = delivery + "/" + String.valueOf(Yeardata);
		}

		else {
			if (periodUnit.equalsIgnoreCase("months") || periodUnit.equalsIgnoreCase("month")) {
				int monthData = Integer.parseInt(delivery) + Integer.parseInt(parsedPeriod);
				if (monthData < 12) {
					data = String.valueOf(monthData) + "-" + ReportTimestamp;
				} else {
					// out.println("delivery:: "+delivery);
					// out.println("parsedPeriod:: "+parsedPeriod);
					int addDeliveryandperiod = Integer.parseInt(delivery) + Integer.parseInt(parsedPeriod);
					float v = (float) addDeliveryandperiod / 12;
					// out.println("v:: "+v);
					data = String.valueOf(v);
					// out.println("vdata:: "+data);
					String s1 = ".";
					if (data.indexOf(s1) != -1) {
						String datas = data.substring(0, data.indexOf(s1));
						// out.println("datas:: "+datas);
						// System.out.println("datas:: "+datas);
						int YearFromDivide = Integer.parseInt(datas);
						// int
						// yearFinal=Integer.parseInt(ReportTimestamp)+YearFromDivide;
						int yearFinal = Integer.parseInt(ReportTimestamp) + Integer.parseInt(datas);
						// out.println("yearFinal:: "+yearFinal);
						String after = data.substring(data.indexOf(s1) + s1.length());
						// out.println("after: "+after);
						if (after.startsWith("0")) {
							String zero = "0";
							// out.println("inside 0: ");
							if (after.indexOf(zero) != -1) {
								String zeroData = after.substring(0, after.indexOf(zero));
								// out.println("zeroData: "+zeroData);

								String afterZero = after.substring(after.indexOf(zero) + zero.length());
								if (afterZero.length() != 0) {
									if (Character.isDigit(afterZero.charAt(0))) {
										data = "1" + "-" + String.valueOf(yearFinal);
										// out.println("finaldata: "+data);
									}
								}
							}

						} else {
							int fullInt = Integer.parseInt(after);
							String first1char = after.substring(0, 1);
							int intForFirst2Char = Integer.parseInt(first1char);
							int monthFromDivide = intForFirst2Char;

							data = String.valueOf(monthFromDivide) + "-" + String.valueOf(yearFinal);
							// out.println("finaldata: "+data);
						}

					}

				}

			} else if (periodUnit.equalsIgnoreCase("days") || periodUnit.equalsIgnoreCase("day")) {

				int deliveryInDays = Integer.parseInt(delivery) * 30;
				int totalAddingdeliveryDaysAndPeriodDays = deliveryInDays + Integer.parseInt(parsedPeriod);
				float totalDaysIntoMonths = (float) totalAddingdeliveryDaysAndPeriodDays / 30;
				if (totalDaysIntoMonths < 12) {
					data = String.valueOf(totalDaysIntoMonths) + "-" + ReportTimestamp;
				} else {
					float monthYear = (float) totalDaysIntoMonths / 12;

					data = String.valueOf(monthYear);
					// out.println("vdata:: "+data);
					String s1 = ".";
					if (data.indexOf(s1) != -1) {
						String datas = data.substring(0, data.indexOf(s1));
						// out.println("datas:: "+datas);
						// System.out.println("datas:: "+datas);
						int YearFromDivide = Integer.parseInt(datas);
						// int
						// yearFinal=Integer.parseInt(ReportTimestamp)+YearFromDivide;
						int yearFinal = Integer.parseInt(ReportTimestamp) + Integer.parseInt(datas);
						// out.println("yearFinal:: "+yearFinal);
						String after = data.substring(data.indexOf(s1) + s1.length());
						// out.println("after: "+after);
						if (after.startsWith("0")) {
							String zero = "0";
							// out.println("inside 0: ");
							if (after.indexOf(zero) != -1) {
								String zeroData = after.substring(0, after.indexOf(zero));
								// out.println("zeroData: "+zeroData);

								String afterZero = after.substring(after.indexOf(zero) + zero.length());
								if (afterZero.length() != 0) {
									if (Character.isDigit(afterZero.charAt(0))) {
										data = "1" + "-" + String.valueOf(yearFinal);
										// out.println("finaldata: "+data);
									}
								}
							}

						} else {
							int fullInt = Integer.parseInt(after);
							String first1char = after.substring(0, 1);
							int intForFirst2Char = Integer.parseInt(first1char);
							int monthFromDivide = intForFirst2Char;

							data = String.valueOf(monthFromDivide) + "-" + String.valueOf(yearFinal);
							// out.println("finaldata: "+data);
						}

					}
				}

			} else if (periodUnit.equalsIgnoreCase("years") || periodUnit.equalsIgnoreCase("year")
					|| periodUnit.equalsIgnoreCase("yr") || periodUnit.equalsIgnoreCase("yrs")) {
				int Yeardata = Integer.parseInt(ReportTimestamp) + Integer.parseInt(parsedPeriod);
				data = delivery + "-" + String.valueOf(Yeardata);
			}
		}
		return data;

	}

	public static String periodDate(String Period1) {
		String Period = "";
		try {

			String PeriodDash = ChangedApiWithoutMultipleArray.checkDashPeriod(Period1);
			if (PeriodDash != null) {
				Period = PeriodDash;

			} else {
				String PeriodPlus = ChangedApiWithoutMultipleArray.checkPlushPeriod(Period1);
				if (PeriodPlus != null) {
					Period = PeriodPlus;
				} else {
					boolean sinlePeriodNumber = ChangedApiWithoutMultipleArray.checkOnlySingleNUmber(Period1);
					if (sinlePeriodNumber == false) {
						Period = Period1;
					} else {
						Period = Period1;
					}
				}
			}

		} catch (Exception e) {
			return e.getMessage();
		}
		return Period;
	}

	public static String deliveryDate(String Delivery1, String ReportTimestamp, String PeriodPassReal,
			String periodUnit, PrintWriter out) {
		String Delivery = "";
		try {

			String DeliverythreeNo = ChangedApiWithoutMultipleArray.checkDash(Delivery1);
			if (DeliverythreeNo != null) {
				boolean checkNumber = ChangedApiWithoutMultipleArray.checkString1(DeliverythreeNo);
				if (checkNumber == true) {
					if (Integer.parseInt(DeliverythreeNo) <= 12) {
						Delivery = DeliverythreeNo;
						String parsedPeriod = periodDate(PeriodPassReal);
						Delivery = identifiymonthYear(PeriodPassReal, Delivery, parsedPeriod, ReportTimestamp,
								periodUnit, out);
						/*
						 * if( !GmailMethods.isNullString(ReportTimestamp) ){
						 * Delivery=Delivery +"/" + ReportTimestamp; }
						 */
					}

				} // true
				else {
					Pattern pattern = Pattern.compile("^[A-Za-z, ]++$");
					if (pattern.matcher(DeliverythreeNo).matches()) {
						int monthInt = ChangedApiWithoutMultipleArray.monthsInint(DeliverythreeNo);
						if (monthInt != 0) {
							Delivery = String.valueOf(monthInt);
							String parsedPeriod = periodDate(PeriodPassReal);
							Delivery = identifiymonthYear(PeriodPassReal, Delivery, parsedPeriod, ReportTimestamp,
									periodUnit, out);
							/*
							 * if( !GmailMethods.isNullString(ReportTimestamp)
							 * ){ Delivery=Delivery +"/" + ReportTimestamp; }
							 */
						}

					}

				}

			} // three null check

			else {

				String DeliveryOne = ChangedApiWithoutMultipleArray.checkDashOne(Delivery1);
				if (!GmailMethods.isNullString(DeliveryOne)) {
					boolean checkNumber = ChangedApiWithoutMultipleArray.checkString1(DeliveryOne);
					if (checkNumber == true) {
						if (Integer.parseInt(DeliveryOne) <= 12) {
							Delivery = DeliveryOne;
							String parsedPeriod = periodDate(PeriodPassReal);
							Delivery = identifiymonthYear(PeriodPassReal, Delivery, parsedPeriod, ReportTimestamp,
									periodUnit, out);
							/*
							 * if( !GmailMethods.isNullString(ReportTimestamp)
							 * ){ Delivery=Delivery +"/" + ReportTimestamp; }
							 */
						}

					} else {

						final Pattern pattern = Pattern.compile("^[A-Za-z, ]++$");
						if (pattern.matcher(DeliveryOne).matches()) {

							int monthInt = ChangedApiWithoutMultipleArray.monthsInint(DeliveryOne);
							if (monthInt != 0) {
								Delivery = String.valueOf(monthInt);
								String parsedPeriod = periodDate(PeriodPassReal);
								Delivery = identifiymonthYear(PeriodPassReal, Delivery, parsedPeriod, ReportTimestamp,
										periodUnit, out);
								/*
								 * if(
								 * !GmailMethods.isNullString(ReportTimestamp)
								 * ){ Delivery=Delivery +"/" + ReportTimestamp;
								 * }
								 */
							}
						}
					}
				} // null check deliveryone

				else {
					String DeliveryDashOne = ChangedApiWithoutMultipleArray.checkDashSlashOne(Delivery1);
					if (!GmailMethods.isNullString(DeliveryDashOne)) {
						boolean checkNumber = ChangedApiWithoutMultipleArray.checkString1(DeliveryDashOne);
						if (checkNumber == true) {

							if (Integer.parseInt(DeliveryDashOne) <= 12) {
								Delivery = DeliveryDashOne;
								String parsedPeriod = periodDate(PeriodPassReal);
								Delivery = identifiymonthYear(PeriodPassReal, Delivery, parsedPeriod, ReportTimestamp,
										periodUnit, out);
								/*
								 * if(
								 * !GmailMethods.isNullString(ReportTimestamp)
								 * ){ Delivery=Delivery +"/" + ReportTimestamp;
								 * }
								 */
							}
						} else {
							final Pattern pattern = Pattern.compile("^[A-Za-z, ]++$");
							if (pattern.matcher(DeliveryDashOne).matches()) {

								int monthInt = ChangedApiWithoutMultipleArray.monthsInint(DeliveryDashOne);
								if (monthInt != 0) {
									Delivery = String.valueOf(monthInt);
									String parsedPeriod = periodDate(PeriodPassReal);
									Delivery = identifiymonthYear(PeriodPassReal, Delivery, parsedPeriod,
											ReportTimestamp, periodUnit, out);
									/*
									 * if( !GmailMethods.isNullString(
									 * ReportTimestamp) ){ Delivery=Delivery
									 * +"/" + ReportTimestamp; }
									 */
								}
							}
						}
					}
					int data = allquarters(Delivery1);
					if (data != 0) {
						Delivery = String.valueOf(data);
						String parsedPeriod = periodDate(PeriodPassReal);
						Delivery = identifiymonthYear(PeriodPassReal, Delivery, parsedPeriod, ReportTimestamp,
								periodUnit, out);
						/*
						 * if( !GmailMethods.isNullString(ReportTimestamp) ){
						 * Delivery=Delivery +"/" + ReportTimestamp; }
						 */
					}

					else {
						//
						String singledata = singleString(Delivery1);
						if (!GmailMethods.isNullString(singledata)) {
							int singleExtract = ChangedApiWithoutMultipleArray.monthsInint(singledata);
							if (singleExtract != 0) {
								Delivery = String.valueOf(singleExtract);
								String parsedPeriod = periodDate(PeriodPassReal);
								Delivery = identifiymonthYear(PeriodPassReal, Delivery, parsedPeriod, ReportTimestamp,
										periodUnit, out);
								/*
								 * if(
								 * !GmailMethods.isNullString(ReportTimestamp)
								 * ){ Delivery=Delivery +"/" + ReportTimestamp;
								 * }
								 */
							}
						} /*
							 * else{ Delivery=Delivery1; }
							 */
					}
				}

			} // all

		} catch (Exception e) {
			return e.getMessage();
		}

		return Delivery;

	}

	public static void portNotMasterupdatehttps(PrintWriter out, Session session) {

		List<Pojo> addTodb = null;
		try {

			Node scorpioDatabase = session.getRootNode().getNode("scorpioDataBase");
			Node ReportData = scorpioDatabase.getNode("ReportData");
			Node Tonnage = ReportData.getNode("Spot");

			if (Tonnage.hasNodes()) {
				NodeIterator itr = Tonnage.getNodes();
				long size = Tonnage.getNodes().getSize();
				// Integer i = (int) (long) size;
				addTodb = new ArrayList<>();
				int j = 0;
				JSONObject s = null;
				JSONArray a = new JSONArray();
				while (itr.hasNext()) {
					Node nextNode = itr.nextNode();
					j++;

					if (nextNode.hasProperty("emailUrl")) {
						String emailUrl = nextNode.getProperty("emailUrl").getString();
						if (emailUrl.lastIndexOf("http:") != -1) {
							emailUrl = emailUrl.substring(emailUrl.lastIndexOf("http:") + 5);
							emailUrl = "https:" + emailUrl;
							out.println(emailUrl);
							nextNode.setProperty("emailUrl", emailUrl);
						}

						session.save();
					}

				} // while close

			}

		} catch (Exception e) {
			e.printStackTrace(out);
		}
	}

	public static String checkSpaceThree(String c, String Currentyear) {

		String three = "";
		String data = "";
		String parts[] = c.split(" ");
		// System.out.println(parts.length);
		if (parts.length == 3) {

			if ((null != c)) {
				String one = "";
				int firstDashIndex = c.indexOf(' ');
				String two = "";
				if (firstDashIndex >= 0) {
					one = c.substring(0, firstDashIndex);
					// System.out.println("one: "+one);
					if ((firstDashIndex + 1) < c.length()) {
						two = c.substring(firstDashIndex + 1);
						// System.out.println("two: "+two);
					}

					if ((firstDashIndex + 1) < two.length()) {
						three = two.substring(0, firstDashIndex + 1);
						// System.out.println("three: "+three);
					}

					int i = ChangedApiWithoutMultipleArray.monthsInint(three);
					if (i != 0) {
						data = one + "/" + String.valueOf(i) + "/" + Currentyear;
					}
				}
			}
		}
		return data;
	}

	public static String twoDate(String twoDate, String Currentyear) {

		String substr = " ";
		String data = "";
		if (twoDate.indexOf(substr) != -1) {
			String month = twoDate.substring(0, twoDate.indexOf(substr));
			// System.out.println(month);

			int i = ChangedApiWithoutMultipleArray.monthsInint(month);

			String date = twoDate.substring(twoDate.indexOf(substr) + substr.length());
			// System.out.println(date);

			if (i != 0) {
				data = date + "/" + String.valueOf(i) + "/" + Currentyear;
			}

		}
		return data;

	}

	public static String twoDateSlash(String twoDate, String Currentyear) {

		String substr = "/";
		String data = "";
		if (twoDate.indexOf(substr) != -1) {
			String month = twoDate.substring(0, twoDate.indexOf(substr));
			// System.out.println(month);

			String date = twoDate.substring(twoDate.indexOf(substr) + substr.length());
			// System.out.println(date);

			if (date.indexOf(substr) != -1) {

				date = date.substring(0, date.indexOf(substr));
				data = date + "/" + String.valueOf(month) + "/" + Currentyear;
			} else {
				data = date + "/" + String.valueOf(month) + "/" + Currentyear;
			}

		}
		return data;

	}

	public static String DateSpach(String twoDate, String Currentyear) {

		String substr = " ";
		String data = "";
		if (twoDate.indexOf(substr) != -1) {
			String date = twoDate.substring(0, twoDate.indexOf(substr));
			// System.out.println(month);

			String month = twoDate.substring(twoDate.indexOf(substr) + substr.length());
			// System.out.println(date);
			int i = ChangedApiWithoutMultipleArray.monthsInint(month);
			if (i != 0) {

				data = date + "/" + String.valueOf(i) + "/" + Currentyear;
			}

		}
		return data;

	}

	public static String dateDot(String twoDate, String Currentyear, PrintWriter out) {

		String substr = ".";
		String data = "";
		if (twoDate.indexOf(substr) != -1) {
			String date = twoDate.substring(0, twoDate.indexOf(substr));
			// out.println("d date:: "+date);

			String month = twoDate.substring(twoDate.indexOf(substr) + substr.length());
			// out.println("d month:: "+month);
			if (month.indexOf(substr) != -1) {
				month = month.substring(0, twoDate.indexOf(substr));
				// out.println("d monthif:: "+month);
				data = date + "/" + month + "/" + Currentyear;
				// out.println("d data:: "+data);
				// out.println("d year:: "+Currentyear);

			}

		}
		return data;

	}

	public static String datedashSingle(String twoDate, String Currentyear) {

		String substr = "-";
		String data = "";
		if (twoDate.indexOf(substr) != -1) {
			String date = twoDate.substring(0, twoDate.indexOf(substr));
			// System.out.println(month);

			String month = twoDate.substring(twoDate.indexOf(substr) + substr.length());

			int i = ChangedApiWithoutMultipleArray.monthsInint(month);
			if (i != 0) {

				data = date + "/" + String.valueOf(i) + "/" + Currentyear;
			}

		}
		return data;

	}

	public static String dateSpaceSingle(String twoDate, String Currentyear) {

		String substr = " ";
		String data = "";
		if (twoDate.indexOf(substr) != -1) {
			String date = twoDate.substring(0, twoDate.indexOf(substr));
			// System.out.println("single space:: "+date);

			String month = twoDate.substring(twoDate.indexOf(substr) + substr.length());
			// System.out.println("single space:: "+month);

			if (month.indexOf(substr) != -1) {
				month = month.substring(0, month.indexOf(substr));
				// System.out.println("single space:: "+month);
				int i = ChangedApiWithoutMultipleArray.monthsInint(month);
				if (i != 0) {

					data = date + "/" + String.valueOf(i) + "/" + Currentyear;
					// System.out.println("single space:: "+data);
				}
			}

		}
		return data;

	}

	public static String postSolr(JSONObject obj, PrintWriter out) throws UnsupportedEncodingException {

		int responseCode = 0;
		String urlParameters = "";

		try {

			// String url1 =
			// "http://35.188.227.168:8983/solr/Vessel/update/json/docs?commit=true";
			String url1 = "http://35.188.227.168:8983/solr/AllReport/update/json/docs?commit=true";
			URL url = new URL(url1);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");

			con.setRequestProperty("Content-Type", "application/json");

			out.println("urlParameters:: " + obj.toString());
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

			out.println(response.toString());

		}

		catch (Exception e) {

			e.printStackTrace();
			// out.println(e.getMessage());
		}
		return String.valueOf(responseCode) + urlParameters;

	}

	public static void fetchVesselForSolr(PrintWriter out, Session session) {

		try {

			Node scorpioDatabase = session.getRootNode().getNode("scorpioDataBase");
			Node vessel = scorpioDatabase.getNode("vessel");

			JSONArray s = null;
			if (vessel.hasNodes()) {
				s = new JSONArray();
				NodeIterator itr = vessel.getNodes();
				JSONObject obj = null;
				while (itr.hasNext()) {
					Node nextNode = itr.nextNode();

					if (nextNode.hasProperty("vesselName")) {
						String vesselName = nextNode.getProperty("vesselName").getString();
						s.put(vesselName);
					}

				} // while vessel close
				obj = new JSONObject();
				obj.put("Data", s);
				obj.put("url", "http://35.188.227.168:8983/solr/#/AllReport/vesselName");
				obj.put("DocumentName", "vesselName");

				postSolr(obj, out);
			}

		} catch (Exception e) {
			e.printStackTrace(out);
		}
	}

	public static void fetchCargograde(PrintWriter out, Session session) {
		try {
			Node scorpioDatabase = session.getRootNode().getNode("scorpioDataBase");
			Node cargoGrade = scorpioDatabase.getNode("Cargo Grade");
			JSONArray s = null;
			if (cargoGrade.hasNodes()) {
				s = new JSONArray();
				NodeIterator itr1 = cargoGrade.getNodes();
				JSONObject obj = null;
				while (itr1.hasNext()) {
					Node nextNode = itr1.nextNode();

					if (nextNode.hasProperty("cargoGrade")) {
						String cargoGradename = nextNode.getProperty("cargoGrade").getString();

						s.put(cargoGradename);
					}

				} // while CargoGrade close
				obj = new JSONObject();
				obj.put("Data", s);
				obj.put("url", "http://35.188.227.168:8983/solr/#/AllReport/CargoGrade");
				obj.put("DocumentName", "CargoGrade");

				postSolr(obj, out);
			}
		} catch (Exception e) {

		}
	}

	public static void fetchCargogradeowners(PrintWriter out, Session session) {
		try {
			Node scorpioDatabase = session.getRootNode().getNode("scorpioDataBase");
			Node owners = scorpioDatabase.getNode("owners");
			JSONArray s = null;

			if (owners.hasNodes()) {
				s = new JSONArray();
				NodeIterator itr2 = owners.getNodes();
				JSONObject obj = null;
				while (itr2.hasNext()) {
					Node nextNode = itr2.nextNode();

					if (nextNode.hasProperty("ownerName")) {
						String cargoGradename = nextNode.getProperty("ownerName").getString();

						s.put(cargoGradename);

					}

				} // while Owners close
				obj = new JSONObject();
				obj.put("Data", s);
				obj.put("url", "http://35.188.227.168:8983/solr/#/AllReport/Owners");
				obj.put("DocumentName", "Owners");

				postSolr(obj, out);

			}

		} catch (Exception e) {

		}
	}

	public static void fetchCargogradeOperators(PrintWriter out, Session session) {
		try {
			Node scorpioDatabase = session.getRootNode().getNode("scorpioDataBase");
			Node Operators = scorpioDatabase.getNode("Operators");
			JSONArray s = null;

			if (Operators.hasNodes()) {
				s = new JSONArray();
				NodeIterator itr2 = Operators.getNodes();
				JSONObject obj = null;
				while (itr2.hasNext()) {
					Node nextNode = itr2.nextNode();

					if (nextNode.hasProperty("operatorsName")) {
						String cargoGradename = nextNode.getProperty("operatorsName").getString();

						s.put(cargoGradename);

					}

				} // while Operators close
				obj = new JSONObject();
				obj.put("Data", s);
				obj.put("url", "http://35.188.227.168:8983/solr/#/AllReport/Operators");
				obj.put("DocumentName", "Operators");

				postSolr(obj, out);

			}

		} catch (Exception e) {

		}
	}

	public static void fetchCargogradePort(PrintWriter out, Session session) {
		try {
			Node scorpioDatabase = session.getRootNode().getNode("scorpioDataBase");
			Node Port = scorpioDatabase.getNode("Port");
			JSONArray s = null;

			if (Port.hasNodes()) {
				s = new JSONArray();
				NodeIterator itr2 = Port.getNodes();
				JSONObject obj = null;
				while (itr2.hasNext()) {
					Node nextNode = itr2.nextNode();

					if (nextNode.hasProperty("portName")) {
						String cargoGradename = nextNode.getProperty("portName").getString();

						s.put(cargoGradename);
					}

				} // while Operators closeobj = new JSONObject();
				obj = new JSONObject();
				obj.put("Data", s);
				obj.put("url", "http://35.188.227.168:8983/solr/#/AllReport/Port");
				obj.put("DocumentName", "Port");

				postSolr(obj, out);

				

			}

		} catch (Exception e) {

		}
	}
	
	public static void Date(PrintWriter out){
		
		try {
			
			JSONArray arr = new FormatDate().formatDateMain();
			JSONObject obj1 = new JSONObject();
			obj1.put("Data", arr);
			obj1.put("url", "http://35.188.227.168:8983/solr/#/AllReport/Date");
			obj1.put("DocumentName", "Date");

			postSolr(obj1, out);
			
			
		} catch (Exception e) {
			
		}
	}

}
