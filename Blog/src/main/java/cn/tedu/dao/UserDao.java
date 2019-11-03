package cn.tedu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import cn.tedu.entity.User;
import cn.tedu.utils.DBUtils;

//Data Access Object
public class UserDao {
	public void insert(User user) {
		try (Connection conn = DBUtils.getConn();) {
			String sql="insert into user(userName,password,email) values(?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			//执行
			ps.executeUpdate();
			System.out.println("注册完毕");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public User login(String username, String password) {
		 try (Connection conn = DBUtils.getConn();) {
			String sql = "select oId from user "
					+ "where userName=? and password=?";
			PreparedStatement ps = 
					conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			//执行SQL
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int oId = rs.getInt(1);
				//创建用户对象返回
				User user = new User(oId, 
						username, password, null);
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
