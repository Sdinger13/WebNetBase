package servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import my.DemoDb;
import my.Student;

/**
 * Servlet implementation class AddStudent2
 */
@WebServlet("/AddStudent3")
public class AddStudent3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//读取请求数据，转成字符串
		String reqText = readAsText(request.getInputStream(), "UTF-8");
		//转成JSON
		JSONObject jreq = new JSONObject(reqText);
		
		
		// URL末尾 , 由问号号引号的字符串 （在RESTful中也可能有部分参数在URL里）
		// 例如  mode=admin&token=12930289
		String query = request.getQueryString();
		HashMap<String, String>queryParams = parseQuery(query);
		
		//处理请求数据
		int id = jreq.getInt("id");
		String name = jreq.getString("name");
		String phone = jreq.getString("phone");
		boolean sex = "male".equals(jreq.getString("sex"));
		
		Student s = new Student(id,name,sex,phone);
		DemoDb.i.add(s);
				
		
				
		//返回应答数据
		JSONObject object = new JSONObject();
		object.put("error", 0);// 错误码,0表示成功
		object.put("reason", "OK");// 错误原因描述, 如果没有错误则提示OK
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain");
		Writer writer = response.getWriter();
		writer.write(object.toString(2));
		writer.close();
	}
	// 流操作在后面的《网络通信篇》里讲解，属于高级课程，暂不要求大家掌握
	// 大家只需要知道 这个方法实现的功能就行：功能就是从InputStream里读取数据转成字符串
	public String readAsText(InputStream streamIn,String charest)throws IOException
	{
		ByteArrayOutputStream cache = new ByteArrayOutputStream(1024*16);
		byte[] data = new byte[1024];
		while (true)
		{
			int n = streamIn.read(data); //n:实际读取的字节数
			if(n<0)break;//连接已经断开
			if(n==0)continue;//数据未完//   要防止超时
			
			//缓存起来
			cache.write(data,0,n);
			if(cache.size()>1024*512)//上限，最多读取512K
				break;
			
		}
		return cache.toString(charest);
	}
	
	
	
	//此方法用于从url后面的query字符串中提取参数，放到HashMap中
	// 注：此功能后面会封装在 af.web.AfFormData类里
	public HashMap<String, String> parseQuery(String query)
	{
		// 示例 id=201901&name=shaofa&phone=13810012345
		HashMap<String, String>params = new HashMap<String, String>();
		
		String[] ppp = query.split("&");// 用&分隔
		// 按&分割字符串，示例结果为
		// ppp[0] 为 id=201901 
		// ppp[1] 为 name=shaofa
		// ppp[2] 为phone=13810012345
		
		for(String p :ppp)
		{
			String[] kv = p.split("=");  //key=value
			String key = kv[0];
			String value = "";
			if(kv.length>1)value = kv[1];// 有时候参数里传的是空值
			params.put(key,value);//TODO: value 视情况进行 URLDecoder	
		}
	
		return params;
	}
	
	
}
