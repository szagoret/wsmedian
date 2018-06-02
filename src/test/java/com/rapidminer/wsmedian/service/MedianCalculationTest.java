package com.rapidminer.wsmedian.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by szagoret
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MedianCalculationTest {

    @Test
    public void testMedianCalculation() {
        // no values
        assertThat(new MedianCalculation("noValues", new HashMap<>()).get().get("noValues")).isNull();

        // empty
        Map<String, List<Double>> emptyArray = new HashMap<>();
        emptyArray.put("col1", Collections.emptyList());
        assertThat(new MedianCalculation("empty", emptyArray).get().get("empty").get("col1")).isEqualTo(0D);

        // unordered array with even length
        Map<String, List<Double>> unorderedArray = new HashMap<>();
        unorderedArray.put("col1", Arrays.asList(5.23D, 4.67D, -6.9D, 1D, 2D, 3.456D));
        assertThat(new MedianCalculation("label", unorderedArray).get().get("label").get("col1")).isEqualTo(2D);
    }
}
