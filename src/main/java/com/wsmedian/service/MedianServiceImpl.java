package com.wsmedian.service;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Created by szagoret
 */

@Service
public class MedianServiceImpl implements MedianService {

    @Override
    public Map<String, Map<String, Double>> compute(Map<String, Map<String, List<Double>>> rawData) {
        return rawData.entrySet()
                .stream()
                // create CompletableFuture task for each column
                .map(groupLabel -> CompletableFuture.supplyAsync(new MedianCalculation(groupLabel.getKey(), groupLabel.getValue())))
                // collect all tasks in a list
                .collect(Collectors.toList())
                .stream()
                // run median calculation in parallel for each column and wait for all of them to finish
                .map(CompletableFuture::join)
                // in order to merge result maps, converts each map to entry set
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                // merge result maps
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
