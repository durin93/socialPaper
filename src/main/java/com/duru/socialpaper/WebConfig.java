package com.duru.socialpaper;


import com.duru.socialpaper.Interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final String[] EXCLUDE_PATHS = {
//            "/api/users/**",
            "/api/users",
            "/api/users/login",
            "/api/profiles/{username}",
            "/error/**"
    };

    @Autowired
    private JwtInterceptor jwpInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwpInterceptor).addPathPatterns("/**")
        .excludePathPatterns(EXCLUDE_PATHS);
    }
}
