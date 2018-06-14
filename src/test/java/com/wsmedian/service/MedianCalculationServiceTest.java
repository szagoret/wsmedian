package com.wsmedian.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MedianCalculationServiceTest {

    @Autowired
    MedianService medianService;

    @Test
    public void testMedianParallelComputation() {

        String[] columns = {"col1", "col2", "col3"};

        Map<String, Map<String, List<Double>>> rawData = new HashMap<>();

        String label1 = "label1";
        Map<String, List<Double>> label1Data = new HashMap<>();
        label1Data.put(columns[0], Arrays.asList(8D, -3.4D, -5D, 11D));
        label1Data.put(columns[1], Arrays.asList(1D, 2D, 3D, 4D));
        label1Data.put(columns[2], Arrays.asList(4D, 3D, 2.0D, 11D));

        String label2 = "label2";
        Map<String, List<Double>> label2Data = new HashMap<>();
        label2Data.put(columns[0], Arrays.asList(8D, -3.4D));
        label2Data.put(columns[1], Arrays.asList(8D, 1D));
        label2Data.put(columns[2], Arrays.asList(4D, 3D));

        String label3 = "label3";
        Map<String, List<Double>> label3Data = new HashMap<>();
        label3Data.put(columns[0], Arrays.asList(8D));
        label3Data.put(columns[1], Arrays.asList(1D));
        label3Data.put(columns[2], Arrays.asList(4D));


        rawData.put(label1, label1Data);
        rawData.put(label2, label2Data);
        rawData.put(label3, label3Data);

        Map<String, Map<String, Double>> result = medianService.compute(rawData);


        // label 1
        assertThat(result.get(label1).get(columns[0])).isEqualTo(-3.4D);
        assertThat(result.get(label1).get(columns[1])).isEqualTo(2D);
        assertThat(result.get(label1).get(columns[2])).isEqualTo(3D);

        // label 2
        assertThat(result.get(label2).get(columns[0])).isEqualTo(-3.4D);
        assertThat(result.get(label2).get(columns[1])).isEqualTo(1D);
        assertThat(result.get(label2).get(columns[2])).isEqualTo(3D);

        // label 3
        assertThat(result.get(label3).get(columns[0])).isEqualTo(8D);
        assertThat(result.get(label3).get(columns[1])).isEqualTo(1D);
        assertThat(result.get(label3).get(columns[2])).isEqualTo(4D);



    }
}
