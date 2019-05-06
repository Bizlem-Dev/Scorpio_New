package com.NewStructureScorpio;

import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.readGmail.GmailMethods;

public class RateRegex {

	public static void main(String[] args) {
		String data=RateRegrex("$5000", null);
				System.out.println(data);

		String ratetype=getRateVal("725,000 / 1,550,000");
		System.out.println(ratetype);
	}

	public static String RateRegrex(String url, PrintWriter out) {
		String etaBasisDate = "";
		try {
			Pattern id = null;
			Matcher m = null;

			// String url="EASTPORT CLEAN MR POSITION BSS S.KOREA AS AT 15th MAR
			// 2019";
			id = Pattern.compile("([RrNn]{3})");
			m = id.matcher(url);
			if (m.find()) {
				etaBasisDate = m.group(1);
				//System.out.println("alag0:: " + etaBasisDate);

			} else {
				id = Pattern.compile("([Oo]\\/[Pp])");
				m = id.matcher(url);
				if (m.find()) {
					etaBasisDate = m.group(1);
					//System.out.println("alag1:: " + etaBasisDate);
				} else {
					id = Pattern.compile("([0-9]{1,3}\\s([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7}))");
					m = id.matcher(url);
					if (m.find()) {
						etaBasisDate = m.group(1);
						//System.out.println("alag2:: " + etaBasisDate);
					} else {
						id = Pattern.compile("(([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7})\\s[0-9]{1,3})");
						m = id.matcher(url);
						if (m.find()) {
							etaBasisDate = m.group(1);
							//System.out.println("alag3:: " + etaBasisDate);
						} else {
							id = Pattern.compile("([0-9]{1,3}\\-[0-9]{1,3}\\.[0-9]\\-[0-9]{1,3}\\.[0-9]\\s([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7}))");
							m = id.matcher(url);
							if (m.find()) {
								etaBasisDate = m.group(1);
								//System.out.println("alag4:: " + etaBasisDate);
							} else {
								id = Pattern.compile("(([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7})\\s[0-9]{1,3}\\-[0-9]{1,3}\\.[0-9]\\-[0-9]{1,3}\\.[0-9])");
								m = id.matcher(url);
								if (m.find()) {
									etaBasisDate = m.group(1);
									//System.out.println("alag5:: " + etaBasisDate);
								} else {
									id = Pattern.compile("([LlSs]{2}\\s[0-9]\\.[0-9]([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7}))");
									m = id.matcher(url);
									if (m.find()) {
										etaBasisDate = m.group(1);
										//System.out.println("alag6:: " + etaBasisDate);
									} else {
										id = Pattern.compile("([0-9]{3,}[/|-][0-9]{3,})");
										m = id.matcher(url);
										if (m.find()) {
											etaBasisDate = m.group(1);
											//System.out.println("alag7:: " + etaBasisDate);
										} else {
											id = Pattern.compile("([0-9]{1,3}\\.[0-9]\\s([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7}))");
											m = id.matcher(url);
											if (m.find()) {
												etaBasisDate = m.group(1);
												//System.out.println("alag8:: " + etaBasisDate);
											} else {
												id = Pattern.compile("(([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7})\\s[0-9]{1,3}\\.[0-9])");
												m = id.matcher(url);
												if (m.find()) {
													etaBasisDate = m.group(1);
													//System.out.println("alag9:: " + etaBasisDate);
												} else {
													id = Pattern.compile(
															"([UuSsDd]{1,3}\\s[0-9]\\.[0-9]{1,2}([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7}))");
													m = id.matcher(url);
													if (m.find()) {
														etaBasisDate = m.group(1);
														//System.out.println("alag10:: " + etaBasisDate);
													} else {
														id = Pattern
																.compile("([0-9]{1,3}([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7}))");
														m = id.matcher(url);
														if (m.find()) {
															etaBasisDate = m.group(1);
															//System.out.println("alag11:: " + etaBasisDate);
														} else {
															id = Pattern.compile(
																	"(([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7})[0-9]{1,3})");
															m = id.matcher(url);
															if (m.find()) {
																etaBasisDate = m.group(1);
																//System.out.println("alag12:: " + etaBasisDate);
															} else {
																id = Pattern.compile(
																		"(([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7})[0-9]{1,3})");
																m = id.matcher(url);
																if (m.find()) {
																	etaBasisDate = m.group(1);
																	//System.out.println("alag13:: " + etaBasisDate);
																} else {
																	id = Pattern.compile(
																			"([0-9]{1,3}([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7}))");
																	m = id.matcher(url);
																	if (m.find()) {
																		etaBasisDate = m.group(1);
																		//System.out.println("alag14:: " + etaBasisDate);
																	} else {
																		id = Pattern.compile(
																				"([0-9]{1,3}\\.[0-9]([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7}))");
																		m = id.matcher(url);
																		if (m.find()) {
																			etaBasisDate = m.group(1);
																			//System.out.println("alag15:: " + etaBasisDate);
																		} else {
																			id = Pattern.compile(
																					"(([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7})[0-9]{1,3}\\.[0-9])");
																			m = id.matcher(url);
																			if (m.find()) {
																				etaBasisDate = m.group(1);
																				//System.out.println("alag16:: " + etaBasisDate);
																			} else {
																				id = Pattern.compile(
																						"(([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7})[0-9]\\,[0-9]{1,3})");
																				m = id.matcher(url);
																				if (m.find()) {
																					etaBasisDate = m.group(1);
																					//System.out.println(
																							//"alag17:: " + etaBasisDate);
																				} else {
																					id = Pattern.compile(
																							"([OoWwNn]{3})");
																					m = id.matcher(url);
																					if (m.find()) {
																						etaBasisDate = m.group(1);
																						/*System.out.println("alag18:: "
																								+ etaBasisDate);*/
																					} else {
																						id = Pattern.compile(
																								"(([0-9]\\,[0-9]{1,3}([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7}))");
																						m = id.matcher(url);
																						if (m.find()) {
																							etaBasisDate = m.group(1);
																							/*System.out.println("alag19:: "
																									+ etaBasisDate);
*/																						} else {
																							id = Pattern.compile(
																									"(([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7})[0-9]{1,3}\\+)");
																							m = id.matcher(url);
																							if (m.find()) {
																								etaBasisDate = m
																										.group(1);
																								/*System.out.println("alag20:: "
																										+ etaBasisDate);*/
																							} else {
																								id = Pattern.compile(
																										"(([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7})[0-9]\\.[0-9]{1,2})");
																								m = id.matcher(url);
																								if (m.find()) {
																									etaBasisDate = m
																											.group(1);
																									/*System.out.println(
																											"alag21:: "
																													+ etaBasisDate);*/
																								} else {
																									id = Pattern
																											.compile(
																													"([0-9]\\.[0-9]{1,2}([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7}))");
																									m = id.matcher(url);
																									if (m.find()) {
																										etaBasisDate = m
																												.group(1);
																										/*System.out.println(
																												"alag22:: "
																														+ etaBasisDate);*/
																									} else {
																										id = Pattern
																												.compile(
																														"([0-9]{1,3}\\.[0-9]([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7}))");
																										m = id.matcher(
																												url);
																										if (m.find()) {
																											etaBasisDate = m
																													.group(1);
																											/*System.out.println(
																													"alag23:: "
																															+ etaBasisDate);*/
																										} else {
																											id = Pattern
																													.compile(
																															"(([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7})[0-9]{1,3}\\.[0-9])");
																											m = id.matcher(
																													url);
																											if (m.find()) {
																												etaBasisDate = m
																														.group(1);
																												/*System.out.println(
																														"alag24:: "
																																+ etaBasisDate);*/
																											} else {
																												id = Pattern
																														.compile(
																																"(([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7})[0-9]{1,3}\\-([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7}))");
																												m = id.matcher(
																														url);
																												if (m.find()) {
																													etaBasisDate = m
																															.group(1);
																													/*System.out.println(
																															"alag25:: "
																																	+ etaBasisDate);*/
																												} else {
																													id = Pattern
																															.compile(
																																	"(([UuSsDd]{1,3}[0-9]{1,3}[WSwsKkMmRrNnLlVvUuPp]{1,7})|([0-9]{1,3}[WSwsKkMmRrNnLlVvUuPp]{1,7}[UuSsDd]{1,3})|([WSwsKkMmRrNnLlVvUuPp]{1,7}[0-9]{1,3}[UuSsDd]{1,3}))");
																													m = id.matcher(
																															url);
																													if (m.find()) {
																														etaBasisDate = m
																																.group(1);
																														/*System.out.println(
																																"alag26:: "
																																		+ etaBasisDate);*/
																													} else {
																														id = Pattern
																																.compile(
																																		"([0-9]{1,3}([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7})\\-([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7}))");
																														m = id.matcher(
																																url);
																														if (m.find()) {
																															etaBasisDate = m
																																	.group(1);
																															/*System.out.println(
																																	"alag27:: "
																																			+ etaBasisDate);*/
																														} else {
																															id = Pattern
																																	.compile(
																																			"(([0-9]\\.[0-9]{1,2}\\s[MmIiLl]{4})|([MmIiLl]{4}\\s[0-9]\\.[0-9]{1,2}))");
																															m = id.matcher(
																																	url);
																															if (m.find()) {
																																etaBasisDate = m
																																		.group(1);
																																/*System.out.println(
																																		"alag28:: "
																																				+ etaBasisDate);*/
																															} else {
																																id = Pattern
																																		.compile(
																																				"([OoWwNn]{3})");
																																m = id.matcher(
																																		url);
																																if (m.find()) {
																																	etaBasisDate = m
																																			.group(1);
																																	/*System.out.println(
																																			"alag29:: "
																																					+ etaBasisDate);*/
																																} else {
																																	id = Pattern
																																			.compile(
																																					"([0-9]{1,2}\\.[0-9]{1,2}\\s([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7}))"); // end
																																	m = id.matcher(
																																			url);
																																	if (m.find()) {
																																		etaBasisDate = m
																																				.group(1);
																																		/*System.out.println(
																																				"alag30:: "
																																						+ etaBasisDate);*/
																																	} else {
																																		id = Pattern
																																				.compile(
																																						"(([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7})\\s[0-9]{1,2}\\.[0-9]{1,2})");
																																		m = id.matcher(
																																				url);
																																		if (m.find()) {
																																			etaBasisDate = m
																																					.group(1);
																																			/*System.out.println(
																																					"alag31:: "
																																							+ etaBasisDate);*/
																																		} else {
																																			id = Pattern
																																					.compile(
																																							"((\\$\\s[0-9]{1,3}\\,[0-9]{1,3})|([0-9]{1,3}\\,[0-9]{1,3}\\s\\$))");
																																			m = id.matcher(
																																					url);
																																			if (m.find()) {
																																				etaBasisDate = m
																																						.group(1);
																																				/*System.out.println(
																																						"alag32:: "
																																								+ etaBasisDate);*/
																																			} else {
																																				id = Pattern
																																						.compile(
																																								"([$][0-9]{1,3}([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7}))");
																																				m = id.matcher(
																																						url);
																																				if (m.find()) {
																																					etaBasisDate = m
																																							.group(1);
																																					/*System.out.println(
																																							"alag33:: "
																																									+ etaBasisDate);*/
																																				} else {
																																					id = Pattern
																																							.compile(
																																									"([UuSsDd]{1,3}[0-9]{1,3}([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7})\\/[0-9]{1,3}([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7})\\/[0-9]{1,3}([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7}))"); //
																																					m = id.matcher(
																																							url);
																																					if (m.find()) {
																																						etaBasisDate = m
																																								.group(1);
																																						/*System.out.println(
																																								"alag34:: "
																																										+ etaBasisDate);*/
																																					} else {
																																						id = Pattern
																																								.compile(
																																										"(([UuSsDd]{1,3}[0-9]{1,3}([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7}))");
																																						m = id.matcher(
																																								url);
																																						if (m.find()) {
																																							etaBasisDate = m
																																									.group(1);
																																							/*System.out.println(
																																									"alag35:: "
																																											+ etaBasisDate);*/
																																						} else {
																																							id = Pattern
																																									.compile(
																																											"([0-9]{1,3}\\.[0-9]\\/[0-9]{1,3}([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7}))");
																																							m = id.matcher(
																																									url);
																																							if (m.find()) {
																																								etaBasisDate = m
																																										.group(1);
																																								/*System.out.println(
																																										"alag36:: "
																																												+ etaBasisDate);*/
																																							}  else {
																																								id = Pattern
																																										.compile(
																																												"(([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7})[0-9]{1,3}\\.[0-9]\\/[0-9]{1,3})");
																																								m = id.matcher(
																																										url);
																																								if (m.find()) {
																																									etaBasisDate = m
																																											.group(1);
																																									/*System.out.println(
																																											"alag37:: "
																																													+ etaBasisDate);*/
																																								} else{
																																									id = Pattern
																																											.compile(
																																													"([0-9]{1,3}\\/[0-9]{1,3}([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7}))");
																																									m = id.matcher(
																																											url);
																																									if (m.find()) {
																																										etaBasisDate = m
																																												.group(1);
																																										/*System.out.println(
																																												"alag38:: "
																																														+ etaBasisDate);*/
																																									}else{
																																										id = Pattern
																																												.compile(
																																														"(([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7})[0-9]{1,3}\\/[0-9]{1,3})");
																																										m = id.matcher(
																																												url);
																																										if (m.find()) {
																																											etaBasisDate = m
																																													.group(1);
																																											/*System.out.println(
																																													"alag39:: "
																																															+ etaBasisDate);*/
																																										}else{
																																											id = Pattern
																																													.compile(
																																															"(([UuSsDd]{1,3}[0-9]{1,3}([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7}))");
																																											m = id.matcher(
																																													url);
																																											if (m.find()) {
																																												etaBasisDate = m
																																														.group(1);
																																												/*System.out.println(
																																														"alag40:: "
																																																+ etaBasisDate);*/
																																											}else{
																																												id = Pattern
																																														.compile(
																																																"(([UuSsDd]{1,3}[0-9]\\.[0-9]{1,3}([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7}))");
																																												m = id.matcher(
																																														url);
																																												if (m.find()) {
																																													etaBasisDate = m
																																															.group(1);
																																													/*System.out.println(
																																															"alag41:: "
																																																	+ etaBasisDate);*/
																																												}else{
																																													id = Pattern
																																															.compile(
																																																	"([$][0-9]{1,3}([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7}))");
																																													m = id.matcher(
																																															url);
																																													if (m.find()) {
																																														etaBasisDate = m
																																																.group(1);
																																														/*System.out.println(
																																																"alag42:: "
																																																		+ etaBasisDate);*/
																																													}else{
																																														id = Pattern
																																																.compile(
																																																		"([$][0-9]{1,3}([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7})[?])");
																																														m = id.matcher(
																																																url);
																																														if (m.find()) {
																																															etaBasisDate = m
																																																	.group(1);
																																															/*System.out.println(
																																																	"alag43:: "
																																																			+ etaBasisDate);*/
																																														}else{
																																															id = Pattern
																																																	.compile(
																																																			"([$][0-9]\\.[0-9]([WwSs]{2}|[Kk]|[Mm]|[Ww]|[RrNn]{3}|[LlVv]{3}|[LlUuMmPpSs]{7}))");
																																															m = id.matcher(
																																																	url);
																																															if (m.find()) {
																																																etaBasisDate = m
																																																		.group(1);
																																																/*System.out.println(
																																																		"alag44:: "
																																																				+ etaBasisDate);*/
																																															}else{
																																																id = Pattern
																																																		.compile(
																																																				"([0-9]\\.[0-9]{1,2}[Mm]\\-[Ww][0-9]{1,3}\\.[0-9])");
																																																m = id.matcher(
																																																		url);
																																																if (m.find()) {
																																																	etaBasisDate = m
																																																			.group(1);
																																																	/*System.out.println(
																																																			"alag45:: "
																																																					+ etaBasisDate);*/
																																																}else{
																																																	id = Pattern
																																																			.compile(
																																																					"([0-9]\\.[0-9][Mm]\\-[Ww][0-9]{1,3})");
																																																	m = id.matcher(
																																																			url);
																																																	if (m.find()) {
																																																		etaBasisDate = m
																																																				.group(1);
																																																		/*System.out.println(
																																																				"alag46:: "
																																																						+ etaBasisDate);*/
																																																	}else{
																																																		id = Pattern
																																																				.compile(
																																																						"([0-9]\\.[0-9]{1,3}[Mm]\\-[Ww][0-9]{1,3}\\.[0-9])"); // issue here
																																																		m = id.matcher(
																																																				url);
																																																		if (m.find()) {
																																																			etaBasisDate = m
																																																					.group(1);
																																																			/*System.out.println(
																																																					"alag47:: "
																																																							+ etaBasisDate);*/
																																																		}else{
																																																			id = Pattern
																																																					.compile(
																																																							"([0-9]{1,3}[Kk]\\-[0-9]\\.[0-9]{1,2}[Mm])");
																																																			m = id.matcher(
																																																					url);
																																																			if (m.find()) {
																																																				etaBasisDate = m
																																																						.group(1);
																																																				/*System.out.println(
																																																						"alag48:: "
																																																								+ etaBasisDate);*/
																																																			}else{
																																																				id = Pattern
																																																						.compile(
																																																								"([Ww][0-9]{1,2}\\/[Ww][0-9]{1,3})");
																																																				m = id.matcher(
																																																						url);
																																																				if (m.find()) {
																																																					etaBasisDate = m
																																																							.group(1);
																																																					/*System.out.println(
																																																							"alag49:: "
																																																									+ etaBasisDate);*/
																																																				}else{
																																																					id = Pattern
																																																							.compile(
																																																									"([Ww][0-9]{1,3}\\.[0-9])");
																																																					m = id.matcher(
																																																							url);
																																																					if (m.find()) {
																																																						etaBasisDate = m
																																																								.group(1);
																																																						/*System.out.println(
																																																								"alag50:: "
																																																										+ etaBasisDate);*/
																																																					}else{
																																																						/*System.out.println(
																																																								"no Rate Found");*/
																																																					}
																																																				}
																																																			}
																																																		}
																																																	}
																																																}
																																															}
																																														}
																																													}
																																												}
																																											}

																																										}
																																									}

																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}

												}
											}
										}
									}
								}
							}
						}
					}

				}

			}

		} catch (Exception e) {
			return etaBasisDate;
		}
		return etaBasisDate;
	}

	public static String getRateVal(String test) {
		String regexAlphOnly ="[^0-9.-//$//?//|//_]";

		Pattern pattern = Pattern.compile(regexAlphOnly);
		String regexNumOnly ="[^a-zA-Z.//$//?//|//_]";

		Pattern pattern1 = Pattern.compile(regexNumOnly);
		String rateType="";
		String rateTypeVal=null;
		String rate="";
		String []arr=  test.split("\\-|/", -1);
		//System.out.println(arr.length);
		boolean rateTypeFlag= false;
		for (String name:arr){
			//System.out.println("Name is "+name);
			Matcher matcher = pattern1.matcher(name);

			while (matcher.find()) {
				rate+=matcher.group();

			}

			Matcher matcher1 = pattern.matcher(name);
			while (matcher1.find()) {
				rateType+=matcher1.group();

			}
			if(!GmailMethods.isNullString(rateType)){
				switch(rateType.toUpperCase().trim())
				{
				case "LVL":
				case "K":
				case "M":{
					if(rateTypeFlag && !(GmailMethods.isNullString(rateTypeVal))){
						rateTypeVal = rateTypeVal+"-" + "LUMPSUM";
					}else{
						rateTypeVal="LUMPSUM";
					}

					break;
				}
				case "S":	  
				case "W":
				case "WS":{
					if(rateTypeFlag && !(GmailMethods.isNullString(rateTypeVal))){
						rateTypeVal = rateTypeVal+"-" + "WS";
					}else{
						rateTypeVal="WS";
					}
					break;
				}
				case "RNR":
				{ 
					if(rateTypeFlag && !(GmailMethods.isNullString(rateTypeVal))){
						rateTypeVal = rateTypeVal+"-" + "RNR";
					}else{
						rateTypeVal="RNR";
					}
					break; 
				}
				default:{
					if(rateTypeFlag && !(GmailMethods.isNullString(rateTypeVal))){
						if(!GmailMethods.isNullString(rate)){

							boolean setRateType = searchNearestPlace(test);
							if(setRateType){
								rateTypeVal ="";
							}
							else {
								rateTypeVal = rateTypeVal+"-" + "WS";
							}

						}else{
							rateTypeVal = rateTypeVal+"-" + "WS";
						}
					}
					if((GmailMethods.isNullString(rateTypeVal)) && (!GmailMethods.isNullString(rate))){
						boolean setRateType = searchNearestPlace(test);
						if(setRateType){
							rateTypeVal ="";
						}
						else {
							rateTypeVal = "lumpsum";
						}
					}

					break;}
				}

			}else{
				rateTypeVal="WS";
			}
			rateType="";
			rateTypeFlag = true;  

		}
		/*if(GmailMethods.isNullString(rateTypeVal) && (!GmailMethods.isNullString(rate))){
			rateTypeVal = "WS";
		}
		 */
		if(GmailMethods.isNullString(rate) && !("RNR".equalsIgnoreCase(rateTypeVal))){
			rateTypeVal = "";
		}
		return rateTypeVal;
	}

	public static boolean searchNearestPlace(String v2txt)
	{
		boolean containsMnth = false;
		try {
			v2txt = v2txt.toLowerCase();
			String[] places = {"jan","january","feb","february","mar","march","apr","april",
					"may","jun","june","jul","july","aug","august","sep","sept","september",
					"oct","october","nov","november","dec","december"};

			for(int i = 0; i<= places.length - 1; i++)
			{
				if(v2txt.contains(places[i]))
				{
					containsMnth = true;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}	

		return containsMnth;
	}

}
