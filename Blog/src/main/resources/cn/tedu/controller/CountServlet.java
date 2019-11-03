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
		//取出之前保存的次数
		Cookie[] cookies=request.getCookies();
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				String name=cookie.getName();
				if(name.equals("count")) {
					//得到之前访问的数量
					count=Integer.valueOf(cookie.getValue());
				}
			}
		}
		count++;
		Cookie c=new Cookie("count", count+"");
		response.addCookie(c);
		//返回数据
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print("第"+count+"次请求！");
		pw.close();
	}
}
