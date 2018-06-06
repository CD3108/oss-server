require([ "jcl", "hammer" ], function($, Hammer) {
	function _toString(obj) {
		var str = "";
		for ( var p in obj) {
			if (obj.hasOwnProperty(p)) {
				str += " " + p + ":" + obj[p];
			}
		}
		console.log(str);
		log(str);
	}
	function log(val) {
		var value = $("#msg").html();
		$("#msg").html(value + " <br/>" + val);
	}
	var myElement = document.getElementById("gestureBlock");

	var mc = new Hammer.Manager(myElement);
	mc.add(new Hammer.Tap({
		event : "doubletap",
		taps : 2
	}));
	mc.add(new Hammer.Tap({
		event : "singletap"
	}));

	mc.get("doubletap").recognizeWith("singletap");
	mc.get("singletap").requireFailure("doubletap");

	mc.on("singletap doubletap", function(ev) {
		log(ev.type + " ");
	});

	var mc = new Hammer(myElement);

	mc.on("pan", function(ev) {
		log("pan" + ev.direction);
	});

	mc.on("swipe", function(ev) {
		log("swipe " + ev.direction);

	});
	mc.on("tap", function(ev) {
		log("tap " + ev.direction);

	});
	mc.on("doubletap", function(ev) {
		log("doubletap " + ev.direction);

	});
});