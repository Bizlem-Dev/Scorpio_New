package com.anagha;


import java.util.Set;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.readGmail.GmailMethods;

public class MongoForFailedAndSuccess {

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
	
	 public static boolean collectionExists( String collectionname , DB mongoDataBase) {
		    Set<String> collectionNames = mongoDataBase.getCollectionNames();
		    for ( String name : collectionNames) {
		        if (name.equalsIgnoreCase(collectionname)) {
		            return true;
		        }
		    }
		    return false;
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

public static DBCollection saveFailedData( String Dbname ,String collectionname, String mongoUpdateFailedCount
		, String timeStamp, String subject, String emailUrl, String textFileLink
		) {
	 
	   DBCollection collection=null;
	   MongoClient mongoClient=null;
    try {
 	   
 	     BasicDBObject obj = new BasicDBObject();
 	   
         mongoClient = new MongoClient("localhost", 27017);
         DB mongoDataBase = mongoClient.getDB(Dbname); // databasename
         
         collection = mongoDataBase.getCollection(collectionname);
         
         obj.put("Count", mongoUpdateFailedCount);
    	 obj.put("timeStamp", timeStamp);
    	 obj.put("subject", subject);
    	 obj.put("emailUrl", emailUrl);
    	 obj.put("textFileLink", textFileLink);
    	 
    	 collection.insert(obj);

    } catch (Exception e) {
        e.printStackTrace();
    } finally{
 	   mongoClient.close();
    }
    return collection;

}

	
}
