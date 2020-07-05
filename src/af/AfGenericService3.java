package af;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sun.corba.se.pept.transport.EventHandler;

import my.AreYouGoodApi;
import my.HelloApi;
import my.HowOldApi;
import my.WhatTimeNowApi;


//通用服务器框架1，利用反射技术，xml文件，进行数据的通信，实现了框架代码和业务代码的分离
public class AfGenericService3 extends HttpServlet {
	protected boolean enableErrorLog = false; // 是否打印异常输出
	protected int MAX_REQUEST_SIZE = 1024 * 512; // 允许上传的JSON最大长度
	
	protected HashMap<String,ConfigItem>configs = new HashMap<String, ConfigItem>();

	
	
	@Override
	public void init() throws ServletException
	{
		//从xml配置文件中读取配置
		try
		{
			loadConfig();
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new Error("af-service.xml格式不正确！启动终止启动！");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 无论是 GET/POST, 均统一处理
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //处理请求数据
		try
		{
			handleRequest(request, response);
		} catch (Exception e)
		{
			if(enableErrorLog)e.printStackTrace();
			response.sendError(500,e.getMessage());
			return;
		}
	}

	private void handleRequest(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		//从URL中解析API的名字
		//servletPath:"/.../hello.api"
		String servletPath = request.getServletPath();
		int p1 = servletPath.lastIndexOf("/");
		int p2 = servletPath.lastIndexOf(".");
		String apiName = servletPath.substring(p1+1,p2);
		
//		查找相关的配置
		ConfigItem cfg = configs.get(apiName);
		if(cfg == null)
			throw new Exception("服务" + apiName + "在af-service.xml里没有配置!");
		if(cfg.clazz == null)
		{
			try{
				cfg.clazz = Class.forName(cfg.clazzName);
			}catch(Exception e)	{
				throw new Exception("找不到服务" + apiName + "的类" + cfg.clazzName );
			}
		}
		
		//创建服务类的对象，处理该请求
		AfGenericApi instance = null;
		try
		{
			instance = (AfGenericApi)cfg.clazz.newInstance();
		}catch(InstantiationException e){
			e.printStackTrace();
			throw new Exception(cfg.clazzName + "无法实例化, 请确保构造方法不带参数!");
		}catch(IllegalAccessException e){
			e.printStackTrace();
			throw new Exception(cfg.clazzName + "无法实例化, 请确保构造方法为public!");
		}catch(ClassCastException e){
			e.printStackTrace();
			throw new Exception(cfg.clazzName + "必须是  AfGenericApi 的子类(或子类的子类)!");
		}catch(Exception e)	{
			e.printStackTrace();
			throw new Exception("在创建 " + cfg.clazzName + "实例的时候出错!请检查构造方法是否有异常!");
		}
			
		
		//根据服务名，创建不同的示例进行处理(单例->多例)
//		AfGenericApi instance = null;
//		if("Hello".equals(apiName))
//		    instance = new HelloApi();
//		else if("HowOld".equals(apiName))
//			instance = new HowOldApi();
//		else if("AreYouGood".equals(apiName))
//			instance = new AreYouGoodApi();
//		else if("WhatTimeNow".equals(apiName))
//			instance = new WhatTimeNowApi();
//		else
//			throw new Exception("不支持这个服务: " + apiName);
		
		//读取请求数据和URL里的参数
//		String charset = "UTF-8";
		String strReq = AfServiceUtils.readAsText(request.getInputStream(), cfg.charset, MAX_REQUEST_SIZE);
		
		// 更改：不再提取URL里的参数  ，一般用不到，如果需要则由子类自己用 AfFormData提取
//		String query = request.getQueryString(); 
//		AfFormData queryParams = AfFormData.parse(query, charset);
		
		//读取请求数据，转成字符串，转成JSON
		instance.httpReq = request;
		instance.httpResp = response;
		instance.charset = cfg.charset;
		String strResp = instance.execute(strReq);// 具体的请求处理在execute()里
			
		
		//发送应答给客户端
		response.setCharacterEncoding(cfg.charset);
		response.setContentType("text/plain");
		//response.setHeader("Connection", "close");
		Writer writer = response.getWriter();
		writer.write(strResp);
		writer.close();
	}

	
	
	
/////////////////////////////////////
// af-service.xml 中的配置项
	class ConfigItem{
		public String name;     //服务接口名
		public String clazzName;    //类名
		public Class clazz;       //类的实体
		public String charset = "UTF-8";
		
		public ConfigItem(String name,String clazzName)
		{
			this.name = name;
			this.clazzName = clazzName;
		}
	}
	
	
	
	
	// 从 af-service.xml 中获取配置
		private void loadConfig() throws Exception
		{
			InputStream stream = this.getClass().getResourceAsStream(
					"/my/af-service.xml");
			SAXReader reader = new SAXReader();
			Document doc = reader.read(stream);
			stream.close();
			
			Element root = doc.getRootElement();
			List<Element> xServiceList = root.elements("service");
			for (Element e : xServiceList)
			{
				String name = e.attributeValue("name");
				String clazzName = e.attributeValue("class");			
				configs.put(name, new ConfigItem(name, clazzName));
			}
		}
}
