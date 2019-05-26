package com.fhypayaso.orochi.config;

import com.fhypayaso.orochi.interceptor.TokenInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor);
    }


    /**
     * 消除跨域
     *
     * @param registry Registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 配置可以被跨域的路径
        registry.addMapping("/**")
                // 允许所有的请求域名访问我们的跨域资源
                .allowedOrigins("*")
                // 允许所有的请求方法访问该跨域资源服务器
                .allowedMethods("*")
                // 允许所有的请求header访问
                .allowedHeaders("*");
    }
}


