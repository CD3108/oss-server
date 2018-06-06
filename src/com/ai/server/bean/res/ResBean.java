package com.ai.server.bean.res;

import com.ai.server.core.bean.BaseBean;
import com.ai.server.util.HttpClientUtils;
import com.ai.server.util.InterfaceTools;
import com.ailk.common.data.IData;
import com.ailk.common.data.IDataset;
import com.ailk.common.data.impl.DataMap;
import com.ailk.common.data.impl.DatasetList;

public class ResBean extends BaseBean {
	
	//资源概况统计
	public IData getResSurvey(IData param){
		IData result = new DataMap();
		IDataset dataList = new DatasetList();
		String str = HttpClientUtils.Post(InterfaceTools.getDataToOsi("getResSurvey.json"), null);
		dataList = new DatasetList(str);
		result.put("dataList", dataList);
		return result;
	}
	//天线与传输匹配率
	public IData getAntennaAndTransmission(IData param){
		IData result = new DataMap();
		IDataset dataList = new DatasetList();
		String str = HttpClientUtils.Post(InterfaceTools.getDataToOsi("getAntennaAndTransmissionRate.json"), null);
		dataList = new DatasetList(str);
		result.put("dataList", dataList);
		return result;
	}
	//网络成环率
	public IData getNetworkRing(IData param){
		IData result = new DataMap();
		IDataset dataList = new DatasetList();
		String str = HttpClientUtils.Post(InterfaceTools.getDataToOsi("getNetworkRingRate.json"), null);
		dataList = new DatasetList(str);
		result.put("dataList", dataList);
		return result;
	}
	
	public static void main(String[] args) {
		
	}
	

}
