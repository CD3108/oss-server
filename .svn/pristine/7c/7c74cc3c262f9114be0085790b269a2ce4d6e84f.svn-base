package com.ai.server.dao;

import org.apache.commons.lang.StringUtils;

import com.ailk.common.data.IData;
import com.ailk.common.data.IDataset;
import com.ailk.common.data.impl.DataMap;
import com.ailk.common.data.impl.DatasetList;
import com.ailk.mobile.db.dao.impl.BaseDAO;

public class TowerDao extends BaseDAO {
	public TowerDao(String connName) throws Exception {
		super(connName);
	}

	// 获取工单信息
	public IData getMainPageInfos(IData param) throws Exception {
		IData ret = new DataMap();

		return ret;
	}

	public IDataset getTowerFee6(IData param) throws Exception {
		IData ret = new DataMap();
		// 费用数
		String sql = "select c.cs_date,round(nvl(p.fee,0)/10000,2) p_fee,round(nvl(t.total,0)/10000,2) t_fee from ( " + " (select to_char(add_months(trunc(sysdate),-6),'yyyyMM') cs_date from dual)"
				+ " union (select to_char(add_months(trunc(sysdate),-5),'yyyyMM') cs_date from dual) " + " union(select to_char(add_months(trunc(sysdate),-4),'yyyyMM') cs_date from dual) "
				+ " union(select to_char(add_months(trunc(sysdate),-3),'yyyyMM') cs_date from dual) " + " union(select to_char(add_months(trunc(sysdate),-2),'yyyyMM') cs_date from dual) "
				+ " union(select to_char(add_months(trunc(sysdate),-1),'yyyyMM') cs_date from dual)) c " + " left join ( " + " select cs_date, round(sum(PRODUCT_SERVER_TOTAL_FEE2), 2) fee "
				+ "  from TT_TOWER_CO_CALCULATE_RESULT where cs_date = to_char(add_months(trunc(sysdate),-6),'yyyyMM') " + "  group by cs_date " + " union "
				+ " select cs_date, round(sum(PRODUCT_SERVER_TOTAL_FEE2), 2) fee " + "  from TT_TOWER_CO_CALCULATE_RESULT where cs_date = to_char(add_months(trunc(sysdate),-5),'yyyyMM') "
				+ "  group by cs_date " + "  union " + "  select cs_date, round(sum(PRODUCT_SERVER_TOTAL_FEE2), 2) fee "
				+ "  from TT_TOWER_CO_CALCULATE_RESULT where cs_date = to_char(add_months(trunc(sysdate),-4),'yyyyMM') " + "  group by cs_date " + "  union "
				+ "  select cs_date, round(sum(PRODUCT_SERVER_TOTAL_FEE2), 2) fee " + "  from TT_TOWER_CO_CALCULATE_RESULT where cs_date = to_char(add_months(trunc(sysdate),-3),'yyyyMM') "
				+ "  group by cs_date " + "  union " + "  select cs_date, round(sum(PRODUCT_SERVER_TOTAL_FEE2), 2) fee "
				+ "  from TT_TOWER_CO_CALCULATE_RESULT where cs_date = to_char(add_months(trunc(sysdate),-2),'yyyyMM') " + "  group by cs_date " + "  union "
				+ " select cs_date, round(sum(PRODUCT_SERVER_TOTAL_FEE2), 2) fee " + "  from TT_TOWER_CO_CALCULATE_RESULT where cs_date = to_char(add_months(trunc(sysdate),-1),'yyyyMM') "
				+ "  group by cs_date " + "  ) p on c.cs_date = p.cs_date  " + " left join ( " + " select stat_month, round(nvl(sum(decode(nvl(field_fee_two, 0), 0, total, total_two)), 0), 2) total "
				+ "  from tt_account_result where stat_month = to_char(add_months(trunc(sysdate),-6),'yyyyMM') " + "  group by stat_month " + "  union "
				+ " select stat_month, round(nvl(sum(decode(nvl(field_fee_two, 0), 0, total, total_two)), 0), 2) total "
				+ "  from tt_account_result where stat_month = to_char(add_months(trunc(sysdate),-5),'yyyyMM') " + "  group by stat_month " + " union "
				+ " select stat_month, round(nvl(sum(decode(nvl(field_fee_two, 0), 0, total, total_two)), 0), 2) total "
				+ "  from tt_account_result where stat_month = to_char(add_months(trunc(sysdate),-4),'yyyyMM') " + "  group by stat_month " + " union "
				+ " select stat_month, round(nvl(sum(decode(nvl(field_fee_two, 0), 0, total, total_two)), 0), 2) total "
				+ "  from tt_account_result where stat_month = to_char(add_months(trunc(sysdate),-3),'yyyyMM') " + "  group by stat_month " + " union "
				+ " select stat_month, round(nvl(sum(decode(nvl(field_fee_two, 0), 0, total, total_two)), 0), 2) total "
				+ "  from tt_account_result where stat_month = to_char(add_months(trunc(sysdate),-2),'yyyyMM') " + "  group by stat_month " + " union "
				+ "  select stat_month, round(nvl(sum(decode(nvl(field_fee_two, 0), 0, total, total_two)), 0), 2) total "
				+ "  from tt_account_result where stat_month = to_char(add_months(trunc(sysdate),-1),'yyyyMM') " + "  group by stat_month) t on c.cs_date = t.stat_month  " + " order by cs_date ";
		IDataset datas = this.queryList(sql, param);

		return datas;
	}

	public IDataset getTowerNum(IData param) throws Exception {
		String sql = " select c.name, nvl(p.cnt,0) cnt1, nvl(t.cnt,0) cnt2 from area c " + " left join( "
				+ "  select count(distinct a.new_iron_tower_id) cnt,(select NAME from AREA where AREA_ID = a.CITY_ID) as CITY_NAME "
				+ "   from TT_TOWER_CO_CALCULATE_RESULT A where cs_date = to_char(add_months(trunc(sysdate),-1),'yyyyMM') " + "   group by a.city_id) p on c.name = p.city_name " + " left join( "
				+ " SELECT count(distinct c.new_iron_tower_id) cnt,(select NAME from AREA where AREA_ID = c.CITY_ID) as CITY_NAME " + "   FROM TT_ACCOUNT_RESULT C "
				+ " GROUP BY C.city_id) t on c.name = t.city_name " + " where c.area_level = 3 " + " order by c.area_id ";
		IDataset datas = this.queryList(sql, param);
		return datas;
	}
	
	// 获取存量确认资源数
	public IDataset getConfirmStockResourceNum_TEST(String yearMonth, String shareTotal) throws Exception {
		IDataset datas = new DatasetList();
		IData data = new DataMap();
		data.put("PRODUCT_LARGE_CLASSES", "地面塔");
		data.put("PRODUCT_TYPE", "普通地面塔");
		data.put("HIGH", "30≤H<35");
		data.put("CNT", "铁塔（无机房及配套）:105<,铁塔+租赁机房+配套:28<,铁塔+自建机房+配套:376<,铁塔+一体化柜+配套:65<,铁塔+RRU拉远+配套:292<");
		datas.add(data);
		
		data = new DataMap();
		data.put("PRODUCT_LARGE_CLASSES", "地面塔");
		data.put("PRODUCT_TYPE", "普通地面塔");
		data.put("HIGH", "35≤H<40");
		data.put("CNT", "铁塔（无机房及配套）:105<,铁塔+自建机房+配套:376<,铁塔+一体化柜+配套:65<,铁塔+RRU拉远+配套:292<");
		datas.add(data);
		
		data = new DataMap();
		data.put("PRODUCT_LARGE_CLASSES", "地面塔");
		data.put("PRODUCT_TYPE", "普通地面塔");
		data.put("HIGH", "40≤H<45");
		data.put("CNT", "铁塔（无机房及配套）:105<,铁塔+租赁机房+配套:28<,铁塔+自建机房+配套:376<,铁塔+一体化柜+配套:65<,铁塔+RRU拉远+配套:292<");
		datas.add(data);
		
		data = new DataMap();
		data.put("PRODUCT_LARGE_CLASSES", "地面塔");
		data.put("PRODUCT_TYPE", "普通地面塔");
		data.put("HIGH", "45≤H≤50");
		data.put("CNT", "铁塔（无机房及配套）:105<,铁塔+租赁机房+配套:28<,铁塔+自建机房+配套:376<,铁塔+一体化柜+配套:65<,铁塔+RRU拉远+配套:292<");
		datas.add(data);
		
		data = new DataMap();
		data.put("PRODUCT_LARGE_CLASSES", "地面塔");
		data.put("PRODUCT_TYPE", "普通地面塔");
		data.put("HIGH", "H<30");
		data.put("CNT", "铁塔（无机房及配套）:105<,铁塔+租赁机房+配套:28<,铁塔+自建机房+配套:376<,铁塔+一体化柜+配套:65<,铁塔+RRU拉远+配套:292<");
		datas.add(data);
		
		data = new DataMap();
		data.put("PRODUCT_LARGE_CLASSES", "地面塔");
		data.put("PRODUCT_TYPE", "景观塔");
		data.put("HIGH", "20≤H<25");
		data.put("CNT", "铁塔（无机房及配套）:105<,铁塔+租赁机房+配套:28<,铁塔+自建机房+配套:376<,铁塔+一体化柜+配套:65<,铁塔+RRU拉远+配套:292<");
		datas.add(data);
		
		data = new DataMap();
		data.put("PRODUCT_LARGE_CLASSES", "地面塔");
		data.put("PRODUCT_TYPE", "景观塔");
		data.put("HIGH", "25≤H<30");
		data.put("CNT", "铁塔（无机房及配套）:105<,铁塔+租赁机房+配套:28<,铁塔+自建机房+配套:376<,铁塔+一体化柜+配套:65<,铁塔+RRU拉远+配套:292<");
		datas.add(data);
		
		data = new DataMap();
		data.put("PRODUCT_LARGE_CLASSES", "地面塔");
		data.put("PRODUCT_TYPE", "景观塔");
		data.put("HIGH", "30≤H<35");
		data.put("CNT", "铁塔（无机房及配套）:105<,铁塔+租赁机房+配套:28<,铁塔+自建机房+配套:376<,铁塔+一体化柜+配套:65<,铁塔+RRU拉远+配套:292<");
		datas.add(data);
		
		data = new DataMap();
		data.put("PRODUCT_LARGE_CLASSES", "地面塔");
		data.put("PRODUCT_TYPE", "景观塔");
		data.put("HIGH", "35≤H≤40");
		data.put("CNT", "铁塔（无机房及配套）:105<,铁塔+租赁机房+配套:28<,铁塔+自建机房+配套:376<,铁塔+一体化柜+配套:65<,铁塔+RRU拉远+配套:292<");
		datas.add(data);
		
		data = new DataMap();
		data.put("PRODUCT_LARGE_CLASSES", "地面塔");
		data.put("PRODUCT_TYPE", "景观塔");
		data.put("HIGH", "H<20");
		data.put("CNT", "铁塔（无机房及配套）:105<,铁塔+租赁机房+配套:28<,铁塔+自建机房+配套:376<,铁塔+一体化柜+配套:65<,铁塔+RRU拉远+配套:292<");
		datas.add(data);
		
		data = new DataMap();
		data.put("PRODUCT_LARGE_CLASSES", "地面塔");
		data.put("PRODUCT_TYPE", "简易塔");
		data.put("HIGH", "H≤20");
		data.put("CNT", "铁塔（无机房及配套）:105<,铁塔+租赁机房+配套:28<,铁塔+自建机房+配套:376<,铁塔+一体化柜+配套:65<,铁塔+RRU拉远+配套:292<");
		datas.add(data);
		
		data = new DataMap();
		data.put("PRODUCT_LARGE_CLASSES", "楼面塔");
		data.put("PRODUCT_TYPE", "普通楼面塔");
		data.put("HIGH", "-");
		data.put("CNT", "铁塔（无机房及配套）:105<,铁塔+租赁机房+配套:28<,铁塔+自建机房+配套:376<,铁塔+一体化柜+配套:65<,铁塔+RRU拉远+配套:292<");
		datas.add(data);
		data = new DataMap();
		data.put("PRODUCT_LARGE_CLASSES", "楼面塔");
		data.put("PRODUCT_TYPE", "楼面抱杆");
		data.put("HIGH", "-");
		data.put("CNT", "铁塔（无机房及配套）:105<,铁塔+租赁机房+配套:28<,铁塔+自建机房+配套:376<,铁塔+一体化柜+配套:65<,铁塔+RRU拉远+配套:292<");
		datas.add(data);
		
		return datas;
		
	}

	// 获取存量确认资源数
	public IDataset getConfirmStockResourceNum(String yearMonth, String shareTotal) throws Exception {
		IData param = new DataMap();
		param.put("YEAR_MONTH", yearMonth);
		param.put("SHARE_TOTAL", shareTotal);
		StringBuffer sql = new StringBuffer();
		sql.append("select product_large_classes,product_type,high,");
		sql.append(" listagg(configuration||':'||cnt||'<',',') within GROUP (ORDER BY configuration desc) cnt ");
		sql.append(" from (select b.product_large_classes, b.product_type,");
		sql.append(" a.high, '存量' type_id, count(1)  cnt, b.configuration");
		sql.append(" from tt_tower_co_calculate_result a,");
		sql.append(" TT_PRODUCT_CONFIGURATION     b ");
		// 根据月份查询
		sql.append(" where a.cs_date =:YEAR_MONTH");
		// and a.city_id = 80247 --根据各地市查询
		// 几家共享情况
		if (!StringUtils.isBlank(shareTotal)) {
			sql.append(" and (to_number(a.tower_share_total) +  to_number(a.NEW_TOWER_SHARE_TOTAL)) =:SHARE_TOTAL ");
		}
		sql.append(" and  a.configuration_type = b.co_configuration");
		sql.append(" and  a.type_flag in (1,2)");
		sql.append(" GROUP BY b.product_large_classes, b.product_type, a.high, b.configuration)");
		sql.append(" group by product_large_classes,product_type,high order by product_large_classes,");
		sql.append(" product_type DESC, high");
		IDataset datas = this.queryList(sql.toString(), param);
		return datas;
	}

	// 获取2015新增确认资源数
	public IDataset getConfirmNewResourceNum(String yearMonth, String shareTotal) throws Exception {
		IData param = new DataMap();
		param.put("YEAR_MONTH", yearMonth);
		param.put("SHARE_TOTAL", shareTotal);
		StringBuffer sql = new StringBuffer();
		sql.append("select product_large_classes,product_type,high,");
		sql.append(" listagg(configuration||':'||cnt||'<',',') within GROUP (ORDER BY configuration desc) cnt ");
		sql.append(" from (select b.product_large_classes, b.product_type, c.high, '2015年新增' type_id, count(1)  cnt, b.configuration");
		sql.append(" from tt_tower_co_calculate_result a, TT_PRODUCT_CONFIGURATION b, tt_calculate_detail c");
		sql.append(" where  (a.tower_kinds || '+' || a.room_configuration) = b.co_configuration   ");
		sql.append(" and a.high > c.min_high");
		sql.append(" and a.high < c.max_high");
		sql.append(" and b.product_large_classes = c.product_large_classes");
		sql.append(" and b.product_type = c.product_type      ");
		sql.append(" and a.type_flag in (3, 4, 5, 6, 7, 8)");
		// 根据月份查询
		sql.append(" and a.cs_date =:YEAR_MONTH ");
		// and a.city_id = 80247 --根据地市查询

		// 几家共享情况
		if (!StringUtils.isBlank(shareTotal)) {
			sql.append(" and a.current_tower_share_total=:SHARE_TOTAL ");
		}

		sql.append(" GROUP BY b.product_large_classes, b.product_type, c.high, b.configuration)");
		sql.append(" group by product_large_classes,product_type,high order by product_large_classes, product_type DESC, high ");
		IDataset datas = this.queryList(sql.toString(), param);
		return datas;
	}
}
