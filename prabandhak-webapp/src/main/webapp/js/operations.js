/**
 * 
 */


function changeClass(){
	var classAndDivision = document.getElementById("classguid").value;
	var newClassAndDivision = document.getElementById("new_classguid").value;
	var selected_students = [];
	$("#tbl_student").find('input[type="checkbox"]:checked').each(function()
			{
				selected_students.push(this.id);
			});
		$.get("changeclassanddivision.htm?class="+classAndDivision+"&newclass="+newClassAndDivision+"&selected_students="+selected_students,
			function(data) {
				var wnd = window.open("about:blank","","_blank");
				wnd.document.write(data);
		});
}

function markAlumnis(){
	var classAndDivision = document.getElementById("classguid").value;
	var selected_students = [];
	$("#tbl_student").find('input[type="checkbox"]:checked').each(function()
			{
				selected_students.push(this.id);
			});
		$.get("makealumnis.htm?class="+classAndDivision+"&selected_students="+selected_students,
			function(data) {
				var wnd = window.open("about:blank","","_blank");
				wnd.document.write(data);
		});
}