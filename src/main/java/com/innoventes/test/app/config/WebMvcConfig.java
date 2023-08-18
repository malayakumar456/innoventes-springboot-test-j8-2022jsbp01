package com.innoventes.test.app.config;

import com.innoventes.test.app.interceptor.WebSiteURLValidationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private WebSiteURLValidationInterceptor webSiteURLValidationInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webSiteURLValidationInterceptor).addPathPatterns("/api/companies");
    }
}
