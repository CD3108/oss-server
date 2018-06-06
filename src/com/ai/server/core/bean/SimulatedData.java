package com.ai.server.core.bean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.ailk.common.data.IData;
import com.ailk.common.data.IDataset;
import com.ailk.common.data.impl.DataMap;
import com.ailk.common.data.impl.DatasetList;
import com.ailk.mobile.config.MobileConfig;
import com.ailk.mobile.util.MobileConstant;

public class SimulatedData extends BaseBean{
	protected final static Logger log = Logger.getLogger(SimulatedData.class); 
	private final static Properties result = new Properties();
	private final static String path = "server-data-result.txt"; 
	static {
		try {
			InputStream inputStream = SimulatedData.class.getClassLoader().getResourceAsStream(path);
			String encode = MobileConfig.getValue(MobileConstant.MobileConfig.ENCODE);
			BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream, encode));
			result.load(bf);
		} catch (Exception e) {
		}
	}

	public IData invoke(IData param) throws Exception{
		if(param.getString("action", "").equals("")){
			return null;
		}
		return new DataMap(result.getProperty(param.getString("action"),"{}"));
	}
	
	public IDataset invokes(IData param) throws Exception{
		if(param.getString("action", "").equals("")){
			return null;
		}
		return new DatasetList(result.getProperty(param.getString("action"),"[]"));
	}
}
