local Class = require("util.Class")
local Tag = require("engine.Tag")
local WmTabbarItem = Class(Tag)

function WmTabbarItem:createNew(obj,htmlbuff)
  self.htmlbuff=htmlbuff
end

function WmTabbarItem:doStartTag(attr)
  self.htmlbuff:append('<div class="nav">')
  self.parent:addChildrenAttr(attr) --子节点属性传递给父节点
end

function WmTabbarItem:doEndTag()
  self.htmlbuff:append('</div>')
end

return WmTabbarItem
