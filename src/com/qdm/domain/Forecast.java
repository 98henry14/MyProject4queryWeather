package com.qdm.domain;

import java.util.Date;

public class Forecast {
	public String fid;
	public String bttq001; 	//白天天气
	public String wstq002;	//晚上天气
	public String zgqw003;	//最高气温
	public String zzqw004;	//最低气温
	public String btfl005;	//白天风力
	public String btfx007;	//白天风向
	public String wsfl006;	//晚上风力
	public String wsfx008;	//晚上风向
	public Date fdate;
	@Override 
	public String toString() {
		return "Forecast [fid=" + fid + ", bttq001=" + bttq001 + ", wstq002=" + wstq002 + ", zgqw003=" + zgqw003
				+ ", zzqw004=" + zzqw004 + ", btfl005=" + btfl005 + ", btfx007=" + btfx007 + ", wsfl006=" + wsfl006
				+ ", wsfx008=" + wsfx008 + ", fdate=" + fdate + "]";
	}


	
	
}
