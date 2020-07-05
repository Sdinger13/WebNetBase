package my;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import af.AfRestfulApi;

public class StudentListApi extends AfRestfulApi
{

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		//直接把List转成JSONArray,要求List内的元素是POJO类型
		List<Student>list = DataSource.i.list;
		JSONArray jresp = new JSONArray(list);
		return jresp;

	}

}
