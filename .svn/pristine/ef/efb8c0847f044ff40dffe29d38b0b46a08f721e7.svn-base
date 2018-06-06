require(["wmTabbar","common","mobile","oInput","util"], function(WmTabbar,Common,Mobile,oInput) {

	WadeMobile.getOfflineCache(function(value){
		var usernamesta,passwordsta,username,password;
		value = new Wade.DataMap(value);
		usernamesta = value.get('usernamesta');
		passwordsta = value.get('passwordsta');
		username = value.get('username');
		password = value.get('password');
		if(usernamesta == '2'){
			$('#username').val(username);
			$("#memoryid span img").attr("src","biz/img/login/label2.png");
		}
		if(passwordsta == '2'){
			$("#memorypwd span img").attr("src","biz/img/login/label2.png");
			$('#password').val(password);
		}
	},['usernamesta','passwordsta','username','password']);
	
	
	// 登录
	$("#login").tap(function(){
		
		
		WadeMobile.getOfflineCache(function(value){
			var usernamesta,passwordsta,username,password;
			value = new Wade.DataMap(value);
			usernamesta = value.get('usernamesta');
			passwordsta = value.get('passwordsta');
			username = value.get('username');
			password = value.get('password');
			if(usernamesta == '2'){
				WadeMobile.setOfflineCache('username',$("#username").val());
			}
			if(passwordsta == '2'){
				WadeMobile.setOfflineCache('password',$("#password").val());
			}
		},['usernamesta','passwordsta','username','password']);
		
		var loginData = new Wade.DataMap();
		loginData.put("username", $("#username").val());
		loginData.put("passwold", $("#password").val());
		//获取手机imei
		WadeMobile.getSysInfo(function(info){
			loginData.put("imei", info);
		},"IMEI");
		//获取是安卓0还是IOS1
		WadeMobile.getSysInfo(function(info){
			if(info == "Android"){
				loginData.put("loginTerminal", "1");
			}else{
				loginData.put("loginTerminal", "0");
			}
			
		},"PLATFORM");
		
		// 输入的参数校验
		var messages = oInput.check();
		if(messages && messages.getMessage()) {
			alert(messages.getMessage());
			return;
		}
		Mobile.loadingStart("登陆中,请稍等……","等待");
		// 将前台输入的参数传至后台校验
		Common.callSvc("OssLogin.login",loginData,function(data){
			console.log("结果[用户登陆]：" + data);
			if(typeof(data) == "string" ){
				data = new Wade.DataMap(data);
			}
			if(data.get("isSuccess") && data.get("isSuccess") == "1"){
				// 保存用户账号
				WadeMobile.loadingStop();
				Common.put("userinfo", data.toString());
				Mobile.openPage("Main");
			}else{
				alert("用户名或密码错误");
				WadeMobile.loadingStop();
			}
			
			
		});
	});
	//记住密码1不记住2记住
	$("#memorypwd").tap(function(){
		if($("#memorypwd span img").attr("src") == "biz/img/login/label2.png"){
			$("#memorypwd span img").attr("src","biz/img/login/label1.png");
			WadeMobile.setOfflineCache('passwordsta','1');
			return;
		}else{
			WadeMobile.setOfflineCache('usernamesta','2');
			WadeMobile.setOfflineCache('passwordsta','2');
			$("#memorypwd span img").attr("src","biz/img/login/label2.png");
			$("#memoryid span img").attr("src","biz/img/login/label2.png");
		}
		
	})
	//记住用户名1不记住2记住
	$("#memoryid").tap(function(){
		if($("#memoryid span img").attr("src") == "biz/img/login/label2.png"){
			$("#memoryid span img").attr("src","biz/img/login/label1.png");
			WadeMobile.setOfflineCache('usernamesta','1');
			$("#memorypwd span img").attr("src","biz/img/login/label1.png");
			WadeMobile.setOfflineCache('passwordsta','1');
			return;
		}else{
			WadeMobile.setOfflineCache('usernamesta','2');
			$("#memoryid span img").attr("src","biz/img/login/label2.png");
		}
	})
	
	
	
});