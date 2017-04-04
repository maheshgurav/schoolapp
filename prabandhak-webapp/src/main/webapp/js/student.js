function getBonafide(id){
$.get("getbonafide.htm?id="+ id,
	function(data) {
		var wnd = window.open("about:blank","","_blank");
		wnd.document.write(data);
	});
}
