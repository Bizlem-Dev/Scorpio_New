package com.reportinformationsystem;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;

public class SyncXPdf {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			
			String vesselName="Hafnia Shanghai";
			if(vesselName.contains(" ")){
//				vesselName=vesselName.replace("", "_");
				vesselName=vesselName.replaceAll(" ", "_").toLowerCase();
				System.out.println(vesselName);
			}
			
			/*if(vesselName.contains("_")){
				vesselName=  vesselName.replace("_", " ");
				System.out.println("returnVesselName_after: "+vesselName);
		      }*/
		/*String jsonString = "{\r\n" + 
				"  \"BrokerTc\": [], \r\n" + 
				"  \"Spot\": [\r\n" + 
				"    {\r\n" + 
				"      \"CHRTS_x\": 38.640000000000001, \r\n" + 
				"      \"CargoGrade\": \"FO\", \r\n" + 
				"      \"CargoQty\": \"30\", \r\n" + 
				"      \"Charterer\": \"CSSA\", \r\n" + 
				"      \"DISCH_x\": 375.31, \r\n" + 
				"      \"DiscPort\": \"UKC\", \r\n" + 
				"      \"GDE_x\": 272.08999999999997, \r\n" + 
				"      \"LOAD_x\": 309.79000000000002, \r\n" + 
				"      \"LoadPort\": \"DONGES\", \r\n" + 
				"      \"QTY_x\": 245.56999999999999, \r\n" + 
				"      \"ReportType\": \"Spot\", \r\n" + 
				"      \"STATUS_x\": 117.38, \r\n" + 
				"      \"Status\": \"FXD\", \r\n" + 
				"      \"VESSEL_x\": 156.02000000000001, \r\n" + 
				"      \"VesselName\": \"CPO RUSSIA\", \r\n" + 
				"      \"unknown_0\": \"CSSA\", \r\n" + 
				"      \"unknown_1\": \"FXD\", \r\n" + 
				"      \"unknown_10\": \"220\", \r\n" + 
				"      \"unknown_2\": \"CPO RUSSIA\", \r\n" + 
				"      \"unknown_3\": \"30\", \r\n" + 
				"      \"unknown_4\": \"FO\", \r\n" + 
				"      \"unknown_5\": \"DONGES\", \r\n" + 
				"      \"unknown_6\": \"UKC\", \r\n" + 
				"      \"unknown_7\": \"04\", \r\n" + 
				"      \"unknown_8\": \"05/11\", \r\n" + 
				"      \"unknown_9\": \"WS\", \r\n" + 
				"      \"x_unknown_0\": 38.640000000000001, \r\n" + 
				"      \"x_unknown_1\": 117.38, \r\n" + 
				"      \"x_unknown_10\": 505.07999999999998, \r\n" + 
				"      \"x_unknown_2\": 156.02000000000001, \r\n" + 
				"      \"x_unknown_3\": 258.64999999999998, \r\n" + 
				"      \"x_unknown_4\": 272.08999999999997, \r\n" + 
				"      \"x_unknown_5\": 309.79000000000002, \r\n" + 
				"      \"x_unknown_6\": 375.31, \r\n" + 
				"      \"x_unknown_7\": 435.10000000000002, \r\n" + 
				"      \"x_unknown_8\": 446.62, \r\n" + 
				"      \"x_unknown_9\": 471.10000000000002\r\n" + 
				"    }, \r\n" + 
				"    {\r\n" + 
				"      \"CHRTS_x\": 38.640000000000001, \r\n" + 
				"      \"CargoGrade\": \"FO\", \r\n" + 
				"      \"CargoQty\": \"45\", \r\n" + 
				"      \"Charterer\": \"BP\", \r\n" + 
				"      \"DISCH_x\": 375.31, \r\n" + 
				"      \"DiscPort\": \"MED\", \r\n" + 
				"      \"GDE_x\": 272.08999999999997, \r\n" + 
				"      \"LOAD_x\": 309.79000000000002, \r\n" + 
				"      \"LoadPort\": \"GOTHENBURG\", \r\n" + 
				"      \"QTY_x\": 245.56999999999999, \r\n" + 
				"      \"ReportType\": \"Spot\", \r\n" + 
				"      \"VESSEL_x\": 156.02000000000001, \r\n" + 
				"      \"VesselName\": \"SCF NEVA\", \r\n" + 
				"      \"unknown_0\": \"BP\", \r\n" + 
				"      \"unknown_1\": \"SUBS\", \r\n" + 
				"      \"unknown_2\": \"SCF NEVA\", \r\n" + 
				"      \"unknown_3\": \"45\", \r\n" + 
				"      \"unknown_4\": \"FO\", \r\n" + 
				"      \"unknown_5\": \"GOTHENBURG\", \r\n" + 
				"      \"unknown_6\": \"MED\", \r\n" + 
				"      \"unknown_7\": \"05/11\", \r\n" + 
				"      \"unknown_8\": \"WS\", \r\n" + 
				"      \"unknown_9\": \"150\", \r\n" + 
				"      \"x_unknown_0\": 38.640000000000001, \r\n" + 
				"      \"x_unknown_1\": 117.38, \r\n" + 
				"      \"x_unknown_2\": 156.02000000000001, \r\n" + 
				"      \"x_unknown_3\": 258.64999999999998, \r\n" + 
				"      \"x_unknown_4\": 272.08999999999997, \r\n" + 
				"      \"x_unknown_5\": 309.79000000000002, \r\n" + 
				"      \"x_unknown_6\": 375.31, \r\n" + 
				"      \"x_unknown_7\": 446.62, \r\n" + 
				"      \"x_unknown_8\": 471.10000000000002, \r\n" + 
				"      \"x_unknown_9\": 505.07999999999998\r\n" + 
				"    }, \r\n" + 
				"    {\r\n" + 
				"      \"CHRTS_x\": 38.640000000000001, \r\n" + 
				"      \"Charterer\": \"OUTST CARGOES\", \r\n" + 
				"      \"ReportType\": \"Spot\", \r\n" + 
				"      \"unknown_0\": \"OUTST CARGOES\", \r\n" + 
				"      \"x_unknown_0\": 38.640000000000001\r\n" + 
				"    }, \r\n" + 
				"    {\r\n" + 
				"      \"CHRTS_x\": 38.640000000000001, \r\n" + 
				"      \"CargoGrade\": \"FO\", \r\n" + 
				"      \"CargoQty\": \"45\", \r\n" + 
				"      \"Charterer\": \"AEGEAN\", \r\n" + 
				"      \"DISCH_x\": 375.31, \r\n" + 
				"      \"DiscPort\": \"LAS PALMAS\", \r\n" + 
				"      \"GDE_x\": 272.08999999999997, \r\n" + 
				"      \"LOAD_x\": 309.79000000000002, \r\n" + 
				"      \"LoadPort\": \"LAS PALMAS\", \r\n" + 
				"      \"QTY_x\": 245.56999999999999, \r\n" + 
				"      \"ReportType\": \"Spot\", \r\n" + 
				"      \"unknown_0\": \"AEGEAN\", \r\n" + 
				"      \"unknown_1\": \"45\", \r\n" + 
				"      \"unknown_2\": \"FO\", \r\n" + 
				"      \"unknown_3\": \"ARA\", \r\n" + 
				"      \"unknown_4\": \"LAS PALMAS\", \r\n" + 
				"      \"unknown_5\": \"08\", \r\n" + 
				"      \"unknown_6\": \"09/11\", \r\n" + 
				"      \"x_unknown_0\": 38.640000000000001, \r\n" + 
				"      \"x_unknown_1\": 258.64999999999998, \r\n" + 
				"      \"x_unknown_2\": 272.08999999999997, \r\n" + 
				"      \"x_unknown_3\": 309.79000000000002, \r\n" + 
				"      \"x_unknown_4\": 375.31, \r\n" + 
				"      \"x_unknown_5\": 435.10000000000002, \r\n" + 
				"      \"x_unknown_6\": 446.62\r\n" + 
				"    }, \r\n" + 
				"    {\r\n" + 
				"      \"CHRTS_x\": 38.640000000000001, \r\n" + 
				"      \"CargoGrade\": \"FO\", \r\n" + 
				"      \"CargoQty\": \"45\", \r\n" + 
				"      \"Charterer\": \"LEVANTINE\", \r\n" + 
				"      \"Comment\": \"WDWF\", \r\n" + 
				"      \"DISCH_x\": 375.31, \r\n" + 
				"      \"DiscPort\": \"EMED\", \r\n" + 
				"      \"GDE_x\": 272.08999999999997, \r\n" + 
				"      \"LOAD_x\": 309.79000000000002, \r\n" + 
				"      \"LoadPort\": \"ARA\", \r\n" + 
				"      \"NOTES_x\": 543.24000000000001, \r\n" + 
				"      \"QTY_x\": 245.56999999999999, \r\n" + 
				"      \"ReportType\": \"Spot\", \r\n" + 
				"      \"unknown_0\": \"LEVANTINE\", \r\n" + 
				"      \"unknown_1\": \"45\", \r\n" + 
				"      \"unknown_2\": \"FO\", \r\n" + 
				"      \"unknown_3\": \"ARA\", \r\n" + 
				"      \"unknown_4\": \"EMED\", \r\n" + 
				"      \"unknown_5\": \"02\", \r\n" + 
				"      \"unknown_6\": \"05/11\", \r\n" + 
				"      \"unknown_7\": \"WDWF\", \r\n" + 
				"      \"x_unknown_0\": 38.640000000000001, \r\n" + 
				"      \"x_unknown_1\": 258.64999999999998, \r\n" + 
				"      \"x_unknown_2\": 272.08999999999997, \r\n" + 
				"      \"x_unknown_3\": 309.79000000000002, \r\n" + 
				"      \"x_unknown_4\": 375.31, \r\n" + 
				"      \"x_unknown_5\": 435.10000000000002, \r\n" + 
				"      \"x_unknown_6\": 446.62, \r\n" + 
				"      \"x_unknown_7\": 548.27999999999997\r\n" + 
				"    }, \r\n" + 
				"    {\r\n" + 
				"      \"CHRTS_x\": 38.640000000000001, \r\n" + 
				"      \"Charterer\": \"MED/BLSEA\", \r\n" + 
				"      \"ReportType\": \"Spot\", \r\n" + 
				"      \"unknown_0\": \"MED/BLSEA\", \r\n" + 
				"      \"x_unknown_0\": 38.640000000000001\r\n" + 
				"    }, \r\n" + 
				"    {\r\n" + 
				"      \"CHRTS_x\": 38.640000000000001, \r\n" + 
				"      \"CargoGrade\": \"FO/VGO\", \r\n" + 
				"      \"CargoQty\": \"30\", \r\n" + 
				"      \"Charterer\": \"UML\", \r\n" + 
				"      \"DISCH_x\": 375.31, \r\n" + 
				"      \"DiscPort\": \"MED\", \r\n" + 
				"      \"GDE_x\": 272.08999999999997, \r\n" + 
				"      \"LOAD_x\": 309.79000000000002, \r\n" + 
				"      \"LoadPort\": \"BSEA\", \r\n" + 
				"      \"QTY_x\": 245.56999999999999, \r\n" + 
				"      \"ReportType\": \"Spot\", \r\n" + 
				"      \"STATUS_x\": 117.38, \r\n" + 
				"      \"Status\": \"FXD\", \r\n" + 
				"      \"VESSEL_x\": 156.02000000000001, \r\n" + 
				"      \"VesselName\": \"QUARTZ\", \r\n" + 
				"      \"unknown_0\": \"UML\", \r\n" + 
				"      \"unknown_1\": \"FXD\", \r\n" + 
				"      \"unknown_10\": \"4/11\", \r\n" + 
				"      \"unknown_11\": \"WS\", \r\n" + 
				"      \"unknown_12\": \"260\", \r\n" + 
				"      \"unknown_2\": \"QUARTZ\", \r\n" + 
				"      \"unknown_3\": \"30\", \r\n" + 
				"      \"unknown_4\": \"FO/VGO\", \r\n" + 
				"      \"unknown_5\": \"BSEA\", \r\n" + 
				"      \"unknown_6\": \"MED\", \r\n" + 
				"      \"unknown_7\": \"0\", \r\n" + 
				"      \"unknown_8\": \"3\", \r\n" + 
				"      \"unknown_9\": \"0\", \r\n" + 
				"      \"x_unknown_0\": 38.640000000000001, \r\n" + 
				"      \"x_unknown_1\": 117.38, \r\n" + 
				"      \"x_unknown_10\": 451.06, \r\n" + 
				"      \"x_unknown_11\": 471.10000000000002, \r\n" + 
				"      \"x_unknown_12\": 505.07999999999998, \r\n" + 
				"      \"x_unknown_2\": 156.02000000000001, \r\n" + 
				"      \"x_unknown_3\": 258.64999999999998, \r\n" + 
				"      \"x_unknown_4\": 272.08999999999997, \r\n" + 
				"      \"x_unknown_5\": 309.79000000000002, \r\n" + 
				"      \"x_unknown_6\": 375.31, \r\n" + 
				"      \"x_unknown_7\": 435.10000000000002, \r\n" + 
				"      \"x_unknown_8\": 439.54000000000002, \r\n" + 
				"      \"x_unknown_9\": 446.62\r\n" + 
				"    }, \r\n" + 
				"    {\r\n" + 
				"      \"CHRTS_x\": 38.640000000000001, \r\n" + 
				"      \"CargoGrade\": \"FO/VGO\", \r\n" + 
				"      \"CargoQty\": \"30\", \r\n" + 
				"      \"Charterer\": \"LITASCO\", \r\n" + 
				"      \"DISCH_x\": 375.31, \r\n" + 
				"      \"DiscPort\": \"MED\", \r\n" + 
				"      \"GDE_x\": 272.08999999999997, \r\n" + 
				"      \"LOAD_x\": 309.79000000000002, \r\n" + 
				"      \"LoadPort\": \"ALEX\", \r\n" + 
				"      \"QTY_x\": 245.56999999999999, \r\n" + 
				"      \"ReportType\": \"Spot\", \r\n" + 
				"      \"VESSEL_x\": 156.02000000000001, \r\n" + 
				"      \"VesselName\": \"NORIENT SCORPIUS\", \r\n" + 
				"      \"unknown_0\": \"LITASCO\", \r\n" + 
				"      \"unknown_1\": \"SUBS\", \r\n" + 
				"      \"unknown_10\": \"240\", \r\n" + 
				"      \"unknown_2\": \"NORIENT SCORPIUS\", \r\n" + 
				"      \"unknown_3\": \"30\", \r\n" + 
				"      \"unknown_4\": \"FO/VGO\", \r\n" + 
				"      \"unknown_5\": \"ALEX\", \r\n" + 
				"      \"unknown_6\": \"MED\", \r\n" + 
				"      \"unknown_7\": \"05\", \r\n" + 
				"      \"unknown_8\": \"07/10\", \r\n" + 
				"      \"unknown_9\": \"WS\", \r\n" + 
				"      \"x_unknown_0\": 38.640000000000001, \r\n" + 
				"      \"x_unknown_1\": 117.38, \r\n" + 
				"      \"x_unknown_10\": 505.07999999999998, \r\n" + 
				"      \"x_unknown_2\": 156.02000000000001, \r\n" + 
				"      \"x_unknown_3\": 258.64999999999998, \r\n" + 
				"      \"x_unknown_4\": 272.08999999999997, \r\n" + 
				"      \"x_unknown_5\": 309.79000000000002, \r\n" + 
				"      \"x_unknown_6\": 375.31, \r\n" + 
				"      \"x_unknown_7\": 435.10000000000002, \r\n" + 
				"      \"x_unknown_8\": 446.62, \r\n" + 
				"      \"x_unknown_9\": 471.10000000000002\r\n" + 
				"    }, \r\n" + 
				"    {\r\n" + 
				"      \"CHRTS_x\": 38.640000000000001, \r\n" + 
				"      \"CargoGrade\": \"FO\", \r\n" + 
				"      \"CargoQty\": \"30\", \r\n" + 
				"      \"Charterer\": \"ENI\", \r\n" + 
				"      \"DISCH_x\": 375.31, \r\n" + 
				"      \"DiscPort\": \"ITALY\", \r\n" + 
				"      \"GDE_x\": 272.08999999999997, \r\n" + 
				"      \"LOAD_x\": 309.79000000000002, \r\n" + 
				"      \"LoadPort\": \"ITALY\", \r\n" + 
				"      \"QTY_x\": 245.56999999999999, \r\n" + 
				"      \"ReportType\": \"Spot\", \r\n" + 
				"      \"VESSEL_x\": 156.02000000000001, \r\n" + 
				"      \"VesselName\": \"BAUCI\", \r\n" + 
				"      \"unknown_0\": \"ENI\", \r\n" + 
				"      \"unknown_1\": \"SUBS\", \r\n" + 
				"      \"unknown_10\": \"WS\", \r\n" + 
				"      \"unknown_11\": \"230+10\", \r\n" + 
				"      \"unknown_2\": \"BAUCI\", \r\n" + 
				"      \"unknown_3\": \"30\", \r\n" + 
				"      \"unknown_4\": \"FO\", \r\n" + 
				"      \"unknown_5\": \"TARANTO\", \r\n" + 
				"      \"unknown_6\": \"MED\", \r\n" + 
				"      \"unknown_7\": \"ITALY\", \r\n" + 
				"      \"unknown_8\": \"03\", \r\n" + 
				"      \"unknown_9\": \"05/11\", \r\n" + 
				"      \"x_unknown_0\": 38.640000000000001, \r\n" + 
				"      \"x_unknown_1\": 117.38, \r\n" + 
				"      \"x_unknown_10\": 471.10000000000002, \r\n" + 
				"      \"x_unknown_11\": 491.5, \r\n" + 
				"      \"x_unknown_2\": 156.02000000000001, \r\n" + 
				"      \"x_unknown_3\": 258.64999999999998, \r\n" + 
				"      \"x_unknown_4\": 272.08999999999997, \r\n" + 
				"      \"x_unknown_5\": 309.79000000000002, \r\n" + 
				"      \"x_unknown_6\": 375.31, \r\n" + 
				"      \"x_unknown_7\": 395.74000000000001, \r\n" + 
				"      \"x_unknown_8\": 435.10000000000002, \r\n" + 
				"      \"x_unknown_9\": 446.62\r\n" + 
				"    }, \r\n" + 
				"    {\r\n" + 
				"      \"CHRTS_x\": 38.640000000000001, \r\n" + 
				"      \"CargoGrade\": \"FO\", \r\n" + 
				"      \"CargoQty\": \"30\", \r\n" + 
				"      \"Charterer\": \"UML\", \r\n" + 
				"      \"Comment\": \"RATE RMRD\", \r\n" + 
				"      \"DISCH_x\": 375.31, \r\n" + 
				"      \"DiscPort\": \"MED\", \r\n" + 
				"      \"GDE_x\": 272.08999999999997, \r\n" + 
				"      \"LOAD_x\": 309.79000000000002, \r\n" + 
				"      \"LoadPort\": \"FOS\", \r\n" + 
				"      \"NOTES_x\": 543.24000000000001, \r\n" + 
				"      \"QTY_x\": 245.56999999999999, \r\n" + 
				"      \"ReportType\": \"Spot\", \r\n" + 
				"      \"VESSEL_x\": 156.02000000000001, \r\n" + 
				"      \"VesselName\": \"HAFNIA BERING\", \r\n" + 
				"      \"unknown_0\": \"UML\", \r\n" + 
				"      \"unknown_1\": \"SUBS\", \r\n" + 
				"      \"unknown_10\": \"5/11\", \r\n" + 
				"      \"unknown_11\": \"WS\", \r\n" + 
				"      \"unknown_12\": \"235\", \r\n" + 
				"      \"unknown_13\": \"RATE RMRD\", \r\n" + 
				"      \"unknown_2\": \"HAFNIA BERING\", \r\n" + 
				"      \"unknown_3\": \"30\", \r\n" + 
				"      \"unknown_4\": \"FO\", \r\n" + 
				"      \"unknown_5\": \"FOS\", \r\n" + 
				"      \"unknown_6\": \"MED\", \r\n" + 
				"      \"unknown_7\": \"0\", \r\n" + 
				"      \"unknown_8\": \"4\", \r\n" + 
				"      \"unknown_9\": \"0\", \r\n" + 
				"      \"x_unknown_0\": 38.640000000000001, \r\n" + 
				"      \"x_unknown_1\": 117.38, \r\n" + 
				"      \"x_unknown_10\": 451.06, \r\n" + 
				"      \"x_unknown_11\": 471.10000000000002, \r\n" + 
				"      \"x_unknown_12\": 505.07999999999998, \r\n" + 
				"      \"x_unknown_13\": 529.20000000000005, \r\n" + 
				"      \"x_unknown_2\": 156.02000000000001, \r\n" + 
				"      \"x_unknown_3\": 258.64999999999998, \r\n" + 
				"      \"x_unknown_4\": 272.08999999999997, \r\n" + 
				"      \"x_unknown_5\": 309.79000000000002, \r\n" + 
				"      \"x_unknown_6\": 375.31, \r\n" + 
				"      \"x_unknown_7\": 435.10000000000002, \r\n" + 
				"      \"x_unknown_8\": 439.54000000000002, \r\n" + 
				"      \"x_unknown_9\": 446.62\r\n" + 
				"    }, \r\n" + 
				"    {\r\n" + 
				"      \"CHRTS_x\": 38.640000000000001, \r\n" + 
				"      \"CargoGrade\": \"DPP\", \r\n" + 
				"      \"CargoQty\": \"30\", \r\n" + 
				"      \"Charterer\": \"PETRACO\", \r\n" + 
				"      \"Comment\": \"S.O.M EX D/D\", \r\n" + 
				"      \"DISCH_x\": 375.31, \r\n" + 
				"      \"DiscPort\": \"MED\", \r\n" + 
				"      \"GDE_x\": 272.08999999999997, \r\n" + 
				"      \"LOAD_x\": 309.79000000000002, \r\n" + 
				"      \"LoadPort\": \"BSEA\", \r\n" + 
				"      \"NOTES_x\": 543.24000000000001, \r\n" + 
				"      \"QTY_x\": 245.56999999999999, \r\n" + 
				"      \"ReportType\": \"Spot\", \r\n" + 
				"      \"VESSEL_x\": 156.02000000000001, \r\n" + 
				"      \"VesselName\": \"SEAMERIT\", \r\n" + 
				"      \"unknown_0\": \"PETRACO\", \r\n" + 
				"      \"unknown_1\": \"SUBS\", \r\n" + 
				"      \"unknown_10\": \"S.O.M EX D/D\", \r\n" + 
				"      \"unknown_2\": \"SEAMERIT\", \r\n" + 
				"      \"unknown_3\": \"30\", \r\n" + 
				"      \"unknown_4\": \"DPP\", \r\n" + 
				"      \"unknown_5\": \"BSEA\", \r\n" + 
				"      \"unknown_6\": \"MED\", \r\n" + 
				"      \"unknown_7\": \"06/11\", \r\n" + 
				"      \"unknown_8\": \"WS\", \r\n" + 
				"      \"unknown_9\": \"260\", \r\n" + 
				"      \"x_unknown_0\": 38.640000000000001, \r\n" + 
				"      \"x_unknown_1\": 117.38, \r\n" + 
				"      \"x_unknown_10\": 525.72000000000003, \r\n" + 
				"      \"x_unknown_2\": 156.02000000000001, \r\n" + 
				"      \"x_unknown_3\": 258.64999999999998, \r\n" + 
				"      \"x_unknown_4\": 272.08999999999997, \r\n" + 
				"      \"x_unknown_5\": 309.79000000000002, \r\n" + 
				"      \"x_unknown_6\": 375.31, \r\n" + 
				"      \"x_unknown_7\": 446.62, \r\n" + 
				"      \"x_unknown_8\": 471.10000000000002, \r\n" + 
				"      \"x_unknown_9\": 505.07999999999998\r\n" + 
				"    }, \r\n" + 
				"    {\r\n" + 
				"      \"CHRTS_x\": 38.640000000000001, \r\n" + 
				"      \"CargoGrade\": \"FO\", \r\n" + 
				"      \"CargoQty\": \"30\", \r\n" + 
				"      \"Charterer\": \"ENI\", \r\n" + 
				"      \"Comment\": \"BACKLOAD\", \r\n" + 
				"      \"DISCH_x\": 375.31, \r\n" + 
				"      \"DiscPort\": \"MED\", \r\n" + 
				"      \"GDE_x\": 272.08999999999997, \r\n" + 
				"      \"LOAD_x\": 309.79000000000002, \r\n" + 
				"      \"LoadPort\": \"LEGHORN\", \r\n" + 
				"      \"NOTES_x\": 543.24000000000001, \r\n" + 
				"      \"QTY_x\": 245.56999999999999, \r\n" + 
				"      \"ReportType\": \"Spot\", \r\n" + 
				"      \"VESSEL_x\": 156.02000000000001, \r\n" + 
				"      \"VesselName\": \"SKYROS\", \r\n" + 
				"      \"unknown_0\": \"ENI\", \r\n" + 
				"      \"unknown_1\": \"SUBS\", \r\n" + 
				"      \"unknown_10\": \"230\", \r\n" + 
				"      \"unknown_11\": \"BACKLOAD\", \r\n" + 
				"      \"unknown_2\": \"SKYROS\", \r\n" + 
				"      \"unknown_3\": \"30\", \r\n" + 
				"      \"unknown_4\": \"FO\", \r\n" + 
				"      \"unknown_5\": \"LEGHORN\", \r\n" + 
				"      \"unknown_6\": \"MED\", \r\n" + 
				"      \"unknown_7\": \"07\", \r\n" + 
				"      \"unknown_8\": \"09/11\", \r\n" + 
				"      \"unknown_9\": \"WS\", \r\n" + 
				"      \"x_unknown_0\": 38.640000000000001, \r\n" + 
				"      \"x_unknown_1\": 117.38, \r\n" + 
				"      \"x_unknown_10\": 505.07999999999998, \r\n" + 
				"      \"x_unknown_11\": 532.67999999999995, \r\n" + 
				"      \"x_unknown_2\": 156.02000000000001, \r\n" + 
				"      \"x_unknown_3\": 258.64999999999998, \r\n" + 
				"      \"x_unknown_4\": 272.08999999999997, \r\n" + 
				"      \"x_unknown_5\": 309.79000000000002, \r\n" + 
				"      \"x_unknown_6\": 375.31, \r\n" + 
				"      \"x_unknown_7\": 435.10000000000002, \r\n" + 
				"      \"x_unknown_8\": 446.62, \r\n" + 
				"      \"x_unknown_9\": 471.10000000000002\r\n" + 
				"    }, \r\n" + 
				"    {\r\n" + 
				"      \"CHRTS_x\": 38.640000000000001, \r\n" + 
				"      \"CargoGrade\": \"DPP\", \r\n" + 
				"      \"CargoQty\": \"45\", \r\n" + 
				"      \"Charterer\": \"CNR\", \r\n" + 
				"      \"DISCH_x\": 375.31, \r\n" + 
				"      \"DiscPort\": \"MED\", \r\n" + 
				"      \"GDE_x\": 272.08999999999997, \r\n" + 
				"      \"LOAD_x\": 309.79000000000002, \r\n" + 
				"      \"LoadPort\": \"BSEA\", \r\n" + 
				"      \"QTY_x\": 245.56999999999999, \r\n" + 
				"      \"ReportType\": \"Spot\", \r\n" + 
				"      \"VESSEL_x\": 156.02000000000001, \r\n" + 
				"      \"VesselName\": \"KRITI ROCK\", \r\n" + 
				"      \"unknown_0\": \"CNR\", \r\n" + 
				"      \"unknown_1\": \"SUBS\", \r\n" + 
				"      \"unknown_2\": \"KRITI ROCK\", \r\n" + 
				"      \"unknown_3\": \"45\", \r\n" + 
				"      \"unknown_4\": \"DPP\", \r\n" + 
				"      \"unknown_5\": \"BSEA\", \r\n" + 
				"      \"unknown_6\": \"MED\", \r\n" + 
				"      \"unknown_7\": \"DNR\", \r\n" + 
				"      \"unknown_8\": \"WS\", \r\n" + 
				"      \"unknown_9\": \"165\", \r\n" + 
				"      \"x_unknown_0\": 38.640000000000001, \r\n" + 
				"      \"x_unknown_1\": 117.38, \r\n" + 
				"      \"x_unknown_2\": 156.02000000000001, \r\n" + 
				"      \"x_unknown_3\": 258.64999999999998, \r\n" + 
				"      \"x_unknown_4\": 272.08999999999997, \r\n" + 
				"      \"x_unknown_5\": 309.79000000000002, \r\n" + 
				"      \"x_unknown_6\": 375.31, \r\n" + 
				"      \"x_unknown_7\": 449.38, \r\n" + 
				"      \"x_unknown_8\": 471.10000000000002, \r\n" + 
				"      \"x_unknown_9\": 505.07999999999998\r\n" + 
				"    }, \r\n" + 
				"    {\r\n" + 
				"      \"CHRTS_x\": 38.640000000000001, \r\n" + 
				"      \"Charterer\": \"OUTST CARGOES\", \r\n" + 
				"      \"ReportType\": \"Spot\", \r\n" + 
				"      \"unknown_0\": \"OUTST CARGOES\", \r\n" + 
				"      \"x_unknown_0\": 38.640000000000001\r\n" + 
				"    }, \r\n" + 
				"    {\r\n" + 
				"      \"CHRTS_x\": 38.640000000000001, \r\n" + 
				"      \"CargoGrade\": \"DPP\", \r\n" + 
				"      \"CargoQty\": \"35\", \r\n" + 
				"      \"Charterer\": \"BP\", \r\n" + 
				"      \"DISCH_x\": 375.31, \r\n" + 
				"      \"DiscPort\": \"MED\", \r\n" + 
				"      \"GDE_x\": 272.08999999999997, \r\n" + 
				"      \"LOAD_x\": 309.79000000000002, \r\n" + 
				"      \"LoadPort\": \"BIZERTE\", \r\n" + 
				"      \"QTY_x\": 245.56999999999999, \r\n" + 
				"      \"ReportType\": \"Spot\", \r\n" + 
				"      \"unknown_0\": \"BP\", \r\n" + 
				"      \"unknown_1\": \"30\", \r\n" + 
				"      \"unknown_2\": \"35\", \r\n" + 
				"      \"unknown_3\": \"DPP\", \r\n" + 
				"      \"unknown_4\": \"BIZERTE\", \r\n" + 
				"      \"unknown_5\": \"MED\", \r\n" + 
				"      \"unknown_6\": \"06\", \r\n" + 
				"      \"unknown_7\": \"07/11\", \r\n" + 
				"      \"x_unknown_0\": 38.640000000000001, \r\n" + 
				"      \"x_unknown_1\": 245.56999999999999, \r\n" + 
				"      \"x_unknown_2\": 257.08999999999997, \r\n" + 
				"      \"x_unknown_3\": 272.08999999999997, \r\n" + 
				"      \"x_unknown_4\": 309.79000000000002, \r\n" + 
				"      \"x_unknown_5\": 375.31, \r\n" + 
				"      \"x_unknown_6\": 435.10000000000002, \r\n" + 
				"      \"x_unknown_7\": 446.62\r\n" + 
				"    }, \r\n" + 
				"    {\r\n" + 
				"      \"CHRTS_x\": 38.640000000000001, \r\n" + 
				"      \"CargoGrade\": \"FO\", \r\n" + 
				"      \"CargoQty\": \"30\", \r\n" + 
				"      \"Charterer\": \"PETROINEOS\", \r\n" + 
				"      \"DISCH_x\": 375.31, \r\n" + 
				"      \"DiscPort\": \"MED\", \r\n" + 
				"      \"GDE_x\": 272.08999999999997, \r\n" + 
				"      \"LOAD_x\": 309.79000000000002, \r\n" + 
				"      \"LoadPort\": \"ALGECIRAS\", \r\n" + 
				"      \"QTY_x\": 245.56999999999999, \r\n" + 
				"      \"ReportType\": \"Spot\", \r\n" + 
				"      \"unknown_0\": \"PETROINEOS\", \r\n" + 
				"      \"unknown_1\": \"30\", \r\n" + 
				"      \"unknown_2\": \"FO\", \r\n" + 
				"      \"unknown_3\": \"ALGECIRAS\", \r\n" + 
				"      \"unknown_4\": \"MED\", \r\n" + 
				"      \"unknown_5\": \"06\", \r\n" + 
				"      \"unknown_6\": \"08/11\", \r\n" + 
				"      \"x_unknown_0\": 38.640000000000001, \r\n" + 
				"      \"x_unknown_1\": 258.64999999999998, \r\n" + 
				"      \"x_unknown_2\": 272.08999999999997, \r\n" + 
				"      \"x_unknown_3\": 309.79000000000002, \r\n" + 
				"      \"x_unknown_4\": 375.31, \r\n" + 
				"      \"x_unknown_5\": 435.10000000000002, \r\n" + 
				"      \"x_unknown_6\": 446.62\r\n" + 
				"    }, \r\n" + 
				"    {\r\n" + 
				"      \"CHRTS_x\": 38.640000000000001, \r\n" + 
				"      \"Charterer\": \"PANAMAX\", \r\n" + 
				"      \"ReportType\": \"Spot\", \r\n" + 
				"      \"unknown_0\": \"PANAMAX\", \r\n" + 
				"      \"unknown_1\": \"\\u2019\", \r\n" + 
				"      \"unknown_2\": \"S\", \r\n" + 
				"      \"x_unknown_0\": 38.640000000000001, \r\n" + 
				"      \"x_unknown_1\": 78.983999999999995, \r\n" + 
				"      \"x_unknown_2\": 81.263999999999996\r\n" + 
				"    }, \r\n" + 
				"    {\r\n" + 
				"      \"ReportType\": \"Spot\", \r\n" + 
				"      \"unknown_0\": \"BRS GENEVA\", \r\n" + 
				"      \"unknown_1\": \"DPP\", \r\n" + 
				"      \"x_unknown_0\": 165.41, \r\n" + 
				"      \"x_unknown_1\": 209.69\r\n" + 
				"    }, \r\n" + 
				"    {\r\n" + 
				"      \"ReportType\": \"Spot\", \r\n" + 
				"      \"unknown_0\": \"tankerdirty\", \r\n" + 
				"      \"unknown_1\": \"@brsbrokers.com\", \r\n" + 
				"      \"x_unknown_0\": 165.41, \r\n" + 
				"      \"x_unknown_1\": 201.53\r\n" + 
				"    }, \r\n" + 
				"    {\r\n" + 
				"      \"CargoQty\": \"+ 41 22 591 88 15\", \r\n" + 
				"      \"QTY_x\": 245.56999999999999, \r\n" + 
				"      \"ReportType\": \"Spot\", \r\n" + 
				"      \"unknown_0\": \"Richard Valentine\", \r\n" + 
				"      \"unknown_1\": \"D/L\", \r\n" + 
				"      \"unknown_2\": \"+ 41 22 591 88 15\", \r\n" + 
				"      \"unknown_3\": \"Mob\", \r\n" + 
				"      \"unknown_4\": \"+ 4\", \r\n" + 
				"      \"unknown_5\": \"1 79 466 86 15\", \r\n" + 
				"      \"x_unknown_0\": 165.41, \r\n" + 
				"      \"x_unknown_1\": 248.09, \r\n" + 
				"      \"x_unknown_2\": 261.29000000000002, \r\n" + 
				"      \"x_unknown_3\": 331.87, \r\n" + 
				"      \"x_unknown_4\": 348.91000000000003, \r\n" + 
				"      \"x_unknown_5\": 358.75\r\n" + 
				"    }, \r\n" + 
				"    {\r\n" + 
				"      \"CargoQty\": \"D/L + 41 22 591 28\", \r\n" + 
				"      \"QTY_x\": 245.56999999999999, \r\n" + 
				"      \"ReportType\": \"Spot\", \r\n" + 
				"      \"unknown_0\": \"Ermanno Taschini\", \r\n" + 
				"      \"unknown_1\": \"D/L + 41 22 591 28\", \r\n" + 
				"      \"unknown_2\": \"18\", \r\n" + 
				"      \"unknown_3\": \"Mob\", \r\n" + 
				"      \"unknown_4\": \"+ 41 79 337 89 70\", \r\n" + 
				"      \"x_unknown_0\": 165.41, \r\n" + 
				"      \"x_unknown_1\": 248.33000000000001, \r\n" + 
				"      \"x_unknown_2\": 311.23000000000002, \r\n" + 
				"      \"x_unknown_3\": 331.99000000000001, \r\n" + 
				"      \"x_unknown_4\": 349.02999999999997\r\n" + 
				"    }, \r\n" + 
				"    {\r\n" + 
				"      \"CargoQty\": \"+ 41 22 591 29 45\", \r\n" + 
				"      \"QTY_x\": 245.56999999999999, \r\n" + 
				"      \"ReportType\": \"Spot\", \r\n" + 
				"      \"unknown_0\": \"Elliott Kaye\", \r\n" + 
				"      \"unknown_1\": \"D/L\", \r\n" + 
				"      \"unknown_2\": \"+ 41 22 591 29 45\", \r\n" + 
				"      \"unknown_3\": \"Mob +\", \r\n" + 
				"      \"unknown_4\": \"41 79 331 73 66\", \r\n" + 
				"      \"x_unknown_0\": 165.41, \r\n" + 
				"      \"x_unknown_1\": 248.69, \r\n" + 
				"      \"x_unknown_2\": 261.88999999999999, \r\n" + 
				"      \"x_unknown_3\": 332.47000000000003, \r\n" + 
				"      \"x_unknown_4\": 355.26999999999998\r\n" + 
				"    }\r\n" + 
				"  ], \r\n" + 
				"  \"TimeChater\": [], \r\n" + 
				"  \"Tonnage\": []\r\n" + 
				"}";*/
		//JSONObject obj = new JSONObject(jsonString);
		/*JSONObject mainJson = new JSONObject();
		JSONArray spot = SyncXPdf.getSpot(jsonString);
		JSONArray tonnage = SyncXPdf.getTonnage(jsonString);
		JSONArray timeCharter = SyncXPdf.getTimeCharter(jsonString);
		JSONArray brokerTc = SyncXPdf.getBrokerTc(jsonString);*/
		/*mainJson.put("Spot", spot);
		mainJson.put("Tonnage", tonnage);
		mainJson.put("TimeChater", timeCharter);
		mainJson.put("BrokerTc", brokerTc);
		System.out.println("mainJSon  :: "+mainJson);*/
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static JSONArray getSpot(String jsonString, PrintWriter out) {
		//JSONArray spotArr = null;
		JSONArray newJsonArr = new JSONArray();
		try {
			JSONObject obj = new JSONObject(jsonString);
//			System.out.println("spotArr.length()  :: "+obj.has("Spot"));
			JSONArray spotArr = obj.getJSONArray("Spot");
			//System.out.println("spotArr.length()  :: "+spotArr.length());
			
			Set<Object> setsX = new HashSet<>();
			if(spotArr.toString().indexOf("VesselName") != -1){
			for(int i=0;i<spotArr.length();i++) {
				JSONObject singleSpotJson = spotArr.getJSONObject(i);
		//		System.out.println(singleSpotJson);
				if(singleSpotJson.has("ReportType") && singleSpotJson.has("VesselName")) {
				 Iterator keys = singleSpotJson.keys();

				    while(keys.hasNext()) {
				        // loop to get the dynamic key
				        String currentDynamicKey = (String)keys.next();
				        if(currentDynamicKey.startsWith("x_unknown_")) {
				        //System.out.println("currentDynamicKey  :: "+currentDynamicKey);
				        setsX.add(singleSpotJson.getInt(currentDynamicKey));
				        }
				    }
				}
				
			}
			}else{
				for(int i=0;i<spotArr.length();i++) {
					JSONObject singleSpotJson = spotArr.getJSONObject(i);
			//		System.out.println(singleSpotJson);
					if(singleSpotJson.has("ReportType")) {
					 Iterator keys = singleSpotJson.keys();

					    while(keys.hasNext()) {
					        // loop to get the dynamic key
					        String currentDynamicKey = (String)keys.next();
					        if(currentDynamicKey.startsWith("x_unknown_")) {
					        //System.out.println("currentDynamicKey  :: "+currentDynamicKey);
					        setsX.add(singleSpotJson.getInt(currentDynamicKey));
					        }
					    }
					}
					
				}

			}
			//System.out.println("setsX  ::  "+setsX);
			Set<Object> sortedX = new TreeSet<>(setsX);
			//out.println("sortedX  ::  "+sortedX);
			//out.println("sortedX  Size ::  "+sortedX.size());
			JSONObject newSingleJson = null;
			if(spotArr.toString().indexOf("VesselName") != -1){
			for(int i=0;i<spotArr.length();i++) {
				JSONObject singleSpotJson = spotArr.getJSONObject(i);
			//	System.out.println(singleSpotJson);
				newSingleJson = new JSONObject();
				if(singleSpotJson.has("ReportType") && singleSpotJson.has("VesselName")) {
				 Iterator keys = singleSpotJson.keys();
				 
				    while(keys.hasNext()) {
				        // loop to get the dynamic key
				    	String currentDynamicKey = (String)keys.next();
				        Object currentDynamicValue = singleSpotJson.get(currentDynamicKey);
					    if(currentDynamicKey.startsWith("x_unknown_")) {
				       // System.out.println("currentDynamicKey  :: "+currentDynamicKey);
				        //System.out.println("currentDynamicValue  :: "+currentDynamicValue);
								
				        }else if(currentDynamicKey.startsWith("unknown_")) {
				        	
				        }else {
				        	newSingleJson.put(currentDynamicKey, currentDynamicValue);
				        }

				    }
				    int unknownC = 0;
				    Iterator value = sortedX.iterator(); 
						while (value.hasNext()) {
								String keyMatch = "nomatch";
								String unknownKey = "0";
								String valueX = value.next().toString();
								//System.out.println("valueX  :: "+valueX);
								Iterator keysMap = singleSpotJson.keys();
					        	while(keysMap.hasNext()) {
							    	// loop to get the dynamic key
							    	String currentDynamicKey = (String)keysMap.next();
							        Object currentDynamicValue = singleSpotJson.get(currentDynamicKey);
							        if(currentDynamicKey.startsWith("x_unknown_")) {
//							    		System.out.println("currentDynamicKey  :: "+currentDynamicKey);
//							    		System.out.println("currentDynamicValue  :: "+singleSpotJson.getInt(currentDynamicKey));
							    		if(String.valueOf(singleSpotJson.getInt(currentDynamicKey)).equals(valueX)) {
								    			//System.out.println("match  :: "+currentDynamicValue);
								    			keyMatch = "match";
								    			unknownKey = currentDynamicKey.substring(currentDynamicKey.lastIndexOf("_")+1, currentDynamicKey.length());
									    		//System.out.println("unknownKey  ::  "+unknownKey);	
								    			break;
								    		}
								    	}
							    }
							    if(keyMatch.equals("match")) {
							    	//System.out.println("match :: unknownC :: "+unknownC);
							    	newSingleJson.put("unknown_"+unknownC, singleSpotJson.get("unknown_"+unknownKey));
							    }else {
							    	//System.out.println("no match :: unknownC :: "+unknownC);
							    	newSingleJson.put("unknown_"+unknownC, "");
							    }
							    unknownC++;
						}

				    
				    newJsonArr.put(newSingleJson);
				}
				
			}
			}else{
				for(int i=0;i<spotArr.length();i++) {
					JSONObject singleSpotJson = spotArr.getJSONObject(i);
				//	System.out.println(singleSpotJson);
					newSingleJson = new JSONObject();
					if(singleSpotJson.has("ReportType")) {
					 Iterator keys = singleSpotJson.keys();
					 
					    while(keys.hasNext()) {
					        // loop to get the dynamic key
					    	String currentDynamicKey = (String)keys.next();
					        Object currentDynamicValue = singleSpotJson.get(currentDynamicKey);
						    if(currentDynamicKey.startsWith("x_unknown_")) {
					       // System.out.println("currentDynamicKey  :: "+currentDynamicKey);
					        //System.out.println("currentDynamicValue  :: "+currentDynamicValue);
									
					        }else if(currentDynamicKey.startsWith("unknown_")) {
					        	
					        }else {
					        	newSingleJson.put(currentDynamicKey, currentDynamicValue);
					        }

					    }
					    int unknownC = 0;
					    Iterator value = sortedX.iterator(); 
							while (value.hasNext()) {
									String keyMatch = "nomatch";
									String unknownKey = "0";
									String valueX = value.next().toString();
									//System.out.println("valueX  :: "+valueX);
									Iterator keysMap = singleSpotJson.keys();
						        	while(keysMap.hasNext()) {
								    	// loop to get the dynamic key
								    	String currentDynamicKey = (String)keysMap.next();
								        Object currentDynamicValue = singleSpotJson.get(currentDynamicKey);
								        if(currentDynamicKey.startsWith("x_unknown_")) {
//								    		System.out.println("currentDynamicKey  :: "+currentDynamicKey);
//								    		System.out.println("currentDynamicValue  :: "+singleSpotJson.getInt(currentDynamicKey));
								    		if(String.valueOf(singleSpotJson.getInt(currentDynamicKey)).equals(valueX)) {
									    			//System.out.println("match  :: "+currentDynamicValue);
									    			keyMatch = "match";
									    			unknownKey = currentDynamicKey.substring(currentDynamicKey.lastIndexOf("_")+1, currentDynamicKey.length());
										    		//System.out.println("unknownKey  ::  "+unknownKey);	
									    			break;
									    		}
									    	}
								    }
								    if(keyMatch.equals("match")) {
								    	//System.out.println("match :: unknownC :: "+unknownC);
								    	newSingleJson.put("unknown_"+unknownC, singleSpotJson.get("unknown_"+unknownKey));
								    }else {
								    	//System.out.println("no match :: unknownC :: "+unknownC);
								    	newSingleJson.put("unknown_"+unknownC, "");
								    }
								    unknownC++;
							}

					    
					    newJsonArr.put(newSingleJson);
					}
					
				}

			}
		 out.println("new Arr :: "+newJsonArr);

		}catch (Exception e) {
			// TODO: handle exception
		}
		return newJsonArr;
	}

	public static JSONArray getTonnage(String jsonString, PrintWriter  out) {
		//JSONArray spotArr = null;
		JSONArray newJsonArr = new JSONArray();
		try {
			JSONObject obj = new JSONObject(jsonString);
			JSONArray spotArr = obj.getJSONArray("Tonnage");
			//System.out.println("spotArr.length()  :: "+spotArr.length());
			
			Set<Object> setsX = new HashSet<>();
			if(spotArr.toString().indexOf("VesselName") != -1){
			for(int i=0;i<spotArr.length();i++) {
				JSONObject singleSpotJson = spotArr.getJSONObject(i);
		//		System.out.println(singleSpotJson);
				if(singleSpotJson.has("ReportType") && singleSpotJson.has("VesselName")) {
				 Iterator keys = singleSpotJson.keys();

				    while(keys.hasNext()) {
				        // loop to get the dynamic key
				        String currentDynamicKey = (String)keys.next();
				        if(currentDynamicKey.startsWith("x_unknown_")) {
				        //System.out.println("currentDynamicKey  :: "+currentDynamicKey);
				        //setsX.add(singleSpotJson.getDouble(currentDynamicKey));
				        setsX.add(singleSpotJson.getInt(currentDynamicKey));
				        }
				    }
				}
				
			}
			}else{
				for(int i=0;i<spotArr.length();i++) {
					JSONObject singleSpotJson = spotArr.getJSONObject(i);
			//		System.out.println(singleSpotJson);
					if(singleSpotJson.has("ReportType")) {
					 Iterator keys = singleSpotJson.keys();

					    while(keys.hasNext()) {
					        // loop to get the dynamic key
					        String currentDynamicKey = (String)keys.next();
					        if(currentDynamicKey.startsWith("x_unknown_")) {
					        //System.out.println("currentDynamicKey  :: "+currentDynamicKey);
					        //setsX.add(singleSpotJson.getDouble(currentDynamicKey));
					        setsX.add(singleSpotJson.getInt(currentDynamicKey));
					        }
					    }
					}
					
				}
			}
			//System.out.println("setsX  ::  "+setsX);
			Set<Object> sortedX = new TreeSet<>(setsX);
			//System.out.println("sortedX  ::  "+sortedX);
			//System.out.println("sortedX  Size ::  "+sortedX.size());
			JSONObject newSingleJson = null;
			if(spotArr.toString().indexOf("VesselName") != -1){
			for(int i=0;i<spotArr.length();i++) {
				JSONObject singleSpotJson = spotArr.getJSONObject(i);
			//	System.out.println(singleSpotJson);
				newSingleJson = new JSONObject();
				if(singleSpotJson.has("ReportType") && singleSpotJson.has("VesselName")) {
				 Iterator keys = singleSpotJson.keys();
				 
				    while(keys.hasNext()) {
				        // loop to get the dynamic key
				    	String currentDynamicKey = (String)keys.next();
				        Object currentDynamicValue = singleSpotJson.get(currentDynamicKey);
					    if(currentDynamicKey.startsWith("x_unknown_")) {
				       // System.out.println("currentDynamicKey  :: "+currentDynamicKey);
				        //System.out.println("currentDynamicValue  :: "+currentDynamicValue);
								
				        }else if(currentDynamicKey.startsWith("unknown_")) {
				        	
				        }else {
				        	newSingleJson.put(currentDynamicKey, currentDynamicValue);
				        }

				    }
				    int unknownC = 0;
				    Iterator value = sortedX.iterator(); 
						while (value.hasNext()) {
								String keyMatch = "nomatch";
								String unknownKey = "0";
								String valueX = value.next().toString();
								//System.out.println("valueX  :: "+valueX);
								Iterator keysMap = singleSpotJson.keys();
					        	while(keysMap.hasNext()) {
							    	// loop to get the dynamic key
							    	String currentDynamicKey = (String)keysMap.next();
							        Object currentDynamicValue = singleSpotJson.get(currentDynamicKey);
								    	if(currentDynamicKey.startsWith("x_unknown_")) {
//								    		System.out.println("currentDynamicKey  :: "+currentDynamicKey);
//								    		System.out.println("currentDynamicValue  :: "+singleSpotJson.getInt(currentDynamicKey));
								    		if(String.valueOf(singleSpotJson.getInt(currentDynamicKey)).equals(valueX)) {
								    			//System.out.println("match  :: "+currentDynamicValue);
								    			keyMatch = "match";
								    			unknownKey = currentDynamicKey.substring(currentDynamicKey.lastIndexOf("_")+1, currentDynamicKey.length());
									    		//System.out.println("unknownKey  ::  "+unknownKey);	
								    			break;
								    		}
								    	}
							    }
							    if(keyMatch.equals("match")) {
							    	//System.out.println("match :: unknownC :: "+unknownC);
							    	newSingleJson.put("unknown_"+unknownC, singleSpotJson.get("unknown_"+unknownKey));
							    }else {
							    	//System.out.println("no match :: unknownC :: "+unknownC);
							    	newSingleJson.put("unknown_"+unknownC, "");
							    }
							    unknownC++;
						}

				    
				    newJsonArr.put(newSingleJson);
				}
				
			}
			}else{
				for(int i=0;i<spotArr.length();i++) {
					JSONObject singleSpotJson = spotArr.getJSONObject(i);
				//	System.out.println(singleSpotJson);
					newSingleJson = new JSONObject();
					if(singleSpotJson.has("ReportType")) {
					 Iterator keys = singleSpotJson.keys();
					 
					    while(keys.hasNext()) {
					        // loop to get the dynamic key
					    	String currentDynamicKey = (String)keys.next();
					        Object currentDynamicValue = singleSpotJson.get(currentDynamicKey);
						    if(currentDynamicKey.startsWith("x_unknown_")) {
					       // System.out.println("currentDynamicKey  :: "+currentDynamicKey);
					        //System.out.println("currentDynamicValue  :: "+currentDynamicValue);
									
					        }else if(currentDynamicKey.startsWith("unknown_")) {
					        	
					        }else {
					        	newSingleJson.put(currentDynamicKey, currentDynamicValue);
					        }

					    }
					    int unknownC = 0;
					    Iterator value = sortedX.iterator(); 
							while (value.hasNext()) {
									String keyMatch = "nomatch";
									String unknownKey = "0";
									String valueX = value.next().toString();
									//System.out.println("valueX  :: "+valueX);
									Iterator keysMap = singleSpotJson.keys();
						        	while(keysMap.hasNext()) {
								    	// loop to get the dynamic key
								    	String currentDynamicKey = (String)keysMap.next();
								        Object currentDynamicValue = singleSpotJson.get(currentDynamicKey);
									    	if(currentDynamicKey.startsWith("x_unknown_")) {
//									    		System.out.println("currentDynamicKey  :: "+currentDynamicKey);
//									    		System.out.println("currentDynamicValue  :: "+singleSpotJson.getInt(currentDynamicKey));
									    		if(String.valueOf(singleSpotJson.getInt(currentDynamicKey)).equals(valueX)) {
									    			//System.out.println("match  :: "+currentDynamicValue);
									    			keyMatch = "match";
									    			unknownKey = currentDynamicKey.substring(currentDynamicKey.lastIndexOf("_")+1, currentDynamicKey.length());
										    		//System.out.println("unknownKey  ::  "+unknownKey);	
									    			break;
									    		}
									    	}
								    }
								    if(keyMatch.equals("match")) {
								    	//System.out.println("match :: unknownC :: "+unknownC);
								    	newSingleJson.put("unknown_"+unknownC, singleSpotJson.get("unknown_"+unknownKey));
								    }else {
								    	//System.out.println("no match :: unknownC :: "+unknownC);
								    	newSingleJson.put("unknown_"+unknownC, "");
								    }
								    unknownC++;
							}

					    
					    newJsonArr.put(newSingleJson);
					}
					
				}

			}
			out.println("new Arr :: "+newJsonArr);

		}catch (Exception e) {
			// TODO: handle exception
		}
		return newJsonArr;
	}
	
	public static JSONArray getTimeCharter(String jsonString) {
		//JSONArray spotArr = null;
		JSONArray newJsonArr = new JSONArray();
		try {
			JSONObject obj = new JSONObject(jsonString);
			JSONArray spotArr = obj.getJSONArray("TimeChater");
			//System.out.println("spotArr.length()  :: "+spotArr.length());
			
			Set<Object> setsX = new HashSet<>();
			if(spotArr.toString().indexOf("VesselName") != -1){
			for(int i=0;i<spotArr.length();i++) {
				JSONObject singleSpotJson = spotArr.getJSONObject(i);
		//		System.out.println(singleSpotJson);
				if(singleSpotJson.has("ReportType") && singleSpotJson.has("VesselName")) {
				 Iterator keys = singleSpotJson.keys();

				    while(keys.hasNext()) {
				        // loop to get the dynamic key
				        String currentDynamicKey = (String)keys.next();
				        if(currentDynamicKey.startsWith("x_unknown_")) {
				        //System.out.println("currentDynamicKey  :: "+currentDynamicKey);
				        setsX.add(singleSpotJson.getInt(currentDynamicKey));
				        }
				    }
				}
				
			}
			}else{
				
					for(int i=0;i<spotArr.length();i++) {
						JSONObject singleSpotJson = spotArr.getJSONObject(i);
				//		System.out.println(singleSpotJson);
						if(singleSpotJson.has("ReportType")) {
						 Iterator keys = singleSpotJson.keys();

						    while(keys.hasNext()) {
						        // loop to get the dynamic key
						        String currentDynamicKey = (String)keys.next();
						        if(currentDynamicKey.startsWith("x_unknown_")) {
						        //System.out.println("currentDynamicKey  :: "+currentDynamicKey);
						        setsX.add(singleSpotJson.getInt(currentDynamicKey));
						        }
						    }
						}
						
					}
			}
			//System.out.println("setsX  ::  "+setsX);
			Set<Object> sortedX = new TreeSet<>(setsX);
			JSONObject newSingleJson = null;
			if(spotArr.toString().indexOf("VesselName") != -1){
			//System.out.println("sortedX  ::  "+sortedX);
			//System.out.println("sortedX  Size ::  "+sortedX.size());
			
			
			for(int i=0;i<spotArr.length();i++) {
				JSONObject singleSpotJson = spotArr.getJSONObject(i);
			//	System.out.println(singleSpotJson);
				newSingleJson = new JSONObject();
				if(singleSpotJson.has("ReportType") && singleSpotJson.has("VesselName")) {
				 Iterator keys = singleSpotJson.keys();
				 
				    while(keys.hasNext()) {
				        // loop to get the dynamic key
				    	String currentDynamicKey = (String)keys.next();
				        Object currentDynamicValue = singleSpotJson.get(currentDynamicKey);
					    if(currentDynamicKey.startsWith("x_unknown_")) {
				       // System.out.println("currentDynamicKey  :: "+currentDynamicKey);
				        //System.out.println("currentDynamicValue  :: "+currentDynamicValue);
								
				        }else if(currentDynamicKey.startsWith("unknown_")) {
				        	
				        }else {
				        	newSingleJson.put(currentDynamicKey, currentDynamicValue);
				        }

				    }
				    int unknownC = 0;
				    Iterator value = sortedX.iterator(); 
						while (value.hasNext()) {
								String keyMatch = "nomatch";
								String unknownKey = "0";
								String valueX = value.next().toString();
								//System.out.println("valueX  :: "+valueX);
								Iterator keysMap = singleSpotJson.keys();
					        	while(keysMap.hasNext()) {
							    	// loop to get the dynamic key
							    	String currentDynamicKey = (String)keysMap.next();
							        Object currentDynamicValue = singleSpotJson.get(currentDynamicKey);
							        if(currentDynamicKey.startsWith("x_unknown_")) {
//							    		System.out.println("currentDynamicKey  :: "+currentDynamicKey);
//							    		System.out.println("currentDynamicValue  :: "+singleSpotJson.getInt(currentDynamicKey));
							    		if(String.valueOf(singleSpotJson.getInt(currentDynamicKey)).equals(valueX)) {
								    			//System.out.println("match  :: "+currentDynamicValue);
								    			keyMatch = "match";
								    			unknownKey = currentDynamicKey.substring(currentDynamicKey.lastIndexOf("_")+1, currentDynamicKey.length());
									    		//System.out.println("unknownKey  ::  "+unknownKey);	
								    			break;
								    		}
								    	}
							    }
							    if(keyMatch.equals("match")) {
							    	//System.out.println("match :: unknownC :: "+unknownC);
							    	newSingleJson.put("unknown_"+unknownC, singleSpotJson.get("unknown_"+unknownKey));
							    }else {
							    	//System.out.println("no match :: unknownC :: "+unknownC);
							    	newSingleJson.put("unknown_"+unknownC, "");
							    }
							    unknownC++;
						}

				    
				    newJsonArr.put(newSingleJson);
				}
				
			}
			}else{
				for(int i=0;i<spotArr.length();i++) {
					JSONObject singleSpotJson = spotArr.getJSONObject(i);
				//	System.out.println(singleSpotJson);
					newSingleJson = new JSONObject();
					if(singleSpotJson.has("ReportType")) {
					 Iterator keys = singleSpotJson.keys();
					 
					    while(keys.hasNext()) {
					        // loop to get the dynamic key
					    	String currentDynamicKey = (String)keys.next();
					        Object currentDynamicValue = singleSpotJson.get(currentDynamicKey);
						    if(currentDynamicKey.startsWith("x_unknown_")) {
					       // System.out.println("currentDynamicKey  :: "+currentDynamicKey);
					        //System.out.println("currentDynamicValue  :: "+currentDynamicValue);
									
					        }else if(currentDynamicKey.startsWith("unknown_")) {
					        	
					        }else {
					        	newSingleJson.put(currentDynamicKey, currentDynamicValue);
					        }

					    }
					    int unknownC = 0;
					    Iterator value = sortedX.iterator(); 
							while (value.hasNext()) {
									String keyMatch = "nomatch";
									String unknownKey = "0";
									String valueX = value.next().toString();
									//System.out.println("valueX  :: "+valueX);
									Iterator keysMap = singleSpotJson.keys();
						        	while(keysMap.hasNext()) {
								    	// loop to get the dynamic key
								    	String currentDynamicKey = (String)keysMap.next();
								        Object currentDynamicValue = singleSpotJson.get(currentDynamicKey);
									    	if(currentDynamicKey.startsWith("x_unknown_")) {
//									    		System.out.println("currentDynamicKey  :: "+currentDynamicKey);
//									    		System.out.println("currentDynamicValue  :: "+singleSpotJson.getInt(currentDynamicKey));
									    		if(String.valueOf(singleSpotJson.getInt(currentDynamicKey)).equals(valueX)) {
									    			//System.out.println("match  :: "+currentDynamicValue);
									    			keyMatch = "match";
									    			unknownKey = currentDynamicKey.substring(currentDynamicKey.lastIndexOf("_")+1, currentDynamicKey.length());
										    		//System.out.println("unknownKey  ::  "+unknownKey);	
									    			break;
									    		}
									    	}
								    }
								    if(keyMatch.equals("match")) {
								    	//System.out.println("match :: unknownC :: "+unknownC);
								    	newSingleJson.put("unknown_"+unknownC, singleSpotJson.get("unknown_"+unknownKey));
								    }else {
								    	//System.out.println("no match :: unknownC :: "+unknownC);
								    	newSingleJson.put("unknown_"+unknownC, "");
								    }
								    unknownC++;
							}

					    
					    newJsonArr.put(newSingleJson);
					}
					
				}
			}
			//System.out.println("new Arr :: "+newJsonArr);

		}catch (Exception e) {
			// TODO: handle exception
		}
		return newJsonArr;
	}

	public static JSONArray getBrokerTc(String jsonString) {
		//JSONArray spotArr = null;
		JSONArray newJsonArr = new JSONArray();
		try {
			JSONObject obj = new JSONObject(jsonString);
			JSONArray spotArr = obj.getJSONArray("BrokerTc");
			//System.out.println("spotArr.length()  :: "+spotArr.length());
			
			Set<Object> setsX = new HashSet<>();
			if(spotArr.toString().indexOf("VesselType") != -1){
			for(int i=0;i<spotArr.length();i++) {
				JSONObject singleSpotJson = spotArr.getJSONObject(i);
		//		System.out.println(singleSpotJson);
				if(singleSpotJson.has("ReportType") && singleSpotJson.has("VesselType")) {
				 Iterator keys = singleSpotJson.keys();

				    while(keys.hasNext()) {
				        // loop to get the dynamic key
				        String currentDynamicKey = (String)keys.next();
				        if(currentDynamicKey.startsWith("x_unknown_")) {
				        //System.out.println("currentDynamicKey  :: "+currentDynamicKey);
				        setsX.add(singleSpotJson.getInt(currentDynamicKey));
				        }
				    }
				}
				
			}
			}else{
				
				for(int i=0;i<spotArr.length();i++) {
					JSONObject singleSpotJson = spotArr.getJSONObject(i);
			//		System.out.println(singleSpotJson);
					if(singleSpotJson.has("ReportType")) {
					 Iterator keys = singleSpotJson.keys();

					    while(keys.hasNext()) {
					        // loop to get the dynamic key
					        String currentDynamicKey = (String)keys.next();
					        if(currentDynamicKey.startsWith("x_unknown_")) {
					        //System.out.println("currentDynamicKey  :: "+currentDynamicKey);
					        setsX.add(singleSpotJson.getInt(currentDynamicKey));
					        }
					    }
					}
					
				}
				
			}
			//System.out.println("setsX  ::  "+setsX);
			Set<Object> sortedX = new TreeSet<>(setsX);
			//System.out.println("sortedX  ::  "+sortedX);
			//System.out.println("sortedX  Size ::  "+sortedX.size());
			JSONObject newSingleJson = null;
			if(spotArr.toString().indexOf("VesselType") != -1){
			for(int i=0;i<spotArr.length();i++) {
				JSONObject singleSpotJson = spotArr.getJSONObject(i);
			//	System.out.println(singleSpotJson);
				newSingleJson = new JSONObject();
				if(singleSpotJson.has("ReportType") && singleSpotJson.has("VesselType")) {
				 Iterator keys = singleSpotJson.keys();
				 
				    while(keys.hasNext()) {
				        // loop to get the dynamic key
				    	String currentDynamicKey = (String)keys.next();
				        Object currentDynamicValue = singleSpotJson.get(currentDynamicKey);
					    if(currentDynamicKey.startsWith("x_unknown_")) {
				       // System.out.println("currentDynamicKey  :: "+currentDynamicKey);
				        //System.out.println("currentDynamicValue  :: "+currentDynamicValue);
								
				        }else if(currentDynamicKey.startsWith("unknown_")) {
				        	
				        }else {
				        	newSingleJson.put(currentDynamicKey, currentDynamicValue);
				        }

				    }
				    int unknownC = 0;
				    Iterator value = sortedX.iterator(); 
						while (value.hasNext()) {
								String keyMatch = "nomatch";
								String unknownKey = "0";
								String valueX = value.next().toString();
								//System.out.println("valueX  :: "+valueX);
								Iterator keysMap = singleSpotJson.keys();
					        	while(keysMap.hasNext()) {
							    	// loop to get the dynamic key
							    	String currentDynamicKey = (String)keysMap.next();
							        Object currentDynamicValue = singleSpotJson.get(currentDynamicKey);
							        if(currentDynamicKey.startsWith("x_unknown_")) {
//							    		System.out.println("currentDynamicKey  :: "+currentDynamicKey);
//							    		System.out.println("currentDynamicValue  :: "+singleSpotJson.getInt(currentDynamicKey));
							    		if(String.valueOf(singleSpotJson.getInt(currentDynamicKey)).equals(valueX)) {
								    			//System.out.println("match  :: "+currentDynamicValue);
								    			keyMatch = "match";
								    			unknownKey = currentDynamicKey.substring(currentDynamicKey.lastIndexOf("_")+1, currentDynamicKey.length());
									    		//System.out.println("unknownKey  ::  "+unknownKey);	
								    			break;
								    		}
								    	}
							    }
							    if(keyMatch.equals("match")) {
							    	//System.out.println("match :: unknownC :: "+unknownC);
							    	newSingleJson.put("unknown_"+unknownC, singleSpotJson.get("unknown_"+unknownKey));
							    }else {
							    	//System.out.println("no match :: unknownC :: "+unknownC);
							    	newSingleJson.put("unknown_"+unknownC, "");
							    }
							    unknownC++;
						}

				    
				    newJsonArr.put(newSingleJson);
				}
			}
			//...
			}else {
				for(int i=0;i<spotArr.length();i++) {
					JSONObject singleSpotJson = spotArr.getJSONObject(i);
				//	System.out.println(singleSpotJson);
					newSingleJson = new JSONObject();
					if(singleSpotJson.has("ReportType") && singleSpotJson.has("VesselType")) {
					 Iterator keys = singleSpotJson.keys();
					 
					    while(keys.hasNext()) {
					        // loop to get the dynamic key
					    	String currentDynamicKey = (String)keys.next();
					        Object currentDynamicValue = singleSpotJson.get(currentDynamicKey);
						    if(currentDynamicKey.startsWith("x_unknown_")) {
					       // System.out.println("currentDynamicKey  :: "+currentDynamicKey);
					        //System.out.println("currentDynamicValue  :: "+currentDynamicValue);
									
					        }else if(currentDynamicKey.startsWith("unknown_")) {
					        	
					        }else {
					        	newSingleJson.put(currentDynamicKey, currentDynamicValue);
					        }

					    }
					    int unknownC = 0;
					    Iterator value = sortedX.iterator(); 
							while (value.hasNext()) {
									String keyMatch = "nomatch";
									String unknownKey = "0";
									String valueX = value.next().toString();
									//System.out.println("valueX  :: "+valueX);
									Iterator keysMap = singleSpotJson.keys();
						        	while(keysMap.hasNext()) {
								    	// loop to get the dynamic key
								    	String currentDynamicKey = (String)keysMap.next();
								        Object currentDynamicValue = singleSpotJson.get(currentDynamicKey);
								        if(currentDynamicKey.startsWith("x_unknown_")) {
//								    		System.out.println("currentDynamicKey  :: "+currentDynamicKey);
//								    		System.out.println("currentDynamicValue  :: "+singleSpotJson.getInt(currentDynamicKey));
								    		if(String.valueOf(singleSpotJson.getInt(currentDynamicKey)).equals(valueX)) {
									    			//System.out.println("match  :: "+currentDynamicValue);
									    			keyMatch = "match";
									    			unknownKey = currentDynamicKey.substring(currentDynamicKey.lastIndexOf("_")+1, currentDynamicKey.length());
										    		//System.out.println("unknownKey  ::  "+unknownKey);	
									    			break;
									    		}
									    	}
								    }
								    if(keyMatch.equals("match")) {
								    	//System.out.println("match :: unknownC :: "+unknownC);
								    	newSingleJson.put("unknown_"+unknownC, singleSpotJson.get("unknown_"+unknownKey));
								    }else {
								    	//System.out.println("no match :: unknownC :: "+unknownC);
								    	newSingleJson.put("unknown_"+unknownC, "");
								    }
								    unknownC++;
							}

					    
					    newJsonArr.put(newSingleJson);
					}
				}
			}
			//System.out.println("new Arr :: "+newJsonArr);

		}catch (Exception e) {
			// TODO: handle exception
		}
		return newJsonArr;
	}
	
	
	/*public static JSONArray getTimeCharter(String jsonString) {
		//JSONArray spotArr = null;
		JSONArray newJsonArr = new JSONArray();
		try {
			JSONObject obj = new JSONObject(jsonString);
			JSONArray spotArr = obj.getJSONArray("TimeChater");
			//System.out.println("spotArr.length()  :: "+spotArr.length());
			
			Set<Object> setsX = new HashSet<>();
			for(int i=0;i<spotArr.length();i++) {
				JSONObject singleSpotJson = spotArr.getJSONObject(i);
		//		System.out.println(singleSpotJson);
				if(singleSpotJson.has("ReportType") && singleSpotJson.has("VesselName")) {
				 Iterator keys = singleSpotJson.keys();

				    while(keys.hasNext()) {
				        // loop to get the dynamic key
				        String currentDynamicKey = (String)keys.next();
				        if(currentDynamicKey.startsWith("x_unknown_")) {
				        //System.out.println("currentDynamicKey  :: "+currentDynamicKey);
				        setsX.add(singleSpotJson.getInt(currentDynamicKey));
				        }
				    }
				}
				
			}
			//System.out.println("setsX  ::  "+setsX);
			Set<Object> sortedX = new TreeSet<>(setsX);
			//System.out.println("sortedX  ::  "+sortedX);
			//System.out.println("sortedX  Size ::  "+sortedX.size());
			JSONObject newSingleJson = null;
			
			for(int i=0;i<spotArr.length();i++) {
				JSONObject singleSpotJson = spotArr.getJSONObject(i);
			//	System.out.println(singleSpotJson);
				newSingleJson = new JSONObject();
				if(singleSpotJson.has("ReportType") && singleSpotJson.has("VesselName")) {
				 Iterator keys = singleSpotJson.keys();
				 
				    while(keys.hasNext()) {
				        // loop to get the dynamic key
				    	String currentDynamicKey = (String)keys.next();
				        Object currentDynamicValue = singleSpotJson.get(currentDynamicKey);
					    if(currentDynamicKey.startsWith("x_unknown_")) {
				       // System.out.println("currentDynamicKey  :: "+currentDynamicKey);
				        //System.out.println("currentDynamicValue  :: "+currentDynamicValue);
								
				        }else if(currentDynamicKey.startsWith("unknown_")) {
				        	
				        }else {
				        	newSingleJson.put(currentDynamicKey, currentDynamicValue);
				        }

				    }
				    int unknownC = 0;
				    Iterator value = sortedX.iterator(); 
						while (value.hasNext()) {
								String keyMatch = "nomatch";
								String unknownKey = "0";
								String valueX = value.next().toString();
								//System.out.println("valueX  :: "+valueX);
								Iterator keysMap = singleSpotJson.keys();
					        	while(keysMap.hasNext()) {
							    	// loop to get the dynamic key
							    	String currentDynamicKey = (String)keysMap.next();
							        Object currentDynamicValue = singleSpotJson.get(currentDynamicKey);
							        if(currentDynamicKey.startsWith("x_unknown_")) {
//							    		System.out.println("currentDynamicKey  :: "+currentDynamicKey);
//							    		System.out.println("currentDynamicValue  :: "+singleSpotJson.getInt(currentDynamicKey));
							    		if(String.valueOf(singleSpotJson.getInt(currentDynamicKey)).equals(valueX)) {
								    			//System.out.println("match  :: "+currentDynamicValue);
								    			keyMatch = "match";
								    			unknownKey = currentDynamicKey.substring(currentDynamicKey.lastIndexOf("_")+1, currentDynamicKey.length());
									    		//System.out.println("unknownKey  ::  "+unknownKey);	
								    			break;
								    		}
								    	}
							    }
							    if(keyMatch.equals("match")) {
							    	//System.out.println("match :: unknownC :: "+unknownC);
							    	newSingleJson.put("unknown_"+unknownC, singleSpotJson.get("unknown_"+unknownKey));
							    }else {
							    	//System.out.println("no match :: unknownC :: "+unknownC);
							    	newSingleJson.put("unknown_"+unknownC, "");
							    }
							    unknownC++;
						}

				    
				    newJsonArr.put(newSingleJson);
				}
				
			}
			//System.out.println("new Arr :: "+newJsonArr);

		}catch (Exception e) {
			// TODO: handle exception
		}
		return newJsonArr;
	}

	public static JSONArray getBrokerTc(String jsonString) {
		//JSONArray spotArr = null;
		JSONArray newJsonArr = new JSONArray();
		try {
			JSONObject obj = new JSONObject(jsonString);
			JSONArray spotArr = obj.getJSONArray("BrokerTc");
			//System.out.println("spotArr.length()  :: "+spotArr.length());
			
			Set<Object> setsX = new HashSet<>();
			for(int i=0;i<spotArr.length();i++) {
				JSONObject singleSpotJson = spotArr.getJSONObject(i);
		//		System.out.println(singleSpotJson);
				if(singleSpotJson.has("ReportType") && singleSpotJson.has("VesselName")) {
				 Iterator keys = singleSpotJson.keys();

				    while(keys.hasNext()) {
				        // loop to get the dynamic key
				        String currentDynamicKey = (String)keys.next();
				        if(currentDynamicKey.startsWith("x_unknown_")) {
				        //System.out.println("currentDynamicKey  :: "+currentDynamicKey);
				        setsX.add(singleSpotJson.getInt(currentDynamicKey));
				        }
				    }
				}
				
			}
			//System.out.println("setsX  ::  "+setsX);
			Set<Object> sortedX = new TreeSet<>(setsX);
			//System.out.println("sortedX  ::  "+sortedX);
			//System.out.println("sortedX  Size ::  "+sortedX.size());
			JSONObject newSingleJson = null;
			
			for(int i=0;i<spotArr.length();i++) {
				JSONObject singleSpotJson = spotArr.getJSONObject(i);
			//	System.out.println(singleSpotJson);
				newSingleJson = new JSONObject();
				if(singleSpotJson.has("ReportType") && singleSpotJson.has("VesselName")) {
				 Iterator keys = singleSpotJson.keys();
				 
				    while(keys.hasNext()) {
				        // loop to get the dynamic key
				    	String currentDynamicKey = (String)keys.next();
				        Object currentDynamicValue = singleSpotJson.get(currentDynamicKey);
					    if(currentDynamicKey.startsWith("x_unknown_")) {
				       // System.out.println("currentDynamicKey  :: "+currentDynamicKey);
				        //System.out.println("currentDynamicValue  :: "+currentDynamicValue);
								
				        }else if(currentDynamicKey.startsWith("unknown_")) {
				        	
				        }else {
				        	newSingleJson.put(currentDynamicKey, currentDynamicValue);
				        }

				    }
				    int unknownC = 0;
				    Iterator value = sortedX.iterator(); 
						while (value.hasNext()) {
								String keyMatch = "nomatch";
								String unknownKey = "0";
								String valueX = value.next().toString();
								//System.out.println("valueX  :: "+valueX);
								Iterator keysMap = singleSpotJson.keys();
					        	while(keysMap.hasNext()) {
							    	// loop to get the dynamic key
							    	String currentDynamicKey = (String)keysMap.next();
							        Object currentDynamicValue = singleSpotJson.get(currentDynamicKey);
							        if(currentDynamicKey.startsWith("x_unknown_")) {
//							    		System.out.println("currentDynamicKey  :: "+currentDynamicKey);
//							    		System.out.println("currentDynamicValue  :: "+singleSpotJson.getInt(currentDynamicKey));
							    		if(String.valueOf(singleSpotJson.getInt(currentDynamicKey)).equals(valueX)) {
								    			//System.out.println("match  :: "+currentDynamicValue);
								    			keyMatch = "match";
								    			unknownKey = currentDynamicKey.substring(currentDynamicKey.lastIndexOf("_")+1, currentDynamicKey.length());
									    		//System.out.println("unknownKey  ::  "+unknownKey);	
								    			break;
								    		}
								    	}
							    }
							    if(keyMatch.equals("match")) {
							    	//System.out.println("match :: unknownC :: "+unknownC);
							    	newSingleJson.put("unknown_"+unknownC, singleSpotJson.get("unknown_"+unknownKey));
							    }else {
							    	//System.out.println("no match :: unknownC :: "+unknownC);
							    	newSingleJson.put("unknown_"+unknownC, "");
							    }
							    unknownC++;
						}

				    
				    newJsonArr.put(newSingleJson);
				}
				
			}
			//System.out.println("new Arr :: "+newJsonArr);

		}catch (Exception e) {
			// TODO: handle exception
		}
		return newJsonArr;
	}*/

}
