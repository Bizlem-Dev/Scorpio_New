package com.readGmail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
//import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.AndTerm;
import javax.mail.search.BodyTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.commons.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;

public class GmailMethods {
	
	private static DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	private static DocumentBuilder docBuilder ;
	private static Session session;
	private static Properties properties;
	private static Store store ;
	private static Folder folder;

	
	public static Properties getServerConnect(Gmail_Pojo gmail_pj) {
		 
		     properties = new Properties();
		try{
			 
			 
			// server setting
			properties.put(String.format("mail.%s.host", gmail_pj.getProtocol()), gmail_pj.getHost());
			properties.put(String.format("mail.%s.port", gmail_pj.getProtocol()), gmail_pj.getPort());
			
			// SSL setting
			properties.setProperty(String.format("mail.%s.socketFactory.class", gmail_pj.getProtocol()),"javax.net.ssl.SSLSocketFactory");
			properties.setProperty(String.format("mail.%s.socketFactory.fallback", gmail_pj.getProtocol()), "false");
			properties.setProperty(String.format("mail.%s.socketFactory.port", gmail_pj.getProtocol()), String.valueOf(gmail_pj.getPort()));
			
			session = javax.mail.Session.getDefaultInstance(properties);
			// connects to the message store
		    store = session.getStore(gmail_pj.getProtocol());
			store.connect(gmail_pj.getUserName(), gmail_pj.getPassWord());
			
		}catch(Exception e){
			e.printStackTrace();
		}
			return properties;
		}

		public static String parseAddresses(Address[] address) {
			String listAddress = "";

			if (address != null) {
				for (int i = 0; i < address.length; i++) {
					listAddress += address[i].toString() + ", ";
				}
			}
			if (listAddress.length() > 1) {
				listAddress = listAddress.substring(0, listAddress.length() - 2);
			}

			return listAddress;
		}
		
		public static Date parseDate(String dateStr) throws ParseException {
			if(StringUtils.isEmpty(dateStr)) {
				return null;
			}
			DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
			Date date = (Date)formatter.parse(dateStr);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return date;
		}


		public static String formatDate(Date date) throws ParseException {
			if(null == date) {
				return null;
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.YEAR);
		}
		
		public static String getText(Part p) throws MessagingException, IOException {
			// System.out.println(p.getContentType());
			String text = null;
			if (p.isMimeType("text/html")) {

				String s = (String) p.getContent();
				// textIsHtml = p.isMimeType("text/html");
				return s;
			}
			if (p.isMimeType("multipart/alternative")) {
				// prefer html text over plain text
				Multipart mp = (Multipart) p.getContent();
				for (int i = 0; i < mp.getCount(); i++) {
					Part bp = mp.getBodyPart(i);
					if (bp.isMimeType("text/plain")) {
						if (text == null) {
							text = getText(bp);
						}
						continue;
					} else if (bp.isMimeType("text/html")) {
						String s = getText(bp);
						if (s != null) {
							return s;
						}
					} else {
						return getText(bp);
					}
				}
				return text;
			} else if (p.isMimeType("multipart/*")) {
				Multipart mp = (Multipart) p.getContent();
				for (int i = 0; i < mp.getCount(); i++) {
					String s = getText(mp.getBodyPart(i));
					// text = text + "\n" + Jsoup.parse(s).text();
					if (s != null) {
						return s;
					}
				}
			}

			return null;
		}
		
		public static JSONObject GmailBodyText(String contentType, Message message, PrintWriter out){
			
			JSONObject body_plus_messegecontent=new JSONObject();
			try {
				Object content;
				String messageContent = "";
				String fileName = "";
				String messagetext = "";
				
				if (contentType.contains("TEXT/PLAIN") || contentType.contains("TEXT/HTML")) {
					content = message.getContent();
					if (content != null) {
						messageContent = content.toString();
					}
				}else if (contentType.contains("multipart")) {
					Multipart multiPart = (Multipart) message.getContent();

					int numberOfParts = multiPart.getCount();
					MimeBodyPart part=null;
					for (int partCount = 0; partCount < numberOfParts; partCount++) {
						 part = (MimeBodyPart) multiPart.getBodyPart(partCount);
						if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
							fileName = part.getFileName();
						} else {
							messageContent = GmailMethods.getText(part);

						}
					}
				} // else if close
				
				
				// parse html
				if(!GmailMethods.isNullString(messageContent)){
//            	Document doc = Jsoup.parse(messageContent);
//				messagetext = doc.body().text();
					messagetext = org.jsoup.Jsoup.parse(messageContent).text();
//					out.println("inside : "+messagetext);
				
				   body_plus_messegecontent.put("messagetext", messagetext);
				   body_plus_messegecontent.put("messageContent", messageContent);
				
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			    out.println(e.getMessage());
			}
			
			return body_plus_messegecontent;
			
		}
		
		
		public static String signature(String messagetext , String messageContent, PrintWriter out) {

			String signature = "NA";
			Document doc = Jsoup.parse(messageContent);
            Elements eles = doc.select("div.gmail_signature");
            try{
			
			if (eles.size() == 1) {
                signature = eles.text();
            }else{
            	String orinal_str_lowercase = messagetext.toLowerCase();
    			String str_search1 = "Thanks & Regards";
    			str_search1 = str_search1.toLowerCase();
    			String str_search1_1 = "Thank & Regards";
    			String str_search1_2 = "Thanks & Regard";
    			String str_search1_3 = "Thank & Regard";
    			String str_search2 = "Thanks and Regards";
    			str_search2 = str_search2.toLowerCase();
    			String str_search3 = "Regards";
    			str_search3 = str_search3.toLowerCase();
    			String str_search4 = "Regard";
    			str_search4 = str_search4.toLowerCase();
                String str_search5 = "Thanks";
    			str_search5 = str_search5.toLowerCase();
                String str_search6 = "Thank";
    			str_search6 = str_search6.toLowerCase();
    			if (orinal_str_lowercase.contains(str_search1)) {
    				if(orinal_str_lowercase.indexOf(str_search1)!=-1){
    				signature = messagetext.substring(orinal_str_lowercase.indexOf(str_search1));
    				}
               } else if (orinal_str_lowercase.contains(str_search2)) {
                	
                	if(orinal_str_lowercase.indexOf(str_search2)!=-1){
    				signature = messagetext.substring(orinal_str_lowercase.indexOf(str_search2));
                	}
    			} else if (orinal_str_lowercase.contains(str_search1_1)) {
    				if(orinal_str_lowercase.indexOf(str_search1_1)!=-1){
    				signature = messagetext.substring(orinal_str_lowercase.indexOf(str_search1_1));
    				}
    			} else if (orinal_str_lowercase.contains(str_search1_2)) {
    				if(orinal_str_lowercase.indexOf(str_search1_2)!=-1){
    				signature = messagetext.substring(orinal_str_lowercase.indexOf(str_search1_2));
    				}
    			} else if (orinal_str_lowercase.contains(str_search1_3)) {
    				if(orinal_str_lowercase.indexOf(str_search1_3)!=-1){
    				signature = messagetext.substring(orinal_str_lowercase.indexOf(str_search1_3));
    				}
    			} else if (orinal_str_lowercase.contains(str_search3)) {
    				if(orinal_str_lowercase.indexOf(str_search3)!=-1){
    				signature = messagetext.substring(orinal_str_lowercase.indexOf(str_search3));
    				}
    			} else if (orinal_str_lowercase.contains(str_search4)) {
    				if(orinal_str_lowercase.indexOf(str_search4)!=-1){
    				signature = messagetext.substring(orinal_str_lowercase.indexOf(str_search4));
    				}
    			} else if (orinal_str_lowercase.contains(str_search5)) {
    				
    				if(orinal_str_lowercase.indexOf(str_search5)!=-1){
    				signature = messagetext.substring(orinal_str_lowercase.indexOf(str_search5));
    				}
    			} else if (orinal_str_lowercase.contains(str_search6)) {
    				if(orinal_str_lowercase.indexOf(str_search5)!=-1){
    				signature = messagetext.substring(orinal_str_lowercase.indexOf(str_search5));
    				}
    			} else {
    				signature="NA";
    			}
			
         } // else close
            }catch(Exception e){
            	out.println(e.getMessage());
            }
			return signature;

		}
		
		
		public static int getFileNameSuffix(File file){
			int max = 0;
			try{
	    	String file_name_suffix=null;
	    	int file_name_suffix_int=0;
	    	File[] listOfFiles = file.listFiles();
	    	
	    	List<Integer> num = new ArrayList<Integer>();
			      for (int i = listOfFiles.length-1; i >=0; i--) {
				      if (listOfFiles[i].isFile()) {
				    	    String file_name=listOfFiles[i].getName();
				    	    file_name_suffix=file_name.substring(file_name.lastIndexOf("_")+1,file_name.lastIndexOf("."));
				    	    file_name_suffix_int=Integer.parseInt(file_name_suffix);
				    	    num.add(file_name_suffix_int);
				    	} 
				  }
			    Integer[] arr = new Integer[num.size()];
					      arr = num.toArray(arr);
			     max = Collections.max(Arrays.asList(arr));
//		        System.out.println("File Prefix : " + max);
		        
			}catch(Exception e){
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		        
			    return max;
	    }
		
		public static void create_text_Xml_attachment(String contentType, Message message, ResourceBundle bundle, Gmail_Pojo gmail_pj, PrintWriter out){
			
			try {
				
				String filename=gmail_pj.getSubject_replace();
				String file_path2=null;
//				File file = new File(file_root_path+"\\"+subject_replace);
				File file = new File(gmail_pj.getFile_root_path()+"/"+gmail_pj.getSubject_replace());
				int file_name_suffix_int=0;
				
				if(file.isDirectory()){
					
					if(file.list().length>0){
						file_name_suffix_int=GmailMethods.getFileNameSuffix(file);
						filename=filename+"_"+String.valueOf(file_name_suffix_int+1);
					}else{
						filename=filename+"_1";
						System.out.println("else filename : "+filename);
					}
					
				}else{
					System.out.println("This is not a directory lets Cereate directory");
				}
				
//				file_path2=file_root_path+"\\"+subject_replace+"\\"+filename;
				file_path2=gmail_pj.getFile_root_path()+"/"+gmail_pj.getSubject_replace()+"/"+filename;
				System.out.println("file_path2 : "+file_path2);
				File file2 = new File(file_path2);
				if (!file2.exists()) {
			            if (file2.mkdir()) {
			            	
			            }
		            }  
				
				docBuilder = docFactory.newDocumentBuilder();
				org.w3c.dom.Document doc =docBuilder.newDocument();
				Element rootElement = doc.createElement("add");
			    doc.appendChild(rootElement);
			    Element sub_RootElement = doc.createElement("doc");
			            rootElement.appendChild(sub_RootElement);
			            
			         // append child elements to root element
			            Element id = doc.createElement("field");
			            id.setAttribute("name", "id");
			            id.appendChild(doc.createTextNode(filename+".xml"));
					    sub_RootElement.appendChild(id);
					    
					    Element category = doc.createElement("field");
					    category.setAttribute("name", "category");
					    category.appendChild(doc.createTextNode(""));
					    sub_RootElement.appendChild(category);
					    
					    Element to1 = doc.createElement("field");
					    to1.setAttribute("name", "to");
					    to1.appendChild(doc.createTextNode(gmail_pj.getTo()));
					    sub_RootElement.appendChild(to1);
			            
					    Element from1 = doc.createElement("field");
					    from1.setAttribute("name", "from");
					    from1.appendChild(doc.createTextNode(gmail_pj.getFrom()));
					    sub_RootElement.appendChild(from1);
					    
			            Element cc1 = doc.createElement("field");
			            cc1.setAttribute("name", "cc");
					    cc1.appendChild(doc.createTextNode(gmail_pj.getCc()));
					    sub_RootElement.appendChild(cc1);
					    
					    Element datetime1 = doc.createElement("field");
					    datetime1.setAttribute("name", "datetime");
					    datetime1.appendChild(doc.createTextNode(gmail_pj.getDatetime()));
					    sub_RootElement.appendChild(datetime1);
			    
					    Element subject1 = doc.createElement("field");
					    subject1.setAttribute("name", "subject");
					    subject1.appendChild(doc.createTextNode(gmail_pj.getSubject()));
					    sub_RootElement.appendChild(subject1);
			    
					    Element body1 = doc.createElement("field");
					    body1.setAttribute("name", "body");
					    body1.appendChild(doc.createTextNode(gmail_pj.getBody()));
					    sub_RootElement.appendChild(body1);
					    
			         // output    
			            TransformerFactory transformerFactory = TransformerFactory.newInstance();
			            Transformer transformer = transformerFactory.newTransformer();
			            DOMSource source = new DOMSource(doc);
			            
			            StreamResult result = new StreamResult(new File(gmail_pj.getFile_root_path()+"/"+gmail_pj.getSubject_replace()+"/"+filename+".xml"));
					    transformer.transform(source, result);
					    
					    /*result = new StreamResult(new StringWriter());
					    transformer.transform(source, result);
					    String xmlString xmlString = result.getWriter().toString();
					    StreamResult console = new StreamResult(System.out);
					    transformer.transform(source, console);*/
			            
				
//				writeUsingFileWriter(new File(file_root_path+"\\"+subject_replace+"\\"+filename+".txt"),subject_replace,body);
				writeUsingFileWriter(new File(gmail_pj.getFile_root_path()+"/"+gmail_pj.getSubject_replace()+"/"+filename+".txt"),gmail_pj.getSubject_replace(),gmail_pj.getBody(), out);
	
				if (contentType.contains("multipart")) {
						
						Multipart multiPart = (Multipart) message.getContent();
						int numberOfParts = multiPart.getCount();
						MimeBodyPart part=null;
//						String attachFiles = "";
						for (int partCount = 0; partCount < numberOfParts; partCount++) {
							 part = (MimeBodyPart) multiPart.getBodyPart(partCount);
							 String fileName = "";
							if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
								fileName = part.getFileName();  // for single attachment
								fileName=fileName.replace(" ", "_");
								fileName=fileName.replaceAll("[^\\p{ASCII}]", "").replaceAll("[^a-zA-Z0-9.]", "_").replaceAll("\\_+", "_").replaceFirst("\\_+", "").replaceAll("答复", "");
//								attachFiles += fileName + ", "; // for multiple attachment
//								part.saveFile(new File(file_path2+"\\"+filename+"_"+fileName));
								part.saveFile(new File(file_path2+"/"+filename+"_"+fileName));
							}
						}
					}
				
				
			} catch (Exception e) {
//				e.printStackTrace();
				out.println(e.getMessage());
//				throw new RuntimeException(e);
			}
			
		}
		
		private static void writeUsingFileWriter(File file,String subject_replace,String body, PrintWriter out) {
			
	        FileWriter fr = null;
	        try {
	            fr = new FileWriter(file);
	            fr.write(subject_replace);
	            fr.write(System.lineSeparator());
	            fr.write(body);
	            fr.write(System.lineSeparator());
	        } catch (IOException e) {
//	            e.printStackTrace();
	            out.println(e.getMessage());
	        }finally{
	            //close resources
	            try {
	                fr.close();
	            } catch (IOException e) {
//	                e.printStackTrace();
	                out.println(e.getMessage());
	            }
	        }
	    }
		
		public static boolean isNullString (String p_text){
			if(p_text != null && p_text.trim().length() > 0 && !"null".equalsIgnoreCase(p_text.trim())){
				return false;
			}
			else{
				return true;
			}
		}
		
		public static boolean isNullNode (Node p_text) throws RepositoryException{
			String nodeName=p_text.getName();
			
			if(p_text != null && nodeName.trim().length() > 0 && !"null".equalsIgnoreCase(nodeName.trim())){
				return false;
			}
			else{
				return true;
			}
		}

		public static Folder openFolder(String folderName) throws Exception {
	        
	        // Open the Folder
	        folder = store.getDefaultFolder();
	        
	        folder = folder.getFolder(folderName);
	        
	        if (folder != null) {
	        
	        // try to open read/write and if that fails try read-only
	        try {
	            
	            folder.open(Folder.READ_WRITE);
	            
	        } catch (MessagingException ex) {
	            folder.open(Folder.READ_ONLY);
	            
	        }
	        
		}// 
			return folder;
	        
	        
	    }
		
		public static void closeFolder() throws Exception {
	        folder.close(false);
	    }
		
		public static void disconnect() throws Exception {
	        store.close();
	    }
		
		public static SearchTerm searchMailterm(PrintWriter out){
			
			SearchTerm term = null;
			try {
				
				int x = -12; //-2
				int y = 1; //1
				Calendar cal = GregorianCalendar.getInstance();
				cal.add(Calendar.DAY_OF_YEAR, y);
				Date tomorrow = cal.getTime();
				//out.println(tomorrow);
				cal.add(Calendar.DAY_OF_YEAR, x);
				Date oneDaysAgo = cal.getTime();
				//out.println(oneDaysAgo);

				out.println(oneDaysAgo + " === " + tomorrow);
				
				SearchTerm olderThan = new ReceivedDateTerm(ComparisonTerm.LT, tomorrow);
				SearchTerm newerThan = new ReceivedDateTerm(ComparisonTerm.GT, oneDaysAgo);
				term = new AndTerm(olderThan, newerThan); // concat the search terms
				//term = new AndTerm(new SubjectTerm(emailSubject),new BodyTerm(emailSubject)); // concat the search terms
				
				/*Calendar cal = null;
				cal = Calendar.getInstance();
				Date minDate = new Date(cal.getTimeInMillis());  // get today date
				cal.add(Calendar.DAY_OF_MONTH, 1);               // add 1 day
				Date maxDate = new Date(cal.getTimeInMillis());  // get tomorrow date
				
				out.println(maxDate + " === " + minDate);
				SearchTerm minDateTerm = new ReceivedDateTerm(ComparisonTerm.GE, minDate);
	            SearchTerm maxDateTerm = new ReceivedDateTerm(ComparisonTerm.LE, maxDate);
	            
	            term = new AndTerm(minDateTerm, maxDateTerm);    // concat the search terms
*/				out.println("term: "+term);
			} catch (Exception e) {
				out.println(e.getMessage());
			}
			return term;
		}
		public static SearchTerm searchSubjectMailterm(String emailSubject,PrintWriter out){
			
			SearchTerm term = null;
			try {
				
				int x = -12; //-2
				int y = 1; //1
				Calendar cal = GregorianCalendar.getInstance();
				cal.add(Calendar.DAY_OF_YEAR, y);
				Date tomorrow = cal.getTime();
				//out.println(tomorrow);
				cal.add(Calendar.DAY_OF_YEAR, x);
				Date oneDaysAgo = cal.getTime();
				//out.println(oneDaysAgo);

				out.println(oneDaysAgo + " === " + tomorrow);
				
				SearchTerm olderThan = new ReceivedDateTerm(ComparisonTerm.LT, tomorrow);
				SearchTerm newerThan = new ReceivedDateTerm(ComparisonTerm.GT, oneDaysAgo);
				//term = new AndTerm(olderThan, newerThan); // concat the search terms
				term = new AndTerm(new SubjectTerm(emailSubject),new BodyTerm(emailSubject)); // concat the search terms
				
				/*Calendar cal = null;
				cal = Calendar.getInstance();
				Date minDate = new Date(cal.getTimeInMillis());  // get today date
				cal.add(Calendar.DAY_OF_MONTH, 1);               // add 1 day
				Date maxDate = new Date(cal.getTimeInMillis());  // get tomorrow date
				
				out.println(maxDate + " === " + minDate);
				SearchTerm minDateTerm = new ReceivedDateTerm(ComparisonTerm.GE, minDate);
	            SearchTerm maxDateTerm = new ReceivedDateTerm(ComparisonTerm.LE, maxDate);
	            
	            term = new AndTerm(minDateTerm, maxDateTerm);    // concat the search terms
*/				out.println("term: "+term);
			} catch (Exception e) {
				out.println(e.getMessage());
			}
			return term;
		}
		
		public int getMessageCount() throws Exception {
	        return folder.getMessageCount();
	    }
	     
	    public int getNewMessageCount() throws Exception {
	        return folder.getNewMessageCount();
	    }
	    
	    public static void printAllMailsDetails(Gmail_Pojo gmail_pj, PrintWriter out){
	    	
	    	out.println("from: "+gmail_pj.getFrom());
        	out.println("subject: "+gmail_pj.getSubject());
        	out.println("toList: "+gmail_pj.getTo());
        	out.println("ccList: "+gmail_pj.getCc());
        	out.println("sentDate: "+gmail_pj.getDatetime());
        	out.println("bodyText: "+gmail_pj.getBody());
        	out.println("mailSentDate: "+gmail_pj.getMailSentDate());
        	out.println("mailReceivedDate: "+gmail_pj.getMailReceivedDate());
        	out.println("Signature: "+gmail_pj.getSignature());
	    }
	    
	    public static String seen_UnseenEmails(PrintWriter out, Message message){
	    	
	    	String seen="";
	    	try {
				
	    		boolean isseen = message.getFlags().contains(Flags.Flag.SEEN);
	    		if (isseen == true) {
            		seen = "1";
            	//	out.println("seen : "+seen);
            	}else{
            		seen = "0";
            		//out.println("Unseen : "+seen);
            	} 
	    		
			} catch (Exception e) {
				out.println(e.getMessage());
			}
			return seen;
	    	
	    }
	    
		public static String getTextFromMessage(Message message) throws Exception {
			
			try{
		    if (message.isMimeType("text/plain")){
		        return message.getContent().toString();
		    }else if (message.isMimeType("multipart/*")) {
		        String result = "";
		        MimeMultipart mimeMultipart = (MimeMultipart)message.getContent();
		        int count = mimeMultipart.getCount();
		        for (int i = 0; i < count; i ++){
		            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
		            if (bodyPart.isMimeType("text/plain")){
		                result = result + "\n" + bodyPart.getContent();
		                break;  //without break same text appears twice in my tests
		            } else if (bodyPart.isMimeType("text/html")){
		                String html = (String) bodyPart.getContent();
		                if(!isNullString(html)){
		                result = result + "\n" + Jsoup.parse(html).text();
		                }

		            }
		        }
		        return result;
		    }
		    return "";
			}catch(Exception e){
			  return e.getMessage();
			}
		}
}
