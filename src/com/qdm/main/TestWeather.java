package com.qdm.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import com.qdm.dao.ForecastDao;
import com.qdm.domain.Forecast;
import com.qdm.service.ForecastService;
import com.qdm.utils.num2contextUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestWeather {
	
	
	public static void main(String[] args) throws Exception {
		//从数据库获取城市数据
		List<String> strList =num2contextUtils.queryAreain();
		//创建URL|资源定位
		HttpURLConnection uRLConnection= null;
		InputStream is = null;
		BufferedReader br =null;
		List<Forecast> fcList = new ArrayList<Forecast>();
		//调用方法数据库
		ForecastService fcs = new ForecastService();
		//先刪除昨日的預測數據，不包含今天
		fcs.deleteForecast();
		
		
		//遍历所有的网页json，转换json格式直接录入数据库
		for (int i=0;i<strList.size();i++) {
				URL url = null;
				String respJson = "";
				String readLine = "";
				url = new URL("http://api.weatherdt.com/common/?area="+strList.get(i)+"&type=forecast&key=ae556a02d1b466e1e24216dee0d4117d"); 
				uRLConnection = (HttpURLConnection)url.openConnection();
				uRLConnection.setDoOutput(true);
				uRLConnection.connect();
				is = uRLConnection.getInputStream();				
				br = new BufferedReader(new InputStreamReader(is));
				while((readLine =br.readLine()) != null){
					respJson +=readLine;		
					}
				//将录入信息写入myJson中
				JSONObject  myJson = JSONObject.fromObject(respJson);
				JSONObject json = myJson.getJSONObject("forecast").getJSONObject("24h");
//				json.toBean(json, LinkedHashMap.class);
				JSONObject day24Json = null;
				JSONObject forejson = null;
				//每20个地区读取未来3天的天气情况。
				for (Object k : json.keySet()) {
					//获取当前时间的下一天日期，即预测日期
					Calendar cd =  new GregorianCalendar();
					SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
					for(int j=1;j<4;j++) {
						Forecast fore = new Forecast();
						cd.add(cd.DATE, 1);
						Date nextday = cd.getTime();
						day24Json = (JSONObject)json.get(k);
						forejson = day24Json.getJSONArray("1001001").getJSONObject(j);
						fore.areaid = k.toString();
						fore.fdate = f.parse(f.format(nextday));
						fore.wsfl006 = num2contextUtils.turn2power(forejson.getString("006")); 
						fore.wsfx008 = num2contextUtils.turn2direction(forejson.getString("008"));
						fore.btfx007 = num2contextUtils.turn2direction(forejson.getString("007"));
						fore.zgqw003 = forejson.getDouble("003"); 
						fore.zdqw004 = forejson.getDouble("004"); 
						fore.bttq001 = num2contextUtils.turn2weather(forejson.getString("001")); 
						fore.btfl005 = num2contextUtils.turn2power(forejson.getString("005")); 
						fore.wstq002 = num2contextUtils.turn2weather(forejson.getString("002")); 
						fcList.add(fore);
					}
				}
		}
		//执行写入方法
		fcs.addWeather(fcList);
		//关闭释放资源
		is.close();								
		br.close();								
		uRLConnection.disconnect();
	}
			
	
}
