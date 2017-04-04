function getReport(typeOfReport){
	var reportFormat = document.getElementById("reportFormat").value;
	if(reportFormat == "Html"){
			$.get(getUrl(typeOfReport),
		function(data) {
			var wnd = window.open("about:blank","","_blank");
			wnd.document.write(data);
		});
	}
	if(reportFormat == "Pdf"){
		getPdfReport(typeOfReport);
	}
}

function getPdfReport(typeOfReport){
	window.open(getUrl(typeOfReport),"","_blank");
}

function getUrl(typeOfReport){
	var url = "";
	var reportFormat = document.getElementById("reportFormat").value;
	var classguid = document.getElementById("classguid").value;
	var gender = document.getElementById("gender").value;
	var category = document.getElementById("category").value;
	var month = document.getElementById("month").value;
	var year = document.getElementById("year").value;
	if(reportFormat == "Html"){
		url = "getreportashtml.htm?";
	}
	if(reportFormat == "Pdf"){
		url = "getreportaspdf.htm?";
	}
	if(typeOfReport == 0){
		url += "classguid=" + classguid + "&gender=" + gender + "&typeofreport=" + typeOfReport;
	}
	if(typeOfReport == 3){
		var casteguid = document.getElementById("casteguid").value;
		url += "classguid=" + classguid + "&gender=" + gender + "&typeofreport=" + typeOfReport
				+"&casteguid="+casteguid;
	}
	if(typeOfReport == 6){
		var scholarshipguid = document.getElementById("scholarshipguid").value;
		url += "classguid=" + classguid + "&gender=" + gender + "&typeofreport=" + typeOfReport
				+"&scholarshipguid="+scholarshipguid;
	}
	if(typeOfReport == 1){
		var scholarshipguid = document.getElementById("scholarshipguid").value;
		url += "classguid=" + classguid + "&gender=" + gender + "&typeofreport=" + typeOfReport +"&category="+category;
	}
	if(typeOfReport == 8){
		url += "classguid=" + classguid + "&gender=" + gender + "&typeofreport=" + typeOfReport;
	}
	if(typeOfReport == 9){
		url += "classguid=" + classguid + "&gender=" + gender + "&typeofreport=" + typeOfReport;
	}
	if(typeOfReport == 10){
		url += "classguid=" + classguid + "&typeofreport=" + typeOfReport;
	}
	if(typeOfReport == 11){
		url += "classguid=" + classguid + "&typeofreport=" + typeOfReport;
	}
	if(typeOfReport == 12){
		url += "classguid=" + classguid + "&typeofreport=" + typeOfReport + "&month=" + month + "&year=" + year;
	}
	if(typeOfReport == 13){
		url += "classguid=" + classguid + "&typeofreport=" + typeOfReport;
	}
	return url;
}