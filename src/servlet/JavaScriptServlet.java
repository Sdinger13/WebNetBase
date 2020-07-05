package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JavaScriptServlet
 */
@WebServlet("/sample.js")
public class JavaScriptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8"); /* 设置字符集编码 */
		response.setContentType("text/plain");   /* 设置内容格式 */
		PrintWriter out = response.getWriter();
		
		/* 返回一行 javascript文本 */
		out.write("var author='邵发'; "); 
	}


}
