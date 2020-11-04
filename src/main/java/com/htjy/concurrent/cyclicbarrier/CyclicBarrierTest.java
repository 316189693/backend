package com.htjy.concurrent.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created By willz
 * Date : 2020/3/4
 * Time: 10:29
 */
public class CyclicBarrierTest implements Runnable {
    private CyclicBarrier cyclicBarrier;

    CyclicBarrierTest(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    public static void main(String[] args) {
        System.out.println("Cyclic barrier test begin: ");
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("start run....");
            }
        });
        for (int i = 0; i < 20; i++) {
            Thread t = new Thread(new CyclicBarrierTest(cyclicBarrier), String.valueOf(i));
            t.start();
        }

    }

    @Override
    public void run() {
        System.out.println("Threa-" + Thread.currentThread().getName() + "-" + "start...");
        try {
            this.cyclicBarrier.await();
            if (!"1".equals(Thread.currentThread().getName())) {
                Thread.sleep(2000);
            }
            System.out.println("Threa-" + Thread.currentThread().getName() + "-" + "end...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }


    }
}
