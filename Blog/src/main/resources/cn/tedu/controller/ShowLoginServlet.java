package cn.tedu.controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.context.Context;

import cn.tedu.entity.User;
import cn.tedu.utils.ThUtils;




@SuppressWarnings("serial")
public class ShowLoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*	判断Session中是否有User对象
		 *	有则说明登录成功过直接显示首页
		 */
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if(user!=null) {//登陆过
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;//后面代码不需要执行
		}
		
		String username="";
		String password="";
		//从cookie中取出用户名和密码
		Cookie[] cookies=request.getCookies();
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("username")) {
					username=cookie.getValue();
				}
				if(cookie.getName().equals("password")) {
					password=cookie.getValue();
				}
			}
		}
		//把得到的用户名和密码传递到页面中显示
		Context context=new Context();
		username=URLDecoder.decode(username,"utf-8");
		password=URLDecoder.decode(password,"utf-8");
		context.setVariable("username",username);
		context.setVariable("password",password);
		
		
		ThUtils.write("login",context, response);
	}
}
