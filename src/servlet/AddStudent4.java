package servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import af.AfSimpleREST;
import my.DataSource;
import my.DemoDb;
import my.Student;

/**
 * Servlet implementation class AddStudent
 */
@WebServlet("/AddStudent4")
public class AddStudent4 extends AfSimpleREST {
	private static final long serialVersionUID = 1L;
  

	@Override
	protected Object execute(HttpServletRequest request, HttpServletResponse response,
			JSONObject jreq) throws Exception
	{
		//getInt()取值不正确或者类型不正确时会抛出异常，必须用try catch或者throw捕获
		//optInt()取值不正确时则会试图进行转化或者返回默认值，不会抛出异常
		int id = jreq.getInt("id");
		String name = jreq.optString("name");
		String phone = jreq.optString("phone");
		boolean sex = "male".equals(jreq.optString("sex"));
		
		// 参数检查
		if(id <= 0)
			throw new Exception("ID必须为正值!");
		if(name.length() == 0)
			throw new Exception("姓名不得为空!");
		
		// 后台处理部分
		Student s = new Student(id,name,sex,phone);
		DataSource.i.add(s);
			

		// 注意：返回 null是正常情形，不表示错误！
		// 返回null 仅仅表示客户端浏览器不需要返回什么特别的数据 。
		// 只有 throw了Exception ，才表示在处理过程中发生了错误。
		// 可以返回 null, 或者 int, long, double, String, 或者  JSONObject / JSONArray
		return null;
	}

	

}
