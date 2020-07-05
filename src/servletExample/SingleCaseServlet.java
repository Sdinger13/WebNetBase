package servletExample;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SingleCaseServlet
 */
@WebServlet("/SingleCaseServlet")
public class SingleCaseServlet extends HttpServlet {
//	线程重入 reentrancy
//	指多个线程同时运行同一对象的同一方法
//	要求 doPost() 方法必须可重入
//	简单原则：
//	在 Servlet 的所有方法里，均不使用类的属性

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		通过别的类，实现单例变多例，可以减少因为线程重入可能导致的错误
		new mulipleCase(request, response).handle();
	}

	
}
