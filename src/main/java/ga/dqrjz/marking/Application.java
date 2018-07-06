package ga.dqrjz.marking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("ga.dqrjz.marking.mapper")
public class Application {
	public static void main(String[] args) {
		// 启动代码
		SpringApplication.run(Application.class, args);
	}
	
}