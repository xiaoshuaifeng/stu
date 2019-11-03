package cn.tedu.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.tedu.entity.User;

/**
 * 	����Ȩ�޼�������
 * @author 86138
 *
 */
public class AccessFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//���session���Ƿ��е�¼��Ϣ
		//1.����е�¼��Ϣ �ͷŹ�web����
		//2.���û�е�¼��Ϣ ���ض��򵽵�¼ҳ��
		
		//Ϊ�˷����ʹ�� ���������API����
		//��ServletRequestת��ΪHttpServletRequest
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		//ת���Ժ� req��request ����ͬһ������
		HttpSession session=req.getSession();
		//��ȡ��¼�û���Ϣ
		User user=(User) session.getAttribute("user");
		System.out.println("�û���"+user);
		if(user==null) {
			//û�е�¼��Ϣ Ҳ����û�е�¼
			System.out.println("�ض��򵽵�¼ҳ");
			String location=req.getContextPath()+"/ShowLoginServlet";
			res.sendRedirect(location);
		}else {
			//�е�¼��Ϣ Ҳ���ǵ�¼�ɹ�
			System.out.println("�Ź�web����");
			chain.doFilter(req, res);
			System.out.println("���������������");
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
