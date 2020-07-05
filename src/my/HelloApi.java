package my;

import af.AfGenericApi;

public class HelloApi extends AfGenericApi
{

	@Override
	public String execute(String strReq) throws Exception
	{
		return "你好, 我是邵发!";
	}

	

}
