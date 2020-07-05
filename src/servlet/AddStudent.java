package servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import my.DemoDb;
import my.Student;

/**
 * Servlet implementation class AddStudent
 */
@WebServlet("/AddStudent")
public class AddStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 因为请求字段里的中文数据，所以要显式设置一下字符编码
		request.setCharacterEncoding("UTF-8");
		
		// 从请求里提取参数字段的值
		int id = Integer.valueOf(request.getParameter("id"));
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		boolean sex = "male".equals(request.getParameter("sex"));
		
		//添加数据库
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

	

}
