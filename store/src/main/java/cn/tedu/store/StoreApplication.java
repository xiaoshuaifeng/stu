package cn.tedu.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//由于这是项目中第1次创建持久层接口，还应该在`StoreApplication`
//启动类之前添加`@MapperScan("cn.tedu.store.mapper")`注解，以配置接口文件的位置。
@MapperScan("cn.tedu.store.mapper")
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

}
