package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import my.Student;

@WebServlet("/StudentQuery")
public class StudentQuery extends HttpServlet
{
	private static final long serialVersionUID = 890999428572519288L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		
		// JSON部分学习的把内容传入JSON
		ArrayList<Student> students = new ArrayList();
		students.add(new Student(20180001, "shao", true,"13802323235"));
		students.add(new Student(20180002, "wang", true,"13233434446"));
		students.add(new Student(20180003, "li",   true,"13434340292"));
		
		JSONArray jarray = new JSONArray(students);
		  
		
		
		// Servlet部分学习的服务器返回内容
		response.setCharacterEncoding("UTF-8"); /* 设置字符集编码 */
		response.setContentType("text/plain"); /* 设置内容格式 */
		PrintWriter out = response.getWriter();
		out.write(jarray.toString(2));
		
		System.out.println( jarray.toString(2) );
	}

	

}
