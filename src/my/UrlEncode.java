package my;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlEncode
{

	public static void testEncode()throws Exception
	{
		String str = "都";
		String query = URLEncoder.encode(str,"UTF-8");
		System.out.println("编码后："+query);
	}
	public static void testDecode()throws Exception
	{
		String str = "%E5%BC%A0";
		String query = URLDecoder.decode(str,"UTF-8");
		System.out.println("解码后："+query);
	}
	public static void main(String[] args)
	{
		try
		{
			testEncode();
			testDecode();
		} catch (Exception e)
		{
			// TODO: handle exception
		}

	}

}
