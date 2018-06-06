local Class = require("util.Class")
local Tag = require("engine.Tag")
local WmDropmenuItem = Class(Tag)

function WmDropmenuItem:createNew(obj,htmlbuff)
  self.htmlbuff = htmlbuff
end

function WmDropmenuItem:doStartTag(attr)
  self.parent:addActions(attr.action or -1);
  self.htmlbuff:append('<li ontap>')
 
end

function WmDropmenuItem:doEndTag()
 self.htmlbuff:append('</li>');
end

return WmDropmenuItem