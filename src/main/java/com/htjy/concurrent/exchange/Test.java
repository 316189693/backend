package com.htjy.concurrent.exchange;

import java.util.concurrent.Exchanger;

public class Test {
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                String s = Thread.currentThread().getName() + " " + String.valueOf(Math.random());

                try {
                    s = exchanger.exchange(s);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":" + s);
            }, "thread_" + i).start();
        }
    }
}
