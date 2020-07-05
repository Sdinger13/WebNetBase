package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.JSONArray;
import org.json.JSONObject;

import af.AfSimpleREST;
import my.Course;


@WebServlet("/CourseListServlet")
public class CourseListServlet extends AfSimpleREST {

	@Override
	protected Object execute(HttpServletRequest request, HttpServletResponse response, JSONObject jreq) throws Exception
	{
		List<Course> courseList = loadConfig();
		return new JSONArray(courseList);
	}

	private  List<Course>loadConfig()throws Exception
	{
//		获取my包下的course.xml的数据
		InputStream stream = getClass().getResourceAsStream("/my/course.xml");
		SAXReader reader = new SAXReader();
		Document doc = reader.read(stream);
		stream.close();
		
		List<Course>result = new ArrayList<Course>();
		
		Element root = doc.getRootElement();
		List<Element> xServiceList = root.elements("course");
		for(Element e : xServiceList)
		{
			String name = e.attributeValue("name");
			String url = e.attributeValue("url");
			result.add(new Course(name,url));
		}
		return result;
	}

}
