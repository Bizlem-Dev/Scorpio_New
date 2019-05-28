package com.mycode;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
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
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import com.abhishek.ConvertDate;
import com.abhishek.ErrorNodeReportwise;
import com.mycode.ReportTypeCorrection;
import com.readGmail.GmailMethods;
import com.readGmail.SlingMethods;
import com.subjectheaders.ChartererUnknownCheck;
import com.subjectheaders.CheckNan;
import com.subjectheaders.PortSlashFoundCheck;
import com.subjectheaders.SolrLogicSubjectHeaders;

import ChangedStructureCurrent.ChangedStructureCurrent_Methods;
import ChangedStructureCurrent.FetchData;

public class SaveReportDataClassNewStr {

	public static void parseAllReportFromJsonToSave(PrintWriter out, Session session, String pythonScriptApiData, String emailUrl, String textSentMailTime, String subjectNodePath
			, String from_Source, String timestampDate, String timestampDateAndTime , String reportcomesFrom, String vinayaJson){

		try {


			/*String allReport="{  \"Spot_table_1\": [    {      \"CargoGrade\": \"Naptha\",       \"CargoQty\": 30.0,       \"CargoType\": \"CPP/Clean\",       \"Charterer\": \"BBNAFT\",       \"Comment\": \"Subs\",       \"DiscPort\": \"UKC\",       \"Fixture Type\": \"Spot\",       \"Information\": \"Market\",       \"LCStart/LCEnd\": \"06-08-2018\",       \"LoadPort\": \"Riga\",       \"Rate\": 125.0,       \"Report Date\": \"03-08-2018\",       \"ReportType\": \"Spot\",       \"Source\": \"Braemar\",       \"Status\": \"Subs\",       \"VesselName\": \"Hans Scholl\",       \"VesselType\": \"This will be picked from Vessel Databse\"    },\n" +
					"{\n" +
					"\"broker_name\":\"\",\n" +
					"\"report_name\":\"\"\n" +
					"}\n" +
					"]\n" +
					"}";*/

			//		String allReport="{\"Tonnage\":[{\"unknown_0\":\"`\",\"ETABasis\":\"2/14/2019\",\"ReportType\":\"Tonnage\"},{\"unknown_0\":\"GRAY = UNCTN POSN\",\"ETABasis\":\"2/14/2019\",\"ReportType\":\"Tonnage\"},{\"unknown_0\":\"RED = ON SUBS\",\"Status\":\"2/14/2019\",\"ETABasis\":\"2/14/2019\",\"ReportType\":\"Tonnage\"},{\"unknown_0\":\"USAC POSITIONS\",\"unknown_2\":\"DWT\",\"Status\":\"OPEN\",\"ETABasis\":\"ETA CBC\",\"VesselName\":\"PANAMAX\",\"Operators\":\"OWNERS\",\"OpenDate\":\"ETA STATIA\",\"Port\":\"OPEN POSITION\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"OPEN POSITION\"},{\"unknown_0\":\"IRON POINT (BOSTON 14 FEB) - ICE 1A - SUBS\",\"unknown_2\":\"51\",\"Status\":\"14/2\",\"ETABasis\":\"17/2\",\"VesselName\":\"SCF PRIME\",\"Operators\":\"ST SHIPPING\",\"OpenDate\":\"14/2\",\"Port\":\"ARUBA\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"ARUBA\"},{\"unknown_0\":\"NAVE CETUS (P.EVERGLADES 14 FEB)\",\"unknown_2\":\"74\",\"Status\":\"14/2\",\"ETABasis\":\"20/2\",\"VesselName\":\"CABO SAN VICENTE - SUBS\",\"Operators\":\"PANAMAX INTL\",\"OpenDate\":\"14/2\",\"Port\":\"CARIBS\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"CARIBS\"},{\"unknown_0\":\"MINERVA JOANNA (NYC 17 FEB) - SUBS\",\"unknown_2\":\"46\",\"Status\":\"14/2\",\"ETABasis\":\"20/2\",\"VesselName\":\"GULF STREAM\",\"Operators\":\"MJOLNER SHIPPING\",\"OpenDate\":\"15/2\",\"Port\":\"ST EUSTATIUS\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"ST EUSTATIUS\"},{\"unknown_0\":\"LUMEN N (MONTREAL 18 FEB) - ICE 1A\",\"unknown_2\":\"63\",\"Status\":\"14/2\",\"ETABasis\":\"21/2\",\"VesselName\":\"SEAWAYS ROSEMAR\",\"Operators\":\"PANAMAX INTL\",\"OpenDate\":\"17/2\",\"Port\":\"BALBOA\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"BALBOA\"},{\"unknown_0\":\"M.GRACE (HOLYROOD 21 FEB) - ICE 1A - SUBS\",\"unknown_2\":\"50\",\"Status\":\"14/2\",\"ETABasis\":\"22/2\",\"VesselName\":\"NAVE CETUS\",\"Operators\":\"NAVIG8\",\"OpenDate\":\"18/2\",\"Port\":\"PORT EVERGLADES\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"PORT EVERGLADES\"},{\"unknown_0\":\"ERIKOUSSA (BAH 18 FEB)\",\"unknown_2\":\"70\",\"Status\":\"17/2\",\"ETABasis\":\"24/2\",\"VesselName\":\"SEAWAYS VISAYAS\",\"Operators\":\"PANAMAX INTL\",\"OpenDate\":\"19/2\",\"Port\":\"CRISTOBAL\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"CRISTOBAL\"},{\"unknown_0\":\"CPO FINLAND (ST CROIX 18 FEB) - ICE 1A\",\"unknown_2\":\"37\",\"Status\":\"14/2\",\"ETABasis\":\"24/2\",\"VesselName\":\"CABO FROWARD\",\"Operators\":\"PANAMAX INTL\",\"OpenDate\":\"20/2\",\"Port\":\"HOUSTON\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"HOUSTON\"},{\"unknown_0\":\"ICE POINT (PHILLY 20 FEB) - ICE 1A - SUBS\",\"unknown_2\":\"51\",\"Status\":\"14/2\",\"ETABasis\":\"24/2\",\"VesselName\":\"ENERGY CHAMPION\",\"Operators\":\"GOLDEN ENERGY\",\"OpenDate\":\"20/2\",\"Port\":\"CORPUS CHRISTI\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"CORPUS CHRISTI\"},{\"unknown_0\":\"COMPASSION (NYC 23 FEB)\",\"unknown_2\":\"72\",\"Status\":\"14/2\",\"ETABasis\":\"26/2\",\"VesselName\":\"ENERGY CHALLENGER\",\"Operators\":\"GOLDEN ENERGY\",\"OpenDate\":\"20/2\",\"Port\":\"HOUSTON\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"HOUSTON\"},{\"unknown_0\":\"MARVEL (BAH 21 FEB)\",\"unknown_2\":\"38\",\"Status\":\"16/2\",\"ETABasis\":\"27/2\",\"VesselName\":\"SKOPELOS\",\"Operators\":\"ELETSON\",\"OpenDate\":\"20/2\",\"Port\":\"SANTO TOMAS\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"SANTO TOMAS\"},{\"unknown_0\":\"SURFER ROSA (BAH 21 FEB) - ICE 1C\",\"unknown_2\":\"46\",\"Status\":\"20/2\",\"ETABasis\":\"27/2\",\"VesselName\":\"DS PROMOTER - UNC\",\"Operators\":\"HEIDMAR\",\"OpenDate\":\"20/2\",\"Port\":\"ARUBA\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"ARUBA\"},{\"unknown_0\":\"STENA PRESIDENT\",\"unknown_2\":\"65\",\"Status\":\"15/2\",\"ETABasis\":\"21/2\",\"VesselName\":\"HOUSTON\",\"Operators\":\"STENA\",\"ReportType\":\"Tonnage\"},{\"unknown_0\":\"USG POSITIONS\",\"unknown_2\":\"DWT\",\"Status\":\"18/2\",\"ETABasis\":\"ETA HOU\",\"VesselName\":\"ERIKOUSSA\",\"Operators\":\"ELETSON\",\"OpenDate\":\"22/2\",\"Port\":\"BAHAMAS\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"BAHAMAS\"},{\"unknown_0\":\"CABO FROWARD (HOU 14 FEB)\",\"unknown_2\":\"74\",\"Status\":\"17/2\",\"ETABasis\":\"14/2\",\"VesselName\":\"XANTHOS\",\"Operators\":\"PLEIADES\",\"OpenDate\":\"23/2\",\"Port\":\"BEAUMONT\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"BEAUMONT\"},{\"unknown_0\":\"ENERGY CHALLENGER (HOU 14 FEB)\",\"unknown_2\":\"70\",\"Status\":\"18/2\",\"ETABasis\":\"14/2\",\"VesselName\":\"ATHENS STAR\",\"Operators\":\"PENFIELD MARINE\",\"OpenDate\":\"24/2\",\"Port\":\"GALVESTON\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"GALVESTON\"},{\"unknown_0\":\"STENA PRESIDENT (HOU 15 FEB)\",\"unknown_2\":\"65\",\"Status\":\"20/2\",\"ETABasis\":\"15/2\",\"VesselName\":\"CAPE TAURA\",\"Operators\":\"UPT\",\"OpenDate\":\"26/2\",\"Port\":\"HOUSTON\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"HOUSTON\"},{\"unknown_0\":\"ENERGY CHAMPION (CORPUS 14 FEB)\",\"unknown_2\":\"70\",\"Status\":\"18/2\",\"ETABasis\":\"15/2\",\"VesselName\":\"LUMEN N\",\"Operators\":\"PENFIELD MARINE\",\"OpenDate\":\"27/2\",\"Port\":\"MONTREAL\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"MONTREAL\"},{\"unknown_0\":\"KASTOS (EC MEXICO 14 FEB)\",\"unknown_2\":\"51\",\"Status\":\"16/2\",\"ETABasis\":\"16/2\",\"VesselName\":\"LR1 CARRIER\",\"Operators\":\"PENFIELD MARINE\",\"OpenDate\":\"01/3\",\"Port\":\"S.FRANCISCO DO SUL\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"S.FRANCISCO DO SUL\"},{\"unknown_0\":\"NAVE CETUS (P.EVERGLADES 14 FEB)\",\"unknown_2\":\"74\",\"Status\":\"23/2\",\"ETABasis\":\"17/2\",\"VesselName\":\"COMPASSION\",\"Operators\":\"BW TANKERS\",\"OpenDate\":\"01/3\",\"Port\":\"NEW YORK\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"NEW YORK\"},{\"unknown_0\":\"ATHENS STAR (GALVESTON 18 FEB)\",\"unknown_2\":\"73\",\"Status\":\"05\",\"ETABasis\":\"18/2\",\"ReportType\":\"Tonnage\"},{\"unknown_0\":\"XANTHOS (BEAUMONT 17 FEB)\",\"unknown_2\":\"61\",\"Status\":\"OPEN\",\"ETABasis\":\"18/2\",\"VesselName\":\"HANDY/MR\",\"Operators\":\"OWNERS\",\"OpenDate\":\"ETA STATIA\",\"Port\":\"OPEN POSITION\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"OPEN POSITION\"},{\"unknown_0\":\"CAPE TAURA (HOU 20 FEB)\",\"unknown_2\":\"73\",\"Status\":\"14/2\",\"ETABasis\":\"20/2\",\"VesselName\":\"ASTERION\",\"Operators\":\"SCORPIO\",\"OpenDate\":\"16/2\",\"Port\":\"CRISTOBAL\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"CRISTOBAL\"},{\"unknown_0\":\"SKOPELOS (SANTO TOMAS 16 FEB)\",\"unknown_2\":\"70\",\"Status\":\"18/2\",\"ETABasis\":\"20/2\",\"VesselName\":\"KINAROS\",\"Operators\":\"ELETSON\",\"OpenDate\":\"18/2\",\"Port\":\"DOMINICA\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"DOMINICA\"},{\"unknown_0\":\"ERIKOUSSA (BAH 18 FEB)\",\"unknown_2\":\"70\",\"Status\":\"18/2\",\"ETABasis\":\"21/2\",\"VesselName\":\"CPO FINLAND\",\"Operators\":\"SCORPIO\",\"OpenDate\":\"19/2\",\"Port\":\"ST CROIX\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"ST CROIX\"},{\"unknown_0\":\"MARVEL (BAH 21 FEB)\",\"unknown_2\":\"38\",\"Status\":\"20/2\",\"ETABasis\":\"24/2\",\"VesselName\":\"PORT UNION\",\"Operators\":\"UNION\",\"OpenDate\":\"20/2\",\"Port\":\"CURACAO\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"CURACAO\"},{\"unknown_0\":\"SURFER ROSA (BAH 21 FEB)\",\"unknown_2\":\"46\",\"Status\":\"14/2\",\"ETABasis\":\"24/2\",\"VesselName\":\"KASTOS\",\"Operators\":\"ELETSON\",\"OpenDate\":\"20/2\",\"Port\":\"EC MEXICO\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"EC MEXICO\"},{\"unknown_0\":\"YANKUL SILVER (HOU 01 MAR)\",\"unknown_2\":\"45\",\"Status\":\"14/2\",\"ETABasis\":\"01/3\",\"VesselName\":\"IRON POINT - SUBS IN CPP\",\"Operators\":\"PB TANKERS\",\"OpenDate\":\"20/2\",\"Port\":\"BOSTON\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"BOSTON\"},{\"unknown_0\":\"MAGIC WAND\",\"unknown_2\":\"46\",\"Status\":\"20/2\",\"ETABasis\":\"22/2\",\"VesselName\":\"JAMAICA\",\"Operators\":\"TRAFIGURA\",\"ReportType\":\"Tonnage\"},{\"unknown_0\":\"USWC POSITIONS\",\"unknown_2\":\"DWT\",\"Status\":\"17/2\",\"ETABasis\":\"ETA S.CRUZ\",\"VesselName\":\"MINERVA JOANNA - SUBS\",\"Operators\":\"MINERVA\",\"OpenDate\":\"23/2\",\"Port\":\"NEW YORK\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"NEW YORK\"},{\"unknown_0\":\"SEAWAYS ROSEMAR (BALBOA 14 FEB)\",\"unknown_2\":\"69\",\"Status\":\"20/2\",\"ETABasis\":\"18/2\",\"VesselName\":\"PRINCE I\",\"Operators\":\"ST SHIPPING\",\"OpenDate\":\"24/2\",\"Port\":\"ESMERALDAS\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"ESMERALDAS\"},{\"unknown_0\":\"MAYA (LA PAMPILLA 14 FEB)\",\"unknown_2\":\"68\",\"Status\":\"21/2\",\"ETABasis\":\"20/2\",\"VesselName\":\"MARVEL\",\"Operators\":\"ROXANA\",\"OpenDate\":\"25/2\",\"Port\":\"FREEPORT (BAH)\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"FREEPORT (BAH)\"},{\"unknown_0\":\"ANTIPOLIS (L.BEACH 16 FEB)\",\"unknown_2\":\"74\",\"Status\":\"21/2\",\"ETABasis\":\"22/2\",\"VesselName\":\"SURFER ROSA\",\"Operators\":\"MINERVA\",\"OpenDate\":\"25/2\",\"Port\":\"FREEPORT (BAH)\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"FREEPORT (BAH)\"},{\"unknown_0\":\"AUGUSTA (LA 18 FEB)\",\"unknown_2\":\"72\",\"Status\":\"20/2\",\"ETABasis\":\"24/2\",\"VesselName\":\"ICE POINT - SUBS\",\"Operators\":\"PB TANKERS\",\"OpenDate\":\"26/2\",\"Port\":\"PHILADELPHIA\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"PHILADELPHIA\"},{\"unknown_0\":\"PRINCE I (ESMERALDAS 20 FEB)\",\"unknown_2\":\"40\",\"Status\":\"21/2\",\"ETABasis\":\"24/2\",\"VesselName\":\"MINERVA GRACE - SUBS\",\"Operators\":\"MINERVA\",\"OpenDate\":\"01/3\",\"Port\":\"HOLYROOD\",\"ReportType\":\"Tonnage\",\"OpenPort\":\"HOLYROOD\"},{\"unknown_0\":\"EVROTAS (P.SANDINO 24 FEB)\",\"unknown_2\":\"61\",\"Status\":\"06\",\"ETABasis\":\"26/2\",\"ReportType\":\"Tonnage\"},{\"unknown_0\":\"GULF PEARL (L.BEACH 23 FEB)\",\"unknown_2\":\"74\",\"Status\":\"05\",\"ETABasis\":\"01/3\",\"ReportType\":\"Tonnage\"},{\"unknown_0\":\"ISOLA CELESTE (LA 23 FEB)\",\"unknown_2\":\"50\",\"Status\":\"08\",\"ETABasis\":\"01/3\",\"ReportType\":\"Tonnage\"},{\"unknown_0\":\"CLARKSONS PLATOU SHIPBROKING\",\"ETABasis\":\"2/14/2019\",\"ReportType\":\"Tonnage\"},{\"unknown_0\":\"713.235.7400\",\"ETABasis\":\"2/14/2019\",\"ReportType\":\"Tonnage\"}]}";

			//		String allReport="{  \"PRODUCTS FIXTURE WORKSHEET_table_1\": [    {      \"Comment\": \"subs\",       \"Status\": \"subs\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#shell\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 158.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"23-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"22-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#fujairah\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"AMPTC\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"14-12-2016\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 274.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 169.0    },     {      \"Comment\": \"SUBS, foc\",       \"Status\": \"subs\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#sea_pecos\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 110.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"23-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"22-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#fujairah\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"FRONTLINE\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"11-03-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 249.90000000000001,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 127.0    },     {      \"Comment\": \"hold - nap/cond/jet+ums \",       \"Status\": \"hold\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#iasonas\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 115.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"23-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"23-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#fujairah\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"KOCH\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"06-01-2009\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 250.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 129.0    },     {      \"Comment\": \"SUBS, blst ex map ta phut\",       \"Status\": \"subs\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#sti_supreme\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 110.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"23-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"23-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#fujairah\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"SCORPIO\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"08-08-2016\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 249.90000000000001,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 128.0    },     {      \"Comment\": \"ppt- ulsd/ nap/cond+nap+transmix\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#searunner\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 114.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"23-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"23-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#fujairah\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"KOCH\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"19-10-2017\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 249.90000000000001,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 126.0    },     {      \"Comment\": \"passsing, blst ex taiwan, eta fuj 27/10\",       \"Status\": \"subs\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#sti_symphony\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 110.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"25-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"21-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#galle\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"SCORPIO\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"17-02-2016\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 249.90000000000001,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 125.0    },     {      \"Comment\": \"subs o/p for dirty - passing in blst ex spore - l/c nap\",       \"Status\": \"subs/fxd o/p - going dirty\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#sfl_trinity\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 116.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"27-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"21-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#galle\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"P66\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"15-08-2017\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 249.90000000000001,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 127.0    },     {      \"Comment\": \"blst ex zhoushan- nap/jet+go/jet\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#sabetta\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 110.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"28-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"28-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#fujairah\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"NOVATEK\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"10-09-2017\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 250.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 119.0    },     {      \"Comment\": \"subs, l/c - cond\",       \"Status\": \"subs\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#king_philippos\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 112.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"29-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"29-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#fujairah\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"NAVIG8\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"14-05-2012\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 249.90000000000001,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 126.0    },     {      \"Comment\": \"SUBS, blst ex spore, eta fuj 30/10, go/nap/jet A-1\",       \"Status\": \"subs\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#lyric_camellia\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 110.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"30-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"24-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#galle\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"LYRAS\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"28-02-2016\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 249.90000000000001,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 128.0    },     {      \"Comment\": \"Null\",       \"Status\": \"2 open ships \",       \"VesselName\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": \"Null\"    },     {      \"Comment\": \"Null\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#lyric_magnolia\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 110.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"01-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"22-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Mersin\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"ST SHIPPING\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"14-07-2016\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 249.90000000000001,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 128.0    },     {      \"Comment\": \"in blst,\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#bahra\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 111.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"03-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"22-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Singapore\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"SCORPIO\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"09-02-2012\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 249.90000000000001,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 125.0    },     {      \"Comment\": \"SUBS\",       \"Status\": \"subs\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ocean_tiara\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 109.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"03-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"23-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Singapore\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"OTC\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"07-10-2007\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 245.09999999999999,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 128.0    },     {      \"Comment\": \"blst ex mailiao \",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#front\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 113.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"03-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"23-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Singapore\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"FRONTLINE\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"25-01-2016\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 249.90000000000001,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 127.0    },     {      \"Comment\": \"Null\",       \"Status\": \"Null\",       \"VesselName\": \"Sea Beauty\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 157.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"03-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"23-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#beira\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"AMPTC\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"03-01-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 277.19999999999999,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 168.0    },     {      \"Comment\": \"subs- eta spore 25/10\",       \"Status\": \"subs\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#tao_lin_wan\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 110.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"04-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"19-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#hong\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"COSCO\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"19-09-2012\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 244.69999999999999,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 122.0    },     {      \"Comment\": \"nap/cond/jet+ulsd\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#sks_delta\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 119.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"05-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"25-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Singapore\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"SKS\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"26-01-2010\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 250.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 131.0    },     {      \"Comment\": \"Null\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#densa_alligator\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 105.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"06-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"29-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#mombasa\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"VITOL\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"15-09-2013\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 244.19999999999999,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 117.0    },     {      \"Comment\": \"Null\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#sti_gauntlet\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 110.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"06-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"23-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#barcelona\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"SCORPIO\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"08-01-2017\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 250.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 126.0    },     {      \"Comment\": \"Null\",       \"Status\": \"uncertain, iranian blt\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#arita\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 113.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"07-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"27-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Singapore\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"HANSEACTIC TANKERS\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"31-12-2017\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 250.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 127.0    },     {      \"Comment\": \"blst ex taiwan, nap/nap/jet+go\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#trysil_spirit\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 105.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"07-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"27-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Singapore\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"TAURUS\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"03-04-2012\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 244.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 117.0    },     {      \"Comment\": \"ums \",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#pacific_a__dorodchi\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 110.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"07-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"25-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Tuban\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"SHELL\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"11-09-2016\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 249.90000000000001,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 127.0    },     {      \"Comment\": \"reformate/gasoil/crude\",       \"Status\": \"last 3rd cargo dirty\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#elka\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 94.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"08-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"28-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Singapore\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"ELKA\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"11-07-2004\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 228.59999999999999,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 110.0    },     {      \"Comment\": \"passing, blst ex sakai, eta fuj 8/11\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#densa_crocodile\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 105.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"08-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"28-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Singapore\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"SCORPIO\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"05-02-2015\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 244.19999999999999,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 117.0    },     {      \"Comment\": \"blst ex yosu, l/c nap\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#southern_spirit\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 113.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"09-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"29-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Singapore\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"NAVIG8\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"07-10-2009\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 250.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 127.0    },     {      \"Comment\": \"eta sikka 11/11, eta fuj 13/11 \",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#british_restraint\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 114.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"09-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"17-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#botany_bay\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"BP\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"22-01-2017\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 250.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 122.0    },     {      \"Comment\": \"ulsd/go/nap\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#torm_hermia\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 115.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"09-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"29-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Singapore\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"TORM\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"08-02-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 250.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 121.0    },     {      \"Comment\": \"NB\",       \"Status\": \"uncoated newbuild\",       \"VesselName\": \"Green Attitude\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 113.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"09-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"22-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Zhoushan\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"MJOLNER\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"14-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 250.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 127.0    },     {      \"Comment\": \"blst ex muroran, eta sikka 10/11,  nap/nap/ulsd\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#fair_seas\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 115.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"10-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"30-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Singapore\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"HEIDMAR\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"06-07-2008\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 250.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 129.0    },     {      \"Comment\": \"subs- blst ex yosu, nap/jet/nap+ma\",       \"Status\": \"subs\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#sks_driva\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 119.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"10-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"30-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Singapore\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"SKS\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"29-03-2010\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 250.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 135.0    },     {      \"Comment\": \"Null\",       \"Status\": \"14 open ships\",       \"VesselName\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": \"Null\"    },     {      \"Comment\": \"Null\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#sti_guide\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 110.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"11-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"26-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Taiwan\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"SCORPIO\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"22-10-2016\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 250.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 126.0    },     {      \"Comment\": \"subs- if, eta fuj 13/11\",       \"Status\": \"subs\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#champion\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 106.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"12-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"23-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#tokuyama\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"NYK\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"14-01-2008\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 241.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 119.0    },     {      \"Comment\": \"Null\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#champion_prosperity\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 115.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"12-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"24-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Yosu\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"CARGILL\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"23-07-2009\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 243.80000000000001,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 127.0    },     {      \"Comment\": \"projected- fxd ex wci, eta spore 30/10, poss north \",       \"Status\": \"projected\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ocean_quest\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 109.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"12-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"01-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Singapore\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"OTC\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"21-08-2008\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 245.09999999999999,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 130.0    },     {      \"Comment\": \"l/c nap\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#polar_ace\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 110.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"13-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"25-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Daesan\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"NAVIG8\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"18-03-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 249.90000000000001,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 130.0    },     {      \"Comment\": \"ulsd\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#seaenvoy\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 113.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"13-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"05-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Dar Es Salaam\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"ST SHIPPING\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"20-11-2017\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 250.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 124.0    },     {      \"Comment\": \"projected- fxd ex ag, eta p.gudang 31/10, poss north \",       \"Status\": \"projected\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#nordmarlin\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 114.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"13-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"02-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Pasir Gudang\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"HEIDMAR\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"13-09-2017\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 249.90000000000001,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 129.0    },     {      \"Comment\": \"SUBS, projected- fxd ex ag, eta mtp 29/11\",       \"Status\": \"subs\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#sti_orchard\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 110.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"14-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"31-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Map Ta Phut\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"SCORPIO\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"24-09-2014\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 255.90000000000001,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 136.0    },     {      \"Comment\": \"nap/ulsd/jet\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#purovsky\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 110.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"14-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"27-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Zhoushan\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"NOVATEK\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"23-01-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 249.90000000000001,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 119.0    },     {      \"Comment\": \"4-5/11, if sikka 12-13/11, if fuj, 14-15/11, ulsd/nap/ulsd\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#gulf_valour\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 115.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"15-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"04-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Singapore\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"GEM\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"28-01-2013\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 249.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 127.0    },     {      \"Comment\": \"15/10\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#fs_diligence\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 109.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"15-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"15-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#fujairah\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"FORMOSA MARINE\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"17-01-2012\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 250.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 130.0    },     {      \"Comment\": \"ulsd/nap/ulsd+jet\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#sea_jewel\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 112.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"15-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"04-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Singapore\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"AMPTC\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"10-03-2013\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 250.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 126.0    },     {      \"Comment\": \"ex brazil \",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#captain_john\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 114.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"15-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"30-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#gibraltar\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"NAVIG8\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"10-06-2014\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 252.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 128.0    },     {      \"Comment\": \"projected- fxd ex ag, eta mtp 30/10\",       \"Status\": \"projected\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#sti_oxford\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 110.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"15-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"01-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Map Ta Phut\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"SCORPIO\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"08-04-2015\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 256.39999999999998,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 135.0    },     {      \"Comment\": \"l3c nap \",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#torm_hellerup\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 115.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"15-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"30-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Kaohsiung\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"TORM\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"19-04-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 250.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 127.0    },     {      \"Comment\": \"NB\",       \"Status\": \"uncoated newbuild\",       \"VesselName\": \"Levantine Sea\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 116.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"15-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"31-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Subic Bay\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"EPS\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"30-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 245.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 125.0    },     {      \"Comment\": \"Null\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ocean_taipan\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 109.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"16-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"03-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Tuban\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"OTC\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"28-05-2008\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 245.09999999999999,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 128.0    },     {      \"Comment\": \"Null\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#minerva_indiana\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 106.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"17-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"29-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Daesan\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"MINERVA\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"14-03-2007\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 239.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 120.0    },     {      \"Comment\": \"hold\",       \"Status\": \"hold\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#zaliv_amurskiy\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 105.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"17-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"25-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#gore_bay\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"SOVCOM\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"29-07-2008\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 243.90000000000001,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 124.0    },     {      \"Comment\": \"nap \",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#alpine\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 108.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"17-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"29-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ulsan\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"ST SHIPPING\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"04-03-2010\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 243.80000000000001,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 125.0    },     {      \"Comment\": \"ulsd, con, con\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#pacific_sky\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 115.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"17-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"01-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Taiwan\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"HEIDMAR\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"05-03-2009\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 250.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 129.0    },     {      \"Comment\": \"nap/nap/jet\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#yang_li_hu\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 110.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"17-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"28-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#tokuyama\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"HEIDMAR\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"19-05-2010\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 244.59999999999999,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 122.0    },     {      \"Comment\": \"Null\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#seriana\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 110.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"17-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"01-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Kaohsiung\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"TRAFIGURA\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"08-09-2015\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 238.30000000000001,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 126.0    },     {      \"Comment\": \"subs, uncoated nb, willing west \",       \"Status\": \"subs\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#brasilia\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 113.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"17-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"29-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#geoje\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"SHELL\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"30-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 250.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 123.0    },     {      \"Comment\": \"blst ex geelong\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#front\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 110.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"18-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"26-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cape_leeuwin\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"FRONTLINE\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"04-01-2017\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 251.80000000000001,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 129.0    },     {      \"Comment\": \"ulsd/ums/ums \",       \"Status\": \"uncertain\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#maersk\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 109.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"18-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"18-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Kaohsiung\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"MAERSK\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"09-11-2005\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 244.59999999999999,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 122.0    },     {      \"Comment\": \"uncertain, nap/jet/nap \",       \"Status\": \"uncertain\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#sks_dokka\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 119.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"18-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"30-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Yosu\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"SKS\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"23-11-2010\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 250.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 135.0    },     {      \"Comment\": \"go+jet/go+jet/go\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#gulf_vision\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 115.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"18-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"25-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Lome\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"GEM\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"24-10-2012\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 249.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 127.0    },     {      \"Comment\": \"d/d/nap/nap/jet\",       \"Status\": \"ex d/d\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#torm\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 110.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"19-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"03-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#guangzhou\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"TORM\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"17-08-2008\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 244.59999999999999,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 122.0    },     {      \"Comment\": \"ex d/d, 8-10/11, nap/ulsd/ulsd+jet\",       \"Status\": \"ex d/d\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#alburaq\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 113.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"19-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"08-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Singapore\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"AMPTC\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"05-10-2008\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 250.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 127.0    },     {      \"Comment\": \"projected- fxd ex ag, eta chiba 27/10\",       \"Status\": \"projected\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#lr2_polaris\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 115.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"20-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"29-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#chiba\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"MAERSK\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"27-11-2008\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 243.80000000000001,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 124.0    },     {      \"Comment\": \"Null\",       \"Status\": \"18 open ships \",       \"VesselName\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": \"Null\"    },     {      \"Comment\": \"projected- fxd ex med, eta spore 8/11, poss north \",       \"Status\": \"projected\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#maersk_promise\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 110.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"21-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"10-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Singapore\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"MAERSK\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"10-07-2006\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 244.59999999999999,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 122.0    },     {      \"Comment\": \"go 50ppm/nap/go\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#neptune\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 105.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"21-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"05-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Kaohsiung\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"KOCH\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"03-01-2013\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 244.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 117.0    },     {      \"Comment\": \"afra nb - first half of nov\",       \"Status\": \"uncoated newbuild\",       \"VesselName\": \"Pusaka Borneo\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 107.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"21-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"01-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#tsuneishi\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"SYNERGY MARITIME\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"30-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 244.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 121.0    },     {      \"Comment\": \"Keen CPP Any dir spot / tc trip/20T/Panama fitted\",       \"Status\": \"uncoated s'max nb\",       \"VesselName\": \"Nordic Tellus\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 157.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"21-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"02-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Yosu\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"TRAFIGURA\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"30-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 274.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 166.0    },     {      \"Comment\": \"N/B ex yard\",       \"Status\": \"uncoated newbuild\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#puma\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 115.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"22-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"01-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Japan\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"AGELEF\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"29-09-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 250.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 125.0    },     {      \"Comment\": \"ulsd+jet+ums/nap/nap \",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#sks_dee\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 119.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"23-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"31-10-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#botany_bay\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"SKS\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"27-07-2010\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 250.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 135.0    },     {      \"Comment\": \"cond\",       \"Status\": \"Null\",       \"VesselName\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#alpine\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 105.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"23-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"03-11-2018\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#incheon\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"ST SHIPPING\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"24-07-2011\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 244.0,       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 117.0    },     {      \"Comment\": \"Null\",       \"Status\": \"3 open ships \",       \"VesselName\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TonnageReportComment\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": \"Null\",       \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": \"Null\"    },     {      \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#isBroker\": \"\",       \"report_name\": \"UPDATED FUJAIRAH LR2 POSITION LIST\"    }  ]}";
			//String data1="{ \"PRODUCTS FIXTURE WORKSHEET_table_1\": [ { \"COMMENTS _\": \"Null\", \"Comment\": \"subs\", \"Status\": \"subs\", \"VesselName\": \"Sea Shell\", \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT\": 158.0, \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS\": \"23-10-2018\", \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate\": \"22-10-2018\", \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#fujairah\", \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\": \"AMPTC\", \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\": \"Tonnage\", \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt\": \"14-12-2016\", \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA\": 274.0, \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub\": 169.0 }]}";


			/*String allReport="{\n" +
					"\"allReport\":[\n" +
					"\n" +
					"{\n" +
					"\n" +
					"\"ReportType\":\"TimeCharterReports\",\n" +
					"\"RepositionRegion\":\"\",\n" +
					"\"CargoType\":\"\",\n" +
					"\"VesselName\":\"StenaWeco Majorie\",\n" +
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
					"\"Comment\":\"stx imo 3 eco 1st gen\",\n" +
					"\"Source\":\"HOWE ROBINSON\",\n" +
					"\"ReportTimestamp\":\"Wed Nov 21 15:15:02 UTC 2018\",\n" +
					"\"Information\":\"Market\",\n" +
					"\"FixtureType\":\"\",\n" +
					"\"Charterer\":\"Stena\",\n" +
					"\"Status\":\"\",\n" +
					"\"CargoGrade\":\"\",\n" +
					"\"CargoQty\":\"\",\n" +
					"\"LCStart\":\"\",\n" +
					"\"LCEnd\":\"\",\n" +
					"\"LoadPort\":\"\",\n" +
					"\"DiscPort\":\"\",\n" +
					"\"RateType\":\"\",\n" +
					"\"Rate\":\"14,750\",\n" +
					"\"ReportDate\":\"\",\n" +
					"\"Spot_TC\":\"TC\",\n" +
					"\"Date\":\"15 jan\",\n" +
					"\"CPP_DPP\":\"clean/cpp\",\n" +
					"\"Delivery\":\"tc extn\",\n" +
					"\"DeliveryPlace\":\"med\",\n" +
					"\"Period\":\"12\",\n" +
					"\"Periodunit\":\"months\",\n" +
					"\"Redelivery\":\"\",\n" +
					"\"TCRate\":\"$14,750\",\n" +
					"\"CurrencyUnit\":\"USD\",\n" +
					"\"Month_Year\":\"\",\n" +
					"\"1yr\":\"\",\n" +
					"\"2yr\":\"\",\n" +
					"\"3yr\":\"\",\n" +
					"\"5yr\":\"\"\n" +
					"\n" +
					"}\n" +
					"\n" +
					"]\n" +
					"}";*/

			String allReport=pythonScriptApiData;

			JSONObject objectjson = new JSONObject(allReport);

			String ReportTypeString="";

			Iterator<String> keys = objectjson.keys();
			while (keys.hasNext()) {
				String key = keys.next();
				Object keyValue = objectjson.get(key);
				if (keyValue instanceof JSONObject) {
					// It's an array
					//interventionJsonArray = (JSONArray)keyValue;
					JSONArray allReportJsonArray=null;
					ChangedStructureCurrent_Methods CSC=new ChangedStructureCurrent_Methods();
					//if(allReportJsonObject.has("allReport")){
					//allReportJsonArray=allReportJsonObject.getJSONArray("allReport");
//					allReportJsonArray = (JSONObject)keyValue;
					JSONObject tabledataJsonObj=null;
					tabledataJsonObj = (JSONObject)keyValue;
					//out.println("allReportJsonArray  :: "+allReportJsonArray);
					if(tabledataJsonObj.has("table_data")){
						allReportJsonArray=tabledataJsonObj.getJSONArray("table_data");
					if(allReportJsonArray.length()>0){
					for(int i=0;i<allReportJsonArray.length();i++){
						JSONObject allReportJsonArrayInsideJSonobject=allReportJsonArray.getJSONObject(i);
						String check_ReportType_id="";

						String returnReportType = "";
						if(allReportJsonArrayInsideJSonobject.has("ReportType")){
							ReportTypeString=allReportJsonArrayInsideJSonobject.getString("ReportType");
							returnReportType = urlValue(ReportTypeString);

							check_ReportType_id=CSC.check_ReportType(session, returnReportType, out);
						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType")){
							ReportTypeString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType");
							returnReportType = urlValue(ReportTypeString);

							check_ReportType_id=CSC.check_ReportType(session, returnReportType, out);
						}


						String dataAll=HeaderSaveMethod.headersDataExcel(out, tabledataJsonObj);
						out.println("dataAll:: "+dataAll);
						String RG="";
						String CT="";
						String VT="";
						String EB="";
						String StatusHeader="";
						String RT="";
						
						if(!GmailMethods.isNullString(dataAll)){

							boolean checkjsonStringDataAll=isJSONValid(dataAll);
							if(checkjsonStringDataAll==true){
								JSONObject dataall=new JSONObject(dataAll);
								if(dataall.has("RG")){
									RG= dataall.getString("RG");
									out.println("RG:: "+RG);
								}if(dataall.has("CT")){
									CT= dataall.getString("CT");
									out.println("CT:: "+CT);
								}if(dataall.has("VT")){
									VT= dataall.getString("VT");
									out.println("VT:: "+VT);
								}if(dataall.has("EB")){
									EB= dataall.getString("EB");
									out.println("EB:: "+EB);
								}if(dataall.has("StatusHeader")){
									StatusHeader= dataall.getString("StatusHeader");
									out.println("StatusHeader:: "+StatusHeader);
								}if(dataall.has("RT")){
									RT= dataall.getString("RT");
									out.println("RT:: "+RT);
								}


							}
						}

						//.............................table objHeader...........
						
						JSONObject objHeaderJsonWise=OnlyCheckFourJsonToReportType.fetchOjectHeaderFromTable(allReportJsonArrayInsideJSonobject);
						String objHeaderJsonWiseRG="";
						String objHeaderJsonWiseRT="";
						String objHeaderJsonWiseCT="";
						
						if(objHeaderJsonWise!=null && objHeaderJsonWise.length()!=0){
						   if(objHeaderJsonWise.has("RepositionRegion")){
							objHeaderJsonWiseRG=objHeaderJsonWise.getString("RepositionRegion");
						   }if(objHeaderJsonWise.has("ReportType")){
							objHeaderJsonWiseRT=objHeaderJsonWise.getString("ReportType");
						    }if(objHeaderJsonWise.has("CargoType")){
							objHeaderJsonWiseCT=objHeaderJsonWise.getString("CargoType");
						}
						}
						
						//........ end tableobjheader here.......................
						
						String check_Port_repositionRegion_id="";
						
						if( !GmailMethods.isNullString(objHeaderJsonWiseRG) ){
							 check_Port_repositionRegion_id=objHeaderJsonWiseRG;
						}
						else if( !GmailMethods.isNullString(RG) ){  // forst prioritty goes to Subject
							    check_Port_repositionRegion_id=RG;
						}else{


							out.println("before RepositionRegion: ");
							if(allReportJsonArrayInsideJSonobject.has("RepositionRegion")){
								out.println("if rg");
								String RepositionRegionString=allReportJsonArrayInsideJSonobject.getString("RepositionRegion");
								String returnRepositionRegion = urlValue(RepositionRegionString);

								if(!GmailMethods.isNullString(returnRepositionRegion)){
									String check_Port_repositionRegion_id12="";
									if(returnRepositionRegion.contains("_")){
										returnRepositionRegion=  returnRepositionRegion.replace("_", " ");
										//out.println("returnDiscPortString: "+returnCargoType);
									}
									String check_Port_repositionRegion_id1=CSC.check_Port(session, returnRepositionRegion, out);
									boolean checkjsonString=isJSONValid(check_Port_repositionRegion_id1);
									if(checkjsonString==true){
										JSONObject data=new JSONObject(check_Port_repositionRegion_id1);
										if(data.has("port_id")){
											check_Port_repositionRegion_id12=data.getString("port_id");
											check_Port_repositionRegion_id=check_Port_repositionRegion_id12;
										}

									}

									//
									if(GmailMethods.isNullString(check_Port_repositionRegion_id)){
										check_Port_repositionRegion_id=RepositionRegionString;
									}

								}/*else{
        							check_Port_repositionRegion_id="";
        						}*/
							}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion")){
								out.println("else if rg");
								String RepositionRegionString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion");
								String returnRepositionRegion = urlValue(RepositionRegionString);

								if(!GmailMethods.isNullString(returnRepositionRegion)){
									if(returnRepositionRegion.contains("_")){
										returnRepositionRegion=  returnRepositionRegion.replace("_", " ");
										//out.println("returnDiscPortString: "+returnCargoType);
									}
									String check_Port_repositionRegion_id1=CSC.check_Port(session, returnRepositionRegion, out);
									boolean checkjsonString=isJSONValid(check_Port_repositionRegion_id1);
									if(checkjsonString==true){
										JSONObject data=new JSONObject(check_Port_repositionRegion_id1);
										if(data.has("port_id")){
											check_Port_repositionRegion_id=data.getString("port_id");
										}


									}

									//
									if(GmailMethods.isNullString(check_Port_repositionRegion_id)){
										check_Port_repositionRegion_id=RepositionRegionString;
									}

								}
							}/*else if(allReportJsonArrayInsideJSonobject.has("RepositionRegion1")){
								check_Port_repositionRegion_id=allReportJsonArrayInsideJSonobject.getString("RepositionRegion1");
							}*/

						} // else rg

						out.println("before CargoType: ");
						String check_CargoType_cargotype_id="";
						String CargoTypeString="";
						
						if( !GmailMethods.isNullString(objHeaderJsonWiseCT) ){
							 check_CargoType_cargotype_id=objHeaderJsonWiseCT;
						}

						else{

							if(allReportJsonArrayInsideJSonobject.has("CargoType")){
								CargoTypeString=allReportJsonArrayInsideJSonobject.getString("CargoType");
								String returnCargoType = urlValue(CargoTypeString);
								if(returnCargoType.contains("_")){
									returnCargoType=  returnCargoType.replace("_", " ");
									//out.println("returnDiscPortString: "+returnCargoType);
								}
								check_CargoType_cargotype_id=CSC.check_CargoType(session, returnCargoType,out);
								out.println("CargoTypeString: "+returnCargoType);
								out.println("check_CargoType_cargotype_id: "+check_CargoType_cargotype_id);
								if(GmailMethods.isNullString(check_CargoType_cargotype_id)){
									check_CargoType_cargotype_id=CargoTypeString;
								}
							}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type")){
								CargoTypeString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type");
								String returnCargoType = urlValue(CargoTypeString);
								if(returnCargoType.contains("_")){
									returnCargoType=  returnCargoType.replace("_", " ");
									//out.println("returnDiscPortString: "+returnCargoType);
								}
								check_CargoType_cargotype_id=CSC.check_CargoType(session, returnCargoType,out);
								out.println("CargoTypeString_url: "+returnCargoType);
								out.println("check_CargoType_cargotype_id_url: "+check_CargoType_cargotype_id);
								if(GmailMethods.isNullString(check_CargoType_cargotype_id)){
									check_CargoType_cargotype_id=CargoTypeString;
								}
							}else if(allReportJsonArrayInsideJSonobject.has("Cargo_Type")){
								CargoTypeString=allReportJsonArrayInsideJSonobject.getString("Cargo_Type");
								String returnCargoType = urlValue(CargoTypeString);
								if(returnCargoType.contains("_")){
									returnCargoType=  returnCargoType.replace("_", " ");
									//out.println("returnDiscPortString: "+returnCargoType);
								}
								check_CargoType_cargotype_id=CSC.check_CargoType(session, returnCargoType,out);
								out.println("CargoTypeString: "+returnCargoType);
								out.println("check_CargoType_cargotype_id: "+check_CargoType_cargotype_id);
								if(GmailMethods.isNullString(check_CargoType_cargotype_id)){
									check_CargoType_cargotype_id=CargoTypeString;
								}
							}

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
						String flag="";

						String check_Operators_id="";

						if(session.getRootNode().hasNode("scorpioDataBase")){
							scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
						}
						if(scorpioDataBase.hasNode("vessel")){
							vessel=scorpioDataBase.getNode("vessel");
						}

						String VesselNameString= "";
						String built1="";
						String cubics1="";
						String dwt1="";
						String ice1="";
						String loa1="";
						String owners_Name="";
						String opertaor_Name="";
						String VesselType_Name="";
						
						if(allReportJsonArrayInsideJSonobject.has("VesselName") || allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselName")){

							out.println("without key data");
							Node vesselSubNodeName=null;

							if(allReportJsonArrayInsideJSonobject.has("VesselName")){
								out.println("without key data inside");
								VesselNameString =	allReportJsonArrayInsideJSonobject.getString("VesselName");

							}
							if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselName")){
								out.println("wit key data inside");
								VesselNameString =	allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselName");
							}
							//        					     String VesselNameString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselName");
							//        						if(!GmailMethods.isNullString(VesselNameString)){
							//        							allReportJsonArrayInsideJSonobject.getString("VesselName");
							//        						}
							String returnVesselName = urlValue(VesselNameString);

							if(!GmailMethods.isNullString(returnVesselName)){
								out.println("returnVesselName: "+returnVesselName);

								if(returnVesselName.contains("_")){
									returnVesselName=  returnVesselName.replace("_", " ");
									out.println("returnVesselName_after: "+returnVesselName);
								}

								check_vesselNameProperties=CSC.check_vesselName(session, returnVesselName, out);
								//out.println("nullwala: "+check_vesselNameProperties);
								if(!GmailMethods.isNullString(check_vesselNameProperties)){

									//        						out.println("check_vesselNameProperties_test: "+check_vesselNameProperties);

									boolean checkjsonString=isJSONValid(check_vesselNameProperties);
									JSONObject check_vesselNamePropertiesJsonobject=null;
									if(checkjsonString==true){
										check_vesselNamePropertiesJsonobject=new JSONObject(check_vesselNameProperties);

										if(check_vesselNamePropertiesJsonobject.has("Flag")){
											flag=check_vesselNamePropertiesJsonobject.getString("Flag");
										}
										//        						if(check_vesselNamePropertiesJsonobject.length()>0){
										//        						out.println("check_vesselNameProperties_test1: "+check_vesselNamePropertiesJsonobject);
										if(check_vesselNamePropertiesJsonobject.has("vessel_Id")){
											vessel_Id=check_vesselNamePropertiesJsonobject.getString("vessel_Id");
											//        							out.println("check_vesselNameProperties_test2: "+vessel_Id);
										}


									} // json string check is valid wala
									else{
										//        							out.println("else");
										if(vessel.hasNode(check_vesselNameProperties)){
											vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
											vessel_Id=vesselSubNodeName.getProperty("vessel_Id").getString();
										}
									}



									if(checkjsonString==true){
										if(check_vesselNamePropertiesJsonobject.has("built")){
											built=check_vesselNamePropertiesJsonobject.getString("built");
										}
									} //valid json
									else{
										if(allReportJsonArrayInsideJSonobject.has("Built")){
											 built1=allReportJsonArrayInsideJSonobject.getString("Built");
											String returnBuilt = urlValue(built1);
											built=built1;
											//        								out.println("data s");
											if(!GmailMethods.isNullString(returnBuilt)){
												if(vessel.hasNode(check_vesselNameProperties)){
													vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
													vesselSubNodeName.setProperty("built", returnBuilt);
													//        									session.save();
												}
											}
											if( GmailMethods.isNullString(built) ){
												built=built1;
											}

										}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt")){
											built1=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt");
											String returnBuilt = urlValue(built);
											built=built1;
											//        								out.println("data s");
											if(!GmailMethods.isNullString(returnBuilt)){
												if(vessel.hasNode(check_vesselNameProperties)){
													vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
													vesselSubNodeName.setProperty("built", returnBuilt);
													//        									session.save();
												}
											}
										} // url built

									}

									if(checkjsonString==true){
										if(check_vesselNamePropertiesJsonobject.has("cubics")){
											cubics=check_vesselNamePropertiesJsonobject.getString("cubics");
										}
									}// isjsonvalid
									else{
										if(allReportJsonArrayInsideJSonobject.has("Cubics")){
											 cubics1=allReportJsonArrayInsideJSonobject.getString("Cubics");
											String returncubics = urlValue(cubics1);
											cubics=cubics1;
											//        								out.println("data sq");
											if(!GmailMethods.isNullString(returncubics)){
												if(vessel.hasNode(check_vesselNameProperties)){
													//        									out.println("data s2");
													vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
													vesselSubNodeName.setProperty("cubics", returncubics);
													//        									session.save();
												}
											}
											
											if( GmailMethods.isNullString(cubics) ){
												cubics=cubics1;
											}

											
										}
										else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub")){
											cubics1=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub");	
											String returncubics = urlValue(cubics1);
											cubics=cubics1;
											//        								out.println("data sq");
											if(!GmailMethods.isNullString(returncubics)){
												if(vessel.hasNode(check_vesselNameProperties)){
													//        									out.println("data s2");
													vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
													vesselSubNodeName.setProperty("cubics", returncubics);
													//        									session.save();
												}
											}

										}

									}

									if(checkjsonString==true){
										if(check_vesselNamePropertiesJsonobject.has("dwt")){
											dwt=check_vesselNamePropertiesJsonobject.getString("dwt");
										}

									}else{
										if(allReportJsonArrayInsideJSonobject.has("DWT")){
											 dwt1=allReportJsonArrayInsideJSonobject.getString("DWT");
											String returndwt = urlValue(dwt1);
											dwt=dwt1;
											//        								out.println("data dwt");
											if(!GmailMethods.isNullString(returndwt)){
												if(vessel.hasNode(check_vesselNameProperties)){
													vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
													vesselSubNodeName.setProperty("dwt", returndwt);
													//        									session.save();
												}
											}
											
											if( GmailMethods.isNullString(dwt) ){
												dwt=dwt1;
											}
											
										}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT")){
											dwt1=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT");
											String returndwt = urlValue(dwt1);
											dwt=dwt1;
											
											if(!GmailMethods.isNullString(returndwt)){
												if(vessel.hasNode(check_vesselNameProperties)){
													vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
													vesselSubNodeName.setProperty("dwt", returndwt);
													//            									session.save();
												}
											}
										}
									}

									if(checkjsonString==true){
										if(check_vesselNamePropertiesJsonobject.has("ice")){
											ice=check_vesselNamePropertiesJsonobject.getString("ice");
										}
									}else{
										if(allReportJsonArrayInsideJSonobject.has("ICE")){
											 ice1=allReportJsonArrayInsideJSonobject.getString("ICE");
											String returnice = urlValue(ice1);
											ice=ice1;
											//        								out.println("data ice");
											if(!GmailMethods.isNullString(returnice)){
												if(vessel.hasNode(check_vesselNameProperties)){
													vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
													vesselSubNodeName.setProperty("ice", returnice);
													//        									session.save();
												}
											}
											if( GmailMethods.isNullString(ice) ){
												ice=ice1;
											}
											
										}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ICE")){
											ice1=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ICE");
											String returnice = urlValue(ice1);
											ice=ice1;
											if(!GmailMethods.isNullString(returnice)){
												if(vessel.hasNode(check_vesselNameProperties)){
													vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
													vesselSubNodeName.setProperty("ice", returnice);
													//            									session.save();
												}
											}
										}
									}

									if(checkjsonString==true){
										if(check_vesselNamePropertiesJsonobject.has("loa")){
											loa=check_vesselNamePropertiesJsonobject.getString("loa");

										}}else{
											if(allReportJsonArrayInsideJSonobject.has("LOA")){
												 loa1=allReportJsonArrayInsideJSonobject.getString("LOA");
												String returloa = urlValue(loa1);
												loa=loa1;
												//        								out.println("data loa");
												if(!GmailMethods.isNullString(returloa)){	
													if(vessel.hasNode(check_vesselNameProperties)){
														vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
														vesselSubNodeName.setProperty("loa", returloa);
														//        									session.save();
													}
												}
												if( GmailMethods.isNullString(loa) ){
													loa=loa1;
												}
												
											}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA")){
												loa1=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA");
												String returloa = urlValue(loa1);
												loa=loa1;
												if(!GmailMethods.isNullString(returloa)){	
													if(vessel.hasNode(check_vesselNameProperties)){
														vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
														vesselSubNodeName.setProperty("loa", returloa);
														//            									session.save();
													}
												}
											}
										}

//									String owners_Name="";
									if(checkjsonString==true){
										if(check_vesselNamePropertiesJsonobject.has("owners_id")){
											owners_id=check_vesselNamePropertiesJsonobject.getString("owners_id");
										}}else{
											if(allReportJsonArrayInsideJSonobject.has("Owners")){
												 owners_Name=allReportJsonArrayInsideJSonobject.getString("Owners");
												String returnowners_Name = urlValue(owners_Name);
												
												owners_id=ChangedStructureCurrent_Methods.check_Owners(session, returnowners_Name,out);
												out.println("owners_id:: "+owners_id);
												if(!GmailMethods.isNullString(owners_id)){

													String vesselNamequery="";
													if(checkjsonString==true){
														if(check_vesselNamePropertiesJsonobject.has("vesselNamequery")){
															vesselNamequery=check_vesselNamePropertiesJsonobject.getString("vesselNamequery");
															//out.println("vesselNamequery_owners: "+vesselNamequery);

														}}
													if(vessel.hasNode(vesselNamequery)){
														vesselSubNodeName=vessel.getNode(vesselNamequery);
														//out.println("noode: "+vesselSubNodeName);
														vesselSubNodeName.setProperty("owners_id", owners_id);
														//        										session.save();

													}else{
														//out.println("vesselNamequery_owners_else: ");
														if(vessel.hasNode(check_vesselNameProperties)){
															//out.println("vesselNamequery_owners_if: ");
															vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
															vesselSubNodeName.setProperty("owners_id", owners_id);
															//session.save();
															//out.println("vesselNamequery_owners_last: ");
														}
													}
												}else{
													if( GmailMethods.isNullString(owners_id) ){
														owners_id=returnowners_Name;
													}
												}
											}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners")){
												owners_Name=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners");
												String returnowners_Name = urlValue(owners_Name);
												owners_id=ChangedStructureCurrent_Methods.check_Owners(session, returnowners_Name,out);
												//        								out.println("data owner");
												if(!GmailMethods.isNullString(owners_id)){

													String vesselNamequery="";
													if(checkjsonString==true){
														if(check_vesselNamePropertiesJsonobject.has("vesselNamequery")){
															vesselNamequery=check_vesselNamePropertiesJsonobject.getString("vesselNamequery");
														}}
													if(vessel.hasNode(vesselNamequery)){
														vesselSubNodeName=vessel.getNode(vesselNamequery);
														//        										out.println("noode: "+vesselSubNodeName);
														vesselSubNodeName.setProperty("owners_id", owners_id);
														//        										session.save();
													}else{

														if(vessel.hasNode(check_vesselNameProperties)){
															vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
															vesselSubNodeName.setProperty("owners_id", owners_id);
															//session.save();
														}
													}
												}

											}
										}

									if(GmailMethods.isNullString(owners_id)){
										owners_id=owners_Name;
									}

									//        						String operatorId="";
									
									if(checkjsonString==true){
										if(check_vesselNamePropertiesJsonobject.has("operatorId")){
											check_Operators_id=check_vesselNamePropertiesJsonobject.getString("operatorId");
										}}else{
											if(allReportJsonArrayInsideJSonobject.has("Operators")){
												opertaor_Name=allReportJsonArrayInsideJSonobject.getString("Operators");
												String returnopertaor_Name = urlValue(opertaor_Name);

												check_Operators_id=CSC.check_Operators(session, returnopertaor_Name,out);
												//            								out.println("data owner");
												out.println("check_Operators_id:: "+check_Operators_id);
												if(!GmailMethods.isNullString(check_Operators_id)){

													String vesselNamequery="";
													if(checkjsonString==true){
														if(check_vesselNamePropertiesJsonobject.has("vesselNamequery")){
															vesselNamequery=check_vesselNamePropertiesJsonobject.getString("vesselNamequery");
														}}
													if(vessel.hasNode(vesselNamequery)){
														vesselSubNodeName=vessel.getNode(vesselNamequery);
														//            										out.println("noode: "+vesselSubNodeName);
														vesselSubNodeName.setProperty("operatorId", check_Operators_id);
														//            										session.save();
													}else{

														if(vessel.hasNode(check_vesselNameProperties)){
															vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
															vesselSubNodeName.setProperty("operatorId", check_Operators_id);
															//session.save();
														}
													}
												}else{
													if( GmailMethods.isNullString(check_Operators_id) ){
														check_Operators_id=returnopertaor_Name;
													}
												}
											}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators")){
												opertaor_Name=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators");
												String returnopertaor_Name = urlValue(opertaor_Name);
												check_Operators_id=CSC.check_Operators(session, returnopertaor_Name,out);
												//            								out.println("data owner");
												if(!GmailMethods.isNullString(check_Operators_id)){

													String vesselNamequery="";
													if(checkjsonString==true){
														if(check_vesselNamePropertiesJsonobject.has("vesselNamequery")){
															vesselNamequery=check_vesselNamePropertiesJsonobject.getString("vesselNamequery");
														}}
													if(vessel.hasNode(vesselNamequery)){
														vesselSubNodeName=vessel.getNode(vesselNamequery);
														//            										out.println("noode: "+vesselSubNodeName);
														vesselSubNodeName.setProperty("operatorId", check_Operators_id);
														//            										session.save();
													}else{

														if(vessel.hasNode(check_vesselNameProperties)){
															vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
															vesselSubNodeName.setProperty("operatorId", check_Operators_id);
															//            									session.save();
														}
													}
												}
											}
										}

									if(GmailMethods.isNullString(check_Operators_id)){
										check_Operators_id=opertaor_Name;
									}


									if(checkjsonString==true){
										if(check_vesselNamePropertiesJsonobject.has("sternline")){
											sternline=check_vesselNamePropertiesJsonobject.getString("sternline");

										}}else{
											if(allReportJsonArrayInsideJSonobject.has("SternLine")){
												sternline=allReportJsonArrayInsideJSonobject.getString("SternLine");
												String returnsternline = urlValue(sternline);
												//        								out.println("data sl");
												if(!GmailMethods.isNullString(returnsternline)){
													if(vessel.hasNode(check_vesselNameProperties)){
														vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
														vesselSubNodeName.setProperty("sternline", returnsternline);
														//        									session.save();
													}
												}
											}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselSternLine")){
												sternline=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselSternLine");
												String returnsternline = urlValue(sternline);
												if(!GmailMethods.isNullString(returnsternline)){
													if(vessel.hasNode(check_vesselNameProperties)){
														vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
														vesselSubNodeName.setProperty("sternline", returnsternline);
														//            									session.save();
													}
												}
											}
										}

									
									if(checkjsonString==true){
										if(check_vesselNamePropertiesJsonobject.has("vesselType_id")){
											vesselType_id=check_vesselNamePropertiesJsonobject.getString("vesselType_id");


										}else{
											if(allReportJsonArrayInsideJSonobject.has("VesselType")){
												VesselType_Name=allReportJsonArrayInsideJSonobject.getString("VesselType");
												String returnVesselType_Name = urlValue(VesselType_Name);
												vesselType_id=ChangedStructureCurrent_Methods.check_vesselType(session, returnVesselType_Name, out);
												//        								out.println("data type");
												if(!GmailMethods.isNullString(vesselType_id)){
													//        									VesselNameString=VesselNameString.replace(" ", "_").replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9]", "_");
													/*if(vessel.hasNode(check_vesselNameProperties) || vessel.hasNode(VesselNameString)){
        									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        									vesselSubNodeName.setProperty("vesselType_id", vesselType_id);
        									session.save();
        								}*/
													String vesselNamequery="";
													if(checkjsonString==true){
														if(check_vesselNamePropertiesJsonobject.has("vesselNamequery")){
															vesselNamequery=check_vesselNamePropertiesJsonobject.getString("vesselNamequery");

														}}


													//        									out.println("vesseltyped: "+VesselNameString);
													if(vessel.hasNode(vesselNamequery)){
														vesselSubNodeName=vessel.getNode(vesselNamequery);
														//        										out.println("noode: "+vesselSubNodeName);
														vesselSubNodeName.setProperty("vesselType_id", vesselType_id);
														//        										session.save();
													}else{
														if(vessel.hasNode(check_vesselNameProperties)){
															vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
															vesselSubNodeName.setProperty("vesselType_id", vesselType_id);
															//        											session.save();
														}
													}

													/*vesselSubNodeName=(vessel.hasNode(check_vesselNameProperties)) ? vessel.getNode(check_vesselNameProperties) : vessel.getNode(VesselNameString);
        									out.println("g: "+vesselSubNodeName);
        									vesselSubNodeName.setProperty("vesselType_id", vesselType_id);
        									session.save();*/
												}else{
													if( GmailMethods.isNullString(vesselType_id) ){
														vesselType_id=returnVesselType_Name;
													}
												}
											} else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType")){

												VesselType_Name=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType");
												String returnVesselType_Name = urlValue(VesselType_Name);
												vesselType_id=ChangedStructureCurrent_Methods.check_vesselType(session, returnVesselType_Name, out);
												//        								out.println("data type");
												if(!GmailMethods.isNullString(vesselType_id)){
													//        									VesselNameString=VesselNameString.replace(" ", "_").replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9]", "_");
													/*if(vessel.hasNode(check_vesselNameProperties) || vessel.hasNode(VesselNameString)){
        									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        									vesselSubNodeName.setProperty("vesselType_id", vesselType_id);
        									session.save();
        								}*/
													String vesselNamequery="";
													if(checkjsonString==true){
														if(check_vesselNamePropertiesJsonobject.has("vesselNamequery")){
															vesselNamequery=check_vesselNamePropertiesJsonobject.getString("vesselNamequery");

														}}


													//        									out.println("vesseltyped: "+VesselNameString);
													if(vessel.hasNode(vesselNamequery)){
														vesselSubNodeName=vessel.getNode(vesselNamequery);
														//        										out.println("noode: "+vesselSubNodeName);
														vesselSubNodeName.setProperty("vesselType_id", vesselType_id);
														//        										session.save();
													}else{
														if(vessel.hasNode(check_vesselNameProperties)){
															vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
															vesselSubNodeName.setProperty("vesselType_id", vesselType_id);
															//        											session.save();
														}
													}

													/*vesselSubNodeName=(vessel.hasNode(check_vesselNameProperties)) ? vessel.getNode(check_vesselNameProperties) : vessel.getNode(VesselNameString);
        									out.println("g: "+vesselSubNodeName);
        									vesselSubNodeName.setProperty("vesselType_id", vesselType_id);
        									session.save();*/
												}

											}else{

												vesselType_id=VT;
												out.println("vt data: "+vesselType_id);

											}
										}

										if(GmailMethods.isNullString(vesselType_id)){
											vesselType_id=VesselType_Name;
										}

									}
									//        						out.println("end");
									if(checkjsonString==true){
										if(check_vesselNamePropertiesJsonobject.has("status_id")){
											status_id=check_vesselNamePropertiesJsonobject.getString("status_id");
										}}

									//        					}
								} // null check when vesselname is not there
							}// vesselname check from json
							else{
//								String VesselType_Name="";
								if(allReportJsonArrayInsideJSonobject.has("VesselType")){
									VesselType_Name=allReportJsonArrayInsideJSonobject.getString("VesselType");
									String returnVesselType_Name = urlValue(VesselType_Name);
									vesselType_id=ChangedStructureCurrent_Methods.check_vesselType(session, returnVesselType_Name, out);

								} // this condition used in brokertcrate report only bcz there is not vessel name given
								else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType")){
									VesselType_Name=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType");
									String returnVesselType_Name = urlValue(VesselType_Name);
									vesselType_id=ChangedStructureCurrent_Methods.check_vesselType(session, returnVesselType_Name, out);

								}else{

									vesselType_id=VT;

								}

								if(GmailMethods.isNullString(vesselType_id)){
									vesselType_id=VesselType_Name;
								}

							}
							out.println("vesselnameProperty is set to null");
						}// all vessel property


						else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselName")){

							Node vesselSubNodeName=null;
							VesselNameString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselName");
							String returnVesselName = urlValue(VesselNameString);

							if(!GmailMethods.isNullString(returnVesselName)){
								if(returnVesselName.contains("_")){
									returnVesselName=  returnVesselName.replace("_", " ");
									out.println("returnVesselName_after_url: "+returnVesselName);
								}

								check_vesselNameProperties=CSC.check_vesselName(session, returnVesselName, out);
								//        						out.println("nullwala: "+check_vesselNameProperties);
								if(!GmailMethods.isNullString(check_vesselNameProperties)){

									//        						out.println("check_vesselNameProperties_test: "+check_vesselNameProperties);

									boolean checkjsonString=isJSONValid(check_vesselNameProperties);
									JSONObject check_vesselNamePropertiesJsonobject=null;
									if(checkjsonString==true){
										check_vesselNamePropertiesJsonobject=new JSONObject(check_vesselNameProperties);

										if(check_vesselNamePropertiesJsonobject.has("Flag")){
											flag=check_vesselNamePropertiesJsonobject.getString("Flag");
										}

										//        						if(check_vesselNamePropertiesJsonobject.length()>0){
										//        						out.println("check_vesselNameProperties_test1: "+check_vesselNamePropertiesJsonobject);
										if(check_vesselNamePropertiesJsonobject.has("vessel_Id")){
											vessel_Id=check_vesselNamePropertiesJsonobject.getString("vessel_Id");
											//        							out.println("check_vesselNameProperties_test2: "+vessel_Id);
										}

									} // json string check is valid wala
									else{
										//        							out.println("else");
										if(vessel.hasNode(check_vesselNameProperties)){
											vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
											vessel_Id=vesselSubNodeName.getProperty("vessel_Id").getString();
										}
									}


									if(checkjsonString==true){
										if(check_vesselNamePropertiesJsonobject.has("built")){
											built=check_vesselNamePropertiesJsonobject.getString("built");
										}
									} //valid json
									else{
										if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt")){
											built=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselBuilt");
											String returnBuilt = urlValue(built);
											//        								out.println("data s");
											if(!GmailMethods.isNullString(returnBuilt)){
												if(vessel.hasNode(check_vesselNameProperties)){
													vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
													vesselSubNodeName.setProperty("built", returnBuilt);
													//session.save();
												}
											}

										}

									}

									if(checkjsonString==true){
										if(check_vesselNamePropertiesJsonobject.has("cubics")){
											cubics=check_vesselNamePropertiesJsonobject.getString("cubics");
										}
									}// isjsonvalid
									else{
										if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub")){
											cubics=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#cub");
											String returncubics = urlValue(cubics);
											//        								out.println("data sq");
											if(!GmailMethods.isNullString(returncubics)){
												if(vessel.hasNode(check_vesselNameProperties)){
													//        									out.println("data s2");
													vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
													vesselSubNodeName.setProperty("cubics", returncubics);
													//session.save();
												}
											}
										}
									}

									if(checkjsonString==true){
										if(check_vesselNamePropertiesJsonobject.has("dwt")){
											dwt=check_vesselNamePropertiesJsonobject.getString("dwt");
										}

									}else{
										if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT")){
											dwt=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DWT");
											String returndwt = urlValue(dwt);
											//        								out.println("data dwt");
											if(!GmailMethods.isNullString(returndwt)){
												if(vessel.hasNode(check_vesselNameProperties)){
													vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
													vesselSubNodeName.setProperty("dwt", returndwt);
													//session.save();
												}
											}
										}
									}

									if(checkjsonString==true){
										if(check_vesselNamePropertiesJsonobject.has("ice")){
											ice=check_vesselNamePropertiesJsonobject.getString("ice");
										}
									}else{
										if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ICE")){
											ice=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ICE");
											String returnice = urlValue(ice);
											//        								out.println("data ice");
											if(!GmailMethods.isNullString(returnice)){
												if(vessel.hasNode(check_vesselNameProperties)){
													vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
													vesselSubNodeName.setProperty("ice", returnice);
													//session.save();
												}
											}
										}
									}

									if(checkjsonString==true){
										if(check_vesselNamePropertiesJsonobject.has("loa")){
											loa=check_vesselNamePropertiesJsonobject.getString("loa");

										}}else{
											if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA")){
												loa=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselLOA");
												String returloa = urlValue(loa);
												//        								out.println("data loa");
												if(!GmailMethods.isNullString(returloa)){	
													if(vessel.hasNode(check_vesselNameProperties)){
														vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
														vesselSubNodeName.setProperty("loa", returloa);
														//session.save();
													}
												}
											}
										}

									if(checkjsonString==true){
										if(check_vesselNamePropertiesJsonobject.has("owners_id")){
											owners_id=check_vesselNamePropertiesJsonobject.getString("owners_id");
										}}else{
											if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners")){
												 owners_Name=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners");
												String returnowners_Name = urlValue(owners_Name);

												owners_id=ChangedStructureCurrent_Methods.check_Owners(session, returnowners_Name,out);
												//        								out.println("data owner");
												if(!GmailMethods.isNullString(owners_id)){

													String vesselNamequery="";
													if(checkjsonString==true){
														if(check_vesselNamePropertiesJsonobject.has("vesselNamequery")){
															vesselNamequery=check_vesselNamePropertiesJsonobject.getString("vesselNamequery");
														}}
													if(vessel.hasNode(vesselNamequery)){
														vesselSubNodeName=vessel.getNode(vesselNamequery);
														//        										out.println("noode: "+vesselSubNodeName);
														vesselSubNodeName.setProperty("owners_id", owners_id);
														//session.save();
													}else{

														if(vessel.hasNode(check_vesselNameProperties)){
															vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
															vesselSubNodeName.setProperty("owners_id", owners_id);
															//session.save();
														}
													}
												}
											}
										}

									//        						String operatorId="";
									if(checkjsonString==true){
										if(check_vesselNamePropertiesJsonobject.has("operatorId")){
											check_Operators_id=check_vesselNamePropertiesJsonobject.getString("operatorId");
										}}else{
											if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators")){
												 opertaor_Name=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators");
												String returnopertaor_Name = urlValue(opertaor_Name);

												check_Operators_id=CSC.check_Operators(session, returnopertaor_Name,out);
												//            								out.println("data owner");
												if(!GmailMethods.isNullString(check_Operators_id)){

													String vesselNamequery="";
													if(checkjsonString==true){
														if(check_vesselNamePropertiesJsonobject.has("vesselNamequery")){
															vesselNamequery=check_vesselNamePropertiesJsonobject.getString("vesselNamequery");
														}}
													if(vessel.hasNode(vesselNamequery)){
														vesselSubNodeName=vessel.getNode(vesselNamequery);
														//            										out.println("noode: "+vesselSubNodeName);
														vesselSubNodeName.setProperty("operatorId", check_Operators_id);
														//session.save();
													}else{

														if(vessel.hasNode(check_vesselNameProperties)){
															vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
															vesselSubNodeName.setProperty("operatorId", check_Operators_id);
															//session.save();
														}
													}
												}
											}
										}



									if(checkjsonString==true){
										if(check_vesselNamePropertiesJsonobject.has("sternline")){
											sternline=check_vesselNamePropertiesJsonobject.getString("sternline");

										}}else{
											if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselSternLine")){
												sternline=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselSternLine");
												String returnsternline = urlValue(sternline);
												//        								out.println("data sl");
												if(!GmailMethods.isNullString(returnsternline)){
													if(vessel.hasNode(check_vesselNameProperties)){
														vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
														vesselSubNodeName.setProperty("sternline", returnsternline);
														//session.save();
													}
												}
											}
										}

									if(checkjsonString==true){
										if(check_vesselNamePropertiesJsonobject.has("vesselType_id")){
											vesselType_id=check_vesselNamePropertiesJsonobject.getString("vesselType_id");


										}else{
											if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType")){
												 VesselType_Name=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType");
												String returnVesselType_Name = urlValue(VesselType_Name);
												vesselType_id=ChangedStructureCurrent_Methods.check_vesselType(session, returnVesselType_Name, out);
												//        								out.println("data type");
												if(!GmailMethods.isNullString(vesselType_id)){
													//        									VesselNameString=VesselNameString.replace(" ", "_").replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9]", "_");
													/*if(vessel.hasNode(check_vesselNameProperties) || vessel.hasNode(VesselNameString)){
        									vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
        									vesselSubNodeName.setProperty("vesselType_id", vesselType_id);
        									session.save();
        								}*/
													String vesselNamequery="";
													if(checkjsonString==true){
														if(check_vesselNamePropertiesJsonobject.has("vesselNamequery")){
															vesselNamequery=check_vesselNamePropertiesJsonobject.getString("vesselNamequery");

														}}


													//        									out.println("vesseltyped: "+VesselNameString);
													if(vessel.hasNode(vesselNamequery)){
														vesselSubNodeName=vessel.getNode(vesselNamequery);
														//        										out.println("noode: "+vesselSubNodeName);
														vesselSubNodeName.setProperty("vesselType_id", vesselType_id);
														//session.save();
													}else{
														if(vessel.hasNode(check_vesselNameProperties)){
															vesselSubNodeName=vessel.getNode(check_vesselNameProperties);
															vesselSubNodeName.setProperty("vesselType_id", vesselType_id);
															//session.save();
														}
													}

													/*vesselSubNodeName=(vessel.hasNode(check_vesselNameProperties)) ? vessel.getNode(check_vesselNameProperties) : vessel.getNode(VesselNameString);
        									out.println("g: "+vesselSubNodeName);
        									vesselSubNodeName.setProperty("vesselType_id", vesselType_id);
        									session.save();*/
												}else{
													vesselType_id="";
												}
											}
										}



									}
									//        						out.println("end");
									if(checkjsonString==true){
										if(check_vesselNamePropertiesJsonobject.has("status_id")){
											status_id=check_vesselNamePropertiesJsonobject.getString("status_id");
										}}

									//        					}
								} // null check when vesselname is not there
							}// vesselname check from json
							else{
								if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType")){
									 VesselType_Name=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType");
									String returnVesselType_Name = urlValue(VesselType_Name);
									vesselType_id=ChangedStructureCurrent_Methods.check_vesselType(session, returnVesselType_Name, out);

								} // this condition used in brokertcrate report only bcz there is not vessel name given
								else if(allReportJsonArrayInsideJSonobject.has("VesselType")){
									 VesselType_Name=allReportJsonArrayInsideJSonobject.getString("VesselType");
									String returnVesselType_Name = urlValue(VesselType_Name);
									vesselType_id=ChangedStructureCurrent_Methods.check_vesselType(session, returnVesselType_Name, out);

								}
							}

						} // vessel else if check


						else{
							if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType")){
								 VesselType_Name=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType");
								String returnVesselType_Name = urlValue(VesselType_Name);
								vesselType_id=ChangedStructureCurrent_Methods.check_vesselType(session, returnVesselType_Name, out);

							} // this condition used in brokertcrate report only bcz there is not vessel name given
							else if(allReportJsonArrayInsideJSonobject.has("VesselType")){
								 VesselType_Name=allReportJsonArrayInsideJSonobject.getString("VesselType");
								String returnVesselType_Name = urlValue(VesselType_Name);
								vesselType_id=ChangedStructureCurrent_Methods.check_vesselType(session, returnVesselType_Name, out);

							}
						}

						// extra for vesseltype

						/*String check_Operators_id="";
        					if(allReportJsonArrayInsideJSonobject.has("Operators")){
        						String OperatorsString=allReportJsonArrayInsideJSonobject.getString("Operators");
        						check_Operators_id=CSC.check_Operators(session, OperatorsString,out);
        					}*/

						String check_EmployementStatus="";
						String EmploymentStatusString="";
						if(allReportJsonArrayInsideJSonobject.has("Status")){
							EmploymentStatusString=allReportJsonArrayInsideJSonobject.getString("Status");
							String returnEmploymentStatusString = urlValue(EmploymentStatusString);
							check_EmployementStatus=CSC.check_EmployementStatus(session, returnEmploymentStatusString,out);
							if(GmailMethods.isNullString(check_EmployementStatus) ){
								check_EmployementStatus=EmploymentStatusString;
							}
						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#EmploymentStatus")){
							EmploymentStatusString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#EmploymentStatus");
							String returnEmploymentStatusString = urlValue(EmploymentStatusString);
							check_EmployementStatus=CSC.check_EmployementStatus(session, returnEmploymentStatusString,out);
							if(GmailMethods.isNullString(check_EmployementStatus) ){
								check_EmployementStatus=EmploymentStatusString;
							}
						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status")){
							EmploymentStatusString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status");
							String returnEmploymentStatusString = urlValue(EmploymentStatusString);
							check_EmployementStatus=CSC.check_EmployementStatus(session, returnEmploymentStatusString,out);
							if(GmailMethods.isNullString(check_EmployementStatus) ){
								check_EmployementStatus=EmploymentStatusString;
							}
						}else if(allReportJsonArrayInsideJSonobject.has("Status1")){
							check_EmployementStatus=allReportJsonArrayInsideJSonobject.getString("Status1");
						}
						if(GmailMethods.isNullString(check_EmployementStatus)){
							if(allReportJsonArrayInsideJSonobject.has("Status1")){
								check_EmployementStatus=allReportJsonArrayInsideJSonobject.getString("Status1");
							}
						}


						String OpenPortString="";
						String check_OpenPort_id="";
						String flagPortOpen="";
						if(allReportJsonArrayInsideJSonobject.has("OpenPort")){
							OpenPortString=allReportJsonArrayInsideJSonobject.getString("OpenPort");
							String returnOpenPortString = urlValue(OpenPortString);

							if(returnOpenPortString.contains("_")){
								returnOpenPortString=  returnOpenPortString.replace("_", " ");
								out.println("returnVesselName_after: "+returnOpenPortString);
							}

							check_OpenPort_id=CSC.check_Port(session, returnOpenPortString, out);
							boolean checkjsonString=isJSONValid(check_OpenPort_id);
							if(checkjsonString==true){
								JSONObject data=new JSONObject(check_OpenPort_id);
								if(data.has("port_id")){
									check_OpenPort_id=data.getString("port_id");
									out.println("check_OpenPort_id: "+check_OpenPort_id);
								}
								/*if(data.has("Area")){
//      								 check_Port_repositionRegion_id=data.getString("Area");
      								    check_Port_repositionRegion_id=check_OpenPort_id;
      							   }*/
								if(data.has("flag")){
									flagPortOpen=data.getString("flag");
								}


							}
							if(GmailMethods.isNullString(check_OpenPort_id) ){
								check_OpenPort_id=OpenPortString;
							}

						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort")){
							OpenPortString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenPort");
							String returnOpenPortString = urlValue(OpenPortString);
							if(returnOpenPortString.contains("_")){
								returnOpenPortString=  returnOpenPortString.replace("_", " ");
								out.println("returnVesselName_: "+returnOpenPortString);
							}
							check_OpenPort_id=CSC.check_Port(session, returnOpenPortString, out);
							boolean checkjsonString=isJSONValid(check_OpenPort_id);
							if(checkjsonString==true){
								JSONObject data=new JSONObject(check_OpenPort_id);
								if(data.has("port_id")){
									check_OpenPort_id=data.getString("port_id");
								}
								/* if(data.has("Area")){
//      								 check_Port_repositionRegion_id=data.getString("Area");
      								    check_Port_repositionRegion_id=check_OpenPort_id;
      							   }*/
								if(data.has("flag")){
									flagPortOpen=data.getString("flag");
								}


							}

							if(GmailMethods.isNullString(check_OpenPort_id) ){
								check_OpenPort_id=OpenPortString;
							}
						}



						String OpenDateString="";
						//OpenDateString check_OpenPort_id
						if(allReportJsonArrayInsideJSonobject.has("OpenDate")){
							OpenDateString=allReportJsonArrayInsideJSonobject.getString("OpenDate");
							OpenDateString = urlValue(OpenDateString);
							out.println("OpenDateString: "+OpenDateString);

						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate")){
							OpenDateString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#OpenDate");
							OpenDateString = urlValue(OpenDateString);
						}

						String ETABasisString="";

							if(allReportJsonArrayInsideJSonobject.has("ETABasis")){
								ETABasisString=allReportJsonArrayInsideJSonobject.getString("ETABasis");
								ETABasisString = urlValue(ETABasisString);
								out.println("ETABasisString :: "+ETABasisString);
							}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS")){
								ETABasisString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS");
								ETABasisString = urlValue(ETABasisString);
							}
						

						String CommentString="";
						if(allReportJsonArrayInsideJSonobject.has("Comment")){
							CommentString=allReportJsonArrayInsideJSonobject.getString("Comment");
							CommentString = urlValue(CommentString);
						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Comment")){
							CommentString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Comment");
							CommentString = urlValue(CommentString);
						}
						else if(allReportJsonArrayInsideJSonobject.has("Comments")){
							CommentString=allReportJsonArrayInsideJSonobject.getString("Comments");
							CommentString = urlValue(CommentString);
						}
						String check_Source="";
						if(allReportJsonArrayInsideJSonobject.has("Source")){
							String SourceString=allReportJsonArrayInsideJSonobject.getString("Source");
							SourceString = urlValue(SourceString);
							check_Source=CSC.check_Source(session, SourceString,out);
						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Source")){
							String SourceString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Source");
							SourceString = urlValue(SourceString);
							check_Source=CSC.check_Source(session, SourceString,out);
						}
						else{
							check_Source=CSC.check_Source(session, from_Source,out);

						}

						//        					String combinedatetimestamp="";
						String ReportTimestampString="";
						JSONObject dateJson=null;
						if(allReportJsonArrayInsideJSonobject.has("ReportTimestamp")){
							ReportTimestampString=allReportJsonArrayInsideJSonobject.getString("ReportTimestamp");
							ReportTimestampString = urlValue(ReportTimestampString);
							/*if(ReportTimestampString!=""){
        							Date date=GmailMethods.parseDate(ReportTimestampString);
        							 dateJson=SlingMethods.formatDate_Month_Year_Day(date, out);

        							    int day=0;
        								int month=0;
        								int year=0;
        								int hours=0;
        								int Minute=0;
        								String s="";
        								if(dateJson.has("day")){
        									 day=dateJson.getInt("day");
        									 String dayString=String.valueOf(day);
        									 boolean b3 = Pattern.matches("(\\d{1}$)", dayString); 
        									 if(b3==true){
        										  s="0"+day;
        									 }else{
        										 s=String.valueOf(day);
        									 }

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

        							 combinedatetimestamp=s+"-"+String.valueOf(month)+"-"+String.valueOf(year)+" "+hours+":"+Minute;

        						}*/
						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportTimeStamp")){
							ReportTimestampString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportTimeStamp");
							ReportTimestampString = urlValue(ReportTimestampString);
						}
						else {
							ReportTimestampString=timestampDateAndTime;
						}

						String check_Information="";
						if(allReportJsonArrayInsideJSonobject.has("Information")){
							String InformationString=allReportJsonArrayInsideJSonobject.getString("Information");
							InformationString = urlValue(InformationString);
							check_Information=CSC.check_Information(session, InformationString, out);

						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Information")){
							String InformationString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Information");
							InformationString = urlValue(InformationString);
							check_Information=CSC.check_Information(session, InformationString, out);
						}
						String check_FixturetType_id="";
						if(allReportJsonArrayInsideJSonobject.has("FixtureType")){
							String FixtureTypeString=allReportJsonArrayInsideJSonobject.getString("FixtureType");
							FixtureTypeString = urlValue(FixtureTypeString);
							check_FixturetType_id=CSC.check_ReportType(session, FixtureTypeString, out);
						}else if(allReportJsonArrayInsideJSonobject.has("Fixture Type")){
							String FixtureTypeString=allReportJsonArrayInsideJSonobject.getString("Fixture Type");
							FixtureTypeString = urlValue(FixtureTypeString);
							check_FixturetType_id=CSC.check_ReportType(session, FixtureTypeString, out);
						}
						else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#FixtureType")){
							String FixtureTypeString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#FixtureType");
							FixtureTypeString = urlValue(FixtureTypeString);
							check_FixturetType_id=CSC.check_ReportType(session, FixtureTypeString, out);
						}
						else{
							check_FixturetType_id="2";
						}
						String check_Charterer="";
						String ChartererString="";
						if(allReportJsonArrayInsideJSonobject.has("Charterer")){
							ChartererString=allReportJsonArrayInsideJSonobject.getString("Charterer");
							ChartererString = urlValue(ChartererString);
							check_Charterer=CSC.check_Charterer(session, ChartererString, out);
							if(GmailMethods.isNullString(check_Charterer) ){
								check_Charterer=ChartererString;
							}
						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers")){
							ChartererString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers");
							ChartererString = urlValue(ChartererString);
							check_Charterer=CSC.check_Charterer(session, ChartererString, out);
							if(GmailMethods.isNullString(check_Charterer) ){
								check_Charterer=ChartererString;
							}
						}

						if(GmailMethods.isNullString(check_Charterer)){

							if(allReportJsonArrayInsideJSonobject.has("Charterers1")){
								check_Charterer=allReportJsonArrayInsideJSonobject.getString("Charterers1");
							}else if(allReportJsonArrayInsideJSonobject.has("Charterers")){
								check_Charterer=allReportJsonArrayInsideJSonobject.getString("Charterers");
							}
						}

						/*if(GmailMethods.isNullString(check_Charterer)){
							JSONObject chartereobj=ChartererUnknownCheck.chartererData(allReportJsonArrayInsideJSonobject);
							if(chartereobj!=null && chartereobj.length()!=0){        						
								if(chartereobj.has("Charterer")){
									JSONObject chartererSolrjsonobj=chartereobj.getJSONObject("Charterer");

									if(chartererSolrjsonobj.has("Charterer")){
										check_Charterer=chartererSolrjsonobj.getString("Charterer");
									}
								}

							}

						}*/


						String check_Status="";
						String StatusString="";
						if(allReportJsonArrayInsideJSonobject.has("Status")){
							StatusString=allReportJsonArrayInsideJSonobject.getString("Status");
							StatusString = urlValue(StatusString);
							check_Status=CSC.check_EmployementStatus(session, StatusString,out);
							if(GmailMethods.isNullString(check_Status) ){
								check_Status=StatusString;
							}
							out.println("check_Status: "+check_Status);
						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status")){
							StatusString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status");
							StatusString = urlValue(StatusString);
							check_Status=CSC.check_EmployementStatus(session, StatusString,out);
							if(GmailMethods.isNullString(check_Status) ){
								check_Status=StatusString;
							}
						}

						if(GmailMethods.isNullString(check_Status)){
							if(allReportJsonArrayInsideJSonobject.has("Status1")){
								check_Status=allReportJsonArrayInsideJSonobject.getString("Status1");
							}
						}

                        if( GmailMethods.isNullString(check_Status) ){
                        	check_Status=StatusString;
                        }else{
                        	if( GmailMethods.isNullString(check_Status) ){
                        		check_Status=StatusHeader;
                        	}
                        }


						String check_CargoGrade="";
						String CargoGradeString="";
						if(allReportJsonArrayInsideJSonobject.has("CargoGrade")){
							CargoGradeString=allReportJsonArrayInsideJSonobject.getString("CargoGrade");
							CargoGradeString = urlValue(CargoGradeString);
							check_CargoGrade=CSC.check_CargoGrade(session, CargoGradeString, out);
							if(GmailMethods.isNullString(check_CargoGrade) ){
								check_CargoGrade=CargoGradeString;
							}
						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade")){
							CargoGradeString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade");
							CargoGradeString = urlValue(CargoGradeString);
							check_CargoGrade=CSC.check_CargoGrade(session, CargoGradeString, out);
							if(GmailMethods.isNullString(check_CargoGrade) ){
								check_CargoGrade=CargoGradeString;
							}
						}else if(allReportJsonArrayInsideJSonobject.has("Cargo_Grade")){
							CargoGradeString=allReportJsonArrayInsideJSonobject.getString("Cargo_Grade");
							CargoGradeString = urlValue(CargoGradeString);
							check_CargoGrade=CSC.check_CargoGrade(session, CargoGradeString, out);
							if(GmailMethods.isNullString(check_CargoGrade) ){
								check_CargoGrade=CargoGradeString;
							}
						}

						if(GmailMethods.isNullString(check_CargoGrade)){
							if(allReportJsonArrayInsideJSonobject.has("Cargo_Grade1")){
								check_CargoGrade= CargoGradeString=allReportJsonArrayInsideJSonobject.getString("Cargo_Grade1");
							}
						}	

						String CargoQtyString="";
//						String cargo_json="";
						//cargo_count rate_count
						if(allReportJsonArrayInsideJSonobject.has("CargoQty") ){
							CargoQtyString=allReportJsonArrayInsideJSonobject.getString("CargoQty");
							CargoQtyString = urlValue(CargoQtyString);
						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#CargoQty")){
							CargoQtyString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#CargoQty");
							CargoQtyString = urlValue(CargoQtyString);
						}
						
						out.println("CargoQtyString_inside: "+CargoQtyString);
						out.println("vessel_inside: "+VesselNameString);
						/*else {


							String pattern = "[^a-zA-Z.//$//?//_//|]";

							for(int cargo_count=0;cargo_count<=15;cargo_count++){

								if(allReportJsonArrayInsideJSonobject.has("unknown_"+cargo_count)){

									Pattern pattern1 = Pattern.compile(pattern);
									Matcher matcher = pattern1.matcher(allReportJsonArrayInsideJSonobject.getString("unknown_"+cargo_count));


									while (matcher.find()) {
										CargoQtyString+=matcher.group();
									}	
									if(!GmailMethods.isNullString(CargoQtyString)){
										cargo_json=	"unknown_"+cargo_count;
										break;
									}								
								}else if(allReportJsonArrayInsideJSonobject.has("unknown"+cargo_count)){
									Pattern pattern1 = Pattern.compile(pattern);
									Matcher matcher = pattern1.matcher(allReportJsonArrayInsideJSonobject.getString("unknown"+cargo_count));


									while (matcher.find()) {
										CargoQtyString+=matcher.group();
									}	
									if(!GmailMethods.isNullString(CargoQtyString)){
										cargo_json=	"unknown"+cargo_count;
										break;
									}
								}



							}


						}*/
						String LCStartString="";
						if(allReportJsonArrayInsideJSonobject.has("LCStart")){
							LCStartString=allReportJsonArrayInsideJSonobject.getString("LCStart");
							LCStartString = urlValue(LCStartString);
						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#LCEnd")){
							LCStartString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#LCEnd");
							LCStartString = urlValue(LCStartString);
						}
						String LCEndString="";
						if(allReportJsonArrayInsideJSonobject.has("LCEnd")){
							LCEndString=allReportJsonArrayInsideJSonobject.getString("LCEnd");
							LCEndString = urlValue(LCEndString);
						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#LCStart")){
							LCEndString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#LCStart");
							LCEndString = urlValue(LCEndString);
						}/*else{
        						if(GmailMethods.isNullString(LCEndString)){
        						   LCEndString=LCStartString;
        						}
        					}*/

						if(GmailMethods.isNullString(LCEndString)){
							LCEndString=LCStartString;
							/*if( GmailMethods.isNullString(LCEndString) ){
								if(allReportJsonArrayInsideJSonobject.has("DATE")){
									LCEndString= allReportJsonArrayInsideJSonobject.getString("DATE");
								}
							}*/
							
						} if(GmailMethods.isNullString(LCStartString)){
							LCStartString=LCEndString;
							/*if( GmailMethods.isNullString(LCStartString) ){
								if(allReportJsonArrayInsideJSonobject.has("DATE")){
									LCStartString= allReportJsonArrayInsideJSonobject.getString("DATE");
								}
							}*/
						}

						out.println("LCEndString: "+LCEndString);
						String check_LoadPort_id="";
						String flagPort="";
						String LoadPortString="";
						if(allReportJsonArrayInsideJSonobject.has("LoadPort")){
							LoadPortString=allReportJsonArrayInsideJSonobject.getString("LoadPort");
							LoadPortString = urlValue(LoadPortString);
							if(!GmailMethods.isNullString(LoadPortString)){
								if(LoadPortString.contains("_")){
									LoadPortString=  LoadPortString.replace("_", " ");
									out.println("returnLoadPortString: "+LoadPortString);
								}
								String  check_LoadPort_id1=CSC.check_Port(session, LoadPortString, out);

								boolean checkjsonString=isJSONValid(check_LoadPort_id1);
								if(checkjsonString==true){
									JSONObject data=new JSONObject(check_LoadPort_id1);
									if(data.has("port_id")){
										check_LoadPort_id=data.getString("port_id");
									}
									if(data.has("flag")){
										flagPort=data.getString("flag");
									}

								}

							}

							if(GmailMethods.isNullString(check_LoadPort_id) ){
								check_LoadPort_id=LoadPortString;
								/*if( GmailMethods.isNullString(check_LoadPort_id) ){
									if(allReportJsonArrayInsideJSonobject.has("RepositionRegion")){
										check_LoadPort_id= allReportJsonArrayInsideJSonobject.getString("RepositionRegion");
									}
								}*/
							}

						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#LoadPort")){
							LoadPortString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#LoadPort");
							LoadPortString = urlValue(LoadPortString);

							if(!GmailMethods.isNullString(LoadPortString)){
								if(LoadPortString.contains("_")){
									LoadPortString=  LoadPortString.replace("_", " ");
									out.println("returnLoadPortString: "+LoadPortString);
								}

								String  check_LoadPort_id1=CSC.check_Port(session, LoadPortString, out);

								boolean checkjsonString=isJSONValid(check_LoadPort_id1);
								if(checkjsonString==true){
									JSONObject data=new JSONObject(check_LoadPort_id1);
									if(data.has("port_id")){
										check_LoadPort_id=data.getString("port_id");
										out.println("check_LoadPort_id: "+check_LoadPort_id);
									}if(data.has("flag")){
										flagPort=data.getString("flag");
									}

								}

							}

							if(GmailMethods.isNullString(check_LoadPort_id) ){
								check_LoadPort_id=LoadPortString;
								/*if( GmailMethods.isNullString(check_LoadPort_id) ){
									if(allReportJsonArrayInsideJSonobject.has("RepositionRegion")){
										check_LoadPort_id= allReportJsonArrayInsideJSonobject.getString("RepositionRegion");
									}
								}*/
							}
						}



						String check_DiscPort_id="";
						//        					String check_DiscPort_id1="";
						String DiscPortString="";
						if(allReportJsonArrayInsideJSonobject.has("DiscPort")){
							DiscPortString=allReportJsonArrayInsideJSonobject.getString("DiscPort");
							DiscPortString = urlValue(DiscPortString);
							if(!GmailMethods.isNullString(DiscPortString)){
								if(DiscPortString.contains("_")){
									DiscPortString=  DiscPortString.replace("_", " ");
									out.println("returnDiscPortString: "+DiscPortString);
								}

								String check_DiscPort_id1=CSC.check_Port(session, DiscPortString, out);

								boolean checkjsonString=isJSONValid(check_DiscPort_id1);
								if(checkjsonString==true){
									JSONObject data=new JSONObject(check_DiscPort_id1);
									if(data.has("port_id")){
										check_DiscPort_id=data.getString("port_id");
									}if(data.has("flag")){
										flagPort=data.getString("flag");
									}

								}

							}
							if(GmailMethods.isNullString(check_DiscPort_id) ){
								check_DiscPort_id=DiscPortString;
								/*if( GmailMethods.isNullString(check_DiscPort_id) ){
									if(allReportJsonArrayInsideJSonobject.has("RepositionRegion")){
										check_DiscPort_id= allReportJsonArrayInsideJSonobject.getString("RepositionRegion");
									}
								}*/
							}

						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DiscPort")){
							DiscPortString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DiscPort");
							DiscPortString = urlValue(DiscPortString);
							if(!GmailMethods.isNullString(DiscPortString)){

								if(DiscPortString.contains("_")){
									DiscPortString=  DiscPortString.replace("_", " ");
									out.println("returnDiscPortString: "+DiscPortString);
								}

								String check_DiscPort_id1=CSC.check_Port(session, DiscPortString, out);

								boolean checkjsonString=isJSONValid(check_DiscPort_id1);
								if(checkjsonString==true){
									JSONObject data=new JSONObject(check_DiscPort_id1);
									if(data.has("port_id")){
										check_DiscPort_id=data.getString("port_id");
										out.println("check_DiscPort_id: "+check_DiscPort_id);
									}if(data.has("flag")){
										flagPort=data.getString("flag");
									}


								}

							}
							if(GmailMethods.isNullString(check_DiscPort_id) ){
								check_DiscPort_id=DiscPortString;
								/*if( GmailMethods.isNullString(check_DiscPort_id) ){
									if(allReportJsonArrayInsideJSonobject.has("RepositionRegion")){
										check_DiscPort_id= allReportJsonArrayInsideJSonobject.getString("RepositionRegion");
									}
								}*/
							}
						}
						
						/*for(int port=0;port<=15;port++){
							if(allReportJsonArrayInsideJSonobject.has("unknown_"+port)){
								String portUnknownValue=allReportJsonArrayInsideJSonobject.getString("unknown_"+port);
								PortSlashFoundCheck.fetchsolrdata(portUnknownValue);
								//PortSlashFoundCheck.parsesolrresp(solrResp);
							}
							
							
						}*/
						

						out.println("after disck port: ");


						String check_RateType="";
						String RateTypeString="";
						String RateString="";
						boolean rateFound =false;

						//only call for nonTonnageReport
						//label change call method.
						//if("Spot".equalsIgnoreCase(returnReportType)){
/*							if((allReportJsonArrayInsideJSonobject.has("Rate") && 
									(GmailMethods.isNullString(allReportJsonArrayInsideJSonobject.getString("Rate"))))
									){
								if((allReportJsonArrayInsideJSonobject.has("RateType") && 
										(!GmailMethods.isNullString(allReportJsonArrayInsideJSonobject.getString("RateType"))))
										|| (allReportJsonArrayInsideJSonobject.has("Rate Type") && 
												(!GmailMethods.isNullString(allReportJsonArrayInsideJSonobject.getString("Rate Type")))) 
										){

								}else{

									boolean reportChange = ReportTypeCorrection.reportCorrectionMethod(VesselNameString, allReportJsonArrayInsideJSonobject,  out,  session,  emailUrl,  reportcomesFrom,  "",  timestampDateAndTime);
									if(!reportChange){
										out.println("!reportChange_inside:: ");
										ReportTypeString = "Tonnage";	
										check_OpenPort_id = LoadPortString;
										OpenDateString = LCEndString;
										check_ReportType_id ="1";
									}
								}
								
							}*/


							if(allReportJsonArrayInsideJSonobject.has("RateType") ){
								RateTypeString=allReportJsonArrayInsideJSonobject.getString("RateType");
								RateTypeString = urlValue(RateTypeString);
								/*if("RNR".equalsIgnoreCase(RateTypeString)){
									rateFound = true;
									RateString ="RNR";
								}*/
								check_RateType=CSC.check_RateType(session, RateTypeString, out);
								if(GmailMethods.isNullString(check_RateType) ){
									check_RateType=RateTypeString;
								}

							}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RateType") ){
								RateTypeString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RateType");
								RateTypeString = urlValue(RateTypeString);
								/*if("RNR".equalsIgnoreCase(RateTypeString)){
									rateFound = true;
									RateString ="RNR";
								}*/
								check_RateType=CSC.check_RateType(session, RateTypeString, out);
								if(GmailMethods.isNullString(check_RateType) ){
									check_RateType=RateTypeString;
								}

							}else if(allReportJsonArrayInsideJSonobject.has("Rate Type")){
								RateTypeString=allReportJsonArrayInsideJSonobject.getString("Rate Type");
								RateTypeString = urlValue(RateTypeString);
								/*if("RNR".equalsIgnoreCase(RateTypeString)){
									rateFound = true;
									RateString ="RNR";
								}*/
								check_RateType=CSC.check_RateType(session, RateTypeString, out);
								if(GmailMethods.isNullString(check_RateType) ){
									check_RateType=RateTypeString;
								}

							}



							out.println("check_RateType: "+check_RateType);

							//cargo_json
							//cargo_count rate_count
							if(allReportJsonArrayInsideJSonobject.has("Rate") ){
								RateString=allReportJsonArrayInsideJSonobject.getString("Rate");
								RateString = urlValue(RateString);
								//String rateTypeVal = getRateVal(RateString);
								/*if("RNR".equalsIgnoreCase(rateTypeVal)){
									RateString = rateTypeVal;
								}*/
								//check_RateType =rateTypeVal;
								/*System.out.println("Rate Type is :: "+rateTypeVal);
								System.out.println("Rate is "+RateString);*/
							}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#SpotRate") ){
								RateString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#SpotRate");
								RateString = urlValue(RateString);
								//String rateTypeVal = getRateVal(RateString);
								//check_RateType =rateTypeVal;
								/*if("RNR".equalsIgnoreCase(rateTypeVal)){
									RateString = rateTypeVal;
								}
								check_RateType =rateTypeVal;
								out.println("Rate Type is :: "+rateTypeVal);
								out.println("Rate is "+RateString);*/
							}
							/*else if(!rateFound){
								String rateTypeVal = null;
								for(int rate_count=0;rate_count<=25;rate_count++){

									if(allReportJsonArrayInsideJSonobject.has("unknown_"+rate_count)){
										String unknownVal= "unknown_"+rate_count;
										if((!GmailMethods.isNullString(cargo_json)) && cargo_json.equals(unknownVal)){
											continue;	
										}
										RateString=allReportJsonArrayInsideJSonobject.getString("unknown_"+rate_count);
										rateTypeVal = getRateVal(RateString);
										if("RNR".equalsIgnoreCase(rateTypeVal)){
											RateString = rateTypeVal;
										}
										check_RateType =rateTypeVal;
										if(!GmailMethods.isNullString(rateTypeVal)){
											out.println("Rate Type is :: "+rateTypeVal);
											out.println("Rate is "+RateString);

											break;
										}else{
											System.out.println("Error in RateType being passed as :: "+rateTypeVal);
											System.out.println("Error in Rate being passed as :: "+RateString);	
										}
									}else if(allReportJsonArrayInsideJSonobject.has("unknown"+rate_count)){
										String unknownVal= "unknown"+rate_count;
										if((!GmailMethods.isNullString(cargo_json)) && cargo_json.equals(unknownVal)){
											continue;	
										}
										RateString=allReportJsonArrayInsideJSonobject.getString("unknown"+rate_count);
										rateTypeVal = getRateVal(RateString);
										if("RNR".equalsIgnoreCase(rateTypeVal)){
											RateString = rateTypeVal;
										}
										check_RateType =rateTypeVal;
										if(!GmailMethods.isNullString(rateTypeVal)){
											out.println("Rate Type is :: "+rateTypeVal);
											out.println("Rate is "+RateString);

											break;
										}else{
											System.out.println("Error in RateType being passed as :: "+rateTypeVal);
											System.out.println("Error in Rate being passed as :: "+RateString);	
										}
									}
								}

							}*/
							/*if(GmailMethods.isNullString(check_RateType)){
							ReportTypeString = "Tonnage";	
							check_OpenPort_id = LoadPortString;
							OpenDateString = LCEndString;
							check_ReportType_id ="1";
							}*/
						//}//
						
							if( !GmailMethods.isNullString(RateString) ){ // if rate do not blank 
								if( GmailMethods.isNullString(check_RateType) ){ // check ratetype blank
									String rateTypeVal = getRateVal(RateString);
									check_RateType=rateTypeVal;
								}
							}else if( !GmailMethods.isNullString(check_RateType) ){  // if rate type not blank 
								  if( GmailMethods.isNullString(RateString) ){  // check rate 
									  RateString=check_RateType;
								}
							}
						

						String ReportDateString="";
						if(allReportJsonArrayInsideJSonobject.has("ReportDate")){
							ReportDateString=allReportJsonArrayInsideJSonobject.getString("ReportDate");
							ReportDateString = urlValue(ReportDateString);
						}else if(allReportJsonArrayInsideJSonobject.has("Report Date")){
							ReportDateString=allReportJsonArrayInsideJSonobject.getString("Report Date");
							ReportDateString = urlValue(ReportDateString);
						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportDate")){
							ReportDateString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportDate");
							ReportDateString = urlValue(ReportDateString);
						}

						else{
							ReportDateString=timestampDateAndTime;
						}
						String check_Spot_TCS_id="";
						if(allReportJsonArrayInsideJSonobject.has("Spot_TC")){
							String Spot_TCString=allReportJsonArrayInsideJSonobject.getString("Spot_TC");
							Spot_TCString = urlValue(Spot_TCString);
							check_Spot_TCS_id=CSC.check_ReportType(session, Spot_TCString, out);
						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Spot_TC")){
							String Spot_TCString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Spot_TC");
							Spot_TCString = urlValue(Spot_TCString);
							check_Spot_TCS_id=CSC.check_ReportType(session, Spot_TCString, out);
						}
						String DateString="";
						if(allReportJsonArrayInsideJSonobject.has("Date")){
							DateString=allReportJsonArrayInsideJSonobject.getString("Date");
							DateString = urlValue(DateString);
						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#date")){
							DateString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#date");
							DateString = urlValue(DateString);
						}
						String check_CPP_DPP_id="";
						/*String CPP_DPPString="";
        					if(allReportJsonArrayInsideJSonobject.has("CargoType")){
        						 CPP_DPPString=allReportJsonArrayInsideJSonobject.getString("CargoType");
        						CPP_DPPString = urlValue(CPP_DPPString);
        						check_CPP_DPP_id=CSC.check_CargoType(session, CPP_DPPString, out);
        					}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type")){
        						 CPP_DPPString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type");
        						CPP_DPPString = urlValue(CPP_DPPString);
        						check_CPP_DPP_id=CSC.check_CargoType(session, CPP_DPPString, out);
        					}*/

						if(!GmailMethods.isNullString(check_CargoType_cargotype_id) ){
							check_CPP_DPP_id=check_CargoType_cargotype_id;
						}else{
							check_CPP_DPP_id="";
						}

						String DeliveryString="";
						if(allReportJsonArrayInsideJSonobject.has("Delivery")){
							DeliveryString=allReportJsonArrayInsideJSonobject.getString("Delivery");
							DeliveryString = urlValue(DeliveryString);
						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TcDelivery")){
							DeliveryString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TcDelivery");
							DeliveryString = urlValue(DeliveryString);
						}
						String check_DeliveryPlace="";
						String DeliveryPlaceString="";
						if(allReportJsonArrayInsideJSonobject.has("DeliveryPlace")){
							DeliveryPlaceString=allReportJsonArrayInsideJSonobject.getString("DeliveryPlace");
							DeliveryPlaceString = urlValue(DeliveryPlaceString);
							check_DeliveryPlace=CSC.check_DeliveryPlace(session, DeliveryPlaceString, out);
							if(GmailMethods.isNullString(check_DeliveryPlace) ){
								check_DeliveryPlace=DeliveryPlaceString;
							}
						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DeliveryPlace")){
							DeliveryPlaceString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#DeliveryPlace");
							DeliveryPlaceString = urlValue(DeliveryPlaceString);
							check_DeliveryPlace=CSC.check_DeliveryPlace(session, DeliveryPlaceString, out);
							if(GmailMethods.isNullString(check_DeliveryPlace) ){
								check_DeliveryPlace=DeliveryPlaceString;
							}
						}



						String PeriodString="";
						if(allReportJsonArrayInsideJSonobject.has("Period")){
							PeriodString=allReportJsonArrayInsideJSonobject.getString("Period");
							PeriodString = urlValue(PeriodString);
						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TcPeriod")){
							PeriodString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TcPeriod");
							PeriodString = urlValue(PeriodString);
						}
						String check_Periodunit="";
						String PeriodunitString="";
						if(allReportJsonArrayInsideJSonobject.has("Periodunit")){
							PeriodunitString=allReportJsonArrayInsideJSonobject.getString("Periodunit");
							PeriodunitString = urlValue(PeriodunitString);
							check_Periodunit=CSC.check_Periodunit(session, PeriodunitString, out);
							if(GmailMethods.isNullString(check_Periodunit) ){
								check_Periodunit=PeriodunitString;
							}
						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Periodunit")){
							PeriodunitString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Periodunit");
							PeriodunitString = urlValue(PeriodunitString);
							check_Periodunit=CSC.check_Periodunit(session, PeriodunitString, out);
							if(GmailMethods.isNullString(check_Periodunit) ){
								check_Periodunit=PeriodunitString;
							}
						}



						String RedeliveryString="";
						if(allReportJsonArrayInsideJSonobject.has("Redelivery")){
							RedeliveryString=allReportJsonArrayInsideJSonobject.getString("Redelivery");
							RedeliveryString = urlValue(RedeliveryString);
						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TcRedelivery")){
							RedeliveryString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#TcRedelivery");
							RedeliveryString = urlValue(RedeliveryString);
						}
						String TCRateString="";
						if(allReportJsonArrayInsideJSonobject.has("TCRate")){
							TCRateString=allReportJsonArrayInsideJSonobject.getString("TCRate");
							TCRateString = urlValue(TCRateString);
						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#SpotRate")){
							TCRateString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#SpotRate");
							TCRateString = urlValue(TCRateString);
						}else if(allReportJsonArrayInsideJSonobject.has("Rate")){
							TCRateString=allReportJsonArrayInsideJSonobject.getString("Rate");
							TCRateString = urlValue(TCRateString);
						}
						String check_currencyUnit="";
						String CurrencyUnitString="";
						if(allReportJsonArrayInsideJSonobject.has("CurrencyUnit")){
							CurrencyUnitString=allReportJsonArrayInsideJSonobject.getString("CurrencyUnit");
							CurrencyUnitString = urlValue(CurrencyUnitString);
							check_currencyUnit=CSC.check_currencyUnit(session, CurrencyUnitString, out);
							if(GmailMethods.isNullString(check_currencyUnit) ){
								check_currencyUnit=CurrencyUnitString;
							}
						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#CurrencyUnit")){
							CurrencyUnitString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#CurrencyUnit");
							CurrencyUnitString = urlValue(CurrencyUnitString);
							check_currencyUnit=CSC.check_currencyUnit(session, CurrencyUnitString, out);
							if(GmailMethods.isNullString(check_currencyUnit) ){
								check_currencyUnit=CurrencyUnitString;
							}
						}



						String Month_YearString="";
						if(allReportJsonArrayInsideJSonobject.has("Month_Year")){
							Month_YearString=allReportJsonArrayInsideJSonobject.getString("Month_Year");
							Month_YearString = urlValue(Month_YearString);
						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Month_Year")){
							Month_YearString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Month_Year");
							Month_YearString = urlValue(Month_YearString);
						}
						String first_yrString="";
						if(allReportJsonArrayInsideJSonobject.has("1yr")){
							first_yrString=allReportJsonArrayInsideJSonobject.getString("1yr");
							first_yrString = urlValue(first_yrString);
						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#1yr")){
							first_yrString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#1yr");
							first_yrString = urlValue(first_yrString);
						}
						String second_yrString="";
						if(allReportJsonArrayInsideJSonobject.has("2yr")){
							second_yrString=allReportJsonArrayInsideJSonobject.getString("2yr");
							second_yrString = urlValue(second_yrString);
						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#2yr")){
							second_yrString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#2yr");
							second_yrString = urlValue(second_yrString);
						}
						String third_yrString="";
						if(allReportJsonArrayInsideJSonobject.has("3yr")){
							third_yrString=allReportJsonArrayInsideJSonobject.getString("3yr");
							third_yrString = urlValue(third_yrString);
						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#3yr")){
							third_yrString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#3yr");
							third_yrString = urlValue(third_yrString);
						}
						String fifth_yrString="";
						if(allReportJsonArrayInsideJSonobject.has("5yr")){
							fifth_yrString=allReportJsonArrayInsideJSonobject.getString("5yr");
							fifth_yrString = urlValue(fifth_yrString);
						}else if(allReportJsonArrayInsideJSonobject.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#5yr")){
							fifth_yrString=allReportJsonArrayInsideJSonobject.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#5yr");
							fifth_yrString = urlValue(fifth_yrString);
						}
						String Extra_Column="";
						if(allReportJsonArrayInsideJSonobject.has("Extra_Column")){
							Extra_Column=allReportJsonArrayInsideJSonobject.getString("Extra_Column");
						}

						/*Date date=GmailMethods.parseDate(textSentMailTime);
							    dateJson=SlingMethods.formatDate_Month_Year_Day(date, out);

							    int day=0;
								int month=0;
								int year=0;
								int hours=0;
								int Minute=0;
								String s="";
								if(dateJson.has("day")){
									 day=dateJson.getInt("day");
									 String dayString=String.valueOf(day);
									 boolean b3 = Pattern.matches("(\\d{1}$)", dayString); 
									 if(b3==true){
										  s="0"+day;
									 }else{
										 s=String.valueOf(day);
									 }

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

								textSentMailTime=s+"-"+String.valueOf(month)+"-"+String.valueOf(year)+" "+hours+":"+Minute;
						 */
						String Flag="";
						if(flag!=""){
							Flag=flag;
						}else {
							Flag="False";
						}

						String flagPortOpen1="";
						if(flagPortOpen!=""){
							flagPortOpen1=flagPortOpen;

						}else{
							flagPortOpen1="false";
						}

						String flagPort1="";
						if(flagPort!=""){
							flagPort1=flagPort;
						}else{
							flagPort1="false";
						}

						//	session.save();



						//callReportwiseReortsaveInSling(out, session, ReportTypeString, check_Port_repositionRegion_id, check_CargoType_cargotype_id, vessel_Id, vesselType_id, built, dwt, cubics, loa, ice, sternline, owners_id, check_Operators_id, check_EmployementStatus, check_OpenPort_id, OpenDateString, ETABasisString, CommentString, check_Source, ReportTimestampString, check_Information, check_FixturetType_id, check_Charterer, check_Status, CargoQtyString, check_CargoGrade, LCStartString, LCEndString, check_LoadPort_id, check_DiscPort_id, check_RateType, RateString, ReportDateString, check_Spot_TCS_id, DateString, check_CPP_DPP_id, DeliveryString, check_DeliveryPlace, PeriodString, check_Periodunit, RedeliveryString, TCRateString, check_currencyUnit, CommentString,Month_YearString,first_yrString,second_yrString,third_yrString,fifth_yrString, emailUrl, subjectNodePath,textSentMailTime,from_Source,timestampDate,timestampDateAndTime, Flag, flagPort1, flagPortOpen1, reportcomesFrom, allReport);

						/*if("pdf".equalsIgnoreCase(reportcomesFrom)){

        					}else{

        						out.println("else all check");
        						callReportwiseReortsaveInSling(out, session, ReportTypeString, check_Port_repositionRegion_id, check_CargoType_cargotype_id, vessel_Id, vesselType_id, built, dwt, cubics, loa, ice, sternline, owners_id, check_Operators_id, check_EmployementStatus, check_OpenPort_id, OpenDateString, ETABasisString, CommentString, check_Source, ReportTimestampString, check_Information, check_FixturetType_id, check_Charterer, check_Status, CargoQtyString, check_CargoGrade, LCStartString, LCEndString, check_LoadPort_id, check_DiscPort_id, check_RateType, RateString, ReportDateString, check_Spot_TCS_id, DateString, check_CPP_DPP_id, DeliveryString, check_DeliveryPlace, PeriodString, check_Periodunit, RedeliveryString, TCRateString, check_currencyUnit, CommentString,Month_YearString,first_yrString,second_yrString,third_yrString,fifth_yrString, emailUrl, subjectNodePath,textSentMailTime,from_Source,timestampDate,timestampDateAndTime, Flag, flagPort1, flagPortOpen1, reportcomesFrom, allReport, allReportJsonArrayInsideJSonobject);
        					    session.save();

        					}*/
						/*if( !GmailMethods.isNullString(vessel_Id) ){
        					     boolean checkVesselNameOtherFalse=isJSONValid(check_vesselNameProperties);
        					     if( checkVesselNameOtherFalse==false ){
        						     vessel_Id=check_vesselNameProperties;
        					    }
        					}*/
						if(GmailMethods.isNullString(vessel_Id)){
							vessel_Id=VesselNameString;
						}
						if(GmailMethods.isNullString(check_CargoType_cargotype_id)){
							check_CargoType_cargotype_id=CT;   // header gpoes here
						}if(GmailMethods.isNullString(built)){
							built=built1;   // header gpoes here
						}if(GmailMethods.isNullString(cubics)){
							cubics=cubics1;   // header gpoes here
						}if(GmailMethods.isNullString(dwt)){
							dwt=dwt1;   // header gpoes here
						}if(GmailMethods.isNullString(ice)){
							ice=ice1;   // header gpoes here
						}if(GmailMethods.isNullString(loa)){
							loa=loa1;   // header gpoes here
						}if(GmailMethods.isNullString(owners_id)){
							owners_id=owners_Name;   // header gpoes here
						}if(GmailMethods.isNullString(check_Operators_id)){
							check_Operators_id=opertaor_Name;   // header gpoes here
						}/*if( !GmailMethods.isNullString(vesselType_id)){
							
							 boolean numeric = true;
							  
						        try {
						            Integer num = Integer.parseInt(vesselType_id);
						            
						        } catch (NumberFormatException e) {
						            numeric = false;
						        }
							  
						        if(numeric){
						        	if("Spot".equalsIgnoreCase(returnReportType) ){
						        		CargoQtyString=vesselType_id;
						        	}
						        }
						} else{
				        	vesselType_id=VesselType_Name;
				        	if( GmailMethods.isNullString(vesselType_id) ){
				        		vesselType_id=VT;
				        	}
				        }*/
						
						if( GmailMethods.isNullString(vesselType_id) ){
							 vesselType_id=VT;
						}
						else{
							boolean numeric = true;
							  
					        try {
					            Integer num = Integer.parseInt(vesselType_id);
					            
					        } catch (NumberFormatException e) {
					            numeric = false;
					        }
						  
					        if(numeric){
					        	
					        }else{
					        	vesselType_id=VesselType_Name;
					        }
						}
						
						/*if(GmailMethods.isNullString(check_EmployementStatus) ){
							 check_EmployementStatus=StatusHeader;
						}*/
//						else{
							if(GmailMethods.isNullString(check_EmployementStatus)){
								check_EmployementStatus=check_Status;
							}/*else{
								if(GmailMethods.isNullString(check_EmployementStatus)){
									check_EmployementStatus=StatusHeader;
								}
							
//							}		
						}*/
						
						//.............checking nan here ..........................................
						
						check_Port_repositionRegion_id=CheckNan.makeNanToBlank(out, check_Port_repositionRegion_id);
						check_CargoType_cargotype_id=CheckNan.makeNanToBlank(out, check_CargoType_cargotype_id);
						vessel_Id=CheckNan.makeNanToBlank(out, vessel_Id);
						owners_id=CheckNan.makeNanToBlank(out, owners_id);
						check_Operators_id=CheckNan.makeNanToBlank(out, check_Operators_id);
						vesselType_id=CheckNan.makeNanToBlank(out, vesselType_id);
						built=CheckNan.makeNanToBlank(out, built);
						cubics=CheckNan.makeNanToBlank(out, cubics);
						dwt=CheckNan.makeNanToBlank(out, dwt);
						ice=CheckNan.makeNanToBlank(out, ice);
						loa=CheckNan.makeNanToBlank(out, loa);
						sternline=CheckNan.makeNanToBlank(out, sternline);
						check_EmployementStatus=CheckNan.makeNanToBlank(out, check_EmployementStatus);
						check_OpenPort_id=CheckNan.makeNanToBlank(out, check_OpenPort_id);
						OpenDateString=CheckNan.makeNanToBlank(out, OpenDateString);
						ETABasisString=CheckNan.makeNanToBlank(out, ETABasisString);
						CommentString=CheckNan.makeNanToBlank(out, CommentString);
						check_Charterer=CheckNan.makeNanToBlank(out, check_Charterer);
						check_Status=CheckNan.makeNanToBlank(out, check_Status);
						check_CargoGrade=CheckNan.makeNanToBlank(out, check_CargoGrade);
						CargoQtyString=CheckNan.makeNanToBlank(out, CargoQtyString);
						LCStartString=CheckNan.makeNanToBlank(out, LCStartString);
						LCEndString=CheckNan.makeNanToBlank(out, LCEndString);
						check_LoadPort_id=CheckNan.makeNanToBlank(out, check_LoadPort_id);
						check_DiscPort_id=CheckNan.makeNanToBlank(out, check_DiscPort_id);
						check_RateType=CheckNan.makeNanToBlank(out, check_RateType);
						RateString=CheckNan.makeNanToBlank(out, RateString);
						DateString=CheckNan.makeNanToBlank(out, DateString);
						check_CPP_DPP_id=CheckNan.makeNanToBlank(out, check_CPP_DPP_id);
						DeliveryString=CheckNan.makeNanToBlank(out, DeliveryString);
						check_DeliveryPlace=CheckNan.makeNanToBlank(out, check_DeliveryPlace);
						PeriodString=CheckNan.makeNanToBlank(out, PeriodString);
						check_Periodunit=CheckNan.makeNanToBlank(out, check_Periodunit);
						RedeliveryString=CheckNan.makeNanToBlank(out, RedeliveryString);
						TCRateString=CheckNan.makeNanToBlank(out, TCRateString);
						check_currencyUnit=CheckNan.makeNanToBlank(out, check_currencyUnit);
						Month_YearString=CheckNan.makeNanToBlank(out, Month_YearString);
						first_yrString=CheckNan.makeNanToBlank(out, first_yrString);
						second_yrString=CheckNan.makeNanToBlank(out, second_yrString);
						third_yrString=CheckNan.makeNanToBlank(out, third_yrString);
						fifth_yrString=CheckNan.makeNanToBlank(out, fifth_yrString);
						
						//..........end nan .........................here
						

						if(GmailMethods.isNullString(ETABasisString)){
							if(allReportJsonArrayInsideJSonobject.has("ETABasis1")){
								ETABasisString=allReportJsonArrayInsideJSonobject.getString("ETABasis1");
							}
							/*else if(allReportJsonArrayInsideJSonobject.has("DATE")){
								   ETABasisString=allReportJsonArrayInsideJSonobject.getString("DATE");
							}*/
						}
						
						if(GmailMethods.isNullString(ETABasisString)){
							ETABasisString=EB;
						} // second priority
						
						/*if(GmailMethods.isNullString(OpenDateString)){
							if(allReportJsonArrayInsideJSonobject.has("OPEN")){
								OpenDateString=allReportJsonArrayInsideJSonobject.getString("OPEN");
							}
							else if(allReportJsonArrayInsideJSonobject.has("Date")){
								OpenDateString=allReportJsonArrayInsideJSonobject.getString("Date");
							}
						}*/
						
						out.println("combine all three data check");
						//out.println("new type: "+ReportTypeString.trim());
						//ReportTypeString ,
						out.println("CargoQtyString_outside: "+CargoQtyString);
						out.println("vessel_outside: "+VesselNameString);
						
						callReportwiseReortsaveInSling(out, session, ReportTypeString, check_Port_repositionRegion_id, check_CargoType_cargotype_id, vessel_Id, vesselType_id, built, dwt, cubics, loa, ice, sternline, owners_id, check_Operators_id, check_EmployementStatus, check_OpenPort_id, OpenDateString, ETABasisString, CommentString, check_Source, ReportTimestampString, check_Information, check_FixturetType_id, check_Charterer, check_Status, CargoQtyString, check_CargoGrade, LCStartString, LCEndString, check_LoadPort_id, check_DiscPort_id, check_RateType, RateString, ReportDateString, check_Spot_TCS_id, DateString, check_CPP_DPP_id, DeliveryString, check_DeliveryPlace, PeriodString, check_Periodunit, RedeliveryString, TCRateString, check_currencyUnit, CommentString,Month_YearString,first_yrString,second_yrString,third_yrString,fifth_yrString, emailUrl, subjectNodePath,textSentMailTime,from_Source,timestampDate,timestampDateAndTime, Flag, flagPort1, flagPortOpen1, reportcomesFrom, allReport, allReportJsonArrayInsideJSonobject.toString(), Extra_Column);
						//session.save();

					}//  for 
					}// length check
				}// table_data check has
				}
				//.......

				if(objectjson.has("Error")){
					JSONArray ErrorArray= objectjson.getJSONArray("Error");
					if(ErrorArray.length()>0){
						//callOtherNodeError(out, session, emailUrl, reportcomesFrom, "", "", pythonScriptApiData, timestampDateAndTime);
						ErrorNodeReportwise.callOtherNodeError(out, session, emailUrl, reportcomesFrom, "", "", pythonScriptApiData, timestampDateAndTime);
					}

				} // error check





				//......

			}
			//}

			/* if("pdf".equalsIgnoreCase(reportcomesFrom)){
				out.println("pdf check data");
				JSONObject mainJson = new JSONObject(allReport);
				JSONObject finalJson = null;
				if(ReportTypeString.equalsIgnoreCase("Tonnage")){
					JSONArray tonnage = SyncXPdf.getTonnage(allReport, out);
					mainJson.put("Tonnage", tonnage);
					finalJson = fetchAllReport.callPArsedBrokerDataTemplateMultiple(session, from_Source, "Tonnage", mainJson.toString() , out);
					out.println("finaljsonTonnage: "+finalJson);
					fetchAllReport.insertUnknownInSling(out, session, finalJson.toString(), from_Source, timestampDateAndTime, reportcomesFrom, emailUrl, subjectNodePath, textSentMailTime, timestampDate);

					if(){
						write here  which are lenght >0 and or blank check json strng 
					}   

				}else if(ReportTypeString.equalsIgnoreCase("Spot")){
					JSONArray spot = SyncXPdf.getSpot(allReport, out);
					mainJson.put("Spot", spot);
					 finalJson = fetchAllReport.callPArsedBrokerDataTemplateMultiple(session, from_Source, "Spot", mainJson.toString() , out);
					 out.println("finaljsonSpot: "+finalJson);
					 fetchAllReport.insertUnknownInSling(out, session, finalJson.toString(), from_Source, timestampDateAndTime, reportcomesFrom, emailUrl, subjectNodePath, textSentMailTime, timestampDate);
				}else if(ReportTypeString.equalsIgnoreCase("TimeCharterReports")){
					JSONArray spot = SyncXPdf.getTimeCharter(allReport);
					mainJson.put("TimeChater", spot);
					 finalJson = fetchAllReport.callPArsedBrokerDataTemplateMultiple(session, from_Source, "TimeCharterReports", mainJson.toString() , out);
					 out.println("finaljsonTimeChater: "+finalJson);
					 fetchAllReport.insertUnknownInSling(out, session, finalJson.toString(), from_Source, timestampDateAndTime, reportcomesFrom, emailUrl, subjectNodePath, textSentMailTime, timestampDate);
				}
				else if(ReportTypeString.equalsIgnoreCase("BrokerTcRate")){
					JSONArray spot = SyncXPdf.getBrokerTc(allReport);
					mainJson.put("BrokerTc", spot);
					 finalJson = fetchAllReport.callPArsedBrokerDataTemplateMultiple(session, from_Source, "BrokerTcRate", mainJson.toString() , out);
					 out.println("finaljsonSpot: "+finalJson);
					 fetchAllReport.insertUnknownInSling(out, session, finalJson.toString(), from_Source, timestampDateAndTime, reportcomesFrom, emailUrl, subjectNodePath, textSentMailTime, timestampDate);
				}


			}*/


			//	JSONObject allReportJsonObject=new JSONObject(allReport);





		} catch (Exception e) {
			e.printStackTrace(out);
			//			out.println(e.getMessage());
		}
	}

	public static void callReportwiseReortsaveInSling(PrintWriter out, Session session, String ReportTypeString, String RepositionRegion, String CargoType
			,String VesselName, String VesselType,String Built,String DWT,String Cubics,String LOA,String ICE,String SternLine,String Owners
			,String Operators,String EmploymentStatus,String OpenPort,String OpenDate,String ETABasis,String Comment,String Source,String ReportTimestamp, String Information, String FixtureType
			,String Charterer,String Status, String CargoQty, String CargoGrade, String LCStart, String LCEnd, String LoadPort, String DiscPort, String RateType, String Rate, String ReportDate, String Spot_TC
			, String Date, String CPP_DPP, String Delivery, String DeliveryPlace, String Period, String Periodunit, String Redelivery, String TCRate, String Unit, String Comments,
			String Month_Year, String first_yr, String second_yr, String third_yr, String fifth_yr, String emailUrl, String subjectNodePath, String textSentMailTime, String from_Source, String timestampDate,String timestampDateAndTime, String Flag, String flagPort, String flagPortOpen1, String reportcomesFrom, String data, String allReportJsonArrayInsideJSonobject, String Extra_Column){

		try {
			ChangedStructureCurrent_Methods CSC=new ChangedStructureCurrent_Methods();

			ReportTypeString = ReportTypeString.trim();
			out.println("new type: "+ReportTypeString.trim());

			if( ReportTypeString.toLowerCase().equals("position") || ReportTypeString.toLowerCase().equals("tonnage") || ReportTypeString.toLowerCase().equals("Tonnage") ){

				ReportTypeString="Tonnage";
				ReportTypeString=CSC.check_ReportType(session, ReportTypeString, out);
				if( GmailMethods.isNullString(VesselName) ){
					//callCheckBlankNodeError(out, session, emailUrl, reportcomesFrom, data, allReportJsonArrayInsideJSonobject, timestampDateAndTime);
				
					ErrorNodeReportwise.callCheckBlankNodeError(out, session, emailUrl, reportcomesFrom, data, allReportJsonArrayInsideJSonobject, timestampDateAndTime);
				}else{
					out.println("Tonnage Called here");
					callTonnageReport(out, session, ReportTypeString, RepositionRegion, CargoType, VesselName, VesselType, Built, DWT, Cubics, LOA, ICE, SternLine, Owners, Operators, EmploymentStatus, OpenPort, OpenDate, ETABasis, Comment, Source, ReportTimestamp, emailUrl,subjectNodePath,textSentMailTime,  from_Source,timestampDate,timestampDateAndTime, Flag,flagPort, flagPortOpen1, reportcomesFrom, data,  allReportJsonArrayInsideJSonobject, Extra_Column);
				}

			}else if( ReportTypeString.equals("Spot") ){
				//				ReportTypeString="Spot";
				if( GmailMethods.isNullString(VesselName) ){
					//callCheckBlankNodeError(out, session, emailUrl, reportcomesFrom, data, allReportJsonArrayInsideJSonobject,timestampDateAndTime);
					ErrorNodeReportwise.callCheckBlankNodeError(out, session, emailUrl, reportcomesFrom, data, allReportJsonArrayInsideJSonobject, timestampDateAndTime);
				
				}else{
					callSpotReport(out, session, Information, FixtureType, Charterer, Status, CargoQty, CargoGrade, LCStart, LCEnd, LoadPort, DiscPort, VesselName, RateType, Rate, Comment, ReportDate, Source, VesselType, ReportTimestamp, emailUrl, subjectNodePath, textSentMailTime, CargoType,  from_Source,timestampDate,timestampDateAndTime, Flag, flagPort, flagPortOpen1, reportcomesFrom, data, allReportJsonArrayInsideJSonobject, Extra_Column);
				}
			}else if( ReportTypeString.toLowerCase().equals("timecharterreports") || ReportTypeString.toLowerCase().equals("timecharter")){
				//				ReportTypeString="Time";
				if( GmailMethods.isNullString(VesselName) ){
					//callCheckBlankNodeError(out, session, emailUrl, reportcomesFrom, data, allReportJsonArrayInsideJSonobject, timestampDateAndTime);
					ErrorNodeReportwise.callCheckBlankNodeError(out, session, emailUrl, reportcomesFrom, data, allReportJsonArrayInsideJSonobject, timestampDateAndTime);
				
				}else{
					callTimecharterReport(out, session, Information, Spot_TC, Date, VesselName, CPP_DPP, Charterer, Delivery, DeliveryPlace, Period, Periodunit, Redelivery, TCRate, Rate, Unit, Comments, ReportTimestamp, Source, VesselType,emailUrl, subjectNodePath, textSentMailTime,  from_Source,timestampDate,timestampDateAndTime, Flag, flagPort, flagPortOpen1, reportcomesFrom, data, allReportJsonArrayInsideJSonobject, Extra_Column);
				}

			}else if( ReportTypeString.toLowerCase().equals("brokertcrate") || ReportTypeString.toLowerCase().equals("brokerrate") ){

				if( GmailMethods.isNullString(VesselName) ){
					//callCheckBlankNodeError(out, session, emailUrl, reportcomesFrom, data, allReportJsonArrayInsideJSonobject, timestampDateAndTime);
					ErrorNodeReportwise.callCheckBlankNodeError(out, session, emailUrl, reportcomesFrom, data, allReportJsonArrayInsideJSonobject, timestampDateAndTime);
				}else{
					callBrokerTcRateReport(out, session, Source, VesselType, Month_Year, first_yr, second_yr, third_yr, fifth_yr,ReportTimestamp, emailUrl, subjectNodePath, textSentMailTime,  from_Source,timestampDate,timestampDateAndTime, Flag, flagPort, flagPortOpen1, reportcomesFrom, data,  allReportJsonArrayInsideJSonobject, Extra_Column);
				}
			}else {

				if( ReportTypeString.equals("Other") || ReportTypeString.equals("None") ){
					//callOtherNodeError(out, session, emailUrl, reportcomesFrom, data, allReportJsonArrayInsideJSonobject, "", timestampDateAndTime);
					ErrorNodeReportwise.callOtherNodeError(out, session, emailUrl, reportcomesFrom, data, allReportJsonArrayInsideJSonobject, "", timestampDateAndTime);
				}


			}


		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}

	public static void callTonnageReport(PrintWriter out, Session session, String ReportType, String RepositionRegion, String CargoType
			,String VesselName, String VesselType,String Built,String DWT,String Cubics,String LOA,String ICE,String SternLine,String Owners
			,String Operators,String EmploymentStatus,String OpenPort,String OpenDate,String ETABasis,String Comment,String Source,String ReportTimestamp, String emailUrl,String subjectNodePath,String textSentMailTime, String from_Source, String timestampDate,String timestampDateAndTime, String Flag, String flagPort, String flagPortOpen1, String reportcomesFrom, String data, String allReportJsonArrayInsideJSonobject, String Extra_Column){

		try {
			out.println("Tonnage Entered");
			if( !GmailMethods.isNullString(VesselName) ){
			/*if( GmailMethods.isNullString(VesselName) ||  GmailMethods.isNullString(CargoType) || GmailMethods.isNullString(OpenPort) || 
					 GmailMethods.isNullString(OpenDate) || GmailMethods.isNullString(RepositionRegion) ){
				// callCheckBlankNodeError(out, session, emailUrl, reportcomesFrom, data, allReportJsonArrayInsideJSonobject, timestampDateAndTime);
				//ErrorNodeReportwise.callAllDynamicReport(out, session, ReportType, emailUrl, subjectNodePath, textSentMailTime, from_Source, reportcomesFrom, allReportJsonArrayInsideJSonobject);
				 ErrorNodeReportwise.callTonnageError(out, session,ReportType,RepositionRegion,CargoType,VesselName, VesselType, Built, DWT, Cubics, LOA, ICE, SternLine, Owners, Operators, EmploymentStatus, OpenPort, OpenDate, ETABasis, Comment, Source, ReportTimestamp,  emailUrl, subjectNodePath, textSentMailTime,  from_Source,  timestampDate, timestampDateAndTime,  Flag,  flagPort,  flagPortOpen1,  reportcomesFrom,  data,  allReportJsonArrayInsideJSonobject);
			}else{*/

				Node scorpio=null;
				Node Tonnage=null;
				Node ReportData=null;
				Node subNode=null;

				if(session.getRootNode().hasNode("scorpioDataBase")){
					scorpio = session.getRootNode().getNode("scorpioDataBase");
				}else{
					scorpio = session.getRootNode().addNode("scorpioDataBase");
					session.refresh(true);
					session.save();
				}

				if(scorpio.hasNode("ReportData")){
					ReportData = scorpio.getNode("ReportData");
				}else{
					ReportData = scorpio.addNode("ReportData");
					session.refresh(true);
					session.save();
				}

				if(ReportData.hasNode("Tonnage")){
					Tonnage=ReportData.getNode("Tonnage");
				}else{
					Tonnage=ReportData.addNode("Tonnage");
					Tonnage.setProperty("jcr:count", String.valueOf(0));
					Tonnage.setProperty("netChangedPointer", String.valueOf(0));
					session.refresh(true);
					session.save();
				}

				Calendar cal = null;
				cal = Calendar.getInstance();

				Date minDate = new Date(cal.getTimeInMillis());  // get today date

				Date date=GmailMethods.parseDate(minDate.toString());
				JSONObject dateJson=SlingMethods.formatDate_Month_Year_Day(date, out);

				int day=0;
				int month=0;
				int year=0;
				int hours=0;
				int Minute=0;
				String s="";
				if(dateJson.has("day")){
					day=dateJson.getInt("day");
					String dayString=String.valueOf(day);
					boolean b3 = Pattern.matches("(\\d{1}$)", dayString); 
					if(b3==true){
						s="0"+day;
					}else{
						s=String.valueOf(day);
					}

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

				String combinedatetimestamp=s+"-"+String.valueOf(month)+"-"+String.valueOf(year);
				Calendar c = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

				if( !GmailMethods.isNullString(textSentMailTime) ){
					c.setTime(sdf.parse(textSentMailTime));
				}

				String count=Tonnage.getProperty("jcr:count").getString();
				String forid= String.valueOf(Integer.parseInt(count)+1);
				if(!Tonnage.hasNode(forid)){
					subNode=Tonnage.addNode(forid);

					subNode.setProperty("Sr.No", forid);
					subNode.setProperty("Id", forid);
					subNode.setProperty("dateAndTime", combinedatetimestamp);
					subNode.setProperty("emailUrl", emailUrl);
					//				 subNode.setProperty("excelUrl", "");
					//				 subNode.setProperty("Error", "");
					//				 subNode.setProperty("attachment", "");
					subNode.setProperty("ReportType",ReportType);
					out.println(" inside tonnage report rg:: "+RepositionRegion);
					subNode.setProperty("RepositionRegion",RepositionRegion);
					out.println(" inside tonnage report CT:: "+CargoType);
					subNode.setProperty("CargoType",CargoType);
					subNode.setProperty("VesselName", VesselName);
					out.println(" inside tonnage report VT:: "+VesselType);
					subNode.setProperty("VesselType", VesselType);
					subNode.setProperty("Built", Built);
					subNode.setProperty("DWT", DWT);
					subNode.setProperty("Cubics", Cubics);
					subNode.setProperty("LOA", LOA);
					subNode.setProperty("ICE", ICE);
					subNode.setProperty("SternLine", SternLine);
					subNode.setProperty("Owners", Owners);
					subNode.setProperty("Operators", Operators);
					subNode.setProperty("EmploymentStatus", EmploymentStatus); 
					subNode.setProperty("OpenPort", OpenPort);
					if(!GmailMethods.isNullString(OpenDate)){
						OpenDate=new ConvertDate().convertDateFormat(OpenDate);
					}else{
						OpenDate="";
					}
					subNode.setProperty("OpenDate", OpenDate);

					out.println(" inside tonnage report EB:: "+ETABasis);
					if(!GmailMethods.isNullString(ETABasis)){
						ETABasis=new ConvertDate().convertDateFormat(ETABasis);
					}else{
						ETABasis="";
					}
					subNode.setProperty("ETABasis", ETABasis);

					subNode.setProperty("Comment", Comment);
					subNode.setProperty("Source", Source);
					subNode.setProperty("ReportTimestamp", c);
					subNode.setProperty("emailClientNodeId", subjectNodePath);
					subNode.setProperty("textSentMailTime", textSentMailTime);
					subNode.setProperty("vesselFlag", Flag); 
					//				 subNode.setProperty("flagPort", flagPort);
					subNode.setProperty("flagOpenPort", flagPortOpen1);
					subNode.setProperty("reportcomesFrom", reportcomesFrom); //
					subNode.setProperty("SingleJson",  allReportJsonArrayInsideJSonobject);
					subNode.setProperty("Extra_Column",  Extra_Column);
					/*if("pdf".equals(reportcomesFrom)){
					 subNode.setProperty("pdfJson", data);
				 }*/

					ChangedStructureCurrent_Methods CSC=new ChangedStructureCurrent_Methods();
					if(!GmailMethods.isNullString(from_Source)){
						from_Source=CSC.check_Source(session, from_Source,out);
					}else{
						from_Source="";
					}
					subNode.setProperty("from_Source", from_Source);
					subNode.setProperty("timestampDate", timestampDate);
					subNode.setProperty("timestampDateAndTime", timestampDateAndTime);

					Tonnage.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
					//				 textNode.setProperty("Flag", "1");
					session.refresh(true);
					session.save();

				}

			}

		} catch (Exception e) {
			//			out.println(e.getMessage());
			System.out.println(e.getMessage());
		}
	}

	public static void callSpotReport(PrintWriter out, Session session, String Information, String FixtureType, String Charterer, String Status
			,String CargoQty, String CargoGrade, String LCStart, String LCEnd, String LoadPort, String DiscPort, String VesselName
			,String RateType, String Rate, String Comment, String ReportDate, String Source, String VesselType, String ReportTimestamp, String emailUrl, String subjectNodePath, String textSentMailTime, String CargoType, String from_Source, String timestampDate,String timestampDateAndTime, String Flag, String flagPort, String flagPortOpen1, String reportcomesFrom, String data, String allReportJsonArrayInsideJSonobject, String Extra_Column){

		try {
			if( !GmailMethods.isNullString(VesselName) ){
			/*if( GmailMethods.isNullString(VesselName) || GmailMethods.isNullString(Charterer) || GmailMethods.isNullString(Status) || GmailMethods.isNullString(CargoGrade) || GmailMethods.isNullString(CargoQty) || GmailMethods.isNullString(LCStart) || GmailMethods.isNullString(Rate) || GmailMethods.isNullString(RateType)){
				//callCheckBlankNodeError(out, session, emailUrl, reportcomesFrom, data, allReportJsonArrayInsideJSonobject, timestampDateAndTime);
				//ErrorNodeReportwise.callAllDynamicReport(out, session, "2", emailUrl, subjectNodePath, textSentMailTime, from_Source, reportcomesFrom, allReportJsonArrayInsideJSonobject);
				ErrorNodeReportwise.callSpotError(out, session, Information, FixtureType, Charterer, Status, CargoQty, CargoGrade, LCStart, LCEnd, LoadPort, DiscPort, VesselName, RateType, Rate, Comment, ReportDate, Source, VesselType, ReportTimestamp, emailUrl, subjectNodePath, textSentMailTime, CargoType, from_Source, timestampDate, timestampDateAndTime, Flag, flagPort, flagPortOpen1, reportcomesFrom, data, allReportJsonArrayInsideJSonobject);
			}else{*/

				Node scorpio=null;
				Node Tonnage=null;
				Node ReportData=null;
				Node subNode=null;

				if(session.getRootNode().hasNode("scorpioDataBase")){
					scorpio = session.getRootNode().getNode("scorpioDataBase");
				}else{
					scorpio = session.getRootNode().addNode("scorpioDataBase");
					session.refresh(true);
					session.save();
				}

				if(scorpio.hasNode("ReportData")){
					ReportData = scorpio.getNode("ReportData");
				}else{
					ReportData = scorpio.addNode("ReportData");
					session.refresh(true);
					session.save();
				}

				if(ReportData.hasNode("Spot")){
					Tonnage=ReportData.getNode("Spot");
				}else{
					Tonnage=ReportData.addNode("Spot");
					Tonnage.setProperty("jcr:count", String.valueOf(0));
					Tonnage.setProperty("netChangedPointer", String.valueOf(0));
					session.refresh(true);
					session.save();
				}



				String count=Tonnage.getProperty("jcr:count").getString();
				String forid= String.valueOf(Integer.parseInt(count)+1);

				Calendar c = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
				c.setTime(sdf.parse(textSentMailTime));

				if(!Tonnage.hasNode(forid)){
					subNode=Tonnage.addNode(forid);

					Calendar cal = null;
					cal = Calendar.getInstance();
					Date minDate = new Date(cal.getTimeInMillis());

					Date date=GmailMethods.parseDate(minDate.toString());
					JSONObject dateJson=SlingMethods.formatDate_Month_Year_Day(date, out);

					int day=0;
					int month=0;
					int year=0;
					int hours=0;
					int Minute=0;
					String s="";
					if(dateJson.has("day")){
						day=dateJson.getInt("day");
						String dayString=String.valueOf(day);
						boolean b3 = Pattern.matches("(\\d{1}$)", dayString); 
						if(b3==true){
							s="0"+day;
						}else{
							s=String.valueOf(day);
						}

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

					String combinedatetimestamp=s+"-"+String.valueOf(month)+"-"+String.valueOf(year);

					subNode.setProperty("Sr.No", forid);
					subNode.setProperty("Id", forid);
					//				 subNode.setProperty("Information", Information);
					subNode.setProperty("Information", "2");
					subNode.setProperty("FixtureType", FixtureType);
					subNode.setProperty("Charterer", Charterer);
					subNode.setProperty("Status", Status);

					subNode.setProperty("CargoType", CargoType);
					subNode.setProperty("CargoGrade", CargoGrade);
					subNode.setProperty("CargoQty", CargoQty);
					if(!GmailMethods.isNullString(LCStart)){
						LCStart=new ConvertDate().convertDateFormat(LCStart);
					}else{
						LCStart="";
					}
					subNode.setProperty("LCStart", LCStart);
					if(!GmailMethods.isNullString(LCEnd)){
						LCEnd=new ConvertDate().convertDateFormat(LCEnd);
					}else{
						LCEnd="";
					}
					subNode.setProperty("LCEnd", LCEnd);
					subNode.setProperty("LoadPort", LoadPort);
					subNode.setProperty("DiscPort", DiscPort);
					subNode.setProperty("VesselName", VesselName);
					subNode.setProperty("RateType", RateType);
					subNode.setProperty("Rate", Rate);
					subNode.setProperty("Comments", Comment);
					subNode.setProperty("ReportDate", ReportDate);
					subNode.setProperty("Source", Source);
					subNode.setProperty("Vesseltype", VesselType);
					subNode.setProperty("dateAndTime", combinedatetimestamp);
					subNode.setProperty("ReportType", "2");
					subNode.setProperty("ReportTimestamp", c);
					subNode.setProperty("emailUrl", emailUrl);
					subNode.setProperty("emailClientNodeId", subjectNodePath);
					subNode.setProperty("textSentMailTime", textSentMailTime);
					subNode.setProperty("vesselFlag", Flag);
					subNode.setProperty("flagPort", flagPort);
					subNode.setProperty("reportcomesFrom", reportcomesFrom);
					subNode.setProperty("SingleJson",  allReportJsonArrayInsideJSonobject);

					/*if("pdf".equals(reportcomesFrom)){
					 subNode.setProperty("pdfJson", data);
				 }*/

					ChangedStructureCurrent_Methods CSC=new ChangedStructureCurrent_Methods();
					if(!GmailMethods.isNullString(from_Source)){
						from_Source=CSC.check_Source(session, from_Source,out);
					}else{
						from_Source="";
					}
					subNode.setProperty("from_Source", from_Source);
					subNode.setProperty("timestampDate", timestampDate);
					subNode.setProperty("timestampDateAndTime", timestampDateAndTime);
					subNode.setProperty("Extra_Column", Extra_Column);
					/*subNode.setProperty("excelUrl", "");
				 subNode.setProperty("Error", "");
				 subNode.setProperty("attachment", "");*/

					Tonnage.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
					//				 textNode.setProperty("Flag", "1");
					session.refresh(true);
					session.save();
				}
			}

		} catch (Exception e) {
//			out.println(e.getMessage());
			System.out.println(e.getMessage());
		}
	}

	public static  void callTimecharterReport(PrintWriter out, Session session, String Information, String Spot_TC, String Date, String VesselName
			, String CPP_DPP, String Charterer, String Delivery, String DeliveryPlace, String Period, String Periodunit, String Redelivery
			, String TCRate, String Rate, String Unit, String Comments, String TimeStamp, String Source, String Vesseltype, String emailUrl, String subjectNodePath, String textSentMailTime, String from_Source, String timestampDate,String timestampDateAndTime, String Flag, String flagPort, String flagPortOpen1,  String reportcomesFrom, String data, String allReportJsonArrayInsideJSonobject, String Extra_Column){

		try {

			if( !GmailMethods.isNullString(VesselName) ){
			/*if( GmailMethods.isNullString(VesselName) || GmailMethods.isNullString(Charterer) || GmailMethods.isNullString(Period) || GmailMethods.isNullString(Delivery) || GmailMethods.isNullString(Redelivery) || GmailMethods.isNullString(TCRate) ){
				//callCheckBlankNodeError(out, session, emailUrl, reportcomesFrom, data, allReportJsonArrayInsideJSonobject, timestampDateAndTime);
				//ErrorNodeReportwise.callAllDynamicReport(out, session, "3", emailUrl, subjectNodePath, textSentMailTime, from_Source, reportcomesFrom, allReportJsonArrayInsideJSonobject);
				ErrorNodeReportwise.callTimecharError( out,  session,  Information,  Spot_TC,  Date,  VesselName,  CPP_DPP,  Charterer,  Delivery,  DeliveryPlace,  Period,  Periodunit,  Redelivery,  TCRate,  Rate,  Unit,  Comments,  TimeStamp,  Source,  Vesseltype,  emailUrl,  subjectNodePath,  textSentMailTime,  from_Source,  timestampDate, timestampDateAndTime,  Flag,  flagPort,  flagPortOpen1, reportcomesFrom,  data,  allReportJsonArrayInsideJSonobject);
			
			}else{*/
				

				Node scorpio=null;
				Node TimeCharterReport=null;
				Node subNode=null;
				Node ReportData=null;
				Node TCReports=null;

				if(session.getRootNode().hasNode("scorpioDataBase")){
					scorpio = session.getRootNode().getNode("scorpioDataBase");
				}else{
					scorpio = session.getRootNode().addNode("scorpioDataBase");
					session.refresh(true);
					session.save();
				}

				if(scorpio.hasNode("ReportData")){
					ReportData = scorpio.getNode("ReportData");
				}else{
					ReportData = scorpio.addNode("ReportData");
					session.refresh(true);
					session.save();
				}

				if(ReportData.hasNode("TCReports")){
					TCReports = ReportData.getNode("TCReports");
				}else{
					TCReports = ReportData.addNode("TCReports");
					session.refresh(true);
					session.save();
				}

				if(TCReports.hasNode("TimeCharterReport")){
					TimeCharterReport=TCReports.getNode("TimeCharterReport");
				}else{
					TimeCharterReport=TCReports.addNode("TimeCharterReport");
					TimeCharterReport.setProperty("jcr:count", String.valueOf(0));
					TimeCharterReport.setProperty("netChangedPointer", String.valueOf(0));
					session.refresh(true);
					session.save();
				}

				String count=TimeCharterReport.getProperty("jcr:count").getString();
				String forid= String.valueOf(Integer.parseInt(count)+1);

				Calendar c = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
				c.setTime(sdf.parse(textSentMailTime));

				if(!TimeCharterReport.hasNode(forid)){
					subNode=TimeCharterReport.addNode(forid);

					Calendar cal = null;
					cal = Calendar.getInstance();
					Date minDate = new Date(cal.getTimeInMillis());
					Date date=GmailMethods.parseDate(minDate.toString());
					JSONObject dateJson=SlingMethods.formatDate_Month_Year_Day(date, out);

					int day=0;
					int month=0;
					int year=0;
					int hours=0;
					int Minute=0;
					String s="";
					if(dateJson.has("day")){
						day=dateJson.getInt("day");
						String dayString=String.valueOf(day);
						boolean b3 = Pattern.matches("(\\d{1}$)", dayString); 
						if(b3==true){
							s="0"+day;
						}else{
							s=String.valueOf(day);
						}

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

					String combinedatetimestamp=s+"-"+String.valueOf(month)+"-"+String.valueOf(year);

					subNode.setProperty("Sr.No", forid);
					subNode.setProperty("Id", forid);
					//				 subNode.setProperty("Information", Information);
					subNode.setProperty("Information", "2");
					subNode.setProperty("Spot_TC","5");
					subNode.setProperty("dateAndTime", combinedatetimestamp);
					subNode.setProperty("emailUrl", emailUrl);
					/*subNode.setProperty("excelUrl", "");
				 subNode.setProperty("Error", "");
				 subNode.setProperty("attachment", "");*/

					subNode.setProperty("Date", Date);
					subNode.setProperty("VesselName", VesselName);
					subNode.setProperty("CPP_DPP", CPP_DPP);
					subNode.setProperty("Charterer", Charterer);
					subNode.setProperty("Delivery", Delivery);
					subNode.setProperty("DeliveryPlace", DeliveryPlace);
					subNode.setProperty("Period", Period);
					subNode.setProperty("Periodunit", Periodunit);
					subNode.setProperty("Redelivery", Redelivery);
					subNode.setProperty("TCRate", TCRate);
					subNode.setProperty("Rate", Rate);
					subNode.setProperty("Unit", Unit);
					subNode.setProperty("Comments", Comments);
					subNode.setProperty("TimeStamp", c);
					subNode.setProperty("Source", Source);
					subNode.setProperty("Vesseltype", Vesseltype);
					subNode.setProperty("ReportType", "3");
					subNode.setProperty("ReportTimestamp", TimeStamp);
					subNode.setProperty("emailClientNodeId", subjectNodePath);
					subNode.setProperty("textSentMailTime", textSentMailTime);
					subNode.setProperty("vesselFlag", Flag);
					subNode.setProperty("reportcomesFrom", reportcomesFrom);
					//				 subNode.setProperty("flagPort", flagPort);
					subNode.setProperty("SingleJson",  allReportJsonArrayInsideJSonobject);
					subNode.setProperty("Extra_Column",  Extra_Column);
					/*if("pdf".equals(reportcomesFrom)){
					 subNode.setProperty("pdfJson", data);
				 }*/

					ChangedStructureCurrent_Methods CSC=new ChangedStructureCurrent_Methods();
					if(!GmailMethods.isNullString(from_Source)){
						from_Source=CSC.check_Source(session, from_Source,out);
					}else{
						from_Source="";
					}
					subNode.setProperty("from_Source", from_Source);
					subNode.setProperty("timestampDate", timestampDate);
					subNode.setProperty("timestampDateAndTime", timestampDateAndTime);

					TimeCharterReport.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
					//				 textNode.setProperty("Flag", "1");
					session.refresh(true);
					session.save();
				}
			}

		} catch (Exception e) {
//			out.println(e.getMessage());
			System.out.println(e.getMessage());
		}
	}

	public static void callBrokerTcRateReport(PrintWriter out, Session session, String Broker, String VesselType, String Month_Year, String first_yr,
			String second_yr, String third_yr, String fifth_yr, String ReportTimestamp, String emailUrl, String subjectNodePath, String textSentMailTime, String from_Source, String timestampDate,String timestampDateAndTime, String Flag, String flagPort, String flagPortOpen1, String reportcomesFrom, String data, String allReportJsonArrayInsideJSonobject, String Extra_Column){

		try {

			
			out.println("VesselType:: "+VesselType +"   ::::  "+"first_yr:: "+first_yr);
			if(   ( !GmailMethods.isNullString(VesselType) ) || ( !GmailMethods.isNullString(first_yr) )   ){
			/*if(    GmailMethods.isNullString(VesselType) ||  GmailMethods.isNullString(first_yr)  || GmailMethods.isNullString(Month_Year) || GmailMethods.isNullString(fifth_yr) || GmailMethods.isNullString(second_yr) ||  GmailMethods.isNullString(third_yr) ){
				  // callCheckBlankNodeError(out, session, emailUrl, reportcomesFrom, data, allReportJsonArrayInsideJSonobject, timestampDateAndTime);
				 //ErrorNodeReportwise.callAllDynamicReport(out, session, "4", emailUrl, subjectNodePath, textSentMailTime, from_Source, reportcomesFrom, allReportJsonArrayInsideJSonobject);
				ErrorNodeReportwise.callBrokerTcRateError(out, session, Broker, VesselType, Month_Year, first_yr, second_yr, third_yr, fifth_yr, ReportTimestamp, emailUrl, subjectNodePath, textSentMailTime, from_Source, timestampDate, timestampDateAndTime, Flag, flagPort, flagPortOpen1, reportcomesFrom, data, allReportJsonArrayInsideJSonobject);
			}else{*/
				
				Node scorpio=null;
				Node BrokerTCRate=null;
				Node subNode=null;
				Node TCReports=null;
				Node ReportData=null;

				if(session.getRootNode().hasNode("scorpioDataBase")){
					scorpio = session.getRootNode().getNode("scorpioDataBase");
				}else{
					scorpio = session.getRootNode().addNode("scorpioDataBase");
					session.save();
				}

				if(scorpio.hasNode("ReportData")){
					ReportData = scorpio.getNode("ReportData");
				}else{
					ReportData = scorpio.addNode("ReportData");
					session.refresh(true);
					session.save();
				}

				if(ReportData.hasNode("TCReports")){
					TCReports = ReportData.getNode("TCReports");
				}else{
					TCReports = ReportData.addNode("TCReports");
					session.refresh(true);
					session.save();
				}

				if(TCReports.hasNode("BrokerTCRate")){
					BrokerTCRate=TCReports.getNode("BrokerTCRate");
				}else{
					BrokerTCRate=TCReports.addNode("BrokerTCRate");
					BrokerTCRate.setProperty("jcr:count", String.valueOf(0));
					BrokerTCRate.setProperty("netChangedPointer", String.valueOf(0));
					session.refresh(true);
					session.save();
				}

				String count=BrokerTCRate.getProperty("jcr:count").getString();
				String forid= String.valueOf(Integer.parseInt(count)+1);
				Calendar c = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
				c.setTime(sdf.parse(textSentMailTime));

				if(!BrokerTCRate.hasNode(forid)){
					subNode=BrokerTCRate.addNode(forid);

					Calendar cal = null;
					cal = Calendar.getInstance();
					Date minDate = new Date(cal.getTimeInMillis());
					Date date=GmailMethods.parseDate(minDate.toString());
					JSONObject dateJson=SlingMethods.formatDate_Month_Year_Day(date, out);

					int day=0;
					int month=0;
					int year=0;
					int hours=0;
					int Minute=0;
					String s="";
					if(dateJson.has("day")){
						day=dateJson.getInt("day");
						String dayString=String.valueOf(day);
						boolean b3 = Pattern.matches("(\\d{1}$)", dayString); 
						if(b3==true){
							s="0"+day;
						}else{
							s=String.valueOf(day);
						}

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

					String combinedatetimestamp=s+"-"+String.valueOf(month)+"-"+String.valueOf(year);

					subNode.setProperty("Sr.No", forid);
					subNode.setProperty("Id", forid);
					subNode.setProperty("dateAndTime", combinedatetimestamp);
					subNode.setProperty("emailUrl", emailUrl);
					/*subNode.setProperty("excelUrl", "");
				 subNode.setProperty("Error", "");
				 subNode.setProperty("attachment", "");*/

					subNode.setProperty("Broker", Broker);
					subNode.setProperty("VesselType", VesselType);
					subNode.setProperty("Month_Year", Month_Year);
					subNode.setProperty("first_yr", first_yr);
					subNode.setProperty("second_yr", second_yr);
					subNode.setProperty("third_yr", third_yr);
					subNode.setProperty("fifth_yr", fifth_yr);
					subNode.setProperty("ReportType", "4");
					subNode.setProperty("ReportTimestamp", c);
					subNode.setProperty("emailClientNodeId", subjectNodePath);
					subNode.setProperty("textSentMailTime", textSentMailTime);
					//				 subNode.setProperty("vesselFlag", Flag);//flagPort
					subNode.setProperty("reportcomesFrom", reportcomesFrom);  //vinayaJson
					subNode.setProperty("SingleJson", allReportJsonArrayInsideJSonobject);
					subNode.setProperty("Extra_Column", Extra_Column);
					/*if("pdf".equals(reportcomesFrom)){
					 subNode.setProperty("pdfJson", data);
				 }*/


					ChangedStructureCurrent_Methods CSC=new ChangedStructureCurrent_Methods();
					if(!GmailMethods.isNullString(from_Source)){
						from_Source=CSC.check_Source(session, from_Source,out);
					}else{
						from_Source="";
					}

					subNode.setProperty("from_Source", from_Source);
					subNode.setProperty("timestampDate", timestampDate);
					subNode.setProperty("timestampDateAndTime", timestampDateAndTime);

					BrokerTCRate.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
					//				 textNode.setProperty("Flag", "1");
					session.refresh(true);
					session.save();
				}
			}


		} catch (Exception e) {
//			out.println(e.getMessage());
			System.out.println(e.getMessage());
		}
	}

	public void callOtherNodeError(PrintWriter out, Session session, String emailUrl,  String reportcomesFrom, String data, String allReportJsonArrayInsideJSonobject, String Error, String timestampDateAndTime){

		try {


			Node scorpio=null;
			Node subNode=null;
			Node TCReports=null;
			Node ReportData=null;

			if(session.getRootNode().hasNode("scorpioDataBase")){
				scorpio = session.getRootNode().getNode("scorpioDataBase");
			}else{
				scorpio = session.getRootNode().addNode("scorpioDataBase");
				//session.save();
			}

			if(scorpio.hasNode("ReportData")){
				ReportData = scorpio.getNode("ReportData");
			}else{
				ReportData = scorpio.addNode("ReportData");
				//session.save();
			}

			if(ReportData.hasNode("Other")){
				TCReports = ReportData.getNode("Other");
			}else{
				TCReports = ReportData.addNode("Other");
				TCReports.setProperty("jcr:count", String.valueOf(0));
				session.refresh(true);
				session.save();
			}

			String count=TCReports.getProperty("jcr:count").getString();
			String forid= String.valueOf(Integer.parseInt(count)+1);
			if(!TCReports.hasNode(forid)){
				subNode=TCReports.addNode(forid);
				subNode.setProperty("Id", forid);
				subNode.setProperty("emailUrl", emailUrl);
				subNode.setProperty("reportcomesFrom", reportcomesFrom); 
				subNode.setProperty("JsonArrayData", data); 
				subNode.setProperty("Jsonobj", allReportJsonArrayInsideJSonobject);
				subNode.setProperty("PallaviReturnError", Error); //
				subNode.setProperty("timestampDateAndTime", timestampDateAndTime);
				/*if("pdf".equals(reportcomesFrom)){
					 subNode.setProperty("pdfJson", data);
				 }*/

				TCReports.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
				session.refresh(true);
				session.save();
			}



		} catch (Exception e) {
//			out.println(e.getMessage());
			System.out.println(e.getMessage());
		}
	}

	public void callCheckBlankNodeError(PrintWriter out, Session session, String emailUrl,  String reportcomesFrom, String data, String allReportJsonArrayInsideJSonobject, String timestampDateAndTime){

		try {


			Node scorpio=null;
			Node subNode=null;
			Node TCReports=null;
			Node ReportData=null;

			if(session.getRootNode().hasNode("scorpioDataBase")){
				scorpio = session.getRootNode().getNode("scorpioDataBase");
			}else{
				scorpio = session.getRootNode().addNode("scorpioDataBase");
				//session.save();
			}

			if(scorpio.hasNode("ReportData")){
				ReportData = scorpio.getNode("ReportData");
			}else{
				ReportData = scorpio.addNode("ReportData");
				//session.save();
			}

			if(ReportData.hasNode("Other")){
				TCReports = ReportData.getNode("Other");
			}else{
				TCReports = ReportData.addNode("Other");
				TCReports.setProperty("jcr:count", String.valueOf(0));
				session.refresh(true);
				session.save();
			}

			String count=TCReports.getProperty("jcr:count").getString();
			String forid= String.valueOf(Integer.parseInt(count)+1);
			if(!TCReports.hasNode(forid)){
				subNode=TCReports.addNode(forid);
				subNode.setProperty("Id", forid);
				subNode.setProperty("emailUrl", emailUrl);
				subNode.setProperty("reportcomesFrom", reportcomesFrom); 
				/* subNode.setProperty("JsonArrayData", data); */
				subNode.setProperty("Jsonobj", allReportJsonArrayInsideJSonobject);
				subNode.setProperty("timestampDateAndTime", timestampDateAndTime);
				/* if("pdf".equals(reportcomesFrom)){
				 subNode.setProperty("pdfJson", data);
			 }*/

				TCReports.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
				session.refresh(true);
				session.save();
			}



		} catch (Exception e) {
//			out.println(e.getMessage());
			System.out.println(e.getMessage());
		}
	}



	public static JSONObject fetchTonnageData(PrintWriter out, Session session){

		JSONObject mainJsonobj=null;
		try {
			FetchData fd=new FetchData();
			Node scorpio = session.getRootNode().getNode("scorpioDataBase");
			Node ReportData=scorpio.getNode("ReportData");
			Node Tonnage=ReportData.getNode("Tonnage");

			if(Tonnage.hasNodes()){
				long size=Tonnage.getNodes().getSize();

				JSONObject keyNameObject=null;

				JSONArray keyNameObjectJsonarray=new JSONArray();

				NodeIterator itr=Tonnage.getNodes();
				String netChangedPointer="";
				String limit ="";
				boolean nodeFlag = false;
				if(Tonnage.hasProperty("netChangedPointer")){
					netChangedPointer=Tonnage.getProperty("netChangedPointer").getString();
					limit= String.valueOf(Integer.parseInt(netChangedPointer)+1);
				}
				int i=0;
				//			while(itr.hasNext()){
				while(Tonnage.hasNode(limit)){
					//			Node nextnode=itr.nextNode();
					nodeFlag = true;
					Node nextnode=Tonnage.getNode(limit);

					/*String nodeNamee=nextnode.getName();
			int nodeNameInt = Integer.valueOf(nodeNamee);
			int netChangedPointerId=Integer.valueOf(netChangedPointer);*/
					//			if(nodeNameInt>netChangedPointerId){

					//				nextnode=Tonnage.getNode(String.valueOf(nodeNameInt));


					int parseId=0;
					i++;
					String subNodeName=nextnode.getName();

					String EmploymentStatus="";
					String Owners="";
					String OpenPort="";
					String Operators="";
					String Id="";
					String CargoType="";
					String Comment="";
					String ETABasis="";
					String OpenDate="";
					String ReportType="";
					String ReportTimestamp="";
					String RepositionRegion="";
					String Source="";
					String VesselName="";
					String dateAndTime="";
					String Sr_No="";


					JSONArray EmploymentStatusJsonarray=new JSONArray();
					JSONArray OwnersJsonarray=new JSONArray();
					JSONArray OpenPortJsonarray=new JSONArray();
					JSONArray OperatorsJsonarray=new JSONArray();
					JSONArray CargoTypeJsonarray=new JSONArray();
					JSONArray CommentJsonarray=new JSONArray();
					JSONArray ETABasisJsonarray=new JSONArray();
					JSONArray OpenDateJsonarray=new JSONArray();
					JSONArray ReportTypeJsonarray=new JSONArray();
					JSONArray ReportTimestampJsonarray=new JSONArray();
					JSONArray RepositionRegionJsonarray=new JSONArray();
					JSONArray SourceJsonarray=new JSONArray();
					JSONArray VesselNameJsonarray=new JSONArray();
					JSONArray BuiltJsonarray=new JSONArray();
					JSONArray DWTJsonarray=new JSONArray();
					JSONArray CubicsJsonarray=new JSONArray();
					JSONArray LOAJsonarray=new JSONArray();
					JSONArray ICEJsonarray=new JSONArray();
					JSONArray SternLinejsonarray=new JSONArray();
					JSONArray VesselTypejsonarray=new JSONArray();


					if(nextnode.hasProperty("Id")){
						Id=nextnode.getProperty("Id").getString();
					}
					if(nextnode.hasProperty("Sr.No")){
						Sr_No=nextnode.getProperty("Sr.No").getString();
					}
					if(nextnode.hasProperty("dateAndTime")){
						dateAndTime=nextnode.getProperty("dateAndTime").getString();
					}
					if(nextnode.hasProperty("EmploymentStatus")){
						EmploymentStatus=nextnode.getProperty("EmploymentStatus").getString();
						EmploymentStatus=fd.fetch_EmployementStatus(session, EmploymentStatus);

						JSONObject EmploymentStatusJsonObject=new JSONObject();
						EmploymentStatusJsonObject.put("EmploymentStatus", EmploymentStatus);
						if(EmploymentStatusJsonObject.length()>0){
							EmploymentStatusJsonarray.put(EmploymentStatusJsonObject);
						}
					}
					if(nextnode.hasProperty("Owners")){
						Owners=nextnode.getProperty("Owners").getString();
						Owners=FetchData.fetch_Owners(session, Owners);

						JSONObject OwnersJsonObject=new JSONObject();
						OwnersJsonObject.put("Owners", Owners);
						if(OwnersJsonObject.length()>0){
							OwnersJsonarray.put(OwnersJsonObject);
						}
					}
					if(nextnode.hasProperty("OpenPort")){
						OpenPort=nextnode.getProperty("OpenPort").getString();
						OpenPort=fd.fetch_Port(session, OpenPort);

						JSONObject OpenPortJsonObject=new JSONObject();
						OpenPortJsonObject.put("OpenPort", OpenPort);
						if(OpenPortJsonObject.length()>0){
							OpenPortJsonarray.put(OpenPortJsonObject);
						}
					}
					if(nextnode.hasProperty("Operators")){
						Operators=nextnode.getProperty("Operators").getString();
						Operators=fd.fetch_Operators(session, Operators);

						JSONObject OperatorsJsonaobject=new JSONObject();
						OperatorsJsonaobject.put("Operators", Operators);
						if(OperatorsJsonaobject.length()>0){
							OperatorsJsonarray.put(OperatorsJsonaobject);
						}
					}
					if(nextnode.hasProperty("CargoType")){
						CargoType=nextnode.getProperty("CargoType").getString();
						CargoType=fd.fetch_CargoType(session, CargoType);

						JSONObject CargoTypeJsonaobject=new JSONObject();
						CargoTypeJsonaobject.put("CargoType", CargoType);
						if(CargoTypeJsonaobject.length()>0){
							CargoTypeJsonarray.put(CargoTypeJsonaobject);
						}
					}
					if(nextnode.hasProperty("Comment")){
						Comment=nextnode.getProperty("Comment").getString();

						JSONObject CommentJsonaobject=new JSONObject();
						CommentJsonaobject.put("Comment", Comment);
						if(CommentJsonaobject.length()>0){
							CommentJsonarray.put(CommentJsonaobject);
						}
					}
					if(nextnode.hasProperty("ETABasis")){
						ETABasis=nextnode.getProperty("ETABasis").getString();

						JSONObject ETABasisJsonaobject=new JSONObject();
						ETABasisJsonaobject.put("ETABasis", ETABasis);
						if(ETABasisJsonaobject.length()>0){
							ETABasisJsonarray.put(ETABasisJsonaobject);
						}
					}
					if(nextnode.hasProperty("OpenDate")){
						OpenDate=nextnode.getProperty("OpenDate").getString();

						JSONObject OpenDateJsonaobject=new JSONObject();
						OpenDateJsonaobject.put("OpenDate", OpenDate);
						if(OpenDateJsonaobject.length()>0){
							OpenDateJsonarray.put(OpenDateJsonaobject);
						}
					}
					if(nextnode.hasProperty("ReportType")){
						ReportType=nextnode.getProperty("ReportType").getString();
						ReportType=fd.fetch_ReportType(session, ReportType);

						JSONObject ReportTypeJsonaobject=new JSONObject();
						ReportTypeJsonaobject.put("ReportType", ReportType);
						if(ReportTypeJsonaobject.length()>0){
							ReportTypeJsonarray.put(ReportTypeJsonaobject);
						}
					}
					if(nextnode.hasProperty("ReportTimestamp")){
						ReportTimestamp=nextnode.getProperty("ReportTimestamp").getString();

						JSONObject ReportTimestampJsonaobject=new JSONObject();
						ReportTimestampJsonaobject.put("ReportTimestamp", ReportTimestamp);
						if(ReportTimestampJsonaobject.length()>0){
							ReportTimestampJsonarray.put(ReportTimestampJsonaobject);
						}
					}
					if(nextnode.hasProperty("RepositionRegion")){
						RepositionRegion=nextnode.getProperty("RepositionRegion").getString();
						RepositionRegion=fd.fetch_Port(session, RepositionRegion);

						JSONObject RepositionRegionJsonaobject=new JSONObject();
						RepositionRegionJsonaobject.put("RepositionRegion", RepositionRegion);
						if(RepositionRegionJsonaobject.length()>0){
							RepositionRegionJsonarray.put(RepositionRegionJsonaobject);
						}
					}
					if(nextnode.hasProperty("Source")){
						Source=nextnode.getProperty("Source").getString();
						Source=fd.fetch_Source(session, Source);

						JSONObject SourceJsonaobject=new JSONObject();
						SourceJsonaobject.put("Source", Source);
						if(SourceJsonaobject.length()>0){
							SourceJsonarray.put(SourceJsonaobject);
						}
					}
					if(nextnode.hasProperty("VesselName")){
						VesselName=nextnode.getProperty("VesselName").getString();
						VesselName=fd.fetch_vesselName(session, VesselName);

						JSONObject fetch_vesselNamejsonobject=new JSONObject(VesselName);
						if(fetch_vesselNamejsonobject.has("vessel_Id")){
							VesselName=fetch_vesselNamejsonobject.getString("vessel_Id");
						}

						JSONObject VesselNameJsonaobject=new JSONObject();
						VesselNameJsonaobject.put("VesselName", VesselName);
						if(VesselNameJsonaobject.length()>0){
							VesselNameJsonarray.put(VesselNameJsonaobject);
						}
					}

					if(nextnode.hasProperty("Built")){
						String Built="";
						Built=nextnode.getProperty("Built").getString();

						JSONObject BuiltJsonaobject=new JSONObject();
						BuiltJsonaobject.put("Built", Built);
						if(BuiltJsonaobject.length()>0){
							BuiltJsonarray.put(BuiltJsonaobject);
						}
					}

					if(nextnode.hasProperty("DWT")){
						String DWT="";
						DWT=nextnode.getProperty("DWT").getString();

						JSONObject DWTJsonaobject=new JSONObject();
						DWTJsonaobject.put("DWT", DWT);
						if(DWTJsonaobject.length()>0){
							DWTJsonarray.put(DWTJsonaobject);
						}
					}

					if(nextnode.hasProperty("Cubics")){
						String Cubics="";
						Cubics=nextnode.getProperty("Cubics").getString();

						JSONObject CubicsJsonaobject=new JSONObject();
						CubicsJsonaobject.put("Cubics", Cubics);
						if(CubicsJsonaobject.length()>0){
							CubicsJsonarray.put(CubicsJsonaobject);
						}
					}

					if(nextnode.hasProperty("LOA")){
						String LOA="";
						LOA=nextnode.getProperty("LOA").getString();

						JSONObject LOAJsonaobject=new JSONObject();
						LOAJsonaobject.put("LOA", LOA);
						if(LOAJsonaobject.length()>0){
							LOAJsonarray.put(LOAJsonaobject);
						}
					}


					if(nextnode.hasProperty("ICE")){
						String ICE="";
						ICE=nextnode.getProperty("ICE").getString();

						JSONObject ICEJsonaobject=new JSONObject();
						ICEJsonaobject.put("ICE", ICE);
						if(ICEJsonaobject.length()>0){
							ICEJsonarray.put(ICEJsonaobject);
						}
					}


					if(nextnode.hasProperty("SternLine")){
						String SternLine="";
						SternLine=nextnode.getProperty("SternLine").getString();

						JSONObject SternLineJsonaobject=new JSONObject();
						SternLineJsonaobject.put("SternLine", SternLine);
						if(SternLineJsonaobject.length()>0){
							SternLinejsonarray.put(SternLineJsonaobject);
						}
					}

					if(nextnode.hasProperty("VesselType")){
						String VesselType="";
						VesselType=nextnode.getProperty("VesselType").getString();
						VesselType=FetchData.fetch_vesselType(session, VesselType);

						JSONObject VesselTypeJsonaobject=new JSONObject();
						VesselTypeJsonaobject.put("VesselType", VesselType);
						if(VesselTypeJsonaobject.length()>0){
							VesselTypejsonarray.put(VesselTypeJsonaobject);
						}
					}

					keyNameObject=new JSONObject();

					keyNameObject.put("tonnageId", Id);
					keyNameObject.put("dateAndTime", dateAndTime);
					keyNameObject.put("EmploymentStatusJsonarray", EmploymentStatusJsonarray);
					keyNameObject.put("OwnersJsonarray", OwnersJsonarray);
					keyNameObject.put("OpenPortJsonarray", OpenPortJsonarray);
					keyNameObject.put("OperatorsJsonarray", OperatorsJsonarray);
					keyNameObject.put("CargoTypeJsonarray", CargoTypeJsonarray);
					keyNameObject.put("CommentJsonarray", CommentJsonarray);
					keyNameObject.put("ETABasisJsonarray", ETABasisJsonarray);
					keyNameObject.put("OpenDateJsonarray", OpenDateJsonarray);
					keyNameObject.put("ReportTypeJsonarray", ReportTypeJsonarray);
					keyNameObject.put("ReportTimestampJsonarray", ReportTimestampJsonarray);
					keyNameObject.put("RepositionRegionJsonarray", RepositionRegionJsonarray);
					keyNameObject.put("SourceJsonarray", SourceJsonarray);
					keyNameObject.put("VesselNameJsonarray", VesselNameJsonarray);
					keyNameObject.put("BuiltJsonarray", BuiltJsonarray);
					keyNameObject.put("DWTJsonarray", DWTJsonarray);
					keyNameObject.put("CubicsJsonarray", CubicsJsonarray);
					keyNameObject.put("LOAJsonarray", LOAJsonarray);
					keyNameObject.put("ICEJsonarray", ICEJsonarray);
					keyNameObject.put("SternLinejsonarray", SternLinejsonarray);
					keyNameObject.put("VesselTypejsonarray", VesselTypejsonarray);
					keyNameObject.put("subNodeName", subNodeName);
					keyNameObject.put("size", size+1);//parseId
					keyNameObject.put("parseId", parseId+1);
					keyNameObject.put("i", i);
					keyNameObject.put("Sr_No", Sr_No);

					if(keyNameObject.length()>0){
						keyNameObjectJsonarray.put(keyNameObject);
					}
					limit= String.valueOf(Integer.parseInt(limit)+1);

					//			}// netpointer
					/*else{
				keyNameObject=new JSONObject();
				keyNameObject.put("Error", "No New Data Available");
				keyNameObjectJsonarray.put(keyNameObject);
				break;
			}*/

				}// while close (netpointer)

				if(!nodeFlag){
					keyNameObject=new JSONObject();
					keyNameObject.put("Error", "No New Data Available");
					keyNameObject.put("ErrorId", "1");
					keyNameObjectJsonarray.put(keyNameObject);

				}

				if(keyNameObjectJsonarray.length()>0){
					mainJsonobj=new JSONObject();
					mainJsonobj.put("tonnageList", keyNameObjectJsonarray);

				}

				Tonnage.setProperty("netChangedPointer",String.valueOf(size));
				session.save();



			} // node check close if

		} catch (Exception e) {
//			out.println(e.getMessage());
			System.out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public static JSONObject fetchSpotData(PrintWriter out, Session session){

		JSONObject mainJsonobj=null;
		try {

			FetchData fd=new FetchData();
			Node scorpio = session.getRootNode().getNode("scorpioDataBase");
			Node ReportData=scorpio.getNode("ReportData");
			Node Spot=ReportData.getNode("Spot");

			if(Spot.hasNodes()){
				long size=Spot.getNodes().getSize(); 
				String Id="";

				JSONObject keyNameObject=null;
				JSONArray keyNameObjectJsonarray=new JSONArray();

				NodeIterator itr=Spot.getNodes();
				int i=0;

				String netChangedPointer="";
				String limit ="";
				boolean nodeFlag = false;
				if(Spot.hasProperty("netChangedPointer")){
					netChangedPointer=Spot.getProperty("netChangedPointer").getString();
					limit= String.valueOf(Integer.parseInt(netChangedPointer)+1);
				}

				//			while(itr.hasNext()){
				while(Spot.hasNode(limit)){
					//			Node nextnode=itr.nextNode();
					nodeFlag = true;
					Node nextnode=Spot.getNode(limit);

					int parseId=0;
					i++;
					String subNodeName=nextnode.getName();

					String Information="";
					String FixtureType="";
					String Charterer="";
					String Status="";
					String CargoType="";
					String CargoGrade="";
					String CargoQty="";
					String LCStart="";
					String Source="";
					String VesselName="";
					String LCEnd="";
					String LoadPort="";
					String DiscPort="";
					String RateType="";
					String Rate="";
					String Comments="";
					String dateAndTime="";
					String Sr_No="";

					JSONArray InformationJsonarray=new JSONArray();
					JSONArray FixtureTypeJsonarray=new JSONArray();
					JSONArray ChartererJsonarray=new JSONArray();
					JSONArray StatusJsonarray=new JSONArray();
					JSONArray CargoTypeJsonarray=new JSONArray();
					JSONArray CargoGradeJsonarray=new JSONArray();
					JSONArray CargoQtyJsonarray=new JSONArray();
					JSONArray SourceJsonarray=new JSONArray();
					JSONArray VesselNameJsonarray=new JSONArray();
					JSONArray LCStartJsonarray=new JSONArray();
					JSONArray LCEndJsonarray=new JSONArray();
					JSONArray LoadPortJsonarray=new JSONArray();
					JSONArray DiscPortJsonarray=new JSONArray();
					JSONArray RateTypeJsonarray=new JSONArray();
					JSONArray RateJsonarray=new JSONArray();
					JSONArray CommentsJsonarray=new JSONArray();
					JSONArray ReportDateJsonarray=new JSONArray();
					JSONArray VesseltypeJsonarray=new JSONArray();


					if(nextnode.hasProperty("Id")){
						Id=nextnode.getProperty("Id").getString();
					}
					if(nextnode.hasProperty("Sr.No")){
						Sr_No=nextnode.getProperty("Sr.No").getString();
					}
					if(nextnode.hasProperty("dateAndTime")){
						dateAndTime=nextnode.getProperty("dateAndTime").getString();
					}
					if(nextnode.hasProperty("Information")){
						Information=nextnode.getProperty("Information").getString();
						Information=fd.fetch_Information(session, Information);

						JSONObject InformationJsonObject=new JSONObject();
						InformationJsonObject.put("Information", Information);
						if(InformationJsonObject.length()>0){
							InformationJsonarray.put(InformationJsonObject);
						}
					}
					if(nextnode.hasProperty("FixtureType")){
						FixtureType=nextnode.getProperty("FixtureType").getString();
						FixtureType=fd.fetch_ReportType(session, FixtureType);

						JSONObject FixtureTypeJsonObject=new JSONObject();
						FixtureTypeJsonObject.put("FixtureType", FixtureType);
						if(FixtureTypeJsonObject.length()>0){
							FixtureTypeJsonarray.put(FixtureTypeJsonObject);
						}
					}
					if(nextnode.hasProperty("Charterer")){
						Charterer=nextnode.getProperty("Charterer").getString();
						Charterer=fd.fetch_Charterer(session, Charterer);

						JSONObject ChartererJsonObject=new JSONObject();
						ChartererJsonObject.put("Charterer", Charterer);
						if(ChartererJsonObject.length()>0){
							ChartererJsonarray.put(ChartererJsonObject);
						}
					}
					if(nextnode.hasProperty("Status")){
						Status=nextnode.getProperty("Status").getString();
						Status=fd.fetch_EmployementStatus(session, Status);

						JSONObject StatusJsonobject=new JSONObject();
						StatusJsonobject.put("Status", Status);
						if(StatusJsonobject.length()>0){
							StatusJsonarray.put(StatusJsonobject);
						}
					}
					if(nextnode.hasProperty("CargoType")){
						CargoType=nextnode.getProperty("CargoType").getString();
						CargoType=fd.fetch_CargoType(session, CargoType);

						JSONObject CargoTypeJsonaobject=new JSONObject();
						CargoTypeJsonaobject.put("CargoType", CargoType);
						if(CargoTypeJsonaobject.length()>0){
							CargoTypeJsonarray.put(CargoTypeJsonaobject);
						}
					}
					if(nextnode.hasProperty("CargoGrade")){
						CargoGrade=nextnode.getProperty("CargoGrade").getString();
						CargoGrade=fd.fetch_CargoGrade(session, CargoGrade);

						JSONObject CargoGradeJsonobject=new JSONObject();
						CargoGradeJsonobject.put("CargoGrade", CargoGrade);
						if(CargoGradeJsonobject.length()>0){
							CargoGradeJsonarray.put(CargoGradeJsonobject);
						}
					}
					if(nextnode.hasProperty("CargoQty")){
						CargoQty=nextnode.getProperty("CargoQty").getString();

						JSONObject CargoQtyJsonaobject=new JSONObject();
						CargoQtyJsonaobject.put("CargoQty", CargoQty);
						if(CargoQtyJsonaobject.length()>0){
							CargoQtyJsonarray.put(CargoQtyJsonaobject);
						}
					}
					if(nextnode.hasProperty("LCStart")){
						LCStart=nextnode.getProperty("LCStart").getString();

						JSONObject LCStartJsonaobject=new JSONObject();
						LCStartJsonaobject.put("LCStart", LCStart);
						if(LCStartJsonaobject.length()>0){
							LCStartJsonarray.put(LCStartJsonaobject);
						}
					}
					if(nextnode.hasProperty("LCEnd")){
						LCEnd=nextnode.getProperty("LCEnd").getString();

						JSONObject LCEndJsonaobject=new JSONObject();
						LCEndJsonaobject.put("LCEnd", LCEnd);
						if(LCEndJsonaobject.length()>0){
							LCEndJsonarray.put(LCEndJsonaobject);
						}
					}
					if(nextnode.hasProperty("LoadPort")){
						LoadPort=nextnode.getProperty("LoadPort").getString();
						LoadPort=fd.fetch_Port(session, LoadPort);

						JSONObject LoadPortJsonaobject=new JSONObject();
						LoadPortJsonaobject.put("LoadPort", LoadPort);
						if(LoadPortJsonaobject.length()>0){
							LoadPortJsonarray.put(LoadPortJsonaobject);
						}
					}
					if(nextnode.hasProperty("DiscPort")){
						DiscPort=nextnode.getProperty("DiscPort").getString();
						DiscPort=fd.fetch_Port(session, DiscPort);

						JSONObject DiscPortJsonaobject=new JSONObject();
						DiscPortJsonaobject.put("DiscPort", DiscPort);
						if(DiscPortJsonaobject.length()>0){
							DiscPortJsonarray.put(DiscPortJsonaobject);
						}
					}
					if(nextnode.hasProperty("VesselName")){
						VesselName=nextnode.getProperty("VesselName").getString();

						VesselName=fd.fetch_vesselName(session, VesselName);
						JSONObject fetch_vesselNamejsonobject=new JSONObject(VesselName);
						if(fetch_vesselNamejsonobject.has("vessel_Id")){
							VesselName=fetch_vesselNamejsonobject.getString("vessel_Id");
						}

						JSONObject VesselNameJsonaobject=new JSONObject();
						VesselNameJsonaobject.put("VesselName", VesselName);
						if(VesselNameJsonaobject.length()>0){
							VesselNameJsonarray.put(VesselNameJsonaobject);
						}
					}
					if(nextnode.hasProperty("RateType")){
						RateType=nextnode.getProperty("RateType").getString();
						RateType=fd.fetch_RateType(session, RateType);

						JSONObject RateTypeJsonaobject=new JSONObject();
						RateTypeJsonaobject.put("RateType", RateType);
						if(RateTypeJsonaobject.length()>0){
							RateTypeJsonarray.put(RateTypeJsonaobject);
						}
					}
					if(nextnode.hasProperty("Rate")){
						Rate=nextnode.getProperty("Rate").getString();

						JSONObject RateJsonaobject=new JSONObject();
						RateJsonaobject.put("Rate", Rate);
						if(RateJsonaobject.length()>0){
							RateJsonarray.put(RateJsonaobject);
						}
					}
					if(nextnode.hasProperty("Comments")){
						Comments=nextnode.getProperty("Comments").getString();

						JSONObject CommentsJsonaobject=new JSONObject();
						CommentsJsonaobject.put("Comments", Comments);
						if(CommentsJsonaobject.length()>0){
							CommentsJsonarray.put(CommentsJsonaobject);
						}
					}
					if(nextnode.hasProperty("ReportDate")){
						String ReportDate="";
						ReportDate=nextnode.getProperty("ReportDate").getString();

						JSONObject ReportDateJsonaobject=new JSONObject();
						ReportDateJsonaobject.put("ReportDate", ReportDate);
						if(ReportDateJsonaobject.length()>0){
							ReportDateJsonarray.put(ReportDateJsonaobject);
						}
					}
					if(nextnode.hasProperty("Source")){
						Source=nextnode.getProperty("Source").getString();
						Source=fd.fetch_Source(session, Source);

						JSONObject SourceJsonaobject=new JSONObject();
						SourceJsonaobject.put("Source", Source);
						if(SourceJsonaobject.length()>0){
							SourceJsonarray.put(SourceJsonaobject);
						}
					}
					if(nextnode.hasProperty("Vesseltype")){
						String Vesseltype="";
						Vesseltype=nextnode.getProperty("Vesseltype").getString();
						Vesseltype=FetchData.fetch_vesselType(session, Vesseltype);

						JSONObject VesseltypeJsonaobject=new JSONObject();
						VesseltypeJsonaobject.put("Vesseltype", Vesseltype);
						if(VesseltypeJsonaobject.length()>0){
							VesseltypeJsonarray.put(VesseltypeJsonaobject);
						}
					}

					keyNameObject =new JSONObject();

					keyNameObject.put("spotId", Id);
					keyNameObject.put("dateAndTime", dateAndTime);
					keyNameObject.put("InformationJsonarray", InformationJsonarray);
					keyNameObject.put("FixtureTypeJsonarray", FixtureTypeJsonarray);
					keyNameObject.put("LCStartJsonarray", LCStartJsonarray);
					keyNameObject.put("LCEndJsonarray", LCEndJsonarray);
					keyNameObject.put("LoadPortJsonarray", LoadPortJsonarray);
					keyNameObject.put("DiscPortJsonarray", DiscPortJsonarray);
					keyNameObject.put("VesselNameJsonarray", VesselNameJsonarray);
					keyNameObject.put("ChartererJsonarray", ChartererJsonarray);
					keyNameObject.put("StatusJsonarray", StatusJsonarray);
					keyNameObject.put("CargoTypeJsonarray", CargoTypeJsonarray);
					keyNameObject.put("CargoGradeJsonarray", CargoGradeJsonarray);
					keyNameObject.put("CargoQtyJsonarray", CargoQtyJsonarray);
					keyNameObject.put("RateTypeJsonarray", RateTypeJsonarray);
					keyNameObject.put("RateJsonarray", RateJsonarray);
					keyNameObject.put("CommentsJsonarray", CommentsJsonarray);
					keyNameObject.put("ReportDateJsonarray", ReportDateJsonarray);
					keyNameObject.put("SourceJsonarray", SourceJsonarray);
					keyNameObject.put("VesseltypeJsonarray", VesseltypeJsonarray);
					keyNameObject.put("subNodeName", subNodeName);
					keyNameObject.put("size", size+1);//parseId
					keyNameObject.put("parseId", parseId+1);
					keyNameObject.put("i", i);
					keyNameObject.put("Sr_No", Sr_No);

					if(keyNameObject.length()>0){
						keyNameObjectJsonarray.put(keyNameObject);
					}

					limit= String.valueOf(Integer.parseInt(limit)+1);


				}// while close (netpointer)

				if(!nodeFlag){
					keyNameObject=new JSONObject();
					keyNameObject.put("Error", "No New Data Available");
					keyNameObject.put("ErrorId", "0");
					keyNameObjectJsonarray.put(keyNameObject);

				}

				if(keyNameObjectJsonarray.length()>0){
					mainJsonobj=new JSONObject();
					mainJsonobj.put("spotList", keyNameObjectJsonarray);

				}

				Spot.setProperty("netChangedPointer",String.valueOf(size));
				session.save();


			} // node check close if

		} catch (Exception e) {
//			out.println(e.getMessage());
			System.out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public static JSONObject fetchTimeCharterReportsData(PrintWriter out, Session session){

		JSONObject mainJsonobj=null;
		try {
			FetchData fd=new FetchData();
			Node scorpio = session.getRootNode().getNode("scorpioDataBase");
			Node ReportData=scorpio.getNode("ReportData");
			Node TCReports=ReportData.getNode("TCReports");
			Node TimeCharterReports=TCReports.getNode("TimeCharterReport");

			if(TimeCharterReports.hasNodes()){
				long size=TimeCharterReports.getNodes().getSize(); 
				String Id="";

				JSONObject keyNameObject=null;
				JSONArray keyNameObjectJsonarray=new JSONArray();

				NodeIterator itr=TimeCharterReports.getNodes();
				int i=0;

				String netChangedPointer="";
				String limit ="";
				boolean nodeFlag = false;
				if(TimeCharterReports.hasProperty("netChangedPointer")){
					netChangedPointer=TimeCharterReports.getProperty("netChangedPointer").getString();
					limit= String.valueOf(Integer.parseInt(netChangedPointer)+1);
				}

				//			while(itr.hasNext()){
				while(TimeCharterReports.hasNode(limit)){
					//			Node nextnode=itr.nextNode();
					nodeFlag = true;
					Node nextnode=TimeCharterReports.getNode(limit);

					i++;
					int parseId=0;
					String subNodeName=nextnode.getName();

					String Information="";
					String Charterer="";
					String Source="";
					String VesselName="";
					String Comments="";
					String Rate="";
					String dateAndTime="";
					String Sr_No="";

					JSONArray InformationJsonarray=new JSONArray();
					JSONArray Spot_TCJsonarray=new JSONArray();
					JSONArray DateJsonarray=new JSONArray();
					JSONArray CPP_DPPJsonarray=new JSONArray();
					JSONArray VesselNameJsonarray=new JSONArray();
					JSONArray ChartererJsonarray= new JSONArray();
					JSONArray DeliveryJsonarray=new JSONArray();
					JSONArray DeliveryPlaceJsonarray=new JSONArray();
					JSONArray SourceJsonarray=new JSONArray();

					JSONArray RateJsonarray=new JSONArray();
					JSONArray CommentsJsonarray=new JSONArray();
					JSONArray PeriodJsonarray=new JSONArray();
					JSONArray VesseltypeJsonarray=new JSONArray();
					JSONArray RedeliveryJsonarray=new JSONArray();
					JSONArray TCRateJsonarray=new JSONArray();
					JSONArray TimeStampJsonarray=new JSONArray();
					JSONArray PeriodunitJsonarray=new JSONArray();
					JSONArray UnitJsonarray=new JSONArray();


					if(nextnode.hasProperty("Id")){
						Id=nextnode.getProperty("Id").getString();
					}
					if(nextnode.hasProperty("Sr.No")){
						Sr_No=nextnode.getProperty("Sr.No").getString();
					}
					if(nextnode.hasProperty("dateAndTime")){
						dateAndTime=nextnode.getProperty("dateAndTime").getString();
					}
					if(nextnode.hasProperty("Information")){
						Information=nextnode.getProperty("Information").getString();
						Information=fd.fetch_Information(session, Information);

						JSONObject InformationJsonObject=new JSONObject();
						InformationJsonObject.put("Information", Information);
						if(InformationJsonObject.length()>0){
							InformationJsonarray.put(InformationJsonObject);
						}
					}
					if(nextnode.hasProperty("Spot_TC")){
						String Spot_TC="";
						Spot_TC=nextnode.getProperty("Spot_TC").getString();
						Spot_TC=fd.fetch_ReportType(session, Spot_TC);

						JSONObject Spot_TCJsonObject=new JSONObject();
						Spot_TCJsonObject.put("Spot_TC", Spot_TC);
						if(Spot_TCJsonObject.length()>0){
							Spot_TCJsonarray.put(Spot_TCJsonObject);
						}
					}
					if(nextnode.hasProperty("Date")){
						String Date="";
						Date=nextnode.getProperty("Date").getString();

						JSONObject DateJsonObject=new JSONObject();
						DateJsonObject.put("Date", Date);
						if(DateJsonObject.length()>0){
							DateJsonarray.put(DateJsonObject);
						}
					}

					if(nextnode.hasProperty("VesselName")){
						VesselName=nextnode.getProperty("VesselName").getString();

						VesselName=fd.fetch_vesselName(session, VesselName);
						JSONObject fetch_vesselNamejsonobject=new JSONObject(VesselName);
						if(fetch_vesselNamejsonobject.has("vessel_Id")){
							VesselName=fetch_vesselNamejsonobject.getString("vessel_Id");
						}

						JSONObject VesselNameJsonaobject=new JSONObject();
						VesselNameJsonaobject.put("VesselName", VesselName);
						if(VesselNameJsonaobject.length()>0){
							VesselNameJsonarray.put(VesselNameJsonaobject);
						}
					}


					if(nextnode.hasProperty("CPP_DPP")){
						String CPP_DPP="";
						CPP_DPP=nextnode.getProperty("CPP_DPP").getString();
						CPP_DPP=fd.fetch_CargoType(session, CPP_DPP);

						JSONObject CPP_DPPJsonobject=new JSONObject();
						CPP_DPPJsonobject.put("CPP_DPP", CPP_DPP);
						if(CPP_DPPJsonobject.length()>0){
							CPP_DPPJsonarray.put(CPP_DPPJsonobject);
						}
					}
					if(nextnode.hasProperty("Charterer")){
						Charterer=nextnode.getProperty("Charterer").getString();
						Charterer=fd.fetch_Charterer(session, Charterer);

						JSONObject ChartererJsonaobject=new JSONObject();
						ChartererJsonaobject.put("Charterer", Charterer);
						if(ChartererJsonaobject.length()>0){
							ChartererJsonarray.put(ChartererJsonaobject);
						}
					}
					if(nextnode.hasProperty("Delivery")){
						String Delivery="";
						Delivery=nextnode.getProperty("Delivery").getString();

						JSONObject DeliveryJsonobject=new JSONObject();
						DeliveryJsonobject.put("Delivery", Delivery);
						if(DeliveryJsonobject.length()>0){
							DeliveryJsonarray.put(DeliveryJsonobject);
						}
					}
					if(nextnode.hasProperty("DeliveryPlace")){
						String DeliveryPlace="";
						DeliveryPlace=nextnode.getProperty("DeliveryPlace").getString();
						DeliveryPlace=fd.fetch_DeliveryPlace(session, DeliveryPlace);

						JSONObject DeliveryPlaceJsonaobject=new JSONObject();
						DeliveryPlaceJsonaobject.put("DeliveryPlace", DeliveryPlace);
						if(DeliveryPlaceJsonaobject.length()>0){
							DeliveryPlaceJsonarray.put(DeliveryPlaceJsonaobject);
						}
					}
					if(nextnode.hasProperty("Period")){
						String Period="";
						Period=nextnode.getProperty("Period").getString();

						JSONObject PeriodJsonaobject=new JSONObject();
						PeriodJsonaobject.put("Period", Period);
						if(PeriodJsonaobject.length()>0){
							PeriodJsonarray.put(PeriodJsonaobject);
						}
					}
					if(nextnode.hasProperty("Periodunit")){
						String Periodunit="";
						Periodunit=nextnode.getProperty("Periodunit").getString();
						Periodunit=fd.fetch_Periodunit(session, Periodunit);

						JSONObject PeriodunitJsonaobject=new JSONObject();
						PeriodunitJsonaobject.put("Periodunit", Periodunit);
						if(PeriodunitJsonaobject.length()>0){
							PeriodunitJsonarray.put(PeriodunitJsonaobject);
						}
					}
					if(nextnode.hasProperty("Redelivery")){
						String Redelivery="";
						Redelivery=nextnode.getProperty("Redelivery").getString();

						JSONObject RedeliveryJsonaobject=new JSONObject();
						RedeliveryJsonaobject.put("Redelivery", Redelivery);
						if(RedeliveryJsonaobject.length()>0){
							RedeliveryJsonarray.put(RedeliveryJsonaobject);
						}
					}
					if(nextnode.hasProperty("TCRate")){
						String TCRate="";
						TCRate=nextnode.getProperty("TCRate").getString();

						JSONObject TCRateJsonaobject=new JSONObject();
						TCRateJsonaobject.put("TCRate", TCRate);
						if(TCRateJsonaobject.length()>0){
							TCRateJsonarray.put(TCRateJsonaobject);
						}
					}

					if(nextnode.hasProperty("Rate")){
						Rate=nextnode.getProperty("Rate").getString();

						JSONObject RateJsonaobject=new JSONObject();
						RateJsonaobject.put("Rate", Rate);
						if(RateJsonaobject.length()>0){
							RateJsonarray.put(RateJsonaobject);
						}
					}

					if(nextnode.hasProperty("Unit")){
						String Unit="";
						Unit=nextnode.getProperty("Unit").getString();
						Unit=fd.fetch_currencyUnit(session, Unit);

						JSONObject UnitJsonaobject=new JSONObject();
						UnitJsonaobject.put("Unit", Unit);
						if(UnitJsonaobject.length()>0){
							UnitJsonarray.put(UnitJsonaobject);
						}
					}

					if(nextnode.hasProperty("Comments")){
						Comments=nextnode.getProperty("Comments").getString();

						JSONObject CommentsJsonaobject=new JSONObject();
						CommentsJsonaobject.put("Comments", Comments);
						if(CommentsJsonaobject.length()>0){
							CommentsJsonarray.put(CommentsJsonaobject);
						}
					}

					if(nextnode.hasProperty("Source")){
						Source=nextnode.getProperty("Source").getString();
						Source=fd.fetch_Source(session, Source);

						JSONObject SourceJsonaobject=new JSONObject();
						SourceJsonaobject.put("Source", Source);
						if(SourceJsonaobject.length()>0){
							SourceJsonarray.put(SourceJsonaobject);
						}
					}

					if(nextnode.hasProperty("Vesseltype")){
						String Vesseltype="";
						Vesseltype=nextnode.getProperty("Vesseltype").getString();
						Vesseltype=FetchData.fetch_vesselType(session, Vesseltype);

						JSONObject VesseltypeJsonaobject=new JSONObject();
						VesseltypeJsonaobject.put("Vesseltype", Vesseltype);
						if(VesseltypeJsonaobject.length()>0){
							VesseltypeJsonarray.put(VesseltypeJsonaobject);
						}
					}

					if(nextnode.hasProperty("TimeStamp")){
						String TimeStamp="";
						TimeStamp=nextnode.getProperty("TimeStamp").getString();

						JSONObject TimeStampJsonaobject=new JSONObject();
						TimeStampJsonaobject.put("TimeStamp", TimeStamp);
						if(TimeStampJsonaobject.length()>0){
							TimeStampJsonarray.put(TimeStampJsonaobject);
						}
					}

					keyNameObject =new JSONObject();

					keyNameObject.put("TimeCharterReportsId", Id);
					keyNameObject.put("dateAndTime", dateAndTime);
					keyNameObject.put("InformationJsonarray", InformationJsonarray);
					keyNameObject.put("Spot_TCJsonarray", Spot_TCJsonarray);
					keyNameObject.put("DateJsonarray", DateJsonarray);
					keyNameObject.put("VesselNameJsonarray", VesselNameJsonarray);
					keyNameObject.put("CPP_DPPJsonarray", CPP_DPPJsonarray);
					keyNameObject.put("ChartererJsonarray", ChartererJsonarray);
					keyNameObject.put("DeliveryJsonarray", DeliveryJsonarray);
					keyNameObject.put("DeliveryPlaceJsonarray", DeliveryPlaceJsonarray);
					keyNameObject.put("PeriodJsonarray", PeriodJsonarray);
					keyNameObject.put("PeriodunitJsonarray", PeriodunitJsonarray);
					keyNameObject.put("RedeliveryJsonarray", RedeliveryJsonarray);
					keyNameObject.put("TCRateJsonarray", TCRateJsonarray);
					keyNameObject.put("RateJsonarray", RateJsonarray);
					keyNameObject.put("UnitJsonarray", UnitJsonarray);
					keyNameObject.put("CommentsJsonarray", CommentsJsonarray);
					keyNameObject.put("SourceJsonarray", SourceJsonarray);
					keyNameObject.put("VesseltypeJsonarray", VesseltypeJsonarray);
					keyNameObject.put("TimeStampJsonarray", TimeStampJsonarray);
					keyNameObject.put("subNodeName", subNodeName);
					keyNameObject.put("size", size+1);//parseId
					keyNameObject.put("parseId", parseId+1);
					keyNameObject.put("i", i);
					keyNameObject.put("Sr_No", Sr_No);

					if(keyNameObject.length()>0){
						keyNameObjectJsonarray.put(keyNameObject);
					}

					limit= String.valueOf(Integer.parseInt(limit)+1);	


				}// while close 

				if(!nodeFlag){
					keyNameObject=new JSONObject();
					keyNameObject.put("Error", "No New Data Available");
					keyNameObjectJsonarray.put(keyNameObject);

				}

				if(keyNameObjectJsonarray.length()>0){
					mainJsonobj=new JSONObject();
					mainJsonobj.put("TimeCharterReportsList", keyNameObjectJsonarray);

					//				out.println(mainJsonobj);
				}

				TimeCharterReports.setProperty("netChangedPointer",String.valueOf(size));
				session.save();


			} // node check close if

		} catch (Exception e) {
//			out.println(e.getMessage());
			System.out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public static JSONObject fetchBrokerTcRateData(PrintWriter out, Session session){

		JSONObject mainJsonobj=null;
		try {
			FetchData fd=new FetchData();
			Node scorpio = session.getRootNode().getNode("scorpioDataBase");
			Node ReportData=scorpio.getNode("ReportData");
			Node TCReports=ReportData.getNode("TCReports");
			Node TimeCharterReports=TCReports.getNode("BrokerTCRate");

			if(TimeCharterReports.hasNodes()){

				JSONObject keyNameObject=null;
				long size=TimeCharterReports.getNodes().getSize();
				JSONArray keyNameObjectJsonarray=new JSONArray();

				NodeIterator itr=TimeCharterReports.getNodes();
				int i=0;

				String netChangedPointer="";
				String limit ="";
				boolean nodeFlag = false;
				if(TimeCharterReports.hasProperty("netChangedPointer")){
					netChangedPointer=TimeCharterReports.getProperty("netChangedPointer").getString();
					limit= String.valueOf(Integer.parseInt(netChangedPointer)+1);
				}

				//			while(itr.hasNext()){
				while(TimeCharterReports.hasNode(limit)){
					//			Node nextnode=itr.nextNode();

					nodeFlag = true;
					Node nextnode=TimeCharterReports.getNode(limit);

					String Id="";
					int parseId=0;
					i++;

					String dateAndTime="";
					String Sr_No="";

					JSONArray BrokerJsonarray=new JSONArray();
					JSONArray VesselTypeJsonarray=new JSONArray();
					JSONArray Month_YearJsonarray=new JSONArray();
					JSONArray first_yrJsonarray=new JSONArray();
					JSONArray second_yrJsonarray=new JSONArray();
					JSONArray third_yrJsonarray= new JSONArray();
					JSONArray fifth_yrJsonarray= new JSONArray();

					String subNodeName=nextnode.getName();

					if(nextnode.hasProperty("Id")){

						Id=nextnode.getProperty("Id").getString();
						parseId=Integer.parseInt(Id);
					}
					if(nextnode.hasProperty("Sr.No")){
						Sr_No=nextnode.getProperty("Sr.No").getString();
					}
					if(nextnode.hasProperty("dateAndTime")){
						dateAndTime=nextnode.getProperty("dateAndTime").getString();
					}
					if(nextnode.hasProperty("Broker")){
						String Broker="";
						Broker=nextnode.getProperty("Broker").getString();
						Broker=fd.fetch_Source(session, Broker);

						JSONObject BrokerJsonObject=new JSONObject();
						BrokerJsonObject.put("Broker", Broker);
						if(BrokerJsonObject.length()>0){
							BrokerJsonarray.put(BrokerJsonObject);
						}
					}
					if(nextnode.hasProperty("VesselType")){
						String VesselType="";
						VesselType=nextnode.getProperty("VesselType").getString();
						VesselType=FetchData.fetch_vesselType(session, VesselType);

						JSONObject VesselTypeJsonObject=new JSONObject();
						VesselTypeJsonObject.put("VesselType", VesselType);
						if(VesselTypeJsonObject.length()>0){
							VesselTypeJsonarray.put(VesselTypeJsonObject);
						}
					}
					if(nextnode.hasProperty("Month_Year")){
						String Month_Year="";
						Month_Year=nextnode.getProperty("Month_Year").getString();

						JSONObject Month_YearJsonObject=new JSONObject();
						Month_YearJsonObject.put("Month_Year", Month_Year);
						if(Month_YearJsonObject.length()>0){
							Month_YearJsonarray.put(Month_YearJsonObject);
						}
					}

					if(nextnode.hasProperty("first_yr")){
						String first_yr="";
						first_yr=nextnode.getProperty("first_yr").getString();

						JSONObject first_yrJsonaobject=new JSONObject();
						first_yrJsonaobject.put("first_yr", first_yr);
						if(first_yrJsonaobject.length()>0){
							first_yrJsonarray.put(first_yrJsonaobject);
						}
					}


					if(nextnode.hasProperty("second_yr")){
						String second_yr="";
						second_yr=nextnode.getProperty("second_yr").getString();

						JSONObject second_yrJsonobject=new JSONObject();
						second_yrJsonobject.put("second_yr", second_yr);
						if(second_yrJsonobject.length()>0){
							second_yrJsonarray.put(second_yrJsonobject);
						}
					}
					if(nextnode.hasProperty("third_yr")){
						String third_yr="";
						third_yr=nextnode.getProperty("third_yr").getString();

						JSONObject third_yrJsonaobject=new JSONObject();
						third_yrJsonaobject.put("third_yr", third_yr);
						if(third_yrJsonaobject.length()>0){
							third_yrJsonarray.put(third_yrJsonaobject);
						}
					}
					if(nextnode.hasProperty("fifth_yr")){
						String fifth_yr="";
						fifth_yr=nextnode.getProperty("fifth_yr").getString();

						JSONObject fifth_yrJsonobject=new JSONObject();
						fifth_yrJsonobject.put("fifth_yr", fifth_yr);
						if(fifth_yrJsonobject.length()>0){
							fifth_yrJsonarray.put(fifth_yrJsonobject);
						}
					}


					keyNameObject=new JSONObject();

					keyNameObject.put("BrokerTcRateId", Id);
					keyNameObject.put("dateAndTime", dateAndTime);
					keyNameObject.put("BrokerJsonarray", BrokerJsonarray);
					keyNameObject.put("VesselTypeJsonarray", VesselTypeJsonarray);
					keyNameObject.put("Month_YearJsonarray", Month_YearJsonarray);
					keyNameObject.put("first_yrJsonarray", first_yrJsonarray);
					keyNameObject.put("second_yrJsonarray", second_yrJsonarray);
					keyNameObject.put("third_yrJsonarray", third_yrJsonarray);
					keyNameObject.put("fifth_yrJsonarray", fifth_yrJsonarray);

					keyNameObject.put("subNodeName", subNodeName);
					keyNameObject.put("size", size+1);//parseId
					keyNameObject.put("parseId", parseId+1);
					keyNameObject.put("i", i);
					keyNameObject.put("Sr_No", Sr_No);

					if(keyNameObject.length()>0){
						keyNameObjectJsonarray.put(keyNameObject);
					}

					limit= String.valueOf(Integer.parseInt(limit)+1);	


				}// while close 

				if(!nodeFlag){
					keyNameObject=new JSONObject();
					keyNameObject.put("Error", "No New Data Available");
					keyNameObjectJsonarray.put(keyNameObject);

				}

				if(keyNameObjectJsonarray.length()>0){
					mainJsonobj=new JSONObject();
					mainJsonobj.put("BrokerTcRateList", keyNameObjectJsonarray);

					//				out.println(mainJsonobj);
				}

				TimeCharterReports.setProperty("netChangedPointer",String.valueOf(size));
				session.save();



			} // node check close if

		} catch (Exception e) {
//			out.println(e.getMessage());
			System.out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public  JSONObject getTonnageReport(Session session,String reportType,PrintWriter out) {

		JSONObject mainJsonobj=null;
		try {
			FetchData fd=new FetchData();
			if( !GmailMethods.isNullString(reportType) ){

				Node scorpio = session.getRootNode().getNode("scorpioDataBase");
				Node ReportData = scorpio.getNode("ReportData");
				Node Tonnage = ReportData.getNode("Tonnage");
				FetchData FD = new FetchData();
				String reporttypequeryid = FD.check_ReportTypeToFetchapi(session, reportType, out);
				//				out.println("reporttypequeryid: " + reporttypequeryid);

				Workspace workspace = session.getWorkspace();

				String slingqery = "select [ReportType] from [nt:base] where ReportType ='" + reporttypequeryid
						+ "'  and ISDESCENDANTNODE('/scorpioDataBase/ReportData/Tonnage/')";

				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();

				RowIterator rowIterator = queryResult.getRows();
				boolean nodeFlag = false;
				long size=queryResult.getRows().getSize();
				JSONObject keyNameObject=null;
				JSONArray keyNameObjectJsonarray=new JSONArray();
				String netChangedPointer="";
				String limit ="";

				if(Tonnage.hasProperty("netChangedPointer")){
					netChangedPointer=Tonnage.getProperty("netChangedPointer").getString();
					limit= String.valueOf(Integer.parseInt(netChangedPointer)+1);
				}
				if(rowIterator!=null){


					//				while (rowIterator.hasNext()) {
					while(Tonnage.hasNode(limit)){
						nodeFlag = true;
						//					Row row = rowIterator.nextRow();
						//					Node node = row.getNode();
						Node nextnode=Tonnage.getNode(limit);

						String EmploymentStatus="";
						String Owners="";
						String OpenPort="";
						String Operators="";
						String Id="";
						String CargoType="";
						String Comment="";
						String ETABasis="";
						String OpenDate="";
						String ReportType="";
						String ReportTimestamp="";
						String RepositionRegion="";
						String Source="";
						String VesselName="";

						JSONArray EmploymentStatusJsonarray=new JSONArray();
						JSONArray OwnersJsonarray=new JSONArray();
						JSONArray OpenPortJsonarray=new JSONArray();
						JSONArray OperatorsJsonarray=new JSONArray();
						JSONArray CargoTypeJsonarray=new JSONArray();
						JSONArray CommentJsonarray=new JSONArray();
						JSONArray ETABasisJsonarray=new JSONArray();
						JSONArray OpenDateJsonarray=new JSONArray();
						JSONArray ReportTypeJsonarray=new JSONArray();
						JSONArray ReportTimestampJsonarray=new JSONArray();
						JSONArray RepositionRegionJsonarray=new JSONArray();
						JSONArray SourceJsonarray=new JSONArray();
						JSONArray VesselNameJsonarray=new JSONArray();
						JSONArray BuiltJsonarray=new JSONArray();
						JSONArray DWTJsonarray=new JSONArray();
						JSONArray CubicsJsonarray=new JSONArray();
						JSONArray LOAJsonarray=new JSONArray();
						JSONArray ICEJsonarray=new JSONArray();
						JSONArray SternLinejsonarray=new JSONArray();
						JSONArray VesselTypejsonarray=new JSONArray();


						if(nextnode.hasProperty("Id")){
							Id=nextnode.getProperty("Id").getString();
						}

						if(nextnode.hasProperty("EmploymentStatus")){
							EmploymentStatus=nextnode.getProperty("EmploymentStatus").getString();
							EmploymentStatus=fd.fetch_EmployementStatus(session, EmploymentStatus);

							JSONObject EmploymentStatusJsonObject=new JSONObject();
							EmploymentStatusJsonObject.put("EmploymentStatus", EmploymentStatus);
							if(EmploymentStatusJsonObject.length()>0){
								EmploymentStatusJsonarray.put(EmploymentStatusJsonObject);
							}
						}
						if(nextnode.hasProperty("Owners")){
							Owners=nextnode.getProperty("Owners").getString();
							Owners=FetchData.fetch_Owners(session, Owners);

							JSONObject OwnersJsonObject=new JSONObject();
							OwnersJsonObject.put("Owners", Owners);
							if(OwnersJsonObject.length()>0){
								OwnersJsonarray.put(OwnersJsonObject);
							}
						}
						/*if(nextnode.hasProperty("OpenPort")){
						OpenPort=nextnode.getProperty("OpenPort").getString();
						OpenPort=fd.fetch_Port(session, OpenPort);

						JSONObject OpenPortJsonObject=new JSONObject();
						OpenPortJsonObject.put("OpenPort", OpenPort);
						if(OpenPortJsonObject.length()>0){
						OpenPortJsonarray.put(OpenPortJsonObject);
						}
					}*/

						if(nextnode.hasProperty("OpenPort")){
							OpenPort=nextnode.getProperty("OpenPort").getString();
							String OpenPort1 = fd.fetch_Port(session, OpenPort);
							boolean checkjsonString = SaveReportDataClassNewStr.isJSONValid(OpenPort1);
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
								JSONObject OpenPortJsonObject=new JSONObject();
								OpenPortJsonObject.put("OpenPort", OpenPort);
								if(OpenPortJsonObject.length()>0){
									OpenPortJsonarray.put(OpenPortJsonObject);
								}
							}//
						}
						if (GmailMethods.isNullString(RepositionRegion)) {
							if(nextnode.hasProperty("RepositionRegion")){
								RepositionRegion=nextnode.getProperty("RepositionRegion").getString();
								String RepositionRegion1 = fd.fetch_Port(session, RepositionRegion);
								boolean checkjsonString = SaveReportDataClassNewStr.isJSONValid(RepositionRegion1);
								if (checkjsonString == true) {
									JSONObject data = new JSONObject(RepositionRegion1);

									if (data.has("Area")) {
										RepositionRegion = data.getString("Area");
										// check_Port_repositionRegion_id=check_OpenPort_id;
									}

								}

								JSONObject RepositionRegionJsonaobject=new JSONObject();
								RepositionRegionJsonaobject.put("RepositionRegion", RepositionRegion);
								if(RepositionRegionJsonaobject.length()>0){
									RepositionRegionJsonarray.put(RepositionRegionJsonaobject);
								}
							}else{
								RepositionRegion = "";
							}
						}

						if(nextnode.hasProperty("Operators")){
							Operators=nextnode.getProperty("Operators").getString();
							Operators=fd.fetch_Operators(session, Operators);

							JSONObject OperatorsJsonaobject=new JSONObject();
							OperatorsJsonaobject.put("Operators", Operators);
							if(OperatorsJsonaobject.length()>0){
								OperatorsJsonarray.put(OperatorsJsonaobject);
							}
						}
						if(nextnode.hasProperty("CargoType")){
							CargoType=nextnode.getProperty("CargoType").getString();
							CargoType=fd.fetch_CargoType(session, CargoType);

							JSONObject CargoTypeJsonaobject=new JSONObject();
							CargoTypeJsonaobject.put("CargoType", CargoType);
							if(CargoTypeJsonaobject.length()>0){
								CargoTypeJsonarray.put(CargoTypeJsonaobject);
							}
						}
						if(nextnode.hasProperty("Comment")){
							Comment=nextnode.getProperty("Comment").getString();

							JSONObject CommentJsonaobject=new JSONObject();
							CommentJsonaobject.put("Comment", Comment);
							if(CommentJsonaobject.length()>0){
								CommentJsonarray.put(CommentJsonaobject);
							}
						}
						if(nextnode.hasProperty("ETABasis")){
							ETABasis=nextnode.getProperty("ETABasis").getString();

							JSONObject ETABasisJsonaobject=new JSONObject();
							ETABasisJsonaobject.put("ETABasis", ETABasis);
							if(ETABasisJsonaobject.length()>0){
								ETABasisJsonarray.put(ETABasisJsonaobject);
							}
						}
						if(nextnode.hasProperty("OpenDate")){
							OpenDate=nextnode.getProperty("OpenDate").getString();

							JSONObject OpenDateJsonaobject=new JSONObject();
							OpenDateJsonaobject.put("OpenDate", OpenDate);
							if(OpenDateJsonaobject.length()>0){
								OpenDateJsonarray.put(OpenDateJsonaobject);
							}
						}
						if(nextnode.hasProperty("ReportType")){
							ReportType=nextnode.getProperty("ReportType").getString();
							ReportType=fd.fetch_ReportType(session, ReportType);

							JSONObject ReportTypeJsonaobject=new JSONObject();
							ReportTypeJsonaobject.put("ReportType", ReportType);
							if(ReportTypeJsonaobject.length()>0){
								ReportTypeJsonarray.put(ReportTypeJsonaobject);
							}
						}
						if(nextnode.hasProperty("ReportTimestamp")){
							ReportTimestamp=nextnode.getProperty("ReportTimestamp").getString();

							JSONObject ReportTimestampJsonaobject=new JSONObject();
							ReportTimestampJsonaobject.put("ReportTimestamp", ReportTimestamp);
							if(ReportTimestampJsonaobject.length()>0){
								ReportTimestampJsonarray.put(ReportTimestampJsonaobject);
							}
						}
						/*if(nextnode.hasProperty("RepositionRegion")){
						RepositionRegion=nextnode.getProperty("RepositionRegion").getString();
						RepositionRegion=fd.fetch_Port(session, RepositionRegion);

						JSONObject RepositionRegionJsonaobject=new JSONObject();
						RepositionRegionJsonaobject.put("RepositionRegion", RepositionRegion);
						if(RepositionRegionJsonaobject.length()>0){
						RepositionRegionJsonarray.put(RepositionRegionJsonaobject);
						}
					}*/
						if(nextnode.hasProperty("Source")){
							Source=nextnode.getProperty("Source").getString();
							Source=fd.fetch_Source(session, Source);

							JSONObject SourceJsonaobject=new JSONObject();
							SourceJsonaobject.put("Source", Source);
							if(SourceJsonaobject.length()>0){
								SourceJsonarray.put(SourceJsonaobject);
							}
						}
						if(nextnode.hasProperty("VesselName")){
							VesselName=nextnode.getProperty("VesselName").getString();
							VesselName=fd.fetch_vesselName(session, VesselName);

							boolean checkjsonString=SaveReportDataClassNewStr.isJSONValid(VesselName);
							if(checkjsonString==true){
								JSONObject fetch_vesselNamejsonobject=new JSONObject(VesselName);
								if(fetch_vesselNamejsonobject.has("vessel_Id")){
									VesselName=fetch_vesselNamejsonobject.getString("vessel_Id");
								}}

							JSONObject VesselNameJsonaobject=new JSONObject();
							VesselNameJsonaobject.put("VesselName", VesselName);
							if(VesselNameJsonaobject.length()>0){
								VesselNameJsonarray.put(VesselNameJsonaobject);
							}
						}

						if(nextnode.hasProperty("Built")){
							String Built="";
							Built=nextnode.getProperty("Built").getString();

							JSONObject BuiltJsonaobject=new JSONObject();
							BuiltJsonaobject.put("Built", Built);
							if(BuiltJsonaobject.length()>0){
								BuiltJsonarray.put(BuiltJsonaobject);
							}
						}

						if(nextnode.hasProperty("DWT")){
							String DWT="";
							DWT=nextnode.getProperty("DWT").getString();

							JSONObject DWTJsonaobject=new JSONObject();
							DWTJsonaobject.put("DWT", DWT);
							if(DWTJsonaobject.length()>0){
								DWTJsonarray.put(DWTJsonaobject);
							}
						}

						if(nextnode.hasProperty("Cubics")){
							String Cubics="";
							Cubics=nextnode.getProperty("Cubics").getString();

							JSONObject CubicsJsonaobject=new JSONObject();
							CubicsJsonaobject.put("Cubics", Cubics);
							if(CubicsJsonaobject.length()>0){
								CubicsJsonarray.put(CubicsJsonaobject);
							}
						}

						if(nextnode.hasProperty("LOA")){
							String LOA="";
							LOA=nextnode.getProperty("LOA").getString();

							JSONObject LOAJsonaobject=new JSONObject();
							LOAJsonaobject.put("LOA", LOA);
							if(LOAJsonaobject.length()>0){
								LOAJsonarray.put(LOAJsonaobject);
							}
						}


						if(nextnode.hasProperty("ICE")){
							String ICE="";
							ICE=nextnode.getProperty("ICE").getString();

							JSONObject ICEJsonaobject=new JSONObject();
							ICEJsonaobject.put("ICE", ICE);
							if(ICEJsonaobject.length()>0){
								ICEJsonarray.put(ICEJsonaobject);
							}
						}


						if(nextnode.hasProperty("SternLine")){
							String SternLine="";
							SternLine=nextnode.getProperty("SternLine").getString();

							JSONObject SternLineJsonaobject=new JSONObject();
							SternLineJsonaobject.put("SternLine", SternLine);
							if(SternLineJsonaobject.length()>0){
								SternLinejsonarray.put(SternLineJsonaobject);
							}
						}

						if(nextnode.hasProperty("VesselType")){
							String VesselType="";
							VesselType=nextnode.getProperty("VesselType").getString();
							VesselType=FetchData.fetch_vesselType(session, VesselType);

							JSONObject VesselTypeJsonaobject=new JSONObject();
							VesselTypeJsonaobject.put("VesselType", VesselType);
							if(VesselTypeJsonaobject.length()>0){
								VesselTypejsonarray.put(VesselTypeJsonaobject);
							}
						}


						keyNameObject=new JSONObject();

						keyNameObject.put("tonnageId", Id);
						keyNameObject.put("EmploymentStatusJsonarray", EmploymentStatusJsonarray);
						keyNameObject.put("OwnersJsonarray", OwnersJsonarray);
						keyNameObject.put("OpenPortJsonarray", OpenPortJsonarray);
						keyNameObject.put("OperatorsJsonarray", OperatorsJsonarray);
						keyNameObject.put("CargoTypeJsonarray", CargoTypeJsonarray);
						keyNameObject.put("CommentJsonarray", CommentJsonarray);
						keyNameObject.put("ETABasisJsonarray", ETABasisJsonarray);
						keyNameObject.put("OpenDateJsonarray", OpenDateJsonarray);
						keyNameObject.put("ReportTypeJsonarray", ReportTypeJsonarray);
						keyNameObject.put("ReportTimestampJsonarray", ReportTimestampJsonarray);
						keyNameObject.put("RepositionRegionJsonarray", RepositionRegionJsonarray);
						keyNameObject.put("SourceJsonarray", SourceJsonarray);
						keyNameObject.put("VesselNameJsonarray", VesselNameJsonarray);
						keyNameObject.put("BuiltJsonarray", BuiltJsonarray);
						keyNameObject.put("DWTJsonarray", DWTJsonarray);
						keyNameObject.put("CubicsJsonarray", CubicsJsonarray);
						keyNameObject.put("LOAJsonarray", LOAJsonarray);
						keyNameObject.put("ICEJsonarray", ICEJsonarray);
						keyNameObject.put("SternLinejsonarray", SternLinejsonarray);
						keyNameObject.put("VesselTypejsonarray", VesselTypejsonarray);


						if(keyNameObject.length()>0){
							keyNameObjectJsonarray.put(keyNameObject);
						}
						limit= String.valueOf(Integer.parseInt(limit)+1);


					} // while
				}

				if(!nodeFlag){
					keyNameObject=new JSONObject();
					keyNameObject.put("Error", "No New Data Available");
					keyNameObjectJsonarray.put(keyNameObject);

				}

				if(keyNameObjectJsonarray.length()>0){
					mainJsonobj=new JSONObject();
					mainJsonobj.put("tonnageList", keyNameObjectJsonarray);

				}

				Tonnage.setProperty("netChangedPointer",String.valueOf(size));
				session.save();


			}

		} catch (Exception e) {
//			out.println(e.getMessage());
			System.out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public JSONObject getSpotReport(Session session,String reportType,PrintWriter out) {

		JSONObject mainJsonobj=null;
		try {
			FetchData fd=new FetchData();
			if( !GmailMethods.isNullString(reportType) ){

				Node scorpio = session.getRootNode().getNode("scorpioDataBase");
				Node ReportData = scorpio.getNode("ReportData");
				Node Tonnage = ReportData.getNode("Spot");
				FetchData FD = new FetchData();
				String reporttypequeryid = FD.check_ReportTypeToFetchapi(session, reportType, out);
				//				out.println("reporttypequeryid: " + reporttypequeryid);

				Workspace workspace = session.getWorkspace();

				String slingqery = "select [ReportType] from [nt:base] where ReportType ='" + reporttypequeryid
						+ "'  and ISDESCENDANTNODE('/scorpioDataBase/ReportData/Spot/')";

				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();

				RowIterator rowIterator = queryResult.getRows();
				boolean nodeFlag = false;
				//				long size=rowIterator.getSize();
				long size=Tonnage.getNodes().getSize();
				//				out.println("size: "+size);
				JSONObject keyNameObject=null;
				JSONArray keyNameObjectJsonarray=new JSONArray();
				String netChangedPointer="";
				String limit ="";

				if(Tonnage.hasProperty("netChangedPointer")){
					netChangedPointer=Tonnage.getProperty("netChangedPointer").getString();
					limit= String.valueOf(Integer.parseInt(netChangedPointer)+1);
				}
				if(rowIterator!=null){


					//				while (rowIterator.hasNext()) {
					while(Tonnage.hasNode(limit)){
						nodeFlag = true;
						//					Row row = rowIterator.nextRow();
						//					Node node = row.getNode();
						Node nextnode=Tonnage.getNode(limit);

						String Information="";
						String FixtureType="";
						String Charterer="";
						String Status="";
						String CargoType="";
						String CargoGrade="";
						String CargoQty="";
						String LCStart="";
						String Source="";
						String VesselName="";
						String LCEnd="";
						String LoadPort="";
						String DiscPort="";
						String RateType="";
						String Rate="";
						String Comments="";
						String Id="";

						JSONArray InformationJsonarray=new JSONArray();
						JSONArray FixtureTypeJsonarray=new JSONArray();
						JSONArray ChartererJsonarray=new JSONArray();
						JSONArray StatusJsonarray=new JSONArray();
						JSONArray CargoTypeJsonarray=new JSONArray();
						JSONArray CargoGradeJsonarray=new JSONArray();
						JSONArray CargoQtyJsonarray=new JSONArray();
						JSONArray SourceJsonarray=new JSONArray();
						JSONArray VesselNameJsonarray=new JSONArray();
						JSONArray LCStartJsonarray=new JSONArray();
						JSONArray LCEndJsonarray=new JSONArray();
						JSONArray LoadPortJsonarray=new JSONArray();
						JSONArray DiscPortJsonarray=new JSONArray();
						JSONArray RateTypeJsonarray=new JSONArray();
						JSONArray RateJsonarray=new JSONArray();
						JSONArray CommentsJsonarray=new JSONArray();
						JSONArray ReportDateJsonarray=new JSONArray();
						JSONArray VesseltypeJsonarray=new JSONArray();



						if(nextnode.hasProperty("Id")){
							Id=nextnode.getProperty("Id").getString();
						}

						if(nextnode.hasProperty("Information")){
							Information=nextnode.getProperty("Information").getString();
							Information=fd.fetch_Information(session, Information);

							JSONObject InformationJsonObject=new JSONObject();
							InformationJsonObject.put("Information", Information);
							if(InformationJsonObject.length()>0){
								InformationJsonarray.put(InformationJsonObject);
							}
						}
						if(nextnode.hasProperty("FixtureType")){
							FixtureType=nextnode.getProperty("FixtureType").getString();
							FixtureType=fd.fetch_ReportType(session, FixtureType);

							JSONObject FixtureTypeJsonObject=new JSONObject();
							FixtureTypeJsonObject.put("FixtureType", FixtureType);
							if(FixtureTypeJsonObject.length()>0){
								FixtureTypeJsonarray.put(FixtureTypeJsonObject);
							}
						}
						if(nextnode.hasProperty("Charterer")){
							Charterer=nextnode.getProperty("Charterer").getString();
							Charterer=fd.fetch_Charterer(session, Charterer);

							JSONObject ChartererJsonObject=new JSONObject();
							ChartererJsonObject.put("Charterer", Charterer);
							if(ChartererJsonObject.length()>0){
								ChartererJsonarray.put(ChartererJsonObject);
							}
						}
						if(nextnode.hasProperty("Status")){
							Status=nextnode.getProperty("Status").getString();
							Status=fd.fetch_EmployementStatus(session, Status);

							JSONObject StatusJsonobject=new JSONObject();
							StatusJsonobject.put("Status", Status);
							if(StatusJsonobject.length()>0){
								StatusJsonarray.put(StatusJsonobject);
							}
						}
						if(nextnode.hasProperty("CargoType")){
							CargoType=nextnode.getProperty("CargoType").getString();
							CargoType=fd.fetch_CargoType(session, CargoType);

							JSONObject CargoTypeJsonaobject=new JSONObject();
							CargoTypeJsonaobject.put("CargoType", CargoType);
							if(CargoTypeJsonaobject.length()>0){
								CargoTypeJsonarray.put(CargoTypeJsonaobject);
							}
						}
						if(nextnode.hasProperty("CargoGrade")){
							CargoGrade=nextnode.getProperty("CargoGrade").getString();
							CargoGrade=fd.fetch_CargoGrade(session, CargoGrade);

							JSONObject CargoGradeJsonobject=new JSONObject();
							CargoGradeJsonobject.put("CargoGrade", CargoGrade);
							if(CargoGradeJsonobject.length()>0){
								CargoGradeJsonarray.put(CargoGradeJsonobject);
							}
						}
						if(nextnode.hasProperty("CargoQty")){
							CargoQty=nextnode.getProperty("CargoQty").getString();

							JSONObject CargoQtyJsonaobject=new JSONObject();
							CargoQtyJsonaobject.put("CargoQty", CargoQty);
							if(CargoQtyJsonaobject.length()>0){
								CargoQtyJsonarray.put(CargoQtyJsonaobject);
							}
						}
						if(nextnode.hasProperty("LCStart")){
							LCStart=nextnode.getProperty("LCStart").getString();

							JSONObject LCStartJsonaobject=new JSONObject();
							LCStartJsonaobject.put("LCStart", LCStart);
							if(LCStartJsonaobject.length()>0){
								LCStartJsonarray.put(LCStartJsonaobject);
							}
						}
						if(nextnode.hasProperty("LCEnd")){
							LCEnd=nextnode.getProperty("LCEnd").getString();

							JSONObject LCEndJsonaobject=new JSONObject();
							LCEndJsonaobject.put("LCEnd", LCEnd);
							if(LCEndJsonaobject.length()>0){
								LCEndJsonarray.put(LCEndJsonaobject);
							}
						}
						if(nextnode.hasProperty("LoadPort")){
							LoadPort=nextnode.getProperty("LoadPort").getString();
							LoadPort=fd.fetch_Port(session, LoadPort);

							JSONObject LoadPortJsonaobject=new JSONObject();
							LoadPortJsonaobject.put("LoadPort", LoadPort);
							if(LoadPortJsonaobject.length()>0){
								LoadPortJsonarray.put(LoadPortJsonaobject);
							}
						}
						if(nextnode.hasProperty("DiscPort")){
							DiscPort=nextnode.getProperty("DiscPort").getString();
							DiscPort=fd.fetch_Port(session, DiscPort);

							JSONObject DiscPortJsonaobject=new JSONObject();
							DiscPortJsonaobject.put("DiscPort", DiscPort);
							if(DiscPortJsonaobject.length()>0){
								DiscPortJsonarray.put(DiscPortJsonaobject);
							}
						}
						if(nextnode.hasProperty("VesselName")){
							VesselName=nextnode.getProperty("VesselName").getString();

							VesselName=fd.fetch_vesselName(session, VesselName);
							JSONObject fetch_vesselNamejsonobject=new JSONObject(VesselName);
							if(fetch_vesselNamejsonobject.has("vessel_Id")){
								VesselName=fetch_vesselNamejsonobject.getString("vessel_Id");
							}

							JSONObject VesselNameJsonaobject=new JSONObject();
							VesselNameJsonaobject.put("VesselName", VesselName);
							if(VesselNameJsonaobject.length()>0){
								VesselNameJsonarray.put(VesselNameJsonaobject);
							}
						}
						if(nextnode.hasProperty("RateType")){
							RateType=nextnode.getProperty("RateType").getString();
							RateType=fd.fetch_RateType(session, RateType);

							JSONObject RateTypeJsonaobject=new JSONObject();
							RateTypeJsonaobject.put("RateType", RateType);
							if(RateTypeJsonaobject.length()>0){
								RateTypeJsonarray.put(RateTypeJsonaobject);
							}
						}
						if(nextnode.hasProperty("Rate")){
							Rate=nextnode.getProperty("Rate").getString();

							JSONObject RateJsonaobject=new JSONObject();
							RateJsonaobject.put("Rate", Rate);
							if(RateJsonaobject.length()>0){
								RateJsonarray.put(RateJsonaobject);
							}
						}
						if(nextnode.hasProperty("Comments")){
							Comments=nextnode.getProperty("Comments").getString();

							JSONObject CommentsJsonaobject=new JSONObject();
							CommentsJsonaobject.put("Comments", Comments);
							if(CommentsJsonaobject.length()>0){
								CommentsJsonarray.put(CommentsJsonaobject);
							}
						}
						if(nextnode.hasProperty("ReportDate")){
							String ReportDate="";
							ReportDate=nextnode.getProperty("ReportDate").getString();

							JSONObject ReportDateJsonaobject=new JSONObject();
							ReportDateJsonaobject.put("ReportDate", ReportDate);
							if(ReportDateJsonaobject.length()>0){
								ReportDateJsonarray.put(ReportDateJsonaobject);
							}
						}
						if(nextnode.hasProperty("Source")){
							Source=nextnode.getProperty("Source").getString();
							Source=fd.fetch_Source(session, Source);

							JSONObject SourceJsonaobject=new JSONObject();
							SourceJsonaobject.put("Source", Source);
							if(SourceJsonaobject.length()>0){
								SourceJsonarray.put(SourceJsonaobject);
							}
						}
						if(nextnode.hasProperty("Vesseltype")){
							String Vesseltype="";
							Vesseltype=nextnode.getProperty("Vesseltype").getString();
							Vesseltype=FetchData.fetch_vesselType(session, Vesseltype);

							JSONObject VesseltypeJsonaobject=new JSONObject();
							VesseltypeJsonaobject.put("Vesseltype", Vesseltype);
							if(VesseltypeJsonaobject.length()>0){
								VesseltypeJsonarray.put(VesseltypeJsonaobject);
							}
						}
						keyNameObject =new JSONObject();

						keyNameObject.put("spotId", Id);
						keyNameObject.put("InformationJsonarray", InformationJsonarray);
						keyNameObject.put("FixtureTypeJsonarray", FixtureTypeJsonarray);
						keyNameObject.put("LCStartJsonarray", LCStartJsonarray);
						keyNameObject.put("LCEndJsonarray", LCEndJsonarray);
						keyNameObject.put("LoadPortJsonarray", LoadPortJsonarray);
						keyNameObject.put("DiscPortJsonarray", DiscPortJsonarray);
						keyNameObject.put("VesselNameJsonarray", VesselNameJsonarray);
						keyNameObject.put("ChartererJsonarray", ChartererJsonarray);
						keyNameObject.put("StatusJsonarray", StatusJsonarray);
						keyNameObject.put("CargoTypeJsonarray", CargoTypeJsonarray);
						keyNameObject.put("CargoGradeJsonarray", CargoGradeJsonarray);
						keyNameObject.put("CargoQtyJsonarray", CargoQtyJsonarray);
						keyNameObject.put("RateTypeJsonarray", RateTypeJsonarray);
						keyNameObject.put("RateJsonarray", RateJsonarray);
						keyNameObject.put("CommentsJsonarray", CommentsJsonarray);
						keyNameObject.put("ReportDateJsonarray", ReportDateJsonarray);
						keyNameObject.put("SourceJsonarray", SourceJsonarray);
						keyNameObject.put("VesseltypeJsonarray", VesseltypeJsonarray);


						if(keyNameObject.length()>0){
							keyNameObjectJsonarray.put(keyNameObject);
						}

						limit= String.valueOf(Integer.parseInt(limit)+1);

					} // while


				}

				if(!nodeFlag){
					keyNameObject=new JSONObject();
					keyNameObject.put("Error", "No New Data Available");
					keyNameObjectJsonarray.put(keyNameObject);

				}

				if(keyNameObjectJsonarray.length()>0){
					mainJsonobj=new JSONObject();
					mainJsonobj.put("spotList", keyNameObjectJsonarray);

				}

				//				out.println("last: "+size);
				Tonnage.setProperty("netChangedPointer",String.valueOf(size));
				session.save();


			}

		} catch (Exception e) {
//			e.printStackTrace(out);
//			out.println(e.getMessage());
			System.out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public JSONObject getTimeCharterReport(Session session,String reportType,PrintWriter out) {

		JSONObject mainJsonobj=null;
		try {
			FetchData fd =new FetchData();
			if( !GmailMethods.isNullString(reportType) ){

				Node scorpio = session.getRootNode().getNode("scorpioDataBase");
				Node ReportData = scorpio.getNode("ReportData");
				Node TCReports=ReportData.getNode("TCReports");
				Node Tonnage = TCReports.getNode("TimeCharterReport");

				FetchData FD = new FetchData();
				String reporttypequeryid = FD.check_ReportTypeToFetchapi(session, reportType, out);
				//				out.println("reporttypequeryid: " + reporttypequeryid);

				Workspace workspace = session.getWorkspace();

				String slingqery = "select [ReportType] from [nt:base] where ReportType ='" + reporttypequeryid
						+ "'  and ISDESCENDANTNODE('/scorpioDataBase/ReportData/TCReports/TimeCharterReport/')";

				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();

				RowIterator rowIterator = queryResult.getRows();
				boolean nodeFlag = false;
				//				long size=queryResult.getRows().getSize();
				long size=Tonnage.getNodes().getSize();
				JSONObject keyNameObject=null;
				JSONArray keyNameObjectJsonarray=new JSONArray();
				String netChangedPointer="";
				String limit ="";

				if(Tonnage.hasProperty("netChangedPointer")){
					netChangedPointer=Tonnage.getProperty("netChangedPointer").getString();
					limit= String.valueOf(Integer.parseInt(netChangedPointer)+1);
				}
				if(rowIterator!=null){


					//				while (rowIterator.hasNext()) {
					while(Tonnage.hasNode(limit)){
						nodeFlag = true;
						//					Row row = rowIterator.nextRow();
						//					Node node = row.getNode();
						Node nextnode=Tonnage.getNode(limit);

						String Information="";
						String Charterer="";
						String Source="";
						String VesselName="";
						String Comments="";
						String Rate="";
						String Id="";

						JSONArray InformationJsonarray=new JSONArray();
						JSONArray Spot_TCJsonarray=new JSONArray();
						JSONArray DateJsonarray=new JSONArray();
						JSONArray CPP_DPPJsonarray=new JSONArray();
						JSONArray VesselNameJsonarray=new JSONArray();
						JSONArray ChartererJsonarray= new JSONArray();
						JSONArray DeliveryJsonarray=new JSONArray();
						JSONArray DeliveryPlaceJsonarray=new JSONArray();
						JSONArray SourceJsonarray=new JSONArray();

						JSONArray RateJsonarray=new JSONArray();
						JSONArray CommentsJsonarray=new JSONArray();
						JSONArray PeriodJsonarray=new JSONArray();
						JSONArray VesseltypeJsonarray=new JSONArray();
						JSONArray RedeliveryJsonarray=new JSONArray();
						JSONArray TCRateJsonarray=new JSONArray();
						JSONArray TimeStampJsonarray=new JSONArray();
						JSONArray PeriodunitJsonarray=new JSONArray();
						JSONArray UnitJsonarray=new JSONArray();



						if(nextnode.hasProperty("Id")){
							Id=nextnode.getProperty("Id").getString();
						}

						if(nextnode.hasProperty("Information")){
							Information=nextnode.getProperty("Information").getString();
							Information=fd.fetch_Information(session, Information);

							JSONObject InformationJsonObject=new JSONObject();
							InformationJsonObject.put("Information", Information);
							if(InformationJsonObject.length()>0){
								InformationJsonarray.put(InformationJsonObject);
							}
						}
						if(nextnode.hasProperty("Spot_TC")){
							String Spot_TC="";
							Spot_TC=nextnode.getProperty("Spot_TC").getString();
							Spot_TC=fd.fetch_ReportType(session, Spot_TC);

							JSONObject Spot_TCJsonObject=new JSONObject();
							Spot_TCJsonObject.put("Spot_TC", Spot_TC);
							if(Spot_TCJsonObject.length()>0){
								Spot_TCJsonarray.put(Spot_TCJsonObject);
							}
						}
						if(nextnode.hasProperty("Date")){
							String Date="";
							Date=nextnode.getProperty("Date").getString();

							JSONObject DateJsonObject=new JSONObject();
							DateJsonObject.put("Date", Date);
							if(DateJsonObject.length()>0){
								DateJsonarray.put(DateJsonObject);
							}
						}

						if(nextnode.hasProperty("VesselName")){
							VesselName=nextnode.getProperty("VesselName").getString();

							VesselName=fd.fetch_vesselName(session, VesselName);
							JSONObject fetch_vesselNamejsonobject=new JSONObject(VesselName);
							if(fetch_vesselNamejsonobject.has("vessel_Id")){
								VesselName=fetch_vesselNamejsonobject.getString("vessel_Id");
							}

							JSONObject VesselNameJsonaobject=new JSONObject();
							VesselNameJsonaobject.put("VesselName", VesselName);
							if(VesselNameJsonaobject.length()>0){
								VesselNameJsonarray.put(VesselNameJsonaobject);
							}
						}


						if(nextnode.hasProperty("CPP_DPP")){
							String CPP_DPP="";
							CPP_DPP=nextnode.getProperty("CPP_DPP").getString();
							CPP_DPP=fd.fetch_CargoType(session, CPP_DPP);

							JSONObject CPP_DPPJsonobject=new JSONObject();
							CPP_DPPJsonobject.put("CPP_DPP", CPP_DPP);
							if(CPP_DPPJsonobject.length()>0){
								CPP_DPPJsonarray.put(CPP_DPPJsonobject);
							}
						}
						if(nextnode.hasProperty("Charterer")){
							Charterer=nextnode.getProperty("Charterer").getString();
							Charterer=fd.fetch_Charterer(session, Charterer);

							JSONObject ChartererJsonaobject=new JSONObject();
							ChartererJsonaobject.put("Charterer", Charterer);
							if(ChartererJsonaobject.length()>0){
								ChartererJsonarray.put(ChartererJsonaobject);
							}
						}
						if(nextnode.hasProperty("Delivery")){
							String Delivery="";
							Delivery=nextnode.getProperty("Delivery").getString();

							JSONObject DeliveryJsonobject=new JSONObject();
							DeliveryJsonobject.put("Delivery", Delivery);
							if(DeliveryJsonobject.length()>0){
								DeliveryJsonarray.put(DeliveryJsonobject);
							}
						}
						if(nextnode.hasProperty("DeliveryPlace")){
							String DeliveryPlace="";
							DeliveryPlace=nextnode.getProperty("DeliveryPlace").getString();
							DeliveryPlace=fd.fetch_DeliveryPlace(session, DeliveryPlace);

							JSONObject DeliveryPlaceJsonaobject=new JSONObject();
							DeliveryPlaceJsonaobject.put("DeliveryPlace", DeliveryPlace);
							if(DeliveryPlaceJsonaobject.length()>0){
								DeliveryPlaceJsonarray.put(DeliveryPlaceJsonaobject);
							}
						}
						if(nextnode.hasProperty("Period")){
							String Period="";
							Period=nextnode.getProperty("Period").getString();

							JSONObject PeriodJsonaobject=new JSONObject();
							PeriodJsonaobject.put("Period", Period);
							if(PeriodJsonaobject.length()>0){
								PeriodJsonarray.put(PeriodJsonaobject);
							}
						}
						if(nextnode.hasProperty("Periodunit")){
							String Periodunit="";
							Periodunit=nextnode.getProperty("Periodunit").getString();
							Periodunit=fd.fetch_Periodunit(session, Periodunit);

							JSONObject PeriodunitJsonaobject=new JSONObject();
							PeriodunitJsonaobject.put("Periodunit", Periodunit);
							if(PeriodunitJsonaobject.length()>0){
								PeriodunitJsonarray.put(PeriodunitJsonaobject);
							}
						}
						if(nextnode.hasProperty("Redelivery")){
							String Redelivery="";
							Redelivery=nextnode.getProperty("Redelivery").getString();

							JSONObject RedeliveryJsonaobject=new JSONObject();
							RedeliveryJsonaobject.put("Redelivery", Redelivery);
							if(RedeliveryJsonaobject.length()>0){
								RedeliveryJsonarray.put(RedeliveryJsonaobject);
							}
						}
						if(nextnode.hasProperty("TCRate")){
							String TCRate="";
							TCRate=nextnode.getProperty("TCRate").getString();

							JSONObject TCRateJsonaobject=new JSONObject();
							TCRateJsonaobject.put("TCRate", TCRate);
							if(TCRateJsonaobject.length()>0){
								TCRateJsonarray.put(TCRateJsonaobject);
							}
						}

						if(nextnode.hasProperty("Rate")){
							Rate=nextnode.getProperty("Rate").getString();

							JSONObject RateJsonaobject=new JSONObject();
							RateJsonaobject.put("Rate", Rate);
							if(RateJsonaobject.length()>0){
								RateJsonarray.put(RateJsonaobject);
							}
						}

						if(nextnode.hasProperty("Unit")){
							String Unit="";
							Unit=nextnode.getProperty("Unit").getString();
							Unit=fd.fetch_currencyUnit(session, Unit);

							JSONObject UnitJsonaobject=new JSONObject();
							UnitJsonaobject.put("Unit", Unit);
							if(UnitJsonaobject.length()>0){
								UnitJsonarray.put(UnitJsonaobject);
							}
						}

						if(nextnode.hasProperty("Comments")){
							Comments=nextnode.getProperty("Comments").getString();

							JSONObject CommentsJsonaobject=new JSONObject();
							CommentsJsonaobject.put("Comments", Comments);
							if(CommentsJsonaobject.length()>0){
								CommentsJsonarray.put(CommentsJsonaobject);
							}
						}

						if(nextnode.hasProperty("Source")){
							Source=nextnode.getProperty("Source").getString();
							Source=fd.fetch_Source(session, Source);

							JSONObject SourceJsonaobject=new JSONObject();
							SourceJsonaobject.put("Source", Source);
							if(SourceJsonaobject.length()>0){
								SourceJsonarray.put(SourceJsonaobject);
							}
						}

						if(nextnode.hasProperty("Vesseltype")){
							String Vesseltype="";
							Vesseltype=nextnode.getProperty("Vesseltype").getString();
							Vesseltype=FetchData.fetch_vesselType(session, Vesseltype);

							JSONObject VesseltypeJsonaobject=new JSONObject();
							VesseltypeJsonaobject.put("Vesseltype", Vesseltype);
							if(VesseltypeJsonaobject.length()>0){
								VesseltypeJsonarray.put(VesseltypeJsonaobject);
							}
						}

						if(nextnode.hasProperty("TimeStamp")){
							String TimeStamp="";
							TimeStamp=nextnode.getProperty("TimeStamp").getString();

							JSONObject TimeStampJsonaobject=new JSONObject();
							TimeStampJsonaobject.put("TimeStamp", TimeStamp);
							if(TimeStampJsonaobject.length()>0){
								TimeStampJsonarray.put(TimeStampJsonaobject);
							}
						}

						keyNameObject =new JSONObject();

						keyNameObject.put("TimeCharterReportsId", Id);
						keyNameObject.put("InformationJsonarray", InformationJsonarray);
						keyNameObject.put("Spot_TCJsonarray", Spot_TCJsonarray);
						keyNameObject.put("DateJsonarray", DateJsonarray);
						keyNameObject.put("VesselNameJsonarray", VesselNameJsonarray);
						keyNameObject.put("CPP_DPPJsonarray", CPP_DPPJsonarray);
						keyNameObject.put("ChartererJsonarray", ChartererJsonarray);
						keyNameObject.put("DeliveryJsonarray", DeliveryJsonarray);
						keyNameObject.put("DeliveryPlaceJsonarray", DeliveryPlaceJsonarray);
						keyNameObject.put("PeriodJsonarray", PeriodJsonarray);
						keyNameObject.put("PeriodunitJsonarray", PeriodunitJsonarray);
						keyNameObject.put("RedeliveryJsonarray", RedeliveryJsonarray);
						keyNameObject.put("TCRateJsonarray", TCRateJsonarray);
						keyNameObject.put("RateJsonarray", RateJsonarray);
						keyNameObject.put("UnitJsonarray", UnitJsonarray);
						keyNameObject.put("CommentsJsonarray", CommentsJsonarray);
						keyNameObject.put("SourceJsonarray", SourceJsonarray);
						keyNameObject.put("VesseltypeJsonarray", VesseltypeJsonarray);
						keyNameObject.put("TimeStampJsonarray", TimeStampJsonarray);


						if(keyNameObject.length()>0){
							keyNameObjectJsonarray.put(keyNameObject);
						}

						limit= String.valueOf(Integer.parseInt(limit)+1);	

					} // while


				}

				if(!nodeFlag){
					keyNameObject=new JSONObject();
					keyNameObject.put("Error", "No New Data Available");
					keyNameObjectJsonarray.put(keyNameObject);

				}

				if(keyNameObjectJsonarray.length()>0){
					mainJsonobj=new JSONObject();
					mainJsonobj.put("TimeCharterReportsList", keyNameObjectJsonarray);

				}

				Tonnage.setProperty("netChangedPointer",String.valueOf(size));
				session.save();


			}

		} catch (Exception e) {
//			out.println(e.getMessage());
			System.out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public JSONObject getBrokerTcRateReport(Session session,String reportType,PrintWriter out) {

		JSONObject mainJsonobj=null;
		try {
			FetchData fd=new FetchData();
			if( !GmailMethods.isNullString(reportType) ){

				Node scorpio = session.getRootNode().getNode("scorpioDataBase");
				Node ReportData = scorpio.getNode("ReportData");
				Node TCReports=ReportData.getNode("TCReports");
				Node Tonnage = TCReports.getNode("BrokerTCRate");

				FetchData FD = new FetchData();
				String reporttypequeryid = FD.check_ReportTypeToFetchapi(session, reportType, out);
				//				out.println("reporttypequeryid: " + reporttypequeryid);

				Workspace workspace = session.getWorkspace();

				String slingqery = "select [ReportType] from [nt:base] where ReportType ='" + reporttypequeryid
						+ "'  and ISDESCENDANTNODE('/scorpioDataBase/ReportData/TCReports/BrokerTCRate/')";

				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();

				RowIterator rowIterator = queryResult.getRows();
				boolean nodeFlag = false;
				//				long size=queryResult.getRows().getSize();
				long size=Tonnage.getNodes().getSize();
				JSONObject keyNameObject=null;
				JSONArray keyNameObjectJsonarray=new JSONArray();
				String netChangedPointer="";
				String limit ="";

				if(Tonnage.hasProperty("netChangedPointer")){
					netChangedPointer=Tonnage.getProperty("netChangedPointer").getString();
					limit= String.valueOf(Integer.parseInt(netChangedPointer)+1);
				}
				if(rowIterator!=null){


					//				while (rowIterator.hasNext()) {
					while(Tonnage.hasNode(limit)){
						nodeFlag = true;
						//					Row row = rowIterator.nextRow();
						//					Node node = row.getNode();
						Node nextnode=Tonnage.getNode(limit);

						String Id="";

						JSONArray BrokerJsonarray=new JSONArray();
						JSONArray VesselTypeJsonarray=new JSONArray();
						JSONArray Month_YearJsonarray=new JSONArray();
						JSONArray first_yrJsonarray=new JSONArray();
						JSONArray second_yrJsonarray=new JSONArray();
						JSONArray third_yrJsonarray= new JSONArray();
						JSONArray fifth_yrJsonarray= new JSONArray();


						if(nextnode.hasProperty("Id")){

							Id=nextnode.getProperty("Id").getString();
						}

						if(nextnode.hasProperty("Broker")){
							String Broker="";
							Broker=nextnode.getProperty("Broker").getString();
							Broker=fd.fetch_Source(session, Broker);

							JSONObject BrokerJsonObject=new JSONObject();
							BrokerJsonObject.put("Broker", Broker);
							if(BrokerJsonObject.length()>0){
								BrokerJsonarray.put(BrokerJsonObject);
							}
						}
						if(nextnode.hasProperty("VesselType")){
							String VesselType="";
							VesselType=nextnode.getProperty("VesselType").getString();
							VesselType=FetchData.fetch_vesselType(session, VesselType);

							JSONObject VesselTypeJsonObject=new JSONObject();
							VesselTypeJsonObject.put("VesselType", VesselType);
							if(VesselTypeJsonObject.length()>0){
								VesselTypeJsonarray.put(VesselTypeJsonObject);
							}
						}
						if(nextnode.hasProperty("Month_Year")){
							String Month_Year="";
							Month_Year=nextnode.getProperty("Month_Year").getString();

							JSONObject Month_YearJsonObject=new JSONObject();
							Month_YearJsonObject.put("Month_Year", Month_Year);
							if(Month_YearJsonObject.length()>0){
								Month_YearJsonarray.put(Month_YearJsonObject);
							}
						}

						if(nextnode.hasProperty("first_yr")){
							String first_yr="";
							first_yr=nextnode.getProperty("first_yr").getString();

							JSONObject first_yrJsonaobject=new JSONObject();
							first_yrJsonaobject.put("first_yr", first_yr);
							if(first_yrJsonaobject.length()>0){
								first_yrJsonarray.put(first_yrJsonaobject);
							}
						}


						if(nextnode.hasProperty("second_yr")){
							String second_yr="";
							second_yr=nextnode.getProperty("second_yr").getString();

							JSONObject second_yrJsonobject=new JSONObject();
							second_yrJsonobject.put("second_yr", second_yr);
							if(second_yrJsonobject.length()>0){
								second_yrJsonarray.put(second_yrJsonobject);
							}
						}
						if(nextnode.hasProperty("third_yr")){
							String third_yr="";
							third_yr=nextnode.getProperty("third_yr").getString();

							JSONObject third_yrJsonaobject=new JSONObject();
							third_yrJsonaobject.put("third_yr", third_yr);
							if(third_yrJsonaobject.length()>0){
								third_yrJsonarray.put(third_yrJsonaobject);
							}
						}
						if(nextnode.hasProperty("fifth_yr")){
							String fifth_yr="";
							fifth_yr=nextnode.getProperty("fifth_yr").getString();

							JSONObject fifth_yrJsonobject=new JSONObject();
							fifth_yrJsonobject.put("fifth_yr", fifth_yr);
							if(fifth_yrJsonobject.length()>0){
								fifth_yrJsonarray.put(fifth_yrJsonobject);
							}
						}

						keyNameObject=new JSONObject();

						keyNameObject.put("BrokerTcRateId", Id);
						keyNameObject.put("BrokerJsonarray", BrokerJsonarray);
						keyNameObject.put("VesselTypeJsonarray", VesselTypeJsonarray);
						keyNameObject.put("Month_YearJsonarray", Month_YearJsonarray);
						keyNameObject.put("first_yrJsonarray", first_yrJsonarray);
						keyNameObject.put("second_yrJsonarray", second_yrJsonarray);
						keyNameObject.put("third_yrJsonarray", third_yrJsonarray);
						keyNameObject.put("fifth_yrJsonarray", fifth_yrJsonarray);


						if(keyNameObject.length()>0){
							keyNameObjectJsonarray.put(keyNameObject);
						}

						limit= String.valueOf(Integer.parseInt(limit)+1);	

					} // while


				}

				if(!nodeFlag){
					keyNameObject=new JSONObject();
					keyNameObject.put("Error", "No New Data Available");
					keyNameObjectJsonarray.put(keyNameObject);

				}

				if(keyNameObjectJsonarray.length()>0){
					mainJsonobj=new JSONObject();
					mainJsonobj.put("BrokerTcRateList", keyNameObjectJsonarray);

				}

				Tonnage.setProperty("netChangedPointer",String.valueOf(size));
				session.save();


			}

		} catch (Exception e) {
//			out.println(e.getMessage());
			System.out.println(e.getMessage());

		}
		return mainJsonobj;

	}

	public static boolean isJSONValid(String test) {
		try {
			new JSONObject(test);
		} catch (JSONException ex) {
			// edited, to include @Arthur's comment
			// e.g. in case JSONArray is valid as well...
			try {
				new JSONArray(test);
			} catch (JSONException ex1) {
				return false;
			}
		}
		return true;
	}

	public static boolean searchNearestPlace(String v2txt)
	{
		boolean containsMnth = false;
		try {
			v2txt = v2txt.toLowerCase();
			String[] places = {"jan","january","feb","february","mar","march","apr","april",
					"may","jun","june","jul","july","aug","august","sep","sept","september",
					"oct","october","nov","november","dec","december"};

			for(int i = 0; i<= places.length - 1; i++)
			{
				if(v2txt.contains(places[i]))
				{
					containsMnth = true;
					break;
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println(e.getMessage());

		}	

		return containsMnth;
	}

	public static String getRateVal(String test) {
		String regexAlphOnly ="[^0-9.-//$//?//|//_]";

		Pattern pattern = Pattern.compile(regexAlphOnly);
		String regexNumOnly ="[^a-zA-Z.//$//?//|//_]";

		Pattern pattern1 = Pattern.compile(regexNumOnly);
		String rateType="";
		String rateTypeVal=null;
		String rate="";
		String []arr=  test.split("\\-|/", -1);
		//System.out.println(arr.length);
		boolean rateTypeFlag= false;
		for (String name:arr){
			//System.out.println("Name is "+name);
			Matcher matcher = pattern1.matcher(name);

			while (matcher.find()) {
				rate+=matcher.group();

			}

			Matcher matcher1 = pattern.matcher(name);
			while (matcher1.find()) {
				rateType+=matcher1.group();

			}
			if(!GmailMethods.isNullString(rateType)){
				switch(rateType.toUpperCase().trim())
				{
				case "LVL":
				case "K":
				case "M":{
					if(rateTypeFlag && !(GmailMethods.isNullString(rateTypeVal))){
						rateTypeVal = rateTypeVal+"-" + "LUMPSUM";
					}else{
						rateTypeVal="LUMPSUM";
					}

					break;
				}
				case "S":	  
				case "W":
				case "WS":{
					if(rateTypeFlag && !(GmailMethods.isNullString(rateTypeVal))){
						rateTypeVal = rateTypeVal+"-" + "WS";
					}else{
						rateTypeVal="WS";
					}
					break;
				}
				case "RNR":
				{ 
					if(rateTypeFlag && !(GmailMethods.isNullString(rateTypeVal))){
						rateTypeVal = rateTypeVal+"-" + "RNR";
					}else{
						rateTypeVal="RNR";
					}
					break; 
				}
				default:{
					if(rateTypeFlag && !(GmailMethods.isNullString(rateTypeVal))){
						if(!GmailMethods.isNullString(rate)){

							boolean setRateType = searchNearestPlace(test);
							if(setRateType){
								rateTypeVal ="";
							}
							else {
								rateTypeVal = rateTypeVal+"-" + "WS";
							}

						}else{
							rateTypeVal = rateTypeVal+"-" + "WS";
						}
					}
					if((GmailMethods.isNullString(rateTypeVal)) && (!GmailMethods.isNullString(rate))){
						boolean setRateType = searchNearestPlace(test);
						if(setRateType){
							rateTypeVal ="";
						}
						else {
							rateTypeVal = "WS";
						}
					}

					break;}
				}
				
			}else{
				rateTypeVal="WS";
			}
			rateType="";
			rateTypeFlag = true;  

		}
		/*if(GmailMethods.isNullString(rateTypeVal) && (!GmailMethods.isNullString(rate))){
			rateTypeVal = "WS";
		}
*/
		if(GmailMethods.isNullString(rate) && !("RNR".equalsIgnoreCase(rateTypeVal))){
			rateTypeVal = "";
		}
		return rateTypeVal;
	}

	public static String urlValue(String text){
		String[] s = {};
		String valueReturn = null;
		try {
			String regex = "^http://.*";

			boolean matches = Pattern.matches(regex, text);

			if(matches){
				s= text.split("#");
				//System.out.println(s[1]);
				valueReturn= s[1];
			}else{
				valueReturn= text;
			}
		} catch (Exception e) {
			// TODO: handle exception
			valueReturn= "";
		}
		return valueReturn;
	}

	public static String headersDataExcel(PrintWriter out, String allReport, Session session){
		JSONObject dataAll=null;
		String dataall="";
		try {

			JSONObject objectjson = new JSONObject(allReport);
			ChangedStructureCurrent_Methods CSC=new ChangedStructureCurrent_Methods();

			//...... for headers data .........
			//		if(objectjson.has("header_data")){
			if(objectjson.has("subject_HeadersData")){
				//			JSONArray header_data_jsonarray=objectjson.getJSONArray("header_data");
				JSONArray header_data_jsonarray=objectjson.getJSONArray("subject_HeadersData");

				String check_Port_repositionRegion_id="";
				out.println("before RepositionRegion_headers: ");

				for(int i=0;i<header_data_jsonarray.length();i++){
					JSONObject headerJsonobj=header_data_jsonarray.getJSONObject(i);
					String returnRepositionRegion="";
					if(headerJsonobj.has("RepositionRegion")){
						String RepositionRegionString=headerJsonobj.getString("RepositionRegion");
						returnRepositionRegion = urlValue(RepositionRegionString);
						if(!GmailMethods.isNullString(returnRepositionRegion)){
							if(returnRepositionRegion.contains("_")){
								returnRepositionRegion=  returnRepositionRegion.replace("_", " ");
								//out.println("returnDiscPortString: "+returnCargoType);
							}
							String check_Port_repositionRegion_id1=CSC.check_Port(session, returnRepositionRegion, out);
							boolean checkjsonString=isJSONValid(check_Port_repositionRegion_id1);
							if(checkjsonString==true){
								JSONObject data=new JSONObject(check_Port_repositionRegion_id1);
								if(data.has("port_id")){
									check_Port_repositionRegion_id=data.getString("port_id");
								}


							}
						}
					}// without key close
					else if(headerJsonobj.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion")){
						String RepositionRegionString=headerJsonobj.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion");
						returnRepositionRegion = urlValue(RepositionRegionString);
						if(!GmailMethods.isNullString(returnRepositionRegion)){
							if(returnRepositionRegion.contains("_")){
								returnRepositionRegion=  returnRepositionRegion.replace("_", " ");
								//out.println("returnDiscPortString: "+returnCargoType);
							}
							String check_Port_repositionRegion_id1=CSC.check_Port(session, returnRepositionRegion, out);
							boolean checkjsonString=isJSONValid(check_Port_repositionRegion_id1);
							if(checkjsonString==true){
								JSONObject data=new JSONObject(check_Port_repositionRegion_id1);
								if(data.has("port_id")){
									check_Port_repositionRegion_id=data.getString("port_id");
								}


							}
						}


					} // with key close

					if(GmailMethods.isNullString(check_Port_repositionRegion_id)){
						check_Port_repositionRegion_id=returnRepositionRegion;
					}

					out.println("before CargoType: ");
					String returnCargoType="";
					String check_CargoType_cargotype_id="";
					if(headerJsonobj.has("CargoType")){
						String CargoTypeString=headerJsonobj.getString("CargoType");
						returnCargoType = urlValue(CargoTypeString);
						if(returnCargoType.contains("_")){
							returnCargoType=  returnCargoType.replace("_", " ");
							//out.println("returnDiscPortString: "+returnCargoType);
						}
						check_CargoType_cargotype_id=CSC.check_CargoType(session, returnCargoType,out);
						out.println("CargoTypeString: "+returnCargoType);
						out.println("check_CargoType_cargotype_id: "+check_CargoType_cargotype_id);
					}// without key
					else if(headerJsonobj.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type")){
						String CargoTypeString=headerJsonobj.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type");
						returnCargoType = urlValue(CargoTypeString);
						if(returnCargoType.contains("_")){
							returnCargoType=  returnCargoType.replace("_", " ");
							//out.println("returnDiscPortString: "+returnCargoType);
						}
						check_CargoType_cargotype_id=CSC.check_CargoType(session, returnCargoType,out);
						out.println("CargoTypeString_url: "+returnCargoType);
						out.println("check_CargoType_cargotype_id_url: "+check_CargoType_cargotype_id);
					} // cargotype with key

					if(GmailMethods.isNullString(check_CargoType_cargotype_id)){
						check_CargoType_cargotype_id=returnCargoType;
					}

					String vesselType_id="";
					String returnVesselType_Name="";
					if(headerJsonobj.has("VesselType")){
						String VesselType_Name=headerJsonobj.getString("VesselType");
						returnVesselType_Name = urlValue(VesselType_Name);
						vesselType_id=ChangedStructureCurrent_Methods.check_vesselType(session, returnVesselType_Name, out);

					} // without key
					else if(headerJsonobj.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType")){
						String VesselType_Name=headerJsonobj.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType");
						returnVesselType_Name = urlValue(VesselType_Name);
						vesselType_id=ChangedStructureCurrent_Methods.check_vesselType(session, returnVesselType_Name, out);

					}// with key

					if(GmailMethods.isNullString(vesselType_id)){
						vesselType_id=returnVesselType_Name;
					}

					String ETABasisString="";
					String ETABasisString1="";
					if(headerJsonobj.has("ETABasis")){
						ETABasisString1=headerJsonobj.getString("ETABasis");
						ETABasisString = urlValue(ETABasisString1);
					}else if(headerJsonobj.has("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS")){
						ETABasisString1=headerJsonobj.getString("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ETABASIS");
						ETABasisString = urlValue(ETABasisString1);
					}// with key

					if(GmailMethods.isNullString(ETABasisString)){
						ETABasisString=ETABasisString1;
					}

					dataAll=new JSONObject();
					dataAll.put("RG", check_Port_repositionRegion_id);
					dataAll.put("CT", check_CargoType_cargotype_id);
					dataAll.put("VT", vesselType_id);
					dataAll.put("EB", ETABasisString);

					dataall=dataAll.toString();
				}
			}
			// ................. close headers data..........

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return dataall;
	}

	public static String headersDataPdf(PrintWriter out, String allReport, Session session){
		JSONObject dataAll=null;
		String dataall="";

		try {
			JSONObject objectjson = new JSONObject(allReport);
			ChangedStructureCurrent_Methods CSC=new ChangedStructureCurrent_Methods();

			if(objectjson.has("headerdata")){
				String check_Port_repositionRegion_id="";
				JSONArray header_data_jsonarray=objectjson.getJSONArray("headerdata");
				for(int i=0;i<header_data_jsonarray.length();i++){
					JSONObject headerJsonobj=header_data_jsonarray.getJSONObject(i);
					JSONObject header_data=headerJsonobj.getJSONObject("header_data");
					if(header_data.has("RepositionRegion")){
						String check_Port_repositionRegion_id1=header_data.getString("RepositionRegion");
						if(!GmailMethods.isNullString(check_Port_repositionRegion_id1)){

							check_Port_repositionRegion_id1=CSC.check_Port(session, check_Port_repositionRegion_id1, out);
							boolean checkjsonString=isJSONValid(check_Port_repositionRegion_id1);
							if(checkjsonString==true){
								JSONObject data=new JSONObject(check_Port_repositionRegion_id1);
								if(data.has("port_id")){
									check_Port_repositionRegion_id=data.getString("port_id");
								}


							}
						}

					} // reposition region

					String check_CargoType_cargotype_id="";
					if(header_data.has("CargoType")){
						String CargoTypeString=header_data.getString("CargoType");

						check_CargoType_cargotype_id=CSC.check_CargoType(session, CargoTypeString,out);
						out.println("CargoTypeString_pdf: "+CargoTypeString);
						out.println("check_CargoType_cargotype_id_pdf: "+check_CargoType_cargotype_id);
					}// without key

					String vesselType_id="";
					if(header_data.has("VesselType")){
						String VesselType_Name=header_data.getString("VesselType");
						vesselType_id=ChangedStructureCurrent_Methods.check_vesselType(session, VesselType_Name, out);

					} // without key
					String ETABasisString="";
					if(header_data.has("ETABasis")){
						ETABasisString=header_data.getString("ETABasis");
					}

					dataAll=new JSONObject();
					dataAll.put("RG", check_Port_repositionRegion_id);
					dataAll.put("CT", check_CargoType_cargotype_id);
					dataAll.put("VT", vesselType_id);
					dataAll.put("EB", ETABasisString);

					dataall=dataAll.toString();

				}

			}


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return dataall;
	}

}
