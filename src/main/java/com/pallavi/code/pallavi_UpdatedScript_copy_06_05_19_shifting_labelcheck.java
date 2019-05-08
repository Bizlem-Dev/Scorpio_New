package com.pallavi.code;


import java.io.BufferedReader;



import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.jcr.Session;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import com.reportinformationsystem.SaveReportDataClass;

public class pallavi_UpdatedScript_copy_06_05_19_shifting_labelcheck {
	/**
	 * @param args
	 * @throws JSONException 
	 * @throws UnsupportedEncodingException 
	 */
	//public static void main(String[] args) throws JSONException   {
//	//	String a = "{  \"table_0\": {    \"header_data\": [      {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CLEAN\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Eastport\"          }        ],         \"line_index\": 0,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#ObjectProperty\",             \"word\": \"size\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#Class\",             \"word\": \"Size\"          }        ],         \"line_index\": 1,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Best\"          }        ],         \"line_index\": 2,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Date\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"date\"          }        ],         \"line_index\": 3,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CLEAN\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Eastport\"          }        ],         \"line_index\": 4,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Jnpt\"          }        ],         \"line_index\": 8,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"jet\"          }        ],         \"line_index\": 10,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"bp\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Vadinar\"          }        ],         \"line_index\": 11,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Paradip\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          }        ],         \"line_index\": 12,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Sitra\"          }        ],         \"line_index\": 13,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Salalah\"          }        ],         \"line_index\": 14,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\",             \"word\": \"fixtures\"          }        ],         \"line_index\": 15,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"STI GRACE\"          }        ],         \"line_index\": 19,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"FRONT TIGER\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          }        ],         \"line_index\": 20,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"PLOUTOS\"          }        ],         \"line_index\": 21,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"shell\"          }        ],         \"line_index\": 22,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"PACIFIC MARTINA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"shell\"          }        ],         \"line_index\": 23,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Ec India\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          }        ],         \"line_index\": 24,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"BW CLYDE\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          }        ],         \"line_index\": 25,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"NAVE ATROPOS\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          }        ],         \"line_index\": 26,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"A.R.A.\"          }        ],         \"line_index\": 27,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"INDIA\"          }        ],         \"line_index\": 30,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"KITION M\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Sikka\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion\",             \"word\": \"SIKKA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"cssa\"          }        ],         \"line_index\": 32,         \"number_of_words\": 6      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"JAG PRANAM\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Paradip\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          }        ],         \"line_index\": 33,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"TORM CARINA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Kandla\"          }        ],         \"line_index\": 36,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"ARDMORE SEAHAWK\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Haldia\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          }        ],         \"line_index\": 37,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ulsd\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Jinzhou\"          }        ],         \"line_index\": 40,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Zhoushan\"          }        ],         \"line_index\": 41,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"trafigura\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Yokkaichi\"          }        ],         \"line_index\": 42,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"petrolimex\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Vietnam\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion\",             \"word\": \"S KOREA\"          }        ],         \"line_index\": 43,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Dalian\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          }        ],         \"line_index\": 44,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          }        ],         \"line_index\": 47,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Balongan\"          }        ],         \"line_index\": 48,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\",             \"word\": \"spot\"          }        ],         \"line_index\": 49,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"VINALINES GLORY\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Balongan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          }        ],         \"line_index\": 53,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Philippines\"          }        ],         \"line_index\": 54,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"China\"          }        ],         \"line_index\": 57,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Russia\"          }        ],         \"line_index\": 60,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"bp\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"SKS DELTA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          }        ],         \"line_index\": 62,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          }        ],         \"line_index\": 63,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Australia-Nz\"          }        ],         \"line_index\": 64,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"OCEAN COSMOS\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Jakarta\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          }        ],         \"line_index\": 69,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"ATLANTIC INFINITY\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Esperance\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          }        ],         \"line_index\": 70,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\",             \"word\": \"fixtures\"          }        ],         \"line_index\": 71,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          }        ],         \"line_index\": 73,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\",             \"word\": \"fixtures\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          }        ],         \"line_index\": 76,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"blt\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"CHRYSANTHEMUM\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"lyras\"          }        ],         \"line_index\": 78,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"JING YU ZUO\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          }        ],         \"line_index\": 79,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"vitol\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"SPOTTAIL\"          }        ],         \"line_index\": 80,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"cbm\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"damico\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"HIGH PERFORMANCE\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Ii\"          }        ],         \"line_index\": 81,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"cbm\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"MISS MARIAROSARIA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          }        ],         \"line_index\": 82,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"montanari\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"VALLE AZZURRA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Ii\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          }        ],         \"line_index\": 83,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"HIGH SUN\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"DWT\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Ii\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"damico\"          }        ],         \"line_index\": 84,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"DWT\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"koch\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"JAG PUNIT\"          }        ],         \"line_index\": 85,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Leo\"          }        ],         \"line_index\": 88,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Eastport\"          }        ],         \"line_index\": 89,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Eastport\"          }        ],         \"line_index\": 96,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Or\"          }        ],         \"line_index\": 97,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"hold\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Or\"          }        ],         \"line_index\": 98,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Us\"          }        ],         \"line_index\": 99,         \"number_of_words\": 1      }    ],     \"table_data\": [      {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"EASTPORT\",           \"1\": \"CLEAN\",           \"2\": \"MARKET_REPORT_DATED_08TH_APR_2019\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\"        },         \"line_index\": 0      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"THIS\",           \"1\": \"MESSAGE\",           \"2\": \"WAS\",           \"3\": \"PREPARED\",           \"4\": \"IN\",           \"5\": \"COURIER\",           \"6\": \"NEW\",           \"7\": \"FONT\",           \"8\": \"SIZE\",           \"9\": \"10.\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\",           \"8\": \"unknown_8\",           \"9\": \"unknown_9\"        },         \"line_index\": 1      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"PLEASE\",           \"1\": \"ARRANGE\",           \"2\": \"ACCORDINGLY\",           \"3\": \"FOR\",           \"4\": \"BEST\",           \"5\": \"VIEW.\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\"        },         \"line_index\": 2      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"EASTPORT\",           \"1\": \"CLEAN\",           \"2\": \"MARKET\",           \"3\": \"REPORT\",           \"4\": \"DATED\",           \"5\": \"08TH\",           \"6\": \"APR\",           \"7\": \"2019\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\"        },         \"line_index\": 4      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"IOC\",           \"1\": \"38\",           \"2\": \"CPP\",           \"3\": \"JNPT\",           \"4\": \"KANDLA\",           \"5\": \"14-15/04\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\"        },         \"line_index\": 8      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"GPC\",           \"1\": \"35\",           \"2\": \"JET\",           \"3\": \"N.MANG/OPTS\",           \"4\": \"16-18/04\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\"        },         \"line_index\": 10      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"BP\",           \"1\": \"35\",           \"2\": \"GO\",           \"3\": \"VADINAR\",           \"4\": \"OPTS\",           \"5\": \"11-15/04\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\"        },         \"line_index\": 11      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"IOC\",           \"1\": \"16\",           \"2\": \"CPP\",           \"3\": \"PARADIP\",           \"4\": \"HALDIA\",           \"5\": \"13-14/04\",           \"6\": \"ON\",           \"7\": \"SUBS\",           \"8\": \"BELOW\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\",           \"8\": \"unknown_8\"        },         \"line_index\": 12      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"MSC\",           \"1\": \"310KB\",           \"2\": \"F76\",           \"3\": \"SITRA\",           \"4\": \"J.ALI-FUJ\",           \"5\": \"22-23/04\",           \"6\": \"CLOSING\",           \"7\": \"10/04,\",           \"8\": \"1100H\",           \"9\": \"EST\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\",           \"8\": \"unknown_8\",           \"9\": \"unknown_9\"        },         \"line_index\": 13      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"STI GRACE\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"JAPAN\",           \"4\": \"22/04\",           \"5\": \"W97.5\",           \"6\": \"ATC=FLD\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\"        },         \"line_index\": 19      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"FRONT TIGER\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"JAPAN\",           \"4\": \"22/04\",           \"5\": \"W97.5\",           \"6\": \"SUBS\",           \"7\": \"RPLC\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\"        },         \"line_index\": 20      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"PLOUTOS\",           \"1\": \"60\",           \"2\": \"UMS\",           \"3\": \"X-AG\",           \"4\": \"15/04\",           \"5\": \"COA\",           \"6\": \"SUBS\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\"        },         \"line_index\": 21      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"LIAN\",           \"1\": \"GUI\",           \"2\": \"HU\",           \"3\": \"55\",           \"4\": \"NAP\",           \"5\": \"JAPAN\",           \"6\": \"END/04\",           \"7\": \"O/P\",           \"8\": \"SHELL\",           \"9\": \"SUBS\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\",           \"8\": \"unknown_8\",           \"9\": \"unknown_9\"        },         \"line_index\": 22      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"PACIFIC MARTINA\",           \"1\": \"55\",           \"2\": \"NAP\",           \"3\": \"JAPAN\",           \"4\": \"15/04\",           \"5\": \"O/P\",           \"6\": \"SHELL\",           \"7\": \"SUBS\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\"        },         \"line_index\": 23      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"NORDIC\",           \"1\": \"TRISTAN\",           \"2\": \"55\",           \"3\": \"NAP\",           \"4\": \"KUWAIT/EC.INDIA\",           \"5\": \"20/04\",           \"6\": \"W113\",           \"7\": \"SUBS\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\"        },         \"line_index\": 24      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"BW CLYDE\",           \"1\": \"55\",           \"2\": \"NAP\",           \"3\": \"JAPAN\",           \"4\": \"OPTS\",           \"5\": \"08/04\",           \"6\": \"W100\",           \"7\": \"ATC=FLD\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\"        },         \"line_index\": 25      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"NAVE ATROPOS\",           \"1\": \"55\",           \"2\": \"NAP\",           \"3\": \"JAPAN\",           \"4\": \"OPTS\",           \"5\": \"08/04\",           \"6\": \"W100\",           \"7\": \"SUBS\",           \"8\": \"RPLC\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\",           \"8\": \"unknown_8\"        },         \"line_index\": 26      }   ]  }}";
//        String alldata="04/08 SEABREEZE 53   56 04/08  USG              THENAMARIS       PPT";
//        JSONObject data= new JSONObject();
//        data.put("key", "value");
//        data.put("0", "ADELE M.RIZZO     80    COND  28-30/04    CAKERAWALA  / THAILAND $380k");
//
//        JSONObject header= new JSONObject();
//        header.put("key", "value");
//
//////System.out.println("data "+data);
//////System.out.println("header "+header);
//
//JSONObject obj1 = singleStringParsingmethod(data,  header, null, null);
//   	////System.out.println(obj1);
//   	////System.out.println("header * "+header);
//
		
		
		
//		JSONObject obj =labelrecognize("ELANDRA BALTIC CLN 11 51 53 25/04 SINGAPORE 25/04 VITOL");
//		////System.out.println(obj);
// String a= getlabelsByStanbol( "NISSOS CHRISTIANA 90 CPP 17/MAY SKOREA UKC 2.35M ATC SUB") ;
	//	String a="{\"0\":\"Nexus Victoria\",\"1\":\"74 910\",\"2\":\"84 247\",\"3\":\"2015\",\"4\":\"16-Apr\",\"5\":\"Fujairah\",\"6\":\"15-Apr\",\"7\":\"BAHRI\",\"8\":\"Go\",\"9\":\"f.o.c.\",\"10\":\"\"}";
	//	JSONObject obj= shift_Data(new JSONObject(a), "5", 11);
	////System.out.println("a "+a);
	
//	JSONObject obj = new JSONObject("{\"0\":\"NISSOS CHRISTIANA\",\"1\":\"90\",\"2\":\"CPP\",\"3\":\"17/MAY\",\"4\":\"SKOREA\",\"5\":\"UKC\",\"6\":\"2.35M\",\"7\":\"ATC\",\"8\":\"SUB\"}");
//	JSONObject newobj=replaceLabels(obj);
//	//System.out.println("newobj  "+newobj);
//	}
//	
	public static String updatedMainScript(PrintWriter out, Session session, String pythonScriptApiData,
			String emailUrl, String textSentMailTime, String subjectNodePath, String from_Source, String timestampDate,
			String timestampDateAndTime, String filepath) {

		
		////System.out.println("start");
		//String pythonScriptApiData="{  \"table_0\": {    \"header_data\": [      {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Date\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"Rate\"          }        ],         \"line_index\": 0,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"qty\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#Class\",             \"word\": \"Size\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#ObjectProperty\",             \"word\": \"size\"          }        ],         \"line_index\": 1,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"JPN\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"LR2\"          }        ],         \"line_index\": 2,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"Price\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Us\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"fo\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"LR2\"          }        ],         \"line_index\": 3,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"JPN\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"LR1\"          }        ],         \"line_index\": 4,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"LR1\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Fujairah\"          }        ],         \"line_index\": 5,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"JPN\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          }        ],         \"line_index\": 6,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"JPN\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          }        ],         \"line_index\": 7,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Us\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"Price\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          }        ],         \"line_index\": 8,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Singapore\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          }        ],         \"line_index\": 9,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Fujairah\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          }        ],         \"line_index\": 10,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"JPN\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          }        ],         \"line_index\": 11,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          }        ],         \"line_index\": 12,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          }        ],         \"line_index\": 13,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          }        ],         \"line_index\": 14,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          }        ],         \"line_index\": 15,         \"number_of_words\": 1      }    ],     \"table_data\": [      {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"SIZE\",           \"1\": \"QTY\",           \"2\": \"ROUTE\",           \"3\": \"RATE\",           \"4\": \"\",           \"5\": \"\",           \"6\": \"\",           \"7\": \"\"        },         \"header\": {          \"0\": \"RATE ASSESSMENT (2019 FLAT RATE)\",           \"1\": \"unknown_0\",           \"2\": \"unknown_1\",           \"3\": \"unknown_2\",           \"4\": \"unknown_3\",           \"5\": \"DATE:\",           \"6\": \"24 APR 2019\",           \"7\": \"unknown_4\"        },         \"line_index\": 11      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"LR2\",           \"1\": \"75KT\",           \"2\": \"AG/JPN\",           \"3\": \"WS92.5\",           \"4\": \"STABLE\",           \"5\": \"\",           \"6\": \"\",           \"7\": \"\"        },         \"header\": {          \"0\": \"RATE ASSESSMENT (2019 FLAT RATE)\",           \"1\": \"unknown_0\",           \"2\": \"unknown_1\",           \"3\": \"unknown_2\",           \"4\": \"unknown_3\",           \"5\": \"DATE:\",           \"6\": \"24 APR 2019\",           \"7\": \"unknown_4\"        },         \"line_index\": 12      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"LR2\",           \"1\": \"80KT\",           \"2\": \"SK/SIN\",           \"3\": \"550K\",           \"4\": \"STABLE\",           \"5\": \"BUNKER PRICE (US$) (FO 380)\",           \"6\": \"\",           \"7\": \"\"        },         \"header\": {          \"0\": \"RATE ASSESSMENT (2019 FLAT RATE)\",           \"1\": \"unknown_0\",           \"2\": \"unknown_1\",           \"3\": \"unknown_2\",           \"4\": \"unknown_3\",           \"5\": \"DATE:\",           \"6\": \"24 APR 2019\",           \"7\": \"unknown_4\"        },         \"line_index\": 13      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"LR1\",           \"1\": \"55KT\",           \"2\": \"AG/JPN\",           \"3\": \"WS90\",           \"4\": \"STABLE\",           \"5\": \"SINGAPORE\",           \"6\": \"436.50\",           \"7\": \"\"        },         \"header\": {          \"0\": \"RATE ASSESSMENT (2019 FLAT RATE)\",           \"1\": \"unknown_0\",           \"2\": \"unknown_1\",           \"3\": \"unknown_2\",           \"4\": \"unknown_3\",           \"5\": \"DATE:\",           \"6\": \"24 APR 2019\",           \"7\": \"unknown_4\"        },         \"line_index\": 14      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"LR1\",           \"1\": \"60KT\",           \"2\": \"SK/SIN\",           \"3\": \"500K\",           \"4\": \"STABLE\",           \"5\": \"FUJAIRAH\",           \"6\": \"436.50\",           \"7\": \"`\"        },         \"header\": {          \"0\": \"RATE ASSESSMENT (2019 FLAT RATE)\",           \"1\": \"unknown_0\",           \"2\": \"unknown_1\",           \"3\": \"unknown_2\",           \"4\": \"unknown_3\",           \"5\": \"DATE:\",           \"6\": \"24 APR 2019\",           \"7\": \"unknown_4\"        },         \"line_index\": 15      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"MR\",           \"1\": \"35KT\",           \"2\": \"WCI/JPN\",           \"3\": \"WS105\",           \"4\": \"STABLE\",           \"5\": \"\",           \"6\": \"\",           \"7\": \"\"        },         \"header\": {          \"0\": \"RATE ASSESSMENT (2019 FLAT RATE)\",           \"1\": \"unknown_0\",           \"2\": \"unknown_1\",           \"3\": \"unknown_2\",           \"4\": \"unknown_3\",           \"5\": \"DATE:\",           \"6\": \"24 APR 2019\",           \"7\": \"unknown_4\"        },         \"line_index\": 16      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"MR\",           \"1\": \"30KT\",           \"2\": \"SIN/JPN\",           \"3\": \"WS145\",           \"4\": \"STABLE\",           \"5\": \"\",           \"6\": \"\",           \"7\": \"\"        },         \"header\": {          \"0\": \"RATE ASSESSMENT (2019 FLAT RATE)\",           \"1\": \"unknown_0\",           \"2\": \"unknown_1\",           \"3\": \"unknown_2\",           \"4\": \"unknown_3\",           \"5\": \"DATE:\",           \"6\": \"24 APR 2019\",           \"7\": \"unknown_4\"        },         \"line_index\": 17      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"MR\",           \"1\": \"35KT\",           \"2\": \"SIN/OZ\",           \"3\": \"WS175\",           \"4\": \"STABLE\",           \"5\": \"BUNKER PRICE (US$) (MGO)\",           \"6\": \"\",           \"7\": \"\"        },         \"header\": {          \"0\": \"RATE ASSESSMENT (2019 FLAT RATE)\",           \"1\": \"unknown_0\",           \"2\": \"unknown_1\",           \"3\": \"unknown_2\",           \"4\": \"unknown_3\",           \"5\": \"DATE:\",           \"6\": \"24 APR 2019\",           \"7\": \"unknown_4\"        },         \"line_index\": 18      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"MR\",           \"1\": \"35KT\",           \"2\": \"SK/OZ\",           \"3\": \"WS187.5\",           \"4\": \"STABLE\",           \"5\": \"SINGAPORE\",           \"6\": \"638.00\",           \"7\": \"\"        },         \"header\": {          \"0\": \"RATE ASSESSMENT (2019 FLAT RATE)\",           \"1\": \"unknown_0\",           \"2\": \"unknown_1\",           \"3\": \"unknown_2\",           \"4\": \"unknown_3\",           \"5\": \"DATE:\",           \"6\": \"24 APR 2019\",           \"7\": \"unknown_4\"        },         \"line_index\": 19      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"MR\",           \"1\": \"35KT\",           \"2\": \"SK/USWC\",           \"3\": \"1300K\",           \"4\": \"FIRM\",           \"5\": \"FUJAIRAH\",           \"6\": \"744.50\",           \"7\": \"\"        },         \"header\": {          \"0\": \"RATE ASSESSMENT (2019 FLAT RATE)\",           \"1\": \"unknown_0\",           \"2\": \"unknown_1\",           \"3\": \"unknown_2\",           \"4\": \"unknown_3\",           \"5\": \"DATE:\",           \"6\": \"24 APR 2019\",           \"7\": \"unknown_4\"        },         \"line_index\": 20      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"MR\",           \"1\": \"30KT\",           \"2\": \"SK/JPN\",           \"3\": \"335K\",           \"4\": \"STABLE\",           \"5\": \"\",           \"6\": \"\",           \"7\": \"\"        },         \"header\": {          \"0\": \"RATE ASSESSMENT (2019 FLAT RATE)\",           \"1\": \"unknown_0\",           \"2\": \"unknown_1\",           \"3\": \"unknown_2\",           \"4\": \"unknown_3\",           \"5\": \"DATE:\",           \"6\": \"24 APR 2019\",           \"7\": \"unknown_4\"        },         \"line_index\": 21      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"MR\",           \"1\": \"30KT\",           \"2\": \"SK/SIN\",           \"3\": \"485K\",           \"4\": \"STABLE\",           \"5\": \"\",           \"6\": \"\",           \"7\": \"\"        },         \"header\": {          \"0\": \"RATE ASSESSMENT (2019 FLAT RATE)\",           \"1\": \"unknown_0\",           \"2\": \"unknown_1\",           \"3\": \"unknown_2\",           \"4\": \"unknown_3\",           \"5\": \"DATE:\",           \"6\": \"24 APR 2019\",           \"7\": \"unknown_4\"        },         \"line_index\": 22      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"MR\",           \"1\": \"35KT\",           \"2\": \"SIN/SIN\",           \"3\": \"155K\",           \"4\": \"SOFT\",           \"5\": \"\",           \"6\": \"\",           \"7\": \"\"        },         \"header\": {          \"0\": \"RATE ASSESSMENT (2019 FLAT RATE)\",           \"1\": \"unknown_0\",           \"2\": \"unknown_1\",           \"3\": \"unknown_2\",           \"4\": \"unknown_3\",           \"5\": \"DATE:\",           \"6\": \"24 APR 2019\",           \"7\": \"unknown_4\"        },         \"line_index\": 23      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"MR\",           \"1\": \"35KT\",           \"2\": \"SK/HK\",           \"3\": \"410K\",           \"4\": \"STABLE\",           \"5\": \"\",           \"6\": \"\",           \"7\": \"\"        },         \"header\": {          \"0\": \"RATE ASSESSMENT (2019 FLAT RATE)\",           \"1\": \"unknown_0\",           \"2\": \"unknown_1\",           \"3\": \"unknown_2\",           \"4\": \"unknown_3\",           \"5\": \"DATE:\",           \"6\": \"24 APR 2019\",           \"7\": \"unknown_4\"        },         \"line_index\": 24      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"MR\",           \"1\": \"35KT\",           \"2\": \"SIN/HK\",           \"3\": \"340K\",           \"4\": \"STABLE\",           \"5\": \"\",           \"6\": \"\",           \"7\": \"\"        },         \"header\": {          \"0\": \"RATE ASSESSMENT (2019 FLAT RATE)\",           \"1\": \"unknown_0\",           \"2\": \"unknown_1\",           \"3\": \"unknown_2\",           \"4\": \"unknown_3\",           \"5\": \"DATE:\",           \"6\": \"24 APR 2019\",           \"7\": \"unknown_4\"        },         \"line_index\": 25      }    ]  },   \"table_1\": {    \"header_data\": [      {        \"data\": [],         \"line_index\": 0,         \"number_of_words\": 0      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"Cargo\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#Class\",             \"word\": \"Vessel Size\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LOAD\"          }        ],         \"line_index\": 1,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Far East\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion\",             \"word\": \"Far East\"          }        ],         \"line_index\": 2,         \"number_of_words\": 2      }    ],     \"table_data\": [      {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"FAR EAST\",           \"1\": \"\",           \"2\": \"\",           \"3\": \"\",           \"4\": \"\",           \"5\": \"\",           \"6\": \"\",           \"7\": \"\",           \"8\": \"\"        },         \"header\": {          \"0\": \"VESSEL\",           \"1\": \"SIZE\",           \"2\": \"CARGO\",           \"3\": \"LAYCAN\",           \"4\": \"LOAD\",           \"5\": \"DISCHARGE\",           \"6\": \"RATE\",           \"7\": \"CHARTERERS\",           \"8\": \"REMARK\"        },         \"line_index\": 30      }    ]  },   \"table_2\": {    \"header_data\": [      {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          }        ],         \"line_index\": 0,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"FS DILIGENCE\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion\",             \"word\": \"Meg\"          }        ],         \"line_index\": 1,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"vitol\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"TRYSIL SPIRIT\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Taiwan\"          }        ],         \"line_index\": 2,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"SEAWAYS SHENANDOAH\"          }        ],         \"line_index\": 3,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"STI SYMPHONY\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          }        ],         \"line_index\": 4,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"PRO TRIUMPH\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          }        ],         \"line_index\": 5,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ulsd\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"JPN\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"SCF PIONEER\"          }        ],         \"line_index\": 6,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"ALPINE PERSEFONE\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion\",             \"word\": \"Meg\"          }        ],         \"line_index\": 7,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion\",             \"word\": \"SIKKA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Sikka\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"KITION M\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#Class\",             \"word\": \"Ship\"          }        ],         \"line_index\": 8,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"vitol\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"CHALLENGE PASSAGE\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"KARACHI\"          }        ],         \"line_index\": 9,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Yosu\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"NORD INSPIRATION\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"maersk\"          }        ],         \"line_index\": 10,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Mizushima\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"BW LEOPARD\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          }        ],         \"line_index\": 11,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"PACIFIC JEWEL\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Taiwan\"          }        ],         \"line_index\": 12,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"MR PAT BROWN\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Cochin\"          }        ],         \"line_index\": 13,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"GRAND\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"shell\"          }        ],         \"line_index\": 14,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Tbn\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"chevron\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          }        ],         \"line_index\": 15,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"GULF MUTTRAH\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"valero\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Usg\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"jet\"          }        ],         \"line_index\": 16,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"SUNNY DAY\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"jet\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Qinzhou\"          }        ],         \"line_index\": 17,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"HSL ANNA\"          }        ],         \"line_index\": 18,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"HELLAS APHRODITE\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"valero\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"jet\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Usg\"          }        ],         \"line_index\": 19,         \"number_of_words\": 4      }    ],     \"table_data\": [      {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"FS DILIGENCE\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"02-May\",           \"4\": \"MEG\",           \"5\": \"JAPAN-KOR\",           \"6\": \"W82.5\",           \"7\": \"LG CHEMS\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"OCEAN ODYSSAY\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"27-Apr\",           \"4\": \"AG\",           \"5\": \"JAPAN\",           \"6\": \"WS95\",           \"7\": \"MARUBENI\",           \"8\": \"SUB\"        },         \"line_index\": 33      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"TRYSIL SPIRIT\",           \"1\": \"75\",           \"2\": \"CPP\",           \"3\": \"04-May\",           \"4\": \"SK+TAIWAN\",           \"5\": \"AUS\",           \"6\": \"RNR\",           \"7\": \"VITOL\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"OCEAN ODYSSAY\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"27-Apr\",           \"4\": \"AG\",           \"5\": \"JAPAN\",           \"6\": \"WS95\",           \"7\": \"MARUBENI\",           \"8\": \"SUB\"        },         \"line_index\": 34      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"SEAWAYS SHENANDOAH\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"07-May\",           \"4\": \"AG\",           \"5\": \"JAPAN-OPTS\",           \"6\": \"WS97.5\",           \"7\": \"DAELIM\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"OCEAN ODYSSAY\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"27-Apr\",           \"4\": \"AG\",           \"5\": \"JAPAN\",           \"6\": \"WS95\",           \"7\": \"MARUBENI\",           \"8\": \"SUB\"        },         \"line_index\": 35      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"STI SYMPHONY\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"07-May\",           \"4\": \"AG\",           \"5\": \"JAPAN\",           \"6\": \"W97.5\",           \"7\": \"SOCAR\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"OCEAN ODYSSAY\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"27-Apr\",           \"4\": \"AG\",           \"5\": \"JAPAN\",           \"6\": \"WS95\",           \"7\": \"MARUBENI\",           \"8\": \"SUB\"        },         \"line_index\": 36      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"PRO TRIUMPH\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"08-May\",           \"4\": \"AG\",           \"5\": \"JAPAN\",           \"6\": \"WS89\",           \"7\": \"MARUBENI\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"OCEAN ODYSSAY\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"27-Apr\",           \"4\": \"AG\",           \"5\": \"JAPAN\",           \"6\": \"WS95\",           \"7\": \"MARUBENI\",           \"8\": \"SUB\"        },         \"line_index\": 37      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"SCF PIONEER\",           \"1\": \"60\",           \"2\": \"ULSD\",           \"3\": \"28-Apr\",           \"4\": \"JPN\",           \"5\": \"SCHINA\",           \"6\": \"590K (2:1)\",           \"7\": \"OCEAN ENERGY\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"OCEAN ODYSSAY\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"27-Apr\",           \"4\": \"AG\",           \"5\": \"JAPAN\",           \"6\": \"WS95\",           \"7\": \"MARUBENI\",           \"8\": \"SUB\"        },         \"line_index\": 38      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"ALPINE PERSEFONE\",           \"1\": \"60\",           \"2\": \"UMS\",           \"3\": \"01-May\",           \"4\": \"RSEA\",           \"5\": \"MEG-SPORE\",           \"6\": \"500K-W95\",           \"7\": \"UNIPEC\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"OCEAN ODYSSAY\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"27-Apr\",           \"4\": \"AG\",           \"5\": \"JAPAN\",           \"6\": \"WS95\",           \"7\": \"MARUBENI\",           \"8\": \"SUB\"        },         \"line_index\": 39      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"KITION M\",           \"1\": \"55\",           \"2\": \"NAP\",           \"3\": \"05-May\",           \"4\": \"SIKKA\",           \"5\": \"JAPAN\",           \"6\": \"WS90\",           \"7\": \"ST SHIP\",           \"8\": \"RPLC\"        },         \"header\": {          \"0\": \"OCEAN ODYSSAY\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"27-Apr\",           \"4\": \"AG\",           \"5\": \"JAPAN\",           \"6\": \"WS95\",           \"7\": \"MARUBENI\",           \"8\": \"SUB\"        },         \"line_index\": 40      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"CHALLENGE PASSAGE\",           \"1\": \"35\",           \"2\": \"NAP\",           \"3\": \"26-Apr\",           \"4\": \"KARACHI\",           \"5\": \"JAPAN-OPTS\",           \"6\": \"WS109\",           \"7\": \"VITOL\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"OCEAN ODYSSAY\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"27-Apr\",           \"4\": \"AG\",           \"5\": \"JAPAN\",           \"6\": \"WS95\",           \"7\": \"MARUBENI\",           \"8\": \"SUB\"        },         \"line_index\": 41      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"NORD INSPIRATION\",           \"1\": \"35\",           \"2\": \"NAP\",           \"3\": \"27-Apr\",           \"4\": \"YOSU\",           \"5\": \"CHIBA\",           \"6\": \"300K\",           \"7\": \"MAERSK\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"OCEAN ODYSSAY\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"27-Apr\",           \"4\": \"AG\",           \"5\": \"JAPAN\",           \"6\": \"WS95\",           \"7\": \"MARUBENI\",           \"8\": \"SUB\"        },         \"line_index\": 42      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"BW LEOPARD\",           \"1\": \"35\",           \"2\": \"CPP\",           \"3\": \"28-Apr\",           \"4\": \"MIZUSHIMA\",           \"5\": \"USWC-SPORE\",           \"6\": \"1.325M/RNR\",           \"7\": \"PETROCHINA\",           \"8\": \"RPLC\"        },         \"header\": {          \"0\": \"OCEAN ODYSSAY\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"27-Apr\",           \"4\": \"AG\",           \"5\": \"JAPAN\",           \"6\": \"WS95\",           \"7\": \"MARUBENI\",           \"8\": \"SUB\"        },         \"line_index\": 43      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"PACIFIC JEWEL\",           \"1\": \"35\",           \"2\": \"CPP\",           \"3\": \"01-May\",           \"4\": \"SK\",           \"5\": \"TAIWAN\",           \"6\": \"RNR\",           \"7\": \"SKE\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"OCEAN ODYSSAY\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"27-Apr\",           \"4\": \"AG\",           \"5\": \"JAPAN\",           \"6\": \"WS95\",           \"7\": \"MARUBENI\",           \"8\": \"SUB\"        },         \"line_index\": 44      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"MR PAT BROWN\",           \"1\": \"35\",           \"2\": \"NAP\",           \"3\": \"02-May\",           \"4\": \"COCHIN\",           \"5\": \"JAPAN-OPTS\",           \"6\": \"WS105\",           \"7\": \"PDC\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"OCEAN ODYSSAY\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"27-Apr\",           \"4\": \"AG\",           \"5\": \"JAPAN\",           \"6\": \"WS95\",           \"7\": \"MARUBENI\",           \"8\": \"SUB\"        },         \"line_index\": 45      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"GRAND ACE 8\",           \"1\": \"35\",           \"2\": \"CPP\",           \"3\": \"02-May\",           \"4\": \"SK\",           \"5\": \"AUS-NZ\",           \"6\": \"W190\",           \"7\": \"SHELL\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"OCEAN ODYSSAY\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"27-Apr\",           \"4\": \"AG\",           \"5\": \"JAPAN\",           \"6\": \"WS95\",           \"7\": \"MARUBENI\",           \"8\": \"SUB\"        },         \"line_index\": 46      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"TBN\",           \"1\": \"35\",           \"2\": \"CPP\",           \"3\": \"05-May\",           \"4\": \"SK\",           \"5\": \"PHIL-HKG\",           \"6\": \"RNR\",           \"7\": \"CHEVRON\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"OCEAN ODYSSAY\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"27-Apr\",           \"4\": \"AG\",           \"5\": \"JAPAN\",           \"6\": \"WS95\",           \"7\": \"MARUBENI\",           \"8\": \"SUB\"        },         \"line_index\": 47      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"GULF MUTTRAH\",           \"1\": \"35\",           \"2\": \"JET\",           \"3\": \"05-May\",           \"4\": \"SK+TAIWAN\",           \"5\": \"USWC-USG\",           \"6\": \"1.3M/RNR\",           \"7\": \"VALERO\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"OCEAN ODYSSAY\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"27-Apr\",           \"4\": \"AG\",           \"5\": \"JAPAN\",           \"6\": \"WS95\",           \"7\": \"MARUBENI\",           \"8\": \"SUB\"        },         \"line_index\": 48      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"SUNNY DAY\",           \"1\": \"35\",           \"2\": \"JET\",           \"3\": \"06-May\",           \"4\": \"QINZHOU\",           \"5\": \"HKG\",           \"6\": \"240K\",           \"7\": \"PETROCHINA\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"OCEAN ODYSSAY\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"27-Apr\",           \"4\": \"AG\",           \"5\": \"JAPAN\",           \"6\": \"WS95\",           \"7\": \"MARUBENI\",           \"8\": \"SUB\"        },         \"line_index\": 49      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"HSL ANNA\",           \"1\": \"35\",           \"2\": \"NAP\",           \"3\": \"07-May\",           \"4\": \"ONSAN\",           \"5\": \"JAPAN\",           \"6\": \"340K\",           \"7\": \"ATC\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"OCEAN ODYSSAY\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"27-Apr\",           \"4\": \"AG\",           \"5\": \"JAPAN\",           \"6\": \"WS95\",           \"7\": \"MARUBENI\",           \"8\": \"SUB\"        },         \"line_index\": 50      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"HELLAS APHRODITE\",           \"1\": \"35\",           \"2\": \"JET\",           \"3\": \"10-May\",           \"4\": \"SK\",           \"5\": \"USWC-USG\",           \"6\": \"1.25M-RNR\",           \"7\": \"VALERO\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"OCEAN ODYSSAY\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"27-Apr\",           \"4\": \"AG\",           \"5\": \"JAPAN\",           \"6\": \"WS95\",           \"7\": \"MARUBENI\",           \"8\": \"SUB\"        },         \"line_index\": 51      }    ]  },   \"table_3\": {    \"header_data\": [      {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Australia\"          }        ],         \"line_index\": 0,         \"number_of_words\": 1      }    ],     \"table_data\": []  },   \"table_4\": {    \"header_data\": [      {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Thailand\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"OCEAN TAIPAN\"          }        ],         \"line_index\": 0,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"SKS DOYLES\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"A.R.A.\"          }        ],         \"line_index\": 1,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ulsd\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"STI CONNAUGHT\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion\",             \"word\": \"UKC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"cnr\"          }        ],         \"line_index\": 2,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Bayu Undan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"FALCON EXPRESS\"          }        ],         \"line_index\": 3,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"SEA TANANA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Philippines\"          }        ],         \"line_index\": 4,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"vitol\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"jet\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"TORM ASLAUG\"          }        ],         \"line_index\": 5,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"exxon\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion\",             \"word\": \"SIKKA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"MR PEGASUS\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Sikka\"          }        ],         \"line_index\": 6,         \"number_of_words\": 4      }    ],     \"table_data\": [      {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"SKS DOYLES\",           \"1\": \"90\",           \"2\": \"UMS\",           \"3\": \"28-Apr\",           \"4\": \"ARA\",           \"5\": \"EC AUS\",           \"6\": \"2.15M\",           \"7\": \"AMPOL\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"OCEAN TAIPAN\",           \"1\": \"90\",           \"2\": \"CPP\",           \"3\": \"end Apr\",           \"4\": \"CAKEKAWALA\",           \"5\": \"THAILAND\",           \"6\": \"RNR\",           \"7\": \"PTT\",           \"8\": \"SUB\"        },         \"line_index\": 58      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"STI CONNAUGHT\",           \"1\": \"90\",           \"2\": \"ULSD\",           \"3\": \"01-May\",           \"4\": \"AG\",           \"5\": \"UKC-SP\",           \"6\": \"RNR\",           \"7\": \"CNR\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"OCEAN TAIPAN\",           \"1\": \"90\",           \"2\": \"CPP\",           \"3\": \"end Apr\",           \"4\": \"CAKEKAWALA\",           \"5\": \"THAILAND\",           \"6\": \"RNR\",           \"7\": \"PTT\",           \"8\": \"SUB\"        },         \"line_index\": 59      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"FALCON EXPRESS\",           \"1\": \"80\",           \"2\": \"COND\",           \"3\": \"04-May\",           \"4\": \"BAYU UNDAN\",           \"5\": \"SCHINA\",           \"6\": \"W96.5\",           \"7\": \"UNIPEC\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"OCEAN TAIPAN\",           \"1\": \"90\",           \"2\": \"CPP\",           \"3\": \"end Apr\",           \"4\": \"CAKEKAWALA\",           \"5\": \"THAILAND\",           \"6\": \"RNR\",           \"7\": \"PTT\",           \"8\": \"SUB\"        },         \"line_index\": 60      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"SEA TANANA\",           \"1\": \"75\",           \"2\": \"GOIL\",           \"3\": \"30-Apr\",           \"4\": \"SP\",           \"5\": \"PHILIPPINES\",           \"6\": \"575K\",           \"7\": \"PETRON\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"OCEAN TAIPAN\",           \"1\": \"90\",           \"2\": \"CPP\",           \"3\": \"end Apr\",           \"4\": \"CAKEKAWALA\",           \"5\": \"THAILAND\",           \"6\": \"RNR\",           \"7\": \"PTT\",           \"8\": \"SUB\"        },         \"line_index\": 61      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"TORM ASLAUG\",           \"1\": \"35\",           \"2\": \"ULSD+JET\",           \"3\": \"28-Apr\",           \"4\": \"SP\",           \"5\": \"AUS\",           \"6\": \"W172.5\",           \"7\": \"VITOL\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"OCEAN TAIPAN\",           \"1\": \"90\",           \"2\": \"CPP\",           \"3\": \"end Apr\",           \"4\": \"CAKEKAWALA\",           \"5\": \"THAILAND\",           \"6\": \"RNR\",           \"7\": \"PTT\",           \"8\": \"SUB\"        },         \"line_index\": 62      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"MR PEGASUS\",           \"1\": \"35\",           \"2\": \"NAP\",           \"3\": \"04-May\",           \"4\": \"SIKKA\",           \"5\": \"SPORE\",           \"6\": \"WS135\",           \"7\": \"SEARIVER(EXXON)\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"OCEAN TAIPAN\",           \"1\": \"90\",           \"2\": \"CPP\",           \"3\": \"end Apr\",           \"4\": \"CAKEKAWALA\",           \"5\": \"THAILAND\",           \"6\": \"RNR\",           \"7\": \"PTT\",           \"8\": \"SUB\"        },         \"line_index\": 63      }    ]  },   \"table_5\": {    \"header_data\": [      {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"INDIA\"          }        ],         \"line_index\": 0,         \"number_of_words\": 1      }    ],     \"table_data\": []  },   \"table_6\": {    \"header_data\": [      {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"MAERSK PROGRESS\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ulsd\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion\",             \"word\": \"UKC\"          }        ],         \"line_index\": 0,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Yanbu\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ulsd\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"MAERSK PETREL\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion\",             \"word\": \"UKC\"          }        ],         \"line_index\": 1,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Yanbu\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"MELTEMI\"          }        ],         \"line_index\": 2,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"vitol\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Sikka\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"ATHINA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion\",             \"word\": \"SIKKA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ulsd\"          }        ],         \"line_index\": 3,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Jubail\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"GULF COAST\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          }        ],         \"line_index\": 4,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"litasco\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"JO KARI\"          }        ],         \"line_index\": 5,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Jnpt\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"UACC HARMONY\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"enoc\"          }        ],         \"line_index\": 6,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"JAG PRAKASH\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Ennore\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion\",             \"word\": \"SIKKA\"          }        ],         \"line_index\": 7,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Bahrain\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"TORM SAN JACINTO\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          }        ],         \"line_index\": 8,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"ATLANTIC STAR\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"shell\"          }        ],         \"line_index\": 9,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"ADRIATIC WAVE\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"shell\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"MESAIEED\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          }        ],         \"line_index\": 10,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Matsuyama\"          }        ],         \"line_index\": 11,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"ZOUZOU\"          }        ],         \"line_index\": 12,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Vadinar\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"UACC RAS TANURA\"          }        ],         \"line_index\": 13,         \"number_of_words\": 3      }    ],     \"table_data\": [      {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"MAERSK PETREL\",           \"1\": \"90\",           \"2\": \"ULSD\",           \"3\": \"07-May\",           \"4\": \"YANBU\",           \"5\": \"UKC-EAF\",           \"6\": \"1.55M\",           \"7\": \"UNIPEC\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"MAERSK PROGRESS\",           \"1\": \"90\",           \"2\": \"ULSD\",           \"3\": \"06-May\",           \"4\": \"AG\",           \"5\": \"UKC-OPS\",           \"6\": \"1.825M\",           \"7\": \"CSSSA\",           \"8\": \"SUB\"        },         \"line_index\": 70      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"MELTEMI\",           \"1\": \"60\",           \"2\": \"UMS\",           \"3\": \"27-Apr\",           \"4\": \"YANBU\",           \"5\": \"UAE-SP\",           \"6\": \"520K-W100\",           \"7\": \"ATC\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"MAERSK PROGRESS\",           \"1\": \"90\",           \"2\": \"ULSD\",           \"3\": \"06-May\",           \"4\": \"AG\",           \"5\": \"UKC-OPS\",           \"6\": \"1.825M\",           \"7\": \"CSSSA\",           \"8\": \"SUB\"        },         \"line_index\": 71      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"ATHINA\",           \"1\": \"60\",           \"2\": \"ULSD\",           \"3\": \"28-Apr\",           \"4\": \"SIKKA\",           \"5\": \"AG-KAZ-OPS\",           \"6\": \"225K-300K\",           \"7\": \"VITOL\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"MAERSK PROGRESS\",           \"1\": \"90\",           \"2\": \"ULSD\",           \"3\": \"06-May\",           \"4\": \"AG\",           \"5\": \"UKC-OPS\",           \"6\": \"1.825M\",           \"7\": \"CSSSA\",           \"8\": \"SUB\"        },         \"line_index\": 72      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"GULF COAST\",           \"1\": \"60\",           \"2\": \"UMS\",           \"3\": \"02-May\",           \"4\": \"JUBAIL\",           \"5\": \"UAE-USAC\",           \"6\": \"225K-1.825M\",           \"7\": \"CSSSA\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"MAERSK PROGRESS\",           \"1\": \"90\",           \"2\": \"ULSD\",           \"3\": \"06-May\",           \"4\": \"AG\",           \"5\": \"UKC-OPS\",           \"6\": \"1.825M\",           \"7\": \"CSSSA\",           \"8\": \"SUB\"        },         \"line_index\": 73      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"JO KARI\",           \"1\": \"35\",           \"2\": \"UMS\",           \"3\": \"26-Apr\",           \"4\": \"J.ALI\",           \"5\": \"KAZ\",           \"6\": \"185K\",           \"7\": \"LITASCO\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"MAERSK PROGRESS\",           \"1\": \"90\",           \"2\": \"ULSD\",           \"3\": \"06-May\",           \"4\": \"AG\",           \"5\": \"UKC-OPS\",           \"6\": \"1.825M\",           \"7\": \"CSSSA\",           \"8\": \"SUB\"        },         \"line_index\": 74      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"UACC HARMONY\",           \"1\": \"35\",           \"2\": \"NAP\",           \"3\": \"27-Apr\",           \"4\": \"JNPT\",           \"5\": \"FUJ\",           \"6\": \"210K\",           \"7\": \"ENOC\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"MAERSK PROGRESS\",           \"1\": \"90\",           \"2\": \"ULSD\",           \"3\": \"06-May\",           \"4\": \"AG\",           \"5\": \"UKC-OPS\",           \"6\": \"1.825M\",           \"7\": \"CSSSA\",           \"8\": \"SUB\"        },         \"line_index\": 75      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"JAG PRAKASH\",           \"1\": \"35\",           \"2\": \"GO+ UMS\",           \"3\": \"27-Apr\",           \"4\": \"SIKKA\",           \"5\": \"ENNORE\",           \"6\": \"375K\",           \"7\": \"RELIANCE\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"MAERSK PROGRESS\",           \"1\": \"90\",           \"2\": \"ULSD\",           \"3\": \"06-May\",           \"4\": \"AG\",           \"5\": \"UKC-OPS\",           \"6\": \"1.825M\",           \"7\": \"CSSSA\",           \"8\": \"SUB\"        },         \"line_index\": 76      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"TORM SAN JACINTO\",           \"1\": \"35\",           \"2\": \"CPP\",           \"3\": \"28-Apr\",           \"4\": \"BAHRAIN\",           \"5\": \"HAMRIYAH\",           \"6\": \"132.5K\",           \"7\": \"E3\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"MAERSK PROGRESS\",           \"1\": \"90\",           \"2\": \"ULSD\",           \"3\": \"06-May\",           \"4\": \"AG\",           \"5\": \"UKC-OPS\",           \"6\": \"1.825M\",           \"7\": \"CSSSA\",           \"8\": \"SUB\"        },         \"line_index\": 77      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"ATLANTIC STAR\",           \"1\": \"35\",           \"2\": \"GTL\",           \"3\": \"28-Apr\",           \"4\": \"AG\",           \"5\": \"WEST\",           \"6\": \"RNR\",           \"7\": \"SHELL\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"MAERSK PROGRESS\",           \"1\": \"90\",           \"2\": \"ULSD\",           \"3\": \"06-May\",           \"4\": \"AG\",           \"5\": \"UKC-OPS\",           \"6\": \"1.825M\",           \"7\": \"CSSSA\",           \"8\": \"SUB\"        },         \"line_index\": 78      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"ADRIATIC WAVE\",           \"1\": \"35\",           \"2\": \"CPP\",           \"3\": \"30-Apr\",           \"4\": \"R.LAFFAN\",           \"5\": \"MESAIEED\",           \"6\": \"RNR\",           \"7\": \"SHELL\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"MAERSK PROGRESS\",           \"1\": \"90\",           \"2\": \"ULSD\",           \"3\": \"06-May\",           \"4\": \"AG\",           \"5\": \"UKC-OPS\",           \"6\": \"1.825M\",           \"7\": \"CSSSA\",           \"8\": \"SUB\"        },         \"line_index\": 79      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"CENTENNIAL MATSUYAMA\",           \"1\": \"35\",           \"2\": \"UMS\",           \"3\": \"02-May\",           \"4\": \"FUJ\",           \"5\": \"DES\",           \"6\": \"W107.5\",           \"7\": \"ATC\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"MAERSK PROGRESS\",           \"1\": \"90\",           \"2\": \"ULSD\",           \"3\": \"06-May\",           \"4\": \"AG\",           \"5\": \"UKC-OPS\",           \"6\": \"1.825M\",           \"7\": \"CSSSA\",           \"8\": \"SUB\"        },         \"line_index\": 80      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"ZOUZOU\",           \"1\": \"35\",           \"2\": \"CPP\",           \"3\": \"02-May\",           \"4\": \"AG\",           \"5\": \"ARGIE\",           \"6\": \"1.2M\",           \"7\": \"ST\",           \"8\": \"RPLC\"        },         \"header\": {          \"0\": \"MAERSK PROGRESS\",           \"1\": \"90\",           \"2\": \"ULSD\",           \"3\": \"06-May\",           \"4\": \"AG\",           \"5\": \"UKC-OPS\",           \"6\": \"1.825M\",           \"7\": \"CSSSA\",           \"8\": \"SUB\"        },         \"line_index\": 81      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"UACC RAS TANURA\",           \"1\": \"35\",           \"2\": \"CPP\",           \"3\": \"05-May\",           \"4\": \"VADINAR\",           \"5\": \"EAF-SAF\",           \"6\": \"W110\",           \"7\": \"CSSSA\",           \"8\": \"SUB\"        },         \"header\": {          \"0\": \"MAERSK PROGRESS\",           \"1\": \"90\",           \"2\": \"ULSD\",           \"3\": \"06-May\",           \"4\": \"AG\",           \"5\": \"UKC-OPS\",           \"6\": \"1.825M\",           \"7\": \"CSSSA\",           \"8\": \"SUB\"        },         \"line_index\": 82      }    ]  },   \"table_7\": {    \"header_data\": [      {        \"data\": [],         \"line_index\": 0,         \"number_of_words\": 0      },       {        \"data\": [],         \"line_index\": 1,         \"number_of_words\": 0      },       {        \"data\": [],         \"line_index\": 2,         \"number_of_words\": 0      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Singapore\"          }        ],         \"line_index\": 3,         \"number_of_words\": 1      },       {        \"data\": [],         \"line_index\": 4,         \"number_of_words\": 0      },       {        \"data\": [],         \"line_index\": 5,         \"number_of_words\": 0      }    ],     \"table_data\": [      {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"MORNINGSTAR CENTRE\"        },         \"header\": {          \"0\": \"FIRSTLINK GLOBAL PTE LTD\"        },         \"line_index\": 87      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"12 NEW INDUSTRIAL ROAD #03-02C\"        },         \"header\": {          \"0\": \"FIRSTLINK GLOBAL PTE LTD\"        },         \"line_index\": 88      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"SINGAPORE 536202\"        },         \"header\": {          \"0\": \"FIRSTLINK GLOBAL PTE LTD\"        },         \"line_index\": 89      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"EMAIL: TANKERS@FLGLOBAL.COM.SG\"        },         \"header\": {          \"0\": \"FIRSTLINK GLOBAL PTE LTD\"        },         \"line_index\": 90      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"TEL: +65 64791066\"        },         \"header\": {          \"0\": \"FIRSTLINK GLOBAL PTE LTD\"        },         \"line_index\": 91      }    ]  },   \"table_8\": {    \"header_data\": [      {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Suez\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CLEAN\"          }        ],         \"line_index\": 0,         \"number_of_words\": 2      }    ],     \"table_data\": [      {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"   FIRSTLINK_CLEAN_MARKET_REPORT_(EAST_OF_SUEZ)_-_24APR2019  \"        },         \"header\": {          \"0\": \"   FIRSTLINK_CLEAN_MARKET_REPORT_(EAST_OF_SUEZ)_-_24APR2019  \"        },         \"label_line_index\": 0,         \"label_string\": \"   FIRSTLINK_CLEAN_MARKET_REPORT_(EAST_OF_SUEZ)_-_24APR2019  \",         \"line_index\": 0      }    ]  }}";
		//String pythonScriptApiData="{  \"table_0\": {    \"header_data\": [      {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#lr1\",             \"word\": \"LR1\"          }        ],         \"line_index\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#total\",             \"word\": \"Total\"          }        ],         \"line_index\": 20      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#lr1\",             \"word\": \"LR1\"          }        ],         \"line_index\": 21      }    ],     \"table_data\": [      {        \"data\": {          \"0\": \"Nexus Victoria\",           \"1\": \"74 910\",           \"2\": \"84 247\",           \"3\": \"2015\",           \"4\": \"16-Apr\",           \"5\": \"Fujairah\",           \"6\": \"15-Apr\",           \"7\": \"BAHRI\",           \"8\": \"Go\",           \"9\": \"f.o.c.\",           \"10\": \"\"        },         \"header\": {          \"0\": \"Name\",           \"1\": \"ICE/IMO\",           \"2\": \"DWT\",           \"3\": \"CBM\",           \"4\": \"BLT\",           \"5\": \"ETA AG\",           \"6\": \"POSITION\",           \"7\": \"OPEN\",           \"8\": \"CONTROLLED BY\",           \"9\": \"LAST CARGO\",           \"10\": \"COMMENT\"        },         \"label_line_index\": 41,         \"label_string\": \"Name ICE/IMO DWT CBM BLT ETA AG POSITION OPEN CONTROLLED BY LAST CARGO COMMENT\",         \"line_index\": 42      }]}}";
	//String pythonScriptApiData="{  \"table_0\": {    \"header_data\": [      {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#AG\",             \"word\": \"AG\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#MR\",             \"word\": \"MR\"          }        ],         \"line_index\": 0      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#SUBS\",             \"word\": \"SUBS\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#HOLD\",             \"word\": \"HOLD\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#SUBS_HOLD\",             \"word\": \"SUBS / HOLD\"          }        ],         \"line_index\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#EAST\",             \"word\": \"EAST\"          }        ],         \"line_index\": 4      }    ],     \"table_data\": [           {        \"data\": {          \"0\": \"PETROLIMEX 10\",           \"1\": \"03\",           \"2\": \"37\",           \"3\": \"42\",           \"4\": \"19/5\",           \"5\": \"VIETNAM\",           \"6\": \"03/5\",           \"7\": \"PETROLIMEX\",           \"8\": \"GO\"        },         \"header\": {          \"0\": \"VESSEL\",           \"1\": \"BLT\",           \"2\": \"DWT\",           \"3\": \"CBM\",           \"4\": \"OPEN\",           \"5\": \"EXPORT/DATE\",           \"6\": \"OWNERS\",           \"7\": \"LAST CGO\",           \"8\": \"unknown_1\"        },         \"label_line_index\": 5,         \"label_string\": \"VESSEL BLT DWT CBM OPEN EXPORT/DATE OWNERS LAST CGO\",         \"line_index\": 109      },       {        \"data\": {          \"0\": \"CSC FRIENDSHIP\",           \"1\": \"08\",           \"2\": \"45\",           \"3\": \"49\",           \"4\": \"20/5\",           \"5\": \"YOSU\",           \"6\": \"PPT\",           \"7\": \"NANJING\",           \"8\": \"ULSD / SUBS-POSS\"        },         \"header\": {          \"0\": \"VESSEL\",           \"1\": \"BLT\",           \"2\": \"DWT\",           \"3\": \"CBM\",           \"4\": \"OPEN\",           \"5\": \"EXPORT/DATE\",           \"6\": \"OWNERS\",           \"7\": \"LAST CGO\",           \"8\": \"unknown_1\"        },         \"label_line_index\": 5,         \"label_string\": \"VESSEL BLT DWT CBM OPEN EXPORT/DATE OWNERS LAST CGO\",         \"line_index\": 110      },       {        \"data\": {          \"0\": \"JUPITER EXPRESS\",           \"1\": \"12\",           \"2\": \"45\",           \"3\": \"52\",           \"4\": \"20/5\",           \"5\": \"PORT HEDLA\",           \"6\": \"02/5\",           \"7\": \"ATM\",           \"8\": \"JET / HOLD\"        },         \"header\": {          \"0\": \"VESSEL\",           \"1\": \"BLT\",           \"2\": \"DWT\",           \"3\": \"CBM\",           \"4\": \"OPEN\",           \"5\": \"EXPORT/DATE\",           \"6\": \"OWNERS\",           \"7\": \"LAST CGO\",           \"8\": \"unknown_1\"        },         \"label_line_index\": 5,         \"label_string\": \"VESSEL BLT DWT CBM OPEN EXPORT/DATE OWNERS LAST CGO\",         \"line_index\": 111      },       {        \"data\": {          \"0\": \"GRAND ACE 5\",           \"1\": \"06\",           \"2\": \"46\",           \"3\": \"53\",           \"4\": \"20/5\",           \"5\": \"VIETNAM\",           \"6\": \"04/5\",           \"7\": \"PAN OCEAN\",           \"8\": \"UMS / SUBS\"        },         \"header\": {          \"0\": \"VESSEL\",           \"1\": \"BLT\",           \"2\": \"DWT\",           \"3\": \"CBM\",           \"4\": \"OPEN\",           \"5\": \"EXPORT/DATE\",           \"6\": \"OWNERS\",           \"7\": \"LAST CGO\",           \"8\": \"unknown_1\"        },         \"label_line_index\": 5,         \"label_string\": \"VESSEL BLT DWT CBM OPEN EXPORT/DATE OWNERS LAST CGO\",         \"line_index\": 112      },       {        \"data\": {          \"0\": \"GRAND ACE 6\",           \"1\": \"07\",           \"2\": \"46\",           \"3\": \"53\",           \"4\": \"20/5\",           \"5\": \"CHINA\",           \"6\": \"PPT\",           \"7\": \"TRAFIGURA\",           \"8\": \"CPP-IMO2/3\"        },         \"header\": {          \"0\": \"VESSEL\",           \"1\": \"BLT\",           \"2\": \"DWT\",           \"3\": \"CBM\",           \"4\": \"OPEN\",           \"5\": \"EXPORT/DATE\",           \"6\": \"OWNERS\",           \"7\": \"LAST CGO\",           \"8\": \"unknown_1\"        },         \"label_line_index\": 5,         \"label_string\": \"VESSEL BLT DWT CBM OPEN EXPORT/DATE OWNERS LAST CGO\",         \"line_index\": 113      },       {        \"data\": {          \"0\": \"FOREVER PROSPERITY\",           \"1\": \"17\",           \"2\": \"49\",           \"3\": \"51\",           \"4\": \"20/5\",           \"5\": \"GERALDTON\",           \"6\": \"01/5\",           \"7\": \"NANJING\",           \"8\": \"CPP\"        },         \"header\": {          \"0\": \"VESSEL\",           \"1\": \"BLT\",           \"2\": \"DWT\",           \"3\": \"CBM\",           \"4\": \"OPEN\",           \"5\": \"EXPORT/DATE\",           \"6\": \"OWNERS\",           \"7\": \"LAST CGO\",           \"8\": \"unknown_1\"        },         \"label_line_index\": 5,         \"label_string\": \"VESSEL BLT DWT CBM OPEN EXPORT/DATE OWNERS LAST CGO\",         \"line_index\": 114      },       {        \"data\": {          \"0\": \"ST. PAULI\",           \"1\": \"17\",           \"2\": \"49\",           \"3\": \"55\",           \"4\": \"20/5\",           \"5\": \"DARWIN N.T\",           \"6\": \"02/5\",           \"7\": \"OETKER\",           \"8\": \"ULSD/UMS/PALMS\"        },         \"header\": {          \"0\": \"VESSEL\",           \"1\": \"BLT\",           \"2\": \"DWT\",           \"3\": \"CBM\",           \"4\": \"OPEN\",           \"5\": \"EXPORT/DATE\",           \"6\": \"OWNERS\",           \"7\": \"LAST CGO\",           \"8\": \"unknown_1\"        },         \"label_line_index\": 5,         \"label_string\": \"VESSEL BLT DWT CBM OPEN EXPORT/DATE OWNERS LAST CGO\",         \"line_index\": 115      },       {        \"data\": {          \"0\": \"GULF HUWAYLAT\",           \"1\": \"08\",           \"2\": \"45\",           \"3\": \"50\",           \"4\": \"20/5\",           \"5\": \"VIETNAM\",           \"6\": \"04/5\",           \"7\": \"UACC\",           \"8\": \"UMS\"        },         \"header\": {          \"0\": \"VESSEL\",           \"1\": \"BLT\",           \"2\": \"DWT\",           \"3\": \"CBM\",           \"4\": \"OPEN\",           \"5\": \"EXPORT/DATE\",           \"6\": \"OWNERS\",           \"7\": \"LAST CGO\",           \"8\": \"unknown_1\"        },         \"label_line_index\": 5,         \"label_string\": \"VESSEL BLT DWT CBM OPEN EXPORT/DATE OWNERS LAST CGO\",         \"line_index\": 116      },       {        \"data\": {          \"0\": \"PETROLIME\",           \"1\": \"X 16\",           \"2\": \"04\",           \"3\": \"46\",           \"4\": \"52\",           \"5\": \"20/5\",           \"6\": \"VIETNAM\",           \"7\": \"04/5\",           \"8\": \"PETROLIMEX\",           \"9\": \"UMS-IMO3\"        },         \"header\": {          \"0\": \"VESSEL\",           \"1\": \"BLT\",           \"2\": \"DWT\",           \"3\": \"CBM\",           \"4\": \"OPEN\",           \"5\": \"EXPORT/DATE\",           \"6\": \"OWNERS\",           \"7\": \"LAST CGO\",           \"8\": \"unknown_1\",           \"9\": \"unknown_2\"        },         \"label_line_index\": 5,         \"label_string\": \"VESSEL BLT DWT CBM OPEN EXPORT/DATE OWNERS LAST CGO\",         \"line_index\": 117      },       {        \"data\": {          \"0\": \"GULF MIRDIF\",           \"1\": \"10\",           \"2\": \"46\",           \"3\": \"46\",           \"4\": \"20/5\",           \"5\": \"ZHANGJIAGA\",           \"6\": \"PPT\",           \"7\": \"GULF NAVIG\",           \"8\": \"CHEMS/CHEMS/CHEMS\"        },         \"header\": {          \"0\": \"VESSEL\",           \"1\": \"BLT\",           \"2\": \"DWT\",           \"3\": \"CBM\",           \"4\": \"OPEN\",           \"5\": \"EXPORT/DATE\",           \"6\": \"OWNERS\",           \"7\": \"LAST CGO\",           \"8\": \"unknown_1\"        },         \"label_line_index\": 5,         \"label_string\": \"VESSEL BLT DWT CBM OPEN EXPORT/DATE OWNERS LAST CGO\",         \"line_index\": 118      },       {        \"data\": {          \"0\": \"FOREVER HARMONY\",           \"1\": \"17\",           \"2\": \"49\",           \"3\": \"51\",           \"4\": \"20/5\",           \"5\": \"PORT LINCO\",           \"6\": \"26/4\",           \"7\": \"NANJING\",           \"8\": \"CPP\"        },         \"header\": {          \"0\": \"VESSEL\",           \"1\": \"BLT\",           \"2\": \"DWT\",           \"3\": \"CBM\",           \"4\": \"OPEN\",           \"5\": \"EXPORT/DATE\",           \"6\": \"OWNERS\",           \"7\": \"LAST CGO\",           \"8\": \"unknown_1\"        },         \"label_line_index\": 5,         \"label_string\": \"VESSEL BLT DWT CBM OPEN EXPORT/DATE OWNERS LAST CGO\",         \"line_index\": 119      }    ]  }}";
	//String pythonScriptApiData="{\"table_0\":{\"header_data\":[{\"data\":[{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"RATE\"}],\"line_index\":0,\"number_of_words\":1}],\"table_data\":[]},\"table_1\":{\"header_data\":[{\"data\":[{\"url\":\"http://www.w3.org/2002/07/owl#DatatypeProperty\",\"word\":\"qty\"},{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"QTY\"}],\"line_index\":0,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Region\",\"word\":\"JPN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"AG\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",\"word\":\"LR2\"}],\"line_index\":1,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",\"word\":\"LR2\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Region\",\"word\":\"SIN\"}],\"line_index\":2,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Region\",\"word\":\"JPN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"AG\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",\"word\":\"LR1\"}],\"line_index\":3,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",\"word\":\"LR1\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Region\",\"word\":\"SIN\"}],\"line_index\":4,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Region\",\"word\":\"JPN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",\"word\":\"MR\"}],\"line_index\":5,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Region\",\"word\":\"JPN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",\"word\":\"MR\"}],\"line_index\":6,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Region\",\"word\":\"SIN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",\"word\":\"MR\"}],\"line_index\":7,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",\"word\":\"MR\"}],\"line_index\":8,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"USWC\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",\"word\":\"MR\"}],\"line_index\":9,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Region\",\"word\":\"JPN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",\"word\":\"MR\"}],\"line_index\":10,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Region\",\"word\":\"SIN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",\"word\":\"MR\"}],\"line_index\":11,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Region\",\"word\":\"SIN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",\"word\":\"MR\"}],\"line_index\":12,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",\"word\":\"MR\"}],\"line_index\":13,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",\"word\":\"MR\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Region\",\"word\":\"SIN\"}],\"line_index\":14,\"number_of_words\":2}],\"table_data\":[{\"StructureType\":\"table\",\"data\":{\"0\":\"LR2\",\"1\":\"75KT\",\"2\":\"AG/JPN\",\"3\":\"FLAT\",\"4\":\"WS107.5\",\"5\":\"\",\"6\":\"STABLE\"},\"header\":{\"0\":\"SIZE\",\"1\":\"QTY\",\"2\":\"ROUTE\",\"3\":\"CHANGE\",\"4\":\"06-MAY\",\"5\":\"03-MAY\",\"6\":\"unknown_0\"},\"line_index\":17},{\"StructureType\":\"table\",\"data\":{\"0\":\"LR2\",\"1\":\"80KT\",\"2\":\"SK/SIN\",\"3\":\"+50K\",\"4\":\"600K\",\"5\":\"550K\",\"6\":\"STABLE\"},\"header\":{\"0\":\"SIZE\",\"1\":\"QTY\",\"2\":\"ROUTE\",\"3\":\"CHANGE\",\"4\":\"06-MAY\",\"5\":\"03-MAY\",\"6\":\"unknown_0\"},\"line_index\":18},{\"StructureType\":\"table\",\"data\":{\"0\":\"LR1\",\"1\":\"55KT\",\"2\":\"AG/JPN\",\"3\":\"FLAT\",\"4\":\"WS105\",\"5\":\"\",\"6\":\"STABLE\"},\"header\":{\"0\":\"SIZE\",\"1\":\"QTY\",\"2\":\"ROUTE\",\"3\":\"CHANGE\",\"4\":\"06-MAY\",\"5\":\"03-MAY\",\"6\":\"unknown_0\"},\"line_index\":19},{\"StructureType\":\"table\",\"data\":{\"0\":\"LR1\",\"1\":\"60KT\",\"2\":\"SK/SIN\",\"3\":\"+25K\",\"4\":\"525K\",\"5\":\"500K\",\"6\":\"STABLE\"},\"header\":{\"0\":\"SIZE\",\"1\":\"QTY\",\"2\":\"ROUTE\",\"3\":\"CHANGE\",\"4\":\"06-MAY\",\"5\":\"03-MAY\",\"6\":\"unknown_0\"},\"line_index\":20},{\"StructureType\":\"table\",\"data\":{\"0\":\"MR\",\"1\":\"35KT\",\"2\":\"WCI/JPN\",\"3\":\"FLAT\",\"4\":\"WS107.5\",\"5\":\"\",\"6\":\"STABLE\"},\"header\":{\"0\":\"SIZE\",\"1\":\"QTY\",\"2\":\"ROUTE\",\"3\":\"CHANGE\",\"4\":\"06-MAY\",\"5\":\"03-MAY\",\"6\":\"unknown_0\"},\"line_index\":21},{\"StructureType\":\"table\",\"data\":{\"0\":\"MR\",\"1\":\"30KT\",\"2\":\"SIN/JPN\",\"3\":\"FLAT\",\"4\":\"WS140\",\"5\":\"\",\"6\":\"STABLE\"},\"header\":{\"0\":\"SIZE\",\"1\":\"QTY\",\"2\":\"ROUTE\",\"3\":\"CHANGE\",\"4\":\"06-MAY\",\"5\":\"03-MAY\",\"6\":\"unknown_0\"},\"line_index\":22},{\"StructureType\":\"table\",\"data\":{\"0\":\"MR\",\"1\":\"35KT\",\"2\":\"SIN/OZ\",\"3\":\"-1\",\"4\":\"WS169\",\"5\":\"WS170\",\"6\":\"STABLE\"},\"header\":{\"0\":\"SIZE\",\"1\":\"QTY\",\"2\":\"ROUTE\",\"3\":\"CHANGE\",\"4\":\"06-MAY\",\"5\":\"03-MAY\",\"6\":\"unknown_0\"},\"line_index\":23},{\"StructureType\":\"table\",\"data\":{\"0\":\"MR\",\"1\":\"35KT\",\"2\":\"SK/OZ\",\"3\":\"-5\",\"4\":\"WS170\",\"5\":\"WS175\",\"6\":\"SOFT\"},\"header\":{\"0\":\"SIZE\",\"1\":\"QTY\",\"2\":\"ROUTE\",\"3\":\"CHANGE\",\"4\":\"06-MAY\",\"5\":\"03-MAY\",\"6\":\"unknown_0\"},\"line_index\":24},{\"StructureType\":\"table\",\"data\":{\"0\":\"MR\",\"1\":\"35KT\",\"2\":\"SK/USWC\",\"3\":\"-60K\",\"4\":\"1125K\",\"5\":\"1185K\",\"6\":\"SOFT\"},\"header\":{\"0\":\"SIZE\",\"1\":\"QTY\",\"2\":\"ROUTE\",\"3\":\"CHANGE\",\"4\":\"06-MAY\",\"5\":\"03-MAY\",\"6\":\"unknown_0\"},\"line_index\":25},{\"StructureType\":\"table\",\"data\":{\"0\":\"MR\",\"1\":\"30KT\",\"2\":\"SK/JPN\",\"3\":\"-30K\",\"4\":\"270K\",\"5\":\"300K\",\"6\":\"SOFT\"},\"header\":{\"0\":\"SIZE\",\"1\":\"QTY\",\"2\":\"ROUTE\",\"3\":\"CHANGE\",\"4\":\"06-MAY\",\"5\":\"03-MAY\",\"6\":\"unknown_0\"},\"line_index\":26},{\"StructureType\":\"table\",\"data\":{\"0\":\"MR\",\"1\":\"30KT\",\"2\":\"SK/SIN\",\"3\":\"-45K\",\"4\":\"400K\",\"5\":\"445K\",\"6\":\"SOFT\"},\"header\":{\"0\":\"SIZE\",\"1\":\"QTY\",\"2\":\"ROUTE\",\"3\":\"CHANGE\",\"4\":\"06-MAY\",\"5\":\"03-MAY\",\"6\":\"unknown_0\"},\"line_index\":27},{\"StructureType\":\"table\",\"data\":{\"0\":\"MR\",\"1\":\"35KT\",\"2\":\"SIN/SIN\",\"3\":\"FLAT\",\"4\":\"155K\",\"5\":\"\",\"6\":\"STABLE\"},\"header\":{\"0\":\"SIZE\",\"1\":\"QTY\",\"2\":\"ROUTE\",\"3\":\"CHANGE\",\"4\":\"06-MAY\",\"5\":\"03-MAY\",\"6\":\"unknown_0\"},\"line_index\":28},{\"StructureType\":\"table\",\"data\":{\"0\":\"MR\",\"1\":\"35KT\",\"2\":\"SK/HK\",\"3\":\"-40K\",\"4\":\"330K\",\"5\":\"370K\",\"6\":\"SOFT\"},\"header\":{\"0\":\"SIZE\",\"1\":\"QTY\",\"2\":\"ROUTE\",\"3\":\"CHANGE\",\"4\":\"06-MAY\",\"5\":\"03-MAY\",\"6\":\"unknown_0\"},\"line_index\":29},{\"StructureType\":\"table\",\"data\":{\"0\":\"MR\",\"1\":\"35KT\",\"2\":\"SIN/HK\",\"3\":\"FLAT\",\"4\":\"330K\",\"5\":\"\",\"6\":\"STABLE\"},\"header\":{\"0\":\"SIZE\",\"1\":\"QTY\",\"2\":\"ROUTE\",\"3\":\"CHANGE\",\"4\":\"06-MAY\",\"5\":\"03-MAY\",\"6\":\"unknown_0\"},\"line_index\":30}]},\"table_2\":{\"header_data\":[{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"FO\"},{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"PRICE\"}],\"line_index\":0,\"number_of_words\":2},{\"data\":[],\"line_index\":1,\"number_of_words\":0},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SINGAPORE\"}],\"line_index\":2,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"FUJAIRAH\"}],\"line_index\":3,\"number_of_words\":1}],\"table_data\":[{\"StructureType\":\"table\",\"data\":{\"0\":\"SINGAPORE\",\"1\":\"USD\",\"2\":\"422.50\",\"3\":\"-12.00\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"unknown_1\",\"2\":\"03-MAY\",\"3\":\"CHANGE FROM LAST\"},\"line_index\":35},{\"StructureType\":\"table\",\"data\":{\"0\":\"FUJAIRAH\",\"1\":\"USD\",\"2\":\"420.00\",\"3\":\"-15.00\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"unknown_1\",\"2\":\"03-MAY\",\"3\":\"CHANGE FROM LAST\"},\"line_index\":36}]},\"table_3\":{\"header_data\":[{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"MGO\"},{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"PRICE\"}],\"line_index\":0,\"number_of_words\":2},{\"data\":[],\"line_index\":1,\"number_of_words\":0},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SINGAPORE\"}],\"line_index\":2,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"FUJAIRAH\"}],\"line_index\":3,\"number_of_words\":1}],\"table_data\":[{\"StructureType\":\"table\",\"data\":{\"0\":\"SINGAPORE\",\"1\":\"USD\",\"2\":\"613.50\",\"3\":\"-14.00\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"unknown_1\",\"2\":\"03-MAY\",\"3\":\"CHANGE FROM LAST\"},\"line_index\":41},{\"StructureType\":\"table\",\"data\":{\"0\":\"FUJAIRAH\",\"1\":\"USD\",\"2\":\"720.00\",\"3\":\"-\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"unknown_1\",\"2\":\"03-MAY\",\"3\":\"CHANGE FROM LAST\"},\"line_index\":42}]},\"table_4\":{\"header_data\":[{\"data\":[],\"line_index\":0,\"number_of_words\":0},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"cargo\"},{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"DISCHARGE\"}],\"line_index\":1,\"number_of_words\":2}],\"table_data\":[]},\"table_5\":{\"header_data\":[{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"FAR EAST\"}],\"line_index\":0,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",\"word\":\"ATC\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"CPP\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"NISSOS CHRISTIANA\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SKOREA\"},{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"CPP\"}],\"line_index\":1,\"number_of_words\":5},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"CPP\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"JAPAN\"},{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"CPP\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",\"word\":\"BP\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"CHAMPION PRINCE\"}],\"line_index\":2,\"number_of_words\":5},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"CPP\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"SCF PIONEER\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"JAPAN\"},{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"CPP\"}],\"line_index\":3,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"SPORE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"PTI NILE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SPORE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ums\"}],\"line_index\":4,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"QINGDAO\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"ARDMORE SEALANCER\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"},{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"SPORE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",\"word\":\"UNIPEC\"}],\"line_index\":5,\"number_of_words\":5},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SPORE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",\"word\":\"UNIPEC\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"OCEAN SPRING\"},{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"SPORE\"}],\"line_index\":6,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"YOSU\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"ATLANTIC MIRAGE\"},{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"CPP\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",\"word\":\"CHEVRON\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"CPP\"}],\"line_index\":7,\"number_of_words\":5},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"GLENDA MEGAN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"WCMEX\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ULSD\"}],\"line_index\":8,\"number_of_words\":3}],\"table_data\":[{\"StructureType\":\"table\",\"data\":{\"0\":\"CHAMPION PRINCE\",\"1\":\"75\",\"2\":\"CPP\",\"3\":\"17/MAY\",\"4\":\"JAPAN\",\"5\":\"OZ\",\"6\":\"WS97.5\",\"7\":\"BP\",\"8\":\"SUB\"},\"header\":{\"0\":\"NISSOS CHRISTIANA\",\"1\":\"90\",\"2\":\"CPP\",\"3\":\"17/MAY\",\"4\":\"SKOREA\",\"5\":\"UKC\",\"6\":\"2.35M\",\"7\":\"ATC\",\"8\":\"SUB\"},\"line_index\":52},{\"StructureType\":\"table\",\"data\":{\"0\":\"SCF PIONEER\",\"1\":\"60\",\"2\":\"CPP\",\"3\":\"17/MAY\",\"4\":\"JAPAN\",\"5\":\"OZ\",\"6\":\"WS114\",\"7\":\"CPC\",\"8\":\"SUB\"},\"header\":{\"0\":\"NISSOS CHRISTIANA\",\"1\":\"90\",\"2\":\"CPP\",\"3\":\"17/MAY\",\"4\":\"SKOREA\",\"5\":\"UKC\",\"6\":\"2.35M\",\"7\":\"ATC\",\"8\":\"SUB\"},\"line_index\":53},{\"StructureType\":\"table\",\"data\":{\"0\":\"PTI NILE\",\"1\":\"35\",\"2\":\"UMS\",\"3\":\"09/MAY\",\"4\":\"DALIAN\",\"5\":\"SPORE-INDO-ECOZ\",\"6\":\"410K-RNR-RNR\",\"7\":\"SHELL\",\"8\":\"SUB\"},\"header\":{\"0\":\"NISSOS CHRISTIANA\",\"1\":\"90\",\"2\":\"CPP\",\"3\":\"17/MAY\",\"4\":\"SKOREA\",\"5\":\"UKC\",\"6\":\"2.35M\",\"7\":\"ATC\",\"8\":\"SUB\"},\"line_index\":54},{\"StructureType\":\"table\",\"data\":{\"0\":\"ARDMORE SEALANCER\",\"1\":\"35\",\"2\":\"ULSD\",\"3\":\"10/MAY\",\"4\":\"QINGDAO\",\"5\":\"SPORE\",\"6\":\"410K\",\"7\":\"UNIPEC\",\"8\":\"SUB\"},\"header\":{\"0\":\"NISSOS CHRISTIANA\",\"1\":\"90\",\"2\":\"CPP\",\"3\":\"17/MAY\",\"4\":\"SKOREA\",\"5\":\"UKC\",\"6\":\"2.35M\",\"7\":\"ATC\",\"8\":\"SUB\"},\"line_index\":55},{\"StructureType\":\"table\",\"data\":{\"0\":\"OCEAN SPRING\",\"1\":\"35\",\"2\":\"GO\",\"3\":\"11/MAY\",\"4\":\"GAOQIAO\",\"5\":\"SPORE\",\"6\":\"410K\",\"7\":\"UNIPEC\",\"8\":\"SUB\"},\"header\":{\"0\":\"NISSOS CHRISTIANA\",\"1\":\"90\",\"2\":\"CPP\",\"3\":\"17/MAY\",\"4\":\"SKOREA\",\"5\":\"UKC\",\"6\":\"2.35M\",\"7\":\"ATC\",\"8\":\"SUB\"},\"line_index\":56},{\"StructureType\":\"table\",\"data\":{\"0\":\"ATLANTIC MIRAGE\",\"1\":\"35\",\"2\":\"CPP\",\"3\":\"12/MAY\",\"4\":\"YOSU\",\"5\":\"PHIL\",\"6\":\"RNR\",\"7\":\"CHEVRON\",\"8\":\"FXD\"},\"header\":{\"0\":\"NISSOS CHRISTIANA\",\"1\":\"90\",\"2\":\"CPP\",\"3\":\"17/MAY\",\"4\":\"SKOREA\",\"5\":\"UKC\",\"6\":\"2.35M\",\"7\":\"ATC\",\"8\":\"SUB\"},\"line_index\":57},{\"StructureType\":\"table\",\"data\":{\"0\":\"GLENDA MEGAN\",\"1\":\"35\",\"2\":\"ULSD\",\"3\":\"14/MAY\",\"4\":\"JAPAN\",\"5\":\"USWC-WCMEX\",\"6\":\"1.21M-1.31M\",\"7\":\"P66\",\"8\":\"SUB\"},\"header\":{\"0\":\"NISSOS CHRISTIANA\",\"1\":\"90\",\"2\":\"CPP\",\"3\":\"17/MAY\",\"4\":\"SKOREA\",\"5\":\"UKC\",\"6\":\"2.35M\",\"7\":\"ATC\",\"8\":\"SUB\"},\"line_index\":58}]},\"table_6\":{\"header_data\":[{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SINGAPORE\"}],\"line_index\":0,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"CPP\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"PHIL\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"GULF CORAL\"},{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"CPP\"}],\"line_index\":1,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"REUNION\"},{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"CPP\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"CHANG JIANG\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"CPP\"}],\"line_index\":2,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"STI MAYFAIR\"},{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"SPORE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SPORE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"CPP\"}],\"line_index\":3,\"number_of_words\":4}],\"table_data\":[{\"StructureType\":\"table\",\"data\":{\"0\":\"CHANG JIANG OOS\",\"1\":\"35\",\"2\":\"CPP\",\"3\":\"07/MAY\",\"4\":\"SPORE\",\"5\":\"REUNION\",\"6\":\"720K\",\"7\":\"CSSSA\",\"8\":\"\"},\"header\":{\"0\":\"GULF CORAL\",\"1\":\"60\",\"2\":\"CPP\",\"3\":\"06/MAY\",\"4\":\"SPORE\",\"5\":\"PHIL\",\"6\":\"445K\",\"7\":\"PETRON\",\"8\":\"OLD\"},\"line_index\":63},{\"StructureType\":\"table\",\"data\":{\"0\":\"STI MAYFAIR\",\"1\":\"35\",\"2\":\"CPP\",\"3\":\"09/MAY\",\"4\":\"SPORE\",\"5\":\"OZ\",\"6\":\"WS167.5\",\"7\":\"AMPOL\",\"8\":\"\"},\"header\":{\"0\":\"GULF CORAL\",\"1\":\"60\",\"2\":\"CPP\",\"3\":\"06/MAY\",\"4\":\"SPORE\",\"5\":\"PHIL\",\"6\":\"445K\",\"7\":\"PETRON\",\"8\":\"OLD\"},\"line_index\":64}]},\"table_7\":{\"header_data\":[{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"INDIA\"}],\"line_index\":0,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"CPP\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"CPP\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"POLAR BRIGHT\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",\"word\":\"ATC\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"AG\"}],\"line_index\":1,\"number_of_words\":5},{\"data\":[{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"SPORE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"FAIR SEAS\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SPORE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",\"word\":\"UNIPEC\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ulsd\"}],\"line_index\":2,\"number_of_words\":5},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ULSD\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"UKC\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"SEAENVOY\"}],\"line_index\":3,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",\"word\":\"SOCAR\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"JAPAN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"CAPTAIN JOHN\"}],\"line_index\":4,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Region\",\"word\":\"FUJ\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"UKC\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"UMS\"},{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"FUJ\"}],\"line_index\":5,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Region\",\"word\":\"Fuj\"},{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"FUJ\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"TUTICORIN\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"SANMAR SONGBIRD\"}],\"line_index\":6,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"JUBAIL\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"ums\"}],\"line_index\":7,\"number_of_words\":2}],\"table_data\":[{\"StructureType\":\"table\",\"data\":{\"0\":\"FAIR SEAS\",\"1\":\"90\",\"2\":\"ULSD\",\"3\":\"17/MAY\",\"4\":\"SIKKA\",\"5\":\"UKC-SPORE\",\"6\":\"2.2M-WS115\",\"7\":\"UNIPEC\",\"8\":\"SUB\"},\"header\":{\"0\":\"POLAR BRIGHT\",\"1\":\"90\",\"2\":\"CPP\",\"3\":\"14/MAY\",\"4\":\"AG\",\"5\":\"EAFR\",\"6\":\"WS110\",\"7\":\"ATC\",\"8\":\"SUB\"},\"line_index\":69},{\"StructureType\":\"table\",\"data\":{\"0\":\"SEAENVOY\",\"1\":\"90\",\"2\":\"ULSD\",\"3\":\"19/MAY\",\"4\":\"SIKKA\",\"5\":\"UKC\",\"6\":\"2.25M\",\"7\":\"KOCH\",\"8\":\"RPLC\"},\"header\":{\"0\":\"POLAR BRIGHT\",\"1\":\"90\",\"2\":\"CPP\",\"3\":\"14/MAY\",\"4\":\"AG\",\"5\":\"EAFR\",\"6\":\"WS110\",\"7\":\"ATC\",\"8\":\"SUB\"},\"line_index\":70},{\"StructureType\":\"table\",\"data\":{\"0\":\"CAPTAIN JOHN\",\"1\":\"75\",\"2\":\"NAP\",\"3\":\"15/MAY\",\"4\":\"AG\",\"5\":\"JAPAN\",\"6\":\"WS100\",\"7\":\"SOCAR\",\"8\":\"SUB\"},\"header\":{\"0\":\"POLAR BRIGHT\",\"1\":\"90\",\"2\":\"CPP\",\"3\":\"14/MAY\",\"4\":\"AG\",\"5\":\"EAFR\",\"6\":\"WS110\",\"7\":\"ATC\",\"8\":\"SUB\"},\"line_index\":71},{\"StructureType\":\"table\",\"data\":{\"0\":\"NORDIC TRISTAN\",\"1\":\"60\",\"2\":\"UMS\",\"3\":\"15/MAY\",\"4\":\"SIKKA\",\"5\":\"FUJ-UKC\",\"6\":\"325K-1.6M\",\"7\":\"SKE\",\"8\":\"SUB\"},\"header\":{\"0\":\"POLAR BRIGHT\",\"1\":\"90\",\"2\":\"CPP\",\"3\":\"14/MAY\",\"4\":\"AG\",\"5\":\"EAFR\",\"6\":\"WS110\",\"7\":\"ATC\",\"8\":\"SUB\"},\"line_index\":72},{\"StructureType\":\"table\",\"data\":{\"0\":\"SANMAR SONGBIRD\",\"1\":\"35\",\"2\":\"NAP\",\"3\":\"10/MAY\",\"4\":\"FUJ\",\"5\":\"TUTICORIN\",\"6\":\"335K\",\"7\":\"ST\",\"8\":\"SUB\"},\"header\":{\"0\":\"POLAR BRIGHT\",\"1\":\"90\",\"2\":\"CPP\",\"3\":\"14/MAY\",\"4\":\"AG\",\"5\":\"EAFR\",\"6\":\"WS110\",\"7\":\"ATC\",\"8\":\"SUB\"},\"line_index\":73},{\"StructureType\":\"table\",\"data\":{\"0\":\"HOUYOSHI EXPRESS\",\"1\":\"35\",\"2\":\"UMS\",\"3\":\"12/MAY\",\"4\":\"JUBAIL\",\"5\":\"NEW YORK\",\"6\":\"1.4M\",\"7\":\"CSSSA\",\"8\":\"SUB\"},\"header\":{\"0\":\"POLAR BRIGHT\",\"1\":\"90\",\"2\":\"CPP\",\"3\":\"14/MAY\",\"4\":\"AG\",\"5\":\"EAFR\",\"6\":\"WS110\",\"7\":\"ATC\",\"8\":\"SUB\"},\"line_index\":74}]},\"table_8\":{\"header_data\":[{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",\"word\":\"GLOBAL\"}],\"line_index\":0,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"Morningstar\"}],\"line_index\":1,\"number_of_words\":1},{\"data\":[],\"line_index\":2,\"number_of_words\":0},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"SINGAPORE\"}],\"line_index\":3,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Source\",\"word\":\"flglobal\"}],\"line_index\":4,\"number_of_words\":1},{\"data\":[],\"line_index\":5,\"number_of_words\":0}],\"table_data\":[{\"StructureType\":\"table\",\"data\":{\"0\":\"MORNINGSTAR CENTRE\"},\"header\":{\"0\":\"FIRSTLINK GLOBAL PTE LTD\"},\"line_index\":78},{\"StructureType\":\"table\",\"data\":{\"0\":\"12 NEW INDUSTRIAL ROAD #03-02C\"},\"header\":{\"0\":\"FIRSTLINK GLOBAL PTE LTD\"},\"line_index\":79},{\"StructureType\":\"table\",\"data\":{\"0\":\"SINGAPORE 536202\"},\"header\":{\"0\":\"FIRSTLINK GLOBAL PTE LTD\"},\"line_index\":80},{\"StructureType\":\"table\",\"data\":{\"0\":\"EMAIL: TANKERS@FLGLOBAL.COM.SG\"},\"header\":{\"0\":\"FIRSTLINK GLOBAL PTE LTD\"},\"line_index\":81},{\"StructureType\":\"table\",\"data\":{\"0\":\"TEL: +65 64791066\"},\"header\":{\"0\":\"FIRSTLINK GLOBAL PTE LTD\"},\"line_index\":82}]},\"table_9\":{\"header_data\":[{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Region\",\"word\":\"SUEZ\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",\"word\":\"CLEAN\"}],\"line_index\":0,\"number_of_words\":2}],\"table_data\":[{\"StructureType\":\"line\",\"data\":{\"0\":\"   FIRSTLINK_CLEAN_MARKET_REPORT_(EAST_OF_SUEZ)_-_06MAY2019  \"},\"header\":{\"0\":\"   FIRSTLINK_CLEAN_MARKET_REPORT_(EAST_OF_SUEZ)_-_06MAY2019  \"},\"label_line_index\":0,\"label_string\":\"   FIRSTLINK_CLEAN_MARKET_REPORT_(EAST_OF_SUEZ)_-_06MAY2019  \",\"line_index\":0}]}}";
	//String pythonScriptApiData="{  \"table_0\": {    \"header_data\": [      {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Alliance\"          }        ],         \"line_index\": 0,         \"number_of_words\": 1      },       {        \"data\": [],         \"line_index\": 1,         \"number_of_words\": 0      },       {        \"data\": [],         \"line_index\": 2,         \"number_of_words\": 0      }    ],     \"table_data\": [      {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"11 COLLYER QUAY #07-05\"        },         \"header\": {          \"0\": \"ALLIANCE PRODUCT TANKER CHARTERING\"        },         \"line_index\": 1      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"TEL: (65) 6224 0123\"        },         \"header\": {          \"0\": \"ALLIANCE PRODUCT TANKER CHARTERING\"        },         \"line_index\": 2      }    ]  },   \"table_1\": {    \"header_data\": [      {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Mobile\"          }        ],         \"line_index\": 0,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Hagen\"          }        ],         \"line_index\": 1,         \"number_of_words\": 1      },       {        \"data\": [],         \"line_index\": 2,         \"number_of_words\": 0      },       {        \"data\": [],         \"line_index\": 3,         \"number_of_words\": 0      }    ],     \"table_data\": [      {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"MARIUS HAGEN\",           \"1\": \"(65) 6225 1907\",           \"2\": \"(65) 9018 2799\",           \"3\": \"\",           \"4\": \"MHAGEN\",           \"5\": \"\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"DIRECT\",           \"2\": \"unknown_1\",           \"3\": \"MOBILE\",           \"4\": \"unknown_2\",           \"5\": \"ICE\"        },         \"line_index\": 5      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"MATTHEW KORBOSKY\",           \"1\": \"(65) 6224 2747\",           \"2\": \"(65) 9777 8488\",           \"3\": \"\",           \"4\": \"MKORBOSKY\",           \"5\": \"\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"DIRECT\",           \"2\": \"unknown_1\",           \"3\": \"MOBILE\",           \"4\": \"unknown_2\",           \"5\": \"ICE\"        },         \"line_index\": 6      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"REJANE MEYER\",           \"1\": \"(65) 6220 4462\",           \"2\": \"(65) 9270 7275\",           \"3\": \"\",           \"4\": \"RMEYER8\",           \"5\": \"\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"DIRECT\",           \"2\": \"unknown_1\",           \"3\": \"MOBILE\",           \"4\": \"unknown_2\",           \"5\": \"ICE\"        },         \"line_index\": 7      }    ]  },   \"table_2\": {    \"header_data\": [      {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CLEAN\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Singapore\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\",             \"word\": \"position list\"          }        ],         \"line_index\": 0,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"DWT\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#Class\",             \"word\": \"Fleet\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#ObjectProperty\",             \"word\": \"open port\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Fleet\"          }        ],         \"line_index\": 1,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"vitol\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"ELANDRA BALTIC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Singapore\"          }        ],         \"line_index\": 3,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Singapore\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"norient\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"RICH BREEZE\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          }        ],         \"line_index\": 4,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Singapore\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"vinalines\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"VINALINES GLORY\"          }        ],         \"line_index\": 5,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Singapore\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          }        ],         \"line_index\": 6,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Port Klang\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ulsd\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"ALPINE MADELEINE\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          }        ],         \"line_index\": 7,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"koch\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Korea\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"JAG PUNIT\"          }        ],         \"line_index\": 8,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Singapore\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"OCEAN NEPTUNE\"          }        ],         \"line_index\": 9,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"MERCINI LADY\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Chittagong\"          }        ],         \"line_index\": 10,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Colombo\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"torm\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"TORM ASLAUG\"          }        ],         \"line_index\": 11,         \"number_of_words\": 6      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Map Ta Phut\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"STENA IMMACULATE\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          }        ],         \"line_index\": 12,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"OCEAN COSMOS\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Singapore\"          }        ],         \"line_index\": 13,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"BRITISH CHIEF\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"bp\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Broome\"          }        ],         \"line_index\": 14,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Chittagong\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"CHALLENGE PHOENIX\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          }        ],         \"line_index\": 15,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Batangas\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"jet\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"ATLANTIC AQUARIUS\"          }        ],         \"line_index\": 16,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"GRAND ACE2\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          }        ],         \"line_index\": 17,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"PETROLIMEX 10\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"petrolimex\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Nha Be\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          }        ],         \"line_index\": 18,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"CHALLENGE PREMIER\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"change\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Change\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"falcon\"          }        ],         \"line_index\": 19,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"OCEAN AUTUMN\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          }        ],         \"line_index\": 20,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"ORIENTAL GOLD\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          }        ],         \"line_index\": 21,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"CHALLENGE PASSAGE\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"maersk tank\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Tuticorin\"          }        ],         \"line_index\": 22,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Colombo\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"st shipping\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          }        ],         \"line_index\": 23,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Tagoloan\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"BW SWIFT\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"jet\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"hafnia\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"bw\"          }        ],         \"line_index\": 24,         \"number_of_words\": 7      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Nha Be\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"petrolimex\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"PETROLIMEX 11\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          }        ],         \"line_index\": 25,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Singapore\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ulsd\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"DAI MINH\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"vosco\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          }        ],         \"line_index\": 26,         \"number_of_words\": 6      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"st shipping\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Chittagong\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"VINALINES GALAXY\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          }        ],         \"line_index\": 27,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"STI MAYFAIR\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Chittagong\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          }        ],         \"line_index\": 28,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"OCEAN VICTORY\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Paradip\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          }        ],         \"line_index\": 29,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Chennai\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"SWARNA KALASH\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"sci\"          }        ],         \"line_index\": 30,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Haldia\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"JAG PRANAM\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          }        ],         \"line_index\": 31,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"jet\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Subic Bay\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"NCC QAMAR\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"uacc\"          }        ],         \"line_index\": 32,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"TORM ERIC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"torm\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          }        ],         \"line_index\": 33,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Nanjing\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"CSC CORAL\"          }        ],         \"line_index\": 34,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"vitol\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Wyndham\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"HELLAS REVENGER\"          }        ],         \"line_index\": 35,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Chittagong\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"jet\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"NEVESKA LADY\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          }        ],         \"line_index\": 36,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"petrolimex\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"PETROLIMEX 09\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Hong Gai\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          }        ],         \"line_index\": 37,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"NCC NAJEM\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"uacc\"          }        ],         \"line_index\": 38,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"petrolimex\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"PETROLIMEX 16\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Nha Be\"          }        ],         \"line_index\": 39,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Singapore\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"jet\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"TORM HELVIG\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"torm\"          }        ],         \"line_index\": 40,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"GRAND ACE5\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Nha Be\"          }        ],         \"line_index\": 41,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Vietnam\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"uacc\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"GULF HUWAYLAT\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          }        ],         \"line_index\": 42,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"OCEAN MARS\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Singapore\"          }        ],         \"line_index\": 43,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"oetker\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"ST. PAULI\"          }        ],         \"line_index\": 44,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"uacc\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"NCC TIHAMA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Singapore\"          }        ],         \"line_index\": 45,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"SEA CHAMPION\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Balongan\"          }        ],         \"line_index\": 46,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Nanjing\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"FOREVER HARMONY\"          }        ],         \"line_index\": 47,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"damico\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Vizag\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"HIGH EFFICIENCY\"          }        ],         \"line_index\": 48,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"torm\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"TORM AGNES\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Colombo\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          }        ],         \"line_index\": 49,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"shell\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"SILVER MONIKA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Singapore\"          }        ],         \"line_index\": 50,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Singapore\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"scf\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"TEATRALNY BRIDGE\"          }        ],         \"line_index\": 51,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"ATLANTIC POLARIS\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Chittagong\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          }        ],         \"line_index\": 52,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Sandakan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"Doric\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          }        ],         \"line_index\": 53,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ulsd\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"ATLANTIC MIRAGE\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          }        ],         \"line_index\": 54,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"vitol\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"China\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"GRAND ACE7\"          }        ],         \"line_index\": 55,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"NAVIG8 STRENGTH\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"navig8\"          }        ],         \"line_index\": 56,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"petrolimex\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"PETROLIMEX 18\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          }        ],         \"line_index\": 57,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Timaru\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"BRITISH CADET\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"bp\"          }        ],         \"line_index\": 58,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"PETROLIMEX 08\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Hong Gai\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"petrolimex\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          }        ],         \"line_index\": 59,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"nyk\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Brisbane\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"CHALLENGE PACIFIC\"          }        ],         \"line_index\": 60,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Brisbane\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"ORIENTAL DIAMOND\"          }        ],         \"line_index\": 61,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"ELANDRA SPRUCE\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"vitol\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Cairns\"          }        ],         \"line_index\": 62,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"NS SILVER\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"scf\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          }        ],         \"line_index\": 63,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"NCC SUDAIR\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Singapore\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"uacc\"          }        ],         \"line_index\": 64,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"ARCHON\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Mongla\"          }        ],         \"line_index\": 65,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"Atlantica\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Haldia\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"veg\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          }        ],         \"line_index\": 66,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Korea\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"vitol\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"NAVE LUMINOSITY\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          }        ],         \"line_index\": 67,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Korea\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ulsd\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"ardmoreship\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"ARDMORE ENGINEER\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          }        ],         \"line_index\": 68,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Bunbury\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          }        ],         \"line_index\": 69,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ulsd\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"ATLANTIC INFINITY\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Adelaide\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"vitol\"          }        ],         \"line_index\": 70,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Port Bonython\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"FREJA HAFNIA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          }        ],         \"line_index\": 71,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Kwinana\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"NORDIC PIA\"          }        ],         \"line_index\": 72,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"palms\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"ARIONAS\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Haldia\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          }        ],         \"line_index\": 73,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"HERMITAGE BRIDGE\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"cln\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Suva\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"scf\"          }        ],         \"line_index\": 74,         \"number_of_words\": 4      }    ],     \"table_data\": [      {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"   ALLIANCE_-_CLEAN_MR_POSITION_LIST_BSS_SINGAPORE  \"        },         \"header\": {          \"0\": \"   ALLIANCE_-_CLEAN_MR_POSITION_LIST_BSS_SINGAPORE  \"        },         \"label_line_index\": 0,         \"label_string\": \"   ALLIANCE_-_CLEAN_MR_POSITION_LIST_BSS_SINGAPORE  \",         \"line_index\": 0      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     VESSEL                 CGO  YR  DWT  CUB  OPEN   PORT             SPORE   FLEET         COMMENTS     \"        },         \"header\": {          \"0\": \"     VESSEL                 CGO  YR  DWT  CUB  OPEN   PORT             SPORE   FLEET         COMMENTS     \"        },         \"label_line_index\": 1,         \"label_string\": \"     VESSEL                 CGO  YR  DWT  CUB  OPEN   PORT             SPORE   FLEET         COMMENTS     \",         \"line_index\": 1      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     ELANDRA BALTIC         CLN  11   51   53  25/04  SINGAPORE        25/04  VITOL     \"        },         \"header\": {          \"0\": \"     ELANDRA BALTIC         CLN  11   51   53  25/04  SINGAPORE        25/04  VITOL     \"        },         \"label_line_index\": 3,         \"label_string\": \"     ELANDRA BALTIC         CLN  11   51   53  25/04  SINGAPORE        25/04  VITOL     \",         \"line_index\": 3      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     RICH BREEZE            CLN  09   47   50  25/04  SINGAPORE        25/04  NORIENT       LC UMS     \"        },         \"header\": {          \"0\": \"     RICH BREEZE            CLN  09   47   50  25/04  SINGAPORE        25/04  NORIENT       LC UMS     \"        },         \"label_line_index\": 4,         \"label_string\": \"     RICH BREEZE            CLN  09   47   50  25/04  SINGAPORE        25/04  NORIENT       LC UMS     \",         \"line_index\": 4      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     VINALINES GLORY        CLN  06   50   52  25/04  SINGAPORE        25/04  VINALINES     LC UMS     \"        },         \"header\": {          \"0\": \"     VINALINES GLORY        CLN  06   50   52  25/04  SINGAPORE        25/04  VINALINES     LC UMS     \"        },         \"label_line_index\": 5,         \"label_string\": \"     VINALINES GLORY        CLN  06   50   52  25/04  SINGAPORE        25/04  VINALINES     LC UMS     \",         \"line_index\": 5      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     PRIMA XP               C/D  02   40   43  25/04  SINGAPORE        25/04  PERTAMINA     \"        },         \"header\": {          \"0\": \"     PRIMA XP               C/D  02   40   43  25/04  SINGAPORE        25/04  PERTAMINA     \"        },         \"label_line_index\": 6,         \"label_string\": \"     PRIMA XP               C/D  02   40   43  25/04  SINGAPORE        25/04  PERTAMINA     \",         \"line_index\": 6      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     ALPINE MADELEINE       CLN  08   49   51  27/04  PORT KLANG       27/04  DIAMONDS      LC UMS+ULSD     \"        },         \"header\": {          \"0\": \"     ALPINE MADELEINE       CLN  08   49   51  27/04  PORT KLANG       27/04  DIAMONDS      LC UMS+ULSD     \"        },         \"label_line_index\": 7,         \"label_string\": \"     ALPINE MADELEINE       CLN  08   49   51  27/04  PORT KLANG       27/04  DIAMONDS      LC UMS+ULSD     \",         \"line_index\": 7      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     JAG PUNIT              CLN  16   49   52  20/04  GROOTE EYLANDT   28/04  KOCH          EX SING/OZ - KOREA 1MAY - SUBS     \"        },         \"header\": {          \"0\": \"     JAG PUNIT              CLN  16   49   52  20/04  GROOTE EYLANDT   28/04  KOCH          EX SING/OZ - KOREA 1MAY - SUBS     \"        },         \"label_line_index\": 8,         \"label_string\": \"     JAG PUNIT              CLN  16   49   52  20/04  GROOTE EYLANDT   28/04  KOCH          EX SING/OZ - KOREA 1MAY - SUBS     \",         \"line_index\": 8      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     OCEAN NEPTUNE          CLN  05   50   60  28/04  SINGAPORE        28/04  OCEANTANKERS  SUBS     \"        },         \"header\": {          \"0\": \"     OCEAN NEPTUNE          CLN  05   50   60  28/04  SINGAPORE        28/04  OCEANTANKERS  SUBS     \"        },         \"label_line_index\": 9,         \"label_string\": \"     OCEAN NEPTUNE          CLN  05   50   60  28/04  SINGAPORE        28/04  OCEANTANKERS  SUBS     \",         \"line_index\": 9      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     MERCINI LADY           CLN  04   46   51  24/04  CHITTAGONG       29/04  ARGYLL        LC JET - IF SING 29/4     \"        },         \"header\": {          \"0\": \"     MERCINI LADY           CLN  04   46   51  24/04  CHITTAGONG       29/04  ARGYLL        LC JET - IF SING 29/4     \"        },         \"label_line_index\": 10,         \"label_string\": \"     MERCINI LADY           CLN  04   46   51  24/04  CHITTAGONG       29/04  ARGYLL        LC JET - IF SING 29/4     \",         \"line_index\": 10      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     TORM ASLAUG            CLN  10   49   51  24/04  COLOMBO          29/04  TORM          LC GO+UMS - SUBS     \"        },         \"header\": {          \"0\": \"     TORM ASLAUG            CLN  10   49   51  24/04  COLOMBO          29/04  TORM          LC GO+UMS - SUBS     \"        },         \"label_line_index\": 11,         \"label_string\": \"     TORM ASLAUG            CLN  10   49   51  24/04  COLOMBO          29/04  TORM          LC GO+UMS - SUBS     \",         \"line_index\": 11      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     STENA IMMACULATE       CLN  17   51   46  27/04  MAP TA PHUT      29/04  STENABULK     PART FOSFA     \"        },         \"header\": {          \"0\": \"     STENA IMMACULATE       CLN  17   51   46  27/04  MAP TA PHUT      29/04  STENABULK     PART FOSFA     \"        },         \"label_line_index\": 12,         \"label_string\": \"     STENA IMMACULATE       CLN  17   51   46  27/04  MAP TA PHUT      29/04  STENABULK     PART FOSFA     \",         \"line_index\": 12      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     OCEAN COSMOS           CLN  00   50   60  29/04  SINGAPORE        29/04  OCEANTANKERS     \"        },         \"header\": {          \"0\": \"     OCEAN COSMOS           CLN  00   50   60  29/04  SINGAPORE        29/04  OCEANTANKERS     \"        },         \"label_line_index\": 13,         \"label_string\": \"     OCEAN COSMOS           CLN  00   50   60  29/04  SINGAPORE        29/04  OCEANTANKERS     \",         \"line_index\": 13      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     BRITISH CHIEF          CLN  17   45   52  26/04  BROOME           01/05  BP            EX SING/OZ     \"        },         \"header\": {          \"0\": \"     BRITISH CHIEF          CLN  17   45   52  26/04  BROOME           01/05  BP            EX SING/OZ     \"        },         \"label_line_index\": 14,         \"label_string\": \"     BRITISH CHIEF          CLN  17   45   52  26/04  BROOME           01/05  BP            EX SING/OZ     \",         \"line_index\": 14      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     CHALLENGE PHOENIX      CLN  07   47   52  26/04  CHITTAGONG       01/05  CARGILL     \"        },         \"header\": {          \"0\": \"     CHALLENGE PHOENIX      CLN  07   47   52  26/04  CHITTAGONG       01/05  CARGILL     \"        },         \"label_line_index\": 15,         \"label_string\": \"     CHALLENGE PHOENIX      CLN  07   47   52  26/04  CHITTAGONG       01/05  CARGILL     \",         \"line_index\": 15      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     ATLANTIC AQUARIUS      CLN  08   49   51  27/04  BATANGAS         01/05  DIAMONDS      LC JET+GO     \"        },         \"header\": {          \"0\": \"     ATLANTIC AQUARIUS      CLN  08   49   51  27/04  BATANGAS         01/05  DIAMONDS      LC JET+GO     \"        },         \"label_line_index\": 16,         \"label_string\": \"     ATLANTIC AQUARIUS      CLN  08   49   51  27/04  BATANGAS         01/05  DIAMONDS      LC JET+GO     \",         \"line_index\": 16      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     GRAND ACE2             CLN  06   45   52  27/04  HAI PHONG        01/05  PANOCEAN      LC GO     \"        },         \"header\": {          \"0\": \"     GRAND ACE2             CLN  06   45   52  27/04  HAI PHONG        01/05  PANOCEAN      LC GO     \"        },         \"label_line_index\": 17,         \"label_string\": \"     GRAND ACE2             CLN  06   45   52  27/04  HAI PHONG        01/05  PANOCEAN      LC GO     \",         \"line_index\": 17      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     PETROLIMEX 10          CLN  03   37   42  29/04  NHA BE           01/05  PETROLIMEX    LC GO     \"        },         \"header\": {          \"0\": \"     PETROLIMEX 10          CLN  03   37   42  29/04  NHA BE           01/05  PETROLIMEX    LC GO     \"        },         \"label_line_index\": 18,         \"label_string\": \"     PETROLIMEX 10          CLN  03   37   42  29/04  NHA BE           01/05  PETROLIMEX    LC GO     \",         \"line_index\": 18      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     ODORI                  CLN  05   45   52  01/05  SINGAPORE        01/05  FALCON        EX CHALLENGE PREMIER - CHANGE OF MNGMNT     \"        },         \"header\": {          \"0\": \"     ODORI                  CLN  05   45   52  01/05  SINGAPORE        01/05  FALCON        EX CHALLENGE PREMIER - CHANGE OF MNGMNT     \"        },         \"label_line_index\": 19,         \"label_string\": \"     ODORI                  CLN  05   45   52  01/05  SINGAPORE        01/05  FALCON        EX CHALLENGE PREMIER - CHANGE OF MNGMNT     \",         \"line_index\": 19      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     OCEAN AUTUMN           CLN  09   41   50  01/05  SPORE            01/05  OCEANTANKERS  SUBS     \"        },         \"header\": {          \"0\": \"     OCEAN AUTUMN           CLN  09   41   50  01/05  SPORE            01/05  OCEANTANKERS  SUBS     \"        },         \"label_line_index\": 20,         \"label_string\": \"     OCEAN AUTUMN           CLN  09   41   50  01/05  SPORE            01/05  OCEANTANKERS  SUBS     \",         \"line_index\": 20      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     ORIENTAL GOLD          CLN  08   50   53  27/04  CHITT            02/05  HMM           LC GO - SUBS     \"        },         \"header\": {          \"0\": \"     ORIENTAL GOLD          CLN  08   50   53  27/04  CHITT            02/05  HMM           LC GO - SUBS     \"        },         \"label_line_index\": 21,         \"label_string\": \"     ORIENTAL GOLD          CLN  08   50   53  27/04  CHITT            02/05  HMM           LC GO - SUBS     \",         \"line_index\": 21      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     CHALLENGE PASSAGE      CLN  05   48   54  27/04  TUTICORIN        02/05  MAERSK TANK   TBC     \"        },         \"header\": {          \"0\": \"     CHALLENGE PASSAGE      CLN  05   48   54  27/04  TUTICORIN        02/05  MAERSK TANK   TBC     \"        },         \"label_line_index\": 22,         \"label_string\": \"     CHALLENGE PASSAGE      CLN  05   48   54  27/04  TUTICORIN        02/05  MAERSK TANK   TBC     \",         \"line_index\": 22      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     SAPSAN                 CLN  05   47   50  27/04  COLOMBO          02/05  ST SHIPPING   LC CPP     \"        },         \"header\": {          \"0\": \"     SAPSAN                 CLN  05   47   50  27/04  COLOMBO          02/05  ST SHIPPING   LC CPP     \"        },         \"label_line_index\": 23,         \"label_string\": \"     SAPSAN                 CLN  05   47   50  27/04  COLOMBO          02/05  ST SHIPPING   LC CPP     \",         \"line_index\": 23      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     BW SWIFT               CLN  16   49   51  28/04  TAGOLOAN         02/05  HAFNIA-BW     LC JET - SUBS     \"        },         \"header\": {          \"0\": \"     BW SWIFT               CLN  16   49   51  28/04  TAGOLOAN         02/05  HAFNIA-BW     LC JET - SUBS     \"        },         \"label_line_index\": 24,         \"label_string\": \"     BW SWIFT               CLN  16   49   51  28/04  TAGOLOAN         02/05  HAFNIA-BW     LC JET - SUBS     \",         \"line_index\": 24      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     PETROLIMEX 11          CLN  08   40   45  30/04  NHA BE           02/05  PETROLIMEX    LC GO     \"        },         \"header\": {          \"0\": \"     PETROLIMEX 11          CLN  08   40   45  30/04  NHA BE           02/05  PETROLIMEX    LC GO     \"        },         \"label_line_index\": 25,         \"label_string\": \"     PETROLIMEX 11          CLN  08   40   45  30/04  NHA BE           02/05  PETROLIMEX    LC GO     \",         \"line_index\": 25      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     DAI MINH               CLN  04   47   50  02/05  SINGAPORE        02/05  VOSCO         LC ULSD - SUBS     \"        },         \"header\": {          \"0\": \"     DAI MINH               CLN  04   47   50  02/05  SINGAPORE        02/05  VOSCO         LC ULSD - SUBS     \"        },         \"label_line_index\": 26,         \"label_string\": \"     DAI MINH               CLN  04   47   50  02/05  SINGAPORE        02/05  VOSCO         LC ULSD - SUBS     \",         \"line_index\": 26      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     VINALINES GALAXY       CLN  07   50   52  28/04  CHITTAGONG       03/05  ST SHIPPING   LC GO     \"        },         \"header\": {          \"0\": \"     VINALINES GALAXY       CLN  07   50   52  28/04  CHITTAGONG       03/05  ST SHIPPING   LC GO     \"        },         \"label_line_index\": 27,         \"label_string\": \"     VINALINES GALAXY       CLN  07   50   52  28/04  CHITTAGONG       03/05  ST SHIPPING   LC GO     \",         \"line_index\": 27      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     STI MAYFAIR            CLN  14   49   51  28/04  CHITTAGONG       03/05  SCORPIO     \"        },         \"header\": {          \"0\": \"     STI MAYFAIR            CLN  14   49   51  28/04  CHITTAGONG       03/05  SCORPIO     \"        },         \"label_line_index\": 28,         \"label_string\": \"     STI MAYFAIR            CLN  14   49   51  28/04  CHITTAGONG       03/05  SCORPIO     \",         \"line_index\": 28      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     OCEAN VICTORY          CLN  02   37   41  28/04  PARADIP          03/05  OCEANTANKERS     \"        },         \"header\": {          \"0\": \"     OCEAN VICTORY          CLN  02   37   41  28/04  PARADIP          03/05  OCEANTANKERS     \"        },         \"label_line_index\": 29,         \"label_string\": \"     OCEAN VICTORY          CLN  02   37   41  28/04  PARADIP          03/05  OCEANTANKERS     \",         \"line_index\": 29      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     SWARNA KALASH          CLN  09   47   50  28/04  CHENNAI          03/05  SCI INC     \"        },         \"header\": {          \"0\": \"     SWARNA KALASH          CLN  09   47   50  28/04  CHENNAI          03/05  SCI INC     \"        },         \"label_line_index\": 30,         \"label_string\": \"     SWARNA KALASH          CLN  09   47   50  28/04  CHENNAI          03/05  SCI INC     \",         \"line_index\": 30      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     JAG PRANAM             CLN  04   48   54  28/04  HALDIA           03/05  GREATSHIP     LC HSD     \"        },         \"header\": {          \"0\": \"     JAG PRANAM             CLN  04   48   54  28/04  HALDIA           03/05  GREATSHIP     LC HSD     \"        },         \"label_line_index\": 31,         \"label_string\": \"     JAG PRANAM             CLN  04   48   54  28/04  HALDIA           03/05  GREATSHIP     LC HSD     \",         \"line_index\": 31      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     NCC QAMAR              CLN  09   46   51  29/04  SUBIC BAY        03/05  NCC/ UACC     LC JET - SHORT VOY ONLY     \"        },         \"header\": {          \"0\": \"     NCC QAMAR              CLN  09   46   51  29/04  SUBIC BAY        03/05  NCC/ UACC     LC JET - SHORT VOY ONLY     \"        },         \"label_line_index\": 32,         \"label_string\": \"     NCC QAMAR              CLN  09   46   51  29/04  SUBIC BAY        03/05  NCC/ UACC     LC JET - SHORT VOY ONLY     \",         \"line_index\": 32      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     TORM ERIC              CLN  06   49   52  01/05  SRIRACHA         03/05  TORM          LC ULSD - SUBS     \"        },         \"header\": {          \"0\": \"     TORM ERIC              CLN  06   49   52  01/05  SRIRACHA         03/05  TORM          LC ULSD - SUBS     \"        },         \"label_line_index\": 33,         \"label_string\": \"     TORM ERIC              CLN  06   49   52  01/05  SRIRACHA         03/05  TORM          LC ULSD - SUBS     \",         \"line_index\": 33      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     CSC CORAL              CLN  08   49   53  03/05  SINGAPORE        03/05  NANJING     \"        },         \"header\": {          \"0\": \"     CSC CORAL              CLN  08   49   53  03/05  SINGAPORE        03/05  NANJING     \"        },         \"label_line_index\": 34,         \"label_string\": \"     CSC CORAL              CLN  08   49   53  03/05  SINGAPORE        03/05  NANJING     \",         \"line_index\": 34      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     HELLAS REVENGER        CLN  16   49   52  29/04  WYNDHAM          05/05  VITOL         EX SING/OZ     \"        },         \"header\": {          \"0\": \"     HELLAS REVENGER        CLN  16   49   52  29/04  WYNDHAM          05/05  VITOL         EX SING/OZ     \"        },         \"label_line_index\": 35,         \"label_string\": \"     HELLAS REVENGER        CLN  16   49   52  29/04  WYNDHAM          05/05  VITOL         EX SING/OZ     \",         \"line_index\": 35      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     NEVESKA LADY           CLN  05   46   51  30/04  CHITTAGONG       05/05  ARGYLL        LC JET     \"        },         \"header\": {          \"0\": \"     NEVESKA LADY           CLN  05   46   51  30/04  CHITTAGONG       05/05  ARGYLL        LC JET     \"        },         \"label_line_index\": 36,         \"label_string\": \"     NEVESKA LADY           CLN  05   46   51  30/04  CHITTAGONG       05/05  ARGYLL        LC JET     \",         \"line_index\": 36      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     PETROLIMEX 09          CLN  07   40   42  01/05  HON GAI          05/05  PETROLIMEX    LC GO     \"        },         \"header\": {          \"0\": \"     PETROLIMEX 09          CLN  07   40   42  01/05  HON GAI          05/05  PETROLIMEX    LC GO     \"        },         \"label_line_index\": 37,         \"label_string\": \"     PETROLIMEX 09          CLN  07   40   42  01/05  HON GAI          05/05  PETROLIMEX    LC GO     \",         \"line_index\": 37      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     NCC NAJEM              CLN  12   45   50  01/05  PHIL             05/05  NCC/ UACC     \"        },         \"header\": {          \"0\": \"     NCC NAJEM              CLN  12   45   50  01/05  PHIL             05/05  NCC/ UACC     \"        },         \"label_line_index\": 38,         \"label_string\": \"     NCC NAJEM              CLN  12   45   50  01/05  PHIL             05/05  NCC/ UACC     \",         \"line_index\": 38      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     PETROLIMEX 16          CLN  04   46   51  03/05  NHA BE           05/05  PETROLIMEX    LC MOGAS - PREFER TC     \"        },         \"header\": {          \"0\": \"     PETROLIMEX 16          CLN  04   46   51  03/05  NHA BE           05/05  PETROLIMEX    LC MOGAS - PREFER TC     \"        },         \"label_line_index\": 39,         \"label_string\": \"     PETROLIMEX 16          CLN  04   46   51  03/05  NHA BE           05/05  PETROLIMEX    LC MOGAS - PREFER TC     \",         \"line_index\": 39      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     TORM HELVIG            CLN  05   46   53  05/05  SINGAPORE        05/05  TORM          LC JET     \"        },         \"header\": {          \"0\": \"     TORM HELVIG            CLN  05   46   53  05/05  SINGAPORE        05/05  TORM          LC JET     \"        },         \"label_line_index\": 40,         \"label_string\": \"     TORM HELVIG            CLN  05   46   53  05/05  SINGAPORE        05/05  TORM          LC JET     \",         \"line_index\": 40      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     GRAND ACE5             CLN  06   46   52  04/05  NHA BE           06/05  PANOCEAN      LC MOGAS     \"        },         \"header\": {          \"0\": \"     GRAND ACE5             CLN  06   46   52  04/05  NHA BE           06/05  PANOCEAN      LC MOGAS     \"        },         \"label_line_index\": 41,         \"label_string\": \"     GRAND ACE5             CLN  06   46   52  04/05  NHA BE           06/05  PANOCEAN      LC MOGAS     \",         \"line_index\": 41      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     GULF HUWAYLAT          CLN  08   45   51  04/05  POSCO VIETNAM P  06/05  NCC/ UACC     \"        },         \"header\": {          \"0\": \"     GULF HUWAYLAT          CLN  08   45   51  04/05  POSCO VIETNAM P  06/05  NCC/ UACC     \"        },         \"label_line_index\": 42,         \"label_string\": \"     GULF HUWAYLAT          CLN  08   45   51  04/05  POSCO VIETNAM P  06/05  NCC/ UACC     \",         \"line_index\": 42      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     OCEAN MARS             CLN  00   50   60  06/05  SINGAPORE        06/05  OCEANTANKERS  SUBS     \"        },         \"header\": {          \"0\": \"     OCEAN MARS             CLN  00   50   60  06/05  SINGAPORE        06/05  OCEANTANKERS  SUBS     \"        },         \"label_line_index\": 43,         \"label_string\": \"     OCEAN MARS             CLN  00   50   60  06/05  SINGAPORE        06/05  OCEANTANKERS  SUBS     \",         \"line_index\": 43      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     ST. PAULI              CLN  17   49   52  01/05  DARWIN (AUS      07/05  OETKER        EX JAPAN/OZ     \"        },         \"header\": {          \"0\": \"     ST. PAULI              CLN  17   49   52  01/05  DARWIN (AUS      07/05  OETKER        EX JAPAN/OZ     \"        },         \"label_line_index\": 44,         \"label_string\": \"     ST. PAULI              CLN  17   49   52  01/05  DARWIN (AUS      07/05  OETKER        EX JAPAN/OZ     \",         \"line_index\": 44      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     NCC TIHAMA             CLN  06   45   51  07/05  SINGAPORE        07/05  NCC/ UACC     \"        },         \"header\": {          \"0\": \"     NCC TIHAMA             CLN  06   45   51  07/05  SINGAPORE        07/05  NCC/ UACC     \"        },         \"label_line_index\": 45,         \"label_string\": \"     NCC TIHAMA             CLN  06   45   51  07/05  SINGAPORE        07/05  NCC/ UACC     \",         \"line_index\": 45      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     SEA CHAMPION           CLN  03   37   42  06/05  BALONGAN         08/05  PERTAMINA     \"        },         \"header\": {          \"0\": \"     SEA CHAMPION           CLN  03   37   42  06/05  BALONGAN         08/05  PERTAMINA     \"        },         \"label_line_index\": 46,         \"label_string\": \"     SEA CHAMPION           CLN  03   37   42  06/05  BALONGAN         08/05  PERTAMINA     \",         \"line_index\": 46      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     FOREVER HARMONY        CLN  18   49   51  26/04  PORT LINCOLN     08/05  NANJING       EX SING/OZ     \"        },         \"header\": {          \"0\": \"     FOREVER HARMONY        CLN  18   49   51  26/04  PORT LINCOLN     08/05  NANJING       EX SING/OZ     \"        },         \"label_line_index\": 47,         \"label_string\": \"     FOREVER HARMONY        CLN  18   49   51  26/04  PORT LINCOLN     08/05  NANJING       EX SING/OZ     \",         \"line_index\": 47      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     HIGH EFFICIENCY        CLN  09   46   52  03/05  VIZAG            08/05  DAMICO        LC GASOLINE     \"        },         \"header\": {          \"0\": \"     HIGH EFFICIENCY        CLN  09   46   52  03/05  VIZAG            08/05  DAMICO        LC GASOLINE     \"        },         \"label_line_index\": 48,         \"label_string\": \"     HIGH EFFICIENCY        CLN  09   46   52  03/05  VIZAG            08/05  DAMICO        LC GASOLINE     \",         \"line_index\": 48      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     TORM AGNES             CLN  11   49   51  03/05  COLOMBO          08/05  TORM          LC JET+GO     \"        },         \"header\": {          \"0\": \"     TORM AGNES             CLN  11   49   51  03/05  COLOMBO          08/05  TORM          LC JET+GO     \"        },         \"label_line_index\": 49,         \"label_string\": \"     TORM AGNES             CLN  11   49   51  03/05  COLOMBO          08/05  TORM          LC JET+GO     \",         \"line_index\": 49      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     SILVER MONIKA          CLN  14   49  103  08/05  SINGAPORE        08/05  SHELL     \"        },         \"header\": {          \"0\": \"     SILVER MONIKA          CLN  14   49  103  08/05  SINGAPORE        08/05  SHELL     \"        },         \"label_line_index\": 50,         \"label_string\": \"     SILVER MONIKA          CLN  14   49  103  08/05  SINGAPORE        08/05  SHELL     \",         \"line_index\": 50      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     TEATRALNY BRIDGE       CLN  06   46   51  08/05  SINGAPORE        08/05  SCF           IF     \"        },         \"header\": {          \"0\": \"     TEATRALNY BRIDGE       CLN  06   46   51  08/05  SINGAPORE        08/05  SCF           IF     \"        },         \"label_line_index\": 51,         \"label_string\": \"     TEATRALNY BRIDGE       CLN  06   46   51  08/05  SINGAPORE        08/05  SCF           IF     \",         \"line_index\": 51      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     ATLANTIC POLARIS       CLN  09   49   51  04/05  CHITTAGONG       09/05  DIAMONDS      LC GO     \"        },         \"header\": {          \"0\": \"     ATLANTIC POLARIS       CLN  09   49   51  04/05  CHITTAGONG       09/05  DIAMONDS      LC GO     \"        },         \"label_line_index\": 52,         \"label_string\": \"     ATLANTIC POLARIS       CLN  09   49   51  04/05  CHITTAGONG       09/05  DIAMONDS      LC GO     \",         \"line_index\": 52      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     DORIC COURAGE          CLN  19   49  519  05/05  SANDAKAN         09/05  STENABULK     NB     \"        },         \"header\": {          \"0\": \"     DORIC COURAGE          CLN  19   49  519  05/05  SANDAKAN         09/05  STENABULK     NB     \"        },         \"label_line_index\": 53,         \"label_string\": \"     DORIC COURAGE          CLN  19   49  519  05/05  SANDAKAN         09/05  STENABULK     NB     \",         \"line_index\": 53      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     ATLANTIC MIRAGE        CLN  09   51   52  05/05  PHIL             09/05  HMM           LC ULSD     \"        },         \"header\": {          \"0\": \"     ATLANTIC MIRAGE        CLN  09   51   52  05/05  PHIL             09/05  HMM           LC ULSD     \"        },         \"label_line_index\": 54,         \"label_string\": \"     ATLANTIC MIRAGE        CLN  09   51   52  05/05  PHIL             09/05  HMM           LC ULSD     \",         \"line_index\": 54      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     GRAND ACE7             CLN  07   46   52  28/04  MACKAY           10/05  VITOL         EX CHINA/OZ     \"        },         \"header\": {          \"0\": \"     GRAND ACE7             CLN  07   46   52  28/04  MACKAY           10/05  VITOL         EX CHINA/OZ     \"        },         \"label_line_index\": 55,         \"label_string\": \"     GRAND ACE7             CLN  07   46   52  28/04  MACKAY           10/05  VITOL         EX CHINA/OZ     \",         \"line_index\": 55      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     NAVIG8 STRENGTH        CLN  09   50   52  07/05  PHIL             11/05  NAVIG8     \"        },         \"header\": {          \"0\": \"     NAVIG8 STRENGTH        CLN  09   50   52  07/05  PHIL             11/05  NAVIG8     \"        },         \"label_line_index\": 56,         \"label_string\": \"     NAVIG8 STRENGTH        CLN  09   50   52  07/05  PHIL             11/05  NAVIG8     \",         \"line_index\": 56      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     PETROLIMEX 18          CLN  07   40   42  09/05  VIET             11/05  PETROLIMEX    LC MOGAS     \"        },         \"header\": {          \"0\": \"     PETROLIMEX 18          CLN  07   40   42  09/05  VIET             11/05  PETROLIMEX    LC MOGAS     \"        },         \"label_line_index\": 57,         \"label_string\": \"     PETROLIMEX 18          CLN  07   40   42  09/05  VIET             11/05  PETROLIMEX    LC MOGAS     \",         \"line_index\": 57      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     BRITISH CADET          CLN  17   45   52  24/04  TIMARU           12/05  BP            EX JAPAN/NZ OR YOSU 12/5     \"        },         \"header\": {          \"0\": \"     BRITISH CADET          CLN  17   45   52  24/04  TIMARU           12/05  BP            EX JAPAN/NZ OR YOSU 12/5     \"        },         \"label_line_index\": 58,         \"label_string\": \"     BRITISH CADET          CLN  17   45   52  24/04  TIMARU           12/05  BP            EX JAPAN/NZ OR YOSU 12/5     \",         \"line_index\": 58      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     PETROLIMEX 08          CLN  03   37   43  08/05  HON GAI          12/05  PETROLIMEX    LC GO     \"        },         \"header\": {          \"0\": \"     PETROLIMEX 08          CLN  03   37   43  08/05  HON GAI          12/05  PETROLIMEX    LC GO     \"        },         \"label_line_index\": 59,         \"label_string\": \"     PETROLIMEX 08          CLN  03   37   43  08/05  HON GAI          12/05  PETROLIMEX    LC GO     \",         \"line_index\": 59      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     CHALLENGE PACIFIC      CLN  07   47   50  30/04  BRISBANE         13/05  NYK           EX SING/OZ     \"        },         \"header\": {          \"0\": \"     CHALLENGE PACIFIC      CLN  07   47   50  30/04  BRISBANE         13/05  NYK           EX SING/OZ     \"        },         \"label_line_index\": 60,         \"label_string\": \"     CHALLENGE PACIFIC      CLN  07   47   50  30/04  BRISBANE         13/05  NYK           EX SING/OZ     \",         \"line_index\": 60      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     ORIENTAL DIAMOND       CLN  08   50   53  30/04  BRISBANE         13/05  HMM           OZ - LC UMS+ULSD+JET     \"        },         \"header\": {          \"0\": \"     ORIENTAL DIAMOND       CLN  08   50   53  30/04  BRISBANE         13/05  HMM           OZ - LC UMS+ULSD+JET     \"        },         \"label_line_index\": 61,         \"label_string\": \"     ORIENTAL DIAMOND       CLN  08   50   53  30/04  BRISBANE         13/05  HMM           OZ - LC UMS+ULSD+JET     \",         \"line_index\": 61      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     ELANDRA SPRUCE         CLN  16   49   51  03/05  CAIRNS           13/05  VITOL         EX KOREA/OZ     \"        },         \"header\": {          \"0\": \"     ELANDRA SPRUCE         CLN  16   49   51  03/05  CAIRNS           13/05  VITOL         EX KOREA/OZ     \"        },         \"label_line_index\": 62,         \"label_string\": \"     ELANDRA SPRUCE         CLN  16   49   51  03/05  CAIRNS           13/05  VITOL         EX KOREA/OZ     \",         \"line_index\": 62      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     NS SILVER              CLN  05   47   51  08/05  NWAUS            13/05  SCF           EX SING/OZ     \"        },         \"header\": {          \"0\": \"     NS SILVER              CLN  05   47   51  08/05  NWAUS            13/05  SCF           EX SING/OZ     \"        },         \"label_line_index\": 63,         \"label_string\": \"     NS SILVER              CLN  05   47   51  08/05  NWAUS            13/05  SCF           EX SING/OZ     \",         \"line_index\": 63      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     NCC SUDAIR             CLN  07   46   51  13/05  SINGAPORE        13/05  NCC/ UACC     \"        },         \"header\": {          \"0\": \"     NCC SUDAIR             CLN  07   46   51  13/05  SINGAPORE        13/05  NCC/ UACC     \"        },         \"label_line_index\": 64,         \"label_string\": \"     NCC SUDAIR             CLN  07   46   51  13/05  SINGAPORE        13/05  NCC/ UACC     \",         \"line_index\": 64      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     ARCHON                 CLN  16   50   51  10/05  MONGLA           15/05  DIAMONDS     \"        },         \"header\": {          \"0\": \"     ARCHON                 CLN  16   50   51  10/05  MONGLA           15/05  DIAMONDS     \"        },         \"label_line_index\": 65,         \"label_string\": \"     ARCHON                 CLN  16   50   51  10/05  MONGLA           15/05  DIAMONDS     \",         \"line_index\": 65      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     ATLANTICA BAY          CLN  07   46   51  10/05  HALDIA           15/05  CLEARLAKE     LC VEG     \"        },         \"header\": {          \"0\": \"     ATLANTICA BAY          CLN  07   46   51  10/05  HALDIA           15/05  CLEARLAKE     LC VEG     \"        },         \"label_line_index\": 66,         \"label_string\": \"     ATLANTICA BAY          CLN  07   46   51  10/05  HALDIA           15/05  CLEARLAKE     LC VEG     \",         \"line_index\": 66      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     NAVE LUMINOSITY        CLN  14   49   51  03/05  BELL BAY         17/05  VITOL         EX KOREA/OZ     \"        },         \"header\": {          \"0\": \"     NAVE LUMINOSITY        CLN  14   49   51  03/05  BELL BAY         17/05  VITOL         EX KOREA/OZ     \"        },         \"label_line_index\": 67,         \"label_string\": \"     NAVE LUMINOSITY        CLN  14   49   51  03/05  BELL BAY         17/05  VITOL         EX KOREA/OZ     \",         \"line_index\": 67      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     ARDMORE ENGINEER       CLN  14   49   51  04/05  MELBOURNE        17/05  ARDMORESHIP   EX KOREA/OZ - LC ULSD     \"        },         \"header\": {          \"0\": \"     ARDMORE ENGINEER       CLN  14   49   51  04/05  MELBOURNE        17/05  ARDMORESHIP   EX KOREA/OZ - LC ULSD     \"        },         \"label_line_index\": 68,         \"label_string\": \"     ARDMORE ENGINEER       CLN  14   49   51  04/05  MELBOURNE        17/05  ARDMORESHIP   EX KOREA/OZ - LC ULSD     \",         \"line_index\": 68      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     GLADYS W               CLN  13   49   52  12/05  BUNBURY          20/05  STENABULK     LC CSS     \"        },         \"header\": {          \"0\": \"     GLADYS W               CLN  13   49   52  12/05  BUNBURY          20/05  STENABULK     LC CSS     \"        },         \"label_line_index\": 69,         \"label_string\": \"     GLADYS W               CLN  13   49   52  12/05  BUNBURY          20/05  STENABULK     LC CSS     \",         \"line_index\": 69      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     ATLANTIC INFINITY      CLN  17   50   52  11/05  ADELAIDE         23/05  VITOL         EX SING/OZ - LC ULSD     \"        },         \"header\": {          \"0\": \"     ATLANTIC INFINITY      CLN  17   50   52  11/05  ADELAIDE         23/05  VITOL         EX SING/OZ - LC ULSD     \"        },         \"label_line_index\": 70,         \"label_string\": \"     ATLANTIC INFINITY      CLN  17   50   52  11/05  ADELAIDE         23/05  VITOL         EX SING/OZ - LC ULSD     \",         \"line_index\": 70      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     FREJA HAFNIA           CLN  06   53   56  12/05  PORT BONYTHON    24/05  CLEARLAKE     LC ULSD     \"        },         \"header\": {          \"0\": \"     FREJA HAFNIA           CLN  06   53   56  12/05  PORT BONYTHON    24/05  CLEARLAKE     LC ULSD     \"        },         \"label_line_index\": 71,         \"label_string\": \"     FREJA HAFNIA           CLN  06   53   56  12/05  PORT BONYTHON    24/05  CLEARLAKE     LC ULSD     \",         \"line_index\": 71      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     NORDIC PIA             CLN  06   38   37  17/05  KWINANA          25/05  BRYGGEN     \"        },         \"header\": {          \"0\": \"     NORDIC PIA             CLN  06   38   37  17/05  KWINANA          25/05  BRYGGEN     \"        },         \"label_line_index\": 72,         \"label_string\": \"     NORDIC PIA             CLN  06   38   37  17/05  KWINANA          25/05  BRYGGEN     \",         \"line_index\": 72      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     ARIONAS                CLN  06   37   40  28/05  HALDIA           02/06  DIAMONDS      PALMS     \"        },         \"header\": {          \"0\": \"     ARIONAS                CLN  06   37   40  28/05  HALDIA           02/06  DIAMONDS      PALMS     \"        },         \"label_line_index\": 73,         \"label_string\": \"     ARIONAS                CLN  06   37   40  28/05  HALDIA           02/06  DIAMONDS      PALMS     \",         \"line_index\": 73      },       {        \"StructureType\": \"line\",         \"data\": {          \"0\": \"     HERMITAGE BRIDGE       CLN  03   47   49  25/05  SUVA             10/06  SCF           END MAY     \"        },         \"header\": {          \"0\": \"     HERMITAGE BRIDGE       CLN  03   47   49  25/05  SUVA             10/06  SCF           END MAY     \"        },         \"label_line_index\": 74,         \"label_string\": \"     HERMITAGE BRIDGE       CLN  03   47   49  25/05  SUVA             10/06  SCF           END MAY     \",         \"line_index\": 74      }    ]  }}\r\n" + 
	//		"";
JSONObject returnobj=null;
JSONObject obj=null;
JSONObject Tableobj=null;
JSONObject Tableobj_new=null;

JSONArray table_dataobj=null;
JSONArray table_dataobj_new=null;
JSONArray header_dataobj =null;
JSONArray header_dataobj_new =null;

JSONObject eachrowobj=null;
JSONObject eachrowobj_new=null;
JSONObject data=null;
JSONObject header=null;
JSONObject new_data=null;
JSONObject new_header=null;
JSONObject objheader=null;
JSONObject labelsInfo=null;
		////System.out.println("start");
		try {
			 returnobj = new JSONObject();
			 obj = new JSONObject(pythonScriptApiData);
			Iterator mainitr = obj.keys();
			while (mainitr.hasNext()) {
				String TableName = (String) mainitr.next();
				////System.out.println("TableName "+TableName );
				 Tableobj = obj.getJSONObject(TableName);
				 Tableobj_new=new JSONObject();
				 table_dataobj = Tableobj.getJSONArray("table_data");
				 header_dataobj = Tableobj.getJSONArray("header_data");
				 table_dataobj_new=new JSONArray();
				// header_dataobj_new = new JSONArray();
				 
				// one loop to check starting of table by checking vesselname
				int vesselline_line_index = 0;
				int vesseljson_count = 0;
				
				String key = null;
				String value = null;
				String solrres= null;
				
				for (int i = 0; i < table_dataobj.length(); i++) {
					 eachrowobj = table_dataobj.getJSONObject(i);
					 data = eachrowobj.getJSONObject("data");
					 header = eachrowobj.getJSONObject("header");
					Iterator itr = data.keys();
					if (vesselline_line_index == 0) {
						while (itr.hasNext()) {
							if (vesselline_line_index == 0) {
								////System.out.println("vesselline_no blank");
								 key = (String) itr.next();
								 value = data.getString(key);
									String DocumentName = callSolrforDataMatch(value);

//								 solrres = pallavi_SortJSON
//										.executesolrQuery_bywords(value.toUpperCase().replace(" ", "_"));
//								boolean checkjsonString = SaveReportDataClass.isJSONValid(solrres);
//								if (checkjsonString) {
//									// parsejson from solr //get DocumentName
//									String DocumentName = pallavi_SortJSON.parsesolrresp(solrres, "DocumentName");
//									////System.out.println("DocumentName  " + DocumentName);
									if (DocumentName.equalsIgnoreCase("VesselName")) {
										// vesselline_no=eachrowobj.getString("Line_no");
										vesselline_line_index = Integer.parseInt(eachrowobj.getString("line_index"));
									}
								
							} else {
								break;
							}
							////System.out.println("vesselline_line_index " + vesselline_line_index);
						}
						vesseljson_count++;

					} else {
						break;
					}
				}
				//System.out.println("vesselline_line_index " + vesselline_line_index);
                //System.out.println("vesseljson_count "+vesseljson_count);

				 objheader = new JSONObject();
				objheader.put("RepositionRegion", "");
				objheader.put("ReportType", "");
				objheader.put("CargoType", "");
				int previousvessellineindex=vesselline_line_index;	

				if(vesseljson_count==0) {
					// method to get lable string and split into json , find report type 
//					for (int i = 0; i < table_dataobj.length(); i++) {
//						 eachrowobj = table_dataobj.getJSONObject(i);
//
//					labelrecognize
//					}
					findheaderdata(vesselline_line_index, objheader, header_dataobj, -1);
					//call one method 
					if(table_dataobj.length()!=0){
					for (int i = 0; i < table_dataobj.length(); i++) {
						////System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
						 eachrowobj = table_dataobj.getJSONObject(i);
						 eachrowobj_new= new JSONObject();
						//find difference
						vesselline_line_index=Integer.parseInt(eachrowobj.getString("line_index"));
						int difference= vesselline_line_index-previousvessellineindex;
						////System.out.println("vesselline_line_index "+vesselline_line_index);
						////System.out.println("previousvessellineindex "+previousvessellineindex);
						////System.out.println("difference "+difference);
						if(difference<0) {difference=0;}else if(difference>4) {difference=4;}else if(difference==1) {difference=0;}
						findheaderdata(vesselline_line_index, objheader, header_dataobj, difference);
						////System.out.println("objheader "+objheader);
						previousvessellineindex=Integer.parseInt(eachrowobj.getString("line_index"));
						 data = eachrowobj.getJSONObject("data");
						 header = eachrowobj.getJSONObject("header");
						////System.out.println("header " + header);
						 new_data = new JSONObject();
						 new_header = new JSONObject();
						 
					 JSONObject newDataHeaderObj=singleStringParsingmethod(data, new_data, header, new_header, objheader);
						if(newDataHeaderObj.has("data")){data=newDataHeaderObj.getJSONObject("data");}
						if(newDataHeaderObj.has("new_data")){data=newDataHeaderObj.getJSONObject("new_data");}
						if(newDataHeaderObj.has("header")){header=newDataHeaderObj.getJSONObject("header");}
						if(newDataHeaderObj.has("new_header")){new_header=newDataHeaderObj.getJSONObject("new_header");}

						
						eachrowobj_new.put("data",data);
						eachrowobj_new.put("new_data",new_data);
						eachrowobj_new.put("header",header);
						eachrowobj_new.put("objheader",objheader);
						eachrowobj_new.put("new_header_data", new_header);
						
//						table_dataobj.getJSONObject(i).put("data",data);
//						table_dataobj.getJSONObject(i).put("header",header);
//
//						table_dataobj.getJSONObject(i).put("objheader",objheader);
//						table_dataobj.getJSONObject(i).put("new_header_data", header);
						//System.out.println("&&&&&&&&&&&& " + eachrowobj_new);

						//System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
						table_dataobj_new.put(eachrowobj_new);
					
					}
				}
				}
				////System.out.println("vesseljson_count " + vesseljson_count);
				////System.out.println("successfull break");
				LinkedHashMap<String, Integer> lhmap = new LinkedHashMap<String, Integer>();
				
				if(vesseljson_count!=0){
					findheaderdata(vesselline_line_index, objheader, header_dataobj, -1);
					//////System.out.println("RepositionRegion "+RepositionRegion);
					//////System.out.println("ReportType "+ReportType);
					//////System.out.println("CargoType "+CargoType);
					labelsInfo= new JSONObject();
				for (int i =0; i < table_dataobj.length(); i++) {
					////System.out.println("***************************************************************************************************");

					 eachrowobj = table_dataobj.getJSONObject(i);
					 eachrowobj_new= new  JSONObject();
					
					
					//find difference
					vesselline_line_index=Integer.parseInt(eachrowobj.getString("line_index"));
					int difference= vesselline_line_index-previousvessellineindex;
					////System.out.println("vesselline_line_index "+vesselline_line_index);
					////System.out.println("previousvessellineindex "+previousvessellineindex);
					////System.out.println("difference "+difference);
					//////System.out.println("RepositionRegion "+RepositionRegion);
					//////System.out.println("ReportType "+ReportType);
					//////System.out.println("CargoType "+CargoType);
					
					if(difference<0) {difference=0;}else if(difference>4) {difference=4;}
					
					findheaderdata(vesselline_line_index, objheader, header_dataobj, difference);
					//////System.out.println("RepositionRegion_1 "+RepositionRegion);
					//////System.out.println("ReportType_1 "+ReportType);
					////System.out.println("objheader "+objheader);
					previousvessellineindex=Integer.parseInt(eachrowobj.getString("line_index"));
					 data = eachrowobj.getJSONObject("data");
					 ////System.out.println("data "+data);
					 header = eachrowobj.getJSONObject("header");
					////System.out.println("header " + header);
					 new_data = new JSONObject();
					 new_header = new JSONObject();
					if(data.length()<6) {
						JSONObject newDataHeaderObj=singleStringParsingmethod(data,new_data, header, new_header, objheader);
						if(newDataHeaderObj.has("data")){data=newDataHeaderObj.getJSONObject("data");}
						if(newDataHeaderObj.has("new_data")){new_data=newDataHeaderObj.getJSONObject("new_data");}
						if(newDataHeaderObj.has("header")){header=newDataHeaderObj.getJSONObject("header");}
						if(newDataHeaderObj.has("new_header")){new_header=newDataHeaderObj.getJSONObject("new_header");}
						if(newDataHeaderObj.has("labelsInfo")){labelsInfo=newDataHeaderObj.getJSONObject("labelsInfo");}

						
						
						eachrowobj_new.put("data",data);
						eachrowobj_new.put("new_data",new_data);
						eachrowobj_new.put("header",header);
						eachrowobj_new.put("objheader",objheader);
						eachrowobj_new.put("new_header_data", new_header);
						eachrowobj_new.put("labelsInfo", labelsInfo);

//						table_dataobj.getJSONObject(i).put("data",data);
//						table_dataobj.getJSONObject(i).put("header",header);
//
//						table_dataobj.getJSONObject(i).put("objheader",objheader);
//						table_dataobj.getJSONObject(i).put("new_header_data", header);
						//System.out.println("&&&&&&&&&&&& " + eachrowobj_new);
						table_dataobj_new.put(eachrowobj_new);

					}else {
						  header=  replaceLabels( header);
					Iterator itr = header.keys();
					while (itr.hasNext()) {
						String hkey = (String) itr.next();
						String hvalue = header.getString(hkey);
						String dvalue = data.getString(hkey);
						new_data.put(hkey, dvalue);
						new_header.put(hkey, hvalue);
						//System.out.println("hvalue " + hvalue);
						//System.out.println("dvalue" + dvalue);
//pre processing on table headers find id lables are actual lables or not if not make each lable as unknown
						//
						// now check lable by lable
						if (hvalue.contains("unknown")) {
							// 1. call regax script
							// 2.call sorl no as no header
							// 3. call new solr api
							// 4.execute method
							String synonyms = " ";
							String solrmatch = callSolrforDataMatch(dvalue);
							String regexmatch = " ";
							String regexpattern = "";
							////System.out.println("dvalue "+dvalue);
							JSONObject regexmatchresult = callregex(dvalue);
							boolean checkjsonString = SaveReportDataClass.isJSONValid(regexmatchresult.toString());
							if (checkjsonString) {
								if (regexmatchresult.has("Format")) {
									regexpattern = regexmatchresult.getString("Format");
								}
								if (regexmatchresult.has("Regex_Type")) {
									regexmatch = regexmatchresult.getString("Regex_Type");
								}
							}
							
							////System.out.println("hkey  "+hvalue  +"synonyms "+ synonyms +"regexmatch   "+regexmatch +" solrmatch "+ solrmatch );
							String mathodname = pallavi_SortJSON.executesolrQuery_getMethodName(" ".toUpperCase(),
									synonyms.toUpperCase(), regexmatch.toUpperCase(), solrmatch.toUpperCase(),
									"METHODNAME");
							////System.out.println("mathodname unknown " + mathodname);
							allmethodexceution(data, new_data,header, new_header, hkey, hvalue, dvalue, mathodname, synonyms,
									regexmatch, regexpattern, solrmatch, lhmap);
						} else {
							
							String hvalue_update=hvalue;
							 if(hvalue.contains(":")) { hvalue=hvalue.replace(":", "");}
				             if(hvalue.contains("*")) {hvalue=hvalue.replace("*", "");}
				             if(hvalue.contains("\\")) { hvalue=hvalue.replace("\\", "");}
				             //if(hvalue.contains("+")) { dvalue=dvalue.replace("+", "");}
				             if(hvalue.contains("#")) {hvalue=hvalue.replace("#", "");}
				             if(hvalue.contains("?")) {hvalue=hvalue.replace("?", "");}
								////System.out.println("hvalue "+hvalue.replace(" ", "_"));
							String solrKeyList = fetchsolrdata(hvalue.trim().replace(" ", "_"));
							// ////System.out.println("solrKeyList "+solrKeyList);
							if (solrKeyList != null) {
								JSONObject headerNameobj = pallavi_SortJSON.parsesolrresp_updated(solrKeyList,
										"HeaderName", "Extrakey");
								//System.out.println("headerName:: " + headerNameobj);
								if (headerNameobj.has("HeaderName")
										&& !headerNameobj.getString("HeaderName").equalsIgnoreCase(" ")) {
									String headername = headerNameobj.getString("HeaderName");
									if (headerNameobj.has("Extrakey")) {
										//new_header.put(hkey, headername);
										// ************************************************************
										String Extrakey = headerNameobj.getString("Extrakey");
										////System.out.println("Extrakey "+Extrakey);
										// call stanbol to get data
										if (Extrakey.equalsIgnoreCase("RepositionRegion")) {
											JSONObject Stanbolmatchobj = getUrlByStanbol(hvalue);
											// ////System.out.println("Stanbolmatchobj "+Stanbolmatchobj);
											if (Stanbolmatchobj.length() > 0) {
												// method to add data in json in appropriate key

												Iterator stanbolitr = Stanbolmatchobj.keys();
												// ////System.out.println(Stanbolmatchobj.length());
												String index=data.length() + "";

												while (stanbolitr.hasNext()) {
													// ////System.out.println("in stanbol while");
													String stanbolkey = (String) stanbolitr.next();
													String stanbolvalue = Stanbolmatchobj.getString(stanbolkey);
													//System.out.println("stanbolkey "+stanbolkey +" stanbolvalue"+stanbolvalue);
													if (stanbolvalue.equalsIgnoreCase("RepositionRegion") || stanbolvalue.equalsIgnoreCase("Port")) {
														data.put(index, stanbolkey);
														new_data.put(index, stanbolkey);
														new_header.put(index, "RepositionRegion");
														// ////System.out.println("new_header ***^^^^%%%% "+new_header);
													}else {
														//String index=data.length() + "";
														data.put(index, hvalue);
														new_data.put(index, hvalue);
														new_header.put(index, "RepositionRegion");
													}
												}
											}else{
												////System.out.println("in else");
												String index=data.length()+"";
												data.put(index, hvalue);
												new_data.put(index, hvalue);
												new_header.put(index, "RepositionRegion");
											}
										}
										if (Extrakey.equalsIgnoreCase("VesselType")) {
											JSONObject Stanbolmatchobj = getUrlByStanbol(hvalue);
											// ////System.out.println("Stanbolmatchobj "+Stanbolmatchobj);
											if (Stanbolmatchobj.length() > 0) {
												// method to add data in json in appropriate key
												Iterator stanbolitr = Stanbolmatchobj.keys();
												// ////System.out.println(Stanbolmatchobj.length());
												String index=data.length() + "";
												while (stanbolitr.hasNext()) {
													// ////System.out.println("in stanbol while");
													String stanbolkey = (String) stanbolitr.next();
													String stanbolvalue = Stanbolmatchobj.getString(stanbolkey);
													 ////System.out.println("stanbolkey "+stanbolkey +" stanbolvalue"+stanbolvalue);
													if (stanbolvalue.equalsIgnoreCase("VesselType")) {
														//String index=data.length()+"";
														data.put(index, hvalue);
														new_data.put(index, stanbolkey);
														new_header.put(index, stanbolvalue);
														// ////System.out.println("new_header ***^^^^%%%% "+new_header);
													}else{
														////System.out.println("in else");
														//String index=data.length()+"";
														data.put(index, hvalue);
														new_data.put(index, hvalue);
														new_header.put(index, "VesselType");
													}
												}
											}else{
												////System.out.println("in else");
												String index=data.length()+"";
												data.put(index, hvalue);
												new_data.put(index, hvalue);
												new_header.put(index, "VesselType");
											}
										}
										// *********************************************************

									}  // if no Extrakey
												// 1. call regax script
												// 2.call sorl
												// 3. call new solr api
												// 4.execute method
										String synonyms = headername;
										////System.out.println("synonyms "+synonyms);
										String solrmatch = callSolrforDataMatch(dvalue);
										////System.out.println("solrmatch "+solrmatch);
										////System.out.println("dvalue "+dvalue);

										String regexmatch = " ";
										String regexpattern = "";
										JSONObject regexmatchresult = callregex(dvalue);
										boolean checkjsonString = SaveReportDataClass
												.isJSONValid(regexmatchresult.toString());
										if (checkjsonString) {
											if (regexmatchresult.has("Format")) {
												regexpattern = regexmatchresult.getString("Format");
											}
											if (regexmatchresult.has("Regex_Type")) {
												regexmatch = regexmatchresult.getString("Regex_Type");
											}
										}
										//System.out.println("regexpattern "+regexpattern+" regexmatch "+regexmatch);
										String mathodname = pallavi_SortJSON.executesolrQuery_getMethodName(
												hvalue.toUpperCase(), synonyms.toUpperCase(), regexmatch.toUpperCase(),
												solrmatch.toUpperCase(), "METHODNAME");
										//System.out.println("mathodname withsynonym " + mathodname);
									if(mathodname.equalsIgnoreCase("Blank")) {
										String rematch= getSynonymmatch(synonyms, "DataType");
										//System.out.println("rematch 1 "+rematch);
										//System.out.println("solrmatch**  "+solrmatch);
										
										if(solrmatch.equalsIgnoreCase("owners") || solrmatch.equalsIgnoreCase("operators") || solrmatch.equalsIgnoreCase("Charterer")) {
                                           //System.out.println("in 1");
										if(rematch.equalsIgnoreCase("owners") || rematch.equalsIgnoreCase("operators") || rematch.equalsIgnoreCase("Charterer")) {
											//System.out.println("in 2");
											
											solrmatch=rematch;
										}}
										String newdvalue=dvalue;
										//System.out.println(rematch  +" && "+solrmatch);
										if(rematch.equalsIgnoreCase(solrmatch) || rematch.equalsIgnoreCase(regexmatch)) {
											newdvalue=dvalue;
											//System.out.println("newdvalue *"+newdvalue);
										}else {
											//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
											if(!hkey.equalsIgnoreCase("0")  && !rematch.equalsIgnoreCase("DataType")) {
                                         	   String regexpattern_new="";
                                         	   String regexmatch_new=" ";

											String newhkey=Integer.toString(Integer.parseInt(hkey)-1);
											//System.out.println("newhkey  "+newhkey);
											if(header.has(newhkey)) {
												String newsolrmatch = callSolrforDataMatch(data.getString(newhkey));
												//System.out.println("newsolrmatch "+newsolrmatch );
												
												//System.out.println("rematch.equalsIgnoreCase(newsolrmatch "+rematch.equalsIgnoreCase(newsolrmatch ));
												if(rematch.equalsIgnoreCase("owners") || rematch.equalsIgnoreCase("operators") || rematch.equalsIgnoreCase("Charterer")) {
													if(newsolrmatch.equalsIgnoreCase("owners") || newsolrmatch.equalsIgnoreCase("operators") || newsolrmatch.equalsIgnoreCase("Charterer")) {
													newsolrmatch=rematch;
												}}
                                               if(rematch.equalsIgnoreCase(newsolrmatch) ) {
       											newdvalue=data.getString(newhkey);
       											new_data.put(hkey, newdvalue);
       										    solrmatch=newsolrmatch;
    											regexpattern=regexpattern_new;
    											regexmatch=regexmatch_new;

       										    //call one method to shift data  shift_Data(data, hkey)
       										 data= shift_Data( data,  hkey, header.length()) ;
                                                //System.out.println("shifteddata 1 "+data);
                                               }else {
                                            	    regexpattern_new="";
                                            	    regexmatch_new=" ";
                                            	   JSONObject regexmatchresult_new = callregex(dvalue);
           										boolean checkjsonString_new = SaveReportDataClass
           												.isJSONValid(regexmatchresult.toString());
           										if (checkjsonString) {
           											if (regexmatchresult_new.has("Format")) {
           												regexpattern_new = regexmatchresult.getString("Format");
           											}
           											if (regexmatchresult_new.has("Regex_Type")) {
           												regexmatch_new = regexmatchresult.getString("Regex_Type");
           											}
           										}
           										//System.out.println("regexpattern_new "+regexpattern_new+" regexmatch_new "+regexmatch_new);
           										
           									 if(rematch.equals(regexmatch_new) ) {
        											newdvalue=data.getString(newhkey);
        											new_data.put(hkey, newdvalue);
        											regexpattern=regexpattern_new;
        											regexmatch=regexmatch_new;
        											
              										 data= shift_Data( data,  hkey, header.length()) ;
                                         //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        											} else{
        												// else check data at right to match
        								//+_+_+_+_+__+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_	
	                                            	    regexpattern_new="";
	                                            	    regexmatch_new=" ";

        												 newhkey=Integer.toString(Integer.parseInt(hkey)+1);
        												//System.out.println("newhkey ***  "+newhkey);
        												if(header.has(newhkey)) {
        													 newsolrmatch = callSolrforDataMatch(data.getString(newhkey));
     														if(newsolrmatch.equalsIgnoreCase("owners") || newsolrmatch.equalsIgnoreCase("operators") || newsolrmatch.equalsIgnoreCase("Charterer")) {      														newsolrmatch=rematch;
        													if(rematch.equalsIgnoreCase("owners") || rematch.equalsIgnoreCase("operators") || rematch.equalsIgnoreCase("Charterer")) {
        														newsolrmatch=rematch;

        														}}
        													//System.out.println("newsolrmatch "+newsolrmatch );
        													
        													//System.out.println("rematch.equalsIgnoreCase(newsolrmatch "+rematch.equalsIgnoreCase(newsolrmatch ));

        	                                               if(rematch.equalsIgnoreCase(newsolrmatch) ) {
        	       											newdvalue=data.getString(newhkey);
        	       											new_data.put(hkey, newdvalue);
        	       										    solrmatch=newsolrmatch;
    	        											regexpattern=regexpattern_new;
    	        											regexmatch=regexmatch_new;

        	                                               }else {
        	                                            	    regexpattern_new="";
        	                                            	    regexmatch_new=" ";
        	                                            	    regexmatchresult_new = callregex(dvalue);
        	           										 checkjsonString_new = SaveReportDataClass
        	           												.isJSONValid(regexmatchresult.toString());
        	           										if (checkjsonString) {
        	           											if (regexmatchresult_new.has("Format")) {
        	           												regexpattern_new = regexmatchresult.getString("Format");
        	           											}
        	           											if (regexmatchresult_new.has("Regex_Type")) {
        	           												regexmatch_new = regexmatchresult.getString("Regex_Type");
        	           											}
        	           										}
        	           										//System.out.println("regexpattern_new 2 "+regexpattern_new+" regexmatch_new 2"+regexmatch_new);
        	           										
        	           									 if(rematch.equals(regexmatch_new) ) {
        	        											newdvalue=data.getString(newhkey);
        	        											new_data.put(hkey, newdvalue);
        	        											regexpattern=regexpattern_new;
        	        											regexmatch=regexmatch_new;
        	        											
        	           									 }}}
        												
        		        				//+_+_+_+_+__+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_				

        											}
                                               }
											}
										}}
										//System.out.println("***here"+ newdvalue);
										
										
										allmethodexceution(data,new_data, header, new_header, hkey, hvalue, newdvalue, mathodname,
												synonyms, regexmatch, regexpattern, solrmatch, lhmap);
										
										
										//System.out.println("data  "+data);
										//System.out.println("new_data  "+new_data);

									}else{
										allmethodexceution(data, new_data,header, new_header, hkey, hvalue, dvalue, mathodname,
												synonyms, regexmatch, regexpattern, solrmatch, lhmap);
									}
								} else {
									// what if header do not match
									// 1. call regax script
									// 2.call sorl
									// 3. call new solr api
									// 4.execute method

									String synonyms = " ";
									////System.out.println("synonyms "+synonyms);
									String solrmatch = callSolrforDataMatch(dvalue);
									////System.out.println("solrmatch "+solrmatch);
									String regexmatch = " ";
									String regexpattern = "";
									//System.out.println("dvalue in no synonym "+dvalue);
									JSONObject regexmatchresult = callregex(dvalue);
									boolean checkjsonString = SaveReportDataClass
											.isJSONValid(regexmatchresult.toString());
									if (checkjsonString) {
										if (regexmatchresult.has("Format")) {
											regexpattern = regexmatchresult.getString("Format");
										}
										if (regexmatchresult.has("Regex_Type")) {
											regexmatch = regexmatchresult.getString("Regex_Type");
										}
									}
	                                   //System.out.println("regexpattern "+regexpattern+" regexmatch "+regexmatch);

									String mathodname = pallavi_SortJSON.executesolrQuery_getMethodName(
											hvalue.toUpperCase(), synonyms.toUpperCase(), regexmatch.toUpperCase(),
											solrmatch.toUpperCase(), "METHODNAME");
									//System.out.println("mathodname withoutsynonym " + mathodname);
									if(mathodname.equalsIgnoreCase("Blank")) {
										String newdvalue=dvalue;
										if(hvalue.equalsIgnoreCase("open") ) { 
											if( !synonyms.equalsIgnoreCase("Port") && !synonyms.equalsIgnoreCase("Date") && !regexmatch.equalsIgnoreCase("Date")) {
											if(!hkey.equalsIgnoreCase("0")) {
												String newhkey=Integer.toString(Integer.parseInt(hkey)-1);
												//System.out.println("newhkey  "+newhkey);
												if(header.has(newhkey)) {
													String newsolrmatch = callSolrforDataMatch(data.getString(newhkey));
													//System.out.println("newsolrmatch "+newsolrmatch );
	                                               if(newsolrmatch.equals("Port")  || newsolrmatch.equals("Date")) {
	       											newdvalue=data.getString(newhkey);
	       											new_data.put(hkey, newdvalue);
	       										   solrmatch=newsolrmatch;
	       										   //System.out.println("solrmatch "+solrmatch);
	                                               }else {
	                                            	   String regexpattern_new="";
	                                            	   String regexmatch_new=" ";
	                                            	   JSONObject regexmatchresult_new = callregex(data.getString(newhkey));
	           										boolean checkjsonString_new = SaveReportDataClass
	           												.isJSONValid(regexmatchresult.toString());
	           										if (checkjsonString) {
	           											if (regexmatchresult_new.has("Format")) {
	           												regexpattern_new = regexmatchresult.getString("Format");
	           											}
	           											if (regexmatchresult_new.has("Regex_Type")) {
	           												regexmatch_new = regexmatchresult.getString("Regex_Type");
	           											}
	           										}
	           										//System.out.println("regexpattern "+regexpattern+" regexmatch "+regexmatch);
	           										
	           									 if(regexmatch.equalsIgnoreCase("Date") ) {
	        											newdvalue=data.getString(newhkey);
	        											new_data.put(hkey, newdvalue);
	        											regexpattern=regexpattern_new;
	        											regexmatch=regexmatch_new;
	        											}
	                                               }
												}
											}}
										if(hvalue.equalsIgnoreCase("Date")) {
											if(!synonyms.equalsIgnoreCase("Date") && !regexmatch.equalsIgnoreCase("Date")) {
												if(!hkey.equalsIgnoreCase("0")) {
													String newhkey=Integer.toString(Integer.parseInt(hkey)-1);
													//System.out.println("newhkey  "+newhkey);
													if(header.has(newhkey)) {
														String newsolrmatch = callSolrforDataMatch(data.getString(newhkey));
														//System.out.println("newsolrmatch "+newsolrmatch );
		                                               if( newsolrmatch.equals("Date")) {
		       											newdvalue=data.getString(newhkey);
		       											new_data.put(hkey, newdvalue);
		       										     solrmatch=newsolrmatch;
		                                               }else {
		                                            	   String regexpattern_new="";
		                                            	   String regexmatch_new=" ";
		                                            	   JSONObject regexmatchresult_new = callregex(data.getString(newhkey));
		           										boolean checkjsonString_new = SaveReportDataClass
		           												.isJSONValid(regexmatchresult.toString());
		           										if (checkjsonString) {
		           											if (regexmatchresult_new.has("Format")) {
		           												regexpattern_new = regexmatchresult.getString("Format");
		           											}
		           											if (regexmatchresult_new.has("Regex_Type")) {
		           												regexmatch_new = regexmatchresult.getString("Regex_Type");
		           											}
		           										}
		           										//System.out.println("regexpattern "+regexpattern+" regexmatch "+regexmatch);
		           										
		           									 if(regexmatch.equalsIgnoreCase("Date") ) {
		        											newdvalue=data.getString(newhkey);
		        											new_data.put(hkey, newdvalue);
		        											regexpattern=regexpattern_new;
		        											regexmatch=regexmatch_new;
		        											}
		                                               }
													}
												}
											}}}
											//System.out.println("***here"+ newdvalue);
											allmethodexceution(data,new_data, header, new_header, hkey, hvalue, newdvalue, mathodname,
													synonyms, regexmatch, regexpattern, solrmatch, lhmap);
											//System.out.println("data  "+data);
											//System.out.println("new_data  "+new_data);

										
										
											
										
										}else {
									allmethodexceution(data,new_data, header, new_header, hkey, hvalue, dvalue, mathodname,
											synonyms, regexmatch, regexpattern, solrmatch, lhmap);
									}
								}
								////System.out.println(3);
							}
							////System.out.println(2);
                           }
					////System.out.println(1 + " hkey " + hkey +" data " + data);
					}
					
					//////System.out.println(1+"data  "+data.toString());
					eachrowobj_new.put("data",data);
					////System.out.println(2+"header "+header);
					eachrowobj_new.put("new_data",new_data);
					//System.out.println(2+"new_data "+new_data);

					eachrowobj_new.put("header",header);
					////System.out.println(3+"objheader "+objheader);
					eachrowobj_new.put("objheader",objheader);
					////System.out.println(4+"new_header "+new_header);
					eachrowobj_new.put("new_header_data", new_header);
					////System.out.println(5);
					

					//table_dataobj.getJSONObject(i).put("objheader",objheader);
					//table_dataobj.getJSONObject(i).put("new_header_data", new_header);
					//System.out.println("&&&&&&&&&&&& " + eachrowobj_new);
					//System.out.println("***************************************************************************************************");
					table_dataobj_new.put(eachrowobj_new);

					}
				
					
				
				}}
				// call method to get averagerate position from lhmap

				Tableobj_new.put("Rate_Position", FindAverageRatePosition(lhmap));
				
				Tableobj_new.put("table_data", table_dataobj_new);
				Tableobj_new.put("header_data", header_dataobj);
				returnobj.put(TableName, Tableobj_new);
			}

			//System.out.println("returnobj " + returnobj);
			pallavi_WriteFile.writeUsingOutputStream( "SCRIPTJSON ::====== "+pythonScriptApiData.toString() +
					"\n OUTPUTJSON::====== "+returnobj .toString() , 
					filepath);

			return returnobj.toString();
		} catch (Exception e) {
			pallavi_WriteFile.writeUsingOutputStream( "SCRIPTJSON ::====== "+pythonScriptApiData.toString() +
					"\n OUTPUTJSON::====== "+returnobj .toString() +"\n error"+ e.getMessage(), 
					filepath);
			e.printStackTrace();
   //System.out.println("error  "+e.getMessage());
			return returnobj.toString();
		}
		}
	
	public static String FindAverageRatePosition(LinkedHashMap<String, Integer> lhmap) {
		String rateposition = "";
		int maxrate = 0;
		try {
		if (lhmap.size() > 0) {
			for (String key : lhmap.keySet()) {
				int value = lhmap.get(key);
				if (value > maxrate) {
					maxrate = value;
					rateposition = key;
				}
			}
		}}catch(Exception e) {
			e.printStackTrace();
		}
		return rateposition;
	}

	public static String callSolrforDataMatch(String dvalue) {
		String DocumentName = " ";
		String solrres;
		try {
			 if(dvalue.contains(":")) { dvalue=dvalue.replace(":", "");}
             if(dvalue.contains("*")) {dvalue=dvalue.replace("*", "");}
             if(dvalue.contains("\\")) { dvalue=dvalue.replace("\\", "");}
             //if(dvalue.contains("+")) { dvalue=dvalue.replace("+", "");}
             if(dvalue.contains("#")) {dvalue=dvalue.replace("#", "");}
             if(dvalue.contains("?")) {dvalue=dvalue.replace("?", "");}
             if(dvalue.equalsIgnoreCase(" ")) {
            	 solrres= "";
             }else {
     			solrres = pallavi_SortJSON.executesolrQuery_bywords(dvalue.toUpperCase().replace(" ", "_"));
             }
			boolean checkjsonString = SaveReportDataClass.isJSONValid(solrres);
			if (checkjsonString) {
				DocumentName = pallavi_SortJSON.parsesolrresp(solrres, "DocumentName");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			solrres=null;
		}
		return DocumentName;
	}

	public static void allmethodexceution(JSONObject data,JSONObject new_data, JSONObject header, JSONObject new_header, String hkey,
			String hvalue, String dvalue, String mathodname, String synonyms, String regexmatch, String regexpattern,
			String solrmatch, LinkedHashMap<String, Integer> lhmap) {
		//System.out.println("synonyms |"+synonyms+ "| regexmatch |"+regexmatch +"| regexpattern   |"+regexpattern +"| solrmatch  |"+solrmatch+"|");

		//System.out.println("hkey "+hkey+ " hvalue "+hvalue +" dvalue   "+dvalue +" dvalue  "+dvalue);
		//System.out.println("data %%"+data);
		//System.out.println("new_data %%"+new_data);
		//System.out.println("new_header %%"+new_header);

		JSONObject regexmatchresult=null;
		JSONObject regexmatchresult_2=null;
		String index="";
		JSONObject Stanbolmatchobj=null;
		try {
			////System.out.println("dvalue "+dvalue);
			if (mathodname.equalsIgnoreCase("method1")) {
				String[] words = dvalue.split("/");
				int portcount = 0;
				for (int i = 0; i < words.length; i++) {
					String solrres = pallavi_SortJSON
							.executesolrQuery_bywords(words[i].toUpperCase().replace(" ", "_"));
					boolean checkjsonString = SaveReportDataClass.isJSONValid(solrres);
					if (checkjsonString) {
						// parsejson from solr //get DocumentName
						String DocumentName = pallavi_SortJSON.parsesolrresp(solrres, "DocumentName");
						////System.out.println("DocumentName  " + DocumentName);
						if (!DocumentName.equals("Port")) {
							portcount++;
						}
					}
				}
				if (portcount > 0) {
					
					new_data.put(hkey, words[0]);
					new_header.put(hkey, "LoadPort");
					if (words.length > 1) {
						 index=data.length()+"";
						data.put(index, words[0]);
						new_data.put(index, words[0]);
						new_header.put(index, "DiscPort");
					}
				} else {
					new_data.put(hkey, words[0]);
					new_header.put(hkey, "LoadPort");
				}
			} else if (mathodname.equalsIgnoreCase("method2")) {
				new_data.put(hkey, dvalue);
				new_header.put(hkey, "OpenPort");
			} else if (mathodname.equalsIgnoreCase("method3")) {
				// call method to convert date in standard form
				 regexmatchresult = callregex(dvalue);
				boolean checkjsonString = SaveReportDataClass.isJSONValid(regexmatchresult.toString());
				if (checkjsonString) {
					if (regexmatchresult.has("Format")) {
						regexpattern = regexmatchresult.getString("Format");
					}
					if (regexmatchresult.has("Regex_Type")) {
						regexmatch = regexmatchresult.getString("Regex_Type");
					}
				}
				String date = new pallavi_ConvertDatewithPattern().convertDatewithPattern(dvalue, regexpattern);
				new_data.put(hkey, date);
				new_header.put(hkey, "OpenDate");

			} else if (mathodname.equalsIgnoreCase("method4")) {
				String[] words = dvalue.split(" ");
				String word = "";
				for (int i = 0; i < words.length - 1; i++) {
					word = word + " " + words[i];
					////System.out.println( "word "+word);
				}
				new_data.put(hkey, word);
				new_header.put(hkey, "OpenPort");

				if (words.length > 0) {
					String date = words[words.length - 1];
					////System.out.println("date  "+date);
					// call regex method to get date pattern
					// convert date into standard format
					// data.put(data.length()+"", date);
					 regexmatchresult = callregex(date);
					////System.out.println("regexmatchresult "+regexmatchresult);
					boolean checkjsonString = SaveReportDataClass.isJSONValid(regexmatchresult.toString());
					if (checkjsonString) {
						if (regexmatchresult.has("Format")) {
							regexpattern = regexmatchresult.getString("Format");
						}
						if (regexmatchresult.has("Regex_Type")) {
							regexmatch = regexmatchresult.getString("Regex_Type");
						}
					}
					String date_updared = new pallavi_ConvertDatewithPattern().convertDatewithPattern(date,
							regexpattern);
					 index=data.length()+"";
						data.put(index, date_updared);
					new_data.put(index, date_updared);
					new_header.put(index, "OpenDate");
				}

			} else if (mathodname.equalsIgnoreCase("method5")) {
				// call method to get two dated saperated by &
				String Date1 = "";
				String Date2 = "";
				String date_updared = new pallavi_ConvertDatewithPattern().convertDatewithPattern(dvalue, regexpattern);
				if (date_updared.contains("&")) {
					String[] datearr = date_updared.split("&");
					if (datearr.length > 1) {
						Date1 = datearr[0];
						Date2 = datearr[1];
						data.put(hkey, Date1);
						new_header.put(hkey, "LCStart");
						 index=data.length() + "";
						data.put(index, Date2);
						new_header.put(index, "LCEnd");
					}else if(datearr.length ==1) {
						Date1 = datearr[0];
						new_data.put(hkey, Date1);
						new_header.put(hkey, "LCStart");

					}

				}

			} else if (mathodname.equalsIgnoreCase("method6")) {

				String[] words = dvalue.split("/");
				int portcount = 0;
				for (int i = 0; i < words.length; i++) {
					String solrres = pallavi_SortJSON
							.executesolrQuery_bywords(words[i].toUpperCase().replace(" ", "_"));
					boolean checkjsonString = SaveReportDataClass.isJSONValid(solrres);
					if (checkjsonString) {
						// parsejson from solr //get DocumentName
						String DocumentName = pallavi_SortJSON.parsesolrresp(solrres, "DocumentName");
						////System.out.println("DocumentName  " + DocumentName);
						if (DocumentName.equals("Port")) {
							portcount++;
						}
					}
				}
				if (portcount > 0) {
					new_data.put(hkey, words[0]);
					new_header.put(hkey, "Port");
					if (words.length > 1) {
						 index= data.length() + "";
						data.put(index, words[0]);
						new_data.put(index, words[0]);
						new_header.put(index, "Port");
					}
				} else {
					new_data.put(hkey, dvalue);
					new_header.put(hkey, "Port");
				}

			} else if (mathodname.equalsIgnoreCase("method7")) {
				String[] words = dvalue.split(" ");
				String word = "";
				for (int i = 0; i < words.length - 1; i++) {
					word = word + " " + words[i];
				}
				new_data.put(hkey, word);
				new_header.put(hkey, "Port");

				if (words.length > 0) {
					String date = words[words.length - 1];
					// all regex method to get date pattern
					// convert date into standard format
					// data.put(data.length()+"", date);
					// new_header.put(data.length()+"", "Date");
					
					
					 regexmatchresult = callregex(date);
					boolean checkjsonString = SaveReportDataClass.isJSONValid(regexmatchresult.toString());
					if (checkjsonString) {
						if (regexmatchresult.has("Format")) {
							regexpattern = regexmatchresult.getString("Format");
						}
						if (regexmatchresult.has("Regex_Type")) {
							regexmatch = regexmatchresult.getString("Regex_Type");
						}
					}
					String date_updared = new pallavi_ConvertDatewithPattern().convertDatewithPattern(dvalue,
							regexpattern);
					 index=data.length() + "";
					data.put(index, date_updared);
					new_data.put(index, date_updared);
					new_header.put(index, "Date");

				}

			} else if (mathodname.equalsIgnoreCase("method8")) {
				// call method to get two dated saperated by &
				String Date1 = "";
				String Date2 = "";
				String date_updared = new pallavi_ConvertDatewithPattern().convertDatewithPattern(dvalue, regexpattern);
				if (date_updared.contains("&")) {
					String[] datearr = date_updared.split("&");
					if (datearr.length > 1) {
						Date1 = datearr[0];
						Date2 = datearr[1];
						new_data.put(hkey, Date1);
						new_header.put(hkey, "Date");
						 index=data.length() + "";
							data.put(index, Date2);
						new_data.put(index, Date2);
						new_header.put(index, "Date");
					}else if(datearr.length ==1) {
						Date1 = datearr[0];
						new_data.put(hkey, Date1);
						new_header.put(hkey, "Date");
					}
				}
			} else if (mathodname.equalsIgnoreCase("method9")) {
				if (lhmap.containsKey(hkey)) {
					int count = (int) lhmap.get(hkey);
					lhmap.put(hkey, count + 1);
				} else {
					lhmap.put(hkey, 1);
				}
				new_data.put(hkey, dvalue);
				new_header.put(hkey, "Rate");

			} else if (mathodname.equalsIgnoreCase("Blank")) {
				//System.out.println("in blank method");
				 regexmatchresult = callregex(dvalue.toUpperCase());
			//System.out.println("regexmatchresult "+regexmatchresult);
				boolean checkjsonString = SaveReportDataClass.isJSONValid(regexmatchresult.toString());
				////System.out.println("checkjsonString "+checkjsonString);
				if (checkjsonString) {
					////System.out.println("in checkjsonString ");
					if (regexmatchresult.has("Format")) {
						regexpattern = regexmatchresult.getString("Format");
					}
					if (regexmatchresult.has("Regex_Type")) {
						regexmatch = regexmatchresult.getString("Regex_Type");
					}
				}
				//System.out.println("regexpattern "+regexpattern  +" regexmatch "+regexmatch);
				String date="";
				if(synonyms.equalsIgnoreCase("Date") || solrmatch.equalsIgnoreCase("Date") || regexmatch.equalsIgnoreCase("date") || regexmatch.equalsIgnoreCase("date/date")) {
				if(!regexpattern.equals("")) {
					date = new pallavi_ConvertDatewithPattern().convertDatewithPattern(dvalue, regexpattern);
				}else {
					date=new pallavi_ConvertDate().convertDateFormat( dvalue);
				}
				}
				//System.out.println("date"+date);
				if (!synonyms.equalsIgnoreCase(" ")) {
					//System.out.println("!synonyms.equalsIgnoreCase(\" \")"+!synonyms.equalsIgnoreCase(" "));
					if(regexmatch.equalsIgnoreCase("date") || regexmatch.equalsIgnoreCase("date/date")) {
					new_data.put(hkey, date);
					}
					new_header.put(hkey, synonyms);
				} else if (!solrmatch.equalsIgnoreCase(" ")) {
					////System.out.println("!solrmatch.equalsIgnoreCase(\" \") "+!solrmatch.equalsIgnoreCase(" "));
					if(regexmatch.equalsIgnoreCase("date")) {
					new_data.put(hkey, date);
					}else if(solrmatch.equalsIgnoreCase(date)){
						String date1 = new pallavi_ConvertDatewithPattern().convertDatewithPattern(dvalue, regexpattern);

						new_data.put(hkey, date1);
					}else {
						new_data.put(hkey, dvalue);

					}
					new_header.put(hkey, solrmatch);
				}else if((!regexmatch.equalsIgnoreCase(" ")) && (regexmatch.equalsIgnoreCase("Rate") || regexmatch.equalsIgnoreCase("Date") )){
					// do nothing
					new_header.put(hkey, regexmatch);

				}else if (regexmatch.equalsIgnoreCase("Word/Date")){
					String[] words = dvalue.split(" ");
					String word = "";
					for (int i = 0; i < words.length - 1; i++) {
						word = word + " " + words[i];
						////System.out.println( "word "+word);
					}
					new_data.put(hkey, word);
					new_header.put(hkey, "Port");

					if (words.length > 0) {
						 date = words[words.length - 1];
						////System.out.println("date  "+date);
						// call regex method to get date pattern
						// convert date into standard format
						// data.put(data.length()+"", date);
						 regexmatchresult = callregex(date.trim());
						////System.out.println("regexmatchresult "+regexmatchresult);
						boolean checkjsonStringw_d = SaveReportDataClass.isJSONValid(regexmatchresult.toString());
						if (checkjsonStringw_d) {
							if (regexmatchresult.has("Format")) {
								regexpattern = regexmatchresult.getString("Format");
							}
							if (regexmatchresult.has("Regex_Type")) {
								regexmatch = regexmatchresult.getString("Regex_Type");
							}
						}
						////System.out.println("date_w_d "+date+" regexpattern "+regexpattern+" "+regexmatch);
						String date_updared = new pallavi_ConvertDatewithPattern().convertDatewithPattern(date,
								regexpattern);
						 index=data.length()+"";
						data.put(index, date_updared);
						new_data.put(index, date_updared);
						new_header.put(index, "Date");
					}
				
			    }else if(regexmatch.equalsIgnoreCase("Word/Word")) {
			    	String[] words = dvalue.split("/");
					int portcount = 0;
					for (int i = 0; i < words.length; i++) {
						////System.out.println("words[i]  "+words[i]);
						String solrres = pallavi_SortJSON
								.executesolrQuery_bywords(words[i].toUpperCase().replace(" ", "_"));
						 checkjsonString = SaveReportDataClass.isJSONValid(solrres);
						if (checkjsonString) {
							// parsejson from solr //get DocumentName
							String DocumentName = pallavi_SortJSON.parsesolrresp(solrres, "DocumentName");
							////System.out.println("DocumentName  " + DocumentName);
							if (DocumentName.equals("Port")) {
								portcount++;
							}else if(DocumentName.equals("RepositionRegion")) {
								portcount++;
							}
							
						}
					}
					////System.out.println("portcount  "+portcount);
					if (portcount > 0) {
						new_data.put(hkey, words[0]);
						new_header.put(hkey, "Port");
						if (words.length > 1) {
							 index= data.length() + "";
							data.put(index, words[1]);
							new_data.put(index, words[1]);
							new_header.put(index, "Port");
						}
					} else {
						new_data.put(hkey, dvalue);
						new_header.put(hkey, "Port");
					}
			    	
			    } if(hvalue.equalsIgnoreCase("open") || hvalue.equalsIgnoreCase("EXPORT/DATE") || 
			    		hvalue.equalsIgnoreCase("EXPORT/PORT")|| hvalue.equalsIgnoreCase("OPEN_PORT")){
					//send data to stanbol
					dvalue=dvalue.toUpperCase();
					  ////System.out.println("dvalue* "+dvalue);
						  Stanbolmatchobj= getUrlByStanbol(dvalue);
						 ////System.out.println("Stanbolmatchobj "+Stanbolmatchobj);
			      if(Stanbolmatchobj.length()>0 ) {
			    	  // method to add data in json in appropriate key 
			    	  Iterator stanbolitr=Stanbolmatchobj.keys();
			    	  ////System.out.println(Stanbolmatchobj.length());
			    	  String ports="";
			    	  while(stanbolitr.hasNext()) {
			    		  String stabbolkey=(String) stanbolitr.next();
			    		  String Stranbolvalue= Stanbolmatchobj.getString(stabbolkey);
			    		  if(Stranbolvalue.equalsIgnoreCase("port")) {
			    			  ports=ports+""+stabbolkey;
			    		  }else {
			    			   index=data.length()+"";
					    	data.put(index, stabbolkey);
			    		  new_data.put(index, stabbolkey);
			    		  new_header.put(index, Stranbolvalue);
			    		  }
			  			//////System.out.println("stabbolkey  **  "+stabbolkey +" Stranbolvalue "+Stranbolvalue);
			    		  ////System.out.println("dvalue ** "+dvalue);
			    		  ////System.out.println("ports "+ports);
			    		if(dvalue.contains(stabbolkey.toUpperCase())) {
			    			dvalue=dvalue.replace(stabbolkey.toUpperCase(), "");
			    		}
			    	  }
			    	   index=data.length()+"";
			    	  data.put(index, ports);
		    		  new_data.put(index, ports);
		    		  new_header.put(index, "OpenPort");
			    	  }
			      ////System.out.println("dvalue % "+dvalue);
			      // send dvalue to regex to check if it is data or not
					 regexmatchresult_2 = callregex(dvalue.toUpperCase().trim());
					////System.out.println("regexmatchresult "+regexmatchresult);
					boolean checkjsonString_2 = SaveReportDataClass.isJSONValid(regexmatchresult.toString());
					////System.out.println("checkjsonString "+checkjsonString);
					if (checkjsonString_2) {
						if (regexmatchresult.has("Format")) {
							regexpattern = regexmatchresult.getString("Format");
						}
						if (regexmatchresult.has("Regex_Type")) {
							regexmatch = regexmatchresult.getString("Regex_Type");
						}
					}
					////System.out.println(regexpattern  +" "+regexmatch);
					String date_2="";
					if( regexmatch.equalsIgnoreCase("date")) {
					if(!regexpattern.equals("")) {
						date = new pallavi_ConvertDatewithPattern().convertDatewithPattern(dvalue, regexpattern);
						 index=data.length()+"";
						data.put(index, date);
						new_data.put(index, date);
			    		  new_header.put(index, "OpenDate");
					}}else {	
						if(dvalue.contains(" ")) {
						dvalue= dvalue.replace(" ", "");
						}
						if(!dvalue.equals("")){
						new_data.put(hkey, dvalue);
			    		  new_header.put(hkey, hvalue);
					}}
				}else {
					//do nothing
				}

			}
			////System.out.println("end ofblank method");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {index=null;
		
		}
	}

	public static String fetchsolrdata(String q) {

		// String f1 = "*,score";
		StringBuffer response1 = null;
		// ////System.out.println("q: "+q);
		try {
			// q = q.replace(" ", "%20");
			q = URLEncoder.encode(q, "UTF-8");

			String url = "";

			// url = "http://34.73.112.165:8983/solr/TemplateCore/select?q=" + q
			// + "&fl=" + f1 + "&df=" + df;

			// url = "http://34.73.112.165:8983/solr/Header_Synomes/select?q=" +
			// q + "&fl=" + f1;
			// url =
			// "http://34.73.112.165:8983/solr/Header_Synomes/select?q=Data:" +
			// q + "&fl=" + "HeaderName";
			url = "http://34.73.112.165:8983/solr/Header_Synomes/select?q=Data_str:" + q
					+ "&fl=score,HeaderName,Extrakey";
			// url =
			// "http://34.73.112.165:8983/solr/Header_Synomes/select?q=Data:("+q+")&fl=HeaderName";

			// url = url.replace(" ", "%20");

			// ////System.out.println("url " + url);
			URL url1 = new URL(url);
			HttpURLConnection con = (HttpURLConnection) url1.openConnection();
			// int responseCode = con.getResponseCode();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			response1 = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response1.append(inputLine);
			}
			// ////System.out.println(response1);
			in.close();

		} catch (Exception e) {
			// e.printStackTrace();
			// ////System.out.println(e.getMessage());
			return "";
		}
		return response1.toString();

	}

	public static JSONObject getUrlByStanbol(String adata) {
		JSONObject urlnadvalue = new JSONObject();
		JSONObject obj=null;
		JSONArray arrobj=null;
		try {
			adata = adata.toLowerCase();
			////System.out.println("adata " + adata);
			if (!adata.isEmpty()) {
				String stanboljson = stanbolBreakResponse(adata);
				// ////System.out.println("stanboljson "+stanboljson);
				 arrobj = new JSONArray(stanboljson);
				// ////System.out.println(arrobj.length());

				for (int i = 0; i < arrobj.length(); i++) {
					 obj = arrobj.getJSONObject(i);
					String entity_ref_url = null;
					String entity_vlaue = null;
					;
					String entity_type = null;

					if (obj.has("http://fise.iks-project.eu/ontology/entity-reference")) {
						JSONArray entityref = obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-reference");
						entity_ref_url = entityref.getJSONObject(0).getString("@id");

						// ////System.out.println(entity_ref_url);
					}
					if (obj.has("http://fise.iks-project.eu/ontology/entity-label")) {
						JSONArray entityrlabel = obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-label");
						entity_vlaue = entityrlabel.getJSONObject(0).getString("@value");
						// ////System.out.println(entity_vlaue);
					}
					if (obj.has("http://fise.iks-project.eu/ontology/entity-type")) {
						JSONArray entityrtype = obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-type");
						entity_type = entityrtype.getJSONObject(0).getString("@id");
						// ////System.out.println(entity_type);
						if (entity_type.contains("#")) {
							entity_type = entity_type.substring(entity_type.lastIndexOf("#") + 1);
						}
					}
					if ((entity_vlaue != null)) {
						urlnadvalue.put(entity_vlaue, entity_type);
						// subfinaljson.put(entity_type, entity_vlaue);
						// if (adata.contains(entity_vlaue.toLowerCase())) {
						// ////System.out.println("contains");
						// adata = adata.replace(entity_vlaue.toLowerCase(), "");
						//
						// }
					}

				}
			}
//			if (!adata.equals("")) {
//				urlnadvalue.put(adata, "unknown");
//
//			}
		} catch (JSONException e) {
			e.printStackTrace();
			try {
				return urlnadvalue.put("error", e.getMessage());
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return urlnadvalue;

	}

	public static String stanbolBreakResponse(String plain_text) {
		String res = null;
		try {
			BufferedReader reader = null;

			int BUFFER_SIZE = 4096;
			InputStream isr = new ByteArrayInputStream(plain_text.toString().getBytes("UTF-8"));

			//URL url = new URL("http://35.227.82.195:8080/enhancer/chain/scorpiosvchain2");
			URL url = new URL("http://35.227.82.195:8080/enhancer/chain/scorpiosvchain2");

			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

			httpConn.setUseCaches(false);
			httpConn.setDoOutput(true);
			// httpConn.setRequestProperty("Content-Type", "application/json");
			// httpConn.setRequestProperty("Content-Type", "text/plain");
			// httpConn.setRequestProperty("Accept","application/rdf+xml");
			// httpConn.setRequestProperty("Content-Type","application/pdf");
			// httpConn.setRequestMethod("POST");

			httpConn.setRequestProperty("Accept", "application/json");
			httpConn.setRequestProperty("Content-Type", "application/pdf");
			// httpConn.setRequestProperty("Content-Type", "application/json");
			// httpConn.setRequestProperty("Content-Type",
			// "application/x-www-form-urlencoded");

			OutputStream outputStream = httpConn.getOutputStream();
			// outputStream.write("file=".getBytes());

			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = isr.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			// ////System.out.println("outputStream"+outputStream.toString());
			outputStream.flush();
			outputStream.close();
			isr.close();
			int responseCode = httpConn.getResponseCode();
			// ////System.out.println("responseCode " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) {

				reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
				String response = reader.readLine();

				String line1;
				StringBuffer sb = new StringBuffer();
				sb.append("[{");
				while ((line1 = reader.readLine()) != null) {
					sb.append(line1);
				}
				// ////System.out.println(sb.toString());
				res = sb.toString();
			}
			// ////System.out.println(res);

		} catch (Exception e) {
			////System.out.println(e.getMessage());
		}
		return res;
	}

	public static JSONObject callregex(String dvalue) throws UnsupportedEncodingException {
		int responseCode = 0;
		String urlParameters = "";
		StringBuffer response = new StringBuffer();
		JSONObject rtnobj = new JSONObject();
		if(dvalue.contains("00:00:00")){
			dvalue.replace("00:00:00", "");
			dvalue=dvalue.trim();
		}
		dvalue=dvalue.toUpperCase();

		JSONArray arr=null;
		JSONObject obj=null;
		try {
			
			//System.out.println("dvalue@  "+dvalue);
			// String url1 =
			// "http://35.186.170.65:8983/solr/Vessel/update/json/docs?commit=true";
			// String url1 =
			// "http://35.186.170.65:8983/solr/HSN_Code/update/json/docs?commit=true";
			// String url1 =
			// "http://34.73.112.165:8983/solr/PO_FlatteningTemplate/update/json/docs?commit=true";
			String url1 = "http://dev.bizlem.io:5032/date";

			URL url = new URL(url1);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			// out.println("urlParameters:: " + obj.toString());
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			// urlParameters = urlParameters.replace(" ", "%20");
			wr.writeBytes(dvalue);
			wr.flush();
			wr.close();
			responseCode = con.getResponseCode();
			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			// out.println(response.toString());
		//System.out.println(" response "+response.toString());
			if (!response.toString().equalsIgnoreCase("")) {
				 arr = new JSONArray(response.toString());
				////System.out.println(arr);
				int count = 0;
				int jsonno = 0;
				for (int i = 0; i < arr.length(); i++) {
					 obj = arr.getJSONObject(i);
					String length = "";
					if (obj.has("length")) {
						length = obj.getString("length");
					}
					if (Integer.parseInt(length) > count) {
						count = Integer.parseInt(length);
						jsonno = i;
					}

				}
				if (arr.length() > jsonno) {
					 obj = arr.getJSONObject(jsonno);
					if (obj.has("Format")) {
						rtnobj.put("Format", obj.getString("Format"));
					}
					if (obj.has("Regex_Type")) {
						rtnobj.put("Regex_Type", obj.getString("Regex_Type"));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// out.println(e.getMessage());
		}
		return rtnobj;
	}
	
	public static void findheaderdata(int vesselline_line_index_currentline, JSONObject objheader,
			JSONArray header_dataobj , int difference ) {
	
	//int difference=0;
		
//		String RepositionRegion;
//		String ReportType;
//		String CargoType;
		JSONObject sub_header_dataobj=null;
		JSONArray arraydata=null;
		JSONObject subdata=null;
	try {
		for (int i=0;i<header_dataobj.length(); i++){
			 sub_header_dataobj = header_dataobj.getJSONObject(i);
			
			int line_index=Integer.parseInt(sub_header_dataobj.getString("line_index"));
			//int vesselline_line_index_int= Integer.parseInt(vesselline_line_index_currentline);
			int vesselline_line_index_int= vesselline_line_index_currentline;
			if(difference==-1) {
int oneless =vesselline_line_index_int-1;
int twoless =vesselline_line_index_int-2;
int threeless =vesselline_line_index_int-3;

if((line_index< oneless) || (line_index< twoless) || (line_index < threeless)) {
	
	 arraydata= sub_header_dataobj.getJSONArray("data");
	for(int j=0; j<arraydata.length(); j++) {
		 subdata= arraydata.getJSONObject(j);
		String url =subdata.getString("url");
		String word= subdata.getString("word");
		String key ="";
		if(url.contains("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018")) {
			key=url.substring(url.indexOf("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018")+"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018".length()+1);
			////System.out.println("key*******   "+key);

				//////System.out.println("word "+word);
			        if(key.equalsIgnoreCase("ReportType")) {
						if(!word.equalsIgnoreCase("")) {
							word=word.toUpperCase().replaceAll(" ", "_");
					//////System.out.println("word *   "+word);
			        	String resp=getreporttypebysolr(word);
						boolean checkjsonString = SaveReportDataClass.isJSONValid(resp);
						//////System.out.println("resp  "+resp);
						if (checkjsonString) {
							// parsejson from solr //get DocumentName
							String HeaderName = pallavi_SortJSON.parsesolrresp(resp, "HeaderName");
							//////System.out.println("HeaderName "+HeaderName);
							if(!HeaderName.equalsIgnoreCase("")) {
								//ReportType=HeaderName;
								objheader.put("ReportType", HeaderName);
								//////System.out.println("objheader  "+objheader);
					        	}}
						}}
					if(key.equalsIgnoreCase("RepositionRegion")) {
						if(!word.equalsIgnoreCase("")) {
							//RepositionRegion=word;
							objheader.put("RepositionRegion", word);

						}}
					if(key.equalsIgnoreCase("CargoType") ) {
						if(!word.equalsIgnoreCase("")) {
						//CargoType=word;
							objheader.put("CargoType", word);
					}}
			
			}		
		}
		}
	
		}else if( difference!=0 && difference!=1 ) {//<difference
			//if(difference>4) {difference=4;}
//////System.out.println("difference* :: i :: "+ i + " :: "+difference);
			for(int k=difference-1;k>0; k--) {
				//////System.out.println("k* "+k);
				int lineless=vesselline_line_index_int-k;
				//////System.out.println("vesselline_line_index_int*  "+vesselline_line_index_int);

				//////System.out.println("lineless*  "+lineless);
				if((line_index==lineless)) {
//////System.out.println("inside");
				 arraydata= sub_header_dataobj.getJSONArray("data");
				for(int j=0; j<arraydata.length(); j++) {
					 subdata= arraydata.getJSONObject(j);
					String url =subdata.getString("url");
					String word= subdata.getString("word");
					String key ="";
					
					if(url.contains("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018")) {
						key=url.substring(url.indexOf("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018")+"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018".length()+1);
						////System.out.println("key*******   "+key);
					    if(key.equalsIgnoreCase("ReportType")) {
							if(!word.equalsIgnoreCase("")) {
								word=word.toUpperCase().replaceAll(" ", "_");
						//////System.out.println("word *   "+word);
				        	String resp=getreporttypebysolr(word);
							boolean checkjsonString = SaveReportDataClass.isJSONValid(resp);
							////System.out.println("resp  "+resp);
							if (checkjsonString) {
								// parsejson from solr //get DocumentName
								String HeaderName = pallavi_SortJSON.parsesolrresp(resp, "HeaderName");
								//////System.out.println("HeaderName "+HeaderName);
								if(!HeaderName.equalsIgnoreCase("")) {
									//ReportType=HeaderName;
									objheader.put("ReportType", HeaderName);
						        	}}
							}}
						        if(key.equalsIgnoreCase("RepositionRegion")) {
						        	if(!word.equalsIgnoreCase("")) {
						        	// RepositionRegion=word;
										objheader.put("RepositionRegion", word);
						        	}}
								if(key.equalsIgnoreCase("CargoType") ) {
									if(!word.equalsIgnoreCase("")) {
									//CargoType=word;
									objheader.put("CargoType", word);
								}}
						}		
					}
				}
		}
	}
		}
		
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	
	
	public static String getreporttypebysolr(String passvalue){
		StringBuffer response1=null;
		try {

		passvalue=passvalue.toUpperCase();
		passvalue=passvalue.replaceAll(" ", "_");
		//////System.out.println(passvalue);

		passvalue = URLEncoder.encode(passvalue, "UTF-8");

		String url = "";
		url = "http://34.73.112.165:8983/solr/ReportType_Synomes/select?q=Data_str:" + passvalue + "&fl=score,HeaderName";

		URL url1 = new URL(url);
		////System.out.println("url1 "+url1);
		HttpURLConnection con = (HttpURLConnection) url1.openConnection();

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		response1 = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
		response1.append(inputLine);
		}
		//////System.out.println(response1);
		in.close();

		} catch (Exception e) {
e.printStackTrace();
		}
		return response1.toString();
		}
	
	public static String getSynonymmatch(String synonym,String DataType){
		StringBuffer response1=null;
		JSONObject Subsorrespobj=null;
		JSONArray docs=null;
		 JSONObject subdocs=null;
		try {
			////System.out.println("in getSynonymmatch");
		//passvalue=passvalue.toUpperCase();
		//passvalue=passvalue.replaceAll(" ", "_");
		//////System.out.println(passvalue);
			synonym = URLEncoder.encode(synonym, "UTF-8");
		String url = "";
		url="http://34.73.112.165:8983/solr/HeaderAndDataValidation/select?fl=score,DataType&q=Headersynonyms:"+synonym;
		//url = "http://34.73.112.165:8983/solr/ReportType_Synomes/select?q=Data_str:" + passvalue + "&fl=score,HeaderName";
		////System.out.println("url " +url);
		URL url1 = new URL(url);
		HttpURLConnection con = (HttpURLConnection) url1.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		response1 = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
		response1.append(inputLine);
		}
		//////System.out.println(response1);
		in.close();
		 JSONObject sorrespobj = new JSONObject(response1.toString());
      if(sorrespobj.has("response") && sorrespobj.getJSONObject("response").has("maxScore") && sorrespobj.getJSONObject("response").has("docs")) {
    // ////System.out.println("in parse json ********");
       Subsorrespobj=sorrespobj.getJSONObject("response");
       docs=Subsorrespobj.getJSONArray("docs");
      double maxscore=Subsorrespobj.getDouble("maxScore");
    //  ////System.out.println("maxscore "+maxscore);
    for(int i=0;i<docs.length();i++) {
      subdocs=docs.getJSONObject(i);
     double score=subdocs.getDouble("score");
     if(score==maxscore) {
     	//////System.out.println("maxscore "+maxscore+" score   "+score);
   	   DataType=subdocs.getJSONArray(DataType).getString(0);
     }}}
      ////System.out.println("DataType "+DataType);
      ////System.out.println("end of getSynonymmatch");
		return DataType;
		} catch (Exception e) {
return DataType;
		}}
	
	 public static JSONObject singleStringParsingmethod(JSONObject data, JSONObject new_data, JSONObject header, JSONObject new_header, JSONObject objheader){
			String alldata="";
			JSONObject returnobj=null;
			JSONObject stanboljson=null;
			JSONArray stanbolarr=null;
			JSONObject dateratejson=null;
			JSONArray dateratearr=null;
			JSONObject solrjson=null;
			JSONArray solrarr=null;
			JSONObject obj=null;
			String alldata_return="";
			JSONObject labelsInfo=null;
			TreeMap<Integer, JSONObject> indexwisemap=null;  
			try {
				returnobj= new JSONObject();
		if(data.length()<6) {
			Iterator ditr= data.keys();  while (ditr.hasNext()) {alldata=alldata+" "+data.getString((String) ditr.next());}
			alldata=alldata.toUpperCase();
			labelsInfo=  labelrecognize(alldata);
			if(labelsInfo!=null && labelsInfo.length()!=0) {
				returnobj.put("labelsInfo", labelsInfo);
			}
			stanboljson=getUrlByStanbol_withIndex(alldata,alldata);
			 if(stanboljson!=null) {
			if(stanboljson.has("stanbolarr")) {
				stanbolarr=stanboljson.getJSONArray("stanbolarr");
			}
			if(stanboljson.has("alldata_return")) {
				alldata_return=stanboljson.getString("alldata_return");
			}}
			dateratejson=callregex_forTotalmatch(alldata, alldata_return);
			 if(dateratejson!=null) {
			if(dateratejson.has("datearr")) {
				dateratearr=dateratejson.getJSONArray("datearr");
			}
			if(dateratejson.has("alldata_return")) {
				alldata_return=dateratejson.getString("alldata_return");
			}
			 }
			 solrjson =callToMatchRemainingString( alldata, alldata_return);
			 if(solrjson!=null) {
			 if(solrjson.has("datearr")) {
					solrarr=solrjson.getJSONArray("solrarr");
				}
				if(solrjson.has("alldata_return")) {
					alldata_return=solrjson.getString("alldata_return");
				}
			 }
		////System.out.println("alldata "+alldata);
		////System.out.println("alldata_return  "+alldata_return);
		////System.out.println("stanbolarr  "+stanbolarr);
		////System.out.println("dateratearr "+dateratearr);
		////System.out.println("solrarr "+solrarr);

		//call method to send data one by one in stanbol and try to match
		
		
       // now create hashtree and create sequance according to index
		indexwisemap= new TreeMap<Integer, JSONObject>();
		if(stanbolarr!=null)
		for(int i=0;i<stanbolarr.length();i++) {
			 obj =stanbolarr.getJSONObject(i);
			int Index=obj.getInt("Index");
			int Index2=obj.getInt("Index2");
			int Index3=obj.getInt("Index3");
			if(Index!=-1) {indexwisemap.put(Index, obj);}
			if(Index2!=-1) {indexwisemap.put(Index2, obj);}
			if(Index3!=-1) {indexwisemap.put(Index3, obj);}
		}}
		if(dateratearr!=null) {
		for(int i=0;i<dateratearr.length();i++) {
			 obj =dateratearr.getJSONObject(i);
			int Index=obj.getInt("Index");
			int Index2=obj.getInt("Index2");
			int Index3=obj.getInt("Index3");
			if(Index!=-1) {indexwisemap.put(Index, obj);}
			if(Index2!=-1) {indexwisemap.put(Index2, obj);}
			if(Index3!=-1) {indexwisemap.put(Index3, obj);}
		}}
			if(solrarr!=null) {
		for(int i=0;i<solrarr.length();i++) {
			 obj =solrarr.getJSONObject(i);
			int Index=obj.getInt("Index");
			int Index2=obj.getInt("Index2");
			int Index3=obj.getInt("Index3");

			if(Index!=-1) {indexwisemap.put(Index, obj);}
			if(Index2!=-1) {indexwisemap.put(Index2, obj);}
			if(Index3!=-1) {indexwisemap.put(Index3, obj);}
		}}
		
		
		////System.out.println("indexwisemap "+indexwisemap);
		data=new JSONObject();
		header=new JSONObject();
		int count=0;
		int previousend_index=0;
        for (Map.Entry< Integer,JSONObject> entry : indexwisemap.entrySet()) {
        	Integer index=entry.getKey();
    		////System.out.println("previousend_index "+previousend_index);

        	////System.out.println("index "+index);
            JSONObject valuejson=entry.getValue();
            ////System.out.println("valuejson "+valuejson);
        	String key=valuejson.getString("key");
        	String value= valuejson.getString("value");
        	int valuelength=valuejson.getInt("valuelength");
        	if(key.equalsIgnoreCase("Date/Date")) {
        		////System.out.println("index>previousend_index "+ (index>previousend_index));
        		if(index>previousend_index) {
        		if(value.contains("&")) {
        			String [] dates=value.split("&");
        			for(int d=0; d<dates.length; d++) {
        				String Date=dates[d];
                    data.put(count+"", Date);
                    header.put(count+"", "Date");
                    count++;
        			}
        		}else {  
        			data.put(count+"", value);
                    header.put(count+"", "Date");
                    count++;
                  }
        		}}else if(key.equalsIgnoreCase("Rate")){
            		if(index>previousend_index) {
                        data.put(count+"", valuejson.getString("value"));
                        header.put(count+"", key);
                        count++;
            		}
        		}else if(key.equalsIgnoreCase("Date")){
            		if(index>previousend_index) {
                        data.put(count+"", valuejson.getString("value"));
                        header.put(count+"", key);
                        count++;
            		}
        		}else{
            data.put(count+"", valuejson.getString("value"));
            if(key.equalsIgnoreCase("RepositionRegion")) {
                header.put(count+"", "Port");
            }else {
            header.put(count+"", key);
            }
            count++;}
        	previousend_index=index+valuelength;
           } 
        String index=data.length()+"";
        data.put(index, alldata_return);
        header.put(index, "unknown_"+index);

////System.out.println("data "+data);
////System.out.println("header "+header);
returnobj.put("data", data);
returnobj.put("new_data", data);
returnobj.put("header", header);
returnobj.put("new_header", header);// as new_header is blank


		
		 return returnobj;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return returnobj;
			}
	 }

	 public static JSONObject getUrlByStanbol_withIndex(String alldata, String alldata_return) {
         JSONArray arr= null;
         JSONObject rtnobj=null;
         JSONObject urlnadvalue=null;
         JSONObject obj=null;
         JSONArray  arrobj=null;
	try {
		arr = new JSONArray();
		rtnobj=new JSONObject();
		if(!alldata.isEmpty()) {
			alldata=alldata.toUpperCase();
    			String stanboljson =stanbolBreakResponse(alldata);
    			//////System.out.println("stanboljson "+stanboljson);
    			  arrobj= new JSONArray(stanboljson);
    			//////System.out.println(arrobj.length());
    			
    			for(int i=0; i<arrobj.length(); i++) {
    				//////System.out.println("**** "+i);
    				 obj= arrobj.getJSONObject(i);
    				String entity_ref_url=null;
    				String entity_vlaue=null;;
    				String entity_type=null;

    				if(obj.has("http://fise.iks-project.eu/ontology/entity-reference")) {
    					JSONArray  entityref=obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-reference");
    					entity_ref_url=entityref.getJSONObject(0).getString("@id");
    					
    					//////System.out.println("1 "+entity_ref_url);
    				}
    				if(obj.has("http://fise.iks-project.eu/ontology/entity-label")) {
    					JSONArray  entityrlabel=obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-label");
    					entity_vlaue=entityrlabel.getJSONObject(0).getString("@value");
    					//////System.out.println("2 "+entity_vlaue);
    				}
    				if(obj.has("http://fise.iks-project.eu/ontology/entity-type")) {
    					JSONArray  entityrtype=obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-type");
    					entity_type=entityrtype.getJSONObject(0).getString("@id");
    					if(entity_type.contains("#")) {
    						entity_type=entity_type.substring(entity_type.lastIndexOf("#")+1);
    					}
    					//////System.out.println(" "+entity_type);

    				}
    				 urlnadvalue= new JSONObject();
    				if((entity_vlaue!=null) ) {
        				urlnadvalue.put("key", entity_type);
        				urlnadvalue.put("value", entity_vlaue);
    				//urlnadvalue.put(entity_vlaue, entity_type);
    				int index=ordinalIndexOf( alldata,  entity_vlaue.toUpperCase(), 0); 
    				int index2= ordinalIndexOf( alldata,  entity_vlaue.toUpperCase(), 1); 
    				int index3= ordinalIndexOf( alldata,  entity_vlaue.toUpperCase(), 2);
    				if(index==-1 && index2==-1 && index3==-1 ) {
    					if(entity_vlaue.contains("_")) {
    						entity_vlaue=entity_vlaue.replace("_", " ");
        				 index=ordinalIndexOf( alldata,  entity_vlaue.toUpperCase(), 0); 
        				 index2= ordinalIndexOf( alldata,  entity_vlaue.toUpperCase(), 1); 
        				 index3= ordinalIndexOf( alldata,  entity_vlaue.toUpperCase(), 2);
    					}
    				}
                    int valuelength=entity_vlaue.length();
    				
    				urlnadvalue.put("Index", index);
    				urlnadvalue.put("Index2", index2);
    				urlnadvalue.put("Index3", index3);
    				urlnadvalue.put("valuelength", valuelength);


    				if(alldata_return.contains(entity_vlaue.toUpperCase())) {
    					alldata_return=alldata_return.replace(entity_vlaue.toUpperCase(), " ");
    				}
    				}
    				if(urlnadvalue.length()>0) {
            			arr.put(urlnadvalue);
    				}
    				rtnobj.put("alldata_return", alldata_return);
    				rtnobj.put("stanbolarr", arr);

    			}
		}		
} catch (JSONException e) {
	e.printStackTrace();
	try {
		rtnobj.put("error", e.getMessage());
		return rtnobj;
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

}
return rtnobj;


}
	 public static int ordinalIndexOf(String str, String substr, int n) {
		    int pos = -1;
		    do {
		        pos = str.indexOf(substr, pos + 1);
		    } while (n-- > 0 && pos != -1);
		    return pos;
		}



	 public static JSONObject callregex_forTotalmatch(String alldata, String alldata_return) {
			int responseCode = 0;
			JSONArray datearr=null;
			JSONArray ratearr=null;
			JSONObject rtnobj=null;

			String urlParameters = "";
			StringBuffer response = new StringBuffer();
			JSONObject subobj = null;
			JSONArray arr =null;
			JSONObject obj=null;
			try {
				rtnobj=new JSONObject();
				datearr= new JSONArray();
				ratearr= new JSONArray();
				alldata=alldata.toUpperCase();
				alldata_return =alldata_return.toUpperCase();
				String url1 = "http://dev.bizlem.io:5030/date";
				URL url = new URL(url1);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("POST");
				con.setRequestProperty("Content-Type", "application/json");
				// out.println("urlParameters:: " + obj.toString());
				con.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				// urlParameters = urlParameters.replace(" ", "%20");
				wr.writeBytes(alldata);
				wr.flush();
				wr.close();
				responseCode = con.getResponseCode();
				BufferedReader in = new BufferedReader(
						new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {response.append(inputLine);}
				in.close();
				// out.println(response.toString());
				////System.out.println(" response "+response.toString());
				if (!response.toString().equalsIgnoreCase("")) {
				arr	= new JSONArray(response.toString());
					//////System.out.println(arr);
					int count = 0;
					int jsonno = 0;
					JSONArray valuearr=null;
					for (int i = 0; i < arr.length(); i++) {
						 obj = arr.getJSONObject(i);
						subobj= new JSONObject();
						String Regex_Type=obj.getString("Regex_Type");

						String maxvalue="";
						if (obj.has("value")) {
							if(obj.get("value") instanceof JSONArray) {
							valuearr = obj.getJSONArray("value");
							
						for(int j=0;j <valuearr.length(); j++) {
							String val=valuearr.getString(j);
							if(maxvalue.length()<val.length()) {
								maxvalue=val;
							}
						}
						}else if(obj.get("value") instanceof String){
							maxvalue= obj.getString("value");
							
						}
						//////System.out.println("maxvalue "+maxvalue);
						
	    				int index=ordinalIndexOf( alldata,  maxvalue.toUpperCase(), 0); 
	    				int index2= ordinalIndexOf( alldata,  maxvalue.toUpperCase(), 1);
	    				int index3= ordinalIndexOf( alldata,  maxvalue.toUpperCase(), 2);
	    				int valuelength=maxvalue.length();

	    				subobj.put("Index", index);
	    				subobj.put("Index2", index2);
	    				subobj.put("Index3", index3);
	    				subobj.put("valuelength", valuelength);


						if(Regex_Type.equalsIgnoreCase("Date") || Regex_Type.equalsIgnoreCase("Date/Date")) {
						String regexpattern=obj.getString("Format");
						////System.out.println("regexpattern "+regexpattern);
						String formateddate = new pallavi_ConvertDatewithPattern().convertDatewithPattern(maxvalue, regexpattern);
						subobj.put("key", Regex_Type);
						subobj.put("value", formateddate);
						if(alldata_return.contains(maxvalue)) {
							alldata_return=alldata_return.replace(maxvalue, " ");
						}
						}else if(Regex_Type.equalsIgnoreCase("Rate")) {
							//subobj.put(maxvalue, Regex_Type);
							subobj.put("key", Regex_Type);
							subobj.put("value", maxvalue);
							if(alldata_return.contains(maxvalue)) {
								alldata_return=alldata_return.replace(maxvalue, " ");
							}
					     }	
                        datearr.put(subobj);
						}
					}
				}
				rtnobj.put("datearr", datearr);
				rtnobj.put("alldata_return", alldata_return);

				//////System.out.println("alldata "+alldata );
				//////System.out.println("alldata_return "+alldata_return);
                // ////System.out.println("datearr  "+datearr);
			} catch (Exception e) {
				e.printStackTrace();
				// out.println(e.getMessage());
			}finally {
				
			}
			return rtnobj;
		}

	 public static JSONObject callToMatchRemainingString(String alldata, String alldata_return) {
		 JSONObject solrobj=null;
		 JSONArray solrarr = null;
		 JSONObject subobj= new JSONObject();
	try {
		alldata_return=alldata_return.toUpperCase();
		solrobj=new JSONObject();
		solrarr= new JSONArray();
		if(!alldata_return.equals("")) {
		String[] words= alldata_return.split(" ");
		for(int i=0; i<words.length; i++) {
			////System.out.println(" $$$$$$ "+words[i]);
			if(!words[i].equals(" ") && !words[i].equals("") ) {
		  String DocumentName=callSolrforDataMatch( words[i]) ;
         if(!DocumentName.equals(" ")) {
        	subobj.put(words[i], DocumentName);
			int index=ordinalIndexOf( alldata,  words[i].toUpperCase(), 0); 
			int index2= ordinalIndexOf( alldata,  words[i].toUpperCase(), 1); 
			int index3= ordinalIndexOf( alldata,  words[i].toUpperCase(), 2); 
			int valuelength=words[i].length();
			subobj.put("Index", index);
			subobj.put("Index2", index2);
			subobj.put("Index3", index3);
			subobj.put("valuelength", valuelength);

			

			if(alldata_return.contains(words[i].toUpperCase())) {
				alldata_return=alldata_return.replace(words[i].toUpperCase(), " ");
			}}
			if(subobj.length()>0) {
				solrarr.put(subobj);
			}
           }}
		}
		if(solrarr.length()>0) {
			solrobj.put("solrarr", solrarr);
		}
	}catch(Exception e) {
		return null;
	}finally {
		subobj=null;
	}
		 
		 return solrobj;	 
	 }
public static JSONObject labelrecognize(String data) {
	String type="";
	String RepositionRegion="";
	String VesselType="";
	JSONObject labelsobj =null;
	JSONObject returnjson=null;
	
	try {
		returnjson= new JSONObject();
		labelsobj=new JSONObject();
		int headercount = 0;
		int keycount=0;
		if (data.length() > 0) {
			String[] arr = data.split(" ");
			for (int i = 0; i < arr.length; i++) {
				String key = arr[i];
				////System.out.println("key  " + key);
				if(!key.equalsIgnoreCase(" ")) {
					String solrKeyList = fetchsolrdata(key.trim().replace(" ", "_"));
					// ////System.out.println("solrKeyList "+solrKeyList);
					if (solrKeyList != null) {
						JSONObject headerNameobj = pallavi_SortJSON.parsesolrresp_updated(solrKeyList,
								"HeaderName", "Extrakey");
						////System.out.println("headerName:: " + headerNameobj);
						if (headerNameobj.has("HeaderName")
								&& !headerNameobj.getString("HeaderName").equalsIgnoreCase(" ")) {
							String headername = headerNameobj.getString("HeaderName");
							if (headerNameobj.has("Extrakey")) {
								String Extrakey = headerNameobj.getString("Extrakey");
								if (Extrakey.equalsIgnoreCase("RepositionRegion")) {
									RepositionRegion=key;
								}
								if (Extrakey.equalsIgnoreCase("VesselType")) {
									VesselType=key;
								}
							}else {
								labelsobj.put(keycount+"", headername);
								headercount++;
								keycount++;
							}
							}else {
								labelsobj.put(keycount+"", key);
								keycount++;
							}
			}	
				}}
		if (headercount >= 4) {
			type="TableLables";
           ////System.out.print(type);
		}else {
			type="TableData";
               ////System.out.print(type);
		}}
		
		if(type.equals("TableLables")) {
			returnjson.put("lables", labelsobj);
			if(!RepositionRegion.equalsIgnoreCase("")) {
				returnjson.put("RepositionRegion", RepositionRegion);
			}
			if(!VesselType.equalsIgnoreCase("")) {
				returnjson.put("VesselType", VesselType);
			}
		}
		
	} catch (Exception e) {
		////System.out.println(e.getMessage());
	}
	
	return returnjson;
}

public static String getlabelsByStanbol(String adata) {
		JSONObject urlnadvalue = new JSONObject();
		JSONObject obj=null;
		JSONArray arrobj=null;
		String rowtype="";
		try {
			adata = adata.toLowerCase();
			////System.out.println("adata " + adata);
			if (!adata.isEmpty()) {
				String stanboljson = stanbolBreakResponse(adata);
				// ////System.out.println("stanboljson "+stanboljson);
				 arrobj = new JSONArray(stanboljson);
				// ////System.out.println(arrobj.length());
				for (int i = 0; i < arrobj.length(); i++) {
					 obj = arrobj.getJSONObject(i);
					String entity_ref_url = null;
					String entity_vlaue = null;
					String entity_site="";
					String entity_type = null;

//					if (obj.has("http://fise.iks-project.eu/ontology/entity-reference")) {
//						JSONArray entityref = obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-reference");
//						entity_ref_url = entityref.getJSONObject(0).getString("@id");
//
//						// ////System.out.println(entity_ref_url);
//					}
					if (obj.has("http://fise.iks-project.eu/ontology/entity-label")) {
						JSONArray entityrlabel = obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-label");
						entity_vlaue = entityrlabel.getJSONObject(0).getString("@value");
					}
					if (obj.has("http://stanbol.apache.org/ontology/entityhub/entityhub#site")) {
						JSONArray entityrlabel = obj.getJSONArray("http://stanbol.apache.org/ontology/entityhub/entityhub#site");
						entity_site = entityrlabel.getJSONObject(0).getString("@value");
						////System.out.println("entity_site "+entity_site);
						if(entity_site.equalsIgnoreCase("scorpioheaders")) {
						//	//System.out.println("entity_vlaue "+entity_vlaue+" entity_site "+entity_site);
							urlnadvalue.put(entity_vlaue, entity_site);
						}
					}
					
//					if (obj.has("http://fise.iks-project.eu/ontology/entity-type")) {
//						JSONArray entityrtype = obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-type");
//						for(int j=0; j<entityrtype.length();j++) {
//						entity_type = entityrtype.getJSONObject(0).getString("@id");
//						//System.out.println(entity_type);
//						if (entity_type.contains("#")) {
//							entity_type = entity_type.substring(entity_type.lastIndexOf("#") + 1);
//                //			if(entity_type.equalsIgnoreCase("")) {}
//                //	     }
//							////System.out.println(entity_type);
//						}
//					}
//					if ((entity_vlaue != null)) {
//						urlnadvalue.put(entity_vlaue, entity_type);
//						// subfinaljson.put(entity_type, entity_vlaue);
//						// if (adata.contains(entity_vlaue.toLowerCase())) {
//						// ////System.out.println("contains");
//						// adata = adata.replace(entity_vlaue.toLowerCase(), "");
//						//
//						// }
//					}
//
//				}
					////System.out.println("+++++++++++++++++++++++==");
			}
			if (urlnadvalue.length()>6) {
				rowtype="Tablelabels";
			}else {
				rowtype="Tabledata";

			}
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				//return urlnadvalue.put("error", e.getMessage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return rowtype;
	
}

public static JSONObject shift_Data(JSONObject data, String hkey,int headerlength ) {
JSONObject obj =null;
String shifted_value=null;


try {
	int hkeyint = Integer.parseInt(hkey);
	obj= new JSONObject();
Iterator itr= data.keys();
while(itr.hasNext()) {
	String key = (String) itr.next();
	String value = data.getString(key);
	
	int keyint = Integer.parseInt(key);
	//System.out.println("key "+key +" value "+value +" keyint "+keyint +" hkeyint "+hkeyint);
	if(keyint<=headerlength) {

	if(keyint<hkeyint) {
		obj.put(key, value);
	}else  {
		shifted_value= data.getString(keyint-1+"");
		obj.put(key, shifted_value);

	}}else {
		obj.put(key, value);
	}
}
//System.out.println("obj shifting "+obj);
	return obj;
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		//System.out.println("error in shifting  ****** "+e.getMessage());
		return data;
	}
	
}

public static JSONObject  replaceLabels(JSONObject header) {
	String alldata="";
	String rowtype="";
	JSONObject obj =null;
	try {
		obj= new JSONObject();
		//System.out.println("header  "+header);
	
	Iterator itr = header.keys();
	while(itr.hasNext()) {
		String key = (String)itr.next();
		String value = header.getString(key);
		if(value.equalsIgnoreCase(" ")) {value=value.replace(" ", "");}
		alldata=alldata+" "+value.trim();
	}
	////System.out.println("alldata "+alldata);
	rowtype=getlabelsByStanbol(alldata);
	////System.out.println("rowtype  "+rowtype);
	if(rowtype.equalsIgnoreCase("Tabledata")) {
		Iterator itr2 = header.keys();
		while(itr2.hasNext()) {
			String key = (String)itr2.next();
			obj.put(key, "unknown_"+key);
	}
	}else if (rowtype.equalsIgnoreCase("Tablelabels")){
		obj=header;
	}
	
	////System.out.println("obj$ "+	 obj);

	return obj;

	}catch(Exception e) {
		e.printStackTrace();
		return header;

	}

}
}


