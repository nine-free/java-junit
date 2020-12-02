package cn.soft1010.java.junit.config;

import cn.soft1010.java.junit.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by zhangjifu on 2020/12/2.
 */
@Component
public class InterceptorConfig {

    @Bean
    LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Bean
    public WebMvcConfigurer interceptor() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(loginInterceptor()).addPathPatterns("/**");
            }
        };
    }
}
