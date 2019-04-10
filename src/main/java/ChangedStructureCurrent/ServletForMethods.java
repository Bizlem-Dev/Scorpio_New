package ChangedStructureCurrent;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

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
		@Property(name = "sling.servlet.paths", value = { "/newStructure" }),
		})

public class ServletForMethods extends SlingAllMethodsServlet {
	@Reference
	private SlingRepository repo;
	Session session = null;
	static ResourceBundle bundle = ResourceBundle.getBundle("config");

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		
		
		try{

		session = repo.login(new SimpleCredentials("admin", "admin".toCharArray()));
		
         ChangedStructureCurrent_Methods CSCM=new ChangedStructureCurrent_Methods();
         FetchData fd=new FetchData();
         
         /*CSCM.reportTypeSlingStructure(out, session);
         CSCM.CargoTypeSlingStructure(out, session);
         CSCM.scorpioOwner(session, out);
         
         CSCM.OperatorsSlingStructure(out, session);
         CSCM.ChartererSlingStructure(out, session);
         CSCM.DeliveryPlaceSlingStructure(out, session);
         CSCM.CargoGradeSlingStructure(out, session);
         CSCM.SourceSlingStructure(out, session);
         CSCM.EmployementStatusSlingStructure(out, session);
         CSCM.RateTypeSlingStructure(out, session);
         CSCM.PeriodUnitSlingStructure(out, session);
         CSCM.currencyUnitSlingStructure(out, session);
         CSCM.InformationSlingStructure(out, session);
         CSCM.scorpioPort(session, out);
         CSCM.scorpioDataBaseSave_new(session, out);*/
         
         
//         CSCM.parseAllReportUsingApi(out, session);
         String data=fd.checkDatesForRecords(session, "09-10-2018", "30-10-2018","Position List", out);
//         String data=fd.checkDatesForRecords(session, "29-10-2018", "2-11-2018","Spot", out);
//         String data=fd.checkDatesForRecords(session, "", "30-10-2018","Position List", out);
         JSONObject dataobj=new JSONObject(data);
         out.println("dataobj: "+dataobj);
//         fd.checkDatesForRecords(session, "5", "29-10-2018", out);
         
        out.println("below save");
		}catch(Exception e){
			
		    out.println(e.getMessage());
		}

	}

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

	}
	
}
