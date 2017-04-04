function copyTo(source,destination){
	var destinations = destination.split(",");
	for(var count=0;count<destinations.length;count++){
		document.getElementById(destinations[count]).value = document.getElementById(source).value;
	}
}

function changeDD(source,destination){
	var element = document.getElementById(source);
	var sourceSelectedId = element.options[element.selectedIndex].id;
	if(element.selectedIndex !=-1){
		var destinationElement = document.getElementById(destination);
		for(var count=0;count<destinationElement.options.length;count++){
				if(destinationElement.options[count].id == sourceSelectedId){
						destinationElement.selectedIndex = count;
				}
			}
			$("#" + destination).trigger('chosen:updated');
		}
}

function getListByFilter(page){
	var filters = "";
	var url = getCurrentLocation();
	for(var count=1;count<8;count++){
		var filterValue = document.getElementById("filter_" + count).value;
		if(filterValue!=null && filterValue!="" && filters!=""){
 			filters += "&" + document.getElementById("filter_" + count).name + "=" + filterValue;
		}
		if(filterValue!=null && filterValue!="" && filters==""){
 			filters += "?" + document.getElementById("filter_" + count).name + "=" + filterValue;
		}
	}
	if(page!=null && filters!="")
		location.replace(url+ filters +"&page="+page);
	if(page!=null && filters=="")
		location.replace(url+ filters +"?page="+page);
	if(page==null)
		location.replace(url+filters);
}

function getCurrentLocation() {
	var url = document.location.href;
	var n = url.indexOf('?');
	url = url.substring(0, n != -1 ? n : url.length);
	return url;
}

function delay(ms){
    var d = new Date();
	ms += new Date().getTime();
	while(new Date() < ms){
	}
}

function getStudents(){
	var classAndDivision = document.getElementById("classguid").value;
	var table = document.getElementById("table_here");
	$('#table_here').html('');
	$('#table_here').append("<table id=\"tbl_student\" class=\"cell-border\"></table>");
	var dataSet = [];
	
	$.get("showstudents.htm?class="+classAndDivision ,
			function(data) {
		$("table#tbl_student").find("tr:not(:has(th))").remove();
		data = JSON.parse(data);	
		for(var count=0;count<data.length;count++){
			var arr1 = [];
			arr1.push(data[count].roll);
			arr1.push(data[count].name);
			dataSet.push(arr1);
		}
		
		$('#tbl_student').dataTable( {
	        "data":   dataSet,
	        "columns" : [
	                     {"title":"Roll Number"},
	                     {"title":"Name"},
	                     {"title":   "Select Student",
			                render: function ( data, type, row ) {
			                    if ( type === 'display' ) {
			                        return '<input type="checkbox" class="editor-active" id=' + row[0] + '>';
			                    }
			                    return data;
			                }
            }],
            
            rowCallback: function ( row, data ) {
                // Set the checked state of the checkbox in the table
                $('input.editor-active', row).prop( 'checked', true);
            }
	    });

	});
}

function getStudents(){
	var classAndDivision = document.getElementById("classguid").value;
	var table = document.getElementById("table_here");
	$('#table_here').html('');
	$('#table_here').append("<table id=\"tbl_student\" class=\"cell-border\"></table>");
	
	var table = document.getElementById("table_here");
	$('#table_here').html('');
	
	var table_html = '';
	table_html += "<table id=\"markstable\" class=\"cell-border\">" +
	"<thead><tr><th>Roll Number</th><th>Name</th><th>Operation</th></thead><tbody>";
	
	$.get("showstudents.htm?class="+classAndDivision ,
			function(data) {
				data = JSON.parse(data);
				for(var count=0;count<data.length;count++){
					table_html += "<tr>";
					table_html += "<td>" + data[count].roll + "</td>";
					table_html += "<td>" + data[count].name + "</td>";
					table_html += "<td><input type=\"checkbox\" id=" + data[count].roll + "></input>" + "</td>";
					table_html += "</tr>";
				}
				table_html += "</tbody></table>";
				$('#table_here').append(table_html);
				$('#markstable').dataTable();
	});
}


function appendColumn() {
    var tbl = document.getElementById('tbl_student'); // table reference
    // open loop for each row and append cell
    for (var i = 0; i < tbl.rows.length; i++) {
    	if(i == 0){
    		createCell(tbl.rows[i].insertCell(tbl.rows[i].cells.length), "Select/Unselect", 'col');
    	}else{
    		createCell(tbl.rows[i].insertCell(tbl.rows[i].cells.length), tbl.rows[i].cells[0].innerHTML, 'col');
    	}
    }
}

function createTextCell(cell, text, style) {
    var div = document.createElement('div'), // create DIV element
    txt = document.createTextNode(text); // create text node
    div.appendChild(txt);                    // append text node to the DIV
    div.setAttribute('class', style);        // set DIV class attribute
    div.setAttribute('className', style);    // set DIV class attribute for IE (?!)
    cell.appendChild(div);                   // append DIV to the table cell
}

function createCell(cell, text, style) {
    var div = document.createElement('div'); // create DIV element
    var txt = document.createElement("input");
    txt.setAttribute("type", "checkbox");
    txt.setAttribute("id", txt);
    txt.setAttribute("name", txt);
    //txt = document.createTextNode(text); // create text node
    div.appendChild(txt);                    // append text node to the DIV
    div.setAttribute('class', style);        // set DIV class attribute
    div.setAttribute('className', style);    // set DIV class attribute for IE (?!)
    cell.appendChild(div);                   // append DIV to the table cell
}

function buildDataTable(){
    $('#tbl_student').dataTable( {
        "paging":   true,
        "ordering": false,
        "info":     false
    } );
}


function getStudentsForAlumni(){
	var classAndDivision = document.getElementById("classguid").value;
	var table = document.getElementById("tbl_student");
	$.get("showstudentstomarkalumnis.htm?class="+classAndDivision ,
		function(data) {
		$("table#tbl_student").find("tr:not(:has(th))").remove();
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
				cell3.innerHTML = "<input data-no-uniform=\"true\" type=\"checkbox\" id=" + data[count].guid +" "
					+"onclick=\"javascript:updateAbsentStudent("+ data[count].guid + ")\" checked=\"true\">";
			}
	});
}