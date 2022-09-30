package com.htjy.ratelimiter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class testarray {
    private final Cat[] a;
    private final int[] c = new int[]{4,5,6};
    testarray(){
        Cat cat1 = new Cat("Cat 1", "Skill 1");
        Cat cat2 = new Cat("Cat 2", "Skill 2");
        a = new Cat[2];
        a[0]=cat1;
        a[1]=cat2;
    }

    public static void main(String[] args) {
        testarray testarray = new testarray();
       testarray.changeObjArray();
       testarray.changeIntArray();
       testarray.testRemove();

    }
    // 造成只有1,5被删除, 3, 6占用原来的0，1的位置
    public void testRemove(){
        List<Integer> list= new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(6);
        for (int i = 0; i<list.size(); i++) {

            System.out.println(i);
            System.out.println(list.get(i));
            list.remove(i);
        }
        List<Integer> list2= new ArrayList<>();
        list2.add(1);
        list2.add(3);
        list2.add(5);
        list2.add(6);
        // 这个报数组越界， 因为foreach的index固定了
        for (int i: list
             ) {
            System.out.println(i);
            System.out.println(list2.get(i));
            list2.remove(i);
        }
    }
    public void changeObjArray(){
        Cat[] b = this.a;
        b[0].setName("Cat 3");
        this.a[1].setName("Cat 4");
        for (Cat i: b
        ) {
            System.out.println(i.getName());
        }
        for (Cat i: a
        ) {
            System.out.println(i.getName());
        }
    }
    public void changeIntArray(){
        int[] b = this.c;
        b[0] =10;
        this.c[1] = 11;
        for (int i: b
        ) {
            System.out.println(i);
        }
        for (int i: this.c
        ) {
            System.out.println(i);
        }
    }
}
