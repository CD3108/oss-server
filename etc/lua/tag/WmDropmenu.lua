local Class = require("util.Class")
local Tag = require("engine.Tag")
local WmDropmenu = Class(Tag)

--htmlbuff
function WmDropmenu:createNew(obj,htmlbuff)
  self.htmlbuff = htmlbuff
  self.actions = {}
end

--attr所有属性
function WmDropmenu:doStartTag(attr)
  --属性初始化
  attr.closeAction = attr.closeAction
  attr.openAction = attr.oppenAction
  self.attr = attr
  self.htmlbuff:append('<div id="')
  self.htmlbuff:append(attr.id)
  self.htmlbuff:append('">','\n')
  self.htmlbuff:append([[
  	<div class="fn">
		<span class="e_button"><span>]])
  self.htmlbuff:append(attr.label)
  self.htmlbuff:append([[</span><span class="e_ico-unfold"></span></span>
	</div>
	<div class="c_dropmenu">
      	<ul>
    ]])
end

function WmDropmenu:doEndTag()
  self.htmlbuff:append([[
     	</ul>
      </div>
    </div>
  ]])
    
  self.htmlbuff:append([[
<script type="text/javascript">
  require(["wmDropmenu"],function(WmDropmenu) {
      var wmDropmenu=new WmDropmenu("]])
  self.htmlbuff:append(self.attr.id)
  self.htmlbuff:append('");','\n')
  self.htmlbuff:append([[
  	wmDropmenu.create();
  ]])
  
  if self.attr.openAction then
  	self.htmlbuff:append([[
      wmDropmenu.setOpenAction(function(){
    ]])
	self.htmlbuff:append(self.attr.openAction,'\n')
	self.htmlbuff:append('});','\n')
  end
  if self.attr.closeAction then
  	self.htmlbuff:append([[
	  wmDropmenu.setCloseAction(function(){
    ]])
 	self.htmlbuff:append(self.attr.closeAction,'\n')
	self.htmlbuff:append('});','\n')
  end
 
 self.htmlbuff:append('		var items = wmDropmenu.getDropmenuItems();','\n')
 for k,v in pairs(self.actions) do
    if v~=-1 then
      self.htmlbuff:append('items[')
      self.htmlbuff:append(k-1)
      self.htmlbuff:append('].setAction(function(){')
      self.htmlbuff:append(v)
      self.htmlbuff:append('});','\n')
    end
  end
  self.htmlbuff:append([[
	});
	</script>
  ]])
end

function WmDropmenu:addActions(action)
  table.insert(self.actions, action)
end

return WmDropmenu

--[[
require(["wmDropmenu","util"],function(WmDropmenu) {
  var wmDropmenu=new WmDropmenu(dropmenu01);
  wmDropmenu.create();
  wmDropmenu.setLabel("下拉菜单01");
  wmDropmenu.setCloseAction(function(){
    console.log("closeAction");
  });
  wmDropmenu.setOpenAction(function(){
    console.log("openAction");
    console.log(wmDropmenu.getLabel());
  });
  var items=wmDropmenu.getItems();
  for(var i=0;i<items.length;i++){
    console.log("index:"+items[i].getIndex());
    console.log("parent:"+items[i].getParent());
    items[i].setAction(function(obj){
      alert(obj.html());
    });
  }
  console.log("length:"+items.length);
  items[2].remove();
  console.log("length:"+items.length);
  for(var i=0;i<items.length;i++){
    //alert(items[i].html());
  }
  //wmDropmenu.removeAll();
  var item1=wmDropmenu.push("111");
  item1.setAction(function(obj){
    alert("我是新来的1");
  });
  var item2=wmDropmenu.push("22222");
  item2.setAction(function(obj){
    alert("我是新来的2");
  });
  item1.remove();
  //wmDropmenu.getItems()[0].remove();
  $("#testbtn").setAction(function(){
    if(wmDropmenu.Invisible()){
      wmDropmenu.show();
    }else{
      wmDropmenu.hidden();
    }
  });
});

]]

