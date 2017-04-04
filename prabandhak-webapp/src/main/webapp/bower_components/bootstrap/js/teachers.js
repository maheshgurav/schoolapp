function getListByFilter(page){
	var filters = "";
	for(var count=1;count<8;count++){
		var filterValue = document.getElementById("filter_" + count).value;
	 	if(filterValue!=null && filterValue!=""){
	 			filters += "?" + document.getElementById("filter_" + count).name + "=" + filterValue;
	 	}
	}
	var url = getCurrentLocation();
	if(page!=null)
		location.replace(url+ filters +"?page="+page);
	else
		location.replace(url+filters);
}

function getCurrentLocation() {
	var url = document.location.href;
	var n = url.indexOf('?');
	url = url.substring(0, n != -1 ? n : url.length);
	return url;
}
