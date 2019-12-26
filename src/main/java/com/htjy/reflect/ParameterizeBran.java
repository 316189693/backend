package com.htjy.reflect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created By willz
 * Date : 12/26/2019
 * Time: 2:33 PM
 */
public class ParameterizeBran {
    List<String> list1;
    Set<String> set1;
    Map<String, Integer> map1;
    Map map2;
    Map.Entry<String, Long> map3;
    String[] array1;
    String name;
    Integer age;
    Cat cat;


    public int countCat() {
        return 5;
    }

    private String feedCat(String food){
        return "cat eat " + food;
    }

    protected static void catchMouse() {

    }

    public ParameterizeBran getP() {
        return new ParameterizeBran();
    }

    public Map<String, Integer> pair(){
        return new HashMap<>();
    }
}
