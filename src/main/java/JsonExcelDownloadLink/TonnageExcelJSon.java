package JsonExcelDownloadLink;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.jcr.api.SlingRepository;

import com.reportinformationsystem.fetchAllReport;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Properties;

@Component(immediate = true, metatype = false)
@Service(value = javax.servlet.Servlet.class)
@Properties({ 
	    @Property(name = "service.description", value = "Save product Servlet"),
	    @Property(name = "service.vendor", value = "VISL Company"),
		@Property(name = "sling.servlet.paths", value = { "/servlet/service/Tonnage" }),
		@Property(name = "sling.servlet.resourceTypes", value = "sling/servlet/default"),//sling/servlet/default
		@Property(name = "sling.servlet.extensions", value = "data"),
		})

public class TonnageExcelJSon extends SlingAllMethodsServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Reference
	private SlingRepository repo;
	
	
	static ResourceBundle bundle = ResourceBundle.getBundle("config");
	Session session = null;
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		if (request.getRequestPathInfo().getExtension().equals("data")) {		
		
		try{
			
		session = repo.login(new SimpleCredentials("admin", "admin".toCharArray()));
	
		fetchAllReport.fetchTonnage_37KData(out, session);
		

		}catch(Exception e){
			
		    out.println(e.getMessage());
		}

		}// extension
	}

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

	}
	
	public static void rename(Node node, String newName) throws RepositoryException 
	{
		node.getSession().move(node.getPath(), node.getParent().getPath() + "/" + newName);
	}
	
	public static JSONObject fetchCronWiseDataForUi(PrintWriter out, Session session){
		JSONObject cronDataFinal=new JSONObject();
		try {
			Node scorpioDataBase=null;
			Node StatusUIProcess=null;
			Node cronNodeFifty=null;
			
			if( session.getRootNode().hasNode("scorpioDataBase") ){
				scorpioDataBase=session.getRootNode().getNode("scorpioDataBase");
			}if( scorpioDataBase.hasNode("StatusUIProcess") ){
				StatusUIProcess=scorpioDataBase.getNode("StatusUIProcess");
			}
			
			if(StatusUIProcess.hasNode("50")){
				cronNodeFifty=StatusUIProcess.getNode("50");
				if(cronNodeFifty.hasNodes()){
					NodeIterator itr=cronNodeFifty.getNodes();
					while(itr.hasNext()){
						Node nextNode=itr.nextNode();
						if(nextNode.hasProperty("Ui_Update")){
							String uiupdate=nextNode.getProperty("Ui_Update").getString();
							if("NO".equalsIgnoreCase(uiupdate)){
								nextNode.remove();
								session.save();
							}
						}
					}
				}
			}
			
			
				
			
		} catch (Exception e) {
			
		}
		return cronDataFinal;
	}
	
}
