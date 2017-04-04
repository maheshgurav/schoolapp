/*Functions to load castes*/
function loadCasts(){
$.get("loadcastes.htm",
	function(data) {
		alert("Controller called : " + data);
		var array = JSON.parse(data);
	});
}

/*Functions to load categories*/
function loadCategories(){
$.get("loadcategories.htm",
	function(data) {
		alert("Controller called : " + data);
		var array = JSON.parse(data);
	});
}

/*Functions to load scholarship types*/
function loadScholarshipTypes(){
$.get("loadscholarshiptypes.htm",
	function(data) {
		alert("Controller called : " + data);
		var array = JSON.parse(data);
	});
}

/*Functions to load classes*/
function loadClasses(){
$.get("loadclasses.htm",
	function(data) {
		alert("Controller called : " + data);
		var array = JSON.parse(data);
	});
}

function updateRollNumbers(){
	var classAndDivision = document.getElementById("classguid").value;
	$.get("updaterollnumbers.htm?class="+classAndDivision,
		function(data) {
			if(data == "true"){
				alert("Student roll numbers updated successfully");
			}else{
				alert("Roll numbers not updated");
			}
	});
}

function loadExams(e){
		e.preventDefault();
		$('#myModal').modal('show');
}

function addSubject(){
	var table = document.getElementById("tbl_subject_teachers_details");
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);
	
	var cell1 = row.insertCell(0);
	var subjectName = document.createElement("select");
	subjectName.id = "subject_" + rowCount;
	cell1.appendChild(subjectName);
	var subjectJson = document.getElementById("subjects").value;
	data = JSON.parse(subjectJson);
	createSubjectOptions(data, subjectName);

	var cell2 = row.insertCell(1);
	var teacherName = document.createElement("select");
	teacherName.id = "teacher_" + rowCount;
	cell2.appendChild(teacherName);
	var teacherJson = document.getElementById("teachers").value;
	data = JSON.parse(teacherJson);
	createTeacherOptions(data, teacherName);
}

function createSubjectOptions(subjects,selectElement){
	for(var i = 0 ; i < subjects.length ; i++){
		var option = document.createElement("option");
		option.value =  subjects[i].guid; 
		option.innerHTML =  subjects[i].name; 
		option.id = subjects[i].guid;
		selectElement.options.add(option);
	}
}

function createTeacherOptions(subjects,selectElement){
	for(var i = 0 ; i < subjects.length ; i++){
		var option = document.createElement("option");
		option.value =  subjects[i].guid; 
		option.innerHTML =  subjects[i].name; 
		option.id = subjects[i].guid;
		selectElement.options.add(option);
	}
}



function submitClassInfoSettingsFormObject(){
	var form = document.getElementById("classinfo_form");
	var table = document.getElementById("tbl_subject_teachers_details");
	var subjectsAndTeachers = document.getElementById("subjectsAndTeachers");
	var rowCount = table.rows.length - 1;
	subjectsAndTeachers.value = "[";
	for(var count=1;count<=rowCount;count++){
		var subjectElement = document.getElementById("subject_"+count);
		var subjectGuid = subjectElement.options[subjectElement.selectedIndex].id;
		var teacherGuid = document.getElementById("teacher_"+count).value;
		
		if(subjectGuid !='' && teacherGuid !=''){
			subjectsAndTeachers.value += "{\"subject_id\":\""+ subjectGuid + "\"," + 
											"\"teacher_id\":\""+ teacherGuid + "\"}," ;
		}
	}
	if(subjectsAndTeachers.value!=''){
		subjectsAndTeachers.value = subjectsAndTeachers.value.substring(0, subjectsAndTeachers.value.length - 1); 
		subjectsAndTeachers.value += "]";
	}
	form.submit();
}
