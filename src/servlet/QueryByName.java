package servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import my.DemoDb;
import my.Student;

/**
 * Servlet implementation class QueryByName
 */
@WebServlet("/QueryByName")
public class QueryByName extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 从 request 里取得参数
		// 在 getParameter() 会进行处理、它取出来的是百分号形式的字符串、它自己会按UTF-8解码，得到正常的字符串
		String filter = request.getParameter("filter");
		
		// 数据查询		
		List<Student> rows = DemoDb.i.list(filter);
				
		// List -> JSONArray
		JSONArray result = new JSONArray(rows);
				
		// 返回应答数据
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain");
		Writer writer = response.getWriter();
		writer.write( result.toString(2));
		writer.close();
	}


}
