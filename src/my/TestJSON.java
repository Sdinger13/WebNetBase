package my;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;


public class TestJSON
{
	public static void test1() 
	{
		Student pojo = new Student(20180001, "邵发", true,"13800012345");
		JSONObject jobj = new JSONObject(pojo); //  必须给POJO添加好Getter/Setter
		String jstr = jobj.toString(2); // 2为缩进宽度
		System.out.println(jstr);
	}
	
	public static void test2()
	{
		ArrayList<Student> students = new ArrayList();
		students.add(new Student(20180001, "shao", true,"13802323235"));
		students.add(new Student(20180002, "wang", true,"13233434446"));
		students.add(new Student(20180003, "li",   true,"13434340292"));
		
		JSONArray jarray = new JSONArray(students);
		System.out.println(jarray.toString(2));
	}

	
	public static void main(String[] args)
	{
		test2();

	}

}
