package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import af.AfSimpleREST;
import my.DataSource;
import my.Student;


/**
 * Servlet implementation class StudentListServlet
 */
@WebServlet("/StudentListServlet")
public class StudentListServlet extends AfSimpleREST {

	@Override
	protected Object execute(HttpServletRequest request, HttpServletResponse response, JSONObject jreq) throws Exception
	{
		//直接把List转成JSONArray,要求List内的元素是POJO类型
		List<Student>list = DataSource.i.list;
		JSONArray jresp = new JSONArray(list);
		return jresp;
	}
	
}
