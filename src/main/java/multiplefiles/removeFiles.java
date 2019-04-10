package multiplefiles;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
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
		@Property(name = "sling.servlet.paths", value = { "/removeFiles" }),
		})

public class removeFiles extends SlingAllMethodsServlet {
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
		
		String subNodeName=request.getParameter("id");
		removeFilesNode(session, subNodeName, out);
		
		
		}catch(Exception e){
//			e.printStackTrace(out);
		    out.println(e.getMessage());
		}
		
	}

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException, IOException {
	
	}
	
	public static void removeFilesNode(Session session, String subNodeName, PrintWriter out) throws PathNotFoundException, RepositoryException{
		
		Node SRS=null;
		Node uploadedFile=null;
		if(session.getRootNode().hasNode("MultiplesFiles")){
			SRS=session.getRootNode().getNode("MultiplesFiles");
		}
		
		if(SRS.hasNode("Files")){
			uploadedFile=SRS.getNode("Files");
			
		}
		

		Node one=null;
		if(uploadedFile.hasNode(subNodeName)){
			one=uploadedFile.getNode(subNodeName);
			one.remove();
			session.save();
		}

	
}
	
}
