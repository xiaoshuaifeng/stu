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
public class ListServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Context context=new Context();
		//查询时间降序的所有文字列表
		ArticleDao dao = new ArticleDao();
		List<Article> list = dao.findAll();
		System.out.println("!!!!!!!!!!!!!!!!!!"+list);
		//传递到页面
		context.setVariable("list", list);
		
		ThUtils.write("list", context, response);
	
	}
}
