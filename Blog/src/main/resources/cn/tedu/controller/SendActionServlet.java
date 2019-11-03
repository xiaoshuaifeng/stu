package cn.tedu.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tedu.dao.ArticleDao;
import cn.tedu.entity.Article;

@SuppressWarnings("serial")
public class SendActionServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.��ȡ���ݹ����Ĳ���
		String title=request.getParameter("title");
		String abs=request.getParameter("abs");
		String content=request.getParameter("content");
		String imgName=request.getParameter("imgName");
		String putTop=request.getParameter("putTop");
		//����ö���1 ���ö���0
		int isTop = putTop==null?0:1;
		//2.�Ѳ�����װ��Articleʵ����
		Article a=new Article(0, title,abs,0,0,isTop,System.currentTimeMillis(),System.currentTimeMillis(), imgName,null, content, null, null);
		System.out.println(a);
		//3.����ArticleDao����
		ArticleDao dao=new ArticleDao();
		//4.����dao��save������article���ݽ�ȥ
		dao.save(a);
		//5.�ض��������б�ҳ��
		response.sendRedirect(request.getContextPath()+"/ListServlet");
		
	}


}
