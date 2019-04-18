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
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.jcr.Session;

import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import com.reportinformationsystem.SaveReportDataClass;

public class pallavi_UpdatedScript_copy_17_04 {
	/**
	 * @param args
	 * @throws JSONException 
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws JSONException   {
	//	String a = "{  \"table_0\": {    \"header_data\": [      {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CLEAN\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Eastport\"          }        ],         \"line_index\": 0,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#ObjectProperty\",             \"word\": \"size\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#Class\",             \"word\": \"Size\"          }        ],         \"line_index\": 1,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Best\"          }        ],         \"line_index\": 2,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Date\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"date\"          }        ],         \"line_index\": 3,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CLEAN\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Eastport\"          }        ],         \"line_index\": 4,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Jnpt\"          }        ],         \"line_index\": 8,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"jet\"          }        ],         \"line_index\": 10,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"bp\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Vadinar\"          }        ],         \"line_index\": 11,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Paradip\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          }        ],         \"line_index\": 12,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Sitra\"          }        ],         \"line_index\": 13,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Salalah\"          }        ],         \"line_index\": 14,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\",             \"word\": \"fixtures\"          }        ],         \"line_index\": 15,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"STI GRACE\"          }        ],         \"line_index\": 19,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"FRONT TIGER\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          }        ],         \"line_index\": 20,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"PLOUTOS\"          }        ],         \"line_index\": 21,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"shell\"          }        ],         \"line_index\": 22,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"PACIFIC MARTINA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"shell\"          }        ],         \"line_index\": 23,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Ec India\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          }        ],         \"line_index\": 24,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"BW CLYDE\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          }        ],         \"line_index\": 25,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"NAVE ATROPOS\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          }        ],         \"line_index\": 26,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"A.R.A.\"          }        ],         \"line_index\": 27,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"INDIA\"          }        ],         \"line_index\": 30,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"KITION M\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Sikka\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion\",             \"word\": \"SIKKA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"cssa\"          }        ],         \"line_index\": 32,         \"number_of_words\": 6      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"JAG PRANAM\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Paradip\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          }        ],         \"line_index\": 33,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"TORM CARINA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Kandla\"          }        ],         \"line_index\": 36,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"ARDMORE SEAHAWK\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Haldia\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          }        ],         \"line_index\": 37,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ulsd\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Jinzhou\"          }        ],         \"line_index\": 40,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Zhoushan\"          }        ],         \"line_index\": 41,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"trafigura\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Yokkaichi\"          }        ],         \"line_index\": 42,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"petrolimex\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Vietnam\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion\",             \"word\": \"S KOREA\"          }        ],         \"line_index\": 43,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Dalian\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          }        ],         \"line_index\": 44,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          }        ],         \"line_index\": 47,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Balongan\"          }        ],         \"line_index\": 48,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\",             \"word\": \"spot\"          }        ],         \"line_index\": 49,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"VINALINES GLORY\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Balongan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"ums\"          }        ],         \"line_index\": 53,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Philippines\"          }        ],         \"line_index\": 54,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"China\"          }        ],         \"line_index\": 57,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Russia\"          }        ],         \"line_index\": 60,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"bp\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"SKS DELTA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          }        ],         \"line_index\": 62,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Japan\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CPP\"          }        ],         \"line_index\": 63,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Australia-Nz\"          }        ],         \"line_index\": 64,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"OCEAN COSMOS\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Jakarta\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          }        ],         \"line_index\": 69,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"ATLANTIC INFINITY\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"subs\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Esperance\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          }        ],         \"line_index\": 70,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\",             \"word\": \"fixtures\"          }        ],         \"line_index\": 71,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          }        ],         \"line_index\": 73,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\",             \"word\": \"fixtures\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          }        ],         \"line_index\": 76,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"blt\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"CHRYSANTHEMUM\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"lyras\"          }        ],         \"line_index\": 78,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"JING YU ZUO\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          }        ],         \"line_index\": 79,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"vitol\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"SPOTTAIL\"          }        ],         \"line_index\": 80,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"cbm\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"damico\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"HIGH PERFORMANCE\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Ii\"          }        ],         \"line_index\": 81,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"cbm\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"MISS MARIAROSARIA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          }        ],         \"line_index\": 82,         \"number_of_words\": 3      },       {        \"data\": [          {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"LC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",             \"word\": \"montanari\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"VALLE AZZURRA\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Ii\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          }        ],         \"line_index\": 83,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"HIGH SUN\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"DWT\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Ii\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",             \"word\": \"damico\"          }        ],         \"line_index\": 84,         \"number_of_words\": 5      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"fixed\"          },           {            \"url\": \"http://www.w3.org/2002/07/owl#DatatypeProperty\",             \"word\": \"DWT\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",             \"word\": \"koch\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",             \"word\": \"JAG PUNIT\"          }        ],         \"line_index\": 85,         \"number_of_words\": 4      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Leo\"          }        ],         \"line_index\": 88,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Eastport\"          }        ],         \"line_index\": 89,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Eastport\"          }        ],         \"line_index\": 96,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Or\"          }        ],         \"line_index\": 97,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",             \"word\": \"hold\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Or\"          }        ],         \"line_index\": 98,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Us\"          }        ],         \"line_index\": 99,         \"number_of_words\": 1      }    ],     \"table_data\": [      {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"EASTPORT\",           \"1\": \"CLEAN\",           \"2\": \"MARKET_REPORT_DATED_08TH_APR_2019\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\"        },         \"line_index\": 0      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"THIS\",           \"1\": \"MESSAGE\",           \"2\": \"WAS\",           \"3\": \"PREPARED\",           \"4\": \"IN\",           \"5\": \"COURIER\",           \"6\": \"NEW\",           \"7\": \"FONT\",           \"8\": \"SIZE\",           \"9\": \"10.\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\",           \"8\": \"unknown_8\",           \"9\": \"unknown_9\"        },         \"line_index\": 1      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"PLEASE\",           \"1\": \"ARRANGE\",           \"2\": \"ACCORDINGLY\",           \"3\": \"FOR\",           \"4\": \"BEST\",           \"5\": \"VIEW.\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\"        },         \"line_index\": 2      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"EASTPORT\",           \"1\": \"CLEAN\",           \"2\": \"MARKET\",           \"3\": \"REPORT\",           \"4\": \"DATED\",           \"5\": \"08TH\",           \"6\": \"APR\",           \"7\": \"2019\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\"        },         \"line_index\": 4      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"IOC\",           \"1\": \"38\",           \"2\": \"CPP\",           \"3\": \"JNPT\",           \"4\": \"KANDLA\",           \"5\": \"14-15/04\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\"        },         \"line_index\": 8      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"GPC\",           \"1\": \"35\",           \"2\": \"JET\",           \"3\": \"N.MANG/OPTS\",           \"4\": \"16-18/04\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\"        },         \"line_index\": 10      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"BP\",           \"1\": \"35\",           \"2\": \"GO\",           \"3\": \"VADINAR\",           \"4\": \"OPTS\",           \"5\": \"11-15/04\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\"        },         \"line_index\": 11      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"IOC\",           \"1\": \"16\",           \"2\": \"CPP\",           \"3\": \"PARADIP\",           \"4\": \"HALDIA\",           \"5\": \"13-14/04\",           \"6\": \"ON\",           \"7\": \"SUBS\",           \"8\": \"BELOW\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\",           \"8\": \"unknown_8\"        },         \"line_index\": 12      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"MSC\",           \"1\": \"310KB\",           \"2\": \"F76\",           \"3\": \"SITRA\",           \"4\": \"J.ALI-FUJ\",           \"5\": \"22-23/04\",           \"6\": \"CLOSING\",           \"7\": \"10/04,\",           \"8\": \"1100H\",           \"9\": \"EST\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\",           \"8\": \"unknown_8\",           \"9\": \"unknown_9\"        },         \"line_index\": 13      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"STI GRACE\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"JAPAN\",           \"4\": \"22/04\",           \"5\": \"W97.5\",           \"6\": \"ATC=FLD\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\"        },         \"line_index\": 19      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"FRONT TIGER\",           \"1\": \"75\",           \"2\": \"NAP\",           \"3\": \"JAPAN\",           \"4\": \"22/04\",           \"5\": \"W97.5\",           \"6\": \"SUBS\",           \"7\": \"RPLC\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\"        },         \"line_index\": 20      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"PLOUTOS\",           \"1\": \"60\",           \"2\": \"UMS\",           \"3\": \"X-AG\",           \"4\": \"15/04\",           \"5\": \"COA\",           \"6\": \"SUBS\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\"        },         \"line_index\": 21      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"LIAN\",           \"1\": \"GUI\",           \"2\": \"HU\",           \"3\": \"55\",           \"4\": \"NAP\",           \"5\": \"JAPAN\",           \"6\": \"END/04\",           \"7\": \"O/P\",           \"8\": \"SHELL\",           \"9\": \"SUBS\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\",           \"8\": \"unknown_8\",           \"9\": \"unknown_9\"        },         \"line_index\": 22      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"PACIFIC MARTINA\",           \"1\": \"55\",           \"2\": \"NAP\",           \"3\": \"JAPAN\",           \"4\": \"15/04\",           \"5\": \"O/P\",           \"6\": \"SHELL\",           \"7\": \"SUBS\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\"        },         \"line_index\": 23      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"NORDIC\",           \"1\": \"TRISTAN\",           \"2\": \"55\",           \"3\": \"NAP\",           \"4\": \"KUWAIT/EC.INDIA\",           \"5\": \"20/04\",           \"6\": \"W113\",           \"7\": \"SUBS\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\"        },         \"line_index\": 24      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"BW CLYDE\",           \"1\": \"55\",           \"2\": \"NAP\",           \"3\": \"JAPAN\",           \"4\": \"OPTS\",           \"5\": \"08/04\",           \"6\": \"W100\",           \"7\": \"ATC=FLD\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\"        },         \"line_index\": 25      },       {        \"StructureType\": \"paragraph\",         \"data\": {          \"0\": \"NAVE ATROPOS\",           \"1\": \"55\",           \"2\": \"NAP\",           \"3\": \"JAPAN\",           \"4\": \"OPTS\",           \"5\": \"08/04\",           \"6\": \"W100\",           \"7\": \"SUBS\",           \"8\": \"RPLC\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"unknown_1\",           \"2\": \"unknown_2\",           \"3\": \"unknown_3\",           \"4\": \"unknown_4\",           \"5\": \"unknown_5\",           \"6\": \"unknown_6\",           \"7\": \"unknown_7\",           \"8\": \"unknown_8\"        },         \"line_index\": 26      }   ]  }}";
//        String alldata="04/08 SEABREEZE 53   56 04/08  USG              THENAMARIS       PPT";
//        JSONObject data= new JSONObject();
//        data.put("key", "value");
//        data.put("0", "04/08 SEABREEZE 53   56 04/08  USG              THENAMARIS       PPT");
//
//        JSONObject header= new JSONObject();
//        header.put("key", "value");
//
////System.out.println("data "+data);
////System.out.println("header "+header);
//
//   	  singleStringParsingmethod(data,  header, null, null);
//   	//System.out.println("data * "+data);
//   	//System.out.println("header * "+header);
//
//	}
	//public static void abc() {
	//	String a="{\"table_0\":{\"header_data\":[{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\",\"word\":\"position list\"}],\"line_index\":0,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.w3.org/2002/07/owl#DatatypeProperty\",\"word\":\"Comment\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Current_Status\",\"word\":\"open\"},{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"Owner\"}],\"line_index\":1,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"SEAWAYS HELLAS\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Antwerp\"}],\"line_index\":2,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",\"word\":\"upt\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"CAPE TROY\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",\"word\":\"subs\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Immingham\"}],\"line_index\":3,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",\"word\":\"subs\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"STENA PRESIDENT\"}],\"line_index\":4,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Dakar\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"DS PROMOTER\"}],\"line_index\":5,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Kingston\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"ERIKOUSSA\"}],\"line_index\":6,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Cristobal\"}],\"line_index\":7,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"AMAZON BRILLIANCE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Cristobal\"}],\"line_index\":8,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"CABO SAN VICENTE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Kingston\"}],\"line_index\":9,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",\"word\":\"upt\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",\"word\":\"subs\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"South West Pass\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"CAPE TAURA\"}],\"line_index\":10,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"MAYA\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Puerto Armuelles\"}],\"line_index\":11,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Usg\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"ANGISTRI\"}],\"line_index\":12,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"ENERGY CHANCELLOR\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Rotterdam\"}],\"line_index\":13,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"NESTOS\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"GDANSK\"}],\"line_index\":14,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.w3.org/2002/07/owl#DatatypeProperty\",\"word\":\"LC\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"STENA PERROS\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Skaw\"}],\"line_index\":15,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"AMAZON FORTITUDE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Freeport\"}],\"line_index\":16,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"HELLESPONT PROTECTOR\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Penfield\"}],\"line_index\":17,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"RIO GRANDE\"}],\"line_index\":18,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Chalmette\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"CONFIDENCE\"}],\"line_index\":19,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"AMAZON FALCON\"}],\"line_index\":20,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"CABO MISAKI\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Jose Ignacio\"}],\"line_index\":21,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion\",\"word\":\"Med\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Med\"}],\"line_index\":22,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"Vessel Name\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion\",\"word\":\"Med\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Current_Status\",\"word\":\"open\"},{\"url\":\"http://www.w3.org/2002/07/owl#DatatypeProperty\",\"word\":\"Comment\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Med\"}],\"line_index\":23,\"number_of_words\":5},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Piraeus\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Operators\",\"word\":\"hafnia\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"COMPASS\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",\"word\":\"bw\"}],\"line_index\":24,\"number_of_words\":4},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Penfield\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",\"word\":\"subs\"}],\"line_index\":25,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Dakar\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"DS PROMOTER\"}],\"line_index\":26,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"DHONOUSSA\"}],\"line_index\":27,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"RIO GRANDE\"}],\"line_index\":28,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"MUSKIE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Fujairah\"}],\"line_index\":29,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",\"word\":\"subs\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",\"word\":\"upt\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"CAPE TALLIN\"}],\"line_index\":30,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",\"word\":\"upt\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"TELLURIDE\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Hong Kong\"}],\"line_index\":31,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Haldia\"}],\"line_index\":32,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"CAPE TEMPEST\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Singapore\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Owners\",\"word\":\"upt\"}],\"line_index\":33,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Jose Ignacio\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"CABO MISAKI\"}],\"line_index\":34,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Singapore\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"GULF PEARL\"}],\"line_index\":35,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Charterers\",\"word\":\"shell\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"PACIFIC DEBBIE\"}],\"line_index\":36,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Vessel\",\"word\":\"CHEMTRANS MOON\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Zhongshan\"}],\"line_index\":37,\"number_of_words\":2}],\"table_data\":[{\"StructureType\":\"table\",\"data\":{\"0\":\"Apr 09\",\"1\":\"Seaways Hellas\",\"2\":\"69\",\"3\":\"03\",\"4\":\"Antwerp\",\"5\":\"Apr 09\",\"6\":\"poss\",\"7\":\"Capetank\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":3,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"Date\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"Port\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"Apr 15\",\"1\":\"Cape Troy\",\"2\":\"73\",\"3\":\"11\",\"4\":\"Immingham\",\"5\":\"Apr 15\",\"6\":\"subs\",\"7\":\"Upt\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":5,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"Date\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"Port\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"Apr 21\",\"1\":\"Stena President\",\"2\":\"64\",\"3\":\"07\",\"4\":\"USAC\",\"5\":\"Apr 11\",\"6\":\"1B subs\",\"7\":\"Stenabulk\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":7,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"Date\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"Port\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"Apr 23\",\"1\":\"Ds Promoter\",\"2\":\"70\",\"3\":\"02\",\"4\":\"Dakar\",\"5\":\"Apr 15\",\"6\":\"\",\"7\":\"Heidmar\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":9,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"Date\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"Port\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"Apr 24\",\"1\":\"Erikoussa\",\"2\":\"70\",\"3\":\"03\",\"4\":\"Kingston (Jam\",\"5\":\"PPT\",\"6\":\"\",\"7\":\"Eletson\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":11,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"Date\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"OPEN\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"Apr 25\",\"1\":\"Meganisi\",\"2\":\"72\",\"3\":\"04\",\"4\":\"Cristobal\",\"5\":\"PPT\",\"6\":\"\",\"7\":\"Eletson\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":13,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"Date\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"Port\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"Amazon Brilliance\",\"2\":\"72\",\"3\":\"05\",\"4\":\"Cristobal\",\"5\":\"PPT\",\"6\":\"\",\"7\":\"Livanos\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":14,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"ETA BSS CONT\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"Port\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"Cabo San Vicente\",\"2\":\"68\",\"3\":\"08\",\"4\":\"Kingston (Jam\",\"5\":\"Apr 11\",\"6\":\"poss\",\"7\":\"Capetank\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":15,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"ETA BSS CONT\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"OPEN\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"Cape Taura\",\"2\":\"73\",\"3\":\"07\",\"4\":\"South West Pass\",\"5\":\"PPT\",\"6\":\"subs states\",\"7\":\"Upt\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":16,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"ETA BSS CONT\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"Port\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"Apr 26\",\"1\":\"Maya\",\"2\":\"68\",\"3\":\"03\",\"4\":\"Puerto Armuelle\",\"5\":\"Apr 09\",\"6\":\"poss\",\"7\":\"Capetank\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":18,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"Date\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"OPEN\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"Angistri\",\"2\":\"76\",\"3\":\"00\",\"4\":\"USG\",\"5\":\"PPT\",\"6\":\"\",\"7\":\"Eletson\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":19,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"ETA BSS CONT\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"Port\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"Energy Chancellor\",\"2\":\"70\",\"3\":\"05\",\"4\":\"Rotterdam\",\"5\":\"Apr 26\",\"6\":\"\",\"7\":\"Golden Energy\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":20,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"ETA BSS CONT\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"Port\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"Apr 28\",\"1\":\"Nestos\",\"2\":\"62\",\"3\":\"05\",\"4\":\"Gdansk\",\"5\":\"Apr 28\",\"6\":\"ex d/d\",\"7\":\"Pleiades\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":22,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"Date\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"Port\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"Apr 30\",\"1\":\"Stena Perros\",\"2\":\"65\",\"3\":\"07\",\"4\":\"Skaw\",\"5\":\"Apr 30\",\"6\":\"1B - ex our t/c\",\"7\":\"Stenabulk\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":24,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"Date\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"Port\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"Amazon Fortitude\",\"2\":\"72\",\"3\":\"18\",\"4\":\"Freeport (Bah\",\"5\":\"Apr 17\",\"6\":\"\",\"7\":\"Livanos\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":25,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"ETA BSS CONT\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"OPEN\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"Hellespont Protector\",\"2\":\"73\",\"3\":\"07\",\"4\":\"St Eustatius\",\"5\":\"Apr 18\",\"6\":\"\",\"7\":\"Penfield\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":26,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"ETA BSS CONT\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"Port\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"May 01\",\"1\":\"Radiant Pride\",\"2\":\"73\",\"3\":\"07\",\"4\":\"Rio Grande\",\"5\":\"Apr 12\",\"6\":\"\",\"7\":\"Penfield\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":28,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"Date\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"VesselName\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"Confidence\",\"2\":\"71\",\"3\":\"04\",\"4\":\"Chalmette\",\"5\":\"Apr 15\",\"6\":\"\",\"7\":\"Dynacom\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":29,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"ETA BSS CONT\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"Port\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"May 08\",\"1\":\"Amazon Falcon\",\"2\":\"72\",\"3\":\"17\",\"4\":\"Gulf LNG Pascag\",\"5\":\"Apr 23\",\"6\":\"\",\"7\":\"Livanos\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":31,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"Date\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"OPEN\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"May 17\",\"1\":\"Cabo Misaki\",\"2\":\"74\",\"3\":\"17\",\"4\":\"Jose Ignacio\",\"5\":\"Apr 27\",\"6\":\"poss\",\"7\":\"Capetank\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":33,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"Date\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"Port\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"MED POSITIONS\",\"1\":\"\",\"2\":\"\",\"3\":\"\",\"4\":\"\",\"5\":\"\",\"6\":\"\",\"7\":\"\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":35,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"ETA BSS CONT\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"ETA BSS MED\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":36,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"ETA BSS CONT\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"PPT\",\"1\":\"Compass\",\"2\":\"72\",\"3\":\"06\",\"4\":\"Piraeus\",\"5\":\"PPT\",\"6\":\"ex d/d\",\"7\":\"Hafnia-Bw\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":37,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"Date\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"Port\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"Apr 14\",\"1\":\"Nordic Bern\",\"2\":\"73\",\"3\":\"08\",\"4\":\"Zouk\",\"5\":\"Apr 14\",\"6\":\"subs\",\"7\":\"Penfield\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":39,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"Date\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"OPEN\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"Apr 20\",\"1\":\"Ds Promoter\",\"2\":\"70\",\"3\":\"02\",\"4\":\"Dakar\",\"5\":\"Apr 15\",\"6\":\"\",\"7\":\"Heidmar\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":41,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"Date\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"Port\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"Apr 25\",\"1\":\"Dhonoussa\",\"2\":\"69\",\"3\":\"05\",\"4\":\"CMED\",\"5\":\"Apr 25\",\"6\":\"\",\"7\":\"Eletson\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":43,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"Date\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"Port\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"Apr 28\",\"1\":\"Radiant Pride\",\"2\":\"73\",\"3\":\"07\",\"4\":\"Rio Grande\",\"5\":\"Apr 12\",\"6\":\"\",\"7\":\"Penfield\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":45,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"Date\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"VesselName\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"May 02\",\"1\":\"Muskie\",\"2\":\"70\",\"3\":\"03\",\"4\":\"Fujairah\",\"5\":\"Apr 22\",\"6\":\"\",\"7\":\"Primetank\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":47,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"Date\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"Port\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"May 08\",\"1\":\"Cape Tallin\",\"2\":\"73\",\"3\":\"08\",\"4\":\"Tj Pelepas\",\"5\":\"Apr 19\",\"6\":\"subs\",\"7\":\"Upt\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":49,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"Date\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"OPEN\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"May 11\",\"1\":\"Telluride\",\"2\":\"72\",\"3\":\"03\",\"4\":\"Hong Kong\",\"5\":\"Apr 17\",\"6\":\"\",\"7\":\"Upt\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":51,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"Date\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"Port\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"May 12\",\"1\":\"Skopelos\",\"2\":\"70\",\"3\":\"03\",\"4\":\"Haldia\",\"5\":\"Apr 25\",\"6\":\"\",\"7\":\"Eletson\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":53,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"Date\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"Port\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"May 13\",\"1\":\"Cape Tempest\",\"2\":\"73\",\"3\":\"08\",\"4\":\"Singapore\",\"5\":\"Apr 24\",\"6\":\"\",\"7\":\"Upt\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":55,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"Date\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"Port\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"Cabo Misaki\",\"2\":\"74\",\"3\":\"17\",\"4\":\"Jose Ignacio\",\"5\":\"Apr 27\",\"6\":\"poss\",\"7\":\"Capetank\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":56,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"ETA BSS CONT\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"Port\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"May 22\",\"1\":\"Gulf Pearl\",\"2\":\"74\",\"3\":\"05\",\"4\":\"Singapore\",\"5\":\"May 03\",\"6\":\"\",\"7\":\"Mjolner\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":58,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"Date\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"Port\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"Pacific Debbie\",\"2\":\"74\",\"3\":\"17\",\"4\":\"SPORE\",\"5\":\"May 03\",\"6\":\"\",\"7\":\"Shell\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":59,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"ETA BSS CONT\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"Port\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"May 25\",\"1\":\"Chemtrans Moon\",\"2\":\"72\",\"3\":\"04\",\"4\":\"Zhongshan\",\"5\":\"May 01\",\"6\":\"\",\"7\":\"Penfield\"},\"header\":{\"0\":\"ETA BSS CONT\",\"1\":\"VESSEL NAME\",\"2\":\"DWT\",\"3\":\"BLT\",\"4\":\"OPEN\",\"5\":\"unknown_0\",\"6\":\"Comments\",\"7\":\"OWNER\"},\"line_index\":61,\"objheader\":{\"RepositionRegion\":\"Med\",\"ReportType\":\"Tonnage\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"Date\",\"1\":\"VesselName\",\"2\":\"DWT\",\"3\":\"Built\",\"4\":\"Port\",\"5\":\"Date\",\"6\":\"Comments\",\"7\":\"Owners\"}}],\"Rate_Position\":\"\"}}";
	//	String a="{\"table_0\":{\"header_data\":[{\"data\":[{\"url\":\"http://www.w3.org/2002/07/owl#Class\",\"word\":\"Vessel\"}],\"line_index\":0,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.w3.org/2002/07/owl#DatatypeProperty\",\"word\":\"date\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Date\"}],\"line_index\":1,\"number_of_words\":2},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",\"word\":\"subs\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Current_Status\",\"word\":\"open\"}],\"line_index\":2,\"number_of_words\":2}],\"table_data\":[{\"StructureType\":\"table\",\"data\":{\"0\":\"DATE\",\"1\":\"12APR-  16Apr\",\"2\":\"12APR -  18APR\",\"3\":\"12APR -21APR\"},\"header\":{\"0\":\"VESSEL COUNT 5 days\",\"1\":\"unknown_0\",\"2\":\"7 days\",\"3\":\"10 days\"},\"line_index\":1},{\"StructureType\":\"table\",\"data\":{\"0\":\"VESSELS OPEN / ON SUBS\",\"1\":\"16/03\",\"2\":\"27/05\",\"3\":\"44/06\"},\"header\":{\"0\":\"VESSEL COUNT 5 days\",\"1\":\"unknown_0\",\"2\":\"7 days\",\"3\":\"10 days\"},\"line_index\":2,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\"\",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"VESSEL COUNT 5 days\",\"1\":\"Date\",\"2\":\"Date\",\"3\":\"10 days\"}}],\"Rate_Position\":\"\"},\"table_1\":{\"header_data\":[{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Usg\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\",\"word\":\"tonnage\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",\"word\":\"MR\"}],\"line_index\":0,\"number_of_words\":3},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",\"word\":\"CLEAN\"}],\"line_index\":1,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",\"word\":\"Usg\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",\"word\":\"handy\"},{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\",\"word\":\"tonnage\"}],\"line_index\":2,\"number_of_words\":3},{\"data\":[],\"line_index\":3,\"number_of_words\":0},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",\"word\":\"handy\"}],\"line_index\":4,\"number_of_words\":1},{\"data\":[{\"url\":\"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Status\",\"word\":\"subs\"}],\"line_index\":5,\"number_of_words\":1}],\"table_data\":[{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"12Apr\",\"10\":\"BALL\",\"2\":\"ZOILO\",\"3\":\"15\",\"4\":\"50\",\"5\":\"53\",\"6\":\"USG          12/4\",\"7\":\"B5 - IMO2/3\",\"8\":\"2,3\",\"9\":\"TANK\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":8,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"12Apr\",\"10\":\"BALL SUBS\",\"2\":\"SWARNA MALA\",\"3\":\"10\",\"4\":\"51\",\"5\":\"56\",\"6\":\"USG          12/4\",\"7\":\"JET/NAP/HSD+\",\"8\":\"\",\"9\":\"KOCH\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":9,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"12Apr\",\"10\":\"BALL\",\"2\":\"HARUNA EXPRESS\",\"3\":\"04\",\"4\":\"45\",\"5\":\"54\",\"6\":\"YUCATAN CHA  10/4\",\"7\":\"ULSD\",\"8\":\"\",\"9\":\"MOSK\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":10,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"12Apr\",\"10\":\"BALL\",\"2\":\"ALPINE MIA\",\"3\":\"08\",\"4\":\"47\",\"5\":\"53\",\"6\":\"USG          12/4\",\"7\":\"UMS/ETH+BIO+\",\"8\":\"3\",\"9\":\"DIAM\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":11,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"12Apr\",\"10\":\"BALL\",\"2\":\"OPAL EXPRESS\",\"3\":\"06\",\"4\":\"48\",\"5\":\"54\",\"6\":\"USG          12/4\",\"7\":\"KERO\",\"8\":\"\",\"9\":\"MOSK\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":12,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"12Apr\",\"10\":\"BALL SUBS\",\"2\":\"ANCE\",\"3\":\"06\",\"4\":\"52\",\"5\":\"56\",\"6\":\"USG          12/4\",\"7\":\"CPP\",\"8\":\"2,3\",\"9\":\"MANS\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":13,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"13Apr\",\"10\":\"BALL\",\"2\":\"RIDGEBURY KATHE\",\"3\":\"09\",\"4\":\"50\",\"5\":\"52\",\"6\":\"USG          13/4\",\"7\":\"DO+JET/UMS/U\",\"8\":\"3\",\"9\":\"NORI\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":14,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"13Apr\",\"10\":\"ON BERTH\",\"2\":\"RELIANCE II\",\"3\":\"06\",\"4\":\"46\",\"5\":\"51\",\"6\":\"PAJARITOS    11/4\",\"7\":\"CPP\",\"8\":\"3\",\"9\":\"CCI\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":15,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"13Apr\",\"10\":\"BALL SUBS\",\"2\":\"MINERVA OCEANIA\",\"3\":\"09\",\"4\":\"47\",\"5\":\"51\",\"6\":\"USG          13/4\",\"7\":\"CPP\",\"8\":\"\",\"9\":\"MINE\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":16,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"13Apr\",\"10\":\"BALL\",\"2\":\"SEABREEZE\",\"3\":\"07\",\"4\":\"53\",\"5\":\"57\",\"6\":\"USG          13/4\",\"7\":\"CPP\",\"8\":\"\",\"9\":\"THEN\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":17,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"13Apr\",\"10\":\"BALL\",\"2\":\"BW FALCON\",\"3\":\"15\",\"4\":\"52\",\"5\":\"52\",\"6\":\"USG          13/4\",\"7\":\"UMS/JET+ULSD\",\"8\":\"2,3\",\"9\":\"HAFN\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":18,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"14Apr\",\"10\":\"BALL\",\"2\":\"MR LEO\",\"3\":\"08\",\"4\":\"50\",\"5\":\"51\",\"6\":\"HOUSTON      14/4\",\"7\":\"CPP / EX DD\",\"8\":\"2,3\",\"9\":\"TRAF\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":19,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"14Apr\",\"10\":\"BALL\",\"2\":\"JANE\",\"3\":\"08\",\"4\":\"51\",\"5\":\"52\",\"6\":\"USG          14/4\",\"7\":\"ULSD/JET/GO\",\"8\":\"3\",\"9\":\"PROD\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":20,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"14Apr\",\"10\":\"BALL\",\"2\":\"ENERGY PATRIOT\",\"3\":\"08\",\"4\":\"46\",\"5\":\"52\",\"6\":\"USG          14/4\",\"7\":\"UMS/JET/ULSD\",\"8\":\"2\",\"9\":\"VALE\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":21,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"15Apr\",\"10\":\"LADEN UNC\",\"2\":\"ANDIAMO\",\"3\":\"09\",\"4\":\"48\",\"5\":\"50\",\"6\":\"CORPUS CHRI  14/4\",\"7\":\"UMS\",\"8\":\"\",\"9\":\"PRIM\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":22,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"15Apr\",\"10\":\"ON BERTH\",\"2\":\"KRITI EMERALD\",\"3\":\"05\",\"4\":\"50\",\"5\":\"61\",\"6\":\"CIUDAD MADE  13/4\",\"7\":\"\",\"8\":\"\",\"9\":\"ST S\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":23,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"15Apr\",\"10\":\"BALL\",\"2\":\"PACIFIC AZUR\",\"3\":\"18\",\"4\":\"49\",\"5\":\"56\",\"6\":\"SAVANNAH     10/4\",\"7\":\"VIRGIN - IMO\",\"8\":\"2,3\",\"9\":\"EAST\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":24,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"15Apr\",\"10\":\"BALL\",\"2\":\"ISOLDE\",\"3\":\"08\",\"4\":\"37\",\"5\":\"41\",\"6\":\"BALBOA       10/4\",\"7\":\"UMS\",\"8\":\"3\",\"9\":\"SCOR\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":25,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"16Apr\",\"10\":\"BALL\",\"2\":\"CELSIUS RIMINI\",\"3\":\"09\",\"4\":\"53\",\"5\":\"57\",\"6\":\"USG          16/4\",\"7\":\"UMS\",\"8\":\"\",\"9\":\"D'AM\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":26,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"17Apr\",\"10\":\"ON BERTH\",\"2\":\"STRYMON\",\"3\":\"05\",\"4\":\"47\",\"5\":\"53\",\"6\":\"SAVANNAH     12/4\",\"7\":\"CPP\",\"8\":\"3\",\"9\":\"SUN\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":28,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"17Apr\",\"10\":\"BALL\",\"2\":\"MINERVA MEDITER\",\"3\":\"08\",\"4\":\"47\",\"5\":\"52\",\"6\":\"BALBOA       12/4\",\"7\":\"CPP/UMS+JET/\",\"8\":\"\",\"9\":\"MINE\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":29,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"17Apr\",\"10\":\"BALL\",\"2\":\"PTI HERCULES\",\"3\":\"06\",\"4\":\"51\",\"5\":\"50\",\"6\":\"CRISTOBAL    12/4\",\"7\":\"ULSD/UMS/ULS\",\"8\":\"3\",\"9\":\"CPTA\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":30,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"17Apr\",\"10\":\"BALL SUBS\",\"2\":\"SEA HERMES\",\"3\":\"04\",\"4\":\"45\",\"5\":\"51\",\"6\":\"BALBOA       12/4\",\"7\":\"ULSD\",\"8\":\"\",\"9\":\"D'AM\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":31,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"17-18\",\"10\":\"BALL\",\"2\":\"TURQUOISE\",\"3\":\"15\",\"4\":\"50\",\"5\":\"50\",\"6\":\"CHARLESTON   12-13\",\"7\":\"UMS/ULSDUMS\",\"8\":\"2,3\",\"9\":\"VALE\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":32,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"18Apr\",\"10\":\"AT ANCHOR\",\"2\":\"TORM MARY\",\"3\":\"02\",\"4\":\"45\",\"5\":\"52\",\"6\":\"PUNTA CARDO  12/4\",\"7\":\"ALKY/NAP/GO\",\"8\":\"2\",\"9\":\"TORM\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":33,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"18Apr\",\"10\":\"ON BERTH\",\"2\":\"ATLANTIC BREEZE\",\"3\":\"07\",\"4\":\"47\",\"5\":\"52\",\"6\":\"NEW YORK     12/4\",\"7\":\"NAP/JET/ULSD\",\"8\":\"2\",\"9\":\"DIAM\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":34,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"18Apr\",\"10\":\"AT ANCHOR\",\"2\":\"QUARTZ\",\"3\":\"15\",\"4\":\"49\",\"5\":\"50\",\"6\":\"PHILADELPHI  12/4\",\"7\":\"UMS/ULSD/UMS\",\"8\":\"2\",\"9\":\"VALE\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":35,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"18-19\",\"10\":\"BALL\",\"2\":\"HAFNIA DAISY\",\"3\":\"15\",\"4\":\"49\",\"5\":\"53\",\"6\":\"NEW YORK     12-13\",\"7\":\"JET/JET/JET\",\"8\":\"2,3\",\"9\":\"VALE\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":36,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"18Apr\",\"10\":\"BALL SUBS\",\"2\":\"FREJA BALTIC\",\"3\":\"08\",\"4\":\"47\",\"5\":\"50\",\"6\":\"BALBOA       13/4\",\"7\":\"ULSD\",\"8\":\"\",\"9\":\"D'AM\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":37,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"18Apr\",\"10\":\"BALL\",\"2\":\"NAVE TITAN\",\"3\":\"13\",\"4\":\"50\",\"5\":\"52\",\"6\":\"NEW YORK     12/4\",\"7\":\"UMS*\",\"8\":\"2,3\",\"9\":\"MANS\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":38,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"18Apr\",\"10\":\"AT ANCHOR\",\"2\":\"LUCTOR\",\"3\":\"11\",\"4\":\"50\",\"5\":\"54\",\"6\":\"POZOS COLOR  13/4\",\"7\":\"NAP\",\"8\":\"2,3\",\"9\":\"BP\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":39,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"18Apr\",\"10\":\"AT ANCHOR\",\"2\":\"SCF USSURI\",\"3\":\"09\",\"4\":\"50\",\"5\":\"53\",\"6\":\"PHILADELPHI  12/4\",\"7\":\"ULSD/UMS/UMS\",\"8\":\"3\",\"9\":\"SCF\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":40,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"20Apr\",\"10\":\"\",\"2\":\"STI LE ROCHER\",\"3\":\"13\",\"4\":\"49\",\"5\":\"54\",\"6\":\"LA LIBERTAD  13/4\",\"7\":\"\",\"8\":\"2,3\",\"9\":\"SCOR\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":42,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"20-21\",\"10\":\"\",\"2\":\"HAFNIA LISE\",\"3\":\"16\",\"4\":\"49\",\"5\":\"54\",\"6\":\"NEW YORK     14-15\",\"7\":\"ULSD/JET/ULS\",\"8\":\"2,3\",\"9\":\"VALE\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":43,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"20Apr\",\"10\":\"\",\"2\":\"KOUROS\",\"3\":\"08\",\"4\":\"51\",\"5\":\"51\",\"6\":\"NEW YORK     14/4\",\"7\":\"UMS+KERO/ULS\",\"8\":\"2,3\",\"9\":\"HAFN\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":44,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"20Apr\",\"10\":\"\",\"2\":\"MARKOS I\",\"3\":\"05\",\"4\":\"45\",\"5\":\"54\",\"6\":\"ESMERALDAS   13/4\",\"7\":\"ULSD/CHEMS/V\",\"8\":\"2\",\"9\":\"KOCH\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":45,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"20Apr\",\"10\":\"\",\"2\":\"IBIS PACIFIC\",\"3\":\"07\",\"4\":\"49\",\"5\":\"51\",\"6\":\"PORT AU PRI  15/4\",\"7\":\"UMS\",\"8\":\"3\",\"9\":\"CCI\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":46,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"20Apr\",\"10\":\"\",\"2\":\"KRITI AMBER\",\"3\":\"05\",\"4\":\"50\",\"5\":\"61\",\"6\":\"NEW YORK     14/4\",\"7\":\"ULSD\",\"8\":\"\",\"9\":\"ST S\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":47,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"20-21\",\"10\":\"\",\"2\":\"SW JULIA I\",\"3\":\"03\",\"4\":\"35\",\"5\":\"43\",\"6\":\"PORT AU PRI  15-16\",\"7\":\"UMS/REF/REF+\",\"8\":\"3\",\"9\":\"UPT\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":48,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"21Apr\",\"10\":\"\",\"2\":\"SM OSPREY\",\"3\":\"17\",\"4\":\"50\",\"5\":\"51\",\"6\":\"BALBOA       16/4\",\"7\":\"\",\"8\":\"2,3\",\"9\":\"CLEA\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":49,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"21Apr\",\"10\":\"\",\"2\":\"ELKA ANGELIQUE\",\"3\":\"01\",\"4\":\"44\",\"5\":\"53\",\"6\":\"CARIBS       14/4\",\"7\":\"NAP/ULSD/JET\",\"8\":\"3\",\"9\":\"ELKA\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":50,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"21Apr\",\"10\":\"\",\"2\":\"CIELO DI SALERN\",\"3\":\"16\",\"4\":\"39\",\"5\":\"42\",\"6\":\"ECUADOR      13/4\",\"7\":\"UMS\",\"8\":\"2,3\",\"9\":\"D'AM\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":51,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"21Apr\",\"10\":\"\",\"2\":\"ALLEGRO\",\"3\":\"12\",\"4\":\"46\",\"5\":\"51\",\"6\":\"SAN JUAN PU  15/4\",\"7\":\"UMS/UMS/MOL\",\"8\":\"2,3\",\"9\":\"TEAM\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":52,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"21Apr\",\"10\":\"\",\"2\":\"NORD VANTAGE\",\"3\":\"18\",\"4\":\"50\",\"5\":\"52\",\"6\":\"RIO HAINA    16/4\",\"7\":\"GASOLINE/GAS\",\"8\":\"2\",\"9\":\"UNIO\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":53,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"21Apr\",\"10\":\"\",\"2\":\"ENERGY PROGRESS\",\"3\":\"08\",\"4\":\"46\",\"5\":\"52\",\"6\":\"SAN JOSE GU  13/4\",\"7\":\"ULSD/JET/UMS\",\"8\":\"2\",\"9\":\"GOLD\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":54,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"21Apr\",\"10\":\"\",\"2\":\"SEAWAYS ATHENS\",\"3\":\"12\",\"4\":\"50\",\"5\":\"53\",\"6\":\"PUERTO QUET  13/4\",\"7\":\"ULSD+JET/UMS\",\"8\":\"3\",\"9\":\"CPTA\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":55,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"21Apr\",\"10\":\"\",\"2\":\"SEAWAYS MILOS\",\"3\":\"11\",\"4\":\"50\",\"5\":\"53\",\"6\":\"TAMPICO      19/4\",\"7\":\"HSD\",\"8\":\"3\",\"9\":\"CPTA\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":56,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"21Apr\",\"10\":\"\",\"2\":\"CEYLON\",\"3\":\"02\",\"4\":\"46\",\"5\":\"49\",\"6\":\"LA PAMPILLA  11/4\",\"7\":\"ULSD/DIESEL/\",\"8\":\"\",\"9\":\"EAST\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":57,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"21Apr\",\"10\":\"SUBS\",\"2\":\"PARSIFAL II\",\"3\":\"08\",\"4\":\"37\",\"5\":\"41\",\"6\":\"HALIFAX      13/4\",\"7\":\"UMS\",\"8\":\"3\",\"9\":\"SCOR\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":58,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"21Apr\",\"10\":\"UNC\",\"2\":\"ADAMAS I\",\"3\":\"09\",\"4\":\"50\",\"5\":\"53\",\"6\":\"ARUBA        15/4\",\"7\":\"DIESEL + JET\",\"8\":\"3\",\"9\":\"CARB\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":59,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"22Apr\",\"10\":\"\",\"2\":\"HORIZON THEANO\",\"3\":\"09\",\"4\":\"50\",\"5\":\"51\",\"6\":\"BALBOA       17/4\",\"7\":\"UMS+MTBE\",\"8\":\"3\",\"9\":\"HORI\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":61,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"22Apr\",\"10\":\"HOLD\",\"2\":\"SEAWAYS SKOPELO\",\"3\":\"09\",\"4\":\"50\",\"5\":\"52\",\"6\":\"BALBOA       17/4\",\"7\":\"UMS\",\"8\":\"3\",\"9\":\"CPTA\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":62,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"22Apr\",\"10\":\"IF\",\"2\":\"GULF JUMEIRAH\",\"3\":\"08\",\"4\":\"46\",\"5\":\"52\",\"6\":\"LAS MINAS    17/4\",\"7\":\"JET+ULSD+UMS\",\"8\":\"2\",\"9\":\"GEM\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":63,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"23Apr\",\"10\":\"\",\"2\":\"BW MYNA\",\"3\":\"15\",\"4\":\"49\",\"5\":\"52\",\"6\":\"ROSARITO     09/4\",\"7\":\"UMS/JET/JET\",\"8\":\"\",\"9\":\"HAFN\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":64,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"23Apr\",\"10\":\"\",\"2\":\"SCF ANGARA\",\"3\":\"08\",\"4\":\"50\",\"5\":\"53\",\"6\":\"POINT TUPPE  15/4\",\"7\":\"UMS\",\"8\":\"3\",\"9\":\"SCF\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":65,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"23Apr\",\"10\":\"SUBS\",\"2\":\"LOUKAS I\",\"3\":\"05\",\"4\":\"45\",\"5\":\"52\",\"6\":\"ST LUCIA     16/4\",\"7\":\"LSD\",\"8\":\"2\",\"9\":\"D'AM\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":66,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"23Apr\",\"10\":\"\",\"2\":\"LADON\",\"3\":\"18\",\"4\":\"50\",\"5\":\"53\",\"6\":\"NEW YORK     17/4\",\"7\":\"UMS/COND/NAP\",\"8\":\"\",\"9\":\"EQUI\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":67,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"24Apr\",\"10\":\"\",\"2\":\"MISS BENEDETTA\",\"3\":\"12\",\"4\":\"50\",\"5\":\"53\",\"6\":\"ACAJUTLA     16/4\",\"7\":\"UMS\",\"8\":\"2\",\"9\":\"CPTA\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":68,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"24-25\",\"10\":\"IF\",\"2\":\"NORDIC AGNETHA\",\"3\":\"09\",\"4\":\"37\",\"5\":\"40\",\"6\":\"NEW YORK     18-19\",\"7\":\"UMS+REF/COND\",\"8\":\"3\",\"9\":\"UPT\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":69,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"25Apr\",\"10\":\"\",\"2\":\"ALPINE HIBISCUS\",\"3\":\"10\",\"4\":\"37\",\"5\":\"40\",\"6\":\"POINTE-A-PI  18/4\",\"7\":\"\",\"8\":\"3\",\"9\":\"ST S\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":70,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"25Apr\",\"10\":\"\",\"2\":\"SILVER ZOE\",\"3\":\"15\",\"4\":\"49\",\"5\":\"53\",\"6\":\"MOLLENDO     14/4\",\"7\":\"ETHANOL/ULSD\",\"8\":\"2\",\"9\":\"SHEL\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":71,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"25Apr\",\"10\":\"IF\",\"2\":\"BW HAWK\",\"3\":\"15\",\"4\":\"50\",\"5\":\"51\",\"6\":\"GUAYMAS      12/4\",\"7\":\"ULSD+JET/ULS\",\"8\":\"2,3\",\"9\":\"HAFN\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":72,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}},{\"StructureType\":\"table\",\"data\":{\"0\":\"\",\"1\":\"26Apr\",\"10\":\"\",\"2\":\"SEAWAYS KYTHNOS\",\"3\":\"10\",\"4\":\"50\",\"5\":\"52\",\"6\":\"TOPOLOBAMPO  14/4\",\"7\":\"UMS+MTBE\",\"8\":\"3\",\"9\":\"CPTA\"},\"header\":{\"0\":\"unknown_0\",\"1\":\"ETA\",\"10\":\"COMMENTS\",\"2\":\"VESSEL\",\"3\":\"BLT\",\"4\":\"DWT\",\"5\":\"CUB\",\"6\":\"OPEN\",\"7\":\"Last Cargo\",\"8\":\"IMO O\",\"9\":\"WNERS\"},\"label_line_index\":6,\"label_string\":\"     ETA    VESSEL           BLT  DWT  CUB  OPEN                Last Cargo    IMO OWNERS COMMENTS\",\"line_index\":73,\"objheader\":{\"RepositionRegion\":\"\",\"ReportType\":\" \",\"CargoType\":\"\"},\"new_header_data\":{\"0\":\"unknown_0\",\"1\":\"ETABasis\",\"10\":\"Comments\",\"2\":\"VesselName\",\"3\":\"Built\",\"4\":\"DWT\",\"5\":\"Cubics\",\"6\":\"OPEN\",\"7\":\"Comments\",\"8\":\"IMO O\",\"9\":\"Owners\"}}],\"Rate_Position\":\"\"}}";
	//String a="{  \"table_1\": {    \"header_data\": [      {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"DIRTY\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Pointe Noire\"          }        ],         \"line_index\": 0,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"DIRTY\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"handy\"          }        ],         \"line_index\": 1,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Pointe Noire\"          }        ],         \"line_index\": 2,         \"number_of_words\": 1      },       {        \"data\": [],         \"line_index\": 3,         \"number_of_words\": 0      },       {        \"data\": [],         \"line_index\": 4,         \"number_of_words\": 0      },       {        \"data\": [],         \"line_index\": 5,         \"number_of_words\": 0      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion\",             \"word\": \"Med\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"W Med\"          }        ],         \"line_index\": 6,         \"number_of_words\": 2      }    ],     \"table_data\": [      {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"\",           \"1\": \"27/04\",           \"2\": \"Baltic Wave\",           \"3\": \"Tanger Med 15/04\",           \"4\": \"37\",           \"5\": \"03\",           \"6\": \"NORIENT                     PPT\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"Date\",           \"2\": \"Vessel\",           \"3\": \"Ex\",           \"4\": \"Dwt\",           \"5\": \"Yr\",           \"6\": \"Fleet                   Comment\"        },         \"label_line_index\": 7,         \"label_string\": \"     Date  Vessel           Ex                    Dwt   Yr  Fleet                   Comment\",         \"line_index\": 9      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"\",           \"1\": \"20/04\",           \"2\": \"50    1\",           \"3\": \"0  MOLLER\",           \"4\": \"\",           \"5\": \"U\",           \"6\": \"NC\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"Date\",           \"2\": \"Vessel\",           \"3\": \"Ex\",           \"4\": \"Dwt\",           \"5\": \"Yr\",           \"6\": \"Fleet                   Comment\"        },         \"label_line_index\": 7,         \"label_string\": \"     Date  Vessel           Ex                    Dwt   Yr  Fleet                   Comment\",         \"line_index\": 12      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"\",           \"1\": \"29/04\",           \"2\": \"Castillo De Mont\",           \"3\": \"Barcelona 15/04\",           \"4\": \"34\",           \"5\": \"02\",           \"6\": \"ELCANO                      PPT\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"Date\",           \"2\": \"Vessel\",           \"3\": \"Ex\",           \"4\": \"Dwt\",           \"5\": \"Yr\",           \"6\": \"Fleet                   Comment\"        },         \"label_line_index\": 7,         \"label_string\": \"     Date  Vessel           Ex                    Dwt   Yr  Fleet                   Comment\",         \"line_index\": 16      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"\",           \"1\": \"21/04\",           \"2\": \"37    08  SCORPI\",           \"3\": \"O                   TR\",           \"4\": \"ACK\",           \"5\": \"\",           \"6\": \"\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"Date\",           \"2\": \"Vessel\",           \"3\": \"Ex\",           \"4\": \"Dwt\",           \"5\": \"Yr\",           \"6\": \"Fleet                   Comment\"        },         \"label_line_index\": 7,         \"label_string\": \"     Date  Vessel           Ex                    Dwt   Yr  Fleet                   Comment\",         \"line_index\": 19      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"\",           \"1\": \"30/04\",           \"2\": \"Baltic Chief I\",           \"3\": \"Barcelona 16-17/04\",           \"4\": \"37\",           \"5\": \"00\",           \"6\": \"UPT\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"Date\",           \"2\": \"Vessel\",           \"3\": \"Ex\",           \"4\": \"Dwt\",           \"5\": \"Yr\",           \"6\": \"Fleet                   Comment\"        },         \"label_line_index\": 7,         \"label_string\": \"     Date  Vessel           Ex                    Dwt   Yr  Fleet                   Comment\",         \"line_index\": 20      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"\",           \"1\": \"30/04\",           \"2\": \"Hafnia Rainier\",           \"3\": \"Gibraltar 18/04\",           \"4\": \"40\",           \"5\": \"04\",           \"6\": \"HAFNIA                     BLST\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"Date\",           \"2\": \"Vessel\",           \"3\": \"Ex\",           \"4\": \"Dwt\",           \"5\": \"Yr\",           \"6\": \"Fleet                   Comment\"        },         \"label_line_index\": 7,         \"label_string\": \"     Date  Vessel           Ex                    Dwt   Yr  Fleet                   Comment\",         \"line_index\": 21      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"\",           \"1\": \"30/04\",           \"2\": \"Roy Maersk\",           \"3\": \"Algeciras 18/04\",           \"4\": \"35\",           \"5\": \"05\",           \"6\": \"MOLLER\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"Date\",           \"2\": \"Vessel\",           \"3\": \"Ex\",           \"4\": \"Dwt\",           \"5\": \"Yr\",           \"6\": \"Fleet                   Comment\"        },         \"label_line_index\": 7,         \"label_string\": \"     Date  Vessel           Ex                    Dwt   Yr  Fleet                   Comment\",         \"line_index\": 22      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"\",           \"1\": \"02/05\",           \"2\": \"Berenike\",           \"3\": \"Gibraltar 20/04\",           \"4\": \"40\",           \"5\": \"03\",           \"6\": \"TSAKOS\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"Date\",           \"2\": \"Vessel\",           \"3\": \"Ex\",           \"4\": \"Dwt\",           \"5\": \"Yr\",           \"6\": \"Fleet                   Comment\"        },         \"label_line_index\": 7,         \"label_string\": \"     Date  Vessel           Ex                    Dwt   Yr  Fleet                   Comment\",         \"line_index\": 23      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"\",           \"1\": \"02/05\",           \"2\": \"Cpo England\",           \"3\": \"Castellon 18/04\",           \"4\": \"37\",           \"5\": \"08\",           \"6\": \"NORIENT\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"Date\",           \"2\": \"Vessel\",           \"3\": \"Ex\",           \"4\": \"Dwt\",           \"5\": \"Yr\",           \"6\": \"Fleet                   Comment\"        },         \"label_line_index\": 7,         \"label_string\": \"     Date  Vessel           Ex                    Dwt   Yr  Fleet                   Comment\",         \"line_index\": 24      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"\",           \"1\": \"02/05\",           \"2\": \"Celsius Perth\",           \"3\": \"Barcelona 18/04\",           \"4\": \"37\",           \"5\": \"04\",           \"6\": \"HAFNIA                    TRACK\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"Date\",           \"2\": \"Vessel\",           \"3\": \"Ex\",           \"4\": \"Dwt\",           \"5\": \"Yr\",           \"6\": \"Fleet                   Comment\"        },         \"label_line_index\": 7,         \"label_string\": \"     Date  Vessel           Ex                    Dwt   Yr  Fleet                   Comment\",         \"line_index\": 25      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"\",           \"1\": \"07/05\",           \"2\": \"Minerva Vaso\",           \"3\": \"Lavera 22/04\",           \"4\": \"51\",           \"5\": \"06\",           \"6\": \"MINERVA\"        },         \"header\": {          \"0\": \"unknown_0\",           \"1\": \"Date\",           \"2\": \"Vessel\",           \"3\": \"Ex\",           \"4\": \"Dwt\",           \"5\": \"Yr\",           \"6\": \"Fleet                   Comment\"        },         \"label_line_index\": 7,         \"label_string\": \"     Date  Vessel           Ex                    Dwt   Yr  Fleet                   Comment\",         \"line_index\": 26      }    ]  }}";	
//		String a="{  \"table_0\": {    \"header_data\": [      {        \"data\": [],         \"line_index\": 0,         \"number_of_words\": 0      }    ],     \"table_data\": []  },   \"table_1\": {    \"header"
//				+ "_data\": [      {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Southport\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CLEAN\"          }        ],         \"line_index\": 0,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Type\",             \"word\": \"CLEAN\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Southport\"          }        ],         \"line_index\": 1,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"As\"          }        ],         \"line_index\": 2,         \"number_of_words\": 1      },       {        \"data\": [],         \"line_index\": 3,         \"number_of_words\": 0      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"As\"          }        ],         \"line_index\": 4,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#ReportType\",             \"word\": \"tonnage\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"As\"          }        ],         \"line_index\": 5,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#VesselType\",             \"word\": \"MR\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Usg\"          }        ],         \"line_index\": 6,         \"number_of_words\": 2      },       {        \"data\": [],         \"line_index\": 7,         \"number_of_words\": 0      },       {        \"data\": [],         \"line_index\": 8,         \"number_of_words\": 0      },       {        \"data\": [],         \"line_index\": 9,         \"number_of_words\": 0      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Cargo_Grade\",             \"word\": \"crude\"          }        ],         \"line_index\": 10,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Current_Status\",             \"word\": \"close\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Brent\"          }        ],         \"line_index\": 11,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"As\"          }        ],         \"line_index\": 12,         \"number_of_words\": 1      },       {        \"data\": [],         \"line_index\": 13,         \"number_of_words\": 0      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"One\"          }        ],         \"line_index\": 14,         \"number_of_words\": 1      },       {        \"data\": [],         \"line_index\": 15,         \"number_of_words\": 0      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Russia\"          }        ],         \"line_index\": 16,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Brent\"          }        ],         \"line_index\": 17,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RateType\",             \"word\": \"ws\"          }        ],         \"line_index\": 18,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion\",             \"word\": \"UKC\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RateType\",             \"word\": \"ws\"          }        ],         \"line_index\": 19,         \"number_of_words\": 2      },       {        \"data\": [],         \"line_index\": 20,         \"number_of_words\": 0      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Aruba\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RateType\",             \"word\": \"ws\"          }        ],         \"line_index\": 21,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RateType\",             \"word\": \"ws\"          }        ],         \"line_index\": 22,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Europe\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RateType\",             \"word\": \"ws\"          }        ],         \"line_index\": 23,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Brazil\"          },           {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RateType\",             \"word\": \"ws\"          }        ],         \"line_index\": 24,         \"number_of_words\": 2      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Usg\"          }        ],         \"line_index\": 25,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Usg\"          }        ],         \"line_index\": 26,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#Port\",             \"word\": \"Usg\"          }        ],         \"line_index\": 27,         \"number_of_words\": 1      },       {        \"data\": [          {            \"url\": \"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018#RepositionRegion\",             \"word\": \"UKC\"          }        ],         \"line_index\": 28,         \"number_of_words\": 1      },       {        \"data\": [],         \"line_index\": 29,         \"number_of_words\": 0      }    ],     \"table_data\": [      {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"BREEZE\",           \"1\": \"90\",           \"2\": \"ULSD\",           \"3\": \"BALT/UKC\",           \"4\": \"22APR\",           \"5\": \"WS\",           \"6\": \"120\",           \"7\": \"VITOL     -\",           \"8\": \"SUBS\"        },         \"header\": {          \"0\": \"STI KINGSWAY\",           \"1\": \"90\",           \"2\": \"UMS\",           \"3\": \"BALT/WAFR\",           \"4\": \"21/APR\",           \"5\": \"WS\",           \"6\": \"70\",           \"7\": \"VITOL     -\",           \"8\": \"SUBS\"        },         \"label_line_index\": 30,         \"label_string\": \"STI KINGSWAY       90 UMS  BALT/WAFR     21/APR  WS 70              VITOL     - SUBS\",         \"line_index\": 31      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"FRONT CHEETAH\",           \"1\": \"90\",           \"2\": \"UMS\",           \"3\": \"BALT/UKC\",           \"4\": \"22APR\",           \"5\": \"WS\",           \"6\": \"80\",           \"7\": \"VITOL     -\",           \"8\": \"SUBS\"        },         \"header\": {          \"0\": \"STI KINGSWAY\",           \"1\": \"90\",           \"2\": \"UMS\",           \"3\": \"BALT/WAFR\",           \"4\": \"21/APR\",           \"5\": \"WS\",           \"6\": \"70\",           \"7\": \"VITOL     -\",           \"8\": \"SUBS\"        },         \"label_line_index\": 30,         \"label_string\": \"STI KINGSWAY       90 UMS  BALT/WAFR     21/APR  WS 70              VITOL     - SUBS\",         \"line_index\": 32      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"SEA ICON\",           \"1\": \"90\",           \"2\": \"ULSD\",           \"3\": \"BALTUKC\",           \"4\": \"02/MAY\",           \"5\": \"WS\",           \"6\": \"120\",           \"7\": \"VITOL     -\",           \"8\": \"SUBS\"        },         \"header\": {          \"0\": \"STI KINGSWAY\",           \"1\": \"90\",           \"2\": \"UMS\",           \"3\": \"BALT/WAFR\",           \"4\": \"21/APR\",           \"5\": \"WS\",           \"6\": \"70\",           \"7\": \"VITOL     -\",           \"8\": \"SUBS\"        },         \"label_line_index\": 30,         \"label_string\": \"STI KINGSWAY       90 UMS  BALT/WAFR     21/APR  WS 70              VITOL     - SUBS\",         \"line_index\": 33      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"PALAWAN STAR\",           \"1\": \"60\",           \"2\": \"UMS\",           \"3\": \"ARA/AG\",           \"4\": \"18/APR\",           \"5\": \"$1.\",           \"6\": \"175M\",           \"7\": \"ARAMCO    -\",           \"8\": \"FXD\"        },         \"header\": {          \"0\": \"STI KINGSWAY\",           \"1\": \"90\",           \"2\": \"UMS\",           \"3\": \"BALT/WAFR\",           \"4\": \"21/APR\",           \"5\": \"WS\",           \"6\": \"70\",           \"7\": \"VITOL     -\",           \"8\": \"SUBS\"        },         \"label_line_index\": 30,         \"label_string\": \"STI KINGSWAY       90 UMS  BALT/WAFR     21/APR  WS 70              VITOL     - SUBS\",         \"line_index\": 34      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"PACIFIC JULIA\",           \"1\": \"60\",           \"2\": \"UMS\",           \"3\": \"PORVOO/TA\",           \"4\": \"24/APR\",           \"5\": \"WS\",           \"6\": \"120\",           \"7\": \"NESTE     -\",           \"8\": \"SUBS\"        },         \"header\": {          \"0\": \"STI KINGSWAY\",           \"1\": \"90\",           \"2\": \"UMS\",           \"3\": \"BALT/WAFR\",           \"4\": \"21/APR\",           \"5\": \"WS\",           \"6\": \"70\",           \"7\": \"VITOL     -\",           \"8\": \"SUBS\"        },         \"label_line_index\": 30,         \"label_string\": \"STI KINGSWAY       90 UMS  BALT/WAFR     21/APR  WS 70              VITOL     - SUBS\",         \"line_index\": 35      },       {        \"StructureType\": \"table\",         \"data\": {          \"0\": \"GINNY\",           \"1\": \"60\",           \"2\": \"UMS\",           \"3\": \"ARA/WAFR\",           \"4\": \"26/APR\",           \"5\": \"WS\",           \"6\": \"115\",           \"7\": \"CSSSA     -\",           \"8\": \"SUBS\"        },         \"header\": {          \"0\": \"STI KINGSWAY\",           \"1\": \"90\",           \"2\": \"UMS\",           \"3\": \"BALT/WAFR\",           \"4\": \"21/APR\",           \"5\": \"WS\",           \"6\": \"70\",           \"7\": \"VITOL     -\",           \"8\": \"SUBS\"        },         \"label_line_index\": 30,         \"label_string\": \"STI KINGSWAY       90 UMS  BALT/WAFR     21/APR  WS 70              VITOL     - SUBS\",         \"line_index\": 36      }       ]  }}";
String a= "";
	}
	
	public static String updatedMainScript(PrintWriter out, Session session, String pythonScriptApiData,
			String emailUrl, String textSentMailTime, String subjectNodePath, String from_Source, String timestampDate,
			String timestampDateAndTime, String filepath) {

	JSONObject obj=null;
	
		//System.out.println("start");
		try {
			JSONObject returnobj = new JSONObject();
			 obj = new JSONObject(pythonScriptApiData);
			Iterator mainitr = obj.keys();
			while (mainitr.hasNext()) {
				String TableName = (String) mainitr.next();
				JSONObject Tableobj = obj.getJSONObject(TableName);
				JSONArray table_dataobj = Tableobj.getJSONArray("table_data");
				JSONArray header_dataobj = Tableobj.getJSONArray("header_data");

				// one loop to check starting of table by checking vesselname
				int vesselline_line_index = 0;
				int vesseljson_count = 0;
				
				
				
				for (int i = 0; i < table_dataobj.length(); i++) {
					JSONObject eachrowobj = table_dataobj.getJSONObject(i);
					JSONObject data = eachrowobj.getJSONObject("data");
					JSONObject header = eachrowobj.getJSONObject("header");
					Iterator itr = data.keys();
					if (vesselline_line_index == 0) {
						while (itr.hasNext()) {
							if (vesselline_line_index == 0) {
								//System.out.println("vesselline_no blank");
								String key = (String) itr.next();
								String value = data.getString(key);
								String solrres = pallavi_SortJSON
										.executesolrQuery_bywords(value.toUpperCase().replace(" ", "_"));
								boolean checkjsonString = SaveReportDataClass.isJSONValid(solrres);
								if (checkjsonString) {
									// parsejson from solr //get DocumentName
									String DocumentName = pallavi_SortJSON.parsesolrresp(solrres, "DocumentName");
									//System.out.println("DocumentName  " + DocumentName);
									if (DocumentName.equalsIgnoreCase("VesselName")) {
										// vesselline_no=eachrowobj.getString("Line_no");
										vesselline_line_index = Integer.parseInt(eachrowobj.getString("line_index"));
									}
								}
							} else {
								break;
							}
							//System.out.println("vesselline_line_index " + vesselline_line_index);
						}
						vesseljson_count++;

					} else {
						break;
					}
				}
				
				HashMap <String , String>lableStringMap= new HashMap<String , String>();

				JSONObject objheader = new JSONObject();
				objheader.put("RepositionRegion", "");
				objheader.put("ReportType", "");
				objheader.put("CargoType", "");
				int previousvessellineindex=vesselline_line_index;	

				if(vesseljson_count==0) {
					findheaderdata(vesselline_line_index, objheader, header_dataobj, -1, "FirstCall");
					//call one method 
					if(table_dataobj.length()!=0){
					for (int i = 0; i < table_dataobj.length(); i++) {
						//System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
						JSONObject eachrowobj = table_dataobj.getJSONObject(i);
						//find difference
						vesselline_line_index=Integer.parseInt(eachrowobj.getString("line_index"));
						int difference= vesselline_line_index-previousvessellineindex;
						//System.out.println("vesselline_line_index "+vesselline_line_index);
						//System.out.println("previousvessellineindex "+previousvessellineindex);
						//System.out.println("difference "+difference);
						if(difference<0) {difference=0;}else if(difference>4) {difference=4;}
						findheaderdata(vesselline_line_index, objheader, header_dataobj, difference, "FirstCall");
						//System.out.println("objheader "+objheader);
						previousvessellineindex=Integer.parseInt(eachrowobj.getString("line_index"));
						JSONObject data = eachrowobj.getJSONObject("data");
						JSONObject header = eachrowobj.getJSONObject("header");
						//System.out.println("header " + header);
						JSONObject new_data = new JSONObject();
						JSONObject new_header = new JSONObject();
					 singleStringParsingmethod( data,  header,  new_header,objheader);
					
					 JSONObject newDataHeaderObj=singleStringParsingmethod(data, header, new_header, objheader);
						if(newDataHeaderObj.has("data")){data=newDataHeaderObj.getJSONObject("data");}
						if(newDataHeaderObj.has("header")){header=newDataHeaderObj.getJSONObject("header");}

						table_dataobj.getJSONObject(i).put("data",data);
						table_dataobj.getJSONObject(i).put("header",header);

						table_dataobj.getJSONObject(i).put("objheader",objheader);
						table_dataobj.getJSONObject(i).put("new_header_data", header);
						//System.out.println("&&&&&&&&&&&& " + table_dataobj.getJSONObject(i));

						//System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					}
				}
				}
				//System.out.println("vesseljson_count " + vesseljson_count);
				//System.out.println("successfull break");
				LinkedHashMap<String, Integer> lhmap = new LinkedHashMap<String, Integer>();
				
				if(vesseljson_count!=0){
					findheaderdata(vesselline_line_index, objheader, header_dataobj, -1, "FirstCall");
					////System.out.println("RepositionRegion "+RepositionRegion);
					////System.out.println("ReportType "+ReportType);
					////System.out.println("CargoType "+CargoType);
				
				for (int i =0; i < table_dataobj.length(); i++) {
					//System.out.println("***************************************************************************************************");

					JSONObject eachrowobj = table_dataobj.getJSONObject(i);

					
					
					//find difference
					vesselline_line_index=Integer.parseInt(eachrowobj.getString("line_index"));
					int difference= vesselline_line_index-previousvessellineindex;
					//System.out.println("vesselline_line_index "+vesselline_line_index);
					//System.out.println("previousvessellineindex "+previousvessellineindex);
					//System.out.println("difference "+difference);
					////System.out.println("RepositionRegion "+RepositionRegion);
					////System.out.println("ReportType "+ReportType);
					////System.out.println("CargoType "+CargoType);
					
					if(difference<0) {difference=0;}else if(difference>4) {difference=4;}
					
					findheaderdata(vesselline_line_index, objheader, header_dataobj, difference, "FirstCall");
					////System.out.println("RepositionRegion_1 "+RepositionRegion);
					////System.out.println("ReportType_1 "+ReportType);
					//System.out.println("objheader "+objheader);
					
					
					previousvessellineindex=Integer.parseInt(eachrowobj.getString("line_index"));
					JSONObject data = eachrowobj.getJSONObject("data");
					JSONObject header = eachrowobj.getJSONObject("header");
					//System.out.println("header " + header);
					JSONObject new_data = new JSONObject();
					JSONObject new_header = new JSONObject();
					if(data.length()<6) {
						JSONObject newDataHeaderObj=singleStringParsingmethod(data, header, new_header, objheader);
						if(newDataHeaderObj.has("data")){data=newDataHeaderObj.getJSONObject("data");}
						if(newDataHeaderObj.has("header")){header=newDataHeaderObj.getJSONObject("header");}

						table_dataobj.getJSONObject(i).put("data",data);
						table_dataobj.getJSONObject(i).put("header",header);

						table_dataobj.getJSONObject(i).put("objheader",objheader);
						table_dataobj.getJSONObject(i).put("new_header_data", header);
						//System.out.println("&&&&&&&&&&&& " + table_dataobj.getJSONObject(i));

					}else {
					Iterator itr = header.keys();
					while (itr.hasNext()) {
						String hkey = (String) itr.next();
						String hvalue = header.getString(hkey);
						String dvalue = data.getString(hkey);
						new_header.put(hkey, hvalue);
						//System.out.println("hvalue " + hvalue);
						//System.out.println("dvalue" + dvalue);

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
							//System.out.println("dvalue "+dvalue);
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
							
							//System.out.println("hkey  "+hvalue  +"synonyms "+ synonyms +"regexmatch   "+regexmatch +" solrmatch "+ solrmatch );
							String mathodname = pallavi_SortJSON.executesolrQuery_getMethodName(" ".toUpperCase(),
									synonyms.toUpperCase(), regexmatch.toUpperCase(), solrmatch.toUpperCase(),
									"METHODNAME");
							//System.out.println("mathodname unknown " + mathodname);
							allmethodexceution(data, header, new_header, hkey, hvalue, dvalue, mathodname, synonyms,
									regexmatch, regexpattern, solrmatch, lhmap);
						} else {
							String solrKeyList = fetchsolrdata(hvalue.replace(" ", "_"));
							// //System.out.println("solrKeyList "+solrKeyList);
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
										// call stanbol to get data
										if (Extrakey.equalsIgnoreCase("RepositionRegion")) {
											JSONObject Stanbolmatchobj = getUrlByStanbol(hvalue);
											// //System.out.println("Stanbolmatchobj "+Stanbolmatchobj);
											if (Stanbolmatchobj.length() > 0) {
												// method to add data in json in appropriate key

												Iterator stanbolitr = Stanbolmatchobj.keys();
												// //System.out.println(Stanbolmatchobj.length());
												while (stanbolitr.hasNext()) {
													// //System.out.println("in stanbol while");
													String stanbolkey = (String) stanbolitr.next();
													String stanbolvalue = Stanbolmatchobj.getString(stanbolkey);
													// //System.out.println("stanbolkey "+stanbolkey +"
													// stanbolvalue"+stanbolvalue);
													if (stanbolvalue.equalsIgnoreCase("RepositionRegion")) {
														String index=data.length() + "";
														data.put(index, stanbolkey);
														new_header.put(index, stanbolvalue);
														// //System.out.println("new_header ***^^^^%%%% "+new_header);
													}
												}
											}
										}
										if (Extrakey.equalsIgnoreCase("VesselType")) {
											JSONObject Stanbolmatchobj = getUrlByStanbol(hvalue);
											// //System.out.println("Stanbolmatchobj "+Stanbolmatchobj);
											if (Stanbolmatchobj.length() > 0) {
												// method to add data in json in appropriate key
												Iterator stanbolitr = Stanbolmatchobj.keys();
												// //System.out.println(Stanbolmatchobj.length());
												while (stanbolitr.hasNext()) {
													// //System.out.println("in stanbol while");
													String stanbolkey = (String) stanbolitr.next();
													String stanbolvalue = Stanbolmatchobj.getString(stanbolkey);
													// //System.out.println("stanbolkey "+stanbolkey +"
													// stanbolvalue"+stanbolvalue);
													if (stanbolvalue.equalsIgnoreCase("VesselType")) {
														String index=data.length()+"";
														data.put(index, stanbolkey);
														new_header.put(index, stanbolvalue);
														// //System.out.println("new_header ***^^^^%%%% "+new_header);
													}
												}
											}
										}
										// *********************************************************

									}  // if no Extrakey
												// 1. call regax script
												// 2.call sorl
												// 3. call new solr api
												// 4.execute method
										String synonyms = headername;
										//System.out.println("synonyms "+synonyms);
										String solrmatch = callSolrforDataMatch(dvalue);
										//System.out.println("solrmatch "+solrmatch);
										//System.out.println("dvalue "+dvalue);

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
										String newdvalue=dvalue;
										if(rematch.equals(solrmatch)) {
											newdvalue=dvalue;
										}else {
											String newhkey=Integer.toString(Integer.parseInt(hkey)-1);
											if(header.has(newhkey)) {
												String newsolrmatch = callSolrforDataMatch(data.getString(newhkey));
                                               if(rematch.equals(newsolrmatch)) {
       											newdvalue=data.getString(newhkey);
                                               }
											}
										}
										allmethodexceution(data, header, new_header, hkey, hvalue, newdvalue, mathodname,
												synonyms, regexmatch, regexpattern, solrmatch, lhmap);
									}else{
										allmethodexceution(data, header, new_header, hkey, hvalue, dvalue, mathodname,
												synonyms, regexmatch, regexpattern, solrmatch, lhmap);
									}
								} else {
									// what if header do not match
									// 1. call regax script
									// 2.call sorl
									// 3. call new solr api
									// 4.execute method

									String synonyms = " ";
									//System.out.println("synonyms "+synonyms);
									String solrmatch = callSolrforDataMatch(dvalue);
									//System.out.println("solrmatch "+solrmatch);
									String regexmatch = " ";
									String regexpattern = "";
									//System.out.println("dvalue "+dvalue);
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
									allmethodexceution(data, header, new_header, hkey, hvalue, dvalue, mathodname,
											synonyms, regexmatch, regexpattern, solrmatch, lhmap);
								}
							}
						}
					}
					table_dataobj.getJSONObject(i).put("objheader",objheader);
					table_dataobj.getJSONObject(i).put("new_header_data", new_header);
					//System.out.println("&&&&&&&&&&&& " + table_dataobj.getJSONObject(i));
					//System.out.println("***************************************************************************************************");

					}
//					if(!RepositionRegion.equals("")) {table_dataobj.getJSONObject(i).put("RepositionRegion",RepositionRegion);}
//					if(!ReportType.equals("")) {table_dataobj.getJSONObject(i).put("ReportType",ReportType);}
//					if(!CargoType.equals("")) {table_dataobj.getJSONObject(i).put("CargoType",CargoType);}
				}}
				// call method to get averagerate position from lhmap

				Tableobj.put("Rate_Position", FindAverageRatePosition(lhmap));
			}

			////System.out.println("obj " + obj);
			pallavi_WriteFile.writeUsingOutputStream( "SCRIPTJSON ::====== "+pythonScriptApiData.toString() +
					"\n OUTPUTJSON::====== "+obj .toString() , 
					filepath);

			return obj.toString();
		} catch (Exception e) {
			pallavi_WriteFile.writeUsingOutputStream( "SCRIPTJSON ::====== "+pythonScriptApiData.toString() +
					"\n OUTPUTJSON::====== "+obj .toString() +"\n error"+ e.getMessage(), 
					filepath);
			e.printStackTrace();
			out.println("error  "+e.getMessage()+"\n obj  "+obj);
			return obj.toString();
		}
	}

	public static String FindAverageRatePosition(LinkedHashMap<String, Integer> lhmap) {
		String rateposition = "";
		int maxrate = 0;
		if (lhmap.size() > 0) {
			for (String key : lhmap.keySet()) {
				int value = lhmap.get(key);
				if (value > maxrate) {
					maxrate = value;
					rateposition = key;
				}
			}
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
             
			solrres = pallavi_SortJSON.executesolrQuery_bywords(dvalue.toUpperCase().replace(" ", "_"));
			boolean checkjsonString = SaveReportDataClass.isJSONValid(solrres);
			if (checkjsonString) {
				DocumentName = pallavi_SortJSON.parsesolrresp(solrres, "DocumentName");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DocumentName;
	}

	public static void allmethodexceution(JSONObject data, JSONObject header, JSONObject new_header, String hkey,
			String hvalue, String dvalue, String mathodname, String synonyms, String regexmatch, String regexpattern,
			String solrmatch, LinkedHashMap<String, Integer> lhmap) {
		try {
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
					data.put(hkey, words[0]);
					new_header.put(hkey, "LoadPort");
					if (words.length > 1) {
						String index=data.length()+"";
						data.put(index, words[0]);
						new_header.put(index, "DiscPort");
					}
				} else {
					data.put(hkey, words[0]);
					new_header.put(hkey, "LoadPort");
				}
			} else if (mathodname.equalsIgnoreCase("method2")) {
				data.put(hkey, dvalue);
				new_header.put(hkey, "OpenPort");
			} else if (mathodname.equalsIgnoreCase("method3")) {
				// call method to convert date in standard form
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
				String date = new pallavi_ConvertDatewithPattern().convertDatewithPattern(dvalue, regexpattern);
				data.put(hkey, date);
				new_header.put(hkey, "OpenDate");

			} else if (mathodname.equalsIgnoreCase("method4")) {
				String[] words = dvalue.split(" ");
				String word = "";
				for (int i = 0; i < words.length - 1; i++) {
					word = word + " " + words[i];
					////System.out.println( "word "+word);
				}
				data.put(hkey, word);
				new_header.put(hkey, "OpenPort");

				if (words.length > 0) {
					String date = words[words.length - 1];
					//.out.println("date  "+date);
					// call regex method to get date pattern
					// convert date into standard format
					// data.put(data.length()+"", date);
					JSONObject regexmatchresult = callregex(date);
					//System.out.println("regexmatchresult "+regexmatchresult);
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
					String index=data.length()+"";
					data.put(index, date_updared);
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
						String index=data.length() + "";
						data.put(index, Date2);
						new_header.put(index, "LCEnd");
					}else if(datearr.length ==1) {
						Date1 = datearr[0];
						data.put(hkey, Date1);
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
						//System.out.println("DocumentName  " + DocumentName);
						if (DocumentName.equals("Port")) {
							portcount++;
						}
					}
				}
				if (portcount > 0) {
					data.put(hkey, words[0]);
					new_header.put(hkey, "Port");
					if (words.length > 1) {
						String index= data.length() + "";
						data.put(index, words[0]);
						new_header.put(index, "Port");
					}
				} else {
					data.put(hkey, dvalue);
					new_header.put(hkey, "Port");
				}

			} else if (mathodname.equalsIgnoreCase("method7")) {
				String[] words = dvalue.split(" ");
				String word = "";
				for (int i = 0; i < words.length - 1; i++) {
					word = word + " " + words[i];
				}
				data.put(hkey, word);
				new_header.put(hkey, "Port");

				if (words.length > 0) {
					String date = words[words.length - 1];
					// all regex method to get date pattern
					// convert date into standard format
					// data.put(data.length()+"", date);
					// new_header.put(data.length()+"", "Date");
					
					
					JSONObject regexmatchresult = callregex(date);
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
					String index=data.length() + "";
					data.put(index, date_updared);
					new_header.put(index, "Date");

				}

			} else if (mathodname.equalsIgnoreCase("method8")) {
				// call method to get two dated saperated by &
				data.put(hkey, "Date1");
				new_header.put(hkey, "Date");
				data.put(data.length() + "", "Date2");
				new_header.put(data.length() + "", "Date");
			} else if (mathodname.equalsIgnoreCase("method9")) {
				if (lhmap.containsKey(hkey)) {
					int count = (int) lhmap.get(hkey);
					lhmap.put(hkey, count + 1);
				} else {
					lhmap.put(hkey, 1);
				}
				data.put(hkey, dvalue);
				new_header.put(hkey, "Rate");

			} else if (mathodname.equalsIgnoreCase("Blank")) {
				//System.out.println("in blank method");
				JSONObject regexmatchresult = callregex(dvalue.toUpperCase());
				//System.out.println("regexmatchresult "+regexmatchresult);
				boolean checkjsonString = SaveReportDataClass.isJSONValid(regexmatchresult.toString());
				//System.out.println("checkjsonString "+checkjsonString);
				if (checkjsonString) {
					
					if (regexmatchresult.has("Format")) {
						regexpattern = regexmatchresult.getString("Format");
					}
					if (regexmatchresult.has("Regex_Type")) {
						regexmatch = regexmatchresult.getString("Regex_Type");
					}
				}
				//System.out.println(regexpattern  +" "+regexmatch);
				String date="";
				if(synonyms.equalsIgnoreCase("Date") || solrmatch.equalsIgnoreCase("Date") || regexmatch.equalsIgnoreCase("date") || regexmatch.equalsIgnoreCase("date/date")) {
				if(!regexpattern.equals("")) {
					date = new pallavi_ConvertDatewithPattern().convertDatewithPattern(dvalue, regexpattern);
				}else {
					date=new pallavi_ConvertDate().convertDateFormat( dvalue);
				}
				}
				//System.out.println(date);
				if (!synonyms.equalsIgnoreCase(" ")) {
					if(regexmatch.equalsIgnoreCase("date") || regexmatch.equalsIgnoreCase("date/date")) {
					data.put(hkey, date);
					}
					new_header.put(hkey, synonyms);
				} else if (!solrmatch.equalsIgnoreCase(" ")) {
					if(regexmatch.equalsIgnoreCase("date")) {
					data.put(hkey, dvalue);
					}else if(solrmatch.equalsIgnoreCase(date)){
						String date1 = new pallavi_ConvertDatewithPattern().convertDatewithPattern(dvalue, regexpattern);

						data.put(hkey, date1);
					}else {
						data.put(hkey, dvalue);

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
						//System.out.println( "word "+word);
					}
					data.put(hkey, word);
					new_header.put(hkey, "Port");

					if (words.length > 0) {
						 date = words[words.length - 1];
						//System.out.println("date  "+date);
						// call regex method to get date pattern
						// convert date into standard format
						// data.put(data.length()+"", date);
						 regexmatchresult = callregex(date.trim());
						//System.out.println("regexmatchresult "+regexmatchresult);
						boolean checkjsonStringw_d = SaveReportDataClass.isJSONValid(regexmatchresult.toString());
						if (checkjsonStringw_d) {
							if (regexmatchresult.has("Format")) {
								regexpattern = regexmatchresult.getString("Format");
							}
							if (regexmatchresult.has("Regex_Type")) {
								regexmatch = regexmatchresult.getString("Regex_Type");
							}
						}
						//System.out.println("date_w_d "+date+" regexpattern "+regexpattern+" "+regexmatch);
						String date_updared = new pallavi_ConvertDatewithPattern().convertDatewithPattern(date,
								regexpattern);
						String index=data.length()+"";
						data.put(index, date_updared);
						new_header.put(index, "Date");
					}
				
			    }else if(regexmatch.equalsIgnoreCase("Word/Word")) {
			    	String[] words = dvalue.split("/");
					int portcount = 0;
					for (int i = 0; i < words.length; i++) {
						//System.out.println("words[i]  "+words[i]);
						String solrres = pallavi_SortJSON
								.executesolrQuery_bywords(words[i].toUpperCase().replace(" ", "_"));
						 checkjsonString = SaveReportDataClass.isJSONValid(solrres);
						if (checkjsonString) {
							// parsejson from solr //get DocumentName
							String DocumentName = pallavi_SortJSON.parsesolrresp(solrres, "DocumentName");
							//System.out.println("DocumentName  " + DocumentName);
							if (DocumentName.equals("Port")) {
								portcount++;
							}else if(DocumentName.equals("RepositionRegion")) {
								portcount++;
							}
							
						}
					}
					//System.out.println("portcount  "+portcount);
					if (portcount > 0) {
						data.put(hkey, words[0]);
						new_header.put(hkey, "Port");
						if (words.length > 1) {
							String index= data.length() + "";
							data.put(index, words[1]);
							new_header.put(index, "Port");
						}
					} else {
						data.put(hkey, dvalue);
						new_header.put(hkey, "Port");
					}
			    	
			    } if(hvalue.equalsIgnoreCase("open") || hvalue.equalsIgnoreCase("EXPORT/DATE") || 
			    		hvalue.equalsIgnoreCase("EXPORT/PORT")|| hvalue.equalsIgnoreCase("OPEN_PORT")){
					//send data to stanbol
					dvalue=dvalue.toUpperCase();
					  //System.out.println("dvalue* "+dvalue);
						 JSONObject Stanbolmatchobj= getUrlByStanbol(dvalue);
						 //System.out.println("Stanbolmatchobj "+Stanbolmatchobj);
			      if(Stanbolmatchobj.length()>0 ) {
			    	  // method to add data in json in appropriate key 
			    	  Iterator stanbolitr=Stanbolmatchobj.keys();
			    	  //System.out.println(Stanbolmatchobj.length());
			    	  String ports="";
			    	  while(stanbolitr.hasNext()) {
			    		  String stabbolkey=(String) stanbolitr.next();
			    		  String Stranbolvalue= Stanbolmatchobj.getString(stabbolkey);
			    		  if(Stranbolvalue.equalsIgnoreCase("port")) {
			    			  ports=ports+""+stabbolkey;
			    		  }else {
			    			  String index=data.length()+"";
			    		  data.put(index, stabbolkey);
			    		  new_header.put(index, Stranbolvalue);
			    		  }
			  			////System.out.println("stabbolkey  **  "+stabbolkey +" Stranbolvalue "+Stranbolvalue);
			    		  //System.out.println("dvalue ** "+dvalue);
			    		  //System.out.println("ports "+ports);
			    		if(dvalue.contains(stabbolkey.toUpperCase())) {
			    			dvalue=dvalue.replace(stabbolkey.toUpperCase(), "");
			    		}
			    	  }
			    	  String index=data.length()+"";
		    		  data.put(index, ports);
		    		  new_header.put(index, "OpenPort");
			    	  }
			      //System.out.println("dvalue % "+dvalue);
			      // send dvalue to regex to check if it is data or not
					JSONObject regexmatchresult_2 = callregex(dvalue.toUpperCase().trim());
					//System.out.println("regexmatchresult "+regexmatchresult);
					boolean checkjsonString_2 = SaveReportDataClass.isJSONValid(regexmatchresult.toString());
					//System.out.println("checkjsonString "+checkjsonString);
					if (checkjsonString_2) {
						if (regexmatchresult.has("Format")) {
							regexpattern = regexmatchresult.getString("Format");
						}
						if (regexmatchresult.has("Regex_Type")) {
							regexmatch = regexmatchresult.getString("Regex_Type");
						}
					}
					//System.out.println(regexpattern  +" "+regexmatch);
					String date_2="";
					if( regexmatch.equalsIgnoreCase("date")) {
					if(!regexpattern.equals("")) {
						date = new pallavi_ConvertDatewithPattern().convertDatewithPattern(dvalue, regexpattern);
						String index=data.length()+"";
						data.put(index, data);
			    		  new_header.put(index, "OpenDate");
					}}else {	
						if(dvalue.contains(" ")) {
						dvalue= dvalue.replace(" ", "");
						}
						if(!dvalue.equals("")){
						data.put(hkey, dvalue);
			    		  new_header.put(hkey, hvalue);
					}}
				}else {
					//do nothing
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String fetchsolrdata(String q) {

		// String f1 = "*,score";
		StringBuffer response1 = null;
		// //System.out.println("q: "+q);
		try {
			// q = q.replace(" ", "%20");
			q = URLEncoder.encode(q, "UTF-8");

			String url = "";

			// url = "http://35.188.227.168:8983/solr/TemplateCore/select?q=" + q
			// + "&fl=" + f1 + "&df=" + df;

			// url = "http://35.188.227.168:8983/solr/Header_Synomes/select?q=" +
			// q + "&fl=" + f1;
			// url =
			// "http://35.188.227.168:8983/solr/Header_Synomes/select?q=Data:" +
			// q + "&fl=" + "HeaderName";
			url = "http://35.188.227.168:8983/solr/Header_Synomes/select?q=Data_str:" + q
					+ "&fl=score,HeaderName,Extrakey";
			// url =
			// "http://35.188.227.168:8983/solr/Header_Synomes/select?q=Data:("+q+")&fl=HeaderName";

			// url = url.replace(" ", "%20");

			// //System.out.println("url " + url);
			URL url1 = new URL(url);
			HttpURLConnection con = (HttpURLConnection) url1.openConnection();
			// int responseCode = con.getResponseCode();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			response1 = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response1.append(inputLine);
			}
			// //System.out.println(response1);
			in.close();

		} catch (Exception e) {
			// e.printStackTrace();
			// //System.out.println(e.getMessage());
			return "";
		}
		return response1.toString();

	}

	public static JSONObject getUrlByStanbol(String adata) {
		JSONObject urlnadvalue = new JSONObject();

		try {
			adata = adata.toLowerCase();
			//System.out.println("adata " + adata);
			if (!adata.isEmpty()) {
				String stanboljson = stanbolBreakResponse(adata);
				// //System.out.println("stanboljson "+stanboljson);
				JSONArray arrobj = new JSONArray(stanboljson);
				// //System.out.println(arrobj.length());

				for (int i = 0; i < arrobj.length(); i++) {
					JSONObject obj = arrobj.getJSONObject(i);
					String entity_ref_url = null;
					String entity_vlaue = null;
					;
					String entity_type = null;

					if (obj.has("http://fise.iks-project.eu/ontology/entity-reference")) {
						JSONArray entityref = obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-reference");
						entity_ref_url = entityref.getJSONObject(0).getString("@id");

						// //System.out.println(entity_ref_url);
					}
					if (obj.has("http://fise.iks-project.eu/ontology/entity-label")) {
						JSONArray entityrlabel = obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-label");
						entity_vlaue = entityrlabel.getJSONObject(0).getString("@value");
						// //System.out.println(entity_vlaue);
					}
					if (obj.has("http://fise.iks-project.eu/ontology/entity-type")) {
						JSONArray entityrtype = obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-type");
						entity_type = entityrtype.getJSONObject(0).getString("@id");
						// //System.out.println(entity_type);
						if (entity_type.contains("#")) {
							entity_type = entity_type.substring(entity_type.lastIndexOf("#") + 1);
						}
					}
					if ((entity_vlaue != null)) {
						urlnadvalue.put(entity_vlaue, entity_type);
						// subfinaljson.put(entity_type, entity_vlaue);
						// if (adata.contains(entity_vlaue.toLowerCase())) {
						// //System.out.println("contains");
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

			URL url = new URL("http://35.188.227.39:8080/enhancer/chain/scorpiosvchain");
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
			// //System.out.println("outputStream"+outputStream.toString());
			outputStream.flush();
			outputStream.close();
			isr.close();
			int responseCode = httpConn.getResponseCode();
			// //System.out.println("responseCode " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) {

				reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
				String response = reader.readLine();

				String line1;
				StringBuffer sb = new StringBuffer();
				sb.append("[{");
				while ((line1 = reader.readLine()) != null) {
					sb.append(line1);
				}
				// //System.out.println(sb.toString());
				res = sb.toString();
			}
			// //System.out.println(res);

		} catch (Exception e) {
			//System.out.println(e.getMessage());
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
			dvalue=dvalue.toUpperCase();
		}
		try {
			// String url1 =
			// "http://35.186.170.65:8983/solr/Vessel/update/json/docs?commit=true";
			// String url1 =
			// "http://35.186.170.65:8983/solr/HSN_Code/update/json/docs?commit=true";
			// String url1 =
			// "http://35.188.227.168:8983/solr/PO_FlatteningTemplate/update/json/docs?commit=true";
			String url1 = "http://35.221.160.146:5032/date";

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
				JSONArray arr = new JSONArray(response.toString());
				//System.out.println(arr);
				int count = 0;
				int jsonno = 0;
				for (int i = 0; i < arr.length(); i++) {
					JSONObject obj = arr.getJSONObject(i);
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
					JSONObject obj = arr.getJSONObject(jsonno);
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
			JSONArray header_dataobj , int difference ,String type) {
	
	//int difference=0;
		
//		String RepositionRegion;
//		String ReportType;
//		String CargoType;
	try {
		for (int i=0;i<header_dataobj.length(); i++){
			JSONObject sub_header_dataobj = header_dataobj.getJSONObject(i);
			
			int line_index=Integer.parseInt(sub_header_dataobj.getString("line_index"));
			//int vesselline_line_index_int= Integer.parseInt(vesselline_line_index_currentline);
			int vesselline_line_index_int= vesselline_line_index_currentline;


			if(difference==-1) {
int oneless =vesselline_line_index_int-1;
int twoless =vesselline_line_index_int-2;
int threeless =vesselline_line_index_int-3;

if((line_index< oneless) || (line_index< twoless) || (line_index < threeless)) {
	
	JSONArray arraydata= sub_header_dataobj.getJSONArray("data");
	for(int j=0; j<arraydata.length(); j++) {
		JSONObject subdata= arraydata.getJSONObject(j);
		String url =subdata.getString("url");
		String word= subdata.getString("word");
		String key ="";
		if(url.contains("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018")) {
			key=url.substring(url.indexOf("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018")+"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018".length()+1);
			//System.out.println("key*******   "+key);

				////System.out.println("word "+word);
			        if(key.equalsIgnoreCase("ReportType")) {
						if(!word.equalsIgnoreCase("")) {
							word=word.toUpperCase().replaceAll(" ", "_");
					////System.out.println("word *   "+word);
			        	String resp=getreporttypebysolr(word);
						boolean checkjsonString = SaveReportDataClass.isJSONValid(resp);
						////System.out.println("resp  "+resp);
						if (checkjsonString) {
							// parsejson from solr //get DocumentName
							String HeaderName = pallavi_SortJSON.parsesolrresp(resp, "HeaderName");
							////System.out.println("HeaderName "+HeaderName);
							if(!HeaderName.equalsIgnoreCase("")) {
								//ReportType=HeaderName;
								objheader.put("ReportType", HeaderName);
								////System.out.println("objheader  "+objheader);
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
////System.out.println("difference* :: i :: "+ i + " :: "+difference);
			for(int k=difference-1;k>0; k--) {
				////System.out.println("k* "+k);
				int lineless=vesselline_line_index_int-k;
				////System.out.println("vesselline_line_index_int*  "+vesselline_line_index_int);

				////System.out.println("lineless*  "+lineless);
				if((line_index==lineless)) {
////System.out.println("inside");
				JSONArray arraydata= sub_header_dataobj.getJSONArray("data");
				for(int j=0; j<arraydata.length(); j++) {
					JSONObject subdata= arraydata.getJSONObject(j);
					String url =subdata.getString("url");
					String word= subdata.getString("word");
					String key ="";
					
					if(url.contains("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018")) {
						key=url.substring(url.indexOf("http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018")+"http://www.semanticweb.org/user/ontologies/2018/10/scorpio-12-11-2018".length()+1);
						//System.out.println("key*******   "+key);
					    if(key.equalsIgnoreCase("ReportType")) {
							if(!word.equalsIgnoreCase("")) {
								word=word.toUpperCase().replaceAll(" ", "_");
						////System.out.println("word *   "+word);
				        	String resp=getreporttypebysolr(word);
							boolean checkjsonString = SaveReportDataClass.isJSONValid(resp);
							//System.out.println("resp  "+resp);
							if (checkjsonString) {
								// parsejson from solr //get DocumentName
								String HeaderName = pallavi_SortJSON.parsesolrresp(resp, "HeaderName");
								////System.out.println("HeaderName "+HeaderName);
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
		////System.out.println(passvalue);

		passvalue = URLEncoder.encode(passvalue, "UTF-8");

		String url = "";
		url = "http://35.188.227.168:8983/solr/ReportType_Synomes/select?q=Data_str:" + passvalue + "&fl=score,HeaderName";

		URL url1 = new URL(url);
		//System.out.println("url1 "+url1);
		HttpURLConnection con = (HttpURLConnection) url1.openConnection();

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		response1 = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
		response1.append(inputLine);
		}
		////System.out.println(response1);
		in.close();

		} catch (Exception e) {
e.printStackTrace();
		}
		return response1.toString();
		}
	
	public static String getSynonymmatch(String synonym,String DataType){
		StringBuffer response1=null;
		try {
		//passvalue=passvalue.toUpperCase();
		//passvalue=passvalue.replaceAll(" ", "_");
		////System.out.println(passvalue);
			synonym = URLEncoder.encode(synonym, "UTF-8");
		String url = "";
		url="http://35.188.227.168:8983/solr/HeaderAndDataValidation/select?fl=score,DataType&q=Headersynonyms:"+synonym;
		//url = "http://35.188.227.168:8983/solr/ReportType_Synomes/select?q=Data_str:" + passvalue + "&fl=score,HeaderName";
		URL url1 = new URL(url);
		HttpURLConnection con = (HttpURLConnection) url1.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		response1 = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
		response1.append(inputLine);
		}
		////System.out.println(response1);
		in.close();
		 JSONObject sorrespobj = new JSONObject(response1.toString());
      if(sorrespobj.has("response") && sorrespobj.getJSONObject("response").has("maxScore") && sorrespobj.getJSONObject("response").has("docs")) {
    // //System.out.println("in parse json ********");
      JSONObject Subsorrespobj=sorrespobj.getJSONObject("response");
      JSONArray docs=Subsorrespobj.getJSONArray("docs");
      double maxscore=Subsorrespobj.getDouble("maxScore");
    //  //System.out.println("maxscore "+maxscore);
    for(int i=0;i<docs.length();i++) {
     JSONObject subdocs=docs.getJSONObject(i);
     double score=subdocs.getDouble("score");
     if(score==maxscore) {
     	////System.out.println("maxscore "+maxscore+" score   "+score);
   	   DataType=subdocs.getJSONArray(DataType).getString(0);
     }}}	
		return DataType;
		} catch (Exception e) {
return DataType;
		}}
	
	 public static JSONObject singleStringParsingmethod(JSONObject data, JSONObject header, JSONObject new_header, JSONObject objheader){
			String alldata="";
			JSONObject returnobj=null;
			JSONObject stanboljson=null;
			JSONArray stanbolarr=null;
			JSONObject dateratejson=null;
			JSONArray dateratearr=null;
			JSONObject solrjson=null;
			JSONArray solrarr=null;

			String alldata_return="";
			TreeMap<Integer, JSONObject> indexwisemap=null;  
			try {
				returnobj= new JSONObject();
		if(data.length()<6) {
			Iterator ditr= data.keys();  while (ditr.hasNext()) {alldata=alldata+" "+data.getString((String) ditr.next());}
			alldata=alldata.toUpperCase();
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
		//System.out.println("alldata "+alldata);
		//System.out.println("alldata_return  "+alldata_return);
		//System.out.println("stanbolarr  "+stanbolarr);
		//System.out.println("dateratearr "+dateratearr);
		//System.out.println("solrarr "+solrarr);

		//call method to send data one by one in stanbol and try to match
		
		
       // now create hashtree and create sequance according to index
		indexwisemap= new TreeMap<Integer, JSONObject>();
		if(stanbolarr!=null)
		for(int i=0;i<stanbolarr.length();i++) {
			JSONObject obj =stanbolarr.getJSONObject(i);
			int Index=obj.getInt("Index");
			int Index2=obj.getInt("Index2");
			int Index3=obj.getInt("Index3");
			if(Index!=-1) {indexwisemap.put(Index, obj);}
			if(Index2!=-1) {indexwisemap.put(Index2, obj);}
			if(Index3!=-1) {indexwisemap.put(Index3, obj);}
		}}
		if(dateratearr!=null) {
		for(int i=0;i<dateratearr.length();i++) {
			JSONObject obj =dateratearr.getJSONObject(i);
			int Index=obj.getInt("Index");
			int Index2=obj.getInt("Index2");
			int Index3=obj.getInt("Index3");
			if(Index!=-1) {indexwisemap.put(Index, obj);}
			if(Index2!=-1) {indexwisemap.put(Index2, obj);}
			if(Index3!=-1) {indexwisemap.put(Index3, obj);}
		}}
			if(solrarr!=null) {
		for(int i=0;i<solrarr.length();i++) {
			JSONObject obj =solrarr.getJSONObject(i);
			int Index=obj.getInt("Index");
			int Index2=obj.getInt("Index2");
			int Index3=obj.getInt("Index3");

			if(Index!=-1) {indexwisemap.put(Index, obj);}
			if(Index2!=-1) {indexwisemap.put(Index2, obj);}
			if(Index3!=-1) {indexwisemap.put(Index3, obj);}
		}}
		
		
		//System.out.println(indexwisemap);
		data=new JSONObject();
		header=new JSONObject();
		int count=0;
        for (Map.Entry< Integer,JSONObject> entry : indexwisemap.entrySet()) { 
            JSONObject valuejson=entry.getValue();
            //System.out.println("valuejson "+valuejson);
        	String key=valuejson.getString("key");
            data.put(count+"", valuejson.getString("value"));
            if(key.equalsIgnoreCase("RepositionRegion")) {
                header.put(count+"", "Port");
            }else {
            header.put(count+"", key);
            }
            count++;
           } 
        String index=data.length()+"";
        data.put(index, alldata_return);
        header.put(index, "unknown_"+index);

//System.out.println("data "+data);
//System.out.println("header "+header);
returnobj.put("data", data);
returnobj.put("header", header);

		
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
	try {
		arr = new JSONArray();
		rtnobj=new JSONObject();
		if(!alldata.isEmpty()) {
			alldata=alldata.toUpperCase();
    			String stanboljson =stanbolBreakResponse(alldata);
    			////System.out.println("stanboljson "+stanboljson);
    			JSONArray  arrobj= new JSONArray(stanboljson);
    			////System.out.println(arrobj.length());
    			
    			for(int i=0; i<arrobj.length(); i++) {
    				////System.out.println("**** "+i);
    				JSONObject obj= arrobj.getJSONObject(i);
    				String entity_ref_url=null;
    				String entity_vlaue=null;;
    				String entity_type=null;

    				if(obj.has("http://fise.iks-project.eu/ontology/entity-reference")) {
    					JSONArray  entityref=obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-reference");
    					entity_ref_url=entityref.getJSONObject(0).getString("@id");
    					
    					////System.out.println("1 "+entity_ref_url);
    				}
    				if(obj.has("http://fise.iks-project.eu/ontology/entity-label")) {
    					JSONArray  entityrlabel=obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-label");
    					entity_vlaue=entityrlabel.getJSONObject(0).getString("@value");
    					////System.out.println("2 "+entity_vlaue);
    				}
    				if(obj.has("http://fise.iks-project.eu/ontology/entity-type")) {
    					JSONArray  entityrtype=obj.getJSONArray("http://fise.iks-project.eu/ontology/entity-type");
    					entity_type=entityrtype.getJSONObject(0).getString("@id");
    					if(entity_type.contains("#")) {
    						entity_type=entity_type.substring(entity_type.lastIndexOf("#")+1);
    					}
    					////System.out.println(" "+entity_type);

    				}
    				 urlnadvalue= new JSONObject();
    				if((entity_vlaue!=null) ) {
        				urlnadvalue.put("key", entity_type);
        				urlnadvalue.put("value", entity_vlaue);
    				//urlnadvalue.put(entity_vlaue, entity_type);
    				int index=ordinalIndexOf( alldata,  entity_vlaue.toUpperCase(), 0); 
    				int index2= ordinalIndexOf( alldata,  entity_vlaue.toUpperCase(), 1); 
    				int index3= ordinalIndexOf( alldata,  entity_vlaue.toUpperCase(), 2); 

    				
    				urlnadvalue.put("Index", index);
    				urlnadvalue.put("Index2", index2);
    				urlnadvalue.put("Index3", index3);

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
			try {
				rtnobj=new JSONObject();
				datearr= new JSONArray();
				ratearr= new JSONArray();
				alldata=alldata.toUpperCase();
				alldata_return =alldata_return.toUpperCase();
				String url1 = "http://35.221.160.146:5030/date";
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
					JSONArray arr = new JSONArray(response.toString());
					////System.out.println(arr);
					int count = 0;
					int jsonno = 0;
					JSONArray valuearr=null;
					for (int i = 0; i < arr.length(); i++) {
						JSONObject obj = arr.getJSONObject(i);
						subobj= new JSONObject();
						String Regex_Type=obj.getString("Regex_Type");


						if (obj.has("value")) {
							valuearr = obj.getJSONArray("value");
							String maxvalue="";
						for(int j=0;j <valuearr.length(); j++) {
							String val=valuearr.getString(j);
							if(maxvalue.length()<val.length()) {
								maxvalue=val;
							}
						}
						////System.out.println("maxvalue "+maxvalue);
						if(alldata_return.contains(maxvalue)) {
							alldata_return=alldata_return.replace(maxvalue, " ");
						}
	    				int index=ordinalIndexOf( alldata,  maxvalue.toUpperCase(), 0); 
	    				int index2= ordinalIndexOf( alldata,  maxvalue.toUpperCase(), 1);
	    				int index3= ordinalIndexOf( alldata,  maxvalue.toUpperCase(), 2);

	    				subobj.put("Index", index);
	    				subobj.put("Index2", index2);
	    				subobj.put("Index3", index3);


						if(Regex_Type.equalsIgnoreCase("Date") || Regex_Type.equalsIgnoreCase("Date/Date")) {
						String regexpattern=obj.getString("Format");
						////System.out.println("regexpattern "+regexpattern);
						String formateddate = new pallavi_ConvertDatewithPattern().convertDatewithPattern(maxvalue, regexpattern);
						subobj.put("key", Regex_Type);
						subobj.put("value", formateddate);

						}else if(Regex_Type.equalsIgnoreCase("Rate")) {
							//subobj.put(maxvalue, Regex_Type);
							subobj.put("key", Regex_Type);
							subobj.put("value", maxvalue);

					     }	
                        datearr.put(subobj);
						}
					}
				}
				rtnobj.put("datearr", datearr);
				rtnobj.put("alldata_return", alldata_return);

				////System.out.println("alldata "+alldata );
				////System.out.println("alldata_return "+alldata_return);
                // //System.out.println("datearr  "+datearr);
			} catch (Exception e) {
				e.printStackTrace();
				// out.println(e.getMessage());
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
			//System.out.println(" $$$$$$ "+words[i]);
			if(!words[i].equals(" ") && !words[i].equals("") ) {
		  String DocumentName=callSolrforDataMatch( words[i]) ;
         if(!DocumentName.equals(" ")) {
        	subobj.put(words[i], DocumentName);
			int index=ordinalIndexOf( alldata,  words[i].toUpperCase(), 0); 
			int index2= ordinalIndexOf( alldata,  words[i].toUpperCase(), 1); 
			int index3= ordinalIndexOf( alldata,  words[i].toUpperCase(), 2); 
			subobj.put("Index", index);
			subobj.put("Index2", index2);
			subobj.put("Index3", index3);

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
	}
		 
		 return solrobj;	 
	 }
//public static String labelrecognize() {
//	
//}
}