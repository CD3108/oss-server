local Class = require("util.Class")
local Tag = require("engine.Tag")
local WmRefresh = Class(Tag)

function WmRefresh:createNew(obj,htmlbuff)
  self.htmlbuff=htmlbuff
end

function WmRefresh:doStartTag(attr)
	if not (attr.id) then
		monitor:error("WmRefresh组件 id属性不能为空 ")
		return nil
	end
	
	self.attr = attr
	self.htmlbuff:append('<div class="c-refresh-container" id="'..attr.id..'">')
	self.htmlbuff:append('<div id="c-refresh-wrapper" style="height:200px;">')
	self.htmlbuff:append('<div id="c-refresh-scroller">')
	self.htmlbuff:append('<div id="pullDown">')
	self.htmlbuff:append('<span class="pullDownIcon"></span><span class="pullDownLabel">下拉刷新...</span>')
	self.htmlbuff:append('</div>')
	self.htmlbuff:append('<div class="c_list">')
	--this is the content area
	
	
end

function WmRefresh:doEndTag()
	local attr = self.attr
	
	self.htmlbuff:append('</div>')
	
	self.htmlbuff:append('<div id="pullUp">')
	self.htmlbuff:append('<span class="pullUpIcon"></span><span class="pullUpLabel">上拉加载更多...</span>')
	self.htmlbuff:append('</div> </div> </div> </div>')
	
	self.htmlbuff:append('<script type="text/javascript">')
	self.htmlbuff:append('require([ "wmRefresh","wmWebUI" ], function(WmRefresh,WmWebUI) {')
	self.htmlbuff:append('var refresh_'..attr.id..' = new WmRefresh("'..attr.id..'");')
	
	if attr.pullDownAction then
		self.htmlbuff:append('refresh_'..attr.id..'.setPullDownAction('..attr.pullDownAction..');')
	end
	
	if attr.pullUpAction then
		self.htmlbuff:append('refresh_'..attr.id..'.setPullUpAction('..attr.pullUpAction..');')
	end
	
	self.htmlbuff:append('WmWebUI.store("'..attr.id..'",refresh_'..attr.id..');')
	self.htmlbuff:append('})')
	self.htmlbuff:append('</script>')
end

function string.split(str, delimiter)	
	if str==nil or str=='' or delimiter==nil then		
		return nil	
	end	    
	local result = {}    
	for match in (str..delimiter):gmatch("(.-)"..delimiter) do        
		table.insert(result, match)    
	end    
	return result
end

return WmRefresh
