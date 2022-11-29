package com.orange.graduation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author orange.zhang
 * @date 2022/11/17 19:47
 */
@EnableSwagger2WebMvc
@Configuration
public class Knife4jConfiguration {

    @Bean(value = "dockerBean")
    public Docket dockerBean() {
        //指定使用Swagger2规范
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .description("# Orange Graduation APIS")
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("Test")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.orange.graduation.web.Controller"))     // Controller Scanning
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}