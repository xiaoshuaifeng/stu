package cn.tedu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class CountServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int count=0;
		//ȡ��֮ǰ����Ĵ���
		Cookie[] cookies=request.getCookies();
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				String name=cookie.getName();
				if(name.equals("count")) {
					//�õ�֮ǰ���ʵ�����
					count=Integer.valueOf(cookie.getValue());
				}
			}
		}
		count++;
		Cookie c=new Cookie("count", count+"");
		response.addCookie(c);
		//��������
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print("��"+count+"������");
		pw.close();
	}
}
