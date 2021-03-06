package com.ui.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.jcr.AccessDeniedException;
import javax.jcr.InvalidItemStateException;
import javax.jcr.ItemExistsException;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.ReferentialIntegrityException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.version.VersionException;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.jcr.api.SlingRepository;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Properties;

@Component(immediate = true, metatype = false)
@Service(value = javax.servlet.Servlet.class)
@Properties({ 
		@Property(name = "sling.servlet.paths", value = { "/removeUiSpot" }),
		})

public class RemoveUiSpot extends SlingAllMethodsServlet {
	@Reference
	private SlingRepository repo;
	Session session = null;
	static ResourceBundle bundle = ResourceBundle.getBundle("config");

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		try{

		session = repo.login(new SimpleCredentials("admin", "admin".toCharArray()));
		
		
		String subNodeName=request.getParameter("id");
		
		RemoveUiSpot.removeSpotNode(session,subNodeName, out);
		//response.sendRedirect("http://35.199.31.139:8080/bernhard/newUi/index.html");
        
		}catch(Exception e){
			e.printStackTrace();
		    out.println(e.getMessage());
		}

	}

	
	public static void removeSpotNode(Session session, String subNodeName, PrintWriter out) throws PathNotFoundException, RepositoryException{
		
		Node scorpio = session.getRootNode().getNode("scorpio");
		Node Spot=scorpio.getNode("Spot");
		
	   out.println("inside");
       Node one=Spot.getNode(subNodeName); // do dynamically // "2"
       out.println("one : "+one);
	   String nodeName=one.getName();
	   int result = Integer.valueOf(nodeName);
	   one.remove();
	   session.save();
	   Rename(Spot, result, session, out);
	   session.save();
	
}

  public static void rename(Node node, String newName, Node Spot, PrintWriter out) throws RepositoryException 
		{
			node.getSession().move(node.getPath(), node.getParent().getPath() + "/" + newName);
			
			javax.jcr.Property s = node.getProperty("Id");
			s.setValue(newName);
			
			if(Spot.hasProperty("jcr:count")){
//			if(Spot.hasNodes()){
				javax.jcr.Property count=Spot.getProperty("jcr:count");
				
				long size=Spot.getNodes().getSize();
				out.println("size: "+size);
				String jcrCountUpdate=String.valueOf(size);
				out.println("jcrCountUpdate: "+jcrCountUpdate);
				count.setValue(jcrCountUpdate);
				
//			}
			}
		
		}

public static void Rename(Node Spot, int previousnodename, Session session, PrintWriter out) throws NumberFormatException, AccessDeniedException, PathNotFoundException, ItemExistsException, ReferentialIntegrityException, ConstraintViolationException, InvalidItemStateException, VersionException, LockException, NoSuchNodeTypeException, RepositoryException{


	if(Spot.hasNodes()){
		NodeIterator itr=Spot.getNodes();
		
		while(itr.hasNext()){
			Node obj=itr.nextNode();
			String nodeNamee=obj.getName();
			int result1 = Integer.valueOf(nodeNamee);
			if(result1>previousnodename){
				int finalint=result1-1;
				Node a=Spot.getNode(String.valueOf(result1));
			    rename(a, String.valueOf(finalint), Spot, out);
				session.save();
				
			}
			}
		
	}

}
	
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

	}
	
}
