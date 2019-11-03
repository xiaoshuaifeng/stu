package cn.tedu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thymeleaf.context.Context;

import cn.tedu.dao.ArticleDao;
import cn.tedu.dao.LinkDao;
import cn.tedu.dao.TagDao;
import cn.tedu.entity.Article;
import cn.tedu.entity.Link;
import cn.tedu.entity.Tag;
import cn.tedu.entity.User;
import cn.tedu.utils.ThUtils;


@SuppressWarnings("serial")
public class HomeServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Context context=new Context();
		
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if(user!=null) {//登录过
			context.setVariable("user", user);
		}else {//没登录过
			response.sendRedirect(request.getContextPath()+"/ShowLoginServlet");
			return;//后面的代码不执行
		}
		
		//通过dao查询数据
		ArticleDao dao=new ArticleDao();
		List<Article> list=dao.getHomeList();
		//给页面传递第一篇文章
		context.setVariable("first", list.get(0));
		//给页面传递第2,3,4篇文章
		context.setVariable("second", list.subList(1, 4));
		//给页面传递第5,6,7,8篇文章
		context.setVariable("normal",list.subList(4,list.size()));
		//查询最新文章列表
		List<Article> newList=dao.getNewList();
		
		System.out.println("最新文章列表："+newList);
		//把列表传递到页面
		context.setVariable("newList", newList);
		//查询评论最热
		List<Article> commentList=dao.getCommentList();
		context.setVariable("commentList", commentList);
		//查询浏览最多
		List<Article> viewList=dao.getViewList();
		context.setVariable("viewList", viewList);
		
		//查询标签信息
		TagDao tagDao=new TagDao();
		List<Tag> tags=tagDao.getTopList();
		//把得到的集合传递到页面
		context.setVariable("tags", tags);
		
		//	实现友情链接步骤
		 // 	创建Link实体(title,address)
		 // 	创建LinkDao 提供findAll方法返回集合
		 //	在当前位置创建dao 并获取集合
		 LinkDao linkDao=new LinkDao();
		 List<Link> links=linkDao.findAll();
		 System.out.println("links:"+links);
		 //	把集合传递到页面中 在页面中控制显示
		 context.setVariable("links", links);
		
		
		ThUtils.write("index", context, response);
		
	}
}
