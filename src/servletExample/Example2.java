package servletExample;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Example2
 */
//@WebServlet(name = "Example2",urlPatterns = "/Example2",loadOnStartup = 1)
public class Example2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  public Example2() {
	      System.out.println("Example2 starting");
	    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		1.	配置 Servlet 随 Tomcat 启动
//		  <servlet>
//		    <servlet-name>Example1</servlet-name>
//		    <servlet-class>my.Example1</servlet-class>
//		    <load-on-startup>1</load-on-startup>      此行用于设置随Tomcat启动
//		  </servlet>
//		  <servlet-mapping>
//		    <servlet-name>Example1</servlet-name>
//		    <url-pattern>/Example1</url-pattern>
//		  </servlet-mapping>
//		规则：
//		（1）<load-on-startup> 表示，当该应用被启动时、自动创建Servlet实例。其数字表示启动的顺序。
//		（2）当存在多个自启动 Servlet时，数字越小的越先启动
//		（3）如果没有 <load-on-startup>配置，则默认不会创建实例，直到第一次被访问时才创建实例
//		或
//		@WebServlet(name="Example2",urlPatterns = "/Example2",loadOnStartup=1)
		System.out.println("Example2 started");

		response.getWriter().append("Served at: This is Example2").append(request.getContextPath());
	}



}
