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
		//����ĩ���������
		te=new TemplateEngine();
		//��������������
		ClassLoaderTemplateResolver r=new ClassLoaderTemplateResolver();
		//�����ַ���
		r.setCharacterEncoding("utf-8");
		//���ú�׺
		r.setSuffix(".html");
		//����
		te.setTemplateResolver(r);
	}
	public static void write (String path,Context context,HttpServletResponse response) throws IOException{
		//��ҳ��·�������ݽ���ģ������
		//�滻��֮��õ�һ���µ�html
		String html=te.process(path, context);
		//�ѵõ���html���
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(html);
		pw.close();
	}
}
