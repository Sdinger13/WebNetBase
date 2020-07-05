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
 * Servlet implementation class QueryById
 */
@WebServlet("/QueryById")
public class QueryById extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从request里取得参数
		String strFrom = request.getParameter("from");
		String strTo = request.getParameter("to");
		
		// 参数处理
		int from = Integer.valueOf( strFrom );
		int to = Integer.valueOf( strTo);
		
		// 数据查询		
		List<Student> rows = DemoDb.i.list(from, to);
				
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
