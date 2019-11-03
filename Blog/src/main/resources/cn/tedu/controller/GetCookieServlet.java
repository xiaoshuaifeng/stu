package cn.tedu.controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class GetCookieServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取cookie
		Cookie[] cookies=request.getCookies();
		//为了避免空指针异常 添加判断
		if(cookies!=null) {
			//遍历每个
			for (Cookie cookie : cookies) {
				String name=cookie.getName();
				String value=cookie.getValue();
				value=URLDecoder.decode(value,"UTF-8");
				System.out.println(name+":"+value);
			}
		}
	}
}
