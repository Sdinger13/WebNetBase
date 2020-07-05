package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import af.AfSimpleREST;

/**
 * Servlet implementation class RestfulTest
 */
@WebServlet("/RestfulTest")
public class RestfulTest extends AfSimpleREST {

	@Override
	protected Object execute(HttpServletRequest request, HttpServletResponse response, JSONObject jreq) throws Exception
	{
		//getInt()取值不正确或者类型不正确时会抛出异常，必须用try catch或者throw捕获
		//optInt()取值不正确时则会试图进行转化或者返回默认值，不会抛出异常
		int id = jreq.optInt("id");
		if(id<0)
		{
			throw new Exception("ID必须为正值");
		}
		
		//处理
		
		//应答
		JSONObject jresp = new JSONObject();
		jresp.put("id",id);
		jresp.put("name","shaofa");
		jresp.put("phone","4661166");
		jresp.put("sex",true);
		return jresp;
	}
	

}
