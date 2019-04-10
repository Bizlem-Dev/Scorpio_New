package ChangedStructureCurrent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.jcr.api.SlingRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.processcountMail.SaveMailCount;
import com.readGmail.GmailMethods;
import com.readGmail.Gmail_Pojo;
import com.reportinformationsystem.FifteenMinuteClass;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Properties;

@Component(immediate = true, metatype = false)
@Service(value = javax.servlet.Servlet.class)
@Properties({ @Property(name = "sling.servlet.paths", value = { "/timeReadChanged" }), })

public class GmailReadMailChanged extends SlingAllMethodsServlet {

	private static final long serialVersionUID = 1L;
	@Reference
	static SlingRepository repo;

	static Session session = null;
	static ResourceBundle bundle = ResourceBundle.getBundle("config");
	private static Folder folder;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		try {

			session = repo.login(new SimpleCredentials("admin", "admin".toCharArray()));
			out.println("hello");
			Gmail_Pojo gm = null;
			gm = new Gmail_Pojo();
			gm.setProtocol(bundle.getString("protocol"));
			gm.setHost(bundle.getString("host"));
			gm.setPort(bundle.getString("port"));
			gm.setUserName(bundle.getString("userName"));
			gm.setPassWord(bundle.getString("password"));

			processEmail(session, gm, out);

		} catch (Exception e) {
			out.println(e.getMessage());
		}

	}

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

	}

	public static void processEmail(Session session, Gmail_Pojo gmail_pj, PrintWriter out) {

		try {
			// session = repo.login(new SimpleCredentials("admin",
			// "admin".toCharArray()));
			out.println("length :: 123" + session);
			GmailMethods.getServerConnect(gmail_pj);
			folder = GmailMethods.openFolder(bundle.getString("GmailFolderName"));
			SearchTerm term = GmailMethods.searchMailterm(out);

			if (term != null) {
				// fetches new messages from server
				 //Message[] arrayMessages = folder.getMessages(); // without
				// search we can
				// use this
				Message[] arrayMessages = folder.search(term); // search
				// messages
				if (arrayMessages.length != 0) {
					out.println("length :: " + arrayMessages.length);

					// for (int i = arrayMessages.length - 1; i >= 0; i--) { //
					// this used for print last message only
					for (int i = 0; i < arrayMessages.length; i++) { // this
																		// will
																		// print
						 try{  // all
						// message
						Message message = arrayMessages[i];
						
						Date recDate = message.getReceivedDate();
//						recDate = (GmailMethods.isNullString(recDate)) ? "" : recDate;
						/*String mailReceivedDate = "";
						if (!GmailMethods.isNullString(recDate)) {
							Date date1 = GmailMethods.parseDate(recDate);
							mailReceivedDate = GmailMethods.formatDate(date1);
						}*/
						
						boolean schedulerReadMail=FifteenMinuteClass.returnTimeRange(recDate);
						if (schedulerReadMail == true) {
							
						out.println("ok");

						String seen = GmailMethods.seen_UnseenEmails(out, message);

						Address[] fromAddress = message.getFrom();
						String from = "";
						if (fromAddress.length > 0) {
							from = fromAddress[0].toString();
							out.println("from: " + from);

							if (from.indexOf("@") != -1) {
								from = from.substring(from.indexOf("@") + 1); // e.g.
																				// @google.com
							}
							if (from.indexOf(".") != -1) {
								from = from.substring(0, from.indexOf("."));
								from = (GmailMethods.isNullString(from)) ? "" : from;
								out.println("from: " + from);
							}

						}

						String subject = message.getSubject();

						String toList = GmailMethods.parseAddresses(message.getRecipients(RecipientType.TO));
						String ccList = GmailMethods.parseAddresses(message.getRecipients(RecipientType.CC));

						String sentDate = message.getSentDate().toString();
						sentDate = (GmailMethods.isNullString(sentDate)) ? "" : sentDate;
						
						
						

						// String dateStr = "Mon Jun 18 00:00:00 IST 2012";
						DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
						Date date3 = (Date) formatter.parse(sentDate);
						String formatedDate=null;
						String formatedDate1=null;

						if (date3 != null) {
							out.println("date3: " + date3);
							Calendar cal = Calendar.getInstance();
							cal.setTime(date3);

							 formatedDate = cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR) + " " + cal.get(Calendar.HOUR_OF_DAY) + ":"
									+ cal.get(Calendar.MINUTE);
							// formatedDate=convertStringToDate(formatedDate);
							formatedDate = (GmailMethods.isNullString(formatedDate)) ? "" : formatedDate;
							out.println("formatedDate : " + formatedDate);

							 formatedDate1 = cal.get(Calendar.DATE) + "-" + (cal.get(Calendar.MONTH) + 1) + "-"
									+ cal.get(Calendar.YEAR);
							formatedDate1 = (GmailMethods.isNullString(formatedDate1)) ? "" : formatedDate1;
							out.println("formatedDate1 : " + formatedDate1);
						}

						String mailSentDate = "";
						if (!GmailMethods.isNullString(sentDate)) {
							Date date = GmailMethods.parseDate(sentDate);
							mailSentDate = GmailMethods.formatDate(date);
						}

						

						String contentType = message.getContentType();
						out.println("subject  :: " + subject);
						String bodyText = GmailReadMailChanged.getTextFromMessage1(message);
						
						 String bodyCheck=getTextFromMessage2(message);
						        bodyCheck = bodyCheck.replace("RE: CXL     RE: ", "").replace("CXL     RE: ", "")
									.replace("REF: ", "").replace("RE: ", "").replace("FW: ", "").replace("#", "_")
									.replace(":", "_").replace(",", "_").replace("[", "").replace("]", "")
									.replaceAll("[\\//|:]+", "").trim().replaceAll("\\s{1,}", "_").replace("Fwd:", "")
									.replace("Fwd: Re :", "").replaceAll("[^\\p{ASCII}]", "").replaceAll("\\_+", "_")
									.replaceAll("[^a-zA-Z0-9.]", "_");
						        
						        bodyCheck = bodyCheck.toString().replaceAll("\\<.*?>",
								 "").replaceAll("[image:\\s]*.*]", "");
						        out.println("bodyCheck: "+bodyCheck);
						        
						      //  bodyCheck = (GmailMethods.isNullString(bodyCheck)) ? "" : bodyCheck;
						
						// bodyText = bodyText.toString().replaceAll("\\<.*?>",
						// "").replaceAll("[image:\\s]*.*]", "");
//						bodyText = (GmailMethods.isNullString(bodyText)) ? "" : bodyText;

						String Bodycheckmsg = bodyText.replace("RE: CXL     RE: ", "").replace("CXL     RE: ", "")
								.replace("REF: ", "").replace("RE: ", "").replace("FW: ", "").replace("#", "_")
								.replace(":", "_").replace(",", "_").replace("[", "").replace("]", "")
								.replaceAll("[\\//|:]+", "").trim().replaceAll("\\s{1,}", "_").replace("Fwd:", "")
								.replace("Fwd: Re :", "").replaceAll("[^\\p{ASCII}]", "").replaceAll("\\_+", "_")
								.replaceAll("[^a-zA-Z0-9.]", "_");

						Bodycheckmsg = (GmailMethods.isNullString(Bodycheckmsg)) ? "" : Bodycheckmsg;

						if (!GmailMethods.isNullString(subject)) {
							String subject_replace = subject.replace("RE: CXL     RE: ", "").replace("CXL     RE: ", "")
									.replace("REF: ", "").replace("RE: ", "").replace("FW: ", "").replace("#", "_")
									.replace(":", "_").replace(",", "_").replace("[", "").replace("]", "")
									.replaceAll("[\\//|:]+", "").trim().replaceAll("\\s{1,}", "_").replace("Fwd:", "")
									.replace("Fwd: Re :", "").replaceAll("[^\\p{ASCII}]", "").replaceAll("\\_+", "_");
							subject_replace = subject_replace.replace("&", "AND").replaceAll("\\*", "_");
							subject_replace = (GmailMethods.isNullString(subject_replace)) ? "" : subject_replace;

							if (subject_replace.contains("*")) {
								subject_replace = subject_replace.replaceAll("\\*", "").replace(" ", "_");
								subject_replace = (GmailMethods.isNullString(subject_replace)) ? "" : subject_replace;
							}
							Node content = null;
							Node subject_replace_node_mainnode = null;

							if (session.getRootNode().hasNode("GmailData1")) {
								content = session.getRootNode().getNode("GmailData1");
							} else {
								content = session.getRootNode().addNode("GmailData1");
								content.setProperty("jcr:count", String.valueOf(0));
								session.save();
							}

							if (content.hasNode(subject_replace)) {
								subject_replace_node_mainnode = content.getNode(subject_replace);

								dublicatecheck_mainNode(out, session, subject_replace, Bodycheckmsg, bodyText, toList,
										from, ccList, sentDate, subject, contentType, message,
										subject_replace_node_mainnode, formatedDate,formatedDate1, bodyCheck);
								out.println("same below");

							} else {
								subject_replace_node_mainnode = content.addNode(subject_replace);
								subject_replace_node_mainnode.setProperty("jcr:count", String.valueOf(0));
								session.save();
								
								String contentCount="";
								if (content.hasProperty("jcr:count")) {
									contentCount = content.getProperty("jcr:count").getString();
									out.println("contentCount: " + contentCount);
									content.setProperty("jcr:count", String.valueOf(Integer.parseInt(contentCount) + 1));
									session.save();
								}
								
								

								String mainnodeCount = "";
								if (subject_replace_node_mainnode.hasProperty("jcr:count")) {
									mainnodeCount = subject_replace_node_mainnode.getProperty("jcr:count").getString();
									out.println("else count: " + mainnodeCount);
									subject_replace_node_mainnode.setProperty("jcr:count",
											String.valueOf(Integer.parseInt(mainnodeCount) + 1));
									session.save();
								}
								Node returnNodeForSameTextAttachment = createTextNode_Save_txt_sling(out,
										subject_replace_node_mainnode, subject_replace, bodyText, mainnodeCount,
										Bodycheckmsg, sentDate, session, from, formatedDate,formatedDate1, bodyCheck);
								out.println("else returnNodeForSameTextAttachment: " + returnNodeForSameTextAttachment);

							
								
								AttachmentSaveData(out, contentType, message, returnNodeForSameTextAttachment,
										subject_replace, session);
								
								SaveMailCount.saveMailCount(out, session, formatedDate1, subject_replace_node_mainnode.getName(), formatedDate, subject_replace_node_mainnode.getPath());
								//original mails
								
								//session.save();
							} 
							}
							
						/*if (i == 1) {
						break;
					}*/
							
						}// check sheduler 

						
						 

						
						  }catch(Exception e){ continue; }
						 

					} // for close
				

					//GmailMethods.closeFolder();
					//GmailMethods.disconnect();
				} // term check null close
			}

		} catch (Exception e) {
			// e.printStackTrace();
			out.print(e.getMessage());
		}

	}
	
	public static String getTextFromMessage2(Message message) throws MessagingException, IOException {

		String result = "";
		if (message.isMimeType("text/plain")) {
			result = message.getContent().toString();
		} else if (message.isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			result = getTextFromMimeMultipart2(mimeMultipart);
		}
		return result;
	}
 
 public static String getTextFromMimeMultipart2(MimeMultipart mimeMultipart)  throws MessagingException, IOException{
	    
	   
	 String result = "";
		int count = mimeMultipart.getCount();
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			// if (bodyPart.isMimeType("text/plain")) {
			if (bodyPart.isMimeType("text/plain")) {
				result = result + "\n" + bodyPart.getContent();

				break; // without break same text appears twice in my tests
				// } else if (bodyPart.isMimeType("text/html")) {
			} else if (bodyPart.isMimeType("text/html")) {
				String html = (String) bodyPart.getContent();
				Document doc = Jsoup.parseBodyFragment(html);
				org.jsoup.nodes.Element body = doc.body();
				result = body.text();
			} else if (bodyPart.getContent() instanceof MimeMultipart) {
				result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
			}
		}
		return result;
	}
	

	public static String getTextFromMessage1(Message message) throws MessagingException, IOException {
		String result = "";
		// if (message.isMimeType("text/plain")) {
		// result = message.getContent().toString();
		// } else if (message.isMimeType("multipart/*")) {
		// MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
		// result = getTextFromMimeMultipart(mimeMultipart);
		// }

		if (message.isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			result = getTextFromMimeMultipart(mimeMultipart);
		}

		return result;
	}

	private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
		String result = "";
		int count = mimeMultipart.getCount();
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			// if (bodyPart.isMimeType("text/plain")) {
			// if (bodyPart.isMimeType("text/plain")) {
			// result = result + "\n" + bodyPart.getContent();
			//
			// break; // without break same text appears twice in my tests
			// // } else if (bodyPart.isMimeType("text/html")) {
			// } else if (bodyPart.isMimeType("text/html")) {
			// String html = (String) bodyPart.getContent();
			// Document doc = Jsoup.parseBodyFragment(html);
			// org.jsoup.nodes.Element body = doc.body();
			// result = body.wholeText();
			// } else if (bodyPart.getContent() instanceof MimeMultipart) {
			// result = result + getTextFromMimeMultipart((MimeMultipart)
			// bodyPart.getContent());
			// }

			if (bodyPart.isMimeType("text/html")) {
				String html = (String) bodyPart.getContent();
				// Document doc = Jsoup.parseBodyFragment(html);
				// org.jsoup.nodes.Element body = doc.body();
				result = html;
			} else if (bodyPart.getContent() instanceof MimeMultipart) {
				result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
			}

		}
		return result;
	}

	public static Node dublicatecheck_mainNode(PrintWriter out, Session session, String subject_replace,
			String emailBody, String bodyText, String toList, String from, String ccList, String sentDate,
			String subject, String contentType, Message message, Node subject_replace_node_mainnode,String formatedDate,String formatedDate1, String bodyCheck) {

		Node CheckedNode = null;

		try {

			if (session.getRootNode().hasNode("GmailData1")) {
				Node readslingnode = session.getRootNode().getNode("GmailData1");
				if (readslingnode.hasNodes()) {
					NodeIterator iterator = readslingnode.getNodes();
					while (iterator.hasNext()) {

						Node mainNodeList = iterator.nextNode();
						String mainNodeListName = mainNodeList.getName();

						if (mainNodeListName.equals(subject_replace)) {
							CheckedNode = mainNodeList;
							out.println("same mainnode");
							checkEmailFields_TimeSentMail(out, CheckedNode, emailBody, subject_replace, bodyText,
									toList, from, ccList, sentDate, subject, contentType, message,
									subject_replace_node_mainnode, session, formatedDate, formatedDate1, bodyCheck);
						}
					}
				}

			} // main node check
		} catch (Exception e) {
			out.println(e.getMessage());
		}

		return CheckedNode;

	}

	public static void writeUsingFileWriter(File file, String subject_replace, String body, PrintWriter out) {

		FileWriter fr = null;
		try {
			fr = new FileWriter(file);
			fr.write(subject_replace);
			fr.append("\n");

			// fr.write(System.lineSeparator());
			// fr.write("\n");
			fr.write(body);
			// fr.write(System.lineSeparator());
			// fr.write("\n");
		} catch (IOException e) {
			// e.printStackTrace();
			out.println(e.getMessage());
		} finally {
			// close resources
			try {
				fr.close();
			} catch (IOException e) {
				// e.printStackTrace();
				out.println(e.getMessage());
			}
		}
	}

	public static Node createTextNode_Save_txt_sling(PrintWriter out, Node subject_replace_node_mainnode,
			String subject_replace, String bodyText, String mainnodeCount, String Bodycheckmsg, String sentDate,
			Session session, String from, String timestampAndTime , String timestampDate, String bodyCheck) {

		Node mainInsideText_unstructured_Node = null;
		try {

			if (!GmailMethods.isNullString(mainnodeCount)) {
				String forid1 = String.valueOf(Integer.parseInt(mainnodeCount) + 1);
				String file_name = subject_replace + "_" + forid1 + ".html";
				out.println("inside method text : " + file_name);
				if (subject_replace_node_mainnode.hasNode(file_name)) {
					mainInsideText_unstructured_Node = subject_replace_node_mainnode.getNode(file_name);
					out.println("inside mainInsideText_unstructured_Node : " + mainInsideText_unstructured_Node);
				} else {
					mainInsideText_unstructured_Node = subject_replace_node_mainnode.addNode(file_name,
							"nt:unstructured");
					// session.save();
				}

				if (mainInsideText_unstructured_Node.hasProperty("Flag")) {

				} else {
					mainInsideText_unstructured_Node.setProperty("Flag", new String());
				}

				if (mainInsideText_unstructured_Node.hasProperty("proccessedData")) {

				} else {
					mainInsideText_unstructured_Node.setProperty("proccessedData", new String());
				}

				if (mainInsideText_unstructured_Node.hasProperty("file_link")) {

				} else {
					String file_link = "https://dev.bizlem.io:8082/scorpio/bin/cpm/nodes/property.bin"
							+ mainInsideText_unstructured_Node.getPath() + "/"
							+ mainInsideText_unstructured_Node.getName() + "/_jcr_content?name=jcr%3Adata";
					mainInsideText_unstructured_Node.setProperty("file_link", file_link);
				}

				if (mainInsideText_unstructured_Node.hasProperty("file_path")) {

				} else {
					String file_path = "https://dev.bizlem.io:8082/scorpio" + mainInsideText_unstructured_Node.getPath()
							+ "/" + mainInsideText_unstructured_Node.getName();
					mainInsideText_unstructured_Node.setProperty("file_path", file_path);
				}

				if (mainInsideText_unstructured_Node.hasProperty("tomcat_file_path")) {

				} else {
					mainInsideText_unstructured_Node.setProperty("tomcat_file_path",
							"/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/" + file_name);
				}

				if (mainInsideText_unstructured_Node.hasProperty("tomcat_file_link")) {

				} else {
					mainInsideText_unstructured_Node.setProperty("tomcat_file_link",
							"https://dev.bizlem.io:8082/scorpio1/" + file_name);
				}

				if (file_name.contains(".html")) {
					if (mainInsideText_unstructured_Node.hasProperty("xml_file_link")) {

					} else {
						String txt_file_link = "https://dev.bizlem.io:8082/scorpio/bin/cpm/nodes/property.bin"
								+ mainInsideText_unstructured_Node.getPath() + "/"
								+ mainInsideText_unstructured_Node.getName() + "/_jcr_content?name=jcr%3Adata";
						String xml_file_link = txt_file_link.replace(".html", ".xml");
						mainInsideText_unstructured_Node.setProperty("xml_file_link", xml_file_link);
					}
				}

				if (mainInsideText_unstructured_Node.hasProperty("html")) {

				} else {
					mainInsideText_unstructured_Node.setProperty("html", Bodycheckmsg);
				}
				if (mainInsideText_unstructured_Node.hasProperty("Body_Text")) {

				} else {
					mainInsideText_unstructured_Node.setProperty("Body_Text", bodyCheck);
				}

				if (mainInsideText_unstructured_Node.hasProperty("SentMailTime")) {

				} else {
					mainInsideText_unstructured_Node.setProperty("SentMailTime", sentDate);
				}
				
				if (mainInsideText_unstructured_Node.hasProperty("from_Source")) {

				} else {
					mainInsideText_unstructured_Node.setProperty("from_Source", from);
				}
				
				if (mainInsideText_unstructured_Node.hasProperty("timestampDateAndTime")) {

				} else {
					mainInsideText_unstructured_Node.setProperty("timestampDateAndTime", timestampAndTime);
				}
				
				if (mainInsideText_unstructured_Node.hasProperty("timestampDate")) {

				} else {
					mainInsideText_unstructured_Node.setProperty("timestampDate", timestampDate);
				}

				Node file_content = null;
				if (mainInsideText_unstructured_Node.hasNode(file_name)) {
					file_content = mainInsideText_unstructured_Node.getNode(file_name);
				} else {
					file_content = mainInsideText_unstructured_Node.addNode(file_name, "nt:file");
					// session.save();
				}

				String file_root_path = "/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/";
				File tomcateirectory = new File(file_root_path);
				if (!tomcateirectory.exists()) {

					tomcateirectory.mkdir();
					out.println("folder created in Tomcate");
				}

				writeUsingFileWriter(new File(file_root_path + "/" + file_name), subject_replace, bodyText, out);

				Node jcrNode = null;
				InputStream stream = new FileInputStream(file_root_path + file_name);
				jcrNode = file_content.addNode("jcr:content", "nt:resource");
				jcrNode.setProperty("jcr:data", stream);
				jcrNode.setProperty("jcr:mimeType", "attach");
				session.save();
			}

		} catch (Exception e) {

			out.println(e.getMessage());

		}
		return mainInsideText_unstructured_Node;
	}

	public static void create_AttachmentNode_SaveAttach_Sling(PrintWriter out, Node subject_replace_node_mainnode,
			String subject_replace, MimeBodyPart part, long NodeSize, String FileName_replace, String Attachment_Name,
			Session session) {
		try {

			Node mainInsideText_unstructured_Node = null;
			Node AttchsubNode = null;
			String file_name = subject_replace + "_" + NodeSize;
			String subAttachnodename = file_name + "_" + FileName_replace;

			if (subject_replace_node_mainnode.hasNode(file_name)) {
				mainInsideText_unstructured_Node = subject_replace_node_mainnode.getNode(file_name);
			} else {
				mainInsideText_unstructured_Node = subject_replace_node_mainnode.addNode(file_name, "nt:unstructured");
				// session.save();
			}

			if (mainInsideText_unstructured_Node.hasNode(subAttachnodename)) {
				AttchsubNode = mainInsideText_unstructured_Node.getNode(subAttachnodename);
			} else {
				AttchsubNode = mainInsideText_unstructured_Node.addNode(subAttachnodename, "nt:unstructured");
			}

			if (AttchsubNode.hasProperty("Flag")) {

			} else {
				AttchsubNode.setProperty("Flag", new String());
			}

			if (AttchsubNode.hasProperty("proccessedData")) {

			} else {
				AttchsubNode.setProperty("proccessedData", new String());
			}

			if (AttchsubNode.hasProperty("file_link")) {

			} else {
				String file_link = "https://dev.bizlem.io:8082/scorpio/bin/cpm/nodes/property.bin"
						+ AttchsubNode.getPath() + "/" + AttchsubNode.getName() + "/_jcr_content?name=jcr%3Adata";
				AttchsubNode.setProperty("file_link", file_link);
			}

			if (AttchsubNode.hasProperty("file_path")) {

			} else {
				String file_path = "https://dev.bizlem.io:8082/scorpio" + AttchsubNode.getPath() + "/"
						+ AttchsubNode.getName();
				AttchsubNode.setProperty("file_path", file_path);
			}

			if (AttchsubNode.hasProperty("tomcat_file_path")) {

			} else {
				AttchsubNode.setProperty("tomcat_file_path",
						"/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/" + subAttachnodename);
			}

			if (AttchsubNode.hasProperty("tomcat_file_link")) {

			} else {
				AttchsubNode.setProperty("tomcat_file_link", "https://dev.bizlem.io:8082/scorpio1/" + subAttachnodename);
			}
			if (AttchsubNode.hasProperty("Attachment_Name")) {

			} else {
				AttchsubNode.setProperty("Attachment_Name", Attachment_Name);
			}

			Node file_content = null;
			if (AttchsubNode.hasNode(subAttachnodename)) {
				file_content = AttchsubNode.getNode(subAttachnodename);
			} else {
				file_content = AttchsubNode.addNode(subAttachnodename, "nt:file");
			}

			String file_root_path = "/usr/local/tomcat8/apache-tomcat-8.5.35/webapps/ROOT/scorpio1/";
			File tomcateirectory = new File(file_root_path);
			if (!tomcateirectory.exists()) {

				tomcateirectory.mkdir();
				out.println("folder created in Tomcate");
			}

			part.saveFile(new File(file_root_path + "/" + subAttachnodename));

			Node jcrNode = null;
			InputStream stream = new FileInputStream(file_root_path + subAttachnodename);
			jcrNode = file_content.addNode("jcr:content", "nt:resource");
			jcrNode.setProperty("jcr:data", stream);
			jcrNode.setProperty("jcr:mimeType", "attach");
			session.save();

		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}

	public static boolean checkEmailFields_attachent(PrintWriter out, Node mainNode, String original_Filename, Session session) {

		boolean originlaFileNameCheck = false;
		//out.println("original_Filename_original_Filename: "+original_Filename);
		//out.println("original_Filename_original_Filenamemainnode: "+mainNode);
		try {

			if (!GmailMethods.isNullString(original_Filename)) {			
				//out.println("original_Filename_original_Filename1: "+original_Filename);
				//out.println("original_Filename_original_Filename1: "+mainNode);
				String path = mainNode.getPath();
				String Attachment_Name = "";
				Node obj = null;

				Workspace workspace = session.getWorkspace();
				String original_FilenameLower = original_Filename.toLowerCase().trim();

				String slingqery = "select [Attachment_Name] from [nt:base] where LOWER(Attachment_Name) ='"
						+ original_FilenameLower + "'  and ISDESCENDANTNODE('" + path + "')";
				out.println("slingqery: "+slingqery);

				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();
				NodeIterator iterator = queryResult.getNodes();
				while (iterator.hasNext()) {

					obj = iterator.nextNode();
					Attachment_Name = obj.getProperty("Attachment_Name").getString();

				}

				Attachment_Name = (GmailMethods.isNullString(Attachment_Name)) ? "" : Attachment_Name;
				out.println("Attachment_Name_query: " + Attachment_Name);

				if (Attachment_Name.toLowerCase().trim().equals(original_FilenameLower)) {
					out.println("Attachment_Name equals");
					originlaFileNameCheck = true;
				}
			}

		} catch (Exception e) {
			out.println(e.getMessage());
		}
		return originlaFileNameCheck;
	}

	public static void AttachmentSaveData(PrintWriter out, String contentType, Message message,
			Node subject_replace_node_mainnode, String subject_replace, Session session) {
		try {

			if (contentType.contains("multipart")) {

				Multipart multiPart = (Multipart) message.getContent();
				int numberOfParts = multiPart.getCount();
				MimeBodyPart part = null;
				for (int partCount = 0; partCount < numberOfParts; partCount++) {
					part = (MimeBodyPart) multiPart.getBodyPart(partCount);
					String fileName = "";
					String originalName = "";
					if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
						originalName = part.getFileName(); // for single
															// attachment
						fileName = originalName.replace(" ", "_").replaceAll("[^\\p{ASCII}]", "")
								.replaceAll("[^a-zA-Z0-9.]", "_").replaceAll("\\_+", "_").replaceFirst("\\_+", "");
						fileName = (GmailMethods.isNullString(fileName)) ? "" : fileName;
						out.println("attachfilename_if: " + fileName);

						boolean originalcheckFileName = checkEmailFields_attachent(out, subject_replace_node_mainnode,
								originalName, session);
						out.println("boolean: " + originalcheckFileName);
						if (!originalcheckFileName) {
							long nodeSize = 0;

							if (subject_replace_node_mainnode.hasNodes()) {

								nodeSize = subject_replace_node_mainnode.getNodes().getSize();
								create_AttachmentNode_SaveAttach_Sling(out, subject_replace_node_mainnode,
										subject_replace, part, nodeSize, fileName, originalName, session);
								session.save();
							}
						}

					}

				}
			}

		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}

	public static String attachmentCheckDublicate(PrintWriter out, String contentType, Message message) {

		String fileName = "";
		try {

			if (contentType.contains("multipart")) {

				Multipart multiPart = (Multipart) message.getContent();
				int numberOfParts = multiPart.getCount();
				MimeBodyPart part = null;
				for (int partCount = 0; partCount < numberOfParts; partCount++) {
					part = (MimeBodyPart) multiPart.getBodyPart(partCount);

					if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
						fileName = part.getFileName(); // for single attachment
						fileName = (GmailMethods.isNullString(fileName)) ? "" : fileName;
					}
				}
			}

		} catch (Exception e) {
			out.println(e.getMessage());
		}
		return fileName;
	}

	public static void checkEmailFields_TimeSentMail(PrintWriter out, Node mainNode, String emailBody,
			String subject_replace, String bodyText, String toList, String from, String ccList, String sentDate,
			String subject, String contentType, Message message, Node subject_replace_node_mainnode, Session session, String formatedDate1,String formatedDate, String bodyCheck) {

		    /* out.println("emailBodycheck: "+emailBody);
		     out.println("bodyText: "+bodyText);
		     out.println("bodyCheck: "+bodyCheck);*/
		try {

			if (!GmailMethods.isNullString(bodyCheck)) {
				String path = "";
				String SentMailTime = "";

				path = subject_replace_node_mainnode.getPath();
				path = (GmailMethods.isNullString(path)) ? "" : path;
				Workspace workspace = session.getWorkspace();

				String slingqery = "select [SentMailTime] from [nt:base] where SentMailTime ='" + sentDate
						+ "'  and ISDESCENDANTNODE('" + path + "')";

				Query query = workspace.getQueryManager().createQuery(slingqery, Query.JCR_SQL2);
				QueryResult queryResult = query.execute();
				NodeIterator iterator = queryResult.getNodes();
				Node obj = null;
				while (iterator.hasNext()) {

					obj = iterator.nextNode();
					SentMailTime = obj.getProperty("SentMailTime").getValue().getString();

				}

				SentMailTime = (GmailMethods.isNullString(SentMailTime)) ? "" : SentMailTime;

				out.println("SentMailTime: " + SentMailTime);
				out.println("length: " + emailBody.length());

				if (SentMailTime.equals(sentDate)) {
					out.println("sentDate equals");
					String fileName = attachmentCheckDublicate(out, contentType, message);
					// fileName = (GmailMethods.isNullString(fileName)) ? "" :
					// fileName;

					if (!GmailMethods.isNullString(fileName)) {
						out.println("fileName: "+fileName);
						boolean originalcheckFileName = checkEmailFields_attachent(out, mainNode, fileName, session);
						out.println("originalcheckFileName: " + originalcheckFileName);
						if (!originalcheckFileName) {
							AttachmentSaveData(out, contentType, message, mainNode, subject_replace, session);
						}
					}

				} else {

					String body_Text1 = "";
					if (SentMailTime == "") {

						out.println("inside if");
						Workspace workspace1 = session.getWorkspace();
						String emailBodyLower = bodyCheck.toLowerCase().trim();

						String slingqer = "select [Body_Text] from [nt:base] where LOWER(Body_Text) ='" + emailBodyLower
								+ "'  and ISDESCENDANTNODE('" + path + "')";

						Query query1 = workspace1.getQueryManager().createQuery(slingqer, Query.JCR_SQL2);
						QueryResult queryResult1 = query1.execute();
						NodeIterator iterator1 = queryResult1.getNodes();
						Node obj1 = null;
						while (iterator1.hasNext()) {

							obj1 = iterator1.nextNode();
							body_Text1 = obj1.getProperty("Body_Text").getValue().getString();

						}
						body_Text1 = (GmailMethods.isNullString(body_Text1)) ? "" : body_Text1;
						out.println("body_Text1: " + body_Text1);

						if (body_Text1.toLowerCase().trim().equals(emailBodyLower)) {
							out.println("body equals body_Text1");
							String fileName = attachmentCheckDublicate(out, contentType, message);

							if (!GmailMethods.isNullString(fileName)) {
								boolean originalcheckFileName = checkEmailFields_attachent(out, mainNode, fileName, session);
								out.println("originalcheckFileName: " + originalcheckFileName);
								if (!originalcheckFileName) {
									AttachmentSaveData(out, contentType, message, mainNode, subject_replace, session);
								}
							}
						} // if
					}

					if (body_Text1 == "" && SentMailTime == "") {
						out.println("not matched ");

						if (mainNode.hasProperty("jcr:count")) {
							out.println("not matched_jcrcount ");
							String mainnodeCount = mainNode.getProperty("jcr:count").getString();
							out.println("not matched_jcrcount_mainnodeCount: " + mainnodeCount);
							Node returnNode = createTextNode_Save_txt_sling(out, mainNode, subject_replace, bodyText,
									mainnodeCount, emailBody, sentDate, session, from, formatedDate, formatedDate1, bodyCheck);

							mainNode.setProperty("jcr:count", String.valueOf(Integer.parseInt(mainnodeCount) + 1));
							
							AttachmentSaveData(out, contentType, message, returnNode, subject_replace, session);
							session.save();
							
							SaveMailCount.saveMailCount(out, session, formatedDate1, subject_replace_node_mainnode.getName(), formatedDate, subject_replace_node_mainnode.getPath());
						    // original mail count
						}

					}

				}

			}
		} catch (Exception e) {
			out.println(e.getMessage());
		}

	}

}
