package com.reportinformationsystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.jcr.api.SlingRepository;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Properties;

@Component(immediate = true, metatype = false)
@Service(value = javax.servlet.Servlet.class)
@Properties({ 
		@Property(name = "sling.servlet.paths", value = { "/emailClientNodeId" }),
		})

public class EmailClientNodeIdFromUi extends SlingAllMethodsServlet {
	@Reference
	private SlingRepository repo;
	Session session = null;
	static ResourceBundle bundle = ResourceBundle.getBundle("config");

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try{

		session = repo.login(new SimpleCredentials("admin", "admin".toCharArray()));
		
		String emailClientNodeId=request.getParameter("emailClientNodeId");
		JSONObject data=ReadGmailDataToShowOnUiNew(session, out, emailClientNodeId);
		out.println(data);
		
		}catch(Exception e){
//			e.printStackTrace(out);
		    out.println(e.getMessage());
		}
		
	}

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException, IOException {
	
	}
	
	
	public static JSONObject ReadGmailDataToShowOnUiNew(Session session, PrintWriter out, String emailClientNodeId){
		JSONObject mainarray=null;
		try{
			
			Node rootNode=session.getRootNode();
			if(rootNode.hasNode("GmailData1")){
				Node gmailNode=rootNode.getNode("GmailData1");
				mainarray=new JSONObject();
				
				if( emailClientNodeId.lastIndexOf("/")!=-1 ){
				    int data=emailClientNodeId.lastIndexOf("/");
				     emailClientNodeId=emailClientNodeId.substring(data+1);
//				    System.out.println(gmail);
				}
				
				if(gmailNode.hasNode(emailClientNodeId)){
					Node subjectNode=gmailNode.getNode(emailClientNodeId);
				
				
				
				JSONArray allHtmlLinkAndAttachmentLinkArray=new JSONArray();
				 if(subjectNode.hasNodes()){
					 NodeIterator subjectNodeItr=subjectNode.getNodes();
					 while(subjectNodeItr.hasNext()){
						 Node textNode=subjectNodeItr.nextNode();
						 
						 JSONObject subjectInsideFileLink=new JSONObject();
						  if(textNode.hasProperty("file_link")){
							  String htmlTomcatFilePath=textNode.getProperty("file_link").getString();
							  htmlTomcatFilePath=DateFromToApiReport.downloadEmail(htmlTomcatFilePath);
							  subjectInsideFileLink.put("htmlFileLink", htmlTomcatFilePath);
							  
						  }
						  
						  
						  if(textNode.hasNodes()){
							 NodeIterator textNodeItr=textNode.getNodes();
							 StringBuffer attachmentTomcatFileLink= new StringBuffer();
							 StringBuffer attachmentTomcatAttachment_Name = new StringBuffer();
							 while(textNodeItr.hasNext()){
								 Node textInsideAttachmentFolderNode=textNodeItr.nextNode();
//								 out.println("textInsideAttachmentFolderNode: "+textInsideAttachmentFolderNode.getName());
								 if(textInsideAttachmentFolderNode.hasNodes()){
									 NodeIterator textInsideAttachmentFolderNodeItr=textInsideAttachmentFolderNode.getNodes();
									 
									 while(textInsideAttachmentFolderNodeItr.hasNext()){
										   Node attachmentNode=textInsideAttachmentFolderNodeItr.nextNode();
										  
//											 out.println("attachmentNode: "+attachmentNode);
											 if(attachmentNode.hasProperty("tomcat_file_link")){
												 attachmentTomcatFileLink.append(attachmentNode.getProperty("tomcat_file_link").getString()).append("~");
												 // attachmentTomcatFileLink=attachmentNode.getProperty("tomcat_file_link").getString()+":";
												 
//												  out.println("attachmentTomcatFilePath: "+attachmentTomcatFilePath);
												 // subjectInsideFileLink.put("attachmentFileLink", attachmentTomcatFileLink.toString());

												  }
											 
											 if(attachmentNode.hasProperty("Attachment_Name")){
												 attachmentTomcatAttachment_Name.append(attachmentNode.getProperty("Attachment_Name").getString()).append("~");
												 // String attachmentTomcatAttachment_Name=attachmentNode.getProperty("Attachment_Name").getString()+":";
//												  out.println("attachmentTomcatFilePath: "+attachmentTomcatFilePath);
												//  subjectInsideFileLink.put("attachmentFileName", attachmentTomcatAttachment_Name);

												  }
											 
											 
										   
										   
									 }
									 subjectInsideFileLink.put("attachmentFileLink", attachmentTomcatFileLink.toString());
									 subjectInsideFileLink.put("attachmentFileName", attachmentTomcatAttachment_Name.toString());
									 
									 // textInsideAttachmentFolderNodeItr while close
								 } // textInsideAttachmentFolderNodeItr hashnodes check

								   //allHtmlLinkAndAttachmentLinkArray.put(subjectInsideFileLink);
							 }// textNodeItr while close
							 
						  } // textNode hasnode check
						  allHtmlLinkAndAttachmentLinkArray.put(subjectInsideFileLink);
						  
						  
						  
					 }
			}
				 JSONObject subjectJSonObject=new JSONObject();
				 subjectJSonObject.put("active", "");
				 subjectJSonObject.put("array", allHtmlLinkAndAttachmentLinkArray);
				 mainarray.put(subjectNode.getName(), subjectJSonObject);
				 
				}
				 
		}
			
		} catch (Exception e) {
			out.println(e.getMessage());
		}
		return mainarray;
	}

	

	
}
