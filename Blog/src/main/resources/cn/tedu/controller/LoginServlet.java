package cn.tedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.tedu.dao.UserDao;
import cn.tedu.entity.User;


@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取传递过来的用户名和密码
		String username=request.getParameter("name");
		String password=request.getParameter("pwd");
		//得到记住密码值
		String rem=request.getParameter("rem");
		System.out.println(username);
		System.out.println(password);
		//2.创建UserDao对象
		UserDao dao=new UserDao();
		//3.调用dao里面的login方法把用户名和密码
		//两个参数传递进去 登录成功返回一个User对象
		//登录失败返回null
		User user=dao.login(username,password);
		//4.判断如果user对象不等于null则跳转到
		//如果等于null 则给浏览器返回用户名或密码错误
		System.out.println(user+":");
		if(user!=null) {//登录成功!
			//判断是否需要记住密码
			if(rem!=null) {//记住密码
				Cookie c1=new Cookie("username",URLEncoder.encode(username,"UTF-8"));
				Cookie c2=new Cookie("password",URLEncoder.encode(password,"UTF-8"));
				response.addCookie(c1);
				response.addCookie(c2);
			}
			//记住登录状态 通过session
			HttpSession session=request.getSession();
			
			session.setAttribute("user", user);
			
			
			
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
		}else {//登录失败
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.print("用户名或密码错误");
			pw.close();
		}
	}

}
