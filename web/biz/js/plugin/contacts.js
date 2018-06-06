require(["domReady!","wadeMobile","util"], function(doc,WadeMobile) {
	var iscroll=new iScroll("content");
	
	$("#getContactDetail").tap(function(){
		WadeMobile.getContacts(function(str){
			var data=new Wade.DataMap(str);
			console.log(data);
			$("#name").html(data.get("name"));
			$("#phoneNumber").html(data.get("phoneNumber"));
			$("#email").html(data.get("email"));
			$("#company").html(data.get("company"));
			$("#remark").html(data.get("remark"));
			iscroll.refresh();
		});
	});
});
