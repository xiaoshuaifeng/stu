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
		//1.获取传递过来的oId
		String oId=request.getParameter("oId");
		System.out.println(oId);
		//2.创建ArticleDao
		ArticleDao dao=new ArticleDao();
		//让浏览量+1
		dao.addViowCount(oId);
		//3.调用dao的findById方法得到Article对象
		Article a=dao.findById(oId);
		System.out.println(a);
		//4.将得到的Article对象传递到页面中
		Context context=new Context();
		context.setVariable("a",a);
		//5.替换article.html页面中的文章内容
		ThUtils.write("article", context, response);
		
	}
}
