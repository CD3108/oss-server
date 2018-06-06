require(["wmTabbar","common","mobile","util"], function(WmTabbar,Common,Mobile) {
	
	$("#tbody tr").tap(function() {
		console.log($(this).data("id"));
		param = new Wade.DataMap();
		param.put("name", $(this).children().eq(0).text());
		param.put("age",  $(this).children().eq(1).text());
		param.put("gender",  $(this).children().eq(2).text());
		Mobile.getTemplate("TemplateRefresh.PersonDetailTemplate", param.toString(), function(html){
			$("#content2").html(html);
			new iScroll("content2");
		});
	});
	
});