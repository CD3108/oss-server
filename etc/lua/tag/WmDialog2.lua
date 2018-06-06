local Class = require("util.Class")
local Tag = require("engine.Tag")
local WmDialog2 = Class(Tag)

function WmDialog2:createNew(obj,htmlbuff)
  self.htmlbuff=htmlbuff
end

function WmDialog2:doStartTag(attr)
	if not (attr.id and attr.title) then
		monitor:error("WmDialog2组件 id、title属性不能为空 ")
		return nil
	end
	
	self.attr = attr
	self.htmlbuff:append('<div class="s-dialog s-dialog-slip" id="'..attr.id..'">')
	self.htmlbuff:append('<div class="s-dialog-content">')
	self.htmlbuff:append('<h3 class="s-dialog-title">'..attr.title..'</h3>')
	self.htmlbuff:append('<div class="s-dialog-mainview">')
	--this is the content area
	
	
end

function WmDialog2:doEndTag()
	local attr = self.attr
	
	self.htmlbuff:append('</div>')
	
	self.htmlbuff:append('<div class="s-dialog-buttons">')
	
	local n_action = nil
	local p_action = nil
	local p_closeDialg = 'true'
	if (not attr.negativeAction) and (not attr.positiveAction) then
		self.htmlbuff:append('<button class="s-dialog-cancle">确定</button>')
	else
		if attr.negativeAction then
			local negative = string.split(attr.negativeAction,",")
			local n_text = negative[1]
			n_action = negative[2]
			
			if n_text then
				self.htmlbuff:append('<button class="s-dialog-cancle">'..n_text..'</button>')
			end
		end
		
		if attr.positiveAction then
			local positive = string.split(attr.positiveAction,",")
			local p_text = positive[1]
			p_action = positive[2]
			
			if positive[3] then
				p_closeDialg = positive[3]
			end
			
			if p_text then
				self.htmlbuff:append('<button class="s-dialog-ok">'..p_text..'</button>')
			end
		end
		
		
	end
	
	self.htmlbuff:append('</div>')	
	
	self.htmlbuff:append('</div>')
	self.htmlbuff:append('</div>')
	
	
	self.htmlbuff:append('<script type="text/javascript">')
	self.htmlbuff:append('require(["wmDialog2","wmWebUI"],function(WmDialog2,WmWebUI) {')
	self.htmlbuff:append('var dialog_'..attr.id..' = new WmDialog2("'..attr.id..'");')
	
	if n_action then
		self.htmlbuff:append('dialog_'..attr.id..'.setNegativeAction('..n_action..',true);')
	end
	
	if p_action then
		self.htmlbuff:append('dialog_'..attr.id..'.setPositiveAction('..p_action..','..p_closeDialg..');')
	end
	
	if attr.contentview then
		local contentView = string.split(attr.contentview,",")
		local id = contentView[1]
		local type = contentView[2]
		
		self.htmlbuff:append('dialog_'..attr.id..'.setContentView("'..id..'","'..type..'");')
	end
	
	self.htmlbuff:append('WmWebUI.store("'..attr.id..'",dialog_'..attr.id..');')
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

return WmDialog2
