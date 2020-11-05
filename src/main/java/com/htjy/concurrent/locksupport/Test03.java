package com.htjy.concurrent.locksupport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

public class Test03 {
    static MyContainer<Integer> myContainer = new MyContainer<>();

    public static void main(String[] args) throws InterruptedException {
        Thread t2 = new Thread(() -> {
            if (myContainer.size() != 5) {
                LockSupport.park();
            }
            System.out.println("Container size:5, t2 close");
        });
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                myContainer.add(i);
                System.out.println("add " + i);
                if (myContainer.size() == 5) {
                    LockSupport.unpark(t2);
                }

            }

        });

        t1.start();
        t2.start();
    }

    static class MyContainer<T> {
        List<T> list;

        public MyContainer() {
            //this.list = Collections.synchronizedList(new ArrayList<>());
            this.list = new ArrayList<>();
        }

        public void add(T obj) {
            this.list.add(obj);
        }

        public int size() {
            return this.list.size();
        }
    }

}
