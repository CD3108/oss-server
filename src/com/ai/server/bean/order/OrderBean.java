package com.ai.server.bean.order;


import com.ai.server.core.bean.BaseBean;
import com.ai.server.util.HttpClientUtils;
import com.ai.server.util.InterfaceTools;
import com.ailk.common.data.IData;
import com.ailk.common.data.IDataset;
import com.ailk.common.data.impl.DataMap;
import com.ailk.common.data.impl.DatasetList;

public class OrderBean extends BaseBean{
	
	public IData getOrderInfos(IData param) throws Exception{

		IData orderInfo = new DataMap(HttpClientUtils.Post(InterfaceTools.getDataToOsi("getOrderInfos.Json"), null));
		
		return orderInfo;
	}
	
	public static void main(String[] args) {
//		String str = HttpClientUtils.Post(InterfaceTools.getDateToOsi("getOrderInfos.Json"), null);
//		String str = HttpClientUtils.Post(InterfaceTools.getDataTest("getTowerProductMatchingRate.json"), null);
//		System.out.println(str);
	}
	public IData getMonitorDayReport(IData param) throws Exception {
		
		IData result = new DataMap();
		IDataset dataList = new DatasetList();
		String str = HttpClientUtils.Post(InterfaceTools.getDataToOsi("gongdan_dayreport/orderDayReport.json"), null);
		dataList = new DatasetList(str);
		if(dataList.getData(0).get("area_name").equals("-1")){
			dataList.remove(0);
			dataList.remove(0);
		}else {
			dataList.remove(0);
		}
		for(int i = 0; i < dataList.size(); i++){
			dataList.getData(i).put("intavgordernum",(int)(Double.parseDouble(String.valueOf(dataList.getData(i).get("avgordernum")))));
		}
		result.put("dataList", dataList);
		return result;
		
	}
	
	
	
}
