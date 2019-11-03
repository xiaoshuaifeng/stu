package cn.tedu.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public class ThUtils {
	private static TemplateEngine te;
	static {
		//创建末班引擎对象
		te=new TemplateEngine();
		//创建解析器对象
		ClassLoaderTemplateResolver r=new ClassLoaderTemplateResolver();
		//设置字符集
		r.setCharacterEncoding("utf-8");
		//设置后缀
		r.setSuffix(".html");
		//关联
		te.setTemplateResolver(r);
	}
	public static void write (String path,Context context,HttpServletResponse response) throws IOException{
		//把页面路径和数据交给模板引擎
		//替换完之后得到一个新的html
		String html=te.process(path, context);
		//把得到的html输出
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(html);
		pw.close();
	}
}
