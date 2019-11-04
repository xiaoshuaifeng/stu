package cn.tedu.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
	
	Integer insert(User user);
	
	Integer deleteById(Integer id);
	
	Integer update(String password);
	
	Integer count();
	
	User findById(Integer id);
	
	List<User> findAll();
	
	Integer updateEmailById(@Param("id") Integer id,@Param("email") String email);
}
