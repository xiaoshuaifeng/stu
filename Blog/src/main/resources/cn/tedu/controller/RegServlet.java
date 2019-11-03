package cn.tedu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.dao.UserDao;
import cn.tedu.entity.User;

@SuppressWarnings({ "serial", "unused" })
public class RegServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取传递过来的参数
		//如果是Post请求 并且参数中可能有中文的话 需要执行以下代码
		request.setCharacterEncoding("UTF-8");
		//获取参数
		String userName=request.getParameter("name");
		String password=request.getParameter("pwd");
		String email=request.getParameter("email");
		User user=new User(0,userName,password,email);
		//创建Dao把用户保存
		UserDao dao=new UserDao();
		dao.insert(user);
		//给页面返回注册成功
//		response.setContentType("text/html;charset=utf-8");
//		PrintWriter pw = response.getWriter();
//		pw.print("注册成功");
//		pw.close();
		//重定向到登录页面
		response.sendRedirect(request.getContextPath()+"/ShowLoginServlet");
		
	}

}
