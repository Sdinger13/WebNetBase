package servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import af.AfSimpleREST;
import af.AfTextFile;

@WebServlet("/SaveFileServlet")
public class SaveFileServlet extends AfSimpleREST {

	@Override
	protected Object execute(HttpServletRequest request, HttpServletResponse response, JSONObject jreq) throws Exception
	{
		String title = jreq.getString("title");
		String content = jreq.getString("content");
		
		
		//获取项目的实际路径
//		<Context path="/demo" docBase="D:\JavaWeb\demo0401\WebRoot"  />
//		中的D:\JavaWeb\demo0401\WebRoot
		String webRootPath = request.getServletContext().getRealPath("/");
		File webroot = new File(webRootPath);
		
		// 存储到 $(webroot)/data/xxx.txt
		File dataDir = new File(webroot,"data/");
		dataDir.mkdirs();
		
		File f = new File(dataDir,title+".txt");
		AfTextFile.write(f, content, "UTF-8");
		
		return null;
	}


}
