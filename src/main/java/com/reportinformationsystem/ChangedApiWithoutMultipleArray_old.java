package com.reportinformationsystem;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.Workspace;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;
import javax.jcr.query.RowIterator;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

import com.readGmail.GmailMethods;
import com.ui.data.CreateExcelMethods;

import ChangedStructureCurrent.ChangedStructureCurrent_Methods;
import ChangedStructureCurrent.FetchData;

public class ChangedApiWithoutMultipleArray_old {

	public static void main(String[] args) {

		String data = "Clarksons Platou Fuel Oil Rate Grid 31st October 2018 *correction bsea handy*";
		// if(data.contains("*")){
		data = data.replaceAll("\\*", "").replace(" ", "_");
		System.out.println(data);
		// }
	}

	public static JSONObject fetchTonnageDataChanged(PrintWriter out, Session session) {

		JSONObject mainJsonobj = null;
		try {
			FetchData fd = new FetchData();
			Node scorpio = session.getRootNode().getNode("scorpioDataBase");
			Node ReportData = scorpio.getNode("ReportData");
			Node Tonnage = ReportData.getNode("Tonnage");

			if (Tonnage.hasNodes()) {
				long size = Tonnage.getNodes().getSize();

				JSONObject keyNameObject = null;

				JSONArray keyNameObjectJsonarray = new JSONArray();

				NodeIterator itr = Tonnage.getNodes();
				String netChangedPointer = "";
				String limit = "";
				boolean nodeFlag = false;
				if (Tonnage.hasProperty("netChangedPointer")) {
					netChangedPointer = Tonnage.getProperty("netChangedPointer").getString();
					limit = String.valueOf(Integer.parseInt(netChangedPointer) + 1);
				}
				int i = 0;
				while (Tonnage.hasNode(limit)) {
					nodeFlag = true;
					Node nextnode = Tonnage.getNode(limit);

					int parseId = 0;
					i++;
					String subNodeName = nextnode.getName();

					String EmploymentStatus = "";
					String Owners = "";
					String OpenPort = "";
					String Operators = "";
					String Id = "";
					String CargoType = "";
					String Comment = "";
					String ETABasis = "";
					String OpenDate = "";
					String ReportType = "";
					String ReportTimestamp = "";
					String RepositionRegion = "";
					String Source = "";
					String VesselName = "";
					String dateAndTime = "";
					String Sr_No = "";
					String emailUrl = "";
					String emailClientNodeId = "";

					if (nextnode.hasProperty("Id")) {
						Id = nextnode.getProperty("Id").getString();
					}
					if (nextnode.hasProperty("Sr.No")) {
						Sr_No = nextnode.getProperty("Sr.No").getString();
					}
					if (nextnode.hasProperty("dateAndTime")) {
						dateAndTime = nextnode.getProperty("dateAndTime").getString();
					}
					if (nextnode.hasProperty("EmploymentStatus")) {
						EmploymentStatus = nextnode.getProperty("EmploymentStatus").getString();
						EmploymentStatus = fd.fetch_EmployementStatus(session, EmploymentStatus);

					}
					if (nextnode.hasProperty("Owners")) {
						Owners = nextnode.getProperty("Owners").getString();
						Owners = FetchData.fetch_Owners(session, Owners);

					}
					if (nextnode.hasProperty("emailUrl")) {
						emailUrl = nextnode.getProperty("emailUrl").getString();

					}
					if (nextnode.hasProperty("emailClientNodeId")) {
						emailClientNodeId = nextnode.getProperty("emailClientNodeId").getString();

					}

					if (nextnode.hasProperty("OpenPort")) {
						OpenPort = nextnode.getProperty("OpenPort").getString();
						String OpenPort1 = fd.fetch_Port(session, OpenPort);

						boolean checkjsonString = SaveReportDataClass.isJSONValid(OpenPort1);
						if (checkjsonString == true) {
							JSONObject data = new JSONObject(OpenPort1);
							if (data.has("portName")) {
								OpenPort = data.getString("portName");
							}
							if (data.has("Area")) {
								RepositionRegion = data.getString("Area");
								// check_Port_repositionRegion_id=check_OpenPort_id;
							}else {
								RepositionRegion="";
							}

						}

					}

					if (GmailMethods.isNullString(RepositionRegion)) {

						if (nextnode.hasProperty("RepositionRegion")) {
							RepositionRegion = nextnode.getProperty("RepositionRegion").getString();

							String RepositionRegion1 = fd.fetch_Port(session, RepositionRegion);
							boolean checkjsonString = SaveReportDataClass.isJSONValid(RepositionRegion1);
							if (checkjsonString == true) {
								JSONObject data = new JSONObject(RepositionRegion1);
								
								if (data.has("Area")) {
									RepositionRegion = data.getString("Area");
									// check_Port_repositionRegion_id=check_OpenPort_id;
								}

							}

						} else {
							RepositionRegion = "";
						}

					} // blank

					if (nextnode.hasProperty("Operators")) {
						Operators = nextnode.getProperty("Operators").getString();
						Operators = fd.fetch_Operators(session, Operators);

					}
					if (nextnode.hasProperty("CargoType")) {
						CargoType = nextnode.getProperty("CargoType").getString();
						CargoType = fd.fetch_CargoType(session, CargoType);

					}
					if (nextnode.hasProperty("Comment")) {
						Comment = nextnode.getProperty("Comment").getString();

					}
					if (nextnode.hasProperty("ETABasis")) {
						ETABasis = nextnode.getProperty("ETABasis").getString();

					}
					if (nextnode.hasProperty("OpenDate")) {
						OpenDate = nextnode.getProperty("OpenDate").getString();

					}
					if (nextnode.hasProperty("ReportType")) {
						ReportType = nextnode.getProperty("ReportType").getString();
						ReportType = fd.fetch_ReportType(session, ReportType);

					}
					if (nextnode.hasProperty("ReportTimestamp")) {
						ReportTimestamp = nextnode.getProperty("ReportTimestamp").getString();

					}

					if (nextnode.hasProperty("Source")) {
						Source = nextnode.getProperty("Source").getString();
						Source = fd.fetch_Source(session, Source);

					}
					if (nextnode.hasProperty("VesselName")) {
						VesselName = nextnode.getProperty("VesselName").getString();
						VesselName = fd.fetch_vesselName(session, VesselName);
						boolean checkjsonString = SaveReportDataClass.isJSONValid(VesselName);
						if (checkjsonString == true) {
							JSONObject fetch_vesselNamejsonobject = new JSONObject(VesselName);
							if (fetch_vesselNamejsonobject.has("vessel_Id")) {
								VesselName = fetch_vesselNamejsonobject.getString("vessel_Id");
							}
						}

					}
					String Built = "";
					if (nextnode.hasProperty("Built")) {

						Built = nextnode.getProperty("Built").getString();

					}
					String DWT = "";
					if (nextnode.hasProperty("DWT")) {

						DWT = nextnode.getProperty("DWT").getString();

					}
					String Cubics = "";
					if (nextnode.hasProperty("Cubics")) {

						Cubics = nextnode.getProperty("Cubics").getString();

					}
					String LOA = "";
					if (nextnode.hasProperty("LOA")) {

						LOA = nextnode.getProperty("LOA").getString();

					}

					String ICE = "";
					if (nextnode.hasProperty("ICE")) {

						ICE = nextnode.getProperty("ICE").getString();

					}

					String SternLine = "";
					if (nextnode.hasProperty("SternLine")) {

						SternLine = nextnode.getProperty("SternLine").getString();

					}
					String VesselType = "";
					if (nextnode.hasProperty("VesselType")) {

						VesselType = nextnode.getProperty("VesselType").getString();
						VesselType = FetchData.fetch_vesselType(session, VesselType);

					}

					keyNameObject = new JSONObject();

					keyNameObject.put("tonnageId", Id);
					keyNameObject.put("dateAndTime", dateAndTime);
					keyNameObject.put("EmploymentStatus", EmploymentStatus);
					keyNameObject.put("Owners", Owners);
					keyNameObject.put("OpenPort", OpenPort);
					keyNameObject.put("Operators", Operators);
					keyNameObject.put("CargoType", CargoType);
					keyNameObject.put("Comment", Comment);
					keyNameObject.put("ETABasis", ETABasis);
					keyNameObject.put("OpenDate", OpenDate);
					keyNameObject.put("ReportType", ReportType);
					keyNameObject.put("ReportTimestamp", ReportTimestamp);
					keyNameObject.put("RepositionRegion", RepositionRegion);
					keyNameObject.put("Source", Source);
					keyNameObject.put("VesselName", VesselName);
					keyNameObject.put("Built", Built);
					keyNameObject.put("DWT", DWT);
					keyNameObject.put("Cubics", Cubics);
					keyNameObject.put("LOA", LOA);
					keyNameObject.put("ICE", ICE);
					keyNameObject.put("SternLine", SternLine);
					keyNameObject.put("VesselType", VesselType);
					keyNameObject.put("subNodeName", subNodeName);
					keyNameObject.put("size", size + 1);// parseId
					keyNameObject.put("parseId", parseId + 1);
					keyNameObject.put("i", i);
					keyNameObject.put("Sr_No", Sr_No);
					keyNameObject.put("emailUrl", emailUrl);
					keyNameObject.put("emailClientNodeId", emailClientNodeId);

					if (keyNameObject.length() > 0) {
						keyNameObjectJsonarray.put(keyNameObject);
					}
					limit = String.valueOf(Integer.parseInt(limit) + 1);

				} // while close (netpointer)

				if (!nodeFlag) {

					Date date = ReportBeforeOnWeekDataDisplay.getLastWeek1();
					DateFormat dateFormatlastweek = new SimpleDateFormat("dd-MM-yyyy");
					String lastWeekDate = dateFormatlastweek.format(date);
					// out.println("lastWeekDate: "+lastWeekDate);

					Calendar cal = null;
					cal = Calendar.getInstance();

					Date todayDate = new Date(cal.getTimeInMillis());
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String currentDate = dateFormat.format(todayDate);
					// out.println("currentDate: "+currentDate);

					/*
					 * String
					 * dataCurrentDate=FetchData.convertStringToDate(currentDate
					 * ); String dataLastWeekDate=FetchData.convertStringToDate(
					 * lastWeekDate);
					 */
					keyNameObjectJsonarray = ReportBeforeOnWeekDataDisplay.getTonnageReportBeforeOneWeek(session,
							currentDate, lastWeekDate, out);

				} /*
					 * else{
					 * 
					 * keyNameObject=new JSONObject();
					 * keyNameObject.put("Error", "No New Data Available");
					 * keyNameObject.put("ErrorId", "1");
					 * keyNameObjectJsonarray.put(keyNameObject); }
					 */

				/*
				 * if(keyNameObjectJsonarray.length()>0){ mainJsonobj=new
				 * JSONObject(); mainJsonobj.put("tonnageList",
				 * keyNameObjectJsonarray);
				 * 
				 * }
				 */

				if (!nodeFlag && keyNameObjectJsonarray.length() > 0) {

					mainJsonobj = new JSONObject();
					mainJsonobj.put("tonnageList", keyNameObjectJsonarray);

					/*Node ExcelSummaryRecord = null;
					if (ReportData.hasNode("TonnageExcelSummaryRecord")) {
						ExcelSummaryRecord = ReportData.getNode("TonnageExcelSummaryRecord");
					} else {
						ExcelSummaryRecord = ReportData.addNode("TonnageExcelSummaryRecord");
						session.save();
					}

					String resultExcel = CreateExcelOfAllReports.createExcelTonnageNew(out, mainJsonobj.toString());
					JSONObject jsonResExcel = new JSONObject(resultExcel);
					if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("S")) {

						String fileName = "";
						if (jsonResExcel.has("fileName")) {
							fileName = jsonResExcel.getString("fileName");
						}

						Node ExcelSummaryRecord_SubNode = null;
						if (ExcelSummaryRecord.hasNode(fileName)) {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.getNode(fileName);
						} else {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.addNode(fileName);
							session.save();
						}

						ExcelSummaryRecord_SubNode.setProperty("summary_url", jsonResExcel.getString("fileUrl"));
						session.save();
						keyNameObject = new JSONObject();
						keyNameObject.put("summary_url", jsonResExcel.getString("fileUrl"));
						keyNameObjectJsonarray.put(keyNameObject);

					}else if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("E")) {

					}*/

				} else if (nodeFlag == true && keyNameObjectJsonarray.length() > 0) {

					mainJsonobj = new JSONObject();
					mainJsonobj.put("tonnageList", keyNameObjectJsonarray);

					/*Node ExcelSummaryRecord = null;
					if (ReportData.hasNode("TonnageExcelSummaryRecord")) {
						ExcelSummaryRecord = ReportData.getNode("TonnageExcelSummaryRecord");
					} else {
						ExcelSummaryRecord = ReportData.addNode("TonnageExcelSummaryRecord");
						session.save();
					}

					String resultExcel = CreateExcelOfAllReports.createExcelTonnageNew(out, mainJsonobj.toString());
					JSONObject jsonResExcel = new JSONObject(resultExcel);
					if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("S")) {

						String fileName = "";
						if (jsonResExcel.has("fileName")) {
							fileName = jsonResExcel.getString("fileName");
						}

						Node ExcelSummaryRecord_SubNode = null;
						if (ExcelSummaryRecord.hasNode(fileName)) {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.getNode(fileName);
						} else {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.addNode(fileName);
							session.save();
						}

						ExcelSummaryRecord_SubNode.setProperty("summary_url", jsonResExcel.getString("fileUrl"));
						session.save();
						keyNameObject.put("summary_url", jsonResExcel.getString("fileUrl"));

					} else if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("E")) {

					}*/

				}

				/*
				 * Node ExcelSummaryRecord=null;
				 * if(ReportData.hasNode("TonnageExcelSummaryRecord")){
				 * ExcelSummaryRecord =
				 * ReportData.getNode("TonnageExcelSummaryRecord"); }else{
				 * ExcelSummaryRecord =
				 * ReportData.addNode("TonnageExcelSummaryRecord");
				 * session.save(); }
				 * 
				 * String
				 * resultExcel=CreateExcelOfAllReports.createExcelTonnageNew(
				 * out, mainJsonobj.toString()); JSONObject jsonResExcel= new
				 * JSONObject(resultExcel); if(jsonResExcel.has("status") &&
				 * jsonResExcel.getString("status").equals("S")) {
				 * 
				 * String fileName=""; if(jsonResExcel.has("fileName")){
				 * fileName=jsonResExcel.getString("fileName"); }
				 * 
				 * Node ExcelSummaryRecord_SubNode=null;
				 * if(ExcelSummaryRecord.hasNode(fileName)){
				 * ExcelSummaryRecord_SubNode =
				 * ExcelSummaryRecord.getNode(fileName); }else{
				 * ExcelSummaryRecord_SubNode =
				 * ExcelSummaryRecord.addNode(fileName); session.save(); }
				 * 
				 * ExcelSummaryRecord_SubNode.setProperty("summary_url",
				 * jsonResExcel.getString("fileUrl")); session.save();
				 * keyNameObject.put("summary_url",
				 * jsonResExcel.getString("fileUrl"));
				 * 
				 * }else if(jsonResExcel.has("status") &&
				 * jsonResExcel.getString("status").equals("E")) {
				 * 
				 * }
				 */

				/*
				 * Tonnage.setProperty("netChangedPointer",String.valueOf(size))
				 * ; session.save();
				 */

			} // node check close if

		} catch (Exception e) {
			e.printStackTrace(out);
			out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public static JSONObject fetchSpotDataChanged(PrintWriter out, Session session) {

		JSONObject mainJsonobj = null;
		try {

			FetchData fd = new FetchData();
			Node scorpio = session.getRootNode().getNode("scorpioDataBase");
			Node ReportData = scorpio.getNode("ReportData");
			Node Spot = ReportData.getNode("Spot");

			if (Spot.hasNodes()) {
				long size = Spot.getNodes().getSize();
				String Id = "";

				JSONObject keyNameObject = null;
				JSONArray keyNameObjectJsonarray = new JSONArray();

				NodeIterator itr = Spot.getNodes();
				int i = 0;

				String netChangedPointer = "";
				String limit = "";
				boolean nodeFlag = false;
				if (Spot.hasProperty("netChangedPointer")) {
					netChangedPointer = Spot.getProperty("netChangedPointer").getString();
					limit = String.valueOf(Integer.parseInt(netChangedPointer) + 1);
				}

				while (Spot.hasNode(limit)) {
					nodeFlag = true;
					Node nextnode = Spot.getNode(limit);

					int parseId = 0;
					i++;
					String subNodeName = nextnode.getName();

					String Information = "";
					String FixtureType = "";
					String Charterer = "";
					String Status = "";
					String CargoType = "";
					String CargoGrade = "";
					String CargoQty = "";
					String LCStart = "";
					String Source = "";
					String VesselName = "";
					String LCEnd = "";
					String LoadPort = "";
					String DiscPort = "";
					String RateType = "";
					String Rate = "";
					String Comments = "";
					String dateAndTime = "";
					String Sr_No = "";
					String emailUrl = "";
					String emailClientNodeId = "";

					if (nextnode.hasProperty("Id")) {
						Id = nextnode.getProperty("Id").getString();
					}
					if (nextnode.hasProperty("Sr.No")) {
						Sr_No = nextnode.getProperty("Sr.No").getString();
					}
					if (nextnode.hasProperty("emailUrl")) {
						emailUrl = nextnode.getProperty("emailUrl").getString();
					}
					if (nextnode.hasProperty("dateAndTime")) {
						dateAndTime = nextnode.getProperty("dateAndTime").getString();
					}
					if (nextnode.hasProperty("Information")) {
						Information = nextnode.getProperty("Information").getString();
						Information = fd.fetch_Information(session, Information);

					}
					if (nextnode.hasProperty("FixtureType")) {
						FixtureType = nextnode.getProperty("FixtureType").getString();
						FixtureType = fd.fetch_ReportType(session, FixtureType);

					}
					if (nextnode.hasProperty("Charterer")) {
						Charterer = nextnode.getProperty("Charterer").getString();
						Charterer = fd.fetch_Charterer(session, Charterer);

					}
					if (nextnode.hasProperty("Status")) {
						Status = nextnode.getProperty("Status").getString();
						Status = fd.fetch_EmployementStatus(session, Status);

					}
					if (nextnode.hasProperty("CargoType")) {
						CargoType = nextnode.getProperty("CargoType").getString();
						CargoType = fd.fetch_CargoType(session, CargoType);

					}
					if (nextnode.hasProperty("CargoGrade")) {
						CargoGrade = nextnode.getProperty("CargoGrade").getString();
						CargoGrade = fd.fetch_CargoGrade(session, CargoGrade);

					}
					if (nextnode.hasProperty("CargoQty")) {
						CargoQty = nextnode.getProperty("CargoQty").getString();

					}
					if (nextnode.hasProperty("LCStart")) {
						LCStart = nextnode.getProperty("LCStart").getString();

					}
					if (nextnode.hasProperty("LCEnd")) {
						LCEnd = nextnode.getProperty("LCEnd").getString();

					}
					if (nextnode.hasProperty("LoadPort")) {
						LoadPort = nextnode.getProperty("LoadPort").getString();
						LoadPort = fd.fetch_Port(session, LoadPort);

					}
					if (nextnode.hasProperty("DiscPort")) {
						DiscPort = nextnode.getProperty("DiscPort").getString();
						DiscPort = fd.fetch_Port(session, DiscPort);

					}
					if (nextnode.hasProperty("VesselName")) {
						VesselName = nextnode.getProperty("VesselName").getString();

						VesselName = fd.fetch_vesselName(session, VesselName);
						JSONObject fetch_vesselNamejsonobject = new JSONObject(VesselName);
						if (fetch_vesselNamejsonobject.has("vessel_Id")) {
							VesselName = fetch_vesselNamejsonobject.getString("vessel_Id");
						}

					}
					if (nextnode.hasProperty("RateType")) {
						RateType = nextnode.getProperty("RateType").getString();
						RateType = fd.fetch_RateType(session, RateType);

					}
					if (nextnode.hasProperty("Rate")) {
						Rate = nextnode.getProperty("Rate").getString();

					}
					if (nextnode.hasProperty("Comments")) {
						Comments = nextnode.getProperty("Comments").getString();

					}
					String ReportDate = "";
					if (nextnode.hasProperty("ReportDate")) {

						ReportDate = nextnode.getProperty("ReportDate").getString();

					}
					if (nextnode.hasProperty("Source")) {
						Source = nextnode.getProperty("Source").getString();
						Source = fd.fetch_Source(session, Source);

					}
					String Vesseltype = "";
					if (nextnode.hasProperty("Vesseltype")) {

						Vesseltype = nextnode.getProperty("Vesseltype").getString();
						Vesseltype = FetchData.fetch_vesselType(session, Vesseltype);

					}
					if (nextnode.hasProperty("emailClientNodeId")) {
						emailClientNodeId = nextnode.getProperty("emailClientNodeId").getString();

					}

					keyNameObject = new JSONObject();

					keyNameObject.put("spotId", Id);
					keyNameObject.put("dateAndTime", dateAndTime);
					keyNameObject.put("Information", Information);
					keyNameObject.put("FixtureType", FixtureType);
					keyNameObject.put("LCStart", LCStart);
					keyNameObject.put("LCEnd", LCEnd);
					keyNameObject.put("LoadPort", LoadPort);
					keyNameObject.put("DiscPort", DiscPort);
					keyNameObject.put("VesselName", VesselName);
					keyNameObject.put("Charterer", Charterer);
					keyNameObject.put("Status", Status);
					keyNameObject.put("CargoType", CargoType);
					keyNameObject.put("CargoGrade", CargoGrade);
					keyNameObject.put("CargoQty", CargoQty);
					keyNameObject.put("RateType", RateType);
					keyNameObject.put("Rate", Rate);
					keyNameObject.put("Comments", Comments);
					keyNameObject.put("ReportDate", ReportDate);
					keyNameObject.put("Source", Source);
					keyNameObject.put("Vesseltype", Vesseltype);
					keyNameObject.put("subNodeName", subNodeName);
					keyNameObject.put("size", size + 1);// parseId
					keyNameObject.put("parseId", parseId + 1);
					keyNameObject.put("i", i);
					keyNameObject.put("Sr_No", Sr_No);
					keyNameObject.put("emailUrl", emailUrl);
					keyNameObject.put("emailClientNodeId", emailClientNodeId);

					if (keyNameObject.length() > 0) {
						keyNameObjectJsonarray.put(keyNameObject);
					}

					limit = String.valueOf(Integer.parseInt(limit) + 1);

				} // while close (netpointer)

				if (!nodeFlag) {

					Date date = ReportBeforeOnWeekDataDisplay.getLastWeek1();
					DateFormat dateFormatlastweek = new SimpleDateFormat("dd-MM-yyyy");
					String lastWeekDate = dateFormatlastweek.format(date);
					// out.println("lastWeekDate: "+lastWeekDate);

					Calendar cal = null;
					cal = Calendar.getInstance();

					Date todayDate = new Date(cal.getTimeInMillis());
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String currentDate = dateFormat.format(todayDate);

					keyNameObjectJsonarray = ReportBeforeOnWeekDataDisplay.getSpotReportBeforeOneWeek(session,
							currentDate, lastWeekDate, out);

				} /*
					 * else{ keyNameObject=new JSONObject();
					 * keyNameObject.put("Error", "No New Data Available");
					 * keyNameObject.put("ErrorId", "2");
					 * keyNameObjectJsonarray.put(keyNameObject); }
					 */

				/*
				 * if (keyNameObjectJsonarray.length() > 0) { mainJsonobj = new
				 * JSONObject(); mainJsonobj.put("spotList",
				 * keyNameObjectJsonarray);
				 * 
				 * }
				 */

				if (!nodeFlag && keyNameObjectJsonarray.length() > 0) {

					mainJsonobj = new JSONObject();
					mainJsonobj.put("spotList", keyNameObjectJsonarray);

					Node ExcelSummaryRecord = null;
					if (ReportData.hasNode("SpotExcelSummaryRecord")) {
						ExcelSummaryRecord = ReportData.getNode("SpotExcelSummaryRecord");
					} else {
						ExcelSummaryRecord = ReportData.addNode("SpotExcelSummaryRecord");
						session.save();
					}

					String resultExcel = CreateExcelOfAllReports.createExcelSpotNew(out, mainJsonobj.toString());
					JSONObject jsonResExcel = new JSONObject(resultExcel);
					if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("S")) {

						String fileName = "";
						if (jsonResExcel.has("fileName")) {
							fileName = jsonResExcel.getString("fileName");
						}

						Node ExcelSummaryRecord_SubNode = null;
						if (ExcelSummaryRecord.hasNode(fileName)) {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.getNode(fileName);
						} else {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.addNode(fileName);
							session.save();
						}

						ExcelSummaryRecord_SubNode.setProperty("summary_url", jsonResExcel.getString("fileUrl"));
						session.save();
						keyNameObject = new JSONObject();
						keyNameObject.put("summary_url", jsonResExcel.getString("fileUrl"));
						keyNameObjectJsonarray.put(keyNameObject);

					} else if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("E")) {

					}

				} else if (nodeFlag == true && keyNameObjectJsonarray.length() > 0) {

					mainJsonobj = new JSONObject();
					mainJsonobj.put("spotList", keyNameObjectJsonarray);

					Node ExcelSummaryRecord = null;
					if (ReportData.hasNode("SpotExcelSummaryRecord")) {
						ExcelSummaryRecord = ReportData.getNode("SpotExcelSummaryRecord");
					} else {
						ExcelSummaryRecord = ReportData.addNode("SpotExcelSummaryRecord");
						session.save();
					}

					String resultExcel = CreateExcelOfAllReports.createExcelSpotNew(out, mainJsonobj.toString());
					JSONObject jsonResExcel = new JSONObject(resultExcel);
					if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("S")) {

						String fileName = "";
						if (jsonResExcel.has("fileName")) {
							fileName = jsonResExcel.getString("fileName");
						}

						Node ExcelSummaryRecord_SubNode = null;
						if (ExcelSummaryRecord.hasNode(fileName)) {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.getNode(fileName);
						} else {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.addNode(fileName);
							session.save();
						}

						ExcelSummaryRecord_SubNode.setProperty("summary_url", jsonResExcel.getString("fileUrl"));
						session.save();
						keyNameObject.put("summary_url", jsonResExcel.getString("fileUrl"));

					} else if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("E")) {

					}

				}

				/*
				 * Node ExcelSummaryRecord = null; if
				 * (ReportData.hasNode("SpotExcelSummaryRecord")) {
				 * ExcelSummaryRecord =
				 * ReportData.getNode("SpotExcelSummaryRecord"); } else {
				 * ExcelSummaryRecord =
				 * ReportData.addNode("SpotExcelSummaryRecord"); session.save();
				 * }
				 * 
				 * String resultExcel =
				 * CreateExcelOfAllReports.createExcelSpotNew(out,
				 * mainJsonobj.toString()); JSONObject jsonResExcel = new
				 * JSONObject(resultExcel); if (jsonResExcel.has("status") &&
				 * jsonResExcel.getString("status").equals("S")) {
				 * 
				 * String fileName = ""; if (jsonResExcel.has("fileName")) {
				 * fileName = jsonResExcel.getString("fileName"); }
				 * 
				 * Node ExcelSummaryRecord_SubNode = null; if
				 * (ExcelSummaryRecord.hasNode(fileName)) {
				 * ExcelSummaryRecord_SubNode =
				 * ExcelSummaryRecord.getNode(fileName); } else {
				 * ExcelSummaryRecord_SubNode =
				 * ExcelSummaryRecord.addNode(fileName); session.save(); }
				 * 
				 * ExcelSummaryRecord_SubNode.setProperty("summary_url",
				 * jsonResExcel.getString("fileUrl")); session.save();
				 * keyNameObject.put("summary_url",
				 * jsonResExcel.getString("fileUrl"));
				 * 
				 * } else if (jsonResExcel.has("status") &&
				 * jsonResExcel.getString("status").equals("E")) {
				 * 
				 * }
				 */

				/*
				 * Spot.setProperty("netChangedPointer",String.valueOf(size));
				 * session.save();
				 */

			} // node check close if

		} catch (Exception e) {
			out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public static JSONObject fetchTimeCharterReportsDataChanged(PrintWriter out, Session session) {

		JSONObject mainJsonobj = null;
		try {
			FetchData fd = new FetchData();
			Node scorpio = session.getRootNode().getNode("scorpioDataBase");
			Node ReportData = scorpio.getNode("ReportData");
			Node TCReports = ReportData.getNode("TCReports");
			Node TimeCharterReports = TCReports.getNode("TimeCharterReport");

			if (TimeCharterReports.hasNodes()) {
				long size = TimeCharterReports.getNodes().getSize();
				String Id = "";

				JSONObject keyNameObject = null;
				JSONArray keyNameObjectJsonarray = new JSONArray();

				NodeIterator itr = TimeCharterReports.getNodes();
				int i = 0;

				String netChangedPointer = "";
				String limit = "";
				boolean nodeFlag = false;
				if (TimeCharterReports.hasProperty("netChangedPointer")) {
					netChangedPointer = TimeCharterReports.getProperty("netChangedPointer").getString();
					limit = String.valueOf(Integer.parseInt(netChangedPointer) + 1);
				}

				while (TimeCharterReports.hasNode(limit)) {
					nodeFlag = true;
					Node nextnode = TimeCharterReports.getNode(limit);

					i++;
					int parseId = 0;
					String subNodeName = nextnode.getName();

					String Information = "";
					String Charterer = "";
					String Source = "";
					String VesselName = "";
					String Comments = "";
					String Rate = "";
					String dateAndTime = "";
					String Sr_No = "";
					String emailUrl = "";

					if (nextnode.hasProperty("Id")) {
						Id = nextnode.getProperty("Id").getString();
					}
					if (nextnode.hasProperty("Sr.No")) {
						Sr_No = nextnode.getProperty("Sr.No").getString();
					}
					if (nextnode.hasProperty("dateAndTime")) {
						dateAndTime = nextnode.getProperty("dateAndTime").getString();
					}
					if (nextnode.hasProperty("Information")) {
						Information = nextnode.getProperty("Information").getString();
						Information = fd.fetch_Information(session, Information);

					}
					if (nextnode.hasProperty("emailUrl")) {
						emailUrl = nextnode.getProperty("emailUrl").getString();
					}
					String Spot_TC = "";
					if (nextnode.hasProperty("Spot_TC")) {

						Spot_TC = nextnode.getProperty("Spot_TC").getString();
						Spot_TC = fd.fetch_ReportType(session, Spot_TC);

					}
					String Date = "";
					if (nextnode.hasProperty("Date")) {

						Date = nextnode.getProperty("Date").getString();

					}

					if (nextnode.hasProperty("VesselName")) {
						VesselName = nextnode.getProperty("VesselName").getString();

						VesselName = fd.fetch_vesselName(session, VesselName);

						JSONObject fetch_vesselNamejsonobject = new JSONObject(VesselName);
						if (fetch_vesselNamejsonobject.has("vessel_Id")) {
							VesselName = fetch_vesselNamejsonobject.getString("vessel_Id");
						}

					}

					String CPP_DPP = "";
					if (nextnode.hasProperty("CPP_DPP")) {

						CPP_DPP = nextnode.getProperty("CPP_DPP").getString();
						CPP_DPP = fd.fetch_CargoType(session, CPP_DPP);

					}

					if (nextnode.hasProperty("Charterer")) {
						Charterer = nextnode.getProperty("Charterer").getString();
						Charterer = fd.fetch_Charterer(session, Charterer);

					}
					String Delivery = "";
					if (nextnode.hasProperty("Delivery")) {

						Delivery = nextnode.getProperty("Delivery").getString();

					}
					String DeliveryPlace = "";
					if (nextnode.hasProperty("DeliveryPlace")) {

						DeliveryPlace = nextnode.getProperty("DeliveryPlace").getString();
						DeliveryPlace = fd.fetch_DeliveryPlace(session, DeliveryPlace);

					}
					String Period = "";
					if (nextnode.hasProperty("Period")) {

						Period = nextnode.getProperty("Period").getString();

					}
					String Periodunit = "";
					if (nextnode.hasProperty("Periodunit")) {

						Periodunit = nextnode.getProperty("Periodunit").getString();
						Periodunit = fd.fetch_Periodunit(session, Periodunit);

					}
					String Redelivery = "";
					if (nextnode.hasProperty("Redelivery")) {

						Redelivery = nextnode.getProperty("Redelivery").getString();

					}
					String TCRate = "";
					if (nextnode.hasProperty("TCRate")) {

						TCRate = nextnode.getProperty("TCRate").getString();

					}

					if (nextnode.hasProperty("Rate")) {
						Rate = nextnode.getProperty("Rate").getString();

					}
					String Unit = "";
					if (nextnode.hasProperty("Unit")) {

						Unit = nextnode.getProperty("Unit").getString();
						Unit = fd.fetch_currencyUnit(session, Unit);

					}

					if (nextnode.hasProperty("Comments")) {
						Comments = nextnode.getProperty("Comments").getString();

					}

					if (nextnode.hasProperty("Source")) {
						Source = nextnode.getProperty("Source").getString();
						Source = fd.fetch_Source(session, Source);

					}
					String Vesseltype = "";
					if (nextnode.hasProperty("Vesseltype")) {

						Vesseltype = nextnode.getProperty("Vesseltype").getString();
						Vesseltype = FetchData.fetch_vesselType(session, Vesseltype);

					}
					String TimeStamp = "";
					if (nextnode.hasProperty("TimeStamp")) {

						TimeStamp = nextnode.getProperty("TimeStamp").getString();

					}
					String emailClientNodeId = "";
					if (nextnode.hasProperty("emailClientNodeId")) {
						emailClientNodeId = nextnode.getProperty("emailClientNodeId").getString();

					}

					keyNameObject = new JSONObject();

					keyNameObject.put("TimeCharterReportsId", Id);
					keyNameObject.put("dateAndTime", dateAndTime);
					keyNameObject.put("Information", Information);
					keyNameObject.put("Spot_TC", Spot_TC);
					keyNameObject.put("Date", Date);
					keyNameObject.put("VesselName", VesselName);
					keyNameObject.put("CPP_DPP", CPP_DPP);
					keyNameObject.put("Charterer", Charterer);
					keyNameObject.put("Delivery", Delivery);
					keyNameObject.put("DeliveryPlace", DeliveryPlace);
					keyNameObject.put("Period", Period);
					keyNameObject.put("Periodunit", Periodunit);
					keyNameObject.put("Redelivery", Redelivery);
					keyNameObject.put("TCRate", TCRate);
					keyNameObject.put("Rate", Rate);
					keyNameObject.put("Unit", Unit);
					keyNameObject.put("Comments", Comments);
					keyNameObject.put("Source", Source);
					keyNameObject.put("Vesseltype", Vesseltype);
					keyNameObject.put("TimeStamp", TimeStamp);
					keyNameObject.put("subNodeName", subNodeName);
					keyNameObject.put("size", size + 1);// parseId
					keyNameObject.put("parseId", parseId + 1);
					keyNameObject.put("i", i);
					keyNameObject.put("Sr_No", Sr_No);
					keyNameObject.put("emailUrl", emailUrl);
					keyNameObject.put("emailClientNodeId", emailClientNodeId);

					if (keyNameObject.length() > 0) {
						keyNameObjectJsonarray.put(keyNameObject);
					}

					limit = String.valueOf(Integer.parseInt(limit) + 1);

				} // while close

				if (!nodeFlag) {

					Date date = ReportBeforeOnWeekDataDisplay.getLastWeek1();
					DateFormat dateFormatlastweek = new SimpleDateFormat("dd-MM-yyyy");
					String lastWeekDate = dateFormatlastweek.format(date);
					// out.println("lastWeekDate: "+lastWeekDate);

					Calendar cal = null;
					cal = Calendar.getInstance();

					Date todayDate = new Date(cal.getTimeInMillis());
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String currentDate = dateFormat.format(todayDate);

					keyNameObjectJsonarray = ReportBeforeOnWeekDataDisplay.getTimeCharterReportBeforeOneWeek(session,
							currentDate, lastWeekDate, out);

				} /*
					 * else{ keyNameObject=new JSONObject();
					 * keyNameObject.put("Error", "No New Data Available");
					 * keyNameObject.put("ErrorId", "3");
					 * keyNameObjectJsonarray.put(keyNameObject); }
					 */

				if (!nodeFlag && keyNameObjectJsonarray.length() > 0) {

					mainJsonobj = new JSONObject();
					mainJsonobj.put("TimeCharterReportsList", keyNameObjectJsonarray);

					Node ExcelSummaryRecord = null;
					if (ReportData.hasNode("TimeCharterReportsExcelSummaryRecord")) {
						ExcelSummaryRecord = ReportData.getNode("TimeCharterReportsExcelSummaryRecord");
					} else {
						ExcelSummaryRecord = ReportData.addNode("TimeCharterReportsExcelSummaryRecord");
						session.save();
					}

					String resultExcel = CreateExcelOfAllReports.createExcelTimeCharterReportsNew(out,
							mainJsonobj.toString());
					JSONObject jsonResExcel = new JSONObject(resultExcel);
					if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("S")) {

						String fileName = "";
						if (jsonResExcel.has("fileName")) {
							fileName = jsonResExcel.getString("fileName");
						}

						Node ExcelSummaryRecord_SubNode = null;
						if (ExcelSummaryRecord.hasNode(fileName)) {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.getNode(fileName);
						} else {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.addNode(fileName);
							session.save();
						}

						ExcelSummaryRecord_SubNode.setProperty("summary_url", jsonResExcel.getString("fileUrl"));
						session.save();
						keyNameObject = new JSONObject();
						keyNameObject.put("summary_url", jsonResExcel.getString("fileUrl"));
						keyNameObjectJsonarray.put(keyNameObject);

					} else if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("E")) {

					}

				} else if (nodeFlag == true && keyNameObjectJsonarray.length() > 0) {

					mainJsonobj = new JSONObject();
					mainJsonobj.put("TimeCharterReportsList", keyNameObjectJsonarray);

					Node ExcelSummaryRecord = null;
					if (ReportData.hasNode("TimeCharterReportsExcelSummaryRecord")) {
						ExcelSummaryRecord = ReportData.getNode("TimeCharterReportsExcelSummaryRecord");
					} else {
						ExcelSummaryRecord = ReportData.addNode("TimeCharterReportsExcelSummaryRecord");
						session.save();
					}

					String resultExcel = CreateExcelOfAllReports.createExcelTimeCharterReportsNew(out,
							mainJsonobj.toString());
					JSONObject jsonResExcel = new JSONObject(resultExcel);
					if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("S")) {

						String fileName = "";
						if (jsonResExcel.has("fileName")) {
							fileName = jsonResExcel.getString("fileName");
						}

						Node ExcelSummaryRecord_SubNode = null;
						if (ExcelSummaryRecord.hasNode(fileName)) {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.getNode(fileName);
						} else {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.addNode(fileName);
							session.save();
						}

						ExcelSummaryRecord_SubNode.setProperty("summary_url", jsonResExcel.getString("fileUrl"));
						session.save();
						keyNameObject.put("summary_url", jsonResExcel.getString("fileUrl"));

					} else if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("E")) {

					}

				}

				/*
				 * if (keyNameObjectJsonarray.length() > 0) { mainJsonobj = new
				 * JSONObject(); mainJsonobj.put("TimeCharterReportsList",
				 * keyNameObjectJsonarray);
				 * 
				 * // out.println(mainJsonobj); }
				 * 
				 * Node ExcelSummaryRecord = null; if
				 * (ReportData.hasNode("TimeCharterReportsExcelSummaryRecord"))
				 * { ExcelSummaryRecord =
				 * ReportData.getNode("TimeCharterReportsExcelSummaryRecord"); }
				 * else { ExcelSummaryRecord =
				 * ReportData.addNode("TimeCharterReportsExcelSummaryRecord");
				 * session.save(); }
				 * 
				 * String resultExcel =
				 * CreateExcelOfAllReports.createExcelTimeCharterReportsNew(out,
				 * mainJsonobj.toString()); JSONObject jsonResExcel = new
				 * JSONObject(resultExcel); if (jsonResExcel.has("status") &&
				 * jsonResExcel.getString("status").equals("S")) {
				 * 
				 * String fileName = ""; if (jsonResExcel.has("fileName")) {
				 * fileName = jsonResExcel.getString("fileName"); }
				 * 
				 * Node ExcelSummaryRecord_SubNode = null; if
				 * (ExcelSummaryRecord.hasNode(fileName)) {
				 * ExcelSummaryRecord_SubNode =
				 * ExcelSummaryRecord.getNode(fileName); } else {
				 * ExcelSummaryRecord_SubNode =
				 * ExcelSummaryRecord.addNode(fileName); session.save(); }
				 * 
				 * ExcelSummaryRecord_SubNode.setProperty("summary_url",
				 * jsonResExcel.getString("fileUrl")); session.save();
				 * keyNameObject.put("summary_url",
				 * jsonResExcel.getString("fileUrl"));
				 * 
				 * } else if (jsonResExcel.has("status") &&
				 * jsonResExcel.getString("status").equals("E")) {
				 * 
				 * }
				 */
				/*
				 * TimeCharterReports.setProperty("netChangedPointer",String.
				 * valueOf(size)); session.save();
				 */

			} // node check close if

		} catch (Exception e) {
			e.printStackTrace(out);
			out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public static JSONObject fetchBrokerTcRateDataChanged(PrintWriter out, Session session) {

		JSONObject mainJsonobj = null;
		try {
			FetchData fd = new FetchData();
			Node scorpio = session.getRootNode().getNode("scorpioDataBase");
			Node ReportData = scorpio.getNode("ReportData");
			Node TCReports = ReportData.getNode("TCReports");
			Node TimeCharterReports = TCReports.getNode("BrokerTCRate");

			if (TimeCharterReports.hasNodes()) {

				JSONObject keyNameObject = null;
				long size = TimeCharterReports.getNodes().getSize();
				JSONArray keyNameObjectJsonarray = new JSONArray();

				NodeIterator itr = TimeCharterReports.getNodes();
				int i = 0;

				String netChangedPointer = "";
				String limit = "";
				boolean nodeFlag = false;
				if (TimeCharterReports.hasProperty("netChangedPointer")) {
					netChangedPointer = TimeCharterReports.getProperty("netChangedPointer").getString();
					limit = String.valueOf(Integer.parseInt(netChangedPointer) + 1);
				}

				while (TimeCharterReports.hasNode(limit)) {
					nodeFlag = true;
					Node nextnode = TimeCharterReports.getNode(limit);

					String Id = "";
					int parseId = 0;
					i++;

					String dateAndTime = "";
					String Sr_No = "";
					String emailUrl = "";

					String subNodeName = nextnode.getName();

					if (nextnode.hasProperty("Id")) {

						Id = nextnode.getProperty("Id").getString();
						parseId = Integer.parseInt(Id);
					}
					if (nextnode.hasProperty("Sr.No")) {
						Sr_No = nextnode.getProperty("Sr.No").getString();
					}
					if (nextnode.hasProperty("dateAndTime")) {
						dateAndTime = nextnode.getProperty("dateAndTime").getString();
					}
					if (nextnode.hasProperty("emailUrl")) {
						emailUrl = nextnode.getProperty("emailUrl").getString();
					}
					String Broker = "";
					if (nextnode.hasProperty("Broker")) {

						Broker = nextnode.getProperty("Broker").getString();
						Broker = fd.fetch_Source(session, Broker);

					}
					String VesselType = "";
					if (nextnode.hasProperty("VesselType")) {

						VesselType = nextnode.getProperty("VesselType").getString();
						VesselType = FetchData.fetch_vesselType(session, VesselType);

					}
					String Month_Year = "";
					if (nextnode.hasProperty("Month_Year")) {

						Month_Year = nextnode.getProperty("Month_Year").getString();

					}
					String first_yr = "";
					if (nextnode.hasProperty("first_yr")) {

						first_yr = nextnode.getProperty("first_yr").getString();

					}

					String second_yr = "";
					if (nextnode.hasProperty("second_yr")) {

						second_yr = nextnode.getProperty("second_yr").getString();

					}
					String third_yr = "";
					if (nextnode.hasProperty("third_yr")) {

						third_yr = nextnode.getProperty("third_yr").getString();

					}
					String fifth_yr = "";
					if (nextnode.hasProperty("fifth_yr")) {

						fifth_yr = nextnode.getProperty("fifth_yr").getString();

					}
					String emailClientNodeId = "";
					if (nextnode.hasProperty("emailClientNodeId")) {
						emailClientNodeId = nextnode.getProperty("emailClientNodeId").getString();

					}

					keyNameObject = new JSONObject();

					keyNameObject.put("BrokerTcRateId", Id);
					keyNameObject.put("dateAndTime", dateAndTime);
					keyNameObject.put("Broker", Broker);
					keyNameObject.put("VesselType", VesselType);
					keyNameObject.put("Month_Year", Month_Year);
					keyNameObject.put("first_yr", first_yr);
					keyNameObject.put("second_yr", second_yr);
					keyNameObject.put("third_yr", third_yr);
					keyNameObject.put("fifth_yr", fifth_yr);

					keyNameObject.put("subNodeName", subNodeName);
					keyNameObject.put("size", size + 1);// parseId
					keyNameObject.put("parseId", parseId + 1);
					keyNameObject.put("i", i);
					keyNameObject.put("Sr_No", Sr_No);
					keyNameObject.put("emailUrl", emailUrl);
					keyNameObject.put("emailClientNodeId", emailClientNodeId);

					if (keyNameObject.length() > 0) {
						keyNameObjectJsonarray.put(keyNameObject);
					}

					limit = String.valueOf(Integer.parseInt(limit) + 1);

				} // while close

				if (!nodeFlag) {

					Date date = ReportBeforeOnWeekDataDisplay.getLastWeek1();
					DateFormat dateFormatlastweek = new SimpleDateFormat("dd-MM-yyyy");
					String lastWeekDate = dateFormatlastweek.format(date);
					// out.println("lastWeekDate: "+lastWeekDate);

					Calendar cal = null;
					cal = Calendar.getInstance();

					Date todayDate = new Date(cal.getTimeInMillis());
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					String currentDate = dateFormat.format(todayDate);

					keyNameObjectJsonarray = ReportBeforeOnWeekDataDisplay.getBrokerTcRateReportBeforeOneWeek(session,
							currentDate, lastWeekDate, out);

				} /*
					 * else{ keyNameObject=new JSONObject();
					 * keyNameObject.put("Error", "No New Data Available");
					 * keyNameObject.put("ErrorId", "4");
					 * keyNameObjectJsonarray.put(keyNameObject); }
					 */

				if (!nodeFlag && keyNameObjectJsonarray.length() > 0) {

					mainJsonobj = new JSONObject();
					mainJsonobj.put("BrokerTcRateList", keyNameObjectJsonarray);

					Node ExcelSummaryRecord = null;
					if (ReportData.hasNode("BrokerTcRateExcelSummaryRecord")) {
						ExcelSummaryRecord = ReportData.getNode("BrokerTcRateExcelSummaryRecord");
					} else {
						ExcelSummaryRecord = ReportData.addNode("BrokerTcRateExcelSummaryRecord");
						session.save();
					}

					String resultExcel = CreateExcelOfAllReports.createExcelBrokerTcRateNew(out,
							mainJsonobj.toString());
					JSONObject jsonResExcel = new JSONObject(resultExcel);
					if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("S")) {

						String fileName = "";
						if (jsonResExcel.has("fileName")) {
							fileName = jsonResExcel.getString("fileName");
						}

						Node ExcelSummaryRecord_SubNode = null;
						if (ExcelSummaryRecord.hasNode(fileName)) {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.getNode(fileName);
						} else {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.addNode(fileName);
							session.save();
						}

						ExcelSummaryRecord_SubNode.setProperty("summary_url", jsonResExcel.getString("fileUrl"));
						session.save();
						keyNameObject = new JSONObject();
						keyNameObject.put("summary_url", jsonResExcel.getString("fileUrl"));
						keyNameObjectJsonarray.put(keyNameObject);

					} else if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("E")) {

					}

				} else if (nodeFlag == true && keyNameObjectJsonarray.length() > 0) {

					mainJsonobj = new JSONObject();
					mainJsonobj.put("BrokerTcRateList", keyNameObjectJsonarray);

					Node ExcelSummaryRecord = null;
					if (ReportData.hasNode("BrokerTcRateExcelSummaryRecord")) {
						ExcelSummaryRecord = ReportData.getNode("BrokerTcRateExcelSummaryRecord");
					} else {
						ExcelSummaryRecord = ReportData.addNode("BrokerTcRateExcelSummaryRecord");
						session.save();
					}

					String resultExcel = CreateExcelOfAllReports.createExcelBrokerTcRateNew(out,
							mainJsonobj.toString());
					JSONObject jsonResExcel = new JSONObject(resultExcel);
					if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("S")) {

						String fileName = "";
						if (jsonResExcel.has("fileName")) {
							fileName = jsonResExcel.getString("fileName");
						}

						Node ExcelSummaryRecord_SubNode = null;
						if (ExcelSummaryRecord.hasNode(fileName)) {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.getNode(fileName);
						} else {
							ExcelSummaryRecord_SubNode = ExcelSummaryRecord.addNode(fileName);
							session.save();
						}

						ExcelSummaryRecord_SubNode.setProperty("summary_url", jsonResExcel.getString("fileUrl"));
						session.save();
						keyNameObject.put("summary_url", jsonResExcel.getString("fileUrl"));

					} else if (jsonResExcel.has("status") && jsonResExcel.getString("status").equals("E")) {

					}

				}

				/*
				 * if (keyNameObjectJsonarray.length() > 0) { mainJsonobj = new
				 * JSONObject(); mainJsonobj.put("BrokerTcRateList",
				 * keyNameObjectJsonarray);
				 * 
				 * // out.println(mainJsonobj); }
				 * 
				 * Node ExcelSummaryRecord = null; if
				 * (ReportData.hasNode("BrokerTcRateExcelSummaryRecord")) {
				 * ExcelSummaryRecord =
				 * ReportData.getNode("BrokerTcRateExcelSummaryRecord"); } else
				 * { ExcelSummaryRecord =
				 * ReportData.addNode("BrokerTcRateExcelSummaryRecord");
				 * session.save(); }
				 * 
				 * String resultExcel =
				 * CreateExcelOfAllReports.createExcelBrokerTcRateNew(out,
				 * mainJsonobj.toString()); JSONObject jsonResExcel = new
				 * JSONObject(resultExcel); if (jsonResExcel.has("status") &&
				 * jsonResExcel.getString("status").equals("S")) {
				 * 
				 * String fileName = ""; if (jsonResExcel.has("fileName")) {
				 * fileName = jsonResExcel.getString("fileName"); }
				 * 
				 * Node ExcelSummaryRecord_SubNode = null; if
				 * (ExcelSummaryRecord.hasNode(fileName)) {
				 * ExcelSummaryRecord_SubNode =
				 * ExcelSummaryRecord.getNode(fileName); } else {
				 * ExcelSummaryRecord_SubNode =
				 * ExcelSummaryRecord.addNode(fileName); session.save(); }
				 * 
				 * ExcelSummaryRecord_SubNode.setProperty("summary_url",
				 * jsonResExcel.getString("fileUrl")); session.save();
				 * keyNameObject.put("summary_url",
				 * jsonResExcel.getString("fileUrl"));
				 * 
				 * } else if (jsonResExcel.has("status") &&
				 * jsonResExcel.getString("status").equals("E")) {
				 * 
				 * }
				 */

				/*
				 * TimeCharterReports.setProperty("netChangedPointer",String.
				 * valueOf(size)); session.save();
				 */

			} // node check close if

		} catch (Exception e) {
			out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public JSONObject getTonnageReportWithoutDataFrame(Session session, String reportType, PrintWriter out) {

		JSONObject mainJsonobj = null;
		try {
			FetchData fd = new FetchData();
			if (!GmailMethods.isNullString(reportType)) {

				Node scorpio = session.getRootNode().getNode("scorpioDataBase");
				Node ReportData = scorpio.getNode("ReportData");
				Node Tonnage = ReportData.getNode("Tonnage");
				FetchData FD = new FetchData();
				String reporttypequeryid = FD.check_ReportTypeToFetchapi(session, reportType, out);
				Workspace workspace = session.getWorkspace();

				String slingqery = "select [ReportType] from [nt:base] where ReportType ='" + reporttypequeryid
						+ "'  and ISDESCENDANTNODE('/scorpioDataBase/ReportData/Tonnage/')";

				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();

				RowIterator rowIterator = queryResult.getRows();
				boolean nodeFlag = false;
				long size = queryResult.getRows().getSize();
				JSONObject keyNameObject = null;
				JSONArray keyNameObjectJsonarray = new JSONArray();
				String netChangedPointer = "";
				String limit = "";

				if (Tonnage.hasProperty("netChangedPointer")) {
					netChangedPointer = Tonnage.getProperty("netChangedPointer").getString();
					limit = String.valueOf(Integer.parseInt(netChangedPointer) + 1);
				}
				if (rowIterator != null) {

					while (Tonnage.hasNode(limit)) {
						nodeFlag = true;
						Node nextnode = Tonnage.getNode(limit);

						String EmploymentStatus = "";
						String Owners = "";
						String OpenPort = "";
						String Operators = "";
						String Id = "";
						String CargoType = "";
						String Comment = "";
						String ETABasis = "";
						String OpenDate = "";
						String ReportType = "";
						String ReportTimestamp = "";
						String RepositionRegion = "";
						String Source = "";
						String VesselName = "";

						if (nextnode.hasProperty("Id")) {
							Id = nextnode.getProperty("Id").getString();
						}

						if (nextnode.hasProperty("EmploymentStatus")) {
							EmploymentStatus = nextnode.getProperty("EmploymentStatus").getString();
							EmploymentStatus = fd.fetch_EmployementStatus(session, EmploymentStatus);

						}
						if (nextnode.hasProperty("Owners")) {
							Owners = nextnode.getProperty("Owners").getString();
							Owners = FetchData.fetch_Owners(session, Owners);

						}
						/*if (nextnode.hasProperty("OpenPort")) {
							OpenPort = nextnode.getProperty("OpenPort").getString();
							OpenPort = fd.fetch_Port(session, OpenPort);

						}*/
						if (nextnode.hasProperty("Operators")) {
							Operators = nextnode.getProperty("Operators").getString();
							Operators = fd.fetch_Operators(session, Operators);

						}
						if (nextnode.hasProperty("CargoType")) {
							CargoType = nextnode.getProperty("CargoType").getString();
							CargoType = fd.fetch_CargoType(session, CargoType);

						}
						if (nextnode.hasProperty("Comment")) {
							Comment = nextnode.getProperty("Comment").getString();

						}
						if (nextnode.hasProperty("ETABasis")) {
							ETABasis = nextnode.getProperty("ETABasis").getString();

						}
						if (nextnode.hasProperty("OpenDate")) {
							OpenDate = nextnode.getProperty("OpenDate").getString();

						}
						if (nextnode.hasProperty("ReportType")) {
							ReportType = nextnode.getProperty("ReportType").getString();
							ReportType = fd.fetch_ReportType(session, ReportType);

						}
						if (nextnode.hasProperty("ReportTimestamp")) {
							ReportTimestamp = nextnode.getProperty("ReportTimestamp").getString();

						}
						/*
						 * if (nextnode.hasProperty("RepositionRegion")) {
						 * RepositionRegion =
						 * nextnode.getProperty("RepositionRegion").getString();
						 * RepositionRegion = fd.fetch_Port(session,
						 * RepositionRegion);
						 * 
						 * }
						 */
						
						if (nextnode.hasProperty("OpenPort")) {
							OpenPort = nextnode.getProperty("OpenPort").getString();
							String OpenPort1 = fd.fetch_Port(session, OpenPort);

							boolean checkjsonString = SaveReportDataClass.isJSONValid(OpenPort1);
							if (checkjsonString == true) {
								JSONObject data = new JSONObject(OpenPort1);
								if (data.has("portName")) {
									OpenPort = data.getString("portName");
								}
								if (data.has("Area")) {
									RepositionRegion = data.getString("Area");
									// check_Port_repositionRegion_id=check_OpenPort_id;
								}else {
									RepositionRegion="";
								}

							}

						}

						if (GmailMethods.isNullString(RepositionRegion)) {

							if (nextnode.hasProperty("RepositionRegion")) {
								RepositionRegion = nextnode.getProperty("RepositionRegion").getString();

								String RepositionRegion1 = fd.fetch_Port(session, RepositionRegion);
								boolean checkjsonString = SaveReportDataClass.isJSONValid(RepositionRegion1);
								if (checkjsonString == true) {
									JSONObject data = new JSONObject(RepositionRegion1);
									
									if (data.has("Area")) {
										RepositionRegion = data.getString("Area");
										// check_Port_repositionRegion_id=check_OpenPort_id;
									}

								}

							} else {
								RepositionRegion = "";
							}

						} // blank

						/*if (nextnode.hasProperty("RepositionRegion")) {
							RepositionRegion = nextnode.getProperty("RepositionRegion").getString();

							if (!GmailMethods.isNullString(RepositionRegion)) {
								Pattern pattern = Pattern.compile("[0-9]+");
								Matcher matcher = pattern.matcher(RepositionRegion);

								// Find all matches
								boolean numbeBooleanr = false;
								while (matcher.find()) {
									// Get the matching string
									numbeBooleanr = true;
									// String match = matcher.group();
								}

								if (numbeBooleanr == true) {
									RepositionRegion = fd.fetch_Port(session, RepositionRegion);
								} else {
									RepositionRegion = nextnode.getProperty("RepositionRegion").getString();
								}

							} else {
								RepositionRegion = "";
							}

						}*/

						if (nextnode.hasProperty("Source")) {
							Source = nextnode.getProperty("Source").getString();
							Source = fd.fetch_Source(session, Source);

						}

						if (nextnode.hasProperty("VesselName")) {
							VesselName = nextnode.getProperty("VesselName").getString();
							VesselName = fd.fetch_vesselName(session, VesselName);

							boolean checkjsonString = SaveReportDataClass.isJSONValid(VesselName);
							if (checkjsonString == true) {
								JSONObject fetch_vesselNamejsonobject = new JSONObject(VesselName);
								if (fetch_vesselNamejsonobject.has("vessel_Id")) {
									VesselName = fetch_vesselNamejsonobject.getString("vessel_Id");
								}
							}

						}
						String Built = "";
						if (nextnode.hasProperty("Built")) {

							Built = nextnode.getProperty("Built").getString();

						}
						String DWT = "";
						if (nextnode.hasProperty("DWT")) {

							DWT = nextnode.getProperty("DWT").getString();

						}
						String Cubics = "";
						if (nextnode.hasProperty("Cubics")) {

							Cubics = nextnode.getProperty("Cubics").getString();

						}
						String LOA = "";
						if (nextnode.hasProperty("LOA")) {

							LOA = nextnode.getProperty("LOA").getString();

						}

						String ICE = "";
						if (nextnode.hasProperty("ICE")) {

							ICE = nextnode.getProperty("ICE").getString();

						}

						String SternLine = "";
						if (nextnode.hasProperty("SternLine")) {

							SternLine = nextnode.getProperty("SternLine").getString();

						}
						String VesselType = "";
						if (nextnode.hasProperty("VesselType")) {

							VesselType = nextnode.getProperty("VesselType").getString();
							VesselType = FetchData.fetch_vesselType(session, VesselType);

						}

						keyNameObject = new JSONObject();

						keyNameObject.put("tonnageId", Id);
						keyNameObject.put("EmploymentStatus", EmploymentStatus);
						keyNameObject.put("Owners", Owners);
						keyNameObject.put("OpenPort", OpenPort);
						keyNameObject.put("Operators", Operators);
						keyNameObject.put("CargoType", CargoType);
						keyNameObject.put("Comment", Comment);
						keyNameObject.put("ETABasis", ETABasis);
						keyNameObject.put("OpenDate", OpenDate);
						keyNameObject.put("ReportType", ReportType);
						keyNameObject.put("ReportTimestamp", ReportTimestamp);
						keyNameObject.put("RepositionRegion", RepositionRegion);
						keyNameObject.put("Source", Source);
						keyNameObject.put("VesselName", VesselName);
						keyNameObject.put("Built", Built);
						keyNameObject.put("DWT", DWT);
						keyNameObject.put("Cubics", Cubics);
						keyNameObject.put("LOA", LOA);
						keyNameObject.put("ICE", ICE);
						keyNameObject.put("SternLine", SternLine);
						keyNameObject.put("VesselType", VesselType);

						if (keyNameObject.length() > 0) {
							keyNameObjectJsonarray.put(keyNameObject);
						}
						limit = String.valueOf(Integer.parseInt(limit) + 1);

					} // while
				}

				if (!nodeFlag) {
					keyNameObject = new JSONObject();
					keyNameObject.put("Error", "No New Data Available");
					keyNameObjectJsonarray.put(keyNameObject);

				}

				if (keyNameObjectJsonarray.length() > 0) {
					mainJsonobj = new JSONObject();
					mainJsonobj.put("tonnageList", keyNameObjectJsonarray);

				}

				Tonnage.setProperty("netChangedPointer", String.valueOf(size));
				session.save();

			}

		} catch (Exception e) {
			out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public JSONObject getSpotReportWithoutDataFrame(Session session, String reportType, PrintWriter out) {

		JSONObject mainJsonobj = null;
		try {
			FetchData fd = new FetchData();
			if (!GmailMethods.isNullString(reportType)) {

				Node scorpio = session.getRootNode().getNode("scorpioDataBase");
				Node ReportData = scorpio.getNode("ReportData");
				Node Tonnage = ReportData.getNode("Spot");
				FetchData FD = new FetchData();
				String reporttypequeryid = FD.check_ReportTypeToFetchapi(session, reportType, out);
				Workspace workspace = session.getWorkspace();

				String slingqery = "select [ReportType] from [nt:base] where ReportType ='" + reporttypequeryid
						+ "'  and ISDESCENDANTNODE('/scorpioDataBase/ReportData/Spot/')";

				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();

				RowIterator rowIterator = queryResult.getRows();
				boolean nodeFlag = false;
				long size = Tonnage.getNodes().getSize();
				JSONObject keyNameObject = null;
				JSONArray keyNameObjectJsonarray = new JSONArray();
				String netChangedPointer = "";
				String limit = "";

				if (Tonnage.hasProperty("netChangedPointer")) {
					netChangedPointer = Tonnage.getProperty("netChangedPointer").getString();
					limit = String.valueOf(Integer.parseInt(netChangedPointer) + 1);
				}
				if (rowIterator != null) {

					while (Tonnage.hasNode(limit)) {
						nodeFlag = true;
						Node nextnode = Tonnage.getNode(limit);

						String Information = "";
						String FixtureType = "";
						String Charterer = "";
						String Status = "";
						String CargoType = "";
						String CargoGrade = "";
						String CargoQty = "";
						String LCStart = "";
						String Source = "";
						String VesselName = "";
						String LCEnd = "";
						String LoadPort = "";
						String DiscPort = "";
						String RateType = "";
						String Rate = "";
						String Comments = "";
						String Id = "";

						if (nextnode.hasProperty("Id")) {
							Id = nextnode.getProperty("Id").getString();
						}

						if (nextnode.hasProperty("Information")) {
							Information = nextnode.getProperty("Information").getString();
							Information = fd.fetch_Information(session, Information);

						}
						if (nextnode.hasProperty("FixtureType")) {
							FixtureType = nextnode.getProperty("FixtureType").getString();
							FixtureType = fd.fetch_ReportType(session, FixtureType);

						}
						if (nextnode.hasProperty("Charterer")) {
							Charterer = nextnode.getProperty("Charterer").getString();
							Charterer = fd.fetch_Charterer(session, Charterer);

						}
						if (nextnode.hasProperty("Status")) {
							Status = nextnode.getProperty("Status").getString();
							Status = fd.fetch_EmployementStatus(session, Status);

						}
						if (nextnode.hasProperty("CargoType")) {
							CargoType = nextnode.getProperty("CargoType").getString();
							CargoType = fd.fetch_CargoType(session, CargoType);

						}
						if (nextnode.hasProperty("CargoGrade")) {
							CargoGrade = nextnode.getProperty("CargoGrade").getString();
							CargoGrade = fd.fetch_CargoGrade(session, CargoGrade);

						}
						if (nextnode.hasProperty("CargoQty")) {
							CargoQty = nextnode.getProperty("CargoQty").getString();

						}
						if (nextnode.hasProperty("LCStart")) {
							LCStart = nextnode.getProperty("LCStart").getString();

						}
						if (nextnode.hasProperty("LCEnd")) {
							LCEnd = nextnode.getProperty("LCEnd").getString();

						}
						if (nextnode.hasProperty("LoadPort")) {
							LoadPort = nextnode.getProperty("LoadPort").getString();
							LoadPort = fd.fetch_Port(session, LoadPort);

						}
						if (nextnode.hasProperty("DiscPort")) {
							DiscPort = nextnode.getProperty("DiscPort").getString();
							DiscPort = fd.fetch_Port(session, DiscPort);

						}
						if (nextnode.hasProperty("VesselName")) {
							VesselName = nextnode.getProperty("VesselName").getString();

							VesselName = fd.fetch_vesselName(session, VesselName);
							JSONObject fetch_vesselNamejsonobject = new JSONObject(VesselName);
							if (fetch_vesselNamejsonobject.has("vessel_Id")) {
								VesselName = fetch_vesselNamejsonobject.getString("vessel_Id");
							}

						}
						if (nextnode.hasProperty("RateType")) {
							RateType = nextnode.getProperty("RateType").getString();
							RateType = fd.fetch_RateType(session, RateType);

						}
						if (nextnode.hasProperty("Rate")) {
							Rate = nextnode.getProperty("Rate").getString();

						}
						if (nextnode.hasProperty("Comments")) {
							Comments = nextnode.getProperty("Comments").getString();

						}
						String ReportDate = "";
						if (nextnode.hasProperty("ReportDate")) {

							ReportDate = nextnode.getProperty("ReportDate").getString();

						}
						if (nextnode.hasProperty("Source")) {
							Source = nextnode.getProperty("Source").getString();
							Source = fd.fetch_Source(session, Source);

						}
						String Vesseltype = "";
						if (nextnode.hasProperty("Vesseltype")) {

							Vesseltype = nextnode.getProperty("Vesseltype").getString();
							Vesseltype = FetchData.fetch_vesselType(session, Vesseltype);

						}
						keyNameObject = new JSONObject();

						keyNameObject.put("spotId", Id);
						keyNameObject.put("Information", Information);
						keyNameObject.put("FixtureType", FixtureType);
						keyNameObject.put("LCStart", LCStart);
						keyNameObject.put("LCEnd", LCEnd);
						keyNameObject.put("LoadPort", LoadPort);
						keyNameObject.put("DiscPort", DiscPort);
						keyNameObject.put("VesselName", VesselName);
						keyNameObject.put("Charterer", Charterer);
						keyNameObject.put("Status", Status);
						keyNameObject.put("CargoType", CargoType);
						keyNameObject.put("CargoGrade", CargoGrade);
						keyNameObject.put("CargoQty", CargoQty);
						keyNameObject.put("RateType", RateType);
						keyNameObject.put("Rate", Rate);
						keyNameObject.put("Comments", Comments);
						keyNameObject.put("ReportDate", ReportDate);
						keyNameObject.put("Source", Source);
						keyNameObject.put("Vesseltype", Vesseltype);

						if (keyNameObject.length() > 0) {
							keyNameObjectJsonarray.put(keyNameObject);
						}

						limit = String.valueOf(Integer.parseInt(limit) + 1);

					} // while

				}

				if (!nodeFlag) {
					keyNameObject = new JSONObject();
					keyNameObject.put("Error", "No New Data Available");
					keyNameObjectJsonarray.put(keyNameObject);

				}

				if (keyNameObjectJsonarray.length() > 0) {
					mainJsonobj = new JSONObject();
					mainJsonobj.put("spotList", keyNameObjectJsonarray);

				}

				Tonnage.setProperty("netChangedPointer", String.valueOf(size));
				session.save();

			}

		} catch (Exception e) {
			e.printStackTrace(out);
			out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public JSONObject getTimeCharterReportWithoutDataFrame(Session session, String reportType, PrintWriter out) {

		JSONObject mainJsonobj = null;
		try {
			FetchData fd = new FetchData();
			if (!GmailMethods.isNullString(reportType)) {

				Node scorpio = session.getRootNode().getNode("scorpioDataBase");
				Node ReportData = scorpio.getNode("ReportData");
				Node TCReports = ReportData.getNode("TCReports");
				Node Tonnage = TCReports.getNode("TimeCharterReport");

				FetchData FD = new FetchData();
				String reporttypequeryid = FD.check_ReportTypeToFetchapi(session, reportType, out);
				Workspace workspace = session.getWorkspace();

				String slingqery = "select [ReportType] from [nt:base] where ReportType ='" + reporttypequeryid
						+ "'  and ISDESCENDANTNODE('/scorpioDataBase/ReportData/TCReports/TimeCharterReport/')";

				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();

				RowIterator rowIterator = queryResult.getRows();
				boolean nodeFlag = false;
				long size = Tonnage.getNodes().getSize();
				JSONObject keyNameObject = null;
				JSONArray keyNameObjectJsonarray = new JSONArray();
				String netChangedPointer = "";
				String limit = "";

				if (Tonnage.hasProperty("netChangedPointer")) {
					netChangedPointer = Tonnage.getProperty("netChangedPointer").getString();
					limit = String.valueOf(Integer.parseInt(netChangedPointer) + 1);
				}
				if (rowIterator != null) {

					while (Tonnage.hasNode(limit)) {
						nodeFlag = true;
						Node nextnode = Tonnage.getNode(limit);

						String Information = "";
						String Charterer = "";
						String Source = "";
						String VesselName = "";
						String Comments = "";
						String Rate = "";
						String Id = "";

						if (nextnode.hasProperty("Id")) {
							Id = nextnode.getProperty("Id").getString();
						}

						if (nextnode.hasProperty("Information")) {
							Information = nextnode.getProperty("Information").getString();
							Information = fd.fetch_Information(session, Information);

						}
						String Spot_TC = "";
						if (nextnode.hasProperty("Spot_TC")) {

							Spot_TC = nextnode.getProperty("Spot_TC").getString();
							Spot_TC = fd.fetch_ReportType(session, Spot_TC);

						}
						String Date = "";
						if (nextnode.hasProperty("Date")) {

							Date = nextnode.getProperty("Date").getString();

						}

						if (nextnode.hasProperty("VesselName")) {
							VesselName = nextnode.getProperty("VesselName").getString();

							VesselName = fd.fetch_vesselName(session, VesselName);
							JSONObject fetch_vesselNamejsonobject = new JSONObject(VesselName);
							if (fetch_vesselNamejsonobject.has("vessel_Id")) {
								VesselName = fetch_vesselNamejsonobject.getString("vessel_Id");
							}

						}

						String CPP_DPP = "";
						if (nextnode.hasProperty("CPP_DPP")) {

							CPP_DPP = nextnode.getProperty("CPP_DPP").getString();
							CPP_DPP = fd.fetch_CargoType(session, CPP_DPP);

						}
						if (nextnode.hasProperty("Charterer")) {
							Charterer = nextnode.getProperty("Charterer").getString();
							Charterer = fd.fetch_Charterer(session, Charterer);

						}
						String Delivery = "";
						if (nextnode.hasProperty("Delivery")) {

							Delivery = nextnode.getProperty("Delivery").getString();

						}
						String DeliveryPlace = "";
						if (nextnode.hasProperty("DeliveryPlace")) {

							DeliveryPlace = nextnode.getProperty("DeliveryPlace").getString();
							DeliveryPlace = fd.fetch_DeliveryPlace(session, DeliveryPlace);

						}
						String Period = "";
						if (nextnode.hasProperty("Period")) {

							Period = nextnode.getProperty("Period").getString();

						}
						String Periodunit = "";
						if (nextnode.hasProperty("Periodunit")) {

							Periodunit = nextnode.getProperty("Periodunit").getString();
							Periodunit = fd.fetch_Periodunit(session, Periodunit);

						}
						String Redelivery = "";
						if (nextnode.hasProperty("Redelivery")) {

							Redelivery = nextnode.getProperty("Redelivery").getString();

						}
						String TCRate = "";
						if (nextnode.hasProperty("TCRate")) {

							TCRate = nextnode.getProperty("TCRate").getString();

						}

						if (nextnode.hasProperty("Rate")) {
							Rate = nextnode.getProperty("Rate").getString();

						}
						String Unit = "";
						if (nextnode.hasProperty("Unit")) {

							Unit = nextnode.getProperty("Unit").getString();
							Unit = fd.fetch_currencyUnit(session, Unit);

						}

						if (nextnode.hasProperty("Comments")) {
							Comments = nextnode.getProperty("Comments").getString();

						}

						if (nextnode.hasProperty("Source")) {
							Source = nextnode.getProperty("Source").getString();
							Source = fd.fetch_Source(session, Source);

						}
						String Vesseltype = "";
						if (nextnode.hasProperty("Vesseltype")) {

							Vesseltype = nextnode.getProperty("Vesseltype").getString();
							Vesseltype = FetchData.fetch_vesselType(session, Vesseltype);

						}
						String TimeStamp = "";
						if (nextnode.hasProperty("TimeStamp")) {

							TimeStamp = nextnode.getProperty("TimeStamp").getString();

						}

						keyNameObject = new JSONObject();

						keyNameObject.put("TimeCharterReportsId", Id);
						keyNameObject.put("Information", Information);
						keyNameObject.put("Spot_TC", Spot_TC);
						keyNameObject.put("Date", Date);
						keyNameObject.put("VesselName", VesselName);
						keyNameObject.put("CPP_DPP", CPP_DPP);
						keyNameObject.put("Charterer", Charterer);
						keyNameObject.put("Delivery", Delivery);
						keyNameObject.put("DeliveryPlace", DeliveryPlace);
						keyNameObject.put("Period", Period);
						keyNameObject.put("Periodunit", Periodunit);
						keyNameObject.put("Redelivery", Redelivery);
						keyNameObject.put("TCRate", TCRate);
						keyNameObject.put("Rate", Rate);
						keyNameObject.put("Unit", Unit);
						keyNameObject.put("Comments", Comments);
						keyNameObject.put("Source", Source);
						keyNameObject.put("Vesseltype", Vesseltype);
						keyNameObject.put("TimeStamp", TimeStamp);

						if (keyNameObject.length() > 0) {
							keyNameObjectJsonarray.put(keyNameObject);
						}

						limit = String.valueOf(Integer.parseInt(limit) + 1);

					} // while

				}

				if (!nodeFlag) {
					keyNameObject = new JSONObject();
					keyNameObject.put("Error", "No New Data Available");
					keyNameObjectJsonarray.put(keyNameObject);

				}

				if (keyNameObjectJsonarray.length() > 0) {
					mainJsonobj = new JSONObject();
					mainJsonobj.put("TimeCharterReportsList", keyNameObjectJsonarray);

				}

				Tonnage.setProperty("netChangedPointer", String.valueOf(size));
				session.save();

			}

		} catch (Exception e) {
			out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public JSONObject getBrokerTcRateReportWithoutDataFrame(Session session, String reportType, PrintWriter out) {

		JSONObject mainJsonobj = null;
		try {
			FetchData fd = new FetchData();
			if (!GmailMethods.isNullString(reportType)) {

				Node scorpio = session.getRootNode().getNode("scorpioDataBase");
				Node ReportData = scorpio.getNode("ReportData");
				Node TCReports = ReportData.getNode("TCReports");
				Node Tonnage = TCReports.getNode("BrokerTCRate");

				FetchData FD = new FetchData();
				String reporttypequeryid = FD.check_ReportTypeToFetchapi(session, reportType, out);
				Workspace workspace = session.getWorkspace();

				String slingqery = "select [ReportType] from [nt:base] where ReportType ='" + reporttypequeryid
						+ "'  and ISDESCENDANTNODE('/scorpioDataBase/ReportData/TCReports/BrokerTCRate/')";

				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();

				RowIterator rowIterator = queryResult.getRows();
				boolean nodeFlag = false;
				long size = Tonnage.getNodes().getSize();
				JSONObject keyNameObject = null;
				JSONArray keyNameObjectJsonarray = new JSONArray();
				String netChangedPointer = "";
				String limit = "";

				if (Tonnage.hasProperty("netChangedPointer")) {
					netChangedPointer = Tonnage.getProperty("netChangedPointer").getString();
					limit = String.valueOf(Integer.parseInt(netChangedPointer) + 1);
				}
				if (rowIterator != null) {

					while (Tonnage.hasNode(limit)) {
						nodeFlag = true;
						Node nextnode = Tonnage.getNode(limit);
						String Id = "";

						if (nextnode.hasProperty("Id")) {
							Id = nextnode.getProperty("Id").getString();
						}
						String Broker = "";
						if (nextnode.hasProperty("Broker")) {

							Broker = nextnode.getProperty("Broker").getString();
							Broker = fd.fetch_Source(session, Broker);

						}
						String VesselType = "";
						if (nextnode.hasProperty("VesselType")) {

							VesselType = nextnode.getProperty("VesselType").getString();
							VesselType = FetchData.fetch_vesselType(session, VesselType);

						}
						String Month_Year = "";
						if (nextnode.hasProperty("Month_Year")) {

							Month_Year = nextnode.getProperty("Month_Year").getString();

						}
						String first_yr = "";
						if (nextnode.hasProperty("first_yr")) {

							first_yr = nextnode.getProperty("first_yr").getString();

						}

						String second_yr = "";
						if (nextnode.hasProperty("second_yr")) {

							second_yr = nextnode.getProperty("second_yr").getString();

						}
						String third_yr = "";
						if (nextnode.hasProperty("third_yr")) {

							third_yr = nextnode.getProperty("third_yr").getString();

						}
						String fifth_yr = "";
						if (nextnode.hasProperty("fifth_yr")) {

							fifth_yr = nextnode.getProperty("fifth_yr").getString();

						}

						keyNameObject = new JSONObject();

						keyNameObject.put("BrokerTcRateId", Id);
						keyNameObject.put("Broker", Broker);
						keyNameObject.put("VesselType", VesselType);
						keyNameObject.put("Month_Year", Month_Year);
						keyNameObject.put("first_yr", first_yr);
						keyNameObject.put("second_yr", second_yr);
						keyNameObject.put("third_yr", third_yr);
						keyNameObject.put("fifth_yr", fifth_yr);

						if (keyNameObject.length() > 0) {
							keyNameObjectJsonarray.put(keyNameObject);
						}

						limit = String.valueOf(Integer.parseInt(limit) + 1);

					} // while

				}

				if (!nodeFlag) {
					keyNameObject = new JSONObject();
					keyNameObject.put("Error", "No New Data Available");
					keyNameObjectJsonarray.put(keyNameObject);

				}

				if (keyNameObjectJsonarray.length() > 0) {
					mainJsonobj = new JSONObject();
					mainJsonobj.put("BrokerTcRateList", keyNameObjectJsonarray);

				}

				Tonnage.setProperty("netChangedPointer", String.valueOf(size));
				session.save();

			}

		} catch (Exception e) {
			out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public JSONObject getTonnageReportDateWithoutDataFrame(Session session, String from, String to, String reportType,
			PrintWriter out) {

		JSONObject mainJsonobj = null;
		try {

			if (!GmailMethods.isNullString(from) && !GmailMethods.isNullString(to)
					&& !GmailMethods.isNullString(reportType)) {

				FetchData fd = new FetchData();
				String reporttypequeryid = fd.check_ReportTypeToFetchapi(session, reportType, out);
				String dataFrom = FetchData.convertStringToDate(from);
				String dataTo = FetchData.convertStringToDate(to);
				Workspace workspace = session.getWorkspace();

				String slingqery = "select [ReportTimestamp] from [nt:base] where ReportTimestamp >='" + dataFrom
						+ "'and ReportTimestamp <='" + dataTo + "' and ReportType=" + reporttypequeryid
						+ " and ISDESCENDANTNODE('/scorpioDataBase/ReportData/Tonnage/')";
				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();
				NodeIterator iterator = queryResult.getNodes();
				boolean nodeFlag = false;

				JSONObject keyNameObject = null;
				JSONArray keyNameObjectJsonarray = new JSONArray();

				while (iterator.hasNext()) {

					nodeFlag = true;
					Node nextnode = iterator.nextNode();

					String EmploymentStatus = "";
					String Owners = "";
					String OpenPort = "";
					String Operators = "";
					String Id = "";
					String CargoType = "";
					String Comment = "";
					String ETABasis = "";
					String OpenDate = "";
					String ReportType = "";
					String ReportTimestamp = "";
					String RepositionRegion = "";
					String Source = "";
					String VesselName = "";

					if (nextnode.hasProperty("Id")) {
						Id = nextnode.getProperty("Id").getString();
					}

					if (nextnode.hasProperty("EmploymentStatus")) {
						EmploymentStatus = nextnode.getProperty("EmploymentStatus").getString();
						EmploymentStatus = fd.fetch_EmployementStatus(session, EmploymentStatus);

					}
					if (nextnode.hasProperty("Owners")) {
						Owners = nextnode.getProperty("Owners").getString();
						Owners = FetchData.fetch_Owners(session, Owners);

					}
					/*if (nextnode.hasProperty("OpenPort")) {
						OpenPort = nextnode.getProperty("OpenPort").getString();
						OpenPort = fd.fetch_Port(session, OpenPort);

					}*/
					if (nextnode.hasProperty("Operators")) {
						Operators = nextnode.getProperty("Operators").getString();
						Operators = fd.fetch_Operators(session, Operators);

					}
					if (nextnode.hasProperty("CargoType")) {
						CargoType = nextnode.getProperty("CargoType").getString();
						CargoType = fd.fetch_CargoType(session, CargoType);

					}
					if (nextnode.hasProperty("Comment")) {
						Comment = nextnode.getProperty("Comment").getString();

					}
					if (nextnode.hasProperty("ETABasis")) {
						ETABasis = nextnode.getProperty("ETABasis").getString();

					}
					if (nextnode.hasProperty("OpenDate")) {
						OpenDate = nextnode.getProperty("OpenDate").getString();

					}
					if (nextnode.hasProperty("ReportType")) {
						ReportType = nextnode.getProperty("ReportType").getString();
						ReportType = fd.fetch_ReportType(session, ReportType);

					}
					if (nextnode.hasProperty("ReportTimestamp")) {
						ReportTimestamp = nextnode.getProperty("ReportTimestamp").getString();

					}
					/*
					 * if (nextnode.hasProperty("RepositionRegion")) {
					 * RepositionRegion =
					 * nextnode.getProperty("RepositionRegion").getString();
					 * RepositionRegion = fd.fetch_Port(session,
					 * RepositionRegion);
					 * 
					 * }
					 */

					if (nextnode.hasProperty("OpenPort")) {
						OpenPort = nextnode.getProperty("OpenPort").getString();
						String OpenPort1 = fd.fetch_Port(session, OpenPort);

						boolean checkjsonString = SaveReportDataClass.isJSONValid(OpenPort1);
						if (checkjsonString == true) {
							JSONObject data = new JSONObject(OpenPort1);
							if (data.has("portName")) {
								OpenPort = data.getString("portName");
							}
							if (data.has("Area")) {
								RepositionRegion = data.getString("Area");
								// check_Port_repositionRegion_id=check_OpenPort_id;
							}else {
								RepositionRegion="";
							}

						}

					}

					if (GmailMethods.isNullString(RepositionRegion)) {

						if (nextnode.hasProperty("RepositionRegion")) {
							RepositionRegion = nextnode.getProperty("RepositionRegion").getString();

							String RepositionRegion1 = fd.fetch_Port(session, RepositionRegion);
							boolean checkjsonString = SaveReportDataClass.isJSONValid(RepositionRegion1);
							if (checkjsonString == true) {
								JSONObject data = new JSONObject(RepositionRegion1);
								
								if (data.has("Area")) {
									RepositionRegion = data.getString("Area");
									// check_Port_repositionRegion_id=check_OpenPort_id;
								}

							}

						} else {
							RepositionRegion = "";
						}

					} // blank
					
					/*if (nextnode.hasProperty("RepositionRegion")) {
						RepositionRegion = nextnode.getProperty("RepositionRegion").getString();

						if (!GmailMethods.isNullString(RepositionRegion)) {
							Pattern pattern = Pattern.compile("[0-9]+");
							Matcher matcher = pattern.matcher(RepositionRegion);

							// Find all matches
							boolean numbeBooleanr = false;
							while (matcher.find()) {
								// Get the matching string
								numbeBooleanr = true;
								// String match = matcher.group();
							}

							if (numbeBooleanr == true) {
								RepositionRegion = fd.fetch_Port(session, RepositionRegion);
							} else {
								RepositionRegion = nextnode.getProperty("RepositionRegion").getString();
							}

						} else {
							RepositionRegion = "";
						}

					}*/

					if (nextnode.hasProperty("Source")) {
						Source = nextnode.getProperty("Source").getString();
						Source = fd.fetch_Source(session, Source);

					}
					if (nextnode.hasProperty("VesselName")) {
						VesselName = nextnode.getProperty("VesselName").getString();
						VesselName = fd.fetch_vesselName(session, VesselName);

						boolean checkjsonString = SaveReportDataClass.isJSONValid(VesselName);
						if (checkjsonString == true) {
							JSONObject fetch_vesselNamejsonobject = new JSONObject(VesselName);
							if (fetch_vesselNamejsonobject.has("vessel_Id")) {
								VesselName = fetch_vesselNamejsonobject.getString("vessel_Id");
							}
						}

					}
					String Built = "";
					if (nextnode.hasProperty("Built")) {

						Built = nextnode.getProperty("Built").getString();

					}
					String DWT = "";
					if (nextnode.hasProperty("DWT")) {

						DWT = nextnode.getProperty("DWT").getString();

					}
					String Cubics = "";
					if (nextnode.hasProperty("Cubics")) {

						Cubics = nextnode.getProperty("Cubics").getString();

					}
					String LOA = "";
					if (nextnode.hasProperty("LOA")) {

						LOA = nextnode.getProperty("LOA").getString();

					}

					String ICE = "";
					if (nextnode.hasProperty("ICE")) {

						ICE = nextnode.getProperty("ICE").getString();

					}

					String SternLine = "";
					if (nextnode.hasProperty("SternLine")) {

						SternLine = nextnode.getProperty("SternLine").getString();

					}
					String VesselType = "";
					if (nextnode.hasProperty("VesselType")) {

						VesselType = nextnode.getProperty("VesselType").getString();
						VesselType = FetchData.fetch_vesselType(session, VesselType);

					}

					keyNameObject = new JSONObject();

					keyNameObject.put("tonnageId", Id);
					keyNameObject.put("EmploymentStatus", EmploymentStatus);
					keyNameObject.put("Owners", Owners);
					keyNameObject.put("OpenPort", OpenPort);
					keyNameObject.put("Operators", Operators);
					keyNameObject.put("CargoType", CargoType);
					keyNameObject.put("Comment", Comment);
					keyNameObject.put("ETABasis", ETABasis);
					keyNameObject.put("OpenDate", OpenDate);
					keyNameObject.put("ReportType", ReportType);
					keyNameObject.put("ReportTimestamp", ReportTimestamp);
					keyNameObject.put("RepositionRegion", RepositionRegion);
					keyNameObject.put("Source", Source);
					keyNameObject.put("VesselName", VesselName);
					keyNameObject.put("Built", Built);
					keyNameObject.put("DWT", DWT);
					keyNameObject.put("Cubics", Cubics);
					keyNameObject.put("LOA", LOA);
					keyNameObject.put("ICE", ICE);
					keyNameObject.put("SternLine", SternLine);
					keyNameObject.put("VesselType", VesselType);

					if (keyNameObject.length() > 0) {
						keyNameObjectJsonarray.put(keyNameObject);
					}

				} // while

				if (!nodeFlag) {
					keyNameObject = new JSONObject();
					keyNameObject.put("Error", "no Data Available Between Given Dates");
					keyNameObjectJsonarray.put(keyNameObject);

				}

				if (keyNameObjectJsonarray.length() > 0) {
					mainJsonobj = new JSONObject();
					mainJsonobj.put("tonnageList", keyNameObjectJsonarray);

				}

			}

		} catch (Exception e) {
			out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public JSONObject getSpotReportDateWithoutDataFrame(Session session, String from, String to, String reportType,
			PrintWriter out) {

		JSONObject mainJsonobj = null;
		try {

			if (!GmailMethods.isNullString(from) && !GmailMethods.isNullString(to)
					&& !GmailMethods.isNullString(reportType)) {

				FetchData fd = new FetchData();
				String reporttypequeryid = fd.check_ReportTypeToFetchapi(session, reportType, out);
				String dataFrom = FetchData.convertStringToDate(from);
				String dataTo = FetchData.convertStringToDate(to);
				Workspace workspace = session.getWorkspace();
				String slingqery = "select [ReportTimestamp] from [nt:base] where ReportTimestamp >='" + dataFrom
						+ "'and ReportTimestamp <='" + dataTo + "' and ReportType=" + reporttypequeryid
						+ " and ISDESCENDANTNODE('/scorpioDataBase/ReportData/Spot/')";
				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();
				NodeIterator iterator = queryResult.getNodes();
				boolean nodeFlag = false;
				JSONObject keyNameObject = null;
				JSONArray keyNameObjectJsonarray = new JSONArray();

				while (iterator.hasNext()) {

					nodeFlag = true;
					Node nextnode = iterator.nextNode();

					String Information = "";
					String FixtureType = "";
					String Charterer = "";
					String Status = "";
					String CargoType = "";
					String CargoGrade = "";
					String CargoQty = "";
					String LCStart = "";
					String Source = "";
					String VesselName = "";
					String LCEnd = "";
					String LoadPort = "";
					String DiscPort = "";
					String RateType = "";
					String Rate = "";
					String Comments = "";
					String Id = "";

					if (nextnode.hasProperty("Id")) {
						Id = nextnode.getProperty("Id").getString();
					}

					if (nextnode.hasProperty("Information")) {
						Information = nextnode.getProperty("Information").getString();
						Information = fd.fetch_Information(session, Information);

					}
					if (nextnode.hasProperty("FixtureType")) {
						FixtureType = nextnode.getProperty("FixtureType").getString();
						FixtureType = fd.fetch_ReportType(session, FixtureType);

					}
					if (nextnode.hasProperty("Charterer")) {
						Charterer = nextnode.getProperty("Charterer").getString();
						Charterer = fd.fetch_Charterer(session, Charterer);

					}
					if (nextnode.hasProperty("Status")) {
						Status = nextnode.getProperty("Status").getString();
						Status = fd.fetch_EmployementStatus(session, Status);

					}
					if (nextnode.hasProperty("CargoType")) {
						CargoType = nextnode.getProperty("CargoType").getString();
						CargoType = fd.fetch_CargoType(session, CargoType);

					}
					if (nextnode.hasProperty("CargoGrade")) {
						CargoGrade = nextnode.getProperty("CargoGrade").getString();
						CargoGrade = fd.fetch_CargoGrade(session, CargoGrade);

					}
					if (nextnode.hasProperty("CargoQty")) {
						CargoQty = nextnode.getProperty("CargoQty").getString();

					}
					if (nextnode.hasProperty("LCStart")) {
						LCStart = nextnode.getProperty("LCStart").getString();

					}
					if (nextnode.hasProperty("LCEnd")) {
						LCEnd = nextnode.getProperty("LCEnd").getString();

					}
					if (nextnode.hasProperty("LoadPort")) {
						LoadPort = nextnode.getProperty("LoadPort").getString();
						LoadPort = fd.fetch_Port(session, LoadPort);

					}
					if (nextnode.hasProperty("DiscPort")) {
						DiscPort = nextnode.getProperty("DiscPort").getString();
						DiscPort = fd.fetch_Port(session, DiscPort);

					}
					if (nextnode.hasProperty("VesselName")) {
						VesselName = nextnode.getProperty("VesselName").getString();

						VesselName = fd.fetch_vesselName(session, VesselName);
						JSONObject fetch_vesselNamejsonobject = new JSONObject(VesselName);
						if (fetch_vesselNamejsonobject.has("vessel_Id")) {
							VesselName = fetch_vesselNamejsonobject.getString("vessel_Id");
						}

					}
					if (nextnode.hasProperty("RateType")) {
						RateType = nextnode.getProperty("RateType").getString();
						RateType = fd.fetch_RateType(session, RateType);

					}
					if (nextnode.hasProperty("Rate")) {
						Rate = nextnode.getProperty("Rate").getString();

					}
					if (nextnode.hasProperty("Comments")) {
						Comments = nextnode.getProperty("Comments").getString();

					}
					String ReportDate = "";
					if (nextnode.hasProperty("ReportDate")) {

						ReportDate = nextnode.getProperty("ReportDate").getString();

					}
					if (nextnode.hasProperty("Source")) {
						Source = nextnode.getProperty("Source").getString();
						Source = fd.fetch_Source(session, Source);

					}
					String Vesseltype = "";
					if (nextnode.hasProperty("Vesseltype")) {

						Vesseltype = nextnode.getProperty("Vesseltype").getString();
						Vesseltype = FetchData.fetch_vesselType(session, Vesseltype);

					}
					keyNameObject = new JSONObject();

					keyNameObject.put("spotId", Id);
					keyNameObject.put("Information", Information);
					keyNameObject.put("FixtureType", FixtureType);
					keyNameObject.put("LCStart", LCStart);
					keyNameObject.put("LCEnd", LCEnd);
					keyNameObject.put("LoadPort", LoadPort);
					keyNameObject.put("DiscPort", DiscPort);
					keyNameObject.put("VesselName", VesselName);
					keyNameObject.put("Charterer", Charterer);
					keyNameObject.put("Status", Status);
					keyNameObject.put("CargoType", CargoType);
					keyNameObject.put("CargoGrade", CargoGrade);
					keyNameObject.put("CargoQty", CargoQty);
					keyNameObject.put("RateType", RateType);
					keyNameObject.put("Rate", Rate);
					keyNameObject.put("Comments", Comments);
					keyNameObject.put("ReportDate", ReportDate);
					keyNameObject.put("Source", Source);
					keyNameObject.put("Vesseltype", Vesseltype);

					if (keyNameObject.length() > 0) {
						keyNameObjectJsonarray.put(keyNameObject);
					}

				} // while

				if (!nodeFlag) {
					keyNameObject = new JSONObject();
					keyNameObject.put("Error", "no Data Available Between Given Dates");
					keyNameObjectJsonarray.put(keyNameObject);

				}

				if (keyNameObjectJsonarray.length() > 0) {
					mainJsonobj = new JSONObject();
					mainJsonobj.put("spotList", keyNameObjectJsonarray);

				}

			}

		} catch (Exception e) {
			out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public JSONObject getTimeCharterReportDateWithoutDataFrame(Session session, String from, String to,
			String reportType, PrintWriter out) {

		JSONObject mainJsonobj = null;
		try {

			if (!GmailMethods.isNullString(from) && !GmailMethods.isNullString(to)
					&& !GmailMethods.isNullString(reportType)) {

				FetchData fd = new FetchData();
				String reporttypequeryid = fd.check_ReportTypeToFetchapi(session, reportType, out);
				String dataFrom = FetchData.convertStringToDate(from);
				String dataTo = FetchData.convertStringToDate(to);
				Workspace workspace = session.getWorkspace();
				String slingqery = "select [ReportTimestamp] from [nt:base] where ReportTimestamp >='" + dataFrom
						+ "'and ReportTimestamp <='" + dataTo + "' and ReportType=" + reporttypequeryid
						+ " and ISDESCENDANTNODE('/scorpioDataBase/ReportData/TCReports/TimeCharterReport/')";
				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();

				NodeIterator iterator = queryResult.getNodes();
				boolean nodeFlag = false;

				JSONObject keyNameObject = null;
				JSONArray keyNameObjectJsonarray = new JSONArray();

				while (iterator.hasNext()) {

					nodeFlag = true;
					Node nextnode = iterator.nextNode();

					String Information = "";
					String Charterer = "";
					String Source = "";
					String VesselName = "";
					String Comments = "";
					String Rate = "";
					String Id = "";

					if (nextnode.hasProperty("Id")) {
						Id = nextnode.getProperty("Id").getString();
					}

					if (nextnode.hasProperty("Information")) {
						Information = nextnode.getProperty("Information").getString();
						Information = fd.fetch_Information(session, Information);

					}
					String Spot_TC = "";
					if (nextnode.hasProperty("Spot_TC")) {

						Spot_TC = nextnode.getProperty("Spot_TC").getString();
						Spot_TC = fd.fetch_ReportType(session, Spot_TC);

					}
					String Date = "";
					if (nextnode.hasProperty("Date")) {

						Date = nextnode.getProperty("Date").getString();

					}

					if (nextnode.hasProperty("VesselName")) {
						VesselName = nextnode.getProperty("VesselName").getString();

						VesselName = fd.fetch_vesselName(session, VesselName);
						JSONObject fetch_vesselNamejsonobject = new JSONObject(VesselName);
						if (fetch_vesselNamejsonobject.has("vessel_Id")) {
							VesselName = fetch_vesselNamejsonobject.getString("vessel_Id");
						}

					}
					String CPP_DPP = "";
					if (nextnode.hasProperty("CPP_DPP")) {

						CPP_DPP = nextnode.getProperty("CPP_DPP").getString();
						CPP_DPP = fd.fetch_CargoType(session, CPP_DPP);

					}
					if (nextnode.hasProperty("Charterer")) {
						Charterer = nextnode.getProperty("Charterer").getString();
						Charterer = fd.fetch_Charterer(session, Charterer);

					}
					String Delivery = "";
					if (nextnode.hasProperty("Delivery")) {

						Delivery = nextnode.getProperty("Delivery").getString();

					}
					String DeliveryPlace = "";
					if (nextnode.hasProperty("DeliveryPlace")) {

						DeliveryPlace = nextnode.getProperty("DeliveryPlace").getString();
						DeliveryPlace = fd.fetch_DeliveryPlace(session, DeliveryPlace);

					}
					String Period = "";
					if (nextnode.hasProperty("Period")) {

						Period = nextnode.getProperty("Period").getString();

					}
					String Periodunit = "";
					if (nextnode.hasProperty("Periodunit")) {

						Periodunit = nextnode.getProperty("Periodunit").getString();
						Periodunit = fd.fetch_Periodunit(session, Periodunit);

					}
					String Redelivery = "";
					if (nextnode.hasProperty("Redelivery")) {

						Redelivery = nextnode.getProperty("Redelivery").getString();

					}
					String TCRate = "";
					if (nextnode.hasProperty("TCRate")) {

						TCRate = nextnode.getProperty("TCRate").getString();

					}

					if (nextnode.hasProperty("Rate")) {
						Rate = nextnode.getProperty("Rate").getString();

					}
					String Unit = "";
					if (nextnode.hasProperty("Unit")) {

						Unit = nextnode.getProperty("Unit").getString();
						Unit = fd.fetch_currencyUnit(session, Unit);

					}

					if (nextnode.hasProperty("Comments")) {
						Comments = nextnode.getProperty("Comments").getString();

					}

					if (nextnode.hasProperty("Source")) {
						Source = nextnode.getProperty("Source").getString();
						Source = fd.fetch_Source(session, Source);

					}
					String Vesseltype = "";
					if (nextnode.hasProperty("Vesseltype")) {

						Vesseltype = nextnode.getProperty("Vesseltype").getString();
						Vesseltype = FetchData.fetch_vesselType(session, Vesseltype);

					}
					String TimeStamp = "";
					if (nextnode.hasProperty("TimeStamp")) {

						TimeStamp = nextnode.getProperty("TimeStamp").getString();

					}

					keyNameObject = new JSONObject();

					keyNameObject.put("TimeCharterReportsId", Id);
					keyNameObject.put("Information", Information);
					keyNameObject.put("Spot_TC", Spot_TC);
					keyNameObject.put("Date", Date);
					keyNameObject.put("VesselName", VesselName);
					keyNameObject.put("CPP_DPP", CPP_DPP);
					keyNameObject.put("Charterer", Charterer);
					keyNameObject.put("Delivery", Delivery);
					keyNameObject.put("DeliveryPlace", DeliveryPlace);
					keyNameObject.put("Period", Period);
					keyNameObject.put("Periodunit", Periodunit);
					keyNameObject.put("Redelivery", Redelivery);
					keyNameObject.put("TCRate", TCRate);
					keyNameObject.put("Rate", Rate);
					keyNameObject.put("Unit", Unit);
					keyNameObject.put("Comments", Comments);
					keyNameObject.put("Source", Source);
					keyNameObject.put("Vesseltype", Vesseltype);
					keyNameObject.put("TimeStamp", TimeStamp);

					if (keyNameObject.length() > 0) {
						keyNameObjectJsonarray.put(keyNameObject);
					}

				} // while

				if (!nodeFlag) {
					keyNameObject = new JSONObject();
					keyNameObject.put("Error", "no Data Available Between Given Dates");
					keyNameObjectJsonarray.put(keyNameObject);

				}

				if (keyNameObjectJsonarray.length() > 0) {
					mainJsonobj = new JSONObject();
					mainJsonobj.put("TimeCharterReportsList", keyNameObjectJsonarray);

				}

			}

		} catch (Exception e) {
			out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public JSONObject getBrokerTcRateReportDateWithoutDataFrame(Session session, String from, String to,
			String reportType, PrintWriter out) {

		JSONObject mainJsonobj = null;
		try {

			if (!GmailMethods.isNullString(from) && !GmailMethods.isNullString(to)
					&& !GmailMethods.isNullString(reportType)) {

				FetchData fd = new FetchData();
				String reporttypequeryid = fd.check_ReportTypeToFetchapi(session, reportType, out);
				String dataFrom = FetchData.convertStringToDate(from);
				String dataTo = FetchData.convertStringToDate(to);
				Workspace workspace = session.getWorkspace();

				String slingqery = "select [ReportTimestamp] from [nt:base] where ReportTimestamp >='" + dataFrom
						+ "'and ReportTimestamp <='" + dataTo + "' and ReportType=" + reporttypequeryid
						+ " and ISDESCENDANTNODE('/scorpioDataBase/ReportData/TCReports/BrokerTCRate/')";
				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();

				NodeIterator iterator = queryResult.getNodes();
				boolean nodeFlag = false;

				JSONObject keyNameObject = null;
				JSONArray keyNameObjectJsonarray = new JSONArray();

				while (iterator.hasNext()) {

					nodeFlag = true;
					Node nextnode = iterator.nextNode();

					String Id = "";

					if (nextnode.hasProperty("Id")) {

						Id = nextnode.getProperty("Id").getString();
					}
					String Broker = "";
					if (nextnode.hasProperty("Broker")) {

						Broker = nextnode.getProperty("Broker").getString();
						Broker = fd.fetch_Source(session, Broker);

					}
					String VesselType = "";
					if (nextnode.hasProperty("VesselType")) {

						VesselType = nextnode.getProperty("VesselType").getString();
						VesselType = FetchData.fetch_vesselType(session, VesselType);

					}
					String Month_Year = "";
					if (nextnode.hasProperty("Month_Year")) {

						Month_Year = nextnode.getProperty("Month_Year").getString();

					}
					String first_yr = "";
					if (nextnode.hasProperty("first_yr")) {

						first_yr = nextnode.getProperty("first_yr").getString();

					}

					String second_yr = "";
					if (nextnode.hasProperty("second_yr")) {

						second_yr = nextnode.getProperty("second_yr").getString();

					}
					String third_yr = "";
					if (nextnode.hasProperty("third_yr")) {

						third_yr = nextnode.getProperty("third_yr").getString();

					}
					String fifth_yr = "";
					if (nextnode.hasProperty("fifth_yr")) {

						fifth_yr = nextnode.getProperty("fifth_yr").getString();

					}

					keyNameObject = new JSONObject();

					keyNameObject.put("BrokerTcRateId", Id);
					keyNameObject.put("Broker", Broker);
					keyNameObject.put("VesselType", VesselType);
					keyNameObject.put("Month_Year", Month_Year);
					keyNameObject.put("first_yr", first_yr);
					keyNameObject.put("second_yr", second_yr);
					keyNameObject.put("third_yr", third_yr);
					keyNameObject.put("fifth_yr", fifth_yr);

					if (keyNameObject.length() > 0) {
						keyNameObjectJsonarray.put(keyNameObject);
					}

				} // while

				if (!nodeFlag) {
					keyNameObject = new JSONObject();
					keyNameObject.put("Error", "no Data Available Between Given Dates");
					keyNameObjectJsonarray.put(keyNameObject);

				}

				if (keyNameObjectJsonarray.length() > 0) {
					mainJsonobj = new JSONObject();
					mainJsonobj.put("BrokerTcRateList", keyNameObjectJsonarray);

				}

			}

		} catch (Exception e) {
			out.println(e.getMessage());

		}
		return mainJsonobj;

	}

}
