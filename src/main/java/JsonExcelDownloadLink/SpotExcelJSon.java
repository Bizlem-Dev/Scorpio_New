package JsonExcelDownloadLink;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.jcr.api.SlingRepository;

import com.reportinformationsystem.fetchAllReport;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Properties;

@SuppressWarnings("deprecation")
@Component(immediate = true, metatype = false)
@Service(value = javax.servlet.Servlet.class)
@Properties({ 
	    @Property(name = "service.description", value = "Save product Servlet"),
	    @Property(name = "service.vendor", value = "VISL Company"),
		@Property(name = "sling.servlet.paths", value = { "/servlet/service/Spot" }),
		@Property(name = "sling.servlet.resourceTypes", value = "sling/servlet/default"),//sling/servlet/default
		@Property(name = "sling.servlet.extensions", value = "data"),
		})

public class SpotExcelJSon extends SlingAllMethodsServlet {
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
	
		fetchAllReport.fetchSpotDataChangedNew(out, session);

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
	
}
