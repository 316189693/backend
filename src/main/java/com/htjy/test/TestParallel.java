package com.htjy.test;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestParallel {
    public static void main(String[] args) {
      test();
    }
    public static Map<Integer, Double> test(){
        int N = 10000;
        double fraction = 1.0/N;

        Map<Integer, Double> rst = IntStream.range(0, N).parallel()
                .mapToObj(n->twoDice()).collect(Collectors.groupingBy(side -> side, Collectors.summingDouble(n -> fraction)));
        return rst;
    }
    public static int twoDice(){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int i = random.nextInt(6) + 1;
        int j = random.nextInt(6) + 1;
        return i + j;
    }
}
