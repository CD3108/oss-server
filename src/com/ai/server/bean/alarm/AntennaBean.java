package com.ai.server.bean.alarm;

import com.ai.server.core.bean.BaseBean;
import com.ai.server.dao.TowerDao;
import com.ai.server.util.HttpClientUtils;
import com.ailk.common.data.IData;
import com.ailk.common.data.IDataset;
import com.ailk.common.data.impl.DataMap;
import com.ailk.common.data.impl.DatasetList;

public class AntennaBean extends BaseBean{

	public IData getAntennaNumAll(IData param) throws Exception{
/*		IData r_1 = new DataMap();
		r_1.put("staffId",param.get("username"));
		r_1.put("password",param.get("passwold"));
		r_1.put("loginName",param.get("username"));
		r_1.put("bindImei",param.get("imei"));
		r_1.put("loginTerminal",param.get("loginTerminal"));*/
		IData result = new DataMap();
		
		String ret = HttpClientUtils.Post("http://133.128.27.58:8092/osi/getAntennaAndHomeRate.json",null);
		IDataset Dataset = new DatasetList(ret);
		
		
		// IDataset towerDataset = testData1(param);
		IDataset dataList = new DatasetList();
		for (int i = 0; i < Dataset.size(); i++) {
			IData one = Dataset.getData(i);
			IData feeData = new DataMap();
			//{"total":4297,"match_number":2835,"city_id":80340,"not_match_number":1462,"city_name":"吕梁市","match_rate":"65.98"}]
			
			feeData.put("total", one.getString("total"));
			feeData.put("match_number", one.getString("match_number"));
			feeData.put("not_match_number", one.getString("not_match_number"));
			feeData.put("match_rate", one.getString("match_rate")+"%");
			feeData.put("city_name", one.getString("city_name"));
			
			if (i % 2 == 0) {
				feeData.put("style", "");
			} else {
				feeData.put("style", " ubc ubt ubb c-gra1 ");
			}
			dataList.add(feeData);

		}
		result.put("dataList", dataList);
		
		IDataset city_nameList = new DatasetList();
		IDataset match_numberList = new DatasetList();
		IDataset not_match_numberList = new DatasetList();
		IDataset match_rateList = new DatasetList();
		for (int i = 0; i < Dataset.size(); i++) {
			IData one = Dataset.getData(i);
			city_nameList.add(one.getString("city_name"));
			match_numberList.add(one.getString("match_number"));
			not_match_numberList.add(one.getString("not_match_number"));
			match_rateList.add(one.getString("match_rate"));

		}
		IData feeData = new DataMap();
		feeData.put("city_nameList", city_nameList);
		feeData.put("match_numberList", match_numberList);
		feeData.put("not_match_numberList", not_match_numberList);
		feeData.put("match_rateList", match_rateList);
		result.put("list", feeData);

		return result;
	}
	
	
	/*public static void main(String args[]){
		String ret = HttpClientUtils.Post("http://133.128.27.58:8092/osi/getAntennaAndHomeRate.json",null);

		IDataset iDataset = new DatasetList(ret);
		
		System.out.println(iDataset.toString());

	}*/
}
