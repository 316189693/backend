package com.htjy.concurrent.locksupport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Test02 {
    static MyContainer<Integer> myContainer = new MyContainer<>();
    static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                myContainer.add(i);
                System.out.println("add "+i);
                if(myContainer.size() == 5) {
                    countDownLatch.countDown();
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
        Thread t2 = new Thread(() -> {
            if(myContainer.size() != 5) {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Container size:5, t2 close");
            countDownLatch.countDown();
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
