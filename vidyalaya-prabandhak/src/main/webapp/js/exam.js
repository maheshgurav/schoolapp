function saveAttendance(){
	var date = document.getElementById("date").value;
	var absentRollNumbers = document.getElementById("absentRollNumbers").value;
    var classGuid = $('#classguid').val();
    $.get("updateattendance.htm?date="+date+"&absentrollnumbers="+absentRollNumbers+"&classguid="+classGuid,
		function(data) {
	});
}

function addsubjectrow(){
	var table = document.getElementById("subject_table");
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);
	
	var cell1 = row.insertCell(0);
	var subjectName = document.createElement("select");
	subjectName.id = "subject_" + rowCount;
	cell1.appendChild(subjectName);
	var subjectJson = document.getElementById("subjects").value;
	data = JSON.parse(subjectJson);
	createOptions(data, subjectName);

	var cell2 = row.insertCell(1);
	var marksCell = document.createElement("input");
	marksCell.type = "text";
	marksCell.name = "marks_" + rowCount;
	marksCell.id = "marks_" + rowCount;
	cell2.appendChild(marksCell);

	var cell3 = row.insertCell(2);
	var marksForPassingCell = document.createElement("input");
	marksForPassingCell.type = "text";
	marksForPassingCell.name = "marksforpassing_" + rowCount;
	marksForPassingCell.id = "marksforpassing_" + rowCount;
	cell3.appendChild(marksForPassingCell);
}

function createOptions(subjects,selectElement){
	for(var i = 0 ; i < subjects.length ; i++){
		var option = document.createElement("option");
		option.value =  subjects[i].code; 
		option.innerHTML =  subjects[i].name; 
		option.id = subjects[i].guid;
		selectElement.options.add(option);
	}
}

function submitExamSettingsFormObject(){
	var form = document.getElementById("exam_form");
	var table = document.getElementById("subject_table");
	var enteredSubjectAndMarks = document.getElementById("enteredSubjectAndMarks");
	var rowCount = table.rows.length - 1;
	for(var count=1;count<=rowCount;count++){
		var subjectElement = document.getElementById("subject_"+count);
		var nameOfSubject = subjectElement.options[subjectElement.selectedIndex].id;
		var marks = document.getElementById("marks_"+count).value;
		var marksForPassing = document.getElementById("marksforpassing_"+count).value;
		if(marks == ''){
			marks = 0;
		}
		if(marksForPassing == ''){
			marksForPassing = 0;
		}
		
		if(nameOfSubject!=''){
			enteredSubjectAndMarks.value += "{\"subject\":\""+ nameOfSubject + "\"," + 
											"\"marks\":\""+ marks + "\"," + 
											"\"marksRequiredForPassing\":\""+ marksForPassing + "\"}," ;
		}
	}	
	form.submit();
}

function getExams(){
	var classAndDivision = document.getElementById("classguid").value;
	$.get("loadstudentexams.htm?class="+classAndDivision,
		function(data) {
		var selectElement = document.getElementById("examguid");
		if(selectElement.options)
			selectElement.options.length = 0;
		data = JSON.parse(data);
		for(var count=0;count<data.length;count++){
			var option = document.createElement("option");
			option.value = data[count].id;
			option.id = data[count].id;
			option.text= data[count].name;
			selectElement.appendChild(option);
			$("#examguid").trigger('chosen:updated');
		}
	});
}

function getSubjects(){
	var classAndDivision = document.getElementById("classguid").value;
	var exam = document.getElementById("examguid").value;
	$.get("loadexamwisesubjects.htm?class="+classAndDivision+"&exam="+exam,
		function(data) {
		var selectElement = document.getElementById("subject");
		if(selectElement.options)
			selectElement.options.length = 0;
		for(var count2=0;count2<selectElement.length;count2++){
			selectElement.options[count2] = null;
		}
		data = JSON.parse(data);
		for(var count=0;count<data.length;count++){
			var option = document.createElement("option");
			option.value = data[count].subject;
			option.id = data[count].subject_code;
			option.text= data[count].subject;
			selectElement.appendChild(option);
			$("#subject").trigger('chosen:updated');
		}
	});
}


/*function getStudentsAndMarks(){
	var classAndDivision = document.getElementById("classguid").value;
	var examguid = document.getElementById("examguid").value;
	var table = document.getElementById("markstable");
	var subjectGuid = document.getElementById("subject").options;
	var absentNumbers = "";
	$.get("loadsubjectwisestudents.htm?class="+classAndDivision + "&examguid="+examguid + "&subjectguid="+subjectGuid[subjectGuid.selectedIndex].id,
		function(data) {
		$("table#markstable").find("tr:not(:has(th))").remove();
			data = JSON.parse(data);
			for(var count=0;count<data.length;count++){
				var row = table.insertRow(count + 1);
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				var cell3 = row.insertCell(2);
				cell1.innerHTML = data[count].roll;
				cell2.innerHTML = data[count].name;
				cell3.innerHTML = "<input type=\"text\" id=student_" + (count+1) + " name=" + data[count].id + " value=" + data[count].marks + "></input>";
			}
			document.getElementById("absentRollNumbers").value = absentNumbers; 
			
			$('#markstable').dataTable();
	});
}
*/

function getStudentsAndMarks(){
	var classAndDivision = document.getElementById("classguid").value;
	var examguid = document.getElementById("examguid").value;
	var table = document.getElementById("markstable");
	var subjectGuid = document.getElementById("subject").options;
	var absentNumbers = "";
	
	var table = document.getElementById("table_here");
	$('#table_here').html('');
	
	var table_html = '';
	table_html += "<table id=\"markstable\" class=\"cell-border\">" +
	"<thead><tr><th>Roll Number</th><th>Name</th><th>Marks</th></thead><tbody>";
	
	$.get("loadsubjectwisestudents.htm?class="+classAndDivision + "&examguid="+examguid + "&subjectguid="+subjectGuid[subjectGuid.selectedIndex].id,
			function(data) {
				data = JSON.parse(data);
				for(var count=0;count<data.length;count++){
					table_html += "<tr>";
					table_html += "<td id=\"student_" + data[count].roll + "\">" + data[count].roll + "</td>";
					table_html += "<td>" + data[count].name + "</td>";
					table_html += "<td><input type=\"text\" id=\"" + data[count].id + "\" class=\"txtBox\"" + " name= \""+ (count+1) +"\" value = "+ data[count].marks +"></input>" + "</td>";
					table_html += "</tr>";
				}
				table_html += "</tbody></table>";
				$('#table_here').append(table_html);
				$('#markstable').dataTable();
	});
}

function submitFillExamMarksFormObject(){
	var form = document.getElementById("fillmarks");
	var table = document.getElementById("markstable");
	var enteredSubjectAndMarks = document.getElementById("marks");
	var rowCount = table.rows.length - 1;
	enteredSubjectAndMarks.value = "";
	for(var i=1;i<table.rows.length;i++)
	{
	    for(var j=0;j<table.rows[i].cells.length;j++)
	    {
	    	if(table.rows[i].cells[j].children[0] != undefined){
	    		var student_element = table.rows[i].cells[j].children[0];
	    		var id = student_element.id;
	    		var marks = student_element.value;
	    		if(marks == ''){
	    			marks = 0;
	    		}
	    		enteredSubjectAndMarks.value += "{\"id\":\""+ id + "\"," + 
				"\"marks\":\""+ marks + "\"}," ;
	    	}
	    }
	}
	
	var classguid = document.getElementById("classguid");
	document.getElementById("form_classGuid").value = classguid.options[classguid.selectedIndex].id;
	var examguid = document.getElementById("examguid");
	document.getElementById("form_examGuid").value = examguid.options[examguid.selectedIndex].id;
	var subjectguid = document.getElementById("subject");
	document.getElementById("form_subjectGuid").value = subjectguid.options[subjectguid.selectedIndex].id;
	form.submit();
}

function getSubjectAndMarks(){
	var classAndDivision = document.getElementById("classguid").value;
	var table = document.getElementById("markstable");
	var exam = document.getElementById("examguid").value;
	var studentGuid = document.getElementById("studentGuid").value;
	var classguid = document.getElementById("classguid").value;
	$("#markstable").find("tr:gt(0)").remove();
	$.get("loadsubjectandmarks.htm?exam="+exam+"&studentGuid="+studentGuid+"&classguid="+classguid,
		function(data) {
		$("table#tbl_student").find("tr:not(:has(th))").remove();
			data = JSON.parse(data);
			for(var count=0;count<data.length;count++){
				var row = table.insertRow(count + 1);
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				var cell3 = row.insertCell(2);
				cell1.innerHTML = data[count].subject_code;
				cell2.innerHTML = data[count].subject_name;
				cell3.innerHTML = "<input type=\"text\" name=" + data[count].subject_code + " id=subject_" + (count+1) + " value=" + data[count].marks + "></input>";
			}
	});
}

function saveSubjectAndMarks(){
	var table = document.getElementById("markstable");
	var exam = document.getElementById("examguid").value;
	var studentGuid = document.getElementById("studentGuid").value;
	var classguid = document.getElementById("classguid").value;
	var rowCount = table.rows.length - 1;
	var enteredSubjectAndMarks = ""
	for(var count=1;count<=rowCount;count++){
		var subject_element = document.getElementById("subject_"+count);
		var id = subject_element.name;
		var marks = subject_element.value;
		if(marks == ''){
			marks = 0;
		}
		
		enteredSubjectAndMarks += "{\"id\":\""+ id + "\"," + 
											"\"marks\":\""+ marks + "\"}," ;
	}
	$.get("savesubjectandmarks.htm?exam="+exam+"&studentGuid="+studentGuid+"&classguid="+classguid+"&marks="+enteredSubjectAndMarks,
		function(data) {
		});
}


function getStudentReportCard(type){
	var exam = document.getElementById("examguid").value;
	var studentGuid = document.getElementById("studentGuid").value;
	if(type == 'html'){
		$.get("getstudentreportcard.htm?exam="+exam+"&studentGuid="+studentGuid,
				function(data) {
					var wnd = window.open("about:blank","","_blank");
					wnd.document.write(data);
		});
	}else{
		window.open("getstudentreportcardpdf.htm?exam="+exam+"&studentGuid="+studentGuid,"","_blank");
	}
}