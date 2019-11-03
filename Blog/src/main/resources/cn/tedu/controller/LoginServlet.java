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
		//1.��ȡ���ݹ������û���������
		String username=request.getParameter("name");
		String password=request.getParameter("pwd");
		//�õ���ס����ֵ
		String rem=request.getParameter("rem");
		System.out.println(username);
		System.out.println(password);
		//2.����UserDao����
		UserDao dao=new UserDao();
		//3.����dao�����login�������û���������
		//�����������ݽ�ȥ ��¼�ɹ�����һ��User����
		//��¼ʧ�ܷ���null
		User user=dao.login(username,password);
		//4.�ж����user���󲻵���null����ת��
		//�������null �������������û������������
		System.out.println(user+":");
		if(user!=null) {//��¼�ɹ�!
			//�ж��Ƿ���Ҫ��ס����
			if(rem!=null) {//��ס����
				Cookie c1=new Cookie("username",URLEncoder.encode(username,"UTF-8"));
				Cookie c2=new Cookie("password",URLEncoder.encode(password,"UTF-8"));
				response.addCookie(c1);
				response.addCookie(c2);
			}
			//��ס��¼״̬ ͨ��session
			HttpSession session=request.getSession();
			
			session.setAttribute("user", user);
			
			
			
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
		}else {//��¼ʧ��
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.print("�û������������");
			pw.close();
		}
	}

}
