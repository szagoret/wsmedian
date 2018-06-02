package com.rapidminer.wsmedian.service;

import java.util.List;
import java.util.Map;

/**
 * Created by szagoret
 */

public interface MedianService {

    Map<String, Map<String, Double>> compute(Map<String, Map<String, List<Double>>> rawData);
}
