package my;

import org.json.JSONObject;

import af.AfRestfulApi;

public class ExampleApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		
		return "This is a Test";
	}

}
