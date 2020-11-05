package com.htjy.concurrent.locksupport;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * print A1B2C3
 */
public class Test05 {
    final static char a = 'A';
    static char z = 'Z';


    static Thread t1 = null, t2 = null;
    static   boolean flag = true;
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    public static void main(String[] args) {


        t1 = new Thread(() -> {
            for (char i = a; i <= z; i++) {
                lock.lock();
                try {
                    if (flag) {
                       char c = i;
                    System.out.println(c);
                    flag = false;
                        condition.signal();
                } else {
                        i--;
                    condition.await();
                }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        }, "Thread_1");

        t2 = new Thread(() -> {

            for (int i = 1; i < 27; i++) {
                lock.lock();
                try {
                    if (!flag) {
                        System.out.println(i);
                        flag = true;

                        condition.signal();
                    } else {
                        i--;
                        condition.await();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }, "Thread_2");
        t1.start();
        t2.start();
    }


}
