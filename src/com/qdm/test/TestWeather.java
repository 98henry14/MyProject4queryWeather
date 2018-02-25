package com.qdm.test;

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
		//获取当前时间的下一天日期，即预测日期
		Calendar cd =  new GregorianCalendar();
		cd.add(cd.DATE, 1);
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Date tomorrow = cd.getTime();
		
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
				JSONObject areaarr = myJson.getJSONObject("forecast").getJSONObject("24h");
				JSONObject json = JSONObject.fromObject(areaarr);
				JSONObject day24Json = null;
				JSONObject forejson = null;
				for (Object k : json.keySet()) {
					Forecast fore = new Forecast();
					day24Json = (JSONObject) json.get(k);
					forejson = day24Json.getJSONArray("1001001").getJSONObject(1);
					fore.fid = k.toString();
					fore.fdate = f.parse(f.format(tomorrow));
					fore.wsfl006 = num2contextUtils.turn2power(forejson.getString("006")); 
					fore.wsfx008 = num2contextUtils.turn2direction(forejson.getString("008"));
					fore.btfx007 = num2contextUtils.turn2direction(forejson.getString("007"));
					fore.zgqw003 = forejson.getString("003"); 	
					fore.zzqw004 = forejson.getString("004"); 
					fore.bttq001 = num2contextUtils.turn2weather(forejson.getString("001")); 
					fore.btfl005 = num2contextUtils.turn2power(forejson.getString("005")); 
					fore.wstq002 = num2contextUtils.turn2weather(forejson.getString("002")); 
					fcList.add(fore);
				}
		}
		//调用方法数据库
		ForecastService fcs = new ForecastService();
		fcs.addWeather(fcList);
		//关闭释放资源
		is.close();								
		br.close();								
		uRLConnection.disconnect();
	}
			
	
}
