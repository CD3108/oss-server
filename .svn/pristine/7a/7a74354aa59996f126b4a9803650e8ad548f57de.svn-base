require(["domReady!","common","util","mobile"], function(doc,Common,util,Mobile) {
	var iscroll2 = new iScroll('s_wrapper', {
	    bounce: false,
	    snap: false,
	    momentum: false,
	    hScrollbar: true,
	    vScrollbar: true,
	    checkDOMChanges: true,
	    onScrollEnd: function() {
	        if (this.currPageX == 0) {
	            self.$("#point1").prop("class", "active");
	            self.$("#point2").prop("class", "");
	        } else {
	            self.$("#point2").prop("class", "active");
	            self.$("#point1").prop("class", "");
	        }
	    }
	});
	
});