package com.rapidminer.wsmedian.service;

import com.rapidminer.wsmedian.util.MultiMapData;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by szagoret
 */

public class MedianCalculation implements Supplier<Map<String, Map<String, Double>>> {

    private final String key;
    private Map<String, List<Double>> columns;

    public MedianCalculation(String key, Map<String, List<Double>> inputData) {
        this.key = key;
        this.columns = inputData;
    }

    private static Double selectKth(Double[] inputArray, int k) {
        if (inputArray == null || inputArray.length == 0 || inputArray.length <= k)
            return 0d;

        if (inputArray.length == 1) {
            return inputArray[0];
        }

        int from = 0, to = inputArray.length - 1;

        // if from == to we reached the kth element
        while (from < to) {
            int r = from, w = to;
            Double mid = inputArray[(r + w) / 2];

            // stop if the reader and writer meets
            while (r < w) {

                if (inputArray[r] >= mid) { // put the large values at the end
                    Double tmp = inputArray[w];
                    inputArray[w] = inputArray[r];
                    inputArray[r] = tmp;
                    w--;
                } else { // the value is smaller than the pivot, skip
                    r++;
                }
            }

            // if we stepped up (r++) we need to step one down
            if (inputArray[r] > mid)
                r--;

            // the r pointer is on the end of the first k elements
            if (k <= r) {
                to = r;
            } else {
                from = r + 1;
            }
        }

        return inputArray[k];
    }

    @Override
    public Map<String, Map<String, Double>> get() {

        final MultiMapData<String, String, Double> result = new MultiMapData();


        for (Map.Entry<String, List<Double>> columnEntry : columns.entrySet()) {

            String columnName = columnEntry.getKey();
            Double[] values = columnEntry.getValue().toArray(new Double[0]);

            // find the array middle
            int kthElement = values.length % 2 != 0 ? (values.length / 2) : ((values.length - 1) / 2);
            Double medianValue = selectKth(values, kthElement);

            result.put(key, columnName, medianValue);
        }

        return result.getMap();

    }
}
