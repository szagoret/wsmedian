package com.rapidminer.wsmedian.controller.advice;

import com.rapidminer.wsmedian.controller.MedianController;
import com.rapidminer.wsmedian.model.QueryHistory;
import com.rapidminer.wsmedian.repository.QueryHistoryRepository;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

/**
 * Created by szagoret
 */

@ControllerAdvice(assignableTypes = MedianController.class)
public class MedianControllerAdvice implements ResponseBodyAdvice<Map<String, Map<String, Double>>> {

    private final QueryHistoryRepository queryHistoryRepository;

    public MedianControllerAdvice(QueryHistoryRepository queryHistoryRepository) {
        this.queryHistoryRepository = queryHistoryRepository;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Map<String, Map<String, Double>> beforeBodyWrite(Map<String, Map<String, Double>> body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Long startTime = (Long) ((ServletServerHttpRequest) request).getServletRequest().getAttribute("Start-Time");
        Long endTime = Instant.now().toEpochMilli();
        Long elapsedTime = endTime - startTime;
        String host = request.getRemoteAddress().getHostName();

        queryHistoryRepository.save(new QueryHistory(body, elapsedTime, new Date(), host));

        return body;
    }
}
