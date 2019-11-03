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
		//获取传递过来的参数
		String keyword=request.getParameter("keyword");
		//通过keyword查询相关文章
		ArticleDao dao=new ArticleDao();
		List<Article> list=dao.findByKeyword(keyword);
		System.out.println("搜索结果："+list);
		
		//借用文章列表页面 显示查询结果
		Context context=new Context();
		context.setVariable("list", list);
		ThUtils.write("list", context, response);
	}
}
