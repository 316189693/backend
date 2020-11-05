package com.htjy.concurrent.locksupport;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * print A1B2C3
 */
public class Test04 {
    final static char a = 'A';
    static char z = 'Z';
    private static ReentrantLock reentrantLock = new ReentrantLock(false);
    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {

        t1 = new Thread(() -> {
            for (char i = a; i <= z; i++) {
                char c = i;
                System.out.println(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }, "Thread_1");

        t2 = new Thread(() -> {
            LockSupport.park();
            for (int i = 1; i < 27; i++) {
                System.out.println(i);
                LockSupport.unpark(t1);
                LockSupport.park();
            }
        }, "Thread_2");
        t1.start();
        t2.start();
    }


}
