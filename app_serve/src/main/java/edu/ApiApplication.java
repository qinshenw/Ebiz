package edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 项目启动类
 *
 * @author 执笔
 * @date 23:12
 */
@SpringBootApplication
@MapperScan(basePackages = {"edu.mapper"})
public class ApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new SpringApplication(ApiApplication.class).run(args);
    }
}