package cn.tedu.utils;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

@SuppressWarnings("unused")
public class DBUtils {
	private static 	BasicDataSource ds;
	static {
		//读取配置文件
		Properties p=new Properties();
		InputStream ips=DBUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
		try {
			p.load(ips);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String driver = p.getProperty("driver");
		String url = p.getProperty("url");
		String username = p.getProperty("username");
		String password = p.getProperty("password");

		//				//注册驱动
		//				Class.forName(driver);
		//				//创建连接对象
		//				Connection conn=DriverManager
		//						.getConnection(url,username,password);
		//创建连接池对象
		ds=new  BasicDataSource();
		//设置连接信息
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		//设置初始连接数量
		ds.setInitialSize(3);
		ds.setMaxActive(5);//最大连接数量
	}

	public static Connection getConn() throws Exception {


		//从连接池中获取连接并返回
		return ds.getConnection();
	}
}
