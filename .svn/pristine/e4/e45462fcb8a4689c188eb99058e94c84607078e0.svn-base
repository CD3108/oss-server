package com.ai.server.dao;

import com.ailk.common.data.IData;
import com.ailk.common.data.IDataset;
import com.ailk.common.data.impl.DataMap;
import com.ailk.mobile.db.dao.impl.BaseDAO;

public class OrderDao extends BaseDAO{
	public OrderDao(String connName) throws Exception {
		super(connName);
	}
	//获取工单信息
	public IData getOrderInfos(IData param) throws Exception {
		IData ret = new DataMap();
		//工单数
		String sql = "SELECT count(*) NUM FROM PIM.PIM_ORDER_INST I  WHERE I.FLOW_ID = 502 AND I.CREATE_TIME BETWEEN SYSDATE - 1 AND SYSDATE";
		IDataset datas = this.queryList(sql,param);
		if(datas != null && datas.size() > 0){
			ret.put("ORDER_NUM", datas.first().getString("NUM", "0"));
		}
		int orderNum = Integer.valueOf(datas.first().getString("NUM", "0"));
		
		if(orderNum == 0){
			ret.put("ORDER_ROUTE", "100");
			return ret;
		}
		
		//回单率
		
		sql ="select ROUND ((SELECT count(*) "+
				"  FROM PIM.PIM_ORDER_INST I "+
				" WHERE I.FLOW_ID = 502 "+
				"   AND I.CREATE_TIME BETWEEN SYSDATE - 1 AND SYSDATE "+
				"   AND i.status_flag = 'S')/ "+
				"(SELECT count(*) "+
				"  FROM PIM.PIM_ORDER_INST I "+
				" WHERE I.FLOW_ID = 502 "+
				"   AND I.CREATE_TIME BETWEEN SYSDATE - 1 AND SYSDATE),4)*100 ORDER_ROUTE "+
				" FROM DUAL ";
		
		 datas = this.queryList(sql,param);
		if(datas != null && datas.size() > 0){
			ret.put("ORDER_ROUTE", datas.first().getString("ORDER_ROUTE", "0"));
		}
		return ret;
	}
	
}
