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
		//��ȡ���ݹ����Ĳ���
		//�����Post���� ���Ҳ����п��������ĵĻ� ��Ҫִ�����´���
		request.setCharacterEncoding("UTF-8");
		//��ȡ����
		String userName=request.getParameter("name");
		String password=request.getParameter("pwd");
		String email=request.getParameter("email");
		User user=new User(0,userName,password,email);
		//����Dao���û�����
		UserDao dao=new UserDao();
		dao.insert(user);
		//��ҳ�淵��ע��ɹ�
//		response.setContentType("text/html;charset=utf-8");
//		PrintWriter pw = response.getWriter();
//		pw.print("ע��ɹ�");
//		pw.close();
		//�ض��򵽵�¼ҳ��
		response.sendRedirect(request.getContextPath()+"/ShowLoginServlet");
		
	}

}
