package com.reportinformationsystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.jcr.api.SlingRepository;

import com.readGmail.GmailMethods;

import ChangedStructureCurrent.ChangedStructureCurrent_Methods;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Properties;

@Component(immediate = true, metatype = false)
@Service(value = javax.servlet.Servlet.class)
@Properties({ @Property(name = "sling.servlet.paths", value = { "/reportSpotSave" }), })

public class saveSpotFromUi extends SlingAllMethodsServlet {
	@Reference
	private SlingRepository repo;
	Session session = null;
	static ResourceBundle bundle = ResourceBundle.getBundle("config");

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

	}

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		try {

			session = repo.login(new SimpleCredentials("admin", "admin".toCharArray()));

			JSONObject passdata = new JSONObject(request.getParameter("employees"));

			if (passdata.has("accounting")) {
				JSONArray jsonarraydata = passdata.getJSONArray("accounting");
				for (int i = 0; i < jsonarraydata.length(); i++) {
					JSONObject jsonobjdata = jsonarraydata.getJSONObject(i);

					String information = "";
					String fixturetype = "";
					String charterer = "";
					String status = "";
					String cargotype = "";
					String cargograde = "";
					String cargoqty = "";
					String editnodename = "";

					ChangedStructureCurrent_Methods s = new ChangedStructureCurrent_Methods();

					if (jsonobjdata.has("info")) {
						information = jsonobjdata.getString("info");
						information = s.check_Information(session, information, out);
					}

					if (jsonobjdata.has("fixturetype")) {
						fixturetype = jsonobjdata.getString("fixturetype");
						fixturetype = s.check_ReportType(session, fixturetype, out);
					}

					if (jsonobjdata.has("Charterer")) {
						charterer = jsonobjdata.getString("Charterer");
						charterer = s.check_Charterer(session, charterer, out);
					}

					if (jsonobjdata.has("status")) {
						status = jsonobjdata.getString("status");
						status = s.check_EmployementStatus(session, status, out);
					}

					if (jsonobjdata.has("CargoType")) {
						cargotype = jsonobjdata.getString("CargoType");
						cargotype = s.check_CargoType(session, cargotype, out);
					}

					if (jsonobjdata.has("cargograde")) {
						cargograde = jsonobjdata.getString("cargograde");
						cargograde = s.check_CargoGrade(session, cargograde, out);
					}

					if (jsonobjdata.has("cargoqty")) {
						cargoqty = jsonobjdata.getString("cargoqty");
					}
					String lcstart = "";
					if (jsonobjdata.has("lcstart")) {
						lcstart = jsonobjdata.getString("lcstart");
					}
					String lcend = "";
					if (jsonobjdata.has("lcend")) {
						lcend = jsonobjdata.getString("lcend");
					}
					String loadport = "";
					if (jsonobjdata.has("loadport")) {
						loadport = jsonobjdata.getString("loadport");
						loadport = s.check_Port(session, loadport, out);

						// out.println("LoadPort1: "+LoadPort1);
						boolean checkjsonString = SaveReportDataClass.isJSONValid(loadport);
						if (checkjsonString == true) {
							JSONObject data = new JSONObject(loadport);
							if (data.has("port_id")) {
								loadport = data.getString("port_id");
								// out.println("LoadPort: "+LoadPort);
							}
						}
					}

					String discport = "";
					if (jsonobjdata.has("discport")) {
						discport = jsonobjdata.getString("discport");
						discport = s.check_Port(session, discport, out);

						boolean checkjsonString = SaveReportDataClass.isJSONValid(discport);
						if (checkjsonString == true) {
							JSONObject data = new JSONObject(discport);
							if (data.has("port_id")) {
								discport = data.getString("port_id");
								// out.println("LoadPort: "+LoadPort);
							}
						}
					}
					
					Node vesselNode = null;
					String objName = "";
					Node obj = null;
					String vessel_id = "";

					if (jsonobjdata.has("VesselName")) {
						Node scorpioDataBase = session.getRootNode().getNode("scorpioDataBase");
						vesselNode = scorpioDataBase.getNode("vessel");
						String vesselname = jsonobjdata.getString("VesselName");
						objName = s.check_vesselName_FOr_Ui_Save(session, vesselname, out);
						if (!GmailMethods.isNullString(objName)) {
							if (vesselNode.hasNode(objName)) {
								obj = vesselNode.getNode(objName);
							}
							if (obj.hasProperty("vessel_Id")) {
								vessel_id = obj.getProperty("vessel_Id").getString();
								// out.println("vessel_id "+vessel_id);
							}

						} //
						else {
							if (!vesselNode.hasNode(vesselname)) {
								String NotvesselName = s.vesselSlingSave(session, out, vesselname);
								if (vesselNode.hasNode(NotvesselName)) {
									Node notVesselNode = vesselNode.getNode(NotvesselName);

									if (notVesselNode.hasProperty("vessel_Id")) {
										vessel_id = notVesselNode.getProperty("vessel_Id").getString();
										// out.println("vessel_id "+vessel_id);
									}
								}

							}
						}
					} // vesselname

					String ratetype = "";
					if (jsonobjdata.has("ratetype")) {
						ratetype = jsonobjdata.getString("ratetype");
						ratetype = s.check_RateType(session, ratetype, out);
					}
					String rate = "";
					if (jsonobjdata.has("Rate")) {
						rate = jsonobjdata.getString("Rate");
					}
					String comments = "";
					if (jsonobjdata.has("Comments")) {
						comments = jsonobjdata.getString("Comments");
					}
					String reportdate = "";
					if (jsonobjdata.has("reportdate")) {
						reportdate = jsonobjdata.getString("reportdate");
					}
					String source = "";
					if (jsonobjdata.has("Source")) {
						source = jsonobjdata.getString("Source");
						source = s.check_Source(session, source, out);
					}
					String vesseltype = "";
					if (jsonobjdata.has("VesselType")) {
						vesseltype = jsonobjdata.getString("VesselType");
						vesseltype = ChangedStructureCurrent_Methods.check_vesselType(session, vesseltype, out);
					}

					if (jsonobjdata.has("editnodename")) {
						editnodename = jsonobjdata.getString("editnodename");
					}
					if (!editnodename.equals("")) {
						ReportBeforeOnWeekDataDisplay.SpotReportEditUi(out, session, information, fixturetype,
								charterer, status, cargoqty, cargograde, lcstart, lcend, loadport, discport, vessel_id,
								ratetype, rate, comments, reportdate, source, vesseltype, cargotype, editnodename);
					} else {

						ReportBeforeOnWeekDataDisplay.callSpotReport(out, session, information, fixturetype, charterer,
								status, cargoqty, cargograde, lcstart, lcend, loadport, discport, vessel_id, ratetype,
								rate, comments, reportdate, source, vesseltype, cargotype);
						// UiMethods.SpotSave(session, out, information,
						// fixturetype, charterer, status, cargotype,
						// cargograde, cargoqty, lcstart, lcend, loadport,
						// discport, vesselname, ratetype, rate, comments,
						// reportdate, source, vesseltype);
					}

				} // for

			} // if

		} catch (Exception e) {
			e.printStackTrace();
			out.println(e.getMessage());
		}

	}

}
