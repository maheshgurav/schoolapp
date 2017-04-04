function updateAbsentStudent(id){
	var absentRollNumbers = document.getElementById("absentRollNumbers").value;
	if(document.getElementById(id).checked == false){
		if(absentRollNumbers==null || absentRollNumbers==""){
			absentRollNumbers = document.getElementById(id).id;
		}else{
			absentRollNumbers = absentRollNumbers + "," + document.getElementById(id).id;
		}
	}
	document.getElementById("absentRollNumbers").value = absentRollNumbers; 
}

function saveAttendance(){
	var date = document.getElementById("date").value;
	var absentRollNumbers = document.getElementById("absentRollNumbers").value;
    var classGuid = $('#classguid').val();
    $.get("updateattendance.htm?date="+date+"&absentrollnumbers="+absentRollNumbers+"&classguid="+classGuid,
		function(data) {
    		alert(data);
	});
}

function checkAndSelectCheckBox(textfield, e){
    if (!e) var e = window.event
    if (e.keyCode) code = e.keyCode;
    else if (e.which) code = e.which;
    var character = String.fromCharCode(code);
    var regexIntegerOnly = /[0-9\.\,]/g;

    if(textfield.value){
        if(textfield.value.charAt(textfield.value.length-1)=="," && character==",")
            return false;
        var arr = textfield.value.split(",");

        $('input:checkbox').each(function () {
            if($.inArray(this.id, arr)!=-1){
                document.getElementById(this.id).checked = false;
            }else{
                document.getElementById(this.id).checked = true;
            }
        });
    }

    if(textfield.value == null || textfield.value == "" || textfield.value == ","){
        $('input:checkbox').each(function () {
            document.getElementById(this.id).checked = true;
        });
    }

    // control keys
    if ((code==null) || (code==0) || (code==8) || (code==9) || (code==13) || (code==27) || (code==46))
        return true;

    //Don't allow this charactersif (code != 9 || code != 8 && code != 36 || code != 37 || code != 38 || (code != 39 || (code == 39 || character == "'")) || code != 40 ) {
    if (character.match(regexIntegerOnly)) {
        return true;
    }else{
        return false;
    }
}

function addCommaAtLast(){
    var valueFromText = document.getElementById("absentRollNumbers").value;
    if(valueFromText.charAt(valueFromText.length-1) != "," && valueFromText!="")
        document.getElementById("absentRollNumbers").value = document.getElementById("absentRollNumbers").value + ",";
}

/*function getAttendance(){
	var classAndDivision = document.getElementById("classguid").value;
	var date = document.getElementById("date").value;
	var table = document.getElementById("attendancetable");
	var absentNumbers = "";
	$.get("showattendance.htm?class="+classAndDivision + "&date="+date,
		function(data) {
		$("table#attendancetable").find("tr:not(:has(th))").remove();
			data = JSON.parse(data);
			for(var count=0;count<data.length;count++){
				var checkboxElement = document.createElement("input");
				checkboxElement.setAttribute("data-no-uniform","true");
				checkboxElement.style.property = "iphone-toggle";
				var row = table.insertRow(count + 1);
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				var cell3 = row.insertCell(2);
				cell1.innerHTML = data[count].roll;
				cell2.innerHTML = data[count].name;
				if(data[count].absent == 1){
					cell3.innerHTML = "<input data-no-uniform=\"true\" type=\"checkbox\" id=" + data[count].roll +" "
					+"onclick=\"javascript:updateAbsentStudent("+ data[count].roll + ")\" >";
					absentNumbers = data[count].roll + ",";
				}else{
					cell3.innerHTML = "<input data-no-uniform=\"true\" type=\"checkbox\" id=" + data[count].roll +" "
					+"onclick=\"javascript:updateAbsentStudent("+ data[count].roll + ")\" checked=\"true\">";
				}
			}
			document.getElementById("absentRollNumbers").value = absentNumbers; 
	});
}
*/




function getAttendance(){
	var table = document.getElementById("table_here");
	$('#table_here').html('');
	
	var table_html = '';
	table_html += "<table id=\"markstable\" class=\"cell-border\">" +
	"<thead><tr><th>Roll Number</th><th>Name</th><th>Marks</th></thead><tbody>";
	var classAndDivision = document.getElementById("classguid").value;
	var date = document.getElementById("date").value;
	var table = document.getElementById("attendancetable");
	var absentNumbers = "";
	$.get("showattendance.htm?class="+classAndDivision + "&date="+date,
		function(data) {
		$("table#attendancetable").find("tr:not(:has(th))").remove();
			data = JSON.parse(data);
				for(var count=0;count<data.length;count++){
					table_html += "<tr>";
					table_html += "<td>" + data[count].roll + "</td>";
					table_html += "<td>" + data[count].name + "</td>";

					var checkboxElement = document.createElement("input");
					checkboxElement.setAttribute("data-no-uniform","true");
					checkboxElement.style.property = "iphone-toggle";

					if(data[count].absent == 1){
						table_html += "<td><input data-no-uniform=\"true\" type=\"checkbox\" id=" + data[count].roll +" "
						+"onclick=\"javascript:updateAbsentStudent("+ data[count].roll + ")\" ></td>";
						absentNumbers = data[count].roll + ",";
					}else{
						table_html += "<td><input data-no-uniform=\"true\" type=\"checkbox\" id=" + data[count].roll +" "
						+"onclick=\"javascript:updateAbsentStudent("+ data[count].roll + ")\" checked=\"true\"></td>";
					}

					table_html += "</tr>";
				}
				table_html += "</tbody></table>";
				$('#table_here').append(table_html);
				$('#markstable').dataTable();
				document.getElementById("absentRollNumbers").value = absentNumbers; 
	});
}
