require([ "domReady!", "mobile", "hammer", "jquery", "util" ], function(doc, Mobile, Hammer, jQuery) {
	new iScroll("content");
	
	// rotate
	var rotateHam = new Hammer($(".rotate")[0], {
		domEvents : true
	});
	var liveScale = 1;
	var currentRotation = 0;
	rotateHam.get('rotate').set({
		enable : true
	});
	jQuery(".rotate").on("rotate",function(e) {
		var rotation = currentRotation + Math.round(liveScale* e.originalEvent.gesture.rotation);
		jQuery(this).find("img").css("transform", "rotate( " + rotation + "deg)");
	});
	jQuery(".rotate").on("rotateend", function(e) {
		currentRotation += Math.round(e.originalEvent.gesture.rotation);
	});

	// pinch
    var pinchHam = new Hammer($(".pinch")[0], {
    	domEvents : true
	});
	var width = 1900;
	var height = 400;
	var left = 950;
	var top = 220;
	pinchHam.get('pinch').set({
		enable : true
	});
	jQuery(".pinch").on("pinch", function(e) {
		if (width * e.originalEvent.gesture.scale >= 300) {
			jQuery(this).find("img").css({
				width : width * e.originalEvent.gesture.scale,
				"margin-left" : -left * e.originalEvent.gesture.scale,
				height : height * e.originalEvent.gesture.scale,
				"margin-top" : -top * e.originalEvent.gesture.scale
			});
		}
		console.log(e.originalEvent.gesture.scale);
	});
	jQuery(".pinch").on("pinchend", function(e) {
		width = width * e.originalEvent.gesture.scale;
		height = height * e.originalEvent.gesture.scale;
		left = left * e.originalEvent.gesture.scale;
		top = top * e.originalEvent.gesture.scale;
		console.log(width);
	});
	
	// press
	new Hammer($( ".press")[0], {
      domEvents: true
    });
    $(".press").on("press", function(e) {
       $(".demo-overlay").toggle();
    });
    
    // pan
	var panImg, panMargin;
	new Hammer($(".pan")[0], {
		domEvents : true
	});
	jQuery(".pan").on("panstart", function(e) {
		panImg = $(".pan img");
		panMargin = parseInt(panImg.css("margin-left"), 10);
	});
	jQuery(".pan").on("pan", function(e) {
		console.log("pan");
		var delta = panMargin + e.originalEvent.gesture.deltaX;
		console.log(delta);
		if (delta >= -1750 && delta <= -150) {
			panImg.css({"margin-left" : panMargin + e.originalEvent.gesture.deltaX});
		}
	});
    
    // tap
	new Hammer($(".tap")[0], {
		domEvents : true
	});
	var tapCurrent = 0;
	var tapImgs = $(".demo-box.tap img");
	$(".tap").on("tap", function(e) {
		if (tapImgs[tapCurrent + 1]) {
			tapCurrent++;
		} else {
			tapCurrent = 0;
		}
		tapImgs.removeClass("active");
		tapImgs.eq(tapCurrent).addClass("active");
	});
	
	// swipe
	new Hammer($(".swipe")[0], {
		domEvents : true
	});
	var swipeCurrent = 0;
	var swipeImgs = $(".demo-box.swipe img");
	$(".swipe").on("swipeleft", function(e) {
		if (swipeImgs[swipeCurrent + 1]) {
			swipeImgs.removeClass("active");
			swipeImgs.eq(++swipeCurrent).addClass("active");
		}
	});
	$(".swipe").on("swiperight", function(e) {
		if (swipeImgs[swipeCurrent - 1]) {
			swipeImgs.removeClass("active");
			swipeImgs.eq(--swipeCurrent).addClass("active");
		}
	});
});
