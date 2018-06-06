package com.ai.server.bean.tower;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ai.server.core.bean.BaseBean;
import com.ai.server.dao.TowerDao;
import com.ai.server.util.HttpClientUtils;
import com.ai.server.util.InterfaceTools;
import com.ailk.common.data.IData;
import com.ailk.common.data.IDataset;
import com.ailk.common.data.impl.DataMap;
import com.ailk.common.data.impl.DatasetList;

public class TowerBean extends BaseBean {
	
	//铁塔产品匹配率查询
	public IData getTowerProductRate(IData param){
		IData result = new DataMap();
		IDataset dataList = new DatasetList();
		String str = HttpClientUtils.Post(InterfaceTools.getDataToOsi("getTowerProductMatchingRate.json"), null);
		dataList = new DatasetList(str);
		result.put("dataList", dataList);
		return result;
	}
	//铁塔计费匹配率查询
	public IData getTowerFeesRate(IData param){
		IData result = new DataMap();
		IDataset dataList = new DatasetList();
		String str = HttpClientUtils.Post(InterfaceTools.getDataToOsi("getTowerChargingMatchingRate.json"), null);
		dataList = new DatasetList(str);
		for(int i = 0; i < dataList.size(); i++){
			dataList.getData(i).put("co_total_6", String.format("%.2f", Double.parseDouble(String.valueOf(dataList.getData(i).get("co_total")))/10000));
			dataList.getData(i).put("cuc_total_6", String.format("%.2f", Double.parseDouble(String.valueOf(dataList.getData(i).get("cuc_total")))/10000));
			dataList.getData(i).put("match_number_6", String.format("%.2f", Double.parseDouble(String.valueOf(dataList.getData(i).get("match_number")))/10000));
		}
		result.put("dataList", dataList);
		return result;
	}
	//铁塔导入资源成功率查询
	public IData getTowerImportResSuccess(IData param){
		IData result = new DataMap();
		IDataset dataList = new DatasetList();
		String str = HttpClientUtils.Post(InterfaceTools.getDataToOsi("getTowerResourceImportSuccessRatio.json"), null);
		dataList = new DatasetList(str);
		result.put("dataList", dataList);
		return result;
	}
	//基站匹配率查询
	public IData getBaseStationRate(IData param){
		IData result = new DataMap();
		IDataset dataList = new DatasetList();
		String str = HttpClientUtils.Post(InterfaceTools.getDataToOsi("getbaseStationMatchingRate.json"), null);
		dataList = new DatasetList(str);
		result.put("dataList", dataList);
		return result;
	}
	//机房匹配率查询
	public IData getBaseRoomRate(IData param){
		IData result = new DataMap();
		IDataset dataList = new DatasetList();
		String str = HttpClientUtils.Post(InterfaceTools.getDataToOsi("getRoomMatchingRate.json"), null);
		dataList = new DatasetList(str);
		result.put("dataList", dataList);
		return result;
	}
	//铁塔数量统计
	public IDataset getTowerNumber(IData param){
		
		return null;
	}
	//机房数量统计
	public IDataset getRoomNum(IData param){
		
		return null;
	}
	

	public IData getMainPageInfos(IData param) throws Exception {
		IData result = new DataMap();

		/*
		 * 近6个铁塔计费与我方计费
		 */
		TowerDao dao = new TowerDao("rmdb");
		IDataset feeTower = dao.getTowerFee6(param);
		// IDataset feeTower = testData(param);
		IData fee = new DataMap();
		IDataset feexAxis_data = new DatasetList();
		IDataset feeseries_data_tt = new DatasetList();
		IDataset feeseries_data_lt = new DatasetList();
		for (int i = 0; i < feeTower.size(); i++) {
			IData one = feeTower.getData(i);
			feexAxis_data.add(one.getString("CS_DATE"));
			feeseries_data_tt.add(one.getString("P_FEE"));
			feeseries_data_lt.add(one.getString("T_FEE"));

		}
		fee.put("xAxis_data", feexAxis_data);
		fee.put("series_data_tt", feeseries_data_tt);
		fee.put("series_data_lt", feeseries_data_lt);
		result.put("fee", fee);

		// 11个地市铁塔公司和我方公司统计的铁塔总数

		IDataset towerDataset = dao.getTowerNum(param);
		// IDataset towerDataset = testData1(param);
		IData tower = new DataMap();
		IDataset towerxAxis_data = new DatasetList();
		IDataset towerseries_data_tt = new DatasetList();
		IDataset towerseries_data_lt = new DatasetList();
		for (int i = 0; i < towerDataset.size(); i++) {
			IData one = towerDataset.getData(i);
			towerxAxis_data.add(one.getString("NAME"));
			towerseries_data_tt.add(one.getString("CNT1"));
			towerseries_data_lt.add(one.getString("CNT2"));

		}
		tower.put("xAxis_data", towerxAxis_data);
		tower.put("series_data_tt", towerseries_data_tt);
		tower.put("series_data_lt", towerseries_data_lt);
		result.put("tower", tower);

		return result;
	}

	public IData getTowerHireFeeAll(IData param) throws Exception {
		IData result = new DataMap();

		/*
		 * 近6个铁塔计费与我方计费
		 */
		TowerDao dao = new TowerDao("rmdb");
		IDataset feeTower = dao.getTowerFee6(param);
		// IDataset feeTower = testData(param);
		IDataset dataList = new DatasetList();
		for (int i = 0; i < feeTower.size(); i++) {
			IData one = feeTower.getData(i);
			IData feeData = new DataMap();
			feeData.put("year_month", one.getString("CS_DATE"));
			feeData.put("tower_fee", one.getString("P_FEE"));
			feeData.put("lt_fee", one.getString("T_FEE"));
			float ce = Float.valueOf(one.getString("P_FEE")) - Float.valueOf(one.getString("T_FEE"));
			feeData.put("ce", String.valueOf(ce));
			if (i % 2 == 0) {
				feeData.put("style", "");
			} else {
				feeData.put("style", " ubc ubt ubb c-gra1 ");
			}
			dataList.add(feeData);

		}
		result.put("dataList", dataList);

		return result;
	}

	public IData getTowerNumAll(IData param) throws Exception {
		IData result = new DataMap();

		TowerDao dao = new TowerDao("rmdb");
		IDataset towerDataset = dao.getTowerNum(param);
		// IDataset towerDataset = testData1(param);
		IDataset dataList = new DatasetList();
		for (int i = 0; i < towerDataset.size(); i++) {
			IData one = towerDataset.getData(i);
			IData feeData = new DataMap();
			feeData.put("year_month", one.getString("NAME"));
			feeData.put("tower_num", one.getString("CNT1"));
			feeData.put("lt_num", one.getString("CNT2"));
			Integer ce = Integer.valueOf(one.getString("CNT1")) - Integer.valueOf(one.getString("CNT2"));
			feeData.put("ce", String.valueOf(ce));
			if (i % 2 == 0) {
				feeData.put("style", "");
			} else {
				feeData.put("style", " ubc ubt ubb c-gra1 ");
			}
			dataList.add(feeData);

		}
		result.put("dataList", dataList);
		return result;
	}

	public IData getConfirmResourceNum(IData param) throws Exception {

		String yearMonth = param.getString("yearMonth", "201605");

		IData result = new DataMap();
		IData resourceData = new DataMap();

		IDataset stockDataList0 = getConfirmResourceList("stock", yearMonth, null);
		for(int i=0;i<stockDataList0.size();i++){
			result.put("stock0"+i, stockDataList0.getDataset(i));
		}
		IDataset newSataList0 = getConfirmResourceList("new", yearMonth, null);
		for(int i=0;i<stockDataList0.size();i++){
			result.put("new0"+i, newSataList0.getDataset(i));
		}

		IDataset stockDataList1 = getConfirmResourceList("stock", yearMonth, "1");
		for(int i=0;i<stockDataList0.size();i++){
			result.put("stock1"+i, stockDataList1.getDataset(i));
		}
		IDataset newSataList1 = getConfirmResourceList("new", yearMonth, "1");
		for(int i=0;i<stockDataList0.size();i++){
			result.put("new1"+i, newSataList1.getDataset(i));
		}

		IDataset stockDataList2 = getConfirmResourceList("stock", yearMonth, "2");
		for(int i=0;i<stockDataList0.size();i++){
			result.put("stock2"+i, stockDataList2.getDataset(i));
		}
		IDataset newSataList2 = getConfirmResourceList("new", yearMonth, "2");
		for(int i=0;i<stockDataList0.size();i++){
			result.put("new2"+i, newSataList2.getDataset(i));
		}

		IDataset stockDataList3 = getConfirmResourceList("stock", yearMonth, "3");
		for(int i=0;i<stockDataList0.size();i++){
			result.put("stock3"+i, stockDataList3.getDataset(i));
		}
		IDataset newSataList3 = getConfirmResourceList("new", yearMonth, "3");
		for(int i=0;i<stockDataList0.size();i++){
			result.put("new3"+i, newSataList3.getDataset(i));
		}

		result.put("dataList", resourceData);
		return result;
	}

	public IDataset getConfirmResourceList(String type, String yearMonth, String shareTotal) throws Exception {

		TowerDao dao = new TowerDao("rmdb");
		IDataset sesourceDataset = null;
		if ("stock".equals(type)) {
			 sesourceDataset = dao.getConfirmStockResourceNum(yearMonth, shareTotal);
			//sesourceDataset = dao.getConfirmStockResourceNum_TEST(yearMonth, shareTotal);
		} else {
			 sesourceDataset = dao.getConfirmNewResourceNum(yearMonth,shareTotal);
			//sesourceDataset = dao.getConfirmStockResourceNum_TEST(yearMonth, shareTotal);
		}
		IDataset dataList = new DatasetList();
		IDataset rowList1 = new DatasetList();
		IDataset rowList2 = new DatasetList();
		IDataset rowList3 = new DatasetList();
		IDataset rowList4 = new DatasetList();
		IDataset rowList5 = new DatasetList();
		IDataset rowList6 = new DatasetList();
		IDataset rowList7 = new DatasetList();
		IDataset rowList8 = new DatasetList();
		IDataset rowList9 = new DatasetList();
		IDataset rowList10 = new DatasetList();
		IDataset rowList11 = new DatasetList();
		IDataset rowList12 = new DatasetList();
		IDataset rowList13 = new DatasetList();
		for (int i = 0; i < sesourceDataset.size(); i++) {

			IData one = sesourceDataset.getData(i);

			if ("普通地面塔".equals(one.getString("PRODUCT_TYPE"))) {
				if ("H<30".equals(one.get("HIGH"))) {
					rowList1 = getResourceNum(rowList1, one.getString("CNT"));

				} else if ("30≤H<35".equals(one.get("HIGH"))) {
					rowList2 = getResourceNum(rowList2, one.getString("CNT"));

				} else if ("35≤H<40".equals(one.get("HIGH"))) {
					rowList3 = getResourceNum(rowList3, one.getString("CNT"));

				} else if ("40≤H<45".equals(one.get("HIGH"))) {
					rowList4 = getResourceNum(rowList4, one.getString("CNT"));

				} else if ("45≤H≤50".equals(one.get("HIGH"))) {
					rowList5 = getResourceNum(rowList5, one.getString("CNT"));

				}
			} else if ("景观塔".equals(one.getString("PRODUCT_TYPE"))) {

				if ("H<20".equals(one.get("HIGH"))) {
					rowList6 = getResourceNum(rowList6, one.getString("CNT"));

				} else if ("20≤H<25".equals(one.get("HIGH"))) {
					rowList7 = getResourceNum(rowList7, one.getString("CNT"));

				} else if ("25≤H<30".equals(one.get("HIGH"))) {
					rowList8 = getResourceNum(rowList8, one.getString("CNT"));

				} else if ("30≤H<35".equals(one.get("HIGH"))) {
					rowList9 = getResourceNum(rowList9, one.getString("CNT"));

				} else if ("35≤H≤40".equals(one.get("HIGH"))) {
					rowList10 = getResourceNum(rowList10, one.getString("CNT"));

				}
			} else if ("简易塔".equals(one.getString("PRODUCT_TYPE"))) {

				if ("H≤20".equals(one.get("HIGH"))) {
					rowList11 = getResourceNum(rowList11, one.getString("CNT"));

				}
			} else if ("普通楼面塔".equals(one.getString("PRODUCT_TYPE"))) {
				rowList12 = getResourceNum(rowList12, one.getString("CNT"));

			} else if ("楼面抱杆".equals(one.getString("PRODUCT_TYPE"))) {
				rowList13 = getResourceNum(rowList13, one.getString("CNT"));

			}
		}
		dataList.add(rowList1);
		dataList.add(rowList2);
		dataList.add(rowList3);
		dataList.add(rowList4);
		dataList.add(rowList5);
		dataList.add(rowList6);
		dataList.add(rowList7);
		dataList.add(rowList8);
		dataList.add(rowList9);
		dataList.add(rowList10);
		dataList.add(rowList11);
		dataList.add(rowList12);
		dataList.add(rowList13);

		return dataList;
	}

	public static IDataset getResourceNum(IDataset rowList, String resmsg) {
		// 铁塔（无机房及配套）:105<,铁塔+租赁机房+配套:28<,铁塔+自建机房+配套:376<,铁塔+一体化柜+配套:65<,铁塔+RRU拉远+配套:292<

		String cnt1 = "0";
		String cnt2 = "0";
		String cnt3 = "0";
		String cnt4 = "0";
		String cnt5 = "0";

		resmsg = resmsg.replace("+", "");

		String regex1 = "铁塔（无机房及配套）:(.*)<,铁塔+租赁机房+配套";
		Pattern pattern1 = Pattern.compile(regex1);
		Matcher matcher1 = pattern1.matcher(resmsg);
		while (matcher1.find()) {
			cnt1 = matcher1.group(1);
		}

		String regex2 = "铁塔+自建机房+配套:(.*)<,铁塔+一体化柜+配套";
		Pattern pattern2 = Pattern.compile(regex2);
		Matcher matcher2 = pattern2.matcher(resmsg);
		while (matcher2.find()) {
			cnt2 = matcher2.group(1);
		}

		String regex3 = "铁塔+租赁机房+配套:(.*)<,铁塔+自建机房+配套";
		Pattern pattern3 = Pattern.compile(regex3);
		Matcher matcher3 = pattern3.matcher(resmsg);
		while (matcher3.find()) {
			cnt3 = matcher3.group(1);
		}

		String regex4 = "铁塔+一体化柜+配套:(.*)<,铁塔+RRU拉远+配套";
		Pattern pattern4 = Pattern.compile(regex4);
		Matcher matcher4 = pattern4.matcher(resmsg);
		while (matcher4.find()) {
			cnt4 = matcher4.group(1);
		}

		String regex5 = "铁塔+RRU拉远+配套:(.*)<";
		Pattern pattern5 = Pattern.compile(regex5);
		Matcher matcher5 = pattern5.matcher(resmsg);
		while (matcher5.find()) {
			cnt5 = matcher5.group(1);
		}

		rowList.add(cnt1);
		rowList.add(cnt2);
		rowList.add(cnt3);
		rowList.add(cnt4);
		rowList.add(cnt5);

		return rowList;
	}

	private IDataset testData1(IData param) {
		IDataset dataSet = new DatasetList();
		IData data = new DataMap();
		data.put("NAME", "太原市");
		data.put("CNT1", "2820");
		data.put("CNT2", "200");
		dataSet.add(data);
		data = new DataMap();
		data.put("NAME", "大同市");
		data.put("CNT1", "1500");
		data.put("CNT2", "11");
		dataSet.add(data);
		data = new DataMap();
		data.put("NAME", "阳泉市");
		data.put("CNT1", "1017");
		data.put("CNT2", "877");
		dataSet.add(data);
		data = new DataMap();
		data.put("NAME", "长治市");
		data.put("CNT1", "1361");
		data.put("CNT2", "2");
		dataSet.add(data);
		data = new DataMap();
		data.put("NAME", "晋城市");
		data.put("CNT1", "1557");
		data.put("CNT2", "120");
		dataSet.add(data);
		data = new DataMap();
		data.put("NAME", "朔州市");
		data.put("CNT1", "1218");
		data.put("CNT2", "220");
		dataSet.add(data);
		data = new DataMap();
		data.put("NAME", "晋中市");
		data.put("CNT1", "2097");
		data.put("CNT2", "330");
		dataSet.add(data);
		data = new DataMap();
		data.put("NAME", "运城市");
		data.put("CNT1", "2659");
		data.put("CNT2", "550");
		dataSet.add(data);
		data = new DataMap();
		data.put("NAME", "忻州市");
		data.put("CNT1", "2364");
		data.put("CNT2", "550");
		dataSet.add(data);
		data = new DataMap();
		data.put("NAME", "临汾市");
		data.put("CNT1", "2487");
		data.put("CNT2", "880");
		dataSet.add(data);
		data = new DataMap();
		data.put("NAME", "吕梁市");
		data.put("CNT1", "2390");
		data.put("CNT2", "110");
		dataSet.add(data);

		return dataSet;
	}

	private IDataset testData(IData param) {
		IDataset dataSet = new DatasetList();
		IData data = new DataMap();
		data.put("CS_DATE", "201601");
		data.put("P_FEE", "0");
		data.put("T_FEE", "0");
		dataSet.add(data);
		data = new DataMap();
		data.put("CS_DATE", "201602");
		data.put("P_FEE", "0");
		data.put("T_FEE", "6077");
		dataSet.add(data);
		data = new DataMap();
		data.put("CS_DATE", "201603");
		data.put("P_FEE", "0");
		data.put("T_FEE", "5895");
		dataSet.add(data);
		data = new DataMap();
		data.put("CS_DATE", "201604");
		data.put("P_FEE", "0");
		data.put("T_FEE", "884");
		dataSet.add(data);
		data = new DataMap();
		data.put("CS_DATE", "201605");
		data.put("P_FEE", "0");
		data.put("T_FEE", "4046");
		dataSet.add(data);
		data = new DataMap();
		data.put("CS_DATE", "201606");
		data.put("P_FEE", "596284");
		data.put("T_FEE", "25092");
		dataSet.add(data);
		return dataSet;
	}

	public static void main(String[] args) {
		String resmsg = "铁塔（无机房及配套）:105<,铁塔+租赁机房+配套:28<,铁塔+自建机房+配套:376<,铁塔+一体化柜+配套:65<,铁塔+RRU拉远+配套:292<";

		resmsg = resmsg.replace("+", "");
		String cnt1 = "0";
		String cnt2 = "0";
		String cnt3 = "0";
		String cnt4 = "0";
		String cnt5 = "0";

		String regex1 = "铁塔（无机房及配套）:(.*)<,铁塔+租赁机房+配套";
		Pattern pattern1 = Pattern.compile(regex1);
		Matcher matcher1 = pattern1.matcher(resmsg);
		while (matcher1.find()) {
			cnt1 = matcher1.group(1);
		}

		String regex2 = "铁塔+自建机房+配套:(.*)<,铁塔+一体化柜+配套";
		Pattern pattern2 = Pattern.compile(regex2);
		Matcher matcher2 = pattern2.matcher(resmsg);
		while (matcher2.find()) {
			cnt2 = matcher2.group(1);
		}

		String regex3 = "铁塔+租赁机房+配套:(.*)<,铁塔+自建机房+配套";
		Pattern pattern3 = Pattern.compile(regex3);
		Matcher matcher3 = pattern3.matcher(resmsg);
		while (matcher3.find()) {
			cnt3 = matcher3.group(1);
		}

		String regex4 = "铁塔+一体化柜+配套:(.*)<,铁塔+RRU拉远+配套";
		Pattern pattern4 = Pattern.compile(regex4);
		Matcher matcher4 = pattern4.matcher(resmsg);
		while (matcher4.find()) {
			cnt4 = matcher4.group(1);
		}

		String regex5 = "铁塔+RRU拉远+配套:(.*)<";
		Pattern pattern5 = Pattern.compile(regex5);
		Matcher matcher5 = pattern5.matcher(resmsg);
		while (matcher5.find()) {
			cnt5 = matcher5.group(1);
		}

		System.out.println(cnt1);
		System.out.println(cnt2);
		System.out.println(cnt3);
		System.out.println(cnt4);
		System.out.println(cnt5);
	}
	
	public IData getResourceAdapter(IData param) throws Exception {

		String yearMonth = param.getString("yearMonth", "201606");

		IData result = new DataMap();
		IData resourceData = new DataMap();

		TowerDao dao = new TowerDao("rmdb");
		IDataset sesourceDataset = null;
		
		//sesourceDataset = dao.getResourceAdapter(param);
		
		String url = "http://133.128.27.58:8092/osi/getHomeRate.json";
		String ret = HttpClientUtils.Post(url,null);
		
		sesourceDataset = new DatasetList(ret);

		IDataset dataList = new DatasetList();
		for (int i = 0; i < sesourceDataset.size(); i++) {
			IData one = sesourceDataset.getData(i);
			IData feeData = new DataMap();
			feeData.put("city_name", one.getString("city_name"));
			feeData.put("total", one.getString("total"));
			feeData.put("match_number", one.getString("match_number"));
			feeData.put("not_match_number", one.getString("not_match_number"));
			feeData.put("match_rate", one.getString("match_rate"));
			//Integer ce = Integer.valueOf(one.getString("not_match_number")) - Integer.valueOf(one.getString("CNT2"));
			//feeData.put("ce", String.valueOf(ce));
			if (i % 2 == 0) {
				feeData.put("style", "");
			} else {
				feeData.put("style", " ubc ubt ubb c-gra1 ");
			}
			dataList.add(feeData);
			System.out.println(one);
		}
		result.put("dataList", dataList);
		
		return result;
	}


}
