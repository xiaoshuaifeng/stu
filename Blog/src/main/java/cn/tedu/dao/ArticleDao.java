package cn.tedu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.tedu.entity.Article;
import cn.tedu.entity.Tag;
import cn.tedu.utils.DBUtils;

public class ArticleDao {
	public List<Article> getHomeList(){
		ArrayList<Article> list=new ArrayList<Article>();
		try (Connection conn = DBUtils.getConn();) {
			String sql="select a.oId,a.title,a.abstract,a.commentCount,a.viewCount,a.putTop,a.created,a.updated,a.imgName,u.username from article a join user u on a.authorId=u.oId order by a.putTop desc,a.created desc limit 0,8";
			Statement s=conn.createStatement();
			//执行查询
			ResultSet rs=s.executeQuery(sql);
			while(rs.next()) {
				int oId=rs.getInt(1);
				String title=rs.getString(2);
				String abs=rs.getString(3);
				int commentCount=rs.getInt(4);
				int viewCount=rs.getInt(5);
				int putTop=rs.getInt(6);
				long created=rs.getLong(7);
				long updated=rs.getLong(8);
				String imgName=rs.getString(9);
				String userName=rs.getString(10);
				Article a=new Article(oId, title, abs, commentCount, viewCount, putTop, created, updated, imgName, userName);
				//查询和该文章对应的标签
				TagDao dao=new TagDao();
				List<Tag> tags=dao.getTagsByArticleId(oId);

				a.setTags(tags);//让文章对象和标签建立关系

				//把实体添加到集合中
				list.add(a);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<Article> getNewList() {
		ArrayList<Article> list=new ArrayList<Article>();
		try (Connection conn = DBUtils.getConn();) {
			String sql="select oId,title from article order by created desc limit 0,5";
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery(sql);
			while(rs.next()) {
				int oId=rs.getInt(1);
				String title=rs.getString(2);
				Article a=new Article(oId, title);
				//添加到集合
				list.add(a);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<Article> getCommentList() {
		ArrayList<Article> list=new ArrayList<Article>();
		try (Connection conn = DBUtils.getConn();) {
			String sql="select oId,title from article order by commentCount desc limit 0,5";
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery(sql);
			while(rs.next()) {
				int oId=rs.getInt(1);
				String title=rs.getString(2);
				Article a=new Article(oId, title);
				//添加到集合
				list.add(a);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<Article> getViewList() {
		ArrayList<Article> list=new ArrayList<Article>();
		try (Connection conn = DBUtils.getConn();) {
			String sql="select oId,title from article order by viewCount desc limit 0,5";
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery(sql);
			while(rs.next()) {
				int oId=rs.getInt(1);
				String title=rs.getString(2);
				Article a=new Article(oId, title);
				//添加到集合
				list.add(a);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<Article> findAll() {
		ArrayList<Article> list=new ArrayList<Article>();
		try (Connection conn = DBUtils.getConn();) {
			String sql="select a.oId,a.title,a.abstract,a.commentCount,a.viewCount,a.putTop,a.created,a.updated,a.imgName,u.username from article a join user u on a.authorId=u.oId order by a.created desc limit 0,8";
			Statement s=conn.createStatement();
			//执行查询
			ResultSet rs=s.executeQuery(sql);
			while(rs.next()) {
				int oId=rs.getInt(1);
				String title=rs.getString(2);
				String abs=rs.getString(3);
				int commentCount=rs.getInt(4);
				int viewCount=rs.getInt(5);
				int putTop=rs.getInt(6);
				long created=rs.getLong(7);
				long updated=rs.getLong(8);
				String imgName=rs.getString(9);
				String userName=rs.getString(10);
				Article a=new Article(oId, title, abs, commentCount, viewCount, putTop, created, updated, imgName, userName);
				//查询和该文章对应的标签
				TagDao dao=new TagDao();
				List<Tag> tags=dao.getTagsByArticleId(oId);

				a.setTags(tags);//让文章对象和标签建立关系

				//把实体添加到集合中
				list.add(a);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<Article> findByKeyword(String keyword) {
		ArrayList<Article> list=new ArrayList<Article>();
		try (Connection conn = DBUtils.getConn();) {
			String sql="select a.oId,a.title,a.abstract,a.commentCount,a.viewCount,a.putTop,a.created,a.updated,a.imgName,u.username from article a join user u on a.authorId=u.oId where a.title like ?";
			PreparedStatement ps=conn.prepareStatement(sql);
			//替换?
			ps.setString(1, "%"+keyword+"%");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int oId=rs.getInt(1);
				String title=rs.getString(2);
				String abs=rs.getString(3);
				int commentCount=rs.getInt(4);
				int viewCount=rs.getInt(5);
				int putTop=rs.getInt(6);
				long created=rs.getLong(7);
				long updated=rs.getLong(8);
				String imgName=rs.getString(9);
				String userName=rs.getString(10);
				Article a=new Article(oId, title, abs, commentCount, viewCount, putTop, created, updated, imgName, userName);
				//查询和该文章对应的标签
				TagDao dao=new TagDao();
				List<Tag> tags=dao.getTagsByArticleId(oId);

				a.setTags(tags);//让文章对象和标签建立关系

				//把实体添加到集合中
				list.add(a);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public Article findById(String id) {
		try (Connection conn = DBUtils.getConn();) {
			String sql="select a.oId,a.title,a.abstract,a.commentCount,a.viewCount,a.putTop,a.created,a.updated,a.imgName,u.username,a.content from article a join user u on a.authorId=u.oId where a.oId=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, Integer.valueOf(id));
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				int oId=rs.getInt(1);
				String title=rs.getString(2);
				String abs=rs.getString(3);
				int commentCount=rs.getInt(4);
				int viewCount=rs.getInt(5);
				int putTop=rs.getInt(6);
				long created=rs.getLong(7);
				long updated=rs.getLong(8);
				String imgName=rs.getString(9);
				String userName=rs.getString(10);
				String content=rs.getString(11);
				Article a=new Article(oId, title, abs, commentCount, viewCount, putTop, created, updated, imgName, userName);
				a.setContent(content);
				//查询和该文章对应的标签
				TagDao dao=new TagDao();
				List<Tag> tags=dao.getTagsByArticleId(oId);
				a.setTags(tags);//让文章对象和标签建立关系
				return a;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void save(Article a) {
		try (Connection conn = DBUtils.getConn();) {
			String sql = "insert into article values(null,?,?,?,0,0,?,null,null,?,?,?,null,null,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, a.getTitle());
			ps.setString(2, a.getAbs());
			ps.setInt(3, 15);
			ps.setString(4, a.getContent());
			ps.setInt(5,  a.getPutTop());
			ps.setLong(6, a.getCreated());
			ps.setLong(7, a.getUpdated());
			ps.setString(8, a.getImgName());
			//执行SQL
			ps.executeUpdate();
			System.out.println("发布完成！！！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addViowCount(String oId) {
		try (Connection conn = DBUtils.getConn();) {
			String sql = "update article set viewCount=viewCount+1 where oId=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, Integer.valueOf(oId));
			ps.executeUpdate();
			System.out.println("浏览量增加完成");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
