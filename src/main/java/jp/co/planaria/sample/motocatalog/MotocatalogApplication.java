package jp.co.planaria.sample.motocatalog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("jp.co.planaria.sample.motocatalog.mappers")
public class MotocatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(MotocatalogApplication.class, args);
	}

}
