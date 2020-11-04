package com.htjy.concurrent.phaser;

import com.htjy.concurrent.countdown.CountDownTest;

import java.util.concurrent.Phaser;

/**
 * Created By willz
 * Date : 2020/3/4
 * Time: 10:45
 */
public class PhaserTest implements Runnable {
    private Phaser phaser;

    PhaserTest(Phaser phaser) {
        this.phaser = phaser;
    }

    public static void main(String[] args) {
        System.out.println("Phaser test begin: ");
        Phaser phaser = new Phaser();
        phaser.register();
        for (int i = 0; i < 5; i++) {
            phaser.register();
            Thread t = new Thread(new PhaserTest(phaser), String.valueOf(i));
            t.start();
        }
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phaser test end: ");
    }

    @Override
    public void run() {

        System.out.println("Thread-" + Thread.currentThread().getName() + "-start:");
        this.phaser.arriveAndAwaitAdvance();
        System.out.println("Thread-" + Thread.currentThread().getName() + "-end:");

    }
}
