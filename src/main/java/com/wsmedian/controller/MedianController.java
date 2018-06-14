package com.wsmedian.controller;

import com.wsmedian.service.MedianService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by szagoret
 */

@RestController
@RequestMapping("/median")
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
