package com.rapidminer.wsmedian.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;

/**
 * Created by szagoret
 */

@Component
public class TimeExecutionInterceptor extends HandlerInterceptorAdapter {

    private static final String START_TIME = "Start-Time";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Long startTime = Instant.now().toEpochMilli();
        request.setAttribute(START_TIME, startTime);
        return true;
    }
}
