package cn.tedu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.context.Context;

import cn.tedu.dao.ArticleDao;
import cn.tedu.entity.Article;
import cn.tedu.utils.ThUtils;


@SuppressWarnings("serial")
public class SearchServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ���ݹ����Ĳ���
		String keyword=request.getParameter("keyword");
		//ͨ��keyword��ѯ�������
		ArticleDao dao=new ArticleDao();
		List<Article> list=dao.findByKeyword(keyword);
		System.out.println("���������"+list);
		
		//���������б�ҳ�� ��ʾ��ѯ���
		Context context=new Context();
		context.setVariable("list", list);
		ThUtils.write("list", context, response);
	}
}
