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
		//1.获取传递过来的参数
		String title=request.getParameter("title");
		String abs=request.getParameter("abs");
		String content=request.getParameter("content");
		String imgName=request.getParameter("imgName");
		String putTop=request.getParameter("putTop");
		//如果置顶是1 不置顶是0
		int isTop = putTop==null?0:1;
		//2.把参数封装到Article实体中
		Article a=new Article(0, title,abs,0,0,isTop,System.currentTimeMillis(),System.currentTimeMillis(), imgName,null, content, null, null);
		System.out.println(a);
		//3.创建ArticleDao对象
		ArticleDao dao=new ArticleDao();
		//4.调用dao的save方法把article传递进去
		dao.save(a);
		//5.重定向到文章列表页面
		response.sendRedirect(request.getContextPath()+"/ListServlet");
		
	}


}
