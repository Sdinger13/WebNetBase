package servletExample;

import javax.imageio.IIOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class mulipleCase
{
	HttpServletRequest request;
	HttpServletResponse response;
	public mulipleCase(HttpServletRequest request,HttpServletResponse response)
	{
		this.request = request;
		this.response = response;
	}
	
	public void handle()throws ServletException,IIOException
	{
		
	}
	
}
