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

    }
}
