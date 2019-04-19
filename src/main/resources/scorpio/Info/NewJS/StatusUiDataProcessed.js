function statusUiFetchProcessed() {
			$
					.ajax({

						url : 'https://dev.bizlem.io:8082/scorpio/statusUiFectchData',
						type : 'GET',
						success : function(data) {
							var jsonStr = data;
							jsonStr = JSON.parse(jsonStr);
							var cronTable = "";

							for (var i = 0; i < jsonStr.cronData.length; i++) {

									
									var Last_Process_Time = "";
									if (jsonStr.cronData[i]
											.hasOwnProperty("Last_Process_Time")) {
										Last_Process_Time = jsonStr.cronData[i].Last_Process_Time;
									}
									var DateandTime = "";
									if (jsonStr.cronData[i]
											.hasOwnProperty("DateandTime")) {
										DateandTime = jsonStr.cronData[i].DateandTime;
									}
									var Subject = "";
									if (jsonStr.cronData[i]
											.hasOwnProperty("Subject")) {
										Subject = jsonStr.cronData[i].Subject;
									}
									var Attachment = "";
									if (jsonStr.cronData[i]
											.hasOwnProperty("Attachment")) {
										Attachment = jsonStr.cronData[i].Attachment;
									}
									var Nlp_1 = "";
									if (jsonStr.cronData[i]
											.hasOwnProperty("Nlp1")) {
										Nlp_1 = jsonStr.cronData[i].Nlp1;
									}
									var Nlp_2 = "";
									if (jsonStr.cronData[i]
											.hasOwnProperty("Nlp2")) {
										Nlp_2 = jsonStr.cronData[i].Nlp2;
									}
									var Nlp_3_and_4_QC = "";
									if (jsonStr.cronData[i]
											.hasOwnProperty("Nlp3_and_4_QC")) {
										Nlp_3_and_4_QC = jsonStr.cronData[i].Nlp3_and_4_QC;
									}
									var Ui_Update = "";
									if (jsonStr.cronData[i]
											.hasOwnProperty("Ui_Update")) {
										Ui_Update = jsonStr.cronData[i].Ui_Update;
									}
									
									var emailUrl = "";
									if (jsonStr.cronData[i]
											.hasOwnProperty("emailUrl")) {
										emailUrl = jsonStr.cronData[i].emailUrl;
									}
									
									var mainNodeName = "";
									if (jsonStr.cronData[i]
											.hasOwnProperty("mainNodeName")) {
										mainNodeName = "'"
												+ jsonStr.cronData[i].mainNodeName
												+ "'";
										
									}// mainNodeName close
									
									var brokerName = "";
									if (jsonStr.cronData[i]
											.hasOwnProperty("brokerName")) {
										brokerName = jsonStr.cronData[i].brokerName;
										
									}// mainNodeName close
									
									//cronTable = cronTable +'<tr><td style="width:160px !important;">'+Last_Process_Time+'</td><td>'+DateandTime+'</td><td><span>'+Subject+'</span><input type="text" name="" class="td-input" value="'+Subject+'"></td><td><span>'+Attachment+'</span><input type="text" name="" class="td-input" value="'+Attachment+'"></td><td><span>'+Nlp_1+'</span><input type="text" name="" class="td-input" value="'+Nlp_1+'"></td><td><span>'+Nlp_2+'</span><input type="text" name="" class="td-input" value="'+Nlp_2+'"></td><td><span>'+Nlp_3_and_4_QC+'</span><input type="text" name="" class="td-input" value="'+Nlp_3_and_4_QC+'"></td><td><span>'+Ui_Update+'</span><input type="text" name="" class="td-input" value="'+Ui_Update+'"></td><td class="display-none"></td><td><a href="#" class="btn btn-success btn-sm edit-table-btn"><i class="fa fa-pencil" aria-hidden="true"></i></a><a href="#" class="btn btn-danger btn-sm delete-table-btn"><i class="fa fa-trash-o" aria-hidden="true"></i></a></td></tr>';
									//cronTable = cronTable +'<tr><td style="width:160px !important;">'+Last_Process_Time+'</td><td>'+DateandTime+'</td><td><span></span><a href="#" onclick="emailClientNodeId('+ mainNodeName+');">link</a></td><td><span>'+Attachment+'</span><input type="text" name="" class="td-input" value="'+Attachment+'"></td><td><span>'+Nlp_1+'</span><input type="text" name="" class="td-input" value="'+Nlp_1+'"></td><td><span>'+Nlp_2+'</span><input type="text" name="" class="td-input" value="'+Nlp_2+'"></td><td><span>'+Nlp_3_and_4_QC+'</span><input type="text" name="" class="td-input" value="'+Nlp_3_and_4_QC+'"></td><td><span>'+Ui_Update+'</span><input type="text" name="" class="td-input" value="'+Ui_Update+'"></td><td><span></span><a href="#" onclick="emailClientNodeId('+ emailClientNodeId+');">Link</a></td><td class="display-none"></td><td><a href="#" class="btn btn-success btn-sm edit-table-btn"><i class="fa fa-pencil" aria-hidden="true"></i></a><a href="#" class="btn btn-danger btn-sm delete-table-btn"><i class="fa fa-trash-o" aria-hidden="true"></i></a></td></tr>';
//									cronTable = cronTable +'<tr><td style="width:160px !important;">'+Last_Process_Time+'</td><td>'+DateandTime+'</td><td><span></span><a href="#" style="text-decoration: none;" onclick="emailClientNodeId('+ mainNodeName+');">'+Subject+'</a></td><td><span>'+Attachment+'</span><input type="text" name="" class="td-input" value="'+Attachment+'"></td><td><span>'+Nlp_1+'</span><input type="text" name="" class="td-input" value="'+Nlp_1+'"></td><td><span>'+Nlp_2+'</span><input type="text" name="" class="td-input" value="'+Nlp_2+'"></td><td><span>'+Nlp_3_and_4_QC+'</span><input type="text" name="" class="td-input" value="'+Nlp_3_and_4_QC+'"></td><td><span>'+Ui_Update+'</span><input type="text" name="" class="td-input" value="'+Ui_Update+'"></td><td class="display-none"></td><td><a href="#" class="btn btn-success btn-sm edit-table-btn"><i class="fa fa-pencil" aria-hidden="true"></i></a><a href="#" class="btn btn-danger btn-sm delete-table-btn"><i class="fa fa-trash-o" aria-hidden="true"></i></a></td></tr>';
									cronTable = cronTable +'<tr><td style="width:160px !important;">'+Last_Process_Time+'</td><td>'+DateandTime+'</td><td><span></span><a href="#" style="text-decoration: none;" onclick="emailClientNodeId('+ mainNodeName+');">'+Subject+'</a></td><td><span>'+Attachment+'</span><input type="text" name="" class="td-input" value="'+Attachment+'"></td><td><span>'+Nlp_1+'</span><input type="text" name="" class="td-input" value="'+Nlp_1+'"></td><td><span>'+Nlp_2+'</span><input type="text" name="" class="td-input" value="'+Nlp_2+'"></td><td><span>'+Nlp_3_and_4_QC+'</span><input type="text" name="" class="td-input" value="'+Nlp_3_and_4_QC+'"></td><td><span>'+Ui_Update+'</span><input type="text" name="" class="td-input" value="'+Ui_Update+'"></td><td><span>'+brokerName+'</span><input type="text" name="" class="td-input" value="'+brokerName+'"></td><td class="display-none"></td><td><a href="#" class="btn btn-success btn-sm edit-table-btn"><i class="fa fa-pencil" aria-hidden="true"></i></a><a href="#" class="btn btn-danger btn-sm delete-table-btn"><i class="fa fa-trash-o" aria-hidden="true"></i></a></td></tr>';
									
							}// main for

								/*tonnageTable = '<tr class="firstTr" style="display: none;"><td>'
										+ tonnageId
										+ '</td><td class="dblclick"><input type="text" name="reporttype" class="display-block-input display-none" id="reporttype"></td><td class="dblclick"><input type="text" name="repositionregion" class="display-block-input display-none" id="repositionregion"></td><td class="dblclick"><input type="text" name="cargotype" class="display-block-input display-none" id="cargotype"></td><td class="dblclick"><input type="text" name="vesselname" class="display-block-input display-none" id="vesselname"></td><td class="dblclick"><input type="text" name="vesseltype" class="display-block-input display-none" id="vesseltype"></td><td class="dblclick"><input type="text" name="built" class="display-block-input display-none" id="built"></td><td class="dblclick"><input type="text" name="dwt" class="display-block-input display-none" id="dwt"></td><td class="dblclick"><input type="text" name="cubics" class="display-block-input display-none" id="cubics"></td><td class="dblclick"><input type="text" name="loa" class="display-block-input display-none" id="loa"></td><td class="dblclick"><input type="text" name="ice" class="display-block-input display-none" id="ice"></td><td class="dblclick"><input type="text" name="sternline" class="display-block-input display-none" id="sternline"></td><td class="dblclick"><input type="text" name="owners" class="display-block-input display-none" id="owners"></td><td class="dblclick"><input type="text" name="operators" class="display-block-input display-none" id="operators"></td><td class="dblclick"><input type="text" name="employementstatus" class="display-block-input display-none" id="employementstatus"></td><td class="dblclick"><input type="text" name="openport" class="display-block-input display-none" id="openport"></td><td class="dblclick"><input type="text" name="opendate" class="display-block-input display-none" id="opendate"></td><td class="dblclick"><input type="text" name="etabasis" class="display-block-input display-none" id="etabasis"></td><td class="dblclick"><input type="text" name="comment" class="display-block-input display-none" id="comment"></td><td class="dblclick"><input type="text" name="source" class="display-block-input display-none" id="source"></td><td class="dblclick"><input type="text" name="reporttimestamp" class="display-block-input display-none" id="reporttimestamp"></td><td class="dblclick"><input type="text" name="" class="display-block-input display-none"></td><td class="display-none"><span>subNodeName</span><input type="text" name="editnodenameTonnage" class="td-input" value=""></td><td><a href="#" class="btn btn-success btn-sm edit-table-btn"><i class="fa fa-pencil" aria-hidden="true"></i></a><a href="#" class="btn btn-danger btn-sm delete-table-btn"><i class="fa fa-trash-o" aria-hidden="true"></i></a></td></tr>'
										+ tonnageTable;*/
								
								document.getElementById("statusUiProcess").innerHTML = cronTable;


						},
					//async: false
					}); // cron ajax close
		}