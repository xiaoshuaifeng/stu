package cn.tedu.mybatis;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Tests {
	
	ClassPathXmlApplicationContext ac;
	MapperScannerConfigurer msc;
	UserMapper userMapper;
	
	@Before
	public void doBefore() {
		ac = new ClassPathXmlApplicationContext(
			"spring-dao.xml");
		// 获取UserMapper接口的代理对象
		userMapper= ac.getBean("userMapper", UserMapper.class);
	}
	
	@Test
	public void insert() {
		// 测试功能
		User user = new User();
		user.setUsername("mapper");
		user.setPassword("8888");
		user.setAge(25);
		user.setPhone("13800139999");
		user.setEmail("mapper@163.com");
		Integer rows = userMapper.insert(user);
		System.out.println("rows=" + rows);
	}
	
	@Test
	public void getConnection() throws SQLException {
		BasicDataSource dataSource
			= ac.getBean("dataSource", BasicDataSource.class);
		System.out.println(dataSource.getConnection());
	}
	
	@Test
	public void deleteById() {
		Integer id = 5;
		Integer rows = userMapper.deleteById(id);
		System.out.println("rows=" + rows);
	}
	
	@Test
	public void update() {
		String password = "99999";
		Integer rows = userMapper.update(password);
		System.out.println("rows=" + rows);
	}
	
	@Test
	public void count() {
		Integer count = userMapper.count();
		System.out.println("count=" + count);
	}
	
	@Test
	public void findById() {
		Integer id = 7;
		User user = userMapper.findById(id);
		System.out.println(user);
	}
	
	@Test
	public void findAll() {
		List<User> users = userMapper.findAll();
		System.out.println("count="+users.size());
		for (User user : users) {
			System.out.println(user);
		}
	}
	
	@Test
	public void updateEmailById() {
		Integer id = 7;
		String email = "hello@163.com";
		Integer rows = userMapper.updateEmailById(id, email);
		System.out.println("rows=" + rows);
	}
	@After
	public void doAfter() {
		ac.close();
	}

}


