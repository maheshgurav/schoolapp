/**
 * 
 */

function generateCertificates(){
	var classAndDivision = document.getElementById("classguid").value;
	var typeOfReport = document.getElementById("reporttype").value;
	var selected_students = [];
	$("#tbl_student").find('input[type="checkbox"]:checked').each(function()
			{
				selected_students.push(this.id);
			});
	if(typeOfReport == 'html'){
		$.get("generatebonafidecertificates.htm?class="+classAndDivision+"&selected_students="+selected_students+"&typeOfReport="+typeOfReport,
			function(data) {
				var wnd = window.open("about:blank","","_blank");
				wnd.document.write(data);
		});
	}
	if(typeOfReport == 'pdf'){
		window.open("generatebonafidecertificatespdf.htm?class="+classAndDivision+"&selected_students="+selected_students+"&typeOfReport="+typeOfReport,"","_blank");
	}
}


function getLeavingCertificates(){
	var classAndDivision = document.getElementById("classguid").value;
	var typeOfReport = document.getElementById("reporttype").value;
	
	var progress = document.getElementById("progress").value;
	var conduct = document.getElementById("conduct").value;
	var reasonOfLeaving = document.getElementById("reasonOfLeaving").value;
	var remark = document.getElementById("remark").value;
	var leavingDate = document.getElementById("leavingDate").value;
	var studyingSinceMonthAndYear = document.getElementById("studyingSinceMonthAndYear").value;
	
	var selected_students = [];
	$("#tbl_student").find('input[type="checkbox"]:checked').each(function()
			{
				selected_students.push(this.id);
			});
	if(typeOfReport == 'html'){
		$.get("generateleavingcertificates.htm?class="+classAndDivision+"&selected_students="+selected_students+"&typeOfReport="+typeOfReport
				+"&progress="+progress+"&conduct="+conduct+"&reasonOfLeaving="+reasonOfLeaving+"&leavingDate="+leavingDate+"&remark="+remark+"&studyingSinceMonthAndYear="+studyingSinceMonthAndYear,
			function(data) {
				var wnd = window.open("about:blank","","_blank");
				wnd.document.write(data);
		});
	}
	if(typeOfReport == 'pdf'){
		window.open("generatelcpdf.htm?class="+classAndDivision+"&selected_students="+selected_students+"&typeOfReport="+typeOfReport
				+"&progress="+progress+"&conduct="+conduct+"&reasonOfLeaving="+reasonOfLeaving+"&leavingDate="+leavingDate+"&remark="+remark+"&studyingSinceMonthAndYear="+studyingSinceMonthAndYear,"","_blank");
	}
}