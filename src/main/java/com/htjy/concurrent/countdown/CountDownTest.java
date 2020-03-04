package com.htjy.concurrent.countdown;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created By willz
 * Date : 2020/3/4
 * Time: 10:15
 */
public class CountDownTest implements Runnable{
    private CountDownLatch countDownLatch;
    CountDownTest(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }
    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for(int i = 0; i < 5; i++)
        {
            Thread t = new Thread(new CountDownTest(countDownLatch), String.valueOf(i));
            t.start();
        }
        System.out.println("waitting....");
        countDownLatch.await(100, TimeUnit.MILLISECONDS);
        System.out.println("finish....");
    }

    @Override
    public void run() {
        System.out.println("Thread-"+ Thread.currentThread().getName() +"start:");
        if (Thread.currentThread().getName().equals("2")) {
            try {
                System.out.println("sleep...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.countDownLatch.countDown();
        System.out.println("Thread-"+ Thread.currentThread().getName() +"end:");
    }
}
