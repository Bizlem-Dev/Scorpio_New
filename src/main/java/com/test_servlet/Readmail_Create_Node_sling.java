package com.test_servlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;
import java.util.ResourceBundle;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.Workspace;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Message.RecipientType;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.SearchTerm;
import javax.servlet.ServletException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.jcr.api.SlingRepository;
import org.w3c.dom.Element;

import com.readGmail.CreateNodeFrmDirectory_Save_Attachment_text_xml_in_Sling;
import com.readGmail.GmailMethods;
import com.readGmail.Gmail_Pojo;
import com.readGmail.ReadEmailFromGmail;

import org.apache.commons.io.FilenameUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Properties;

@Component(immediate = true, metatype = false)
@Service(value = javax.servlet.Servlet.class)
@Properties({ 
		@Property(name = "sling.servlet.paths", value = { "/gmail" }),
		})

public class Readmail_Create_Node_sling extends SlingAllMethodsServlet {
	@Reference
	private SlingRepository repo;
	static Session session = null;
	static ResourceBundle bundle = ResourceBundle.getBundle("config");
	private static Folder folder;
	private static DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	private static DocumentBuilder docBuilder ;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		
		
		try{

		session = repo.login(new SimpleCredentials("admin", "admin".toCharArray()));
		out.println("hello");
		Gmail_Pojo gm = null;
		gm = new Gmail_Pojo();
		gm.setProtocol(bundle.getString("protocol"));
		gm.setHost(bundle.getString("host"));
		gm.setPort(bundle.getString("port"));
		gm.setUserName(bundle.getString("userName"));
		gm.setPassWord(bundle.getString("password"));
		
		processEmail(gm, out);
        
		}catch(Exception e){
			e.printStackTrace();
		    out.println(e.getMessage());
		}

	}

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

	}
	
	public static void processEmail(Gmail_Pojo gmail_pj, PrintWriter out) {
		  
		try {
			
			GmailMethods.getServerConnect(gmail_pj);
			folder=GmailMethods.openFolder(bundle.getString("GmailFolderName"));
			SearchTerm term=GmailMethods.searchMailterm(out);
            
			if(term!=null){
         // fetches new messages from server
            Message[] arrayMessages = folder.getMessages();  // without search we can use this 
//            Message[] arrayMessages = folder.search(term);     // search messages
            if( arrayMessages.length != 0 ){
            out.println("length :: " + arrayMessages.length);
            
//          for (int i = arrayMessages.length - 1; i >= 0; i--) {  // this used for print last message only
            for (int i = 0; i < arrayMessages.length; i++) {       // this will print all message
            	Message message = arrayMessages[i];
            	
            	String seen=GmailMethods.seen_UnseenEmails(out, message);
            	
            	Address[] fromAddress = message.getFrom();
            	String from="";
            	if(fromAddress.length>0){
            	 from = fromAddress[0].toString();
            	}
            	
            	String subject = message.getSubject();
            	
            	String toList=GmailMethods.parseAddresses(message.getRecipients(RecipientType.TO));
            	String ccList=GmailMethods.parseAddresses(message.getRecipients(RecipientType.CC));
            	
            	String sentDate = message.getSentDate().toString();
            	sentDate=(GmailMethods.isNullString(sentDate)) ? "" : sentDate;
            	String mailSentDate="";
            	if(!GmailMethods.isNullString(sentDate)){
            		Date date=GmailMethods.parseDate(sentDate);
                	mailSentDate=GmailMethods.formatDate(date);
            	}
            	
            	String recDate = message.getReceivedDate().toString();
            	recDate=(GmailMethods.isNullString(recDate)) ? "" : recDate;
            	String mailReceivedDate="";
            	if(!GmailMethods.isNullString(recDate)){
            	   Date date1=GmailMethods.parseDate(recDate);
            	   mailReceivedDate=GmailMethods.formatDate(date1);
            	}
                
            	String contentType = message.getContentType();
            	String bodyText="";
            	       bodyText=Readmail_Create_Node_sling.getTextFromMessage1(message);
            	       bodyText=bodyText.toString().replaceAll("\\<.*?>","").replaceAll("[image:\\s]*.*]","");
            	       bodyText=(GmailMethods.isNullString(bodyText)) ? "" : bodyText;
            	       
            	String messageContent="";
            	String Signature="";
            	String Bodycheckmsg="";
            	JSONObject body_plus_messegecontent=null;
            	
            	      body_plus_messegecontent=GmailMethods.GmailBodyText(contentType, message, out);
            	      if(body_plus_messegecontent.has("messagetext")){
                   	   Bodycheckmsg=body_plus_messegecontent.getString("messagetext");
                   	   Bodycheckmsg=Bodycheckmsg.replace("RE: CXL     RE: ", "").replace("CXL     RE: ", "")
	    			    .replace("REF: ", "").replace("RE: ", "").replace("FW: ", "").replace("#", "_").replace(":", "_").replace(",", "_")
	    			    .replace("[", "").replace("]", "").replaceAll("[\\//|:]+","").trim().replaceAll("\\s{1,}", "_")
	    			    .replace("Fwd:", "").replace("Fwd: Re :", "").replaceAll("[^\\p{ASCII}]", "").replaceAll("\\_+", "_") 
	    			    .replaceAll("[^a-zA-Z0-9.]", "_");
                  
//                   	   out.println("Bodycheckmsg: "+Bodycheckmsg);
                   	}
            	   if(body_plus_messegecontent.has("messageContent")){
            	      messageContent=body_plus_messegecontent.getString("messageContent");
            	      if(!GmailMethods.isNullString(messageContent) && (!GmailMethods.isNullString(bodyText))){
            	    	  Signature=GmailMethods.signature(Bodycheckmsg, messageContent, out);
            	      }
            	      
            	   }
            	
            	if(!GmailMethods.isNullString(subject)){
            		String subject_replace = subject.replace("RE: CXL     RE: ", "").replace("CXL     RE: ", "")
    		    			.replace("REF: ", "").replace("RE: ", "").replace("FW: ", "").replace("#", "_").replace(":", "_").replace(",", "_")
    		    			.replace("[", "").replace("]", "").replaceAll("[\\//|:]+","").trim().replaceAll("\\s{1,}", "_")
    		    			.replace("Fwd:", "").replace("Fwd: Re :", "").replaceAll("[^\\p{ASCII}]", "").replaceAll("\\_+", "_")   ;
            		
            		Node content=null;
            		Node subject_replace_node_mainnode=null;
            		Node Email_Fields=null;
            		
            		if(session.getRootNode().hasNode("readslingnode")){
        				content = session.getRootNode().getNode("readslingnode");
        			}else{
        				content = session.getRootNode().addNode("readslingnode");
        			}
            		
            		if(content.hasNode(subject_replace)){
            			subject_replace_node_mainnode=content.getNode(subject_replace);
            			if(subject_replace_node_mainnode.hasNode("Email_Fields")){
            				Email_Fields=subject_replace_node_mainnode.getNode("Email_Fields");
            				
            			}
            			
            			 dublicatecheck_mainNode(out, session, subject_replace, Bodycheckmsg, bodyText, toList, from, ccList, mailSentDate, subject, contentType, message);
            			 out.println("same below");
            			
            			
            		}else{
            			subject_replace_node_mainnode=content.addNode(subject_replace);
            			subject_replace_node_mainnode.setProperty("jcr:count", String.valueOf(0));
            			session.save();
            			Node body=null;
            			if(!subject_replace_node_mainnode.hasNode("Email_Fields")){
            				Email_Fields=subject_replace_node_mainnode.addNode("Email_Fields");
            				body=Email_Fields.addNode("Body");
            				body.setProperty("jcr:count", String.valueOf(0));
            				session.save();
            			}
            			
            			 body=Email_Fields.getNode("Body");
						 String count=body.getProperty("jcr:count").getString();
						 String forid= String.valueOf(Integer.parseInt(count)+1);
						 if(!body.hasNode(forid)){
							 Node bodylist=body.addNode(forid);
							 bodylist.setProperty("Body_Text", Bodycheckmsg);
							 bodylist.setProperty("Body_Content_Length", String.valueOf(Bodycheckmsg.length()));
							 body.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
							 session.save();
					}
						 String mainnodeCount=subject_replace_node_mainnode.getProperty("jcr:count").getString();
						 createTextNode_Save_txt_sling(out, subject_replace_node_mainnode, subject_replace, bodyText, mainnodeCount); 
						 createXmlNode_Save_Xml_sling(out, subject_replace_node_mainnode, subject_replace, bodyText, toList, from, ccList, sentDate, subject, mainnodeCount);
                             
						 subject_replace_node_mainnode.setProperty("jcr:count",String.valueOf(Integer.parseInt(mainnodeCount)+1));
						 
						 AttachmentSaveData(out, contentType, message, Email_Fields, subject_replace_node_mainnode, subject_replace);
						 
						 
            		}
 
            		  session.save();
            	}
  
            	
            }  // for close
            
            GmailMethods.closeFolder();
            GmailMethods.disconnect();
			}// term check null close
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			out.print(e.getMessage());
		}
		
	}
	
	static String getTextFromMessage1(Message message) throws MessagingException, IOException {
	    String result = "";
	    if (message.isMimeType("text/plain")) {
	        result = message.getContent().toString();
	    } else if (message.isMimeType("multipart/*")) {
	        MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
	        result = getTextFromMimeMultipart(mimeMultipart);
	    }
	    return result;
	}
 
 private static String getTextFromMimeMultipart(
	        MimeMultipart mimeMultipart)  throws MessagingException, IOException{
	    String result = "";
	    int count = mimeMultipart.getCount();
	    for (int i = 0; i < count; i++) {
	        BodyPart bodyPart = mimeMultipart.getBodyPart(i);
	        if (bodyPart.isMimeType("text/plain")) {
	            result = result + "\n" + bodyPart.getContent();
	            break; // without break same text appears twice in my tests
	        } else if (bodyPart.isMimeType("text/html")) {
	            String html = (String) bodyPart.getContent();
	            result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
	        } else if (bodyPart.getContent() instanceof MimeMultipart){
	            result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
	        }
	    }
	    return result;
	}
	
 public static Node dublicatecheck_mainNode(PrintWriter out, Session session, String subject_replace, String emailBody, String bodyText, String toList, String from, String ccList, 
		 String sentDate, String subject, String contentType, Message message){
	   
	  Node CheckedNode=null;
	   
	   try {
		
		   if(session.getRootNode().hasNode("readslingnode")){
		   Node readslingnode  = session.getRootNode().getNode("readslingnode");
		   if(readslingnode.hasNodes()){
			   NodeIterator iterator = readslingnode.getNodes();
			   while (iterator.hasNext()) {

				Node mainNodeList = iterator.nextNode();
				String mainNodeListName=mainNodeList.getName();
				 
					if(mainNodeListName.equals(subject_replace)){
						CheckedNode=mainNodeList;
						out.println("same mainnode");
						checkEmailFields(out, CheckedNode, emailBody, subject_replace, bodyText, toList, from, ccList, sentDate, subject, contentType,  message);
					}
				}
		   }
		 
		   } // main node check
	} catch (Exception e) {
		out.println(e.getMessage());
	}
	   
	return CheckedNode;
	   
 }
 
 public static void checkEmailFields_old(PrintWriter out, Node mainNode, String emailBody, String subject_replace, String bodyText, String toList, String from, String ccList,String sentDate, String subject, 
		 String contentType, Message message){
	 
	 
	 try {
		 
//		 check_EmailFields_Query(out, session, emailBody, mainNode);
		if(mainNode.hasNode("Email_Fields")){
			Node Email_Fields=mainNode.getNode("Email_Fields");
			Node body=null;
			if(Email_Fields.hasNode("Body")){
				 body=Email_Fields.getNode("Body");
				if(body.hasNodes()){
					NodeIterator itr=body.getNodes();
					String body_Text="";
					while(itr.hasNext()){
						Node bodyListNode=itr.nextNode();
						if(bodyListNode.hasProperty("Body_Text")){
							body_Text=bodyListNode.getProperty("Body_Text").getString().trim();
							
					}//
					}// while close
					
//						out.println("inside body check:: "+body_Text);
//						out.println("inside emailBody check:: "+emailBody);
						if(body_Text.equals(emailBody)){
							out.println("body equals");
							
							 String fileName=attachmentCheckDublicate(out, contentType, message);
							 
							 if(!GmailMethods.isNullString(fileName)){
								 boolean originalcheckFileName= checkEmailFields_attachent(out, mainNode, fileName);
								 out.println("originalcheckFileName: "+originalcheckFileName);
							      if(!originalcheckFileName){
							    	  AttachmentSaveData(out, contentType, message, Email_Fields, mainNode, subject_replace);
							      }
							 }
							
							 
							
						}else{
							out.println("not matched ");
							 String count=body.getProperty("jcr:count").getString();
							 String forid= String.valueOf(Integer.parseInt(count)+1);
							 if(!body.hasNode(forid)){
								 Node bodylist=body.addNode(forid);
								 bodylist.setProperty("Body_Text", emailBody);
								 body.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
								 session.save();
						}
							 String mainnodeCount=mainNode.getProperty("jcr:count").getString();
							 createTextNode_Save_txt_sling(out, mainNode, subject_replace, bodyText, mainnodeCount); 
							 createXmlNode_Save_Xml_sling(out, mainNode, subject_replace, bodyText, toList, from, ccList, sentDate, subject, mainnodeCount);
	                             
							 mainNode.setProperty("jcr:count",String.valueOf(Integer.parseInt(mainnodeCount)+1));
							 
							 AttachmentSaveData(out, contentType, message, Email_Fields, mainNode, subject_replace);
							 session.save();
						}
					
					
					//
					
				}
			}
		}
		
		
		 
		 
	} catch (Exception e) {
		out.println(e.getMessage());
	}
 }
 
 public static void writeUsingFileWriter(File file,String subject_replace,String body, PrintWriter out) {
		
     FileWriter fr = null;
     try {
         fr = new FileWriter(file);
         fr.write(subject_replace);
         fr.append("\n");
         
//         fr.write(System.lineSeparator());
//         fr.write("\n");
         fr.write(body);
//         fr.write(System.lineSeparator());
         fr.write("\n");
     } catch (IOException e) {
//         e.printStackTrace();
         out.println(e.getMessage());
     }finally{
         //close resources
         try {
             fr.close();
         } catch (IOException e) {
//             e.printStackTrace();
             out.println(e.getMessage());
         }
     }
 }
 public static void writeFile1(String fileroot,String subject_replace,String body, PrintWriter out) throws IOException {
		File fout = new File(fileroot);
		FileOutputStream fos = new FileOutputStream(fout);
	 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	 
		
			bw.write(subject_replace);
			bw.newLine();
			bw.write(body);
			bw.newLine();
		
	 
		bw.close();
	}
 
 public static void createTextNode_Save_txt_sling(PrintWriter out, Node subject_replace_node_mainnode, String subject_replace, String bodyText, String mainnodeCount){
	 try {
		
		 String forid1= String.valueOf(Integer.parseInt(mainnodeCount)+1);
		 Node mainInsideText_unstructured_Node=null;
		 String file_name=subject_replace+"_"+forid1+".txt";
		 if(subject_replace_node_mainnode.hasNode(file_name)){
			 mainInsideText_unstructured_Node=subject_replace_node_mainnode.getNode(file_name);
		 }else{
			 mainInsideText_unstructured_Node=subject_replace_node_mainnode.addNode(file_name, "nt:unstructured");
		 }
		 
		 if(mainInsideText_unstructured_Node.hasProperty("Flag")){
			 
		 }else{
			 mainInsideText_unstructured_Node.setProperty("Flag", new String());
		 }
		 
         if(mainInsideText_unstructured_Node.hasProperty("proccessedData")){
			 
		 }else{
			 mainInsideText_unstructured_Node.setProperty("proccessedData", new String());
		 }
		 
         if(mainInsideText_unstructured_Node.hasProperty("file_link")){
			 
		 }else{
			 String file_link="http://35.199.31.139:8080/bernhard/bin/cpm/nodes/property.bin"+mainInsideText_unstructured_Node.getPath()+"/"+mainInsideText_unstructured_Node.getName()+"/_jcr_content?name=jcr%3Adata";
			 mainInsideText_unstructured_Node.setProperty("file_link", file_link);
		 }

         if(mainInsideText_unstructured_Node.hasProperty("file_path")){
			 
		 }else{
			 String file_path="http://35.199.31.139:8080/bernhard"+mainInsideText_unstructured_Node.getPath()+"/"+mainInsideText_unstructured_Node.getName();
			 mainInsideText_unstructured_Node.setProperty("file_path", file_path);
		 }

         if(mainInsideText_unstructured_Node.hasProperty("tomcat_file_path")){
			 
		 }else{
			 mainInsideText_unstructured_Node.setProperty("tomcat_file_path", "/home/ubuntu/apache-tomcat-8.5.31/webapps/ROOT/scorpio/"+file_name);
		 }

         if(mainInsideText_unstructured_Node.hasProperty("tomcat_file_link")){
			 
		 }else{
			 mainInsideText_unstructured_Node.setProperty("tomcat_file_link", "http://35.199.31.139:8080/scorpio/"+file_name);
		 } 
         
         
         if(file_name.contains(".txt")){
        	 if(mainInsideText_unstructured_Node.hasProperty("xml_file_link")){
        		 
        	 }else{
        		 String txt_file_link="http://35.199.31.139:8080/bernhard/bin/cpm/nodes/property.bin"+mainInsideText_unstructured_Node.getPath()+"/"+mainInsideText_unstructured_Node.getName()+"/_jcr_content?name=jcr%3Adata";
					 String xml_file_link=txt_file_link.replace(".txt", ".xml");
					 mainInsideText_unstructured_Node.setProperty("xml_file_link", xml_file_link);
        	 }
         }

         Node file_content=null;
         if(mainInsideText_unstructured_Node.hasNode(file_name)){
        	 file_content=mainInsideText_unstructured_Node.getNode(file_name);
         }else{
        	 file_content=mainInsideText_unstructured_Node.addNode(file_name, "nt:file");
         }
         
             String file_root_path="/home/ubuntu/apache-tomcat-8.5.31/webapps/ROOT/scorpio/";
             File tomcateirectory=new File(file_root_path);
			   if(!tomcateirectory.exists()){
				   
				   tomcateirectory.mkdir();
				   out.println("folder created in Tomcate");
			   }
             
             writeUsingFileWriter(new File(file_root_path+"/"+file_name), subject_replace, bodyText, out);
//			     String s=file_root_path+"/"+file_name;
//             writeFile1(s, subject_replace, bodyText, out);
             
             Node jcrNode = null;
             InputStream stream = new FileInputStream(file_root_path+file_name);
             jcrNode = file_content.addNode("jcr:content", "nt:resource");
             jcrNode.setProperty("jcr:data", stream);
			 jcrNode.setProperty("jcr:mimeType", "attach");
				 session.save();
		 
		 
	} catch (Exception e) {
		out.println(e.getMessage());
	}
 }
 
 public static void create_AttachmentNode_SaveAttach_Sling(PrintWriter out, Node subject_replace_node_mainnode, String subject_replace, MimeBodyPart part, long NodeSize, String FileName_replace){
	 try {
		 
		
		 Node mainInsideText_unstructured_Node=null;
		 Node AttchsubNode=null;
		 String file_name=subject_replace+"_"+NodeSize;
		 String subAttachnodename=file_name+"_"+FileName_replace;
		 if(subject_replace_node_mainnode.hasNode(file_name)){
			 mainInsideText_unstructured_Node=subject_replace_node_mainnode.getNode(file_name);
		 }else{
			 mainInsideText_unstructured_Node=subject_replace_node_mainnode.addNode(file_name, "nt:unstructured");
		 }
		 
		 if(mainInsideText_unstructured_Node.hasNode(subAttachnodename)){
			 AttchsubNode=mainInsideText_unstructured_Node.getNode(subAttachnodename);
		 }else{
			 AttchsubNode=mainInsideText_unstructured_Node.addNode(subAttachnodename, "nt:unstructured");
		 }
		 
		 
		 if(AttchsubNode.hasProperty("Flag")){
			 
		 }else{
			 AttchsubNode.setProperty("Flag", new String());
		 }
		 
         if(AttchsubNode.hasProperty("proccessedData")){
			 
		 }else{
			 AttchsubNode.setProperty("proccessedData", new String());
		 }
		 
         if(AttchsubNode.hasProperty("file_link")){
			 
		 }else{
			 String file_link="http://35.199.31.139:8080/bernhard/bin/cpm/nodes/property.bin"+AttchsubNode.getPath()+"/"+AttchsubNode.getName()+"/_jcr_content?name=jcr%3Adata";
			 AttchsubNode.setProperty("file_link", file_link);
		 }

         if(AttchsubNode.hasProperty("file_path")){
			 
		 }else{
			 String file_path="http://35.199.31.139:8080/bernhard"+AttchsubNode.getPath()+"/"+AttchsubNode.getName();
			 AttchsubNode.setProperty("file_path", file_path);
		 }

         if(AttchsubNode.hasProperty("tomcat_file_path")){
			 
		 }else{
			 AttchsubNode.setProperty("tomcat_file_path", "/home/ubuntu/apache-tomcat-8.5.31/webapps/ROOT/scorpio/"+subAttachnodename);
		 }

         if(AttchsubNode.hasProperty("tomcat_file_link")){
			 
		 }else{
			 AttchsubNode.setProperty("tomcat_file_link", "http://35.199.31.139:8080/scorpio/"+subAttachnodename);
		 } 
         
         
         if(file_name.contains(".txt")){
        	 if(AttchsubNode.hasProperty("xml_file_link")){
        		 
        	 }else{
        		 String txt_file_link="http://35.199.31.139:8080/bernhard/bin/cpm/nodes/property.bin"+AttchsubNode.getPath()+"/"+AttchsubNode.getName()+"/_jcr_content?name=jcr%3Adata";
				 String xml_file_link=txt_file_link.replace(".txt", ".xml");
				 AttchsubNode.setProperty("xml_file_link", xml_file_link);
        	 }
         }

         Node file_content=null;
         if(AttchsubNode.hasNode(subAttachnodename)){
        	 file_content=AttchsubNode.getNode(subAttachnodename);
         }else{
        	 file_content=AttchsubNode.addNode(subAttachnodename, "nt:file");
         }
         
             String file_root_path="/home/ubuntu/apache-tomcat-8.5.31/webapps/ROOT/scorpio/";
             File tomcateirectory=new File(file_root_path);
			   if(!tomcateirectory.exists()){
				   
				   tomcateirectory.mkdir();
				   out.println("folder created in Tomcate");
			   }

			   part.saveFile(new File(file_root_path+"/"+subAttachnodename));
             
             Node jcrNode = null;
             InputStream stream = new FileInputStream(file_root_path+subAttachnodename);
             jcrNode = file_content.addNode("jcr:content", "nt:resource");
             jcrNode.setProperty("jcr:data", stream);
			 jcrNode.setProperty("jcr:mimeType", "attach");
				 session.save();
		 
		 
	} catch (Exception e) {
		out.println(e.getMessage());
	}
 }
 
 public static void createXml(PrintWriter out, String filename, String to, String from, String cc, String dateTime, String subject, String bodytext, String s){
	 
	 try {
		
		    docBuilder = docFactory.newDocumentBuilder();
			org.w3c.dom.Document doc =docBuilder.newDocument();
			Element rootElement = doc.createElement("add");
		    doc.appendChild(rootElement);
		    Element sub_RootElement = doc.createElement("doc");
		            rootElement.appendChild(sub_RootElement);
		            
		         // append child elements to root element
		            Element id = doc.createElement("field");
		            id.setAttribute("name", "id");
		            id.appendChild(doc.createTextNode(filename));
				    sub_RootElement.appendChild(id);
				    
				    Element category = doc.createElement("field");
				    category.setAttribute("name", "category");
				    category.appendChild(doc.createTextNode(""));
				    sub_RootElement.appendChild(category);
				    
				    Element to1 = doc.createElement("field");
				    to1.setAttribute("name", "to");
				    to1.appendChild(doc.createTextNode(to));
				    sub_RootElement.appendChild(to1);
		            
				    Element from1 = doc.createElement("field");
				    from1.setAttribute("name", "from");
				    from1.appendChild(doc.createTextNode(from));
				    sub_RootElement.appendChild(from1);
				    
		            Element cc1 = doc.createElement("field");
		            cc1.setAttribute("name", "cc");
				    cc1.appendChild(doc.createTextNode(cc));
				    sub_RootElement.appendChild(cc1);
				    
				    Element datetime1 = doc.createElement("field");
				    datetime1.setAttribute("name", "datetime");
				    datetime1.appendChild(doc.createTextNode(dateTime));
				    sub_RootElement.appendChild(datetime1);
		    
				    Element subject1 = doc.createElement("field");
				    subject1.setAttribute("name", "subject");
				    subject1.appendChild(doc.createTextNode(subject));
				    sub_RootElement.appendChild(subject1);
		    
				    Element body1 = doc.createElement("field");
				    body1.setAttribute("name", "body");
				    body1.appendChild(doc.createTextNode(bodytext));
				    sub_RootElement.appendChild(body1);
				    
		         // output    
		            TransformerFactory transformerFactory = TransformerFactory.newInstance();
		            Transformer transformer = transformerFactory.newTransformer();
		            DOMSource source = new DOMSource(doc);
		            
		            StreamResult result = new StreamResult(new File(s));
				    transformer.transform(source, result);
		 
	} catch (Exception e) {
		out.println(e.getMessage());
	}
 }
 
public static boolean checkEmailFields_attachent(PrintWriter out, Node mainNode, String original_Filename){
	 
	  boolean originlaFileNameCheck = false;
	 try {
		 
		/*if(mainNode.hasNode("Email_Fields")){
			Node Email_Fields=mainNode.getNode("Email_Fields");
			Node body=null;
			if(Email_Fields.hasNode("Body")){
				 body=Email_Fields.getNode("Body");
				if(body.hasNodes()){
					NodeIterator itr=body.getNodes();
					while(itr.hasNext()){
						Node bodyListNode=itr.nextNode();
						if(bodyListNode.hasProperty("Body_Text")){
							String Attachment_Name=bodyListNode.getProperty("Body_Text").getString().trim();
							out.println("alldata: "+Attachment_Name);
							if(Attachment_Name.equals(original_Filename.trim())){
								out.println("Attachment_Name equals");
								originlaFileNameCheck=true;
							}
						}
					}
				}
			}
		}*/
		 Node Email_Fields=null;
		 Node body=null;
		 String path="";
		 if(!GmailMethods.isNullString(original_Filename)){
				if(mainNode.hasNode("Email_Fields")){
					 Email_Fields=mainNode.getNode("Email_Fields");
					if(Email_Fields.hasNode("Attachment_With_Type")){
						body=Email_Fields.getNode("Attachment_With_Type");
						path=body.getPath();
					}
				}
				String Attachment_Name="";
				Node obj=null;
				
				Workspace workspace = session.getWorkspace();
                String slingqery = "select [Attachment_Name] from [nt:base] where (contains('Attachment_Name','" + original_Filename
						+ "')) and ISDESCENDANTNODE('"+path+"')";
				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();
				NodeIterator iterator = queryResult.getNodes();
				while (iterator.hasNext()) {

					obj = iterator.nextNode();
					Attachment_Name = obj.getProperty("Attachment_Name").getString();
					
				}
				out.println("Attachment_Name_query: "+Attachment_Name);
				Attachment_Name=(GmailMethods.isNullString(Attachment_Name)) ? "" : Attachment_Name;
				if(Attachment_Name.equals(original_Filename)){
					out.println("Attachment_Name equals");
					originlaFileNameCheck=true;
				}
		 }
		 	 
	} catch (Exception e) {
		out.println(e.getMessage());
	}
	return originlaFileNameCheck;
 }
 
public static void createXmlNode_Save_Xml_sling(PrintWriter out, Node subject_replace_node_mainnode, String subject_replace, String bodyText, String to, String from, String cc, String dateTime, String subject, String mainnodeCount){
	 try {
		 
		 String forid1= String.valueOf(Integer.parseInt(mainnodeCount)+1);
		 Node mainInsideText_unstructured_Node=null;
		 String file_name=subject_replace+"_"+forid1+".xml";
		 if(subject_replace_node_mainnode.hasNode(file_name)){
			 mainInsideText_unstructured_Node=subject_replace_node_mainnode.getNode(file_name);
		 }else{
			 mainInsideText_unstructured_Node=subject_replace_node_mainnode.addNode(file_name, "nt:unstructured");
		 }
		 
		 if(mainInsideText_unstructured_Node.hasProperty("Flag")){
			 
		 }else{
			 mainInsideText_unstructured_Node.setProperty("Flag", new String());
		 }
		 
        if(mainInsideText_unstructured_Node.hasProperty("proccessedData")){
			 
		 }else{
			 mainInsideText_unstructured_Node.setProperty("proccessedData", new String());
		 }
		 
        if(mainInsideText_unstructured_Node.hasProperty("file_link")){
			 
		 }else{
			 String file_link="http://35.199.31.139:8080/bernhard/bin/cpm/nodes/property.bin"+mainInsideText_unstructured_Node.getPath()+"/"+mainInsideText_unstructured_Node.getName()+"/_jcr_content?name=jcr%3Adata";
			 mainInsideText_unstructured_Node.setProperty("file_link", file_link);
		 }

        if(mainInsideText_unstructured_Node.hasProperty("file_path")){
			 
		 }else{
			 String file_path="http://35.199.31.139:8080/bernhard"+mainInsideText_unstructured_Node.getPath()+"/"+mainInsideText_unstructured_Node.getName();
			 mainInsideText_unstructured_Node.setProperty("file_path", file_path);
		 }

        if(mainInsideText_unstructured_Node.hasProperty("tomcat_file_path")){
			 
		 }else{
			 mainInsideText_unstructured_Node.setProperty("tomcat_file_path", "/home/ubuntu/apache-tomcat-8.5.31/webapps/ROOT/scorpio/"+file_name);
		 }

        if(mainInsideText_unstructured_Node.hasProperty("tomcat_file_link")){
			 
		 }else{
			 mainInsideText_unstructured_Node.setProperty("tomcat_file_link", "http://35.199.31.139:8080/scorpio/"+file_name);
		 } 
        
        
        if(file_name.contains(".txt")){
       	 if(mainInsideText_unstructured_Node.hasProperty("xml_file_link")){
       		 
       	 }else{
       		 String txt_file_link="http://35.199.31.139:8080/bernhard/bin/cpm/nodes/property.bin"+mainInsideText_unstructured_Node.getPath()+"/"+mainInsideText_unstructured_Node.getName()+"/_jcr_content?name=jcr%3Adata";
					 String xml_file_link=txt_file_link.replace(".txt", ".xml");
					 mainInsideText_unstructured_Node.setProperty("xml_file_link", xml_file_link);
       	 }
        }

        Node file_content=null;
        if(mainInsideText_unstructured_Node.hasNode(file_name)){
       	 file_content=mainInsideText_unstructured_Node.getNode(file_name);
        }else{
       	 file_content=mainInsideText_unstructured_Node.addNode(file_name, "nt:file");
        }
        
            String file_root_path="/home/ubuntu/apache-tomcat-8.5.31/webapps/ROOT/scorpio/";
            File tomcateirectory=new File(file_root_path);
			   if(!tomcateirectory.exists()){
				   
				   tomcateirectory.mkdir();
				   out.println("folder created in Tomcate");
			   }
            
            createXml(out, file_name, to, from, cc, dateTime, subject, bodyText, file_root_path+"/"+file_name);
//			     String s=file_root_path+"/"+file_name;
            
            Node jcrNode = null;
            InputStream stream = new FileInputStream(file_root_path+file_name);
            jcrNode = file_content.addNode("jcr:content", "nt:resource");
            jcrNode.setProperty("jcr:data", stream);
			 jcrNode.setProperty("jcr:mimeType", "attach");
				 session.save();
		 
		 
	} catch (Exception e) {
		out.println(e.getMessage());
	}
}

public static void AttachmentSaveData(PrintWriter out, String contentType, Message message, Node Email_Fields, Node subject_replace_node_mainnode, String subject_replace){
	try {
		
		 if (contentType.contains("multipart")) {
				
				Multipart multiPart = (Multipart) message.getContent();
				int numberOfParts = multiPart.getCount();
				MimeBodyPart part=null;
				for (int partCount = 0; partCount < numberOfParts; partCount++) {
					 part = (MimeBodyPart) multiPart.getBodyPart(partCount);
					 String fileName = "";
					 String originalName="";
					if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
						originalName = part.getFileName();  // for single attachment
						fileName=originalName.replace(" ", "_").replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9.]", "_").replaceAll("\\_+", "_").replaceFirst("\\_+", "");
						out.println("attachfilename: "+fileName);
						String fileextension=FilenameUtils.getExtension(fileName);
                        Node Attachment_With_Type=null;
						if(Email_Fields.hasNode("Attachment_With_Type")){
							Attachment_With_Type=Email_Fields.getNode("Attachment_With_Type");
						}else{
							
							if(!Email_Fields.hasNode("Attachment_With_Type")){
							    Attachment_With_Type=Email_Fields.addNode("Attachment_With_Type");
							    Attachment_With_Type.setProperty("jcr:count", String.valueOf(0));
						        session.save();
							}
							
								 
						}
						
						 Attachment_With_Type=Email_Fields.getNode("Attachment_With_Type");
						 String count1=Attachment_With_Type.getProperty("jcr:count").getString();
						 String forid2= String.valueOf(Integer.parseInt(count1)+1);
						 boolean originalcheckFileName= checkEmailFields_attachent(out, subject_replace_node_mainnode, originalName);
						 out.println("boolean: "+originalcheckFileName);
						 if(!originalcheckFileName){
						 if(!Attachment_With_Type.hasNode(forid2)){
							 Node attachmentlist=Attachment_With_Type.addNode(forid2);
							 attachmentlist.setProperty("Attachment_Name", originalName);
							 attachmentlist.setProperty("Attachment_Name_replace", fileName);
							 attachmentlist.setProperty("Attachment_Type", fileextension);
							 Attachment_With_Type.setProperty("jcr:count",String.valueOf(Integer.parseInt(count1)+1));
							 session.save();
							 long nodeSize=0;
							 if(Attachment_With_Type.hasNodes()){
								 nodeSize=Attachment_With_Type.getNodes().getSize();
								 create_AttachmentNode_SaveAttach_Sling(out, subject_replace_node_mainnode, subject_replace, part, nodeSize, fileName);
								 session.save();
							 }
					}
						 
						 
						 }

					}
				}
			}
		
	} catch (Exception e) {
		out.println(e.getMessage());
	}
}
	
public static String attachmentCheckDublicate(PrintWriter out, String contentType, Message message){
	
	 String fileName = "";
	try {
		
		if (contentType.contains("multipart")) {
			
			Multipart multiPart = (Multipart) message.getContent();
			int numberOfParts = multiPart.getCount();
			MimeBodyPart part=null;
			for (int partCount = 0; partCount < numberOfParts; partCount++) {
				 part = (MimeBodyPart) multiPart.getBodyPart(partCount);
				 
				if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
					fileName = part.getFileName();  // for single attachment
					
				}
			}
		}
		
	} catch (Exception e) {
		out.println(e.getMessage());
	}
	return fileName;
}

public static void checkEmailFields(PrintWriter out, Node mainNode, String emailBody, String subject_replace, String bodyText, String toList, String from, String ccList,String sentDate, String subject, 
		 String contentType, Message message){

	
	try {

		if(!GmailMethods.isNullString(emailBody)){
			String path="";
			String body_Text = "";
			Node Email_Fields=null;
			Node body=null;
          	 /*StringBuilder sb = new StringBuilder();
             sb.append(emailBody);*/
		if(mainNode.hasNode("Email_Fields")){
			 Email_Fields=mainNode.getNode("Email_Fields");
			
			
			if(Email_Fields.hasNode("Body")){
				body=Email_Fields.getNode("Body");
				path=body.getPath();
			}
		}

		Workspace workspace = session.getWorkspace();
//		out.println("inside :: "+emailBody);

		String slingqery = "select [Body_Text] from [nt:base] where (contains('Body_Text','" + emailBody
				+ "')) and ISDESCENDANTNODE('"+path+"')";
		
//		out.println("slingqery:: "+slingqery);
		Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
		QueryResult queryResult = query.execute();
		NodeIterator iterator = queryResult.getNodes();
		Node obj = null;
		while (iterator.hasNext()) {

			obj = iterator.nextNode();
			body_Text = obj.getProperty("Body_Text").getValue().getString();
			
		}
		out.println("valuevesselname: "+body_Text);
		
		out.println("inside data: "+emailBody);

//		body_Text=(GmailMethods.isNullString(body_Text)) ? "" : body_Text;
		out.println("length: "+emailBody.length());
		
		
		
		if(body_Text.equals(emailBody)){
			out.println("body equals");
			String fileName=attachmentCheckDublicate(out, contentType, message);
			 
			 if(!GmailMethods.isNullString(fileName)){
				  boolean originalcheckFileName= checkEmailFields_attachent(out, mainNode, fileName);
				  out.println("originalcheckFileName: "+originalcheckFileName);
			      if(!originalcheckFileName){
			    	  AttachmentSaveData(out, contentType, message, Email_Fields, mainNode, subject_replace);
			      }
			 }
			
		}
//		else if(body_Text==""){
		else{
			
			 String body_Text1="";
			 if(body_Text==""){
			   
				out.println("inside if");
				String emaillength="";
				int len=emailBody.length();
				emaillength=String.valueOf(len);
				out.println("emaillength: "+emaillength);
				Workspace workspace1 = session.getWorkspace();
				String slingqer = "select [Body_Content_Length] from [nt:base] where (contains('Body_Content_Length','" +emaillength
						+ "')) and ISDESCENDANTNODE('"+path+"')";
				
				out.println("slingqer: "+slingqer);
				     Query query1 = workspace1.getQueryManager().createQuery(slingqer, Query.JCR_SQL2);
				     QueryResult queryResult1 = query1.execute();
				     NodeIterator iterator1 = queryResult1.getNodes();
					Node obj1 = null;
					while (iterator1.hasNext()) {

						 obj1 = iterator1.nextNode();
						 body_Text1 = obj1.getProperty("Body_Content_Length").getValue().getString();
						 out.println("bodylength: "+body_Text1);
						
					}
					
					if(body_Text1.equals(emaillength)){
					out.println("body equals length");
					String fileName=attachmentCheckDublicate(out, contentType, message);
					 
					 if(!GmailMethods.isNullString(fileName)){
						  boolean originalcheckFileName= checkEmailFields_attachent(out, mainNode, fileName);
						  out.println("originalcheckFileName: "+originalcheckFileName);
					      if(!originalcheckFileName){
					    	  AttachmentSaveData(out, contentType, message, Email_Fields, mainNode, subject_replace);
					      }
					 }
					}// if
			 }
			
					 if(body_Text1=="" && body_Text==""){
						 out.println("not matched ");
						 String count=body.getProperty("jcr:count").getString();
						 String forid= String.valueOf(Integer.parseInt(count)+1);
						 if(!body.hasNode(forid)){
							 Node bodylist=body.addNode(forid);
							 bodylist.setProperty("Body_Text", emailBody);
							 bodylist.setProperty("Body_Content_Length", String.valueOf(emailBody.length()));
							 body.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
							 session.save();
					}
						 
						 String mainnodeCount=mainNode.getProperty("jcr:count").getString();
						 createTextNode_Save_txt_sling(out, mainNode, subject_replace, bodyText, mainnodeCount); 
						 createXmlNode_Save_Xml_sling(out, mainNode, subject_replace, bodyText, toList, from, ccList, sentDate, subject, mainnodeCount);
			                 
						 mainNode.setProperty("jcr:count",String.valueOf(Integer.parseInt(mainnodeCount)+1));
						 
						 AttachmentSaveData(out, contentType, message, Email_Fields, mainNode, subject_replace);
						 session.save();
					 }
					 
		
			 /*else{
			
			 out.println("not matched ");
			 String count=body.getProperty("jcr:count").getString();
			 String forid= String.valueOf(Integer.parseInt(count)+1);
			 if(!body.hasNode(forid)){
				 Node bodylist=body.addNode(forid);
				 bodylist.setProperty("Body_Text", emailBody);
				 bodylist.setProperty("Body_Content_Length", emailBody.length());
				 body.setProperty("jcr:count",String.valueOf(Integer.parseInt(count)+1));
				 session.save();
		}
			 
			 String mainnodeCount=mainNode.getProperty("jcr:count").getString();
			 createTextNode_Save_txt_sling(out, mainNode, subject_replace, bodyText, mainnodeCount); 
			 createXmlNode_Save_Xml_sling(out, mainNode, subject_replace, bodyText, toList, from, ccList, sentDate, subject, mainnodeCount);
                 
			 mainNode.setProperty("jcr:count",String.valueOf(Integer.parseInt(mainnodeCount)+1));
			 
			 AttachmentSaveData(out, contentType, message, Email_Fields, mainNode, subject_replace);
			 session.save();
		}// else close 
*/		}
		
		}
	} catch (Exception e) {
		
		out.println(e.getMessage());
	}
	

}

}
