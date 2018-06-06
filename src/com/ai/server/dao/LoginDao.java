package com.ai.server.dao;

import com.ailk.common.data.IData;
import com.ailk.common.data.IDataset;
import com.ailk.common.data.impl.DataMap;
import com.ailk.mobile.db.dao.impl.BaseDAO;

public class LoginDao extends BaseDAO{
	public LoginDao(String connName) throws Exception {
		super(connName);
	}
	//登录
	public IData getUserInfo(IData param) throws Exception {
		String passwd = param.getString("USER_PASSWORD");
		String sql = "select * from tf_f_user t where t.code=:STAFF_ID AND t.password=:USER_PASSWORD";
		IDataset datas = this.queryList(sql,param);
		if(datas != null && datas.size() > 0){
			return datas.first();
		}
		return null;
	}
	//获取用户菜单信息
	public IDataset getUserMenus(String userId) throws Exception {
		IData param  = new DataMap();
		param.put("USER_ID", userId);
		String sql ="select distinct b.ID,b.PARENTID,b.LEVELNO,b.ISLEAF,b.PAGEURL,b.ENABLED,b.SORTCODE,b.menu_icon,b.NAME"+
				" from  TF_F_USERROLE a,TD_M_MENU b,TF_F_ROLEMENU c where a.USERID=:USER_ID"+
				" and b.menutype='9' and a.ROLEID = c.ROLEID and b.code=c.MENUID order by b.sortcode";
		IDataset datas = this.queryList(sql,param);
		
		return datas;
	}
}
