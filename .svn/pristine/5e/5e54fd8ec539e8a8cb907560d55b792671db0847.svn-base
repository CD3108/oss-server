require(["mobile","util"], function(Mobile) {
	
	// 设置手势锁
	$("#setScreeLock").tap(function() {
		
		//// 需要保存的数据
		//var data = new Wade.DataMap();
		//data.put("userName","xiaowang");
		//data.put("password","nihao");
		Mobile.setScreenLock("",function(result) {

			// 设置锁成功后的操作....
			alert("手势锁设置成功！去解锁一下吧");
			$("#stateOn").show();
			$("#stateOff").hide();
			$("#hiddenContent").show();
		});
	});
	
	// 解锁
	$("#screenUnlock").tap(function() {
		Mobile.screenUnlock("Index", function(result) {
			
			// 解锁成功后的操作....
			alert("恭喜你解锁成功！另外偷偷告诉你，你的手势锁很酷哦");
		});
	});
	
	// 获取是否设置了手势锁的状态
	Mobile.getScreenLockState(function(result) {
		if(typeof(result) == "string" ){
			result = new Wade.DataMap(result);
		}
		
		// 根据是否设置了手势锁做的不同操作....
		if(result.get("lockState") == "lock") {
			$("#stateOn").show();
			$("#stateOff").hide();
			
			$("#hiddenContent").show();
		} else {
			$("#stateOn").hide();
			$("#stateOff").show();
		}
	});
});