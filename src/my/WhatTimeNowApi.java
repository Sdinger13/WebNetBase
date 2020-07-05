package my;

import java.text.SimpleDateFormat;

import af.AfGenericApi;

public class WhatTimeNowApi extends AfGenericApi
{

	@Override
	public String execute(String strReq) throws Exception
	{
		//获取当前的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timenow = sdf.format(System.currentTimeMillis());
		return timenow;
	}

}
