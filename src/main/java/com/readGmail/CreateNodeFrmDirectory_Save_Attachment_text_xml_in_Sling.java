package com.readGmail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.ResourceBundle;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.sling.commons.json.JSONArray;

public class CreateNodeFrmDirectory_Save_Attachment_text_xml_in_Sling {

	private static final int BUFFER_SIZE = 4096;
//	private static PrintWriter out;
//	private static ResourceBundle bundle = ResourceBundle.getBundle("config");
//	private static Session session;
	
	public static void main(String[] args) {
		  // create StringBuilder object
        StringBuilder sb = new StringBuilder();
        sb.append("abhishek");
        System.out.println(sb.length());
	}
	
	public static void Nodecreate_saveattachment_text_xml(ResourceBundle bundle, Session session, PrintWriter out){
		
		try {
			
			File currentDir = new File(bundle.getString("file_path")); // current directory 
			out.println("currentDir: "+currentDir);
			JSONArray json_arr=null;
			json_arr=displayDirectoryContents(currentDir);
			out.println(json_arr.length());
			
			Node content=null;
	        Node sub_content=null;
	        Node file_sub=null;
	        Node file_content=null;
	        Node attachment=null;
	        Node attachment_sub=null;
	        Node attachment_content=null;
	        String folder_name=null;
	        String file_name=null;
	        String sub_node_name=null;
	        
	        if(session.getRootNode().hasNode(bundle.getString("GmailReadNode"))){
				content = session.getRootNode().getNode(bundle.getString("GmailReadNode"));
			}else{
				content = session.getRootNode().addNode(bundle.getString("GmailReadNode"));
			}
	        
	        File subDir=null;
			File[] files=null;
			File[] attachment_files=null;
			String[] paths=null;
			
			 for(int i=0;i<json_arr.length();i++){
				 folder_name=json_arr.get(i).toString();
				 out.println("folder_name: "+folder_name);
	         	 paths=folder_name.split("/");
	         	 out.println("paths: "+paths);
	         	 sub_node_name=paths[paths.length-1];
	         	 out.println("sub_node_name: "+sub_node_name);
	         	 
	         	if(content.hasNode(sub_node_name)){
	        		sub_content = content.getNode(sub_node_name);
				}else{
					sub_content = content.addNode(sub_node_name);
				}
	         	
	         	subDir = new File(folder_name); // current directory
	        	files = subDir.listFiles();
	        	
	        	for (File file : files) {
	        		if (file.isDirectory()) {
	        			folder_name=file.getName().toString();
	        			out.println("sub root node folder_name after: "+folder_name);
	        			if(sub_content.hasNode(folder_name)){
		            		attachment = sub_content.getNode(folder_name);
						}else{
							attachment = sub_content.addNode(folder_name);
						}
	        			
	        			attachment_files=file.listFiles();
	        			for (File attachment_file : attachment_files) {
	        				if (attachment_file.isDirectory()) {
	        					
	        				}else{
	        					
	        					file_name=attachment_file.getName().toString();
	        					if(attachment.hasNode(file_name)){
		    						attachment_sub = attachment.getNode(file_name);
		    					}else{
		    						attachment_sub = attachment.addNode(file_name, "nt:unstructured");
		    					}
	        					
	        					if(attachment_sub.hasProperty(bundle.getString("flagProperty"))){
	      						     
	    						}else{
	    							attachment_sub.setProperty(bundle.getString("flagProperty"), new String());
	    						}
	        					
	        					if(attachment_sub.hasProperty(bundle.getString("proccessedDataproperty"))){
	      						     
	    						}else{
	    							attachment_sub.setProperty(bundle.getString("proccessedDataproperty"), new String());
	    						}
	        					
	        					if(attachment_sub.hasProperty(bundle.getString("file_linkProperty"))){
	     						     
	    						}else{
	    							String file_link="http://"+bundle.getString("slingIp")+":"+bundle.getString("tomcatPort")+"/"+bundle.getString("slingWarName")+"/bin/cpm/nodes/property.bin"+attachment_sub.getPath()+"/"+attachment_sub.getName()+"/_jcr_content?name=jcr%3Adata";
	    							//out.println("file_link: "+file_link);
	    							attachment_sub.setProperty(bundle.getString("file_linkProperty"), file_link);
	    						}
	        					
	        					if(attachment_sub.hasProperty(bundle.getString("filepathProperty"))){
	     						     
	    						}else{
	    							String file_path="http://"+bundle.getString("slingIp")+":"+bundle.getString("tomcatPort")+"/"+bundle.getString("slingWarName")+""+attachment_sub.getPath()+"/"+attachment_sub.getName();
	    							//out.println("file_path: "+file_path);
	    							attachment_sub.setProperty(bundle.getString("filepathProperty"), file_path);
	    						}
	        					
	        					if(attachment_sub.hasProperty(bundle.getString("tomcat_file_pathProperty"))){
	     						     
	    						}else{
	    							attachment_sub.setProperty(bundle.getString("tomcat_file_pathProperty"), bundle.getString("AttachmentPathtextxml")+file_name);
	    						}
	    						if(attachment_sub.hasProperty(bundle.getString("tomcat_file_linkProperty"))){
	      						     
	    						}else{
	    							attachment_sub.setProperty(bundle.getString("tomcat_file_linkProperty"), "http://"+bundle.getString("slingIp")+":"+bundle.getString("tomcatPort")+"/"+bundle.getString("tomcatAttachmentFolderName")+"/"+file_name);
	    						}
	    						
	    						if(attachment_sub.hasNode(file_name)){
		    						attachment_content = attachment_sub.getNode(file_name);
		    					}else{
		    						attachment_content = attachment_sub.addNode(file_name, "nt:file");
		    						Node jcrNode = null;
		    						InputStream stream = new FileInputStream(attachment_file);
									jcrNode = attachment_content.addNode("jcr:content", "nt:resource");
									jcrNode.setProperty("jcr:data", stream);
									jcrNode.setProperty("jcr:mimeType", "attach");
									
									saveFile2Tomcat(new FileInputStream(attachment_file),file_name, bundle, out);
		    					}
	    						
	        					
	        				}// else close attachment
	        				
	        			} // for attachment close
	        			
	        		}// file directory check
	        		else{
	        			file_name=file.getName().toString();
	        			file_name=file.getName().toString();
	        			if(sub_content.hasNode(file_name)){
							file_sub = sub_content.getNode(file_name);
						}else{
							file_sub = sub_content.addNode(file_name, "nt:unstructured");
						}
	        			
	        			if(file_sub.hasProperty(bundle.getString("flagProperty"))){
						     
						}else{
							file_sub.setProperty(bundle.getString("flagProperty"), new String());
						}
					   if(file_sub.hasProperty(bundle.getString("proccessedDataproperty"))){
						     
						}else{
							file_sub.setProperty(bundle.getString("proccessedDataproperty"), new String());
						}
						if(file_sub.hasProperty(bundle.getString("file_linkProperty"))){
  						     
						}else{
							String file_link="http://"+bundle.getString("slingIp")+":"+bundle.getString("tomcatPort")+"/"+bundle.getString("slingWarName")+"/bin/cpm/nodes/property.bin"+file_sub.getPath()+"/"+file_sub.getName()+"/_jcr_content?name=jcr%3Adata";
							//out.println("file_link: "+file_link);
							file_sub.setProperty(bundle.getString("file_linkProperty"), file_link);
						}
						if(file_sub.hasProperty(bundle.getString("filepathProperty"))){
 						     
						}else{
							String file_path="http://"+bundle.getString("slingIp")+":"+bundle.getString("tomcatPort")+"/"+bundle.getString("slingWarName")+""+file_sub.getPath()+"/"+file_sub.getName();
							//out.println("file_path: "+file_path);
							file_sub.setProperty(bundle.getString("filepathProperty"), file_path);
						}
						if(file_sub.hasProperty(bundle.getString("tomcat_file_pathProperty"))){
 						     
						}else{
							file_sub.setProperty(bundle.getString("tomcat_file_pathProperty"), bundle.getString("AttachmentPathtextxml")+file_name);
						}
						if(file_sub.hasProperty(bundle.getString("tomcat_file_linkProperty"))){
  						     
						}else{
							file_sub.setProperty(bundle.getString("tomcat_file_linkProperty"), "http://"+bundle.getString("slingIp")+":"+bundle.getString("tomcatPort")+"/"+bundle.getString("tomcatAttachmentFolderName")+"/"+file_name);
						}
						
						if(file_name.contains(".txt")){
							
							if(file_sub.hasProperty(bundle.getString("xml_file_linkProperty"))){
     						     
    						}else{
    							
//    							try{
    							String txt_file_link="http://"+bundle.getString("slingIp")+":"+bundle.getString("tomcatPort")+"/"+bundle.getString("slingWarName")+"/bin/cpm/nodes/property.bin"+file_sub.getPath()+"/"+file_sub.getName()+"/_jcr_content?name=jcr%3Adata";
    							String xml_file_link=txt_file_link.replace(".txt", ".xml");
    							file_sub.setProperty(bundle.getString("xml_file_linkProperty"), xml_file_link);
//    							}catch(Exception e){
//    								continue;
//    							}
    							
    						}
						}
						
						if(file_sub.hasNode(file_name)){
							file_content = file_sub.getNode(file_name);
						}else{
							file_content = file_sub.addNode(file_name, "nt:file");
							Node jcrNode = null;
							InputStream stream = new FileInputStream(file);
							jcrNode = file_content.addNode("jcr:content", "nt:resource");
							jcrNode.setProperty("jcr:data", stream);
							jcrNode.setProperty("jcr:mimeType", "attach");
							
							if(file_name.contains(".txt")){
								saveFile2Tomcat(new FileInputStream(file),file_name, bundle, out);
							}
							
						}
						
	        			
	        		}
	        		
	        	} // for file close
			 }// for json length close
			 session.save();
	        
	        
		} catch (Exception e) {
			out.println("catch error:: "+e.getMessage());
		}
	}
	
	
	public static JSONArray displayDirectoryContents(File dir) {
		JSONArray json_arr=new JSONArray();
		try {
			
			File[] files = dir.listFiles();
			
			String file_name;
			for (File file : files) {
				
				if (file.isDirectory()) {
					json_arr.put(file.getCanonicalPath().toString());
					displayDirectoryContents(file);
				} else {
//		            System.out.println("     file:" + file.getCanonicalPath());
		            file_name=file.getName();
		            file_name=file_name.substring(0,file_name.lastIndexOf("."));
		        }
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return json_arr;
	}
	
	public static String saveFile2Tomcat(InputStream inputStream,String filename, ResourceBundle bundle, PrintWriter out) {

		String stored_file_path=null;

		try {
			
			   File tomcateirectory=new File(bundle.getString("AttachmentPathtextxml"));
			   if(!tomcateirectory.exists()){
				   
				   tomcateirectory.mkdir();
				   out.println("folder created in Tomcate");
			   }
			   
			    stored_file_path = bundle.getString("AttachmentPathtextxml")+filename;
                FileOutputStream outputStream = new FileOutputStream(stored_file_path);
	            int bytesRead = -1;
	            byte[] buffer = new byte[BUFFER_SIZE];
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
	                outputStream.write(buffer, 0, bytesRead);
	            }
	            outputStream.close();
	            inputStream.close();
	            out.println("File downloaded");
	         
        } catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		return stored_file_path;

	}
	
}
