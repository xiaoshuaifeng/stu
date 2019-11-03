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
		//��ȡ�����ļ�
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

		//				//ע������
		//				Class.forName(driver);
		//				//�������Ӷ���
		//				Connection conn=DriverManager
		//						.getConnection(url,username,password);
		//�������ӳض���
		ds=new  BasicDataSource();
		//����������Ϣ
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		//���ó�ʼ��������
		ds.setInitialSize(3);
		ds.setMaxActive(5);//�����������
	}

	public static Connection getConn() throws Exception {


		//�����ӳ��л�ȡ���Ӳ�����
		return ds.getConnection();
	}
}
