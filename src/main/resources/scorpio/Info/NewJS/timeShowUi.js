function lastTimeProcessed() {
	$
	.ajax({
		url : 'https://dev.bizlem.io:8082/scorpio/lastTime',
		type : 'GET',
		success : function(data) {
			var jsonStr = data;
			alert("LastTime:: "+data);
			var tonnageTable = "";
			
			tonnageTable = tonnageTable+ ' <span>'+data+'</span>';
			
			document.getElementById("lastTime").innerHTML = tonnageTable;
		}
	});
}