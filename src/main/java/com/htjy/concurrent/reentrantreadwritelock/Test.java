package com.htjy.concurrent.reentrantreadwritelock;

import javax.annotation.security.RunAs;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test {
    private static ReadWriteLock lock = new ReentrantReadWriteLock();
    static  Lock read = lock.readLock();
    static Lock write = lock.writeLock();
    private static int v = 0;
    public static void main(String[] args) {

        for(int i = 0; i< 10; i++){
            new Thread(new PersonWrite("write"+i)).start();
            new Thread(new PersonRead("read"+i)).start();
        }

    }

    static class PersonRead implements Runnable {
        private String name;
        public PersonRead(String name){
            this.name = name;
        }
        @Override
        public void run() {
            try{

                read.lock();
                System.out.println(this.name + "read:"+v);
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            finally {
                read.unlock();
            }
        }
    }
    static class PersonWrite implements Runnable {
        private String name;
        public PersonWrite(String name){
            this.name = name;
        }
        @Override
        public void run() {
            try{
                read.lock();
                v++;
                System.out.println(this.name + "write:"+v);
                System.out.println();
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                read.unlock();
            }
        }
    }
}

