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
		if(user!=null) {//��¼��
			context.setVariable("user", user);
		}else {//û��¼��
			response.sendRedirect(request.getContextPath()+"/ShowLoginServlet");
			return;//����Ĵ��벻ִ��
		}
		
		//ͨ��dao��ѯ����
		ArticleDao dao=new ArticleDao();
		List<Article> list=dao.getHomeList();
		//��ҳ�洫�ݵ�һƪ����
		context.setVariable("first", list.get(0));
		//��ҳ�洫�ݵ�2,3,4ƪ����
		context.setVariable("second", list.subList(1, 4));
		//��ҳ�洫�ݵ�5,6,7,8ƪ����
		context.setVariable("normal",list.subList(4,list.size()));
		//��ѯ���������б�
		List<Article> newList=dao.getNewList();
		
		System.out.println("���������б�"+newList);
		//���б��ݵ�ҳ��
		context.setVariable("newList", newList);
		//��ѯ��������
		List<Article> commentList=dao.getCommentList();
		context.setVariable("commentList", commentList);
		//��ѯ������
		List<Article> viewList=dao.getViewList();
		context.setVariable("viewList", viewList);
		
		//��ѯ��ǩ��Ϣ
		TagDao tagDao=new TagDao();
		List<Tag> tags=tagDao.getTopList();
		//�ѵõ��ļ��ϴ��ݵ�ҳ��
		context.setVariable("tags", tags);
		
		//	ʵ���������Ӳ���
		 // 	����Linkʵ��(title,address)
		 // 	����LinkDao �ṩfindAll�������ؼ���
		 //	�ڵ�ǰλ�ô���dao ����ȡ����
		 LinkDao linkDao=new LinkDao();
		 List<Link> links=linkDao.findAll();
		 System.out.println("links:"+links);
		 //	�Ѽ��ϴ��ݵ�ҳ���� ��ҳ���п�����ʾ
		 context.setVariable("links", links);
		
		
		ThUtils.write("index", context, response);
		
	}
}
