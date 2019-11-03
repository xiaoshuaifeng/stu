package cn.tedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class AddCookieServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie c1=new Cookie("msg1", "Helloworld");
		//�����Ҫ������������Ҫת��
		Cookie c2=new Cookie("msg2", URLEncoder.encode("��Һã�","utf-8"));
		/*	����Cookieʱ�� ���������ʱ�� Cookie�������ڴ���
		 * 	������ر����ݾͱ���� �������ʱ�� �ᱻ���浽������ ʱ�䵽������
		 */
		c2.setMaxAge(60*60*24*30);//����һ����
		//ͨ��response��cookie�·����������
		response.addCookie(c1);
		response.addCookie(c2);
		//��ʾ������
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print("������");
		pw.close();
	}
}
