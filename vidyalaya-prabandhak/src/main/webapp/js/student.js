function getBonafide(id){
	window.open("getbonafide.htm?id="+ id,"","_blank");
/*$.get("getbonafide.htm?id="+ id,
	function(data) {
		var wnd = window.open("about:blank","","_blank");
		wnd.document.write(data);
	});*/
}

function saveSubjects(){
	var studentguid = document.getElementById("student_guid").value;
	var selected_subjects = [];
	$('div#subs input[type=checkbox]').each(function(){
		if($(this).is(":checked")){
			selected_subjects.push($(this).attr('id'));
		}
	});
	$.get("savesubjects.htm?studentguid="+studentguid+"&subjects="+selected_subjects,
		function(data) {
			if(data == "Success"){
				document.getElementById("success_alert").style.display = "block"
			}else {
				document.getElementById("failure_alert").style.display = "block"
			}
				
	});

}