package com.ai.server.bean.alarm;

import java.text.DecimalFormat;

import com.ai.server.core.bean.BaseBean;
import com.ai.server.util.HttpClientUtils;
import com.ai.server.util.InterfaceTools;
import com.ailk.common.data.IData;
import com.ailk.common.data.IDataset;
import com.ailk.common.data.impl.DataMap;
import com.ailk.common.data.impl.DatasetList;

public class AlarmBean extends BaseBean{

	//告警准实时报表
	public IData getAlarmTrueTime(IData param){
		IData result = new DataMap();
		IDataset dataList = new DatasetList();
		String str = HttpClientUtils.Post(InterfaceTools.getDataToOsi("gongdan_dayreport/orderDayReport.json"), null);
		dataList = new DatasetList(str);
		if(dataList.getData(0).get("area_name").equals("-1")){
			dataList.remove(0);
			dataList.remove(0);
			dataList.remove(dataList.size() - 1);
		}else {
			dataList.remove(0);
			dataList.remove(dataList.size() - 1);
		}
		IDataset dataList1 = new DatasetList();
		String str1 = HttpClientUtils.Post(InterfaceTools.getDataToOsi("getAlarmRealTime.json"), null);
		dataList1 = new DatasetList(str1);
		DecimalFormat    df   = new DecimalFormat("######0.00");   
		for(int i = 0; i < dataList.size(); i++){
			for(int j = 0; j < dataList1.size(); j++){
				if(String.valueOf(dataList.getData(i).get("area_name")).equals(String.valueOf(dataList1.getData(j).get("city_name")))){
					dataList.getData(i).put("active_alarm_all", dataList1.getData(j).get("active_alarm_all"));
					dataList.getData(i).put("daliy_active_alarm", dataList1.getData(j).get("daliy_active_alarm"));
					dataList.getData(i).put("daliy_alarm_all", dataList1.getData(j).get("daliy_alarm_all"));
					dataList.getData(i).put("daliy_need_send_sheet_alarm", dataList1.getData(j).get("daliy_need_send_sheet_alarm"));
					dataList.getData(i).put("daliy_alarm_all_wuxian", dataList1.getData(j).get("daliy_alarm_all_wuxian"));
					dataList.getData(i).put("daliy_alarm_all_kuandai", dataList1.getData(j).get("daliy_alarm_all_kuandai"));
					dataList.getData(i).put("daliy_alarm_all_chuanshu", dataList1.getData(j).get("daliy_alarm_all_chuanshu"));
					dataList.getData(i).put("daliy_alarm_all_dongliwang", dataList1.getData(j).get("daliy_alarm_all_dongliwang"));
					dataList.getData(i).put("orderalarm", df.format((Double.parseDouble(String.valueOf(dataList.getData(i).get("ordernum"))) / Double.parseDouble(String.valueOf(dataList1.getData(j).get("daliy_alarm_all")))) * 100));
				}
			}
		}
		
		result.put("dataList", dataList);
		return result;
	}
	
	//准实时退服小区
	public IData getTrueTimeOutVillge(IData param){
		IData result = new DataMap();
		IDataset dataList = new DatasetList();
		String str = HttpClientUtils.Post(InterfaceTools.getDataToOsi("Alarm_NoReport/getTfReport.json"), null);
		dataList = new DatasetList(str);
		DecimalFormat    df   = new DecimalFormat("######0.00");
		for(int i = 0; i < dataList.size();i ++){
			dataList.getData(i).put("daybenyueLJSC", (int)(Double.parseDouble(String.valueOf(dataList.getData(i).get("benyueLJSC")).replace(",", "")) / 3600 ));
			dataList.getData(i).put("daytodaySC", (int)(Double.parseDouble(String.valueOf(dataList.getData(i).get("todaySC")).replace(",", "")) / 3600 ));
			dataList.getData(i).put("todayTfLvfate", df.format(Double.parseDouble(String.valueOf(dataList.getData(i).get("todayTfLv")).replace(",", "")) * 100 ));
			dataList.getData(i).put("dayRiJunSC", df.format((Double.parseDouble(String.valueOf(dataList.getData(i).get("RiJunSC")).replace(",", "")) / 3600 )));
			if((Double.parseDouble(String.valueOf(dataList.getData(i).get("nearly30DaysMinSC")).replace(",", "")) / 3600 ) <= 0){
				dataList.getData(i).put("nearly30DaysMinSC_", 0);
			}else {
				dataList.getData(i).put("nearly30DaysMinSC_", df.format((Double.parseDouble(String.valueOf(dataList.getData(i).get("nearly30DaysMinSC")).replace(",", "")) / 3600 )));
			}
			dataList.getData(i).put("nearly30DaysMaxSC_", df.format((Double.parseDouble(String.valueOf(dataList.getData(i).get("nearly30DaysMaxSC")).replace(",", "")) / 3600 )));
		}
		result.put("dataList", dataList);
		return result;
	}

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
