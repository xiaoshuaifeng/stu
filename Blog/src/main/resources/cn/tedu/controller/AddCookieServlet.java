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
		//如果需要保存中文书需要转码
		Cookie c2=new Cookie("msg2", URLEncoder.encode("大家好！","utf-8"));
		/*	设置Cookie时间 如果不设置时间 Cookie保存在内存中
		 * 	浏览器关闭数据就被清除 如果设置时间 会被保存到磁盘中 时间到后才清除
		 */
		c2.setMaxAge(60*60*24*30);//保存一个月
		//通过response将cookie下发到浏览器中
		response.addCookie(c1);
		response.addCookie(c2);
		//显示添加完成
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print("添加完成");
		pw.close();
	}
}
