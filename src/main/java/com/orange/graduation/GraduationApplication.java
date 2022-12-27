package com.orange.graduation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;


@ServletComponentScan
@EnableCaching
@MapperScan({"com.orange.graduation.mapper"})
@SpringBootApplication(scanBasePackages="com.orange.graduation")
public class GraduationApplication {


    public static void main(String[] args) {
        System.setProperty("pagehelper.banner", "false"); // not output pagehelper banner
        SpringApplication.run(GraduationApplication.class, args);

    }

}
