require(["wmTabbar","common","mobile","util"], function(WmTabbar,Common,Mobile) {
	
	$("#tbody tr").tap(function() {
		param = new Wade.DataMap();
		param.put("id", $(this).data("id"));
		Mobile.getPage("PageRefresh.PersonDetailTemplateMore", param.toString(), function(html){
			$("#content2").html(html);
			new iScroll("content2");
		});
	});
	
});	