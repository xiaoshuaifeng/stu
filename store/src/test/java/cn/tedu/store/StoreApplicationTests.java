package cn.tedu.store;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.catalina.authenticator.DigestAuthenticator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private DataSource dataSource;

	@Test
	public void getConnection() throws Exception {
		Connection conn = dataSource.getConnection();
		System.err.println(conn);
	}
	
	@Test
	public void getMd5Password() {
		String password = "1234";
		String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
		System.err.println(md5Password);
	}
}
