package com.mongocode;

import java.io.PrintWriter;
import java.util.Set;

import javax.jcr.Session;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.readGmail.GmailMethods;
import com.readGmail.Gmail_Pojo;

import ChangedStructureCurrent.GmailReadMailChanged;

public class ReadSubjectWiseMail {

	public static void main(String[] args) {

	}

	 public static void multipleCollectionReadData( PrintWriter out, String Dbname, Session session, Gmail_Pojo gmail_pj) {
		 MongoClient mongoClient=null;
		 try{
		 mongoClient = new MongoClient("localhost", 27017);
         DB mongoDataBase = mongoClient.getDB(Dbname);
		 
		   Set<String> collectionNames = mongoDataBase.getCollectionNames();
		    for ( String name : collectionNames) {
		    	out.println("name:: "+name);
		    	getReadMailCollectionData(out, mongoDataBase, name, session, gmail_pj);
		    }
		    
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally{
	    	   mongoClient.close();
	       }
		}
	
 public static void getReadMailCollectionData(PrintWriter out , DB mongoDataBase ,String collectionname , Session session, Gmail_Pojo gmail_pj){
		 DBCollection collection=null;
	   	 String Subject="";
		 try {
	         collection = mongoDataBase.getCollection(collectionname);
	         
	         DBCursor collection_cursor = collection.find();
	         while(collection_cursor.hasNext()){
	        	 DBObject db_obj=collection_cursor.next();
	        	 Subject= db_obj.get("Subject").toString();
	        			 
	        			 if( !GmailMethods.isNullString(Subject) ){
	        				  out.println("Subject: "+Subject);
	        				  GmailReadMailChanged.processEmail(session, gmail_pj, out, Subject, mongoDataBase , "");
	        				  
	        			 }
	        	 } 
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		 finally{
	    	   Subject=null;
	       }
		 
	 }
	
}
