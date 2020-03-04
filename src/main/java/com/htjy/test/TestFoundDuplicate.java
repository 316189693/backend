package com.htjy.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestFoundDuplicate {
    public static void main(String[] args) {
        List<Item> list = new ArrayList();
        list.add(new Item("10","20"));
        list.add(new Item("10","20"));
        list.add(new Item("20","20"));
        list.add(new Item("20","20"));
        list.add(new Item("30","20"));
        list.add(new Item("50","20"));
        List<String> resultList = list.stream().collect(Collectors.collectingAndThen(Collectors
                .groupingBy(o -> o.code, Collectors.counting()),map->{
            map.values().removeIf(size -> size == 1);
            List<String> tempList = new ArrayList(map.keySet());
            return tempList;
        }));

        System.out.println(String.join(",", resultList));
        int i =5;
        System.out.println(i);
        System.out.println(numberOfLeadingZeros(i));
    }

        public static int numberOfLeadingZeros(int i) {
        // HD, Figure 5-6

        if (i == 0)
            return 32;
        int n = 1;
        if (i >>> 16 == 0) { n += 16; i <<= 16; }
        if (i >>> 24 == 0) { n +=  8; i <<=  8; }
        if (i >>> 28 == 0) { n +=  4; i <<=  4; }
        if (i >>> 30 == 0) { n +=  2; i <<=  2; }
        n -= i >>> 31;
        return n;
    }
}
