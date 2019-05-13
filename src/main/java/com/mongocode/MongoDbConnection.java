package com.mongocode;

import java.io.PrintWriter;
import java.util.Set;

import javax.jcr.Session;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoIterable;
import com.readGmail.GmailMethods;
import com.readGmail.Gmail_Pojo;

import ChangedStructureCurrent.GmailReadMailChanged;

public class MongoDbConnection {

	
	public static void main(String[] args) {
		MongoDbConnection MDC=new MongoDbConnection();
	//	MDC.getMongoDbAnyConn("ReadmailDataBase", "ReadMailCollection", "24-04-2019", "0", "data ukc position list");
		//MDC.getReadMailCollectionData(null, "ReadmailDataBase", "ReadMailCollection", null, null);
		saveGmailReadCount("GmailReadCountDataBase", "23-04-2019", "5");
	}
	
	 public DBCollection getMongoDbAnyConn( String Dbname ,String collectionname, String Date, String Flag, String Subject, String error) {
		 
   	   DBCollection collection=null;
   	   MongoClient mongoClient=null;
       try {
    	   System.out.println("inmongodb inside");
            mongoClient = new MongoClient("localhost", 27017);
            DB mongoDataBase = mongoClient.getDB(Dbname);
            collection = mongoDataBase.getCollection(collectionname);
            
            BasicDBObject obj = new BasicDBObject();
			 
			 obj.put("Date", Date);
			 obj.put("Flag", Flag);
			 obj.put("Subject", Subject);
			 obj.put("Error", error);
			 collection.insert(obj);

       } catch (Exception e) {
           e.printStackTrace();
       } finally{
    	   mongoClient.close();
    	   System.out.println("insertCatchReadMailData Method Executed Successfully!");
       }
       return collection;

   }   
	 
	 public void getReadMailCollectionData(PrintWriter out , String Dbname ,String collectionname , Session session, Gmail_Pojo gmail_pj){
		 
		 DBCollection collection=null;
	   	 MongoClient mongoClient=null;
	   	 
	   	 String Flag="";
	   	 String Subject="";
	   	 String mongoId="";
	   	 
		 try {
		   	 
			 
		   	 mongoClient = new MongoClient("localhost", 27017);
	         DB mongoDataBase = mongoClient.getDB(Dbname);
	         collection = mongoDataBase.getCollection(collectionname);
	         
	         DBCursor collection_cursor = collection.find();
	         while(collection_cursor.hasNext()){
	        	 DBObject db_obj=collection_cursor.next();
	        	 Flag= db_obj.get("Flag").toString();
	        	 Subject= db_obj.get("Subject").toString();
	        	 mongoId= db_obj.get("_id").toString();
	        	 
	        	 if( !GmailMethods.isNullString(Flag) ){
	        		 if( Flag.equalsIgnoreCase("0") ){
	        			 // first time will set 0 
	        			 
	        			 if( !GmailMethods.isNullString(Subject) ){
	        				  System.out.println("Subject: "+Subject);
	        				  GmailReadMailChanged.processEmail(session, gmail_pj, out, Subject, mongoDataBase , mongoId);
	        				  
	        			 }
	        			 
	        		 }else if( Flag.equalsIgnoreCase("2") ){
	        			 // second time while processing if we get then set 2 flag
	        			 
	        			 if( !GmailMethods.isNullString(Subject) ){
	        				  out.println("Subject: "+Subject);
	        				  GmailReadMailChanged.processEmail(session, gmail_pj, out, Subject , mongoDataBase , mongoId);
	        			 }
	        		 }
	        		 
	        	 } // flag null check
	        	 
	         }
			 
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		 finally{
	    	   mongoClient.close();
	    	   Flag=null;
	    	   Subject=null;
	    	   mongoId=null;
	    	  // System.out.println("fetch Method Executed Successfully!");
	       }
		 
	 }
	 
	 public static void mongoUpdateDataBasedOnId(PrintWriter out,  DB mongoDataBase, String mongoId, String flagCondition){
		 
		 try {
			 
			 mongoDataBase.getCollection("ReadMailCollection").update(
					    new BasicDBObject("_id", new ObjectId(mongoId)),
					    new BasicDBObject("$set", new BasicDBObject("Flag", flagCondition))
					);
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	 
	 public static boolean collectionExists( String collectionname , DB mongoDataBase) {
		    Set<String> collectionNames = mongoDataBase.getCollectionNames();
		    for ( String name : collectionNames) {
		        if (name.equalsIgnoreCase(collectionname)) {
		            return true;
		        }
		    }
		    return false;
		}
	 
	 public static DBCollection saveGmailReadCount( String Dbname ,String collectionname, String passNewCount) {
		 
	   	   DBCollection collection=null;
	   	   MongoClient mongoClient=null;
	       try {
	    	   
	    	   BasicDBObject obj = new BasicDBObject();
	    	   
	            mongoClient = new MongoClient("localhost", 27017);
	            DB mongoDataBase = mongoClient.getDB(Dbname); // databasename
	            
	           boolean collectionNameExists= collectionExists(collectionname, mongoDataBase);
	            if(collectionNameExists){
	            	// System.out.println("true: "+collectionname);
	            	 collection = mongoDataBase.getCollection(collectionname);
	            	 //System.out.println("collection: "+collection);
	            	 getReadMailCountCollection(Dbname, collectionname, passNewCount);
	            	 
	            }else{
	            	collection=mongoDataBase.createCollection(collectionname,new BasicDBObject());
	            	
	            	 obj.put("count", passNewCount);
					 collection.insert(obj);
	            }
	            

	       } catch (Exception e) {
	           e.printStackTrace();
	       } finally{
	    	   mongoClient.close();
	       }
	       return collection;

	   }
	 
 public static void getReadMailCountCollection( String Dbname ,String collectionname, String passNewCount){
		 
		 DBCollection collection=null;
	   	 MongoClient mongoClient=null;
	   	 
	   	 String Count="";
	   	 String mongoId="";
	   	 
		 try {
			 
		   	 mongoClient = new MongoClient("localhost", 27017);
	         DB mongoDataBase = mongoClient.getDB(Dbname);
	         collection = mongoDataBase.getCollection(collectionname);
	         
	         DBCursor collection_cursor = collection.find();
	         while(collection_cursor.hasNext()){
	        	 DBObject db_obj=collection_cursor.next();
	        	 Count= db_obj.get("count").toString();
	        	// System.out.println(Count);
	        	 mongoId= db_obj.get("_id").toString();
	        	// System.out.println("mongoId: "+mongoId);
	        	 
	        	int finalIntCount= Integer.parseInt(Count)+Integer.parseInt(passNewCount);
	        	//System.out.println("finalIntCount: "+finalIntCount);
	        	 if( !GmailMethods.isNullString(Count) ){
	        		// System.out.println("null check: "+String.valueOf(finalIntCount));
	        		 mongoUpdateReadMailCountCollectionData(mongoDataBase, mongoId, collectionname, String.valueOf(finalIntCount));
	        	 }
	        	 
	         }
			 
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		 finally{
	    	   mongoClient.close();
	    	   Count=null;
	    	   mongoId=null;
	    	   
	       }
		 
	 }

 public static void mongoUpdateReadMailCountCollectionData( DB mongoDataBase, String mongoId, String collectionName, String passNewCount){
	 
	 try {
		 
		 mongoDataBase.getCollection(collectionName).update(
				    new BasicDBObject("_id", new ObjectId(mongoId)),
				    new BasicDBObject("$set", new BasicDBObject("count", passNewCount))
				);
		 
		
	} catch (Exception e) {
		e.printStackTrace();
	}
 }
 
 public static DBCollection saveFailedDataTable2( String Dbname ,String collectionname
			, String timeStamp, String subject, String emailUrl, String reportIdentiyFailedReason
			) {
		 
		   DBCollection collection=null;
		   MongoClient mongoClient=null;
	    try {
	 	   
	 	     BasicDBObject obj = new BasicDBObject();
	 	   
	         mongoClient = new MongoClient("localhost", 27017);
	         DB mongoDataBase = mongoClient.getDB(Dbname); // databasename
	         
	         collection = mongoDataBase.getCollection(collectionname);
	         
	    	 obj.put("timeStamp", timeStamp);
	    	 obj.put("subject", subject);
	    	 obj.put("emailUrl", emailUrl);
	    	 obj.put("reportIdentiyFailedReason", reportIdentiyFailedReason);
	    	 
	    	 collection.insert(obj);

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally{
	 	   mongoClient.close();
	    }
	    return collection;

	}
 
 public static DBCollection table3Data( String Dbname ,String collectionname, String processedDataCount
			, String subject, String emailUrl, String rejectedDataCount, String rejectedReason, String rejectedJsonData, String totalNoOfRows
			) {
		 
		   DBCollection collection=null;
		   MongoClient mongoClient=null;
	    try {
	 	   
	 	     BasicDBObject obj = new BasicDBObject();
	 	   
	         mongoClient = new MongoClient("localhost", 27017);
	         DB mongoDataBase = mongoClient.getDB(Dbname); // databasename
	         
	         collection = mongoDataBase.getCollection(collectionname);
	         
	         obj.put("processedDataCount", processedDataCount);
	    	 obj.put("rejectedDataCount", rejectedDataCount);
	    	 obj.put("rejectedReason", rejectedReason);
	    	 obj.put("rejectedJsonData", rejectedJsonData);
	    	 obj.put("emailUrl", emailUrl);
	    	 obj.put("subject", subject);
	    	 obj.put("totalNoOfRows", totalNoOfRows);
	    	 
	    	 collection.insert(obj);

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally{
	 	   mongoClient.close();
	    }
	    return collection;

	}
 

}
