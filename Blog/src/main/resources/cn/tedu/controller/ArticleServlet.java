package cn.tedu.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.context.Context;

import cn.tedu.dao.ArticleDao;
import cn.tedu.entity.Article;
import cn.tedu.utils.ThUtils;


@SuppressWarnings("serial")
public class ArticleServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.��ȡ���ݹ�����oId
		String oId=request.getParameter("oId");
		System.out.println(oId);
		//2.����ArticleDao
		ArticleDao dao=new ArticleDao();
		//�������+1
		dao.addViowCount(oId);
		//3.����dao��findById�����õ�Article����
		Article a=dao.findById(oId);
		System.out.println(a);
		//4.���õ���Article���󴫵ݵ�ҳ����
		Context context=new Context();
		context.setVariable("a",a);
		//5.�滻article.htmlҳ���е���������
		ThUtils.write("article", context, response);
		
	}
}
