package com.rapidminer.wsmedian.controller;

import com.rapidminer.wsmedian.service.MedianService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by szagoret
 */

@RestController
public class MedianController {

    private final MedianService medianService;

    public MedianController(MedianService medianService) {
        this.medianService = medianService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Map<String, Double>>> calculateMedian(@RequestBody Map<String, Map<String, List<Double>>> rawData) {
        return ResponseEntity.ok(medianService.compute(rawData));
    }

}
