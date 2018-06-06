local Class = require("util.Class")
local Tag = require("engine.Tag")
local WmSwitch = Class(Tag)

--htmlbuff
function WmSwitch:createNew(obj,htmlbuff)
  self.htmlbuff = htmlbuff
end

local function split(s, delim)
    if type(delim) ~= "string" or string.len(delim) <= 0 then
        return
    end

    local start = 1
    local t = {}
    while true do
    local pos = string.find (s, delim, start, true) -- plain find
        if not pos then
          break
        end

        table.insert (t, string.sub (s, start, pos - 1))
        start = pos + string.len (delim)
    end
    table.insert (t, string.sub (s, start))

    return t
end

--attr 所有属性
function WmSwitch:doStartTag(attr)
  --属性初始化
  attr.value = attr.value or "true"
  attr.label = attr.label or "on|off"
  local stateStyle = ""
  if attr.value=="false" then
    stateStyle="transform:translateX(-100%)"
  end
  self.htmlbuff:append([[
    <div class="value">
        <span class="e_switch e_switch-on" id="]])
  self.htmlbuff:append(attr.id)
  self.htmlbuff:append('">','\n')
  
  local labelTable=split(attr.label,"|")
  self.htmlbuff:append('<span class="e_switchOn">')
  self.htmlbuff:append(labelTable[1])
  self.htmlbuff:append('</span>','\n')
  
  self.htmlbuff:append('<span class="e_switchOff">')
  self.htmlbuff:append(labelTable[2])
  self.htmlbuff:append('</span>','\n')
  
  self.htmlbuff:append('<span class="e_switchBar" style="')
  self.htmlbuff:append(stateStyle)
  self.htmlbuff:append('"></span>','\n')
  
  self.htmlbuff:append('<input type="hidden" value="')
  self.htmlbuff:append(attr.value)
  self.htmlbuff:append([["/>
        </span>
     </div>
  ]]);
  
  self.htmlbuff:append([[
  <script>
  require(["wmSwitch"],function(WmSwitch) {
  	var wmSwitch = new WmSwitch("]])
  self.htmlbuff:append(attr.id)
  self.htmlbuff:append('");','\n')
  self.htmlbuff:append('wmSwitch.create();','\n');

  if attr.onAction then
	self.htmlbuff:append('	wmSwitch.setOnAction(function(){','\n')
	self.htmlbuff:append(attr.onAction,'\n')
	self.htmlbuff:append('});','\n');
  end
  if attr.offAction then
   	self.htmlbuff:append('	wmSwitch.setOffAction(function(){','\n')
	self.htmlbuff:append(attr.offAction,'\n')
	self.htmlbuff:append('});','\n');
  end
  if attr.changeAction then
   	self.htmlbuff:append('	wmSwitch.setChangeAction(function(){','\n')
	self.htmlbuff:append(attr.changeAction,'\n')
	self.htmlbuff:append('});','\n');
  end
  if attr.disabled then
   	self.htmlbuff:append('	wmSwitch.setDisabled(')
	self.htmlbuff:append(attr.disabled)
	self.htmlbuff:append(');','\n');
  end
  self.htmlbuff:append([[
  });
  </script>
  ]])
end

function WmSwitch:doEndTag()

end
return WmSwitch