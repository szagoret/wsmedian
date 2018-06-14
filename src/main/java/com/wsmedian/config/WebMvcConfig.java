package com.wsmedian.config;

import com.wsmedian.interceptor.TimeExecutionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by szagoret
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final TimeExecutionInterceptor timeExecutionInterceptor;

    public WebMvcConfig(TimeExecutionInterceptor timeExecutionInterceptor) {
        this.timeExecutionInterceptor = timeExecutionInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeExecutionInterceptor).addPathPatterns("/median");
    }
}
