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