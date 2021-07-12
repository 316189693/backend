package com.htjy.weakreference;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class MyWR01 {
   /* public static void main(String[] args) {
        List<WeakHashMap<byte[][], byte[][]>> maps = new ArrayList<WeakHashMap<byte[][], byte[][]>>();
        for (int i = 0; i < 1000; i++) {
            WeakHashMap<byte[][], byte[][]> d = new WeakHashMap<byte[][], byte[][]>();
            d.put(new byte[5000][5000], new byte[5000][5000]);
            maps.add(d);
            System.gc();
            System.err.println(i);
        }
    }*/
     public static void main(String[] args) {
        List<WeakHashMap<byte[][], byte[][]>> maps = new ArrayList<WeakHashMap<byte[][], byte[][]>>();
        for (int i = 0; i < 1000; i++) {
            WeakHashMap<byte[][], byte[][]> d = new WeakHashMap<byte[][], byte[][]>();
            d.put(new byte[5000][5000], new byte[5000][5000]);
            maps.add(d);
            System.gc();
            System.err.println(i);
            //元素并不存在，只达到访问WeakHashMap方法目的
   //         System.out.println(d.get(0));
        }
    }
}
