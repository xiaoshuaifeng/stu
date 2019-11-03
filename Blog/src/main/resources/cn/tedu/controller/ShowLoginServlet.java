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
		
		/*	�ж�Session���Ƿ���User����
		 *	����˵����¼�ɹ���ֱ����ʾ��ҳ
		 */
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if(user!=null) {//��½��
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;//������벻��Ҫִ��
		}
		
		String username="";
		String password="";
		//��cookie��ȡ���û���������
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
		//�ѵõ����û��������봫�ݵ�ҳ������ʾ
		Context context=new Context();
		username=URLDecoder.decode(username,"utf-8");
		password=URLDecoder.decode(password,"utf-8");
		context.setVariable("username",username);
		context.setVariable("password",password);
		
		
		ThUtils.write("login",context, response);
	}
}
