package com.htjy.concurrent.reentrantreadwritelock;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Test03 {
    public static void main(String[] args) {
        double[] test = new double[]{0,1,2,3,4,3.5};
        double[] rst = simpleMovingAverage(test, 3);
        System.out.println("RST:");
        for (double item : rst) {
            System.out.println(item);
        }
        System.out.println();
    }
    public static double[] simpleMovingAverage(double[] values, int n) {
        double[] sums = Arrays.copyOf(values, values.length);
        Arrays.parallelPrefix(sums, Double::sum);
        int start = n -1;
        return IntStream.range(start, sums.length)
                .mapToDouble(i->{
                    return i;
                }).toArray();

    }
}
