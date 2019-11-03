package cn.tedu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.tedu.entity.Tag;
import cn.tedu.utils.DBUtils;

public class TagDao {
	public List<Tag> getTagsByArticleId(int id){
		ArrayList<Tag> list = new ArrayList<Tag>();
		try (Connection conn = DBUtils.getConn();) {
			String sql=" select t.title from tag t join tag_article ta on ta.tag_oId=t.oId where article_oId=?";
			System.out.println("标签："+sql);
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				String title=rs.getString(1);
				Tag tag=new Tag(0,title,0);
				list.add(tag);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<Tag> getTopList() {
		ArrayList<Tag> list=new ArrayList<Tag>();
		try (Connection conn = DBUtils.getConn();) {
			String sql="select oId,title,referenceCount from tag order by referenceCount desc limit 0,5";
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery(sql);
			while(rs.next()) {
				int oId=rs.getInt(1);
				String title=rs.getString(2);
				int referenceCount=rs.getInt(3);
				//封装到对象并添加到集合
				list.add(new Tag(oId,title,referenceCount));
			}
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
