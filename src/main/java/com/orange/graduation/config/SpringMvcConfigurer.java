package com.orange.graduation.config;

import com.orange.graduation.config.resolver.BodyParamsResolver;
import com.orange.graduation.config.resolver.QueryParamsResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author orange.zhang
 * @date 2022/11/17 20:43
 */
@Configuration
public class SpringMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new QueryParamsResolver());
        resolvers.add(new BodyParamsResolver());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET","POST","PUT","DELETE","HEAD","OPTIONS")
                .maxAge(3600)
                .allowedHeaders("*");
    }
}
