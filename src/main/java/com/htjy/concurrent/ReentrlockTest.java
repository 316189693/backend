package com.htjy.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrlockTest {

    private static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        Thread thread = null;
        for(int i = 0; i<100; i++) {
            new Thread(()->test(), "thread "+i).start();
        }
    }
    public static void test(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"Locking....");
            int i = Integer.MAX_VALUE;
            int j = 0;
           Thread.yield();
        }catch(Exception e){

        }finally {
            System.out.println(Thread.currentThread().getName()+"UNLocking....");
            lock.unlock();

        }


    }

}
