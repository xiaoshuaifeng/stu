package cn.tedu.store.mapper;

import cn.tedu.store.entity.User;

public interface UserMapper {
	//创建`cn.tedu.store.mapper.UserMapper`接口，并在接口中添加抽象方法：
	/**
	 * 	处理用户数据操作的持久层接口
	 */
	/**
	 *	插入用户数据
	 * @param user 用户数据
	 * @return 受影响的行数
	 */
	Integer insert(User user);

	/**
	 * 根据用户名查询用户数据
	 * @param username 用户名
	 * @return 匹配的用户数据，如果没有匹配的数据，则返回null
	 */
	User findByUsername(String username);
}
