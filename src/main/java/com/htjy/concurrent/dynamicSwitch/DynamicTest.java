package com.htjy.concurrent.dynamicSwitch;

import java.util.Map;
import java.util.Set;

public class DynamicTest {

    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            while (true){
                Set<Map.Entry<Object,Object>> set = DynamicLoadProperties.getProperties().entrySet();
                for(Map.Entry<Object,Object> b : set){
                    System.out.println(b.getKey()+"="+b.getValue());
                }
                try{
                    Thread.sleep(1000);
                } catch(Exception e){e.printStackTrace();}
            }
        });
        thread.start();

    }


}
