package com.htjy.concurrent.semaphore;

import com.htjy.concurrent.phaser.PaserTest02;

import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;

/**
 * Created By willz
 * Date : 2020/3/4
 * Time: 11:29
 */
public class SemaphoreTest implements Runnable{
    private Semaphore semaphore;
    SemaphoreTest(Semaphore semaphore){
        this.semaphore = semaphore;
    }
    public static void main(String[] args) {
        System.out.println("semaphore test begin: ");
       Semaphore semaphore = new Semaphore(5);
        for(int i = 0; i < 10; i++)
        {

            Thread t = new Thread(new SemaphoreTest(semaphore), String.valueOf(i));
            t.start();
        }
        System.out.println("Phaser test end: ");
    }
    @Override
    public void run() {

        System.out.println("Thread-"+ Thread.currentThread().getName() +"-start:");
        try {
            this.semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread-"+ Thread.currentThread().getName() +"-end:");
        this.semaphore.release();
    }
}
