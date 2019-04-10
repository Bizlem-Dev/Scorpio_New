function TonnageErrorUifetchdataChanged() {
			$
					.ajax({

						url : 'https://dev.bizlem.io:8082/scorpio/reportTonnageError?count=10',
						type : 'GET',
						success : function(data) {
							var jsonStr = data;
							//alert("jsonStr: " + jsonStr);
							jsonStr = JSON.parse(jsonStr);
							var tonnageTable = "";
							var errorTable = "";
							var size = "";
							var parseId = "";
							var forid = "";
							var summary_url = "";
							var tonnageId = "";

							var error = "";
							var inceid = 0;

							for (var i = 0; i < jsonStr.tonnageList.length; i++) {

									if (jsonStr.tonnageList[i]
											.hasOwnProperty("tonnageId")) {
										tonnageId = jsonStr.tonnageList[i].tonnageId;
										//alert("tonnageId:  "+tonnageId);
									}
									var emailUrl = "";
									if (jsonStr.tonnageList[i]
											.hasOwnProperty("emailUrl")) {
										emailUrl = jsonStr.tonnageList[i].emailUrl;
										//alert("tonnageId:  "+tonnageId);
									}

									if (jsonStr.tonnageList[i]
											.hasOwnProperty("size")) {
										size = jsonStr.tonnageList[i].size;
										//alert("BrokerTcRateId:  "+BrokerTcRateId);
									}

									if (jsonStr.tonnageList[i]
											.hasOwnProperty("parseId")) {
										parseId = jsonStr.tonnageList[i].parseId;
										//alert("BrokerTcRateId:  "+BrokerTcRateId);
									}

									var subNodeName = "";
									if (jsonStr.tonnageList[i]
											.hasOwnProperty("subNodeName")) {
										subNodeName = jsonStr.tonnageList[i].subNodeName;
										//alert("subNodeName:  "+subNodeName);
									}

									if (jsonStr.tonnageList[i]
											.hasOwnProperty("i")) {
										forid = jsonStr.tonnageList[i].i;
										//alert("subNodeName:  "+subNodeName);
									}

									if (jsonStr.tonnageList[i]
											.hasOwnProperty("summary_url")) {
										summary_url = jsonStr.tonnageList[i].summary_url;
										//alert("summary_url:  "+summary_url);
									}

									var EmploymentStatusJsonarray = "";
									if (jsonStr.tonnageList[i]
											.hasOwnProperty("EmploymentStatus")) {
										EmploymentStatusJsonarray = jsonStr.tonnageList[i].EmploymentStatus;
									}

									var OwnersJsonarray = "";
									if (jsonStr.tonnageList[i]
											.hasOwnProperty("Owners")) {
										OwnersJsonarray = jsonStr.tonnageList[i].Owners;
									}

									var OpenPortJsonarray = "";
									if (jsonStr.tonnageList[i]
											.hasOwnProperty("OpenPort")) {
										OpenPortJsonarray = jsonStr.tonnageList[i].OpenPort;
									}

									var OperatorsJsonarray = "";
									if (jsonStr.tonnageList[i]
											.hasOwnProperty("Operators")) {
										OperatorsJsonarray = jsonStr.tonnageList[i].Operators;
									}

									var CargoTypeJsonarray = "";
									if (jsonStr.tonnageList[i]
											.hasOwnProperty("CargoType")) {
										CargoTypeJsonarray = jsonStr.tonnageList[i].CargoType;
									}
									var emailClientNodeId = "";
									if (jsonStr.tonnageList[i]
											.hasOwnProperty("emailClientNodeId")) {
										emailClientNodeId = "'"
												+ jsonStr.tonnageList[i].emailClientNodeId
												+ "'";
										console.log("emailClientNodeId:  "
												+ emailClientNodeId);
									}// emailClientNodeId close

									var CommentJsonarray = "";
									if (jsonStr.tonnageList[i]
											.hasOwnProperty("Comment")) {
										CommentJsonarray = jsonStr.tonnageList[i].Comment;
									}

									var ETABasisJsonarray = "";
									if (jsonStr.tonnageList[i]
											.hasOwnProperty("ETABasis")) {
										ETABasisJsonarray = jsonStr.tonnageList[i].ETABasis;
									}

									var OpenDateJsonarray = "";
									if (jsonStr.tonnageList[i]
											.hasOwnProperty("OpenDate")) {
										OpenDateJsonarray = jsonStr.tonnageList[i].OpenDate;
									}

									var ReportTypeJsonarray = "";
									if (jsonStr.tonnageList[i]
											.hasOwnProperty("ReportType")) {
										ReportTypeJsonarray = jsonStr.tonnageList[i].ReportType;
									}

									var ReportTimestampJsonarray = "";
									if (jsonStr.tonnageList[i]
											.hasOwnProperty("ReportTimestamp")) {
										ReportTimestampJsonarray = jsonStr.tonnageList[i].ReportTimestamp;
									}

									var RepositionRegionJsonarray = "";
									if (jsonStr.tonnageList[i]
											.hasOwnProperty("RepositionRegion")) {
										RepositionRegionJsonarray = jsonStr.tonnageList[i].RepositionRegion;
									}

									var SourceJsonarray = "";
									if (jsonStr.tonnageList[i]
											.hasOwnProperty("Source")) {
										SourceJsonarray = jsonStr.tonnageList[i].Source;
									}

									var BuiltJsonarray = "";
									if (jsonStr.tonnageList[i]
											.hasOwnProperty("Built")) {
										BuiltJsonarray = jsonStr.tonnageList[i].Built;
									}

									var DWTJsonarray = "";
									if (jsonStr.tonnageList[i]
											.hasOwnProperty("DWT")) {
										DWTJsonarray = jsonStr.tonnageList[i].DWT;
									}

									var CubicsJsonarray = "";
									if (jsonStr.tonnageList[i]
											.hasOwnProperty("Cubics")) {
										CubicsJsonarray = jsonStr.tonnageList[i].Cubics;
									}

									var LOAJsonarray = "";
									if (jsonStr.tonnageList[i]
											.hasOwnProperty("LOA")) {
										LOAJsonarray = jsonStr.tonnageList[i].LOA;
									}

									var ICEJsonarray = "";
									if (jsonStr.tonnageList[i]
											.hasOwnProperty("ICE")) {
										ICEJsonarray = jsonStr.tonnageList[i].ICE;
									}

									var SternLinejsonarray = "";
									if (jsonStr.tonnageList[i]
											.hasOwnProperty("SternLine")) {
										SternLinejsonarray = jsonStr.tonnageList[i].SternLine;
									}

									var VesselTypejsonarray = "";
									if (jsonStr.tonnageList[i]
											.hasOwnProperty("VesselType")) {
										VesselTypejsonarray = jsonStr.tonnageList[i].VesselType;
									}

									var VesselNameJsonarray = "";
									if (jsonStr.tonnageList[i]
											.hasOwnProperty("VesselName")) {
										VesselNameJsonarray = jsonStr.tonnageList[i].VesselName;

									}

									var vesselFlag = "";
									if (jsonStr.tonnageList[i]
											.hasOwnProperty("vesselFlag")) {
										vesselFlag = jsonStr.tonnageList[i].vesselFlag;
									}

									var portFlag = "";
									if (jsonStr.tonnageList[i]
											.hasOwnProperty("flagOpenPort")) {
										portFlag = jsonStr.tonnageList[i].flagOpenPort;
									}

										/*tonnageTable = tonnageTable
												+ '<tr><td>'
												+ tonnageId
												+ '</td><td><span>'
												+ ReportTypeJsonarray
												+ '</span><input type="text" name="reporttype" class="td-input"  value="'+ReportTypeJsonarray+'"></td><td><span>'
												+ RepositionRegionJsonarray
												+ '</span><input type="text" name="repositionregion" class="td-input" value="'+RepositionRegionJsonarray+'"></td><td><span>'
												+ ETABasisJsonarray
												+ '</span><input type="text" name="etabasis" class="td-input" value="'+ETABasisJsonarray+'"></td><td><span>'
												+ CargoTypeJsonarray
												+ '</span><input type="text" name="cargotype" class="td-input" value="'+CargoTypeJsonarray+'"></td><td><span>'
												+ VesselNameJsonarray
												+ '</span><input type="text" name="vesselname" class="td-input" value="'+VesselNameJsonarray+'"></td><td><span>'
												+ VesselTypejsonarray
												+ '</span><input type="text" name="vesseltype" class="td-input" value="'+VesselTypejsonarray+'"></td><td><span>'
												+ BuiltJsonarray
												+ '</span><input type="text" name="built" class="td-input" value="'+BuiltJsonarray+'"></td><td><span>'
												+ DWTJsonarray
												+ '</span><input type="text" name="dwt" class="td-input" value="'+DWTJsonarray+'"></td><td><span>'
												+ CubicsJsonarray
												+ '</span><input type="text" name="cubics" class="td-input" value="'+CubicsJsonarray+'"></td><td><span>'
												+ LOAJsonarray
												+ '</span><input type="text" name="loa" class="td-input" value="'+LOAJsonarray+'"></td><td><span>'
												+ ICEJsonarray
												+ '</span><input type="text" name="ice" class="td-input" value="'+ICEJsonarray+'"></td><td><span>'
												+ SternLinejsonarray
												+ '</span><input type="text" name="sternline" class="td-input" value="'+SternLinejsonarray+'"></td><td><span>'
												+ OwnersJsonarray
												+ '</span><input type="text" name="owners" class="td-input" value="'+OwnersJsonarray+'"></td><td><span>'
												+ OperatorsJsonarray
												+ '</span><input type="text" name="operators" class="td-input" value="'+OperatorsJsonarray+'"></td><td><span>'
												+ EmploymentStatusJsonarray
												+ '</span><input type="text" name="employementstatus" class="td-input" value="'+EmploymentStatusJsonarray+'"></td><td><span>'
												+ OpenPortJsonarray
												+ '</span><input type="text" name="openport" class="td-input" value="'+OpenPortJsonarray+'"></td><td><span>'
												+ OpenDateJsonarray
												+ '</span><input type="text" name="opendate" class="td-input" value="'+OpenDateJsonarray+'"></td><td><span>'
												
												+ CommentJsonarray
												+ '</span><input type="text" name="comment" class="td-input" value="'+CommentJsonarray+'"></td><td><span>'
												+ SourceJsonarray
												+ '</span><input type="text" name="source" class="td-input" value="'+SourceJsonarray+'"></td><td><span>'
												+ ReportTimestampJsonarray
												+ '</span><input type="text" name="reporttimestamp" class="td-input" value="'+ReportTimestampJsonarray+'"></td><td><span></span><a href="#" onclick="emailClientNodeId('
												+ emailClientNodeId
												+ ');">Link</a></td><td class="display-none"><span>'
												+ subNodeName
												+ '</span><input type="text" name="editnodenameTonnage" class="td-input" value="'+subNodeName+'"></td><td class="display-none"></td><td><a href="#" class="btn btn-success btn-sm edit-table-btn"><i class="fa fa-pencil" aria-hidden="true"></i></a><a href="#" class="btn btn-danger btn-sm delete-table-btn" onclick="removeUiTonnage('
												+ subNodeName*/
									
									
									tonnageTable = tonnageTable
									+ '<tr><td>'
									+ tonnageId
									+ '</td><td><span>'
									+ ReportTypeJsonarray
									+ '</span><input type="text" name="" class="td-input"  value="'+ReportTypeJsonarray+'"></td><td><span>'
									+ RepositionRegionJsonarray
									+ '</span><input type="text" name="" class="td-input" value="'+RepositionRegionJsonarray+'"></td><td><span>'
									+ ETABasisJsonarray
									+ '</span><input type="text" name="" class="td-input" value="'+ETABasisJsonarray+'"></td><td><span>'
									+ CargoTypeJsonarray
									+ '</span><input type="text" name="" class="td-input" value="'+CargoTypeJsonarray+'"></td><td><span>'
									+ VesselNameJsonarray
									+ '</span><input type="text" name="" class="td-input" value="'+VesselNameJsonarray+'"></td><td><span>'
									+ VesselTypejsonarray
									+ '</span><input type="text" name="" class="td-input" value="'+VesselTypejsonarray+'"></td><td><span>'
									+ BuiltJsonarray
									+ '</span><input type="text" name="" class="td-input" value="'+BuiltJsonarray+'"></td><td><span>'
									+ DWTJsonarray
									+ '</span><input type="text" name="" class="td-input" value="'+DWTJsonarray+'"></td><td><span>'
									+ CubicsJsonarray
									+ '</span><input type="text" name="" class="td-input" value="'+CubicsJsonarray+'"></td><td><span>'
									+ LOAJsonarray
									+ '</span><input type="text" name="" class="td-input" value="'+LOAJsonarray+'"></td><td><span>'
									+ ICEJsonarray
									+ '</span><input type="text" name="" class="td-input" value="'+ICEJsonarray+'"></td><td><span>'
									+ SternLinejsonarray
									+ '</span><input type="text" name="" class="td-input" value="'+SternLinejsonarray+'"></td><td><span>'
									+ OwnersJsonarray
									+ '</span><input type="text" name="" class="td-input" value="'+OwnersJsonarray+'"></td><td><span>'
									+ OperatorsJsonarray
									+ '</span><input type="text" name="" class="td-input" value="'+OperatorsJsonarray+'"></td><td><span>'
									+ EmploymentStatusJsonarray
									+ '</span><input type="text" name="" class="td-input" value="'+EmploymentStatusJsonarray+'"></td><td><span>'
									+ OpenPortJsonarray
									+ '</span><input type="text" name="" class="td-input" value="'+OpenPortJsonarray+'"></td><td><span>'
									+ OpenDateJsonarray
									+ '</span><input type="text" name="" class="td-input" value="'+OpenDateJsonarray+'"></td><td><span>'
									
									+ CommentJsonarray
									+ '</span><input type="text" name="" class="td-input" value="'+CommentJsonarray+'"></td><td><span>'
									+ SourceJsonarray
									+ '</span><input type="text" name="" class="td-input" value="'+SourceJsonarray+'"></td><td><span>'
									+ ReportTimestampJsonarray
									+ '</span><input type="text" name="" class="td-input" value="'+ReportTimestampJsonarray+'"></td><td><span></span><a href="#" onclick="emailClientNodeId('
									+ emailClientNodeId
									+ ');">Link</a></td><td class="display-none"><span>'
									+ subNodeName
									+ '</span><input type="text" name="" class="td-input" value="'+subNodeName+'"></td><td class="display-none"></td><td><a href="#" class="btn btn-success btn-sm edit-table-btn"><i class="fa fa-pencil" aria-hidden="true"></i></a><a href="#" class="btn btn-danger btn-sm delete-table-btn" onclick=""><i class="fa fa-trash-o" aria-hidden="true"></i></a></td></tr>';
					
								

							}// main for

								tonnageTable = '<tr class="firstTr" style="display: none;"><td>'
										+ tonnageId
										+ '</td><td class="dblclick"><input type="text" name="reporttype" class="display-block-input display-none" id="reporttype"></td><td class="dblclick"><input type="text" name="repositionregion" class="display-block-input display-none" id="repositionregion"></td><td class="dblclick"><input type="text" name="cargotype" class="display-block-input display-none" id="cargotype"></td><td class="dblclick"><input type="text" name="vesselname" class="display-block-input display-none" id="vesselname"></td><td class="dblclick"><input type="text" name="vesseltype" class="display-block-input display-none" id="vesseltype"></td><td class="dblclick"><input type="text" name="built" class="display-block-input display-none" id="built"></td><td class="dblclick"><input type="text" name="dwt" class="display-block-input display-none" id="dwt"></td><td class="dblclick"><input type="text" name="cubics" class="display-block-input display-none" id="cubics"></td><td class="dblclick"><input type="text" name="loa" class="display-block-input display-none" id="loa"></td><td class="dblclick"><input type="text" name="ice" class="display-block-input display-none" id="ice"></td><td class="dblclick"><input type="text" name="sternline" class="display-block-input display-none" id="sternline"></td><td class="dblclick"><input type="text" name="owners" class="display-block-input display-none" id="owners"></td><td class="dblclick"><input type="text" name="operators" class="display-block-input display-none" id="operators"></td><td class="dblclick"><input type="text" name="employementstatus" class="display-block-input display-none" id="employementstatus"></td><td class="dblclick"><input type="text" name="openport" class="display-block-input display-none" id="openport"></td><td class="dblclick"><input type="text" name="opendate" class="display-block-input display-none" id="opendate"></td><td class="dblclick"><input type="text" name="etabasis" class="display-block-input display-none" id="etabasis"></td><td class="dblclick"><input type="text" name="comment" class="display-block-input display-none" id="comment"></td><td class="dblclick"><input type="text" name="source" class="display-block-input display-none" id="source"></td><td class="dblclick"><input type="text" name="reporttimestamp" class="display-block-input display-none" id="reporttimestamp"></td><td class="dblclick"><input type="text" name="" class="display-block-input display-none"></td><td class="display-none"><span>subNodeName</span><input type="text" name="editnodenameTonnage" class="td-input" value=""></td><td><a href="#" class="btn btn-success btn-sm edit-table-btn"><i class="fa fa-pencil" aria-hidden="true"></i></a><a href="#" class="btn btn-danger btn-sm delete-table-btn"><i class="fa fa-trash-o" aria-hidden="true"></i></a></td></tr>'
										+ tonnageTable;
								
								document.getElementById("tonnageTableError").innerHTML = tonnageTable;


						},
					//async: false
					}); // Tonnage ajax close
		}